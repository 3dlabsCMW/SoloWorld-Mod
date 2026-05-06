# FTB Offline Farms

FTB Offline Farms is a NeoForge **Minecraft 1.21.1** addon that uses **FTB Chunks selections as the source of truth** for chunk targeting.

## What it does
- Reads FTB Chunks data (force-loaded first) instead of custom selection commands.
- Default mode processes only FTB **force-loaded** chunks.
- Optionally includes claimed-but-not-force-loaded chunks (disabled by default due to performance risk).
- Prevents singleplayer pause behavior while the world is open (configurable).
- Applies conservative offline catch-up after reopening a world.

## What it does NOT do
- It **cannot** keep the world truly ticking after Minecraft is fully closed.
- True 24/7 ticking requires a dedicated server that stays online.

## Performance and safety design
- Conservative catch-up budgets and limits for heavy modpacks.
- Chunk queue processing over multiple ticks.
- No mob ticking/spawning simulation.
- No global redstone simulation.

## Installation (NeoForge 21.1.x, Minecraft 1.21.1)
1. Install Java 21.
2. Install NeoForge 21.1.x for Minecraft 1.21.1.
3. Install FTB Chunks matching your pack.
4. Build this mod (`./gradlew build`) and place output jar in `mods/`.
5. Launch game.

## Config
Config file: `config/ftb_offline_farms.json`

Key fields:
- `preventSingleplayerPause`
- `offlineCatchupEnabled`
- `maxOfflineCatchupMinutes`
- `scanOnlyForceLoadedChunks`
- `includeClaimedChunksWithoutForceLoad` (**warning: can cause severe lag on very large claimed areas**)
- `maxChunksPerLoginCatchup`
- `maxBlockEntitiesPerChunk`
- `maxBlockUpdatesPerLogin`
- `catchupWorkBudgetPerTick`
- `rescanIntervalSeconds`
- `debugLogging`

## Debug command
Optional:
- `/ftbofflinefarms debug`

Prints detected FTB target chunk count.
