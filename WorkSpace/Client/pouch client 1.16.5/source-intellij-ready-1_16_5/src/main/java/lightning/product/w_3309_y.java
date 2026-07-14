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
import lightning.product.c_1514_x;
import lightning.product.PosRuleTestType;
import lightning.product.PosRuleTest;
import lightning.product.u_530_F;

public class w_3309_y
extends PosRuleTest {
    public static final Codec<w_3309_y> n_1700_B = RecordCodecBuilder.create(p_237092_0_ -> p_237092_0_.group((App)Codec.FLOAT.fieldOf("min_chance").orElse((Object)Float.valueOf(0.0f)).forGetter(p_237096_0_ -> Float.valueOf(p_237096_0_.J_1907_R)), (App)Codec.FLOAT.fieldOf("max_chance").orElse((Object)Float.valueOf(0.0f)).forGetter(p_237095_0_ -> Float.valueOf(p_237095_0_.G_564_y)), (App)Codec.INT.fieldOf("min_dist").orElse((Object)0).forGetter(p_237094_0_ -> p_237094_0_.P_1922_E), (App)Codec.INT.fieldOf("max_dist").orElse((Object)0).forGetter(p_237093_0_ -> p_237093_0_.u_1723_Y)).apply((Applicative)p_237092_0_, w_3309_y::new));
    private final float J_1907_R;
    private final float G_564_y;
    private final int P_1922_E;
    private final int u_1723_Y;

    public w_3309_y(float p_i232116_1_, float p_i232116_2_, int p_i232116_3_, int p_i232116_4_) {
        if (p_i232116_3_ >= p_i232116_4_) {
            throw new IllegalArgumentException("Invalid range: [" + p_i232116_3_ + "," + p_i232116_4_ + "]");
        }
        this.J_1907_R = p_i232116_1_;
        this.G_564_y = p_i232116_2_;
        this.P_1922_E = p_i232116_3_;
        this.u_1723_Y = p_i232116_4_;
    }

    @Override
    public boolean n_1700_B(c_1514_x p_230385_1_, c_1514_x p_230385_2_, c_1514_x p_230385_3_, Random p_230385_4_) {
        int i = p_230385_2_.manhattanDistance(p_230385_3_);
        float f = p_230385_4_.nextFloat();
        return (double)f <= u_530_F.J_1907_R((double)this.J_1907_R, (double)this.G_564_y, u_530_F.R_4764_Y((double)i, (double)this.P_1922_E, (double)this.u_1723_Y));
    }

    @Override
    protected PosRuleTestType<?> n_1700_B() {
        return PosRuleTestType.J_1907_R;
    }
}


