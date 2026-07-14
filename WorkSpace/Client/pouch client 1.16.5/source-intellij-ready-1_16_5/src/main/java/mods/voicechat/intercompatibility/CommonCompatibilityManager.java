/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 */
package mods.voicechat.intercompatibility;

import com.mojang.brigadier.CommandDispatcher;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import lightning.product.B_4088_l;
import lightning.product.N_4263_v;
import lightning.product.a_3913_L;
import lightning.product.e_3591_l;
import lightning.product.y_2498_m;
import mods.voicechat.api.VoicechatPlugin;
import mods.voicechat.api.VoicechatServerApi;
import mods.voicechat.net.NetManager;
import mods.voicechat.permission.PermissionManager;
import mods.voicechat.plugins.impl.VoicechatServerApiImpl;
import mods.voicechat.service.Service;
import net.minecraft.server.G_564_y;

public abstract class CommonCompatibilityManager {
    public static CommonCompatibilityManager INSTANCE = Service.get(CommonCompatibilityManager.class);

    public abstract String getModVersion();

    public abstract String getModName();

    public abstract Path getGameDirectory();

    public abstract void emitServerVoiceChatConnectedEvent(B_4088_l var1);

    public abstract void emitServerVoiceChatDisconnectedEvent(UUID var1);

    public abstract void emitPlayerCompatibilityCheckSucceeded(B_4088_l var1);

    public abstract void onServerVoiceChatConnected(Consumer<B_4088_l> var1);

    public abstract void onServerVoiceChatDisconnected(Consumer<UUID> var1);

    public abstract void onServerStarting(Consumer<G_564_y> var1);

    public abstract void onServerStopping(Consumer<G_564_y> var1);

    public abstract void onPlayerLoggedIn(Consumer<B_4088_l> var1);

    public abstract void onPlayerLoggedOut(Consumer<B_4088_l> var1);

    public abstract void onPlayerCompatibilityCheckSucceeded(Consumer<B_4088_l> var1);

    public abstract void onRegisterServerCommands(Consumer<CommandDispatcher<y_2498_m>> var1);

    public abstract NetManager getNetManager();

    public abstract boolean isDevEnvironment();

    public abstract boolean isDedicatedServer();

    public abstract boolean isModLoaded(String var1);

    public abstract List<VoicechatPlugin> loadPlugins();

    public abstract PermissionManager createPermissionManager();

    public VoicechatServerApi getServerApi() {
        return VoicechatServerApiImpl.INSTANCE;
    }

    public Object createRawApiEntity(N_4263_v entity) {
        return entity;
    }

    public Object createRawApiPlayer(a_3913_L player) {
        return player;
    }

    public Object createRawApiLevel(e_3591_l level) {
        return level;
    }
}

