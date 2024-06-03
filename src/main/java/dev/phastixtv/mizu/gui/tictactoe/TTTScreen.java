package dev.phastixtv.mizu.gui.tictactoe;

import dev.phastixtv.mizu.Mizu;
import dev.phastixtv.mizu.util.render.RenderUtil;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class TTTScreen extends Screen {
    private final TTT ttt;
    public ButtonWidget resetButton;

    public TTTScreen() {
        super(Text.literal("Play Tic Tac Toe"));
        this.ttt = Mizu.INSTANCE.getTtt();
        ttt.init();
    }

    @Override
    protected void init() {
        int guiScale = (int) Mizu.INSTANCE.getMc().getWindow().getScaleFactor();

        int width = 300 / guiScale;
        int height = 300 / guiScale;
        int x = this.width / 2 - (450 / guiScale);
        int y = this.height / 2 - (450 / guiScale);
        int gapX = 0;
        int gapY = 0;

        int row = 0;
        for (int i = 0; i < 9; i++) {
            if (row == 3) {
                gapY += 305 / guiScale;
                gapX = 0;
                row = 0;
            }
            row++;
            if (i == 1 || i == 2 || i == 4 || i == 5 || i == 7 || i == 8) {
                gapX += 305 / guiScale;
            }

            ttt.getBox()[i] = new TTTBox();
            ttt.getBox()[i].setVar(height, width, x, y, gapX, gapY);
        }

        width = 900 / guiScale;
        height = 10 / guiScale;
        x = this.width / 2 - (450 / guiScale);
        y = this.height / 2 + (150 / guiScale);

        int gap = 300 / guiScale;

        ttt.getHorizontalLine().setVar(height, width, x, y, gap);

        width = 10 / guiScale;
        height = 900 / guiScale;
        x = this.width / 2 - (150 / guiScale);
        y = this.height / 2 - (450 / guiScale);

        ttt.getVerticalLine().setVar(height, width, x, y, gap);

        // Reset Button
        /*
        resetButton = ButtonWidget.builder(Text.literal("Reset"), button -> {
            ttt.resetGame();
        }).dimensions(this.width / 2 - 50, this.height / 2 + (450 / guiScale) + 20, 100, 20).build();

        addDrawableChild(resetButton);

         */
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        for (int i = 0; i < 9; i++) {
            ttt.getBox()[i].render(context, mouseX, mouseY, delta);
        }
        ttt.getVerticalLine().render(context, mouseX, mouseY, delta);
        ttt.getHorizontalLine().render(context, mouseX, mouseY, delta);

        if (ttt.getWinner() != 0) {
            String message = ttt.getWinner() == 1 ? "Player Wins!" : ttt.getWinner() == 2 ? "Bot Wins!" : "Draw!";
            RenderUtil.drawCenteredText(context, message, this.width / 2, this.height / 2 - 60, 15 / (int) Mizu.INSTANCE.getMc().getWindow().getScaleFactor(), true);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (int i = 0; i < ttt.getBox().length; i++) {
            if (ttt.getBox()[i].mouseClicked(mouseX, mouseY, button)) {
                ttt.updateBoxState(i);
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }
}
