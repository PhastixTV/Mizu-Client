package dev.phastixtv.client.gui.screens.menu;

import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.container.FlowLayout;
import net.minecraft.util.Identifier;

public class OptionMenuScreen extends BaseUIModelScreen<FlowLayout> {

    public OptionMenuScreen() {
        super(FlowLayout.class, DataSource.asset(new Identifier("mizuclient:menu/clickgui")));
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        rootComponent.childById(ButtonComponent.class, "button-mods").onPress(buttonComponent -> {
            this.client.setScreen(new ModMenuScreen());
        });
    }
}
