/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.maxhenkel.rnnoise4j.Denoiser
 *  de.maxhenkel.rnnoise4j.UnknownPlatformException
 *  javax.annotation.Nullable
 */
package mods.voicechat.voice.client;

import de.maxhenkel.rnnoise4j.UnknownPlatformException;
import java.io.IOException;
import javax.annotation.Nullable;
import mods.voicechat.Voicechat;
import mods.voicechat.intercompatibility.CrossSideManager;
import mods.voicechat.voice.common.Utils;

public class Denoiser
extends de.maxhenkel.rnnoise4j.Denoiser {
    private Denoiser() throws IOException, UnknownPlatformException {
    }

    @Nullable
    public static Denoiser createDenoiser() {
        if (!CrossSideManager.get().useNatives()) {
            return null;
        }
        return Utils.createSafe(Denoiser::new, e -> Voicechat.LOGGER.warn("Failed to load RNNoise", e));
    }
}

