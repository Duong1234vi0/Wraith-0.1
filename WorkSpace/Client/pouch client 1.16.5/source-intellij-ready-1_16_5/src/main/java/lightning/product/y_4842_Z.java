/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.P_4249_L;
import lightning.product.MinecraftClient;
import lightning.product.k_1366_K;
import lightning.product.s_4405_m;

public class y_4842_Z {
    public static y_4842_Z n_1700_B = new y_4842_Z();
    public final k_1366_K J_1907_R = new k_1366_K(false).n_1700_B();
    public final k_1366_K R_4764_Y = new k_1366_K(false).n_1700_B();

    public void n_1700_B(float offset, int steps) {
        int step;
        int i;
        MinecraftClient mc = MinecraftClient.A_4115_X();
        P_4249_L mcFramebuffer = mc.G_564_y();
        this.R_4764_Y.J_1907_R();
        mcFramebuffer.v_4262_N();
        s_4405_m.u_1723_Y.J_1907_R();
        s_4405_m.u_1723_Y.n_1700_B("offset", offset);
        s_4405_m.u_1723_Y.J_1907_R("resolution", 1.0f / (float)mc.RealmsServerPing().P_4830_p(), 1.0f / (float)mc.RealmsServerPing().h_1847_R());
        k_1366_K.R_4764_Y();
        k_1366_K[] buffers = new k_1366_K[]{this.R_4764_Y, this.J_1907_R};
        for (i = 1; i < steps; ++i) {
            step = i % 2;
            buffers[step].J_1907_R();
            buffers[(step + 1) % 2].G_564_y();
        }
        s_4405_m.u_1723_Y.R_4764_Y();
        s_4405_m.v_4262_N.J_1907_R();
        s_4405_m.v_4262_N.n_1700_B("offset", offset);
        s_4405_m.v_4262_N.J_1907_R("resolution", 1.0f / (float)mc.RealmsServerPing().P_4830_p(), 1.0f / (float)mc.RealmsServerPing().h_1847_R());
        for (i = 0; i < steps; ++i) {
            step = i % 2;
            buffers[(step + 1) % 2].J_1907_R();
            buffers[step].G_564_y();
        }
        s_4405_m.v_4262_N.R_4764_Y();
        mcFramebuffer.J_1907_R(false);
    }
}



