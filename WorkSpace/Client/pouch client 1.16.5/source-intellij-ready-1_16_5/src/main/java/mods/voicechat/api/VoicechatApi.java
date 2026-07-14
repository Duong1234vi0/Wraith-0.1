/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.api;

import java.io.InputStream;
import java.io.OutputStream;
import javax.annotation.Nullable;
import javax.sound.sampled.AudioFormat;
import mods.voicechat.api.Entity;
import mods.voicechat.api.Position;
import mods.voicechat.api.ServerLevel;
import mods.voicechat.api.ServerPlayer;
import mods.voicechat.api.VolumeCategory;
import mods.voicechat.api.audio.AudioConverter;
import mods.voicechat.api.mp3.Mp3Decoder;
import mods.voicechat.api.mp3.Mp3Encoder;
import mods.voicechat.api.opus.OpusDecoder;
import mods.voicechat.api.opus.OpusEncoder;
import mods.voicechat.api.opus.OpusEncoderMode;

public interface VoicechatApi {
    public OpusEncoder createEncoder();

    public OpusEncoder createEncoder(OpusEncoderMode var1);

    public OpusDecoder createDecoder();

    @Nullable
    public Mp3Encoder createMp3Encoder(AudioFormat var1, int var2, int var3, OutputStream var4);

    @Nullable
    public Mp3Decoder createMp3Decoder(InputStream var1);

    public AudioConverter getAudioConverter();

    public Entity fromEntity(Object var1);

    public ServerLevel fromServerLevel(Object var1);

    public ServerPlayer fromServerPlayer(Object var1);

    public Position createPosition(double var1, double var3, double var5);

    public VolumeCategory.Builder volumeCategoryBuilder();

    public double getVoiceChatDistance();
}

