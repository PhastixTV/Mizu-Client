package dev.phastixtv.mizu.gui.tictactoe;

import dev.phastixtv.mizu.Mizu;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TTTScreen extends Screen {

    private TTTVerticalLine tttVerticalLine;
    private TTTHorizontalLine tttHorizontalLine;
    private TTTBox tttBox;

    public static int player;
    public static int winner;
    public  static int state[];

    private int guiScale;

    @Getter
    private static final List<TTTBox> boxes = new ArrayList<>();

    public TTTScreen() {
        super(Text.of("Tic-Tac-Toe"));
        player = 0;
        winner = 0;
        state = new int[9];

        guiScale = (int) Mizu.INSTANCE.getMc().getWindow().getScaleFactor();

        tttVerticalLine = new TTTVerticalLine(
                780 / guiScale,
                10 / guiScale,
                this.width / 2 + (817 / guiScale),
                this.height / 2 + (120 / guiScale),
                this.width / 2 + (275 / guiScale),
                Color.BLACK
        );

        tttHorizontalLine = new TTTHorizontalLine(
                780 / guiScale,
                10 / guiScale,
                this.width / 2 + (550 / guiScale),
                this.height / 2 + (380 / guiScale),
                this.width / 2 + (270 / guiScale),
                Color.BLACK
        );

        int row = 0;
        int boxX = this.width / 2 + (550 / guiScale);
        int boxY = this.height / 2 + (120 / guiScale);
        int boxGapX = this.width / 2 + (280 / guiScale);
        int boxGapY = this.height / 2 + (280 / guiScale);

        for (int index = 0; index < 9; index++) {
            tttBox = new TTTBox(
                    280 / guiScale,
                    280 / guiScale,
                    boxX,
                    boxY,
                    new Color(50,50,50, 0)
            );
            boxes.add(tttBox);
            boxX += boxGapX;
            row++;

            if (row >= 3) {
                row = 0;
                boxX = this.width / 2 + (550 / guiScale);
                boxY += boxGapY;
            }
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        for (TTTBox box : boxes) {
            if (player == 1 && !box.isSelected()) {
                box.setSelected(true);
                player = 0;
            }
            box.render(context);
        }

        tttVerticalLine.render(context);
        tttHorizontalLine.render(context);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (TTTBox box : boxes) {
            box.mouseClicked(mouseX, mouseY, button);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
}
