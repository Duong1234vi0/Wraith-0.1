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
import mods.voicechat.api.events.GroupEvent;
import mods.voicechat.plugins.impl.events.ServerEventImpl;

public class GroupEventImpl
extends ServerEventImpl
implements GroupEvent {
    @Nullable
    protected Group group;
    protected VoicechatConnection connection;

    public GroupEventImpl(@Nullable Group group, VoicechatConnection connection) {
        this.group = group;
        this.connection = connection;
    }

    @Override
    @Nullable
    public Group getGroup() {
        return this.group;
    }

    @Override
    public VoicechatConnection getConnection() {
        return this.connection;
    }
}

