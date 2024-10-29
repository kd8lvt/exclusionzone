package com.kd8lvt.exclusionzone.handler;

public class ModEventHandlers {
    private ModEventHandlers() {}
    public static void registerAll() {
        //Resource reload events
        // (ResourceType.SERVER_DATA registerReloadListener)
        ResourceReloadHandlers.registerHandlers();
        //Server lifecycle events
        // (ServerLifecycleEvents.END_DATA_PACK_RELOAD / ServerLifecycleEvents.SERVER_STOPPING)
        LifecycleHandlers.registerHandlers();
        //Tox tracker-related events
        // (ServerPlayerEvents.AFTER_RESPAWN / ServerPlayConnectionEvents.JOIN / ServerPlayConnectionEvents.DISCONNECT)
        ToxTrackerHandlers.registerHandlers();
        //Tick events
        // (ServerTickEvents.END_SERVER_TICK)
        TickHandlers.registerHandlers();
    }
}
