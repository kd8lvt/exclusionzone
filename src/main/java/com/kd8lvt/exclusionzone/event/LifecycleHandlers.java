package com.kd8lvt.exclusionzone.event;

import com.kd8lvt.exclusionzone.content.item.Tools.LoggingAxe;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

import static com.kd8lvt.exclusionzone.ExclusionZone.Server;

public class LifecycleHandlers {
    private LifecycleHandlers() {}
    public static void registerHandlers() {
        //Server startup logic
        ServerLifecycleEvents.SERVER_STARTING.register(server-> Server = server);

        //Server shutdown logic
        ServerLifecycleEvents.SERVER_STOPPING.register(server-> LoggingAxe.queue.onServerShuttingDown());
    }
}
