/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins.impl;

import java.io.InputStream;
import java.io.OutputStream;
import javax.annotation.Nullable;
import javax.sound.sampled.AudioFormat;
import lightning.product.B_4088_l;
import lightning.product.N_4263_v;
import lightning.product.e_3591_l;
import mods.voicechat.api.Entity;
import mods.voicechat.api.Position;
import mods.voicechat.api.ServerLevel;
import mods.voicechat.api.ServerPlayer;
import mods.voicechat.api.VoicechatApi;
import mods.voicechat.api.VolumeCategory;
import mods.voicechat.api.audio.AudioConverter;
import mods.voicechat.api.mp3.Mp3Decoder;
import mods.voicechat.api.mp3.Mp3Encoder;
import mods.voicechat.api.opus.OpusDecoder;
import mods.voicechat.api.opus.OpusEncoder;
import mods.voicechat.api.opus.OpusEncoderMode;
import mods.voicechat.plugins.impl.EntityImpl;
import mods.voicechat.plugins.impl.PositionImpl;
import mods.voicechat.plugins.impl.ServerLevelImpl;
import mods.voicechat.plugins.impl.ServerPlayerImpl;
import mods.voicechat.plugins.impl.VolumeCategoryImpl;
import mods.voicechat.plugins.impl.audio.AudioConverterImpl;
import mods.voicechat.plugins.impl.mp3.Mp3DecoderImpl;
import mods.voicechat.plugins.impl.mp3.Mp3EncoderImpl;
import mods.voicechat.plugins.impl.opus.OpusManager;
import mods.voicechat.voice.common.Utils;

public abstract class VoicechatApiImpl
implements VoicechatApi {
    private static final AudioConverter AUDIO_CONVERTER = new AudioConverterImpl();

    @Override
    public OpusEncoder createEncoder() {
        return OpusManager.createEncoder(null);
    }

    @Override
    public OpusEncoder createEncoder(OpusEncoderMode mode) {
        return OpusManager.createEncoder(mode);
    }

    @Override
    @Nullable
    public Mp3Encoder createMp3Encoder(AudioFormat audioFormat, int bitrate, int quality, OutputStream outputStream) {
        return Mp3EncoderImpl.createEncoder(audioFormat, bitrate, quality, outputStream);
    }

    @Override
    @Nullable
    public Mp3Decoder createMp3Decoder(InputStream inputStream) {
        return Mp3DecoderImpl.createDecoder(inputStream);
    }

    @Override
    public OpusDecoder createDecoder() {
        return OpusManager.createDecoder();
    }

    @Override
    public AudioConverter getAudioConverter() {
        return AUDIO_CONVERTER;
    }

    @Override
    public Entity fromEntity(Object entity) {
        if (entity instanceof N_4263_v) {
            N_4263_v e = (N_4263_v)entity;
            return new EntityImpl(e);
        }
        throw new IllegalArgumentException("entity is not an instance of Entity");
    }

    @Override
    public ServerLevel fromServerLevel(Object serverLevel) {
        if (serverLevel instanceof e_3591_l) {
            e_3591_l l = (e_3591_l)serverLevel;
            return new ServerLevelImpl(l);
        }
        throw new IllegalArgumentException("serverLevel is not an instance of ServerLevel");
    }

    @Override
    public ServerPlayer fromServerPlayer(Object serverPlayer) {
        if (serverPlayer instanceof B_4088_l) {
            B_4088_l p = (B_4088_l)serverPlayer;
            return new ServerPlayerImpl(p);
        }
        throw new IllegalArgumentException("serverPlayer is not an instance of ServerPlayer");
    }

    @Override
    public Position createPosition(double x, double y, double z) {
        return new PositionImpl(x, y, z);
    }

    @Override
    public VolumeCategory.Builder volumeCategoryBuilder() {
        return new VolumeCategoryImpl.BuilderImpl();
    }

    @Override
    public double getVoiceChatDistance() {
        return Utils.getDefaultDistanceServer();
    }
}

