package dev.phastixtv.mizu.gui.modmenu;

import dev.phastixtv.mizu.Mizu;
import dev.phastixtv.mizu.module.Category;
import dev.phastixtv.mizu.module.Module;
import dev.phastixtv.mizu.util.render.HoverUtil;
import dev.phastixtv.mizu.util.render.RenderUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryButton {

    private final List<ModuleButton> moduleButtons;
    
    private final Mizu client = Mizu.INSTANCE;
    private final MinecraftClient mc = client.getMc();

    public Category category;
    public boolean selected;
    public boolean textShadow;
    public int x, y, width, height, radius;
    public Color disabledColor;
    public Color enabledColor;
    
    public CategoryButton(Category category, int x, int y, int width, int height, int radius, Color disabledColor,  Color enabledColor, boolean textShadow) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radius = radius;
        this.disabledColor = disabledColor;
        this.enabledColor = enabledColor;
        this.textShadow = textShadow;
        
        moduleButtons = new ArrayList<>();

        double guiScale = this.mc.getWindow().getScaleFactor();

        int maxPosX = this.mc.getWindow().getScaledWidth();
        int maxPosY = this.mc.getWindow().getScaledHeight();
        int posX = Math.min(Math.round(700 / (float) guiScale), maxPosX);
        int posY = Math.min(Math.round(150 / (float) guiScale), maxPosY);

        int index = 0;

        for (Module module : Mizu.INSTANCE.getModuleManager().getModules(category)) {
            moduleButtons.add(new ModuleButton(module, posX, posY, 100, 100, 10));
            posX += 125;
            index++;
            if (index >= 4) {
                posY += 125;
                posX -= 4 * 125;
                index = 0;
            }
        }
    }
    
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        TextRenderer textRenderer = mc.textRenderer;
        
        RenderUtil.fillRoundRect(context, x, y, width, height, radius, selected ? enabledColor.getRGB() : disabledColor.getRGB());
        
        context.drawText(textRenderer, category.getName(), x + 30, y + 8, Color.WHITE.getRGB(), textShadow);
        context.drawItem(category.getItem(), x + 7, y + 4);

        if (selected) {
            for (ModuleButton button : moduleButtons) {
                button.render(context, mouseX, mouseY, delta);
            }
        }
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (ModuleButton moduleButton : moduleButtons) {
            moduleButton.mouseClicked(mouseX, mouseY, button);
        }
        if (HoverUtil.rectHovered(x, y, width, height, mouseX, mouseY) && button == 0) {
            selected = !selected;
        }
        return true;
    }

}
