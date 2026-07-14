/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins.impl.audiochannel;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import mods.voicechat.api.ServerPlayer;
import mods.voicechat.api.audiochannel.AudioChannel;
import mods.voicechat.voice.server.Server;

public abstract class AudioChannelImpl
implements AudioChannel {
    protected UUID channelId;
    protected Server server;
    protected AtomicLong sequenceNumber;
    @Nullable
    protected Predicate<ServerPlayer> filter;
    @Nullable
    protected String category;

    public AudioChannelImpl(UUID channelId, Server server) {
        this.channelId = channelId;
        this.server = server;
        this.sequenceNumber = new AtomicLong();
    }

    @Override
    public void setFilter(Predicate<ServerPlayer> filter) {
        this.filter = filter;
    }

    @Override
    public UUID getId() {
        return this.channelId;
    }

    @Override
    public boolean isClosed() {
        return this.server.isClosed();
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

