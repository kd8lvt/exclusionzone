package com.kd8lvt.exclusionzone.entity.model;

import com.kd8lvt.exclusionzone.entity.CaroInvictusEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PlayerEntityModel;

@SuppressWarnings({"FieldCanBeLocal", "unused", "CommentedOutCode"})
public class CaroInvictusModel extends PlayerEntityModel<CaroInvictusEntity> {
    private final ModelPart head;
    private final ModelPart headwear;
    private final ModelPart body;
    private final ModelPart jacket;
    private final ModelPart left_arm;
    private final ModelPart left_sleve;
    private final ModelPart right_arm;
    private final ModelPart right_sleve;
    private final ModelPart left_leg;
    private final ModelPart left_pants;
    private final ModelPart right_leg;
    private final ModelPart right_pants;
    public CaroInvictusModel(EntityRendererFactory.Context context) {
        super(context.getPart(EntityModelLayers.PLAYER),false);
        ModelPart root = context.getPart(EntityModelLayers.PLAYER);
        this.head = root.getChild("head");
        this.headwear = root.getChild("hat");
        this.body = root.getChild("body");
        this.jacket = root.getChild("jacket");
        this.left_arm = root.getChild("left_arm");
        this.left_sleve = root.getChild("left_sleeve");
        this.right_arm = root.getChild("right_arm");
        this.right_sleve = root.getChild("right_sleeve");
        this.left_leg = root.getChild("left_leg");
        this.left_pants = root.getChild("left_pants");
        this.right_leg = root.getChild("right_leg");
        this.right_pants = root.getChild("right_pants");
    }
    //Keeping this just in case I want to do custom animations.
    /*public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData headwear = modelPartData.addChild("headwear", ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData jacket = modelPartData.addChild("jacket", ModelPartBuilder.create().uv(16, 32).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData left_arm = modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(32, 48).cuboid(9.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

        ModelPartData left_sleve = modelPartData.addChild("left_sleve", ModelPartBuilder.create().uv(48, 48).cuboid(9.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

        ModelPartData right_arm = modelPartData.addChild("right_arm", ModelPartBuilder.create().uv(40, 16).cuboid(-13.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

        ModelPartData right_sleve = modelPartData.addChild("right_sleve", ModelPartBuilder.create().uv(40, 32).cuboid(-13.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

        ModelPartData left_leg = modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(16, 48).cuboid(2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

        ModelPartData left_pants = modelPartData.addChild("left_pants", ModelPartBuilder.create().uv(0, 48).cuboid(2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

        ModelPartData right_leg = modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(0, 16).cuboid(-6.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 12.0F, 0.0F));

        ModelPartData right_pants = modelPartData.addChild("right_pants", ModelPartBuilder.create().uv(0, 32).cuboid(-6.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(2.0F, 12.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }
    @Override
    public void setAngles(CaroInvictusEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        headwear.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        jacket.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        left_arm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        left_sleve.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        right_arm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        right_sleve.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        left_leg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        left_pants.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        right_leg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        right_pants.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }*/
}
