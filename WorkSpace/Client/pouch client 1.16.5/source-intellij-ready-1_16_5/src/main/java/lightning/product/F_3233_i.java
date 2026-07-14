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
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.o_2105_O;
import lightning.product.Material;
import lightning.product.z_1753_f;

public class F_3233_i
extends Feature<o_2105_O> {
    public F_3233_i(Codec<o_2105_O> p_i231933_1_) {
        super(p_i231933_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, o_2105_O p_241855_5_) {
        if (p_241855_4_.getY() > p_241855_1_.d_2461_k() - 1) {
            return false;
        }
        if (!p_241855_1_.getBlockState(p_241855_4_).n_1700_B(a_3742_W.c_3005_b) && !p_241855_1_.getBlockState(p_241855_4_.down()).n_1700_B(a_3742_W.c_3005_b)) {
            return false;
        }
        boolean flag = false;
        for (b_257_Y direction : b_257_Y.values()) {
            if (direction == b_257_Y.n_1700_B || !p_241855_1_.getBlockState(p_241855_4_.offset(direction)).n_1700_B(a_3742_W.ServerHelper)) continue;
            flag = true;
            break;
        }
        if (!flag) {
            return false;
        }
        p_241855_1_.n_1700_B(p_241855_4_, a_3742_W.G_4691_Q.multiplayerClientSuggestionProvider(), 2);
        block1: for (int i = 0; i < 200; ++i) {
            c_1514_x blockpos;
            K_4074_S blockstate;
            int j = p_241855_3_.nextInt(5) - p_241855_3_.nextInt(6);
            int k = 3;
            if (j < 2) {
                k += j / 2;
            }
            if (k < 1 || (blockstate = p_241855_1_.getBlockState(blockpos = p_241855_4_.add(p_241855_3_.nextInt(k) - p_241855_3_.nextInt(k), j, p_241855_3_.nextInt(k) - p_241855_3_.nextInt(k)))).R_4764_Y() != Material.n_1700_B && !blockstate.n_1700_B(a_3742_W.c_3005_b) && !blockstate.n_1700_B(a_3742_W.ServerHelper) && !blockstate.n_1700_B(a_3742_W.O_1795_e)) continue;
            for (b_257_Y direction1 : b_257_Y.values()) {
                K_4074_S blockstate1 = p_241855_1_.getBlockState(blockpos.offset(direction1));
                if (!blockstate1.n_1700_B(a_3742_W.G_4691_Q)) continue;
                p_241855_1_.n_1700_B(blockpos, a_3742_W.G_4691_Q.multiplayerClientSuggestionProvider(), 2);
                continue block1;
            }
        }
        return true;
    }
}



