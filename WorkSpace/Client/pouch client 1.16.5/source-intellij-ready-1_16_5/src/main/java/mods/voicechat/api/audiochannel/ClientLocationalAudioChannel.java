/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api.audiochannel;

import mods.voicechat.api.Position;
import mods.voicechat.api.audiochannel.ClientAudioChannel;

public interface ClientLocationalAudioChannel
extends ClientAudioChannel {
    public Position getLocation();

    public void setLocation(Position var1);

    public float getDistance();

    public void setDistance(float var1);
}

