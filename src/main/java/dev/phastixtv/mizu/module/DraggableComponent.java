package dev.phastixtv.mizu.module;

import dev.phastixtv.mizu.keys.Key;
import dev.phastixtv.mizu.util.render.RenderUtil;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Mouse;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Mouse;

@Getter
@Setter
public class DraggableComponent {

    private final DrawContext context;

    private int x;
    private int y;
    private int width;
    private int height;
    private int color;
    private int lastX;
    private int lastY;

    private boolean dragging;

    public DraggableComponent(DrawContext context, int x, int y, int width, int height, int color){
        this.context = context;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void render(int mouseX, int mouseY){
        draggingFix(mouseX, mouseY);
        RenderUtil.fillRect(context, this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY + this.getHeight(), this.getColor());
        boolean mouseOverX = (mouseX >= this.getX && mouseX <= this.getX+this.getWidth());
        boolean mouseOverY = (mouseY >= this.getY && mouseY <= this.getY()+this.getHeight());
        if(mouseOverX && mouseOverY){
            if(){
                if (!this.dragging) {
                    this.lastX = x - mouseX;
                    this.lastY = y - mouseY;
                    this.dragging = true;
                }
            }
        }
    }

    private void draggingFix(int mouseX, int mouseY) {
        if (this.dragging) {
            this.x = mouseX + this.lastX;
            this.y = mouseY + this.lastY;
            if(!Mouse.isButtonDown(0)) this.dragging = false;
        }
    }
}
