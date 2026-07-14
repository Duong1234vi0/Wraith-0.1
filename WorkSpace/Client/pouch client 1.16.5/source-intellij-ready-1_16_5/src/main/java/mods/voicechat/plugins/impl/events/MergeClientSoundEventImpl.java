/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins.impl.events;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import mods.voicechat.api.events.MergeClientSoundEvent;
import mods.voicechat.plugins.impl.events.ClientEventImpl;

public class MergeClientSoundEventImpl
extends ClientEventImpl
implements MergeClientSoundEvent {
    @Nullable
    private List<short[]> audioToMerge;

    @Override
    public boolean isCancellable() {
        return false;
    }

    @Override
    public void mergeAudio(short[] audio) {
        if (this.audioToMerge == null) {
            this.audioToMerge = new ArrayList<short[]>();
        }
        this.audioToMerge.add(audio);
    }

    @Nullable
    public List<short[]> getAudioToMerge() {
        return this.audioToMerge;
    }
}

