/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.datafixers.util.Either
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.mojang.datafixers.kinds.Applicative;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.function.Supplier;
import lightning.product.BoundingBox;
import lightning.product.W_2163_m;
import lightning.product.X_2241_P;
import lightning.product.a_2886_t;
import lightning.product.e_3109_Q;
import lightning.product.g_2336_b;
import lightning.product.StructurePoolElementType;
import lightning.product.r_3979_x_0;
import lightning.product.r_4719_P;
import lightning.product.w_1748_S;

public class p_3713_U
extends e_3109_Q {
    public static final Codec<p_3713_U> n_1700_B = RecordCodecBuilder.create(p_236833_0_ -> p_236833_0_.group(p_3713_U.v_4262_N(), p_3713_U.u_1723_Y(), p_3713_U.J_1907_R()).apply((Applicative)p_236833_0_, p_3713_U::new));

    protected p_3713_U(Either<g_2336_b, a_2886_t> p_i242007_1_, Supplier<r_3979_x_0> p_i242007_2_, X_2241_P.n_1700_B p_i242007_3_) {
        super(p_i242007_1_, p_i242007_2_, p_i242007_3_);
    }

    @Override
    protected w_1748_S n_1700_B(W_2163_m p_230379_1_, BoundingBox p_230379_2_, boolean p_230379_3_) {
        w_1748_S placementsettings = super.n_1700_B(p_230379_1_, p_230379_2_, p_230379_3_);
        placementsettings.J_1907_R(r_4719_P.J_1907_R);
        placementsettings.n_1700_B(r_4719_P.G_564_y);
        return placementsettings;
    }

    @Override
    public StructurePoolElementType<?> n_1700_B() {
        return StructurePoolElementType.P_1922_E;
    }

    @Override
    public String toString() {
        return "LegacySingle[" + String.valueOf(this.G_564_y) + "]";
    }
}


