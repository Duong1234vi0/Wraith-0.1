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
import lightning.product.HugeMushroomFeatureConfiguration;
import lightning.product.c_1514_x;
import lightning.product.AbstractHugeMushroomFeature;
import lightning.product.s_3698_N;
import lightning.product.LevelAccessor;

public class HugeBrownMushroomFeature
extends AbstractHugeMushroomFeature {
    public HugeBrownMushroomFeature(Codec<HugeMushroomFeatureConfiguration> p_i231957_1_) {
        super(p_i231957_1_);
    }

    @Override
    protected void n_1700_B(LevelAccessor p_225564_1_, Random p_225564_2_, c_1514_x p_225564_3_, int p_225564_4_, c_1514_x.n_1700_B p_225564_5_, HugeMushroomFeatureConfiguration p_225564_6_) {
        int i = p_225564_6_.G_564_y;
        for (int j = -i; j <= i; ++j) {
            for (int k = -i; k <= i; ++k) {
                boolean flag5;
                boolean flag = j == -i;
                boolean flag1 = j == i;
                boolean flag2 = k == -i;
                boolean flag3 = k == i;
                boolean flag4 = flag || flag1;
                boolean bl = flag5 = flag2 || flag3;
                if (flag4 && flag5) continue;
                p_225564_5_.n_1700_B(p_225564_3_, j, p_225564_4_, k);
                if (p_225564_1_.getBlockState(p_225564_5_).t_148_a(p_225564_1_, p_225564_5_)) continue;
                boolean flag6 = flag || flag5 && j == 1 - i;
                boolean flag7 = flag1 || flag5 && j == i - 1;
                boolean flag8 = flag2 || flag4 && k == 1 - i;
                boolean flag9 = flag3 || flag4 && k == i - 1;
                this.n_1700_B(p_225564_1_, p_225564_5_, (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)p_225564_6_.J_1907_R.n_1700_B(p_225564_2_, p_225564_3_).n_1700_B(s_3698_N.M_182_A, flag6)).n_1700_B(s_3698_N.h_1847_R, flag7)).n_1700_B(s_3698_N.P_4830_p, flag8)).n_1700_B(s_3698_N.Q_4569_t, flag9));
            }
        }
    }

    @Override
    protected int n_1700_B(int p_225563_1_, int p_225563_2_, int p_225563_3_, int p_225563_4_) {
        return p_225563_4_ <= 3 ? 0 : p_225563_3_;
    }
}


