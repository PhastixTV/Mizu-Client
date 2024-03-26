package dev.phastixtv.client;

import dev.phastixtv.client.keybinds.KeyBindHandler;
import net.fabricmc.api.ClientModInitializer;

public class MizuClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyBindHandler.registerKeyBinds();
    }

}