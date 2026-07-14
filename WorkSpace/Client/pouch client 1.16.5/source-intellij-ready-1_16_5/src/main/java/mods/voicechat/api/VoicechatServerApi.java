/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.api;

import java.util.Collection;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import mods.voicechat.api.Entity;
import mods.voicechat.api.Group;
import mods.voicechat.api.Position;
import mods.voicechat.api.ServerLevel;
import mods.voicechat.api.ServerPlayer;
import mods.voicechat.api.VoicechatApi;
import mods.voicechat.api.VoicechatConnection;
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

public interface VoicechatServerApi
extends VoicechatApi {
    public void sendEntitySoundPacketTo(VoicechatConnection var1, EntitySoundPacket var2);

    public void sendLocationalSoundPacketTo(VoicechatConnection var1, LocationalSoundPacket var2);

    public void sendStaticSoundPacketTo(VoicechatConnection var1, StaticSoundPacket var2);

    @Nullable
    public EntityAudioChannel createEntityAudioChannel(UUID var1, Entity var2);

    @Nullable
    public LocationalAudioChannel createLocationalAudioChannel(UUID var1, ServerLevel var2, Position var3);

    @Nullable
    public StaticAudioChannel createStaticAudioChannel(UUID var1, ServerLevel var2, VoicechatConnection var3);

    public AudioPlayer createAudioPlayer(AudioChannel var1, OpusEncoder var2, Supplier<short[]> var3);

    public AudioPlayer createAudioPlayer(AudioChannel var1, OpusEncoder var2, short[] var3);

    public AudioSender createAudioSender(VoicechatConnection var1);

    public boolean registerAudioSender(AudioSender var1);

    public boolean unregisterAudioSender(AudioSender var1);

    public PlayerAudioListener.Builder playerAudioListenerBuilder();

    public boolean registerAudioListener(AudioListener var1);

    public boolean unregisterAudioListener(AudioListener var1);

    public boolean unregisterAudioListener(UUID var1);

    @Nullable
    public VoicechatConnection getConnectionOf(UUID var1);

    @Nullable
    default public VoicechatConnection getConnectionOf(ServerPlayer player) {
        return this.getConnectionOf(player.getUuid());
    }

    @Deprecated
    public Group createGroup(String var1, @Nullable String var2);

    @Deprecated
    public Group createGroup(String var1, @Nullable String var2, boolean var3);

    public Group.Builder groupBuilder();

    public boolean removeGroup(UUID var1);

    @Nullable
    public Group getGroup(UUID var1);

    public Collection<Group> getGroups();

    @Nullable
    @Deprecated
    public UUID getSecret(UUID var1);

    public Collection<ServerPlayer> getPlayersInRange(ServerLevel var1, Position var2, double var3, Predicate<ServerPlayer> var5);

    public double getBroadcastRange();

    default public Collection<ServerPlayer> getPlayersInRange(ServerLevel level, Position pos, double range) {
        return this.getPlayersInRange(level, pos, range, null);
    }

    public void registerVolumeCategory(VolumeCategory var1);

    default public void unregisterVolumeCategory(VolumeCategory category) {
        this.unregisterVolumeCategory(category.getId());
    }

    public void unregisterVolumeCategory(String var1);

    public Collection<VolumeCategory> getVolumeCategories();

    public ConfigAccessor getServerConfig();
}

