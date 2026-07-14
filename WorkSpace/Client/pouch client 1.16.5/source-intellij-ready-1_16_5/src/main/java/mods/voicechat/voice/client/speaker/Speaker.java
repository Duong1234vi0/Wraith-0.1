/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.voice.client.speaker;

import javax.annotation.Nullable;
import lightning.product.e_2866_D;
import mods.voicechat.voice.client.speaker.SpeakerException;

public interface Speaker {
    public void open() throws SpeakerException;

    public void play(short[] var1, float var2, @Nullable e_2866_D var3, @Nullable String var4, float var5);

    default public void play(short[] data, float volume, @Nullable String category) {
        this.play(data, volume, null, category, 0.0f);
    }

    public void close();
}

