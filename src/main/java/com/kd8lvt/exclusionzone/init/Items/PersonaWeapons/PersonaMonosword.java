package com.kd8lvt.exclusionzone.init.Items.PersonaWeapons;

import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.TagKey;

public class PersonaMonosword extends PersonaWeapon {
    public PersonaMonosword() {
        super(new PersonaMonoswordMaterial(), new Settings());
    }
}

class PersonaMonoswordMaterial implements ToolMaterial {

    @Override
    public int getDurability() {
        return 8192;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 0;
    }

    @Override
    public float getAttackDamage() {
        return 8;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return null;
    }

    @Override
    public int getEnchantability() {
        return -1;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.REDSTONE);
    }
}
