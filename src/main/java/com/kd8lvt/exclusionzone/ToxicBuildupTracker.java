package com.kd8lvt.exclusionzone;

import com.kd8lvt.exclusionzone.init.Entities.CaroInvictusEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;
import net.minecraft.server.MinecraftServer;
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

public class ToxicBuildupTracker {
    private final HashMap<ServerPlayerEntity,Integer> tox = new HashMap<>();

    public void onTick(MinecraftServer server) {
        server.getPlayerManager().getPlayerList().forEach(player->{
            if (!this.tox.containsKey(player)) this.tox.put(player,0);
        });

        this.tox.forEach((key, value) -> {
            if (value > 2000) this.setBuildup(key,2000);
            if (value < 0) this.setBuildup(key,0);

            boolean increaseTox = false;
            if (Objects.equals(key.getServerWorld().getBiome(key.getBlockPos()).getIdAsString(), "exclusionzone:exclusion_zone")) {
                for (ItemStack stack : key.getInventory().armor) {
                    if (stack.isEmpty()) {
                        increaseTox = true;
                        break;
                    }
                }
            }

            if (!key.getServerWorld().getEntitiesByClass(CaroInvictusEntity.class, new Box(key.getBlockPos().offset(Direction.DOWN,8).offset(Direction.SOUTH,8).offset(Direction.EAST,8).toCenterPos(),key.getBlockPos().offset(Direction.UP,8).offset(Direction.NORTH,8).offset(Direction.WEST,8).toCenterPos()), caroInvictusEntity -> caroInvictusEntity.hasDied).isEmpty()) increaseTox = true;

            if (increaseTox) this.incrementBuildup(key);
            else this.decrementBuildup(key);

            this.applyEffects(key);

            if (value <= 0) return;
            ArrayList<Text> txt = new ArrayList<>(Text.of("⚠ ").getWithStyle(Style.EMPTY.withColor(Formatting.YELLOW)));
            txt.addAll(Text.of("█".repeat(Math.max(Math.min(value/200,2000),0))).getWithStyle(Style.EMPTY.withColor(0x00A776)));
            txt.addAll(Text.of("░".repeat(Math.max(Math.min((2000-(value)+200)/200,2000),0))).getWithStyle(Style.EMPTY.withColor(0x00A776)));
            txt.addAll(Text.of(" ⚠").getWithStyle(Style.EMPTY.withColor(Formatting.YELLOW)));
            MutableText concatTxt = Text.of("").copy();
            txt.forEach(concatTxt::append);
            OverlayMessageS2CPacket titlePacket = new OverlayMessageS2CPacket(concatTxt);
            key.networkHandler.sendPacket(titlePacket);
        });
    }

    private void applyEffects(ServerPlayerEntity entity) {
        int buildup = tox.get(entity);
        if (buildup > 200) entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,20,0,true,false,false));
        if (buildup > 400) entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,20,0,true,false,false));
        if (buildup > 600) entity.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER,20,0,true,false,false));
        if (buildup > 800) entity.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE,20,0,true,false,false));
        if (buildup > 1000) entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,20,1,true,false,false));
        if (buildup > 1200) entity.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS,200,0,true,false,false));
        if (buildup > 1400) entity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA,20,0,true,false,false));
        if (buildup > 1600) entity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON,20,0,true,false,false));
        if (buildup > 1800) entity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON,20,5,true,false,false));
        if (buildup > 2000) entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER,200,0,true,false,false));
    }

    public void add(ServerPlayerEntity entity) {
        tox.put(entity, 0);
    }

    public int remove(ServerPlayerEntity entity) {
        return tox.remove(entity);
    }

    public int get(ServerPlayerEntity entity) {
        return tox.get(entity);
    }

    public boolean setBuildup(ServerPlayerEntity entity, int amt) {
        return tox.replace(entity,tox.get(entity),amt);
    }

    public boolean incrementBuildup(ServerPlayerEntity entity) {
        return tox.replace(entity,tox.get(entity),(tox.get(entity)!=null?tox.get(entity):0)+1);
    }

    public boolean incrementBuildup(ServerPlayerEntity entity,int delta) {
        return tox.replace(entity,tox.get(entity),(tox.get(entity)!=null?tox.get(entity):0)+delta);
    }

    public boolean decrementBuildup(ServerPlayerEntity entity) {
        return tox.replace(entity,tox.get(entity),(tox.get(entity)!=null?tox.get(entity):0)-1);
    }

    public boolean decrementBuildup(ServerPlayerEntity entity,int delta) {
        return tox.replace(entity,tox.get(entity),(tox.get(entity)!=null?tox.get(entity):0)-delta);
    }
}
