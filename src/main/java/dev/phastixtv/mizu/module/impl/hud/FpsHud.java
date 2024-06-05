package dev.phastixtv.mizu.module.impl.hud;

import com.google.common.eventbus.Subscribe;
import dev.phastixtv.mizu.Mizu;
import dev.phastixtv.mizu.event.impl.DrawOverlayEvent;
import dev.phastixtv.mizu.mixin.MinecraftClientAccessor;
import dev.phastixtv.mizu.module.Category;
import dev.phastixtv.mizu.module.Module;
import dev.phastixtv.mizu.module.ModuleInfo;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

@ModuleInfo(
        name = "Fps",
        description = "It shows your fps on the display",
        category = Category.HUD
)

@Getter
public class FpsHud extends Module {

    @Override
    protected void init() {
        super.init();
        setPosX(50);
        setPosY(10);
    }

    @Subscribe
    public void onDrawOverlay(DrawOverlayEvent event) {
        DrawContext context = event.getContext();
        if (!this.mc.options.hudHidden && this.mc.player != null) {
            String text = this.mc.getCurrentFps() + " FPS";

            int textPosX = getPosX();
            int textPosY = getPosY();

            context.drawText(mc.textRenderer, text, textPosX, textPosY, Color.WHITE.getRGB(), true);
        }
    }
}
