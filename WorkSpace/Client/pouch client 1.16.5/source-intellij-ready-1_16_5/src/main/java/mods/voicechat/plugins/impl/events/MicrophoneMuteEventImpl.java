/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl.events;

import mods.voicechat.api.events.MicrophoneMuteEvent;
import mods.voicechat.plugins.impl.events.ClientEventImpl;

public class MicrophoneMuteEventImpl
extends ClientEventImpl
implements MicrophoneMuteEvent {
    private final boolean muted;

    public MicrophoneMuteEventImpl(boolean muted) {
        this.muted = muted;
    }

    @Override
    public boolean isDisabled() {
        return this.muted;
    }
}

