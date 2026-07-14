/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.V_3137_a;
import lightning.product.c_1514_x;
import lightning.product.PosRuleTestType;

public abstract class PosRuleTest {
    public static final Codec<PosRuleTest> R_4764_Y = V_3137_a.g_2268_R.dispatch("predicate_type", PosRuleTest::n_1700_B, PosRuleTestType::codec);

    public abstract boolean n_1700_B(c_1514_x var1, c_1514_x var2, c_1514_x var3, Random var4);

    protected abstract PosRuleTestType<?> n_1700_B();
}


