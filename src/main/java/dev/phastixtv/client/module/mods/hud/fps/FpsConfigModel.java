package dev.phastixtv.client.module.mods.hud.fps;

import io.wispforest.owo.config.annotation.Config;

@Config(name = "fps", wrapperName = "FpsConfig")
public class FpsConfigModel {

    public boolean enabled = false;

    public boolean drawWithShadows = false;

    public boolean drawWithBackground = false;

    public int textSpacing = 5;

    public int textColor = 0xEEEEEE;

    public int textAlpha = 230;

    public int offsetLeft = 4;

    public int offsetTop = 4;

    public boolean showFps = false;

    public String fps = " FPS";
}
