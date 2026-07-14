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
import lightning.product.W_2163_m;
import lightning.product.b_2085_h;
import lightning.product.c_1514_x;
import lightning.product.StructureStart;
import lightning.product.f_1884_F;
import lightning.product.k_594_Q;
import lightning.product.o_2105_O;
import lightning.product.r_4097_j;
import lightning.product.z_1753_f;

public class j_4900_U
extends StructureFeature<o_2105_O> {
    public j_4900_U(Codec<o_2105_O> p_i231965_1_) {
        super(p_i231965_1_);
    }

    @Override
    public StructureFeature.n_1700_B<o_2105_O> n_1700_B() {
        return n_1700_B::new;
    }

    public static class n_1700_B
    extends StructureStart<o_2105_O> {
        public n_1700_B(StructureFeature<o_2105_O> p_i225806_1_, int p_i225806_2_, int p_i225806_3_, BoundingBox p_i225806_4_, int p_i225806_5_, long p_i225806_6_) {
            super(p_i225806_1_, p_i225806_2_, p_i225806_3_, p_i225806_4_, p_i225806_5_, p_i225806_6_);
        }

        @Override
        public void n_1700_B(r_4097_j p_230364_1_, z_1753_f p_230364_2_, b_2085_h p_230364_3_, int p_230364_4_, int p_230364_5_, k_594_Q p_230364_6_, o_2105_O p_230364_7_) {
            int i = p_230364_4_ * 16;
            int j = p_230364_5_ * 16;
            c_1514_x blockpos = new c_1514_x(i, 90, j);
            W_2163_m rotation = W_2163_m.n_1700_B(this.G_564_y);
            f_1884_F.n_1700_B(p_230364_3_, blockpos, rotation, this.J_1907_R, this.G_564_y);
            this.J_1907_R();
        }
    }
}


