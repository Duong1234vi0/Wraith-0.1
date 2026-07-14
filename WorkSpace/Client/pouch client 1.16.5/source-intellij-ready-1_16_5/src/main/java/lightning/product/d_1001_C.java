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

public class d_1001_C
extends AbstractHugeMushroomFeature {
    public d_1001_C(Codec<HugeMushroomFeatureConfiguration> p_i231960_1_) {
        super(p_i231960_1_);
    }

    @Override
    protected void n_1700_B(LevelAccessor p_225564_1_, Random p_225564_2_, c_1514_x p_225564_3_, int p_225564_4_, c_1514_x.n_1700_B p_225564_5_, HugeMushroomFeatureConfiguration p_225564_6_) {
        for (int i = p_225564_4_ - 3; i <= p_225564_4_; ++i) {
            int j = i < p_225564_4_ ? p_225564_6_.G_564_y : p_225564_6_.G_564_y - 1;
            int k = p_225564_6_.G_564_y - 2;
            for (int l = -j; l <= j; ++l) {
                for (int i1 = -j; i1 <= j; ++i1) {
                    boolean flag5;
                    boolean flag = l == -j;
                    boolean flag1 = l == j;
                    boolean flag2 = i1 == -j;
                    boolean flag3 = i1 == j;
                    boolean flag4 = flag || flag1;
                    boolean bl = flag5 = flag2 || flag3;
                    if (i < p_225564_4_ && flag4 == flag5) continue;
                    p_225564_5_.n_1700_B(p_225564_3_, l, i, i1);
                    if (p_225564_1_.getBlockState(p_225564_5_).t_148_a(p_225564_1_, p_225564_5_)) continue;
                    this.n_1700_B(p_225564_1_, p_225564_5_, (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)p_225564_6_.J_1907_R.n_1700_B(p_225564_2_, p_225564_3_).n_1700_B(s_3698_N.t_1786_h, i >= p_225564_4_ - 1)).n_1700_B(s_3698_N.M_182_A, l < -k)).n_1700_B(s_3698_N.h_1847_R, l > k)).n_1700_B(s_3698_N.P_4830_p, i1 < -k)).n_1700_B(s_3698_N.Q_4569_t, i1 > k));
                }
            }
        }
    }

    @Override
    protected int n_1700_B(int p_225563_1_, int p_225563_2_, int p_225563_3_, int p_225563_4_) {
        int i = 0;
        if (p_225563_4_ < p_225563_2_ && p_225563_4_ >= p_225563_2_ - 3) {
            i = p_225563_3_;
        } else if (p_225563_4_ == p_225563_2_) {
            i = p_225563_3_;
        }
        return i;
    }
}


