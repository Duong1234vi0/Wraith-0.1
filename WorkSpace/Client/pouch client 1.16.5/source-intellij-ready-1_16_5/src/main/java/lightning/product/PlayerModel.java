/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Iterables
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import lightning.product.D_4792_h;
import lightning.product.N_4263_v;
import lightning.product.e_1174_E;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.k_4231_L;
import lightning.product.n_1658_l;
import lightning.product.o_2576_A;
import lightning.product.r_4811_B;

public class PlayerModel<T extends r_4811_B>
extends n_1658_l<T> {
    private List<e_4189_z> M_588_G = Lists.newArrayList();
    public final e_4189_z t_1786_h;
    public final e_4189_z multiplayerClientSuggestionProvider;
    public final e_4189_z w_1457_N;
    public final e_4189_z Y_601_j;
    public final e_4189_z Y_259_p;
    private final e_4189_z P_4830_p;
    private final e_4189_z Q_2552_b;
    private final boolean C_2741_M;

    public PlayerModel(float modelSize, boolean smallArmsIn) {
        super(o_2576_A::w_1484_f, modelSize, 0.0f, 64, 64);
        this.C_2741_M = smallArmsIn;
        this.Q_2552_b = new e_4189_z(this, 24, 0);
        this.Q_2552_b.n_1700_B(-3.0f, -6.0f, -1.0f, 6.0f, 6.0f, 1.0f, modelSize);
        this.P_4830_p = new e_4189_z(this, 0, 0);
        this.P_4830_p.J_1907_R(64, 32);
        this.P_4830_p.n_1700_B(-5.0f, 0.0f, -1.0f, 10.0f, 16.0f, 1.0f, modelSize);
        if (smallArmsIn) {
            this.P_1922_E = new e_4189_z(this, 32, 48);
            this.P_1922_E.n_1700_B(-1.0f, -2.0f, -2.0f, 3.0f, 12.0f, 4.0f, modelSize);
            this.P_1922_E.n_1700_B(5.0f, 2.5f, 0.0f);
            this.G_564_y = new e_4189_z(this, 40, 16);
            this.G_564_y.n_1700_B(-2.0f, -2.0f, -2.0f, 3.0f, 12.0f, 4.0f, modelSize);
            this.G_564_y.n_1700_B(-5.0f, 2.5f, 0.0f);
            this.t_1786_h = new e_4189_z(this, 48, 48);
            this.t_1786_h.n_1700_B(-1.0f, -2.0f, -2.0f, 3.0f, 12.0f, 4.0f, modelSize + 0.25f);
            this.t_1786_h.n_1700_B(5.0f, 2.5f, 0.0f);
            this.multiplayerClientSuggestionProvider = new e_4189_z(this, 40, 32);
            this.multiplayerClientSuggestionProvider.n_1700_B(-2.0f, -2.0f, -2.0f, 3.0f, 12.0f, 4.0f, modelSize + 0.25f);
            this.multiplayerClientSuggestionProvider.n_1700_B(-5.0f, 2.5f, 10.0f);
        } else {
            this.P_1922_E = new e_4189_z(this, 32, 48);
            this.P_1922_E.n_1700_B(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, modelSize);
            this.P_1922_E.n_1700_B(5.0f, 2.0f, 0.0f);
            this.t_1786_h = new e_4189_z(this, 48, 48);
            this.t_1786_h.n_1700_B(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, modelSize + 0.25f);
            this.t_1786_h.n_1700_B(5.0f, 2.0f, 0.0f);
            this.multiplayerClientSuggestionProvider = new e_4189_z(this, 40, 32);
            this.multiplayerClientSuggestionProvider.n_1700_B(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, modelSize + 0.25f);
            this.multiplayerClientSuggestionProvider.n_1700_B(-5.0f, 2.0f, 10.0f);
        }
        this.v_4262_N = new e_4189_z(this, 16, 48);
        this.v_4262_N.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, modelSize);
        this.v_4262_N.n_1700_B(1.9f, 12.0f, 0.0f);
        this.w_1457_N = new e_4189_z(this, 0, 48);
        this.w_1457_N.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, modelSize + 0.25f);
        this.w_1457_N.n_1700_B(1.9f, 12.0f, 0.0f);
        this.Y_601_j = new e_4189_z(this, 0, 32);
        this.Y_601_j.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, modelSize + 0.25f);
        this.Y_601_j.n_1700_B(-1.9f, 12.0f, 0.0f);
        this.Y_259_p = new e_4189_z(this, 16, 32);
        this.Y_259_p.n_1700_B(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, modelSize + 0.25f);
        this.Y_259_p.n_1700_B(0.0f, 0.0f, 0.0f);
    }

    @Override
    protected Iterable<e_4189_z> J_1907_R() {
        return Iterables.concat(super.J_1907_R(), (Iterable)ImmutableList.of((Object)this.w_1457_N, (Object)this.Y_601_j, (Object)this.t_1786_h, (Object)this.multiplayerClientSuggestionProvider, (Object)this.Y_259_p));
    }

    public void n_1700_B(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn) {
        this.Q_2552_b.n_1700_B(this.n_1700_B);
        this.Q_2552_b.R_4764_Y = 0.0f;
        this.Q_2552_b.G_564_y = 0.0f;
        this.Q_2552_b.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
    }

    public void J_1907_R(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn) {
        this.P_4830_p.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.w_1457_N.n_1700_B(this.v_4262_N);
        this.Y_601_j.n_1700_B(this.u_1723_Y);
        this.t_1786_h.n_1700_B(this.P_1922_E);
        this.multiplayerClientSuggestionProvider.n_1700_B(this.G_564_y);
        this.Y_259_p.n_1700_B(this.R_4764_Y);
        if (((r_4811_B)entityIn).J_1907_R(e_1174_E.P_1922_E).n_1700_B()) {
            if (((N_4263_v)entityIn).Z_875_P()) {
                this.P_4830_p.P_1922_E = 1.4f;
                this.P_4830_p.G_564_y = 1.85f;
            } else {
                this.P_4830_p.P_1922_E = 0.0f;
                this.P_4830_p.G_564_y = 0.0f;
            }
        } else if (((N_4263_v)entityIn).Z_875_P()) {
            this.P_4830_p.P_1922_E = 0.3f;
            this.P_4830_p.G_564_y = 0.8f;
        } else {
            this.P_4830_p.P_1922_E = -1.1f;
            this.P_4830_p.G_564_y = -0.85f;
        }
    }

    @Override
    public void a_(boolean visible) {
        super.a_(visible);
        this.t_1786_h.s_956_w = visible;
        this.multiplayerClientSuggestionProvider.s_956_w = visible;
        this.w_1457_N.s_956_w = visible;
        this.Y_601_j.s_956_w = visible;
        this.Y_259_p.s_956_w = visible;
        this.P_4830_p.s_956_w = visible;
        this.Q_2552_b.s_956_w = visible;
    }

    @Override
    public void n_1700_B(k_4231_L sideIn, g_221_o matrixStackIn) {
        e_4189_z modelrenderer = this.n_1700_B(sideIn);
        if (this.C_2741_M) {
            float f = 0.5f * (float)(sideIn == k_4231_L.J_1907_R ? 1 : -1);
            modelrenderer.R_4764_Y += f;
            modelrenderer.n_1700_B(matrixStackIn);
            modelrenderer.R_4764_Y -= f;
        } else {
            modelrenderer.n_1700_B(matrixStackIn);
        }
    }

    public e_4189_z n_1700_B(Random randomIn) {
        return this.M_588_G.get(randomIn.nextInt(this.M_588_G.size()));
    }

    @Override
    public void accept(e_4189_z p_accept_1_) {
        if (this.M_588_G == null) {
            this.M_588_G = Lists.newArrayList();
        }
        this.M_588_G.add(p_accept_1_);
    }
}


