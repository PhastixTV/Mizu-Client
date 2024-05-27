package dev.phastixtv.mizu.event.impl;

import dev.phastixtv.mizu.Mizu;
import dev.phastixtv.mizu.event.Event;
import dev.phastixtv.mizu.event.EventManager;
import dev.phastixtv.mizu.keys.Key;
import dev.phastixtv.mizu.keys.KeyInputRegistry;
import net.minecraft.client.MinecraftClient;

import java.awt.event.KeyEvent;

public class ScrollDownEvent extends Event {

    public ScrollDownEvent() {
        EventManager.register(this);
        for (Key key : KeyInputRegistry.keys) {
            if (key.isPressed()) {
                key.onScrollDown();
            }
        }
    }
}
