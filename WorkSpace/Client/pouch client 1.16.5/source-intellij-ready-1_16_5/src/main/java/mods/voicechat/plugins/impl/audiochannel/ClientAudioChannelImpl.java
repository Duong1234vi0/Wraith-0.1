/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins.impl.audiochannel;

import java.util.UUID;
import javax.annotation.Nullable;
import mods.voicechat.api.audiochannel.ClientAudioChannel;
import mods.voicechat.voice.client.ClientManager;
import mods.voicechat.voice.client.ClientVoicechat;
import mods.voicechat.voice.common.SoundPacket;

public abstract class ClientAudioChannelImpl
implements ClientAudioChannel {
    protected UUID id;
    @Nullable
    protected String category;

    public ClientAudioChannelImpl(UUID id) {
        this.id = id;
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    protected abstract SoundPacket<?> createSoundPacket(short[] var1);

    @Override
    public void play(short[] rawAudio) {
        ClientVoicechat client = ClientManager.getClient();
        if (client != null) {
            client.processSoundPacket(this.createSoundPacket(rawAudio));
        }
    }

    @Override
    @Nullable
    public String getCategory() {
        return this.category;
    }

    @Override
    public void setCategory(@Nullable String category) {
        this.category = category;
    }
}

