package dev.phastixtv.mizu.gui.tictactoe;

import dev.phastixtv.mizu.Mizu;

public class TTTGewinnRoutine {

    public TTTGewinnRoutine(TTTBox box) {
        Mizu.SCHEDULER.runTask(new Runnable() {
            @Override
            public void run() {
                if (TTTScreen.winner == 0) {

                }
            }
        });
    }
}