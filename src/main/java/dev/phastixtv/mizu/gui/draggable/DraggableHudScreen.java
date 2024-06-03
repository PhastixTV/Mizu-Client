package dev.phastixtv.mizu.gui.draggable;

import dev.phastixtv.mizu.Mizu;
import dev.phastixtv.mizu.module.Module;
import dev.phastixtv.mizu.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;

public class DraggableHudScreen extends Screen {

    private Module selectedModule = null;
    private double offsetX, offsetY;
    private final ModuleManager moduleManager;
    private MinecraftClient mc = Mizu.INSTANCE.getMc();

    public DraggableHudScreen() {
        super(Text.literal("Draggable Hud"));
        this.moduleManager = Mizu.INSTANCE.getModuleManager();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        // Draw all modules
        for (Module module : moduleManager.getModules()) {
            if (module.isEnabled()) {
                context.drawText(mc.textRenderer, module.getName(), module.getPosX(), module.getPosY(), 0xFFFFFF, true);
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == GLFW.GLFW_MOUSE_BUTTON_1) {
            for (Module module : moduleManager.getModules()) {
                if (module.isEnabled()) {
                    int modX = module.getPosX();
                    int modY = module.getPosY();
                    int modWidth = mc.textRenderer.getWidth(module.getName());
                    int modHeight = mc.textRenderer.fontHeight;

                    if (mouseX >= modX && mouseX <= modX + modWidth &&
                            mouseY >= modY && mouseY <= modY + modHeight) {
                        selectedModule = module;
                        offsetX = mouseX - modX;
                        offsetY = mouseY - modY;
                        return true;
                    }
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == GLFW.GLFW_MOUSE_BUTTON_1) {
            selectedModule = null;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (selectedModule != null && button == GLFW.GLFW_MOUSE_BUTTON_1) {
            selectedModule.setPosX((int) (mouseX - offsetX));
            selectedModule.setPosY((int) (mouseY - offsetY));
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }
}
