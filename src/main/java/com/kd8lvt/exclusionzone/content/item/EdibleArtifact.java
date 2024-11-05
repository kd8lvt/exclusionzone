package com.kd8lvt.exclusionzone.content.item;

import net.minecraft.component.type.FoodComponent;

public class EdibleArtifact extends Artifact {
    public EdibleArtifact(FoodComponent foodComponent) {
        super(new Settings().food(foodComponent));
    }
}
