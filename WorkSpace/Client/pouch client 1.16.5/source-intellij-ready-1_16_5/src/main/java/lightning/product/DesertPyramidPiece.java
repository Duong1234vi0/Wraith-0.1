/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.J_3017_d;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.WorldGenLevel;
import lightning.product.ScatteredFeaturePiece;
import lightning.product.U_2912_j;
import lightning.product.Y_1387_d;
import lightning.product.a_3742_W;
import lightning.product.b_2085_h;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.StructurePieceType;
import lightning.product.o_4810_o;
import lightning.product.z_1753_f;
import lightning.product.z_2909_G;

public class DesertPyramidPiece
extends ScatteredFeaturePiece {
    private final boolean[] P_1922_E = new boolean[4];

    public DesertPyramidPiece(Random random, int x, int z) {
        super(StructurePieceType.d_2461_k, random, x, 64, z, 21, 15, 21);
    }

    public DesertPyramidPiece(b_2085_h p_i51351_1_, U_2912_j p_i51351_2_) {
        super(StructurePieceType.d_2461_k, p_i51351_2_);
        this.P_1922_E[0] = p_i51351_2_.t_1786_h("hasPlacedChest0");
        this.P_1922_E[1] = p_i51351_2_.t_1786_h("hasPlacedChest1");
        this.P_1922_E[2] = p_i51351_2_.t_1786_h("hasPlacedChest2");
        this.P_1922_E[3] = p_i51351_2_.t_1786_h("hasPlacedChest3");
    }

    @Override
    protected void n_1700_B(U_2912_j tagCompound) {
        super.n_1700_B(tagCompound);
        tagCompound.n_1700_B("hasPlacedChest0", this.P_1922_E[0]);
        tagCompound.n_1700_B("hasPlacedChest1", this.P_1922_E[1]);
        tagCompound.n_1700_B("hasPlacedChest2", this.P_1922_E[2]);
        tagCompound.n_1700_B("hasPlacedChest3", this.P_1922_E[3]);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
        this.n_1700_B(p_230383_1_, p_230383_5_, 0, -4, 0, this.n_1700_B - 1, 0, this.R_4764_Y - 1, a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), false);
        for (int i = 1; i <= 9; ++i) {
            this.n_1700_B(p_230383_1_, p_230383_5_, i, i, i, this.n_1700_B - 1 - i, i, this.R_4764_Y - 1 - i, a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), false);
            this.n_1700_B(p_230383_1_, p_230383_5_, i + 1, i, i + 1, this.n_1700_B - 2 - i, i, this.R_4764_Y - 2 - i, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
        }
        for (int k1 = 0; k1 < this.n_1700_B; ++k1) {
            for (int j = 0; j < this.R_4764_Y; ++j) {
                int k = -5;
                this.J_1907_R(p_230383_1_, a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), k1, -5, j, p_230383_5_);
            }
        }
        K_4074_S blockstate1 = (K_4074_S)a_3742_W.E_4256_w.multiplayerClientSuggestionProvider().n_1700_B(z_2909_G.P_4830_p, b_257_Y.R_4764_Y);
        K_4074_S blockstate2 = (K_4074_S)a_3742_W.E_4256_w.multiplayerClientSuggestionProvider().n_1700_B(z_2909_G.P_4830_p, b_257_Y.G_564_y);
        K_4074_S blockstate3 = (K_4074_S)a_3742_W.E_4256_w.multiplayerClientSuggestionProvider().n_1700_B(z_2909_G.P_4830_p, b_257_Y.u_1723_Y);
        K_4074_S blockstate = (K_4074_S)a_3742_W.E_4256_w.multiplayerClientSuggestionProvider().n_1700_B(z_2909_G.P_4830_p, b_257_Y.P_1922_E);
        this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 0, 4, 9, 4, a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 1, 10, 1, 3, 10, 3, a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, blockstate1, 2, 10, 0, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate2, 2, 10, 4, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate3, 0, 10, 2, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate, 4, 10, 2, p_230383_5_);
        this.n_1700_B(p_230383_1_, p_230383_5_, this.n_1700_B - 5, 0, 0, this.n_1700_B - 1, 9, 4, a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, this.n_1700_B - 4, 10, 1, this.n_1700_B - 2, 10, 3, a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, blockstate1, this.n_1700_B - 3, 10, 0, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate2, this.n_1700_B - 3, 10, 4, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate3, this.n_1700_B - 5, 10, 2, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate, this.n_1700_B - 1, 10, 2, p_230383_5_);
        this.n_1700_B(p_230383_1_, p_230383_5_, 8, 0, 0, 12, 4, 4, a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 9, 1, 0, 11, 3, 4, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), 9, 1, 1, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), 9, 2, 1, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), 9, 3, 1, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), 10, 3, 1, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), 11, 3, 1, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), 11, 2, 1, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), 11, 1, 1, p_230383_5_);
        this.n_1700_B(p_230383_1_, p_230383_5_, 4, 1, 1, 8, 3, 3, a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 4, 1, 2, 8, 2, 2, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 12, 1, 1, 16, 3, 3, a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 12, 1, 2, 16, 2, 2, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 5, 4, 5, this.n_1700_B - 6, 4, this.R_4764_Y - 6, a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 9, 4, 9, 11, 4, 11, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 8, 1, 8, 8, 3, 8, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 12, 1, 8, 12, 3, 8, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 8, 1, 12, 8, 3, 12, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 12, 1, 12, 12, 3, 12, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, 5, 4, 4, 11, a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, this.n_1700_B - 5, 1, 5, this.n_1700_B - 2, 4, 11, a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 6, 7, 9, 6, 7, 11, a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, this.n_1700_B - 7, 7, 9, this.n_1700_B - 7, 7, 11, a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 5, 5, 9, 5, 7, 11, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, this.n_1700_B - 6, 5, 9, this.n_1700_B - 6, 7, 11, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 5, 5, 10, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 5, 6, 10, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 6, 6, 10, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), this.n_1700_B - 6, 5, 10, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), this.n_1700_B - 6, 6, 10, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), this.n_1700_B - 7, 6, 10, p_230383_5_);
        this.n_1700_B(p_230383_1_, p_230383_5_, 2, 4, 4, 2, 6, 4, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, this.n_1700_B - 3, 4, 4, this.n_1700_B - 3, 6, 4, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, blockstate1, 2, 4, 5, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate1, 2, 3, 4, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate1, this.n_1700_B - 3, 4, 5, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate1, this.n_1700_B - 3, 3, 4, p_230383_5_);
        this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, 3, 2, 2, 3, a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, this.n_1700_B - 3, 1, 3, this.n_1700_B - 2, 2, 3, a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), 1, 1, 2, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), this.n_1700_B - 2, 1, 2, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.NoFall.multiplayerClientSuggestionProvider(), 1, 2, 2, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.NoFall.multiplayerClientSuggestionProvider(), this.n_1700_B - 2, 2, 2, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate, 2, 1, 2, p_230383_5_);
        this.n_1700_B(p_230383_1_, blockstate3, this.n_1700_B - 3, 1, 2, p_230383_5_);
        this.n_1700_B(p_230383_1_, p_230383_5_, 4, 3, 5, 4, 3, 17, a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, this.n_1700_B - 5, 3, 5, this.n_1700_B - 5, 3, 17, a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 3, 1, 5, 4, 2, 16, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, this.n_1700_B - 6, 1, 5, this.n_1700_B - 5, 2, 16, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
        for (int l = 5; l <= 17; l += 2) {
            this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), 4, 1, l, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.t_4219_U.multiplayerClientSuggestionProvider(), 4, 2, l, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), this.n_1700_B - 5, 1, l, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.t_4219_U.multiplayerClientSuggestionProvider(), this.n_1700_B - 5, 2, l, p_230383_5_);
        }
        this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), 10, 0, 7, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), 10, 0, 8, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), 9, 0, 9, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), 11, 0, 9, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), 8, 0, 10, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), 12, 0, 10, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), 7, 0, 10, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), 13, 0, 10, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), 9, 0, 11, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), 11, 0, 11, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), 10, 0, 12, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), 10, 0, 13, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.AutoExplosion.multiplayerClientSuggestionProvider(), 10, 0, 10, p_230383_5_);
        for (int l1 = 0; l1 <= this.n_1700_B - 1; l1 += this.n_1700_B - 1) {
            this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), l1, 2, 1, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), l1, 2, 2, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), l1, 2, 3, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), l1, 3, 1, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), l1, 3, 2, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), l1, 3, 3, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), l1, 4, 1, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.t_4219_U.multiplayerClientSuggestionProvider(), l1, 4, 2, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), l1, 4, 3, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), l1, 5, 1, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), l1, 5, 2, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), l1, 5, 3, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), l1, 6, 1, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.t_4219_U.multiplayerClientSuggestionProvider(), l1, 6, 2, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), l1, 6, 3, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), l1, 7, 1, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), l1, 7, 2, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), l1, 7, 3, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), l1, 8, 1, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), l1, 8, 2, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), l1, 8, 3, p_230383_5_);
        }
        for (int i2 = 2; i2 <= this.n_1700_B - 3; i2 += this.n_1700_B - 3 - 2) {
            this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), i2 - 1, 2, 0, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), i2, 2, 0, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), i2 + 1, 2, 0, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), i2 - 1, 3, 0, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), i2, 3, 0, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), i2 + 1, 3, 0, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), i2 - 1, 4, 0, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.t_4219_U.multiplayerClientSuggestionProvider(), i2, 4, 0, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), i2 + 1, 4, 0, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), i2 - 1, 5, 0, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), i2, 5, 0, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), i2 + 1, 5, 0, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), i2 - 1, 6, 0, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.t_4219_U.multiplayerClientSuggestionProvider(), i2, 6, 0, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), i2 + 1, 6, 0, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), i2 - 1, 7, 0, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), i2, 7, 0, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), i2 + 1, 7, 0, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), i2 - 1, 8, 0, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), i2, 8, 0, p_230383_5_);
            this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), i2 + 1, 8, 0, p_230383_5_);
        }
        this.n_1700_B(p_230383_1_, p_230383_5_, 8, 4, 0, 12, 6, 0, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 8, 6, 0, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 12, 6, 0, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), 9, 5, 0, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.t_4219_U.multiplayerClientSuggestionProvider(), 10, 5, 0, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.h_3858_e.multiplayerClientSuggestionProvider(), 11, 5, 0, p_230383_5_);
        this.n_1700_B(p_230383_1_, p_230383_5_, 8, -14, 8, 12, -11, 12, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 8, -10, 8, 12, -10, 12, a_3742_W.t_4219_U.multiplayerClientSuggestionProvider(), a_3742_W.t_4219_U.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 8, -9, 8, 12, -9, 12, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 8, -8, 8, 12, -1, 12, a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), a_3742_W.h_4320_q.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 9, -11, 9, 11, -1, 11, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, a_3742_W.i_601_W.multiplayerClientSuggestionProvider(), 10, -11, 10, p_230383_5_);
        this.n_1700_B(p_230383_1_, p_230383_5_, 9, -13, 9, 11, -13, 11, a_3742_W.TextRenderingUtils.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 8, -11, 10, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 8, -10, 10, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.t_4219_U.multiplayerClientSuggestionProvider(), 7, -10, 10, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), 7, -11, 10, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 12, -11, 10, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 12, -10, 10, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.t_4219_U.multiplayerClientSuggestionProvider(), 13, -10, 10, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), 13, -11, 10, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 10, -11, 8, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 10, -10, 8, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.t_4219_U.multiplayerClientSuggestionProvider(), 10, -10, 7, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), 10, -11, 7, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 10, -11, 12, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 10, -10, 12, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.t_4219_U.multiplayerClientSuggestionProvider(), 10, -10, 13, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.V_1446_Y.multiplayerClientSuggestionProvider(), 10, -11, 13, p_230383_5_);
        for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
            if (this.P_1922_E[direction.G_564_y()]) continue;
            int i1 = direction.t_148_a() * 2;
            int j1 = direction.u_2550_I() * 2;
            this.P_1922_E[direction.G_564_y()] = this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 10 + i1, -11, 10 + j1, o_4810_o.Z_875_P);
        }
        return true;
    }
}



