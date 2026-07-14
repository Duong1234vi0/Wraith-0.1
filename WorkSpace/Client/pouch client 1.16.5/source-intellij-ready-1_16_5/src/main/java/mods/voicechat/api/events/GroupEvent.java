/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.api.events;

import javax.annotation.Nullable;
import mods.voicechat.api.Group;
import mods.voicechat.api.VoicechatConnection;
import mods.voicechat.api.events.ServerEvent;

public interface GroupEvent
extends ServerEvent {
    @Nullable
    public Group getGroup();

    @Nullable
    public VoicechatConnection getConnection();
}

