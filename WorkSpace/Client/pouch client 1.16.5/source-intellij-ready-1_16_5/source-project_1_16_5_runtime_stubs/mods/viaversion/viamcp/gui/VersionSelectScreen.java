package mods.viaversion.viamcp.gui;

import lightning.product.O_694_j;
import lightning.product.Y_4083_F;
import lightning.product.g_221_o;
import lightning.product.x_282_a;

public class VersionSelectScreen extends O_694_j {
    private int version = 754;

    public VersionSelectScreen(Y_4083_F font, int x, int y, int width, int height, x_282_a title) {
        super(font, x, y, width, height, title);
        this.setText("1.16.5");
        this.setMaxStringLength(32);
    }

    public void setVersion(int protocol) {
        this.version = protocol;
        if (protocol == 754) {
            this.setText("1.16.5");
        } else {
            this.setText(String.valueOf(protocol));
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}
