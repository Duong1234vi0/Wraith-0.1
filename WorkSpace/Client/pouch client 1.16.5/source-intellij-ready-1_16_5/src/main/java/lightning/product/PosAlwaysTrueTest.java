/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.c_1514_x;
import lightning.product.PosRuleTestType;
import lightning.product.PosRuleTest;

public class PosAlwaysTrueTest
extends PosRuleTest {
    public static final Codec<PosAlwaysTrueTest> n_1700_B;
    public static final PosAlwaysTrueTest J_1907_R;

    private PosAlwaysTrueTest() {
    }

    @Override
    public boolean n_1700_B(c_1514_x p_230385_1_, c_1514_x p_230385_2_, c_1514_x p_230385_3_, Random p_230385_4_) {
        return true;
    }

    @Override
    protected PosRuleTestType<?> n_1700_B() {
        return PosRuleTestType.n_1700_B;
    }

    static {
        J_1907_R = new PosAlwaysTrueTest();
        n_1700_B = Codec.unit(() -> J_1907_R);
    }
}


