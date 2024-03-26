package dev.phastixtv.client.modules.overlay.fps;

import com.sun.jna.platform.unix.X11;
import dev.phastixtv.client.Mizu;
import dev.phastixtv.client.modules.overlay.IOverlay;
import dev.phastixtv.mixin.MinecraftClientAccessor;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.core.*;
import io.wispforest.owo.ui.core.Component;
import io.wispforest.owo.ui.core.Insets;
import io.wispforest.owo.ui.hud.Hud;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.w3c.dom.css.RGBColor;

import java.awt.*;
import java.awt.Color;
import java.util.List;

import static com.ibm.icu.impl.breakiter.LSTMBreakEngine.LSTMClass.BEGIN;

public class FpsOverlay implements IOverlay {

    private MinecraftClient client = Mizu.minecraftClient;

    private final DrawContext context;

    private final float tickDelta;

    public FpsOverlay(DrawContext context, float tickDelta) {
        this.context = context;
        this.tickDelta = tickDelta;
        render(context, tickDelta);
    }

    public void render(DrawContext context, float tickDelta) {
        if (!Mizu.minecraftClient.options.hudHidden && Mizu.FPS_CONFIG.textAlpha() > 3 && Mizu.FPS_CONFIG.showFps() && client.player != null) {

            double guiScale = Mizu.minecraftClient.getWindow().getScaleFactor();

            List<String> text = List.of(((MinecraftClientAccessor) Mizu.minecraftClient).getCurrentFps() + Mizu.FPS_CONFIG.fps());

            int maxTextPosX = client.getWindow().getScaledWidth() - client.textRenderer.getWidth(this.getLongestString(text));
            int maxTextPosY = client.getWindow().getScaledHeight() - client.textRenderer.fontHeight;
            int textPosX = Math.min(Math.round(Mizu.FPS_CONFIG.offsetLeft() / (float) guiScale), maxTextPosX);
            int textPosY = Math.min(Math.round(Mizu.FPS_CONFIG.offsetTop() / (float) guiScale), maxTextPosY);

            int textColor = ((Mizu.FPS_CONFIG.textAlpha() & 0xFF) << 24) | Mizu.FPS_CONFIG.textColor();

            for (int i = 0; i < text.size(); i++) {
                String line = text.get(i);
                context.drawText(client.textRenderer, line, textPosX, textPosY, textColor, Mizu.FPS_CONFIG.drawWithShadows());
            }
        }
    }

    private String getLongestString(List<String> textLines) {
        return textLines
                .stream()
                .reduce("",
                        (longestText, text) -> longestText.length() < text.length() ? text : longestText
                );
    }
}
