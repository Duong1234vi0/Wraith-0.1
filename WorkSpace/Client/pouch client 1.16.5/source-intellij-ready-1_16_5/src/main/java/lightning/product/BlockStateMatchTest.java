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
import lightning.product.RuleTestType;

public class BlockStateMatchTest
extends RuleTest {
    public static final Codec<BlockStateMatchTest> n_1700_B = K_4074_S.J_1907_R.fieldOf("block_state").xmap(BlockStateMatchTest::new, p_237080_0_ -> p_237080_0_.J_1907_R).codec();
    private final K_4074_S J_1907_R;

    public BlockStateMatchTest(K_4074_S state) {
        this.J_1907_R = state;
    }

    @Override
    public boolean n_1700_B(K_4074_S p_215181_1_, Random p_215181_2_) {
        return p_215181_1_ == this.J_1907_R;
    }

    @Override
    protected RuleTestType<?> n_1700_B() {
        return RuleTestType.R_4764_Y;
    }
}


