/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.g_2336_b;

public class SoundEvent {
    public static final Codec<SoundEvent> n_1700_B = g_2336_b.n_1700_B.xmap(SoundEvent::new, sound -> sound.J_1907_R);
    private final g_2336_b J_1907_R;

    public SoundEvent(g_2336_b name) {
        this.J_1907_R = name;
    }

    public g_2336_b n_1700_B() {
        return this.J_1907_R;
    }
}


