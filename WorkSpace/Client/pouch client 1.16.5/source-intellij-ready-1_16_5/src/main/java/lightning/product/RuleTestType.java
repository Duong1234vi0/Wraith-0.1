/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.RandomBlockMatchTest;
import lightning.product.RuleTest;
import lightning.product.BlockStateMatchTest;
import lightning.product.V_3137_a;
import lightning.product.TagMatchTest;
import lightning.product.RandomBlockStateMatchTest;
import lightning.product.AlwaysTrueTest;
import lightning.product.BlockMatchTest;

public interface RuleTestType<P extends RuleTest> {
    public static final RuleTestType<AlwaysTrueTest> n_1700_B = RuleTestType.n_1700_B("always_true", AlwaysTrueTest.n_1700_B);
    public static final RuleTestType<BlockMatchTest> J_1907_R = RuleTestType.n_1700_B("block_match", BlockMatchTest.n_1700_B);
    public static final RuleTestType<BlockStateMatchTest> R_4764_Y = RuleTestType.n_1700_B("blockstate_match", BlockStateMatchTest.n_1700_B);
    public static final RuleTestType<TagMatchTest> G_564_y = RuleTestType.n_1700_B("tag_match", TagMatchTest.n_1700_B);
    public static final RuleTestType<RandomBlockMatchTest> P_1922_E = RuleTestType.n_1700_B("random_block_match", RandomBlockMatchTest.n_1700_B);
    public static final RuleTestType<RandomBlockStateMatchTest> u_1723_Y = RuleTestType.n_1700_B("random_blockstate_match", RandomBlockStateMatchTest.n_1700_B);

    public Codec<P> codec();

    public static <P extends RuleTest> RuleTestType<P> n_1700_B(String p_237129_0_, Codec<P> p_237129_1_) {
        return V_3137_a.n_1700_B(V_3137_a.c_4037_x, p_237129_0_, () -> p_237129_1_);
    }
}


