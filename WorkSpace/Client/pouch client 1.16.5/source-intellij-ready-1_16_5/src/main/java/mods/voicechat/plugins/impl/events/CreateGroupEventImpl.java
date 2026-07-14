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
import mods.voicechat.api.events.CreateGroupEvent;
import mods.voicechat.plugins.impl.events.GroupEventImpl;

public class CreateGroupEventImpl
extends GroupEventImpl
implements CreateGroupEvent {
    public CreateGroupEventImpl(Group group, @Nullable VoicechatConnection connection) {
        super(group, connection);
    }
}

