package com.matt.ftbofflinefarms.catchup;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;

public interface OfflineCatchupHandler {
    boolean canHandle(BlockEntity be);
    int catchUp(ServerLevel level, BlockPos pos, BlockEntity be, long elapsedTicks, CatchupContext ctx);
}
