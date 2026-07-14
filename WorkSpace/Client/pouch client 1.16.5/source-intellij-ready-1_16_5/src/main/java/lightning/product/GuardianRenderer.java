/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_1098_v;
import lightning.product.D_4792_h;
import lightning.product.E_4918_z;
import lightning.product.G_1455_B;
import lightning.product.I_4817_s;
import lightning.product.M_1336_P;
import lightning.product.Z_3224_L;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_1290_k;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.r_1334_c;
import lightning.product.r_4811_B;
import lightning.product.GuardianModel;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;

public class GuardianRenderer
extends r_1334_c<G_1455_B, GuardianModel> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/guardian.png");
    private static final g_2336_b t_1786_h = new g_2336_b("textures/entity/guardian_beam.png");
    private static final o_2576_A multiplayerClientSuggestionProvider = o_2576_A.G_564_y(t_1786_h);

    public GuardianRenderer(w_2040_b renderManagerIn) {
        this(renderManagerIn, 0.5f);
    }

    protected GuardianRenderer(w_2040_b p_i50968_1_, float p_i50968_2_) {
        super(p_i50968_1_, new GuardianModel(), p_i50968_2_);
    }

    @Override
    public boolean n_1700_B(G_1455_B livingEntityIn, E_4918_z camera, double camX, double camY, double camZ) {
        r_4811_B livingentity;
        if (super.n_1700_B(livingEntityIn, camera, camX, camY, camZ)) {
            return true;
        }
        if (livingEntityIn.o_82_k() && (livingentity = livingEntityIn.h_973_D()) != null) {
            e_2866_D vector3d = this.n_1700_B(livingentity, (double)livingentity.v_165_F() * 0.5, 1.0f);
            e_2866_D vector3d1 = this.n_1700_B(livingEntityIn, livingEntityIn.X_1313_W(), 1.0f);
            return camera.isBoundingBoxInFrustum(new I_4817_s(vector3d1.J_1907_R, vector3d1.R_4764_Y, vector3d1.G_564_y, vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y));
        }
        return false;
    }

    private e_2866_D n_1700_B(r_4811_B entityLivingBaseIn, double p_177110_2_, float p_177110_4_) {
        double d0 = u_530_F.G_564_y((double)p_177110_4_, entityLivingBaseIn.q_1982_R, entityLivingBaseIn.O_3598_v());
        double d1 = u_530_F.G_564_y((double)p_177110_4_, entityLivingBaseIn.dtoRealmsServerAddress, entityLivingBaseIn.X_2960_b()) + p_177110_2_;
        double d2 = u_530_F.G_564_y((double)p_177110_4_, entityLivingBaseIn.w_612_n, entityLivingBaseIn.l_2647_k());
        return new e_2866_D(d0, d1, d2);
    }

    @Override
    public void n_1700_B(G_1455_B entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        r_4811_B livingentity = entityIn.h_973_D();
        if (livingentity != null) {
            float f = entityIn.A_4115_X(partialTicks);
            float f1 = (float)entityIn.O_508_d.X_933_l() + partialTicks;
            float f2 = f1 * 0.5f % 1.0f;
            float f3 = entityIn.X_1313_W();
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(0.0, (double)f3, 0.0);
            e_2866_D vector3d = this.n_1700_B(livingentity, (double)livingentity.v_165_F() * 0.5, partialTicks);
            e_2866_D vector3d1 = this.n_1700_B(entityIn, f3, partialTicks);
            e_2866_D vector3d2 = vector3d.G_564_y(vector3d1);
            float f4 = (float)(vector3d2.u_1723_Y() + 1.0);
            vector3d2 = vector3d2.G_564_y();
            float f5 = (float)Math.acos(vector3d2.R_4764_Y);
            float f6 = (float)Math.atan2(vector3d2.G_564_y, vector3d2.J_1907_R);
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y((1.5707964f - f6) * 57.295776f));
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(f5 * 57.295776f));
            boolean i = true;
            float f7 = f1 * 0.05f * -1.5f;
            float f8 = f * f;
            int j = 64 + (int)(f8 * 191.0f);
            int k = 32 + (int)(f8 * 191.0f);
            int l = 128 - (int)(f8 * 64.0f);
            float f9 = 0.2f;
            float f10 = 0.282f;
            float f11 = u_530_F.J_1907_R(f7 + 2.3561945f) * 0.282f;
            float f12 = u_530_F.n_1700_B(f7 + 2.3561945f) * 0.282f;
            float f13 = u_530_F.J_1907_R(f7 + 0.7853982f) * 0.282f;
            float f14 = u_530_F.n_1700_B(f7 + 0.7853982f) * 0.282f;
            float f15 = u_530_F.J_1907_R(f7 + 3.926991f) * 0.282f;
            float f16 = u_530_F.n_1700_B(f7 + 3.926991f) * 0.282f;
            float f17 = u_530_F.J_1907_R(f7 + 5.4977875f) * 0.282f;
            float f18 = u_530_F.n_1700_B(f7 + 5.4977875f) * 0.282f;
            float f19 = u_530_F.J_1907_R(f7 + (float)Math.PI) * 0.2f;
            float f20 = u_530_F.n_1700_B(f7 + (float)Math.PI) * 0.2f;
            float f21 = u_530_F.J_1907_R(f7 + 0.0f) * 0.2f;
            float f22 = u_530_F.n_1700_B(f7 + 0.0f) * 0.2f;
            float f23 = u_530_F.J_1907_R(f7 + 1.5707964f) * 0.2f;
            float f24 = u_530_F.n_1700_B(f7 + 1.5707964f) * 0.2f;
            float f25 = u_530_F.J_1907_R(f7 + 4.712389f) * 0.2f;
            float f26 = u_530_F.n_1700_B(f7 + 4.712389f) * 0.2f;
            float f27 = 0.0f;
            float f28 = 0.4999f;
            float f29 = -1.0f + f2;
            float f30 = f4 * 2.5f + f29;
            D_4792_h ivertexbuilder = bufferIn.getBuffer(multiplayerClientSuggestionProvider);
            g_221_o.n_1700_B matrixstack$entry = matrixStackIn.R_4764_Y();
            D_1098_v matrix4f = matrixstack$entry.n_1700_B();
            o_1290_k matrix3f = matrixstack$entry.J_1907_R();
            GuardianRenderer.n_1700_B(ivertexbuilder, matrix4f, matrix3f, f19, f4, f20, j, k, l, 0.4999f, f30);
            GuardianRenderer.n_1700_B(ivertexbuilder, matrix4f, matrix3f, f19, 0.0f, f20, j, k, l, 0.4999f, f29);
            GuardianRenderer.n_1700_B(ivertexbuilder, matrix4f, matrix3f, f21, 0.0f, f22, j, k, l, 0.0f, f29);
            GuardianRenderer.n_1700_B(ivertexbuilder, matrix4f, matrix3f, f21, f4, f22, j, k, l, 0.0f, f30);
            GuardianRenderer.n_1700_B(ivertexbuilder, matrix4f, matrix3f, f23, f4, f24, j, k, l, 0.4999f, f30);
            GuardianRenderer.n_1700_B(ivertexbuilder, matrix4f, matrix3f, f23, 0.0f, f24, j, k, l, 0.4999f, f29);
            GuardianRenderer.n_1700_B(ivertexbuilder, matrix4f, matrix3f, f25, 0.0f, f26, j, k, l, 0.0f, f29);
            GuardianRenderer.n_1700_B(ivertexbuilder, matrix4f, matrix3f, f25, f4, f26, j, k, l, 0.0f, f30);
            float f31 = 0.0f;
            if (entityIn.RealmsWorldResetDto % 2 == 0) {
                f31 = 0.5f;
            }
            GuardianRenderer.n_1700_B(ivertexbuilder, matrix4f, matrix3f, f11, f4, f12, j, k, l, 0.5f, f31 + 0.5f);
            GuardianRenderer.n_1700_B(ivertexbuilder, matrix4f, matrix3f, f13, f4, f14, j, k, l, 1.0f, f31 + 0.5f);
            GuardianRenderer.n_1700_B(ivertexbuilder, matrix4f, matrix3f, f17, f4, f18, j, k, l, 1.0f, f31);
            GuardianRenderer.n_1700_B(ivertexbuilder, matrix4f, matrix3f, f15, f4, f16, j, k, l, 0.5f, f31);
            matrixStackIn.J_1907_R();
        }
    }

    private static void n_1700_B(D_4792_h p_229108_0_, D_1098_v p_229108_1_, o_1290_k p_229108_2_, float p_229108_3_, float p_229108_4_, float p_229108_5_, int p_229108_6_, int p_229108_7_, int p_229108_8_, float p_229108_9_, float p_229108_10_) {
        p_229108_0_.n_1700_B(p_229108_1_, p_229108_3_, p_229108_4_, p_229108_5_).color(p_229108_6_, p_229108_7_, p_229108_8_, 255).tex(p_229108_9_, p_229108_10_).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(0xF000F0).n_1700_B(p_229108_2_, 0.0f, 1.0f, 0.0f).endVertex();
    }

    @Override
    public g_2336_b n_1700_B(G_1455_B entity) {
        return n_1700_B;
    }
}


