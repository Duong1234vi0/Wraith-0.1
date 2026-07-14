/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins.impl.events;

import javax.annotation.Nullable;
import mods.voicechat.api.Group;
import mods.voicechat.api.VoicechatConnection;
import mods.voicechat.api.events.LeaveGroupEvent;
import mods.voicechat.plugins.impl.events.GroupEventImpl;

public class LeaveGroupEventImpl
extends GroupEventImpl
implements LeaveGroupEvent {
    public LeaveGroupEventImpl(@Nullable Group group, VoicechatConnection connection) {
        super(group, connection);
    }
}

