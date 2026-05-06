package com.matt.ftbofflinefarms.chunk;

import com.matt.ftbofflinefarms.bridge.FTBChunksBridge;
import com.matt.ftbofflinefarms.config.ConfigManager;
import net.minecraft.server.MinecraftServer;

import java.util.Set;

public class ChunkScanner {
    private final FTBChunksBridge bridge;
    private final ConfigManager config;
    private Set<ChunkKey> cached = Set.of();

    public ChunkScanner(FTBChunksBridge bridge, ConfigManager config) {
        this.bridge = bridge;
        this.config = config;
    }

    public Set<ChunkKey> scan(MinecraftServer server) {
        cached = bridge.getTargetChunks(server, config.get());
        return cached;
    }

    public Set<ChunkKey> getCached() { return cached; }
}
