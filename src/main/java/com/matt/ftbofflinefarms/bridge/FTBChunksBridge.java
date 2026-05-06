package com.matt.ftbofflinefarms.bridge;

import com.matt.ftbofflinefarms.chunk.ChunkKey;
import com.matt.ftbofflinefarms.config.ConfigData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.neoforged.fml.ModList;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FTBChunksBridge {
    public boolean isFTBChunksLoaded() {
        return ModList.get().isLoaded("ftbchunks");
    }

    public Set<ChunkKey> getForceLoadedChunks(MinecraftServer server) {
        // NOTE: Keep all FTB Chunks API references isolated here.
        // Verify against the installed FTB Chunks version and replace this stub with official calls.
        return new HashSet<>();
    }

    public Set<ChunkKey> getClaimedChunks(MinecraftServer server) {
        // NOTE: Keep all FTB Chunks API references isolated here.
        return new HashSet<>();
    }

    public Set<ChunkKey> getTargetChunks(MinecraftServer server, ConfigData config) {
        Set<ChunkKey> forceLoaded = getForceLoadedChunks(server);
        if (!config.includeClaimedChunksWithoutForceLoad) {
            return forceLoaded;
        }
        Set<ChunkKey> merged = new HashSet<>(forceLoaded);
        for (ChunkKey c : getClaimedChunks(server)) {
            merged.add(new ChunkKey(c.dimension(), c.chunkX(), c.chunkZ(), c.teamId() != null ? c.teamId() : UUID.randomUUID(), true, c.forceLoaded()));
        }
        return merged;
    }
}
