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
import lightning.product.b_2085_h;
import lightning.product.StructureStart;
import lightning.product.DesertPyramidPiece;
import lightning.product.k_594_Q;
import lightning.product.o_2105_O;
import lightning.product.r_4097_j;
import lightning.product.z_1753_f;

public class v_4827_Y
extends StructureFeature<o_2105_O> {
    public v_4827_Y(Codec<o_2105_O> p_i231947_1_) {
        super(p_i231947_1_);
    }

    @Override
    public StructureFeature.n_1700_B<o_2105_O> n_1700_B() {
        return n_1700_B::new;
    }

    public static class n_1700_B
    extends StructureStart<o_2105_O> {
        public n_1700_B(StructureFeature<o_2105_O> p_i225801_1_, int p_i225801_2_, int p_i225801_3_, BoundingBox p_i225801_4_, int p_i225801_5_, long p_i225801_6_) {
            super(p_i225801_1_, p_i225801_2_, p_i225801_3_, p_i225801_4_, p_i225801_5_, p_i225801_6_);
        }

        @Override
        public void n_1700_B(r_4097_j p_230364_1_, z_1753_f p_230364_2_, b_2085_h p_230364_3_, int p_230364_4_, int p_230364_5_, k_594_Q p_230364_6_, o_2105_O p_230364_7_) {
            DesertPyramidPiece desertpyramidpiece = new DesertPyramidPiece(this.G_564_y, p_230364_4_ * 16, p_230364_5_ * 16);
            this.J_1907_R.add(desertpyramidpiece);
            this.J_1907_R();
        }
    }
}


