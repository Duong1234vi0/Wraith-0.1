/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.K_4074_S;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.O_2369_F;
import lightning.product.EntityModel;
import lightning.product.Z_2049_e;
import lightning.product.Z_3224_L;
import lightning.product.MinecraftClient;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_3091_w;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;
import lightning.product.MinecartModel;
import lightning.product.y_4319_k;

public class MinecartRenderer<T extends y_4319_k>
extends Z_2049_e<T> {
    private static final g_2336_b v_4262_N = new g_2336_b("textures/entity/minecart.png");
    protected final EntityModel<T> n_1700_B = new MinecartModel();

    public MinecartRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn);
        this.R_4764_Y = 0.7f;
    }

    @Override
    public void n_1700_B(T entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.n_1700_B();
        long i = (long)((N_4263_v)entityIn).j_276_v() * 493286711L;
        i = i * i * 4392167121L + i * 98761L;
        float f = (((float)(i >> 16 & 7L) + 0.5f) / 8.0f - 0.5f) * 0.004f;
        float f1 = (((float)(i >> 20 & 7L) + 0.5f) / 8.0f - 0.5f) * 0.004f;
        float f2 = (((float)(i >> 24 & 7L) + 0.5f) / 8.0f - 0.5f) * 0.004f;
        matrixStackIn.n_1700_B((double)f, (double)f1, (double)f2);
        double d0 = u_530_F.G_564_y((double)partialTicks, ((y_4319_k)entityIn).q_1982_R, ((N_4263_v)entityIn).O_3598_v());
        double d1 = u_530_F.G_564_y((double)partialTicks, ((y_4319_k)entityIn).dtoRealmsServerAddress, ((N_4263_v)entityIn).X_2960_b());
        double d2 = u_530_F.G_564_y((double)partialTicks, ((y_4319_k)entityIn).w_612_n, ((N_4263_v)entityIn).l_2647_k());
        double d3 = 0.3f;
        e_2866_D vector3d = ((y_4319_k)entityIn).M_182_A(d0, d1, d2);
        float f3 = u_530_F.v_4262_N(partialTicks, ((y_4319_k)entityIn).UploadStatus, ((y_4319_k)entityIn).f_4016_n);
        if (vector3d != null) {
            e_2866_D vector3d1 = ((y_4319_k)entityIn).n_1700_B(d0, d1, d2, (double)0.3f);
            e_2866_D vector3d2 = ((y_4319_k)entityIn).n_1700_B(d0, d1, d2, (double)-0.3f);
            if (vector3d1 == null) {
                vector3d1 = vector3d;
            }
            if (vector3d2 == null) {
                vector3d2 = vector3d;
            }
            matrixStackIn.n_1700_B(vector3d.J_1907_R - d0, (vector3d1.R_4764_Y + vector3d2.R_4764_Y) / 2.0 - d1, vector3d.G_564_y - d2);
            e_2866_D vector3d3 = vector3d2.J_1907_R(-vector3d1.J_1907_R, -vector3d1.R_4764_Y, -vector3d1.G_564_y);
            if (vector3d3.u_1723_Y() != 0.0) {
                vector3d3 = vector3d3.G_564_y();
                entityYaw = (float)(Math.atan2(vector3d3.G_564_y, vector3d3.J_1907_R) * 180.0 / Math.PI);
                f3 = (float)(Math.atan(vector3d3.R_4764_Y) * 73.0);
            }
        }
        matrixStackIn.n_1700_B(0.0, 0.375, 0.0);
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f - entityYaw));
        matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(-f3));
        float f5 = (float)((y_4319_k)entityIn).t_148_a() - partialTicks;
        float f6 = ((y_4319_k)entityIn).w_1484_f() - partialTicks;
        if (f6 < 0.0f) {
            f6 = 0.0f;
        }
        if (f5 > 0.0f) {
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(u_530_F.n_1700_B(f5) * f5 * f6 / 10.0f * (float)((y_4319_k)entityIn).u_2550_I()));
        }
        int j = ((y_4319_k)entityIn).multiplayerClientSuggestionProvider();
        K_4074_S blockstate = ((y_4319_k)entityIn).Q_4569_t();
        if (blockstate.w_1484_f() != O_2369_F.n_1700_B) {
            matrixStackIn.n_1700_B();
            float f4 = 0.75f;
            matrixStackIn.n_1700_B(0.75f, 0.75f, 0.75f);
            matrixStackIn.n_1700_B(-0.5, (double)((float)(j - 8) / 16.0f), 0.5);
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(90.0f));
            this.n_1700_B(entityIn, partialTicks, blockstate, matrixStackIn, bufferIn, packedLightIn);
            matrixStackIn.J_1907_R();
        }
        matrixStackIn.n_1700_B(-1.0f, -1.0f, 1.0f);
        this.n_1700_B.n_1700_B(entityIn, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f);
        D_4792_h ivertexbuilder = bufferIn.getBuffer(this.n_1700_B.getRenderType(this.n_1700_B(entityIn)));
        this.n_1700_B.render(matrixStackIn, ivertexbuilder, packedLightIn, Z_3224_L.n_1700_B, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStackIn.J_1907_R();
    }

    @Override
    public g_2336_b n_1700_B(T entity) {
        return v_4262_N;
    }

    protected void n_1700_B(T entityIn, float partialTicks, K_4074_S stateIn, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        MinecraftClient.A_4115_X().z_1333_t().n_1700_B(stateIn, matrixStackIn, bufferIn, packedLightIn, Z_3224_L.n_1700_B);
    }
}



