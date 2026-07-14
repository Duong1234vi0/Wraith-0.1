/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import lightning.product.E_3771_B;
import lightning.product.BlockGetter;
import lightning.product.J_3017_d;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.WorldGenLevel;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.Y_1387_d;
import lightning.product.ElderGuardian;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.b_2085_h;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.StructurePieceType;
import lightning.product.t_5_h;
import lightning.product.z_1753_f;

public class o_2945_w {

    static class Q_2552_b
    implements t_148_a {
        private Q_2552_b() {
        }

        @Override
        public boolean n_1700_B(h_1847_R definition) {
            return definition.R_4764_Y[b_257_Y.R_4764_Y.R_4764_Y()] && !definition.J_1907_R[b_257_Y.R_4764_Y.R_4764_Y()].G_564_y;
        }

        @Override
        public P_4830_p n_1700_B(b_257_Y p_175968_1_, h_1847_R p_175968_2_, Random p_175968_3_) {
            h_1847_R oceanmonumentpieces$roomdefinition = p_175968_2_;
            if (!p_175968_2_.R_4764_Y[b_257_Y.R_4764_Y.R_4764_Y()] || p_175968_2_.J_1907_R[b_257_Y.R_4764_Y.R_4764_Y()].G_564_y) {
                oceanmonumentpieces$roomdefinition = p_175968_2_.J_1907_R[b_257_Y.G_564_y.R_4764_Y()];
            }
            oceanmonumentpieces$roomdefinition.G_564_y = true;
            oceanmonumentpieces$roomdefinition.J_1907_R[b_257_Y.R_4764_Y.R_4764_Y()].G_564_y = true;
            return new P_1922_E(p_175968_1_, oceanmonumentpieces$roomdefinition);
        }
    }

    static class Y_259_p
    implements t_148_a {
        private Y_259_p() {
        }

        @Override
        public boolean n_1700_B(h_1847_R definition) {
            if (definition.R_4764_Y[b_257_Y.R_4764_Y.R_4764_Y()] && !definition.J_1907_R[b_257_Y.R_4764_Y.R_4764_Y()].G_564_y && definition.R_4764_Y[b_257_Y.J_1907_R.R_4764_Y()] && !definition.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()].G_564_y) {
                h_1847_R oceanmonumentpieces$roomdefinition = definition.J_1907_R[b_257_Y.R_4764_Y.R_4764_Y()];
                return oceanmonumentpieces$roomdefinition.R_4764_Y[b_257_Y.J_1907_R.R_4764_Y()] && !oceanmonumentpieces$roomdefinition.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()].G_564_y;
            }
            return false;
        }

        @Override
        public P_4830_p n_1700_B(b_257_Y p_175968_1_, h_1847_R p_175968_2_, Random p_175968_3_) {
            p_175968_2_.G_564_y = true;
            p_175968_2_.J_1907_R[b_257_Y.R_4764_Y.R_4764_Y()].G_564_y = true;
            p_175968_2_.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()].G_564_y = true;
            p_175968_2_.J_1907_R[b_257_Y.R_4764_Y.R_4764_Y()].J_1907_R[b_257_Y.J_1907_R.R_4764_Y()].G_564_y = true;
            return new G_564_y(p_175968_1_, p_175968_2_);
        }
    }

    static class Y_601_j
    implements t_148_a {
        private Y_601_j() {
        }

        @Override
        public boolean n_1700_B(h_1847_R definition) {
            return definition.R_4764_Y[b_257_Y.J_1907_R.R_4764_Y()] && !definition.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()].G_564_y;
        }

        @Override
        public P_4830_p n_1700_B(b_257_Y p_175968_1_, h_1847_R p_175968_2_, Random p_175968_3_) {
            p_175968_2_.G_564_y = true;
            p_175968_2_.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()].G_564_y = true;
            return new R_4764_Y(p_175968_1_, p_175968_2_);
        }
    }

    static class w_1457_N
    implements t_148_a {
        private w_1457_N() {
        }

        @Override
        public boolean n_1700_B(h_1847_R definition) {
            if (definition.R_4764_Y[b_257_Y.u_1723_Y.R_4764_Y()] && !definition.J_1907_R[b_257_Y.u_1723_Y.R_4764_Y()].G_564_y && definition.R_4764_Y[b_257_Y.J_1907_R.R_4764_Y()] && !definition.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()].G_564_y) {
                h_1847_R oceanmonumentpieces$roomdefinition = definition.J_1907_R[b_257_Y.u_1723_Y.R_4764_Y()];
                return oceanmonumentpieces$roomdefinition.R_4764_Y[b_257_Y.J_1907_R.R_4764_Y()] && !oceanmonumentpieces$roomdefinition.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()].G_564_y;
            }
            return false;
        }

        @Override
        public P_4830_p n_1700_B(b_257_Y p_175968_1_, h_1847_R p_175968_2_, Random p_175968_3_) {
            p_175968_2_.G_564_y = true;
            p_175968_2_.J_1907_R[b_257_Y.u_1723_Y.R_4764_Y()].G_564_y = true;
            p_175968_2_.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()].G_564_y = true;
            p_175968_2_.J_1907_R[b_257_Y.u_1723_Y.R_4764_Y()].J_1907_R[b_257_Y.J_1907_R.R_4764_Y()].G_564_y = true;
            return new J_1907_R(p_175968_1_, p_175968_2_);
        }
    }

    static class multiplayerClientSuggestionProvider
    implements t_148_a {
        private multiplayerClientSuggestionProvider() {
        }

        @Override
        public boolean n_1700_B(h_1847_R definition) {
            return definition.R_4764_Y[b_257_Y.u_1723_Y.R_4764_Y()] && !definition.J_1907_R[b_257_Y.u_1723_Y.R_4764_Y()].G_564_y;
        }

        @Override
        public P_4830_p n_1700_B(b_257_Y p_175968_1_, h_1847_R p_175968_2_, Random p_175968_3_) {
            p_175968_2_.G_564_y = true;
            p_175968_2_.J_1907_R[b_257_Y.u_1723_Y.R_4764_Y()].G_564_y = true;
            return new n_1700_B(p_175968_1_, p_175968_2_);
        }
    }

    public static class t_1786_h
    extends P_4830_p {
        private int M_182_A;

        public t_1786_h(b_257_Y p_i45585_1_, BoundingBox p_i45585_2_, int p_i45585_3_) {
            super(StructurePieceType.N_2525_X, p_i45585_1_, p_i45585_2_);
            this.M_182_A = p_i45585_3_ & 1;
        }

        public t_1786_h(b_2085_h p_i50643_1_, U_2912_j p_i50643_2_) {
            super(StructurePieceType.N_2525_X, p_i50643_2_);
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            if (this.M_182_A == 0) {
                for (int i = 0; i < 4; ++i) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 10 - i, 3 - i, 20 - i, 12 + i, 3 - i, 20, J_1907_R, J_1907_R, false);
                }
                this.n_1700_B(p_230383_1_, p_230383_5_, 7, 0, 6, 15, 0, 16, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 6, 0, 6, 6, 3, 20, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 16, 0, 6, 16, 3, 20, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 7, 1, 7, 7, 1, 20, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 15, 1, 7, 15, 1, 20, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 7, 1, 6, 9, 3, 6, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 13, 1, 6, 15, 3, 6, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 8, 1, 7, 9, 1, 7, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 13, 1, 7, 14, 1, 7, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 9, 0, 5, 13, 0, 5, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 10, 0, 7, 12, 0, 7, R_4764_Y, R_4764_Y, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 8, 0, 10, 8, 0, 12, R_4764_Y, R_4764_Y, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 14, 0, 10, 14, 0, 12, R_4764_Y, R_4764_Y, false);
                for (int i1 = 18; i1 >= 7; i1 -= 3) {
                    this.n_1700_B(p_230383_1_, P_1922_E, 6, 3, i1, p_230383_5_);
                    this.n_1700_B(p_230383_1_, P_1922_E, 16, 3, i1, p_230383_5_);
                }
                this.n_1700_B(p_230383_1_, P_1922_E, 10, 0, 10, p_230383_5_);
                this.n_1700_B(p_230383_1_, P_1922_E, 12, 0, 10, p_230383_5_);
                this.n_1700_B(p_230383_1_, P_1922_E, 10, 0, 12, p_230383_5_);
                this.n_1700_B(p_230383_1_, P_1922_E, 12, 0, 12, p_230383_5_);
                this.n_1700_B(p_230383_1_, P_1922_E, 8, 3, 6, p_230383_5_);
                this.n_1700_B(p_230383_1_, P_1922_E, 14, 3, 6, p_230383_5_);
                this.n_1700_B(p_230383_1_, J_1907_R, 4, 2, 4, p_230383_5_);
                this.n_1700_B(p_230383_1_, P_1922_E, 4, 1, 4, p_230383_5_);
                this.n_1700_B(p_230383_1_, J_1907_R, 4, 0, 4, p_230383_5_);
                this.n_1700_B(p_230383_1_, J_1907_R, 18, 2, 4, p_230383_5_);
                this.n_1700_B(p_230383_1_, P_1922_E, 18, 1, 4, p_230383_5_);
                this.n_1700_B(p_230383_1_, J_1907_R, 18, 0, 4, p_230383_5_);
                this.n_1700_B(p_230383_1_, J_1907_R, 4, 2, 18, p_230383_5_);
                this.n_1700_B(p_230383_1_, P_1922_E, 4, 1, 18, p_230383_5_);
                this.n_1700_B(p_230383_1_, J_1907_R, 4, 0, 18, p_230383_5_);
                this.n_1700_B(p_230383_1_, J_1907_R, 18, 2, 18, p_230383_5_);
                this.n_1700_B(p_230383_1_, P_1922_E, 18, 1, 18, p_230383_5_);
                this.n_1700_B(p_230383_1_, J_1907_R, 18, 0, 18, p_230383_5_);
                this.n_1700_B(p_230383_1_, J_1907_R, 9, 7, 20, p_230383_5_);
                this.n_1700_B(p_230383_1_, J_1907_R, 13, 7, 20, p_230383_5_);
                this.n_1700_B(p_230383_1_, p_230383_5_, 6, 0, 21, 7, 4, 21, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 15, 0, 21, 16, 4, 21, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 11, 2, 16);
            } else if (this.M_182_A == 1) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 9, 3, 18, 13, 3, 20, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 9, 0, 18, 9, 2, 18, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 13, 0, 18, 13, 2, 18, J_1907_R, J_1907_R, false);
                int j1 = 9;
                int j = 20;
                int k = 5;
                for (int l = 0; l < 2; ++l) {
                    this.n_1700_B(p_230383_1_, J_1907_R, j1, 6, 20, p_230383_5_);
                    this.n_1700_B(p_230383_1_, P_1922_E, j1, 5, 20, p_230383_5_);
                    this.n_1700_B(p_230383_1_, J_1907_R, j1, 4, 20, p_230383_5_);
                    j1 = 13;
                }
                this.n_1700_B(p_230383_1_, p_230383_5_, 7, 3, 7, 15, 3, 14, J_1907_R, J_1907_R, false);
                j1 = 10;
                for (int k1 = 0; k1 < 2; ++k1) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, j1, 0, 10, j1, 6, 10, J_1907_R, J_1907_R, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, j1, 0, 12, j1, 6, 12, J_1907_R, J_1907_R, false);
                    this.n_1700_B(p_230383_1_, P_1922_E, j1, 0, 10, p_230383_5_);
                    this.n_1700_B(p_230383_1_, P_1922_E, j1, 0, 12, p_230383_5_);
                    this.n_1700_B(p_230383_1_, P_1922_E, j1, 4, 10, p_230383_5_);
                    this.n_1700_B(p_230383_1_, P_1922_E, j1, 4, 12, p_230383_5_);
                    j1 = 12;
                }
                j1 = 8;
                for (int l1 = 0; l1 < 2; ++l1) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, j1, 0, 7, j1, 2, 7, J_1907_R, J_1907_R, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, j1, 0, 14, j1, 2, 14, J_1907_R, J_1907_R, false);
                    j1 = 14;
                }
                this.n_1700_B(p_230383_1_, p_230383_5_, 8, 3, 8, 8, 3, 13, R_4764_Y, R_4764_Y, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 14, 3, 8, 14, 3, 13, R_4764_Y, R_4764_Y, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 11, 5, 13);
            }
            return true;
        }
    }

    public static class M_182_A
    extends P_4830_p {
        public M_182_A(b_257_Y p_i50644_1_, h_1847_R p_i50644_2_) {
            super(StructurePieceType.H_1990_U, 1, p_i50644_1_, p_i50644_2_, 1, 1, 1);
        }

        public M_182_A(b_2085_h p_i50645_1_, U_2912_j p_i50645_2_) {
            super(StructurePieceType.H_1990_U, p_i50645_2_);
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            if (this.M_588_G.n_1700_B / 25 > 0) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, this.M_588_G.R_4764_Y[b_257_Y.n_1700_B.R_4764_Y()]);
            }
            if (this.M_588_G.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()] == null) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 4, 1, 6, 4, 6, n_1700_B);
            }
            for (int i = 1; i <= 6; ++i) {
                for (int j = 1; j <= 6; ++j) {
                    if (p_230383_4_.nextInt(3) == 0) continue;
                    int k = 2 + (p_230383_4_.nextInt(4) == 0 ? 0 : 1);
                    K_4074_S blockstate = a_3742_W.UploadStatus.multiplayerClientSuggestionProvider();
                    this.n_1700_B(p_230383_1_, p_230383_5_, i, k, j, i, 3, j, blockstate, blockstate, false);
                }
            }
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 0, 0, 1, 7, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 7, 1, 0, 7, 1, 7, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, 0, 6, 1, 0, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, 7, 6, 1, 7, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 0, 0, 2, 7, R_4764_Y, R_4764_Y, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 7, 2, 0, 7, 2, 7, R_4764_Y, R_4764_Y, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 2, 0, 6, 2, 0, R_4764_Y, R_4764_Y, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 2, 7, 6, 2, 7, R_4764_Y, R_4764_Y, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, 0, 0, 3, 7, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 7, 3, 0, 7, 3, 7, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 3, 0, 6, 3, 0, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 3, 7, 6, 3, 7, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 3, 0, 2, 4, R_4764_Y, R_4764_Y, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 7, 1, 3, 7, 2, 4, R_4764_Y, R_4764_Y, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 0, 4, 2, 0, R_4764_Y, R_4764_Y, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 7, 4, 2, 7, R_4764_Y, R_4764_Y, false);
            if (this.M_588_G.R_4764_Y[b_257_Y.G_564_y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 0, 4, 2, 0);
            }
            return true;
        }
    }

    public static class Q_4569_t
    extends P_4830_p {
        private int M_182_A;

        public Q_4569_t(b_257_Y p_i45587_1_, h_1847_R p_i45587_2_, Random p_i45587_3_) {
            super(StructurePieceType.Z_976_R, 1, p_i45587_1_, p_i45587_2_, 1, 1, 1);
            this.M_182_A = p_i45587_3_.nextInt(3);
        }

        public Q_4569_t(b_2085_h p_i50646_1_, U_2912_j p_i50646_2_) {
            super(StructurePieceType.Z_976_R, p_i50646_2_);
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            boolean flag;
            if (this.M_588_G.n_1700_B / 25 > 0) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, this.M_588_G.R_4764_Y[b_257_Y.n_1700_B.R_4764_Y()]);
            }
            if (this.M_588_G.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()] == null) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 4, 1, 6, 4, 6, n_1700_B);
            }
            boolean bl = flag = this.M_182_A != 0 && p_230383_4_.nextBoolean() && !this.M_588_G.R_4764_Y[b_257_Y.n_1700_B.R_4764_Y()] && !this.M_588_G.R_4764_Y[b_257_Y.J_1907_R.R_4764_Y()] && this.M_588_G.R_4764_Y() > 1;
            if (this.M_182_A == 0) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 0, 2, 1, 2, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, 0, 2, 3, 2, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 0, 0, 2, 2, n_1700_B, n_1700_B, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 2, 0, 2, 2, 0, n_1700_B, n_1700_B, false);
                this.n_1700_B(p_230383_1_, P_1922_E, 1, 2, 1, p_230383_5_);
                this.n_1700_B(p_230383_1_, p_230383_5_, 5, 1, 0, 7, 1, 2, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 5, 3, 0, 7, 3, 2, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 7, 2, 0, 7, 2, 2, n_1700_B, n_1700_B, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 5, 2, 0, 6, 2, 0, n_1700_B, n_1700_B, false);
                this.n_1700_B(p_230383_1_, P_1922_E, 6, 2, 1, p_230383_5_);
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 5, 2, 1, 7, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, 5, 2, 3, 7, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 5, 0, 2, 7, n_1700_B, n_1700_B, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 2, 7, 2, 2, 7, n_1700_B, n_1700_B, false);
                this.n_1700_B(p_230383_1_, P_1922_E, 1, 2, 6, p_230383_5_);
                this.n_1700_B(p_230383_1_, p_230383_5_, 5, 1, 5, 7, 1, 7, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 5, 3, 5, 7, 3, 7, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 7, 2, 5, 7, 2, 7, n_1700_B, n_1700_B, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 5, 2, 7, 6, 2, 7, n_1700_B, n_1700_B, false);
                this.n_1700_B(p_230383_1_, P_1922_E, 6, 2, 6, p_230383_5_);
                if (this.M_588_G.R_4764_Y[b_257_Y.G_564_y.R_4764_Y()]) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 3, 3, 0, 4, 3, 0, J_1907_R, J_1907_R, false);
                } else {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 3, 3, 0, 4, 3, 1, J_1907_R, J_1907_R, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 3, 2, 0, 4, 2, 0, n_1700_B, n_1700_B, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 0, 4, 1, 1, J_1907_R, J_1907_R, false);
                }
                if (this.M_588_G.R_4764_Y[b_257_Y.R_4764_Y.R_4764_Y()]) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 3, 3, 7, 4, 3, 7, J_1907_R, J_1907_R, false);
                } else {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 3, 3, 6, 4, 3, 7, J_1907_R, J_1907_R, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 3, 2, 7, 4, 2, 7, n_1700_B, n_1700_B, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 6, 4, 1, 7, J_1907_R, J_1907_R, false);
                }
                if (this.M_588_G.R_4764_Y[b_257_Y.P_1922_E.R_4764_Y()]) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, 3, 0, 3, 4, J_1907_R, J_1907_R, false);
                } else {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, 3, 1, 3, 4, J_1907_R, J_1907_R, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 3, 0, 2, 4, n_1700_B, n_1700_B, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 3, 1, 1, 4, J_1907_R, J_1907_R, false);
                }
                if (this.M_588_G.R_4764_Y[b_257_Y.u_1723_Y.R_4764_Y()]) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 7, 3, 3, 7, 3, 4, J_1907_R, J_1907_R, false);
                } else {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 6, 3, 3, 7, 3, 4, J_1907_R, J_1907_R, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 7, 2, 3, 7, 2, 4, n_1700_B, n_1700_B, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 6, 1, 3, 7, 1, 4, J_1907_R, J_1907_R, false);
                }
            } else if (this.M_182_A == 1) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 2, 1, 2, 2, 3, 2, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 2, 1, 5, 2, 3, 5, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 5, 1, 5, 5, 3, 5, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 5, 1, 2, 5, 3, 2, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, P_1922_E, 2, 2, 2, p_230383_5_);
                this.n_1700_B(p_230383_1_, P_1922_E, 2, 2, 5, p_230383_5_);
                this.n_1700_B(p_230383_1_, P_1922_E, 5, 2, 5, p_230383_5_);
                this.n_1700_B(p_230383_1_, P_1922_E, 5, 2, 2, p_230383_5_);
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 0, 1, 3, 0, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 1, 0, 3, 1, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 7, 1, 3, 7, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 6, 0, 3, 6, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 6, 1, 7, 7, 3, 7, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 7, 1, 6, 7, 3, 6, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 6, 1, 0, 7, 3, 0, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 7, 1, 1, 7, 3, 1, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, n_1700_B, 1, 2, 0, p_230383_5_);
                this.n_1700_B(p_230383_1_, n_1700_B, 0, 2, 1, p_230383_5_);
                this.n_1700_B(p_230383_1_, n_1700_B, 1, 2, 7, p_230383_5_);
                this.n_1700_B(p_230383_1_, n_1700_B, 0, 2, 6, p_230383_5_);
                this.n_1700_B(p_230383_1_, n_1700_B, 6, 2, 7, p_230383_5_);
                this.n_1700_B(p_230383_1_, n_1700_B, 7, 2, 6, p_230383_5_);
                this.n_1700_B(p_230383_1_, n_1700_B, 6, 2, 0, p_230383_5_);
                this.n_1700_B(p_230383_1_, n_1700_B, 7, 2, 1, p_230383_5_);
                if (!this.M_588_G.R_4764_Y[b_257_Y.G_564_y.R_4764_Y()]) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 1, 3, 0, 6, 3, 0, J_1907_R, J_1907_R, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 1, 2, 0, 6, 2, 0, n_1700_B, n_1700_B, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, 0, 6, 1, 0, J_1907_R, J_1907_R, false);
                }
                if (!this.M_588_G.R_4764_Y[b_257_Y.R_4764_Y.R_4764_Y()]) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 1, 3, 7, 6, 3, 7, J_1907_R, J_1907_R, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 1, 2, 7, 6, 2, 7, n_1700_B, n_1700_B, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, 7, 6, 1, 7, J_1907_R, J_1907_R, false);
                }
                if (!this.M_588_G.R_4764_Y[b_257_Y.P_1922_E.R_4764_Y()]) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, 1, 0, 3, 6, J_1907_R, J_1907_R, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 1, 0, 2, 6, n_1700_B, n_1700_B, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 1, 0, 1, 6, J_1907_R, J_1907_R, false);
                }
                if (!this.M_588_G.R_4764_Y[b_257_Y.u_1723_Y.R_4764_Y()]) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 7, 3, 1, 7, 3, 6, J_1907_R, J_1907_R, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 7, 2, 1, 7, 2, 6, n_1700_B, n_1700_B, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 7, 1, 1, 7, 1, 6, J_1907_R, J_1907_R, false);
                }
            } else if (this.M_182_A == 2) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 0, 0, 1, 7, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 7, 1, 0, 7, 1, 7, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, 0, 6, 1, 0, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, 7, 6, 1, 7, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 0, 0, 2, 7, R_4764_Y, R_4764_Y, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 7, 2, 0, 7, 2, 7, R_4764_Y, R_4764_Y, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 2, 0, 6, 2, 0, R_4764_Y, R_4764_Y, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 2, 7, 6, 2, 7, R_4764_Y, R_4764_Y, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, 0, 0, 3, 7, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 7, 3, 0, 7, 3, 7, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 3, 0, 6, 3, 0, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 3, 7, 6, 3, 7, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 3, 0, 2, 4, R_4764_Y, R_4764_Y, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 7, 1, 3, 7, 2, 4, R_4764_Y, R_4764_Y, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 0, 4, 2, 0, R_4764_Y, R_4764_Y, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 7, 4, 2, 7, R_4764_Y, R_4764_Y, false);
                if (this.M_588_G.R_4764_Y[b_257_Y.G_564_y.R_4764_Y()]) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 0, 4, 2, 0);
                }
                if (this.M_588_G.R_4764_Y[b_257_Y.R_4764_Y.R_4764_Y()]) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 7, 4, 2, 7);
                }
                if (this.M_588_G.R_4764_Y[b_257_Y.P_1922_E.R_4764_Y()]) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 3, 0, 2, 4);
                }
                if (this.M_588_G.R_4764_Y[b_257_Y.u_1723_Y.R_4764_Y()]) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 7, 1, 3, 7, 2, 4);
                }
            }
            if (flag) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 3, 4, 1, 4, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 3, 2, 3, 4, 2, 4, n_1700_B, n_1700_B, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 3, 3, 3, 4, 3, 4, J_1907_R, J_1907_R, false);
            }
            return true;
        }
    }

    static class h_1847_R {
        private final int n_1700_B;
        private final h_1847_R[] J_1907_R = new h_1847_R[6];
        private final boolean[] R_4764_Y = new boolean[6];
        private boolean G_564_y;
        private boolean P_1922_E;
        private int u_1723_Y;

        public h_1847_R(int p_i45584_1_) {
            this.n_1700_B = p_i45584_1_;
        }

        public void n_1700_B(b_257_Y p_175957_1_, h_1847_R p_175957_2_) {
            this.J_1907_R[p_175957_1_.R_4764_Y()] = p_175957_2_;
            p_175957_2_.J_1907_R[p_175957_1_.u_1723_Y().R_4764_Y()] = this;
        }

        public void n_1700_B() {
            for (int i = 0; i < 6; ++i) {
                this.R_4764_Y[i] = this.J_1907_R[i] != null;
            }
        }

        public boolean n_1700_B(int p_175959_1_) {
            if (this.P_1922_E) {
                return true;
            }
            this.u_1723_Y = p_175959_1_;
            for (int i = 0; i < 6; ++i) {
                if (this.J_1907_R[i] == null || !this.R_4764_Y[i] || this.J_1907_R[i].u_1723_Y == p_175959_1_ || !this.J_1907_R[i].n_1700_B(p_175959_1_)) continue;
                return true;
            }
            return false;
        }

        public boolean J_1907_R() {
            return this.n_1700_B >= 75;
        }

        public int R_4764_Y() {
            int i = 0;
            for (int j = 0; j < 6; ++j) {
                if (!this.R_4764_Y[j]) continue;
                ++i;
            }
            return i;
        }
    }

    public static abstract class P_4830_p
    extends E_3771_B {
        protected static final K_4074_S n_1700_B = a_3742_W.z_2311_U.multiplayerClientSuggestionProvider();
        protected static final K_4074_S J_1907_R = a_3742_W.Q_1082_O.multiplayerClientSuggestionProvider();
        protected static final K_4074_S R_4764_Y = a_3742_W.G_3540_E.multiplayerClientSuggestionProvider();
        protected static final K_4074_S G_564_y = J_1907_R;
        protected static final K_4074_S P_1922_E = a_3742_W.T_33_Q.multiplayerClientSuggestionProvider();
        protected static final K_4074_S u_1723_Y = a_3742_W.c_3005_b.multiplayerClientSuggestionProvider();
        protected static final Set<T_2915_h> v_4262_N = ImmutableSet.builder().add((Object)a_3742_W.O_1795_e).add((Object)a_3742_W.ServerHelper).add((Object)a_3742_W.G_4691_Q).add((Object)u_1723_Y.J_1907_R()).build();
        protected static final int w_1484_f = P_4830_p.J_1907_R(2, 0, 0);
        protected static final int t_148_a = P_4830_p.J_1907_R(2, 2, 0);
        protected static final int s_956_w = P_4830_p.J_1907_R(0, 1, 0);
        protected static final int u_2550_I = P_4830_p.J_1907_R(4, 1, 0);
        protected h_1847_R M_588_G;

        protected static final int J_1907_R(int p_175820_0_, int p_175820_1_, int p_175820_2_) {
            return p_175820_1_ * 25 + p_175820_2_ * 5 + p_175820_0_;
        }

        public P_4830_p(StructurePieceType p_i50647_1_, int p_i50647_2_) {
            super(p_i50647_1_, p_i50647_2_);
        }

        public P_4830_p(StructurePieceType p_i50648_1_, b_257_Y p_i50648_2_, BoundingBox p_i50648_3_) {
            super(p_i50648_1_, 1);
            this.n_1700_B(p_i50648_2_);
            this.h_1847_R = p_i50648_3_;
        }

        protected P_4830_p(StructurePieceType p_i50649_1_, int p_i50649_2_, b_257_Y p_i50649_3_, h_1847_R p_i50649_4_, int p_i50649_5_, int p_i50649_6_, int p_i50649_7_) {
            super(p_i50649_1_, p_i50649_2_);
            this.n_1700_B(p_i50649_3_);
            this.M_588_G = p_i50649_4_;
            int i = p_i50649_4_.n_1700_B;
            int j = i % 5;
            int k = i / 5 % 5;
            int l = i / 25;
            this.h_1847_R = p_i50649_3_ != b_257_Y.R_4764_Y && p_i50649_3_ != b_257_Y.G_564_y ? new BoundingBox(0, 0, 0, p_i50649_7_ * 8 - 1, p_i50649_6_ * 4 - 1, p_i50649_5_ * 8 - 1) : new BoundingBox(0, 0, 0, p_i50649_5_ * 8 - 1, p_i50649_6_ * 4 - 1, p_i50649_7_ * 8 - 1);
            switch (p_i50649_3_) {
                case R_4764_Y: {
                    this.h_1847_R.n_1700_B(j * 8, l * 4, -(k + p_i50649_7_) * 8 + 1);
                    break;
                }
                case G_564_y: {
                    this.h_1847_R.n_1700_B(j * 8, l * 4, k * 8);
                    break;
                }
                case P_1922_E: {
                    this.h_1847_R.n_1700_B(-(k + p_i50649_7_) * 8 + 1, l * 4, j * 8);
                    break;
                }
                default: {
                    this.h_1847_R.n_1700_B(k * 8, l * 4, j * 8);
                }
            }
        }

        public P_4830_p(StructurePieceType p_i50650_1_, U_2912_j p_i50650_2_) {
            super(p_i50650_1_, p_i50650_2_);
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
        }

        protected void n_1700_B(WorldGenLevel worldIn, BoundingBox boundingBoxIn, int x1, int y1, int z1, int x2, int y2, int z2) {
            for (int i = y1; i <= y2; ++i) {
                for (int j = x1; j <= x2; ++j) {
                    for (int k = z1; k <= z2; ++k) {
                        K_4074_S blockstate = this.n_1700_B((BlockGetter)worldIn, j, i, k, boundingBoxIn);
                        if (v_4262_N.contains(blockstate.J_1907_R())) continue;
                        if (this.n_1700_B(i) >= worldIn.d_2461_k() && blockstate != u_1723_Y) {
                            this.n_1700_B(worldIn, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), j, i, k, boundingBoxIn);
                            continue;
                        }
                        this.n_1700_B(worldIn, u_1723_Y, j, i, k, boundingBoxIn);
                    }
                }
            }
        }

        protected void n_1700_B(WorldGenLevel worldIn, BoundingBox p_175821_2_, int x, int z, boolean hasOpeningDownwards) {
            if (hasOpeningDownwards) {
                this.n_1700_B(worldIn, p_175821_2_, x + 0, 0, z + 0, x + 2, 0, z + 8 - 1, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175821_2_, x + 5, 0, z + 0, x + 8 - 1, 0, z + 8 - 1, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175821_2_, x + 3, 0, z + 0, x + 4, 0, z + 2, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175821_2_, x + 3, 0, z + 5, x + 4, 0, z + 8 - 1, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175821_2_, x + 3, 0, z + 2, x + 4, 0, z + 2, J_1907_R, J_1907_R, false);
                this.n_1700_B(worldIn, p_175821_2_, x + 3, 0, z + 5, x + 4, 0, z + 5, J_1907_R, J_1907_R, false);
                this.n_1700_B(worldIn, p_175821_2_, x + 2, 0, z + 3, x + 2, 0, z + 4, J_1907_R, J_1907_R, false);
                this.n_1700_B(worldIn, p_175821_2_, x + 5, 0, z + 3, x + 5, 0, z + 4, J_1907_R, J_1907_R, false);
            } else {
                this.n_1700_B(worldIn, p_175821_2_, x + 0, 0, z + 0, x + 8 - 1, 0, z + 8 - 1, n_1700_B, n_1700_B, false);
            }
        }

        protected void n_1700_B(WorldGenLevel worldIn, BoundingBox p_175819_2_, int p_175819_3_, int p_175819_4_, int p_175819_5_, int p_175819_6_, int p_175819_7_, int p_175819_8_, K_4074_S p_175819_9_) {
            for (int i = p_175819_4_; i <= p_175819_7_; ++i) {
                for (int j = p_175819_3_; j <= p_175819_6_; ++j) {
                    for (int k = p_175819_5_; k <= p_175819_8_; ++k) {
                        if (this.n_1700_B((BlockGetter)worldIn, j, i, k, p_175819_2_) != u_1723_Y) continue;
                        this.n_1700_B(worldIn, p_175819_9_, j, i, k, p_175819_2_);
                    }
                }
            }
        }

        protected boolean n_1700_B(BoundingBox p_175818_1_, int p_175818_2_, int p_175818_3_, int p_175818_4_, int p_175818_5_) {
            int i = this.n_1700_B(p_175818_2_, p_175818_3_);
            int j = this.J_1907_R(p_175818_2_, p_175818_3_);
            int k = this.n_1700_B(p_175818_4_, p_175818_5_);
            int l = this.J_1907_R(p_175818_4_, p_175818_5_);
            return p_175818_1_.n_1700_B(Math.min(i, k), Math.min(j, l), Math.max(i, k), Math.max(j, l));
        }

        protected boolean n_1700_B(WorldGenLevel worldIn, BoundingBox p_175817_2_, int p_175817_3_, int p_175817_4_, int p_175817_5_) {
            int k;
            int j;
            int i = this.n_1700_B(p_175817_3_, p_175817_5_);
            if (p_175817_2_.J_1907_R(new c_1514_x(i, j = this.n_1700_B(p_175817_4_), k = this.J_1907_R(p_175817_3_, p_175817_5_)))) {
                ElderGuardian elderguardianentity = t_5_h.multiplayerClientSuggestionProvider.n_1700_B(worldIn.J_1907_R());
                elderguardianentity.n_1700_B(elderguardianentity.L_1733_J());
                elderguardianentity.J_1907_R((double)i + 0.5, j, (double)k + 0.5, 0.0f, 0.0f);
                elderguardianentity.n_1700_B(worldIn, worldIn.J_1907_R(elderguardianentity.b_2312_j()), a_3160_D.G_564_y, (V_3157_k)null, null);
                worldIn.n_1700_B(elderguardianentity);
                return true;
            }
            return false;
        }
    }

    public static class M_588_G
    extends P_4830_p {
        public M_588_G(b_257_Y p_i45591_1_, BoundingBox p_i45591_2_) {
            super(StructurePieceType.X_933_l, p_i45591_1_, p_i45591_2_);
        }

        public M_588_G(b_2085_h p_i50651_1_, U_2912_j p_i50651_2_) {
            super(StructurePieceType.X_933_l, p_i50651_2_);
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, -1, 2, 11, -1, 11, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, -1, 0, 1, -1, 11, n_1700_B, n_1700_B, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 12, -1, 0, 13, -1, 11, n_1700_B, n_1700_B, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, -1, 0, 11, -1, 1, n_1700_B, n_1700_B, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, -1, 12, 11, -1, 13, n_1700_B, n_1700_B, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 0, 0, 0, 13, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 13, 0, 0, 13, 0, 13, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 0, 0, 12, 0, 0, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 0, 13, 12, 0, 13, J_1907_R, J_1907_R, false);
            for (int i = 2; i <= 11; i += 3) {
                this.n_1700_B(p_230383_1_, P_1922_E, 0, 0, i, p_230383_5_);
                this.n_1700_B(p_230383_1_, P_1922_E, 13, 0, i, p_230383_5_);
                this.n_1700_B(p_230383_1_, P_1922_E, i, 0, 0, p_230383_5_);
            }
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 0, 3, 4, 0, 9, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 9, 0, 3, 11, 0, 9, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 0, 9, 9, 0, 11, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, J_1907_R, 5, 0, 8, p_230383_5_);
            this.n_1700_B(p_230383_1_, J_1907_R, 8, 0, 8, p_230383_5_);
            this.n_1700_B(p_230383_1_, J_1907_R, 10, 0, 10, p_230383_5_);
            this.n_1700_B(p_230383_1_, J_1907_R, 3, 0, 10, p_230383_5_);
            this.n_1700_B(p_230383_1_, p_230383_5_, 3, 0, 3, 3, 0, 7, R_4764_Y, R_4764_Y, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 10, 0, 3, 10, 0, 7, R_4764_Y, R_4764_Y, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 0, 10, 7, 0, 10, R_4764_Y, R_4764_Y, false);
            int l = 3;
            for (int j = 0; j < 2; ++j) {
                for (int k = 2; k <= 8; k += 3) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, l, 0, k, l, 2, k, J_1907_R, J_1907_R, false);
                }
                l = 10;
            }
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 0, 10, 5, 2, 10, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 8, 0, 10, 8, 2, 10, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, -1, 7, 7, -1, 8, R_4764_Y, R_4764_Y, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, -1, 3, 7, -1, 4);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 1, 6);
            return true;
        }
    }

    public static class u_2550_I
    extends P_4830_p {
        public u_2550_I(b_257_Y p_i50663_1_, h_1847_R p_i50663_2_) {
            super(StructurePieceType.T_2506_i, 1, p_i50663_1_, p_i50663_2_, 2, 2, 2);
        }

        public u_2550_I(b_2085_h p_i50664_1_, U_2912_j p_i50664_2_) {
            super(StructurePieceType.T_2506_i, p_i50664_2_);
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 8, 0, 14, 8, 14, n_1700_B);
            int i = 7;
            K_4074_S blockstate = J_1907_R;
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 7, 0, 0, 7, 15, blockstate, blockstate, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 15, 7, 0, 15, 7, 15, blockstate, blockstate, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 7, 0, 15, 7, 0, blockstate, blockstate, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 7, 15, 14, 7, 15, blockstate, blockstate, false);
            for (int k = 1; k <= 6; ++k) {
                blockstate = J_1907_R;
                if (k == 2 || k == 6) {
                    blockstate = n_1700_B;
                }
                for (int j = 0; j <= 15; j += 15) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, j, k, 0, j, k, 1, blockstate, blockstate, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, j, k, 6, j, k, 9, blockstate, blockstate, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, j, k, 14, j, k, 15, blockstate, blockstate, false);
                }
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, k, 0, 1, k, 0, blockstate, blockstate, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 6, k, 0, 9, k, 0, blockstate, blockstate, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 14, k, 0, 14, k, 0, blockstate, blockstate, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, k, 15, 14, k, 15, blockstate, blockstate, false);
            }
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 3, 6, 9, 6, 9, R_4764_Y, R_4764_Y, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 7, 4, 7, 8, 5, 8, a_3742_W.y_2772_m.multiplayerClientSuggestionProvider(), a_3742_W.y_2772_m.multiplayerClientSuggestionProvider(), false);
            for (int l = 3; l <= 6; l += 3) {
                for (int i1 = 6; i1 <= 9; i1 += 3) {
                    this.n_1700_B(p_230383_1_, P_1922_E, i1, l, 6, p_230383_5_);
                    this.n_1700_B(p_230383_1_, P_1922_E, i1, l, 9, p_230383_5_);
                }
            }
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 1, 6, 5, 2, 6, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 1, 9, 5, 2, 9, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 10, 1, 6, 10, 2, 6, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 10, 1, 9, 10, 2, 9, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 1, 5, 6, 2, 5, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 9, 1, 5, 9, 2, 5, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 1, 10, 6, 2, 10, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 9, 1, 10, 9, 2, 10, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 2, 5, 5, 6, 5, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 2, 10, 5, 6, 10, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 10, 2, 5, 10, 6, 5, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 10, 2, 10, 10, 6, 10, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 7, 1, 5, 7, 6, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 10, 7, 1, 10, 7, 6, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 7, 9, 5, 7, 14, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 10, 7, 9, 10, 7, 14, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 7, 5, 6, 7, 5, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 7, 10, 6, 7, 10, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 9, 7, 5, 14, 7, 5, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 9, 7, 10, 14, 7, 10, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 1, 2, 2, 1, 3, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 2, 3, 1, 2, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 13, 1, 2, 13, 1, 3, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 12, 1, 2, 12, 1, 2, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 1, 12, 2, 1, 13, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 13, 3, 1, 13, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 13, 1, 12, 13, 1, 13, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 12, 1, 13, 12, 1, 13, J_1907_R, J_1907_R, false);
            return true;
        }
    }

    public static class s_956_w
    extends P_4830_p {
        private h_1847_R M_182_A;
        private h_1847_R t_1786_h;
        private final List<P_4830_p> multiplayerClientSuggestionProvider = Lists.newArrayList();

        public s_956_w(Random p_i45599_1_, int p_i45599_2_, int p_i45599_3_, b_257_Y p_i45599_4_) {
            super(StructurePieceType.G_624_v, 0);
            this.n_1700_B(p_i45599_4_);
            b_257_Y direction = this.t_148_a();
            this.h_1847_R = direction.h_1847_R() == b_257_Y.n_1700_B.R_4764_Y ? new BoundingBox(p_i45599_2_, 39, p_i45599_3_, p_i45599_2_ + 58 - 1, 61, p_i45599_3_ + 58 - 1) : new BoundingBox(p_i45599_2_, 39, p_i45599_3_, p_i45599_2_ + 58 - 1, 61, p_i45599_3_ + 58 - 1);
            List<h_1847_R> list = this.n_1700_B(p_i45599_1_);
            this.M_182_A.G_564_y = true;
            this.multiplayerClientSuggestionProvider.add(new u_1723_Y(direction, this.M_182_A));
            this.multiplayerClientSuggestionProvider.add(new u_2550_I(direction, this.t_1786_h));
            ArrayList list1 = Lists.newArrayList();
            list1.add(new w_1457_N());
            list1.add(new Y_259_p());
            list1.add(new Q_2552_b());
            list1.add(new multiplayerClientSuggestionProvider());
            list1.add(new Y_601_j());
            list1.add(new w_1484_f());
            list1.add(new v_4262_N());
            block0: for (h_1847_R oceanmonumentpieces$roomdefinition : list) {
                if (oceanmonumentpieces$roomdefinition.G_564_y || oceanmonumentpieces$roomdefinition.J_1907_R()) continue;
                for (Object oceanmonumentpieces$imonumentroomfithelper : list1) {
                    if (!oceanmonumentpieces$imonumentroomfithelper.n_1700_B(oceanmonumentpieces$roomdefinition)) continue;
                    this.multiplayerClientSuggestionProvider.add(oceanmonumentpieces$imonumentroomfithelper.n_1700_B(direction, oceanmonumentpieces$roomdefinition, p_i45599_1_));
                    continue block0;
                }
            }
            int j = this.h_1847_R.J_1907_R;
            int k = this.n_1700_B(9, 22);
            int l = this.J_1907_R(9, 22);
            for (P_4830_p oceanmonumentpieces$piece : this.multiplayerClientSuggestionProvider) {
                oceanmonumentpieces$piece.v_4262_N().n_1700_B(k, j, l);
            }
            BoundingBox mutableboundingbox1 = BoundingBox.n_1700_B(this.n_1700_B(1, 1), this.n_1700_B(1), this.J_1907_R(1, 1), this.n_1700_B(23, 21), this.n_1700_B(8), this.J_1907_R(23, 21));
            BoundingBox mutableboundingbox2 = BoundingBox.n_1700_B(this.n_1700_B(34, 1), this.n_1700_B(1), this.J_1907_R(34, 1), this.n_1700_B(56, 21), this.n_1700_B(8), this.J_1907_R(56, 21));
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(this.n_1700_B(22, 22), this.n_1700_B(13), this.J_1907_R(22, 22), this.n_1700_B(35, 35), this.n_1700_B(17), this.J_1907_R(35, 35));
            int i = p_i45599_1_.nextInt();
            this.multiplayerClientSuggestionProvider.add(new t_1786_h(direction, mutableboundingbox1, i++));
            this.multiplayerClientSuggestionProvider.add(new t_1786_h(direction, mutableboundingbox2, i++));
            this.multiplayerClientSuggestionProvider.add(new M_588_G(direction, mutableboundingbox));
        }

        public s_956_w(b_2085_h p_i50665_1_, U_2912_j p_i50665_2_) {
            super(StructurePieceType.G_624_v, p_i50665_2_);
        }

        private List<h_1847_R> n_1700_B(Random p_175836_1_) {
            h_1847_R[] aoceanmonumentpieces$roomdefinition = new h_1847_R[75];
            for (int i = 0; i < 5; ++i) {
                for (int j = 0; j < 4; ++j) {
                    boolean k = false;
                    int l = s_956_w.J_1907_R(i, 0, j);
                    aoceanmonumentpieces$roomdefinition[l] = new h_1847_R(l);
                }
            }
            for (int i2 = 0; i2 < 5; ++i2) {
                for (int l2 = 0; l2 < 4; ++l2) {
                    boolean k3 = true;
                    int j4 = s_956_w.J_1907_R(i2, 1, l2);
                    aoceanmonumentpieces$roomdefinition[j4] = new h_1847_R(j4);
                }
            }
            for (int j2 = 1; j2 < 4; ++j2) {
                for (int i3 = 0; i3 < 2; ++i3) {
                    int l3 = 2;
                    int k4 = s_956_w.J_1907_R(j2, 2, i3);
                    aoceanmonumentpieces$roomdefinition[k4] = new h_1847_R(k4);
                }
            }
            this.M_182_A = aoceanmonumentpieces$roomdefinition[w_1484_f];
            for (int k2 = 0; k2 < 5; ++k2) {
                for (int j3 = 0; j3 < 5; ++j3) {
                    for (int i4 = 0; i4 < 3; ++i4) {
                        int l4 = s_956_w.J_1907_R(k2, i4, j3);
                        if (aoceanmonumentpieces$roomdefinition[l4] == null) continue;
                        for (b_257_Y direction : b_257_Y.values()) {
                            int l1;
                            int i1 = k2 + direction.t_148_a();
                            int j1 = i4 + direction.s_956_w();
                            int k1 = j3 + direction.u_2550_I();
                            if (i1 < 0 || i1 >= 5 || k1 < 0 || k1 >= 5 || j1 < 0 || j1 >= 3 || aoceanmonumentpieces$roomdefinition[l1 = s_956_w.J_1907_R(i1, j1, k1)] == null) continue;
                            if (k1 == j3) {
                                aoceanmonumentpieces$roomdefinition[l4].n_1700_B(direction, aoceanmonumentpieces$roomdefinition[l1]);
                                continue;
                            }
                            aoceanmonumentpieces$roomdefinition[l4].n_1700_B(direction.u_1723_Y(), aoceanmonumentpieces$roomdefinition[l1]);
                        }
                    }
                }
            }
            h_1847_R oceanmonumentpieces$roomdefinition = new h_1847_R(1003);
            h_1847_R oceanmonumentpieces$roomdefinition1 = new h_1847_R(1001);
            h_1847_R oceanmonumentpieces$roomdefinition2 = new h_1847_R(1002);
            aoceanmonumentpieces$roomdefinition[t_148_a].n_1700_B(b_257_Y.J_1907_R, oceanmonumentpieces$roomdefinition);
            aoceanmonumentpieces$roomdefinition[s_956_w].n_1700_B(b_257_Y.G_564_y, oceanmonumentpieces$roomdefinition1);
            aoceanmonumentpieces$roomdefinition[u_2550_I].n_1700_B(b_257_Y.G_564_y, oceanmonumentpieces$roomdefinition2);
            oceanmonumentpieces$roomdefinition.G_564_y = true;
            oceanmonumentpieces$roomdefinition1.G_564_y = true;
            oceanmonumentpieces$roomdefinition2.G_564_y = true;
            this.M_182_A.P_1922_E = true;
            this.t_1786_h = aoceanmonumentpieces$roomdefinition[s_956_w.J_1907_R(p_175836_1_.nextInt(4), 0, 2)];
            this.t_1786_h.G_564_y = true;
            this.t_1786_h.J_1907_R[b_257_Y.u_1723_Y.R_4764_Y()].G_564_y = true;
            this.t_1786_h.J_1907_R[b_257_Y.R_4764_Y.R_4764_Y()].G_564_y = true;
            this.t_1786_h.J_1907_R[b_257_Y.u_1723_Y.R_4764_Y()].J_1907_R[b_257_Y.R_4764_Y.R_4764_Y()].G_564_y = true;
            this.t_1786_h.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()].G_564_y = true;
            this.t_1786_h.J_1907_R[b_257_Y.u_1723_Y.R_4764_Y()].J_1907_R[b_257_Y.J_1907_R.R_4764_Y()].G_564_y = true;
            this.t_1786_h.J_1907_R[b_257_Y.R_4764_Y.R_4764_Y()].J_1907_R[b_257_Y.J_1907_R.R_4764_Y()].G_564_y = true;
            this.t_1786_h.J_1907_R[b_257_Y.u_1723_Y.R_4764_Y()].J_1907_R[b_257_Y.R_4764_Y.R_4764_Y()].J_1907_R[b_257_Y.J_1907_R.R_4764_Y()].G_564_y = true;
            ArrayList list = Lists.newArrayList();
            for (h_1847_R oceanmonumentpieces$roomdefinition4 : aoceanmonumentpieces$roomdefinition) {
                if (oceanmonumentpieces$roomdefinition4 == null) continue;
                oceanmonumentpieces$roomdefinition4.n_1700_B();
                list.add(oceanmonumentpieces$roomdefinition4);
            }
            oceanmonumentpieces$roomdefinition.n_1700_B();
            Collections.shuffle(list, p_175836_1_);
            int i5 = 1;
            for (h_1847_R oceanmonumentpieces$roomdefinition3 : list) {
                int j5 = 0;
                for (int k5 = 0; j5 < 2 && k5 < 5; ++k5) {
                    int l5 = p_175836_1_.nextInt(6);
                    if (!oceanmonumentpieces$roomdefinition3.R_4764_Y[l5]) continue;
                    int i6 = b_257_Y.n_1700_B(l5).u_1723_Y().R_4764_Y();
                    oceanmonumentpieces$roomdefinition3.R_4764_Y[l5] = false;
                    oceanmonumentpieces$roomdefinition3.J_1907_R[l5].R_4764_Y[i6] = false;
                    if (oceanmonumentpieces$roomdefinition3.n_1700_B(i5++) && oceanmonumentpieces$roomdefinition3.J_1907_R[l5].n_1700_B(i5++)) {
                        ++j5;
                        continue;
                    }
                    oceanmonumentpieces$roomdefinition3.R_4764_Y[l5] = true;
                    oceanmonumentpieces$roomdefinition3.J_1907_R[l5].R_4764_Y[i6] = true;
                }
            }
            list.add(oceanmonumentpieces$roomdefinition);
            list.add(oceanmonumentpieces$roomdefinition1);
            list.add(oceanmonumentpieces$roomdefinition2);
            return list;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            int i = Math.max(p_230383_1_.d_2461_k(), 64) - this.h_1847_R.J_1907_R;
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 0, 58, i, 58);
            this.n_1700_B(false, 0, p_230383_1_, p_230383_4_, p_230383_5_);
            this.n_1700_B(true, 33, p_230383_1_, p_230383_4_, p_230383_5_);
            this.n_1700_B(p_230383_1_, p_230383_4_, p_230383_5_);
            this.J_1907_R(p_230383_1_, p_230383_4_, p_230383_5_);
            this.R_4764_Y(p_230383_1_, p_230383_4_, p_230383_5_);
            this.G_564_y(p_230383_1_, p_230383_4_, p_230383_5_);
            this.P_1922_E(p_230383_1_, p_230383_4_, p_230383_5_);
            this.u_1723_Y(p_230383_1_, p_230383_4_, p_230383_5_);
            for (int j = 0; j < 7; ++j) {
                int k = 0;
                while (k < 7) {
                    if (k == 0 && j == 3) {
                        k = 6;
                    }
                    int l = j * 9;
                    int i1 = k * 9;
                    for (int j1 = 0; j1 < 4; ++j1) {
                        for (int k1 = 0; k1 < 4; ++k1) {
                            this.n_1700_B(p_230383_1_, J_1907_R, l + j1, 0, i1 + k1, p_230383_5_);
                            this.J_1907_R(p_230383_1_, J_1907_R, l + j1, -1, i1 + k1, p_230383_5_);
                        }
                    }
                    if (j != 0 && j != 6) {
                        k += 6;
                        continue;
                    }
                    ++k;
                }
            }
            for (int l1 = 0; l1 < 5; ++l1) {
                this.n_1700_B(p_230383_1_, p_230383_5_, -1 - l1, 0 + l1 * 2, -1 - l1, -1 - l1, 23, 58 + l1);
                this.n_1700_B(p_230383_1_, p_230383_5_, 58 + l1, 0 + l1 * 2, -1 - l1, 58 + l1, 23, 58 + l1);
                this.n_1700_B(p_230383_1_, p_230383_5_, 0 - l1, 0 + l1 * 2, -1 - l1, 57 + l1, 23, -1 - l1);
                this.n_1700_B(p_230383_1_, p_230383_5_, 0 - l1, 0 + l1 * 2, 58 + l1, 57 + l1, 23, 58 + l1);
            }
            for (P_4830_p oceanmonumentpieces$piece : this.multiplayerClientSuggestionProvider) {
                if (!oceanmonumentpieces$piece.v_4262_N().n_1700_B(p_230383_5_)) continue;
                oceanmonumentpieces$piece.n_1700_B(p_230383_1_, p_230383_2_, p_230383_3_, p_230383_4_, p_230383_5_, p_230383_6_, p_230383_7_);
            }
            return true;
        }

        private void n_1700_B(boolean p_175840_1_, int p_175840_2_, WorldGenLevel worldIn, Random p_175840_4_, BoundingBox p_175840_5_) {
            int i = 24;
            if (this.n_1700_B(p_175840_5_, p_175840_2_, 0, p_175840_2_ + 23, 20)) {
                this.n_1700_B(worldIn, p_175840_5_, p_175840_2_ + 0, 0, 0, p_175840_2_ + 24, 0, 20, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175840_5_, p_175840_2_ + 0, 1, 0, p_175840_2_ + 24, 10, 20);
                for (int j = 0; j < 4; ++j) {
                    this.n_1700_B(worldIn, p_175840_5_, p_175840_2_ + j, j + 1, j, p_175840_2_ + j, j + 1, 20, J_1907_R, J_1907_R, false);
                    this.n_1700_B(worldIn, p_175840_5_, p_175840_2_ + j + 7, j + 5, j + 7, p_175840_2_ + j + 7, j + 5, 20, J_1907_R, J_1907_R, false);
                    this.n_1700_B(worldIn, p_175840_5_, p_175840_2_ + 17 - j, j + 5, j + 7, p_175840_2_ + 17 - j, j + 5, 20, J_1907_R, J_1907_R, false);
                    this.n_1700_B(worldIn, p_175840_5_, p_175840_2_ + 24 - j, j + 1, j, p_175840_2_ + 24 - j, j + 1, 20, J_1907_R, J_1907_R, false);
                    this.n_1700_B(worldIn, p_175840_5_, p_175840_2_ + j + 1, j + 1, j, p_175840_2_ + 23 - j, j + 1, j, J_1907_R, J_1907_R, false);
                    this.n_1700_B(worldIn, p_175840_5_, p_175840_2_ + j + 8, j + 5, j + 7, p_175840_2_ + 16 - j, j + 5, j + 7, J_1907_R, J_1907_R, false);
                }
                this.n_1700_B(worldIn, p_175840_5_, p_175840_2_ + 4, 4, 4, p_175840_2_ + 6, 4, 20, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175840_5_, p_175840_2_ + 7, 4, 4, p_175840_2_ + 17, 4, 6, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175840_5_, p_175840_2_ + 18, 4, 4, p_175840_2_ + 20, 4, 20, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175840_5_, p_175840_2_ + 11, 8, 11, p_175840_2_ + 13, 8, 20, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, G_564_y, p_175840_2_ + 12, 9, 12, p_175840_5_);
                this.n_1700_B(worldIn, G_564_y, p_175840_2_ + 12, 9, 15, p_175840_5_);
                this.n_1700_B(worldIn, G_564_y, p_175840_2_ + 12, 9, 18, p_175840_5_);
                int j1 = p_175840_2_ + (p_175840_1_ ? 19 : 5);
                int k = p_175840_2_ + (p_175840_1_ ? 5 : 19);
                for (int l = 20; l >= 5; l -= 3) {
                    this.n_1700_B(worldIn, G_564_y, j1, 5, l, p_175840_5_);
                }
                for (int k1 = 19; k1 >= 7; k1 -= 3) {
                    this.n_1700_B(worldIn, G_564_y, k, 5, k1, p_175840_5_);
                }
                for (int l1 = 0; l1 < 4; ++l1) {
                    int i1 = p_175840_1_ ? p_175840_2_ + 24 - (17 - l1 * 3) : p_175840_2_ + 17 - l1 * 3;
                    this.n_1700_B(worldIn, G_564_y, i1, 5, 5, p_175840_5_);
                }
                this.n_1700_B(worldIn, G_564_y, k, 5, 5, p_175840_5_);
                this.n_1700_B(worldIn, p_175840_5_, p_175840_2_ + 11, 1, 12, p_175840_2_ + 13, 7, 12, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175840_5_, p_175840_2_ + 12, 1, 11, p_175840_2_ + 12, 7, 13, n_1700_B, n_1700_B, false);
            }
        }

        private void n_1700_B(WorldGenLevel worldIn, Random p_175839_2_, BoundingBox p_175839_3_) {
            if (this.n_1700_B(p_175839_3_, 22, 5, 35, 17)) {
                this.n_1700_B(worldIn, p_175839_3_, 25, 0, 0, 32, 8, 20);
                for (int i = 0; i < 4; ++i) {
                    this.n_1700_B(worldIn, p_175839_3_, 24, 2, 5 + i * 4, 24, 4, 5 + i * 4, J_1907_R, J_1907_R, false);
                    this.n_1700_B(worldIn, p_175839_3_, 22, 4, 5 + i * 4, 23, 4, 5 + i * 4, J_1907_R, J_1907_R, false);
                    this.n_1700_B(worldIn, J_1907_R, 25, 5, 5 + i * 4, p_175839_3_);
                    this.n_1700_B(worldIn, J_1907_R, 26, 6, 5 + i * 4, p_175839_3_);
                    this.n_1700_B(worldIn, P_1922_E, 26, 5, 5 + i * 4, p_175839_3_);
                    this.n_1700_B(worldIn, p_175839_3_, 33, 2, 5 + i * 4, 33, 4, 5 + i * 4, J_1907_R, J_1907_R, false);
                    this.n_1700_B(worldIn, p_175839_3_, 34, 4, 5 + i * 4, 35, 4, 5 + i * 4, J_1907_R, J_1907_R, false);
                    this.n_1700_B(worldIn, J_1907_R, 32, 5, 5 + i * 4, p_175839_3_);
                    this.n_1700_B(worldIn, J_1907_R, 31, 6, 5 + i * 4, p_175839_3_);
                    this.n_1700_B(worldIn, P_1922_E, 31, 5, 5 + i * 4, p_175839_3_);
                    this.n_1700_B(worldIn, p_175839_3_, 27, 6, 5 + i * 4, 30, 6, 5 + i * 4, n_1700_B, n_1700_B, false);
                }
            }
        }

        private void J_1907_R(WorldGenLevel worldIn, Random p_175837_2_, BoundingBox p_175837_3_) {
            if (this.n_1700_B(p_175837_3_, 15, 20, 42, 21)) {
                this.n_1700_B(worldIn, p_175837_3_, 15, 0, 21, 42, 0, 21, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175837_3_, 26, 1, 21, 31, 3, 21);
                this.n_1700_B(worldIn, p_175837_3_, 21, 12, 21, 36, 12, 21, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175837_3_, 17, 11, 21, 40, 11, 21, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175837_3_, 16, 10, 21, 41, 10, 21, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175837_3_, 15, 7, 21, 42, 9, 21, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175837_3_, 16, 6, 21, 41, 6, 21, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175837_3_, 17, 5, 21, 40, 5, 21, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175837_3_, 21, 4, 21, 36, 4, 21, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175837_3_, 22, 3, 21, 26, 3, 21, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175837_3_, 31, 3, 21, 35, 3, 21, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175837_3_, 23, 2, 21, 25, 2, 21, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175837_3_, 32, 2, 21, 34, 2, 21, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175837_3_, 28, 4, 20, 29, 4, 21, J_1907_R, J_1907_R, false);
                this.n_1700_B(worldIn, J_1907_R, 27, 3, 21, p_175837_3_);
                this.n_1700_B(worldIn, J_1907_R, 30, 3, 21, p_175837_3_);
                this.n_1700_B(worldIn, J_1907_R, 26, 2, 21, p_175837_3_);
                this.n_1700_B(worldIn, J_1907_R, 31, 2, 21, p_175837_3_);
                this.n_1700_B(worldIn, J_1907_R, 25, 1, 21, p_175837_3_);
                this.n_1700_B(worldIn, J_1907_R, 32, 1, 21, p_175837_3_);
                for (int i = 0; i < 7; ++i) {
                    this.n_1700_B(worldIn, R_4764_Y, 28 - i, 6 + i, 21, p_175837_3_);
                    this.n_1700_B(worldIn, R_4764_Y, 29 + i, 6 + i, 21, p_175837_3_);
                }
                for (int j = 0; j < 4; ++j) {
                    this.n_1700_B(worldIn, R_4764_Y, 28 - j, 9 + j, 21, p_175837_3_);
                    this.n_1700_B(worldIn, R_4764_Y, 29 + j, 9 + j, 21, p_175837_3_);
                }
                this.n_1700_B(worldIn, R_4764_Y, 28, 12, 21, p_175837_3_);
                this.n_1700_B(worldIn, R_4764_Y, 29, 12, 21, p_175837_3_);
                for (int k = 0; k < 3; ++k) {
                    this.n_1700_B(worldIn, R_4764_Y, 22 - k * 2, 8, 21, p_175837_3_);
                    this.n_1700_B(worldIn, R_4764_Y, 22 - k * 2, 9, 21, p_175837_3_);
                    this.n_1700_B(worldIn, R_4764_Y, 35 + k * 2, 8, 21, p_175837_3_);
                    this.n_1700_B(worldIn, R_4764_Y, 35 + k * 2, 9, 21, p_175837_3_);
                }
                this.n_1700_B(worldIn, p_175837_3_, 15, 13, 21, 42, 15, 21);
                this.n_1700_B(worldIn, p_175837_3_, 15, 1, 21, 15, 6, 21);
                this.n_1700_B(worldIn, p_175837_3_, 16, 1, 21, 16, 5, 21);
                this.n_1700_B(worldIn, p_175837_3_, 17, 1, 21, 20, 4, 21);
                this.n_1700_B(worldIn, p_175837_3_, 21, 1, 21, 21, 3, 21);
                this.n_1700_B(worldIn, p_175837_3_, 22, 1, 21, 22, 2, 21);
                this.n_1700_B(worldIn, p_175837_3_, 23, 1, 21, 24, 1, 21);
                this.n_1700_B(worldIn, p_175837_3_, 42, 1, 21, 42, 6, 21);
                this.n_1700_B(worldIn, p_175837_3_, 41, 1, 21, 41, 5, 21);
                this.n_1700_B(worldIn, p_175837_3_, 37, 1, 21, 40, 4, 21);
                this.n_1700_B(worldIn, p_175837_3_, 36, 1, 21, 36, 3, 21);
                this.n_1700_B(worldIn, p_175837_3_, 33, 1, 21, 34, 1, 21);
                this.n_1700_B(worldIn, p_175837_3_, 35, 1, 21, 35, 2, 21);
            }
        }

        private void R_4764_Y(WorldGenLevel worldIn, Random p_175841_2_, BoundingBox p_175841_3_) {
            if (this.n_1700_B(p_175841_3_, 21, 21, 36, 36)) {
                this.n_1700_B(worldIn, p_175841_3_, 21, 0, 22, 36, 0, 36, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175841_3_, 21, 1, 22, 36, 23, 36);
                for (int i = 0; i < 4; ++i) {
                    this.n_1700_B(worldIn, p_175841_3_, 21 + i, 13 + i, 21 + i, 36 - i, 13 + i, 21 + i, J_1907_R, J_1907_R, false);
                    this.n_1700_B(worldIn, p_175841_3_, 21 + i, 13 + i, 36 - i, 36 - i, 13 + i, 36 - i, J_1907_R, J_1907_R, false);
                    this.n_1700_B(worldIn, p_175841_3_, 21 + i, 13 + i, 22 + i, 21 + i, 13 + i, 35 - i, J_1907_R, J_1907_R, false);
                    this.n_1700_B(worldIn, p_175841_3_, 36 - i, 13 + i, 22 + i, 36 - i, 13 + i, 35 - i, J_1907_R, J_1907_R, false);
                }
                this.n_1700_B(worldIn, p_175841_3_, 25, 16, 25, 32, 16, 32, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175841_3_, 25, 17, 25, 25, 19, 25, J_1907_R, J_1907_R, false);
                this.n_1700_B(worldIn, p_175841_3_, 32, 17, 25, 32, 19, 25, J_1907_R, J_1907_R, false);
                this.n_1700_B(worldIn, p_175841_3_, 25, 17, 32, 25, 19, 32, J_1907_R, J_1907_R, false);
                this.n_1700_B(worldIn, p_175841_3_, 32, 17, 32, 32, 19, 32, J_1907_R, J_1907_R, false);
                this.n_1700_B(worldIn, J_1907_R, 26, 20, 26, p_175841_3_);
                this.n_1700_B(worldIn, J_1907_R, 27, 21, 27, p_175841_3_);
                this.n_1700_B(worldIn, P_1922_E, 27, 20, 27, p_175841_3_);
                this.n_1700_B(worldIn, J_1907_R, 26, 20, 31, p_175841_3_);
                this.n_1700_B(worldIn, J_1907_R, 27, 21, 30, p_175841_3_);
                this.n_1700_B(worldIn, P_1922_E, 27, 20, 30, p_175841_3_);
                this.n_1700_B(worldIn, J_1907_R, 31, 20, 31, p_175841_3_);
                this.n_1700_B(worldIn, J_1907_R, 30, 21, 30, p_175841_3_);
                this.n_1700_B(worldIn, P_1922_E, 30, 20, 30, p_175841_3_);
                this.n_1700_B(worldIn, J_1907_R, 31, 20, 26, p_175841_3_);
                this.n_1700_B(worldIn, J_1907_R, 30, 21, 27, p_175841_3_);
                this.n_1700_B(worldIn, P_1922_E, 30, 20, 27, p_175841_3_);
                this.n_1700_B(worldIn, p_175841_3_, 28, 21, 27, 29, 21, 27, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175841_3_, 27, 21, 28, 27, 21, 29, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175841_3_, 28, 21, 30, 29, 21, 30, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175841_3_, 30, 21, 28, 30, 21, 29, n_1700_B, n_1700_B, false);
            }
        }

        private void G_564_y(WorldGenLevel worldIn, Random p_175835_2_, BoundingBox p_175835_3_) {
            if (this.n_1700_B(p_175835_3_, 0, 21, 6, 58)) {
                this.n_1700_B(worldIn, p_175835_3_, 0, 0, 21, 6, 0, 57, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175835_3_, 0, 1, 21, 6, 7, 57);
                this.n_1700_B(worldIn, p_175835_3_, 4, 4, 21, 6, 4, 53, n_1700_B, n_1700_B, false);
                for (int i = 0; i < 4; ++i) {
                    this.n_1700_B(worldIn, p_175835_3_, i, i + 1, 21, i, i + 1, 57 - i, J_1907_R, J_1907_R, false);
                }
                for (int j = 23; j < 53; j += 3) {
                    this.n_1700_B(worldIn, G_564_y, 5, 5, j, p_175835_3_);
                }
                this.n_1700_B(worldIn, G_564_y, 5, 5, 52, p_175835_3_);
                for (int k = 0; k < 4; ++k) {
                    this.n_1700_B(worldIn, p_175835_3_, k, k + 1, 21, k, k + 1, 57 - k, J_1907_R, J_1907_R, false);
                }
                this.n_1700_B(worldIn, p_175835_3_, 4, 1, 52, 6, 3, 52, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175835_3_, 5, 1, 51, 5, 3, 53, n_1700_B, n_1700_B, false);
            }
            if (this.n_1700_B(p_175835_3_, 51, 21, 58, 58)) {
                this.n_1700_B(worldIn, p_175835_3_, 51, 0, 21, 57, 0, 57, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175835_3_, 51, 1, 21, 57, 7, 57);
                this.n_1700_B(worldIn, p_175835_3_, 51, 4, 21, 53, 4, 53, n_1700_B, n_1700_B, false);
                for (int l = 0; l < 4; ++l) {
                    this.n_1700_B(worldIn, p_175835_3_, 57 - l, l + 1, 21, 57 - l, l + 1, 57 - l, J_1907_R, J_1907_R, false);
                }
                for (int i1 = 23; i1 < 53; i1 += 3) {
                    this.n_1700_B(worldIn, G_564_y, 52, 5, i1, p_175835_3_);
                }
                this.n_1700_B(worldIn, G_564_y, 52, 5, 52, p_175835_3_);
                this.n_1700_B(worldIn, p_175835_3_, 51, 1, 52, 53, 3, 52, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175835_3_, 52, 1, 51, 52, 3, 53, n_1700_B, n_1700_B, false);
            }
            if (this.n_1700_B(p_175835_3_, 0, 51, 57, 57)) {
                this.n_1700_B(worldIn, p_175835_3_, 7, 0, 51, 50, 0, 57, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175835_3_, 7, 1, 51, 50, 10, 57);
                for (int j1 = 0; j1 < 4; ++j1) {
                    this.n_1700_B(worldIn, p_175835_3_, j1 + 1, j1 + 1, 57 - j1, 56 - j1, j1 + 1, 57 - j1, J_1907_R, J_1907_R, false);
                }
            }
        }

        private void P_1922_E(WorldGenLevel worldIn, Random p_175842_2_, BoundingBox p_175842_3_) {
            if (this.n_1700_B(p_175842_3_, 7, 21, 13, 50)) {
                this.n_1700_B(worldIn, p_175842_3_, 7, 0, 21, 13, 0, 50, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175842_3_, 7, 1, 21, 13, 10, 50);
                this.n_1700_B(worldIn, p_175842_3_, 11, 8, 21, 13, 8, 53, n_1700_B, n_1700_B, false);
                for (int i = 0; i < 4; ++i) {
                    this.n_1700_B(worldIn, p_175842_3_, i + 7, i + 5, 21, i + 7, i + 5, 54, J_1907_R, J_1907_R, false);
                }
                for (int j = 21; j <= 45; j += 3) {
                    this.n_1700_B(worldIn, G_564_y, 12, 9, j, p_175842_3_);
                }
            }
            if (this.n_1700_B(p_175842_3_, 44, 21, 50, 54)) {
                this.n_1700_B(worldIn, p_175842_3_, 44, 0, 21, 50, 0, 50, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175842_3_, 44, 1, 21, 50, 10, 50);
                this.n_1700_B(worldIn, p_175842_3_, 44, 8, 21, 46, 8, 53, n_1700_B, n_1700_B, false);
                for (int k = 0; k < 4; ++k) {
                    this.n_1700_B(worldIn, p_175842_3_, 50 - k, k + 5, 21, 50 - k, k + 5, 54, J_1907_R, J_1907_R, false);
                }
                for (int l = 21; l <= 45; l += 3) {
                    this.n_1700_B(worldIn, G_564_y, 45, 9, l, p_175842_3_);
                }
            }
            if (this.n_1700_B(p_175842_3_, 8, 44, 49, 54)) {
                this.n_1700_B(worldIn, p_175842_3_, 14, 0, 44, 43, 0, 50, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175842_3_, 14, 1, 44, 43, 10, 50);
                for (int i1 = 12; i1 <= 45; i1 += 3) {
                    this.n_1700_B(worldIn, G_564_y, i1, 9, 45, p_175842_3_);
                    this.n_1700_B(worldIn, G_564_y, i1, 9, 52, p_175842_3_);
                    if (i1 != 12 && i1 != 18 && i1 != 24 && i1 != 33 && i1 != 39 && i1 != 45) continue;
                    this.n_1700_B(worldIn, G_564_y, i1, 9, 47, p_175842_3_);
                    this.n_1700_B(worldIn, G_564_y, i1, 9, 50, p_175842_3_);
                    this.n_1700_B(worldIn, G_564_y, i1, 10, 45, p_175842_3_);
                    this.n_1700_B(worldIn, G_564_y, i1, 10, 46, p_175842_3_);
                    this.n_1700_B(worldIn, G_564_y, i1, 10, 51, p_175842_3_);
                    this.n_1700_B(worldIn, G_564_y, i1, 10, 52, p_175842_3_);
                    this.n_1700_B(worldIn, G_564_y, i1, 11, 47, p_175842_3_);
                    this.n_1700_B(worldIn, G_564_y, i1, 11, 50, p_175842_3_);
                    this.n_1700_B(worldIn, G_564_y, i1, 12, 48, p_175842_3_);
                    this.n_1700_B(worldIn, G_564_y, i1, 12, 49, p_175842_3_);
                }
                for (int j1 = 0; j1 < 3; ++j1) {
                    this.n_1700_B(worldIn, p_175842_3_, 8 + j1, 5 + j1, 54, 49 - j1, 5 + j1, 54, n_1700_B, n_1700_B, false);
                }
                this.n_1700_B(worldIn, p_175842_3_, 11, 8, 54, 46, 8, 54, J_1907_R, J_1907_R, false);
                this.n_1700_B(worldIn, p_175842_3_, 14, 8, 44, 43, 8, 53, n_1700_B, n_1700_B, false);
            }
        }

        private void u_1723_Y(WorldGenLevel worldIn, Random p_175838_2_, BoundingBox p_175838_3_) {
            if (this.n_1700_B(p_175838_3_, 14, 21, 20, 43)) {
                this.n_1700_B(worldIn, p_175838_3_, 14, 0, 21, 20, 0, 43, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175838_3_, 14, 1, 22, 20, 14, 43);
                this.n_1700_B(worldIn, p_175838_3_, 18, 12, 22, 20, 12, 39, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175838_3_, 18, 12, 21, 20, 12, 21, J_1907_R, J_1907_R, false);
                for (int i = 0; i < 4; ++i) {
                    this.n_1700_B(worldIn, p_175838_3_, i + 14, i + 9, 21, i + 14, i + 9, 43 - i, J_1907_R, J_1907_R, false);
                }
                for (int j = 23; j <= 39; j += 3) {
                    this.n_1700_B(worldIn, G_564_y, 19, 13, j, p_175838_3_);
                }
            }
            if (this.n_1700_B(p_175838_3_, 37, 21, 43, 43)) {
                this.n_1700_B(worldIn, p_175838_3_, 37, 0, 21, 43, 0, 43, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175838_3_, 37, 1, 22, 43, 14, 43);
                this.n_1700_B(worldIn, p_175838_3_, 37, 12, 22, 39, 12, 39, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175838_3_, 37, 12, 21, 39, 12, 21, J_1907_R, J_1907_R, false);
                for (int k = 0; k < 4; ++k) {
                    this.n_1700_B(worldIn, p_175838_3_, 43 - k, k + 9, 21, 43 - k, k + 9, 43 - k, J_1907_R, J_1907_R, false);
                }
                for (int l = 23; l <= 39; l += 3) {
                    this.n_1700_B(worldIn, G_564_y, 38, 13, l, p_175838_3_);
                }
            }
            if (this.n_1700_B(p_175838_3_, 15, 37, 42, 43)) {
                this.n_1700_B(worldIn, p_175838_3_, 21, 0, 37, 36, 0, 43, n_1700_B, n_1700_B, false);
                this.n_1700_B(worldIn, p_175838_3_, 21, 1, 37, 36, 14, 43);
                this.n_1700_B(worldIn, p_175838_3_, 21, 12, 37, 36, 12, 39, n_1700_B, n_1700_B, false);
                for (int i1 = 0; i1 < 4; ++i1) {
                    this.n_1700_B(worldIn, p_175838_3_, 15 + i1, i1 + 9, 43 - i1, 42 - i1, i1 + 9, 43 - i1, J_1907_R, J_1907_R, false);
                }
                for (int j1 = 21; j1 <= 36; j1 += 3) {
                    this.n_1700_B(worldIn, G_564_y, j1, 13, 38, p_175838_3_);
                }
            }
        }
    }

    static interface t_148_a {
        public boolean n_1700_B(h_1847_R var1);

        public P_4830_p n_1700_B(b_257_Y var1, h_1847_R var2, Random var3);
    }

    static class w_1484_f
    implements t_148_a {
        private w_1484_f() {
        }

        @Override
        public boolean n_1700_B(h_1847_R definition) {
            return !definition.R_4764_Y[b_257_Y.P_1922_E.R_4764_Y()] && !definition.R_4764_Y[b_257_Y.u_1723_Y.R_4764_Y()] && !definition.R_4764_Y[b_257_Y.R_4764_Y.R_4764_Y()] && !definition.R_4764_Y[b_257_Y.G_564_y.R_4764_Y()] && !definition.R_4764_Y[b_257_Y.J_1907_R.R_4764_Y()];
        }

        @Override
        public P_4830_p n_1700_B(b_257_Y p_175968_1_, h_1847_R p_175968_2_, Random p_175968_3_) {
            p_175968_2_.G_564_y = true;
            return new M_182_A(p_175968_1_, p_175968_2_);
        }
    }

    static class v_4262_N
    implements t_148_a {
        private v_4262_N() {
        }

        @Override
        public boolean n_1700_B(h_1847_R definition) {
            return true;
        }

        @Override
        public P_4830_p n_1700_B(b_257_Y p_175968_1_, h_1847_R p_175968_2_, Random p_175968_3_) {
            p_175968_2_.G_564_y = true;
            return new Q_4569_t(p_175968_1_, p_175968_2_, p_175968_3_);
        }
    }

    public static class u_1723_Y
    extends P_4830_p {
        public u_1723_Y(b_257_Y p_i45592_1_, h_1847_R p_i45592_2_) {
            super(StructurePieceType.g_164_R, 1, p_i45592_1_, p_i45592_2_, 1, 1, 1);
        }

        public u_1723_Y(b_2085_h p_i50652_1_, U_2912_j p_i50652_2_) {
            super(StructurePieceType.g_164_R, p_i50652_2_);
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, 0, 2, 3, 7, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 3, 0, 7, 3, 7, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 0, 1, 2, 7, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 2, 0, 7, 2, 7, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 0, 0, 1, 7, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 7, 1, 0, 7, 1, 7, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 7, 7, 3, 7, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, 0, 2, 3, 0, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 1, 0, 6, 3, 0, J_1907_R, J_1907_R, false);
            if (this.M_588_G.R_4764_Y[b_257_Y.R_4764_Y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 7, 4, 2, 7);
            }
            if (this.M_588_G.R_4764_Y[b_257_Y.P_1922_E.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 3, 1, 2, 4);
            }
            if (this.M_588_G.R_4764_Y[b_257_Y.u_1723_Y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 6, 1, 3, 7, 2, 4);
            }
            return true;
        }
    }

    public static class P_1922_E
    extends P_4830_p {
        public P_1922_E(b_257_Y p_i50653_1_, h_1847_R p_i50653_2_) {
            super(StructurePieceType.B_1668_F, 1, p_i50653_1_, p_i50653_2_, 1, 1, 2);
        }

        public P_1922_E(b_2085_h p_i50654_1_, U_2912_j p_i50654_2_) {
            super(StructurePieceType.B_1668_F, p_i50654_2_);
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            h_1847_R oceanmonumentpieces$roomdefinition = this.M_588_G.J_1907_R[b_257_Y.R_4764_Y.R_4764_Y()];
            h_1847_R oceanmonumentpieces$roomdefinition1 = this.M_588_G;
            if (this.M_588_G.n_1700_B / 25 > 0) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 8, oceanmonumentpieces$roomdefinition.R_4764_Y[b_257_Y.n_1700_B.R_4764_Y()]);
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, oceanmonumentpieces$roomdefinition1.R_4764_Y[b_257_Y.n_1700_B.R_4764_Y()]);
            }
            if (oceanmonumentpieces$roomdefinition1.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()] == null) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 4, 1, 6, 4, 7, n_1700_B);
            }
            if (oceanmonumentpieces$roomdefinition.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()] == null) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 4, 8, 6, 4, 14, n_1700_B);
            }
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, 0, 0, 3, 15, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 7, 3, 0, 7, 3, 15, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 3, 0, 7, 3, 0, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 3, 15, 6, 3, 15, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 0, 0, 2, 15, n_1700_B, n_1700_B, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 7, 2, 0, 7, 2, 15, n_1700_B, n_1700_B, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 2, 0, 7, 2, 0, n_1700_B, n_1700_B, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 2, 15, 6, 2, 15, n_1700_B, n_1700_B, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 0, 0, 1, 15, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 7, 1, 0, 7, 1, 15, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, 0, 7, 1, 0, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, 15, 6, 1, 15, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, 1, 1, 1, 2, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 1, 1, 6, 1, 2, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 3, 1, 1, 3, 2, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 3, 1, 6, 3, 2, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, 13, 1, 1, 14, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 1, 13, 6, 1, 14, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 3, 13, 1, 3, 14, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 3, 13, 6, 3, 14, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 1, 6, 2, 3, 6, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 1, 6, 5, 3, 6, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 1, 9, 2, 3, 9, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 1, 9, 5, 3, 9, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 3, 2, 6, 4, 2, 6, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 3, 2, 9, 4, 2, 9, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 2, 7, 2, 2, 8, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 2, 7, 5, 2, 8, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, P_1922_E, 2, 2, 5, p_230383_5_);
            this.n_1700_B(p_230383_1_, P_1922_E, 5, 2, 5, p_230383_5_);
            this.n_1700_B(p_230383_1_, P_1922_E, 2, 2, 10, p_230383_5_);
            this.n_1700_B(p_230383_1_, P_1922_E, 5, 2, 10, p_230383_5_);
            this.n_1700_B(p_230383_1_, J_1907_R, 2, 3, 5, p_230383_5_);
            this.n_1700_B(p_230383_1_, J_1907_R, 5, 3, 5, p_230383_5_);
            this.n_1700_B(p_230383_1_, J_1907_R, 2, 3, 10, p_230383_5_);
            this.n_1700_B(p_230383_1_, J_1907_R, 5, 3, 10, p_230383_5_);
            if (oceanmonumentpieces$roomdefinition1.R_4764_Y[b_257_Y.G_564_y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 0, 4, 2, 0);
            }
            if (oceanmonumentpieces$roomdefinition1.R_4764_Y[b_257_Y.u_1723_Y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 7, 1, 3, 7, 2, 4);
            }
            if (oceanmonumentpieces$roomdefinition1.R_4764_Y[b_257_Y.P_1922_E.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 3, 0, 2, 4);
            }
            if (oceanmonumentpieces$roomdefinition.R_4764_Y[b_257_Y.R_4764_Y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 15, 4, 2, 15);
            }
            if (oceanmonumentpieces$roomdefinition.R_4764_Y[b_257_Y.P_1922_E.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 11, 0, 2, 12);
            }
            if (oceanmonumentpieces$roomdefinition.R_4764_Y[b_257_Y.u_1723_Y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 7, 1, 11, 7, 2, 12);
            }
            return true;
        }
    }

    public static class G_564_y
    extends P_4830_p {
        public G_564_y(b_257_Y p_i50655_1_, h_1847_R p_i50655_2_) {
            super(StructurePieceType.e_2887_G, 1, p_i50655_1_, p_i50655_2_, 1, 2, 2);
        }

        public G_564_y(b_2085_h p_i50656_1_, U_2912_j p_i50656_2_) {
            super(StructurePieceType.e_2887_G, p_i50656_2_);
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            h_1847_R oceanmonumentpieces$roomdefinition = this.M_588_G.J_1907_R[b_257_Y.R_4764_Y.R_4764_Y()];
            h_1847_R oceanmonumentpieces$roomdefinition1 = this.M_588_G;
            h_1847_R oceanmonumentpieces$roomdefinition2 = oceanmonumentpieces$roomdefinition.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()];
            h_1847_R oceanmonumentpieces$roomdefinition3 = oceanmonumentpieces$roomdefinition1.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()];
            if (this.M_588_G.n_1700_B / 25 > 0) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 8, oceanmonumentpieces$roomdefinition.R_4764_Y[b_257_Y.n_1700_B.R_4764_Y()]);
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, oceanmonumentpieces$roomdefinition1.R_4764_Y[b_257_Y.n_1700_B.R_4764_Y()]);
            }
            if (oceanmonumentpieces$roomdefinition3.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()] == null) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 8, 1, 6, 8, 7, n_1700_B);
            }
            if (oceanmonumentpieces$roomdefinition2.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()] == null) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 8, 8, 6, 8, 14, n_1700_B);
            }
            for (int i = 1; i <= 7; ++i) {
                K_4074_S blockstate = J_1907_R;
                if (i == 2 || i == 6) {
                    blockstate = n_1700_B;
                }
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, i, 0, 0, i, 15, blockstate, blockstate, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 7, i, 0, 7, i, 15, blockstate, blockstate, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, i, 0, 6, i, 0, blockstate, blockstate, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, i, 15, 6, i, 15, blockstate, blockstate, false);
            }
            for (int j = 1; j <= 7; ++j) {
                K_4074_S blockstate1 = R_4764_Y;
                if (j == 2 || j == 6) {
                    blockstate1 = P_1922_E;
                }
                this.n_1700_B(p_230383_1_, p_230383_5_, 3, j, 7, 4, j, 8, blockstate1, blockstate1, false);
            }
            if (oceanmonumentpieces$roomdefinition1.R_4764_Y[b_257_Y.G_564_y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 0, 4, 2, 0);
            }
            if (oceanmonumentpieces$roomdefinition1.R_4764_Y[b_257_Y.u_1723_Y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 7, 1, 3, 7, 2, 4);
            }
            if (oceanmonumentpieces$roomdefinition1.R_4764_Y[b_257_Y.P_1922_E.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 3, 0, 2, 4);
            }
            if (oceanmonumentpieces$roomdefinition.R_4764_Y[b_257_Y.R_4764_Y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 15, 4, 2, 15);
            }
            if (oceanmonumentpieces$roomdefinition.R_4764_Y[b_257_Y.P_1922_E.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 11, 0, 2, 12);
            }
            if (oceanmonumentpieces$roomdefinition.R_4764_Y[b_257_Y.u_1723_Y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 7, 1, 11, 7, 2, 12);
            }
            if (oceanmonumentpieces$roomdefinition3.R_4764_Y[b_257_Y.G_564_y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 3, 5, 0, 4, 6, 0);
            }
            if (oceanmonumentpieces$roomdefinition3.R_4764_Y[b_257_Y.u_1723_Y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 7, 5, 3, 7, 6, 4);
                this.n_1700_B(p_230383_1_, p_230383_5_, 5, 4, 2, 6, 4, 5, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 6, 1, 2, 6, 3, 2, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 6, 1, 5, 6, 3, 5, J_1907_R, J_1907_R, false);
            }
            if (oceanmonumentpieces$roomdefinition3.R_4764_Y[b_257_Y.P_1922_E.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 5, 3, 0, 6, 4);
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 4, 2, 2, 4, 5, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, 2, 1, 3, 2, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, 5, 1, 3, 5, J_1907_R, J_1907_R, false);
            }
            if (oceanmonumentpieces$roomdefinition2.R_4764_Y[b_257_Y.R_4764_Y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 3, 5, 15, 4, 6, 15);
            }
            if (oceanmonumentpieces$roomdefinition2.R_4764_Y[b_257_Y.P_1922_E.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 5, 11, 0, 6, 12);
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 4, 10, 2, 4, 13, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, 10, 1, 3, 10, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, 13, 1, 3, 13, J_1907_R, J_1907_R, false);
            }
            if (oceanmonumentpieces$roomdefinition2.R_4764_Y[b_257_Y.u_1723_Y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 7, 5, 11, 7, 6, 12);
                this.n_1700_B(p_230383_1_, p_230383_5_, 5, 4, 10, 6, 4, 13, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 6, 1, 10, 6, 3, 10, J_1907_R, J_1907_R, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 6, 1, 13, 6, 3, 13, J_1907_R, J_1907_R, false);
            }
            return true;
        }
    }

    public static class R_4764_Y
    extends P_4830_p {
        public R_4764_Y(b_257_Y p_i50657_1_, h_1847_R p_i50657_2_) {
            super(StructurePieceType.g_221_o, 1, p_i50657_1_, p_i50657_2_, 1, 2, 1);
        }

        public R_4764_Y(b_2085_h p_i50658_1_, U_2912_j p_i50658_2_) {
            super(StructurePieceType.g_221_o, p_i50658_2_);
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            if (this.M_588_G.n_1700_B / 25 > 0) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, this.M_588_G.R_4764_Y[b_257_Y.n_1700_B.R_4764_Y()]);
            }
            h_1847_R oceanmonumentpieces$roomdefinition = this.M_588_G.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()];
            if (oceanmonumentpieces$roomdefinition.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()] == null) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 8, 1, 6, 8, 6, n_1700_B);
            }
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 4, 0, 0, 4, 7, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 7, 4, 0, 7, 4, 7, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 4, 0, 6, 4, 0, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 4, 7, 6, 4, 7, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 4, 1, 2, 4, 2, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 4, 2, 1, 4, 2, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 4, 1, 5, 4, 2, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 4, 2, 6, 4, 2, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 4, 5, 2, 4, 6, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 4, 5, 1, 4, 5, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 4, 5, 5, 4, 6, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 4, 5, 6, 4, 5, J_1907_R, J_1907_R, false);
            h_1847_R oceanmonumentpieces$roomdefinition1 = this.M_588_G;
            for (int i = 1; i <= 5; i += 4) {
                int j = 0;
                if (oceanmonumentpieces$roomdefinition1.R_4764_Y[b_257_Y.G_564_y.R_4764_Y()]) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 2, i, j, 2, i + 2, j, J_1907_R, J_1907_R, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 5, i, j, 5, i + 2, j, J_1907_R, J_1907_R, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 3, i + 2, j, 4, i + 2, j, J_1907_R, J_1907_R, false);
                } else {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 0, i, j, 7, i + 2, j, J_1907_R, J_1907_R, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 0, i + 1, j, 7, i + 1, j, n_1700_B, n_1700_B, false);
                }
                j = 7;
                if (oceanmonumentpieces$roomdefinition1.R_4764_Y[b_257_Y.R_4764_Y.R_4764_Y()]) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 2, i, j, 2, i + 2, j, J_1907_R, J_1907_R, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 5, i, j, 5, i + 2, j, J_1907_R, J_1907_R, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 3, i + 2, j, 4, i + 2, j, J_1907_R, J_1907_R, false);
                } else {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 0, i, j, 7, i + 2, j, J_1907_R, J_1907_R, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 0, i + 1, j, 7, i + 1, j, n_1700_B, n_1700_B, false);
                }
                int k = 0;
                if (oceanmonumentpieces$roomdefinition1.R_4764_Y[b_257_Y.P_1922_E.R_4764_Y()]) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, k, i, 2, k, i + 2, 2, J_1907_R, J_1907_R, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, k, i, 5, k, i + 2, 5, J_1907_R, J_1907_R, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, k, i + 2, 3, k, i + 2, 4, J_1907_R, J_1907_R, false);
                } else {
                    this.n_1700_B(p_230383_1_, p_230383_5_, k, i, 0, k, i + 2, 7, J_1907_R, J_1907_R, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, k, i + 1, 0, k, i + 1, 7, n_1700_B, n_1700_B, false);
                }
                k = 7;
                if (oceanmonumentpieces$roomdefinition1.R_4764_Y[b_257_Y.u_1723_Y.R_4764_Y()]) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, k, i, 2, k, i + 2, 2, J_1907_R, J_1907_R, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, k, i, 5, k, i + 2, 5, J_1907_R, J_1907_R, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, k, i + 2, 3, k, i + 2, 4, J_1907_R, J_1907_R, false);
                } else {
                    this.n_1700_B(p_230383_1_, p_230383_5_, k, i, 0, k, i + 2, 7, J_1907_R, J_1907_R, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, k, i + 1, 0, k, i + 1, 7, n_1700_B, n_1700_B, false);
                }
                oceanmonumentpieces$roomdefinition1 = oceanmonumentpieces$roomdefinition;
            }
            return true;
        }
    }

    public static class J_1907_R
    extends P_4830_p {
        public J_1907_R(b_257_Y p_i50659_1_, h_1847_R p_i50659_2_) {
            super(StructurePieceType.z_4693_k, 1, p_i50659_1_, p_i50659_2_, 2, 2, 1);
        }

        public J_1907_R(b_2085_h p_i50660_1_, U_2912_j p_i50660_2_) {
            super(StructurePieceType.z_4693_k, p_i50660_2_);
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            h_1847_R oceanmonumentpieces$roomdefinition = this.M_588_G.J_1907_R[b_257_Y.u_1723_Y.R_4764_Y()];
            h_1847_R oceanmonumentpieces$roomdefinition1 = this.M_588_G;
            h_1847_R oceanmonumentpieces$roomdefinition2 = oceanmonumentpieces$roomdefinition1.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()];
            h_1847_R oceanmonumentpieces$roomdefinition3 = oceanmonumentpieces$roomdefinition.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()];
            if (this.M_588_G.n_1700_B / 25 > 0) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 8, 0, oceanmonumentpieces$roomdefinition.R_4764_Y[b_257_Y.n_1700_B.R_4764_Y()]);
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, oceanmonumentpieces$roomdefinition1.R_4764_Y[b_257_Y.n_1700_B.R_4764_Y()]);
            }
            if (oceanmonumentpieces$roomdefinition2.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()] == null) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 8, 1, 7, 8, 6, n_1700_B);
            }
            if (oceanmonumentpieces$roomdefinition3.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()] == null) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 8, 8, 1, 14, 8, 6, n_1700_B);
            }
            for (int i = 1; i <= 7; ++i) {
                K_4074_S blockstate = J_1907_R;
                if (i == 2 || i == 6) {
                    blockstate = n_1700_B;
                }
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, i, 0, 0, i, 7, blockstate, blockstate, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 15, i, 0, 15, i, 7, blockstate, blockstate, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, i, 0, 15, i, 0, blockstate, blockstate, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, i, 7, 14, i, 7, blockstate, blockstate, false);
            }
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 1, 3, 2, 7, 4, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 2, 4, 7, 2, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 5, 4, 7, 5, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 13, 1, 3, 13, 7, 4, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 11, 1, 2, 12, 7, 2, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 11, 1, 5, 12, 7, 5, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 1, 3, 5, 3, 4, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 10, 1, 3, 10, 3, 4, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 7, 2, 10, 7, 5, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 5, 2, 5, 7, 2, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 10, 5, 2, 10, 7, 2, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 5, 5, 5, 7, 5, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 10, 5, 5, 10, 7, 5, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, J_1907_R, 6, 6, 2, p_230383_5_);
            this.n_1700_B(p_230383_1_, J_1907_R, 9, 6, 2, p_230383_5_);
            this.n_1700_B(p_230383_1_, J_1907_R, 6, 6, 5, p_230383_5_);
            this.n_1700_B(p_230383_1_, J_1907_R, 9, 6, 5, p_230383_5_);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 4, 3, 6, 4, 4, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 9, 4, 3, 10, 4, 4, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, P_1922_E, 5, 4, 2, p_230383_5_);
            this.n_1700_B(p_230383_1_, P_1922_E, 5, 4, 5, p_230383_5_);
            this.n_1700_B(p_230383_1_, P_1922_E, 10, 4, 2, p_230383_5_);
            this.n_1700_B(p_230383_1_, P_1922_E, 10, 4, 5, p_230383_5_);
            if (oceanmonumentpieces$roomdefinition1.R_4764_Y[b_257_Y.G_564_y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 0, 4, 2, 0);
            }
            if (oceanmonumentpieces$roomdefinition1.R_4764_Y[b_257_Y.R_4764_Y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 7, 4, 2, 7);
            }
            if (oceanmonumentpieces$roomdefinition1.R_4764_Y[b_257_Y.P_1922_E.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 3, 0, 2, 4);
            }
            if (oceanmonumentpieces$roomdefinition.R_4764_Y[b_257_Y.G_564_y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 11, 1, 0, 12, 2, 0);
            }
            if (oceanmonumentpieces$roomdefinition.R_4764_Y[b_257_Y.R_4764_Y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 11, 1, 7, 12, 2, 7);
            }
            if (oceanmonumentpieces$roomdefinition.R_4764_Y[b_257_Y.u_1723_Y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 15, 1, 3, 15, 2, 4);
            }
            if (oceanmonumentpieces$roomdefinition2.R_4764_Y[b_257_Y.G_564_y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 3, 5, 0, 4, 6, 0);
            }
            if (oceanmonumentpieces$roomdefinition2.R_4764_Y[b_257_Y.R_4764_Y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 3, 5, 7, 4, 6, 7);
            }
            if (oceanmonumentpieces$roomdefinition2.R_4764_Y[b_257_Y.P_1922_E.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 5, 3, 0, 6, 4);
            }
            if (oceanmonumentpieces$roomdefinition3.R_4764_Y[b_257_Y.G_564_y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 11, 5, 0, 12, 6, 0);
            }
            if (oceanmonumentpieces$roomdefinition3.R_4764_Y[b_257_Y.R_4764_Y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 11, 5, 7, 12, 6, 7);
            }
            if (oceanmonumentpieces$roomdefinition3.R_4764_Y[b_257_Y.u_1723_Y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 15, 5, 3, 15, 6, 4);
            }
            return true;
        }
    }

    public static class n_1700_B
    extends P_4830_p {
        public n_1700_B(b_257_Y p_i50661_1_, h_1847_R p_i50661_2_) {
            super(StructurePieceType.q_4610_l, 1, p_i50661_1_, p_i50661_2_, 2, 1, 1);
        }

        public n_1700_B(b_2085_h p_i50662_1_, U_2912_j p_i50662_2_) {
            super(StructurePieceType.q_4610_l, p_i50662_2_);
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            h_1847_R oceanmonumentpieces$roomdefinition = this.M_588_G.J_1907_R[b_257_Y.u_1723_Y.R_4764_Y()];
            h_1847_R oceanmonumentpieces$roomdefinition1 = this.M_588_G;
            if (this.M_588_G.n_1700_B / 25 > 0) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 8, 0, oceanmonumentpieces$roomdefinition.R_4764_Y[b_257_Y.n_1700_B.R_4764_Y()]);
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, oceanmonumentpieces$roomdefinition1.R_4764_Y[b_257_Y.n_1700_B.R_4764_Y()]);
            }
            if (oceanmonumentpieces$roomdefinition1.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()] == null) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 4, 1, 7, 4, 6, n_1700_B);
            }
            if (oceanmonumentpieces$roomdefinition.J_1907_R[b_257_Y.J_1907_R.R_4764_Y()] == null) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 8, 4, 1, 14, 4, 6, n_1700_B);
            }
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, 0, 0, 3, 7, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 15, 3, 0, 15, 3, 7, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 3, 0, 15, 3, 0, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 3, 7, 14, 3, 7, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 0, 0, 2, 7, n_1700_B, n_1700_B, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 15, 2, 0, 15, 2, 7, n_1700_B, n_1700_B, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 2, 0, 15, 2, 0, n_1700_B, n_1700_B, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 2, 7, 14, 2, 7, n_1700_B, n_1700_B, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 0, 0, 1, 7, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 15, 1, 0, 15, 1, 7, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, 0, 15, 1, 0, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, 7, 14, 1, 7, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 1, 0, 10, 1, 4, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 2, 0, 9, 2, 3, n_1700_B, n_1700_B, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 3, 0, 10, 3, 4, J_1907_R, J_1907_R, false);
            this.n_1700_B(p_230383_1_, P_1922_E, 6, 2, 3, p_230383_5_);
            this.n_1700_B(p_230383_1_, P_1922_E, 9, 2, 3, p_230383_5_);
            if (oceanmonumentpieces$roomdefinition1.R_4764_Y[b_257_Y.G_564_y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 0, 4, 2, 0);
            }
            if (oceanmonumentpieces$roomdefinition1.R_4764_Y[b_257_Y.R_4764_Y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 7, 4, 2, 7);
            }
            if (oceanmonumentpieces$roomdefinition1.R_4764_Y[b_257_Y.P_1922_E.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 3, 0, 2, 4);
            }
            if (oceanmonumentpieces$roomdefinition.R_4764_Y[b_257_Y.G_564_y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 11, 1, 0, 12, 2, 0);
            }
            if (oceanmonumentpieces$roomdefinition.R_4764_Y[b_257_Y.R_4764_Y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 11, 1, 7, 12, 2, 7);
            }
            if (oceanmonumentpieces$roomdefinition.R_4764_Y[b_257_Y.u_1723_Y.R_4764_Y()]) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 15, 1, 3, 15, 2, 4);
            }
            return true;
        }
    }
}



