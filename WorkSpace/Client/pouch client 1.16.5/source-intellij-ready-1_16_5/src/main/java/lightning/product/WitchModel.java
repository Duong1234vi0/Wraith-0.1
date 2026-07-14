/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.e_4189_z;
import lightning.product.VillagerModel;
import lightning.product.u_530_F;

public class WitchModel<T extends N_4263_v>
extends VillagerModel<T> {
    private boolean s_956_w;
    private final e_4189_z u_2550_I = new e_4189_z(this).J_1907_R(64, 128);

    public WitchModel(float scale) {
        super(scale, 64, 128);
        this.u_2550_I.n_1700_B(0.0f, -2.0f, 0.0f);
        this.u_2550_I.n_1700_B(0, 0).n_1700_B(0.0f, 3.0f, -6.75f, 1.0f, 1.0f, 1.0f, -0.25f);
        this.t_148_a.J_1907_R(this.u_2550_I);
        this.n_1700_B = new e_4189_z(this).J_1907_R(64, 128);
        this.n_1700_B.n_1700_B(0.0f, 0.0f, 0.0f);
        this.n_1700_B.n_1700_B(0, 0).n_1700_B(-4.0f, -10.0f, -4.0f, 8.0f, 10.0f, 8.0f, scale);
        this.J_1907_R = new e_4189_z(this).J_1907_R(64, 128);
        this.J_1907_R.n_1700_B(-5.0f, -10.03125f, -5.0f);
        this.J_1907_R.n_1700_B(0, 64).n_1700_B(0.0f, 0.0f, 0.0f, 10.0f, 2.0f, 10.0f);
        this.n_1700_B.J_1907_R(this.J_1907_R);
        this.n_1700_B.J_1907_R(this.t_148_a);
        e_4189_z modelrenderer = new e_4189_z(this).J_1907_R(64, 128);
        modelrenderer.n_1700_B(1.75f, -4.0f, 2.0f);
        modelrenderer.n_1700_B(0, 76).n_1700_B(0.0f, 0.0f, 0.0f, 7.0f, 4.0f, 7.0f);
        modelrenderer.u_1723_Y = -0.05235988f;
        modelrenderer.w_1484_f = 0.02617994f;
        this.J_1907_R.J_1907_R(modelrenderer);
        e_4189_z modelrenderer1 = new e_4189_z(this).J_1907_R(64, 128);
        modelrenderer1.n_1700_B(1.75f, -4.0f, 2.0f);
        modelrenderer1.n_1700_B(0, 87).n_1700_B(0.0f, 0.0f, 0.0f, 4.0f, 4.0f, 4.0f);
        modelrenderer1.u_1723_Y = -0.10471976f;
        modelrenderer1.w_1484_f = 0.05235988f;
        modelrenderer.J_1907_R(modelrenderer1);
        e_4189_z modelrenderer2 = new e_4189_z(this).J_1907_R(64, 128);
        modelrenderer2.n_1700_B(1.75f, -2.0f, 2.0f);
        modelrenderer2.n_1700_B(0, 95).n_1700_B(0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f, 0.25f);
        modelrenderer2.u_1723_Y = -0.20943952f;
        modelrenderer2.w_1484_f = 0.10471976f;
        modelrenderer1.J_1907_R(modelrenderer2);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.t_148_a.n_1700_B(0.0f, -2.0f, 0.0f);
        float f = 0.01f * (float)(((N_4263_v)entityIn).j_276_v() % 10);
        this.t_148_a.u_1723_Y = u_530_F.n_1700_B((float)((N_4263_v)entityIn).RealmsWorldResetDto * f) * 4.5f * ((float)Math.PI / 180);
        this.t_148_a.v_4262_N = 0.0f;
        this.t_148_a.w_1484_f = u_530_F.J_1907_R((float)((N_4263_v)entityIn).RealmsWorldResetDto * f) * 2.5f * ((float)Math.PI / 180);
        if (this.s_956_w) {
            this.t_148_a.n_1700_B(0.0f, 1.0f, -1.5f);
            this.t_148_a.u_1723_Y = -0.9f;
        }
    }

    public e_4189_z J_1907_R() {
        return this.t_148_a;
    }

    public void J_1907_R(boolean p_205074_1_) {
        this.s_956_w = p_205074_1_;
    }
}


