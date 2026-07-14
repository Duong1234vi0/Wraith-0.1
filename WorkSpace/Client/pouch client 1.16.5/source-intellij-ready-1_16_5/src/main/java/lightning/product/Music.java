/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lightning.product.SoundEvent;

public class Music {
    public static final Codec<Music> n_1700_B = RecordCodecBuilder.create(selectorInstance -> selectorInstance.group((App)SoundEvent.n_1700_B.fieldOf("sound").forGetter(selector -> selector.J_1907_R), (App)Codec.INT.fieldOf("min_delay").forGetter(selector -> selector.R_4764_Y), (App)Codec.INT.fieldOf("max_delay").forGetter(selector -> selector.G_564_y), (App)Codec.BOOL.fieldOf("replace_current_music").forGetter(selector -> selector.P_1922_E)).apply((Applicative)selectorInstance, Music::new));
    private final SoundEvent J_1907_R;
    private final int R_4764_Y;
    private final int G_564_y;
    private final boolean P_1922_E;

    public Music(SoundEvent soundEvent, int minDelay, int maxDelay, boolean replaceCurrentMusic) {
        this.J_1907_R = soundEvent;
        this.R_4764_Y = minDelay;
        this.G_564_y = maxDelay;
        this.P_1922_E = replaceCurrentMusic;
    }

    public SoundEvent n_1700_B() {
        return this.J_1907_R;
    }

    public int J_1907_R() {
        return this.R_4764_Y;
    }

    public int R_4764_Y() {
        return this.G_564_y;
    }

    public boolean G_564_y() {
        return this.P_1922_E;
    }
}


