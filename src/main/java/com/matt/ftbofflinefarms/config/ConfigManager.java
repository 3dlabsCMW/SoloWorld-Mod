package com.matt.ftbofflinefarms.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.matt.ftbofflinefarms.FTBOfflineFarmsMod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = Path.of("config", "ftb_offline_farms.json");
    private ConfigData config = ConfigData.defaults();

    public void loadOrCreate() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            if (Files.exists(CONFIG_PATH)) {
                config = GSON.fromJson(Files.readString(CONFIG_PATH), ConfigData.class);
                if (config == null) config = ConfigData.defaults();
            }
            config = config.clamp();
            Files.writeString(CONFIG_PATH, GSON.toJson(config));
            if (config.includeClaimedChunksWithoutForceLoad) {
                FTBOfflineFarmsMod.LOGGER.warn("includeClaimedChunksWithoutForceLoad=true can cause major lag in large modpacks.");
            }
        } catch (IOException e) {
            FTBOfflineFarmsMod.LOGGER.error("Failed to load config", e);
            config = ConfigData.defaults();
        }
    }

    public ConfigData get() {
        return config;
    }
}
