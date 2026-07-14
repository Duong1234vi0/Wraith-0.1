/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_1098_v;
import lightning.product.D_4792_h;
import lightning.product.E_4918_z;
import lightning.product.K_4719_o;
import lightning.product.N_4263_v;
import lightning.product.EntityModel;
import lightning.product.Z_530_i;
import lightning.product.c_1514_x;
import lightning.product.e_1689_x;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.o_4479_Q;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.shaders.Shaders;

public abstract class r_1334_c<T extends Z_530_i, M extends EntityModel<T>>
extends o_4479_Q<T, M> {
    public r_1334_c(w_2040_b renderManagerIn, M entityModelIn, float shadowSizeIn) {
        super(renderManagerIn, entityModelIn, shadowSizeIn);
    }

    @Override
    protected boolean J_1907_R(T entity) {
        return super.J_1907_R(entity) && (((r_4811_B)entity).I_1407_m() || ((N_4263_v)entity).t_3452_g() && entity == this.J_1907_R.R_4764_Y);
    }

    @Override
    public boolean n_1700_B(T livingEntityIn, E_4918_z camera, double camX, double camY, double camZ) {
        if (super.n_1700_B(livingEntityIn, camera, camX, camY, camZ)) {
            return true;
        }
        N_4263_v entity = ((Z_530_i)livingEntityIn).y_2622_c();
        return entity != null ? camera.isBoundingBoxInFrustum(entity.h_2739_B()) : false;
    }

    @Override
    public void n_1700_B(T entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        N_4263_v entity = ((Z_530_i)entityIn).y_2622_c();
        if (entity != null) {
            this.n_1700_B(entityIn, partialTicks, matrixStackIn, bufferIn, entity);
        }
    }

    private <E extends N_4263_v> void n_1700_B(T entityLivingIn, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, E leashHolder) {
        if (!Config.isShaders() || !Shaders.isShadowPass) {
            matrixStackIn.n_1700_B();
            e_2866_D vector3d = leashHolder.P_1922_E(partialTicks);
            double d0 = (double)(u_530_F.v_4262_N(partialTicks, ((Z_530_i)entityLivingIn).C_1162_e, ((Z_530_i)entityLivingIn).D_4361_a) * ((float)Math.PI / 180)) + 1.5707963267948966;
            e_2866_D vector3d1 = ((N_4263_v)entityLivingIn).x_4991_F();
            double d1 = Math.cos(d0) * vector3d1.G_564_y + Math.sin(d0) * vector3d1.J_1907_R;
            double d2 = Math.sin(d0) * vector3d1.G_564_y - Math.cos(d0) * vector3d1.J_1907_R;
            double d3 = u_530_F.G_564_y((double)partialTicks, ((Z_530_i)entityLivingIn).r_715_M, ((N_4263_v)entityLivingIn).O_3598_v()) + d1;
            double d4 = u_530_F.G_564_y((double)partialTicks, ((Z_530_i)entityLivingIn).A_1038_p, ((N_4263_v)entityLivingIn).X_2960_b()) + vector3d1.R_4764_Y;
            double d5 = u_530_F.G_564_y((double)partialTicks, ((Z_530_i)entityLivingIn).i_1637_u, ((N_4263_v)entityLivingIn).l_2647_k()) + d2;
            matrixStackIn.n_1700_B(d1, vector3d1.R_4764_Y, d2);
            float f = (float)(vector3d.J_1907_R - d3);
            float f1 = (float)(vector3d.R_4764_Y - d4);
            float f2 = (float)(vector3d.G_564_y - d5);
            float f3 = 0.025f;
            D_4792_h ivertexbuilder = bufferIn.getBuffer(o_2576_A.M_588_G());
            D_1098_v matrix4f = matrixStackIn.R_4764_Y().n_1700_B();
            float f4 = u_530_F.t_148_a(f * f + f2 * f2) * 0.025f / 2.0f;
            float f5 = f2 * f4;
            float f6 = f * f4;
            c_1514_x blockpos = new c_1514_x(((N_4263_v)entityLivingIn).u_2550_I(partialTicks));
            c_1514_x blockpos1 = new c_1514_x(leashHolder.u_2550_I(partialTicks));
            int i = this.n_1700_B(entityLivingIn, blockpos);
            int j = this.J_1907_R.n_1700_B(leashHolder).n_1700_B(leashHolder, blockpos1);
            int k = ((Z_530_i)entityLivingIn).O_508_d.getLightFor(K_4719_o.n_1700_B, blockpos);
            int l = ((Z_530_i)entityLivingIn).O_508_d.getLightFor(K_4719_o.n_1700_B, blockpos1);
            if (Config.isShaders()) {
                Shaders.beginLeash();
            }
            r_1334_c.n_1700_B(ivertexbuilder, matrix4f, f, f1, f2, i, j, k, l, 0.025f, 0.025f, f5, f6);
            r_1334_c.n_1700_B(ivertexbuilder, matrix4f, f, f1, f2, i, j, k, l, 0.025f, 0.0f, f5, f6);
            if (Config.isShaders()) {
                Shaders.endLeash();
            }
            matrixStackIn.J_1907_R();
        }
    }

    public static void n_1700_B(D_4792_h bufferIn, D_1098_v matrixIn, float p_229119_2_, float p_229119_3_, float p_229119_4_, int blockLight, int holderBlockLight, int skyLight, int holderSkyLight, float p_229119_9_, float p_229119_10_, float p_229119_11_, float p_229119_12_) {
        int i = 24;
        for (int j = 0; j < 24; ++j) {
            float f = (float)j / 23.0f;
            int k = (int)u_530_F.v_4262_N(f, blockLight, holderBlockLight);
            int l = (int)u_530_F.v_4262_N(f, skyLight, holderSkyLight);
            int i1 = e_1689_x.n_1700_B(k, l);
            r_1334_c.n_1700_B(bufferIn, matrixIn, i1, p_229119_2_, p_229119_3_, p_229119_4_, p_229119_9_, p_229119_10_, 24, j, false, p_229119_11_, p_229119_12_);
            r_1334_c.n_1700_B(bufferIn, matrixIn, i1, p_229119_2_, p_229119_3_, p_229119_4_, p_229119_9_, p_229119_10_, 24, j + 1, true, p_229119_11_, p_229119_12_);
        }
    }

    public static void n_1700_B(D_4792_h bufferIn, D_1098_v matrixIn, int packedLight, float p_229120_3_, float p_229120_4_, float p_229120_5_, float p_229120_6_, float p_229120_7_, int p_229120_8_, int p_229120_9_, boolean p_229120_10_, float p_229120_11_, float p_229120_12_) {
        float f = 0.5f;
        float f1 = 0.4f;
        float f2 = 0.3f;
        if (p_229120_9_ % 2 == 0) {
            f *= 0.7f;
            f1 *= 0.7f;
            f2 *= 0.7f;
        }
        float f3 = (float)p_229120_9_ / (float)p_229120_8_;
        float f4 = p_229120_3_ * f3;
        float f5 = p_229120_4_ > 0.0f ? p_229120_4_ * f3 * f3 : p_229120_4_ - p_229120_4_ * (1.0f - f3) * (1.0f - f3);
        float f6 = p_229120_5_ * f3;
        if (!p_229120_10_) {
            bufferIn.n_1700_B(matrixIn, f4 + p_229120_11_, f5 + p_229120_6_ - p_229120_7_, f6 - p_229120_12_).n_1700_B(f, f1, f2, 1.0f).J_1907_R(packedLight).endVertex();
        }
        bufferIn.n_1700_B(matrixIn, f4 - p_229120_11_, f5 + p_229120_7_, f6 + p_229120_12_).n_1700_B(f, f1, f2, 1.0f).J_1907_R(packedLight).endVertex();
        if (p_229120_10_) {
            bufferIn.n_1700_B(matrixIn, f4 + p_229120_11_, f5 + p_229120_6_ - p_229120_7_, f6 - p_229120_12_).n_1700_B(f, f1, f2, 1.0f).J_1907_R(packedLight).endVertex();
        }
    }
}


