/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.gui.widgets;

import lightning.product.F_2904_S;
import lightning.product.x_282_a;
import mods.voicechat.VoicechatClient;
import mods.voicechat.gui.widgets.BooleanConfigButton;
import mods.voicechat.voice.client.Denoiser;

public class DenoiserButton
extends BooleanConfigButton {
    private static final x_282_a ENABLED = new F_2904_S("message.voicechat.denoiser.on");
    private static final x_282_a DISABLED = new F_2904_S("message.voicechat.denoiser.off");

    public DenoiserButton(int x, int y, int width, int height) {
        super(x, y, width, height, VoicechatClient.CLIENT_CONFIG.denoiser, enabled -> new F_2904_S("message.voicechat.denoiser", enabled != false ? ENABLED : DISABLED));
        if (Denoiser.createDenoiser() == null) {
            this.active = false;
        }
    }
}

