/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.PosAlwaysTrueTest;
import lightning.product.C_2640_k;
import lightning.product.V_3137_a;
import lightning.product.PosRuleTest;
import lightning.product.w_3309_y;

public interface PosRuleTestType<P extends PosRuleTest> {
    public static final PosRuleTestType<PosAlwaysTrueTest> n_1700_B = PosRuleTestType.n_1700_B("always_true", PosAlwaysTrueTest.n_1700_B);
    public static final PosRuleTestType<w_3309_y> J_1907_R = PosRuleTestType.n_1700_B("linear_pos", w_3309_y.n_1700_B);
    public static final PosRuleTestType<C_2640_k> R_4764_Y = PosRuleTestType.n_1700_B("axis_aligned_linear_pos", C_2640_k.n_1700_B);

    public Codec<P> codec();

    public static <P extends PosRuleTest> PosRuleTestType<P> n_1700_B(String p_237107_0_, Codec<P> p_237107_1_) {
        return V_3137_a.n_1700_B(V_3137_a.g_2268_R, p_237107_0_, () -> p_237107_1_);
    }
}


