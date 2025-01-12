package com.kd8lvt.exclusionzone;

import com.kd8lvt.exclusionzone.api.CommonConstants;
import com.kd8lvt.exclusionzone.event.ModEventHandlers;
import com.kd8lvt.exclusionzone.registry.ModRegistries;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.impl.biome.modification.BuiltInRegistryKeys;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static com.kd8lvt.exclusionzone.api.CommonConstants.MOD_ID;
import static com.kd8lvt.exclusionzone.api.CommonConstants.SERVER;

public class ExclusionZone implements ModInitializer {
	public static final boolean IN_DEV = FabricLoader.getInstance().isDevelopmentEnvironment();
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
		//Registry
		ModRegistries.registerAll();

		//Event Handlers
		ModEventHandlers.registerAll();

		if (IN_DEV) LOGGER.info("Dev Environment, According To Fabric!");
	}

	//TODO: Get rid of this and do things properly
	@Deprecated(forRemoval = true,since="1.6.0")
	public static void runCommand(String cmd) {
		if (SERVER != null) SERVER.getCommandManager().executeWithPrefix(SERVER.getCommandSource().withSilent(),cmd);
	}

	public static Identifier id(String id) {
		return Identifier.of(MOD_ID,id);
	}
}