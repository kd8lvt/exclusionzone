package com.kd8lvt.exclusionzone.registry;

import com.kd8lvt.exclusionzone.command.SetGradientCommand;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class ModCommands {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment environment) {
        SetGradientCommand.register(dispatcher,commandRegistryAccess);
    }
}
