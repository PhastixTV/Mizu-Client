package dev.phastixtv.mizu.gui.modmenu;

import dev.phastixtv.mizu.Mizu;
import dev.phastixtv.mizu.module.Category;
import dev.phastixtv.mizu.util.render.RenderUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModMenuScreen extends Screen {

    private final List<CategoryButton> categories;

    private final Mizu client = Mizu.INSTANCE;
    private final MinecraftClient mc = client.getMc();

    public ModMenuScreen() {
        super(Text.literal("Mod Menu"));
        categories = new ArrayList<>();

        double guiScale = this.mc.getWindow().getScaleFactor();

        int maxPosX = this.mc.getWindow().getScaledWidth();
        int maxPosY = this.mc.getWindow().getScaledHeight();
        int posX = Math.min(Math.round(250 / (float) guiScale), maxPosX);
        int offset = Math.min(Math.round(150 / (float) guiScale), maxPosY);

        for (Category category : Category.values()) {
            categories.add(new CategoryButton(category, posX, offset , 100, 25, 10, new Color(32, 32, 32, 255), new Color(24, 24, 24, 255) ,true));
            offset += 35;
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int guiScale = (int) this.mc.getWindow().getScaleFactor();

        int objHeight = 400;
        int objWidth = 775;
        int posX = this.width / this.mc.getWindow().getScaledWidth() * guiScale;
        int posY = this.height / this.mc.getWindow().getScaledHeight() * guiScale;

        RenderUtil.fillRoundRect(context, posX + 100, posY + 50, objWidth, objHeight, 10, new Color(28, 28, 28, 255).getRGB());

        /*
        int posX = this.width / 2;
        int posY = this.height / 2;

        RenderUtil.fillRoundRect(context, posX - 220, posY - 20, 775, 400, 10, new Color(32, 32, 32, 255).getRGB());
        RenderUtil.fillRoundRect(context, posX - 200, posY - 100, 775, 400, 10, new Color(64, 64, 64, 255).getRGB());
        RenderUtil.fillRoundRect(context, this.width, this.height, 775, 400, 10, new Color(64, 64, 64, 255).getRGB());
         */

        for (CategoryButton categoryButtons : categories) {
            categoryButtons.render(context, mouseX, mouseY, delta);
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE || keyCode == GLFW.GLFW_KEY_RIGHT_SHIFT) {
            // client.getModuleManager().getModule(ClickGui.class).toggle();
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (CategoryButton categoryButtons : categories) {
            categoryButtons.mouseClicked(mouseX, mouseY, button);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
}
