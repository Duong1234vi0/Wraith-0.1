/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.E_4918_z;
import lightning.product.M_1336_P;
import lightning.product.V_3354_l;
import lightning.product.Z_2049_e;
import lightning.product.Z_3224_L;
import lightning.product.c_1514_x;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_4563_n;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;
import lightning.product.w_3785_E;

public class EndCrystalRenderer
extends Z_2049_e<V_3354_l> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/end_crystal/end_crystal.png");
    private static final o_2576_A v_4262_N = o_2576_A.G_564_y(n_1700_B);
    private static final float w_1484_f = (float)Math.sin(0.7853981633974483);
    private final e_4189_z t_148_a;
    private final e_4189_z s_956_w;
    private final e_4189_z u_2550_I;

    public EndCrystalRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn);
        this.R_4764_Y = 0.5f;
        this.s_956_w = new e_4189_z(64, 32, 0, 0);
        this.s_956_w.n_1700_B(-4.0f, -4.0f, -4.0f, 8.0f, 8.0f, 8.0f);
        this.t_148_a = new e_4189_z(64, 32, 32, 0);
        this.t_148_a.n_1700_B(-4.0f, -4.0f, -4.0f, 8.0f, 8.0f, 8.0f);
        this.u_2550_I = new e_4189_z(64, 32, 0, 16);
        this.u_2550_I.n_1700_B(-6.0f, 0.0f, -6.0f, 12.0f, 4.0f, 12.0f);
    }

    @Override
    public void n_1700_B(V_3354_l entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        matrixStackIn.n_1700_B();
        float f = EndCrystalRenderer.n_1700_B(entityIn, partialTicks);
        float f1 = ((float)entityIn.n_1700_B + partialTicks) * 3.0f;
        D_4792_h ivertexbuilder = bufferIn.getBuffer(v_4262_N);
        matrixStackIn.n_1700_B();
        matrixStackIn.n_1700_B(2.0f, 2.0f, 2.0f);
        matrixStackIn.n_1700_B(0.0, -0.5, 0.0);
        int i = Z_3224_L.n_1700_B;
        if (entityIn.u_1723_Y()) {
            this.u_2550_I.n_1700_B(matrixStackIn, ivertexbuilder, packedLightIn, i);
        }
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f1));
        matrixStackIn.n_1700_B(0.0, (double)(1.5f + f / 2.0f), 0.0);
        matrixStackIn.n_1700_B(new w_3785_E(new M_1336_P(w_1484_f, 0.0f, w_1484_f), 60.0f, true));
        this.s_956_w.n_1700_B(matrixStackIn, ivertexbuilder, packedLightIn, i);
        float f2 = 0.875f;
        matrixStackIn.n_1700_B(0.875f, 0.875f, 0.875f);
        matrixStackIn.n_1700_B(new w_3785_E(new M_1336_P(w_1484_f, 0.0f, w_1484_f), 60.0f, true));
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f1));
        this.s_956_w.n_1700_B(matrixStackIn, ivertexbuilder, packedLightIn, i);
        matrixStackIn.n_1700_B(0.875f, 0.875f, 0.875f);
        matrixStackIn.n_1700_B(new w_3785_E(new M_1336_P(w_1484_f, 0.0f, w_1484_f), 60.0f, true));
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f1));
        this.t_148_a.n_1700_B(matrixStackIn, ivertexbuilder, packedLightIn, i);
        matrixStackIn.J_1907_R();
        matrixStackIn.J_1907_R();
        c_1514_x blockpos = entityIn.P_1922_E();
        if (blockpos != null) {
            float f3 = (float)blockpos.getX() + 0.5f;
            float f4 = (float)blockpos.getY() + 0.5f;
            float f5 = (float)blockpos.getZ() + 0.5f;
            float f6 = (float)((double)f3 - entityIn.O_3598_v());
            float f7 = (float)((double)f4 - entityIn.X_2960_b());
            float f8 = (float)((double)f5 - entityIn.l_2647_k());
            matrixStackIn.n_1700_B((double)f6, (double)f7, (double)f8);
            j_4563_n.n_1700_B(-f6, -f7 + f, -f8, partialTicks, entityIn.n_1700_B, matrixStackIn, bufferIn, packedLightIn);
        }
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    public static float n_1700_B(V_3354_l p_229051_0_, float p_229051_1_) {
        float f = (float)p_229051_0_.n_1700_B + p_229051_1_;
        float f1 = u_530_F.n_1700_B(f * 0.2f) / 2.0f + 0.5f;
        f1 = (f1 * f1 + f1) * 0.4f;
        return f1 - 1.4f;
    }

    @Override
    public g_2336_b n_1700_B(V_3354_l entity) {
        return n_1700_B;
    }

    @Override
    public boolean n_1700_B(V_3354_l livingEntityIn, E_4918_z camera, double camX, double camY, double camZ) {
        return super.n_1700_B(livingEntityIn, camera, camX, camY, camZ) || livingEntityIn.P_1922_E() != null;
    }
}


