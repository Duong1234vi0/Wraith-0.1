/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api.events;

public interface Event {
    public boolean isCancellable();

    public boolean cancel();

    public boolean isCancelled();
}

