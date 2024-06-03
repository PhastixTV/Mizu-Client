package dev.phastixtv.mizu.keys.impl;

import dev.phastixtv.mizu.Mizu;
import dev.phastixtv.mizu.gui.tictactoe.TTTScreen;
import dev.phastixtv.mizu.keys.Key;
import org.lwjgl.glfw.GLFW;

public class TTTKey implements Key {
    boolean pressed = false;

    @Override
    public int getKey() {
        return GLFW.GLFW_KEY_M;
    }

    @Override
    public String getName() {
        return "TTTKey";
    }

    @Override
    public void releaseAction() {

    }

    @Override
    public void pressAction() {
        Mizu.INSTANCE.getMc().setScreen(new TTTScreen());
    }

    @Override
    public void holdAction() {

    }

    @Override
    public boolean isPressed() {
        return false;
    }

    @Override
    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
}
