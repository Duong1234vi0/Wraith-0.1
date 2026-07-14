/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.D_1098_v;
import lightning.product.D_4792_h;
import lightning.product.EndCrystalRenderer;
import lightning.product.M_1336_P;
import lightning.product.EntityModel;
import lightning.product.Z_2049_e;
import lightning.product.Z_3224_L;
import lightning.product.b_2971_b;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_1290_k;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.shaders.Shaders;

public class j_4563_n
extends Z_2049_e<b_2971_b> {
    public static final g_2336_b n_1700_B = new g_2336_b("textures/entity/end_crystal/end_crystal_beam.png");
    private static final g_2336_b v_4262_N = new g_2336_b("textures/entity/enderdragon/dragon_exploding.png");
    private static final g_2336_b w_1484_f = new g_2336_b("textures/entity/enderdragon/dragon.png");
    private static final g_2336_b t_148_a = new g_2336_b("textures/entity/enderdragon/dragon_eyes.png");
    private static final o_2576_A s_956_w = o_2576_A.G_564_y(w_1484_f);
    private static final o_2576_A u_2550_I = o_2576_A.s_956_w(w_1484_f);
    private static final o_2576_A M_588_G = o_2576_A.P_4830_p(t_148_a);
    private static final o_2576_A P_4830_p = o_2576_A.t_148_a(n_1700_B);
    private static final float h_1847_R = (float)(Math.sqrt(3.0) / 2.0);
    private final n_1700_B Q_4569_t = new n_1700_B();

    public j_4563_n(w_2040_b renderManagerIn) {
        super(renderManagerIn);
        this.R_4764_Y = 0.5f;
    }

    @Override
    public void n_1700_B(b_2971_b entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        matrixStackIn.n_1700_B();
        float f = (float)entityIn.n_1700_B(7, partialTicks)[0];
        float f1 = (float)(entityIn.n_1700_B(5, partialTicks)[1] - entityIn.n_1700_B(10, partialTicks)[1]);
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-f));
        matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(f1 * 10.0f));
        matrixStackIn.n_1700_B(0.0, 0.0, 1.0);
        matrixStackIn.n_1700_B(-1.0f, -1.0f, 1.0f);
        matrixStackIn.n_1700_B(0.0, (double)-1.501f, 0.0);
        boolean flag = entityIn.RealmsLongRunningMcoTaskScreen > 0;
        this.Q_4569_t.n_1700_B(entityIn, 0.0f, 0.0f, partialTicks);
        if (entityIn.multiplayerClientSuggestionProvider > 0) {
            float f2 = (float)entityIn.multiplayerClientSuggestionProvider / 200.0f;
            D_4792_h ivertexbuilder = bufferIn.getBuffer(o_2576_A.n_1700_B(v_4262_N, f2));
            this.Q_4569_t.render(matrixStackIn, ivertexbuilder, packedLightIn, Z_3224_L.n_1700_B, 1.0f, 1.0f, 1.0f, 1.0f);
            D_4792_h ivertexbuilder1 = bufferIn.getBuffer(u_2550_I);
            this.Q_4569_t.render(matrixStackIn, ivertexbuilder1, packedLightIn, Z_3224_L.n_1700_B(0.0f, flag), 1.0f, 1.0f, 1.0f, 1.0f);
        } else {
            D_4792_h ivertexbuilder3 = bufferIn.getBuffer(s_956_w);
            this.Q_4569_t.render(matrixStackIn, ivertexbuilder3, packedLightIn, Z_3224_L.n_1700_B(0.0f, flag), 1.0f, 1.0f, 1.0f, 1.0f);
        }
        D_4792_h ivertexbuilder4 = bufferIn.getBuffer(M_588_G);
        if (Config.isShaders()) {
            Shaders.beginSpiderEyes();
        }
        Config.getRenderGlobal().v_4262_N = true;
        this.Q_4569_t.render(matrixStackIn, ivertexbuilder4, packedLightIn, Z_3224_L.n_1700_B, 1.0f, 1.0f, 1.0f, 1.0f);
        Config.getRenderGlobal().v_4262_N = false;
        if (Config.isShaders()) {
            Shaders.endSpiderEyes();
        }
        if (entityIn.multiplayerClientSuggestionProvider > 0) {
            float f5 = ((float)entityIn.multiplayerClientSuggestionProvider + partialTicks) / 200.0f;
            float f7 = Math.min(f5 > 0.8f ? (f5 - 0.8f) / 0.2f : 0.0f, 1.0f);
            Random random = new Random(432L);
            D_4792_h ivertexbuilder2 = bufferIn.getBuffer(o_2576_A.Y_259_p());
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(0.0, -1.0, -2.0);
            int i = 0;
            while ((float)i < (f5 + f5 * f5) / 2.0f * 60.0f) {
                matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(random.nextFloat() * 360.0f));
                matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(random.nextFloat() * 360.0f));
                matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(random.nextFloat() * 360.0f));
                matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(random.nextFloat() * 360.0f));
                matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(random.nextFloat() * 360.0f));
                matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(random.nextFloat() * 360.0f + f5 * 90.0f));
                float f3 = random.nextFloat() * 20.0f + 5.0f + f7 * 10.0f;
                float f4 = random.nextFloat() * 2.0f + 1.0f + f7 * 2.0f;
                D_1098_v matrix4f = matrixStackIn.R_4764_Y().n_1700_B();
                int j = (int)(255.0f * (1.0f - f7));
                j_4563_n.n_1700_B(ivertexbuilder2, matrix4f, j);
                j_4563_n.n_1700_B(ivertexbuilder2, matrix4f, f3, f4);
                j_4563_n.J_1907_R(ivertexbuilder2, matrix4f, f3, f4);
                j_4563_n.n_1700_B(ivertexbuilder2, matrix4f, j);
                j_4563_n.J_1907_R(ivertexbuilder2, matrix4f, f3, f4);
                j_4563_n.R_4764_Y(ivertexbuilder2, matrix4f, f3, f4);
                j_4563_n.n_1700_B(ivertexbuilder2, matrix4f, j);
                j_4563_n.R_4764_Y(ivertexbuilder2, matrix4f, f3, f4);
                j_4563_n.n_1700_B(ivertexbuilder2, matrix4f, f3, f4);
                ++i;
            }
            matrixStackIn.J_1907_R();
        }
        matrixStackIn.J_1907_R();
        if (entityIn.Y_601_j != null) {
            matrixStackIn.n_1700_B();
            float f6 = (float)(entityIn.Y_601_j.O_3598_v() - u_530_F.G_564_y((double)partialTicks, entityIn.r_715_M, entityIn.O_3598_v()));
            float f8 = (float)(entityIn.Y_601_j.X_2960_b() - u_530_F.G_564_y((double)partialTicks, entityIn.A_1038_p, entityIn.X_2960_b()));
            float f9 = (float)(entityIn.Y_601_j.l_2647_k() - u_530_F.G_564_y((double)partialTicks, entityIn.i_1637_u, entityIn.l_2647_k()));
            j_4563_n.n_1700_B(f6, f8 + EndCrystalRenderer.n_1700_B(entityIn.Y_601_j, partialTicks), f9, partialTicks, entityIn.RealmsWorldResetDto, matrixStackIn, bufferIn, packedLightIn);
            matrixStackIn.J_1907_R();
        }
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    private static void n_1700_B(D_4792_h p_229061_0_, D_1098_v p_229061_1_, int p_229061_2_) {
        p_229061_0_.n_1700_B(p_229061_1_, 0.0f, 0.0f, 0.0f).color(255, 255, 255, p_229061_2_).endVertex();
        p_229061_0_.n_1700_B(p_229061_1_, 0.0f, 0.0f, 0.0f).color(255, 255, 255, p_229061_2_).endVertex();
    }

    private static void n_1700_B(D_4792_h p_229060_0_, D_1098_v p_229060_1_, float p_229060_2_, float p_229060_3_) {
        p_229060_0_.n_1700_B(p_229060_1_, -h_1847_R * p_229060_3_, p_229060_2_, -0.5f * p_229060_3_).color(255, 0, 255, 0).endVertex();
    }

    private static void J_1907_R(D_4792_h p_229062_0_, D_1098_v p_229062_1_, float p_229062_2_, float p_229062_3_) {
        p_229062_0_.n_1700_B(p_229062_1_, h_1847_R * p_229062_3_, p_229062_2_, -0.5f * p_229062_3_).color(255, 0, 255, 0).endVertex();
    }

    private static void R_4764_Y(D_4792_h p_229063_0_, D_1098_v p_229063_1_, float p_229063_2_, float p_229063_3_) {
        p_229063_0_.n_1700_B(p_229063_1_, 0.0f, p_229063_2_, 1.0f * p_229063_3_).color(255, 0, 255, 0).endVertex();
    }

    public static void n_1700_B(float p_229059_0_, float p_229059_1_, float p_229059_2_, float p_229059_3_, int p_229059_4_, g_221_o p_229059_5_, o_3091_w p_229059_6_, int p_229059_7_) {
        float f = u_530_F.R_4764_Y(p_229059_0_ * p_229059_0_ + p_229059_2_ * p_229059_2_);
        float f1 = u_530_F.R_4764_Y(p_229059_0_ * p_229059_0_ + p_229059_1_ * p_229059_1_ + p_229059_2_ * p_229059_2_);
        p_229059_5_.n_1700_B();
        p_229059_5_.n_1700_B(0.0, 2.0, 0.0);
        p_229059_5_.n_1700_B(M_1336_P.G_564_y.J_1907_R((float)(-Math.atan2(p_229059_2_, p_229059_0_)) - 1.5707964f));
        p_229059_5_.n_1700_B(M_1336_P.J_1907_R.J_1907_R((float)(-Math.atan2(f, p_229059_1_)) - 1.5707964f));
        D_4792_h ivertexbuilder = p_229059_6_.getBuffer(P_4830_p);
        float f2 = 0.0f - ((float)p_229059_4_ + p_229059_3_) * 0.01f;
        float f3 = u_530_F.R_4764_Y(p_229059_0_ * p_229059_0_ + p_229059_1_ * p_229059_1_ + p_229059_2_ * p_229059_2_) / 32.0f - ((float)p_229059_4_ + p_229059_3_) * 0.01f;
        int i = 8;
        float f4 = 0.0f;
        float f5 = 0.75f;
        float f6 = 0.0f;
        g_221_o.n_1700_B matrixstack$entry = p_229059_5_.R_4764_Y();
        D_1098_v matrix4f = matrixstack$entry.n_1700_B();
        o_1290_k matrix3f = matrixstack$entry.J_1907_R();
        for (int j = 1; j <= 8; ++j) {
            float f7 = u_530_F.n_1700_B((float)j * ((float)Math.PI * 2) / 8.0f) * 0.75f;
            float f8 = u_530_F.J_1907_R((float)j * ((float)Math.PI * 2) / 8.0f) * 0.75f;
            float f9 = (float)j / 8.0f;
            ivertexbuilder.n_1700_B(matrix4f, f4 * 0.2f, f5 * 0.2f, 0.0f).color(0, 0, 0, 255).tex(f6, f2).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(p_229059_7_).n_1700_B(matrix3f, 0.0f, -1.0f, 0.0f).endVertex();
            ivertexbuilder.n_1700_B(matrix4f, f4, f5, f1).color(255, 255, 255, 255).tex(f6, f3).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(p_229059_7_).n_1700_B(matrix3f, 0.0f, -1.0f, 0.0f).endVertex();
            ivertexbuilder.n_1700_B(matrix4f, f7, f8, f1).color(255, 255, 255, 255).tex(f9, f3).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(p_229059_7_).n_1700_B(matrix3f, 0.0f, -1.0f, 0.0f).endVertex();
            ivertexbuilder.n_1700_B(matrix4f, f7 * 0.2f, f8 * 0.2f, 0.0f).color(0, 0, 0, 255).tex(f9, f2).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(p_229059_7_).n_1700_B(matrix3f, 0.0f, -1.0f, 0.0f).endVertex();
            f4 = f7;
            f5 = f8;
            f6 = f9;
        }
        p_229059_5_.J_1907_R();
    }

    @Override
    public g_2336_b n_1700_B(b_2971_b entity) {
        return w_1484_f;
    }

    public static class n_1700_B
    extends EntityModel<b_2971_b> {
        private final e_4189_z n_1700_B;
        private final e_4189_z J_1907_R;
        private final e_4189_z R_4764_Y;
        private final e_4189_z G_564_y;
        private e_4189_z P_1922_E;
        private e_4189_z u_1723_Y;
        private e_4189_z v_4262_N;
        private e_4189_z w_1484_f;
        private e_4189_z t_148_a;
        private e_4189_z s_956_w;
        private e_4189_z u_2550_I;
        private e_4189_z M_588_G;
        private e_4189_z P_4830_p;
        private e_4189_z t_1786_h;
        private e_4189_z multiplayerClientSuggestionProvider;
        private e_4189_z w_1457_N;
        private e_4189_z Y_601_j;
        private e_4189_z Y_259_p;
        private e_4189_z Q_2552_b;
        private e_4189_z C_2741_M;
        @Nullable
        private b_2971_b k_2293_S;
        private float q_2307_F;

        public n_1700_B() {
            this.textureWidth = 256;
            this.textureHeight = 256;
            float f = -16.0f;
            this.n_1700_B = new e_4189_z(this);
            this.n_1700_B.n_1700_B("upperlip", -6.0f, -1.0f, -24.0f, 12, 5, 16, 0.0f, 176, 44);
            this.n_1700_B.n_1700_B("upperhead", -8.0f, -8.0f, -10.0f, 16, 16, 16, 0.0f, 112, 30);
            this.n_1700_B.t_148_a = true;
            this.n_1700_B.n_1700_B("scale", -5.0f, -12.0f, -4.0f, 2, 4, 6, 0.0f, 0, 0);
            this.n_1700_B.n_1700_B("nostril", -5.0f, -3.0f, -22.0f, 2, 2, 4, 0.0f, 112, 0);
            this.n_1700_B.t_148_a = false;
            this.n_1700_B.n_1700_B("scale", 3.0f, -12.0f, -4.0f, 2, 4, 6, 0.0f, 0, 0);
            this.n_1700_B.n_1700_B("nostril", 3.0f, -3.0f, -22.0f, 2, 2, 4, 0.0f, 112, 0);
            this.R_4764_Y = new e_4189_z(this);
            this.R_4764_Y.n_1700_B(0.0f, 4.0f, -8.0f);
            this.R_4764_Y.n_1700_B("jaw", -6.0f, 0.0f, -16.0f, 12, 4, 16, 0.0f, 176, 65);
            this.n_1700_B.J_1907_R(this.R_4764_Y);
            this.J_1907_R = new e_4189_z(this);
            this.J_1907_R.n_1700_B("box", -5.0f, -5.0f, -5.0f, 10, 10, 10, 0.0f, 192, 104);
            this.J_1907_R.n_1700_B("scale", -1.0f, -9.0f, -3.0f, 2, 4, 6, 0.0f, 48, 0);
            this.G_564_y = new e_4189_z(this);
            this.G_564_y.n_1700_B(0.0f, 4.0f, 8.0f);
            this.G_564_y.n_1700_B("body", -12.0f, 0.0f, -16.0f, 24, 24, 64, 0.0f, 0, 0);
            this.G_564_y.n_1700_B("scale", -1.0f, -6.0f, -10.0f, 2, 6, 12, 0.0f, 220, 53);
            this.G_564_y.n_1700_B("scale", -1.0f, -6.0f, 10.0f, 2, 6, 12, 0.0f, 220, 53);
            this.G_564_y.n_1700_B("scale", -1.0f, -6.0f, 30.0f, 2, 6, 12, 0.0f, 220, 53);
            this.P_1922_E = new e_4189_z(this);
            this.P_1922_E.t_148_a = true;
            this.P_1922_E.n_1700_B(12.0f, 5.0f, 2.0f);
            this.P_1922_E.n_1700_B("bone", 0.0f, -4.0f, -4.0f, 56, 8, 8, 0.0f, 112, 88);
            this.P_1922_E.n_1700_B("skin", 0.0f, 0.0f, 2.0f, 56, 0, 56, 0.0f, -56, 88);
            this.u_1723_Y = new e_4189_z(this);
            this.u_1723_Y.t_148_a = true;
            this.u_1723_Y.n_1700_B(56.0f, 0.0f, 0.0f);
            this.u_1723_Y.n_1700_B("bone", 0.0f, -2.0f, -2.0f, 56, 4, 4, 0.0f, 112, 136);
            this.u_1723_Y.n_1700_B("skin", 0.0f, 0.0f, 2.0f, 56, 0, 56, 0.0f, -56, 144);
            this.P_1922_E.J_1907_R(this.u_1723_Y);
            this.v_4262_N = new e_4189_z(this);
            this.v_4262_N.n_1700_B(12.0f, 20.0f, 2.0f);
            this.v_4262_N.n_1700_B("main", -4.0f, -4.0f, -4.0f, 8, 24, 8, 0.0f, 112, 104);
            this.w_1484_f = new e_4189_z(this);
            this.w_1484_f.n_1700_B(0.0f, 20.0f, -1.0f);
            this.w_1484_f.n_1700_B("main", -3.0f, -1.0f, -3.0f, 6, 24, 6, 0.0f, 226, 138);
            this.v_4262_N.J_1907_R(this.w_1484_f);
            this.t_148_a = new e_4189_z(this);
            this.t_148_a.n_1700_B(0.0f, 23.0f, 0.0f);
            this.t_148_a.n_1700_B("main", -4.0f, 0.0f, -12.0f, 8, 4, 16, 0.0f, 144, 104);
            this.w_1484_f.J_1907_R(this.t_148_a);
            this.s_956_w = new e_4189_z(this);
            this.s_956_w.n_1700_B(16.0f, 16.0f, 42.0f);
            this.s_956_w.n_1700_B("main", -8.0f, -4.0f, -8.0f, 16, 32, 16, 0.0f, 0, 0);
            this.u_2550_I = new e_4189_z(this);
            this.u_2550_I.n_1700_B(0.0f, 32.0f, -4.0f);
            this.u_2550_I.n_1700_B("main", -6.0f, -2.0f, 0.0f, 12, 32, 12, 0.0f, 196, 0);
            this.s_956_w.J_1907_R(this.u_2550_I);
            this.M_588_G = new e_4189_z(this);
            this.M_588_G.n_1700_B(0.0f, 31.0f, 4.0f);
            this.M_588_G.n_1700_B("main", -9.0f, 0.0f, -20.0f, 18, 6, 24, 0.0f, 112, 0);
            this.u_2550_I.J_1907_R(this.M_588_G);
            this.P_4830_p = new e_4189_z(this);
            this.P_4830_p.n_1700_B(-12.0f, 5.0f, 2.0f);
            this.P_4830_p.n_1700_B("bone", -56.0f, -4.0f, -4.0f, 56, 8, 8, 0.0f, 112, 88);
            this.P_4830_p.n_1700_B("skin", -56.0f, 0.0f, 2.0f, 56, 0, 56, 0.0f, -56, 88);
            this.t_1786_h = new e_4189_z(this);
            this.t_1786_h.n_1700_B(-56.0f, 0.0f, 0.0f);
            this.t_1786_h.n_1700_B("bone", -56.0f, -2.0f, -2.0f, 56, 4, 4, 0.0f, 112, 136);
            this.t_1786_h.n_1700_B("skin", -56.0f, 0.0f, 2.0f, 56, 0, 56, 0.0f, -56, 144);
            this.P_4830_p.J_1907_R(this.t_1786_h);
            this.multiplayerClientSuggestionProvider = new e_4189_z(this);
            this.multiplayerClientSuggestionProvider.n_1700_B(-12.0f, 20.0f, 2.0f);
            this.multiplayerClientSuggestionProvider.n_1700_B("main", -4.0f, -4.0f, -4.0f, 8, 24, 8, 0.0f, 112, 104);
            this.w_1457_N = new e_4189_z(this);
            this.w_1457_N.n_1700_B(0.0f, 20.0f, -1.0f);
            this.w_1457_N.n_1700_B("main", -3.0f, -1.0f, -3.0f, 6, 24, 6, 0.0f, 226, 138);
            this.multiplayerClientSuggestionProvider.J_1907_R(this.w_1457_N);
            this.Y_601_j = new e_4189_z(this);
            this.Y_601_j.n_1700_B(0.0f, 23.0f, 0.0f);
            this.Y_601_j.n_1700_B("main", -4.0f, 0.0f, -12.0f, 8, 4, 16, 0.0f, 144, 104);
            this.w_1457_N.J_1907_R(this.Y_601_j);
            this.Y_259_p = new e_4189_z(this);
            this.Y_259_p.n_1700_B(-16.0f, 16.0f, 42.0f);
            this.Y_259_p.n_1700_B("main", -8.0f, -4.0f, -8.0f, 16, 32, 16, 0.0f, 0, 0);
            this.Q_2552_b = new e_4189_z(this);
            this.Q_2552_b.n_1700_B(0.0f, 32.0f, -4.0f);
            this.Q_2552_b.n_1700_B("main", -6.0f, -2.0f, 0.0f, 12, 32, 12, 0.0f, 196, 0);
            this.Y_259_p.J_1907_R(this.Q_2552_b);
            this.C_2741_M = new e_4189_z(this);
            this.C_2741_M.n_1700_B(0.0f, 31.0f, 4.0f);
            this.C_2741_M.n_1700_B("main", -9.0f, 0.0f, -20.0f, 18, 6, 24, 0.0f, 112, 0);
            this.Q_2552_b.J_1907_R(this.C_2741_M);
        }

        @Override
        public void n_1700_B(b_2971_b entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
            this.k_2293_S = entityIn;
            this.q_2307_F = partialTick;
        }

        @Override
        public void n_1700_B(b_2971_b entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        }

        @Override
        public void render(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
            matrixStackIn.n_1700_B();
            float f = u_530_F.v_4262_N(this.q_2307_F, this.k_2293_S.Q_4569_t, this.k_2293_S.M_182_A);
            this.R_4764_Y.u_1723_Y = (float)(Math.sin(f * ((float)Math.PI * 2)) + 1.0) * 0.2f;
            float f1 = (float)(Math.sin(f * ((float)Math.PI * 2) - 1.0f) + 1.0);
            f1 = (f1 * f1 + f1 * 2.0f) * 0.05f;
            matrixStackIn.n_1700_B(0.0, (double)(f1 - 2.0f), -3.0);
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(f1 * 2.0f));
            float f2 = 0.0f;
            float f3 = 20.0f;
            float f4 = -12.0f;
            float f5 = 1.5f;
            double[] adouble = this.k_2293_S.n_1700_B(6, this.q_2307_F);
            float f6 = u_530_F.u_2550_I(this.k_2293_S.n_1700_B(5, this.q_2307_F)[0] - this.k_2293_S.n_1700_B(10, this.q_2307_F)[0]);
            float f7 = u_530_F.u_2550_I(this.k_2293_S.n_1700_B(5, this.q_2307_F)[0] + (double)(f6 / 2.0f));
            float f8 = f * ((float)Math.PI * 2);
            for (int i = 0; i < 5; ++i) {
                double[] adouble1 = this.k_2293_S.n_1700_B(5 - i, this.q_2307_F);
                float f9 = (float)Math.cos((float)i * 0.45f + f8) * 0.15f;
                this.J_1907_R.v_4262_N = u_530_F.u_2550_I(adouble1[0] - adouble[0]) * ((float)Math.PI / 180) * 1.5f;
                this.J_1907_R.u_1723_Y = f9 + this.k_2293_S.n_1700_B(i, adouble, adouble1) * ((float)Math.PI / 180) * 1.5f * 5.0f;
                this.J_1907_R.w_1484_f = -u_530_F.u_2550_I(adouble1[0] - (double)f7) * ((float)Math.PI / 180) * 1.5f;
                this.J_1907_R.G_564_y = f3;
                this.J_1907_R.P_1922_E = f4;
                this.J_1907_R.R_4764_Y = f2;
                f3 = (float)((double)f3 + Math.sin(this.J_1907_R.u_1723_Y) * 10.0);
                f4 = (float)((double)f4 - Math.cos(this.J_1907_R.v_4262_N) * Math.cos(this.J_1907_R.u_1723_Y) * 10.0);
                f2 = (float)((double)f2 - Math.sin(this.J_1907_R.v_4262_N) * Math.cos(this.J_1907_R.u_1723_Y) * 10.0);
                this.J_1907_R.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
            }
            this.n_1700_B.G_564_y = f3;
            this.n_1700_B.P_1922_E = f4;
            this.n_1700_B.R_4764_Y = f2;
            double[] adouble2 = this.k_2293_S.n_1700_B(0, this.q_2307_F);
            this.n_1700_B.v_4262_N = u_530_F.u_2550_I(adouble2[0] - adouble[0]) * ((float)Math.PI / 180);
            this.n_1700_B.u_1723_Y = u_530_F.u_2550_I((double)this.k_2293_S.n_1700_B(6, adouble, adouble2)) * ((float)Math.PI / 180) * 1.5f * 5.0f;
            this.n_1700_B.w_1484_f = -u_530_F.u_2550_I(adouble2[0] - (double)f7) * ((float)Math.PI / 180);
            this.n_1700_B.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(0.0, 1.0, 0.0);
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(-f6 * 1.5f));
            matrixStackIn.n_1700_B(0.0, -1.0, 0.0);
            this.G_564_y.w_1484_f = 0.0f;
            this.G_564_y.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
            float f10 = f * ((float)Math.PI * 2);
            this.P_1922_E.u_1723_Y = 0.125f - (float)Math.cos(f10) * 0.2f;
            this.P_1922_E.v_4262_N = -0.25f;
            this.P_1922_E.w_1484_f = -((float)(Math.sin(f10) + 0.125)) * 0.8f;
            this.u_1723_Y.w_1484_f = (float)(Math.sin(f10 + 2.0f) + 0.5) * 0.75f;
            this.P_4830_p.u_1723_Y = this.P_1922_E.u_1723_Y;
            this.P_4830_p.v_4262_N = -this.P_1922_E.v_4262_N;
            this.P_4830_p.w_1484_f = -this.P_1922_E.w_1484_f;
            this.t_1786_h.w_1484_f = -this.u_1723_Y.w_1484_f;
            this.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, f1, this.P_1922_E, this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G);
            this.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, f1, this.P_4830_p, this.multiplayerClientSuggestionProvider, this.w_1457_N, this.Y_601_j, this.Y_259_p, this.Q_2552_b, this.C_2741_M);
            matrixStackIn.J_1907_R();
            float f11 = -((float)Math.sin(f * ((float)Math.PI * 2))) * 0.0f;
            f8 = f * ((float)Math.PI * 2);
            f3 = 10.0f;
            f4 = 60.0f;
            f2 = 0.0f;
            adouble = this.k_2293_S.n_1700_B(11, this.q_2307_F);
            for (int j = 0; j < 12; ++j) {
                adouble2 = this.k_2293_S.n_1700_B(12 + j, this.q_2307_F);
                f11 = (float)((double)f11 + Math.sin((float)j * 0.45f + f8) * (double)0.05f);
                this.J_1907_R.v_4262_N = (u_530_F.u_2550_I(adouble2[0] - adouble[0]) * 1.5f + 180.0f) * ((float)Math.PI / 180);
                this.J_1907_R.u_1723_Y = f11 + (float)(adouble2[1] - adouble[1]) * ((float)Math.PI / 180) * 1.5f * 5.0f;
                this.J_1907_R.w_1484_f = u_530_F.u_2550_I(adouble2[0] - (double)f7) * ((float)Math.PI / 180) * 1.5f;
                this.J_1907_R.G_564_y = f3;
                this.J_1907_R.P_1922_E = f4;
                this.J_1907_R.R_4764_Y = f2;
                f3 = (float)((double)f3 + Math.sin(this.J_1907_R.u_1723_Y) * 10.0);
                f4 = (float)((double)f4 - Math.cos(this.J_1907_R.v_4262_N) * Math.cos(this.J_1907_R.u_1723_Y) * 10.0);
                f2 = (float)((double)f2 - Math.sin(this.J_1907_R.v_4262_N) * Math.cos(this.J_1907_R.u_1723_Y) * 10.0);
                this.J_1907_R.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
            }
            matrixStackIn.J_1907_R();
        }

        private void n_1700_B(g_221_o p_229081_1_, D_4792_h p_229081_2_, int p_229081_3_, int p_229081_4_, float p_229081_5_, e_4189_z p_229081_6_, e_4189_z p_229081_7_, e_4189_z p_229081_8_, e_4189_z p_229081_9_, e_4189_z p_229081_10_, e_4189_z p_229081_11_, e_4189_z p_229081_12_) {
            p_229081_10_.u_1723_Y = 1.0f + p_229081_5_ * 0.1f;
            p_229081_11_.u_1723_Y = 0.5f + p_229081_5_ * 0.1f;
            p_229081_12_.u_1723_Y = 0.75f + p_229081_5_ * 0.1f;
            p_229081_7_.u_1723_Y = 1.3f + p_229081_5_ * 0.1f;
            p_229081_8_.u_1723_Y = -0.5f - p_229081_5_ * 0.1f;
            p_229081_9_.u_1723_Y = 0.75f + p_229081_5_ * 0.1f;
            p_229081_6_.n_1700_B(p_229081_1_, p_229081_2_, p_229081_3_, p_229081_4_);
            p_229081_7_.n_1700_B(p_229081_1_, p_229081_2_, p_229081_3_, p_229081_4_);
            p_229081_10_.n_1700_B(p_229081_1_, p_229081_2_, p_229081_3_, p_229081_4_);
        }
    }
}


