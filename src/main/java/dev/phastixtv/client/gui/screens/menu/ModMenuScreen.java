package dev.phastixtv.client.gui.screens.menu;

import dev.phastixtv.client.Mizu;
import dev.phastixtv.client.modules.overlay.fps.FpsConfig;
import dev.phastixtv.event.impl.ChatEvent;
import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.parsing.UIParsing;
import net.minecraft.network.message.SentMessage;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.awt.*;
import java.awt.event.ActionEvent;

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
            ChatEvent chatEvent = new ChatEvent();

            System.out.println(ChatEvent.getLastMessage());
        });
    }
}
