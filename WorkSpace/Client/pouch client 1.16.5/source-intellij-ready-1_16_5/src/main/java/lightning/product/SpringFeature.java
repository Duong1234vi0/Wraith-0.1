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
import lightning.product.SpringConfiguration;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.z_1753_f;

public class SpringFeature
extends Feature<SpringConfiguration> {
    public SpringFeature(Codec<SpringConfiguration> p_i231995_1_) {
        super(p_i231995_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, SpringConfiguration p_241855_5_) {
        if (!p_241855_5_.u_1723_Y.contains(p_241855_1_.getBlockState(p_241855_4_.up()).J_1907_R())) {
            return false;
        }
        if (p_241855_5_.R_4764_Y && !p_241855_5_.u_1723_Y.contains(p_241855_1_.getBlockState(p_241855_4_.down()).J_1907_R())) {
            return false;
        }
        K_4074_S blockstate = p_241855_1_.getBlockState(p_241855_4_);
        if (!blockstate.v_4262_N() && !p_241855_5_.u_1723_Y.contains(blockstate.J_1907_R())) {
            return false;
        }
        int i = 0;
        int j = 0;
        if (p_241855_5_.u_1723_Y.contains(p_241855_1_.getBlockState(p_241855_4_.west()).J_1907_R())) {
            ++j;
        }
        if (p_241855_5_.u_1723_Y.contains(p_241855_1_.getBlockState(p_241855_4_.east()).J_1907_R())) {
            ++j;
        }
        if (p_241855_5_.u_1723_Y.contains(p_241855_1_.getBlockState(p_241855_4_.north()).J_1907_R())) {
            ++j;
        }
        if (p_241855_5_.u_1723_Y.contains(p_241855_1_.getBlockState(p_241855_4_.south()).J_1907_R())) {
            ++j;
        }
        if (p_241855_5_.u_1723_Y.contains(p_241855_1_.getBlockState(p_241855_4_.down()).J_1907_R())) {
            ++j;
        }
        int k = 0;
        if (p_241855_1_.u_1723_Y(p_241855_4_.west())) {
            ++k;
        }
        if (p_241855_1_.u_1723_Y(p_241855_4_.east())) {
            ++k;
        }
        if (p_241855_1_.u_1723_Y(p_241855_4_.north())) {
            ++k;
        }
        if (p_241855_1_.u_1723_Y(p_241855_4_.south())) {
            ++k;
        }
        if (p_241855_1_.u_1723_Y(p_241855_4_.down())) {
            ++k;
        }
        if (j == p_241855_5_.G_564_y && k == p_241855_5_.P_1922_E) {
            p_241855_1_.n_1700_B(p_241855_4_, p_241855_5_.J_1907_R.v_4262_N(), 2);
            p_241855_1_.M_588_G().n_1700_B(p_241855_4_, p_241855_5_.J_1907_R.n_1700_B(), 0);
            ++i;
        }
        return i > 0;
    }
}


