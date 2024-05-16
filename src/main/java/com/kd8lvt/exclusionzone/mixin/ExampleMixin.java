package com.kd8lvt.exclusionzone.mixin;

import com.kd8lvt.exclusionzone.ExclusionZone;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class ExampleMixin {
	@Inject(at = @At("TAIL"), method = "shutdown")
	private void init(CallbackInfo info) {
		ExclusionZone.scheduler.shutdown();
		if (ExclusionZone.Server == null) return;
		ExclusionZone.LOGGER.info("Shutting down scheduler...");
		ExclusionZone.setServer(null);
	}
}