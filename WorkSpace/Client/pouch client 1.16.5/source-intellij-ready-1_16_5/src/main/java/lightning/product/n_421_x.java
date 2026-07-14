/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ResourceManager;
import lightning.product.V_3137_a;
import lightning.product.MinecraftClient;
import lightning.product.g_2336_b;
import lightning.product.SimpleParticleType;

public final class n_421_x {
    public static final String n_1700_B = "maseffects";
    private static Boolean v_4262_N;
    public static final SimpleParticleType J_1907_R;
    public static final SimpleParticleType R_4764_Y;
    public static final SimpleParticleType G_564_y;
    public static final SimpleParticleType P_1922_E;
    public static final SimpleParticleType u_1723_Y;

    private n_421_x() {
    }

    public static void n_1700_B() {
    }

    public static boolean J_1907_R() {
        if (v_4262_N != null) {
            return v_4262_N;
        }
        MinecraftClient mc = MinecraftClient.A_4115_X();
        if (mc == null) {
            v_4262_N = true;
            return true;
        }
        ResourceManager resourceManager = mc.T_2506_i();
        boolean ok = resourceManager != null && resourceManager.J_1907_R(new g_2336_b(n_1700_B, "particles/revive.json")) && resourceManager.J_1907_R(new g_2336_b(n_1700_B, "textures/particle/revive_0.png"));
        v_4262_N = ok;
        return ok;
    }

    private static SimpleParticleType n_1700_B(String name) {
        return V_3137_a.n_1700_B(V_3137_a.g_164_R, new g_2336_b(n_1700_B, name), new SimpleParticleType(true){});
    }

    static {
        J_1907_R = n_421_x.n_1700_B("revive");
        R_4764_Y = n_421_x.n_1700_B("revive_spark");
        G_564_y = n_421_x.n_1700_B("death_spark");
        P_1922_E = n_421_x.n_1700_B("death_skull");
        u_1723_Y = n_421_x.n_1700_B("pearl_trail");
    }
}



