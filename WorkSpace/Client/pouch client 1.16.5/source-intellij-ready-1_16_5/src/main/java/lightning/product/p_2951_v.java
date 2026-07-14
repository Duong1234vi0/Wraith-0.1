/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_3620_e;
import lightning.product.ItemTransforms;
import lightning.product.G_3165_y;
import lightning.product.H_3330_w;
import lightning.product.L_3848_p;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.Z_1993_T;
import lightning.product.Z_2049_e;
import lightning.product.Z_3224_L;
import lightning.product.b_257_Y;
import lightning.product.b_4440_Q;
import lightning.product.MinecraftClient;
import lightning.product.d_1062_x;
import lightning.product.e_2866_D;
import lightning.product.e_3977_C;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.ModelManager;
import lightning.product.o_3091_w;
import lightning.product.w_2040_b;
import lightning.product.x_282_a;
import lightning.product.y_740_d;
import net.optifine.Config;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;
import net.optifine.shaders.Shaders;

public class p_2951_v
extends Z_2049_e<y_740_d> {
    private static final d_1062_x n_1700_B = new d_1062_x("item_frame", "map=false");
    private static final d_1062_x v_4262_N = new d_1062_x("item_frame", "map=true");
    private final MinecraftClient w_1484_f = MinecraftClient.A_4115_X();
    private final H_3330_w t_148_a;
    private static double s_956_w = 4096.0;

    public p_2951_v(w_2040_b renderManagerIn, H_3330_w itemRendererIn) {
        super(renderManagerIn);
        this.t_148_a = itemRendererIn;
    }

    @Override
    public void n_1700_B(y_740_d entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        Z_1993_T itemstack;
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.n_1700_B();
        b_257_Y direction = entityIn.o_2767_H();
        e_2866_D vector3d = this.n_1700_B(entityIn, partialTicks);
        matrixStackIn.n_1700_B(-vector3d.n_1700_B(), -vector3d.J_1907_R(), -vector3d.R_4764_Y());
        double d0 = 0.46875;
        matrixStackIn.n_1700_B((double)direction.t_148_a() * 0.46875, (double)direction.s_956_w() * 0.46875, (double)direction.u_2550_I() * 0.46875);
        matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(entityIn.f_4016_n));
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f - entityIn.p_178_J));
        boolean flag = entityIn.F_3572_x();
        if (!flag) {
            e_3977_C blockrendererdispatcher = this.w_1484_f.z_1333_t();
            ModelManager modelmanager = blockrendererdispatcher.J_1907_R().n_1700_B();
            d_1062_x modelresourcelocation = entityIn.h_1847_R().J_1907_R() instanceof G_3165_y ? v_4262_N : n_1700_B;
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(-0.5, -0.5, -0.5);
            blockrendererdispatcher.R_4764_Y().n_1700_B(matrixStackIn.R_4764_Y(), bufferIn.getBuffer(b_4440_Q.v_4262_N()), null, modelmanager.n_1700_B(modelresourcelocation), 1.0f, 1.0f, 1.0f, packedLightIn, Z_3224_L.n_1700_B);
            matrixStackIn.J_1907_R();
        }
        if (!(itemstack = entityIn.h_1847_R()).n_1700_B()) {
            boolean flag1 = itemstack.J_1907_R() instanceof G_3165_y;
            if (flag) {
                matrixStackIn.n_1700_B(0.0, 0.0, 0.5);
            } else {
                matrixStackIn.n_1700_B(0.0, 0.0, 0.4375);
            }
            int i = flag1 ? entityIn.Q_4569_t() % 4 * 2 : entityIn.Q_4569_t();
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y((float)i * 360.0f / 8.0f));
            if (!Reflector.postForgeBusEvent(Reflector.RenderItemInFrameEvent_Constructor, entityIn, this, matrixStackIn, bufferIn, packedLightIn)) {
                if (flag1) {
                    matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(180.0f));
                    float f = 0.0078125f;
                    matrixStackIn.n_1700_B(0.0078125f, 0.0078125f, 0.0078125f);
                    matrixStackIn.n_1700_B(-64.0, -64.0, 0.0);
                    F_3620_e mapdata = ReflectorForge.getMapData(itemstack, entityIn.O_508_d);
                    matrixStackIn.n_1700_B(0.0, 0.0, -1.0);
                    if (mapdata != null) {
                        this.w_1484_f.s_956_w.t_148_a().n_1700_B(matrixStackIn, bufferIn, mapdata, true, packedLightIn);
                    }
                } else {
                    matrixStackIn.n_1700_B(0.5f, 0.5f, 0.5f);
                    if (this.R_4764_Y(entityIn)) {
                        this.t_148_a.n_1700_B(itemstack, ItemTransforms.J_1907_R.t_148_a, packedLightIn, Z_3224_L.n_1700_B, matrixStackIn, bufferIn);
                    }
                }
            }
        }
        matrixStackIn.J_1907_R();
    }

    @Override
    public e_2866_D n_1700_B(y_740_d entityIn, float partialTicks) {
        return new e_2866_D((float)entityIn.o_2767_H().t_148_a() * 0.3f, -0.25, (float)entityIn.o_2767_H().u_2550_I() * 0.3f);
    }

    @Override
    public g_2336_b n_1700_B(y_740_d entity) {
        return L_3848_p.n_1700_B;
    }

    @Override
    protected boolean J_1907_R(y_740_d entity) {
        if (MinecraftClient.q_2307_F() && !entity.h_1847_R().n_1700_B() && entity.h_1847_R().Y_601_j() && this.J_1907_R.R_4764_Y == entity) {
            double d0 = this.J_1907_R.J_1907_R(entity);
            float f = entity.U_1341_G() ? 32.0f : 64.0f;
            return d0 < (double)(f * f);
        }
        return false;
    }

    @Override
    protected void n_1700_B(y_740_d entityIn, x_282_a displayNameIn, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        super.n_1700_B(entityIn, entityIn.h_1847_R().multiplayerClientSuggestionProvider(), matrixStackIn, bufferIn, packedLightIn);
    }

    private boolean R_4764_Y(y_740_d p_isRenderItem_1_) {
        N_4263_v entity;
        double d0;
        if (Shaders.isShadowPass) {
            return false;
        }
        return Config.zoomMode || !((d0 = p_isRenderItem_1_.v_4262_N((entity = this.w_1484_f.g_2268_R()).O_3598_v(), entity.X_2960_b(), entity.l_2647_k())) > s_956_w);
    }

    public static void P_1922_E() {
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        double d0 = Config.limit(minecraft.P_4830_p.R_3077_Z, 1.0, 120.0);
        double d1 = Math.max(6.0 * (double)minecraft.RealmsServerPing().h_1847_R() / d0, 16.0);
        s_956_w = d1 * d1;
    }
}



