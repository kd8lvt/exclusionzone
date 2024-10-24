package com.kd8lvt.exclusionzone.item.Tools;

import com.kd8lvt.exclusionzone.block.util.ExclusionZoneFakePlayer;
import com.kd8lvt.exclusionzone.item.base.IHasResearchNotes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

import static com.kd8lvt.exclusionzone.registry.ModItems.ttStyle;

public class LoggingAxe extends AxeItem implements IHasResearchNotes {
    private static final List<ToolComponent.Rule> BREAK_RULES = List.of(ToolComponent.Rule.of(TagKey.of(Registries.BLOCK.getKey(),Identifier.of("minecraft:mineable/axe")),10));
    public static final TagKey<Block> LOGS_TAG = TagKey.of(Registries.BLOCK.getKey(), Identifier.of("minecraft:logs"));
    public final ArrayList<Pair<BlockPos,Integer>> scheduledBlocks = new ArrayList<>();
    private int tick = 0;
    public List<Text> tt = new ArrayList<>();
    public LoggingAxe() {
        super(Glasscutter.material, new Settings().rarity(Rarity.UNCOMMON).component(DataComponentTypes.TOOL,new ToolComponent(BREAK_RULES,2,1)));
        tt.addAll(Text.of("Chops down the whole tree at once, rather than leaving it floating there.").getWithStyle(ttStyle));
        tt.addAll(Text.of("Also great for debugging code!").getWithStyle(ttStyle));
    }

    @Override
    public List<Text> getTooltips() {
        return tt;
    }

    public boolean blockIsWood(BlockState state) { return state.isIn(LOGS_TAG);}

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        boolean original = super.postMine(stack, world, state, pos, miner);
        if (world.isClient || !scheduledBlocks.isEmpty()) return original;
        if (blockIsWood(state)) {
            stack.setDamage(stack.getDamage()-1);
            scheduledBlocks.addAll(findConnectedWood(world,pos));
        }
        return original;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        calculateTooltip(tt,stack,context,tooltip,type);
    }

    static List<Text> calculateTooltip(List<Text> tt, ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        ArrayList<Text> notes = new ArrayList<>(Text.translatable("tooltips.exclusionzone.research_notes.header").getWithStyle(ttStyle));
        for (int i=0;i<tt.size();i++) {
            notes.addAll(Text.stringifiedTranslatable(stack.getItem().getTranslationKey()+".research_notes_"+i).getWithStyle(ttStyle));
        }
        tooltip.addAll(notes);
        return tooltip;
    }

    private ArrayList<Pair<BlockPos,Integer>> findConnectedWood(World world, BlockPos pos) {
        ArrayList<Pair<BlockPos,Integer>> found = new ArrayList<>(List.of(new Pair<>(pos,0)));
        ArrayList<Pair<BlockPos,Integer>> visited = new ArrayList<>();
        ArrayList<BlockPos> checked = new ArrayList<>();
        int oldFoundSize = -999;
        int depth = 0;
        while (found.size() != oldFoundSize) {
            oldFoundSize=found.size();
            for (int idx=0;idx<found.size();idx++) {
                Pair<BlockPos,Integer> pair = found.get(idx);
                if (!visited.contains(pair)) {
                    for (Direction dir : Direction.values()) {
                        BlockPos target = pair.getLeft().offset(dir,1);
                        if (checked.contains(target) || found.stream().anyMatch(pair2->pair2.getLeft()==target)) continue;
                        if (blockIsWood(world.getBlockState(target))) {
                            found.add(new Pair<>(target,depth));
                        } else {
                            for (Direction dir2 : Direction.values()) {
                                BlockPos target2 = target.offset(dir2,1);
                                if (checked.contains(target2) || found.stream().anyMatch(pair2->pair2.getLeft()==target2)) continue;
                                if (blockIsWood(world.getBlockState(target2))) {
                                    found.add(new Pair<>(target2,depth));
                                }
                                checked.add(target2);
                            }
                        }
                        checked.add(target);
                    }
                    visited.add(pair);
                }
            }
            depth++;
        }
        return found;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (scheduledBlocks.isEmpty()) return;
        if (world.isClient) return;
        if (entity instanceof ExclusionZoneFakePlayer) return;
        if (scheduledBlocks.stream().filter(pair->pair.getRight()==0).toList().isEmpty()) tick++;
        if (tick % 5 != 0) return;
        ExclusionZoneFakePlayer fp = new ExclusionZoneFakePlayer((ServerWorld)world);
        fp.setCurrentHand(Hand.MAIN_HAND);
        fp.setStackInHand(Hand.MAIN_HAND,stack);
        for (int i=0;i<scheduledBlocks.size();i++) {
            Pair<BlockPos,Integer> pair = scheduledBlocks.get(i);
            if (pair.getRight() == 0) {
                fp.setPosition(pair.getLeft().toCenterPos());
                world.breakBlock(pair.getLeft(),true,fp);
                fp.getStackInHand(Hand.MAIN_HAND).damage(1,fp,EquipmentSlot.MAINHAND);
                scheduledBlocks.remove(pair);
                break;
            }
        }
        if (scheduledBlocks.stream().filter(pair->pair.getRight()==0).toList().isEmpty()) {
            for (int i=0;i<scheduledBlocks.stream().filter(pair->pair.getRight()>0).toList().size();i++) {
                Pair<BlockPos,Integer> pair = scheduledBlocks.stream().filter(p->p.getRight()>0).toList().get(i);
                pair.setRight(pair.getRight()-1);
            }
        }
        fp.remove(Entity.RemovalReason.DISCARDED);
    }
}
