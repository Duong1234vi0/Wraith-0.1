/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.api.events;

import javax.annotation.Nullable;
import mods.voicechat.api.ClientVoicechatSocket;
import mods.voicechat.api.events.ClientEvent;

public interface ClientVoicechatInitializationEvent
extends ClientEvent {
    public void setSocketImplementation(ClientVoicechatSocket var1);

    @Nullable
    public ClientVoicechatSocket getSocketImplementation();
}

