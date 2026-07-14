/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.api.events;

import javax.annotation.Nullable;
import mods.voicechat.api.VoicechatSocket;
import mods.voicechat.api.events.ServerEvent;

public interface VoicechatServerStartingEvent
extends ServerEvent {
    public void setSocketImplementation(VoicechatSocket var1);

    @Nullable
    public VoicechatSocket getSocketImplementation();
}

