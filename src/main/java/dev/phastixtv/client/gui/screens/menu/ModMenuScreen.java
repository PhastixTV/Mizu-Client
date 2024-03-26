package dev.phastixtv.client.gui.screens.menu;

import dev.phastixtv.client.Mizu;
import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.FlowLayout;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.awt.*;

public class ModMenuScreen extends BaseUIModelScreen<FlowLayout> {

    public ModMenuScreen() {
        super(FlowLayout.class, DataSource.asset(new Identifier("mizuclient:menu/modmenu")));
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        rootComponent.childById(ButtonComponent.class, "test").onPress(buttonComponent -> {
            if (Mizu.FPS_CONFIG.showFps()) {
                 Mizu.FPS_CONFIG.showFps(false);
            }else {
                Mizu.FPS_CONFIG.showFps(true);
            }
        });
    }
}
