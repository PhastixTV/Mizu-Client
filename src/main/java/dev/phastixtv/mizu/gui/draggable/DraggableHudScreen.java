package dev.phastixtv.mizu.gui.draggable;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class DraggableHudScreen extends Screen {
    protected DraggableHudScreen() {
        super(Text.literal("Draggable Hud"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {

    }
}
