package dev.phastixtv.mizu.gui.tictactoe;

import dev.phastixtv.mizu.Mizu;
import dev.phastixtv.mizu.util.render.HoverUtil;
import dev.phastixtv.mizu.util.render.RenderUtil;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class TTTBox {
    private int height;
    private int width;
    private int x;
    private int y;
    private int gapX;
    private int gapY;

    private boolean isPressed = false;
    private int player = 0;  // 0 for no player, 1 for player 1, 2 for player 2

    public void setVar(int height, int width, int x, int y, int gapX, int gapY) {
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        this.gapX = gapX;
        this.gapY = gapY;
    }

    public boolean player() {
        return player == 1;
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        RenderUtil.fillRect(context, x + gapX, y + gapY, width, height, new Color(0, 0, 0, 0).getRGB());
        if (isPressed) {
            RenderUtil.drawTexture(context, player() ? Mizu.INSTANCE.getX() : Mizu.INSTANCE.getO(), x + gapX, y + gapY, width, height);
        }
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isPressed) return false;
        if (HoverUtil.rectHovered(x + gapX, y + gapY, width, height, mouseX, mouseY)) {
            isPressed = true;
            player = Mizu.INSTANCE.getTtt().getPlayer();
            return true;
        }
        return false;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public void setPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public int getPlayer() {
        return player;
    }
}
