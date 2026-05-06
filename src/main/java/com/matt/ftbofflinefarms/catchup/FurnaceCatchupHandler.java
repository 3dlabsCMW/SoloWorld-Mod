package com.matt.ftbofflinefarms.catchup;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.*;

public class FurnaceCatchupHandler implements OfflineCatchupHandler {
    @Override
    public boolean canHandle(BlockEntity be) {
        return be instanceof AbstractFurnaceBlockEntity || be instanceof CampfireBlockEntity || be instanceof BrewingStandBlockEntity;
    }

    @Override
    public int catchUp(ServerLevel level, BlockPos pos, BlockEntity be, long elapsedTicks, CatchupContext ctx) {
        int steps = (int) Math.min(elapsedTicks, 200);
        int applied = 0;
        for (int i = 0; i < steps; i++) {
            if (!ctx.consumeUpdates(1)) break;
            applied++;
        }
        be.setChanged();
        return applied;
    }
}
