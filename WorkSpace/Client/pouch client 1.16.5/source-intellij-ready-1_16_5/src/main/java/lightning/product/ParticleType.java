/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.ParticleOptions;

public abstract class ParticleType<T extends ParticleOptions> {
    private final boolean n_1700_B;
    private final ParticleOptions.n_1700_B<T> J_1907_R;

    protected ParticleType(boolean alwaysShow, ParticleOptions.n_1700_B<T> deserializer) {
        this.n_1700_B = alwaysShow;
        this.J_1907_R = deserializer;
    }

    public boolean P_1922_E() {
        return this.n_1700_B;
    }

    public ParticleOptions.n_1700_B<T> u_1723_Y() {
        return this.J_1907_R;
    }

    public abstract Codec<T> J_1907_R();
}


