package com.kd8lvt.exclusionzone.command;

import com.kd8lvt.exclusionzone.block.entity.GradientGlassBE;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;

import java.awt.*;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class SetGradientCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(literal("setgradient").then(argument("block",BlockPosArgumentType.blockPos()).then(argument("start_color_red", StringArgumentType.string()).then(argument("end_color",StringArgumentType.string()).executes(SetGradientCommand::execute)))));
    }

    public static int execute(CommandContext<ServerCommandSource> ctx) {
        try {
            ServerCommandSource source = ctx.getSource();
            BlockPos pos = BlockPosArgumentType.getLoadedBlockPos(ctx, "block");
            String startColorStr = StringArgumentType.getString(ctx,"start_color");
            String endColorStr = StringArgumentType.getString(ctx,"end_color");
            float[] startColor = Color.decode(startColorStr).getColorComponents(new float[4]);
            float[] endColor = Color.decode(endColorStr).getColorComponents(new float[4]);
            BlockEntity be = source.getWorld().getBlockEntity(pos);
            if (!(be instanceof GradientGlassBE glass)) throw new Exception("Invalid block at position "+pos);
            glass.gradientStart = ColorHelper.Argb.fromFloats(1,startColor[1],startColor[2],startColor[3]);
            glass.gradientEnd = ColorHelper.Argb.fromFloats(1,endColor[1],endColor[2],endColor[3]);
            source.sendFeedback(()->Text.of("Set the gradient of the block!"),false);
            return 1;
        } catch (Exception e) {
            ctx.getSource().sendError(Text.of(e.toString()));
        }
        return 0;
    }
}
