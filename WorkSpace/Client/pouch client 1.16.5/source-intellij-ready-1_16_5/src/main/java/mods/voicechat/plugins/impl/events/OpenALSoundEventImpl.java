/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins.impl.events;

import java.util.UUID;
import javax.annotation.Nullable;
import mods.voicechat.api.Position;
import mods.voicechat.api.events.OpenALSoundEvent;
import mods.voicechat.plugins.impl.PositionImpl;
import mods.voicechat.plugins.impl.events.ClientEventImpl;

public class OpenALSoundEventImpl
extends ClientEventImpl
implements OpenALSoundEvent,
OpenALSoundEvent.Post,
OpenALSoundEvent.Pre {
    @Nullable
    protected PositionImpl position;
    @Nullable
    protected UUID channelId;
    @Nullable
    protected String category;
    protected int source;

    public OpenALSoundEventImpl(@Nullable UUID channelId, @Nullable PositionImpl position, @Nullable String category, int source) {
        this.channelId = channelId;
        this.position = position;
        this.category = category;
        this.source = source;
    }

    @Override
    @Nullable
    public Position getPosition() {
        return this.position;
    }

    @Override
    @Nullable
    public UUID getChannelId() {
        return this.channelId;
    }

    @Override
    public int getSource() {
        return this.source;
    }

    @Override
    public String getCategory() {
        return this.category;
    }

    @Override
    public boolean isCancellable() {
        return false;
    }
}

