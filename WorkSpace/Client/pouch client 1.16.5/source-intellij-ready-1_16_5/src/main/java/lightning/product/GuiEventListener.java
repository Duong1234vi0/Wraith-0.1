/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public interface GuiEventListener {
    default public void n_1700_B(double xPos, double mouseY) {
    }

    default public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false;
    }

    default public boolean mouseReleased(double mouseX, double mouseY, int button) {
        return false;
    }

    default public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        return false;
    }

    default public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        return false;
    }

    default public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return false;
    }

    default public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        return false;
    }

    default public boolean charTyped(char codePoint, int modifiers) {
        return false;
    }

    default public boolean changeFocus(boolean focus) {
        return false;
    }

    default public boolean isMouseOver(double mouseX, double mouseY) {
        return false;
    }
}


