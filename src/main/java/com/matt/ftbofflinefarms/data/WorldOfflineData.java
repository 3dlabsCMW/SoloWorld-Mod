package com.matt.ftbofflinefarms.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public class WorldOfflineData extends SavedData {
    public static final String DATA_NAME = "ftbofflinefarms_world_data";
    public long lastUnloadEpochSeconds;
    public long lastCatchupEpochSeconds;

    public static WorldOfflineData load(CompoundTag tag) {
        WorldOfflineData data = new WorldOfflineData();
        data.lastUnloadEpochSeconds = tag.getLong("lastUnloadEpochSeconds");
        data.lastCatchupEpochSeconds = tag.getLong("lastCatchupEpochSeconds");
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putLong("lastUnloadEpochSeconds", lastUnloadEpochSeconds);
        tag.putLong("lastCatchupEpochSeconds", lastCatchupEpochSeconds);
        return tag;
    }

    public static WorldOfflineData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(new Factory<>(WorldOfflineData::new, WorldOfflineData::load), DATA_NAME);
    }
}
