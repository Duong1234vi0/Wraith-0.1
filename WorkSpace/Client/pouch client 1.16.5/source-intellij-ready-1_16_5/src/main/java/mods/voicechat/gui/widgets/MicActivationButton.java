/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.gui.widgets;

import java.util.function.Consumer;
import lightning.product.F_2904_S;
import lightning.product.x_282_a;
import mods.voicechat.VoicechatClient;
import mods.voicechat.gui.widgets.EnumButton;
import mods.voicechat.voice.client.MicrophoneActivationType;

public class MicActivationButton
extends EnumButton<MicrophoneActivationType> {
    protected Consumer<MicrophoneActivationType> onChange;

    public MicActivationButton(int xIn, int yIn, int widthIn, int heightIn, Consumer<MicrophoneActivationType> onChange) {
        super(xIn, yIn, widthIn, heightIn, VoicechatClient.CLIENT_CONFIG.microphoneActivationType);
        this.onChange = onChange;
        this.updateText();
        onChange.accept((MicrophoneActivationType)((Object)this.entry.get()));
    }

    @Override
    protected x_282_a getText(MicrophoneActivationType type) {
        return new F_2904_S("message.voicechat.activation_type", type.getText());
    }

    @Override
    protected void onUpdate(MicrophoneActivationType type) {
        this.onChange.accept(type);
    }
}

