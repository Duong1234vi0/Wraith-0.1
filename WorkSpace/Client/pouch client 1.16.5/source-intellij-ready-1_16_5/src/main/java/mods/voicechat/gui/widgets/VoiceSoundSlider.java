/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.gui.widgets;

import lightning.product.F_2904_S;
import lightning.product.U_2871_b;
import lightning.product.x_282_a;
import mods.voicechat.VoicechatClient;
import mods.voicechat.gui.widgets.DebouncedSlider;

public class VoiceSoundSlider
extends DebouncedSlider {
    public VoiceSoundSlider(int x, int y, int width, int height) {
        super(x, y, width, height, new U_2871_b(""), ((Double)VoicechatClient.CLIENT_CONFIG.voiceChatVolume.get()).floatValue() / 2.0f);
        this.func_230979_b_();
    }

    @Override
    protected void func_230979_b_() {
        this.setMessage(this.getMsg());
    }

    public x_282_a getMsg() {
        return new F_2904_S("message.voicechat.voice_chat_volume", Math.round(this.sliderValue * 200.0) + "%");
    }

    @Override
    public void applyDebounced() {
        VoicechatClient.CLIENT_CONFIG.voiceChatVolume.set((Object)(this.sliderValue * 2.0)).save();
    }
}

