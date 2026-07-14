/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public final class t_1920_R
extends Enum<t_1920_R> {
    public static final /* enum */ t_1920_R n_1700_B = new t_1920_R(true, false);
    public static final /* enum */ t_1920_R J_1907_R = new t_1920_R(false, false);
    public static final /* enum */ t_1920_R R_4764_Y = new t_1920_R(false, true);
    private static final t_1920_R[] G_564_y;
    private boolean P_1922_E;
    private boolean u_1723_Y;
    private static final /* synthetic */ t_1920_R[] v_4262_N;

    public static t_1920_R[] values() {
        return (t_1920_R[])v_4262_N.clone();
    }

    public static t_1920_R valueOf(String name) {
        return Enum.valueOf(t_1920_R.class, name);
    }

    private t_1920_R(boolean p_i242049_3_, boolean p_i242049_4_) {
        this.P_1922_E = p_i242049_3_;
        this.u_1723_Y = p_i242049_4_;
    }

    public boolean n_1700_B() {
        return this.P_1922_E;
    }

    public boolean J_1907_R() {
        return this.u_1723_Y;
    }

    public t_1920_R R_4764_Y() {
        return G_564_y[(this.ordinal() + 1) % G_564_y.length];
    }

    private static /* synthetic */ t_1920_R[] G_564_y() {
        return new t_1920_R[]{n_1700_B, J_1907_R, R_4764_Y};
    }

    static {
        v_4262_N = t_1920_R.G_564_y();
        G_564_y = t_1920_R.values();
    }
}

