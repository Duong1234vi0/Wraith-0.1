/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.maxhenkel.opus4j.OpusDecoder
 *  de.maxhenkel.opus4j.UnknownPlatformException
 */
package mods.voicechat.plugins.impl.opus;

import de.maxhenkel.opus4j.UnknownPlatformException;
import java.io.IOException;
import mods.voicechat.api.opus.OpusDecoder;

public class NativeOpusDecoderImpl
extends de.maxhenkel.opus4j.OpusDecoder
implements OpusDecoder {
    public NativeOpusDecoderImpl(int sampleRate, int channels) throws IOException, UnknownPlatformException {
        super(sampleRate, channels);
    }
}

