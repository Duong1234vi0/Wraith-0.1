/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_688_b;
import lightning.product.P_3504_Q;
import lightning.product.P_4249_L;
import lightning.product.MinecraftAccess;
import lightning.product.MinecraftClient;
import lightning.product.l_3729_r;

public class k_1366_K
extends P_4249_L
implements MinecraftAccess {
    private boolean u_2550_I;

    public k_1366_K(boolean useDepth) {
        super(1, 1, useDepth, MinecraftClient.n_1700_B);
    }

    public k_1366_K(int width, int height, boolean useDepth) {
        super(width, height, useDepth, MinecraftClient.n_1700_B);
    }

    private static void R_4764_Y(k_1366_K framebuffer) {
        if (k_1366_K.n_1700_B(framebuffer)) {
            framebuffer.J_1907_R(Math.max(c_3005_b.RealmsServerPing().u_2550_I(), 1), Math.max(c_3005_b.RealmsServerPing().M_588_G(), 1), MinecraftClient.n_1700_B);
        }
    }

    public k_1366_K n_1700_B() {
        this.u_2550_I = true;
        return this;
    }

    @Override
    public void n_1700_B(int framebufferFilterIn) {
        super.n_1700_B(this.u_2550_I ? 9729 : framebufferFilterIn);
    }

    public void n_1700_B(boolean clear) {
        k_1366_K.R_4764_Y(this);
        if (clear) {
            this.R_4764_Y(MinecraftClient.n_1700_B);
        }
        this.J_1907_R(false);
    }

    public void J_1907_R() {
        this.n_1700_B(true);
    }

    public static void n_1700_B(double x, double y, double width, double height) {
        A_4115_X.n_1700_B(7, E_688_b.Q_2552_b);
        A_4115_X.pos(x, y, 0.0).tex(0.0f, 1.0f).endVertex();
        A_4115_X.pos(x, y + height, 0.0).tex(0.0f, 0.0f).endVertex();
        A_4115_X.pos(x + width, y + height, 0.0).tex(1.0f, 0.0f).endVertex();
        A_4115_X.pos(x + width, y, 0.0).tex(1.0f, 1.0f).endVertex();
        Y_1740_V.J_1907_R();
    }

    public static void R_4764_Y() {
        P_3504_Q window = l_3729_r.n_1700_B(c_3005_b.RealmsServerPing().Q_4569_t(), c_3005_b.RealmsServerPing().M_182_A());
        double width = window.n_1700_B();
        double height = window.J_1907_R();
        k_1366_K.n_1700_B(0.0, 0.0, width, height);
    }

    public void G_564_y() {
        this.v_4262_N();
        k_1366_K.R_4764_Y();
    }

    public static boolean n_1700_B(k_1366_K framebuffer) {
        return framebuffer == null || framebuffer.R_4764_Y != H_2857_Y.u_2550_I() || framebuffer.G_564_y != H_2857_Y.M_588_G();
    }

    public static k_1366_K J_1907_R(k_1366_K framebuffer) {
        if (k_1366_K.n_1700_B(framebuffer)) {
            if (framebuffer != null) {
                framebuffer.P_1922_E();
            }
            return new k_1366_K(H_2857_Y.u_2550_I(), H_2857_Y.M_588_G(), true);
        }
        return framebuffer;
    }
}



