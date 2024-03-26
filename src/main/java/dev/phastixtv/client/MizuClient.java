package dev.phastixtv.client;

import dev.phastixtv.client.keybinds.KeyBindHandler;
import dev.phastixtv.client.modules.overlay.fps.FpsConfigModel;
import dev.phastixtv.client.modules.overlay.fps.FpsOverlay;
import io.wispforest.owo.config.annotation.Config;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;

public class MizuClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyBindHandler.registerKeyBinds();
    }
}
