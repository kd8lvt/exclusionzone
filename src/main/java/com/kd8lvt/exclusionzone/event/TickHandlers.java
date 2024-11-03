package com.kd8lvt.exclusionzone.event;

import com.kd8lvt.exclusionzone.content.item.Tools.LoggingAxe;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class TickHandlers {
    private TickHandlers() {}
    public static void registerHandlers() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            //I'm eventually going to add things. We both know this.
            //noinspection Convert2MethodRef
            LoggingAxe.queue.processQueue(server);
        });
    }
}
