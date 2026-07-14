/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.voice.client;

import mods.voicechat.voice.client.ClientManager;
import mods.voicechat.voice.client.ClientVoicechat;
import mods.voicechat.voice.client.ClientVoicechatConnection;

public class ClientUtils {
    public static float getDefaultDistanceClient() {
        ClientVoicechat client = ClientManager.getClient();
        if (client == null) {
            return 48.0f;
        }
        ClientVoicechatConnection connection = client.getConnection();
        if (connection == null) {
            return 48.0f;
        }
        return (float)connection.getData().getVoiceChatDistance();
    }
}

