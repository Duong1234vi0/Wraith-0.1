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
import lightning.product.WorldGenLevel;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.o_2105_O;
import lightning.product.u_530_F;
import lightning.product.z_1753_f;

public class IceSpikeFeature
extends Feature<o_2105_O> {
    public IceSpikeFeature(Codec<o_2105_O> p_i231962_1_) {
        super(p_i231962_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, o_2105_O p_241855_5_) {
        while (p_241855_1_.u_1723_Y(p_241855_4_) && p_241855_4_.getY() > 2) {
            p_241855_4_ = p_241855_4_.down();
        }
        if (!p_241855_1_.getBlockState(p_241855_4_).n_1700_B(a_3742_W.l_697_B)) {
            return false;
        }
        p_241855_4_ = p_241855_4_.up(p_241855_3_.nextInt(4));
        int i = p_241855_3_.nextInt(4) + 7;
        int j = i / 4 + p_241855_3_.nextInt(2);
        if (j > 1 && p_241855_3_.nextInt(60) == 0) {
            p_241855_4_ = p_241855_4_.up(10 + p_241855_3_.nextInt(30));
        }
        for (int k = 0; k < i; ++k) {
            float f = (1.0f - (float)k / (float)i) * (float)j;
            int l = u_530_F.u_1723_Y(f);
            for (int i1 = -l; i1 <= l; ++i1) {
                float f1 = (float)u_530_F.n_1700_B(i1) - 0.25f;
                for (int j1 = -l; j1 <= l; ++j1) {
                    float f2 = (float)u_530_F.n_1700_B(j1) - 0.25f;
                    if ((i1 != 0 || j1 != 0) && f1 * f1 + f2 * f2 > f * f || (i1 == -l || i1 == l || j1 == -l || j1 == l) && p_241855_3_.nextFloat() > 0.75f) continue;
                    K_4074_S blockstate = p_241855_1_.getBlockState(p_241855_4_.add(i1, k, j1));
                    T_2915_h block = blockstate.J_1907_R();
                    if (blockstate.v_4262_N() || IceSpikeFeature.J_1907_R(block) || block == a_3742_W.l_697_B || block == a_3742_W.O_1795_e) {
                        this.n_1700_B(p_241855_1_, p_241855_4_.add(i1, k, j1), a_3742_W.ServerHelper.multiplayerClientSuggestionProvider());
                    }
                    if (k == 0 || l <= 1) continue;
                    blockstate = p_241855_1_.getBlockState(p_241855_4_.add(i1, -k, j1));
                    block = blockstate.J_1907_R();
                    if (!blockstate.v_4262_N() && !IceSpikeFeature.J_1907_R(block) && block != a_3742_W.l_697_B && block != a_3742_W.O_1795_e) continue;
                    this.n_1700_B(p_241855_1_, p_241855_4_.add(i1, -k, j1), a_3742_W.ServerHelper.multiplayerClientSuggestionProvider());
                }
            }
        }
        int k1 = j - 1;
        if (k1 < 0) {
            k1 = 0;
        } else if (k1 > 1) {
            k1 = 1;
        }
        for (int l1 = -k1; l1 <= k1; ++l1) {
            block5: for (int i2 = -k1; i2 <= k1; ++i2) {
                c_1514_x blockpos = p_241855_4_.add(l1, -1, i2);
                int j2 = 50;
                if (Math.abs(l1) == 1 && Math.abs(i2) == 1) {
                    j2 = p_241855_3_.nextInt(5);
                }
                while (blockpos.getY() > 50) {
                    K_4074_S blockstate1 = p_241855_1_.getBlockState(blockpos);
                    T_2915_h block1 = blockstate1.J_1907_R();
                    if (!blockstate1.v_4262_N() && !IceSpikeFeature.J_1907_R(block1) && block1 != a_3742_W.l_697_B && block1 != a_3742_W.O_1795_e && block1 != a_3742_W.ServerHelper) continue block5;
                    this.n_1700_B(p_241855_1_, blockpos, a_3742_W.ServerHelper.multiplayerClientSuggestionProvider());
                    blockpos = blockpos.down();
                    if (--j2 > 0) continue;
                    blockpos = blockpos.down(p_241855_3_.nextInt(5) + 1);
                    j2 = p_241855_3_.nextInt(5);
                }
            }
        }
        return true;
    }
}



