package dev.phastixtv.config;

import io.wispforest.owo.config.annotation.Config;

@Config(name = "mizu", wrapperName = "MizuConfig")
public class MizuConfigModel {

    public int anIntOption = 16;
    public boolean aBooleanToggle = false;
    public boolean loggedin = false;

    public enum Choices {
        A_CHOICE, ANOTHER_CHOICE
    }
}
