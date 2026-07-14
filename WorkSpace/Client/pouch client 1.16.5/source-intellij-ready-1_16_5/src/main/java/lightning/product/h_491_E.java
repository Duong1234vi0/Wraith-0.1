/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_4115_X;
import lightning.product.Arrow;
import lightning.product.PlayerModel;
import lightning.product.N_4263_v;
import lightning.product.W_1980_j;
import lightning.product.g_221_o;
import lightning.product.h_3270_j;
import lightning.product.o_3091_w;
import lightning.product.o_4479_Q;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;

public class h_491_E<T extends r_4811_B, M extends PlayerModel<T>>
extends W_1980_j<T, M> {
    private final w_2040_b n_1700_B;
    private Arrow J_1907_R;

    public h_491_E(o_4479_Q<T, M> rendererIn) {
        super(rendererIn);
        this.n_1700_B = rendererIn.J_1907_R();
    }

    @Override
    protected int n_1700_B(T p_225631_1_) {
        return ((r_4811_B)p_225631_1_).n_4539_g();
    }

    @Override
    protected void n_1700_B(g_221_o p_225632_1_, o_3091_w p_225632_2_, int p_225632_3_, N_4263_v p_225632_4_, float p_225632_5_, float p_225632_6_, float p_225632_7_, float p_225632_8_) {
        h_3270_j event = new h_3270_j(h_3270_j.n_1700_B.P_4830_p);
        A_4115_X.n_1700_B(event);
        if (event.n_1700_B()) {
            return;
        }
        float f = u_530_F.R_4764_Y(p_225632_5_ * p_225632_5_ + p_225632_7_ * p_225632_7_);
        this.J_1907_R = new Arrow(p_225632_4_.O_508_d, p_225632_4_.O_3598_v(), p_225632_4_.X_2960_b(), p_225632_4_.l_2647_k());
        this.J_1907_R.p_178_J = (float)(Math.atan2(p_225632_5_, p_225632_7_) * 57.2957763671875);
        this.J_1907_R.f_4016_n = (float)(Math.atan2(p_225632_6_, f) * 57.2957763671875);
        this.J_1907_R.j_276_v = this.J_1907_R.p_178_J;
        this.J_1907_R.UploadStatus = this.J_1907_R.f_4016_n;
        this.n_1700_B.n_1700_B(this.J_1907_R, 0.0, 0.0, 0.0, 0.0f, p_225632_8_, p_225632_1_, p_225632_2_, p_225632_3_);
    }
}


