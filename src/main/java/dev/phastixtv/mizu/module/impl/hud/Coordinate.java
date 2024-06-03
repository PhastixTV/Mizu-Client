package dev.phastixtv.mizu.module.impl.hud;

import com.google.common.eventbus.Subscribe;
import dev.phastixtv.mizu.event.impl.DrawOverlayEvent;
import dev.phastixtv.mizu.module.Category;
import dev.phastixtv.mizu.module.Module;
import dev.phastixtv.mizu.module.ModuleInfo;
import dev.phastixtv.mizu.util.render.RenderUtil;
import net.minecraft.client.gui.DrawContext;

@ModuleInfo(
        name = "Coordinate",
        description = "It shows your coordinates",
        category = Category.HUD
)
public class Coordinate extends Module {

    @Subscribe
    public void onDrawOverlayer(DrawOverlayEvent event) {
        DrawContext context = event.getContext();

        double guiScale = this.mc.getWindow().getScaleFactor();

        String text = "X: " + Math.round(mc.player.getX())
                + " Y: " + Math.round(mc.player.getY() )
                + " Z: " + Math.round(mc.player.getZ());

        int maxTextPosX = this.mc.getWindow().getScaledWidth() - this.mc.textRenderer.getWidth(text);
        int maxTextPosY = this.mc.getWindow().getScaledHeight() - this.mc.textRenderer.fontHeight;
        //int textPosX = Math.min(Math.round(10 / (float) guiScale), maxTextPosX);
        // int textPosY = Math.min(Math.round(10 / (float) guiScale), maxTextPosY);
        int textPosX = getPosX();
        int textPosY = getPosY();

        RenderUtil.drawText(context, text, textPosX, textPosY, true);
    }
}
