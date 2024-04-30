package dev.phastixtv.mizu.event.impl;

import dev.phastixtv.mizu.event.Event;
import net.minecraft.client.gui.screen.Screen;

public class OpenScreenEvent extends Event {

    private final Screen screen;

    public OpenScreenEvent(Screen screen) {
        this.screen = screen;
    }

    public Screen getScreen() {
        return screen;
    }
}
