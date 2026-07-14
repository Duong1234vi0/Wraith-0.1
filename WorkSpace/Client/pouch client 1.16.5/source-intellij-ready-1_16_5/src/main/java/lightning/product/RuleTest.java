/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.K_4074_S;
import lightning.product.RuleTestType;
import lightning.product.V_3137_a;

public abstract class RuleTest {
    public static final Codec<RuleTest> R_4764_Y = V_3137_a.c_4037_x.dispatch("predicate_type", RuleTest::n_1700_B, RuleTestType::codec);

    public abstract boolean n_1700_B(K_4074_S var1, Random var2);

    protected abstract RuleTestType<?> n_1700_B();
}


