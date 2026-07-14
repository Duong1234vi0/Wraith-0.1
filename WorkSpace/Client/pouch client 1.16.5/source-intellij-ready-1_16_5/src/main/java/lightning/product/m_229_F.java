/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.SimpleParticleType;

public final class m_229_F {
    public static final String n_1700_B = "minecraft";
    public static final SimpleParticleType J_1907_R = m_229_F.n_1700_B("water_ripple");
    public static final SimpleParticleType R_4764_Y = m_229_F.n_1700_B("water_splash_emitter", true);
    public static final SimpleParticleType G_564_y = m_229_F.n_1700_B("water_splash", true);
    public static final SimpleParticleType P_1922_E = m_229_F.n_1700_B("water_splash_foam", true);
    public static final SimpleParticleType u_1723_Y = m_229_F.n_1700_B("water_splash_ring", true);

    private m_229_F() {
    }

    public static void n_1700_B() {
    }

    private static SimpleParticleType n_1700_B(String name) {
        return m_229_F.n_1700_B(name, false);
    }

    private static SimpleParticleType n_1700_B(String name, boolean alwaysShow) {
        return V_3137_a.n_1700_B(V_3137_a.g_164_R, new g_2336_b(n_1700_B, name), new SimpleParticleType(alwaysShow){});
    }
}


