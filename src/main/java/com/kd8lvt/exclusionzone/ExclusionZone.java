package com.kd8lvt.exclusionzone;

import com.kd8lvt.exclusionzone.handler.ModEventHandlers;
import com.kd8lvt.exclusionzone.player.ToxicBuildupTracker;
import com.kd8lvt.exclusionzone.registry.ModItems;
import com.kd8lvt.exclusionzone.registry.ModRegistries;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ItemGroup;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExclusionZone implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("exclusionzone");
	public static MinecraftServer Server = null;
	public static final ToxicBuildupTracker toxTracker = new ToxicBuildupTracker();

	@Override
	public void onInitialize() {
		//Registry
		ModRegistries.registerAll();

		//Event Handlers
		ModEventHandlers.registerAll();
	}

	//TODO: Get rid of this and do things properly
	@Deprecated(forRemoval = true,since="1.6.0")
	public static void runCommand(String cmd) {
		if (Server != null) Server.getCommandManager().executeWithPrefix(Server.getCommandSource().withSilent(),cmd);
	}

	public static Identifier id(String id) {
		return Identifier.of("exclusionzone",id);
	}

	public static void setServer(MinecraftServer server) {
		Server = server;
	}

	public static void TabEntryCollector(ItemGroup.DisplayContext ignoredDisplayContext, ItemGroup.Entries entries) {
		ModItems.CreativeTabSetup(entries);
	}
}