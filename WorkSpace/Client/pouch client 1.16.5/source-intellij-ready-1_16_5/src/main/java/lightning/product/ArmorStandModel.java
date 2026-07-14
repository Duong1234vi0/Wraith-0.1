/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Iterables
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import lightning.product.D_686_b;
import lightning.product.ArmorStandArmorModel;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.k_4231_L;
import lightning.product.u_530_F;

public class ArmorStandModel
extends ArmorStandArmorModel {
    private final e_4189_z M_588_G;
    private final e_4189_z P_4830_p;
    private final e_4189_z t_1786_h;
    private final e_4189_z multiplayerClientSuggestionProvider;

    public ArmorStandModel() {
        this(0.0f);
    }

    public ArmorStandModel(float modelSize) {
        super(modelSize, 64, 64);
        this.n_1700_B = new e_4189_z(this, 0, 0);
        this.n_1700_B.n_1700_B(-1.0f, -7.0f, -1.0f, 2.0f, 7.0f, 2.0f, modelSize);
        this.n_1700_B.n_1700_B(0.0f, 0.0f, 0.0f);
        this.R_4764_Y = new e_4189_z(this, 0, 26);
        this.R_4764_Y.n_1700_B(-6.0f, 0.0f, -1.5f, 12.0f, 3.0f, 3.0f, modelSize);
        this.R_4764_Y.n_1700_B(0.0f, 0.0f, 0.0f);
        this.G_564_y = new e_4189_z(this, 24, 0);
        this.G_564_y.n_1700_B(-2.0f, -2.0f, -1.0f, 2.0f, 12.0f, 2.0f, modelSize);
        this.G_564_y.n_1700_B(-5.0f, 2.0f, 0.0f);
        this.P_1922_E = new e_4189_z(this, 32, 16);
        this.P_1922_E.t_148_a = true;
        this.P_1922_E.n_1700_B(0.0f, -2.0f, -1.0f, 2.0f, 12.0f, 2.0f, modelSize);
        this.P_1922_E.n_1700_B(5.0f, 2.0f, 0.0f);
        this.u_1723_Y = new e_4189_z(this, 8, 0);
        this.u_1723_Y.n_1700_B(-1.0f, 0.0f, -1.0f, 2.0f, 11.0f, 2.0f, modelSize);
        this.u_1723_Y.n_1700_B(-1.9f, 12.0f, 0.0f);
        this.v_4262_N = new e_4189_z(this, 40, 16);
        this.v_4262_N.t_148_a = true;
        this.v_4262_N.n_1700_B(-1.0f, 0.0f, -1.0f, 2.0f, 11.0f, 2.0f, modelSize);
        this.v_4262_N.n_1700_B(1.9f, 12.0f, 0.0f);
        this.M_588_G = new e_4189_z(this, 16, 0);
        this.M_588_G.n_1700_B(-3.0f, 3.0f, -1.0f, 2.0f, 7.0f, 2.0f, modelSize);
        this.M_588_G.n_1700_B(0.0f, 0.0f, 0.0f);
        this.M_588_G.s_956_w = true;
        this.P_4830_p = new e_4189_z(this, 48, 16);
        this.P_4830_p.n_1700_B(1.0f, 3.0f, -1.0f, 2.0f, 7.0f, 2.0f, modelSize);
        this.P_4830_p.n_1700_B(0.0f, 0.0f, 0.0f);
        this.t_1786_h = new e_4189_z(this, 0, 48);
        this.t_1786_h.n_1700_B(-4.0f, 10.0f, -1.0f, 8.0f, 2.0f, 2.0f, modelSize);
        this.t_1786_h.n_1700_B(0.0f, 0.0f, 0.0f);
        this.multiplayerClientSuggestionProvider = new e_4189_z(this, 0, 32);
        this.multiplayerClientSuggestionProvider.n_1700_B(-6.0f, 11.0f, -6.0f, 12.0f, 1.0f, 12.0f, modelSize);
        this.multiplayerClientSuggestionProvider.n_1700_B(0.0f, 12.0f, 0.0f);
        this.J_1907_R.s_956_w = false;
    }

    @Override
    public void n_1700_B(D_686_b entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        this.multiplayerClientSuggestionProvider.u_1723_Y = 0.0f;
        this.multiplayerClientSuggestionProvider.v_4262_N = (float)Math.PI / 180 * -u_530_F.w_1484_f(partialTick, entityIn.j_276_v, entityIn.p_178_J);
        this.multiplayerClientSuggestionProvider.w_1484_f = 0.0f;
    }

    @Override
    public void n_1700_B(D_686_b entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.P_1922_E.s_956_w = entityIn.w_1484_f();
        this.G_564_y.s_956_w = entityIn.w_1484_f();
        this.multiplayerClientSuggestionProvider.s_956_w = !entityIn.h_1847_R();
        this.v_4262_N.n_1700_B(1.9f, 12.0f, 0.0f);
        this.u_1723_Y.n_1700_B(-1.9f, 12.0f, 0.0f);
        this.M_588_G.u_1723_Y = (float)Math.PI / 180 * entityIn.multiplayerClientSuggestionProvider().J_1907_R();
        this.M_588_G.v_4262_N = (float)Math.PI / 180 * entityIn.multiplayerClientSuggestionProvider().R_4764_Y();
        this.M_588_G.w_1484_f = (float)Math.PI / 180 * entityIn.multiplayerClientSuggestionProvider().G_564_y();
        this.P_4830_p.u_1723_Y = (float)Math.PI / 180 * entityIn.multiplayerClientSuggestionProvider().J_1907_R();
        this.P_4830_p.v_4262_N = (float)Math.PI / 180 * entityIn.multiplayerClientSuggestionProvider().R_4764_Y();
        this.P_4830_p.w_1484_f = (float)Math.PI / 180 * entityIn.multiplayerClientSuggestionProvider().G_564_y();
        this.t_1786_h.u_1723_Y = (float)Math.PI / 180 * entityIn.multiplayerClientSuggestionProvider().J_1907_R();
        this.t_1786_h.v_4262_N = (float)Math.PI / 180 * entityIn.multiplayerClientSuggestionProvider().R_4764_Y();
        this.t_1786_h.w_1484_f = (float)Math.PI / 180 * entityIn.multiplayerClientSuggestionProvider().G_564_y();
    }

    @Override
    protected Iterable<e_4189_z> J_1907_R() {
        return Iterables.concat(super.J_1907_R(), (Iterable)ImmutableList.of((Object)this.M_588_G, (Object)this.P_4830_p, (Object)this.t_1786_h, (Object)this.multiplayerClientSuggestionProvider));
    }

    @Override
    public void n_1700_B(k_4231_L sideIn, g_221_o matrixStackIn) {
        e_4189_z modelrenderer = this.n_1700_B(sideIn);
        boolean flag = modelrenderer.s_956_w;
        modelrenderer.s_956_w = true;
        super.n_1700_B(sideIn, matrixStackIn);
        modelrenderer.s_956_w = flag;
    }
}


