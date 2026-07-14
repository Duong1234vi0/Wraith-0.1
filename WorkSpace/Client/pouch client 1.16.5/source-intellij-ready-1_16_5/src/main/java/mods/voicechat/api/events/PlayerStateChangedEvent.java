/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.api.events;

import java.util.UUID;
import javax.annotation.Nullable;
import mods.voicechat.api.VoicechatConnection;
import mods.voicechat.api.events.ServerEvent;

public interface PlayerStateChangedEvent
extends ServerEvent {
    public boolean isDisabled();

    public boolean isDisconnected();

    public UUID getPlayerUuid();

    @Nullable
    public VoicechatConnection getConnection();
}

