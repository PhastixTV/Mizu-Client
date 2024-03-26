package dev.phastixtv.config;

import dev.phastixtv.client.modules.overlay.fps.FpsConfigModel;
import io.wispforest.owo.config.annotation.Config;

@Config(name = "mizu", wrapperName = "MizuConfig")
public class MizuConfigModel {

    public int anIntOption = 16;
    public boolean aBooleanToggle = false;

    public enum Choices {
        A_CHOICE, ANOTHER_CHOICE;
    }

    public boolean loggedin = false;
}
