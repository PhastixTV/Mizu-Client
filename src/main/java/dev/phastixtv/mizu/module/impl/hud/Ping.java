package dev.phastixtv.mizu.module.impl.hud;

import com.google.common.eventbus.Subscribe;
import dev.phastixtv.mizu.event.impl.DrawOverlayEvent;
import dev.phastixtv.mizu.module.Category;
import dev.phastixtv.mizu.module.Module;
import dev.phastixtv.mizu.module.ModuleInfo;
import dev.phastixtv.mizu.util.render.RenderUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.PlayerListEntry;

@ModuleInfo(
        name = "Ping",
        description = "It show you what your ping is",
        category = Category.HUD
)
public class Ping extends Module {

    @Subscribe
    public void onDrawOverlay(DrawOverlayEvent event) {
        DrawContext context = event.getContext();


        double guiScale = this.mc.getWindow().getScaleFactor();

        String text = getLocalPing() + " ms";

        int maxTextPosX = this.mc.getWindow().getScaledWidth() - this.mc.textRenderer.getWidth(text);
        int maxTextPosY = this.mc.getWindow().getScaledHeight() - this.mc.textRenderer.fontHeight;
        int textPosX = Math.min(Math.round(10 / (float) guiScale), maxTextPosX);
        int textPosY = Math.min(Math.round(25 / (float) guiScale), maxTextPosY);

        RenderUtil.drawText(context, text, textPosX, textPosY, true);
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
