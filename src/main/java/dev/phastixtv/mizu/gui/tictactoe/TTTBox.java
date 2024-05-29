package dev.phastixtv.mizu.gui.tictactoe;

import dev.phastixtv.mizu.Mizu;
import dev.phastixtv.mizu.util.render.HoverUtil;
import dev.phastixtv.mizu.util.render.RenderUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class TTTBox {

    private final int width;
    private final int height;
    private final int x;
    private final int y;
    private final Color color;


    @Getter
    @Setter
    public static boolean selected;
    @Getter
    private int player;

    public TTTBox(int width, int height, int x, int y, Color color) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.color = color;
        selected = false;
        player = 0;
    }

    public void render(DrawContext context) {
        RenderUtil.fillRect(context, x, y, width, height, color.getRGB());
        if (selected) {
            RenderUtil.drawTexture(context, player == 0 ? Mizu.INSTANCE.getX() : Mizu.INSTANCE.getO(), x, y, width, height);
        }
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (HoverUtil.rectHovered(x, y, width, height, mouseX, mouseY)) {
            if (selected) return false;
            if (TTTScreen.player == 0) {
                player = 1;
                TTTScreen.player = player;
            } else if (TTTScreen.player == 1) {
                player = 0;
                TTTScreen.player = player;
            }
            selected = true;
        }
        return true;
    }
}
