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
    }

    public void init() {
        int guiScale = (int) this.mc.getWindow().getScaleFactor();
        int objHeight = 50 / guiScale;
        int objWidth = 200 / guiScale;
        int radius = 20 / guiScale;
        int posX = this.width / 2 - (550 / guiScale);
        int posY = this.height / 2 - (300 / guiScale) ;

        for (Category category : Category.values()) {
            categories.add(new CategoryButton(category, posX, posY, objWidth, objHeight, radius, new Color(32, 32, 32, 255), new Color(24, 24, 24, 255) ,true));
            posY += 70 / guiScale;
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int guiScale = (int) this.mc.getWindow().getScaleFactor();
        int objHeight = 700 / guiScale;
        int objWidth = 1200 / guiScale;
        int radius = 40 / guiScale;
        int posX = this.width / 2 - (600 / guiScale);
        int posY = this.height / 2 - (350 / guiScale);

        RenderUtil.fillRoundRect(context, posX, posY, objWidth, objHeight, radius, new Color(28, 28, 28, 255).getRGB());

        for (CategoryButton categoryButtons : categories) {
            categoryButtons.render(context, mouseX, mouseY, delta);
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
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
