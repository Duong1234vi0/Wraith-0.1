/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.lwjgl.openal.AL11
 */
package mods.voicechat.voice.client.speaker;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import lightning.product.M_1336_P;
import lightning.product.MinecraftClient;
import lightning.product.e_2866_D;
import lightning.product.h_3572_K;
import mods.voicechat.Voicechat;
import mods.voicechat.VoicechatClient;
import mods.voicechat.api.events.OpenALSoundEvent;
import mods.voicechat.plugins.ClientPluginManager;
import mods.voicechat.voice.client.ClientUtils;
import mods.voicechat.voice.client.SoundManager;
import mods.voicechat.voice.client.speaker.Speaker;
import mods.voicechat.voice.client.speaker.SpeakerException;
import mods.voicechat.voice.common.NamedThreadPoolFactory;
import org.lwjgl.openal.AL11;

public abstract class ALSpeakerBase
implements Speaker {
    protected final MinecraftClient mc = MinecraftClient.A_4115_X();
    protected final SoundManager soundManager;
    protected final int sampleRate;
    protected int bufferSize;
    protected int bufferSampleSize;
    protected int source;
    protected volatile int bufferIndex;
    protected final int[] buffers;
    protected final ExecutorService executor;
    @Nullable
    protected UUID audioChannelId;

    public ALSpeakerBase(SoundManager soundManager, int sampleRate, int bufferSize, @Nullable UUID audioChannelId) {
        this.soundManager = soundManager;
        this.sampleRate = sampleRate;
        this.bufferSize = bufferSize;
        this.bufferSampleSize = bufferSize;
        this.audioChannelId = audioChannelId;
        this.buffers = new int[32];
        Object threadName = audioChannelId == null ? "SoundSourceThread" : "SoundSourceThread-" + String.valueOf(audioChannelId);
        this.executor = Executors.newSingleThreadExecutor(NamedThreadPoolFactory.create((String)threadName));
    }

    @Override
    public void open() throws SpeakerException {
        this.runInContext(this::openSync);
    }

    protected void openSync() {
        if (this.hasValidSourceSync()) {
            return;
        }
        this.source = AL11.alGenSources();
        SoundManager.checkAlError();
        AL11.alSourcei((int)this.source, (int)4103, (int)0);
        SoundManager.checkAlError();
        AL11.alDistanceModel((int)53251);
        SoundManager.checkAlError();
        AL11.alSourcef((int)this.source, (int)4131, (float)ClientUtils.getDefaultDistanceClient());
        SoundManager.checkAlError();
        AL11.alSourcef((int)this.source, (int)4128, (float)0.0f);
        SoundManager.checkAlError();
        AL11.alGenBuffers((int[])this.buffers);
        SoundManager.checkAlError();
    }

    @Override
    public void play(short[] data, float volume, @Nullable e_2866_D position, @Nullable String category, float maxDistance) {
        this.runInContext(() -> {
            this.removeProcessedBuffersSync();
            boolean stopped = this.isStoppedSync();
            if (stopped) {
                Voicechat.LOGGER.debug("Filling playback buffer {}", this.audioChannelId);
                for (int i = 0; i < this.getBufferSize(); ++i) {
                    this.writeSync(new short[this.bufferSize], 1.0f, position, category, maxDistance);
                }
            }
            this.writeSync(data, volume, position, category, maxDistance);
            if (stopped) {
                AL11.alSourcePlay((int)this.source);
                SoundManager.checkAlError();
            }
        });
    }

    protected boolean isStoppedSync() {
        return this.getStateSync() == 4113 || this.getStateSync() == 4116 || this.getQueuedBuffersSync() <= 0;
    }

    protected int getBufferSize() {
        return (Integer)VoicechatClient.CLIENT_CONFIG.outputBufferSize.get();
    }

    protected void writeSync(short[] data, float volume, @Nullable e_2866_D position, @Nullable String category, float maxDistance) {
        ClientPluginManager.instance().onALSound(this.source, this.audioChannelId, position, category, OpenALSoundEvent.Pre.class);
        this.setPositionSync(position, maxDistance);
        ClientPluginManager.instance().onALSound(this.source, this.audioChannelId, position, category, OpenALSoundEvent.class);
        AL11.alSourcef((int)this.source, (int)4110, (float)6.0f);
        SoundManager.checkAlError();
        AL11.alSourcef((int)this.source, (int)4106, (float)this.getVolume(volume, position, maxDistance));
        SoundManager.checkAlError();
        AL11.alListenerf((int)4106, (float)1.0f);
        SoundManager.checkAlError();
        int queuedBuffers = this.getQueuedBuffersSync();
        if (queuedBuffers >= this.buffers.length) {
            Voicechat.LOGGER.warn("Full playback buffer: {}/{}", queuedBuffers, this.buffers.length);
            int sampleOffset = AL11.alGetSourcei((int)this.source, (int)4133);
            SoundManager.checkAlError();
            int buffersToSkip = queuedBuffers - this.getBufferSize();
            AL11.alSourcei((int)this.source, (int)4133, (int)(sampleOffset + buffersToSkip * this.bufferSampleSize));
            SoundManager.checkAlError();
            this.removeProcessedBuffersSync();
        }
        AL11.alBufferData((int)this.buffers[this.bufferIndex], (int)this.getFormat(), (short[])this.convert(data, position), (int)this.sampleRate);
        SoundManager.checkAlError();
        AL11.alSourceQueueBuffers((int)this.source, (int)this.buffers[this.bufferIndex]);
        SoundManager.checkAlError();
        this.bufferIndex = (this.bufferIndex + 1) % this.buffers.length;
        ClientPluginManager.instance().onALSound(this.source, this.audioChannelId, position, category, OpenALSoundEvent.Post.class);
    }

    protected float getVolume(float volume, @Nullable e_2866_D position, float maxDistance) {
        return volume;
    }

    protected void linearAttenuation(float maxDistance) {
        AL11.alDistanceModel((int)53251);
        SoundManager.checkAlError();
        AL11.alSourcef((int)this.source, (int)4131, (float)maxDistance);
        SoundManager.checkAlError();
        AL11.alSourcef((int)this.source, (int)4128, (float)(maxDistance / 2.0f));
        SoundManager.checkAlError();
    }

    protected abstract int getFormat();

    protected short[] convert(short[] data, @Nullable e_2866_D position) {
        return data;
    }

    protected void setPositionSync(@Nullable e_2866_D soundPos, float maxDistance) {
        h_3572_K camera = this.mc.s_956_w.M_588_G();
        e_2866_D position = camera.J_1907_R();
        M_1336_P look = camera.P_4830_p();
        M_1336_P up = camera.h_1847_R();
        AL11.alListener3f((int)4100, (float)((float)position.J_1907_R), (float)((float)position.R_4764_Y), (float)((float)position.G_564_y));
        SoundManager.checkAlError();
        AL11.alListenerfv((int)4111, (float[])new float[]{look.n_1700_B(), look.J_1907_R(), look.R_4764_Y(), up.n_1700_B(), up.J_1907_R(), up.R_4764_Y()});
        SoundManager.checkAlError();
        if (soundPos != null) {
            this.linearAttenuation(maxDistance);
            AL11.alSourcei((int)this.source, (int)514, (int)0);
            SoundManager.checkAlError();
            AL11.alSource3f((int)this.source, (int)4100, (float)((float)soundPos.J_1907_R), (float)((float)soundPos.R_4764_Y), (float)((float)soundPos.G_564_y));
            SoundManager.checkAlError();
        } else {
            this.linearAttenuation(48.0f);
            AL11.alSourcei((int)this.source, (int)514, (int)1);
            SoundManager.checkAlError();
            AL11.alSource3f((int)this.source, (int)4100, (float)0.0f, (float)0.0f, (float)0.0f);
            SoundManager.checkAlError();
        }
    }

    @Override
    public void close() {
        this.runInContext(this::closeSync);
    }

    protected void closeSync() {
        if (this.hasValidSourceSync()) {
            if (this.getStateSync() == 4114) {
                AL11.alSourceStop((int)this.source);
                SoundManager.checkAlError();
            }
            AL11.alDeleteSources((int)this.source);
            SoundManager.checkAlError();
            AL11.alDeleteBuffers((int[])this.buffers);
            SoundManager.checkAlError();
        }
        this.source = 0;
        this.executor.shutdown();
    }

    public void checkBufferEmpty(Runnable onEmpty) {
        this.runInContext(() -> {
            if (this.getStateSync() == 4116 || this.getQueuedBuffersSync() <= 0) {
                onEmpty.run();
            }
        });
    }

    protected void removeProcessedBuffersSync() {
        int processed = AL11.alGetSourcei((int)this.source, (int)4118);
        SoundManager.checkAlError();
        for (int i = 0; i < processed; ++i) {
            AL11.alSourceUnqueueBuffers((int)this.source);
            SoundManager.checkAlError();
        }
    }

    protected int getStateSync() {
        int state = AL11.alGetSourcei((int)this.source, (int)4112);
        SoundManager.checkAlError();
        return state;
    }

    protected int getQueuedBuffersSync() {
        int buffers = AL11.alGetSourcei((int)this.source, (int)4117);
        SoundManager.checkAlError();
        return buffers;
    }

    protected boolean hasValidSourceSync() {
        boolean validSource = AL11.alIsSource((int)this.source);
        SoundManager.checkAlError();
        return validSource;
    }

    public void runInContext(Runnable runnable) {
        if (this.executor.isShutdown()) {
            return;
        }
        this.soundManager.runInContext(this.executor, runnable);
    }

    public void fetchQueuedBuffersAsync(Consumer<Integer> supplier) {
        this.runInContext(() -> {
            if (this.isStoppedSync()) {
                supplier.accept(-1);
                return;
            }
            supplier.accept(this.getQueuedBuffersSync());
        });
    }
}


