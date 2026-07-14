package ua.babka.render;

public class HoverUtil {
    public static boolean isHovered(double mouseX, double mouseY, double x2, double y2, double width, double height) {
        return mouseX >= x2 && mouseX <= x2 + width && mouseY >= y2 && mouseY <= y2 + height;
    }
}

