/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.D_4792_h;
import lightning.product.ListModel;
import lightning.product.R_1299_M;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.u_530_F;

public class P_2855_e
extends ListModel<R_1299_M> {
    private final e_4189_z n_1700_B;
    private final e_4189_z J_1907_R;
    private final e_4189_z R_4764_Y;
    private final e_4189_z G_564_y;
    private final e_4189_z P_1922_E;
    private final e_4189_z u_1723_Y;
    private final e_4189_z v_4262_N;
    private final e_4189_z w_1484_f;
    private final e_4189_z t_148_a;
    private final e_4189_z s_956_w;
    private final e_4189_z u_2550_I;

    public P_2855_e() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.n_1700_B = new e_4189_z(this, 2, 8);
        this.n_1700_B.n_1700_B(-1.5f, 0.0f, -1.5f, 3.0f, 6.0f, 3.0f);
        this.n_1700_B.n_1700_B(0.0f, 16.5f, -3.0f);
        this.J_1907_R = new e_4189_z(this, 22, 1);
        this.J_1907_R.n_1700_B(-1.5f, -1.0f, -1.0f, 3.0f, 4.0f, 1.0f);
        this.J_1907_R.n_1700_B(0.0f, 21.07f, 1.16f);
        this.R_4764_Y = new e_4189_z(this, 19, 8);
        this.R_4764_Y.n_1700_B(-0.5f, 0.0f, -1.5f, 1.0f, 5.0f, 3.0f);
        this.R_4764_Y.n_1700_B(1.5f, 16.94f, -2.76f);
        this.G_564_y = new e_4189_z(this, 19, 8);
        this.G_564_y.n_1700_B(-0.5f, 0.0f, -1.5f, 1.0f, 5.0f, 3.0f);
        this.G_564_y.n_1700_B(-1.5f, 16.94f, -2.76f);
        this.P_1922_E = new e_4189_z(this, 2, 2);
        this.P_1922_E.n_1700_B(-1.0f, -1.5f, -1.0f, 2.0f, 3.0f, 2.0f);
        this.P_1922_E.n_1700_B(0.0f, 15.69f, -2.76f);
        this.u_1723_Y = new e_4189_z(this, 10, 0);
        this.u_1723_Y.n_1700_B(-1.0f, -0.5f, -2.0f, 2.0f, 1.0f, 4.0f);
        this.u_1723_Y.n_1700_B(0.0f, -2.0f, -1.0f);
        this.P_1922_E.J_1907_R(this.u_1723_Y);
        this.v_4262_N = new e_4189_z(this, 11, 7);
        this.v_4262_N.n_1700_B(-0.5f, -1.0f, -0.5f, 1.0f, 2.0f, 1.0f);
        this.v_4262_N.n_1700_B(0.0f, -0.5f, -1.5f);
        this.P_1922_E.J_1907_R(this.v_4262_N);
        this.w_1484_f = new e_4189_z(this, 16, 7);
        this.w_1484_f.n_1700_B(-0.5f, 0.0f, -0.5f, 1.0f, 2.0f, 1.0f);
        this.w_1484_f.n_1700_B(0.0f, -1.75f, -2.45f);
        this.P_1922_E.J_1907_R(this.w_1484_f);
        this.t_148_a = new e_4189_z(this, 2, 18);
        this.t_148_a.n_1700_B(0.0f, -4.0f, -2.0f, 0.0f, 5.0f, 4.0f);
        this.t_148_a.n_1700_B(0.0f, -2.15f, 0.15f);
        this.P_1922_E.J_1907_R(this.t_148_a);
        this.s_956_w = new e_4189_z(this, 14, 18);
        this.s_956_w.n_1700_B(-0.5f, 0.0f, -0.5f, 1.0f, 2.0f, 1.0f);
        this.s_956_w.n_1700_B(1.0f, 22.0f, -1.05f);
        this.u_2550_I = new e_4189_z(this, 14, 18);
        this.u_2550_I.n_1700_B(-0.5f, 0.0f, -0.5f, 1.0f, 2.0f, 1.0f);
        this.u_2550_I.n_1700_B(-1.0f, 22.0f, -1.05f);
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.n_1700_B, (Object)this.R_4764_Y, (Object)this.G_564_y, (Object)this.J_1907_R, (Object)this.P_1922_E, (Object)this.s_956_w, (Object)this.u_2550_I);
    }

    @Override
    public void n_1700_B(R_1299_M entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.n_1700_B(P_2855_e.n_1700_B(entityIn), entityIn.RealmsWorldResetDto, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    }

    @Override
    public void n_1700_B(R_1299_M entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        this.n_1700_B(P_2855_e.n_1700_B(entityIn));
    }

    public void n_1700_B(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float p_228284_5_, float p_228284_6_, float p_228284_7_, float p_228284_8_, int p_228284_9_) {
        this.n_1700_B(lightning.product.P_2855_e$n_1700_B.P_1922_E);
        this.n_1700_B(lightning.product.P_2855_e$n_1700_B.P_1922_E, p_228284_9_, p_228284_5_, p_228284_6_, 0.0f, p_228284_7_, p_228284_8_);
        this.n_1700_B().forEach(p_228285_4_ -> p_228285_4_.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn));
    }

    private void n_1700_B(n_1700_B p_217162_1_, int p_217162_2_, float p_217162_3_, float p_217162_4_, float p_217162_5_, float p_217162_6_, float p_217162_7_) {
        this.P_1922_E.u_1723_Y = p_217162_7_ * ((float)Math.PI / 180);
        this.P_1922_E.v_4262_N = p_217162_6_ * ((float)Math.PI / 180);
        this.P_1922_E.w_1484_f = 0.0f;
        this.P_1922_E.R_4764_Y = 0.0f;
        this.n_1700_B.R_4764_Y = 0.0f;
        this.J_1907_R.R_4764_Y = 0.0f;
        this.G_564_y.R_4764_Y = -1.5f;
        this.R_4764_Y.R_4764_Y = 1.5f;
        switch (p_217162_1_.ordinal()) {
            case 2: {
                break;
            }
            case 3: {
                float f = u_530_F.J_1907_R((float)p_217162_2_);
                float f1 = u_530_F.n_1700_B((float)p_217162_2_);
                this.P_1922_E.R_4764_Y = f;
                this.P_1922_E.G_564_y = 15.69f + f1;
                this.P_1922_E.u_1723_Y = 0.0f;
                this.P_1922_E.v_4262_N = 0.0f;
                this.P_1922_E.w_1484_f = u_530_F.n_1700_B((float)p_217162_2_) * 0.4f;
                this.n_1700_B.R_4764_Y = f;
                this.n_1700_B.G_564_y = 16.5f + f1;
                this.R_4764_Y.w_1484_f = -0.0873f - p_217162_5_;
                this.R_4764_Y.R_4764_Y = 1.5f + f;
                this.R_4764_Y.G_564_y = 16.94f + f1;
                this.G_564_y.w_1484_f = 0.0873f + p_217162_5_;
                this.G_564_y.R_4764_Y = -1.5f + f;
                this.G_564_y.G_564_y = 16.94f + f1;
                this.J_1907_R.R_4764_Y = f;
                this.J_1907_R.G_564_y = 21.07f + f1;
                break;
            }
            case 1: {
                this.s_956_w.u_1723_Y += u_530_F.J_1907_R(p_217162_3_ * 0.6662f) * 1.4f * p_217162_4_;
                this.u_2550_I.u_1723_Y += u_530_F.J_1907_R(p_217162_3_ * 0.6662f + (float)Math.PI) * 1.4f * p_217162_4_;
            }
            default: {
                float f2 = p_217162_5_ * 0.3f;
                this.P_1922_E.G_564_y = 15.69f + f2;
                this.J_1907_R.u_1723_Y = 1.015f + u_530_F.J_1907_R(p_217162_3_ * 0.6662f) * 0.3f * p_217162_4_;
                this.J_1907_R.G_564_y = 21.07f + f2;
                this.n_1700_B.G_564_y = 16.5f + f2;
                this.R_4764_Y.w_1484_f = -0.0873f - p_217162_5_;
                this.R_4764_Y.G_564_y = 16.94f + f2;
                this.G_564_y.w_1484_f = 0.0873f + p_217162_5_;
                this.G_564_y.G_564_y = 16.94f + f2;
                this.s_956_w.G_564_y = 22.0f + f2;
                this.u_2550_I.G_564_y = 22.0f + f2;
            }
        }
    }

    private void n_1700_B(n_1700_B p_217160_1_) {
        this.t_148_a.u_1723_Y = -0.2214f;
        this.n_1700_B.u_1723_Y = 0.4937f;
        this.R_4764_Y.u_1723_Y = -0.69813174f;
        this.R_4764_Y.v_4262_N = (float)(-Math.PI);
        this.G_564_y.u_1723_Y = -0.69813174f;
        this.G_564_y.v_4262_N = (float)(-Math.PI);
        this.s_956_w.u_1723_Y = -0.0299f;
        this.u_2550_I.u_1723_Y = -0.0299f;
        this.s_956_w.G_564_y = 22.0f;
        this.u_2550_I.G_564_y = 22.0f;
        this.s_956_w.w_1484_f = 0.0f;
        this.u_2550_I.w_1484_f = 0.0f;
        switch (p_217160_1_.ordinal()) {
            case 2: {
                float f = 1.9f;
                this.P_1922_E.G_564_y = 17.59f;
                this.J_1907_R.u_1723_Y = 1.5388988f;
                this.J_1907_R.G_564_y = 22.97f;
                this.n_1700_B.G_564_y = 18.4f;
                this.R_4764_Y.w_1484_f = -0.0873f;
                this.R_4764_Y.G_564_y = 18.84f;
                this.G_564_y.w_1484_f = 0.0873f;
                this.G_564_y.G_564_y = 18.84f;
                this.s_956_w.G_564_y += 1.0f;
                this.u_2550_I.G_564_y += 1.0f;
                this.s_956_w.u_1723_Y += 1.0f;
                this.u_2550_I.u_1723_Y += 1.0f;
                break;
            }
            case 3: {
                this.s_956_w.w_1484_f = -0.34906584f;
                this.u_2550_I.w_1484_f = 0.34906584f;
            }
            default: {
                break;
            }
            case 0: {
                this.s_956_w.u_1723_Y += 0.69813174f;
                this.u_2550_I.u_1723_Y += 0.69813174f;
            }
        }
    }

    private static n_1700_B n_1700_B(R_1299_M p_217158_0_) {
        if (p_217158_0_.h_1640_b()) {
            return lightning.product.P_2855_e$n_1700_B.G_564_y;
        }
        if (p_217158_0_.z_2372_L()) {
            return lightning.product.P_2855_e$n_1700_B.R_4764_Y;
        }
        return p_217158_0_.y_2447_C() ? lightning.product.P_2855_e$n_1700_B.n_1700_B : lightning.product.P_2855_e$n_1700_B.J_1907_R;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] u_1723_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])u_1723_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E};
        }

        static {
            u_1723_Y = lightning.product.P_2855_e$n_1700_B.n_1700_B();
        }
    }
}


