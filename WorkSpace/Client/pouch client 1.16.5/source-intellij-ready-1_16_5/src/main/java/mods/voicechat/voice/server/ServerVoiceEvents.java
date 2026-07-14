/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.voice.server;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.U_2871_b;
import lightning.product.V_4604_M;
import lightning.product.j_3341_s;
import lightning.product.x_282_a;
import mods.voicechat.Voicechat;
import mods.voicechat.intercompatibility.CommonCompatibilityManager;
import mods.voicechat.intercompatibility.CrossSideManager;
import mods.voicechat.net.NetManager;
import mods.voicechat.net.SecretPacket;
import mods.voicechat.plugins.PluginManager;
import mods.voicechat.voice.common.Secret;
import mods.voicechat.voice.server.Server;
import net.minecraft.server.G_564_y;

public class ServerVoiceEvents {
    private final Map<UUID, Integer> clientCompatibilities = new ConcurrentHashMap<UUID, Integer>();
    private Server server;

    public ServerVoiceEvents() {
        CommonCompatibilityManager.INSTANCE.onServerStarting(this::serverStarting);
        CommonCompatibilityManager.INSTANCE.onPlayerLoggedIn(this::playerLoggedIn);
        CommonCompatibilityManager.INSTANCE.onPlayerLoggedOut(this::playerLoggedOut);
        CommonCompatibilityManager.INSTANCE.onServerStopping(this::serverStopping);
        CommonCompatibilityManager.INSTANCE.onServerVoiceChatConnected(this::serverVoiceChatConnected);
        CommonCompatibilityManager.INSTANCE.onServerVoiceChatDisconnected(this::serverVoiceChatDisconnected);
        CommonCompatibilityManager.INSTANCE.onPlayerCompatibilityCheckSucceeded(this::playerCompatibilityCheckSucceeded);
        CommonCompatibilityManager.INSTANCE.getNetManager().requestSecretChannel.setServerListener((server, player, handler, packet) -> {
            Voicechat.LOGGER.info("Received secret request of {} ({})", player.O_1309_Q().getString(), packet.getCompatibilityVersion());
            this.clientCompatibilities.put(player.w_2705_t(), packet.getCompatibilityVersion());
            if (packet.getCompatibilityVersion() != Voicechat.COMPATIBILITY_VERSION) {
                Voicechat.LOGGER.warn("Connected client {} has incompatible voice chat version (server={}, client={})", player.O_1309_Q().getString(), Voicechat.COMPATIBILITY_VERSION, packet.getCompatibilityVersion());
                player.n_1700_B(this.getIncompatibleMessage(packet.getCompatibilityVersion()), j_3341_s.J_1907_R);
            } else {
                this.initializePlayerConnection(player);
            }
        });
    }

    public x_282_a getIncompatibleMessage(int clientCompatibilityVersion) {
        if (clientCompatibilityVersion <= 6) {
            return new U_2871_b(String.format((String)Voicechat.TRANSLATIONS.voicechatNotCompatibleMessage.get(), CommonCompatibilityManager.INSTANCE.getModVersion(), CommonCompatibilityManager.INSTANCE.getModName()));
        }
        return new F_2904_S("message.voicechat.incompatible_version", new U_2871_b(CommonCompatibilityManager.INSTANCE.getModVersion()).n_1700_B(D_4024_W.multiplayerClientSuggestionProvider), new U_2871_b(CommonCompatibilityManager.INSTANCE.getModName()).n_1700_B(D_4024_W.multiplayerClientSuggestionProvider));
    }

    public boolean isCompatible(B_4088_l player) {
        return this.isCompatible(player.w_2705_t());
    }

    public boolean isCompatible(UUID playerUuid) {
        return this.clientCompatibilities.getOrDefault(playerUuid, -1) == Voicechat.COMPATIBILITY_VERSION;
    }

    public void serverStarting(G_564_y mcServer) {
        if (this.server != null) {
            this.server.close();
            this.server = null;
        }
        if (!CrossSideManager.get().shouldRunVoiceChatServer(mcServer)) {
            Voicechat.LOGGER.info("Disabling voice chat in singleplayer", new Object[0]);
            return;
        }
        if (mcServer instanceof V_4604_M && !mcServer.Z_976_R()) {
            Voicechat.LOGGER.warn("Running in offline mode - Voice chat encryption is not secure!", new Object[0]);
        }
        try {
            this.server = new Server(mcServer);
            this.server.start();
            PluginManager.instance().onServerStarted();
        }
        catch (Exception e) {
            Voicechat.LOGGER.error("Failed to start voice chat server", e);
        }
    }

    public void initializePlayerConnection(B_4088_l player) {
        if (this.server == null) {
            return;
        }
        CommonCompatibilityManager.INSTANCE.emitPlayerCompatibilityCheckSucceeded(player);
        Secret secret = this.server.generateNewSecret(player.w_2705_t());
        if (secret == null) {
            Voicechat.LOGGER.warn("Player already requested secret - ignoring", new Object[0]);
            return;
        }
        NetManager.sendToClient(player, new SecretPacket(player, secret, this.server.getPort(), Voicechat.SERVER_CONFIG));
        Voicechat.LOGGER.info("Sent secret to {}", player.O_1309_Q().getString());
    }

    public void playerLoggedIn(final B_4088_l serverPlayer) {
        if (this.server != null) {
            this.server.onPlayerLoggedIn(serverPlayer);
        }
        if (!((Boolean)Voicechat.SERVER_CONFIG.forceVoiceChat.get()).booleanValue()) {
            return;
        }
        final Timer timer = new Timer(serverPlayer.y_4642_Y().getName() + "%s-login-timer", true);
        timer.schedule(new TimerTask(){

            @Override
            public void run() {
                timer.cancel();
                timer.purge();
                if (!serverPlayer.J_1907_R.Q_2552_b()) {
                    return;
                }
                if (!serverPlayer.n_1700_B.n_1700_B.u_1723_Y()) {
                    return;
                }
                if (!ServerVoiceEvents.this.isCompatible(serverPlayer)) {
                    serverPlayer.J_1907_R.execute(() -> serverPlayer2.n_1700_B.n_1700_B(new U_2871_b(String.format((String)Voicechat.TRANSLATIONS.forceVoicechatKickMessage.get(), CommonCompatibilityManager.INSTANCE.getModName(), CommonCompatibilityManager.INSTANCE.getModVersion()))));
                }
            }
        }, ((Integer)Voicechat.SERVER_CONFIG.loginTimeout.get()).intValue());
    }

    public void playerLoggedOut(B_4088_l player) {
        this.clientCompatibilities.remove(player.w_2705_t());
        if (this.server == null) {
            return;
        }
        this.server.onPlayerLoggedOut(player);
        Voicechat.LOGGER.info("Disconnecting client {}", player.O_1309_Q().getString());
    }

    public void serverVoiceChatConnected(B_4088_l serverPlayer) {
        if (this.server == null) {
            return;
        }
        this.server.onPlayerVoicechatConnect(serverPlayer);
    }

    public void serverVoiceChatDisconnected(UUID uuid) {
        if (this.server == null) {
            return;
        }
        this.server.onPlayerVoicechatDisconnect(uuid);
    }

    public void playerCompatibilityCheckSucceeded(B_4088_l player) {
        if (this.server == null) {
            return;
        }
        this.server.onPlayerCompatibilityCheckSucceeded(player);
    }

    @Nullable
    public Server getServer() {
        return this.server;
    }

    public void serverStopping(G_564_y mcServer) {
        if (this.server != null) {
            this.server.close();
            this.server = null;
        }
    }
}


