package com.kd8lvt.exclusionzone.handler;

import com.kd8lvt.exclusionzone.item.Tools.LoggingAxe;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import static com.kd8lvt.exclusionzone.ExclusionZone.toxTracker;

public class TickHandlers {
    private TickHandlers() {}
    public static void registerHandlers() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            toxTracker.onTick(server);
            LoggingAxe.queue.processQueue(server);
        });
    }
}
