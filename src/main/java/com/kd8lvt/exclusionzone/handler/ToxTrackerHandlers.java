package com.kd8lvt.exclusionzone.handler;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

import static com.kd8lvt.exclusionzone.ExclusionZone.toxTracker;

public class ToxTrackerHandlers {
    private ToxTrackerHandlers() {}
    public static void registerHandlers() {
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            toxTracker.remove(oldPlayer);
            toxTracker.add(newPlayer);
        });
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> toxTracker.add(handler.getPlayer()));
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> toxTracker.remove(handler.getPlayer()));
    }
}
