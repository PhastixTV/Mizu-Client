package dev.phastixtv.client.module.mods.hud;

import net.minecraft.client.gui.DrawContext;

public interface IHud {
    void render(DrawContext context, float tickDelta);
}
