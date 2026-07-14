/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.voice.client;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.V_772_m;
import lightning.product.MinecraftClient;
import lightning.product.x_282_a;
import mods.voicechat.Voicechat;
import mods.voicechat.VoicechatClient;
import mods.voicechat.debug.CooldownTimer;
import mods.voicechat.gui.onboarding.OnboardingManager;
import mods.voicechat.voice.client.AudioChannel;
import mods.voicechat.voice.client.AudioRecorder;
import mods.voicechat.voice.client.ChatUtils;
import mods.voicechat.voice.client.ClientManager;
import mods.voicechat.voice.client.ClientVoicechatConnection;
import mods.voicechat.voice.client.InitializationData;
import mods.voicechat.voice.client.MicThread;
import mods.voicechat.voice.client.SoundManager;
import mods.voicechat.voice.client.TalkCache;
import mods.voicechat.voice.client.speaker.SpeakerException;
import mods.voicechat.voice.common.SoundPacket;

public class ClientVoicechat {
    @Nullable
    private SoundManager soundManager;
    private final Map<UUID, AudioChannel> audioChannels;
    private final TalkCache talkCache;
    @Nullable
    private MicThread micThread;
    @Nullable
    private ClientVoicechatConnection connection;
    @Nullable
    private InitializationData initializationData;
    @Nullable
    private AudioRecorder recorder;
    private long startTime = System.currentTimeMillis();

    public ClientVoicechat() {
        this.talkCache = new TalkCache();
        try {
            this.reloadSoundManager();
        }
        catch (SpeakerException e) {
            Voicechat.LOGGER.error("Failed to start sound manager", e);
            ChatUtils.sendPlayerError("message.voicechat.speaker_unavailable", e);
        }
        this.audioChannels = new HashMap<UUID, AudioChannel>();
    }

    public void onVoiceChatConnected(ClientVoicechatConnection connection) {
        this.startMicThread(connection);
    }

    public void onVoiceChatDisconnected() {
        this.closeMicThread();
        if (this.connection != null) {
            this.connection.close();
            this.connection = null;
        }
    }

    public void connect(InitializationData data) throws Exception {
        this.initializationData = data;
        Voicechat.LOGGER.info("Connecting to voice chat server: '{}:{}'", this.initializationData.getServerIP(), this.initializationData.getServerPort());
        this.connection = new ClientVoicechatConnection(this, this.initializationData);
        this.connection.start();
        OnboardingManager.onConnecting();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void processSoundPacket(SoundPacket packet) {
        if (this.connection == null) {
            return;
        }
        Map<UUID, AudioChannel> map = this.audioChannels;
        synchronized (map) {
            if (!ClientManager.getPlayerStateManager().isDisabled()) {
                AudioChannel sendTo = this.audioChannels.get(packet.getChannelId());
                if (sendTo == null) {
                    try {
                        AudioChannel ch = new AudioChannel(this, this.connection.getData(), packet.getChannelId());
                        ch.addToQueue(packet);
                        ch.start();
                        this.audioChannels.put(packet.getChannelId(), ch);
                    }
                    catch (Exception e) {
                        CooldownTimer.run("playback_unavailable", () -> {
                            Voicechat.LOGGER.error("Failed to create audio channel", e);
                            ChatUtils.sendPlayerError("message.voicechat.playback_unavailable", e);
                        });
                    }
                } else {
                    sendTo.addToQueue(packet);
                }
            }
            this.audioChannels.values().stream().filter(AudioChannel::canKill).forEach(AudioChannel::closeAndKill);
            this.audioChannels.entrySet().removeIf(entry -> ((AudioChannel)entry.getValue()).isClosed());
        }
    }

    public void reloadSoundManager() throws SpeakerException {
        if (this.soundManager != null) {
            this.soundManager.close();
        }
        this.soundManager = new SoundManager((String)VoicechatClient.CLIENT_CONFIG.speaker.get());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void reloadAudio() {
        Voicechat.LOGGER.info("Reloading audio", new Object[0]);
        this.closeMicThread();
        Map<UUID, AudioChannel> map = this.audioChannels;
        synchronized (map) {
            Voicechat.LOGGER.info("Clearing audio channels", new Object[0]);
            this.audioChannels.forEach((uuid, audioChannel) -> audioChannel.closeAndKill());
            this.audioChannels.clear();
            try {
                Voicechat.LOGGER.info("Restarting sound manager", new Object[0]);
                this.reloadSoundManager();
            }
            catch (SpeakerException e) {
                Voicechat.LOGGER.error("Failed to restart sound manager", e);
            }
        }
        Voicechat.LOGGER.info("Starting microphone thread", new Object[0]);
        if (this.connection != null) {
            this.startMicThread(this.connection);
        }
    }

    private void startMicThread(ClientVoicechatConnection connection) {
        if (this.micThread != null) {
            this.micThread.close();
        }
        try {
            this.micThread = new MicThread(this, connection);
            this.micThread.start();
        }
        catch (Exception e) {
            Voicechat.LOGGER.error("Failed to start microphone thread", e);
            ChatUtils.sendPlayerError("message.voicechat.microphone_unavailable", e);
        }
    }

    public void closeMicThread() {
        if (this.micThread != null) {
            Voicechat.LOGGER.info("Stopping microphone thread", new Object[0]);
            this.micThread.close();
            this.micThread = null;
        }
    }

    public boolean toggleRecording() {
        return this.setRecording(this.recorder == null);
    }

    public boolean setRecording(boolean recording) {
        if (recording && !((Boolean)VoicechatClient.CLIENT_CONFIG.useNatives.get()).booleanValue()) {
            Voicechat.LOGGER.warn("Tried to start a recording with natives being disabled", new Object[0]);
            return false;
        }
        if (recording == (this.recorder != null)) {
            return false;
        }
        V_772_m player = MinecraftClient.A_4115_X().Y_259_p;
        if (recording) {
            if (this.connection == null || !this.connection.getData().allowRecording()) {
                if (player != null) {
                    player.n_1700_B((x_282_a)new F_2904_S("message.voicechat.recording_disabled"), true);
                }
                return false;
            }
            this.recorder = AudioRecorder.create();
            if (player != null) {
                player.n_1700_B((x_282_a)new F_2904_S("message.voicechat.recording_started").n_1700_B(D_4024_W.P_1922_E), true);
            }
            return true;
        }
        AudioRecorder rec = this.recorder;
        this.recorder = null;
        if (player != null) {
            player.n_1700_B((x_282_a)new F_2904_S("message.voicechat.recording_stopped").n_1700_B(D_4024_W.P_1922_E), true);
        }
        rec.saveAndClose();
        return true;
    }

    @Nullable
    public MicThread getMicThread() {
        return this.micThread;
    }

    @Nullable
    public ClientVoicechatConnection getConnection() {
        return this.connection;
    }

    @Nullable
    public InitializationData getInitializationData() {
        return this.initializationData;
    }

    @Nullable
    public SoundManager getSoundManager() {
        return this.soundManager;
    }

    public TalkCache getTalkCache() {
        return this.talkCache;
    }

    @Nullable
    public AudioRecorder getRecorder() {
        return this.recorder;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public Map<UUID, AudioChannel> getAudioChannels() {
        return this.audioChannels;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean closeAudioChannel(UUID id) {
        Map<UUID, AudioChannel> map = this.audioChannels;
        synchronized (map) {
            boolean removed;
            boolean bl = removed = this.audioChannels.remove(id) != null;
            if (removed) {
                Voicechat.LOGGER.debug("Removed audio channel of {} due to disconnection from voice chat", id);
            }
            return removed;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void close() {
        Map<UUID, AudioChannel> map = this.audioChannels;
        synchronized (map) {
            Voicechat.LOGGER.info("Clearing audio channels", new Object[0]);
            this.audioChannels.forEach((uuid, audioChannel) -> audioChannel.closeAndKill());
            this.audioChannels.clear();
        }
        if (this.soundManager != null) {
            this.soundManager.close();
        }
        this.closeMicThread();
        if (this.connection != null) {
            this.connection.close();
            this.connection = null;
        }
        if (this.recorder != null) {
            AudioRecorder rec = this.recorder;
            this.recorder = null;
            rec.saveAndClose();
        }
    }
}


