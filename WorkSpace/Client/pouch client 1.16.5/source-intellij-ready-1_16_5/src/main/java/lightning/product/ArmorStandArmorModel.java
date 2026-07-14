/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_686_b;
import lightning.product.n_1658_l;

public class ArmorStandArmorModel
extends n_1658_l<D_686_b> {
    public ArmorStandArmorModel(float modelSize) {
        this(modelSize, 64, 32);
    }

    protected ArmorStandArmorModel(float modelSize, int textureWidthIn, int textureHeightIn) {
        super(modelSize, 0.0f, textureWidthIn, textureHeightIn);
    }

    @Override
    public void n_1700_B(D_686_b entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.n_1700_B.u_1723_Y = (float)Math.PI / 180 * entityIn.M_182_A().J_1907_R();
        this.n_1700_B.v_4262_N = (float)Math.PI / 180 * entityIn.M_182_A().R_4764_Y();
        this.n_1700_B.w_1484_f = (float)Math.PI / 180 * entityIn.M_182_A().G_564_y();
        this.n_1700_B.n_1700_B(0.0f, 1.0f, 0.0f);
        this.R_4764_Y.u_1723_Y = (float)Math.PI / 180 * entityIn.multiplayerClientSuggestionProvider().J_1907_R();
        this.R_4764_Y.v_4262_N = (float)Math.PI / 180 * entityIn.multiplayerClientSuggestionProvider().R_4764_Y();
        this.R_4764_Y.w_1484_f = (float)Math.PI / 180 * entityIn.multiplayerClientSuggestionProvider().G_564_y();
        this.P_1922_E.u_1723_Y = (float)Math.PI / 180 * entityIn.C_2741_M().J_1907_R();
        this.P_1922_E.v_4262_N = (float)Math.PI / 180 * entityIn.C_2741_M().R_4764_Y();
        this.P_1922_E.w_1484_f = (float)Math.PI / 180 * entityIn.C_2741_M().G_564_y();
        this.G_564_y.u_1723_Y = (float)Math.PI / 180 * entityIn.k_2293_S().J_1907_R();
        this.G_564_y.v_4262_N = (float)Math.PI / 180 * entityIn.k_2293_S().R_4764_Y();
        this.G_564_y.w_1484_f = (float)Math.PI / 180 * entityIn.k_2293_S().G_564_y();
        this.v_4262_N.u_1723_Y = (float)Math.PI / 180 * entityIn.c_3005_b().J_1907_R();
        this.v_4262_N.v_4262_N = (float)Math.PI / 180 * entityIn.c_3005_b().R_4764_Y();
        this.v_4262_N.w_1484_f = (float)Math.PI / 180 * entityIn.c_3005_b().G_564_y();
        this.v_4262_N.n_1700_B(1.9f, 11.0f, 0.0f);
        this.u_1723_Y.u_1723_Y = (float)Math.PI / 180 * entityIn.A_4115_X().J_1907_R();
        this.u_1723_Y.v_4262_N = (float)Math.PI / 180 * entityIn.A_4115_X().R_4764_Y();
        this.u_1723_Y.w_1484_f = (float)Math.PI / 180 * entityIn.A_4115_X().G_564_y();
        this.u_1723_Y.n_1700_B(-1.9f, 11.0f, 0.0f);
        this.J_1907_R.n_1700_B(this.n_1700_B);
    }
}


