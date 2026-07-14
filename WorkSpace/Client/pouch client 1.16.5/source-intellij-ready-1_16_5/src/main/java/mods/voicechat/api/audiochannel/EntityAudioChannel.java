/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api.audiochannel;

import mods.voicechat.api.Entity;
import mods.voicechat.api.audiochannel.AudioChannel;

public interface EntityAudioChannel
extends AudioChannel {
    public boolean isWhispering();

    public void setWhispering(boolean var1);

    public void updateEntity(Entity var1);

    public Entity getEntity();

    public float getDistance();

    public void setDistance(float var1);
}

