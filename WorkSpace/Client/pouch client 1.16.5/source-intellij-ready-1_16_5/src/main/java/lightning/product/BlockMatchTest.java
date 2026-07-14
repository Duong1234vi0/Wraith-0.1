/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.RuleTest;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.RuleTestType;
import lightning.product.V_3137_a;

public class BlockMatchTest
extends RuleTest {
    public static final Codec<BlockMatchTest> n_1700_B = V_3137_a.q_4610_l.fieldOf("block").xmap(BlockMatchTest::new, p_237076_0_ -> p_237076_0_.J_1907_R).codec();
    private final T_2915_h J_1907_R;

    public BlockMatchTest(T_2915_h block) {
        this.J_1907_R = block;
    }

    @Override
    public boolean n_1700_B(K_4074_S p_215181_1_, Random p_215181_2_) {
        return p_215181_1_.n_1700_B(this.J_1907_R);
    }

    @Override
    protected RuleTestType<?> n_1700_B() {
        return RuleTestType.J_1907_R;
    }
}


