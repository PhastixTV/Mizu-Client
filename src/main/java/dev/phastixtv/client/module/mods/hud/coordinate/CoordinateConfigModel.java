package dev.phastixtv.client.module.mods.hud.coordinate;

import io.wispforest.owo.config.annotation.Config;

@Config(name = "coordinate", wrapperName = "CoordinateConfig")
public class CoordinateConfigModel {

    public boolean enabled = false;

    public boolean drawWithShadows = false;

    public boolean drawWithBackground = false;

    public int textSpacing = 5;

    public int textColor = 0xEEEEEE;

    public int textAlpha = 230;

    public int offsetLeft = 40;

    public int offsetTop = 100;

    public boolean showCoordinates = true;
}
