package dev.phastixtv.client.gui.screens.menu;

import dev.phastixtv.client.Mizu;
import dev.phastixtv.client.module.mods.hud.fps.FpsConfig;
import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.container.FlowLayout;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModMenuScreen extends BaseUIModelScreen<FlowLayout> {

    public ModMenuScreen() {
        super(FlowLayout.class, DataSource.asset(new Identifier("mizuclient:modmenu/modmenu")));
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        rootComponent.childById(ButtonComponent.class, "fps-button").onPress(buttonComponent -> {
            FpsConfig config = Mizu.getInstance().getFPS_CONFIG();

            if (config.showFps() == false) {
                buttonComponent.setMessage(Text.literal("FPS: enabled"));
                config.showFps(true);
            } else {
                buttonComponent.setMessage(Text.literal("FPS: disabled"));
                config.showFps(false);
            }
        });

        rootComponent.childById(ButtonComponent.class, "test-button").onPress(buttonComponent -> {

        });

    }
}
