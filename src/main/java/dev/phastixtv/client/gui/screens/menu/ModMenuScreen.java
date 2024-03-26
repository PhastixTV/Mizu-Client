package dev.phastixtv.client.gui.screens.menu;

import dev.phastixtv.client.Mizu;
import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.container.FlowLayout;
import net.minecraft.util.Identifier;

public class ModMenuScreen extends BaseUIModelScreen<FlowLayout> {

    public ModMenuScreen() {
        super(FlowLayout.class, DataSource.asset(new Identifier("mizuclient:menu/modmenu")));
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        rootComponent.childById(ButtonComponent.class, "test").onPress(buttonComponent -> {
            Mizu.getInstance().getFpsConfig().showFps(!Mizu.getInstance().getFpsConfig().showFps());
        });
    }
}
