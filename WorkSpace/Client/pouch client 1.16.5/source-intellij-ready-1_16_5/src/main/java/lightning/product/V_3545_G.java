/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.C_1437_B;
import lightning.product.StructureFeature;
import lightning.product.BoundingBox;
import lightning.product.W_2163_m;
import lightning.product.Y_1387_d;
import lightning.product.b_2085_h;
import lightning.product.c_1514_x;
import lightning.product.StructureStart;
import lightning.product.WorldgenRandom;
import lightning.product.k_594_Q;
import lightning.product.BiomeSource;
import lightning.product.o_2105_O;
import lightning.product.r_4097_j;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;

public class V_3545_G
extends StructureFeature<o_2105_O> {
    public V_3545_G(Codec<o_2105_O> p_i231950_1_) {
        super(p_i231950_1_);
    }

    @Override
    protected boolean J_1907_R() {
        return false;
    }

    @Override
    protected boolean n_1700_B(z_1753_f p_230363_1_, BiomeSource p_230363_2_, long p_230363_3_, WorldgenRandom p_230363_5_, int p_230363_6_, int p_230363_7_, k_594_Q p_230363_8_, Y_1387_d p_230363_9_, o_2105_O p_230363_10_) {
        return V_3545_G.n_1700_B(p_230363_6_, p_230363_7_, p_230363_1_) >= 60;
    }

    @Override
    public StructureFeature.n_1700_B<o_2105_O> n_1700_B() {
        return n_1700_B::new;
    }

    private static int n_1700_B(int chunkX, int chunkY, z_1753_f generatorIn) {
        Random random = new Random(chunkX + chunkY * 10387313);
        W_2163_m rotation = W_2163_m.n_1700_B(random);
        int i = 5;
        int j = 5;
        if (rotation == W_2163_m.J_1907_R) {
            i = -5;
        } else if (rotation == W_2163_m.R_4764_Y) {
            i = -5;
            j = -5;
        } else if (rotation == W_2163_m.G_564_y) {
            j = -5;
        }
        int k = (chunkX << 4) + 7;
        int l = (chunkY << 4) + 7;
        int i1 = generatorIn.R_4764_Y(k, l, z_2963_s.n_1700_B.n_1700_B);
        int j1 = generatorIn.R_4764_Y(k, l + j, z_2963_s.n_1700_B.n_1700_B);
        int k1 = generatorIn.R_4764_Y(k + i, l, z_2963_s.n_1700_B.n_1700_B);
        int l1 = generatorIn.R_4764_Y(k + i, l + j, z_2963_s.n_1700_B.n_1700_B);
        return Math.min(Math.min(i1, j1), Math.min(k1, l1));
    }

    public static class n_1700_B
    extends StructureStart<o_2105_O> {
        public n_1700_B(StructureFeature<o_2105_O> p_i225802_1_, int p_i225802_2_, int p_i225802_3_, BoundingBox p_i225802_4_, int p_i225802_5_, long p_i225802_6_) {
            super(p_i225802_1_, p_i225802_2_, p_i225802_3_, p_i225802_4_, p_i225802_5_, p_i225802_6_);
        }

        @Override
        public void n_1700_B(r_4097_j p_230364_1_, z_1753_f p_230364_2_, b_2085_h p_230364_3_, int p_230364_4_, int p_230364_5_, k_594_Q p_230364_6_, o_2105_O p_230364_7_) {
            W_2163_m rotation = W_2163_m.n_1700_B(this.G_564_y);
            int i = V_3545_G.n_1700_B(p_230364_4_, p_230364_5_, p_230364_2_);
            if (i >= 60) {
                c_1514_x blockpos = new c_1514_x(p_230364_4_ * 16 + 8, i, p_230364_5_ * 16 + 8);
                C_1437_B.n_1700_B(p_230364_3_, blockpos, rotation, this.J_1907_R, this.G_564_y);
                this.J_1907_R();
            }
        }
    }
}


