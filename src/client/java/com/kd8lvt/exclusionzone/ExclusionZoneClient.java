package com.kd8lvt.exclusionzone;

import com.kd8lvt.exclusionzone.entity.render.CaroInvictusRenderer;
import com.kd8lvt.exclusionzone.init.ModBlocks;
import com.kd8lvt.exclusionzone.init.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class ExclusionZoneClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ENDERWEED,RenderLayer.getCutout());
		EntityRendererRegistry.register(ModEntities.CARO_INVICTUS, CaroInvictusRenderer::new);
	}
}