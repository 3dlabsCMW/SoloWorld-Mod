package com.matt.ftbofflinefarms.catchup;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CropCatchupHandler {
    public int apply(ServerLevel level, BlockPos pos, long elapsedTicks, CatchupContext ctx) {
        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();
        if (!(block instanceof CropBlock || block instanceof NetherWartBlock || block instanceof SweetBerryBushBlock || block instanceof CocoaBlock)) return 0;
        if (!ctx.consumeUpdates(1)) return 0;
        return 1;
    }
}
