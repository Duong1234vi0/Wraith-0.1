/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.gui.widgets;

import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.D_590_W;
import lightning.product.MutableComponent;
import lightning.product.AbstractButton;
import lightning.product.Q_4113_P;
import lightning.product.U_2871_b;
import lightning.product.MinecraftClient;
import lightning.product.x_282_a;

public class KeybindButton
extends AbstractButton {
    private static final MinecraftClient mc = MinecraftClient.A_4115_X();
    protected D_590_W keyMapping;
    @Nullable
    protected x_282_a description;
    protected boolean listening;

    public KeybindButton(D_590_W mapping, int x, int y, int width, int height, @Nullable x_282_a description) {
        super(x, y, width, height, U_2871_b.R_4764_Y);
        this.keyMapping = mapping;
        this.description = description;
        this.updateText();
    }

    public KeybindButton(D_590_W mapping, int x, int y, int width, int height) {
        this(mapping, x, y, width, height, null);
    }

    protected void updateText() {
        MutableComponent text = this.listening ? new U_2871_b("> ").n_1700_B(KeybindButton.getText(this.keyMapping).P_1922_E().n_1700_B(D_4024_W.M_182_A, D_4024_W.Y_601_j)).n_1700_B(" <").n_1700_B(D_4024_W.Q_4569_t) : KeybindButton.getText(this.keyMapping).P_1922_E();
        if (this.description != null) {
            text = this.description.P_1922_E().n_1700_B(": ").n_1700_B(text);
        }
        this.setMessage(text);
    }

    private static x_282_a getText(D_590_W keyMapping) {
        return keyMapping.s_956_w();
    }

    @Override
    public boolean isHovered() {
        return this.isHovered;
    }

    @Override
    public void onPress() {
        this.listening = true;
        this.updateText();
    }

    @Override
    public boolean mouseClicked(double x, double y, int button) {
        if (this.listening) {
            KeybindButton.mc.P_4830_p.n_1700_B(this.keyMapping, Q_4113_P.J_1907_R.R_4764_Y.n_1700_B(button));
            this.listening = false;
            this.updateText();
            return true;
        }
        return super.mouseClicked(x, y, button);
    }

    @Override
    public boolean keyPressed(int key, int scanCode, int modifiers) {
        if (this.listening) {
            if (key == 256) {
                KeybindButton.mc.P_4830_p.n_1700_B(this.keyMapping, Q_4113_P.n_1700_B);
            } else {
                KeybindButton.mc.P_4830_p.n_1700_B(this.keyMapping, Q_4113_P.n_1700_B(key, scanCode));
            }
            this.listening = false;
            this.updateText();
            return true;
        }
        return super.keyPressed(key, scanCode, modifiers);
    }

    @Override
    public boolean keyReleased(int key, int scanCode, int modifiers) {
        if (this.listening && key == 256) {
            return true;
        }
        return super.keyReleased(key, scanCode, modifiers);
    }

    public boolean isListening() {
        return this.listening;
    }
}



