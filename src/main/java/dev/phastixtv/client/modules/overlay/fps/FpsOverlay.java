package dev.phastixtv.client.modules.overlay.fps;

import dev.phastixtv.client.Mizu;
import dev.phastixtv.client.modules.overlay.IOverlay;
import dev.phastixtv.mixin.MinecraftClientAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.util.List;

public class FpsOverlay implements IOverlay {

    private final DrawContext context;
    private final float tickDelta;
    private final MinecraftClient client = Mizu.getInstance().getMinecraftClient();

    public FpsOverlay(DrawContext context, float tickDelta) {
        this.context = context;
        this.tickDelta = tickDelta;
        render(context, tickDelta);
    }

    public void render(DrawContext context, float tickDelta) {
        if (!Mizu.getInstance().getMinecraftClient().options.hudHidden && Mizu.getInstance().getFpsConfig().textAlpha() > 3 && Mizu.getInstance().getFpsConfig().showFps() && client.player != null) {

            double guiScale = Mizu.getInstance().getMinecraftClient().getWindow().getScaleFactor();

            List<String> text = List.of(((MinecraftClientAccessor) Mizu.getInstance().getMinecraftClient()).getCurrentFps() + Mizu.getInstance().getFpsConfig().fps());

            int maxTextPosX = client.getWindow().getScaledWidth() - client.textRenderer.getWidth(this.getLongestString(text));
            int maxTextPosY = client.getWindow().getScaledHeight() - client.textRenderer.fontHeight;
            int textPosX = Math.min(Math.round(Mizu.getInstance().getFPS_CONFIG().offsetLeft() / (float) guiScale), maxTextPosX);
            int textPosY = Math.min(Math.round(Mizu.getInstance().getFPS_CONFIG().offsetTop() / (float) guiScale), maxTextPosY);

            int textColor = ((Mizu.getInstance().getFPS_CONFIG().textAlpha() & 0xFF) << 24) | Mizu.getInstance().getFpsConfig().textColor();

            for (int i = 0; i < text.size(); i++) {
                String line = text.get(i);
                context.drawText(client.textRenderer, line, textPosX, textPosY, textColor, Mizu.getInstance().getFpsConfig().drawWithShadows());
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
