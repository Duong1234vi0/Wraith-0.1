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
import lightning.product.C_1985_D;
import lightning.product.EndPortalFrameBlock;
import lightning.product.E_3771_B;
import lightning.product.J_3017_d;
import lightning.product.WallTorchBlock;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.S_1431_H;
import lightning.product.WorldGenLevel;
import lightning.product.U_2912_j;
import lightning.product.V_1045_N;
import lightning.product.Y_1387_d;
import lightning.product.a_3742_W;
import lightning.product.b_2085_h;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.StructurePieceType;
import lightning.product.g_3212_H;
import lightning.product.i_2154_H;
import lightning.product.SpawnerBlockEntity;
import lightning.product.n_1769_f;
import lightning.product.o_4810_o;
import lightning.product.IronBarsBlock;
import lightning.product.t_5_h;
import lightning.product.y_3008_A;
import lightning.product.z_1753_f;
import lightning.product.z_2909_G;

public class g_4102_b {
    private static final u_1723_Y[] n_1700_B = new u_1723_Y[]{new u_1723_Y(Q_4569_t.class, 40, 0), new u_1723_Y(w_1484_f.class, 5, 5), new u_1723_Y(G_564_y.class, 20, 0), new u_1723_Y(t_148_a.class, 20, 0), new u_1723_Y(s_956_w.class, 10, 6), new u_1723_Y(P_4830_p.class, 5, 5), new u_1723_Y(u_2550_I.class, 5, 5), new u_1723_Y(R_4764_Y.class, 5, 4), new u_1723_Y(n_1700_B.class, 5, 4), new u_1723_Y(P_1922_E.class, 10, 2){

        @Override
        public boolean n_1700_B(int p_75189_1_) {
            return super.n_1700_B(p_75189_1_) && p_75189_1_ > 4;
        }
    }, new u_1723_Y(v_4262_N.class, 20, 1){

        @Override
        public boolean n_1700_B(int p_75189_1_) {
            return super.n_1700_B(p_75189_1_) && p_75189_1_ > 5;
        }
    }};
    private static List<u_1723_Y> J_1907_R;
    private static Class<? extends M_182_A> R_4764_Y;
    private static int G_564_y;
    private static final h_1847_R P_1922_E;

    public static void n_1700_B() {
        J_1907_R = Lists.newArrayList();
        for (u_1723_Y strongholdpieces$pieceweight : n_1700_B) {
            strongholdpieces$pieceweight.R_4764_Y = 0;
            J_1907_R.add(strongholdpieces$pieceweight);
        }
        R_4764_Y = null;
    }

    private static boolean J_1907_R() {
        boolean flag = false;
        G_564_y = 0;
        for (u_1723_Y strongholdpieces$pieceweight : J_1907_R) {
            if (strongholdpieces$pieceweight.G_564_y > 0 && strongholdpieces$pieceweight.R_4764_Y < strongholdpieces$pieceweight.G_564_y) {
                flag = true;
            }
            G_564_y += strongholdpieces$pieceweight.J_1907_R;
        }
        return flag;
    }

    private static M_182_A n_1700_B(Class<? extends M_182_A> clazz, List<E_3771_B> p_175954_1_, Random p_175954_2_, int p_175954_3_, int p_175954_4_, int p_175954_5_, @Nullable b_257_Y p_175954_6_, int p_175954_7_) {
        M_182_A strongholdpieces$stronghold = null;
        if (clazz == Q_4569_t.class) {
            strongholdpieces$stronghold = Q_4569_t.n_1700_B(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        } else if (clazz == w_1484_f.class) {
            strongholdpieces$stronghold = w_1484_f.n_1700_B(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        } else if (clazz == G_564_y.class) {
            strongholdpieces$stronghold = lightning.product.g_4102_b$G_564_y.n_1700_B(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        } else if (clazz == t_148_a.class) {
            strongholdpieces$stronghold = t_148_a.n_1700_B(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        } else if (clazz == s_956_w.class) {
            strongholdpieces$stronghold = s_956_w.n_1700_B(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        } else if (clazz == P_4830_p.class) {
            strongholdpieces$stronghold = P_4830_p.n_1700_B(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        } else if (clazz == u_2550_I.class) {
            strongholdpieces$stronghold = u_2550_I.n_1700_B(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        } else if (clazz == R_4764_Y.class) {
            strongholdpieces$stronghold = lightning.product.g_4102_b$R_4764_Y.n_1700_B(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        } else if (clazz == n_1700_B.class) {
            strongholdpieces$stronghold = lightning.product.g_4102_b$n_1700_B.n_1700_B(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        } else if (clazz == P_1922_E.class) {
            strongholdpieces$stronghold = lightning.product.g_4102_b$P_1922_E.n_1700_B(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        } else if (clazz == v_4262_N.class) {
            strongholdpieces$stronghold = v_4262_N.n_1700_B(p_175954_1_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        return strongholdpieces$stronghold;
    }

    private static M_182_A n_1700_B(M_588_G p_175955_0_, List<E_3771_B> p_175955_1_, Random p_175955_2_, int p_175955_3_, int p_175955_4_, int p_175955_5_, b_257_Y p_175955_6_, int p_175955_7_) {
        if (!g_4102_b.J_1907_R()) {
            return null;
        }
        if (R_4764_Y != null) {
            M_182_A strongholdpieces$stronghold = g_4102_b.n_1700_B(R_4764_Y, p_175955_1_, p_175955_2_, p_175955_3_, p_175955_4_, p_175955_5_, p_175955_6_, p_175955_7_);
            R_4764_Y = null;
            if (strongholdpieces$stronghold != null) {
                return strongholdpieces$stronghold;
            }
        }
        int j = 0;
        block0: while (j < 5) {
            ++j;
            int i = p_175955_2_.nextInt(G_564_y);
            for (u_1723_Y strongholdpieces$pieceweight : J_1907_R) {
                if ((i -= strongholdpieces$pieceweight.J_1907_R) >= 0) continue;
                if (!strongholdpieces$pieceweight.n_1700_B(p_175955_7_) || strongholdpieces$pieceweight == p_175955_0_.n_1700_B) continue block0;
                M_182_A strongholdpieces$stronghold1 = g_4102_b.n_1700_B(strongholdpieces$pieceweight.n_1700_B, p_175955_1_, p_175955_2_, p_175955_3_, p_175955_4_, p_175955_5_, p_175955_6_, p_175955_7_);
                if (strongholdpieces$stronghold1 == null) continue;
                ++strongholdpieces$pieceweight.R_4764_Y;
                p_175955_0_.n_1700_B = strongholdpieces$pieceweight;
                if (!strongholdpieces$pieceweight.n_1700_B()) {
                    J_1907_R.remove(strongholdpieces$pieceweight);
                }
                return strongholdpieces$stronghold1;
            }
        }
        BoundingBox mutableboundingbox = lightning.product.g_4102_b$J_1907_R.n_1700_B(p_175955_1_, p_175955_2_, p_175955_3_, p_175955_4_, p_175955_5_, p_175955_6_);
        return mutableboundingbox != null && mutableboundingbox.J_1907_R > 1 ? new J_1907_R(p_175955_7_, mutableboundingbox, p_175955_6_) : null;
    }

    private static E_3771_B J_1907_R(M_588_G p_175953_0_, List<E_3771_B> p_175953_1_, Random p_175953_2_, int p_175953_3_, int p_175953_4_, int p_175953_5_, @Nullable b_257_Y p_175953_6_, int p_175953_7_) {
        if (p_175953_7_ > 50) {
            return null;
        }
        if (Math.abs(p_175953_3_ - p_175953_0_.v_4262_N().n_1700_B) <= 112 && Math.abs(p_175953_5_ - p_175953_0_.v_4262_N().R_4764_Y) <= 112) {
            M_182_A structurepiece = g_4102_b.n_1700_B(p_175953_0_, p_175953_1_, p_175953_2_, p_175953_3_, p_175953_4_, p_175953_5_, p_175953_6_, p_175953_7_ + 1);
            if (structurepiece != null) {
                p_175953_1_.add(structurepiece);
                p_175953_0_.R_4764_Y.add(structurepiece);
            }
            return structurepiece;
        }
        return null;
    }

    static {
        P_1922_E = new h_1847_R();
    }

    static class u_1723_Y {
        public final Class<? extends M_182_A> n_1700_B;
        public final int J_1907_R;
        public int R_4764_Y;
        public final int G_564_y;

        public u_1723_Y(Class<? extends M_182_A> p_i2076_1_, int p_i2076_2_, int p_i2076_3_) {
            this.n_1700_B = p_i2076_1_;
            this.J_1907_R = p_i2076_2_;
            this.G_564_y = p_i2076_3_;
        }

        public boolean n_1700_B(int p_75189_1_) {
            return this.G_564_y == 0 || this.R_4764_Y < this.G_564_y;
        }

        public boolean n_1700_B() {
            return this.G_564_y == 0 || this.R_4764_Y < this.G_564_y;
        }
    }

    public static class Q_4569_t
    extends M_182_A {
        private final boolean n_1700_B;
        private final boolean J_1907_R;

        public Q_4569_t(int p_i45573_1_, Random p_i45573_2_, BoundingBox p_i45573_3_, b_257_Y p_i45573_4_) {
            super(StructurePieceType.t_4043_B, p_i45573_1_);
            this.n_1700_B(p_i45573_4_);
            this.G_564_y = this.n_1700_B(p_i45573_2_);
            this.h_1847_R = p_i45573_3_;
            this.n_1700_B = p_i45573_2_.nextInt(2) == 0;
            this.J_1907_R = p_i45573_2_.nextInt(2) == 0;
        }

        public Q_4569_t(b_2085_h p_i50115_1_, U_2912_j p_i50115_2_) {
            super(StructurePieceType.t_4043_B, p_i50115_2_);
            this.n_1700_B = p_i50115_2_.t_1786_h("Left");
            this.J_1907_R = p_i50115_2_.t_1786_h("Right");
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
            super.n_1700_B(tagCompound);
            tagCompound.n_1700_B("Left", this.n_1700_B);
            tagCompound.n_1700_B("Right", this.J_1907_R);
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            this.n_1700_B((M_588_G)componentIn, listIn, rand, 1, 1);
            if (this.n_1700_B) {
                this.J_1907_R((M_588_G)componentIn, listIn, rand, 1, 2);
            }
            if (this.J_1907_R) {
                this.R_4764_Y((M_588_G)componentIn, listIn, rand, 1, 2);
            }
        }

        public static Q_4569_t n_1700_B(List<E_3771_B> p_175862_0_, Random p_175862_1_, int p_175862_2_, int p_175862_3_, int p_175862_4_, b_257_Y p_175862_5_, int p_175862_6_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_175862_2_, p_175862_3_, p_175862_4_, -1, -1, 0, 5, 5, 7, p_175862_5_);
            return Q_4569_t.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_175862_0_, mutableboundingbox) == null ? new Q_4569_t(p_175862_6_, p_175862_1_, mutableboundingbox, p_175862_5_) : null;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 0, 4, 4, 6, true, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_4_, p_230383_5_, this.G_564_y, 1, 1, 0);
            this.n_1700_B(p_230383_1_, p_230383_4_, p_230383_5_, M_182_A.n_1700_B.n_1700_B, 1, 1, 6);
            K_4074_S blockstate = (K_4074_S)a_3742_W.C_1269_X.multiplayerClientSuggestionProvider().n_1700_B(WallTorchBlock.P_4830_p, b_257_Y.u_1723_Y);
            K_4074_S blockstate1 = (K_4074_S)a_3742_W.C_1269_X.multiplayerClientSuggestionProvider().n_1700_B(WallTorchBlock.P_4830_p, b_257_Y.P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 0.1f, 1, 2, 1, blockstate);
            this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 0.1f, 3, 2, 1, blockstate1);
            this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 0.1f, 1, 2, 5, blockstate);
            this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 0.1f, 3, 2, 5, blockstate1);
            if (this.n_1700_B) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 2, 0, 3, 4, P_4830_p, P_4830_p, false);
            }
            if (this.J_1907_R) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 4, 1, 2, 4, 3, 4, P_4830_p, P_4830_p, false);
            }
            return true;
        }
    }

    public static class w_1484_f
    extends M_182_A {
        public w_1484_f(int p_i45576_1_, Random p_i45576_2_, BoundingBox p_i45576_3_, b_257_Y p_i45576_4_) {
            super(StructurePieceType.Z_875_P, p_i45576_1_);
            this.n_1700_B(p_i45576_4_);
            this.G_564_y = this.n_1700_B(p_i45576_2_);
            this.h_1847_R = p_i45576_3_;
        }

        public w_1484_f(b_2085_h p_i50130_1_, U_2912_j p_i50130_2_) {
            super(StructurePieceType.Z_875_P, p_i50130_2_);
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            this.n_1700_B((M_588_G)componentIn, listIn, rand, 1, 1);
        }

        public static w_1484_f n_1700_B(List<E_3771_B> p_175860_0_, Random p_175860_1_, int p_175860_2_, int p_175860_3_, int p_175860_4_, b_257_Y p_175860_5_, int p_175860_6_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_175860_2_, p_175860_3_, p_175860_4_, -1, -1, 0, 9, 5, 11, p_175860_5_);
            return w_1484_f.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_175860_0_, mutableboundingbox) == null ? new w_1484_f(p_175860_6_, p_175860_1_, mutableboundingbox, p_175860_5_) : null;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 0, 8, 4, 10, true, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_4_, p_230383_5_, this.G_564_y, 1, 1, 0);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, 10, 3, 3, 10, P_4830_p, P_4830_p, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 1, 1, 4, 3, 1, false, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 1, 3, 4, 3, 3, false, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 1, 7, 4, 3, 7, false, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 1, 9, 4, 3, 9, false, p_230383_4_, P_1922_E);
            for (int i = 1; i <= 3; ++i) {
                this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.Z_4720_K.multiplayerClientSuggestionProvider().n_1700_B(IronBarsBlock.P_4830_p, true)).n_1700_B(IronBarsBlock.Q_4569_t, true), 4, i, 4, p_230383_5_);
                this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)((K_4074_S)a_3742_W.Z_4720_K.multiplayerClientSuggestionProvider().n_1700_B(IronBarsBlock.P_4830_p, true)).n_1700_B(IronBarsBlock.Q_4569_t, true)).n_1700_B(IronBarsBlock.h_1847_R, true), 4, i, 5, p_230383_5_);
                this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.Z_4720_K.multiplayerClientSuggestionProvider().n_1700_B(IronBarsBlock.P_4830_p, true)).n_1700_B(IronBarsBlock.Q_4569_t, true), 4, i, 6, p_230383_5_);
                this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.Z_4720_K.multiplayerClientSuggestionProvider().n_1700_B(IronBarsBlock.M_182_A, true)).n_1700_B(IronBarsBlock.h_1847_R, true), 5, i, 5, p_230383_5_);
                this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.Z_4720_K.multiplayerClientSuggestionProvider().n_1700_B(IronBarsBlock.M_182_A, true)).n_1700_B(IronBarsBlock.h_1847_R, true), 6, i, 5, p_230383_5_);
                this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.Z_4720_K.multiplayerClientSuggestionProvider().n_1700_B(IronBarsBlock.M_182_A, true)).n_1700_B(IronBarsBlock.h_1847_R, true), 7, i, 5, p_230383_5_);
            }
            this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.Z_4720_K.multiplayerClientSuggestionProvider().n_1700_B(IronBarsBlock.P_4830_p, true)).n_1700_B(IronBarsBlock.Q_4569_t, true), 4, 3, 2, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.Z_4720_K.multiplayerClientSuggestionProvider().n_1700_B(IronBarsBlock.P_4830_p, true)).n_1700_B(IronBarsBlock.Q_4569_t, true), 4, 3, 8, p_230383_5_);
            K_4074_S blockstate1 = (K_4074_S)a_3742_W.h_2739_B.multiplayerClientSuggestionProvider().n_1700_B(S_1431_H.P_4830_p, b_257_Y.P_1922_E);
            K_4074_S blockstate = (K_4074_S)((K_4074_S)a_3742_W.h_2739_B.multiplayerClientSuggestionProvider().n_1700_B(S_1431_H.P_4830_p, b_257_Y.P_1922_E)).n_1700_B(S_1431_H.t_1786_h, g_3212_H.n_1700_B);
            this.n_1700_B(p_230383_1_, blockstate1, 4, 1, 2, p_230383_5_);
            this.n_1700_B(p_230383_1_, blockstate, 4, 2, 2, p_230383_5_);
            this.n_1700_B(p_230383_1_, blockstate1, 4, 1, 8, p_230383_5_);
            this.n_1700_B(p_230383_1_, blockstate, 4, 2, 8, p_230383_5_);
            return true;
        }
    }

    public static class G_564_y
    extends t_1786_h {
        public G_564_y(int p_i45579_1_, Random p_i45579_2_, BoundingBox p_i45579_3_, b_257_Y p_i45579_4_) {
            super(StructurePieceType.C_2741_M, p_i45579_1_);
            this.n_1700_B(p_i45579_4_);
            this.G_564_y = this.n_1700_B(p_i45579_2_);
            this.h_1847_R = p_i45579_3_;
        }

        public G_564_y(b_2085_h p_i50134_1_, U_2912_j p_i50134_2_) {
            super(StructurePieceType.C_2741_M, p_i50134_2_);
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            b_257_Y direction = this.t_148_a();
            if (direction != b_257_Y.R_4764_Y && direction != b_257_Y.u_1723_Y) {
                this.R_4764_Y((M_588_G)componentIn, listIn, rand, 1, 1);
            } else {
                this.J_1907_R((M_588_G)componentIn, listIn, rand, 1, 1);
            }
        }

        public static G_564_y n_1700_B(List<E_3771_B> p_175867_0_, Random p_175867_1_, int p_175867_2_, int p_175867_3_, int p_175867_4_, b_257_Y p_175867_5_, int p_175867_6_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_175867_2_, p_175867_3_, p_175867_4_, -1, -1, 0, 5, 5, 5, p_175867_5_);
            return lightning.product.g_4102_b$G_564_y.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_175867_0_, mutableboundingbox) == null ? new G_564_y(p_175867_6_, p_175867_1_, mutableboundingbox, p_175867_5_) : null;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 0, 4, 4, 4, true, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_4_, p_230383_5_, this.G_564_y, 1, 1, 0);
            b_257_Y direction = this.t_148_a();
            if (direction != b_257_Y.R_4764_Y && direction != b_257_Y.u_1723_Y) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 4, 1, 1, 4, 3, 3, P_4830_p, P_4830_p, false);
            } else {
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 1, 0, 3, 3, P_4830_p, P_4830_p, false);
            }
            return true;
        }
    }

    public static class t_148_a
    extends t_1786_h {
        public t_148_a(int p_i50127_1_, Random p_i50127_2_, BoundingBox p_i50127_3_, b_257_Y p_i50127_4_) {
            super(StructurePieceType.c_3005_b, p_i50127_1_);
            this.n_1700_B(p_i50127_4_);
            this.G_564_y = this.n_1700_B(p_i50127_2_);
            this.h_1847_R = p_i50127_3_;
        }

        public t_148_a(b_2085_h p_i50128_1_, U_2912_j p_i50128_2_) {
            super(StructurePieceType.c_3005_b, p_i50128_2_);
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            b_257_Y direction = this.t_148_a();
            if (direction != b_257_Y.R_4764_Y && direction != b_257_Y.u_1723_Y) {
                this.J_1907_R((M_588_G)componentIn, listIn, rand, 1, 1);
            } else {
                this.R_4764_Y((M_588_G)componentIn, listIn, rand, 1, 1);
            }
        }

        public static t_148_a n_1700_B(List<E_3771_B> p_214824_0_, Random p_214824_1_, int p_214824_2_, int p_214824_3_, int p_214824_4_, b_257_Y p_214824_5_, int p_214824_6_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_214824_2_, p_214824_3_, p_214824_4_, -1, -1, 0, 5, 5, 5, p_214824_5_);
            return t_148_a.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_214824_0_, mutableboundingbox) == null ? new t_148_a(p_214824_6_, p_214824_1_, mutableboundingbox, p_214824_5_) : null;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 0, 4, 4, 4, true, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_4_, p_230383_5_, this.G_564_y, 1, 1, 0);
            b_257_Y direction = this.t_148_a();
            if (direction != b_257_Y.R_4764_Y && direction != b_257_Y.u_1723_Y) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 1, 0, 3, 3, P_4830_p, P_4830_p, false);
            } else {
                this.n_1700_B(p_230383_1_, p_230383_5_, 4, 1, 1, 4, 3, 3, P_4830_p, P_4830_p, false);
            }
            return true;
        }
    }

    public static class s_956_w
    extends M_182_A {
        protected final int n_1700_B;

        public s_956_w(int p_i45575_1_, Random p_i45575_2_, BoundingBox p_i45575_3_, b_257_Y p_i45575_4_) {
            super(StructurePieceType.H_2857_Y, p_i45575_1_);
            this.n_1700_B(p_i45575_4_);
            this.G_564_y = this.n_1700_B(p_i45575_2_);
            this.h_1847_R = p_i45575_3_;
            this.n_1700_B = p_i45575_2_.nextInt(5);
        }

        public s_956_w(b_2085_h p_i50125_1_, U_2912_j p_i50125_2_) {
            super(StructurePieceType.H_2857_Y, p_i50125_2_);
            this.n_1700_B = p_i50125_2_.w_1484_f("Type");
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
            super.n_1700_B(tagCompound);
            tagCompound.J_1907_R("Type", this.n_1700_B);
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            this.n_1700_B((M_588_G)componentIn, listIn, rand, 4, 1);
            this.J_1907_R((M_588_G)componentIn, listIn, rand, 1, 4);
            this.R_4764_Y((M_588_G)componentIn, listIn, rand, 1, 4);
        }

        public static s_956_w n_1700_B(List<E_3771_B> p_175859_0_, Random p_175859_1_, int p_175859_2_, int p_175859_3_, int p_175859_4_, b_257_Y p_175859_5_, int p_175859_6_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_175859_2_, p_175859_3_, p_175859_4_, -4, -1, 0, 11, 7, 11, p_175859_5_);
            return s_956_w.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_175859_0_, mutableboundingbox) == null ? new s_956_w(p_175859_6_, p_175859_1_, mutableboundingbox, p_175859_5_) : null;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 0, 10, 6, 10, true, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_4_, p_230383_5_, this.G_564_y, 4, 1, 0);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 1, 10, 6, 3, 10, P_4830_p, P_4830_p, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 1, 4, 0, 3, 6, P_4830_p, P_4830_p, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 10, 1, 4, 10, 3, 6, P_4830_p, P_4830_p, false);
            switch (this.n_1700_B) {
                case 0: {
                    this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 5, 1, 5, p_230383_5_);
                    this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 5, 2, 5, p_230383_5_);
                    this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 5, 3, 5, p_230383_5_);
                    this.n_1700_B(p_230383_1_, (K_4074_S)a_3742_W.C_1269_X.multiplayerClientSuggestionProvider().n_1700_B(WallTorchBlock.P_4830_p, b_257_Y.P_1922_E), 4, 3, 5, p_230383_5_);
                    this.n_1700_B(p_230383_1_, (K_4074_S)a_3742_W.C_1269_X.multiplayerClientSuggestionProvider().n_1700_B(WallTorchBlock.P_4830_p, b_257_Y.u_1723_Y), 6, 3, 5, p_230383_5_);
                    this.n_1700_B(p_230383_1_, (K_4074_S)a_3742_W.C_1269_X.multiplayerClientSuggestionProvider().n_1700_B(WallTorchBlock.P_4830_p, b_257_Y.G_564_y), 5, 3, 4, p_230383_5_);
                    this.n_1700_B(p_230383_1_, (K_4074_S)a_3742_W.C_1269_X.multiplayerClientSuggestionProvider().n_1700_B(WallTorchBlock.P_4830_p, b_257_Y.R_4764_Y), 5, 3, 6, p_230383_5_);
                    this.n_1700_B(p_230383_1_, a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), 4, 1, 4, p_230383_5_);
                    this.n_1700_B(p_230383_1_, a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), 4, 1, 5, p_230383_5_);
                    this.n_1700_B(p_230383_1_, a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), 4, 1, 6, p_230383_5_);
                    this.n_1700_B(p_230383_1_, a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), 6, 1, 4, p_230383_5_);
                    this.n_1700_B(p_230383_1_, a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), 6, 1, 5, p_230383_5_);
                    this.n_1700_B(p_230383_1_, a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), 6, 1, 6, p_230383_5_);
                    this.n_1700_B(p_230383_1_, a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), 5, 1, 4, p_230383_5_);
                    this.n_1700_B(p_230383_1_, a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), 5, 1, 6, p_230383_5_);
                    break;
                }
                case 1: {
                    for (int i1 = 0; i1 < 5; ++i1) {
                        this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 3, 1, 3 + i1, p_230383_5_);
                        this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 7, 1, 3 + i1, p_230383_5_);
                        this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 3 + i1, 1, 3, p_230383_5_);
                        this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 3 + i1, 1, 7, p_230383_5_);
                    }
                    this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 5, 1, 5, p_230383_5_);
                    this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 5, 2, 5, p_230383_5_);
                    this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 5, 3, 5, p_230383_5_);
                    this.n_1700_B(p_230383_1_, a_3742_W.c_3005_b.multiplayerClientSuggestionProvider(), 5, 4, 5, p_230383_5_);
                    break;
                }
                case 2: {
                    for (int i = 1; i <= 9; ++i) {
                        this.n_1700_B(p_230383_1_, a_3742_W.P_4830_p.multiplayerClientSuggestionProvider(), 1, 3, i, p_230383_5_);
                        this.n_1700_B(p_230383_1_, a_3742_W.P_4830_p.multiplayerClientSuggestionProvider(), 9, 3, i, p_230383_5_);
                    }
                    for (int j = 1; j <= 9; ++j) {
                        this.n_1700_B(p_230383_1_, a_3742_W.P_4830_p.multiplayerClientSuggestionProvider(), j, 3, 1, p_230383_5_);
                        this.n_1700_B(p_230383_1_, a_3742_W.P_4830_p.multiplayerClientSuggestionProvider(), j, 3, 9, p_230383_5_);
                    }
                    this.n_1700_B(p_230383_1_, a_3742_W.P_4830_p.multiplayerClientSuggestionProvider(), 5, 1, 4, p_230383_5_);
                    this.n_1700_B(p_230383_1_, a_3742_W.P_4830_p.multiplayerClientSuggestionProvider(), 5, 1, 6, p_230383_5_);
                    this.n_1700_B(p_230383_1_, a_3742_W.P_4830_p.multiplayerClientSuggestionProvider(), 5, 3, 4, p_230383_5_);
                    this.n_1700_B(p_230383_1_, a_3742_W.P_4830_p.multiplayerClientSuggestionProvider(), 5, 3, 6, p_230383_5_);
                    this.n_1700_B(p_230383_1_, a_3742_W.P_4830_p.multiplayerClientSuggestionProvider(), 4, 1, 5, p_230383_5_);
                    this.n_1700_B(p_230383_1_, a_3742_W.P_4830_p.multiplayerClientSuggestionProvider(), 6, 1, 5, p_230383_5_);
                    this.n_1700_B(p_230383_1_, a_3742_W.P_4830_p.multiplayerClientSuggestionProvider(), 4, 3, 5, p_230383_5_);
                    this.n_1700_B(p_230383_1_, a_3742_W.P_4830_p.multiplayerClientSuggestionProvider(), 6, 3, 5, p_230383_5_);
                    for (int k = 1; k <= 3; ++k) {
                        this.n_1700_B(p_230383_1_, a_3742_W.P_4830_p.multiplayerClientSuggestionProvider(), 4, k, 4, p_230383_5_);
                        this.n_1700_B(p_230383_1_, a_3742_W.P_4830_p.multiplayerClientSuggestionProvider(), 6, k, 4, p_230383_5_);
                        this.n_1700_B(p_230383_1_, a_3742_W.P_4830_p.multiplayerClientSuggestionProvider(), 4, k, 6, p_230383_5_);
                        this.n_1700_B(p_230383_1_, a_3742_W.P_4830_p.multiplayerClientSuggestionProvider(), 6, k, 6, p_230383_5_);
                    }
                    this.n_1700_B(p_230383_1_, a_3742_W.o_2341_D.multiplayerClientSuggestionProvider(), 5, 3, 5, p_230383_5_);
                    for (int l = 2; l <= 8; ++l) {
                        this.n_1700_B(p_230383_1_, a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), 2, 3, l, p_230383_5_);
                        this.n_1700_B(p_230383_1_, a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), 3, 3, l, p_230383_5_);
                        if (l <= 3 || l >= 7) {
                            this.n_1700_B(p_230383_1_, a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), 4, 3, l, p_230383_5_);
                            this.n_1700_B(p_230383_1_, a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), 5, 3, l, p_230383_5_);
                            this.n_1700_B(p_230383_1_, a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), 6, 3, l, p_230383_5_);
                        }
                        this.n_1700_B(p_230383_1_, a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), 7, 3, l, p_230383_5_);
                        this.n_1700_B(p_230383_1_, a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), 8, 3, l, p_230383_5_);
                    }
                    K_4074_S blockstate = (K_4074_S)a_3742_W.L_3570_A.multiplayerClientSuggestionProvider().n_1700_B(C_1985_D.P_4830_p, b_257_Y.P_1922_E);
                    this.n_1700_B(p_230383_1_, blockstate, 9, 1, 3, p_230383_5_);
                    this.n_1700_B(p_230383_1_, blockstate, 9, 2, 3, p_230383_5_);
                    this.n_1700_B(p_230383_1_, blockstate, 9, 3, 3, p_230383_5_);
                    this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 3, 4, 8, o_4810_o.k_2293_S);
                }
            }
            return true;
        }
    }

    public static class P_4830_p
    extends M_182_A {
        public P_4830_p(int p_i45572_1_, Random p_i45572_2_, BoundingBox p_i45572_3_, b_257_Y p_i45572_4_) {
            super(StructurePieceType.x_607_J, p_i45572_1_);
            this.n_1700_B(p_i45572_4_);
            this.G_564_y = this.n_1700_B(p_i45572_2_);
            this.h_1847_R = p_i45572_3_;
        }

        public P_4830_p(b_2085_h p_i50113_1_, U_2912_j p_i50113_2_) {
            super(StructurePieceType.x_607_J, p_i50113_2_);
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            this.n_1700_B((M_588_G)componentIn, listIn, rand, 1, 1);
        }

        public static P_4830_p n_1700_B(List<E_3771_B> p_175861_0_, Random p_175861_1_, int p_175861_2_, int p_175861_3_, int p_175861_4_, b_257_Y p_175861_5_, int p_175861_6_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_175861_2_, p_175861_3_, p_175861_4_, -1, -7, 0, 5, 11, 8, p_175861_5_);
            return P_4830_p.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_175861_0_, mutableboundingbox) == null ? new P_4830_p(p_175861_6_, p_175861_1_, mutableboundingbox, p_175861_5_) : null;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 0, 4, 10, 7, true, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_4_, p_230383_5_, this.G_564_y, 1, 7, 0);
            this.n_1700_B(p_230383_1_, p_230383_4_, p_230383_5_, M_182_A.n_1700_B.n_1700_B, 1, 1, 7);
            K_4074_S blockstate = (K_4074_S)a_3742_W.S_3139_t.multiplayerClientSuggestionProvider().n_1700_B(z_2909_G.P_4830_p, b_257_Y.G_564_y);
            for (int i = 0; i < 6; ++i) {
                this.n_1700_B(p_230383_1_, blockstate, 1, 6 - i, 1 + i, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate, 2, 6 - i, 1 + i, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate, 3, 6 - i, 1 + i, p_230383_5_);
                if (i >= 5) continue;
                this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 1, 5 - i, 1 + i, p_230383_5_);
                this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 2, 5 - i, 1 + i, p_230383_5_);
                this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 3, 5 - i, 1 + i, p_230383_5_);
            }
            return true;
        }
    }

    public static class u_2550_I
    extends M_182_A {
        private final boolean n_1700_B;

        public u_2550_I(StructurePieceType p_i50120_1_, int p_i50120_2_, Random p_i50120_3_, int p_i50120_4_, int p_i50120_5_) {
            super(p_i50120_1_, p_i50120_2_);
            this.n_1700_B = true;
            this.n_1700_B(b_257_Y.R_4764_Y.n_1700_B.n_1700_B(p_i50120_3_));
            this.G_564_y = M_182_A.n_1700_B.n_1700_B;
            this.h_1847_R = this.t_148_a().h_1847_R() == b_257_Y.n_1700_B.R_4764_Y ? new BoundingBox(p_i50120_4_, 64, p_i50120_5_, p_i50120_4_ + 5 - 1, 74, p_i50120_5_ + 5 - 1) : new BoundingBox(p_i50120_4_, 64, p_i50120_5_, p_i50120_4_ + 5 - 1, 74, p_i50120_5_ + 5 - 1);
        }

        public u_2550_I(int p_i45574_1_, Random p_i45574_2_, BoundingBox p_i45574_3_, b_257_Y p_i45574_4_) {
            super(StructurePieceType.A_4115_X, p_i45574_1_);
            this.n_1700_B = false;
            this.n_1700_B(p_i45574_4_);
            this.G_564_y = this.n_1700_B(p_i45574_2_);
            this.h_1847_R = p_i45574_3_;
        }

        public u_2550_I(StructurePieceType p_i50121_1_, U_2912_j p_i50121_2_) {
            super(p_i50121_1_, p_i50121_2_);
            this.n_1700_B = p_i50121_2_.t_1786_h("Source");
        }

        public u_2550_I(b_2085_h p_i50122_1_, U_2912_j p_i50122_2_) {
            this(StructurePieceType.A_4115_X, p_i50122_2_);
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
            super.n_1700_B(tagCompound);
            tagCompound.n_1700_B("Source", this.n_1700_B);
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            if (this.n_1700_B) {
                R_4764_Y = R_4764_Y.class;
            }
            this.n_1700_B((M_588_G)componentIn, listIn, rand, 1, 1);
        }

        public static u_2550_I n_1700_B(List<E_3771_B> p_175863_0_, Random p_175863_1_, int p_175863_2_, int p_175863_3_, int p_175863_4_, b_257_Y p_175863_5_, int p_175863_6_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_175863_2_, p_175863_3_, p_175863_4_, -1, -7, 0, 5, 11, 5, p_175863_5_);
            return u_2550_I.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_175863_0_, mutableboundingbox) == null ? new u_2550_I(p_175863_6_, p_175863_1_, mutableboundingbox, p_175863_5_) : null;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 0, 4, 10, 4, true, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_4_, p_230383_5_, this.G_564_y, 1, 7, 0);
            this.n_1700_B(p_230383_1_, p_230383_4_, p_230383_5_, M_182_A.n_1700_B.n_1700_B, 1, 1, 4);
            this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 2, 6, 1, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 1, 5, 1, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), 1, 6, 1, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 1, 5, 2, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 1, 4, 3, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), 1, 5, 3, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 2, 4, 3, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 3, 3, 3, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), 3, 4, 3, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 3, 3, 2, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 3, 2, 1, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), 3, 3, 1, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 2, 2, 1, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 1, 1, 1, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), 1, 2, 1, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 1, 1, 2, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), 1, 1, 3, p_230383_5_);
            return true;
        }
    }

    public static class R_4764_Y
    extends M_182_A {
        private final boolean n_1700_B;
        private final boolean J_1907_R;
        private final boolean R_4764_Y;
        private final boolean P_1922_E;

        public R_4764_Y(int p_i45580_1_, Random p_i45580_2_, BoundingBox p_i45580_3_, b_257_Y p_i45580_4_) {
            super(StructurePieceType.Q_2552_b, p_i45580_1_);
            this.n_1700_B(p_i45580_4_);
            this.G_564_y = this.n_1700_B(p_i45580_2_);
            this.h_1847_R = p_i45580_3_;
            this.n_1700_B = p_i45580_2_.nextBoolean();
            this.J_1907_R = p_i45580_2_.nextBoolean();
            this.R_4764_Y = p_i45580_2_.nextBoolean();
            this.P_1922_E = p_i45580_2_.nextInt(3) > 0;
        }

        public R_4764_Y(b_2085_h p_i50136_1_, U_2912_j p_i50136_2_) {
            super(StructurePieceType.Q_2552_b, p_i50136_2_);
            this.n_1700_B = p_i50136_2_.t_1786_h("leftLow");
            this.J_1907_R = p_i50136_2_.t_1786_h("leftHigh");
            this.R_4764_Y = p_i50136_2_.t_1786_h("rightLow");
            this.P_1922_E = p_i50136_2_.t_1786_h("rightHigh");
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
            super.n_1700_B(tagCompound);
            tagCompound.n_1700_B("leftLow", this.n_1700_B);
            tagCompound.n_1700_B("leftHigh", this.J_1907_R);
            tagCompound.n_1700_B("rightLow", this.R_4764_Y);
            tagCompound.n_1700_B("rightHigh", this.P_1922_E);
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            int i = 3;
            int j = 5;
            b_257_Y direction = this.t_148_a();
            if (direction == b_257_Y.P_1922_E || direction == b_257_Y.R_4764_Y) {
                i = 8 - i;
                j = 8 - j;
            }
            this.n_1700_B((M_588_G)componentIn, listIn, rand, 5, 1);
            if (this.n_1700_B) {
                this.J_1907_R((M_588_G)componentIn, listIn, rand, i, 1);
            }
            if (this.J_1907_R) {
                this.J_1907_R((M_588_G)componentIn, listIn, rand, j, 7);
            }
            if (this.R_4764_Y) {
                this.R_4764_Y((M_588_G)componentIn, listIn, rand, i, 1);
            }
            if (this.P_1922_E) {
                this.R_4764_Y((M_588_G)componentIn, listIn, rand, j, 7);
            }
        }

        public static R_4764_Y n_1700_B(List<E_3771_B> p_175866_0_, Random p_175866_1_, int p_175866_2_, int p_175866_3_, int p_175866_4_, b_257_Y p_175866_5_, int p_175866_6_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_175866_2_, p_175866_3_, p_175866_4_, -4, -3, 0, 10, 9, 11, p_175866_5_);
            return lightning.product.g_4102_b$R_4764_Y.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_175866_0_, mutableboundingbox) == null ? new R_4764_Y(p_175866_6_, p_175866_1_, mutableboundingbox, p_175866_5_) : null;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 0, 9, 8, 10, true, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_4_, p_230383_5_, this.G_564_y, 4, 3, 0);
            if (this.n_1700_B) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, 1, 0, 5, 3, P_4830_p, P_4830_p, false);
            }
            if (this.R_4764_Y) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 9, 3, 1, 9, 5, 3, P_4830_p, P_4830_p, false);
            }
            if (this.J_1907_R) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 5, 7, 0, 7, 9, P_4830_p, P_4830_p, false);
            }
            if (this.P_1922_E) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 9, 5, 7, 9, 7, 9, P_4830_p, P_4830_p, false);
            }
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 1, 10, 7, 3, 10, P_4830_p, P_4830_p, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 2, 1, 8, 2, 6, false, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 1, 5, 4, 4, 9, false, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_5_, 8, 1, 5, 8, 4, 9, false, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 4, 7, 3, 4, 9, false, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 3, 5, 3, 3, 6, false, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 3, 4, 3, 3, 4, a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 4, 6, 3, 4, 6, a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 1, 7, 7, 1, 8, false, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 1, 9, 7, 1, 9, a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 2, 7, 7, 2, 7, a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 5, 7, 4, 5, 9, a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 8, 5, 7, 8, 5, 9, a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), a_3742_W.MoveHelper.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 5, 5, 7, 7, 5, 9, (K_4074_S)a_3742_W.MoveHelper.multiplayerClientSuggestionProvider().n_1700_B(y_3008_A.P_4830_p, n_1769_f.R_4764_Y), (K_4074_S)a_3742_W.MoveHelper.multiplayerClientSuggestionProvider().n_1700_B(y_3008_A.P_4830_p, n_1769_f.R_4764_Y), false);
            this.n_1700_B(p_230383_1_, (K_4074_S)a_3742_W.C_1269_X.multiplayerClientSuggestionProvider().n_1700_B(WallTorchBlock.P_4830_p, b_257_Y.G_564_y), 6, 5, 6, p_230383_5_);
            return true;
        }
    }

    public static class n_1700_B
    extends M_182_A {
        private boolean n_1700_B;

        public n_1700_B(int p_i45582_1_, Random p_i45582_2_, BoundingBox p_i45582_3_, b_257_Y p_i45582_4_) {
            super(StructurePieceType.Y_601_j, p_i45582_1_);
            this.n_1700_B(p_i45582_4_);
            this.G_564_y = this.n_1700_B(p_i45582_2_);
            this.h_1847_R = p_i45582_3_;
        }

        public n_1700_B(b_2085_h p_i50140_1_, U_2912_j p_i50140_2_) {
            super(StructurePieceType.Y_601_j, p_i50140_2_);
            this.n_1700_B = p_i50140_2_.t_1786_h("Chest");
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
            super.n_1700_B(tagCompound);
            tagCompound.n_1700_B("Chest", this.n_1700_B);
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            this.n_1700_B((M_588_G)componentIn, listIn, rand, 1, 1);
        }

        public static n_1700_B n_1700_B(List<E_3771_B> p_175868_0_, Random p_175868_1_, int p_175868_2_, int p_175868_3_, int p_175868_4_, b_257_Y p_175868_5_, int p_175868_6_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_175868_2_, p_175868_3_, p_175868_4_, -1, -1, 0, 5, 5, 7, p_175868_5_);
            return lightning.product.g_4102_b$n_1700_B.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_175868_0_, mutableboundingbox) == null ? new n_1700_B(p_175868_6_, p_175868_1_, mutableboundingbox, p_175868_5_) : null;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 0, 4, 4, 6, true, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_4_, p_230383_5_, this.G_564_y, 1, 1, 0);
            this.n_1700_B(p_230383_1_, p_230383_4_, p_230383_5_, M_182_A.n_1700_B.n_1700_B, 1, 1, 6);
            this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 2, 3, 1, 4, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, a_3742_W.Phase.multiplayerClientSuggestionProvider(), 3, 1, 1, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.Phase.multiplayerClientSuggestionProvider(), 3, 1, 5, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.Phase.multiplayerClientSuggestionProvider(), 3, 2, 2, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.Phase.multiplayerClientSuggestionProvider(), 3, 2, 4, p_230383_5_);
            for (int i = 2; i <= 4; ++i) {
                this.n_1700_B(p_230383_1_, a_3742_W.Phase.multiplayerClientSuggestionProvider(), 2, 1, i, p_230383_5_);
            }
            if (!this.n_1700_B && p_230383_5_.J_1907_R(new c_1514_x(this.n_1700_B(3, 3), this.n_1700_B(2), this.J_1907_R(3, 3)))) {
                this.n_1700_B = true;
                this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 3, 2, 3, o_4810_o.q_2307_F);
            }
            return true;
        }
    }

    public static class P_1922_E
    extends M_182_A {
        private final boolean n_1700_B;

        public P_1922_E(int p_i45578_1_, Random p_i45578_2_, BoundingBox p_i45578_3_, b_257_Y p_i45578_4_) {
            super(StructurePieceType.k_2293_S, p_i45578_1_);
            this.n_1700_B(p_i45578_4_);
            this.G_564_y = this.n_1700_B(p_i45578_2_);
            this.h_1847_R = p_i45578_3_;
            this.n_1700_B = p_i45578_3_.P_1922_E() > 6;
        }

        public P_1922_E(b_2085_h p_i50133_1_, U_2912_j p_i50133_2_) {
            super(StructurePieceType.k_2293_S, p_i50133_2_);
            this.n_1700_B = p_i50133_2_.t_1786_h("Tall");
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
            super.n_1700_B(tagCompound);
            tagCompound.n_1700_B("Tall", this.n_1700_B);
        }

        public static P_1922_E n_1700_B(List<E_3771_B> p_175864_0_, Random p_175864_1_, int p_175864_2_, int p_175864_3_, int p_175864_4_, b_257_Y p_175864_5_, int p_175864_6_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_175864_2_, p_175864_3_, p_175864_4_, -4, -1, 0, 14, 11, 15, p_175864_5_);
            if (!(lightning.product.g_4102_b$P_1922_E.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_175864_0_, mutableboundingbox) == null || lightning.product.g_4102_b$P_1922_E.n_1700_B(mutableboundingbox = BoundingBox.n_1700_B(p_175864_2_, p_175864_3_, p_175864_4_, -4, -1, 0, 14, 6, 15, p_175864_5_)) && E_3771_B.n_1700_B(p_175864_0_, mutableboundingbox) == null)) {
                return null;
            }
            return new P_1922_E(p_175864_6_, p_175864_1_, mutableboundingbox, p_175864_5_);
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            int i = 11;
            if (!this.n_1700_B) {
                i = 6;
            }
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 0, 13, i - 1, 14, true, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_4_, p_230383_5_, this.G_564_y, 4, 1, 0);
            this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 0.07f, 2, 1, 1, 11, 4, 13, a_3742_W.y_1700_S.multiplayerClientSuggestionProvider(), a_3742_W.y_1700_S.multiplayerClientSuggestionProvider(), false, false);
            boolean j = true;
            int k = 12;
            for (int l = 1; l <= 13; ++l) {
                if ((l - 1) % 4 == 0) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, l, 1, 4, l, a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 12, 1, l, 12, 4, l, a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), false);
                    this.n_1700_B(p_230383_1_, (K_4074_S)a_3742_W.C_1269_X.multiplayerClientSuggestionProvider().n_1700_B(WallTorchBlock.P_4830_p, b_257_Y.u_1723_Y), 2, 3, l, p_230383_5_);
                    this.n_1700_B(p_230383_1_, (K_4074_S)a_3742_W.C_1269_X.multiplayerClientSuggestionProvider().n_1700_B(WallTorchBlock.P_4830_p, b_257_Y.P_1922_E), 11, 3, l, p_230383_5_);
                    if (!this.n_1700_B) continue;
                    this.n_1700_B(p_230383_1_, p_230383_5_, 1, 6, l, 1, 9, l, a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), false);
                    this.n_1700_B(p_230383_1_, p_230383_5_, 12, 6, l, 12, 9, l, a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), false);
                    continue;
                }
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, l, 1, 4, l, a_3742_W.UploadTokenCache.multiplayerClientSuggestionProvider(), a_3742_W.UploadTokenCache.multiplayerClientSuggestionProvider(), false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 12, 1, l, 12, 4, l, a_3742_W.UploadTokenCache.multiplayerClientSuggestionProvider(), a_3742_W.UploadTokenCache.multiplayerClientSuggestionProvider(), false);
                if (!this.n_1700_B) continue;
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 6, l, 1, 9, l, a_3742_W.UploadTokenCache.multiplayerClientSuggestionProvider(), a_3742_W.UploadTokenCache.multiplayerClientSuggestionProvider(), false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 12, 6, l, 12, 9, l, a_3742_W.UploadTokenCache.multiplayerClientSuggestionProvider(), a_3742_W.UploadTokenCache.multiplayerClientSuggestionProvider(), false);
            }
            for (int l1 = 3; l1 < 12; l1 += 2) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, l1, 4, 3, l1, a_3742_W.UploadTokenCache.multiplayerClientSuggestionProvider(), a_3742_W.UploadTokenCache.multiplayerClientSuggestionProvider(), false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 6, 1, l1, 7, 3, l1, a_3742_W.UploadTokenCache.multiplayerClientSuggestionProvider(), a_3742_W.UploadTokenCache.multiplayerClientSuggestionProvider(), false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 9, 1, l1, 10, 3, l1, a_3742_W.UploadTokenCache.multiplayerClientSuggestionProvider(), a_3742_W.UploadTokenCache.multiplayerClientSuggestionProvider(), false);
            }
            if (this.n_1700_B) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 1, 5, 1, 3, 5, 13, a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 10, 5, 1, 12, 5, 13, a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 4, 5, 1, 9, 5, 2, a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 4, 5, 12, 9, 5, 13, a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), false);
                this.n_1700_B(p_230383_1_, a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), 9, 5, 11, p_230383_5_);
                this.n_1700_B(p_230383_1_, a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), 8, 5, 11, p_230383_5_);
                this.n_1700_B(p_230383_1_, a_3742_W.h_1847_R.multiplayerClientSuggestionProvider(), 9, 5, 10, p_230383_5_);
                K_4074_S blockstate5 = (K_4074_S)((K_4074_S)a_3742_W.h_2848_I.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.M_182_A, true)).n_1700_B(FenceBlock.h_1847_R, true);
                K_4074_S blockstate = (K_4074_S)((K_4074_S)a_3742_W.h_2848_I.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.P_4830_p, true)).n_1700_B(FenceBlock.Q_4569_t, true);
                this.n_1700_B(p_230383_1_, p_230383_5_, 3, 6, 3, 3, 6, 11, blockstate, blockstate, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 10, 6, 3, 10, 6, 9, blockstate, blockstate, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 4, 6, 2, 9, 6, 2, blockstate5, blockstate5, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 4, 6, 12, 7, 6, 12, blockstate5, blockstate5, false);
                this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.h_2848_I.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.P_4830_p, true)).n_1700_B(FenceBlock.h_1847_R, true), 3, 6, 2, p_230383_5_);
                this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.h_2848_I.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.Q_4569_t, true)).n_1700_B(FenceBlock.h_1847_R, true), 3, 6, 12, p_230383_5_);
                this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.h_2848_I.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.P_4830_p, true)).n_1700_B(FenceBlock.M_182_A, true), 10, 6, 2, p_230383_5_);
                for (int i1 = 0; i1 <= 2; ++i1) {
                    this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.h_2848_I.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.Q_4569_t, true)).n_1700_B(FenceBlock.M_182_A, true), 8 + i1, 6, 12 - i1, p_230383_5_);
                    if (i1 == 2) continue;
                    this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.h_2848_I.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.P_4830_p, true)).n_1700_B(FenceBlock.h_1847_R, true), 8 + i1, 6, 11 - i1, p_230383_5_);
                }
                K_4074_S blockstate6 = (K_4074_S)a_3742_W.L_3570_A.multiplayerClientSuggestionProvider().n_1700_B(C_1985_D.P_4830_p, b_257_Y.G_564_y);
                this.n_1700_B(p_230383_1_, blockstate6, 10, 1, 13, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate6, 10, 2, 13, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate6, 10, 3, 13, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate6, 10, 4, 13, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate6, 10, 5, 13, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate6, 10, 6, 13, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate6, 10, 7, 13, p_230383_5_);
                int j1 = 7;
                int k1 = 7;
                K_4074_S blockstate1 = (K_4074_S)a_3742_W.h_2848_I.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.h_1847_R, true);
                this.n_1700_B(p_230383_1_, blockstate1, 6, 9, 7, p_230383_5_);
                K_4074_S blockstate2 = (K_4074_S)a_3742_W.h_2848_I.multiplayerClientSuggestionProvider().n_1700_B(FenceBlock.M_182_A, true);
                this.n_1700_B(p_230383_1_, blockstate2, 7, 9, 7, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate1, 6, 8, 7, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate2, 7, 8, 7, p_230383_5_);
                K_4074_S blockstate3 = (K_4074_S)((K_4074_S)blockstate.n_1700_B(FenceBlock.M_182_A, true)).n_1700_B(FenceBlock.h_1847_R, true);
                this.n_1700_B(p_230383_1_, blockstate3, 6, 7, 7, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate3, 7, 7, 7, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate1, 5, 7, 7, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate2, 8, 7, 7, p_230383_5_);
                this.n_1700_B(p_230383_1_, (K_4074_S)blockstate1.n_1700_B(FenceBlock.P_4830_p, true), 6, 7, 6, p_230383_5_);
                this.n_1700_B(p_230383_1_, (K_4074_S)blockstate1.n_1700_B(FenceBlock.Q_4569_t, true), 6, 7, 8, p_230383_5_);
                this.n_1700_B(p_230383_1_, (K_4074_S)blockstate2.n_1700_B(FenceBlock.P_4830_p, true), 7, 7, 6, p_230383_5_);
                this.n_1700_B(p_230383_1_, (K_4074_S)blockstate2.n_1700_B(FenceBlock.Q_4569_t, true), 7, 7, 8, p_230383_5_);
                K_4074_S blockstate4 = a_3742_W.o_2341_D.multiplayerClientSuggestionProvider();
                this.n_1700_B(p_230383_1_, blockstate4, 5, 8, 7, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate4, 8, 8, 7, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate4, 6, 8, 6, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate4, 6, 8, 8, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate4, 7, 8, 6, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate4, 7, 8, 8, p_230383_5_);
            }
            this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 3, 3, 5, o_4810_o.C_2741_M);
            if (this.n_1700_B) {
                this.n_1700_B(p_230383_1_, P_4830_p, 12, 9, 1, p_230383_5_);
                this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 12, 8, 1, o_4810_o.C_2741_M);
            }
            return true;
        }
    }

    public static class v_4262_N
    extends M_182_A {
        private boolean n_1700_B;

        public v_4262_N(int p_i50131_1_, BoundingBox p_i50131_2_, b_257_Y p_i50131_3_) {
            super(StructurePieceType.q_2307_F, p_i50131_1_);
            this.n_1700_B(p_i50131_3_);
            this.h_1847_R = p_i50131_2_;
        }

        public v_4262_N(b_2085_h p_i50132_1_, U_2912_j p_i50132_2_) {
            super(StructurePieceType.q_2307_F, p_i50132_2_);
            this.n_1700_B = p_i50132_2_.t_1786_h("Mob");
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
            super.n_1700_B(tagCompound);
            tagCompound.n_1700_B("Mob", this.n_1700_B);
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            if (componentIn != null) {
                ((M_588_G)componentIn).J_1907_R = this;
            }
        }

        public static v_4262_N n_1700_B(List<E_3771_B> p_175865_0_, int p_175865_1_, int p_175865_2_, int p_175865_3_, b_257_Y p_175865_4_, int p_175865_5_) {
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_175865_1_, p_175865_2_, p_175865_3_, -4, -1, 0, 11, 8, 16, p_175865_4_);
            return v_4262_N.n_1700_B(mutableboundingbox) && E_3771_B.n_1700_B(p_175865_0_, mutableboundingbox) == null ? new v_4262_N(p_175865_5_, mutableboundingbox, p_175865_4_) : null;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 0, 10, 7, 15, false, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_4_, p_230383_5_, M_182_A.n_1700_B.R_4764_Y, 4, 1, 0);
            int i = 6;
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, i, 1, 1, i, 14, false, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_5_, 9, i, 1, 9, i, 14, false, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, i, 1, 8, i, 2, false, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, i, 14, 8, i, 14, false, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, 1, 2, 1, 4, false, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_5_, 8, 1, 1, 9, 1, 4, false, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, 1, 1, 1, 3, a_3742_W.H_2857_Y.multiplayerClientSuggestionProvider(), a_3742_W.H_2857_Y.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 9, 1, 1, 9, 1, 3, a_3742_W.H_2857_Y.multiplayerClientSuggestionProvider(), a_3742_W.H_2857_Y.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 8, 7, 1, 12, false, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 1, 9, 6, 1, 11, a_3742_W.H_2857_Y.multiplayerClientSuggestionProvider(), a_3742_W.H_2857_Y.multiplayerClientSuggestionProvider(), false);
            K_4074_S blockstate = (K_4074_S)((K_4074_S)a_3742_W.Z_4720_K.multiplayerClientSuggestionProvider().n_1700_B(IronBarsBlock.P_4830_p, true)).n_1700_B(IronBarsBlock.Q_4569_t, true);
            K_4074_S blockstate1 = (K_4074_S)((K_4074_S)a_3742_W.Z_4720_K.multiplayerClientSuggestionProvider().n_1700_B(IronBarsBlock.M_182_A, true)).n_1700_B(IronBarsBlock.h_1847_R, true);
            for (int j = 3; j < 14; j += 2) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 3, j, 0, 4, j, blockstate, blockstate, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, 10, 3, j, 10, 4, j, blockstate, blockstate, false);
            }
            for (int i1 = 2; i1 < 9; i1 += 2) {
                this.n_1700_B(p_230383_1_, p_230383_5_, i1, 3, 15, i1, 4, 15, blockstate1, blockstate1, false);
            }
            K_4074_S blockstate5 = (K_4074_S)a_3742_W.F_2860_q.multiplayerClientSuggestionProvider().n_1700_B(z_2909_G.P_4830_p, b_257_Y.R_4764_Y);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 1, 5, 6, 1, 7, false, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 2, 6, 6, 2, 7, false, p_230383_4_, P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 3, 7, 6, 3, 7, false, p_230383_4_, P_1922_E);
            for (int k = 4; k <= 6; ++k) {
                this.n_1700_B(p_230383_1_, blockstate5, k, 1, 4, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate5, k, 2, 5, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate5, k, 3, 6, p_230383_5_);
            }
            K_4074_S blockstate6 = (K_4074_S)a_3742_W.l_2995_s.multiplayerClientSuggestionProvider().n_1700_B(EndPortalFrameBlock.P_4830_p, b_257_Y.R_4764_Y);
            K_4074_S blockstate2 = (K_4074_S)a_3742_W.l_2995_s.multiplayerClientSuggestionProvider().n_1700_B(EndPortalFrameBlock.P_4830_p, b_257_Y.G_564_y);
            K_4074_S blockstate3 = (K_4074_S)a_3742_W.l_2995_s.multiplayerClientSuggestionProvider().n_1700_B(EndPortalFrameBlock.P_4830_p, b_257_Y.u_1723_Y);
            K_4074_S blockstate4 = (K_4074_S)a_3742_W.l_2995_s.multiplayerClientSuggestionProvider().n_1700_B(EndPortalFrameBlock.P_4830_p, b_257_Y.P_1922_E);
            boolean flag = true;
            boolean[] aboolean = new boolean[12];
            for (int l = 0; l < aboolean.length; ++l) {
                aboolean[l] = p_230383_4_.nextFloat() > 0.9f;
                flag &= aboolean[l];
            }
            this.n_1700_B(p_230383_1_, (K_4074_S)blockstate6.n_1700_B(EndPortalFrameBlock.h_1847_R, aboolean[0]), 4, 3, 8, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)blockstate6.n_1700_B(EndPortalFrameBlock.h_1847_R, aboolean[1]), 5, 3, 8, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)blockstate6.n_1700_B(EndPortalFrameBlock.h_1847_R, aboolean[2]), 6, 3, 8, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)blockstate2.n_1700_B(EndPortalFrameBlock.h_1847_R, aboolean[3]), 4, 3, 12, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)blockstate2.n_1700_B(EndPortalFrameBlock.h_1847_R, aboolean[4]), 5, 3, 12, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)blockstate2.n_1700_B(EndPortalFrameBlock.h_1847_R, aboolean[5]), 6, 3, 12, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)blockstate3.n_1700_B(EndPortalFrameBlock.h_1847_R, aboolean[6]), 3, 3, 9, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)blockstate3.n_1700_B(EndPortalFrameBlock.h_1847_R, aboolean[7]), 3, 3, 10, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)blockstate3.n_1700_B(EndPortalFrameBlock.h_1847_R, aboolean[8]), 3, 3, 11, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)blockstate4.n_1700_B(EndPortalFrameBlock.h_1847_R, aboolean[9]), 7, 3, 9, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)blockstate4.n_1700_B(EndPortalFrameBlock.h_1847_R, aboolean[10]), 7, 3, 10, p_230383_5_);
            this.n_1700_B(p_230383_1_, (K_4074_S)blockstate4.n_1700_B(EndPortalFrameBlock.h_1847_R, aboolean[11]), 7, 3, 11, p_230383_5_);
            if (flag) {
                K_4074_S blockstate7 = a_3742_W.M_2562_s.multiplayerClientSuggestionProvider();
                this.n_1700_B(p_230383_1_, blockstate7, 4, 3, 9, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate7, 5, 3, 9, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate7, 6, 3, 9, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate7, 4, 3, 10, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate7, 5, 3, 10, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate7, 6, 3, 10, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate7, 4, 3, 11, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate7, 5, 3, 11, p_230383_5_);
                this.n_1700_B(p_230383_1_, blockstate7, 6, 3, 11, p_230383_5_);
            }
            if (!this.n_1700_B) {
                i = this.n_1700_B(3);
                c_1514_x blockpos = new c_1514_x(this.n_1700_B(5, 6), i, this.J_1907_R(5, 6));
                if (p_230383_5_.J_1907_R(blockpos)) {
                    this.n_1700_B = true;
                    p_230383_1_.n_1700_B(blockpos, a_3742_W.j_306_t.multiplayerClientSuggestionProvider(), 2);
                    i_2154_H tileentity = p_230383_1_.getTileEntity(blockpos);
                    if (tileentity instanceof SpawnerBlockEntity) {
                        ((SpawnerBlockEntity)tileentity).v_4262_N().n_1700_B(t_5_h.t_4219_U);
                    }
                }
            }
            return true;
        }
    }

    static abstract class M_182_A
    extends E_3771_B {
        protected n_1700_B G_564_y = n_1700_B.n_1700_B;

        protected M_182_A(StructurePieceType p_i50110_1_, int p_i50110_2_) {
            super(p_i50110_1_, p_i50110_2_);
        }

        public M_182_A(StructurePieceType p_i50111_1_, U_2912_j p_i50111_2_) {
            super(p_i50111_1_, p_i50111_2_);
            this.G_564_y = n_1700_B.valueOf(p_i50111_2_.M_588_G("EntryDoor"));
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
            tagCompound.n_1700_B("EntryDoor", this.G_564_y.name());
        }

        protected void n_1700_B(WorldGenLevel p_242917_1_, Random p_242917_2_, BoundingBox p_242917_3_, n_1700_B p_242917_4_, int p_242917_5_, int p_242917_6_, int p_242917_7_) {
            switch (p_242917_4_.ordinal()) {
                case 0: {
                    this.n_1700_B(p_242917_1_, p_242917_3_, p_242917_5_, p_242917_6_, p_242917_7_, p_242917_5_ + 3 - 1, p_242917_6_ + 3 - 1, p_242917_7_, P_4830_p, P_4830_p, false);
                    break;
                }
                case 1: {
                    this.n_1700_B(p_242917_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), p_242917_5_, p_242917_6_, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), p_242917_5_, p_242917_6_ + 1, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), p_242917_5_, p_242917_6_ + 2, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), p_242917_5_ + 1, p_242917_6_ + 2, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), p_242917_5_ + 2, p_242917_6_ + 2, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), p_242917_5_ + 2, p_242917_6_ + 1, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), p_242917_5_ + 2, p_242917_6_, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, a_3742_W.F_518_D.multiplayerClientSuggestionProvider(), p_242917_5_ + 1, p_242917_6_, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, (K_4074_S)a_3742_W.F_518_D.multiplayerClientSuggestionProvider().n_1700_B(S_1431_H.t_1786_h, g_3212_H.n_1700_B), p_242917_5_ + 1, p_242917_6_ + 1, p_242917_7_, p_242917_3_);
                    break;
                }
                case 2: {
                    this.n_1700_B(p_242917_1_, a_3742_W.a_1344_X.multiplayerClientSuggestionProvider(), p_242917_5_ + 1, p_242917_6_, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, a_3742_W.a_1344_X.multiplayerClientSuggestionProvider(), p_242917_5_ + 1, p_242917_6_ + 1, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, (K_4074_S)a_3742_W.Z_4720_K.multiplayerClientSuggestionProvider().n_1700_B(IronBarsBlock.M_182_A, true), p_242917_5_, p_242917_6_, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, (K_4074_S)a_3742_W.Z_4720_K.multiplayerClientSuggestionProvider().n_1700_B(IronBarsBlock.M_182_A, true), p_242917_5_, p_242917_6_ + 1, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, (K_4074_S)((K_4074_S)a_3742_W.Z_4720_K.multiplayerClientSuggestionProvider().n_1700_B(IronBarsBlock.h_1847_R, true)).n_1700_B(IronBarsBlock.M_182_A, true), p_242917_5_, p_242917_6_ + 2, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, (K_4074_S)((K_4074_S)a_3742_W.Z_4720_K.multiplayerClientSuggestionProvider().n_1700_B(IronBarsBlock.h_1847_R, true)).n_1700_B(IronBarsBlock.M_182_A, true), p_242917_5_ + 1, p_242917_6_ + 2, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, (K_4074_S)((K_4074_S)a_3742_W.Z_4720_K.multiplayerClientSuggestionProvider().n_1700_B(IronBarsBlock.h_1847_R, true)).n_1700_B(IronBarsBlock.M_182_A, true), p_242917_5_ + 2, p_242917_6_ + 2, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, (K_4074_S)a_3742_W.Z_4720_K.multiplayerClientSuggestionProvider().n_1700_B(IronBarsBlock.h_1847_R, true), p_242917_5_ + 2, p_242917_6_ + 1, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, (K_4074_S)a_3742_W.Z_4720_K.multiplayerClientSuggestionProvider().n_1700_B(IronBarsBlock.h_1847_R, true), p_242917_5_ + 2, p_242917_6_, p_242917_7_, p_242917_3_);
                    break;
                }
                case 3: {
                    this.n_1700_B(p_242917_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), p_242917_5_, p_242917_6_, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), p_242917_5_, p_242917_6_ + 1, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), p_242917_5_, p_242917_6_ + 2, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), p_242917_5_ + 1, p_242917_6_ + 2, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), p_242917_5_ + 2, p_242917_6_ + 2, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), p_242917_5_ + 2, p_242917_6_ + 1, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), p_242917_5_ + 2, p_242917_6_, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, a_3742_W.h_2739_B.multiplayerClientSuggestionProvider(), p_242917_5_ + 1, p_242917_6_, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, (K_4074_S)a_3742_W.h_2739_B.multiplayerClientSuggestionProvider().n_1700_B(S_1431_H.t_1786_h, g_3212_H.n_1700_B), p_242917_5_ + 1, p_242917_6_ + 1, p_242917_7_, p_242917_3_);
                    this.n_1700_B(p_242917_1_, (K_4074_S)a_3742_W.o_3599_Z.multiplayerClientSuggestionProvider().n_1700_B(V_1045_N.w_612_n, b_257_Y.R_4764_Y), p_242917_5_ + 2, p_242917_6_ + 1, p_242917_7_ + 1, p_242917_3_);
                    this.n_1700_B(p_242917_1_, (K_4074_S)a_3742_W.o_3599_Z.multiplayerClientSuggestionProvider().n_1700_B(V_1045_N.w_612_n, b_257_Y.G_564_y), p_242917_5_ + 2, p_242917_6_ + 1, p_242917_7_ - 1, p_242917_3_);
                }
            }
        }

        protected n_1700_B n_1700_B(Random p_74988_1_) {
            int i = p_74988_1_.nextInt(5);
            switch (i) {
                default: {
                    return n_1700_B.n_1700_B;
                }
                case 2: {
                    return n_1700_B.J_1907_R;
                }
                case 3: {
                    return n_1700_B.R_4764_Y;
                }
                case 4: 
            }
            return n_1700_B.G_564_y;
        }

        @Nullable
        protected E_3771_B n_1700_B(M_588_G p_74986_1_, List<E_3771_B> p_74986_2_, Random p_74986_3_, int p_74986_4_, int p_74986_5_) {
            b_257_Y direction = this.t_148_a();
            if (direction != null) {
                switch (direction) {
                    case R_4764_Y: {
                        return g_4102_b.J_1907_R(p_74986_1_, p_74986_2_, p_74986_3_, this.h_1847_R.n_1700_B + p_74986_4_, this.h_1847_R.J_1907_R + p_74986_5_, this.h_1847_R.R_4764_Y - 1, direction, this.w_1484_f());
                    }
                    case G_564_y: {
                        return g_4102_b.J_1907_R(p_74986_1_, p_74986_2_, p_74986_3_, this.h_1847_R.n_1700_B + p_74986_4_, this.h_1847_R.J_1907_R + p_74986_5_, this.h_1847_R.u_1723_Y + 1, direction, this.w_1484_f());
                    }
                    case P_1922_E: {
                        return g_4102_b.J_1907_R(p_74986_1_, p_74986_2_, p_74986_3_, this.h_1847_R.n_1700_B - 1, this.h_1847_R.J_1907_R + p_74986_5_, this.h_1847_R.R_4764_Y + p_74986_4_, direction, this.w_1484_f());
                    }
                    case u_1723_Y: {
                        return g_4102_b.J_1907_R(p_74986_1_, p_74986_2_, p_74986_3_, this.h_1847_R.G_564_y + 1, this.h_1847_R.J_1907_R + p_74986_5_, this.h_1847_R.R_4764_Y + p_74986_4_, direction, this.w_1484_f());
                    }
                }
            }
            return null;
        }

        @Nullable
        protected E_3771_B J_1907_R(M_588_G p_74989_1_, List<E_3771_B> p_74989_2_, Random p_74989_3_, int p_74989_4_, int p_74989_5_) {
            b_257_Y direction = this.t_148_a();
            if (direction != null) {
                switch (direction) {
                    case R_4764_Y: {
                        return g_4102_b.J_1907_R(p_74989_1_, p_74989_2_, p_74989_3_, this.h_1847_R.n_1700_B - 1, this.h_1847_R.J_1907_R + p_74989_4_, this.h_1847_R.R_4764_Y + p_74989_5_, b_257_Y.P_1922_E, this.w_1484_f());
                    }
                    case G_564_y: {
                        return g_4102_b.J_1907_R(p_74989_1_, p_74989_2_, p_74989_3_, this.h_1847_R.n_1700_B - 1, this.h_1847_R.J_1907_R + p_74989_4_, this.h_1847_R.R_4764_Y + p_74989_5_, b_257_Y.P_1922_E, this.w_1484_f());
                    }
                    case P_1922_E: {
                        return g_4102_b.J_1907_R(p_74989_1_, p_74989_2_, p_74989_3_, this.h_1847_R.n_1700_B + p_74989_5_, this.h_1847_R.J_1907_R + p_74989_4_, this.h_1847_R.R_4764_Y - 1, b_257_Y.R_4764_Y, this.w_1484_f());
                    }
                    case u_1723_Y: {
                        return g_4102_b.J_1907_R(p_74989_1_, p_74989_2_, p_74989_3_, this.h_1847_R.n_1700_B + p_74989_5_, this.h_1847_R.J_1907_R + p_74989_4_, this.h_1847_R.R_4764_Y - 1, b_257_Y.R_4764_Y, this.w_1484_f());
                    }
                }
            }
            return null;
        }

        @Nullable
        protected E_3771_B R_4764_Y(M_588_G p_74987_1_, List<E_3771_B> p_74987_2_, Random p_74987_3_, int p_74987_4_, int p_74987_5_) {
            b_257_Y direction = this.t_148_a();
            if (direction != null) {
                switch (direction) {
                    case R_4764_Y: {
                        return g_4102_b.J_1907_R(p_74987_1_, p_74987_2_, p_74987_3_, this.h_1847_R.G_564_y + 1, this.h_1847_R.J_1907_R + p_74987_4_, this.h_1847_R.R_4764_Y + p_74987_5_, b_257_Y.u_1723_Y, this.w_1484_f());
                    }
                    case G_564_y: {
                        return g_4102_b.J_1907_R(p_74987_1_, p_74987_2_, p_74987_3_, this.h_1847_R.G_564_y + 1, this.h_1847_R.J_1907_R + p_74987_4_, this.h_1847_R.R_4764_Y + p_74987_5_, b_257_Y.u_1723_Y, this.w_1484_f());
                    }
                    case P_1922_E: {
                        return g_4102_b.J_1907_R(p_74987_1_, p_74987_2_, p_74987_3_, this.h_1847_R.n_1700_B + p_74987_5_, this.h_1847_R.J_1907_R + p_74987_4_, this.h_1847_R.u_1723_Y + 1, b_257_Y.G_564_y, this.w_1484_f());
                    }
                    case u_1723_Y: {
                        return g_4102_b.J_1907_R(p_74987_1_, p_74987_2_, p_74987_3_, this.h_1847_R.n_1700_B + p_74987_5_, this.h_1847_R.J_1907_R + p_74987_4_, this.h_1847_R.u_1723_Y + 1, b_257_Y.G_564_y, this.w_1484_f());
                    }
                }
            }
            return null;
        }

        protected static boolean n_1700_B(BoundingBox p_74991_0_) {
            return p_74991_0_ != null && p_74991_0_.J_1907_R > 10;
        }

        public static final class n_1700_B
        extends Enum<n_1700_B> {
            public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
            public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
            public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
            public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
            private static final /* synthetic */ n_1700_B[] P_1922_E;

            public static n_1700_B[] values() {
                return (n_1700_B[])P_1922_E.clone();
            }

            public static n_1700_B valueOf(String name) {
                return Enum.valueOf(n_1700_B.class, name);
            }

            private static /* synthetic */ n_1700_B[] n_1700_B() {
                return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
            }

            static {
                P_1922_E = lightning.product.g_4102_b$M_182_A$n_1700_B.n_1700_B();
            }
        }
    }

    public static class M_588_G
    extends u_2550_I {
        public u_1723_Y n_1700_B;
        @Nullable
        public v_4262_N J_1907_R;
        public final List<E_3771_B> R_4764_Y = Lists.newArrayList();

        public M_588_G(Random p_i50117_1_, int p_i50117_2_, int p_i50117_3_) {
            super(StructurePieceType.Y_1740_V, 0, p_i50117_1_, p_i50117_2_, p_i50117_3_);
        }

        public M_588_G(b_2085_h p_i50118_1_, U_2912_j p_i50118_2_) {
            super(StructurePieceType.Y_1740_V, p_i50118_2_);
        }
    }

    public static class J_1907_R
    extends M_182_A {
        private final int n_1700_B;

        public J_1907_R(int p_i50137_1_, BoundingBox p_i50137_2_, b_257_Y p_i50137_3_) {
            super(StructurePieceType.Y_259_p, p_i50137_1_);
            this.n_1700_B(p_i50137_3_);
            this.h_1847_R = p_i50137_2_;
            this.n_1700_B = p_i50137_3_ != b_257_Y.R_4764_Y && p_i50137_3_ != b_257_Y.G_564_y ? p_i50137_2_.G_564_y() : p_i50137_2_.u_1723_Y();
        }

        public J_1907_R(b_2085_h p_i50138_1_, U_2912_j p_i50138_2_) {
            super(StructurePieceType.Y_259_p, p_i50138_2_);
            this.n_1700_B = p_i50138_2_.w_1484_f("Steps");
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
            super.n_1700_B(tagCompound);
            tagCompound.J_1907_R("Steps", this.n_1700_B);
        }

        public static BoundingBox n_1700_B(List<E_3771_B> p_175869_0_, Random p_175869_1_, int p_175869_2_, int p_175869_3_, int p_175869_4_, b_257_Y p_175869_5_) {
            int i = 3;
            BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_175869_2_, p_175869_3_, p_175869_4_, -1, -1, 0, 5, 5, 4, p_175869_5_);
            E_3771_B structurepiece = E_3771_B.n_1700_B(p_175869_0_, mutableboundingbox);
            if (structurepiece == null) {
                return null;
            }
            if (structurepiece.v_4262_N().J_1907_R == mutableboundingbox.J_1907_R) {
                for (int j = 3; j >= 1; --j) {
                    mutableboundingbox = BoundingBox.n_1700_B(p_175869_2_, p_175869_3_, p_175869_4_, -1, -1, 0, 5, 5, j - 1, p_175869_5_);
                    if (structurepiece.v_4262_N().n_1700_B(mutableboundingbox)) continue;
                    return BoundingBox.n_1700_B(p_175869_2_, p_175869_3_, p_175869_4_, -1, -1, 0, 5, 5, j, p_175869_5_);
                }
            }
            return null;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            for (int i = 0; i < this.n_1700_B; ++i) {
                this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 0, 0, i, p_230383_5_);
                this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 1, 0, i, p_230383_5_);
                this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 2, 0, i, p_230383_5_);
                this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 3, 0, i, p_230383_5_);
                this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 4, 0, i, p_230383_5_);
                for (int j = 1; j <= 3; ++j) {
                    this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 0, j, i, p_230383_5_);
                    this.n_1700_B(p_230383_1_, a_3742_W.a_1344_X.multiplayerClientSuggestionProvider(), 1, j, i, p_230383_5_);
                    this.n_1700_B(p_230383_1_, a_3742_W.a_1344_X.multiplayerClientSuggestionProvider(), 2, j, i, p_230383_5_);
                    this.n_1700_B(p_230383_1_, a_3742_W.a_1344_X.multiplayerClientSuggestionProvider(), 3, j, i, p_230383_5_);
                    this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 4, j, i, p_230383_5_);
                }
                this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 0, 4, i, p_230383_5_);
                this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 1, 4, i, p_230383_5_);
                this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 2, 4, i, p_230383_5_);
                this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 3, 4, i, p_230383_5_);
                this.n_1700_B(p_230383_1_, a_3742_W.f_691_R.multiplayerClientSuggestionProvider(), 4, 4, i, p_230383_5_);
            }
            return true;
        }
    }

    static class h_1847_R
    extends E_3771_B.n_1700_B {
        private h_1847_R() {
        }

        @Override
        public void n_1700_B(Random rand, int x, int y, int z, boolean wall) {
            float f;
            this.n_1700_B = wall ? ((f = rand.nextFloat()) < 0.2f ? a_3742_W.g_1734_y.multiplayerClientSuggestionProvider() : (f < 0.5f ? a_3742_W.I_4481_g.multiplayerClientSuggestionProvider() : (f < 0.55f ? a_3742_W.g_46_E.multiplayerClientSuggestionProvider() : a_3742_W.f_691_R.multiplayerClientSuggestionProvider()))) : a_3742_W.a_1344_X.multiplayerClientSuggestionProvider();
        }
    }

    public static abstract class t_1786_h
    extends M_182_A {
        protected t_1786_h(StructurePieceType p_i50108_1_, int p_i50108_2_) {
            super(p_i50108_1_, p_i50108_2_);
        }

        public t_1786_h(StructurePieceType p_i50109_1_, U_2912_j p_i50109_2_) {
            super(p_i50109_1_, p_i50109_2_);
        }
    }
}



