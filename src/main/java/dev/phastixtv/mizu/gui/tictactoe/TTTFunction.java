package dev.phastixtv.mizu.gui.tictactoe;

public class TTTFunction {

    public static void reset() {
        for (int i = 0; i < TTTScreen.state.length; i++) {
            TTTScreen.state[i] = 0;
        }

        TTTScreen.player = 0;
        TTTScreen.winner = 0;
    }
}
