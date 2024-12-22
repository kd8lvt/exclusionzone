package com.kd8lvt.exclusionzone.api.datagen.lang;

import com.kd8lvt.exclusionzone.registry.ModAttributes;
import com.kd8lvt.exclusionzone.registry.ModDamageTypes;
import com.kd8lvt.exclusionzone.registry.ModEnchantments;
import net.minecraft.util.Identifier;

import static com.kd8lvt.exclusionzone.ExclusionZone.id;

public class TranslationKeys {
    public enum TOOLTIPS {
        RESEARCH_HEADER;
        public TranslationTypes type = TranslationTypes.TOOLTIP;
        public String toTranslationKey() {
            return type.of(this.get());
        }
        public Identifier get() {
            return switch (this) {
                case RESEARCH_HEADER -> id("research_notes.header");
            };
        }
    }
    public enum ITEM_GROUPS {
        MAIN;
        public TranslationTypes type = TranslationTypes.ITEM_GROUP;
        public String toTranslationKey() {
            return type.of(this.get());
        }
        public Identifier get() {
            return switch (this) {
                case MAIN -> id("main");
            };
        }
    }
    public enum DEATH_MESSAGES {
        //Environmental toxic buildup. Not sure if TOXIC_ENVIRONMENT_PLAYER is possible but its here now.
        TOXIC_ENVIRONMENT,
        TOXIC_ENVIRONMENT_PLAYER,
        //Toxic buildup applied directly from the attribute.
        TOXIC_ENTITY,
        TOXIC_ENTITY_ITEM,
        TOXIC_ENTITY_PLAYER;
        public TranslationTypes type = TranslationTypes.DEATH_MESSAGE;
        public Identifier get(){
            return switch (this){
                case TOXIC_ENVIRONMENT        -> ModDamageTypes.KEYS.TOXIC_ENVIRONMENT.getValue();
                case TOXIC_ENVIRONMENT_PLAYER -> ModDamageTypes.KEYS.TOXIC_ENVIRONMENT.getValue().withSuffixedPath(".player");
                case TOXIC_ENTITY             -> ModDamageTypes.KEYS.TOXIC_ENTITY.getValue();
                case TOXIC_ENTITY_ITEM        -> ModDamageTypes.KEYS.TOXIC_ENTITY.getValue().withSuffixedPath(".item");
                case TOXIC_ENTITY_PLAYER      -> ModDamageTypes.KEYS.TOXIC_ENTITY.getValue().withSuffixedPath(".player");
            };
        }
        public String toTranslationKey() {
            return this.type.of(this.get());
        }
    }
    public enum ATTRIBUTES {
        TOXIN_RESISTANCE,
        TOXIC_DAMAGE;
        public TranslationTypes type = TranslationTypes.ATTRIBUTE;
        public Identifier get() {
            return switch (this) {
                case TOXIN_RESISTANCE -> ModAttributes.TOXIN_RESISTANCE_ID;
                case TOXIC_DAMAGE     -> ModAttributes.TOXIN_DAMAGE_ID;
            };
        }
        public String toTranslationKey() {
            return this.type.of(this.get());
        }
    }
    public enum ENCHANTMENTS {
        TOXIC_RESISTANCE,
        TOXIC_DAMAGE;
        final TranslationTypes type = TranslationTypes.ENCHANTMENT;
        public Identifier get() {
            return switch (this){
                case ENCHANTMENTS.TOXIC_DAMAGE     -> ModEnchantments.KEYS.TOXICUS_PERDITIO.getValue();
                case TOXIC_RESISTANCE -> ModEnchantments.KEYS.TOXICAE_PRAESIDIUM.getValue();
            };
        }
        public String toTranslationKey() {
            return this.type.of(this.get());
        }
    }

    public enum TranslationTypes {
        TOOLTIP,
        ITEM_GROUP,
        DEATH_MESSAGE,
        ATTRIBUTE,
        ENCHANTMENT,
        NONE;

        public String of(Identifier id) {
            return switch (this) {
                case TOOLTIP       -> id.toTranslationKey("tooltips");
                case ITEM_GROUP       -> id.toTranslationKey("itemgroup");
                case DEATH_MESSAGE -> id.toTranslationKey("death.attack");
                case ATTRIBUTE     -> id.toTranslationKey("generic");
                case ENCHANTMENT   -> id.toTranslationKey("enchantment");
                case NONE          -> id.toTranslationKey();
            };
        }
    }
}
