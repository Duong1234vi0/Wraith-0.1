/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.gui;

import lightning.product.U_2871_b;
import lightning.product.Button;

public class GuiButtonOF
extends Button {
    public final int id;

    public GuiButtonOF(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, Button.n_1700_B pressable) {
        super(x, y, widthIn, heightIn, new U_2871_b(buttonText), pressable);
        this.id = buttonId;
    }

    public GuiButtonOF(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
        this(buttonId, x, y, widthIn, heightIn, buttonText, (Button btn) -> {});
    }

    public GuiButtonOF(int buttonId, int x, int y, String buttonText) {
        this(buttonId, x, y, 200, 20, buttonText, (Button btn) -> {});
    }

    public void setMessage(String messageIn) {
        super.setMessage(new U_2871_b(messageIn));
    }
}


