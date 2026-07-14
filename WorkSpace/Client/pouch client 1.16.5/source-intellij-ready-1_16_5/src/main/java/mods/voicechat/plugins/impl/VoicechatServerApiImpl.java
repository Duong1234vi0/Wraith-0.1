/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import mods.voicechat.Voicechat;
import mods.voicechat.api.Entity;
import mods.voicechat.api.Group;
import mods.voicechat.api.Position;
import mods.voicechat.api.ServerLevel;
import mods.voicechat.api.ServerPlayer;
import mods.voicechat.api.VoicechatConnection;
import mods.voicechat.api.VoicechatServerApi;
import mods.voicechat.api.VolumeCategory;
import mods.voicechat.api.audiochannel.AudioChannel;
import mods.voicechat.api.audiochannel.AudioPlayer;
import mods.voicechat.api.audiochannel.EntityAudioChannel;
import mods.voicechat.api.audiochannel.LocationalAudioChannel;
import mods.voicechat.api.audiochannel.StaticAudioChannel;
import mods.voicechat.api.audiolistener.AudioListener;
import mods.voicechat.api.audiolistener.PlayerAudioListener;
import mods.voicechat.api.audiosender.AudioSender;
import mods.voicechat.api.config.ConfigAccessor;
import mods.voicechat.api.opus.OpusEncoder;
import mods.voicechat.api.packets.EntitySoundPacket;
import mods.voicechat.api.packets.LocationalSoundPacket;
import mods.voicechat.api.packets.StaticSoundPacket;
import mods.voicechat.intercompatibility.CommonCompatibilityManager;
import mods.voicechat.plugins.PluginManager;
import mods.voicechat.plugins.impl.GroupImpl;
import mods.voicechat.plugins.impl.PositionImpl;
import mods.voicechat.plugins.impl.ServerLevelImpl;
import mods.voicechat.plugins.impl.ServerPlayerImpl;
import mods.voicechat.plugins.impl.VoicechatApiImpl;
import mods.voicechat.plugins.impl.VoicechatConnectionImpl;
import mods.voicechat.plugins.impl.VolumeCategoryImpl;
import mods.voicechat.plugins.impl.audiochannel.AudioPlayerImpl;
import mods.voicechat.plugins.impl.audiochannel.AudioSupplier;
import mods.voicechat.plugins.impl.audiochannel.EntityAudioChannelImpl;
import mods.voicechat.plugins.impl.audiochannel.LocationalAudioChannelImpl;
import mods.voicechat.plugins.impl.audiochannel.StaticAudioChannelImpl;
import mods.voicechat.plugins.impl.audiolistener.PlayerAudioListenerImpl;
import mods.voicechat.plugins.impl.audiosender.AudioSenderImpl;
import mods.voicechat.plugins.impl.config.ConfigAccessorImpl;
import mods.voicechat.plugins.impl.packets.EntitySoundPacketImpl;
import mods.voicechat.plugins.impl.packets.LocationalSoundPacketImpl;
import mods.voicechat.plugins.impl.packets.StaticSoundPacketImpl;
import mods.voicechat.voice.common.PlayerState;
import mods.voicechat.voice.common.SoundPacket;
import mods.voicechat.voice.server.ClientConnection;
import mods.voicechat.voice.server.Group;
import mods.voicechat.voice.server.Server;
import mods.voicechat.voice.server.ServerWorldUtils;

public class VoicechatServerApiImpl
extends VoicechatApiImpl
implements VoicechatServerApi {
    @Deprecated
    public static final VoicechatServerApiImpl INSTANCE = new VoicechatServerApiImpl();

    protected VoicechatServerApiImpl() {
    }

    public static VoicechatServerApi instance() {
        return CommonCompatibilityManager.INSTANCE.getServerApi();
    }

    @Override
    public void sendEntitySoundPacketTo(VoicechatConnection connection, EntitySoundPacket p) {
        if (p instanceof EntitySoundPacketImpl) {
            EntitySoundPacketImpl packet = (EntitySoundPacketImpl)p;
            VoicechatServerApiImpl.sendPacket(connection, packet.getPacket());
        }
    }

    @Override
    public void sendLocationalSoundPacketTo(VoicechatConnection connection, LocationalSoundPacket p) {
        if (p instanceof LocationalSoundPacketImpl) {
            LocationalSoundPacketImpl packet = (LocationalSoundPacketImpl)p;
            VoicechatServerApiImpl.sendPacket(connection, packet.getPacket());
        }
    }

    @Override
    public void sendStaticSoundPacketTo(VoicechatConnection connection, StaticSoundPacket p) {
        if (p instanceof StaticSoundPacketImpl) {
            StaticSoundPacketImpl packet = (StaticSoundPacketImpl)p;
            VoicechatServerApiImpl.sendPacket(connection, packet.getPacket());
        }
    }

    @Override
    @Nullable
    public EntityAudioChannel createEntityAudioChannel(UUID channelId, Entity entity) {
        Server server = Voicechat.SERVER.getServer();
        if (server == null) {
            return null;
        }
        return new EntityAudioChannelImpl(channelId, server, entity);
    }

    @Override
    @Nullable
    public LocationalAudioChannel createLocationalAudioChannel(UUID channelId, ServerLevel level, Position initialPosition) {
        Server server = Voicechat.SERVER.getServer();
        if (server == null) {
            return null;
        }
        if (initialPosition instanceof PositionImpl) {
            PositionImpl p = (PositionImpl)initialPosition;
            return new LocationalAudioChannelImpl(channelId, server, level, p);
        }
        throw new IllegalArgumentException("initialPosition is not an instance of PositionImpl");
    }

    @Override
    @Nullable
    public StaticAudioChannel createStaticAudioChannel(UUID channelId, ServerLevel level, VoicechatConnection connection) {
        Server server = Voicechat.SERVER.getServer();
        if (server == null) {
            return null;
        }
        if (connection instanceof VoicechatConnectionImpl) {
            VoicechatConnectionImpl conn = (VoicechatConnectionImpl)connection;
            return new StaticAudioChannelImpl(channelId, server, conn);
        }
        return null;
    }

    @Override
    public AudioPlayer createAudioPlayer(AudioChannel audioChannel, OpusEncoder encoder, Supplier<short[]> audioSupplier) {
        return new AudioPlayerImpl(audioChannel, encoder, audioSupplier);
    }

    @Override
    public AudioPlayer createAudioPlayer(AudioChannel audioChannel, OpusEncoder encoder, short[] audio) {
        return new AudioPlayerImpl(audioChannel, encoder, new AudioSupplier(audio));
    }

    @Override
    public AudioSender createAudioSender(VoicechatConnection connection) {
        return new AudioSenderImpl(connection.getPlayer().getUuid());
    }

    @Override
    public boolean registerAudioSender(AudioSender sender) {
        if (!(sender instanceof AudioSenderImpl)) {
            return false;
        }
        return AudioSenderImpl.registerAudioSender((AudioSenderImpl)sender);
    }

    @Override
    public boolean unregisterAudioSender(AudioSender sender) {
        if (!(sender instanceof AudioSenderImpl)) {
            return false;
        }
        return AudioSenderImpl.unregisterAudioSender((AudioSenderImpl)sender);
    }

    @Override
    public PlayerAudioListener.Builder playerAudioListenerBuilder() {
        return new PlayerAudioListenerImpl.BuilderImpl();
    }

    @Override
    public boolean registerAudioListener(AudioListener listener) {
        return PluginManager.instance().registerAudioListener(listener);
    }

    @Override
    public boolean unregisterAudioListener(AudioListener listener) {
        return this.unregisterAudioListener(listener.getListenerId());
    }

    @Override
    public boolean unregisterAudioListener(UUID listenerId) {
        return PluginManager.instance().unregisterAudioListener(listenerId);
    }

    public static void sendPacket(VoicechatConnection receiver, SoundPacket<?> soundPacket) {
        Server server = Voicechat.SERVER.getServer();
        if (server == null) {
            return;
        }
        PlayerState state = server.getPlayerStateManager().getState(receiver.getPlayer().getUuid());
        if (state == null) {
            return;
        }
        if (!(receiver.getPlayer() instanceof ServerPlayerImpl)) {
            throw new IllegalArgumentException("ServerPlayer is not an instance of ServerPlayerImpl");
        }
        ServerPlayerImpl serverPlayerImpl = (ServerPlayerImpl)receiver.getPlayer();
        ClientConnection c = server.getConnections().get(receiver.getPlayer().getUuid());
        server.sendSoundPacket(null, null, serverPlayerImpl.getRealServerPlayer(), state, c, soundPacket, "plugin");
    }

    @Override
    @Nullable
    public VoicechatConnection getConnectionOf(UUID playerUuid) {
        Server server = Voicechat.SERVER.getServer();
        if (server == null) {
            return null;
        }
        B_4088_l player = server.getServer().p_178_J().n_1700_B(playerUuid);
        if (player == null) {
            return null;
        }
        return VoicechatConnectionImpl.fromPlayer(player);
    }

    @Override
    public mods.voicechat.api.Group createGroup(String name, @Nullable String password) {
        return this.createGroup(name, password, false);
    }

    @Override
    public mods.voicechat.api.Group createGroup(String name, @Nullable String password, boolean persistent) {
        return this.groupBuilder().setName(name).setPassword(password).setPersistent(persistent).build();
    }

    @Override
    public Group.Builder groupBuilder() {
        return new GroupImpl.BuilderImpl();
    }

    @Override
    public boolean removeGroup(UUID groupId) {
        Server server = Voicechat.SERVER.getServer();
        if (server == null) {
            return false;
        }
        return server.getGroupManager().removeGroup(groupId);
    }

    @Override
    @Nullable
    public mods.voicechat.api.Group getGroup(UUID groupId) {
        Server server = Voicechat.SERVER.getServer();
        if (server == null) {
            return null;
        }
        return new GroupImpl(server.getGroupManager().getGroup(groupId));
    }

    @Override
    public Collection<mods.voicechat.api.Group> getGroups() {
        Server server = Voicechat.SERVER.getServer();
        if (server == null) {
            return Collections.emptyList();
        }
        return server.getGroupManager().getGroups().values().stream().map(group -> new GroupImpl((Group)group)).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Deprecated
    public UUID getSecret(UUID userId) {
        return null;
    }

    @Override
    public Collection<ServerPlayer> getPlayersInRange(ServerLevel level, Position pos, double range, @Nullable Predicate<ServerPlayer> filter) {
        if (!(pos instanceof PositionImpl)) {
            throw new IllegalArgumentException("Position is not an instance of PositionImpl");
        }
        PositionImpl p = (PositionImpl)pos;
        if (!(level instanceof ServerLevelImpl)) {
            throw new IllegalArgumentException("ServerLevel is not an instance of ServerLevelImpl");
        }
        ServerLevelImpl serverLevel = (ServerLevelImpl)level;
        return ServerWorldUtils.getPlayersInRange(serverLevel.getRawServerLevel(), p.getPosition(), range, filter == null ? null : player -> filter.test(new ServerPlayerImpl((B_4088_l)player))).stream().map(ServerPlayerImpl::new).collect(Collectors.toList());
    }

    @Override
    public double getBroadcastRange() {
        return Math.max((Double)Voicechat.SERVER_CONFIG.voiceChatDistance.get(), (Double)Voicechat.SERVER_CONFIG.broadcastRange.get());
    }

    @Override
    public void registerVolumeCategory(VolumeCategory category) {
        if (!(category instanceof VolumeCategoryImpl)) {
            throw new IllegalArgumentException("VolumeCategory is not an instance of VolumeCategoryImpl");
        }
        VolumeCategoryImpl c = (VolumeCategoryImpl)category;
        Server server = Voicechat.SERVER.getServer();
        if (server == null) {
            return;
        }
        server.getCategoryManager().addCategory(c);
        PluginManager.instance().onRegisterVolumeCategory(category);
    }

    @Override
    public void unregisterVolumeCategory(String categoryId) {
        Server server = Voicechat.SERVER.getServer();
        if (server == null) {
            return;
        }
        VolumeCategoryImpl category = server.getCategoryManager().removeCategory(categoryId);
        if (category != null) {
            PluginManager.instance().onUnregisterVolumeCategory(category);
        }
    }

    @Override
    public Collection<VolumeCategory> getVolumeCategories() {
        Server server = Voicechat.SERVER.getServer();
        if (server == null) {
            return Collections.emptyList();
        }
        return server.getCategoryManager().getCategories().stream().map(VolumeCategory.class::cast).collect(Collectors.toList());
    }

    @Override
    public ConfigAccessor getServerConfig() {
        return new ConfigAccessorImpl(Voicechat.SERVER_CONFIG.voiceChatDistance.getConfig());
    }
}

