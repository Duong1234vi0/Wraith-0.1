/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.gui.volume;

import lightning.product.F_2904_S;
import lightning.product.U_2871_b;
import lightning.product.x_282_a;
import mods.voicechat.gui.widgets.DebouncedSlider;

public class AdjustVolumeSlider
extends DebouncedSlider {
    protected static final x_282_a MUTED = new F_2904_S("message.voicechat.muted");
    protected static final float MAXIMUM = 4.0f;
    protected final VolumeConfigEntry volumeConfigEntry;

    public AdjustVolumeSlider(int xIn, int yIn, int widthIn, int heightIn, VolumeConfigEntry volumeConfigEntry) {
        super(xIn, yIn, widthIn, heightIn, new U_2871_b(""), volumeConfigEntry.get() / 4.0);
        this.volumeConfigEntry = volumeConfigEntry;
        this.func_230979_b_();
    }

    @Override
    protected void func_230979_b_() {
        if (this.sliderValue <= 0.0) {
            this.setMessage(MUTED);
            return;
        }
        long amp = Math.round(this.sliderValue * 4.0 * 100.0 - 100.0);
        this.setMessage(new F_2904_S("message.voicechat.volume_amplification", ((float)amp > 0.0f ? "+" : "") + amp + "%"));
    }

    @Override
    public void applyDebounced() {
        this.volumeConfigEntry.save(this.sliderValue * 4.0);
    }

    public static interface VolumeConfigEntry {
        public void save(double var1);

        public double get();
    }
}

