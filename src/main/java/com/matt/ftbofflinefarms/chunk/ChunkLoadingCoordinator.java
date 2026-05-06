package com.matt.ftbofflinefarms.chunk;

import com.matt.ftbofflinefarms.config.ConfigManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;

public class ChunkLoadingCoordinator {
    private final ChunkScanner scanner;
    private final ConfigManager config;
    private long nextRescanTick;

    public ChunkLoadingCoordinator(ChunkScanner scanner, ConfigManager config) {
        this.scanner = scanner;
        this.config = config;
    }

    public void onServerStarted(MinecraftServer server) { rescanAndApply(server); }
    public void onServerStopping(MinecraftServer server) { }

    public void tick(MinecraftServer server) {
        if (server.getTickCount() >= nextRescanTick) {
            rescanAndApply(server);
        }
    }

    private void rescanAndApply(MinecraftServer server) {
        var chunks = scanner.scan(server);
        nextRescanTick = server.getTickCount() + (long) config.get().rescanIntervalSeconds * 20L;
        for (ChunkKey key : chunks) {
            ServerLevel level = server.getLevel(key.dimension());
            if (level != null) {
                level.setChunkForced(key.chunkX(), key.chunkZ(), true);
                level.getChunkSource().addRegionTicket(net.minecraft.server.level.TicketType.FORCED, new ChunkPos(key.chunkX(), key.chunkZ()), 2, new ChunkPos(key.chunkX(), key.chunkZ()));
            }
        }
    }
}
