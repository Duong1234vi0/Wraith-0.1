/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.voice.client;

import lightning.product.F_2904_S;
import lightning.product.x_282_a;

public enum MicrophoneActivationType {
    PTT(new F_2904_S("message.voicechat.activation_type.ptt")),
    VOICE(new F_2904_S("message.voicechat.activation_type.voice"));

    private final x_282_a component;

    private MicrophoneActivationType(x_282_a component) {
        this.component = component;
    }

    public x_282_a getText() {
        return this.component;
    }
}

