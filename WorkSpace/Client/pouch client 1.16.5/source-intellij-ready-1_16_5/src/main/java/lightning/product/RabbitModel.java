/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.D_4792_h;
import lightning.product.M_2433_H;
import lightning.product.EntityModel;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.u_530_F;

public class RabbitModel<T extends M_2433_H>
extends EntityModel<T> {
    private final e_4189_z n_1700_B = new e_4189_z(this, 26, 24);
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
    private final e_4189_z M_588_G;
    private float P_4830_p;

    public RabbitModel() {
        this.n_1700_B.n_1700_B(-1.0f, 5.5f, -3.7f, 2.0f, 1.0f, 7.0f);
        this.n_1700_B.n_1700_B(3.0f, 17.5f, 3.7f);
        this.n_1700_B.t_148_a = true;
        this.n_1700_B(this.n_1700_B, 0.0f, 0.0f, 0.0f);
        this.J_1907_R = new e_4189_z(this, 8, 24);
        this.J_1907_R.n_1700_B(-1.0f, 5.5f, -3.7f, 2.0f, 1.0f, 7.0f);
        this.J_1907_R.n_1700_B(-3.0f, 17.5f, 3.7f);
        this.J_1907_R.t_148_a = true;
        this.n_1700_B(this.J_1907_R, 0.0f, 0.0f, 0.0f);
        this.R_4764_Y = new e_4189_z(this, 30, 15);
        this.R_4764_Y.n_1700_B(-1.0f, 0.0f, 0.0f, 2.0f, 4.0f, 5.0f);
        this.R_4764_Y.n_1700_B(3.0f, 17.5f, 3.7f);
        this.R_4764_Y.t_148_a = true;
        this.n_1700_B(this.R_4764_Y, -0.34906584f, 0.0f, 0.0f);
        this.G_564_y = new e_4189_z(this, 16, 15);
        this.G_564_y.n_1700_B(-1.0f, 0.0f, 0.0f, 2.0f, 4.0f, 5.0f);
        this.G_564_y.n_1700_B(-3.0f, 17.5f, 3.7f);
        this.G_564_y.t_148_a = true;
        this.n_1700_B(this.G_564_y, -0.34906584f, 0.0f, 0.0f);
        this.P_1922_E = new e_4189_z(this, 0, 0);
        this.P_1922_E.n_1700_B(-3.0f, -2.0f, -10.0f, 6.0f, 5.0f, 10.0f);
        this.P_1922_E.n_1700_B(0.0f, 19.0f, 8.0f);
        this.P_1922_E.t_148_a = true;
        this.n_1700_B(this.P_1922_E, -0.34906584f, 0.0f, 0.0f);
        this.u_1723_Y = new e_4189_z(this, 8, 15);
        this.u_1723_Y.n_1700_B(-1.0f, 0.0f, -1.0f, 2.0f, 7.0f, 2.0f);
        this.u_1723_Y.n_1700_B(3.0f, 17.0f, -1.0f);
        this.u_1723_Y.t_148_a = true;
        this.n_1700_B(this.u_1723_Y, -0.17453292f, 0.0f, 0.0f);
        this.v_4262_N = new e_4189_z(this, 0, 15);
        this.v_4262_N.n_1700_B(-1.0f, 0.0f, -1.0f, 2.0f, 7.0f, 2.0f);
        this.v_4262_N.n_1700_B(-3.0f, 17.0f, -1.0f);
        this.v_4262_N.t_148_a = true;
        this.n_1700_B(this.v_4262_N, -0.17453292f, 0.0f, 0.0f);
        this.w_1484_f = new e_4189_z(this, 32, 0);
        this.w_1484_f.n_1700_B(-2.5f, -4.0f, -5.0f, 5.0f, 4.0f, 5.0f);
        this.w_1484_f.n_1700_B(0.0f, 16.0f, -1.0f);
        this.w_1484_f.t_148_a = true;
        this.n_1700_B(this.w_1484_f, 0.0f, 0.0f, 0.0f);
        this.t_148_a = new e_4189_z(this, 52, 0);
        this.t_148_a.n_1700_B(-2.5f, -9.0f, -1.0f, 2.0f, 5.0f, 1.0f);
        this.t_148_a.n_1700_B(0.0f, 16.0f, -1.0f);
        this.t_148_a.t_148_a = true;
        this.n_1700_B(this.t_148_a, 0.0f, -0.2617994f, 0.0f);
        this.s_956_w = new e_4189_z(this, 58, 0);
        this.s_956_w.n_1700_B(0.5f, -9.0f, -1.0f, 2.0f, 5.0f, 1.0f);
        this.s_956_w.n_1700_B(0.0f, 16.0f, -1.0f);
        this.s_956_w.t_148_a = true;
        this.n_1700_B(this.s_956_w, 0.0f, 0.2617994f, 0.0f);
        this.u_2550_I = new e_4189_z(this, 52, 6);
        this.u_2550_I.n_1700_B(-1.5f, -1.5f, 0.0f, 3.0f, 3.0f, 2.0f);
        this.u_2550_I.n_1700_B(0.0f, 20.0f, 7.0f);
        this.u_2550_I.t_148_a = true;
        this.n_1700_B(this.u_2550_I, -0.3490659f, 0.0f, 0.0f);
        this.M_588_G = new e_4189_z(this, 32, 9);
        this.M_588_G.n_1700_B(-0.5f, -2.5f, -5.5f, 1.0f, 1.0f, 1.0f);
        this.M_588_G.n_1700_B(0.0f, 16.0f, -1.0f);
        this.M_588_G.t_148_a = true;
        this.n_1700_B(this.M_588_G, 0.0f, 0.0f, 0.0f);
    }

    @Override
    private void n_1700_B(e_4189_z renderer, float x, float y, float z) {
        renderer.u_1723_Y = x;
        renderer.v_4262_N = y;
        renderer.w_1484_f = z;
    }

    @Override
    public void render(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.M_182_A) {
            float f = 1.5f;
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(0.56666666f, 0.56666666f, 0.56666666f);
            matrixStackIn.n_1700_B(0.0, 1.375, 0.125);
            ImmutableList.of((Object)this.w_1484_f, (Object)this.s_956_w, (Object)this.t_148_a, (Object)this.M_588_G).forEach(p_228292_8_ -> p_228292_8_.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.J_1907_R();
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(0.4f, 0.4f, 0.4f);
            matrixStackIn.n_1700_B(0.0, 2.25, 0.0);
            ImmutableList.of((Object)this.n_1700_B, (Object)this.J_1907_R, (Object)this.R_4764_Y, (Object)this.G_564_y, (Object)this.P_1922_E, (Object)this.u_1723_Y, (Object)this.v_4262_N, (Object)this.u_2550_I).forEach(p_228291_8_ -> p_228291_8_.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.J_1907_R();
        } else {
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(0.6f, 0.6f, 0.6f);
            matrixStackIn.n_1700_B(0.0, 1.0, 0.0);
            ImmutableList.of((Object)this.n_1700_B, (Object)this.J_1907_R, (Object)this.R_4764_Y, (Object)this.G_564_y, (Object)this.P_1922_E, (Object)this.u_1723_Y, (Object)this.v_4262_N, (Object)this.w_1484_f, (Object)this.t_148_a, (Object)this.s_956_w, (Object)this.u_2550_I, (Object)this.M_588_G, (Object[])new e_4189_z[0]).forEach(p_228290_8_ -> p_228290_8_.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.J_1907_R();
        }
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = ageInTicks - (float)((M_2433_H)entityIn).RealmsWorldResetDto;
        this.M_588_G.u_1723_Y = headPitch * ((float)Math.PI / 180);
        this.w_1484_f.u_1723_Y = headPitch * ((float)Math.PI / 180);
        this.t_148_a.u_1723_Y = headPitch * ((float)Math.PI / 180);
        this.s_956_w.u_1723_Y = headPitch * ((float)Math.PI / 180);
        this.M_588_G.v_4262_N = netHeadYaw * ((float)Math.PI / 180);
        this.w_1484_f.v_4262_N = netHeadYaw * ((float)Math.PI / 180);
        this.t_148_a.v_4262_N = this.M_588_G.v_4262_N - 0.2617994f;
        this.s_956_w.v_4262_N = this.M_588_G.v_4262_N + 0.2617994f;
        this.P_4830_p = u_530_F.n_1700_B(((M_2433_H)entityIn).c_3005_b(f) * (float)Math.PI);
        this.R_4764_Y.u_1723_Y = (this.P_4830_p * 50.0f - 21.0f) * ((float)Math.PI / 180);
        this.G_564_y.u_1723_Y = (this.P_4830_p * 50.0f - 21.0f) * ((float)Math.PI / 180);
        this.n_1700_B.u_1723_Y = this.P_4830_p * 50.0f * ((float)Math.PI / 180);
        this.J_1907_R.u_1723_Y = this.P_4830_p * 50.0f * ((float)Math.PI / 180);
        this.u_1723_Y.u_1723_Y = (this.P_4830_p * -40.0f - 11.0f) * ((float)Math.PI / 180);
        this.v_4262_N.u_1723_Y = (this.P_4830_p * -40.0f - 11.0f) * ((float)Math.PI / 180);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, partialTick);
        this.P_4830_p = u_530_F.n_1700_B(((M_2433_H)entityIn).c_3005_b(partialTick) * (float)Math.PI);
    }
}


