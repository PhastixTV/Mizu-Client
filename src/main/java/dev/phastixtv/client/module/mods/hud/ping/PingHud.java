package dev.phastixtv.client.module.mods.hud.ping;

import dev.phastixtv.client.Mizu;
import dev.phastixtv.client.module.Module;
import dev.phastixtv.client.module.ModuleCategory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.PlayerListEntry;

import java.nio.charset.MalformedInputException;

public class PingHud extends Module {

    private final DrawContext context;

    public PingHud(DrawContext context) {
        super("ping-hud", ModuleCategory.HUD, false);
        this.context = context;
        render(context);
    }

    public void render(DrawContext context) {
        if (!Mizu.getInstance().getMinecraftClient().options.hudHidden && Mizu.getInstance().getPING_CONFIG().textAlpha() > 3 && Mizu.getInstance().getFPS_CONFIG().showFps() && this.client.player != null) {

            double guiScale = Mizu.getInstance().getMinecraftClient().getWindow().getScaleFactor();

            String text = getLocalPing() + " ms";

            int maxTextPosX = this.client.getWindow().getScaledWidth() - this.client.textRenderer.getWidth(text);
            int maxTextPosY = this.client.getWindow().getScaledHeight() - this.client.textRenderer.fontHeight;
            int textPosX = Math.min(Math.round(Mizu.getInstance().getPING_CONFIG().offsetLeft() / (float) guiScale), maxTextPosX);
            int textPosY = Math.min(Math.round(Mizu.getInstance().getPING_CONFIG().offsetTop() / (float) guiScale), maxTextPosY);

            int textColor = ((Mizu.getInstance().getPING_CONFIG().textAlpha() & 0xFF) << 24) | Mizu.getInstance().getPING_CONFIG().textColor();

            context.drawText(this.client.textRenderer, text, textPosX, textPosY, textColor, Mizu.getInstance().getPING_CONFIG().drawWithShadows());
        }
    }

    public static int getLocalPing() {
        ClientPlayNetworkHandler networkHandler = MinecraftClient.getInstance().getNetworkHandler();
        if (networkHandler == null) {
            return -1;
        }

        PlayerListEntry localPlayer = networkHandler.getPlayerListEntry(networkHandler.getProfile().getId());
        if (localPlayer == null) {
            return -1;
        }

        return localPlayer.getLatency();
    }
}
