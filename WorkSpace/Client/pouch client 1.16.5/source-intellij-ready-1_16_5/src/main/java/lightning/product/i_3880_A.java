/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.FenceBlock;
import lightning.product.E_3771_B;
import lightning.product.Fluids;
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
import lightning.product.i_2154_H;
import lightning.product.SpawnerBlockEntity;
import lightning.product.o_4810_o;
import lightning.product.t_5_h;
import lightning.product.z_1753_f;
import lightning.product.z_2909_G;

public class i_3880_A {
    private static final P_4830_p[] n_1700_B = new P_4830_p[]{new P_4830_p(M_182_A.class, 30, 0, true), new P_4830_p(w_1484_f.class, 10, 4), new P_4830_p(u_1723_Y.class, 10, 4), new P_4830_p(h_1847_R.class, 10, 3), new P_4830_p(t_1786_h.class, 5, 2), new P_4830_p(s_956_w.class, 5, 1)};
    private static final P_4830_p[] J_1907_R = new P_4830_p[]{new P_4830_p(P_1922_E.class, 25, 0, true), new P_4830_p(v_4262_N.class, 15, 5), new P_4830_p(J_1907_R.class, 5, 10), new P_4830_p(n_1700_B.class, 5, 10), new P_4830_p(R_4764_Y.class, 10, 3, true), new P_4830_p(G_564_y.class, 7, 2), new P_4830_p(u_2550_I.class, 5, 2)};

    private static M_588_G n_1700_B(P_4830_p p_175887_0_, List<E_3771_B> p_175887_1_, Random p_175887_2_, int p_175887_3_, int p_175887_4_, int p_175887_5_, b_257_Y p_175887_6_, int p_175887_7_) {
        Class<? extends M_588_G> oclass = p_175887_0_.n_1700_B;
        M_588_G fortresspieces$piece = null;
        if (oclass == M_182_A.class) {
            fortresspieces$piece = M_182_A.n_1700_B(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
        } else if (oclass == w_1484_f.class) {
            fortresspieces$piece = w_1484_f.n_1700_B(p_175887_1_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
        } else if (oclass == u_1723_Y.class) {
            fortresspieces$piece = u_1723_Y.n_1700_B(p_175887_1_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
        } else if (oclass == h_1847_R.class) {
            fortresspieces$piece = h_1847_R.n_1700_B(p_175887_1_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_7_, p_175887_6_);
        } else if (oclass == t_1786_h.class) {
            fortresspieces$piece = t_1786_h.n_1700_B(p_175887_1_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_7_, p_175887_6_);
        } else if (oclass == s_956_w.class) {
            fortresspieces$piece = s_956_w.n_1700_B(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
        } else if (oclass == P_1922_E.class) {
            fortresspieces$piece = P_1922_E.n_1700_B(p_175887_1_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
        } else if (oclass == J_1907_R.class) {
            fortresspieces$piece = lightning.product.i_3880_A$J_1907_R.n_1700_B(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
        } else if (oclass == n_1700_B.class) {
            fortresspieces$piece = lightning.product.i_3880_A$n_1700_B.n_1700_B(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
        } else if (oclass == R_4764_Y.class) {
            fortresspieces$piece = R_4764_Y.n_1700_B(p_175887_1_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
        } else if (oclass == G_564_y.class) {
            fortresspieces$piece = G_564_y.n_1700_B(p_175887_1_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
        } else if (oclass == v_4262_N.class) {
            fortresspieces$piece = v_4262_N.n_1700_B(p_175887_1_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
        } else if (oclass == u_2550_I.class) {
            fortresspieces$piece = u_2550_I.n_1700_B(p_175887_1_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
        }
        return fortresspieces$piece;
    }

    static class P_4830_p {
        public final Class<? extends M_588_G> n_1700_B;
        public final int J_1907_R;
        public int R_4764_Y;
        public final int G_564_y;
        public final boolean P_1922_E;

        public P_4830_p(Class<? extends M_588_G> p_i2055_1_, int p_i2055_2_, int p_i2055_3_, boolean p_i2055_4_) {
            this.n_1700_B = p_i2055_1_;
            this.J_1907_R = p_i2055_2_;
            this.G_564_y = p_i2055_3_;
            this.P_1922_E = p_i2055_4_;
        }

        public P_4830_p(Class<? extends M_588_G> p_i2056_1_, int p_i2056_2_, int p_i2056_3_) {
            this(p_i2056_1_, p_i2056_2_, p_i2056_3_, false);
        }

        public boolean n_1700_B(int p_78822_1_) {
            return this.G_564_y == 0 || this.R_4764_Y < this.G_564_y;
        }

        public boolean n_1700_B() {
            return this.G_564_y == 0 || this.R_4764_Y < this.G_564_y;
        }
    }

    public static class M_182_A
    extends M_588_G {
        public M_182_A(int p_i45620_1_, Random p_i45620_2_, BoundingBox p_i45620_3_, b_257_Y p_i45620_4_) {
            super(StructurePieceType.v_4262_N, p_i45620_1_);
            this.n_1700_B(p_i45620_4_);
            this.h_1847_R = p_i45620_3_;
        }

        public M_182_A(b_2085_h p_i50283_1_, U_2912_j p_i50283_2_) {
            super(StructurePieceType.v_4262_N, p_i50283_2_);
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            this.n_1700_B((Q_4569_t)componentIn, listIn, rand, 1, 3, false);
        }

        public static M_182_A n_1700_B(List<E_3771_B> p_175882_0_, Random p_175882_1_, int p_175882_2_, int p_175882_3_, int p_175882_4_, b_257_Y p_175882_5_, int p_175882_6_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_175882_2_, p_175882_3_, p_175882_4_, -1, -3, 0, 5, 10, 19, p_175882_5_);
            return M_182_A.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_175882_0_, mutableboundingbox) == null ? new M_182_A(p_175882_6_, p_175882_1_, mutableboundingbox, p_175882_5_) : null;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, 0, 4, 4, 18, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 5, 0, 3, 7, 18, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 5, 0, 0, 5, 18, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 5, 0, 4, 5, 18, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 0, 4, 2, 5, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 13, 4, 2, 18, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 0, 4, 1, 3, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 15, 4, 1, 18, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            for (int i = 0; i <= 4; ++i) {
                for (int j = 0; j <= 2; ++j) {
                    this.J_1907_R(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), i, -1, j, p_230383_5_);
                    this.J_1907_R(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), i, -1, 18 - j, p_230383_5_);
                }
            }
            K_4074_S blockstate1 = (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.P_4830_p, true)).n_1700_B(FenceBlock.Q_4569_t, true);
            K_4074_S blockstate2 = (K_4074_S)blockstate1.n_1700_B(FenceBlock.h_1847_R, true);
            K_4074_S blockstate = (K_4074_S)blockstate1.n_1700_B(FenceBlock.M_182_A, true);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 1, 0, 4, 1, blockstate2, blockstate2, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, 4, 0, 4, 4, blockstate2, blockstate2, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, 14, 0, 4, 14, blockstate2, blockstate2, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 17, 0, 4, 17, blockstate2, blockstate2, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 1, 1, 4, 4, 1, blockstate, blockstate, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 3, 4, 4, 4, 4, blockstate, blockstate, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 3, 14, 4, 4, 14, blockstate, blockstate, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 1, 17, 4, 4, 17, blockstate, blockstate, false);
            return true;
        }
    }

    public static class w_1484_f
    extends M_588_G {
        public w_1484_f(int p_i50286_1_, BoundingBox p_i50286_2_, b_257_Y p_i50286_3_) {
            super(StructurePieceType.P_1922_E, p_i50286_1_);
            this.n_1700_B(p_i50286_3_);
            this.h_1847_R = p_i50286_2_;
        }

        protected w_1484_f(Random p_i2042_1_, int p_i2042_2_, int p_i2042_3_) {
            super(StructurePieceType.P_1922_E, 0);
            this.n_1700_B(b_257_Y.R_4764_Y.n_1700_B.n_1700_B(p_i2042_1_));
            this.h_1847_R = this.t_148_a().h_1847_R() == b_257_Y.n_1700_B.R_4764_Y ? new BoundingBox(p_i2042_2_, 64, p_i2042_3_, p_i2042_2_ + 19 - 1, 73, p_i2042_3_ + 19 - 1) : new BoundingBox(p_i2042_2_, 64, p_i2042_3_, p_i2042_2_ + 19 - 1, 73, p_i2042_3_ + 19 - 1);
        }

        protected w_1484_f(StructurePieceType p_i50287_1_, U_2912_j p_i50287_2_) {
            super(p_i50287_1_, p_i50287_2_);
        }

        public w_1484_f(b_2085_h p_i50288_1_, U_2912_j p_i50288_2_) {
            this(StructurePieceType.P_1922_E, p_i50288_2_);
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            this.n_1700_B((Q_4569_t)componentIn, listIn, rand, 8, 3, false);
            this.J_1907_R((Q_4569_t)componentIn, listIn, rand, 3, 8, false);
            this.R_4764_Y((Q_4569_t)componentIn, listIn, rand, 3, 8, false);
        }

        public static w_1484_f n_1700_B(List<E_3771_B> p_175885_0_, int p_175885_1_, int p_175885_2_, int p_175885_3_, b_257_Y p_175885_4_, int p_175885_5_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_175885_1_, p_175885_2_, p_175885_3_, -8, -3, 0, 19, 10, 19, p_175885_4_);
            return w_1484_f.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_175885_0_, mutableboundingbox) == null ? new w_1484_f(p_175885_5_, mutableboundingbox, p_175885_4_) : null;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 7, 3, 0, 11, 4, 18, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, 7, 18, 4, 11, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 8, 5, 0, 10, 7, 18, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 5, 8, 18, 7, 10, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 7, 5, 0, 7, 5, 7, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 7, 5, 11, 7, 5, 18, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 11, 5, 0, 11, 5, 7, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 11, 5, 11, 11, 5, 18, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 5, 7, 7, 5, 7, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 11, 5, 7, 18, 5, 7, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 5, 11, 7, 5, 11, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 11, 5, 11, 18, 5, 11, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 7, 2, 0, 11, 2, 5, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 7, 2, 13, 11, 2, 18, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 7, 0, 0, 11, 1, 3, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 7, 0, 15, 11, 1, 18, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            for (int i = 7; i <= 11; ++i) {
                for (int j = 0; j <= 2; ++j) {
                    this.J_1907_R(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), i, -1, j, p_230383_5_);
                    this.J_1907_R(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), i, -1, 18 - j, p_230383_5_);
                }
            }
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 7, 5, 2, 11, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 13, 2, 7, 18, 2, 11, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 7, 3, 1, 11, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 15, 0, 7, 18, 1, 11, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            for (int k = 0; k <= 2; ++k) {
                for (int l = 7; l <= 11; ++l) {
                    this.J_1907_R(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), k, -1, l, p_230383_5_);
                    this.J_1907_R(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), 18 - k, -1, l, p_230383_5_);
                }
            }
            return true;
        }
    }

    public static class u_1723_Y
    extends M_588_G {
        public u_1723_Y(int p_i50258_1_, BoundingBox p_i50258_2_, b_257_Y p_i50258_3_) {
            super(StructurePieceType.t_1786_h, p_i50258_1_);
            this.n_1700_B(p_i50258_3_);
            this.h_1847_R = p_i50258_2_;
        }

        public u_1723_Y(b_2085_h p_i50259_1_, U_2912_j p_i50259_2_) {
            super(StructurePieceType.t_1786_h, p_i50259_2_);
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            this.n_1700_B((Q_4569_t)componentIn, listIn, rand, 2, 0, false);
            this.J_1907_R((Q_4569_t)componentIn, listIn, rand, 0, 2, false);
            this.R_4764_Y((Q_4569_t)componentIn, listIn, rand, 0, 2, false);
        }

        public static u_1723_Y n_1700_B(List<E_3771_B> p_175873_0_, int p_175873_1_, int p_175873_2_, int p_175873_3_, b_257_Y p_175873_4_, int p_175873_5_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_175873_1_, p_175873_2_, p_175873_3_, -2, 0, 0, 7, 9, 7, p_175873_4_);
            return u_1723_Y.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_175873_0_, mutableboundingbox) == null ? new u_1723_Y(p_175873_5_, mutableboundingbox, p_175873_4_) : null;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 0, 6, 1, 6, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 0, 6, 7, 6, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 0, 1, 6, 0, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 6, 1, 6, 6, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 2, 0, 6, 6, 0, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 2, 6, 6, 6, 6, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 0, 0, 6, 1, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 5, 0, 6, 6, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 2, 0, 6, 6, 1, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 2, 5, 6, 6, 6, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            K_4074_S blockstate = (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.M_182_A, true)).n_1700_B(FenceBlock.h_1847_R, true);
            K_4074_S blockstate1 = (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.P_4830_p, true)).n_1700_B(FenceBlock.Q_4569_t, true);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 6, 0, 4, 6, 0, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 5, 0, 4, 5, 0, blockstate, blockstate, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 6, 6, 4, 6, 6, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 5, 6, 4, 5, 6, blockstate, blockstate, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 6, 2, 0, 6, 4, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 5, 2, 0, 5, 4, blockstate1, blockstate1, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 6, 2, 6, 6, 4, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 5, 2, 6, 5, 4, blockstate1, blockstate1, false);
            for (int i = 0; i <= 6; ++i) {
                for (int j = 0; j <= 6; ++j) {
                    this.J_1907_R(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), i, -1, j, p_230383_5_);
                }
            }
            return true;
        }
    }

    public static class h_1847_R
    extends M_588_G {
        public h_1847_R(int p_i50255_1_, BoundingBox p_i50255_2_, b_257_Y p_i50255_3_) {
            super(StructurePieceType.multiplayerClientSuggestionProvider, p_i50255_1_);
            this.n_1700_B(p_i50255_3_);
            this.h_1847_R = p_i50255_2_;
        }

        public h_1847_R(b_2085_h p_i50256_1_, U_2912_j p_i50256_2_) {
            super(StructurePieceType.multiplayerClientSuggestionProvider, p_i50256_2_);
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            this.R_4764_Y((Q_4569_t)componentIn, listIn, rand, 6, 2, false);
        }

        public static h_1847_R n_1700_B(List<E_3771_B> p_175872_0_, int p_175872_1_, int p_175872_2_, int p_175872_3_, int p_175872_4_, b_257_Y p_175872_5_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_175872_1_, p_175872_2_, p_175872_3_, -2, 0, 0, 7, 11, 7, p_175872_5_);
            return h_1847_R.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_175872_0_, mutableboundingbox) == null ? new h_1847_R(p_175872_4_, mutableboundingbox, p_175872_5_) : null;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 0, 6, 1, 6, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 0, 6, 10, 6, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 0, 1, 8, 0, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 2, 0, 6, 8, 0, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 1, 0, 8, 6, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 2, 1, 6, 8, 6, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 2, 6, 5, 8, 6, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            K_4074_S blockstate = (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.M_182_A, true)).n_1700_B(FenceBlock.h_1847_R, true);
            K_4074_S blockstate1 = (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.P_4830_p, true)).n_1700_B(FenceBlock.Q_4569_t, true);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, 2, 0, 5, 4, blockstate1, blockstate1, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 3, 2, 6, 5, 2, blockstate1, blockstate1, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 3, 4, 6, 5, 4, blockstate1, blockstate1, false);
            this.n_1700_B(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), 5, 2, 5, p_230383_5_);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 2, 5, 4, 3, 5, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 3, 2, 5, 3, 4, 5, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 2, 5, 2, 5, 5, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 2, 5, 1, 6, 5, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 7, 1, 5, 7, 4, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 8, 2, 6, 8, 4, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 6, 0, 4, 8, 0, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 5, 0, 4, 5, 0, blockstate, blockstate, false);
            for (int i = 0; i <= 6; ++i) {
                for (int j = 0; j <= 6; ++j) {
                    this.J_1907_R(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), i, -1, j, p_230383_5_);
                }
            }
            return true;
        }
    }

    public static class t_1786_h
    extends M_588_G {
        private boolean n_1700_B;

        public t_1786_h(int p_i50262_1_, BoundingBox p_i50262_2_, b_257_Y p_i50262_3_) {
            super(StructurePieceType.M_182_A, p_i50262_1_);
            this.n_1700_B(p_i50262_3_);
            this.h_1847_R = p_i50262_2_;
        }

        public t_1786_h(b_2085_h p_i50263_1_, U_2912_j p_i50263_2_) {
            super(StructurePieceType.M_182_A, p_i50263_2_);
            this.n_1700_B = p_i50263_2_.t_1786_h("Mob");
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
            super.n_1700_B(tagCompound);
            tagCompound.n_1700_B("Mob", this.n_1700_B);
        }

        public static t_1786_h n_1700_B(List<E_3771_B> p_175874_0_, int p_175874_1_, int p_175874_2_, int p_175874_3_, int p_175874_4_, b_257_Y p_175874_5_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_175874_1_, p_175874_2_, p_175874_3_, -2, 0, 0, 7, 8, 9, p_175874_5_);
            return t_1786_h.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_175874_0_, mutableboundingbox) == null ? new t_1786_h(p_175874_4_, mutableboundingbox, p_175874_5_) : null;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            c_1514_x blockpos;
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 0, 6, 7, 7, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 0, 0, 5, 1, 7, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 2, 1, 5, 2, 7, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 3, 2, 5, 3, 7, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 4, 3, 5, 4, 7, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 2, 0, 1, 4, 2, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 2, 0, 5, 4, 2, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 5, 2, 1, 5, 3, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 5, 2, 5, 5, 3, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 5, 3, 0, 5, 8, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 5, 3, 6, 5, 8, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 5, 8, 5, 5, 8, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            K_4074_S blockstate = (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.M_182_A, true)).n_1700_B(FenceBlock.h_1847_R, true);
            K_4074_S blockstate1 = (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.P_4830_p, true)).n_1700_B(FenceBlock.Q_4569_t, true);
            this.n_1700_B(p_230383_1_, (K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.M_182_A, true), 1, 6, 3, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.h_1847_R, true), 5, 6, 3, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.h_1847_R, true)).n_1700_B(FenceBlock.P_4830_p, true), 0, 6, 3, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.M_182_A, true)).n_1700_B(FenceBlock.P_4830_p, true), 6, 6, 3, p_230383_5_);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 6, 4, 0, 6, 7, blockstate1, blockstate1, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 6, 4, 6, 6, 7, blockstate1, blockstate1, false);
            this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.h_1847_R, true)).n_1700_B(FenceBlock.Q_4569_t, true), 0, 6, 8, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.M_182_A, true)).n_1700_B(FenceBlock.Q_4569_t, true), 6, 6, 8, p_230383_5_);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 6, 8, 5, 6, 8, blockstate, blockstate, false);
            this.n_1700_B(p_230383_1_, (K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.h_1847_R, true), 1, 7, 8, p_230383_5_);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 7, 8, 4, 7, 8, blockstate, blockstate, false);
            this.n_1700_B(p_230383_1_, (K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.M_182_A, true), 5, 7, 8, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.h_1847_R, true), 2, 8, 8, p_230383_5_);
            this.n_1700_B(p_230383_1_, blockstate, 3, 8, 8, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.M_182_A, true), 4, 8, 8, p_230383_5_);
            if (!this.n_1700_B && p_230383_5_.J_1907_R(blockpos = new c_1514_x(this.n_1700_B(3, 5), this.n_1700_B(5), this.J_1907_R(3, 5)))) {
                this.n_1700_B = true;
                p_230383_1_.n_1700_B(blockpos, a_3742_W.j_306_t.multiplayerClientSuggestionProvider(), 2);
                i_2154_H tileentity = p_230383_1_.getTileEntity(blockpos);
                if (tileentity instanceof SpawnerBlockEntity) {
                    ((SpawnerBlockEntity)tileentity).v_4262_N().n_1700_B(t_5_h.u_1723_Y);
                }
            }
            for (int i = 0; i <= 6; ++i) {
                for (int j = 0; j <= 6; ++j) {
                    this.J_1907_R(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), i, -1, j, p_230383_5_);
                }
            }
            return true;
        }
    }

    public static class s_956_w
    extends M_588_G {
        public s_956_w(int p_i45617_1_, Random p_i45617_2_, BoundingBox p_i45617_3_, b_257_Y p_i45617_4_) {
            super(StructurePieceType.s_956_w, p_i45617_1_);
            this.n_1700_B(p_i45617_4_);
            this.h_1847_R = p_i45617_3_;
        }

        public s_956_w(b_2085_h p_i50276_1_, U_2912_j p_i50276_2_) {
            super(StructurePieceType.s_956_w, p_i50276_2_);
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            this.n_1700_B((Q_4569_t)componentIn, listIn, rand, 5, 3, true);
        }

        public static s_956_w n_1700_B(List<E_3771_B> p_175881_0_, Random p_175881_1_, int p_175881_2_, int p_175881_3_, int p_175881_4_, b_257_Y p_175881_5_, int p_175881_6_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_175881_2_, p_175881_3_, p_175881_4_, -5, -3, 0, 13, 14, 13, p_175881_5_);
            return s_956_w.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_175881_0_, mutableboundingbox) == null ? new s_956_w(p_175881_6_, p_175881_1_, mutableboundingbox, p_175881_5_) : null;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, 0, 12, 4, 12, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 5, 0, 12, 13, 12, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 5, 0, 1, 12, 12, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 11, 5, 0, 12, 12, 12, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 5, 11, 4, 12, 12, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 8, 5, 11, 10, 12, 12, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 9, 11, 7, 12, 12, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 5, 0, 4, 12, 1, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 8, 5, 0, 10, 12, 1, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 9, 0, 7, 12, 1, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 11, 2, 10, 12, 10, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 8, 0, 7, 8, 0, a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider(), a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider(), false);
            K_4074_S blockstate = (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.M_182_A, true)).n_1700_B(FenceBlock.h_1847_R, true);
            K_4074_S blockstate1 = (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.P_4830_p, true)).n_1700_B(FenceBlock.Q_4569_t, true);
            for (int i = 1; i <= 11; i += 2) {
                this.n_1700_B(p_230383_1_, p_230383_5_, i, 10, 0, i, 11, 0, blockstate, blockstate, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, i, 10, 12, i, 11, 12, blockstate, blockstate, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 10, i, 0, 11, i, blockstate1, blockstate1, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 12, 10, i, 12, 11, i, blockstate1, blockstate1, false);
                this.n_1700_B(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), i, 13, 0, p_230383_5_);
                this.n_1700_B(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), i, 13, 12, p_230383_5_);
                this.n_1700_B(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), 0, 13, i, p_230383_5_);
                this.n_1700_B(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), 12, 13, i, p_230383_5_);
                if (i == 11) continue;
                this.n_1700_B(p_230383_1_, blockstate, i + 1, 13, 0, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate, i + 1, 13, 12, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate1, 0, 13, i + 1, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate1, 12, 13, i + 1, p_230383_5_);
            }
            this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.P_4830_p, true)).n_1700_B(FenceBlock.h_1847_R, true), 0, 13, 0, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.Q_4569_t, true)).n_1700_B(FenceBlock.h_1847_R, true), 0, 13, 12, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.Q_4569_t, true)).n_1700_B(FenceBlock.M_182_A, true), 12, 13, 12, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.P_4830_p, true)).n_1700_B(FenceBlock.M_182_A, true), 12, 13, 0, p_230383_5_);
            for (int k = 3; k <= 9; k += 2) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 7, k, 1, 8, k, (K_4074_S)blockstate1.n_1700_B(FenceBlock.M_182_A, true), (K_4074_S)blockstate1.n_1700_B(FenceBlock.M_182_A, true), false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 11, 7, k, 11, 8, k, (K_4074_S)blockstate1.n_1700_B(FenceBlock.h_1847_R, true), (K_4074_S)blockstate1.n_1700_B(FenceBlock.h_1847_R, true), false);
            }
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 2, 0, 8, 2, 12, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 4, 12, 2, 8, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 0, 0, 8, 1, 3, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 0, 9, 8, 1, 12, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 4, 3, 1, 8, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 9, 0, 4, 12, 1, 8, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            for (int l = 4; l <= 8; ++l) {
                for (int j = 0; j <= 2; ++j) {
                    this.J_1907_R(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), l, -1, j, p_230383_5_);
                    this.J_1907_R(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), l, -1, 12 - j, p_230383_5_);
                }
            }
            for (int i1 = 0; i1 <= 2; ++i1) {
                for (int j1 = 4; j1 <= 8; ++j1) {
                    this.J_1907_R(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), i1, -1, j1, p_230383_5_);
                    this.J_1907_R(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), 12 - i1, -1, j1, p_230383_5_);
                }
            }
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 5, 5, 7, 5, 7, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 1, 6, 6, 4, 6, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), 6, 0, 6, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.H_2857_Y.multiplayerClientSuggestionProvider(), 6, 5, 6, p_230383_5_);
            c_1514_x blockpos = new c_1514_x(this.n_1700_B(6, 6), this.n_1700_B(5), this.J_1907_R(6, 6));
            if (p_230383_5_.J_1907_R(blockpos)) {
                p_230383_1_.M_588_G().n_1700_B(blockpos, Fluids.P_1922_E, 0);
            }
            return true;
        }
    }

    public static class P_1922_E
    extends M_588_G {
        public P_1922_E(int p_i50268_1_, BoundingBox p_i50268_2_, b_257_Y p_i50268_3_) {
            super(StructurePieceType.P_4830_p, p_i50268_1_);
            this.n_1700_B(p_i50268_3_);
            this.h_1847_R = p_i50268_2_;
        }

        public P_1922_E(b_2085_h p_i50269_1_, U_2912_j p_i50269_2_) {
            super(StructurePieceType.P_4830_p, p_i50269_2_);
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            this.n_1700_B((Q_4569_t)componentIn, listIn, rand, 1, 0, true);
        }

        public static P_1922_E n_1700_B(List<E_3771_B> p_175877_0_, int p_175877_1_, int p_175877_2_, int p_175877_3_, b_257_Y p_175877_4_, int p_175877_5_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_175877_1_, p_175877_2_, p_175877_3_, -1, 0, 0, 5, 7, 5, p_175877_4_);
            return P_1922_E.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_175877_0_, mutableboundingbox) == null ? new P_1922_E(p_175877_5_, mutableboundingbox, p_175877_4_) : null;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 0, 4, 1, 4, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 0, 4, 5, 4, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
            K_4074_S blockstate = (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.P_4830_p, true)).n_1700_B(FenceBlock.Q_4569_t, true);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 0, 0, 5, 4, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 2, 0, 4, 5, 4, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, 1, 0, 4, 1, blockstate, blockstate, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, 3, 0, 4, 3, blockstate, blockstate, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 3, 1, 4, 4, 1, blockstate, blockstate, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 3, 3, 4, 4, 3, blockstate, blockstate, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 6, 0, 4, 6, 4, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            for (int i = 0; i <= 4; ++i) {
                for (int j = 0; j <= 4; ++j) {
                    this.J_1907_R(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), i, -1, j, p_230383_5_);
                }
            }
            return true;
        }
    }

    public static class J_1907_R
    extends M_588_G {
        private boolean n_1700_B;

        public J_1907_R(int p_i45613_1_, Random p_i45613_2_, BoundingBox p_i45613_3_, b_257_Y p_i45613_4_) {
            super(StructurePieceType.h_1847_R, p_i45613_1_);
            this.n_1700_B(p_i45613_4_);
            this.h_1847_R = p_i45613_3_;
            this.n_1700_B = p_i45613_2_.nextInt(3) == 0;
        }

        public J_1907_R(b_2085_h p_i50266_1_, U_2912_j p_i50266_2_) {
            super(StructurePieceType.h_1847_R, p_i50266_2_);
            this.n_1700_B = p_i50266_2_.t_1786_h("Chest");
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
            super.n_1700_B(tagCompound);
            tagCompound.n_1700_B("Chest", this.n_1700_B);
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            this.R_4764_Y((Q_4569_t)componentIn, listIn, rand, 0, 1, true);
        }

        public static J_1907_R n_1700_B(List<E_3771_B> p_175876_0_, Random p_175876_1_, int p_175876_2_, int p_175876_3_, int p_175876_4_, b_257_Y p_175876_5_, int p_175876_6_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_175876_2_, p_175876_3_, p_175876_4_, -1, 0, 0, 5, 7, 5, p_175876_5_);
            return lightning.product.i_3880_A$J_1907_R.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_175876_0_, mutableboundingbox) == null ? new J_1907_R(p_175876_6_, p_175876_1_, mutableboundingbox, p_175876_5_) : null;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 0, 4, 1, 4, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 0, 4, 5, 4, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
            K_4074_S blockstate = (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.M_182_A, true)).n_1700_B(FenceBlock.h_1847_R, true);
            K_4074_S blockstate1 = (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.P_4830_p, true)).n_1700_B(FenceBlock.Q_4569_t, true);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 0, 0, 5, 4, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, 1, 0, 4, 1, blockstate1, blockstate1, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, 3, 0, 4, 3, blockstate1, blockstate1, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 2, 0, 4, 5, 0, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 2, 4, 4, 5, 4, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 3, 4, 1, 4, 4, blockstate, blockstate, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 3, 3, 4, 3, 4, 4, blockstate, blockstate, false);
            if (this.n_1700_B && p_230383_5_.J_1907_R(new c_1514_x(this.n_1700_B(1, 3), this.n_1700_B(2), this.J_1907_R(1, 3)))) {
                this.n_1700_B = false;
                this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 1, 2, 3, o_4810_o.Q_2552_b);
            }
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 6, 0, 4, 6, 4, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            for (int i = 0; i <= 4; ++i) {
                for (int j = 0; j <= 4; ++j) {
                    this.J_1907_R(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), i, -1, j, p_230383_5_);
                }
            }
            return true;
        }
    }

    public static class n_1700_B
    extends M_588_G {
        private boolean n_1700_B;

        public n_1700_B(int p_i45615_1_, Random p_i45615_2_, BoundingBox p_i45615_3_, b_257_Y p_i45615_4_) {
            super(StructurePieceType.M_588_G, p_i45615_1_);
            this.n_1700_B(p_i45615_4_);
            this.h_1847_R = p_i45615_3_;
            this.n_1700_B = p_i45615_2_.nextInt(3) == 0;
        }

        public n_1700_B(b_2085_h p_i50272_1_, U_2912_j p_i50272_2_) {
            super(StructurePieceType.M_588_G, p_i50272_2_);
            this.n_1700_B = p_i50272_2_.t_1786_h("Chest");
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
            super.n_1700_B(tagCompound);
            tagCompound.n_1700_B("Chest", this.n_1700_B);
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            this.J_1907_R((Q_4569_t)componentIn, listIn, rand, 0, 1, true);
        }

        public static n_1700_B n_1700_B(List<E_3771_B> p_175879_0_, Random p_175879_1_, int p_175879_2_, int p_175879_3_, int p_175879_4_, b_257_Y p_175879_5_, int p_175879_6_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_175879_2_, p_175879_3_, p_175879_4_, -1, 0, 0, 5, 7, 5, p_175879_5_);
            return lightning.product.i_3880_A$n_1700_B.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_175879_0_, mutableboundingbox) == null ? new n_1700_B(p_175879_6_, p_175879_1_, mutableboundingbox, p_175879_5_) : null;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 0, 4, 1, 4, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 0, 4, 5, 4, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
            K_4074_S blockstate = (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.M_182_A, true)).n_1700_B(FenceBlock.h_1847_R, true);
            K_4074_S blockstate1 = (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.P_4830_p, true)).n_1700_B(FenceBlock.Q_4569_t, true);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 2, 0, 4, 5, 4, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 3, 1, 4, 4, 1, blockstate1, blockstate1, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 3, 3, 4, 4, 3, blockstate1, blockstate1, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 0, 0, 5, 0, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 4, 3, 5, 4, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 3, 4, 1, 4, 4, blockstate, blockstate, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 3, 3, 4, 3, 4, 4, blockstate, blockstate, false);
            if (this.n_1700_B && p_230383_5_.J_1907_R(new c_1514_x(this.n_1700_B(3, 3), this.n_1700_B(2), this.J_1907_R(3, 3)))) {
                this.n_1700_B = false;
                this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 3, 2, 3, o_4810_o.Q_2552_b);
            }
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 6, 0, 4, 6, 4, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            for (int i = 0; i <= 4; ++i) {
                for (int j = 0; j <= 4; ++j) {
                    this.J_1907_R(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), i, -1, j, p_230383_5_);
                }
            }
            return true;
        }
    }

    public static class R_4764_Y
    extends M_588_G {
        public R_4764_Y(int p_i50280_1_, BoundingBox p_i50280_2_, b_257_Y p_i50280_3_) {
            super(StructurePieceType.w_1484_f, p_i50280_1_);
            this.n_1700_B(p_i50280_3_);
            this.h_1847_R = p_i50280_2_;
        }

        public R_4764_Y(b_2085_h p_i50281_1_, U_2912_j p_i50281_2_) {
            super(StructurePieceType.w_1484_f, p_i50281_2_);
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            this.n_1700_B((Q_4569_t)componentIn, listIn, rand, 1, 0, true);
        }

        public static R_4764_Y n_1700_B(List<E_3771_B> p_175883_0_, int p_175883_1_, int p_175883_2_, int p_175883_3_, b_257_Y p_175883_4_, int p_175883_5_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_175883_1_, p_175883_2_, p_175883_3_, -1, -7, 0, 5, 14, 10, p_175883_4_);
            return R_4764_Y.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_175883_0_, mutableboundingbox) == null ? new R_4764_Y(p_175883_5_, mutableboundingbox, p_175883_4_) : null;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            K_4074_S blockstate = (K_4074_S)a_3742_W.O_2761_o.multiplayerClientSuggestionProvider().n_1700_B(z_2909_G.P_4830_p, b_257_Y.G_564_y);
            K_4074_S blockstate1 = (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.P_4830_p, true)).n_1700_B(FenceBlock.Q_4569_t, true);
            for (int i = 0; i <= 9; ++i) {
                int j = Math.max(1, 7 - i);
                int k = Math.min(Math.max(j + 5, 14 - i), 13);
                int l = i;
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, i, 4, j, i, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, j + 1, i, 3, k - 1, i, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
                if (i <= 6) {
                    this.n_1700_B(p_230383_1_, blockstate, 1, j + 1, i, p_230383_5_);
                    this.n_1700_B(p_230383_1_, blockstate, 2, j + 1, i, p_230383_5_);
                    this.n_1700_B(p_230383_1_, blockstate, 3, j + 1, i, p_230383_5_);
                }
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, k, i, 4, k, i, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, j + 1, i, 0, k - 1, i, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 4, j + 1, i, 4, k - 1, i, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
                if ((i & 1) == 0) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 0, j + 2, i, 0, j + 3, i, blockstate1, blockstate1, false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 4, j + 2, i, 4, j + 3, i, blockstate1, blockstate1, false);
                }
                for (int i1 = 0; i1 <= 4; ++i1) {
                    this.J_1907_R(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), i1, -1, l, p_230383_5_);
                }
            }
            return true;
        }
    }

    public static class G_564_y
    extends M_588_G {
        public G_564_y(int p_i50277_1_, BoundingBox p_i50277_2_, b_257_Y p_i50277_3_) {
            super(StructurePieceType.t_148_a, p_i50277_1_);
            this.n_1700_B(p_i50277_3_);
            this.h_1847_R = p_i50277_2_;
        }

        public G_564_y(b_2085_h p_i50278_1_, U_2912_j p_i50278_2_) {
            super(StructurePieceType.t_148_a, p_i50278_2_);
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            int i = 1;
            b_257_Y direction = this.t_148_a();
            if (direction == b_257_Y.P_1922_E || direction == b_257_Y.R_4764_Y) {
                i = 5;
            }
            this.J_1907_R((Q_4569_t)componentIn, listIn, rand, 0, i, rand.nextInt(8) > 0);
            this.R_4764_Y((Q_4569_t)componentIn, listIn, rand, 0, i, rand.nextInt(8) > 0);
        }

        public static G_564_y n_1700_B(List<E_3771_B> p_214814_0_, int p_214814_1_, int p_214814_2_, int p_214814_3_, b_257_Y p_214814_4_, int p_214814_5_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_214814_1_, p_214814_2_, p_214814_3_, -3, 0, 0, 9, 7, 9, p_214814_4_);
            return G_564_y.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_214814_0_, mutableboundingbox) == null ? new G_564_y(p_214814_5_, mutableboundingbox, p_214814_4_) : null;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            K_4074_S blockstate = (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.P_4830_p, true)).n_1700_B(FenceBlock.Q_4569_t, true);
            K_4074_S blockstate1 = (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.M_182_A, true)).n_1700_B(FenceBlock.h_1847_R, true);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 0, 8, 1, 8, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 0, 8, 5, 8, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 6, 0, 8, 6, 5, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 0, 2, 5, 0, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 2, 0, 8, 5, 0, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 3, 0, 1, 4, 0, blockstate1, blockstate1, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 7, 3, 0, 7, 4, 0, blockstate1, blockstate1, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 4, 8, 2, 8, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, 4, 2, 2, 4, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 1, 4, 7, 2, 4, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 3, 8, 7, 3, 8, blockstate1, blockstate1, false);
            this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.h_1847_R, true)).n_1700_B(FenceBlock.Q_4569_t, true), 0, 3, 8, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.M_182_A, true)).n_1700_B(FenceBlock.Q_4569_t, true), 8, 3, 8, p_230383_5_);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, 6, 0, 3, 7, blockstate, blockstate, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 8, 3, 6, 8, 3, 7, blockstate, blockstate, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, 4, 0, 5, 5, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 8, 3, 4, 8, 5, 5, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 3, 5, 2, 5, 5, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 6, 3, 5, 7, 5, 5, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 4, 5, 1, 5, 5, blockstate1, blockstate1, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 7, 4, 5, 7, 5, 5, blockstate1, blockstate1, false);
            for (int i = 0; i <= 5; ++i) {
                for (int j = 0; j <= 8; ++j) {
                    this.J_1907_R(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), j, -1, i, p_230383_5_);
                }
            }
            return true;
        }
    }

    public static class v_4262_N
    extends M_588_G {
        public v_4262_N(int p_i50273_1_, BoundingBox p_i50273_2_, b_257_Y p_i50273_3_) {
            super(StructurePieceType.u_2550_I, p_i50273_1_);
            this.n_1700_B(p_i50273_3_);
            this.h_1847_R = p_i50273_2_;
        }

        public v_4262_N(b_2085_h p_i50274_1_, U_2912_j p_i50274_2_) {
            super(StructurePieceType.u_2550_I, p_i50274_2_);
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            this.n_1700_B((Q_4569_t)componentIn, listIn, rand, 1, 0, true);
            this.J_1907_R((Q_4569_t)componentIn, listIn, rand, 0, 1, true);
            this.R_4764_Y((Q_4569_t)componentIn, listIn, rand, 0, 1, true);
        }

        public static v_4262_N n_1700_B(List<E_3771_B> p_175878_0_, int p_175878_1_, int p_175878_2_, int p_175878_3_, b_257_Y p_175878_4_, int p_175878_5_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_175878_1_, p_175878_2_, p_175878_3_, -1, 0, 0, 5, 7, 5, p_175878_4_);
            return v_4262_N.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_175878_0_, mutableboundingbox) == null ? new v_4262_N(p_175878_5_, mutableboundingbox, p_175878_4_) : null;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 0, 4, 1, 4, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 0, 4, 5, 4, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 0, 0, 5, 0, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 2, 0, 4, 5, 0, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 4, 0, 5, 4, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 2, 4, 4, 5, 4, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 6, 0, 4, 6, 4, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            for (int i = 0; i <= 4; ++i) {
                for (int j = 0; j <= 4; ++j) {
                    this.J_1907_R(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), i, -1, j, p_230383_5_);
                }
            }
            return true;
        }
    }

    public static class u_2550_I
    extends M_588_G {
        public u_2550_I(int p_i50264_1_, BoundingBox p_i50264_2_, b_257_Y p_i50264_3_) {
            super(StructurePieceType.Q_4569_t, p_i50264_1_);
            this.n_1700_B(p_i50264_3_);
            this.h_1847_R = p_i50264_2_;
        }

        public u_2550_I(b_2085_h p_i50265_1_, U_2912_j p_i50265_2_) {
            super(StructurePieceType.Q_4569_t, p_i50265_2_);
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            this.n_1700_B((Q_4569_t)componentIn, listIn, rand, 5, 3, true);
            this.n_1700_B((Q_4569_t)componentIn, listIn, rand, 5, 11, true);
        }

        public static u_2550_I n_1700_B(List<E_3771_B> p_175875_0_, int p_175875_1_, int p_175875_2_, int p_175875_3_, b_257_Y p_175875_4_, int p_175875_5_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_175875_1_, p_175875_2_, p_175875_3_, -5, -3, 0, 13, 14, 13, p_175875_4_);
            return u_2550_I.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_175875_0_, mutableboundingbox) == null ? new u_2550_I(p_175875_5_, mutableboundingbox, p_175875_4_) : null;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, 0, 12, 4, 12, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 5, 0, 12, 13, 12, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 5, 0, 1, 12, 12, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 11, 5, 0, 12, 12, 12, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 5, 11, 4, 12, 12, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 8, 5, 11, 10, 12, 12, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 9, 11, 7, 12, 12, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 5, 0, 4, 12, 1, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 8, 5, 0, 10, 12, 1, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 9, 0, 7, 12, 1, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 11, 2, 10, 12, 10, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            K_4074_S blockstate = (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.M_182_A, true)).n_1700_B(FenceBlock.h_1847_R, true);
            K_4074_S blockstate1 = (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.P_4830_p, true)).n_1700_B(FenceBlock.Q_4569_t, true);
            K_4074_S blockstate2 = (K_4074_S)blockstate1.n_1700_B(FenceBlock.M_182_A, true);
            K_4074_S blockstate3 = (K_4074_S)blockstate1.n_1700_B(FenceBlock.h_1847_R, true);
            for (int i = 1; i <= 11; i += 2) {
                this.n_1700_B(p_230383_1_, p_230383_5_, i, 10, 0, i, 11, 0, blockstate, blockstate, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, i, 10, 12, i, 11, 12, blockstate, blockstate, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 10, i, 0, 11, i, blockstate1, blockstate1, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 12, 10, i, 12, 11, i, blockstate1, blockstate1, false);
                this.n_1700_B(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), i, 13, 0, p_230383_5_);
                this.n_1700_B(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), i, 13, 12, p_230383_5_);
                this.n_1700_B(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), 0, 13, i, p_230383_5_);
                this.n_1700_B(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), 12, 13, i, p_230383_5_);
                if (i == 11) continue;
                this.n_1700_B(p_230383_1_, blockstate, i + 1, 13, 0, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate, i + 1, 13, 12, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate1, 0, 13, i + 1, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate1, 12, 13, i + 1, p_230383_5_);
            }
            this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.P_4830_p, true)).n_1700_B(FenceBlock.h_1847_R, true), 0, 13, 0, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.Q_4569_t, true)).n_1700_B(FenceBlock.h_1847_R, true), 0, 13, 12, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.Q_4569_t, true)).n_1700_B(FenceBlock.M_182_A, true), 12, 13, 12, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.d_4500_Q.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.P_4830_p, true)).n_1700_B(FenceBlock.M_182_A, true), 12, 13, 0, p_230383_5_);
            for (int j1 = 3; j1 <= 9; j1 += 2) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 7, j1, 1, 8, j1, blockstate2, blockstate2, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 11, 7, j1, 11, 8, j1, blockstate3, blockstate3, false);
            }
            K_4074_S blockstate4 = (K_4074_S)a_3742_W.O_2761_o.multiplayerClientSuggestionProvider().n_1700_B(z_2909_G.P_4830_p, b_257_Y.R_4764_Y);
            for (int j = 0; j <= 6; ++j) {
                int k = j + 4;
                for (int l = 5; l <= 7; ++l) {
                    this.n_1700_B(p_230383_1_, blockstate4, l, 5 + j, k, p_230383_5_);
                }
                if (k >= 5 && k <= 8) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 5, 5, k, 7, j + 4, k, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
                } else if (k >= 9 && k <= 10) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 5, 8, k, 7, j + 4, k, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
                }
                if (j < 1) continue;
                this.n_1700_B(p_230383_1_, p_230383_5_, 5, 6 + j, k, 7, 9 + j, k, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
            }
            for (int k1 = 5; k1 <= 7; ++k1) {
                this.n_1700_B(p_230383_1_, blockstate4, k1, 12, 11, p_230383_5_);
            }
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 6, 7, 5, 7, 7, blockstate3, blockstate3, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 7, 6, 7, 7, 7, 7, blockstate2, blockstate2, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 13, 12, 7, 13, 12, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 5, 2, 3, 5, 3, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 5, 9, 3, 5, 10, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 5, 4, 2, 5, 8, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 9, 5, 2, 10, 5, 3, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 9, 5, 9, 10, 5, 10, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 10, 5, 4, 10, 5, 8, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            K_4074_S blockstate5 = (K_4074_S)blockstate4.n_1700_B(z_2909_G.P_4830_p, b_257_Y.u_1723_Y);
            K_4074_S blockstate6 = (K_4074_S)blockstate4.n_1700_B(z_2909_G.P_4830_p, b_257_Y.P_1922_E);
            this.n_1700_B(p_230383_1_, blockstate6, 4, 5, 2, p_230383_5_);
            this.n_1700_B(p_230383_1_, blockstate6, 4, 5, 3, p_230383_5_);
            this.n_1700_B(p_230383_1_, blockstate6, 4, 5, 9, p_230383_5_);
            this.n_1700_B(p_230383_1_, blockstate6, 4, 5, 10, p_230383_5_);
            this.n_1700_B(p_230383_1_, blockstate5, 8, 5, 2, p_230383_5_);
            this.n_1700_B(p_230383_1_, blockstate5, 8, 5, 3, p_230383_5_);
            this.n_1700_B(p_230383_1_, blockstate5, 8, 5, 9, p_230383_5_);
            this.n_1700_B(p_230383_1_, blockstate5, 8, 5, 10, p_230383_5_);
            this.n_1700_B(p_230383_1_, p_230383_5_, 3, 4, 4, 4, 4, 8, a_3742_W.C_415_h.multiplayerClientSuggestionProvider(), a_3742_W.C_415_h.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 8, 4, 4, 9, 4, 8, a_3742_W.C_415_h.multiplayerClientSuggestionProvider(), a_3742_W.C_415_h.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 3, 5, 4, 4, 5, 8, a_3742_W.W_3729_Q.multiplayerClientSuggestionProvider(), a_3742_W.W_3729_Q.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 8, 5, 4, 9, 5, 8, a_3742_W.W_3729_Q.multiplayerClientSuggestionProvider(), a_3742_W.W_3729_Q.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 2, 0, 8, 2, 12, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 2, 4, 12, 2, 8, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 0, 0, 8, 1, 3, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 0, 9, 8, 1, 12, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 4, 3, 1, 8, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 9, 0, 4, 12, 1, 8, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            for (int l1 = 4; l1 <= 8; ++l1) {
                for (int i1 = 0; i1 <= 2; ++i1) {
                    this.J_1907_R(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), l1, -1, i1, p_230383_5_);
                    this.J_1907_R(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), l1, -1, 12 - i1, p_230383_5_);
                }
            }
            for (int i2 = 0; i2 <= 2; ++i2) {
                for (int j2 = 4; j2 <= 8; ++j2) {
                    this.J_1907_R(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), i2, -1, j2, p_230383_5_);
                    this.J_1907_R(p_230383_1_, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), 12 - i2, -1, j2, p_230383_5_);
                }
            }
            return true;
        }
    }

    public static class Q_4569_t
    extends w_1484_f {
        public P_4830_p n_1700_B;
        public List<P_4830_p> J_1907_R;
        public List<P_4830_p> R_4764_Y;
        public final List<E_3771_B> G_564_y = Lists.newArrayList();

        public Q_4569_t(Random p_i2059_1_, int p_i2059_2_, int p_i2059_3_) {
            super(p_i2059_1_, p_i2059_2_, p_i2059_3_);
            this.J_1907_R = Lists.newArrayList();
            for (P_4830_p fortresspieces$pieceweight : n_1700_B) {
                fortresspieces$pieceweight.R_4764_Y = 0;
                this.J_1907_R.add(fortresspieces$pieceweight);
            }
            this.R_4764_Y = Lists.newArrayList();
            for (P_4830_p fortresspieces$pieceweight1 : J_1907_R) {
                fortresspieces$pieceweight1.R_4764_Y = 0;
                this.R_4764_Y.add(fortresspieces$pieceweight1);
            }
        }

        public Q_4569_t(b_2085_h p_i50253_1_, U_2912_j p_i50253_2_) {
            super(StructurePieceType.w_1457_N, p_i50253_2_);
        }
    }

    static abstract class M_588_G
    extends E_3771_B {
        protected M_588_G(StructurePieceType p_i50260_1_, int p_i50260_2_) {
            super(p_i50260_1_, p_i50260_2_);
        }

        public M_588_G(StructurePieceType p_i50261_1_, U_2912_j p_i50261_2_) {
            super(p_i50261_1_, p_i50261_2_);
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
        }

        private int n_1700_B(List<P_4830_p> p_74960_1_) {
            boolean flag = false;
            int i = 0;
            for (P_4830_p fortresspieces$pieceweight : p_74960_1_) {
                if (fortresspieces$pieceweight.G_564_y > 0 && fortresspieces$pieceweight.R_4764_Y < fortresspieces$pieceweight.G_564_y) {
                    flag = true;
                }
                i += fortresspieces$pieceweight.J_1907_R;
            }
            return flag ? i : -1;
        }

        private M_588_G n_1700_B(Q_4569_t p_175871_1_, List<P_4830_p> p_175871_2_, List<E_3771_B> p_175871_3_, Random p_175871_4_, int p_175871_5_, int p_175871_6_, int p_175871_7_, b_257_Y p_175871_8_, int p_175871_9_) {
            int i = this.n_1700_B(p_175871_2_);
            boolean flag = i > 0 && p_175871_9_ <= 30;
            int j = 0;
            block0: while (j < 5 && flag) {
                ++j;
                int k = p_175871_4_.nextInt(i);
                for (P_4830_p fortresspieces$pieceweight : p_175871_2_) {
                    if ((k -= fortresspieces$pieceweight.J_1907_R) >= 0) continue;
                    if (!fortresspieces$pieceweight.n_1700_B(p_175871_9_) || fortresspieces$pieceweight == p_175871_1_.n_1700_B && !fortresspieces$pieceweight.P_1922_E) continue block0;
                    M_588_G fortresspieces$piece = i_3880_A.n_1700_B(fortresspieces$pieceweight, p_175871_3_, p_175871_4_, p_175871_5_, p_175871_6_, p_175871_7_, p_175871_8_, p_175871_9_);
                    if (fortresspieces$piece == null) continue;
                    ++fortresspieces$pieceweight.R_4764_Y;
                    p_175871_1_.n_1700_B = fortresspieces$pieceweight;
                    if (!fortresspieces$pieceweight.n_1700_B()) {
                        p_175871_2_.remove(fortresspieces$pieceweight);
                    }
                    return fortresspieces$piece;
                }
            }
            return t_148_a.n_1700_B(p_175871_3_, p_175871_4_, p_175871_5_, p_175871_6_, p_175871_7_, p_175871_8_, p_175871_9_);
        }

        private E_3771_B n_1700_B(Q_4569_t p_175870_1_, List<E_3771_B> p_175870_2_, Random p_175870_3_, int p_175870_4_, int p_175870_5_, int p_175870_6_, @Nullable b_257_Y p_175870_7_, int p_175870_8_, boolean p_175870_9_) {
            if (Math.abs(p_175870_4_ - p_175870_1_.v_4262_N().n_1700_B) <= 112 && Math.abs(p_175870_6_ - p_175870_1_.v_4262_N().R_4764_Y) <= 112) {
                M_588_G structurepiece;
                List<P_4830_p> list = p_175870_1_.J_1907_R;
                if (p_175870_9_) {
                    list = p_175870_1_.R_4764_Y;
                }
                if ((structurepiece = this.n_1700_B(p_175870_1_, list, p_175870_2_, p_175870_3_, p_175870_4_, p_175870_5_, p_175870_6_, p_175870_7_, p_175870_8_ + 1)) != null) {
                    p_175870_2_.add(structurepiece);
                    p_175870_1_.G_564_y.add(structurepiece);
                }
                return structurepiece;
            }
            return t_148_a.n_1700_B(p_175870_2_, p_175870_3_, p_175870_4_, p_175870_5_, p_175870_6_, p_175870_7_, p_175870_8_);
        }

        @Nullable
        protected E_3771_B n_1700_B(Q_4569_t p_74963_1_, List<E_3771_B> p_74963_2_, Random p_74963_3_, int p_74963_4_, int p_74963_5_, boolean p_74963_6_) {
            b_257_Y direction = this.t_148_a();
            if (direction != null) {
                switch (direction) {
                    case R_4764_Y: {
                        return this.n_1700_B(p_74963_1_, p_74963_2_, p_74963_3_, this.h_1847_R.n_1700_B + p_74963_4_, this.h_1847_R.J_1907_R + p_74963_5_, this.h_1847_R.R_4764_Y - 1, direction, this.w_1484_f(), p_74963_6_);
                    }
                    case G_564_y: {
                        return this.n_1700_B(p_74963_1_, p_74963_2_, p_74963_3_, this.h_1847_R.n_1700_B + p_74963_4_, this.h_1847_R.J_1907_R + p_74963_5_, this.h_1847_R.u_1723_Y + 1, direction, this.w_1484_f(), p_74963_6_);
                    }
                    case P_1922_E: {
                        return this.n_1700_B(p_74963_1_, p_74963_2_, p_74963_3_, this.h_1847_R.n_1700_B - 1, this.h_1847_R.J_1907_R + p_74963_5_, this.h_1847_R.R_4764_Y + p_74963_4_, direction, this.w_1484_f(), p_74963_6_);
                    }
                    case u_1723_Y: {
                        return this.n_1700_B(p_74963_1_, p_74963_2_, p_74963_3_, this.h_1847_R.G_564_y + 1, this.h_1847_R.J_1907_R + p_74963_5_, this.h_1847_R.R_4764_Y + p_74963_4_, direction, this.w_1484_f(), p_74963_6_);
                    }
                }
            }
            return null;
        }

        @Nullable
        protected E_3771_B J_1907_R(Q_4569_t p_74961_1_, List<E_3771_B> p_74961_2_, Random p_74961_3_, int p_74961_4_, int p_74961_5_, boolean p_74961_6_) {
            b_257_Y direction = this.t_148_a();
            if (direction != null) {
                switch (direction) {
                    case R_4764_Y: {
                        return this.n_1700_B(p_74961_1_, p_74961_2_, p_74961_3_, this.h_1847_R.n_1700_B - 1, this.h_1847_R.J_1907_R + p_74961_4_, this.h_1847_R.R_4764_Y + p_74961_5_, b_257_Y.P_1922_E, this.w_1484_f(), p_74961_6_);
                    }
                    case G_564_y: {
                        return this.n_1700_B(p_74961_1_, p_74961_2_, p_74961_3_, this.h_1847_R.n_1700_B - 1, this.h_1847_R.J_1907_R + p_74961_4_, this.h_1847_R.R_4764_Y + p_74961_5_, b_257_Y.P_1922_E, this.w_1484_f(), p_74961_6_);
                    }
                    case P_1922_E: {
                        return this.n_1700_B(p_74961_1_, p_74961_2_, p_74961_3_, this.h_1847_R.n_1700_B + p_74961_5_, this.h_1847_R.J_1907_R + p_74961_4_, this.h_1847_R.R_4764_Y - 1, b_257_Y.R_4764_Y, this.w_1484_f(), p_74961_6_);
                    }
                    case u_1723_Y: {
                        return this.n_1700_B(p_74961_1_, p_74961_2_, p_74961_3_, this.h_1847_R.n_1700_B + p_74961_5_, this.h_1847_R.J_1907_R + p_74961_4_, this.h_1847_R.R_4764_Y - 1, b_257_Y.R_4764_Y, this.w_1484_f(), p_74961_6_);
                    }
                }
            }
            return null;
        }

        @Nullable
        protected E_3771_B R_4764_Y(Q_4569_t p_74965_1_, List<E_3771_B> p_74965_2_, Random p_74965_3_, int p_74965_4_, int p_74965_5_, boolean p_74965_6_) {
            b_257_Y direction = this.t_148_a();
            if (direction != null) {
                switch (direction) {
                    case R_4764_Y: {
                        return this.n_1700_B(p_74965_1_, p_74965_2_, p_74965_3_, this.h_1847_R.G_564_y + 1, this.h_1847_R.J_1907_R + p_74965_4_, this.h_1847_R.R_4764_Y + p_74965_5_, b_257_Y.u_1723_Y, this.w_1484_f(), p_74965_6_);
                    }
                    case G_564_y: {
                        return this.n_1700_B(p_74965_1_, p_74965_2_, p_74965_3_, this.h_1847_R.G_564_y + 1, this.h_1847_R.J_1907_R + p_74965_4_, this.h_1847_R.R_4764_Y + p_74965_5_, b_257_Y.u_1723_Y, this.w_1484_f(), p_74965_6_);
                    }
                    case P_1922_E: {
                        return this.n_1700_B(p_74965_1_, p_74965_2_, p_74965_3_, this.h_1847_R.n_1700_B + p_74965_5_, this.h_1847_R.J_1907_R + p_74965_4_, this.h_1847_R.u_1723_Y + 1, b_257_Y.G_564_y, this.w_1484_f(), p_74965_6_);
                    }
                    case u_1723_Y: {
                        return this.n_1700_B(p_74965_1_, p_74965_2_, p_74965_3_, this.h_1847_R.n_1700_B + p_74965_5_, this.h_1847_R.J_1907_R + p_74965_4_, this.h_1847_R.u_1723_Y + 1, b_257_Y.G_564_y, this.w_1484_f(), p_74965_6_);
                    }
                }
            }
            return null;
        }

        protected static boolean n_1700_B(BoundingBox p_74964_0_) {
            return p_74964_0_ != null && p_74964_0_.J_1907_R > 10;
        }
    }

    public static class t_148_a
    extends M_588_G {
        private final int n_1700_B;

        public t_148_a(int p_i45621_1_, Random p_i45621_2_, BoundingBox p_i45621_3_, b_257_Y p_i45621_4_) {
            super(StructurePieceType.u_1723_Y, p_i45621_1_);
            this.n_1700_B(p_i45621_4_);
            this.h_1847_R = p_i45621_3_;
            this.n_1700_B = p_i45621_2_.nextInt();
        }

        public t_148_a(b_2085_h p_i50285_1_, U_2912_j p_i50285_2_) {
            super(StructurePieceType.u_1723_Y, p_i50285_2_);
            this.n_1700_B = p_i50285_2_.w_1484_f("Seed");
        }

        public static t_148_a n_1700_B(List<E_3771_B> p_175884_0_, Random p_175884_1_, int p_175884_2_, int p_175884_3_, int p_175884_4_, b_257_Y p_175884_5_, int p_175884_6_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_175884_2_, p_175884_3_, p_175884_4_, -1, -3, 0, 5, 10, 8, p_175884_5_);
            return t_148_a.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_175884_0_, mutableboundingbox) == null ? new t_148_a(p_175884_6_, p_175884_1_, mutableboundingbox, p_175884_5_) : null;
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
            super.n_1700_B(tagCompound);
            tagCompound.J_1907_R("Seed", this.n_1700_B);
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            Random random = new Random(this.n_1700_B);
            for (int i = 0; i <= 4; ++i) {
                for (int j = 3; j <= 4; ++j) {
                    int k = random.nextInt(8);
                    this.n_1700_B(p_230383_1_, p_230383_5_, i, j, 0, i, j, k, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
                }
            }
            int l = random.nextInt(8);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 5, 0, 0, 5, l, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            l = random.nextInt(8);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 5, 0, 4, 5, l, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            for (int i1 = 0; i1 <= 4; ++i1) {
                int k1 = random.nextInt(5);
                this.n_1700_B(p_230383_1_, p_230383_5_, i1, 2, 0, i1, 2, k1, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
            }
            for (int j1 = 0; j1 <= 4; ++j1) {
                for (int l1 = 0; l1 <= 1; ++l1) {
                    int i2 = random.nextInt(3);
                    this.n_1700_B(p_230383_1_, p_230383_5_, j1, l1, 0, j1, l1, i2, a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), a_3742_W.h_1015_G.multiplayerClientSuggestionProvider(), false);
                }
            }
            return true;
        }
    }
}


