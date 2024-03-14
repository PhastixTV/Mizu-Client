package dev.phastixtv.client.gui.screens;

import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.container.FlowLayout;
import net.minecraft.util.Identifier;

public class TestScreen extends BaseUIModelScreen<FlowLayout> {

    public TestScreen() {
        super(FlowLayout.class, DataSource.asset(new Identifier("mizuclient:test")));
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        rootComponent.childById(ButtonComponent.class, "the-button").onPress(button -> {
            System.out.println("click");
        });
    }
}
