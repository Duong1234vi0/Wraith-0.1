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

public class ThreeLayersFeatureSize
extends FeatureSize {
    public static final Codec<ThreeLayersFeatureSize> R_4764_Y = RecordCodecBuilder.create(p_236722_0_ -> p_236722_0_.group((App)Codec.intRange((int)0, (int)80).fieldOf("limit").orElse((Object)1).forGetter(p_236727_0_ -> p_236727_0_.G_564_y), (App)Codec.intRange((int)0, (int)80).fieldOf("upper_limit").orElse((Object)1).forGetter(p_236726_0_ -> p_236726_0_.P_1922_E), (App)Codec.intRange((int)0, (int)16).fieldOf("lower_size").orElse((Object)0).forGetter(p_236725_0_ -> p_236725_0_.u_1723_Y), (App)Codec.intRange((int)0, (int)16).fieldOf("middle_size").orElse((Object)1).forGetter(p_236724_0_ -> p_236724_0_.v_4262_N), (App)Codec.intRange((int)0, (int)16).fieldOf("upper_size").orElse((Object)1).forGetter(p_236723_0_ -> p_236723_0_.w_1484_f), ThreeLayersFeatureSize.n_1700_B()).apply((Applicative)p_236722_0_, ThreeLayersFeatureSize::new));
    private final int G_564_y;
    private final int P_1922_E;
    private final int u_1723_Y;
    private final int v_4262_N;
    private final int w_1484_f;

    public ThreeLayersFeatureSize(int p_i232024_1_, int p_i232024_2_, int p_i232024_3_, int p_i232024_4_, int p_i232024_5_, OptionalInt p_i232024_6_) {
        super(p_i232024_6_);
        this.G_564_y = p_i232024_1_;
        this.P_1922_E = p_i232024_2_;
        this.u_1723_Y = p_i232024_3_;
        this.v_4262_N = p_i232024_4_;
        this.w_1484_f = p_i232024_5_;
    }

    @Override
    protected FeatureSizeType<?> J_1907_R() {
        return FeatureSizeType.J_1907_R;
    }

    @Override
    public int n_1700_B(int p_230369_1_, int p_230369_2_) {
        if (p_230369_2_ < this.G_564_y) {
            return this.u_1723_Y;
        }
        return p_230369_2_ >= p_230369_1_ - this.P_1922_E ? this.w_1484_f : this.v_4262_N;
    }
}


