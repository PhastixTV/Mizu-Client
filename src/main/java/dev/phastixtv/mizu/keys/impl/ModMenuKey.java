package dev.phastixtv.mizu.keys.impl;

import dev.phastixtv.mizu.Mizu;
import dev.phastixtv.mizu.gui.modmenu.ModMenuScreen;
import dev.phastixtv.mizu.keys.Key;
import org.lwjgl.glfw.GLFW;

public class ModMenuKey implements Key {
    boolean pressed = false;

    @Override
    public int getKey() {
        return GLFW.GLFW_KEY_RIGHT_SHIFT;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void releaseAction() {

    }

    @Override
    public void pressAction() {
        Mizu.INSTANCE.getMc().setScreen(new ModMenuScreen());
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
