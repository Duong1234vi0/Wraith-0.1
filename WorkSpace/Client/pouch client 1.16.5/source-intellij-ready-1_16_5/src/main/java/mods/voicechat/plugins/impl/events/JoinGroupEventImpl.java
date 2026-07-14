/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl.events;

import mods.voicechat.api.Group;
import mods.voicechat.api.VoicechatConnection;
import mods.voicechat.api.events.JoinGroupEvent;
import mods.voicechat.plugins.impl.events.GroupEventImpl;

public class JoinGroupEventImpl
extends GroupEventImpl
implements JoinGroupEvent {
    public JoinGroupEventImpl(Group group, VoicechatConnection connection) {
        super(group, connection);
    }
}

