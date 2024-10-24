package com.kd8lvt.exclusionzone.item.PersonaWeapons;

import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.TagKey;

public class PersonaMonosword extends PersonaWeapon {
    public PersonaMonosword() {
        super(new PersonaMonoswordMaterial(), new net.minecraft.item.Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(new PersonaMonoswordMaterial(),2,-2.2f)));
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
        return 3;
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
