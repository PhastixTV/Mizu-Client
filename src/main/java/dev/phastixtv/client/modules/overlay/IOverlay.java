package dev.phastixtv.client.modules.overlay;

import net.minecraft.client.gui.DrawContext;

public interface IOverlay {
    public void render(DrawContext context, float tickDelta);
}
