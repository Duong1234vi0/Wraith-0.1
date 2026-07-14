/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.FluidTags;
import lightning.product.K_4074_S;
import lightning.product.WorldGenLevel;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.RandomPatchConfiguration;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;
import lightning.product.z_3539_x;

public class RandomPatchFeature
extends Feature<RandomPatchConfiguration> {
    public RandomPatchFeature(Codec<RandomPatchConfiguration> p_i231979_1_) {
        super(p_i231979_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, RandomPatchConfiguration p_241855_5_) {
        K_4074_S blockstate = p_241855_5_.J_1907_R.n_1700_B(p_241855_3_, p_241855_4_);
        c_1514_x blockpos = p_241855_5_.u_2550_I ? p_241855_1_.n_1700_B(z_2963_s.n_1700_B.n_1700_B, p_241855_4_) : p_241855_4_;
        int i = 0;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int j = 0; j < p_241855_5_.u_1723_Y; ++j) {
            blockpos$mutable.n_1700_B(blockpos, p_241855_3_.nextInt(p_241855_5_.v_4262_N + 1) - p_241855_3_.nextInt(p_241855_5_.v_4262_N + 1), p_241855_3_.nextInt(p_241855_5_.w_1484_f + 1) - p_241855_3_.nextInt(p_241855_5_.w_1484_f + 1), p_241855_3_.nextInt(p_241855_5_.t_148_a + 1) - p_241855_3_.nextInt(p_241855_5_.t_148_a + 1));
            z_3539_x blockpos1 = blockpos$mutable.down();
            K_4074_S blockstate1 = p_241855_1_.getBlockState((c_1514_x)blockpos1);
            if (!p_241855_1_.u_1723_Y(blockpos$mutable) && (!p_241855_5_.s_956_w || !p_241855_1_.getBlockState(blockpos$mutable).R_4764_Y().P_1922_E()) || !blockstate.n_1700_B(p_241855_1_, (c_1514_x)blockpos$mutable) || !p_241855_5_.G_564_y.isEmpty() && !p_241855_5_.G_564_y.contains(blockstate1.J_1907_R()) || p_241855_5_.P_1922_E.contains(blockstate1) || p_241855_5_.M_588_G && !p_241855_1_.getFluidState(((c_1514_x)blockpos1).west()).n_1700_B(FluidTags.J_1907_R) && !p_241855_1_.getFluidState(((c_1514_x)blockpos1).east()).n_1700_B(FluidTags.J_1907_R) && !p_241855_1_.getFluidState(((c_1514_x)blockpos1).north()).n_1700_B(FluidTags.J_1907_R) && !p_241855_1_.getFluidState(((c_1514_x)blockpos1).south()).n_1700_B(FluidTags.J_1907_R)) continue;
            p_241855_5_.R_4764_Y.n_1700_B(p_241855_1_, blockpos$mutable, blockstate, p_241855_3_);
            ++i;
        }
        return i > 0;
    }
}


