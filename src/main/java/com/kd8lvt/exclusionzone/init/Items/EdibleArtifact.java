package com.kd8lvt.exclusionzone.init.Items;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;

public class EdibleArtifact extends Item {
    public EdibleArtifact(FoodComponent foodComponent) {
        super(new Settings().food(foodComponent));
    }
}
