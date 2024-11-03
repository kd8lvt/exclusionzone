package com.kd8lvt.exclusionzone.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.BrushableBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrushableBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntity.class)
public abstract class BlockEntityMixin {
    @Inject(at=@At("HEAD"),method="supports",cancellable = true)
    private void exclusionzone$supports(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (((BlockEntity)(Object)this) instanceof BrushableBlockEntity && state.getBlock() instanceof BrushableBlock) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}
