/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api.events;

import mods.voicechat.api.events.ClientEvent;

public interface ClientSoundEvent
extends ClientEvent {
    public short[] getRawAudio();

    public void setRawAudio(short[] var1);

    public boolean isWhispering();
}

