/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import java.util.LinkedList;
import java.util.Random;
import lightning.product.E_3771_B;
import lightning.product.StructureFeature;
import lightning.product.WoodlandMansionPieces;
import lightning.product.J_3017_d;
import lightning.product.BoundingBox;
import lightning.product.WorldGenLevel;
import lightning.product.W_2163_m;
import lightning.product.Y_1387_d;
import lightning.product.a_3742_W;
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

public class WoodlandMansionFeature
extends StructureFeature<o_2105_O> {
    public WoodlandMansionFeature(Codec<o_2105_O> p_i232005_1_) {
        super(p_i232005_1_);
    }

    @Override
    protected boolean J_1907_R() {
        return false;
    }

    @Override
    protected boolean n_1700_B(z_1753_f p_230363_1_, BiomeSource p_230363_2_, long p_230363_3_, WorldgenRandom p_230363_5_, int p_230363_6_, int p_230363_7_, k_594_Q p_230363_8_, Y_1387_d p_230363_9_, o_2105_O p_230363_10_) {
        for (k_594_Q biome : p_230363_2_.n_1700_B(p_230363_6_ * 16 + 9, p_230363_1_.u_1723_Y(), p_230363_7_ * 16 + 9, 32)) {
            if (biome.P_1922_E().n_1700_B(this)) continue;
            return false;
        }
        return true;
    }

    @Override
    public StructureFeature.n_1700_B<o_2105_O> n_1700_B() {
        return n_1700_B::new;
    }

    public static class n_1700_B
    extends StructureStart<o_2105_O> {
        public n_1700_B(StructureFeature<o_2105_O> p_i225823_1_, int p_i225823_2_, int p_i225823_3_, BoundingBox p_i225823_4_, int p_i225823_5_, long p_i225823_6_) {
            super(p_i225823_1_, p_i225823_2_, p_i225823_3_, p_i225823_4_, p_i225823_5_, p_i225823_6_);
        }

        @Override
        public void n_1700_B(r_4097_j p_230364_1_, z_1753_f p_230364_2_, b_2085_h p_230364_3_, int p_230364_4_, int p_230364_5_, k_594_Q p_230364_6_, o_2105_O p_230364_7_) {
            W_2163_m rotation = W_2163_m.n_1700_B(this.G_564_y);
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
            int k = (p_230364_4_ << 4) + 7;
            int l = (p_230364_5_ << 4) + 7;
            int i1 = p_230364_2_.R_4764_Y(k, l, z_2963_s.n_1700_B.n_1700_B);
            int j1 = p_230364_2_.R_4764_Y(k, l + j, z_2963_s.n_1700_B.n_1700_B);
            int k1 = p_230364_2_.R_4764_Y(k + i, l, z_2963_s.n_1700_B.n_1700_B);
            int l1 = p_230364_2_.R_4764_Y(k + i, l + j, z_2963_s.n_1700_B.n_1700_B);
            int i2 = Math.min(Math.min(i1, j1), Math.min(k1, l1));
            if (i2 >= 60) {
                c_1514_x blockpos = new c_1514_x(p_230364_4_ * 16 + 8, i2 + 1, p_230364_5_ * 16 + 8);
                LinkedList list = Lists.newLinkedList();
                WoodlandMansionPieces.n_1700_B(p_230364_3_, blockpos, rotation, list, this.G_564_y);
                this.J_1907_R.addAll(list);
                this.J_1907_R();
            }
        }

        @Override
        public void n_1700_B(WorldGenLevel p_230366_1_, J_3017_d p_230366_2_, z_1753_f p_230366_3_, Random p_230366_4_, BoundingBox p_230366_5_, Y_1387_d p_230366_6_) {
            super.n_1700_B(p_230366_1_, p_230366_2_, p_230366_3_, p_230366_4_, p_230366_5_, p_230366_6_);
            int i = this.R_4764_Y.J_1907_R;
            for (int j = p_230366_5_.n_1700_B; j <= p_230366_5_.G_564_y; ++j) {
                for (int k = p_230366_5_.R_4764_Y; k <= p_230366_5_.u_1723_Y; ++k) {
                    c_1514_x blockpos1;
                    c_1514_x blockpos = new c_1514_x(j, i, k);
                    if (p_230366_1_.u_1723_Y(blockpos) || !this.R_4764_Y.J_1907_R(blockpos)) continue;
                    boolean flag = false;
                    for (E_3771_B structurepiece : this.J_1907_R) {
                        if (!structurepiece.v_4262_N().J_1907_R(blockpos)) continue;
                        flag = true;
                        break;
                    }
                    if (!flag) continue;
                    for (int l = i - 1; l > 1 && (p_230366_1_.u_1723_Y(blockpos1 = new c_1514_x(j, l, k)) || p_230366_1_.getBlockState(blockpos1).R_4764_Y().n_1700_B()); --l) {
                        p_230366_1_.n_1700_B(blockpos1, a_3742_W.P_4830_p.multiplayerClientSuggestionProvider(), 2);
                    }
                }
            }
        }
    }
}


