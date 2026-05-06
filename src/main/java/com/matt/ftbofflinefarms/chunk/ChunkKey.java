package com.matt.ftbofflinefarms.chunk;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import java.util.UUID;

public record ChunkKey(ResourceKey<Level> dimension, int chunkX, int chunkZ, UUID teamId, boolean claimed, boolean forceLoaded) {}
