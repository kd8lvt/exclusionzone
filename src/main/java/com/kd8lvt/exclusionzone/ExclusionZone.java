package com.kd8lvt.exclusionzone;

import com.kd8lvt.exclusionzone.init.Items.PersonaWeapons.PersonaWeaponTraits;
import com.kd8lvt.exclusionzone.init.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.item.ItemGroup;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExclusionZone implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("exclusionzone");
	public static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	public static MinecraftServer Server = null;
	public static final boolean muttering_debug = false;
	public static final ToxicBuildupTracker toxTracker = new ToxicBuildupTracker();
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ServerLifecycleEvents.SERVER_STARTING.register(server->{Server = server;});
		LOGGER.info("[ExclusionZone] Registering Sounds...");
		ModSounds.register();
		LOGGER.info("[ExclusionZone] Registering Blocks...");
		ModBlocks.register();
		LOGGER.info("[ExclusionZone] Registering Items...");
		ModItems.register();
		LOGGER.info("[ExclusionZone] Registering Potions...");
		ModPotions.register();
		LOGGER.info("[ExclusionZone] Registrering Entities...");
		ModEntities.register();
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
			@Override
			public Identifier getFabricId() {
				return id("persona_weapon_traits");
			}

			@Override
			public void reload(ResourceManager manager) {
				PersonaWeaponTraits.reload(manager);
			}
		});

		ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
			toxTracker.remove(oldPlayer);
			toxTracker.add(newPlayer);
		});

		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> toxTracker.add(handler.getPlayer()));

		ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> toxTracker.remove(handler.getPlayer()));

		ServerTickEvents.END_SERVER_TICK.register(toxTracker::onTick);
	}

	public static void runCommand(String cmd) {
		if (Server != null) Server.getCommandManager().executeWithPrefix(Server.getCommandSource().withSilent(),cmd);
	}

	private static void schedule(String cmd, LocalTime targetTime) {
		if (Server == null) return;
		LocalTime currentTime = LocalTime.now();
		long time_until = currentTime.until(targetTime, ChronoUnit.MINUTES);
		if (time_until <= 2) time_until += 1440; //if two minutes or less remain until trigger time, or it's past the trigger time, roll over to the next day
		scheduler.scheduleAtFixedRate(()->runCommand(cmd),
				time_until,
				1440, //run once daily
				TimeUnit.MINUTES
		);
	}

	public static Identifier id(String id) {
		return Identifier.of("exclusionzone",id);
	}

	public static void setServer(MinecraftServer server) {
		Server = server;
	}

	public static void TabEntryCollector(ItemGroup.DisplayContext displayContext, ItemGroup.Entries entries) {
		ModItems.CreativeTabSetup(entries);
	}
}