package com.matt.ftbofflinefarms.mixin;

import net.minecraft.client.gui.screens.PauseScreen;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PauseScreen.class)
public abstract class PauseScreenMixin {
    // Intentionally minimal placeholder.
    // Use a client-side injection to gate pause behavior with preventSingleplayerPause config.
}
