package dev.phastixtv.mizu.event.impl;

import dev.phastixtv.mizu.event.Event;
import net.minecraft.client.gui.DrawContext;

public class DrawOverlayEvent extends Event {

    private DrawContext context;

    public DrawOverlayEvent(DrawContext context) {
        this.context = context;
    }

    public DrawContext getContext() {
        return context;
    }
}
