/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import mods.voicechat.Voicechat;
import mods.voicechat.api.VoicechatConnection;
import mods.voicechat.api.VoicechatPlugin;
import mods.voicechat.api.VoicechatSocket;
import mods.voicechat.api.VolumeCategory;
import mods.voicechat.api.audiolistener.AudioListener;
import mods.voicechat.api.audiolistener.PlayerAudioListener;
import mods.voicechat.api.events.CreateGroupEvent;
import mods.voicechat.api.events.EntitySoundPacketEvent;
import mods.voicechat.api.events.Event;
import mods.voicechat.api.events.EventRegistration;
import mods.voicechat.api.events.JoinGroupEvent;
import mods.voicechat.api.events.LeaveGroupEvent;
import mods.voicechat.api.events.LocationalSoundPacketEvent;
import mods.voicechat.api.events.MicrophonePacketEvent;
import mods.voicechat.api.events.PlayerConnectedEvent;
import mods.voicechat.api.events.PlayerDisconnectedEvent;
import mods.voicechat.api.events.PlayerStateChangedEvent;
import mods.voicechat.api.events.RegisterVolumeCategoryEvent;
import mods.voicechat.api.events.RemoveGroupEvent;
import mods.voicechat.api.events.StaticSoundPacketEvent;
import mods.voicechat.api.events.UnregisterVolumeCategoryEvent;
import mods.voicechat.api.events.VoiceHostEvent;
import mods.voicechat.api.events.VoicechatServerStartedEvent;
import mods.voicechat.api.events.VoicechatServerStartingEvent;
import mods.voicechat.api.events.VoicechatServerStoppedEvent;
import mods.voicechat.intercompatibility.CommonCompatibilityManager;
import mods.voicechat.plugins.EventBuilder;
import mods.voicechat.plugins.impl.GroupImpl;
import mods.voicechat.plugins.impl.VoicechatConnectionImpl;
import mods.voicechat.plugins.impl.VoicechatServerApiImpl;
import mods.voicechat.plugins.impl.VoicechatSocketImpl;
import mods.voicechat.plugins.impl.audiolistener.PlayerAudioListenerImpl;
import mods.voicechat.plugins.impl.events.CreateGroupEventImpl;
import mods.voicechat.plugins.impl.events.EntitySoundPacketEventImpl;
import mods.voicechat.plugins.impl.events.JoinGroupEventImpl;
import mods.voicechat.plugins.impl.events.LeaveGroupEventImpl;
import mods.voicechat.plugins.impl.events.LocationalSoundPacketEventImpl;
import mods.voicechat.plugins.impl.events.MicrophonePacketEventImpl;
import mods.voicechat.plugins.impl.events.PlayerConnectedEventImpl;
import mods.voicechat.plugins.impl.events.PlayerDisconnectedEventImpl;
import mods.voicechat.plugins.impl.events.PlayerStateChangedEventImpl;
import mods.voicechat.plugins.impl.events.RegisterVolumeCategoryEventImpl;
import mods.voicechat.plugins.impl.events.RemoveGroupEventImpl;
import mods.voicechat.plugins.impl.events.StaticSoundPacketEventImpl;
import mods.voicechat.plugins.impl.events.UnregisterVolumeCategoryEventImpl;
import mods.voicechat.plugins.impl.events.VoiceHostEventImpl;
import mods.voicechat.plugins.impl.events.VoicechatServerStartedEventImpl;
import mods.voicechat.plugins.impl.events.VoicechatServerStartingEventImpl;
import mods.voicechat.plugins.impl.events.VoicechatServerStoppedEventImpl;
import mods.voicechat.plugins.impl.packets.EntitySoundPacketImpl;
import mods.voicechat.plugins.impl.packets.LocationalSoundPacketImpl;
import mods.voicechat.plugins.impl.packets.MicrophonePacketImpl;
import mods.voicechat.plugins.impl.packets.SoundPacketImpl;
import mods.voicechat.plugins.impl.packets.StaticSoundPacketImpl;
import mods.voicechat.voice.common.GroupSoundPacket;
import mods.voicechat.voice.common.LocationSoundPacket;
import mods.voicechat.voice.common.MicPacket;
import mods.voicechat.voice.common.PlayerSoundPacket;
import mods.voicechat.voice.common.PlayerState;
import mods.voicechat.voice.common.SoundPacket;
import mods.voicechat.voice.server.Group;
import mods.voicechat.voice.server.Server;
import net.minecraft.server.G_564_y;

public class PluginManager {
    private List<VoicechatPlugin> plugins;
    private Map<Class<? extends Event>, List<Consumer<? extends Event>>> events;
    private Map<UUID, List<PlayerAudioListener>> playerAudioListeners;
    private static PluginManager instance;

    public void init() {
        if (this.plugins != null) {
            return;
        }
        Voicechat.LOGGER.info("Loading plugins", new Object[0]);
        this.plugins = CommonCompatibilityManager.INSTANCE.loadPlugins();
        Voicechat.LOGGER.info("Loaded {} plugin(s)", this.plugins.size());
        Voicechat.LOGGER.info("Initializing plugins", new Object[0]);
        for (VoicechatPlugin plugin : this.plugins) {
            try {
                plugin.initialize(VoicechatServerApiImpl.instance());
            }
            catch (Throwable e) {
                Voicechat.LOGGER.warn("Failed to initialize plugin '{}'", plugin.getPluginId(), e);
            }
        }
        Voicechat.LOGGER.info("Initialized {} plugin(s)", this.plugins.size());
        this.gatherEvents();
        this.playerAudioListeners = new HashMap<UUID, List<PlayerAudioListener>>();
    }

    private void gatherEvents() {
        EventBuilder eventBuilder = EventBuilder.create();
        EventRegistration registration = eventBuilder::addEvent;
        for (VoicechatPlugin plugin : this.plugins) {
            Voicechat.LOGGER.info("Registering events for '{}'", plugin.getPluginId());
            try {
                plugin.registerEvents(registration);
            }
            catch (Throwable e) {
                Voicechat.LOGGER.warn("Failed to register events for plugin '{}'", plugin.getPluginId(), e);
            }
        }
        this.events = eventBuilder.build();
    }

    public boolean registerAudioListener(AudioListener l) {
        if (!(l instanceof PlayerAudioListener)) {
            return false;
        }
        PlayerAudioListener listener = (PlayerAudioListener)l;
        boolean exists = this.playerAudioListeners.values().stream().anyMatch(listeners -> listeners.stream().anyMatch(playerAudioListener -> playerAudioListener.getListenerId().equals(listener.getListenerId())));
        if (exists) {
            return false;
        }
        this.playerAudioListeners.computeIfAbsent(listener.getPlayerUuid(), k -> new ArrayList()).add(listener);
        return true;
    }

    public boolean unregisterAudioListener(UUID listenerId) {
        boolean removed = this.playerAudioListeners.values().stream().anyMatch(listeners -> listeners.removeIf(listener -> listener.getListenerId().equals(listenerId)));
        if (!removed) {
            return false;
        }
        this.playerAudioListeners.values().removeIf(List::isEmpty);
        return true;
    }

    public List<PlayerAudioListener> getPlayerAudioListeners(UUID playerUuid) {
        return this.playerAudioListeners.getOrDefault(playerUuid, Collections.emptyList());
    }

    public void onListenerAudio(UUID playerUuid, SoundPacket<?> packet) {
        if (playerUuid.equals(packet.getSender())) {
            return;
        }
        List<PlayerAudioListener> listeners = this.getPlayerAudioListeners(playerUuid);
        if (listeners.isEmpty()) {
            return;
        }
        SoundPacketImpl soundPacket = packet instanceof GroupSoundPacket ? new StaticSoundPacketImpl((GroupSoundPacket)packet) : (packet instanceof PlayerSoundPacket ? new EntitySoundPacketImpl((PlayerSoundPacket)packet) : (packet instanceof LocationSoundPacket ? new LocationalSoundPacketImpl((LocationSoundPacket)packet) : new SoundPacketImpl(packet)));
        for (PlayerAudioListener l : listeners) {
            if (!(l instanceof PlayerAudioListenerImpl)) continue;
            ((PlayerAudioListenerImpl)l).getListener().accept(soundPacket);
        }
    }

    public <T extends Event> boolean dispatchEvent(Class<? extends T> eventClass, T event) {
        List<Consumer<? extends Event>> events = this.events.get(eventClass);
        if (events == null) {
            return false;
        }
        for (Consumer<? extends Event> evt : events) {
            try {
                Consumer<? extends Event> e = evt;
                e.accept(event);
                if (!event.isCancelled()) continue;
                break;
            }
            catch (Exception e) {
                Voicechat.LOGGER.error("Failed to dispatch event '{}'", event.getClass().getSimpleName(), e);
            }
        }
        return event.isCancelled();
    }

    public VoicechatSocket getSocketImplementation(G_564_y server) {
        VoicechatServerStartingEventImpl event = new VoicechatServerStartingEventImpl();
        this.dispatchEvent(VoicechatServerStartingEvent.class, event);
        VoicechatSocket socket = event.getSocketImplementation();
        if (socket == null) {
            socket = new VoicechatSocketImpl();
            Voicechat.LOGGER.debug("Using default voicechat socket implementation", new Object[0]);
        } else {
            Voicechat.LOGGER.info("Using custom voicechat socket implementation: {}", socket.getClass().getName());
        }
        return socket;
    }

    public String getVoiceHost(String voiceHost) {
        VoiceHostEventImpl event = new VoiceHostEventImpl(voiceHost);
        this.dispatchEvent(VoiceHostEvent.class, event);
        return event.getVoiceHost();
    }

    public void onRegisterVolumeCategory(VolumeCategory category) {
        this.dispatchEvent(RegisterVolumeCategoryEvent.class, new RegisterVolumeCategoryEventImpl(category));
    }

    public void onUnregisterVolumeCategory(VolumeCategory category) {
        this.dispatchEvent(UnregisterVolumeCategoryEvent.class, new UnregisterVolumeCategoryEventImpl(category));
    }

    public void onServerStarted() {
        this.dispatchEvent(VoicechatServerStartedEvent.class, new VoicechatServerStartedEventImpl());
    }

    public void onServerStopped() {
        this.dispatchEvent(VoicechatServerStoppedEvent.class, new VoicechatServerStoppedEventImpl());
    }

    public void onPlayerConnected(B_4088_l player) {
        this.dispatchEvent(PlayerConnectedEvent.class, new PlayerConnectedEventImpl(VoicechatConnectionImpl.fromPlayer(player)));
    }

    public void onPlayerDisconnected(UUID player) {
        this.dispatchEvent(PlayerDisconnectedEvent.class, new PlayerDisconnectedEventImpl(player));
    }

    public void onPlayerStateChanged(PlayerState state) {
        this.dispatchEvent(PlayerStateChangedEvent.class, new PlayerStateChangedEventImpl(state));
    }

    public boolean onJoinGroup(B_4088_l player, @Nullable Group group) {
        if (group == null) {
            return this.onLeaveGroup(player);
        }
        return this.dispatchEvent(JoinGroupEvent.class, new JoinGroupEventImpl(new GroupImpl(group), VoicechatConnectionImpl.fromPlayer(player)));
    }

    public boolean onCreateGroup(@Nullable B_4088_l player, @Nullable Group group) {
        if (group == null) {
            if (player == null) {
                return false;
            }
            return this.onLeaveGroup(player);
        }
        return this.dispatchEvent(CreateGroupEvent.class, new CreateGroupEventImpl(new GroupImpl(group), VoicechatConnectionImpl.fromPlayer(player)));
    }

    public boolean onLeaveGroup(B_4088_l player) {
        Group g;
        UUID groupUUID;
        Server server = Voicechat.SERVER.getServer();
        if (server == null) {
            return false;
        }
        GroupImpl group = null;
        PlayerState state = server.getPlayerStateManager().getState(player.w_2705_t());
        if (state != null && (groupUUID = state.getGroup()) != null && (g = server.getGroupManager().getGroup(groupUUID)) != null) {
            group = new GroupImpl(g);
        }
        return this.dispatchEvent(LeaveGroupEvent.class, new LeaveGroupEventImpl(group, VoicechatConnectionImpl.fromPlayer(player)));
    }

    public boolean onRemoveGroup(Group group) {
        return this.dispatchEvent(RemoveGroupEvent.class, new RemoveGroupEventImpl(new GroupImpl(group)));
    }

    public boolean onMicPacket(B_4088_l player, PlayerState state, MicPacket packet) {
        return this.dispatchEvent(MicrophonePacketEvent.class, new MicrophonePacketEventImpl(new MicrophonePacketImpl(packet, player.w_2705_t()), new VoicechatConnectionImpl(player, state)));
    }

    public boolean onSoundPacket(@Nullable B_4088_l sender, @Nullable PlayerState senderState, B_4088_l receiver, PlayerState receiverState, SoundPacket<?> p, String source) {
        VoicechatConnectionImpl senderConnection = null;
        if (sender != null && senderState != null) {
            senderConnection = new VoicechatConnectionImpl(sender, senderState);
        }
        VoicechatConnectionImpl receiverConnection = new VoicechatConnectionImpl(receiver, receiverState);
        if (p instanceof LocationSoundPacket) {
            LocationSoundPacket packet = (LocationSoundPacket)p;
            return this.dispatchEvent(LocationalSoundPacketEvent.class, new LocationalSoundPacketEventImpl(new LocationalSoundPacketImpl(packet), (VoicechatConnection)senderConnection, (VoicechatConnection)receiverConnection, source));
        }
        if (p instanceof PlayerSoundPacket) {
            PlayerSoundPacket packet = (PlayerSoundPacket)p;
            return this.dispatchEvent(EntitySoundPacketEvent.class, new EntitySoundPacketEventImpl(new EntitySoundPacketImpl(packet), (VoicechatConnection)senderConnection, (VoicechatConnection)receiverConnection, source));
        }
        if (p instanceof GroupSoundPacket) {
            GroupSoundPacket packet = (GroupSoundPacket)p;
            return this.dispatchEvent(StaticSoundPacketEvent.class, new StaticSoundPacketEventImpl(new StaticSoundPacketImpl(packet), (VoicechatConnection)senderConnection, (VoicechatConnection)receiverConnection, source));
        }
        return false;
    }

    public static PluginManager instance() {
        if (instance == null) {
            instance = new PluginManager();
            instance.init();
        }
        return instance;
    }
}

