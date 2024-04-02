package dev.phastixtv.client.modules.overlay.fps;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;

@Config(name = "fps", wrapperName = "FpsConfig")
public class FpsConfigModel {

    public boolean enabled = false;

    public boolean drawWithShadows = false;

    public boolean drawWithBackground = false;

    public int textSpacing = 5;

    public int textColor = 0xEEEEEE;

    public int textAlpha = 230;

    public int offsetLeft = 400;

    public int offsetTop = 4;

    public boolean showFps = true;

    public String fps = " FPS";
}
