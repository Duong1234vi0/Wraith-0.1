/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.api.events;

import java.util.UUID;
import javax.annotation.Nullable;
import mods.voicechat.api.Position;
import mods.voicechat.api.events.ClientEvent;

public interface ClientReceiveSoundEvent
extends ClientEvent {
    public UUID getId();

    public short[] getRawAudio();

    public void setRawAudio(@Nullable short[] var1);

    public static interface StaticSound
    extends ClientReceiveSoundEvent {
    }

    public static interface LocationalSound
    extends ClientReceiveSoundEvent {
        public Position getPosition();

        public float getDistance();
    }

    public static interface EntitySound
    extends ClientReceiveSoundEvent {
        public boolean isWhispering();

        public float getDistance();
    }
}

