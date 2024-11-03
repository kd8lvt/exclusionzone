package com.kd8lvt.exclusionzone.content.item.Tools;

import com.kd8lvt.exclusionzone.content.block.util.ExclusionZoneFakePlayer;
import com.kd8lvt.exclusionzone.content.item.base.DeepItemStorageHelper;
import com.kd8lvt.exclusionzone.content.item.base.IHasResearchNotes;
import com.kd8lvt.exclusionzone.registry.ModDataComponents;
import net.minecraft.component.ComponentType;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class VoidTear extends Item implements IHasResearchNotes {
    public static final ComponentType<NbtComponent> STORED_ITEMS_COMPONENT = (ComponentType<NbtComponent>) ModDataComponents.get("deep_storage");
    public static final ComponentType<Boolean> ENABLED_COMPONENT = (ComponentType<Boolean>) ModDataComponents.get("enabled");
    public static final NbtCompound DEFAULT_STORED_ITEMS = new NbtCompound();
    static {
        DEFAULT_STORED_ITEMS.put("stored",new NbtList());
    }
    public static final int MAX_STORED_TYPES = 1;
    public static final int MAX_STORED_COUNT = 8192;
    public static final List<Text> tt = List.of(
        Text.literal("This mysterious tear quickly absorbs any instance of the first item placed within it."),
        Text.literal("(It controls identically to a Bundle!)"),
        Text.literal("Any placeable items, such as Cobblestone, that find their way inside a Void Tear can be placed as normal."),
        Text.literal("It has a large capacity for a single item type."),
        Text.literal("Should it fill up, it will happily munch away at any excess items, sending them to some distant void."),
        Text.literal("Shift-right click to enable/disable auto-absorb.")
    );
    public VoidTear() {
        super(new Settings().component(STORED_ITEMS_COMPONENT,NbtComponent.of(DEFAULT_STORED_ITEMS)).component(ENABLED_COMPONENT,false));
    }

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        if (clickType == ClickType.RIGHT) {
            if (slot.hasStack()) {
                DeepItemStorageHelper.addToStorage(stack,slot.getStack(),MAX_STORED_TYPES,MAX_STORED_COUNT,true);
                slot.setStack(ItemStack.EMPTY);
            }
            else slot.setStack(DeepItemStorageHelper.takeStackFromStorage(stack,0));
            return true;
        }
        return super.onStackClicked(stack, slot, clickType, player);
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType == ClickType.RIGHT) {
            if (!otherStack.equals(ItemStack.EMPTY)) {
                if (otherStack.getItem().canBeNested()) {
                    int remainder = DeepItemStorageHelper.addToStorage(stack, otherStack, MAX_STORED_TYPES, MAX_STORED_COUNT, true);
                    if (remainder <= 0) {
                        cursorStackReference.set(ItemStack.EMPTY);
                    } else {
                        cursorStackReference.set(otherStack.copyWithCount(remainder));
                    }
                } else {
                    cursorStackReference.set(DeepItemStorageHelper.takeStackFromStorage(stack, 0));
                }
                return true;
            } else {
                otherStack = DeepItemStorageHelper.takeStackFromStorage(stack,0);
                cursorStackReference.set(otherStack);
                return true;
            }
        }
        return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.getComponents().getOrDefault(ENABLED_COMPONENT,false);
    }

    @Override
    public List<Text> getTooltips() {
        return tt;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        /*NbtList comp = DeepItemStorageHelper.getComponentFromStack(stack).copyNbt().getList("stored",NbtList.COMPOUND_TYPE);
        tooltip.add(Text.of("Stored:"));
        for (int i=0;i<comp.size();i++) {
            NbtCompound val = comp.getCompound(i);
            tooltip.add((Text.literal("  -").append(Text.translatable(Registries.ITEM.get(Identifier.of(val.getString("item"))).getTranslationKey()).append(" x"+val.getInt("count")))));
        }*/
        IHasResearchNotes.calculateTooltip(tt,stack,context,tooltip,type);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack thisStack = user.getStackInHand(hand);
        if (user.isSneaking()) return tryToggle(thisStack);
        NbtList comp = DeepItemStorageHelper.getComponentFromStack(thisStack).copyNbt().getList("stored",NbtList.COMPOUND_TYPE);
        if (comp.isEmpty()) return TypedActionResult.fail(thisStack);

        Item storedItem = DeepItemStorageHelper.getStoredItem(thisStack,0);
        TypedActionResult<ItemStack> res = storedItem.use(world,user,hand);
        if (res.getResult() == ActionResult.CONSUME || res.getResult() == ActionResult.SUCCESS) {
            DeepItemStorageHelper.decrementStorage(comp,0);
            DeepItemStorageHelper.save(thisStack,comp);
            user.setStackInHand(hand,thisStack);
        }
        if (res.getResult().isAccepted()) return TypedActionResult.success(thisStack,res.getResult().shouldSwingHand());
        return TypedActionResult.fail(thisStack);
    }

    public static TypedActionResult<ItemStack> tryToggle(ItemStack thisStack) {
        thisStack.apply(ENABLED_COMPONENT,false,!thisStack.getComponents().getOrDefault(ENABLED_COMPONENT,false), (b1, b2) -> b2);
        return TypedActionResult.success(thisStack,true);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ItemStack thisStack = context.getStack();
        NbtList comp = DeepItemStorageHelper.getComponentFromStack(thisStack).copyNbt().getList("stored",NbtList.COMPOUND_TYPE);
        if (comp.isEmpty()) return ActionResult.FAIL;
        if (context.getWorld().isClient) return ActionResult.SUCCESS_NO_ITEM_USED;
        if (context.getPlayer() == null) return ActionResult.FAIL;
        PlayerEntity player = context.getPlayer();
        if (player.isSpectator()) return ActionResult.PASS;
        ItemStack storageStack = DeepItemStorageHelper.takeOneFromStorage(thisStack,0);
        //Do I *need* to use a fake player? Probably not, no.
        ExclusionZoneFakePlayer fp = new ExclusionZoneFakePlayer((ServerWorld)context.getWorld());
        fp.setPos(player.getPos().x,player.getPos().y,player.getPos().z);
        fp.setCurrentHand(context.getHand());
        fp.setStackInHand(context.getHand(),storageStack);
        storageStack.useOnBlock(new ItemUsageContext(Objects.requireNonNull(fp),context.getHand(),new BlockHitResult(context.getHitPos(),context.getSide(),context.getBlockPos(),context.hitsInsideBlock())));
        //Add the item back to the tear's inventory if it failed to place
        DeepItemStorageHelper.addToStorage(thisStack,storageStack,MAX_STORED_TYPES,MAX_STORED_COUNT,true);
        fp.discard();
        return ActionResult.SUCCESS_NO_ITEM_USED;
    }

    @Override
    public boolean allowComponentsUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return false;
    }

    @Override
    public boolean allowContinuingBlockBreaking(PlayerEntity player, ItemStack oldStack, ItemStack newStack) {
        return true;
    }

    int ticks = 0;
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player) {
            if (stack.getComponents().getOrDefault(ENABLED_COMPONENT,false) && ticks%5 == 0) {
                if (player.getInventory().contains(other -> other.getItem() == DeepItemStorageHelper.getStoredItem(stack, 0)) || "minecraft:air".equals(DeepItemStorageHelper.getComponentFromStack(stack).getNbt().getList("stored", NbtElement.COMPOUND_TYPE).getCompound(0).getString("item"))) {
                    for (int i = 0; i < player.getInventory().size(); i++) {
                        if (player.getInventory().getStack(i).getItem() == DeepItemStorageHelper.getStoredItem(stack,0)) {
                            int remainder = DeepItemStorageHelper.addToStorage(stack,player.getInventory().getStack(i),1,8192,true);
                            if (remainder > 0) player.getInventory().getStack(i).setCount(remainder);
                            else player.getInventory().setStack(i,ItemStack.EMPTY);
                        }
                    }
                }
            }
            ticks++;
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public Text getName(ItemStack stack) {
        if (!DeepItemStorageHelper.getComponentFromStack(stack).getNbt().getList("stored", NbtList.COMPOUND_TYPE).isEmpty()) {
            return super.getName(stack).copy().append(Text.literal(" (").append(Text.translatable(DeepItemStorageHelper.getStoredItem(stack,0).getTranslationKey()).append(Text.literal(" x"+DeepItemStorageHelper.getStoredCount(stack,0)+")"))));
        }
        return super.getName(stack);
    }
}
