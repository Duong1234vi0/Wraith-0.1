/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.maxhenkel.opus4j.OpusEncoder
 *  de.maxhenkel.opus4j.OpusEncoder$Application
 *  de.maxhenkel.opus4j.UnknownPlatformException
 */
package mods.voicechat.plugins.impl.opus;

import de.maxhenkel.opus4j.OpusEncoder;
import de.maxhenkel.opus4j.UnknownPlatformException;
import java.io.IOException;

public class NativeOpusEncoderImpl
extends OpusEncoder
implements mods.voicechat.api.opus.OpusEncoder {
    public NativeOpusEncoderImpl(int sampleRate, int channels, OpusEncoder.Application application) throws IOException, UnknownPlatformException {
        super(sampleRate, channels, application);
    }
}

