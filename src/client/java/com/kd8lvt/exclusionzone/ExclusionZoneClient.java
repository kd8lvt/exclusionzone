package com.kd8lvt.exclusionzone;

import com.kd8lvt.exclusionzone.entity.render.CaroInvictusRenderer;
import com.kd8lvt.exclusionzone.content.entity.CaroInvictusEntity;
import com.kd8lvt.exclusionzone.registry.ModBlocks;
import com.kd8lvt.exclusionzone.registry.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EntityType;

@Environment(EnvType.CLIENT)
public class ExclusionZoneClient implements ClientModInitializer {
	@SuppressWarnings("unchecked")
    @Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.get("plant/enderweed"),RenderLayer.getCutout());
		EntityRendererRegistry.register((EntityType<CaroInvictusEntity>)ModEntities.get("caro_invictus"), CaroInvictusRenderer::new);
	}
}