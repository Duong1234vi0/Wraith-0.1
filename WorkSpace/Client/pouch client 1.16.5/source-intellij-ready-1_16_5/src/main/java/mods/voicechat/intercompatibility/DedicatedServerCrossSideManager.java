/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.sun.jna.Platform
 */
package mods.voicechat.intercompatibility;

import com.sun.jna.Platform;
import mods.voicechat.Voicechat;
import mods.voicechat.intercompatibility.CrossSideManager;
import mods.voicechat.macos.VersionCheck;
import net.minecraft.server.G_564_y;

public class DedicatedServerCrossSideManager
extends CrossSideManager {
    @Override
    public int getMtuSize() {
        return (Integer)Voicechat.SERVER_CONFIG.voiceChatMtuSize.get();
    }

    @Override
    public boolean useNatives() {
        if (Platform.isMac() && !VersionCheck.isMacOSNativeCompatible()) {
            return false;
        }
        return (Boolean)Voicechat.SERVER_CONFIG.useNatives.get();
    }

    @Override
    public boolean shouldRunVoiceChatServer(G_564_y server) {
        return true;
    }
}

