/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public final class m_3054_I
extends Enum<m_3054_I> {
    public static final /* enum */ m_3054_I n_1700_B = new m_3054_I();
    public static final /* enum */ m_3054_I J_1907_R = new m_3054_I();
    public static final /* enum */ m_3054_I R_4764_Y = new m_3054_I();
    public static final /* enum */ m_3054_I G_564_y = new m_3054_I();
    private static final /* synthetic */ m_3054_I[] P_1922_E;

    public static m_3054_I[] values() {
        return (m_3054_I[])P_1922_E.clone();
    }

    public static m_3054_I valueOf(String name) {
        return Enum.valueOf(m_3054_I.class, name);
    }

    public boolean n_1700_B() {
        return this == n_1700_B || this == J_1907_R;
    }

    public boolean J_1907_R() {
        return this == n_1700_B;
    }

    public static m_3054_I n_1700_B(boolean p_233537_0_) {
        return p_233537_0_ ? n_1700_B : J_1907_R;
    }

    private static /* synthetic */ m_3054_I[] R_4764_Y() {
        return new m_3054_I[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
    }

    static {
        P_1922_E = m_3054_I.R_4764_Y();
    }
}

