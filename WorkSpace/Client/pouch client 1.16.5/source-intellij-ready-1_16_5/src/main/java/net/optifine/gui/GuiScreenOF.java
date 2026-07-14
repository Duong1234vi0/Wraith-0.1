/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.gui;

import java.util.List;
import lightning.product.M_2935_g;
import lightning.product.V_2511_L;
import lightning.product.Y_4083_F;
import lightning.product.MinecraftClient;
import lightning.product.FormattedCharSequence;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;
import net.optifine.gui.IOptionControl;
import net.optifine.util.GuiUtils;

public class GuiScreenOF
extends k_2603_m {
    protected List<V_2511_L> buttonList;
    protected Y_4083_F fontRenderer;
    protected boolean mousePressed;

    public GuiScreenOF(x_282_a title) {
        super(title);
        this.buttonList = this.buttons;
        this.fontRenderer = MinecraftClient.A_4115_X().t_148_a;
        this.mousePressed = false;
    }

    protected void actionPerformed(V_2511_L button) {
    }

    protected void actionPerformedRightClick(V_2511_L button) {
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        boolean flag = super.mouseClicked(mouseX, mouseY, mouseButton);
        this.mousePressed = true;
        V_2511_L widget = GuiScreenOF.getSelectedButton((int)mouseX, (int)mouseY, this.buttonList);
        if (widget != null && widget.active) {
            IOptionControl ioptioncontrol;
            if (mouseButton == 1 && widget instanceof IOptionControl && (ioptioncontrol = (IOptionControl)((Object)widget)).getControlOption() == M_2935_g.GUI_SCALE) {
                widget.playDownSound(this.minecraft.Z_976_R());
            }
            if (mouseButton == 0) {
                this.actionPerformed(widget);
            } else if (mouseButton == 1) {
                this.actionPerformedRightClick(widget);
            }
            return true;
        }
        return flag;
    }

    @Override
    public boolean mouseReleased(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_) {
        if (!this.mousePressed) {
            return false;
        }
        this.mousePressed = false;
        this.setDragging(false);
        return this.getListener() != null && this.getListener().mouseReleased(p_mouseReleased_1_, p_mouseReleased_3_, p_mouseReleased_5_) ? true : super.mouseReleased(p_mouseReleased_1_, p_mouseReleased_3_, p_mouseReleased_5_);
    }

    @Override
    public boolean mouseDragged(double p_mouseDragged_1_, double p_mouseDragged_3_, int p_mouseDragged_5_, double p_mouseDragged_6_, double p_mouseDragged_8_) {
        return !this.mousePressed ? false : super.mouseDragged(p_mouseDragged_1_, p_mouseDragged_3_, p_mouseDragged_5_, p_mouseDragged_6_, p_mouseDragged_8_);
    }

    public static V_2511_L getSelectedButton(int x, int y, List<V_2511_L> listButtons) {
        for (int i = 0; i < listButtons.size(); ++i) {
            V_2511_L widget = listButtons.get(i);
            if (!widget.visible) continue;
            int j = GuiUtils.getWidth(widget);
            int k = GuiUtils.getHeight(widget);
            if (x < widget.x || y < widget.y || x >= widget.x + j || y >= widget.y + k) continue;
            return widget;
        }
        return null;
    }

    public static void drawCenteredString(g_221_o matrixStackIn, Y_4083_F fontRendererIn, FormattedCharSequence textIn, int xIn, int yIn, int colorIn) {
        fontRendererIn.n_1700_B(matrixStackIn, textIn, (float)(xIn - fontRendererIn.n_1700_B(textIn) / 2), (float)yIn, colorIn);
    }
}



