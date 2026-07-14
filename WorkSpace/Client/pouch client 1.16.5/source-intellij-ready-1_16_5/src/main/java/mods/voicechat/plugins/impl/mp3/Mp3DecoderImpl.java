/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.maxhenkel.lame4j.Mp3Decoder
 *  de.maxhenkel.lame4j.ShortArrayBuffer
 *  de.maxhenkel.lame4j.UnknownPlatformException
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins.impl.mp3;

import de.maxhenkel.lame4j.Mp3Decoder;
import de.maxhenkel.lame4j.ShortArrayBuffer;
import de.maxhenkel.lame4j.UnknownPlatformException;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;
import javax.sound.sampled.AudioFormat;
import mods.voicechat.Voicechat;
import mods.voicechat.intercompatibility.CrossSideManager;
import mods.voicechat.voice.common.Utils;

public class Mp3DecoderImpl
implements mods.voicechat.api.mp3.Mp3Decoder {
    private final Mp3Decoder decoder;
    private IOException decodeError;
    @Nullable
    private short[] samples;
    @Nullable
    private AudioFormat audioFormat;

    public Mp3DecoderImpl(InputStream inputStream) throws IOException, UnknownPlatformException {
        this.decoder = new Mp3Decoder(inputStream);
    }

    private void decodeIfNecessary() throws IOException {
        if (this.decodeError != null) {
            throw this.decodeError;
        }
        try {
            if (this.samples == null) {
                short[] samples;
                ShortArrayBuffer sampleBuffer = new ShortArrayBuffer(2048);
                while ((samples = this.decoder.decodeNextFrame()) != null) {
                    sampleBuffer.writeShorts(samples);
                }
                this.samples = sampleBuffer.toShortArray();
                this.audioFormat = this.decoder.createAudioFormat();
            }
        }
        catch (IOException e) {
            this.decodeError = e;
            throw e;
        }
    }

    @Override
    public short[] decode() throws IOException {
        this.decodeIfNecessary();
        return this.samples;
    }

    @Override
    public AudioFormat getAudioFormat() throws IOException {
        this.decodeIfNecessary();
        return this.audioFormat;
    }

    @Override
    public int getBitrate() throws IOException {
        this.decodeIfNecessary();
        return this.decoder.getBitRate();
    }

    @Nullable
    public static mods.voicechat.api.mp3.Mp3Decoder createDecoder(InputStream inputStream) {
        if (!CrossSideManager.get().useNatives()) {
            return null;
        }
        return Utils.createSafe(() -> new Mp3DecoderImpl(inputStream), e -> Voicechat.LOGGER.error("Failed to load mp3 decoder", e));
    }
}

