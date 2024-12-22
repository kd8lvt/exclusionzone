package com.kd8lvt.exclusionzone.event;

import com.kd8lvt.exclusionzone.api.CommonConstants;
import com.kd8lvt.exclusionzone.content.item.Tools.LoggingAxe;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

public class LifecycleHandlers {
    private LifecycleHandlers() {}
    public static void registerHandlers() {
        ServerLifecycleEvents.SERVER_STARTING.register(LifecycleHandlers::serverStarting);
        ServerLifecycleEvents.SERVER_STOPPING.register(LifecycleHandlers::serverStopping);
    }

    //Server startup logic
    public static void serverStarting(MinecraftServer server) {
        CommonConstants.SERVER = server;
    }

    //Server shutdown logic
    public static void serverStopping(MinecraftServer server) {
        LoggingAxe.queue.onServerShuttingDown();
    }
}
