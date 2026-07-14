/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.api.events;

import java.util.UUID;
import javax.annotation.Nullable;
import mods.voicechat.api.Position;
import mods.voicechat.api.events.ClientEvent;

public interface OpenALSoundEvent
extends ClientEvent {
    @Nullable
    public Position getPosition();

    @Nullable
    public UUID getChannelId();

    public int getSource();

    @Nullable
    public String getCategory();

    public static interface Post
    extends OpenALSoundEvent {
    }

    public static interface Pre
    extends OpenALSoundEvent {
    }
}

