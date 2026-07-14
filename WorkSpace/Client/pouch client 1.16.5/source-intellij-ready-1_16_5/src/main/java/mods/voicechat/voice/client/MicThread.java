/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.voice.client;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;
import lightning.product.MinecraftClient;
import mods.voicechat.Voicechat;
import mods.voicechat.VoicechatClient;
import mods.voicechat.api.opus.OpusEncoder;
import mods.voicechat.config.ServerConfig;
import mods.voicechat.debug.VoicechatUncaughtExceptionHandler;
import mods.voicechat.plugins.ClientPluginManager;
import mods.voicechat.plugins.impl.opus.OpusManager;
import mods.voicechat.voice.client.AudioRecorder;
import mods.voicechat.voice.client.ClientManager;
import mods.voicechat.voice.client.ClientVoicechat;
import mods.voicechat.voice.client.ClientVoicechatConnection;
import mods.voicechat.voice.client.Denoiser;
import mods.voicechat.voice.client.MicActivator;
import mods.voicechat.voice.client.MicrophoneActivationType;
import mods.voicechat.voice.client.MicrophoneException;
import mods.voicechat.voice.client.PositionalAudioUtils;
import mods.voicechat.voice.client.VolumeManager;
import mods.voicechat.voice.client.microphone.Microphone;
import mods.voicechat.voice.client.microphone.MicrophoneManager;
import mods.voicechat.voice.common.MicPacket;
import mods.voicechat.voice.common.NetworkMessage;
import mods.voicechat.voice.common.Utils;

public class MicThread
extends Thread {
    @Nullable
    private final ClientVoicechat client;
    @Nullable
    private final ClientVoicechatConnection connection;
    @Nullable
    private final Microphone mic;
    private final VolumeManager volumeManager;
    private boolean running;
    private boolean microphoneLocked;
    private boolean wasWhispering;
    private final OpusEncoder encoder;
    @Nullable
    private Denoiser denoiser;
    private final MicActivator micActivator = new MicActivator();
    private volatile boolean wasPTT;
    private boolean hasSentAudio;
    private final AtomicLong sequenceNumber = new AtomicLong();
    private volatile boolean stopPacketSent = true;

    public MicThread(@Nullable ClientVoicechat client, @Nullable ClientVoicechatConnection connection) throws MicrophoneException {
        this.client = client;
        this.connection = connection;
        this.running = true;
        this.encoder = OpusManager.createEncoder(connection == null ? ServerConfig.Codec.VOIP.getMode() : connection.getData().getCodec().getMode());
        this.denoiser = Denoiser.createDenoiser();
        if (this.denoiser == null) {
            Voicechat.LOGGER.warn("Denoiser not available", new Object[0]);
        }
        this.volumeManager = new VolumeManager();
        this.setDaemon(true);
        this.setName("MicrophoneThread");
        this.setUncaughtExceptionHandler(new VoicechatUncaughtExceptionHandler());
        this.mic = MicrophoneManager.createMicrophone();
    }

    @Override
    public void run() {
        while (this.running) {
            if (this.connection != null) {
                this.connection.checkTimeout();
                if (!this.running) break;
            }
            if (this.microphoneLocked || ClientManager.getPlayerStateManager().isDisabled()) {
                this.micActivator.stopActivating();
                this.wasPTT = false;
                this.wasWhispering = false;
                this.flushIfNeeded();
                if (!this.microphoneLocked && ClientManager.getPlayerStateManager().isDisabled()) {
                    if (this.mic.isStarted()) {
                        this.mic.stop();
                    }
                    if (this.denoiser != null) {
                        this.denoiser.close();
                    }
                }
                Utils.sleep(10);
                continue;
            }
            short[] audio = this.pollMic();
            if (audio == null) continue;
            boolean sentAudio = false;
            MicrophoneActivationType type = (MicrophoneActivationType)((Object)VoicechatClient.CLIENT_CONFIG.microphoneActivationType.get());
            if (type.equals((Object)MicrophoneActivationType.PTT)) {
                sentAudio = this.ptt(audio);
            } else if (type.equals((Object)MicrophoneActivationType.VOICE)) {
                sentAudio = this.voice(audio);
            }
            if (sentAudio) continue;
            this.sendAudio(null, ClientManager.getPttKeyHandler().isWhisperDown());
        }
    }

    @Nullable
    public short[] pollMic() {
        if (!this.mic.isStarted()) {
            this.mic.start();
        }
        if (this.denoiser != null && this.denoiser.isClosed()) {
            this.denoiser = Denoiser.createDenoiser();
        }
        if (this.mic.available() < 960) {
            Utils.sleep(5);
            return null;
        }
        short[] buff = this.mic.read();
        this.volumeManager.adjustVolume(buff, (Double)VoicechatClient.CLIENT_CONFIG.microphoneGain.get());
        return this.denoiseIfEnabled(buff);
    }

    private boolean voice(short[] audio) {
        this.wasPTT = false;
        if (ClientManager.getPlayerStateManager().isMuted()) {
            this.micActivator.stopActivating();
            this.wasWhispering = false;
            return false;
        }
        this.wasWhispering = ClientManager.getPttKeyHandler().isWhisperDown();
        return this.micActivator.push(audio, a -> this.sendAudio((short[])a, this.wasWhispering));
    }

    private boolean ptt(short[] audio) {
        this.micActivator.stopActivating();
        if (!ClientManager.getPttKeyHandler().isAnyDown()) {
            if (this.wasPTT) {
                this.wasPTT = false;
                this.wasWhispering = false;
            }
            return false;
        }
        this.wasPTT = true;
        this.wasWhispering = ClientManager.getPttKeyHandler().isWhisperDown();
        this.sendAudio(audio, this.wasWhispering);
        return true;
    }

    public short[] denoiseIfEnabled(short[] audio) {
        if (this.denoiser != null && ((Boolean)VoicechatClient.CLIENT_CONFIG.denoiser.get()).booleanValue()) {
            return this.denoiser.denoise(audio);
        }
        return audio;
    }

    private void flush() {
        this.sendStopPacket();
        if (!this.encoder.isClosed()) {
            this.encoder.resetState();
        }
        if (this.client == null) {
            return;
        }
        AudioRecorder recorder = this.client.getRecorder();
        if (recorder == null) {
            return;
        }
        recorder.flushChunkThreaded(MinecraftClient.A_4115_X().z_1737_N().P_1922_E().getId());
    }

    private void sendAudio(@Nullable short[] rawAudio, boolean whispering) {
        short[] mergedAudio = ClientPluginManager.instance().onMergeClientSound(rawAudio);
        if (mergedAudio == null) {
            this.flushIfNeeded();
            return;
        }
        short[] finalAudio = ClientPluginManager.instance().onClientSound(mergedAudio, whispering);
        if (finalAudio == null) {
            this.flushIfNeeded();
            return;
        }
        this.sendAudioPacket(finalAudio, whispering);
        this.hasSentAudio = true;
    }

    private void flushIfNeeded() {
        if (!this.hasSentAudio) {
            return;
        }
        this.flush();
        this.hasSentAudio = false;
    }

    public boolean isTalking() {
        return !this.microphoneLocked && (this.micActivator.isActivating() || this.wasPTT);
    }

    public boolean isWhispering() {
        return this.isTalking() && this.wasWhispering;
    }

    public void setMicrophoneLocked(boolean microphoneLocked) {
        this.microphoneLocked = microphoneLocked;
        this.micActivator.stopActivating();
        this.wasPTT = false;
    }

    public void close() {
        if (!this.running) {
            return;
        }
        this.running = false;
        if (Thread.currentThread() != this) {
            try {
                this.join(100L);
            }
            catch (InterruptedException e) {
                Voicechat.LOGGER.error("Interrupted while waiting for mic thread to close", e);
            }
        }
        if (this.mic != null) {
            this.mic.close();
        }
        this.encoder.close();
        if (this.denoiser != null) {
            this.denoiser.close();
        }
        this.flush();
    }

    private void sendAudioPacket(short[] audio, boolean whispering) {
        if (this.connection != null && this.connection.isInitialized()) {
            byte[] encoded = this.encoder.encode(audio);
            this.connection.sendToServer(new NetworkMessage(new MicPacket(encoded, whispering, this.sequenceNumber.getAndIncrement())));
            this.stopPacketSent = false;
        }
        try {
            if (this.client != null && this.client.getRecorder() != null) {
                this.client.getRecorder().appendChunk(MinecraftClient.A_4115_X().z_1737_N().P_1922_E().getId(), System.currentTimeMillis(), PositionalAudioUtils.convertToStereo(audio));
            }
        }
        catch (IOException e) {
            Voicechat.LOGGER.error("Failed to record audio", e);
            this.client.setRecording(false);
        }
    }

    private void sendStopPacket() {
        if (this.stopPacketSent) {
            return;
        }
        if (this.connection == null || !this.connection.isInitialized()) {
            return;
        }
        this.connection.sendToServer(new NetworkMessage(new MicPacket(new byte[0], false, this.sequenceNumber.getAndIncrement())));
        this.stopPacketSent = true;
    }
}


