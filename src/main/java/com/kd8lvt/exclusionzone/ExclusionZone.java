package com.kd8lvt.exclusionzone;

import com.kd8lvt.exclusionzone.event.ModEventHandlers;
import com.kd8lvt.exclusionzone.registry.ModItems;
import com.kd8lvt.exclusionzone.registry.ModRegistries;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExclusionZone implements ModInitializer {
	public static final String MOD_ID = "exclusionzone";
	public static final boolean IN_DEV = FabricLoader.getInstance().isDevelopmentEnvironment();
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static MinecraftServer Server = null;

	@Override
	public void onInitialize() {
		//Registry
		ModRegistries.registerAll();

		//Event Handlers
		ModEventHandlers.registerAll();

		LOGGER.info("Dev Environment According To Fabric: "+IN_DEV);
	}

	//TODO: Get rid of this and do things properly
	@Deprecated(forRemoval = true,since="1.6.0")
	public static void runCommand(String cmd) {
		if (Server != null) Server.getCommandManager().executeWithPrefix(Server.getCommandSource().withSilent(),cmd);
	}

	public static Identifier id(String id) {
		return Identifier.of(MOD_ID,id);
	}

	public static void setServer(MinecraftServer server) {
		Server = server;
	}

	public static void TabEntryCollector(ItemGroup.DisplayContext ignoredDisplayContext, ItemGroup.Entries entries) {
		ModItems.CreativeTabSetup(entries);
	}
}