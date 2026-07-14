/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.ProbabilityFeatureConfiguration;
import lightning.product.StructureFeature;
import lightning.product.BoundingBox;
import lightning.product.Y_1387_d;
import lightning.product.b_2085_h;
import lightning.product.c_1514_x;
import lightning.product.StructureStart;
import lightning.product.WorldgenRandom;
import lightning.product.k_594_Q;
import lightning.product.BiomeSource;
import lightning.product.r_4097_j;
import lightning.product.r_4360_R;
import lightning.product.z_1753_f;

public class e_4586_L
extends StructureFeature<ProbabilityFeatureConfiguration> {
    public e_4586_L(Codec<ProbabilityFeatureConfiguration> p_i231935_1_) {
        super(p_i231935_1_);
    }

    @Override
    protected boolean n_1700_B(z_1753_f p_230363_1_, BiomeSource p_230363_2_, long p_230363_3_, WorldgenRandom p_230363_5_, int p_230363_6_, int p_230363_7_, k_594_Q p_230363_8_, Y_1387_d p_230363_9_, ProbabilityFeatureConfiguration p_230363_10_) {
        p_230363_5_.n_1700_B(p_230363_3_, p_230363_6_, p_230363_7_, 10387320);
        return p_230363_5_.nextFloat() < p_230363_10_.J_1907_R;
    }

    @Override
    public StructureFeature.n_1700_B<ProbabilityFeatureConfiguration> n_1700_B() {
        return n_1700_B::new;
    }

    public static class n_1700_B
    extends StructureStart<ProbabilityFeatureConfiguration> {
        public n_1700_B(StructureFeature<ProbabilityFeatureConfiguration> p_i225799_1_, int p_i225799_2_, int p_i225799_3_, BoundingBox p_i225799_4_, int p_i225799_5_, long p_i225799_6_) {
            super(p_i225799_1_, p_i225799_2_, p_i225799_3_, p_i225799_4_, p_i225799_5_, p_i225799_6_);
        }

        @Override
        public void n_1700_B(r_4097_j p_230364_1_, z_1753_f p_230364_2_, b_2085_h p_230364_3_, int p_230364_4_, int p_230364_5_, k_594_Q p_230364_6_, ProbabilityFeatureConfiguration p_230364_7_) {
            int i = p_230364_4_ * 16;
            int j = p_230364_5_ * 16;
            c_1514_x blockpos = new c_1514_x(i + 9, 90, j + 9);
            this.J_1907_R.add(new r_4360_R.n_1700_B(blockpos));
            this.J_1907_R();
        }

        @Override
        public c_1514_x n_1700_B() {
            return new c_1514_x((this.u_1723_Y() << 4) + 9, 0, (this.v_4262_N() << 4) + 9);
        }
    }
}


