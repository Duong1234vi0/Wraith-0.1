/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl.audiochannel;

import java.util.UUID;
import mods.voicechat.api.audiochannel.ClientStaticAudioChannel;
import mods.voicechat.plugins.impl.audiochannel.ClientAudioChannelImpl;
import mods.voicechat.voice.common.GroupSoundPacket;
import mods.voicechat.voice.common.SoundPacket;

public class ClientStaticAudioChannelImpl
extends ClientAudioChannelImpl
implements ClientStaticAudioChannel {
    public ClientStaticAudioChannelImpl(UUID id) {
        super(id);
    }

    @Override
    protected SoundPacket<?> createSoundPacket(short[] rawAudio) {
        return new GroupSoundPacket(this.id, this.id, rawAudio, this.category);
    }
}

