package dev.phastixtv.client.gui.screens.menu;

import dev.phastixtv.client.Mizu;
import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.container.FlowLayout;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ClickGUIScreen extends BaseUIModelScreen<FlowLayout> {

    public ClickGUIScreen() {
        super(FlowLayout.class, DataSource.asset(new Identifier("mizuclient:menu/clickgui")));
    }
    @Override
    protected void build(FlowLayout rootComponent) {
        rootComponent.childById(ButtonComponent.class, "button-mods").onPress(buttonComponent -> {
            System.out.println("Clickgui");
            this.client.setScreen(new ModMenuScreen());
        });
    }
}
