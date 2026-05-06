package com.matt.ftbofflinefarms.catchup;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;

public class BambooCatchupHandler {
    public int apply(ServerLevel level, BlockPos pos, long elapsedTicks, CatchupContext ctx) {
        if (!level.getBlockState(pos).is(Blocks.BAMBOO) || !ctx.consumeUpdates(1)) return 0;
        return 1;
    }
}
