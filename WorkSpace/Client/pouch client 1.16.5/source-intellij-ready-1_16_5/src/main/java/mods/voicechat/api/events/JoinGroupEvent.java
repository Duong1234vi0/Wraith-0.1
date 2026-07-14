/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api.events;

import mods.voicechat.api.Group;
import mods.voicechat.api.VoicechatConnection;
import mods.voicechat.api.events.GroupEvent;

public interface JoinGroupEvent
extends GroupEvent {
    @Override
    public Group getGroup();

    @Override
    public VoicechatConnection getConnection();
}

