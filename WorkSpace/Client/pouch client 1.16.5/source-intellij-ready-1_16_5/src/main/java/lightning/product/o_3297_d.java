/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.J_3017_d;
import lightning.product.K_4074_S;
import lightning.product.K_550_M;
import lightning.product.BoundingBox;
import lightning.product.WorldGenLevel;
import lightning.product.ScatteredFeaturePiece;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.Y_1387_d;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.b_2085_h;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.StructurePieceType;
import lightning.product.ServerLevelAccessor;
import lightning.product.t_5_h;
import lightning.product.u_863_c;
import lightning.product.w_3611_Y;
import lightning.product.z_1753_f;
import lightning.product.z_2909_G;

public class o_3297_d
extends ScatteredFeaturePiece {
    private boolean P_1922_E;
    private boolean u_1723_Y;

    public o_3297_d(Random random, int x, int z) {
        super(StructurePieceType.v_4276_D, random, x, 64, z, 7, 7, 9);
    }

    public o_3297_d(b_2085_h p_i51340_1_, U_2912_j p_i51340_2_) {
        super(StructurePieceType.v_4276_D, p_i51340_2_);
        this.P_1922_E = p_i51340_2_.t_1786_h("Witch");
        this.u_1723_Y = p_i51340_2_.t_1786_h("Cat");
    }

    @Override
    protected void n_1700_B(U_2912_j tagCompound) {
        super.n_1700_B(tagCompound);
        tagCompound.n_1700_B("Witch", this.P_1922_E);
        tagCompound.n_1700_B("Cat", this.u_1723_Y);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
        int k;
        int i1;
        int l;
        if (!this.n_1700_B(p_230383_1_, p_230383_5_, 0)) {
            return false;
        }
        this.n_1700_B(p_230383_1_, p_230383_5_, 1, 1, 1, 5, 1, 7, a_3742_W.Q_4569_t.multiplayerClientSuggestionProvider(), a_3742_W.Q_4569_t.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 1, 4, 2, 5, 4, 7, a_3742_W.Q_4569_t.multiplayerClientSuggestionProvider(), a_3742_W.Q_4569_t.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 2, 1, 0, 4, 1, 0, a_3742_W.Q_4569_t.multiplayerClientSuggestionProvider(), a_3742_W.Q_4569_t.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 2, 2, 2, 3, 3, 2, a_3742_W.Q_4569_t.multiplayerClientSuggestionProvider(), a_3742_W.Q_4569_t.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 1, 2, 3, 1, 3, 6, a_3742_W.Q_4569_t.multiplayerClientSuggestionProvider(), a_3742_W.Q_4569_t.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 5, 2, 3, 5, 3, 6, a_3742_W.Q_4569_t.multiplayerClientSuggestionProvider(), a_3742_W.Q_4569_t.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 2, 2, 7, 4, 3, 7, a_3742_W.Q_4569_t.multiplayerClientSuggestionProvider(), a_3742_W.Q_4569_t.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 1, 0, 2, 1, 3, 2, a_3742_W.z_1737_N.multiplayerClientSuggestionProvider(), a_3742_W.z_1737_N.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 5, 0, 2, 5, 3, 2, a_3742_W.z_1737_N.multiplayerClientSuggestionProvider(), a_3742_W.z_1737_N.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 1, 0, 7, 1, 3, 7, a_3742_W.z_1737_N.multiplayerClientSuggestionProvider(), a_3742_W.z_1737_N.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 5, 0, 7, 5, 3, 7, a_3742_W.z_1737_N.multiplayerClientSuggestionProvider(), a_3742_W.z_1737_N.multiplayerClientSuggestionProvider(), false);
        this.n_1700_B(p_230383_1_, a_3742_W.h_2848_I.multiplayerClientSuggestionProvider(), 2, 3, 2, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.h_2848_I.multiplayerClientSuggestionProvider(), 3, 3, 7, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 1, 3, 4, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 5, 3, 4, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 5, 3, 5, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.J_3635_s.multiplayerClientSuggestionProvider(), 1, 3, 5, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.O_2934_T.multiplayerClientSuggestionProvider(), 3, 2, 6, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.m_1621_v.multiplayerClientSuggestionProvider(), 4, 2, 6, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.h_2848_I.multiplayerClientSuggestionProvider(), 1, 2, 1, p_230383_5_);
        this.n_1700_B(p_230383_1_, a_3742_W.h_2848_I.multiplayerClientSuggestionProvider(), 5, 2, 1, p_230383_5_);
        K_4074_S blockstate = (K_4074_S)a_3742_W.U_144_f.multiplayerClientSuggestionProvider().n_1700_B(z_2909_G.P_4830_p, b_257_Y.R_4764_Y);
        K_4074_S blockstate1 = (K_4074_S)a_3742_W.U_144_f.multiplayerClientSuggestionProvider().n_1700_B(z_2909_G.P_4830_p, b_257_Y.u_1723_Y);
        K_4074_S blockstate2 = (K_4074_S)a_3742_W.U_144_f.multiplayerClientSuggestionProvider().n_1700_B(z_2909_G.P_4830_p, b_257_Y.P_1922_E);
        K_4074_S blockstate3 = (K_4074_S)a_3742_W.U_144_f.multiplayerClientSuggestionProvider().n_1700_B(z_2909_G.P_4830_p, b_257_Y.G_564_y);
        this.n_1700_B(p_230383_1_, p_230383_5_, 0, 4, 1, 6, 4, 1, blockstate, blockstate, false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 0, 4, 2, 0, 4, 7, blockstate1, blockstate1, false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 6, 4, 2, 6, 4, 7, blockstate2, blockstate2, false);
        this.n_1700_B(p_230383_1_, p_230383_5_, 0, 4, 8, 6, 4, 8, blockstate3, blockstate3, false);
        this.n_1700_B(p_230383_1_, (K_4074_S)blockstate.n_1700_B(z_2909_G.Q_4569_t, u_863_c.P_1922_E), 0, 4, 1, p_230383_5_);
        this.n_1700_B(p_230383_1_, (K_4074_S)blockstate.n_1700_B(z_2909_G.Q_4569_t, u_863_c.G_564_y), 6, 4, 1, p_230383_5_);
        this.n_1700_B(p_230383_1_, (K_4074_S)blockstate3.n_1700_B(z_2909_G.Q_4569_t, u_863_c.G_564_y), 0, 4, 8, p_230383_5_);
        this.n_1700_B(p_230383_1_, (K_4074_S)blockstate3.n_1700_B(z_2909_G.Q_4569_t, u_863_c.P_1922_E), 6, 4, 8, p_230383_5_);
        for (int i = 2; i <= 7; i += 5) {
            for (int j = 1; j <= 5; j += 4) {
                this.J_1907_R(p_230383_1_, a_3742_W.z_1737_N.multiplayerClientSuggestionProvider(), j, -1, i, p_230383_5_);
            }
        }
        if (!this.P_1922_E && p_230383_5_.J_1907_R(new c_1514_x(l = this.n_1700_B(2, 5), i1 = this.n_1700_B(2), k = this.J_1907_R(2, 5)))) {
            this.P_1922_E = true;
            w_3611_Y witchentity = t_5_h.RetryCallException.n_1700_B(p_230383_1_.J_1907_R());
            witchentity.T_3594_S();
            witchentity.J_1907_R((double)l + 0.5, i1, (double)k + 0.5, 0.0f, 0.0f);
            witchentity.n_1700_B(p_230383_1_, p_230383_1_.J_1907_R(new c_1514_x(l, i1, k)), a_3160_D.G_564_y, (V_3157_k)null, null);
            p_230383_1_.n_1700_B(witchentity);
        }
        this.n_1700_B(p_230383_1_, p_230383_5_);
        return true;
    }

    private void n_1700_B(ServerLevelAccessor p_214821_1_, BoundingBox p_214821_2_) {
        int k;
        int j;
        int i;
        if (!this.u_1723_Y && p_214821_2_.J_1907_R(new c_1514_x(i = this.n_1700_B(2, 5), j = this.n_1700_B(2), k = this.J_1907_R(2, 5)))) {
            this.u_1723_Y = true;
            K_550_M catentity = t_5_h.w_1484_f.n_1700_B(p_214821_1_.J_1907_R());
            catentity.T_3594_S();
            catentity.J_1907_R((double)i + 0.5, j, (double)k + 0.5, 0.0f, 0.0f);
            catentity.n_1700_B(p_214821_1_, p_214821_1_.J_1907_R(new c_1514_x(i, j, k)), a_3160_D.G_564_y, (V_3157_k)null, null);
            p_214821_1_.n_1700_B(catentity);
        }
    }
}


