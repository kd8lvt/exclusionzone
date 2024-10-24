package com.kd8lvt.exclusionzone.item.Tools;

import com.kd8lvt.exclusionzone.block.util.ExclusionZoneFakePlayer;
import com.kd8lvt.exclusionzone.item.base.BlockQueue;
import com.kd8lvt.exclusionzone.item.base.IHasResearchNotes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.kd8lvt.exclusionzone.registry.ModItems.ttStyle;

public class LoggingAxe extends AxeItem implements IHasResearchNotes {
    private static final List<ToolComponent.Rule> BREAK_RULES = List.of(ToolComponent.Rule.of(TagKey.of(Registries.BLOCK.getKey(),Identifier.of("minecraft:mineable/axe")),10));
    public static final TagKey<Block> LOGS_TAG = TagKey.of(Registries.BLOCK.getKey(), Identifier.of("minecraft:logs"));
    public static final LoggingAxeQueue queue = new LoggingAxeQueue();
    public List<Text> tt = new ArrayList<>();
    public LoggingAxe() {
        super(
                Glasscutter.material,
                new Settings()
                        .rarity(Rarity.UNCOMMON)
                        .component(DataComponentTypes.TOOL,new ToolComponent(BREAK_RULES,2,1))
                        .customDamage((stack, amount, entity, slot, breakCallback) -> {
                            if (amount > stack.getMaxDamage()-stack.getDamage()) queue.removeForHolder(entity);
                            return amount;
                        }));
        tt.addAll(Text.of("Chops down the whole tree at once, rather than leaving it floating there.").getWithStyle(ttStyle));
        tt.addAll(Text.of("Also great for debugging code!").getWithStyle(ttStyle));
    }

    @Override
    public List<Text> getTooltips() {
        return tt;
    }

    public static boolean blockIsWood(BlockState state) { return state.isIn(LOGS_TAG);}

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        boolean original = super.postMine(stack, world, state, pos, miner);
        if (world.isClient || !queue.isEmpty()) return original;
        if (blockIsWood(state)) {
            stack.setDamage(stack.getDamage()-1);
            findAndQueueConnectedWood(world,pos,stack,miner);
        }
        return original;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getSide() == Direction.UP) {
            World world = context.getWorld();
            BlockState state = world.getBlockState(context.getBlockPos());
            if (this.getStrippedState(state).isEmpty()) {
                PlayerEntity player = context.getPlayer();
                if (player != null && player.getInventory().contains(ItemTags.SAPLINGS)) {
                    Inventory inv = player.getInventory();
                    ItemStack saplingStack = null;
                    for (int i = 0; i < inv.size(); i++) {
                        if (inv.getStack(i).isIn(ItemTags.SAPLINGS)) {
                            saplingStack = inv.getStack(i);
                            break;
                        }
                    }
                    if (saplingStack != null) {
                        ActionResult res = saplingStack.useOnBlock(new ItemUsageContext(world,player,context.getHand(),saplingStack,new BlockHitResult(context.getHitPos(),context.getSide(),context.getBlockPos(),context.hitsInsideBlock())));
                        if (res.isAccepted()) return ActionResult.SUCCESS_NO_ITEM_USED;
                    }
                }
            }
        }
        return super.useOnBlock(context);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        calculateTooltip(tt,stack,context,tooltip,type);
    }

    static List<Text> calculateTooltip(List<Text> tt, ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        ArrayList<Text> notes = new ArrayList<>(Text.translatable("tooltips.exclusionzone.research_notes.header").getWithStyle(ttStyle));
        for (int i=0;i<tt.size();i++) {
            notes.addAll(Text.stringifiedTranslatable(stack.getItem().getTranslationKey()+".research_notes_"+i).getWithStyle(ttStyle));
        }
        tooltip.addAll(notes);
        return tooltip;
    }

    private void findAndQueueConnectedWood(World world, BlockPos pos, ItemStack stack, Entity holder) {
        List<BlockQueue.QueuedBlock> found = new ArrayList<>(List.of(new BlockQueue.QueuedBlock(pos, 0, stack, holder)));
        ArrayList<BlockQueue.QueuedBlock> visited = new ArrayList<>();
        ArrayList<BlockPos> checked = new ArrayList<>();
        int oldFoundSize = -999;
        int depth = 0;
        while (found.size() != oldFoundSize) {
            oldFoundSize=found.size();
            for (int idx=0;idx<found.size();idx++) {
                BlockQueue.QueuedBlock qBlock = found.get(idx);
                if (!visited.contains(qBlock)) {
                    checkAndAddAdjacent(qBlock.pos,depth,qBlock.stack,holder,found,checked,world,true);
                    visited.add(qBlock);
                }
            }
            depth++;
        }
        queue.addAll(found);
    }

    private static void checkAndAddAdjacent(BlockPos pos, int depth, ItemStack stack, Entity holder, List<BlockQueue.QueuedBlock> found, ArrayList<BlockPos> checked, World world, boolean checkDiagonals) {
        for (Direction dir : Direction.values()) {
            BlockPos target = pos.offset(dir,1);
            if (checked.contains(target)) continue;
            if (blockIsWood(world.getBlockState(target))) {
                found.add(new BlockQueue.QueuedBlock(target,depth,stack,holder));
            } else if (checkDiagonals) {
                //Check diagonals, but not any further.
                checkAndAddAdjacent(target,depth,stack,holder,found,checked,world,false);
            }
            checked.add(target);
        }
    }

    public static class LoggingAxeQueue extends BlockQueue {
        private int tick = 0;
        ExclusionZoneFakePlayer fp = null;
        public LoggingAxeQueue() {super("logging_axe");}
        @Override
        public void processQueue(MinecraftServer server) {
            if (queue.isEmpty()) return;
            boolean queueHasNext = !Objects.isNull(nextInQueue());
            if (!queueHasNext) tick++;
            if (tick % 5 != 0) return;
            QueuedBlock qBlock = nextInQueue();
            if (queueHasNext && qBlock.holder != null) {
                World world = qBlock.holder.getWorld();
                if (fp == null) fp = new ExclusionZoneFakePlayer((ServerWorld) world);
                fp.setCurrentHand(Hand.MAIN_HAND);
                fp.setStackInHand(Hand.MAIN_HAND, qBlock.stack);
                fp.setPosition(qBlock.pos.toCenterPos());
                world.breakBlock(qBlock.pos, true, fp);
                if (qBlock.stack.getDamage()+1 > qBlock.stack.getMaxDamage()) removeForHolder(qBlock.holder);
                else queue.remove(qBlock);
                qBlock.stack.damage(1, fp, EquipmentSlot.MAINHAND);
            } else {
                for (int i = 0; i< queue.stream().filter(pair->pair.getDepth()>0).toList().size(); i++) {
                    qBlock = queue.stream().filter(b -> b.getDepth()>0).toList().get(i);
                    qBlock.process();
                }
            }
        }

        @Override
        public void onServerShuttingDown() {
            if (fp != null) fp.remove(Entity.RemovalReason.DISCARDED);
            super.onServerShuttingDown();
        }
    }

}
