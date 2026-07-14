/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.D_1098_v;
import lightning.product.D_1621_L;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.P_328_a;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.a_3913_L;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.i_4434_b;
import lightning.product.k_2603_m;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.q_3206_W;
import lightning.product.r_4811_B;
import lightning.product.y_2603_k;
import lombok.Generated;

public class b_2162_C
extends X_3546_T {
    private static b_2162_C u_2550_I;
    public final q_3206_W v_4262_N = new q_3206_W("\u041a\u043d\u043e\u043f\u043a\u0430 \u043a\u043e\u043b\u0435\u0441\u0430");
    public final p_1977_n w_1484_f = new p_1977_n("\u041d\u0430 \u0434\u0440\u0443\u0437\u0435\u0439", false);
    public final p_1977_n t_148_a = new p_1977_n("\u041d\u0430 \u0441\u0435\u0431\u044f", true);
    public final p_1977_n s_956_w = new p_1977_n("\u041d\u0430 \u0432\u0441\u0435\u0445", false);
    private String M_588_G = "\u041f\u0440\u0438\u0432\u0435\u0442\u0441\u0442\u0432\u0438\u0435";
    private boolean P_4830_p = true;
    private String h_1847_R = null;

    public static b_2162_C h_1847_R() {
        return u_2550_I;
    }

    public b_2162_C() {
        super("Emotions", y_2603_k.R_4764_Y);
        u_2550_I = this;
        this.n_1700_B(this.v_4262_N, this.s_956_w, this.w_1484_f, this.t_148_a);
    }

    @Y_1740_V
    public void n_1700_B(i_4434_b e) {
        if (b_2162_C.c_3005_b.Y_1740_V != null && !(b_2162_C.c_3005_b.Y_1740_V instanceof D_1621_L)) {
            return;
        }
        if (e.n_1700_B() == ((Integer)this.v_4262_N.J_1907_R()).intValue()) {
            if (e.J_1907_R()) {
                if (!(b_2162_C.c_3005_b.Y_1740_V instanceof D_1621_L)) {
                    c_3005_b.n_1700_B(new D_1621_L());
                }
            } else {
                k_2603_m k_2603_m2 = b_2162_C.c_3005_b.Y_1740_V;
                if (k_2603_m2 instanceof D_1621_L) {
                    String[] emotions;
                    D_1621_L screen = (D_1621_L)k_2603_m2;
                    int hovered = screen.n_1700_B();
                    if (hovered == -2) {
                        this.P_4830_p = true;
                    } else if (hovered >= 0 && hovered < (emotions = new String[]{"\u041f\u0440\u0438\u0432\u0435\u0442\u0441\u0442\u0432\u0438\u0435", "\u0422\u0430\u043d\u0435\u0446", "\u0414\u0440\u043e\u0447\u043a\u0430", "\u041d\u0430\u043c\u0430\u0437", "\u0410\u043b\u044c\u0444\u0430 \u0445\u043e\u0434\u044c\u0431\u0430"}).length) {
                        this.M_588_G = emotions[hovered];
                        this.P_4830_p = false;
                    }
                    c_3005_b.n_1700_B((k_2603_m)null);
                }
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(P_328_a e) {
        if (this.P_4830_p || b_2162_C.c_3005_b.Y_259_p == null) {
            return;
        }
        if (!this.M_588_G.equals("\u041d\u0430\u043c\u0430\u0437")) {
            return;
        }
        if (this.h_1847_R != null) {
            return;
        }
        r_4811_B r_4811_B2 = e.J_1907_R();
        if (r_4811_B2 instanceof a_3913_L) {
            a_3913_L player = (a_3913_L)r_4811_B2;
            boolean isLocal = player == b_2162_C.c_3005_b.Y_259_p;
            boolean isFriend = o_148_s.Y_601_j().v_4262_N().R_4764_Y(player.y_4642_Y().getName());
            if (!(isLocal || isFriend || this.s_956_w.t_148_a().booleanValue())) {
                return;
            }
            if (isFriend && !isLocal && !this.w_1484_f.t_148_a().booleanValue()) {
                return;
            }
            if (isLocal && !this.t_148_a.t_148_a().booleanValue()) {
                return;
            }
            g_221_o ms = e.R_4764_Y();
            ms.n_1700_B();
            ms.n_1700_B(0.0, 1.501, 0.0);
            ms.n_1700_B(-1.0f, -1.0f, 1.0f);
            this.n_1700_B(ms);
            ms.J_1907_R();
        }
    }

    private void n_1700_B(g_221_o ms) {
        float dz;
        D_1098_v mat = ms.R_4764_Y().n_1700_B();
        float length = 1.4f;
        float width = 0.75f;
        float halfW = width / 2.0f;
        float y = 0.005f;
        float zStart = 0.25f;
        float zEnd = zStart - length;
        float border = 0.04f;
        float innerBorder = 0.025f;
        float outerExtra = 0.02f;
        c_4037_x.e_4240_b();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.q_2307_F();
        c_4037_x.N_4405_n();
        A_4115_X.n_1700_B(7, E_688_b.Y_601_j);
        b_2162_C.n_1700_B(A_4115_X, mat, -halfW - outerExtra, zEnd - outerExtra, halfW + outerExtra, zStart + outerExtra, y - 0.002f, 0.15f, 0.07f, 0.03f, 1.0f);
        b_2162_C.n_1700_B(A_4115_X, mat, -halfW, zEnd, halfW, zStart, y, 0.55f, 0.07f, 0.07f, 1.0f);
        float bI = border;
        float bO = border + innerBorder;
        b_2162_C.n_1700_B(A_4115_X, mat, -halfW + bI, zEnd + bI, halfW - bI, zEnd + bO, y + 0.001f, 0.78f, 0.55f, 0.15f, 1.0f);
        b_2162_C.n_1700_B(A_4115_X, mat, -halfW + bI, zStart - bO, halfW - bI, zStart - bI, y + 0.001f, 0.78f, 0.55f, 0.15f, 1.0f);
        b_2162_C.n_1700_B(A_4115_X, mat, -halfW + bI, zEnd + bO, -halfW + bO, zStart - bO, y + 0.001f, 0.78f, 0.55f, 0.15f, 1.0f);
        b_2162_C.n_1700_B(A_4115_X, mat, halfW - bO, zEnd + bO, halfW - bI, zStart - bO, y + 0.001f, 0.78f, 0.55f, 0.15f, 1.0f);
        float innerLeft = -halfW + bO + 0.02f;
        float innerRight = halfW - bO - 0.02f;
        float innerFwd = zEnd + bO + 0.02f;
        float innerBack = zStart - bO - 0.02f;
        float mihrabTipZ = innerFwd + 0.05f;
        float mihrabBaseZ = innerFwd + (innerBack - innerFwd) * 0.45f;
        float mihrabHalfW = (innerRight - innerLeft) * 0.35f;
        float mihrabCenterX = 0.0f;
        b_2162_C.n_1700_B(A_4115_X, mat, mihrabCenterX - mihrabHalfW, mihrabBaseZ, mihrabCenterX + mihrabHalfW, mihrabBaseZ + 0.02f, y + 0.001f, 0.78f, 0.55f, 0.15f, 1.0f);
        b_2162_C.n_1700_B(A_4115_X, mat, mihrabCenterX - mihrabHalfW, mihrabTipZ, mihrabCenterX - mihrabHalfW + 0.02f, mihrabBaseZ, y + 0.001f, 0.78f, 0.55f, 0.15f, 1.0f);
        b_2162_C.n_1700_B(A_4115_X, mat, mihrabCenterX + mihrabHalfW - 0.02f, mihrabTipZ, mihrabCenterX + mihrabHalfW, mihrabBaseZ, y + 0.001f, 0.78f, 0.55f, 0.15f, 1.0f);
        b_2162_C.n_1700_B(A_4115_X, mat, mihrabCenterX - 0.015f, mihrabTipZ - 0.01f, mihrabCenterX + 0.015f, mihrabTipZ + 0.03f, y + 0.001f, 0.78f, 0.55f, 0.15f, 1.0f);
        float diamondSize = 0.03f;
        float midZ = (innerFwd + innerBack) / 2.0f;
        for (int i = 0; i < 3 && !((dz = mihrabBaseZ + 0.08f + (float)i * 0.12f) > innerBack - 0.05f); ++i) {
            b_2162_C.n_1700_B(A_4115_X, mat, -diamondSize, dz - diamondSize, diamondSize, dz + diamondSize, y + 0.002f, 0.85f, 0.62f, 0.2f, 1.0f);
        }
        float sideX = halfW * 0.45f;
        for (int i = 0; i < 4; ++i) {
            float dz2 = innerFwd + (innerBack - innerFwd) * ((float)i + 0.5f) / 4.0f;
            b_2162_C.n_1700_B(A_4115_X, mat, sideX - diamondSize, dz2 - diamondSize, sideX + diamondSize, dz2 + diamondSize, y + 0.002f, 0.85f, 0.62f, 0.2f, 1.0f);
            b_2162_C.n_1700_B(A_4115_X, mat, -sideX - diamondSize, dz2 - diamondSize, -sideX + diamondSize, dz2 + diamondSize, y + 0.002f, 0.85f, 0.62f, 0.2f, 1.0f);
        }
        Y_1740_V.J_1907_R();
        A_4115_X.n_1700_B(7, E_688_b.Y_601_j);
        float fringeLen = 0.07f;
        float fringeW = 0.01f;
        float fringeGap = 0.03f;
        int fringeCount = (int)((width - border * 2.0f) / fringeGap);
        for (int i = 0; i < fringeCount; ++i) {
            float fx = -halfW + border + (float)i * fringeGap + fringeGap * 0.5f;
            b_2162_C.n_1700_B(A_4115_X, mat, fx - fringeW, zEnd - fringeLen - outerExtra, fx + fringeW, zEnd - outerExtra, y, 0.88f, 0.78f, 0.5f, 1.0f);
            b_2162_C.n_1700_B(A_4115_X, mat, fx - fringeW, zStart + outerExtra, fx + fringeW, zStart + fringeLen + outerExtra, y, 0.88f, 0.78f, 0.5f, 1.0f);
        }
        Y_1740_V.J_1907_R();
        c_4037_x.x_607_J();
        c_4037_x.k_2293_S();
    }

    private static void n_1700_B(D_3318_r buf, D_1098_v mat, float x0, float z0, float x1, float z1, float y, float r, float g, float b, float a) {
        buf.n_1700_B(mat, x0, y, z0).n_1700_B(r, g, b, a).endVertex();
        buf.n_1700_B(mat, x0, y, z1).n_1700_B(r, g, b, a).endVertex();
        buf.n_1700_B(mat, x1, y, z1).n_1700_B(r, g, b, a).endVertex();
        buf.n_1700_B(mat, x1, y, z0).n_1700_B(r, g, b, a).endVertex();
    }

    @Generated
    public q_3206_W Q_4569_t() {
        return this.v_4262_N;
    }

    @Generated
    public p_1977_n M_182_A() {
        return this.w_1484_f;
    }

    @Generated
    public p_1977_n t_1786_h() {
        return this.t_148_a;
    }

    @Generated
    public p_1977_n N_4405_n() {
        return this.s_956_w;
    }

    @Generated
    public String w_1457_N() {
        return this.M_588_G;
    }

    @Generated
    public void R_4764_Y(String currentEmotion) {
        this.M_588_G = currentEmotion;
    }

    @Generated
    public boolean Y_601_j() {
        return this.P_4830_p;
    }

    @Generated
    public void P_1922_E(boolean stopped) {
        this.P_4830_p = stopped;
    }

    @Generated
    public String Y_259_p() {
        return this.h_1847_R;
    }

    @Generated
    public void G_564_y(String previewEmotion) {
        this.h_1847_R = previewEmotion;
    }
}

