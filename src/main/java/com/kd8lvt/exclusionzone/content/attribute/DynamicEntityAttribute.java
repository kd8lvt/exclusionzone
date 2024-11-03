package com.kd8lvt.exclusionzone.content.attribute;

import net.minecraft.entity.attribute.EntityAttribute;

//Credit:
//Shamelessly ripped from https://github.com/pufmat/attributesmod/blob/1.21/Common/src/main/java/net/puffish/attributesmod/attribute/DynamicEntityAttribute.java
public class DynamicEntityAttribute extends EntityAttribute {
    public DynamicEntityAttribute(String translationKey) {
        super(translationKey, 0d);
    }
}
