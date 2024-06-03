package dev.phastixtv.mizu.gui.tictactoe;

import dev.phastixtv.mizu.Mizu;
import dev.phastixtv.mizu.util.render.RenderUtil;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class TTTVerticalLine {
    private int height;
    private int width;
    private int x;
    private int y;
    private int gap;

    public void setVar(int height, int width, int x, int y, int gap) {
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        this.gap = gap;
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        RenderUtil.fillRect(context, x, y, width, height, Color.BLACK.getRGB());
        RenderUtil.fillRect(context, x + gap, y, width, height, Color.BLACK.getRGB());
    }
}
