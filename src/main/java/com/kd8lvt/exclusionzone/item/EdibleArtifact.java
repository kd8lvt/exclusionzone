package com.kd8lvt.exclusionzone.item;

import net.minecraft.component.type.FoodComponent;

public class EdibleArtifact extends Artifact {
    public EdibleArtifact(FoodComponent foodComponent) {
        super(new net.minecraft.item.Item.Settings().food(foodComponent));
    }
}
