/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.BlockGetter;
import lightning.product.StructureFeature;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.NetherFossilPieces;
import lightning.product.Y_1387_d;
import lightning.product.a_3742_W;
import lightning.product.b_2085_h;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.BeardedStructureStart;
import lightning.product.k_594_Q;
import lightning.product.o_2105_O;
import lightning.product.r_4097_j;
import lightning.product.z_1753_f;

public class B_4810_D
extends StructureFeature<o_2105_O> {
    public B_4810_D(Codec<o_2105_O> p_i232105_1_) {
        super(p_i232105_1_);
    }

    @Override
    public StructureFeature.n_1700_B<o_2105_O> n_1700_B() {
        return n_1700_B::new;
    }

    public static class n_1700_B
    extends BeardedStructureStart<o_2105_O> {
        public n_1700_B(StructureFeature<o_2105_O> p_i232106_1_, int p_i232106_2_, int p_i232106_3_, BoundingBox p_i232106_4_, int p_i232106_5_, long p_i232106_6_) {
            super(p_i232106_1_, p_i232106_2_, p_i232106_3_, p_i232106_4_, p_i232106_5_, p_i232106_6_);
        }

        @Override
        public void n_1700_B(r_4097_j p_230364_1_, z_1753_f p_230364_2_, b_2085_h p_230364_3_, int p_230364_4_, int p_230364_5_, k_594_Q p_230364_6_, o_2105_O p_230364_7_) {
            int l;
            Y_1387_d chunkpos = new Y_1387_d(p_230364_4_, p_230364_5_);
            int i = chunkpos.J_1907_R() + this.G_564_y.nextInt(16);
            int j = chunkpos.R_4764_Y() + this.G_564_y.nextInt(16);
            int k = p_230364_2_.u_1723_Y();
            BlockGetter iblockreader = p_230364_2_.n_1700_B(i, j);
            c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B(i, l, j);
            for (l = k + this.G_564_y.nextInt(p_230364_2_.P_1922_E() - 2 - k); l > k; --l) {
                K_4074_S blockstate = iblockreader.getBlockState(blockpos$mutable);
                blockpos$mutable.n_1700_B(b_257_Y.n_1700_B);
                K_4074_S blockstate1 = iblockreader.getBlockState(blockpos$mutable);
                if (blockstate.v_4262_N() && (blockstate1.n_1700_B(a_3742_W.C_415_h) || blockstate1.G_564_y(iblockreader, (c_1514_x)blockpos$mutable, b_257_Y.J_1907_R))) break;
            }
            if (l > k) {
                NetherFossilPieces.n_1700_B(p_230364_3_, this.J_1907_R, this.G_564_y, new c_1514_x(i, l, j));
                this.J_1907_R();
            }
        }
    }
}


