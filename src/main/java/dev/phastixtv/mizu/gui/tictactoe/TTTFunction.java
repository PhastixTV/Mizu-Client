package dev.phastixtv.mizu.gui.tictactoe;

public class TTTFunction {

    public static void reset() {
        TTTScreen.player = 0;
        TTTScreen.winner = 0;
        TTTBox.selected = false;
    }
}
