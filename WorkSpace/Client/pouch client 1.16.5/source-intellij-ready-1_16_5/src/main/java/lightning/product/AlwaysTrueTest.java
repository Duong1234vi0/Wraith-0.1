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

public class AlwaysTrueTest
extends RuleTest {
    public static final Codec<AlwaysTrueTest> n_1700_B;
    public static final AlwaysTrueTest J_1907_R;

    private AlwaysTrueTest() {
    }

    @Override
    public boolean n_1700_B(K_4074_S p_215181_1_, Random p_215181_2_) {
        return true;
    }

    @Override
    protected RuleTestType<?> n_1700_B() {
        return RuleTestType.n_1700_B;
    }

    static {
        J_1907_R = new AlwaysTrueTest();
        n_1700_B = Codec.unit(() -> J_1907_R);
    }
}


