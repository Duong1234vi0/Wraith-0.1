/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api;

import mods.voicechat.api.VoicechatApi;
import mods.voicechat.api.events.EventRegistration;

public interface VoicechatPlugin {
    public String getPluginId();

    default public void initialize(VoicechatApi api) {
    }

    default public void registerEvents(EventRegistration registration) {
    }
}

