package dev.phastixtv.mizu.module.impl.hud;

import com.google.common.eventbus.Subscribe;
import dev.phastixtv.mizu.Mizu;
import dev.phastixtv.mizu.event.impl.DrawOverlayEvent;
import dev.phastixtv.mizu.mixin.MinecraftClientAccessor;
import dev.phastixtv.mizu.module.Category;
import dev.phastixtv.mizu.module.Module;
import dev.phastixtv.mizu.module.ModuleInfo;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

@ModuleInfo(
        name = "FpsHud",
        description = "It shows your fps on the display",
        category = Category.HUD
)
public class FpsHud extends Module {

    @Subscribe
    public void onDrawOverlay(DrawOverlayEvent event) {
        DrawContext context = event.getContext();
        if (!this.mc.options.hudHidden && this.mc.player != null) {

            double guiScale = this.mc.getWindow().getScaleFactor();

            String text = this.mc.getCurrentFps() + " FPS";

            int maxTextPosX = this.mc.getWindow().getScaledWidth() - this.mc.textRenderer.getWidth(text);
            int maxTextPosY = this.mc.getWindow().getScaledHeight() - this.mc.textRenderer.fontHeight;
            int textPosX = Math.min(Math.round(10 / (float) guiScale), maxTextPosX);
            int textPosY = Math.min(Math.round(10 / (float) guiScale), maxTextPosY);

            context.drawText(mc.textRenderer, text, textPosX, textPosY, Color.WHITE.getRGB(), false);
        }
    }
}
