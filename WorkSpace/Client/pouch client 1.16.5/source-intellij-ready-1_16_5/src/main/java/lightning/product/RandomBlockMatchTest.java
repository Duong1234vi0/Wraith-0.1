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
import lightning.product.RuleTest;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.RuleTestType;
import lightning.product.V_3137_a;

public class RandomBlockMatchTest
extends RuleTest {
    public static final Codec<RandomBlockMatchTest> n_1700_B = RecordCodecBuilder.create(p_237118_0_ -> p_237118_0_.group((App)V_3137_a.q_4610_l.fieldOf("block").forGetter(p_237120_0_ -> p_237120_0_.J_1907_R), (App)Codec.FLOAT.fieldOf("probability").forGetter(p_237119_0_ -> Float.valueOf(p_237119_0_.G_564_y))).apply((Applicative)p_237118_0_, RandomBlockMatchTest::new));
    private final T_2915_h J_1907_R;
    private final float G_564_y;

    public RandomBlockMatchTest(T_2915_h block, float probability) {
        this.J_1907_R = block;
        this.G_564_y = probability;
    }

    @Override
    public boolean n_1700_B(K_4074_S p_215181_1_, Random p_215181_2_) {
        return p_215181_1_.n_1700_B(this.J_1907_R) && p_215181_2_.nextFloat() < this.G_564_y;
    }

    @Override
    protected RuleTestType<?> n_1700_B() {
        return RuleTestType.P_1922_E;
    }
}


