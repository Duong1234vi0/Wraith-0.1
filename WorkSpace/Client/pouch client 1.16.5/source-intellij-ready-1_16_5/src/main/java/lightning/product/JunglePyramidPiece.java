/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.E_1708_F;
import lightning.product.E_3771_B;
import lightning.product.F_2203_T;
import lightning.product.J_3017_d;
import lightning.product.K_3256_W;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.Q_4220_D;
import lightning.product.WorldGenLevel;
import lightning.product.ScatteredFeaturePiece;
import lightning.product.U_2912_j;
import lightning.product.Y_1387_d;
import lightning.product.Z_4734_t;
import lightning.product.a_3742_W;
import lightning.product.b_2085_h;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.StructurePieceType;
import lightning.product.RepeaterBlock;
import lightning.product.h_4152_b;
import lightning.product.l_311_L;
import lightning.product.o_4810_o;
import lightning.product.y_1030_X;
import lightning.product.z_1753_f;
import lightning.product.z_2909_G;

public class JunglePyramidPiece
extends ScatteredFeaturePiece {
    private boolean P_1922_E;
    private boolean u_1723_Y;
    private boolean v_4262_N;
    private boolean w_1484_f;
    private static final n_1700_B t_148_a = new n_1700_B();

    public JunglePyramidPiece(Random random, int x, int z) {
        super(StructurePieceType.e_4240_b, random, x, 64, z, 12, 10, 15);
    }

    public JunglePyramidPiece(b_2085_h p_i51350_1_, U_2912_j p_i51350_2_) {
        super(StructurePieceType.e_4240_b, p_i51350_2_);
        this.P_1922_E = p_i51350_2_.t_1786_h("placedMainChest");
        this.u_1723_Y = p_i51350_2_.t_1786_h("placedHiddenChest");
        this.v_4262_N = p_i51350_2_.t_1786_h("placedTrap1");
        this.w_1484_f = p_i51350_2_.t_1786_h("placedTrap2");
    }

    @Override
    protected void n_1700_B(U_2912_j tagCompound) {
        super.n_1700_B(tagCompound);
        tagCompound.n_1700_B("placedMainChest", this.P_1922_E);
        tagCompound.n_1700_B("placedHiddenChest", this.u_1723_Y);
        tagCompound.n_1700_B("placedTrap1", this.v_4262_N);
        tagCompound.n_1700_B("placedTrap2", this.w_1484_f);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
        if (!this.n_1700_B(p_230383_1_, p_230383_5_, 0)) {
            return false;
        }
        this.n_1700_B(p_230383_1_, p_230383_5_, 0, -4, 0, this.n_1700_B - 1, 0, this.R_4764_Y - 1, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 2, 1, 2, 9, 2, 2, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 2, 1, 12, 9, 2, 12, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 2, 1, 3, 2, 2, 11, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 9, 1, 3, 9, 2, 11, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 1, 3, 1, 10, 6, 1, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 1, 3, 13, 10, 6, 13, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 1, 3, 2, 1, 6, 12, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 10, 3, 2, 10, 6, 12, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 2, 3, 2, 9, 3, 12, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 2, 6, 2, 9, 6, 12, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 3, 7, 3, 8, 7, 11, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 4, 8, 4, 7, 8, 10, false, p_230383_4_, t_148_a);
        this.J_1907_R(p_230383_1_, p_230383_5_, 3, 1, 3, 8, 2, 11);
        this.J_1907_R(p_230383_1_, p_230383_5_, 4, 3, 6, 7, 3, 9);
        this.J_1907_R(p_230383_1_, p_230383_5_, 2, 4, 2, 9, 5, 12);
        this.J_1907_R(p_230383_1_, p_230383_5_, 4, 6, 5, 7, 6, 9);
        this.J_1907_R(p_230383_1_, p_230383_5_, 5, 7, 6, 6, 7, 8);
        this.J_1907_R(p_230383_1_, p_230383_5_, 5, 1, 2, 6, 2, 2);
        this.J_1907_R(p_230383_1_, p_230383_5_, 5, 2, 12, 6, 2, 12);
        this.J_1907_R(p_230383_1_, p_230383_5_, 5, 5, 1, 6, 5, 1);
        this.J_1907_R(p_230383_1_, p_230383_5_, 5, 5, 13, 6, 5, 13);
        this.n_1700_B(p_230383_1_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 1, 5, 5, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 10, 5, 5, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 1, 5, 9, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 10, 5, 9, p_230383_5_);
        for (int i = 0; i <= 14; i += 14) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 2, 4, i, 2, 5, i, false, p_230383_4_, t_148_a);
            this.n_1700_B(p_230383_1_, p_230383_5_, 4, 4, i, 4, 5, i, false, p_230383_4_, t_148_a);
            this.n_1700_B(p_230383_1_, p_230383_5_, 7, 4, i, 7, 5, i, false, p_230383_4_, t_148_a);
            this.n_1700_B(p_230383_1_, p_230383_5_, 9, 4, i, 9, 5, i, false, p_230383_4_, t_148_a);
        }
        this.n_1700_B(p_230383_1_, p_230383_5_, 5, 6, 0, 6, 6, 0, false, p_230383_4_, t_148_a);
        for (int l = 0; l <= 11; l += 11) {
            for (int j = 2; j <= 12; j += 2) {
                this.n_1700_B(p_230383_1_, p_230383_5_, l, 4, j, l, 5, j, false, p_230383_4_, t_148_a);
            }
            this.n_1700_B(p_230383_1_, p_230383_5_, l, 6, 5, l, 6, 5, false, p_230383_4_, t_148_a);
            this.n_1700_B(p_230383_1_, p_230383_5_, l, 6, 9, l, 6, 9, false, p_230383_4_, t_148_a);
        }
        this.n_1700_B(p_230383_1_, p_230383_5_, 2, 7, 2, 2, 9, 2, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 9, 7, 2, 9, 9, 2, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 2, 7, 12, 2, 9, 12, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 9, 7, 12, 9, 9, 12, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 4, 9, 4, 4, 9, 4, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 7, 9, 4, 7, 9, 4, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 4, 9, 10, 4, 9, 10, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 7, 9, 10, 7, 9, 10, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 5, 9, 7, 6, 9, 7, false, p_230383_4_, t_148_a);
        K_4074_S blockstate3 = (K_4074_S)a_3742_W.S_3139_t.multiplayerClientSuggestionProvider().n_1700_B(z_2909_G.P_4830_p, b_257_Y.u_1723_Y);
        K_4074_S blockstate4 = (K_4074_S)a_3742_W.S_3139_t.multiplayerClientSuggestionProvider().n_1700_B(z_2909_G.P_4830_p, b_257_Y.P_1922_E);
        K_4074_S blockstate = (K_4074_S)a_3742_W.S_3139_t.multiplayerClientSuggestionProvider().n_1700_B(z_2909_G.P_4830_p, b_257_Y.G_564_y);
        K_4074_S blockstate1 = (K_4074_S)a_3742_W.S_3139_t.multiplayerClientSuggestionProvider().n_1700_B(z_2909_G.P_4830_p, b_257_Y.R_4764_Y);
        this.n_1700_B(p_230383_1_, blockstate1, 5, 9, 6, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate1, 6, 9, 6, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate, 5, 9, 8, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate, 6, 9, 8, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate1, 4, 0, 0, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate1, 5, 0, 0, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate1, 6, 0, 0, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate1, 7, 0, 0, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate1, 4, 1, 8, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate1, 4, 2, 9, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate1, 4, 3, 10, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate1, 7, 1, 8, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate1, 7, 2, 9, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate1, 7, 3, 10, p_230383_5_);
        this.n_1700_B(p_230383_1_, p_230383_5_, 4, 1, 9, 4, 1, 9, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 7, 1, 9, 7, 1, 9, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 4, 1, 10, 7, 2, 10, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 5, 4, 5, 6, 4, 5, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, blockstate3, 4, 4, 5, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate4, 7, 4, 5, p_230383_5_);
        for (int k = 0; k < 4; ++k) {
            this.n_1700_B(p_230383_1_, blockstate, 5, 0 - k, 6 + k, p_230383_5_);
            this.n_1700_B(p_230383_1_, blockstate, 6, 0 - k, 6 + k, p_230383_5_);
            this.J_1907_R(p_230383_1_, p_230383_5_, 5, 0 - k, 7 + k, 6, 0 - k, 9 + k);
        }
        this.J_1907_R(p_230383_1_, p_230383_5_, 1, -3, 12, 10, -1, 13);
        this.J_1907_R(p_230383_1_, p_230383_5_, 1, -3, 1, 3, -1, 13);
        this.J_1907_R(p_230383_1_, p_230383_5_, 1, -3, 1, 9, -1, 5);
        for (int i1 = 1; i1 <= 13; i1 += 2) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, -3, i1, 1, -2, i1, false, p_230383_4_, t_148_a);
        }
        for (int j1 = 2; j1 <= 12; j1 += 2) {
            this.n_1700_B(p_230383_1_, p_230383_5_, 1, -1, j1, 3, -1, j1, false, p_230383_4_, t_148_a);
        }
        this.n_1700_B(p_230383_1_, p_230383_5_, 2, -2, 1, 5, -2, 1, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 7, -2, 1, 9, -2, 1, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 6, -3, 1, 6, -3, 1, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 6, -1, 1, 6, -1, 1, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.d_2169_p.multiplayerClientSuggestionProvider().n_1700_B(l_311_L.P_4830_p, b_257_Y.u_1723_Y)).n_1700_B(l_311_L.Q_4569_t, true), 1, -3, 8, p_230383_5_);
        this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.d_2169_p.multiplayerClientSuggestionProvider().n_1700_B(l_311_L.P_4830_p, b_257_Y.P_1922_E)).n_1700_B(l_311_L.Q_4569_t, true), 4, -3, 8, p_230383_5_);
        this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)((K_4074_S)a_3742_W.I_3637_j.multiplayerClientSuggestionProvider().n_1700_B(E_1708_F.t_1786_h, true)).n_1700_B(E_1708_F.w_1457_N, true)).n_1700_B(E_1708_F.h_1847_R, true), 2, -3, 8, p_230383_5_);
        this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)((K_4074_S)a_3742_W.I_3637_j.multiplayerClientSuggestionProvider().n_1700_B(E_1708_F.t_1786_h, true)).n_1700_B(E_1708_F.w_1457_N, true)).n_1700_B(E_1708_F.h_1847_R, true), 3, -3, 8, p_230383_5_);
        K_4074_S blockstate5 = (K_4074_S)((K_4074_S)a_3742_W.P_5000_x.multiplayerClientSuggestionProvider().n_1700_B(Z_4734_t.P_4830_p, y_1030_X.J_1907_R)).n_1700_B(Z_4734_t.Q_4569_t, y_1030_X.J_1907_R);
        this.n_1700_B(p_230383_1_, blockstate5, 5, -3, 7, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate5, 5, -3, 6, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate5, 5, -3, 5, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate5, 5, -3, 4, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate5, 5, -3, 3, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate5, 5, -3, 2, p_230383_5_);
        this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.P_5000_x.multiplayerClientSuggestionProvider().n_1700_B(Z_4734_t.P_4830_p, y_1030_X.J_1907_R)).n_1700_B(Z_4734_t.M_182_A, y_1030_X.J_1907_R), 5, -3, 1, p_230383_5_);
        this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.P_5000_x.multiplayerClientSuggestionProvider().n_1700_B(Z_4734_t.h_1847_R, y_1030_X.J_1907_R)).n_1700_B(Z_4734_t.M_182_A, y_1030_X.J_1907_R), 4, -3, 1, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.U_1341_G.multiplayerClientSuggestionProvider(), 3, -3, 1, p_230383_5_);
        if (!this.v_4262_N) {
            this.v_4262_N = this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 3, -2, 1, b_257_Y.R_4764_Y, o_4810_o.H_2857_Y);
        }
        this.n_1700_B(p_230383_1_, (K_4074_S)a_3742_W.U_4087_m.multiplayerClientSuggestionProvider().n_1700_B(Q_4220_D.M_182_A, true), 3, -2, 2, p_230383_5_);
        this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.d_2169_p.multiplayerClientSuggestionProvider().n_1700_B(l_311_L.P_4830_p, b_257_Y.R_4764_Y)).n_1700_B(l_311_L.Q_4569_t, true), 7, -3, 1, p_230383_5_);
        this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.d_2169_p.multiplayerClientSuggestionProvider().n_1700_B(l_311_L.P_4830_p, b_257_Y.G_564_y)).n_1700_B(l_311_L.Q_4569_t, true), 7, -3, 5, p_230383_5_);
        this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)((K_4074_S)a_3742_W.I_3637_j.multiplayerClientSuggestionProvider().n_1700_B(E_1708_F.M_182_A, true)).n_1700_B(E_1708_F.multiplayerClientSuggestionProvider, true)).n_1700_B(E_1708_F.h_1847_R, true), 7, -3, 2, p_230383_5_);
        this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)((K_4074_S)a_3742_W.I_3637_j.multiplayerClientSuggestionProvider().n_1700_B(E_1708_F.M_182_A, true)).n_1700_B(E_1708_F.multiplayerClientSuggestionProvider, true)).n_1700_B(E_1708_F.h_1847_R, true), 7, -3, 3, p_230383_5_);
        this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)((K_4074_S)a_3742_W.I_3637_j.multiplayerClientSuggestionProvider().n_1700_B(E_1708_F.M_182_A, true)).n_1700_B(E_1708_F.multiplayerClientSuggestionProvider, true)).n_1700_B(E_1708_F.h_1847_R, true), 7, -3, 4, p_230383_5_);
        this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.P_5000_x.multiplayerClientSuggestionProvider().n_1700_B(Z_4734_t.h_1847_R, y_1030_X.J_1907_R)).n_1700_B(Z_4734_t.M_182_A, y_1030_X.J_1907_R), 8, -3, 6, p_230383_5_);
        this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.P_5000_x.multiplayerClientSuggestionProvider().n_1700_B(Z_4734_t.M_182_A, y_1030_X.J_1907_R)).n_1700_B(Z_4734_t.Q_4569_t, y_1030_X.J_1907_R), 9, -3, 6, p_230383_5_);
        this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)a_3742_W.P_5000_x.multiplayerClientSuggestionProvider().n_1700_B(Z_4734_t.P_4830_p, y_1030_X.J_1907_R)).n_1700_B(Z_4734_t.Q_4569_t, y_1030_X.n_1700_B), 9, -3, 5, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.U_1341_G.multiplayerClientSuggestionProvider(), 9, -3, 4, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate5, 9, -2, 4, p_230383_5_);
        if (!this.w_1484_f) {
            this.w_1484_f = this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 9, -2, 3, b_257_Y.P_1922_E, o_4810_o.H_2857_Y);
        }
        this.n_1700_B(p_230383_1_, (K_4074_S)a_3742_W.U_4087_m.multiplayerClientSuggestionProvider().n_1700_B(Q_4220_D.Q_4569_t, true), 8, -1, 3, p_230383_5_);
        this.n_1700_B(p_230383_1_, (K_4074_S)a_3742_W.U_4087_m.multiplayerClientSuggestionProvider().n_1700_B(Q_4220_D.Q_4569_t, true), 8, -2, 3, p_230383_5_);
        if (!this.P_1922_E) {
            this.P_1922_E = this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 8, -3, 3, o_4810_o.c_3005_b);
        }
        this.n_1700_B(p_230383_1_, a_3742_W.U_1341_G.multiplayerClientSuggestionProvider(), 9, -3, 2, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.U_1341_G.multiplayerClientSuggestionProvider(), 8, -3, 1, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.U_1341_G.multiplayerClientSuggestionProvider(), 4, -3, 5, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.U_1341_G.multiplayerClientSuggestionProvider(), 5, -2, 5, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.U_1341_G.multiplayerClientSuggestionProvider(), 5, -1, 5, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.U_1341_G.multiplayerClientSuggestionProvider(), 6, -3, 5, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.U_1341_G.multiplayerClientSuggestionProvider(), 7, -2, 5, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.U_1341_G.multiplayerClientSuggestionProvider(), 7, -1, 5, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.U_1341_G.multiplayerClientSuggestionProvider(), 8, -3, 5, p_230383_5_);
        this.n_1700_B(p_230383_1_, p_230383_5_, 9, -1, 1, 9, -1, 5, false, p_230383_4_, t_148_a);
        this.J_1907_R(p_230383_1_, p_230383_5_, 8, -3, 8, 10, -1, 10);
        this.n_1700_B(p_230383_1_, a_3742_W.I_3457_f.multiplayerClientSuggestionProvider(), 8, -2, 11, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.I_3457_f.multiplayerClientSuggestionProvider(), 9, -2, 11, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.I_3457_f.multiplayerClientSuggestionProvider(), 10, -2, 11, p_230383_5_);
        K_4074_S blockstate2 = (K_4074_S)((K_4074_S)a_3742_W.x_92_N.multiplayerClientSuggestionProvider().n_1700_B(K_3256_W.w_612_n, b_257_Y.R_4764_Y)).n_1700_B(K_3256_W.RealmsServerPing, F_2203_T.J_1907_R);
        this.n_1700_B(p_230383_1_, blockstate2, 8, -2, 12, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate2, 9, -2, 12, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate2, 10, -2, 12, p_230383_5_);
        this.n_1700_B(p_230383_1_, p_230383_5_, 8, -3, 8, 8, -3, 10, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, p_230383_5_, 10, -3, 8, 10, -3, 10, false, p_230383_4_, t_148_a);
        this.n_1700_B(p_230383_1_, a_3742_W.U_1341_G.multiplayerClientSuggestionProvider(), 10, -2, 9, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate5, 8, -2, 9, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate5, 8, -2, 10, p_230383_5_);
        this.n_1700_B(p_230383_1_, (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)a_3742_W.P_5000_x.multiplayerClientSuggestionProvider().n_1700_B(Z_4734_t.P_4830_p, y_1030_X.J_1907_R)).n_1700_B(Z_4734_t.Q_4569_t, y_1030_X.J_1907_R)).n_1700_B(Z_4734_t.h_1847_R, y_1030_X.J_1907_R)).n_1700_B(Z_4734_t.M_182_A, y_1030_X.J_1907_R), 10, -1, 9, p_230383_5_);
        this.n_1700_B(p_230383_1_, (K_4074_S)a_3742_W.RealmsDefaultUncaughtExceptionHandler.multiplayerClientSuggestionProvider().n_1700_B(h_4152_b.P_4830_p, b_257_Y.J_1907_R), 9, -2, 8, p_230383_5_);
        this.n_1700_B(p_230383_1_, (K_4074_S)a_3742_W.RealmsDefaultUncaughtExceptionHandler.multiplayerClientSuggestionProvider().n_1700_B(h_4152_b.P_4830_p, b_257_Y.P_1922_E), 10, -2, 8, p_230383_5_);
        this.n_1700_B(p_230383_1_, (K_4074_S)a_3742_W.RealmsDefaultUncaughtExceptionHandler.multiplayerClientSuggestionProvider().n_1700_B(h_4152_b.P_4830_p, b_257_Y.P_1922_E), 10, -1, 8, p_230383_5_);
        this.n_1700_B(p_230383_1_, (K_4074_S)a_3742_W.T_437_o.multiplayerClientSuggestionProvider().n_1700_B(RepeaterBlock.w_612_n, b_257_Y.R_4764_Y), 10, -2, 10, p_230383_5_);
        if (!this.u_1723_Y) {
            this.u_1723_Y = this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 9, -3, 10, o_4810_o.c_3005_b);
        }
        return true;
    }

    static class n_1700_B
    extends E_3771_B.n_1700_B {
        private n_1700_B() {
        }

        @Override
        public void n_1700_B(Random rand, int x, int y, int z, boolean wall) {
            this.n_1700_B = rand.nextFloat() < 0.4f ? a_3742_W.P_4830_p.multiplayerClientSuggestionProvider() : a_3742_W.U_1341_G.multiplayerClientSuggestionProvider();
        }
    }
}


