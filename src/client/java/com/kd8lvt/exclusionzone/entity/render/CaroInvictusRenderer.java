package com.kd8lvt.exclusionzone.entity.render;

import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.entity.model.CaroInvictusModel;
import com.kd8lvt.exclusionzone.content.entity.CaroInvictusEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class CaroInvictusRenderer extends MobEntityRenderer<CaroInvictusEntity, CaroInvictusModel> {

    public CaroInvictusRenderer(EntityRendererFactory.Context context) {
        super(context,new CaroInvictusModel(context),0.5f);
    }

    @Override
    public Identifier getTexture(CaroInvictusEntity entity) {
        if (entity.hasDied && !entity.regenning) return ExclusionZone.id("textures/entity/caro_invictus/phase_two.png");
        return ExclusionZone.id("textures/entity/caro_invictus/phase_one.png");
    }

    @Override
    protected boolean isShaking(CaroInvictusEntity entity) {
        return entity.regenning;
    }

}
