/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.List;
import lightning.product.E_3771_B;
import lightning.product.StructureFeature;
import lightning.product.BoundingBox;
import lightning.product.Y_1387_d;
import lightning.product.b_2085_h;
import lightning.product.StructureStart;
import lightning.product.WorldgenRandom;
import lightning.product.g_4102_b;
import lightning.product.k_594_Q;
import lightning.product.BiomeSource;
import lightning.product.o_2105_O;
import lightning.product.r_4097_j;
import lightning.product.z_1753_f;

public class StrongholdFeature
extends StructureFeature<o_2105_O> {
    public StrongholdFeature(Codec<o_2105_O> p_i231996_1_) {
        super(p_i231996_1_);
    }

    @Override
    public StructureFeature.n_1700_B<o_2105_O> n_1700_B() {
        return n_1700_B::new;
    }

    @Override
    protected boolean n_1700_B(z_1753_f p_230363_1_, BiomeSource p_230363_2_, long p_230363_3_, WorldgenRandom p_230363_5_, int p_230363_6_, int p_230363_7_, k_594_Q p_230363_8_, Y_1387_d p_230363_9_, o_2105_O p_230363_10_) {
        return p_230363_1_.n_1700_B(new Y_1387_d(p_230363_6_, p_230363_7_));
    }

    public static class n_1700_B
    extends StructureStart<o_2105_O> {
        private final long P_1922_E;

        public n_1700_B(StructureFeature<o_2105_O> p_i225818_1_, int p_i225818_2_, int p_i225818_3_, BoundingBox p_i225818_4_, int p_i225818_5_, long p_i225818_6_) {
            super(p_i225818_1_, p_i225818_2_, p_i225818_3_, p_i225818_4_, p_i225818_5_, p_i225818_6_);
            this.P_1922_E = p_i225818_6_;
        }

        @Override
        public void n_1700_B(r_4097_j p_230364_1_, z_1753_f p_230364_2_, b_2085_h p_230364_3_, int p_230364_4_, int p_230364_5_, k_594_Q p_230364_6_, o_2105_O p_230364_7_) {
            g_4102_b.M_588_G strongholdpieces$stairs2;
            int i = 0;
            do {
                this.J_1907_R.clear();
                this.R_4764_Y = BoundingBox.n_1700_B();
                this.G_564_y.R_4764_Y(this.P_1922_E + (long)i++, p_230364_4_, p_230364_5_);
                g_4102_b.n_1700_B();
                strongholdpieces$stairs2 = new g_4102_b.M_588_G(this.G_564_y, (p_230364_4_ << 4) + 2, (p_230364_5_ << 4) + 2);
                this.J_1907_R.add(strongholdpieces$stairs2);
                strongholdpieces$stairs2.n_1700_B(strongholdpieces$stairs2, this.J_1907_R, this.G_564_y);
                List<E_3771_B> list = strongholdpieces$stairs2.R_4764_Y;
                while (!list.isEmpty()) {
                    int j = this.G_564_y.nextInt(list.size());
                    E_3771_B structurepiece = list.remove(j);
                    structurepiece.n_1700_B(strongholdpieces$stairs2, this.J_1907_R, this.G_564_y);
                }
                this.J_1907_R();
                this.n_1700_B(p_230364_2_.u_1723_Y(), this.G_564_y, 10);
            } while (this.J_1907_R.isEmpty() || strongholdpieces$stairs2.J_1907_R == null);
        }
    }
}


