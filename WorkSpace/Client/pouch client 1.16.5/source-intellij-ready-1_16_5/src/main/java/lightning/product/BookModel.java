/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import java.util.List;
import lightning.product.D_4792_h;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.o_2576_A;
import lightning.product.u_530_F;
import lightning.product.v_3569_v;

public class BookModel
extends v_3569_v {
    private final e_4189_z n_1700_B = new e_4189_z(64, 32, 0, 0).n_1700_B(-6.0f, -5.0f, -0.005f, 6.0f, 10.0f, 0.005f);
    private final e_4189_z J_1907_R = new e_4189_z(64, 32, 16, 0).n_1700_B(0.0f, -5.0f, -0.005f, 6.0f, 10.0f, 0.005f);
    private final e_4189_z R_4764_Y;
    private final e_4189_z G_564_y;
    private final e_4189_z P_1922_E;
    private final e_4189_z u_1723_Y;
    private final e_4189_z v_4262_N = new e_4189_z(64, 32, 12, 0).n_1700_B(-1.0f, -5.0f, 0.0f, 2.0f, 10.0f, 0.005f);
    private final List<e_4189_z> w_1484_f;

    public BookModel() {
        super(o_2576_A::J_1907_R);
        this.R_4764_Y = new e_4189_z(64, 32, 0, 10).n_1700_B(0.0f, -4.0f, -0.99f, 5.0f, 8.0f, 1.0f);
        this.G_564_y = new e_4189_z(64, 32, 12, 10).n_1700_B(0.0f, -4.0f, -0.01f, 5.0f, 8.0f, 1.0f);
        this.P_1922_E = new e_4189_z(64, 32, 24, 10).n_1700_B(0.0f, -4.0f, 0.0f, 5.0f, 8.0f, 0.005f);
        this.u_1723_Y = new e_4189_z(64, 32, 24, 10).n_1700_B(0.0f, -4.0f, 0.0f, 5.0f, 8.0f, 0.005f);
        this.w_1484_f = ImmutableList.of((Object)this.n_1700_B, (Object)this.J_1907_R, (Object)this.v_4262_N, (Object)this.R_4764_Y, (Object)this.G_564_y, (Object)this.P_1922_E, (Object)this.u_1723_Y);
        this.n_1700_B.n_1700_B(0.0f, 0.0f, -1.0f);
        this.J_1907_R.n_1700_B(0.0f, 0.0f, 1.0f);
        this.v_4262_N.v_4262_N = 1.5707964f;
    }

    @Override
    public void render(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    public void n_1700_B(g_221_o matrixStack, D_4792_h buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.w_1484_f.forEach(p_228248_8_ -> p_228248_8_.n_1700_B(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha));
    }

    public void n_1700_B(float p_228247_1_, float rightPageFlipAmount, float leftPageFlipAmount, float bookOpenAmount) {
        float f = (u_530_F.n_1700_B(p_228247_1_ * 0.02f) * 0.1f + 1.25f) * bookOpenAmount;
        this.n_1700_B.v_4262_N = (float)Math.PI + f;
        this.J_1907_R.v_4262_N = -f;
        this.R_4764_Y.v_4262_N = f;
        this.G_564_y.v_4262_N = -f;
        this.P_1922_E.v_4262_N = f - f * 2.0f * rightPageFlipAmount;
        this.u_1723_Y.v_4262_N = f - f * 2.0f * leftPageFlipAmount;
        this.R_4764_Y.R_4764_Y = u_530_F.n_1700_B(f);
        this.G_564_y.R_4764_Y = u_530_F.n_1700_B(f);
        this.P_1922_E.R_4764_Y = u_530_F.n_1700_B(f);
        this.u_1723_Y.R_4764_Y = u_530_F.n_1700_B(f);
    }
}


