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
import lightning.product.RuleTestType;

public class RandomBlockStateMatchTest
extends RuleTest {
    public static final Codec<RandomBlockStateMatchTest> n_1700_B = RecordCodecBuilder.create(p_237122_0_ -> p_237122_0_.group((App)K_4074_S.J_1907_R.fieldOf("block_state").forGetter(p_237124_0_ -> p_237124_0_.J_1907_R), (App)Codec.FLOAT.fieldOf("probability").forGetter(p_237123_0_ -> Float.valueOf(p_237123_0_.G_564_y))).apply((Applicative)p_237122_0_, RandomBlockStateMatchTest::new));
    private final K_4074_S J_1907_R;
    private final float G_564_y;

    public RandomBlockStateMatchTest(K_4074_S state, float probability) {
        this.J_1907_R = state;
        this.G_564_y = probability;
    }

    @Override
    public boolean n_1700_B(K_4074_S p_215181_1_, Random p_215181_2_) {
        return p_215181_1_ == this.J_1907_R && p_215181_2_.nextFloat() < this.G_564_y;
    }

    @Override
    protected RuleTestType<?> n_1700_B() {
        return RuleTestType.u_1723_Y;
    }
}


