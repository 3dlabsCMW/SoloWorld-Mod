package com.matt.ftbofflinefarms.catchup;

import com.matt.ftbofflinefarms.FTBOfflineFarmsMod;
import com.matt.ftbofflinefarms.chunk.ChunkKey;
import com.matt.ftbofflinefarms.chunk.ChunkScanner;
import com.matt.ftbofflinefarms.config.ConfigManager;
import com.matt.ftbofflinefarms.data.WorldOfflineData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Queue;

public class OfflineCatchupManager {
    private final ChunkScanner scanner;
    private final ConfigManager config;
    private final Queue<ChunkKey> queue = new ArrayDeque<>();

    public OfflineCatchupManager(ChunkScanner scanner, ConfigManager config) { this.scanner = scanner; this.config = config; }

    public void onServerStarted(MinecraftServer server) {
        if (!config.get().offlineCatchupEnabled) return;
        var targets = scanner.scan(server).stream().limit(config.get().maxChunksPerLoginCatchup).toList();
        queue.clear(); queue.addAll(targets);
        FTBOfflineFarmsMod.LOGGER.info("Detected {} FTB chunks, queued {} for offline catch-up", scanner.getCached().size(), queue.size());
    }

    public void onServerStopping(MinecraftServer server) {
        long now = Instant.now().getEpochSecond();
        for (ServerLevel level : server.getAllLevels()) {
            WorldOfflineData data = WorldOfflineData.get(level);
            data.lastUnloadEpochSeconds = now;
            data.setDirty();
        }
    }

    public void tick(MinecraftServer server) {
        if (queue.isEmpty()) return;
        int budget = config.get().catchupWorkBudgetPerTick;
        while (budget-- > 0 && !queue.isEmpty()) {
            ChunkKey key = queue.poll();
            ServerLevel level = server.getLevel(key.dimension());
            if (level == null) continue;
            // conservative placeholder work unit
        }
    }
}
