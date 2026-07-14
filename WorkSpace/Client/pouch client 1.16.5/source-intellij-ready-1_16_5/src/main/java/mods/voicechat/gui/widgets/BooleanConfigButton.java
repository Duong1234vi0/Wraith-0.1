/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.maxhenkel.configbuilder.entry.ConfigEntry
 */
package mods.voicechat.gui.widgets;

import de.maxhenkel.configbuilder.entry.ConfigEntry;
import java.util.function.Function;
import lightning.product.AbstractButton;
import lightning.product.U_2871_b;
import lightning.product.x_282_a;

public class BooleanConfigButton
extends AbstractButton {
    protected ConfigEntry<Boolean> entry;
    protected Function<Boolean, x_282_a> component;

    public BooleanConfigButton(int x, int y, int width, int height, ConfigEntry<Boolean> entry, Function<Boolean, x_282_a> component) {
        super(x, y, width, height, new U_2871_b(""));
        this.entry = entry;
        this.component = component;
        this.updateText();
    }

    private void updateText() {
        this.setMessage(this.component.apply((Boolean)this.entry.get()));
    }

    @Override
    public void onPress() {
        this.entry.set((Object)((Boolean)this.entry.get() == false ? 1 : 0)).save();
        this.updateText();
    }
}


