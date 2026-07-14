/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.E_2181_N;
import lightning.product.StructureFeature;
import lightning.product.PoolElementStructurePiece;
import lightning.product.Pools;
import lightning.product.BoundingBox;
import lightning.product.b_2085_h;
import lightning.product.c_1514_x;
import lightning.product.BeardedStructureStart;
import lightning.product.k_594_Q;
import lightning.product.r_4097_j;
import lightning.product.JigsawConfiguration;
import lightning.product.z_1753_f;

public class g_4497_s
extends StructureFeature<JigsawConfiguration> {
    private final int Y_259_p;
    private final boolean Q_2552_b;
    private final boolean C_2741_M;

    public g_4497_s(Codec<JigsawConfiguration> p_i241978_1_, int p_i241978_2_, boolean p_i241978_3_, boolean p_i241978_4_) {
        super(p_i241978_1_);
        this.Y_259_p = p_i241978_2_;
        this.Q_2552_b = p_i241978_3_;
        this.C_2741_M = p_i241978_4_;
    }

    @Override
    public StructureFeature.n_1700_B<JigsawConfiguration> n_1700_B() {
        return (p_242778_1_, p_242778_2_, p_242778_3_, p_242778_4_, p_242778_5_, p_242778_6_) -> new n_1700_B(this, p_242778_2_, p_242778_3_, p_242778_4_, p_242778_5_, p_242778_6_);
    }

    public static class n_1700_B
    extends BeardedStructureStart<JigsawConfiguration> {
        private final g_4497_s P_1922_E;

        public n_1700_B(g_4497_s p_i241979_1_, int p_i241979_2_, int p_i241979_3_, BoundingBox p_i241979_4_, int p_i241979_5_, long p_i241979_6_) {
            super(p_i241979_1_, p_i241979_2_, p_i241979_3_, p_i241979_4_, p_i241979_5_, p_i241979_6_);
            this.P_1922_E = p_i241979_1_;
        }

        @Override
        public void n_1700_B(r_4097_j p_230364_1_, z_1753_f p_230364_2_, b_2085_h p_230364_3_, int p_230364_4_, int p_230364_5_, k_594_Q p_230364_6_, JigsawConfiguration p_230364_7_) {
            c_1514_x blockpos = new c_1514_x(p_230364_4_ * 16, this.P_1922_E.Y_259_p, p_230364_5_ * 16);
            Pools.n_1700_B();
            E_2181_N.n_1700_B(p_230364_1_, p_230364_7_, PoolElementStructurePiece::new, p_230364_2_, p_230364_3_, blockpos, this.J_1907_R, this.G_564_y, this.P_1922_E.Q_2552_b, this.P_1922_E.C_2741_M);
            this.J_1907_R();
        }
    }
}


