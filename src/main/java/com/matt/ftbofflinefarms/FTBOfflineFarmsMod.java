package com.matt.ftbofflinefarms;

import com.matt.ftbofflinefarms.bridge.FTBChunksBridge;
import com.matt.ftbofflinefarms.catchup.OfflineCatchupManager;
import com.matt.ftbofflinefarms.chunk.ChunkLoadingCoordinator;
import com.matt.ftbofflinefarms.chunk.ChunkScanner;
import com.matt.ftbofflinefarms.command.DebugCommand;
import com.matt.ftbofflinefarms.config.ConfigManager;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(FTBOfflineFarmsMod.MOD_ID)
public class FTBOfflineFarmsMod {
    public static final String MOD_ID = "ftbofflinefarms";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private final ConfigManager configManager = new ConfigManager();
    private final FTBChunksBridge ftbChunksBridge = new FTBChunksBridge();
    private final ChunkScanner chunkScanner = new ChunkScanner(ftbChunksBridge, configManager);
    private final ChunkLoadingCoordinator chunkLoadingCoordinator = new ChunkLoadingCoordinator(chunkScanner, configManager);
    private final OfflineCatchupManager catchupManager = new OfflineCatchupManager(chunkScanner, configManager);

    public FTBOfflineFarmsMod(IEventBus bus, ModContainer container) {
        configManager.loadOrCreate();
        NeoForge.EVENT_BUS.addListener(this::onServerStarted);
        NeoForge.EVENT_BUS.addListener(this::onServerStopping);
        NeoForge.EVENT_BUS.addListener(this::onServerTick);
        NeoForge.EVENT_BUS.addListener(this::onRegisterCommands);
    }

    private void onServerStarted(ServerStartedEvent event) {
        chunkLoadingCoordinator.onServerStarted(event.getServer());
        catchupManager.onServerStarted(event.getServer());
    }

    private void onServerStopping(ServerStoppingEvent event) {
        catchupManager.onServerStopping(event.getServer());
        chunkLoadingCoordinator.onServerStopping(event.getServer());
    }

    private void onServerTick(ServerTickEvent.Post event) {
        chunkLoadingCoordinator.tick(event.getServer());
        catchupManager.tick(event.getServer());
    }

    private void onRegisterCommands(RegisterCommandsEvent event) {
        DebugCommand.register(event.getDispatcher(), chunkScanner);
    }
}
