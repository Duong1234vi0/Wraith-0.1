package ua.babka.ui.component;

import net.minecraft.client.util.math.MatrixStack;

public interface IComponent {
    default public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
    }

    default public void mouseClicked(double mouseX, double mouseY, int button) {
    }

    default public void mouseReleased(double mouseX, double mouseY, int button) {
    }

    default public void mouseScrolled(double mouseX, double mouseY, double delta) {
    }

    default public void keyPressed(int keyCode, int scanCode, int modifiers) {
    }
}

