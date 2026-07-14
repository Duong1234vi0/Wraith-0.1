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
import java.util.Objects;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.f_2392_k;

public final class F_427_K {
    public static final Codec<F_427_K> n_1700_B = RecordCodecBuilder.create(builderInstance -> builderInstance.group((App)b_4507_u.P_1922_E.fieldOf("dimension").forGetter(F_427_K::n_1700_B), (App)c_1514_x.CODEC.fieldOf("pos").forGetter(F_427_K::J_1907_R)).apply((Applicative)builderInstance, F_427_K::n_1700_B));
    private final f_2392_k<b_4507_u> J_1907_R;
    private final c_1514_x R_4764_Y;

    private F_427_K(f_2392_k<b_4507_u> dimension, c_1514_x pos) {
        this.J_1907_R = dimension;
        this.R_4764_Y = pos;
    }

    public static F_427_K n_1700_B(f_2392_k<b_4507_u> dimension, c_1514_x pos) {
        return new F_427_K(dimension, pos);
    }

    public f_2392_k<b_4507_u> n_1700_B() {
        return this.J_1907_R;
    }

    public c_1514_x J_1907_R() {
        return this.R_4764_Y;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            F_427_K globalpos = (F_427_K)p_equals_1_;
            return Objects.equals(this.J_1907_R, globalpos.J_1907_R) && Objects.equals(this.R_4764_Y, globalpos.R_4764_Y);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.J_1907_R, this.R_4764_Y);
    }

    public String toString() {
        return this.J_1907_R.toString() + " " + String.valueOf(this.R_4764_Y);
    }
}

