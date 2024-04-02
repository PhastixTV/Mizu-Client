package dev.phastixtv.client.module.mods.hud.ping;

import io.wispforest.owo.config.annotation.Config;

@Config(name = "ping", wrapperName = "PingConfig")
public class PingConfigModel {
    public boolean enabled = false;

    public boolean drawWithShadows = false;

    public boolean drawWithBackground = false;

    public int textSpacing = 5;

    public int textColor = 0xEEEEEE;

    public int textAlpha = 230;

    public int offsetLeft = 4;

    public int offsetTop = 8;

    public boolean showPing = false;
}
