package dev.phastixtv.mizu.util.render;

public class HoverUtil {

    public static boolean rectHovered(double x, double y, double width, double height, double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY <= y + height && mouseY >= y;
    }
}
