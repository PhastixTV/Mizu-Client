package dev.phastixtv.mizu.gui.modmenu;

import dev.phastixtv.mizu.Mizu;
import dev.phastixtv.mizu.module.Module;
import dev.phastixtv.mizu.util.render.HoverUtil;
import dev.phastixtv.mizu.util.render.RenderUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.sound.Sound;

import java.awt.*;

public class ModuleButton {

    private final Mizu client = Mizu.INSTANCE;
    private final MinecraftClient mc = client.getMc();

    public Module module;
    public int x;
    public int y;
    public int width;
    public int height;
    public int radius;


    public ModuleButton(Module module, int x, int y, int width, int height, int radius) {
        this.module = module;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radius = radius;
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        TextRenderer textRenderer = mc.textRenderer;
        float guiScale = (float) mc.getWindow().getScaleFactor();

        RenderUtil.fillRoundRect(context, x, y, width, height, radius, new Color(35, 35, 35, 255).getRGB());
        RenderUtil.fillRoundBottomRect(context, x, y + 80, width, height - 80, radius, module.isEnabled() ? Color.GREEN.getRGB() : Color.RED.getRGB());
        RenderUtil.drawCenteredText(context, module.getName(), x + 50, y + 30, 2 / guiScale,true);
        RenderUtil.drawCenteredText(context, module.isEnabled() ? "Enabled" : "Disabled", x + 50, y + 80,2 / guiScale,true);
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (HoverUtil.rectHovered(x, y + 80, width, height - 80, mouseX, mouseY) && button == 0) {
           module.toggle();
        }
        return true;
    }
}
