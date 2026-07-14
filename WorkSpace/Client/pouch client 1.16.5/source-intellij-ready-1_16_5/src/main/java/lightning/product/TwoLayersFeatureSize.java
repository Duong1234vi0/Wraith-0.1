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
import java.util.OptionalInt;
import lightning.product.FeatureSize;
import lightning.product.FeatureSizeType;

public class TwoLayersFeatureSize
extends FeatureSize {
    public static final Codec<TwoLayersFeatureSize> R_4764_Y = RecordCodecBuilder.create(p_236732_0_ -> p_236732_0_.group((App)Codec.intRange((int)0, (int)81).fieldOf("limit").orElse((Object)1).forGetter(p_236735_0_ -> p_236735_0_.G_564_y), (App)Codec.intRange((int)0, (int)16).fieldOf("lower_size").orElse((Object)0).forGetter(p_236734_0_ -> p_236734_0_.P_1922_E), (App)Codec.intRange((int)0, (int)16).fieldOf("upper_size").orElse((Object)1).forGetter(p_236733_0_ -> p_236733_0_.u_1723_Y), TwoLayersFeatureSize.n_1700_B()).apply((Applicative)p_236732_0_, TwoLayersFeatureSize::new));
    private final int G_564_y;
    private final int P_1922_E;
    private final int u_1723_Y;

    public TwoLayersFeatureSize(int p_i232025_1_, int p_i232025_2_, int p_i232025_3_) {
        this(p_i232025_1_, p_i232025_2_, p_i232025_3_, OptionalInt.empty());
    }

    public TwoLayersFeatureSize(int p_i232026_1_, int p_i232026_2_, int p_i232026_3_, OptionalInt p_i232026_4_) {
        super(p_i232026_4_);
        this.G_564_y = p_i232026_1_;
        this.P_1922_E = p_i232026_2_;
        this.u_1723_Y = p_i232026_3_;
    }

    @Override
    protected FeatureSizeType<?> J_1907_R() {
        return FeatureSizeType.n_1700_B;
    }

    @Override
    public int n_1700_B(int p_230369_1_, int p_230369_2_) {
        return p_230369_2_ < this.G_564_y ? this.P_1922_E : this.u_1723_Y;
    }
}


