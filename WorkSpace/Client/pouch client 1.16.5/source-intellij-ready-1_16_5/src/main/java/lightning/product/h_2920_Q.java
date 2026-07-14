/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.StructureFeature;
import lightning.product.BoundingBox;
import lightning.product.N_2400_q;
import lightning.product.ShipwreckConfiguration;
import lightning.product.W_2163_m;
import lightning.product.b_2085_h;
import lightning.product.c_1514_x;
import lightning.product.StructureStart;
import lightning.product.k_594_Q;
import lightning.product.r_4097_j;
import lightning.product.z_1753_f;

public class h_2920_Q
extends StructureFeature<ShipwreckConfiguration> {
    public h_2920_Q(Codec<ShipwreckConfiguration> p_i231989_1_) {
        super(p_i231989_1_);
    }

    @Override
    public StructureFeature.n_1700_B<ShipwreckConfiguration> n_1700_B() {
        return n_1700_B::new;
    }

    public static class n_1700_B
    extends StructureStart<ShipwreckConfiguration> {
        public n_1700_B(StructureFeature<ShipwreckConfiguration> p_i225817_1_, int p_i225817_2_, int p_i225817_3_, BoundingBox p_i225817_4_, int p_i225817_5_, long p_i225817_6_) {
            super(p_i225817_1_, p_i225817_2_, p_i225817_3_, p_i225817_4_, p_i225817_5_, p_i225817_6_);
        }

        @Override
        public void n_1700_B(r_4097_j p_230364_1_, z_1753_f p_230364_2_, b_2085_h p_230364_3_, int p_230364_4_, int p_230364_5_, k_594_Q p_230364_6_, ShipwreckConfiguration p_230364_7_) {
            W_2163_m rotation = W_2163_m.n_1700_B(this.G_564_y);
            c_1514_x blockpos = new c_1514_x(p_230364_4_ * 16, 90, p_230364_5_ * 16);
            N_2400_q.n_1700_B(p_230364_3_, blockpos, rotation, this.J_1907_R, this.G_564_y, p_230364_7_);
            this.J_1907_R();
        }
    }
}


