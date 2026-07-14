/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_4700_p;

public final class u_863_c
extends Enum<u_863_c>
implements E_4700_p {
    public static final /* enum */ u_863_c n_1700_B = new u_863_c("straight");
    public static final /* enum */ u_863_c J_1907_R = new u_863_c("inner_left");
    public static final /* enum */ u_863_c R_4764_Y = new u_863_c("inner_right");
    public static final /* enum */ u_863_c G_564_y = new u_863_c("outer_left");
    public static final /* enum */ u_863_c P_1922_E = new u_863_c("outer_right");
    private final String u_1723_Y;
    private static final /* synthetic */ u_863_c[] v_4262_N;

    public static u_863_c[] values() {
        return (u_863_c[])v_4262_N.clone();
    }

    public static u_863_c valueOf(String name) {
        return Enum.valueOf(u_863_c.class, name);
    }

    private u_863_c(String name) {
        this.u_1723_Y = name;
    }

    public String toString() {
        return this.u_1723_Y;
    }

    @Override
    public String n_1700_B() {
        return this.u_1723_Y;
    }

    private static /* synthetic */ u_863_c[] J_1907_R() {
        return new u_863_c[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E};
    }

    static {
        v_4262_N = u_863_c.J_1907_R();
    }
}

