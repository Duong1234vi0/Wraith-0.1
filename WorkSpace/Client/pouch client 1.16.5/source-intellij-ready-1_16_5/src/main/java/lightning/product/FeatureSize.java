/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;
import java.util.OptionalInt;
import lightning.product.FeatureSizeType;
import lightning.product.V_3137_a;

public abstract class FeatureSize {
    public static final Codec<FeatureSize> n_1700_B = V_3137_a.R_3077_Z.dispatch(FeatureSize::J_1907_R, FeatureSizeType::n_1700_B);
    protected final OptionalInt J_1907_R;

    protected static <S extends FeatureSize> RecordCodecBuilder<S, OptionalInt> n_1700_B() {
        return Codec.intRange((int)0, (int)80).optionalFieldOf("min_clipped_height").xmap(p_236708_0_ -> p_236708_0_.map(OptionalInt::of).orElse(OptionalInt.empty()), p_236709_0_ -> p_236709_0_.isPresent() ? Optional.of(p_236709_0_.getAsInt()) : Optional.empty()).forGetter(p_236707_0_ -> p_236707_0_.J_1907_R);
    }

    public FeatureSize(OptionalInt p_i232022_1_) {
        this.J_1907_R = p_i232022_1_;
    }

    protected abstract FeatureSizeType<?> J_1907_R();

    public abstract int n_1700_B(int var1, int var2);

    public OptionalInt R_4764_Y() {
        return this.J_1907_R;
    }
}


