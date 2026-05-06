package com.matt.ftbofflinefarms.command;

import com.matt.ftbofflinefarms.chunk.ChunkScanner;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class DebugCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, ChunkScanner scanner) {
        dispatcher.register(Commands.literal("ftbofflinefarms")
                .then(Commands.literal("debug")
                        .executes(ctx -> {
                            int count = scanner.scan(ctx.getSource().getServer()).size();
                            ctx.getSource().sendSuccess(() -> net.minecraft.network.chat.Component.literal("FTB target chunks detected: " + count), false);
                            return 1;
                        })));
    }
}
