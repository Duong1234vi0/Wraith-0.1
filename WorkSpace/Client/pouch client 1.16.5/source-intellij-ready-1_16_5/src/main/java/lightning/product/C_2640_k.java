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
import java.util.Random;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.PosRuleTestType;
import lightning.product.PosRuleTest;
import lightning.product.u_530_F;

public class C_2640_k
extends PosRuleTest {
    public static final Codec<C_2640_k> n_1700_B = RecordCodecBuilder.create(p_237051_0_ -> p_237051_0_.group((App)Codec.FLOAT.fieldOf("min_chance").orElse((Object)Float.valueOf(0.0f)).forGetter(p_237056_0_ -> Float.valueOf(p_237056_0_.J_1907_R)), (App)Codec.FLOAT.fieldOf("max_chance").orElse((Object)Float.valueOf(0.0f)).forGetter(p_237055_0_ -> Float.valueOf(p_237055_0_.G_564_y)), (App)Codec.INT.fieldOf("min_dist").orElse((Object)0).forGetter(p_237054_0_ -> p_237054_0_.P_1922_E), (App)Codec.INT.fieldOf("max_dist").orElse((Object)0).forGetter(p_237053_0_ -> p_237053_0_.u_1723_Y), (App)b_257_Y.n_1700_B.G_564_y.fieldOf("axis").orElse((Object)b_257_Y.n_1700_B.J_1907_R).forGetter(p_237052_0_ -> p_237052_0_.v_4262_N)).apply((Applicative)p_237051_0_, C_2640_k::new));
    private final float J_1907_R;
    private final float G_564_y;
    private final int P_1922_E;
    private final int u_1723_Y;
    private final b_257_Y.n_1700_B v_4262_N;

    public C_2640_k(float p_i232114_1_, float p_i232114_2_, int p_i232114_3_, int p_i232114_4_, b_257_Y.n_1700_B p_i232114_5_) {
        if (p_i232114_3_ >= p_i232114_4_) {
            throw new IllegalArgumentException("Invalid range: [" + p_i232114_3_ + "," + p_i232114_4_ + "]");
        }
        this.J_1907_R = p_i232114_1_;
        this.G_564_y = p_i232114_2_;
        this.P_1922_E = p_i232114_3_;
        this.u_1723_Y = p_i232114_4_;
        this.v_4262_N = p_i232114_5_;
    }

    @Override
    public boolean n_1700_B(c_1514_x p_230385_1_, c_1514_x p_230385_2_, c_1514_x p_230385_3_, Random p_230385_4_) {
        b_257_Y direction = b_257_Y.n_1700_B(b_257_Y.J_1907_R.n_1700_B, this.v_4262_N);
        float f = Math.abs((p_230385_2_.getX() - p_230385_3_.getX()) * direction.t_148_a());
        float f1 = Math.abs((p_230385_2_.getY() - p_230385_3_.getY()) * direction.s_956_w());
        float f2 = Math.abs((p_230385_2_.getZ() - p_230385_3_.getZ()) * direction.u_2550_I());
        int i = (int)(f + f1 + f2);
        float f3 = p_230385_4_.nextFloat();
        return (double)f3 <= u_530_F.J_1907_R((double)this.J_1907_R, (double)this.G_564_y, u_530_F.R_4764_Y((double)i, (double)this.P_1922_E, (double)this.u_1723_Y));
    }

    @Override
    protected PosRuleTestType<?> n_1700_B() {
        return PosRuleTestType.R_4764_Y;
    }
}


