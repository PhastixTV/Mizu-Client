package dev.phastixtv.mizu.gui.tictactoe;

import dev.phastixtv.mizu.Mizu;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.util.Random;

@Getter
@Setter
public class TTT {

    private int player;
    private int winner;
    private int[] state;
    private TTTBox[] box;
    private TTTVerticalLine verticalLine;
    private TTTHorizontalLine horizontalLine;

    private MinecraftClient mc;

    public TTT() {
        this.mc = Mizu.INSTANCE.getMc();
    }

    public void init() {
        player = 1;
        winner = 0;
        state = new int[9];
        box = new TTTBox[9];
        verticalLine = new TTTVerticalLine();
        horizontalLine = new TTTHorizontalLine();
    }

    public void updateBoxState(int index) {
        if (state[index] != 0 || winner != 0) return;

        state[index] = player;
        box[index].setPlayer(player);
        if (checkWin(player)) {
            winner = player;
        } else if (isBoardFull()) {
            winner = 3; // Draw
        } else {
            player = player == 1 ? 2 : 1;
            if (player == 2) {
                makeBotMove();
            }
        }
    }

    private void makeBotMove() {
        if (winner != 0) return; // Do not make a move if there's already a winner

        Random random = new Random();
        int index;

        do {
            index = random.nextInt(9);
        } while (state[index] != 0);

        state[index] = player;
        box[index].setPlayer(player);
        box[index].setPressed(true);

        if (checkWin(player)) {
            winner = player;
        } else if (isBoardFull()) {
            winner = 3; // Draw
        } else {
            player = 1;
        }
    }

    private boolean checkWin(int player) {
        // Check all win conditions
        int[][] winConditions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // columns
                {0, 4, 8}, {2, 4, 6}             // diagonals
        };

        for (int[] condition : winConditions) {
            if (state[condition[0]] == player && state[condition[1]] == player && state[condition[2]] == player) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int value : state) {
            if (value == 0) return false;
        }
        return true;
    }

    public void resetGame() {
        init();
    }
}
