/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.voice.server;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.N_4263_v;
import lightning.product.V_4604_M;
import lightning.product.x_282_a;
import mods.voicechat.Voicechat;
import mods.voicechat.api.RawUdpPacket;
import mods.voicechat.api.VoicechatSocket;
import mods.voicechat.debug.CooldownTimer;
import mods.voicechat.debug.VoicechatUncaughtExceptionHandler;
import mods.voicechat.intercompatibility.CommonCompatibilityManager;
import mods.voicechat.permission.PermissionManager;
import mods.voicechat.plugins.PluginManager;
import mods.voicechat.voice.common.AuthenticateAckPacket;
import mods.voicechat.voice.common.AuthenticatePacket;
import mods.voicechat.voice.common.ConnectionCheckAckPacket;
import mods.voicechat.voice.common.ConnectionCheckPacket;
import mods.voicechat.voice.common.GroupSoundPacket;
import mods.voicechat.voice.common.KeepAlivePacket;
import mods.voicechat.voice.common.LocationSoundPacket;
import mods.voicechat.voice.common.MicPacket;
import mods.voicechat.voice.common.NetworkMessage;
import mods.voicechat.voice.common.Packet;
import mods.voicechat.voice.common.PingPacket;
import mods.voicechat.voice.common.PlayerSoundPacket;
import mods.voicechat.voice.common.PlayerState;
import mods.voicechat.voice.common.Secret;
import mods.voicechat.voice.common.SoundPacket;
import mods.voicechat.voice.common.Utils;
import mods.voicechat.voice.server.ClientConnection;
import mods.voicechat.voice.server.Group;
import mods.voicechat.voice.server.PingManager;
import mods.voicechat.voice.server.PlayerStateManager;
import mods.voicechat.voice.server.ServerCategoryManager;
import mods.voicechat.voice.server.ServerGroupManager;
import mods.voicechat.voice.server.ServerWorldUtils;
import net.minecraft.server.G_564_y;

public class Server
extends Thread {
    private final Map<UUID, ClientConnection> connections;
    private final Map<UUID, ClientConnection> unCheckedConnections;
    private final Map<UUID, Secret> secrets;
    private int port;
    private final G_564_y server;
    private VoicechatSocket socket;
    private final ProcessThread processThread;
    private final BlockingQueue<RawUdpPacket> packetQueue;
    private final PingManager pingManager;
    private final PlayerStateManager playerStateManager;
    private final ServerGroupManager groupManager;
    private final ServerCategoryManager categoryManager;

    public Server(G_564_y server) {
        if (server instanceof V_4604_M) {
            int configPort = (Integer)Voicechat.SERVER_CONFIG.voiceChatPort.get();
            if (configPort < 0) {
                Voicechat.LOGGER.info("Using the Minecraft servers port as voice chat port", new Object[0]);
                this.port = server.d_2461_k();
            } else {
                this.port = configPort;
            }
        } else {
            this.port = 0;
        }
        this.server = server;
        this.socket = PluginManager.instance().getSocketImplementation(server);
        this.connections = new ConcurrentHashMap<UUID, ClientConnection>();
        this.unCheckedConnections = new ConcurrentHashMap<UUID, ClientConnection>();
        this.secrets = new ConcurrentHashMap<UUID, Secret>();
        this.packetQueue = new LinkedBlockingQueue<RawUdpPacket>();
        this.pingManager = new PingManager(this);
        this.playerStateManager = new PlayerStateManager(this);
        this.groupManager = new ServerGroupManager(this);
        this.categoryManager = new ServerCategoryManager(this);
        this.setDaemon(true);
        this.setName("VoiceChatServerThread");
        this.setUncaughtExceptionHandler(new VoicechatUncaughtExceptionHandler());
        this.processThread = new ProcessThread();
        this.processThread.start();
    }

    public void onPlayerLoggedIn(B_4088_l player) {
        this.playerStateManager.onPlayerLoggedIn(player);
    }

    public void onPlayerLoggedOut(B_4088_l player) {
        this.disconnectClient(player.w_2705_t());
        this.playerStateManager.onPlayerLoggedOut(player);
        this.groupManager.onPlayerLoggedOut(player);
    }

    public void onPlayerVoicechatConnect(B_4088_l player) {
        this.playerStateManager.onPlayerVoicechatConnect(player);
    }

    public void onPlayerVoicechatDisconnect(UUID uuid) {
        this.playerStateManager.onPlayerVoicechatDisconnect(uuid);
    }

    public void onPlayerCompatibilityCheckSucceeded(B_4088_l player) {
        this.playerStateManager.onPlayerCompatibilityCheckSucceeded(player);
        this.groupManager.onPlayerCompatibilityCheckSucceeded(player);
        this.categoryManager.onPlayerCompatibilityCheckSucceeded(player);
    }

    @Override
    public void run() {
        try {
            String bindAddress = this.getBindAddress();
            try {
                InetAddress.getByName(bindAddress);
            }
            catch (UnknownHostException e) {
                Voicechat.LOGGER.error("Failed to parse bind IP address '{}'", bindAddress, e);
                Voicechat.LOGGER.info("Binding to wildcard IP address", new Object[0]);
                bindAddress = "";
            }
            this.socket.open(this.port, bindAddress);
            if (bindAddress.isEmpty()) {
                Voicechat.LOGGER.info("Voice chat server started at port {}", this.socket.getLocalPort());
            } else {
                Voicechat.LOGGER.info("Voice chat server started at {}:{}", bindAddress, this.socket.getLocalPort());
            }
            while (!this.socket.isClosed()) {
                try {
                    this.packetQueue.add(this.socket.read());
                }
                catch (Exception e) {
                    if (this.socket.isClosed() && e instanceof SocketException || !Voicechat.debugMode()) continue;
                    Voicechat.LOGGER.error("Failed to read from socket", e);
                }
            }
        }
        catch (Exception e) {
            Voicechat.LOGGER.error("Voice chat server error", e);
        }
    }

    private String getBindAddress() {
        String bindAddress = (String)Voicechat.SERVER_CONFIG.voiceChatBindAddress.get();
        if (bindAddress.trim().equals("*")) {
            bindAddress = "";
        } else if (bindAddress.trim().isEmpty() && this.server instanceof V_4604_M && !(bindAddress = ((V_4604_M)this.server).n_1700_B().R_4764_Y).trim().isEmpty()) {
            try {
                InetAddress address = InetAddress.getByName(bindAddress);
                if (address.isLoopbackAddress()) {
                    bindAddress = "";
                } else {
                    Voicechat.LOGGER.info("Using server-ip as bind address: {}", bindAddress);
                }
            }
            catch (Exception e) {
                Voicechat.LOGGER.warn("Invalid server-ip", e);
                bindAddress = "";
            }
        }
        return bindAddress;
    }

    public void changePort(int port) throws Exception {
        VoicechatSocket newSocket = PluginManager.instance().getSocketImplementation(this.server);
        newSocket.open(port, this.getBindAddress());
        VoicechatSocket old = this.socket;
        this.socket = newSocket;
        this.port = port;
        old.close();
        this.connections.clear();
        this.unCheckedConnections.clear();
        this.secrets.clear();
    }

    public Secret getSecret(UUID playerUUID) {
        if (this.hasSecret(playerUUID)) {
            return this.secrets.get(playerUUID);
        }
        Secret secret = Secret.generateNewRandomSecret();
        this.secrets.put(playerUUID, secret);
        return secret;
    }

    @Nullable
    public Secret generateNewSecret(UUID playerUUID) {
        if (this.hasSecret(playerUUID)) {
            return null;
        }
        return this.getSecret(playerUUID);
    }

    public boolean hasSecret(UUID playerUUID) {
        return this.secrets.containsKey(playerUUID);
    }

    public void disconnectClient(UUID playerUUID) {
        this.connections.remove(playerUUID);
        this.unCheckedConnections.remove(playerUUID);
        this.secrets.remove(playerUUID);
        PluginManager.instance().onPlayerDisconnected(playerUUID);
    }

    public void close() {
        this.socket.close();
        this.processThread.close();
        PluginManager.instance().onServerStopped();
    }

    public boolean isClosed() {
        return !this.processThread.running;
    }

    public void onMicPacket(UUID playerUuid, MicPacket packet) {
        B_4088_l player = this.server.p_178_J().n_1700_B(playerUuid);
        if (player == null) {
            return;
        }
        if (!PermissionManager.INSTANCE.SPEAK_PERMISSION.hasPermission(player)) {
            CooldownTimer.run("no-speak-" + String.valueOf(playerUuid), 30000L, () -> player.n_1700_B((x_282_a)new F_2904_S("message.voicechat.no_speak_permission"), true));
            return;
        }
        PlayerState state = this.playerStateManager.getState(player.w_2705_t());
        if (state == null) {
            return;
        }
        if (!PluginManager.instance().onMicPacket(player, state, packet)) {
            this.processMicPacket(player, state, packet);
        }
    }

    private void processMicPacket(B_4088_l player, PlayerState state, MicPacket packet) {
        if (state.hasGroup()) {
            Group group = this.groupManager.getGroup(state.getGroup());
            this.processGroupPacket(state, player, packet);
            if (group == null || group.isOpen()) {
                this.processProximityPacket(state, player, packet);
            }
            return;
        }
        this.processProximityPacket(state, player, packet);
    }

    private void processGroupPacket(PlayerState senderState, B_4088_l sender, MicPacket packet) {
        UUID groupId = senderState.getGroup();
        if (groupId == null) {
            return;
        }
        GroupSoundPacket groupSoundPacket = new GroupSoundPacket(senderState.getUuid(), senderState.getUuid(), packet.getData(), packet.getSequenceNumber(), null);
        for (PlayerState state : this.playerStateManager.getStates()) {
            B_4088_l p;
            if (!groupId.equals(state.getGroup()) || senderState.getUuid().equals(state.getUuid()) || (p = this.server.p_178_J().n_1700_B(state.getUuid())) == null) continue;
            ClientConnection connection = this.getConnection(state.getUuid());
            this.sendSoundPacket(sender, senderState, p, state, connection, groupSoundPacket, "group");
        }
    }

    private void processProximityPacket(PlayerState senderState, B_4088_l sender, MicPacket packet) {
        UUID groupId = senderState.getGroup();
        float distance = Utils.getDefaultDistanceServer();
        SoundPacket soundPacket = null;
        String source = null;
        if (sender.d_2461_k()) {
            B_4088_l spectatingPlayer;
            N_4263_v camera;
            if (((Boolean)Voicechat.SERVER_CONFIG.spectatorPlayerPossession.get()).booleanValue() && (camera = sender.T_2506_i()) instanceof B_4088_l && (spectatingPlayer = (B_4088_l)camera) != sender) {
                PlayerState receiverState = this.playerStateManager.getState(spectatingPlayer.w_2705_t());
                if (receiverState == null) {
                    return;
                }
                GroupSoundPacket groupSoundPacket = new GroupSoundPacket(senderState.getUuid(), senderState.getUuid(), packet.getData(), packet.getSequenceNumber(), null);
                ClientConnection connection = this.getConnection(receiverState.getUuid());
                this.sendSoundPacket(sender, senderState, spectatingPlayer, receiverState, connection, groupSoundPacket, "spectator");
                return;
            }
            if (((Boolean)Voicechat.SERVER_CONFIG.spectatorInteraction.get()).booleanValue()) {
                soundPacket = new LocationSoundPacket(sender.w_2705_t(), sender.w_2705_t(), sender.u_2550_I(1.0f), packet.getData(), packet.getSequenceNumber(), distance, null);
                source = "spectator";
            }
        }
        if (soundPacket == null) {
            float crouchMultiplayer = sender.Z_875_P() ? ((Double)Voicechat.SERVER_CONFIG.crouchDistanceMultiplier.get()).floatValue() : 1.0f;
            float whisperMultiplayer = packet.isWhispering() ? ((Double)Voicechat.SERVER_CONFIG.whisperDistanceMultiplier.get()).floatValue() : 1.0f;
            float multiplier = crouchMultiplayer * whisperMultiplayer;
            soundPacket = new PlayerSoundPacket(sender.w_2705_t(), sender.w_2705_t(), packet.getData(), packet.getSequenceNumber(), packet.isWhispering(), distance *= multiplier, null);
            source = "proximity";
        }
        this.broadcast(ServerWorldUtils.getPlayersInRange(sender.c_3005_b(), sender.s_4990_V(), this.getBroadcastRange(distance), p -> !p.w_2705_t().equals(sender.w_2705_t())), soundPacket, sender, senderState, groupId, source);
    }

    public void sendSoundPacket(@Nullable B_4088_l sender, @Nullable PlayerState senderState, B_4088_l receiver, PlayerState receiverState, @Nullable ClientConnection connection, SoundPacket<?> soundPacket, String source) {
        PluginManager.instance().onListenerAudio(receiver.w_2705_t(), soundPacket);
        if (connection == null) {
            return;
        }
        if (receiverState.isDisabled() || receiverState.isDisconnected()) {
            return;
        }
        if (PluginManager.instance().onSoundPacket(sender, senderState, receiver, receiverState, soundPacket, source)) {
            return;
        }
        if (!PermissionManager.INSTANCE.LISTEN_PERMISSION.hasPermission(receiver)) {
            CooldownTimer.run(String.format("no-listen-%s", receiver.w_2705_t()), 30000L, () -> receiver.n_1700_B((x_282_a)new F_2904_S("message.voicechat.no_listen_permission"), true));
            return;
        }
        this.sendPacket(soundPacket, connection);
    }

    public double getBroadcastRange(float minRange) {
        double broadcastRange = (Double)Voicechat.SERVER_CONFIG.broadcastRange.get();
        if (broadcastRange < 0.0) {
            broadcastRange = (Double)Voicechat.SERVER_CONFIG.voiceChatDistance.get() + 1.0;
        }
        return Math.max(broadcastRange, (double)minRange);
    }

    public void broadcast(Collection<B_4088_l> players, SoundPacket<?> packet, @Nullable B_4088_l sender, @Nullable PlayerState senderState, @Nullable UUID groupId, String source) {
        for (B_4088_l player : players) {
            PlayerState state = this.playerStateManager.getState(player.w_2705_t());
            if (state == null || state.hasGroup() && state.getGroup().equals(groupId)) continue;
            Group receiverGroup = null;
            if (state.hasGroup()) {
                receiverGroup = this.groupManager.getGroup(state.getGroup());
            }
            if (receiverGroup != null && receiverGroup.isIsolated()) continue;
            ClientConnection connection = this.getConnection(state.getUuid());
            this.sendSoundPacket(sender, senderState, player, state, connection, packet, source);
        }
    }

    private void sendKeepAlives() {
        long timestamp = System.currentTimeMillis();
        this.connections.values().removeIf(connection -> {
            if (timestamp - connection.getLastKeepAliveResponse() >= (long)((Integer)Voicechat.SERVER_CONFIG.keepAlive.get()).intValue() * 10L) {
                this.secrets.remove(connection.getPlayerUUID());
                Voicechat.LOGGER.info("Player {} timed out", connection.getPlayerUUID());
                B_4088_l player = this.server.p_178_J().n_1700_B(connection.getPlayerUUID());
                if (player != null) {
                    Voicechat.LOGGER.info("Reconnecting player {}", player.O_1309_Q().getString());
                    Voicechat.SERVER.initializePlayerConnection(player);
                } else {
                    Voicechat.LOGGER.warn("Reconnecting player {} failed (Could not find player)", connection.getPlayerUUID());
                }
                CommonCompatibilityManager.INSTANCE.emitServerVoiceChatDisconnectedEvent(connection.getPlayerUUID());
                PluginManager.instance().onPlayerDisconnected(connection.getPlayerUUID());
                return true;
            }
            return false;
        });
        for (ClientConnection connection2 : this.connections.values()) {
            this.sendPacket(new KeepAlivePacket(), connection2);
        }
    }

    @Nullable
    public ClientConnection getSender(NetworkMessage message) {
        return this.connections.values().stream().filter(connection -> connection.getAddress().equals(message.getAddress())).findAny().orElse(null);
    }

    @Nullable
    public ClientConnection getUnconnectedSender(NetworkMessage message) {
        return this.unCheckedConnections.values().stream().filter(connection -> connection.getAddress().equals(message.getAddress())).findAny().orElse(null);
    }

    public Map<UUID, ClientConnection> getConnections() {
        return this.connections;
    }

    @Nullable
    public ClientConnection getConnection(UUID playerID) {
        return this.connections.get(playerID);
    }

    public VoicechatSocket getSocket() {
        return this.socket;
    }

    public int getPort() {
        return this.socket.getLocalPort();
    }

    public boolean sendPacket(Packet<?> packet, ClientConnection connection) {
        try {
            this.sendPacketRaw(packet, connection);
            return true;
        }
        catch (Exception e) {
            Voicechat.LOGGER.error("Failed to send voice chat packet to {}", connection.getPlayerUUID());
            return false;
        }
    }

    public void sendPacketRaw(Packet<?> packet, ClientConnection connection) throws Exception {
        connection.send(this, new NetworkMessage(packet));
    }

    public PingManager getPingManager() {
        return this.pingManager;
    }

    public PlayerStateManager getPlayerStateManager() {
        return this.playerStateManager;
    }

    public ServerGroupManager getGroupManager() {
        return this.groupManager;
    }

    public ServerCategoryManager getCategoryManager() {
        return this.categoryManager;
    }

    public G_564_y getServer() {
        return this.server;
    }

    private class ProcessThread
    extends Thread {
        private boolean running = true;
        private long lastKeepAlive = 0L;

        public ProcessThread() {
            this.setDaemon(true);
            this.setName("VoiceChatPacketProcessingThread");
            this.setUncaughtExceptionHandler(new VoicechatUncaughtExceptionHandler());
        }

        @Override
        public void run() {
            while (this.running) {
                try {
                    Packet<MicPacket> packet;
                    AuthenticatePacket packet2;
                    Secret secret;
                    NetworkMessage message;
                    RawUdpPacket rawPacket;
                    Server.this.pingManager.checkTimeouts();
                    long keepAliveTime = System.currentTimeMillis();
                    if (keepAliveTime - this.lastKeepAlive > (long)((Integer)Voicechat.SERVER_CONFIG.keepAlive.get()).intValue()) {
                        Server.this.sendKeepAlives();
                        this.lastKeepAlive = keepAliveTime;
                    }
                    if ((rawPacket = Server.this.packetQueue.poll(10L, TimeUnit.MILLISECONDS)) == null) continue;
                    try {
                        message = NetworkMessage.readPacketServer(rawPacket, Server.this);
                    }
                    catch (Exception e) {
                        CooldownTimer.run("failed_reading_packet", () -> Voicechat.LOGGER.warn("Failed to read packet from {}", rawPacket.getSocketAddress()));
                        continue;
                    }
                    if (message == null) continue;
                    if (System.currentTimeMillis() - message.getTimestamp() > message.getTTL()) {
                        CooldownTimer.run("ttl", () -> {
                            Voicechat.LOGGER.warn("Dropping voice chat packets! Your Server might be overloaded!", new Object[0]);
                            Voicechat.LOGGER.warn("Packet queue has {} packets", Server.this.packetQueue.size());
                        });
                        continue;
                    }
                    if (message.getPacket() instanceof AuthenticatePacket && (secret = Server.this.secrets.get((packet2 = (AuthenticatePacket)message.getPacket()).getPlayerUUID())) != null && secret.equals(packet2.getSecret())) {
                        ClientConnection connection = Server.this.unCheckedConnections.get(packet2.getPlayerUUID());
                        if (connection == null) {
                            connection = Server.this.connections.get(packet2.getPlayerUUID());
                        }
                        if (connection == null) {
                            connection = new ClientConnection(packet2.getPlayerUUID(), message.getAddress());
                            Server.this.unCheckedConnections.put(packet2.getPlayerUUID(), connection);
                            Voicechat.LOGGER.info("Successfully authenticated player {}", packet2.getPlayerUUID());
                        }
                        Server.this.sendPacket(new AuthenticateAckPacket(), connection);
                    }
                    if (message.getPacket() instanceof ConnectionCheckPacket) {
                        ClientConnection connection = Server.this.getUnconnectedSender(message);
                        if (connection == null) {
                            connection = Server.this.getSender(message);
                            if (connection == null) continue;
                            Server.this.sendPacket(new ConnectionCheckAckPacket(), connection);
                            continue;
                        }
                        connection.setLastKeepAliveResponse(System.currentTimeMillis());
                        Server.this.connections.put(connection.getPlayerUUID(), connection);
                        Server.this.unCheckedConnections.remove(connection.getPlayerUUID());
                        Voicechat.LOGGER.info("Successfully validated connection of player {}", connection.getPlayerUUID());
                        B_4088_l player = Server.this.server.p_178_J().n_1700_B(connection.getPlayerUUID());
                        if (player != null) {
                            CommonCompatibilityManager.INSTANCE.emitServerVoiceChatConnectedEvent(player);
                            PluginManager.instance().onPlayerConnected(player);
                            Voicechat.LOGGER.info("Player {} ({}) successfully connected to voice chat", player.O_1309_Q().getString(), connection.getPlayerUUID());
                        }
                        Server.this.sendPacket(new ConnectionCheckAckPacket(), connection);
                        continue;
                    }
                    ClientConnection conn = Server.this.getSender(message);
                    if (conn == null) continue;
                    if (message.getPacket() instanceof MicPacket) {
                        packet = (MicPacket)message.getPacket();
                        Server.this.onMicPacket(conn.getPlayerUUID(), (MicPacket)packet);
                        continue;
                    }
                    if (message.getPacket() instanceof PingPacket) {
                        packet = (PingPacket)message.getPacket();
                        Server.this.pingManager.onPongPacket((PingPacket)packet);
                        continue;
                    }
                    if (!(message.getPacket() instanceof KeepAlivePacket)) continue;
                    conn.setLastKeepAliveResponse(System.currentTimeMillis());
                }
                catch (Exception e) {
                    Voicechat.LOGGER.error("Voice chat server error", e);
                }
            }
        }

        public void close() {
            this.running = false;
        }
    }
}

