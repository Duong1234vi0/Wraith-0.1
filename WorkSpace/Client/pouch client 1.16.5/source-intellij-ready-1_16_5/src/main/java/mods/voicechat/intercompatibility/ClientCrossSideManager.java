/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.sun.jna.Platform
 */
package mods.voicechat.intercompatibility;

import com.sun.jna.Platform;
import lightning.product.V_4604_M;
import mods.voicechat.Voicechat;
import mods.voicechat.VoicechatClient;
import mods.voicechat.intercompatibility.CrossSideManager;
import mods.voicechat.macos.VersionCheck;
import mods.voicechat.voice.client.ClientManager;
import mods.voicechat.voice.client.ClientVoicechat;
import mods.voicechat.voice.client.ClientVoicechatConnection;
import net.minecraft.server.G_564_y;

public class ClientCrossSideManager
extends CrossSideManager {
    @Override
    public int getMtuSize() {
        ClientVoicechatConnection connection;
        ClientVoicechat client = ClientManager.getClient();
        if (client != null && (connection = client.getConnection()) != null) {
            return connection.getData().getMtuSize();
        }
        return (Integer)Voicechat.SERVER_CONFIG.voiceChatMtuSize.get();
    }

    @Override
    public boolean useNatives() {
        if (Platform.isMac() && !VersionCheck.isMacOSNativeCompatible()) {
            return false;
        }
        if (VoicechatClient.CLIENT_CONFIG == null) {
            return (Boolean)Voicechat.SERVER_CONFIG.useNatives.get();
        }
        return (Boolean)VoicechatClient.CLIENT_CONFIG.useNatives.get();
    }

    @Override
    public boolean shouldRunVoiceChatServer(G_564_y server) {
        return server instanceof V_4604_M || VoicechatClient.CLIENT_CONFIG == null || (Boolean)VoicechatClient.CLIENT_CONFIG.runLocalServer.get() != false;
    }
}

