package com.kd8lvt.exclusionzone.api.helpers;

import com.kd8lvt.exclusionzone.content.entity.CaroInvictusEntity;
import com.kd8lvt.exclusionzone.registry.ModAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.nbt.NbtFloat;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.Objects;

import static com.kd8lvt.exclusionzone.ExclusionZone.IN_DEV;
import static com.kd8lvt.exclusionzone.registry.ModAttributes.TOXIN_RESISTANCE;

@SuppressWarnings("unused")
public class ToxicBuildupHelper {
    private static final String KEY = "toxic_buildup";
    public static void tickFor(LivingEntity entity) {
        Float buildup = getOrDefault(entity,0f);

        boolean increaseTox = (Objects.equals(entity.getWorld().getBiome(entity.getBlockPos()).getIdAsString(), "exclusionzone:exclusion_zone"));

        if (!increaseTox && !entity.getWorld().getEntitiesByClass(CaroInvictusEntity.class, new Box(entity.getBlockPos().offset(Direction.DOWN,8).offset(Direction.SOUTH,8).offset(Direction.EAST,8).toCenterPos(),entity.getBlockPos().offset(Direction.UP,8).offset(Direction.NORTH,8).offset(Direction.WEST,8).toCenterPos()), caroInvictusEntity -> caroInvictusEntity.hasDied).isEmpty()) increaseTox = true;

        if (increaseTox) incrementBuildup(entity);
        else decrementBuildupUnresisted(entity,1f);

        applyEffects(entity);
        trySendActionbar(entity, buildup);
    }

    public static Float getOrDefault(LivingEntity entity, Float default_) {
        return IEntityDataHelper.getOrDefault(entity,KEY, NbtFloat.of(default_)).floatValue();
    }

    private static void trySendActionbar(LivingEntity entity, Float buildup) {
        if (!(entity instanceof ServerPlayerEntity player)) return;
        if (Math.floor(buildup) <= 0f) return;
        buildup = (float) Math.floor(buildup);
        ArrayList<Text> txt = new ArrayList<>(Text.of("⚠ ").getWithStyle(Style.EMPTY.withColor(Formatting.YELLOW)));
        txt.addAll(Text.of("█".repeat((int) Math.max(Math.min(buildup/200,2000),0))).getWithStyle(Style.EMPTY.withColor(0x00A776)));
        txt.addAll(Text.of("░".repeat((int) Math.max(Math.min((2000-(buildup)+200)/200,2000),0))).getWithStyle(Style.EMPTY.withColor(0x00A776)));
        txt.addAll(Text.of(" ⚠").getWithStyle(Style.EMPTY.withColor(Formatting.YELLOW)));
        MutableText concatTxt = Text.of("").copy(); //Because casting is hard apparently??? What was I cooking 4.5 months ago?
        txt.forEach(concatTxt::append);
        OverlayMessageS2CPacket titlePacket = new OverlayMessageS2CPacket(concatTxt);
        player.networkHandler.sendPacket(titlePacket);
    }
    
    private static void applyEffects(LivingEntity entity) {
        float buildup = getOrDefault(entity,0f);
        if (buildup > 200) applyEffect(entity,StatusEffects.WEAKNESS,20,0);
        if (buildup > 400) applyEffect(entity,StatusEffects.SLOWNESS,20,0);
        if (buildup > 600) applyEffect(entity,StatusEffects.HUNGER,20,0);
        if (buildup > 800) applyEffect(entity,StatusEffects.MINING_FATIGUE,20,0);
        if (buildup > 1000) applyEffect(entity,StatusEffects.WEAKNESS,20,1);
        if (buildup > 1400) applyEffect(entity,StatusEffects.NAUSEA,20,0);
        if (buildup > 1200) applyEffect(entity,StatusEffects.DARKNESS,200,0);
        if (buildup > 1600 && buildup <= 1800) applyEffect(entity, StatusEffects.POISON, 250, 0);
        if (buildup > 1800) applyEffect(entity, StatusEffects.POISON, 250, 5);
        //Intentionally > instead of >= to prevent a weird issue with wither only damaging after a few ticks
        if (buildup >= 1900) applyEffect(entity, StatusEffects.WITHER, 250, 0);
    }
    
    private static void applyEffect(LivingEntity entity, RegistryEntry<StatusEffect> effect, int duration, int amplifier) {
        if (entity.hasStatusEffect(effect) && !Objects.requireNonNull(entity.getStatusEffect(effect)).isDurationBelow(5) && amplifier <= Objects.requireNonNull(entity.getStatusEffect(effect)).getAmplifier()) return;
        if (IN_DEV) entity.addStatusEffect(new StatusEffectInstance(effect,duration,amplifier));
        else entity.addStatusEffect(new StatusEffectInstance(effect,duration,amplifier,true,false,false));
    }

    /**
     * Returns delta (applied toxic buildup) multiplied by the complement of the entity's toxin resistance.<br />
     * In other words, toxin resistance should be a 0-1 value that represents a percentage reduction in received toxin damage<br />
     * For example, an entity with a toxin resistance of 0 will take 100% (100-0=100) toxin damage<br />
     * Another entity with a toxin resistance of 0.75 will take 25% (100-75=25) toxin damage<br />
     * That said, *negative* resistance values also apply, making you take *more* toxin damage.<br />
     * Technically, that also means you can recover damage instead of taking it with resistance values > 1<br />
     * But, that could be an interesting mechanic, so I'm calling it a feature.<br />
     * @param entity The entity to apply the resistance of
     * @param delta The amount of toxic buildup to apply resistance to
     * @return delta, after resistance has been applied
     */
    public static Float applyResistance(LivingEntity entity,Float delta) {
        //toxin resist is 1 by default, to allow for percentage modifications. hence TOXIN_RESISTANCE-1
        return (float) (delta * (1-(entity.getAttributes().getValue(TOXIN_RESISTANCE)-1)));
    }

    /**
     * Gets the toxic buildup for {@code entity}.
     * @param entity the entity to get the toxic buildup of
     * @return {@code entity}'s toxic buildup
     */
    public static Float get(LivingEntity entity) {
        return getOrDefault(entity,0f);
    }

    /**
     * Sets the toxic buildup of {@code entity}.<br />
     * {@code entity} does not need to be in the tracker already.
     * @param entity The entity to set the toxic buildup of
     * @param amt The amount to set {@code entity}'s toxic buildup to. Clamped to 0-2000 (inclusive)
     */
    public static void setBuildup(LivingEntity entity, Float amt) {
        amt = Math.max(0,Math.min(2000,amt));
        IEntityDataHelper.set(entity,KEY,NbtFloat.of(amt));
    }

    /**
     * Increments {@code entity}'s toxic buildup by one.<br />
     * Affected by {@link ModAttributes#TOXIN_RESISTANCE}
     * @param entity The entity to increment toxic buildup for.
     */
    public static void incrementBuildup(LivingEntity entity) {
        incrementBuildup(entity,1f);
    }

    /**
     * Increments {@code entity}'s toxic buildup by {@code delta}<br />
     * Affected by {@link ModAttributes#TOXIN_RESISTANCE}
     * @param entity the entity to increment toxic buildup for
     * @param delta the amount to increment by
     */
    public static void incrementBuildup(LivingEntity entity, Float delta) {
        incrementBuildupUnresisted(entity,applyResistance(entity,delta));
    }

    /**
     * Increments {@code entity}'s toxic buildup by {@code delta}<br />
     * <b>NOT</b> affected by {@link ModAttributes#TOXIN_RESISTANCE}
     * @param entity the entity to increment toxic buildup for
     * @param delta the amount to increment by
     */
    public static void incrementBuildupUnresisted(LivingEntity entity, Float delta) {
        setBuildup(entity,getOrDefault(entity,0f)+delta);
    }
    /**
     * Decrements {@code entity}'s toxic buildup by one.<br />
     * Affected by {@link ModAttributes#TOXIN_RESISTANCE}
     * @param entity The entity to decrement toxic buildup for.
     */
    public static void decrementBuildup(LivingEntity entity) {
        decrementBuildup(entity,1f);
    }

    /**
     * Decrements {@code entity}'s toxic buildup by {@code delta}<br />
     * Affected by {@link ModAttributes#TOXIN_RESISTANCE}
     * @param entity the entity to decrement toxic buildup for
     * @param delta the amount to decrement by
     */
    public static void decrementBuildup(LivingEntity entity, Float delta) {
        decrementBuildupUnresisted(entity, applyResistance(entity,delta));
    }


    /**
     * Decrements {@code entity}'s toxic buildup by {@code delta}<br />
     * <b>NOT</b> affected by {@link ModAttributes#TOXIN_RESISTANCE}
     * @param entity the entity to decrement toxic buildup for
     * @param delta the amount to decrement by
     */
    public static void decrementBuildupUnresisted(LivingEntity entity, Float delta) {
        setBuildup(entity,getOrDefault(entity,0f)-delta);
    }

}
