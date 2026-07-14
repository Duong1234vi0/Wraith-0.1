/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.datafixers.util.Either
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;

public class g_1198_o {
    public static final Codec<g_1198_o> n_1700_B = Codec.either((Codec)Codec.INT, (Codec)RecordCodecBuilder.create(p_242258_0_ -> p_242258_0_.group((App)Codec.INT.fieldOf("base").forGetter(p_242263_0_ -> p_242263_0_.J_1907_R), (App)Codec.INT.fieldOf("spread").forGetter(p_242262_0_ -> p_242262_0_.R_4764_Y)).apply((Applicative)p_242258_0_, g_1198_o::new)).comapFlatMap(p_242261_0_ -> p_242261_0_.R_4764_Y < 0 ? DataResult.error((String)("Spread must be non-negative, got: " + p_242261_0_.R_4764_Y)) : DataResult.success((Object)p_242261_0_), Function.identity())).xmap(p_242257_0_ -> (g_1198_o)p_242257_0_.map(g_1198_o::n_1700_B, p_242260_0_ -> p_242260_0_), p_242256_0_ -> p_242256_0_.R_4764_Y == 0 ? Either.left((Object)p_242256_0_.J_1907_R) : Either.right((Object)p_242256_0_));
    private final int J_1907_R;
    private final int R_4764_Y;

    public static Codec<g_1198_o> n_1700_B(int p_242254_0_, int p_242254_1_, int p_242254_2_) {
        Function<g_1198_o, DataResult> function = p_242255_3_ -> {
            if (p_242255_3_.J_1907_R >= p_242254_0_ && p_242255_3_.J_1907_R <= p_242254_1_) {
                return p_242255_3_.R_4764_Y <= p_242254_2_ ? DataResult.success((Object)p_242255_3_) : DataResult.error((String)("Spread too big: " + p_242255_3_.R_4764_Y + " > " + p_242254_2_));
            }
            return DataResult.error((String)("Base value out of range: " + p_242255_3_.J_1907_R + " [" + p_242254_0_ + "-" + p_242254_1_ + "]"));
        };
        return n_1700_B.flatXmap(function, function);
    }

    private g_1198_o(int p_i241900_1_, int p_i241900_2_) {
        this.J_1907_R = p_i241900_1_;
        this.R_4764_Y = p_i241900_2_;
    }

    public static g_1198_o n_1700_B(int p_242252_0_) {
        return new g_1198_o(p_242252_0_, 0);
    }

    public static g_1198_o n_1700_B(int p_242253_0_, int p_242253_1_) {
        return new g_1198_o(p_242253_0_, p_242253_1_);
    }

    public int n_1700_B(Random p_242259_1_) {
        return this.R_4764_Y == 0 ? this.J_1907_R : this.J_1907_R + p_242259_1_.nextInt(this.R_4764_Y + 1);
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            g_1198_o featurespread = (g_1198_o)p_equals_1_;
            return this.J_1907_R == featurespread.J_1907_R && this.R_4764_Y == featurespread.R_4764_Y;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.J_1907_R, this.R_4764_Y);
    }

    public String toString() {
        return "[" + this.J_1907_R + "-" + (this.J_1907_R + this.R_4764_Y) + "]";
    }
}

