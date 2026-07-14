/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_4700_p;

public final class m_2244_y
extends Enum<m_2244_y>
implements E_4700_p {
    public static final /* enum */ m_2244_y n_1700_B = new m_2244_y("top");
    public static final /* enum */ m_2244_y J_1907_R = new m_2244_y("bottom");
    private final String R_4764_Y;
    private static final /* synthetic */ m_2244_y[] G_564_y;

    public static m_2244_y[] values() {
        return (m_2244_y[])G_564_y.clone();
    }

    public static m_2244_y valueOf(String name) {
        return Enum.valueOf(m_2244_y.class, name);
    }

    private m_2244_y(String name) {
        this.R_4764_Y = name;
    }

    public String toString() {
        return this.R_4764_Y;
    }

    @Override
    public String n_1700_B() {
        return this.R_4764_Y;
    }

    private static /* synthetic */ m_2244_y[] J_1907_R() {
        return new m_2244_y[]{n_1700_B, J_1907_R};
    }

    static {
        G_564_y = m_2244_y.J_1907_R();
    }
}

