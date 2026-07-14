/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.L_3848_p;
import lightning.product.M_1336_P;
import lightning.product.T_2910_P;
import lightning.product.e_4189_z;
import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.h_3572_K;
import lightning.product.l_1802_R;
import lightning.product.m_1551_m;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.u_530_F;
import lightning.product.w_3785_E;

public class ConduitRenderer
extends l_1802_R<m_1551_m> {
    public static final T_2910_P n_1700_B = new T_2910_P(L_3848_p.n_1700_B, new g_2336_b("entity/conduit/base"));
    public static final T_2910_P J_1907_R = new T_2910_P(L_3848_p.n_1700_B, new g_2336_b("entity/conduit/cage"));
    public static final T_2910_P R_4764_Y = new T_2910_P(L_3848_p.n_1700_B, new g_2336_b("entity/conduit/wind"));
    public static final T_2910_P G_564_y = new T_2910_P(L_3848_p.n_1700_B, new g_2336_b("entity/conduit/wind_vertical"));
    public static final T_2910_P P_1922_E = new T_2910_P(L_3848_p.n_1700_B, new g_2336_b("entity/conduit/open_eye"));
    public static final T_2910_P u_1723_Y = new T_2910_P(L_3848_p.n_1700_B, new g_2336_b("entity/conduit/closed_eye"));
    private final e_4189_z w_1484_f = new e_4189_z(16, 16, 0, 0);
    private final e_4189_z t_148_a;
    private final e_4189_z s_956_w;
    private final e_4189_z u_2550_I;

    public ConduitRenderer(f_2689_h p_i226009_1_) {
        super(p_i226009_1_);
        this.w_1484_f.n_1700_B(-4.0f, -4.0f, 0.0f, 8.0f, 8.0f, 0.0f, 0.01f);
        this.t_148_a = new e_4189_z(64, 32, 0, 0);
        this.t_148_a.n_1700_B(-8.0f, -8.0f, -8.0f, 16.0f, 16.0f, 16.0f);
        this.s_956_w = new e_4189_z(32, 16, 0, 0);
        this.s_956_w.n_1700_B(-3.0f, -3.0f, -3.0f, 6.0f, 6.0f, 6.0f);
        this.u_2550_I = new e_4189_z(32, 16, 0, 0);
        this.u_2550_I.n_1700_B(-4.0f, -4.0f, -4.0f, 8.0f, 8.0f, 8.0f);
    }

    @Override
    public void n_1700_B(m_1551_m tileEntityIn, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, int combinedOverlayIn) {
        float f = (float)tileEntityIn.n_1700_B + partialTicks;
        if (!tileEntityIn.v_4262_N()) {
            float f5 = tileEntityIn.n_1700_B(0.0f);
            D_4792_h ivertexbuilder1 = n_1700_B.n_1700_B(bufferIn, o_2576_A::J_1907_R);
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(0.5, 0.5, 0.5);
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f5));
            this.s_956_w.n_1700_B(matrixStackIn, ivertexbuilder1, combinedLightIn, combinedOverlayIn);
            matrixStackIn.J_1907_R();
        } else {
            float f1 = tileEntityIn.n_1700_B(partialTicks) * 57.295776f;
            float f2 = u_530_F.n_1700_B(f * 0.1f) / 2.0f + 0.5f;
            f2 = f2 * f2 + f2;
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(0.5, (double)(0.3f + f2 * 0.2f), 0.5);
            M_1336_P vector3f = new M_1336_P(0.5f, 1.0f, 0.5f);
            vector3f.G_564_y();
            matrixStackIn.n_1700_B(new w_3785_E(vector3f, f1, true));
            this.u_2550_I.n_1700_B(matrixStackIn, J_1907_R.n_1700_B(bufferIn, o_2576_A::G_564_y), combinedLightIn, combinedOverlayIn);
            matrixStackIn.J_1907_R();
            int i = tileEntityIn.n_1700_B / 66 % 3;
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(0.5, 0.5, 0.5);
            if (i == 1) {
                matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(90.0f));
            } else if (i == 2) {
                matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(90.0f));
            }
            D_4792_h ivertexbuilder = (i == 1 ? G_564_y : R_4764_Y).n_1700_B(bufferIn, o_2576_A::G_564_y);
            this.t_148_a.n_1700_B(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
            matrixStackIn.J_1907_R();
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(0.5, 0.5, 0.5);
            matrixStackIn.n_1700_B(0.875f, 0.875f, 0.875f);
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(180.0f));
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(180.0f));
            this.t_148_a.n_1700_B(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
            matrixStackIn.J_1907_R();
            h_3572_K activerenderinfo = this.v_4262_N.P_1922_E;
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(0.5, (double)(0.3f + f2 * 0.2f), 0.5);
            matrixStackIn.n_1700_B(0.5f, 0.5f, 0.5f);
            float f3 = -activerenderinfo.P_1922_E();
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f3));
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(activerenderinfo.G_564_y()));
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(180.0f));
            float f4 = 1.3333334f;
            matrixStackIn.n_1700_B(1.3333334f, 1.3333334f, 1.3333334f);
            this.w_1484_f.n_1700_B(matrixStackIn, (tileEntityIn.w_1484_f() ? P_1922_E : u_1723_Y).n_1700_B(bufferIn, o_2576_A::G_564_y), combinedLightIn, combinedOverlayIn);
            matrixStackIn.J_1907_R();
        }
    }
}


