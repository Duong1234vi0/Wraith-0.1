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
import lightning.product.SerializationTags;
import lightning.product.RuleTestType;
import lightning.product.r_109_r;

public class TagMatchTest
extends RuleTest {
    public static final Codec<TagMatchTest> n_1700_B = r_109_r.n_1700_B(() -> SerializationTags.n_1700_B().n_1700_B()).fieldOf("tag").xmap(TagMatchTest::new, p_237162_0_ -> p_237162_0_.J_1907_R).codec();
    private final r_109_r<T_2915_h> J_1907_R;

    public TagMatchTest(r_109_r<T_2915_h> tag) {
        this.J_1907_R = tag;
    }

    @Override
    public boolean n_1700_B(K_4074_S p_215181_1_, Random p_215181_2_) {
        return p_215181_1_.n_1700_B(this.J_1907_R);
    }

    @Override
    protected RuleTestType<?> n_1700_B() {
        return RuleTestType.G_564_y;
    }
}


