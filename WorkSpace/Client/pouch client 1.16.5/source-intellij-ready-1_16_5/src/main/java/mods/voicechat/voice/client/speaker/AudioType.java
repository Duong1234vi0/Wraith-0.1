/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.voice.client.speaker;

import lightning.product.F_2904_S;
import lightning.product.x_282_a;

public enum AudioType {
    NORMAL(new F_2904_S("message.voicechat.audio_type.normal")),
    REDUCED(new F_2904_S("message.voicechat.audio_type.reduced")),
    OFF(new F_2904_S("message.voicechat.audio_type.off"));

    private final x_282_a component;

    private AudioType(x_282_a component) {
        this.component = component;
    }

    public x_282_a getText() {
        return this.component;
    }
}

