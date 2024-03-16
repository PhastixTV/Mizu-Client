package dev.phastixtv.client.gui.screens.menu;

import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.FlowLayout;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModMenuScreen extends BaseUIModelScreen<FlowLayout> {

    Boolean test_button = true;

    public ModMenuScreen() {
        super(FlowLayout.class, DataSource.asset(new Identifier("mizuclient:menu/banner_editor")));
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        /*
        rootComponent.childById(ButtonComponent.class, "test").onPress(buttonComponent -> {
            if (test_button) {
                buttonComponent.setMessage(Text.literal("FALSE"));
                test_button = false;
            }else if (!test_button) {
                buttonComponent.setMessage(Text.literal("ENABLED"));
                test_button = true;
            } else
                System.out.println("Test not work");
        });

         */
    }
}
