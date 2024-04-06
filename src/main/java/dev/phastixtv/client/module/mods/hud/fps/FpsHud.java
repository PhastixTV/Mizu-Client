package dev.phastixtv.client.module.mods.hud.fps;

import com.google.common.eventbus.Subscribe;
import dev.phastixtv.client.Mizu;
import dev.phastixtv.client.module.Module;
import dev.phastixtv.client.module.ModuleCategory;
import dev.phastixtv.client.module.ModuleManager;
import dev.phastixtv.client.module.mods.hud.IHud;
import dev.phastixtv.mixin.MinecraftClientAccessor;
import net.minecraft.client.gui.DrawContext;

public class FpsHud extends Module {

    private final DrawContext context;

    public FpsHud(DrawContext context) {
        super("fps-hud", ModuleCategory.HUD, Mizu.getInstance().getFPS_CONFIG().showFps());
        this.context = context;
        render(context);
    }

    public void render(DrawContext context) {
        if (!Mizu.getInstance().getMinecraftClient().options.hudHidden && Mizu.getInstance().getFPS_CONFIG().textAlpha() > 3 && Mizu.getInstance().getFPS_CONFIG().showFps() && client.player != null) {

            double guiScale = Mizu.getInstance().getMinecraftClient().getWindow().getScaleFactor();

            String text = ((MinecraftClientAccessor) Mizu.getInstance().getMinecraftClient()).getCurrentFps() + Mizu.getInstance().getFPS_CONFIG().fps();

            int maxTextPosX = this.client.getWindow().getScaledWidth() - this.client.textRenderer.getWidth(text);
            int maxTextPosY = this.client.getWindow().getScaledHeight() - this.client.textRenderer.fontHeight;
            int textPosX = Math.min(Math.round(Mizu.getInstance().getFPS_CONFIG().offsetLeft() / (float) guiScale), maxTextPosX);
            int textPosY = Math.min(Math.round(Mizu.getInstance().getFPS_CONFIG().offsetTop() / (float) guiScale), maxTextPosY);

            int textColor = ((Mizu.getInstance().getFPS_CONFIG().textAlpha() & 0xFF) << 24) | Mizu.getInstance().getFPS_CONFIG().textColor();

            context.drawText(this.client.textRenderer, text, textPosX, textPosY, textColor, Mizu.getInstance().getFPS_CONFIG().drawWithShadows());
        }
    }
}
