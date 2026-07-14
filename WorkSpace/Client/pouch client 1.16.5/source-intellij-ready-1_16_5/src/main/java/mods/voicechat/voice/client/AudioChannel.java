/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.voice.client;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;
import lightning.product.I_4817_s;
import lightning.product.N_4263_v;
import lightning.product.MinecraftClient;
import lightning.product.e_2866_D;
import lightning.product.j_3341_s;
import lightning.product.r_4811_B;
import mods.voicechat.Voicechat;
import mods.voicechat.VoicechatClient;
import mods.voicechat.api.opus.OpusDecoder;
import mods.voicechat.debug.VoicechatUncaughtExceptionHandler;
import mods.voicechat.integration.freecam.FreecamUtil;
import mods.voicechat.plugins.ClientPluginManager;
import mods.voicechat.plugins.impl.opus.OpusManager;
import mods.voicechat.voice.client.AudioPacketBuffer;
import mods.voicechat.voice.client.AudioRecorder;
import mods.voicechat.voice.client.ClientManager;
import mods.voicechat.voice.client.ClientVoicechat;
import mods.voicechat.voice.client.InitializationData;
import mods.voicechat.voice.client.PositionalAudioUtils;
import mods.voicechat.voice.client.speaker.Speaker;
import mods.voicechat.voice.client.speaker.SpeakerManager;
import mods.voicechat.voice.common.GroupSoundPacket;
import mods.voicechat.voice.common.LocationSoundPacket;
import mods.voicechat.voice.common.PlayerSoundPacket;
import mods.voicechat.voice.common.SoundPacket;
import mods.voicechat.voice.common.Utils;

public class AudioChannel
extends Thread {
    private final MinecraftClient minecraft;
    private final ClientVoicechat client;
    private final InitializationData initializationData;
    private final UUID uuid;
    private final BlockingQueue<SoundPacket<?>> queue;
    private final AudioPacketBuffer packetBuffer;
    private long lastPacketTime;
    private Speaker speaker;
    private boolean stopped;
    private final OpusDecoder decoder;
    private long lastSequenceNumber;
    private long lostPackets;

    public AudioChannel(ClientVoicechat client, InitializationData initializationData, UUID uuid) {
        this.client = client;
        this.initializationData = initializationData;
        this.uuid = uuid;
        this.queue = new LinkedBlockingQueue();
        this.packetBuffer = new AudioPacketBuffer((Integer)VoicechatClient.CLIENT_CONFIG.audioPacketThreshold.get());
        this.lastPacketTime = System.currentTimeMillis();
        this.stopped = false;
        this.decoder = OpusManager.createDecoder();
        this.lastSequenceNumber = -1L;
        this.minecraft = MinecraftClient.A_4115_X();
        this.setDaemon(true);
        this.setName("AudioChannelThread-" + uuid.toString());
        this.setUncaughtExceptionHandler(new VoicechatUncaughtExceptionHandler());
        Voicechat.LOGGER.info("Creating audio channel for {}", uuid);
    }

    public boolean canKill() {
        return System.currentTimeMillis() - this.lastPacketTime > 30000L;
    }

    public void closeAndKill() {
        Voicechat.LOGGER.info("Closing audio channel for {}", this.uuid);
        this.stopped = true;
        this.queue.clear();
        if (Thread.currentThread() == this) {
            return;
        }
        this.interrupt();
        try {
            this.join();
        }
        catch (InterruptedException e) {
            Voicechat.LOGGER.error("Interrupted while waiting for audio channel to close", e);
        }
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public void addToQueue(SoundPacket<?> p) {
        this.queue.add(p);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void run() {
        block25: {
            try {
                if (this.client.getSoundManager() == null) {
                    throw new IllegalStateException("Started audio channel without sound manager");
                }
                this.speaker = SpeakerManager.createSpeaker(this.client.getSoundManager(), this.uuid);
                while (!this.stopped) {
                    if (ClientManager.getPlayerStateManager().isDisabled()) {
                        this.closeAndKill();
                        if (this.speaker != null) {
                            this.flushRecording();
                            this.speaker.close();
                        }
                        this.decoder.close();
                        break block25;
                    }
                    SoundPacket<?> packet = this.packetBuffer.poll(this.queue);
                    if (packet == null) continue;
                    this.lastPacketTime = System.currentTimeMillis();
                    if (!packet.isFromClientAudioChannel() && this.lastSequenceNumber >= 0L && packet.getSequenceNumber() <= this.lastSequenceNumber || this.minecraft.Y_601_j == null || this.minecraft.Y_259_p == null) continue;
                    if (packet.getData().length == 0) {
                        if (packet instanceof PlayerSoundPacket) {
                            PlayerSoundPacket playerSoundPacket = (PlayerSoundPacket)packet;
                            ClientPluginManager.instance().onReceiveEntityClientSound(this.uuid, new short[0], playerSoundPacket.isWhispering(), playerSoundPacket.getDistance());
                        } else if (packet instanceof LocationSoundPacket) {
                            LocationSoundPacket locationSoundPacket = (LocationSoundPacket)packet;
                            ClientPluginManager.instance().onReceiveLocationalClientSound(this.uuid, new short[0], locationSoundPacket.getLocation(), locationSoundPacket.getDistance());
                        } else if (packet instanceof GroupSoundPacket) {
                            ClientPluginManager.instance().onReceiveStaticClientSound(this.uuid, new short[0]);
                        }
                        this.lastSequenceNumber = -1L;
                        this.packetBuffer.clear();
                        this.flushRecording();
                        this.decoder.resetState();
                        continue;
                    }
                    if (!packet.isFromClientAudioChannel() && this.lastSequenceNumber >= 0L) {
                        int packetsToCompensate = (int)(packet.getSequenceNumber() - (this.lastSequenceNumber + 1L));
                        if (packetsToCompensate > 0) {
                            Voicechat.LOGGER.debug("Compensating {}/{} packets ", packetsToCompensate >= 4 ? 0 : packetsToCompensate, packetsToCompensate);
                        }
                        if (packetsToCompensate <= 4) {
                            this.lostPackets += (long)packetsToCompensate;
                            for (int i = 0; i < packetsToCompensate; ++i) {
                                this.writeToSpeaker(packet, this.decoder.decode(null));
                            }
                        } else {
                            Voicechat.LOGGER.debug("Skipping compensation for {} packets", packetsToCompensate);
                        }
                    }
                    this.lastSequenceNumber = packet.getSequenceNumber();
                    short[] decodedAudio = packet.isFromClientAudioChannel() ? Utils.bytesToShorts(packet.getData()) : this.decoder.decode(packet.getData());
                    this.writeToSpeaker(packet, decodedAudio);
                }
                if (this.speaker != null) {
                    this.flushRecording();
                    this.speaker.close();
                }
                this.decoder.close();
            }
            catch (InterruptedException packet) {
                if (this.speaker != null) {
                    this.flushRecording();
                    this.speaker.close();
                }
                this.decoder.close();
                Voicechat.LOGGER.info("Closed audio channel for {}", this.uuid);
                return;
            }
            catch (Throwable e) {
                try {
                    Voicechat.LOGGER.error("Audio channel error", e);
                    if (this.speaker != null) {
                        this.flushRecording();
                        this.speaker.close();
                    }
                    this.decoder.close();
                }
                catch (Throwable throwable) {
                    if (this.speaker != null) {
                        this.flushRecording();
                        this.speaker.close();
                    }
                    this.decoder.close();
                    Voicechat.LOGGER.info("Closed audio channel for {}", this.uuid);
                    throw throwable;
                }
                Voicechat.LOGGER.info("Closed audio channel for {}", this.uuid);
                return;
            }
            Voicechat.LOGGER.info("Closed audio channel for {}", this.uuid);
            return;
        }
        Voicechat.LOGGER.info("Closed audio channel for {}", this.uuid);
    }

    private void flushRecording() {
        AudioRecorder recorder = this.client.getRecorder();
        if (recorder == null) {
            return;
        }
        recorder.flushChunkThreaded(this.uuid);
    }

    private void writeToSpeaker(SoundPacket<?> packet, short[] monoData) {
        float channelVolume = VoicechatClient.USERNAME_CACHE.has(this.uuid) ? (float)VoicechatClient.PLAYER_VOLUME_CONFIG.getVolume(this.uuid) : (packet.getCategory() != null ? (float)VoicechatClient.CATEGORY_VOLUME_CONFIG.getVolume(packet.getCategory()) : (float)VoicechatClient.PLAYER_VOLUME_CONFIG.getVolume(j_3341_s.J_1907_R));
        float volume = ((Double)VoicechatClient.CLIENT_CONFIG.voiceChatVolume.get()).floatValue() * channelVolume;
        if (packet instanceof GroupSoundPacket) {
            short[] processedMonoData = ClientPluginManager.instance().onReceiveStaticClientSound(this.uuid, monoData);
            this.speaker.play(processedMonoData, volume, packet.getCategory());
            this.client.getTalkCache().updateTalking(this.uuid, false);
            this.appendRecording(() -> PositionalAudioUtils.convertToStereo(processedMonoData));
        } else if (packet instanceof PlayerSoundPacket) {
            PlayerSoundPacket soundPacket = (PlayerSoundPacket)packet;
            N_4263_v entity = this.minecraft.Y_601_j.n_1700_B(this.uuid);
            if (entity == null) {
                e_2866_D position = this.minecraft.s_956_w.M_588_G().J_1907_R();
                I_4817_s box = new I_4817_s(position.J_1907_R - (double)soundPacket.getDistance() - 1.0, position.R_4764_Y - (double)soundPacket.getDistance() - 1.0, position.G_564_y - (double)soundPacket.getDistance() - 1.0, position.J_1907_R + (double)soundPacket.getDistance() + 1.0, position.R_4764_Y + (double)soundPacket.getDistance() + 1.0, position.G_564_y + (double)soundPacket.getDistance() + 1.0);
                entity = this.minecraft.Y_601_j.J_1907_R((N_4263_v)null, box, e -> e.w_2705_t().equals(this.uuid)).stream().findAny().orElse(null);
                if (entity == null) {
                    return;
                }
            }
            if (entity == this.minecraft.C_2741_M) {
                short[] processedMonoData = ClientPluginManager.instance().onReceiveStaticClientSound(this.uuid, monoData);
                this.speaker.play(processedMonoData, volume, soundPacket.getCategory());
                this.client.getTalkCache().updateTalking(this.uuid, soundPacket.isWhispering());
                this.appendRecording(() -> PositionalAudioUtils.convertToStereo(processedMonoData));
                return;
            }
            float deathVolume = 1.0f;
            if (entity instanceof r_4811_B) {
                deathVolume = Math.min(Math.max((20.0f - (float)((r_4811_B)entity).O_2151_c) / 20.0f, 0.0f), 1.0f);
            }
            volume *= deathVolume;
            e_2866_D pos = entity.u_2550_I(1.0f);
            short[] processedMonoData = ClientPluginManager.instance().onReceiveEntityClientSound(this.uuid, monoData, soundPacket.isWhispering(), soundPacket.getDistance());
            if (FreecamUtil.getDistanceTo(pos) > (double)soundPacket.getDistance() + 1.0) {
                return;
            }
            float distanceVolume = FreecamUtil.getDistanceVolume(soundPacket.getDistance(), pos);
            if (FreecamUtil.isFreecamEnabled()) {
                this.speaker.play(processedMonoData, volume *= distanceVolume, soundPacket.getCategory());
                if (distanceVolume > 0.0f) {
                    this.client.getTalkCache().updateTalking(this.uuid, soundPacket.isWhispering());
                }
                float recordingVolume = volume;
                this.appendRecording(() -> PositionalAudioUtils.convertToStereo(processedMonoData, recordingVolume));
                return;
            }
            this.speaker.play(processedMonoData, volume, pos, soundPacket.getCategory(), soundPacket.getDistance());
            if (distanceVolume > 0.0f) {
                this.client.getTalkCache().updateTalking(this.uuid, soundPacket.isWhispering());
            }
            float recordingVolume = deathVolume;
            this.appendRecording(() -> PositionalAudioUtils.convertToStereoForRecording(soundPacket.getDistance(), pos, processedMonoData, recordingVolume));
        } else if (packet instanceof LocationSoundPacket) {
            LocationSoundPacket p = (LocationSoundPacket)packet;
            short[] processedMonoData = ClientPluginManager.instance().onReceiveLocationalClientSound(this.uuid, monoData, p.getLocation(), p.getDistance());
            if (FreecamUtil.getDistanceTo(p.getLocation()) > (double)p.getDistance() + 1.0) {
                return;
            }
            this.speaker.play(processedMonoData, volume, p.getLocation(), p.getCategory(), p.getDistance());
            this.client.getTalkCache().updateTalking(this.uuid, false);
            this.appendRecording(() -> PositionalAudioUtils.convertToStereoForRecording(p.getDistance(), p.getLocation(), processedMonoData));
        }
    }

    private void appendRecording(Supplier<short[]> stereo) {
        if (this.client.getRecorder() != null) {
            try {
                this.client.getRecorder().appendChunk(this.uuid, System.currentTimeMillis(), stereo.get());
            }
            catch (IOException e) {
                Voicechat.LOGGER.error("Failed to record audio", e);
                this.client.setRecording(false);
            }
        }
    }

    public boolean isClosed() {
        return this.stopped;
    }

    public BlockingQueue<SoundPacket<?>> getQueue() {
        return this.queue;
    }

    public Speaker getSpeaker() {
        return this.speaker;
    }

    public AudioPacketBuffer getPacketBuffer() {
        return this.packetBuffer;
    }

    public long getLostPackets() {
        return this.lostPackets;
    }
}


