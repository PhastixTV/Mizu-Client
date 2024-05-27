package dev.phastixtv.mizu.gui.tictactoe;

import dev.phastixtv.mizu.Mizu;
import dev.phastixtv.mizu.util.render.RenderUtil;
import lombok.AllArgsConstructor;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

@AllArgsConstructor
public class TTTVerticalLine {

    private final int length;
    private final int width;
    private final int posX;
    private final int posY;
    private final int gap;
    private final Color color;

    public void render(DrawContext context) {
        RenderUtil.fillRect(context, posX, posY, width,length, color.getRGB());
        RenderUtil.fillRect(context, posX + gap, posY, width,length, color.getRGB());
    }
}
