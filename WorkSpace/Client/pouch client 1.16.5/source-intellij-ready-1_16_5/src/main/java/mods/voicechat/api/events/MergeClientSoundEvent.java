/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api.events;

import mods.voicechat.api.events.ClientEvent;

public interface MergeClientSoundEvent
extends ClientEvent {
    public void mergeAudio(short[] var1);
}

