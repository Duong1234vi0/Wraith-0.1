/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 */
package mods.voicechat.intercompatibility;

import com.mojang.brigadier.CommandDispatcher;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import lightning.product.A_4115_X;
import lightning.product.B_4088_l;
import lightning.product.Y_1740_V;
import lightning.product.MinecraftClient;
import lightning.product.y_2498_m;
import mods.voicechat.api.VoicechatPlugin;
import mods.voicechat.eventforge.FMLServerStartedEvent;
import mods.voicechat.eventforge.FMLServerStoppingEvent;
import mods.voicechat.eventforge.PlayerEvent;
import mods.voicechat.eventforge.RegisterCommandsEvent;
import mods.voicechat.events.ServerVoiceChatConnectedEvent;
import mods.voicechat.events.ServerVoiceChatDisconnectedEvent;
import mods.voicechat.events.VoiceChatCompatibilityCheckSucceededEvent;
import mods.voicechat.intercompatibility.CommonCompatibilityManager;
import mods.voicechat.net.ForgeNetManager;
import mods.voicechat.net.NetManager;
import mods.voicechat.permission.ForgePermissionManager;
import mods.voicechat.permission.PermissionManager;
import net.minecraft.server.G_564_y;

public class ForgeCommonCompatibilityManager
extends CommonCompatibilityManager {
    private final List<Consumer<G_564_y>> serverStartingEvents = new CopyOnWriteArrayList<Consumer<G_564_y>>();
    private final List<Consumer<G_564_y>> serverStoppingEvents = new CopyOnWriteArrayList<Consumer<G_564_y>>();
    private final List<Consumer<CommandDispatcher<y_2498_m>>> registerServerCommandsEvents = new CopyOnWriteArrayList<Consumer<CommandDispatcher<y_2498_m>>>();
    private final List<Consumer<B_4088_l>> playerLoggedInEvents = new CopyOnWriteArrayList<Consumer<B_4088_l>>();
    private final List<Consumer<B_4088_l>> playerLoggedOutEvents = new CopyOnWriteArrayList<Consumer<B_4088_l>>();
    private final List<Consumer<B_4088_l>> voicechatConnectEvents = new CopyOnWriteArrayList<Consumer<B_4088_l>>();
    private final List<Consumer<B_4088_l>> voicechatCompatibilityCheckSucceededEvents = new CopyOnWriteArrayList<Consumer<B_4088_l>>();
    private final List<Consumer<UUID>> voicechatDisconnectEvents = new CopyOnWriteArrayList<Consumer<UUID>>();
    private ForgeNetManager netManager;

    @Y_1740_V
    public void serverStarting(FMLServerStartedEvent event) {
        this.serverStartingEvents.forEach(consumer -> consumer.accept(event.getServer()));
    }

    @Y_1740_V
    public void serverStopping(FMLServerStoppingEvent event) {
        this.serverStoppingEvents.forEach(consumer -> consumer.accept(event.getServer()));
    }

    @Y_1740_V
    public void onRegisterCommands(RegisterCommandsEvent event) {
        this.registerServerCommandsEvents.forEach(consumer -> consumer.accept(event.getDispatcher()));
    }

    @Y_1740_V
    public void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getPlayer() instanceof B_4088_l) {
            B_4088_l player = (B_4088_l)event.getPlayer();
            this.playerLoggedInEvents.forEach(consumer -> consumer.accept(player));
        }
    }

    @Y_1740_V
    public void playerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getPlayer() instanceof B_4088_l) {
            B_4088_l player = (B_4088_l)event.getPlayer();
            this.playerLoggedOutEvents.forEach(consumer -> consumer.accept(player));
        }
    }

    @Override
    public String getModVersion() {
        return "";
    }

    @Override
    public String getModName() {
        return "VoiceMode";
    }

    @Override
    public Path getGameDirectory() {
        return MinecraftClient.A_4115_X().M_182_A.toPath();
    }

    @Override
    public void emitServerVoiceChatConnectedEvent(B_4088_l player) {
        this.voicechatConnectEvents.forEach(consumer -> consumer.accept(player));
        A_4115_X.n_1700_B(new ServerVoiceChatConnectedEvent(player));
    }

    @Override
    public void emitServerVoiceChatDisconnectedEvent(UUID clientID) {
        this.voicechatDisconnectEvents.forEach(consumer -> consumer.accept(clientID));
        A_4115_X.n_1700_B(new ServerVoiceChatDisconnectedEvent(clientID));
    }

    @Override
    public void emitPlayerCompatibilityCheckSucceeded(B_4088_l player) {
        this.voicechatCompatibilityCheckSucceededEvents.forEach(consumer -> consumer.accept(player));
        A_4115_X.n_1700_B(new VoiceChatCompatibilityCheckSucceededEvent(player));
    }

    @Override
    public void onServerVoiceChatConnected(Consumer<B_4088_l> onVoiceChatConnected) {
        this.voicechatConnectEvents.add(onVoiceChatConnected);
    }

    @Override
    public void onServerVoiceChatDisconnected(Consumer<UUID> onVoiceChatDisconnected) {
        this.voicechatDisconnectEvents.add(onVoiceChatDisconnected);
    }

    @Override
    public void onServerStarting(Consumer<G_564_y> onServerStarting) {
        this.serverStartingEvents.add(onServerStarting);
    }

    @Override
    public void onServerStopping(Consumer<G_564_y> onServerStopping) {
        this.serverStoppingEvents.add(onServerStopping);
    }

    @Override
    public void onPlayerLoggedIn(Consumer<B_4088_l> onPlayerLoggedIn) {
        this.playerLoggedInEvents.add(onPlayerLoggedIn);
    }

    @Override
    public void onPlayerLoggedOut(Consumer<B_4088_l> onPlayerLoggedOut) {
        this.playerLoggedOutEvents.add(onPlayerLoggedOut);
    }

    @Override
    public void onPlayerCompatibilityCheckSucceeded(Consumer<B_4088_l> onPlayerCompatibilityCheckSucceeded) {
        this.voicechatCompatibilityCheckSucceededEvents.add(onPlayerCompatibilityCheckSucceeded);
    }

    @Override
    public void onRegisterServerCommands(Consumer<CommandDispatcher<y_2498_m>> onRegisterServerCommands) {
        this.registerServerCommandsEvents.add(onRegisterServerCommands);
    }

    @Override
    public NetManager getNetManager() {
        if (this.netManager == null) {
            this.netManager = new ForgeNetManager();
        }
        return this.netManager;
    }

    @Override
    public boolean isDevEnvironment() {
        return false;
    }

    @Override
    public boolean isDedicatedServer() {
        return false;
    }

    @Override
    public boolean isModLoaded(String modId) {
        return true;
    }

    @Override
    public List<VoicechatPlugin> loadPlugins() {
        ArrayList<VoicechatPlugin> plugins = new ArrayList<VoicechatPlugin>();
        return plugins;
    }

    @Override
    public PermissionManager createPermissionManager() {
        return new ForgePermissionManager();
    }
}


