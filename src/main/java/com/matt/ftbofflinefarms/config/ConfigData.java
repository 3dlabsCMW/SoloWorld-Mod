package com.matt.ftbofflinefarms.config;

public class ConfigData {
    public boolean preventSingleplayerPause = true;
    public boolean offlineCatchupEnabled = true;
    public int maxOfflineCatchupMinutes = 240;
    public boolean scanOnlyForceLoadedChunks = true;
    public boolean includeClaimedChunksWithoutForceLoad = false;
    public int maxChunksPerLoginCatchup = 64;
    public int maxBlockEntitiesPerChunk = 512;
    public int maxBlockUpdatesPerLogin = 5000;
    public int catchupWorkBudgetPerTick = 200;
    public int rescanIntervalSeconds = 30;
    public boolean debugLogging = false;

    public static ConfigData defaults() { return new ConfigData(); }

    public ConfigData clamp() {
        maxOfflineCatchupMinutes = clamp(maxOfflineCatchupMinutes, 1, 24 * 60);
        maxChunksPerLoginCatchup = clamp(maxChunksPerLoginCatchup, 1, 2048);
        maxBlockEntitiesPerChunk = clamp(maxBlockEntitiesPerChunk, 1, 4096);
        maxBlockUpdatesPerLogin = clamp(maxBlockUpdatesPerLogin, 100, 1_000_000);
        catchupWorkBudgetPerTick = clamp(catchupWorkBudgetPerTick, 10, 10_000);
        rescanIntervalSeconds = clamp(rescanIntervalSeconds, 5, 600);
        return this;
    }

    private static int clamp(int value, int min, int max) { return Math.max(min, Math.min(max, value)); }
}
