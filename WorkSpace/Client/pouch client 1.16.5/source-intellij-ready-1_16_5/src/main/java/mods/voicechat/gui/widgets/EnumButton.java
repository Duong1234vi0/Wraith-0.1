/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.maxhenkel.configbuilder.entry.ConfigEntry
 */
package mods.voicechat.gui.widgets;

import de.maxhenkel.configbuilder.entry.ConfigEntry;
import lightning.product.AbstractButton;
import lightning.product.U_2871_b;
import lightning.product.x_282_a;

public abstract class EnumButton<T extends Enum<T>>
extends AbstractButton {
    protected ConfigEntry<T> entry;

    public EnumButton(int xIn, int yIn, int widthIn, int heightIn, ConfigEntry<T> entry) {
        super(xIn, yIn, widthIn, heightIn, new U_2871_b(""));
        this.entry = entry;
        this.updateText();
    }

    protected void updateText() {
        this.setMessage(this.getText((Enum)this.entry.get()));
    }

    protected abstract x_282_a getText(T var1);

    protected void onUpdate(T type) {
    }

    @Override
    public void onPress() {
        Enum e = (Enum)this.entry.get();
        Enum[] values = (Enum[])e.getClass().getEnumConstants();
        Enum type = values[(e.ordinal() + 1) % values.length];
        this.entry.set((Object)type).save();
        this.updateText();
        this.onUpdate(type);
    }
}


