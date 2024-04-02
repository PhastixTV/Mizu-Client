package dev.phastixtv.client.module.mods.hud.coordinate;

import dev.phastixtv.client.Mizu;
import dev.phastixtv.client.module.Module;
import dev.phastixtv.client.module.ModuleCategory;
import dev.phastixtv.client.module.mods.hud.IHud;
import dev.phastixtv.client.module.mods.hud.coordinate.CoordinateConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.util.List;

public class CoordinateHud extends Module {
    private final DrawContext context;
    private final float tickDelta;
    private final MinecraftClient client = Mizu.getInstance().getMinecraftClient();

    public CoordinateHud(DrawContext context, float tickDelta) {
        super("coordinate-hud", ModuleCategory.HUD, Mizu.getInstance().getCOORDINATE_CONFIG().showCoordinates());
        this.context = context;
        this.tickDelta = tickDelta;
        render(context, tickDelta);
    }

    public void render(DrawContext context, float tickDelta) {
        if (!Mizu.getInstance().getMinecraftClient().options.hudHidden && Mizu.getInstance().getCOORDINATE_CONFIG().textAlpha() > 3 && Mizu.getInstance().getCOORDINATE_CONFIG().showCoordinates() && client.player != null) {

            double guiScale = Mizu.getInstance().getMinecraftClient().getWindow().getScaleFactor();

            String text = "X: " + Math.round(client.player.getX()) + " Y: " + Math.round(client.player.getY() ) + " Z: " + Math.round(client.player.getZ());

            int maxTextPosX = client.getWindow().getScaledWidth() - client.textRenderer.getWidth(text);
            int maxTextPosY = client.getWindow().getScaledHeight() - client.textRenderer.fontHeight;
            int textPosX = Math.min(Math.round(Mizu.getInstance().getCOORDINATE_CONFIG().offsetLeft() / (float) guiScale), maxTextPosX);
            int textPosY = Math.min(Math.round(Mizu.getInstance().getCOORDINATE_CONFIG().offsetTop() / (float) guiScale), maxTextPosY);

            int textColor = ((Mizu.getInstance().getCOORDINATE_CONFIG().textAlpha() & 0xFF) << 24) | Mizu.getInstance().getFPS_CONFIG().textColor();

            context.drawText(client.textRenderer, text, textPosX, textPosY, textColor, Mizu.getInstance().getCOORDINATE_CONFIG().drawWithShadows());
        }
    }
}
