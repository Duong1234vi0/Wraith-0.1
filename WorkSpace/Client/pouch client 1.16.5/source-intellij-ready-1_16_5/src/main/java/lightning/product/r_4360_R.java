/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.E_3771_B;
import lightning.product.J_3017_d;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.WorldGenLevel;
import lightning.product.U_2912_j;
import lightning.product.Y_1387_d;
import lightning.product.a_3742_W;
import lightning.product.b_2085_h;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.StructurePieceType;
import lightning.product.ServerLevelAccessor;
import lightning.product.o_4810_o;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;

public class r_4360_R {

    public static class n_1700_B
    extends E_3771_B {
        public n_1700_B(c_1514_x p_i48882_1_) {
            super(StructurePieceType.T_3594_S, 0);
            this.h_1847_R = new BoundingBox(p_i48882_1_.getX(), p_i48882_1_.getY(), p_i48882_1_.getZ(), p_i48882_1_.getX(), p_i48882_1_.getY(), p_i48882_1_.getZ());
        }

        public n_1700_B(b_2085_h p_i50677_1_, U_2912_j p_i50677_2_) {
            super(StructurePieceType.T_3594_S, p_i50677_2_);
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            int i = p_230383_1_.n_1700_B(z_2963_s.n_1700_B.R_4764_Y, this.h_1847_R.n_1700_B, this.h_1847_R.R_4764_Y);
            c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B(this.h_1847_R.n_1700_B, i, this.h_1847_R.R_4764_Y);
            while (blockpos$mutable.getY() > 0) {
                K_4074_S blockstate = p_230383_1_.getBlockState(blockpos$mutable);
                K_4074_S blockstate1 = p_230383_1_.getBlockState((c_1514_x)blockpos$mutable.down());
                if (blockstate1 == a_3742_W.h_4320_q.multiplayerClientSuggestionProvider() || blockstate1 == a_3742_W.J_1907_R.multiplayerClientSuggestionProvider() || blockstate1 == a_3742_W.v_4262_N.multiplayerClientSuggestionProvider() || blockstate1 == a_3742_W.R_4764_Y.multiplayerClientSuggestionProvider() || blockstate1 == a_3742_W.P_1922_E.multiplayerClientSuggestionProvider()) {
                    K_4074_S blockstate2 = !blockstate.v_4262_N() && !this.n_1700_B(blockstate) ? blockstate : a_3742_W.A_4115_X.multiplayerClientSuggestionProvider();
                    for (b_257_Y direction : b_257_Y.values()) {
                        c_1514_x blockpos = blockpos$mutable.offset(direction);
                        K_4074_S blockstate3 = p_230383_1_.getBlockState(blockpos);
                        if (!blockstate3.v_4262_N() && !this.n_1700_B(blockstate3)) continue;
                        c_1514_x blockpos1 = blockpos.down();
                        K_4074_S blockstate4 = p_230383_1_.getBlockState(blockpos1);
                        if ((blockstate4.v_4262_N() || this.n_1700_B(blockstate4)) && direction != b_257_Y.J_1907_R) {
                            p_230383_1_.n_1700_B(blockpos, blockstate1, 3);
                            continue;
                        }
                        p_230383_1_.n_1700_B(blockpos, blockstate2, 3);
                    }
                    this.h_1847_R = new BoundingBox(blockpos$mutable.getX(), blockpos$mutable.getY(), blockpos$mutable.getZ(), blockpos$mutable.getX(), blockpos$mutable.getY(), blockpos$mutable.getZ());
                    return this.n_1700_B((ServerLevelAccessor)p_230383_1_, p_230383_5_, p_230383_4_, blockpos$mutable, o_4810_o.e_4240_b, (K_4074_S)null);
                }
                blockpos$mutable.J_1907_R(0, -1, 0);
            }
            return false;
        }

        private boolean n_1700_B(K_4074_S p_204295_1_) {
            return p_204295_1_ == a_3742_W.c_3005_b.multiplayerClientSuggestionProvider() || p_204295_1_ == a_3742_W.H_2857_Y.multiplayerClientSuggestionProvider();
        }
    }
}


