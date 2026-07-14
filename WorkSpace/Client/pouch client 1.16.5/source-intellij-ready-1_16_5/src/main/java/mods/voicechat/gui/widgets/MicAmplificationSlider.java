/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.gui.widgets;

import lightning.product.F_2904_S;
import lightning.product.U_2871_b;
import mods.voicechat.VoicechatClient;
import mods.voicechat.gui.widgets.DebouncedSlider;

public class MicAmplificationSlider
extends DebouncedSlider {
    public MicAmplificationSlider(int xIn, int yIn, int widthIn, int heightIn) {
        super(xIn, yIn, widthIn, heightIn, new U_2871_b(""), ((Double)VoicechatClient.CLIENT_CONFIG.microphoneGain.get() - -40.0) / 64.0);
        this.updateMessage();
    }

    protected void updateMessage() {
        double gainDb = this.sliderValue * 64.0 + -40.0;
        long amp = Math.round(gainDb);
        this.setMessage(new F_2904_S("message.voicechat.microphone_amplification", ((float)amp > 0.0f ? "+" : "") + amp + " dB"));
    }

    @Override
    public void applyDebounced() {
        double gainDb = this.sliderValue * 64.0 + -40.0;
        VoicechatClient.CLIENT_CONFIG.microphoneGain.set((Object)gainDb).save();
    }

    @Override
    protected void func_230979_b_() {
    }

    @Override
    protected void func_230972_a_() {
    }
}

