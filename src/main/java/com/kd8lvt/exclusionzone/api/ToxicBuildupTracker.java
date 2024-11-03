package com.kd8lvt.exclusionzone.api;

import com.kd8lvt.exclusionzone.content.entity.CaroInvictusEntity;
import com.kd8lvt.exclusionzone.registry.ModAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

import static com.kd8lvt.exclusionzone.registry.ModAttributes.TOXIN_RESISTANCE;

@SuppressWarnings("unused")
public class ToxicBuildupTracker {
    private static final HashMap<UUID,Float> buildupTracker = new HashMap<>();

    @SuppressWarnings("unused")
    public static void tickFor(LivingEntity entity) {
        Float buildup = buildupTracker.getOrDefault(entity.getUuid(),0f);
        if (buildup > 2000) setBuildup(entity,2000f);
        if (buildup < 0) setBuildup(entity,0f);

        boolean increaseTox = false;
        if (Objects.equals(entity.getWorld().getBiome(entity.getBlockPos()).getIdAsString(), "exclusionzone:exclusion_zone")) {
            for (ItemStack stack : entity.getAllArmorItems()) {
                if (stack.isEmpty()) {
                    increaseTox = true;
                    break;
                }
            }
        }

        if (!entity.getWorld().getEntitiesByClass(CaroInvictusEntity.class, new Box(entity.getBlockPos().offset(Direction.DOWN,8).offset(Direction.SOUTH,8).offset(Direction.EAST,8).toCenterPos(),entity.getBlockPos().offset(Direction.UP,8).offset(Direction.NORTH,8).offset(Direction.WEST,8).toCenterPos()), caroInvictusEntity -> caroInvictusEntity.hasDied).isEmpty()) increaseTox = true;

        if (increaseTox) incrementBuildup(entity);
        else decrementBuildupUnresisted(entity,1f);

        applyEffects(entity);
        trySendActionbar(entity, buildup);
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
        Float buildup = buildupTracker.get(entity.getUuid());
        if (buildup > 200) entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,20,0,true,false,false));
        if (buildup > 400) entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,20,0,true,false,false));
        if (buildup > 600) entity.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER,20,0,true,false,false));
        if (buildup > 800) entity.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE,20,0,true,false,false));
        if (buildup > 1000) entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,20,1,true,false,false));
        if (buildup > 1200) entity.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS,200,0,true,false,false));
        if (buildup > 1400) entity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA,20,0,true,false,false));
        if (buildup > 1600) entity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON,20,0,true,false,false));
        if (buildup > 1800) entity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON,20,5,true,false,false));
        //Intentionally > instead of >= to prevent a weird issue with wither only damaging after a few ticks
        if (buildup > 2000) entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER,200,0,true,false,false));
    }

    /**
     * Returns delta (applied toxic buildup) multiplied by the complement of the entity's toxin resistance.<br />
     * In other words, toxin resistance should be a 0-1 value that represents a percentage reduction in received toxin damage<br />
     * For example, an entity with a toxin resistance of 0 will take 100% (100-0=100) toxin damage<br />
     * Another entity with a toxin resistance of 0.75 will take 25% (100-75=25) toxin damage<br />
     * That said, *negative* resistance values also apply, making you take *more* toxin damage.<br />
     * Technically, that also means you can recover damage instead of taking it with resistance values > 1<br />
     * But, that could be an interesting mechanic so I'm calling it a feature not a bug.<br />
     * @param entity The entity to apply the resistance of
     * @param delta The amount of toxic buildup to apply resistance to
     * @return delta, after resistance has been applied
     */
    public static Float applyResistance(LivingEntity entity,Float delta) {
        return (float) (delta * (1-entity.getAttributes().getValue(TOXIN_RESISTANCE)));
    }

    /**
     * Removes an entity from the tracker.<br />
     * Only worth doing if the entity isn't going to be around next tick, and you aren't using {@link net.minecraft.entity.Entity#discard()}
     * @param entity The entity to remove
     * @return Their toxic buildup prior to being removed
     */
    public static Float remove(LivingEntity entity) {
        return buildupTracker.remove(entity.getUuid());
    }

    /**
     * Gets the toxic buildup for {@code entity}.
     * @param entity the entity to get the toxic buildup of
     * @return {@code entity}'s toxic buildup
     */
    public static Float get(LivingEntity entity) {
        return buildupTracker.get(entity.getUuid());
    }

    /**
     * Sets the toxic buildup of {@code entity}.<br />
     * {@code entity} does not need to be in the tracker already.
     * @param entity The entity to set the toxic buildup of
     * @param amt The amount to set {@code entity}'s toxic buildup to.
     */
    public static void setBuildup(LivingEntity entity, Float amt) {
        buildupTracker.merge(entity.getUuid(),amt,(_a,_b)->amt);
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
        buildupTracker.merge(entity.getUuid(), delta, Float::sum);
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
        buildupTracker.merge(entity.getUuid(), -delta, Float::sum); // negating input args instead of subtracting, because why not
    }

}
