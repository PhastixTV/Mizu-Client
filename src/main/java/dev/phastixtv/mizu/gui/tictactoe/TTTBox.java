package dev.phastixtv.mizu.gui.tictactoe;

import dev.phastixtv.mizu.util.render.RenderUtil;
import lombok.AllArgsConstructor;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

@AllArgsConstructor
public class TTTBox {

    private final int width;
    private final int height;
    private final int x;
    private final int y;
    private final Color color;

    public void render(DrawContext context) {
        RenderUtil.fillRect(context, x, y, width, height, color.getRGB());
    }
}
