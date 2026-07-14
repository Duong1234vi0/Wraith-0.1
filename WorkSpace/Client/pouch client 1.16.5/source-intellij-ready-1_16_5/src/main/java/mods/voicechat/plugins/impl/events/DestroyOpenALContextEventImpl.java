/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl.events;

import mods.voicechat.api.events.DestroyOpenALContextEvent;
import mods.voicechat.plugins.impl.events.ClientEventImpl;

public class DestroyOpenALContextEventImpl
extends ClientEventImpl
implements DestroyOpenALContextEvent {
    protected long context;
    protected long device;

    public DestroyOpenALContextEventImpl(long context, long device) {
        this.context = context;
        this.device = device;
    }

    @Override
    public long getContext() {
        return this.context;
    }

    @Override
    public long getDevice() {
        return this.device;
    }

    @Override
    public boolean isCancellable() {
        return false;
    }
}

