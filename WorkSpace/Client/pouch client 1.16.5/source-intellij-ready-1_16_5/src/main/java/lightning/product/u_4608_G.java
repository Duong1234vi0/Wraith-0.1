/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.SharedConstants;
import lightning.product.x_282_a;

public final class u_4608_G
extends Enum<u_4608_G> {
    public static final /* enum */ u_4608_G n_1700_B = new u_4608_G("old");
    public static final /* enum */ u_4608_G J_1907_R = new u_4608_G("new");
    public static final /* enum */ u_4608_G R_4764_Y = new u_4608_G("compatible");
    private final x_282_a G_564_y;
    private final x_282_a P_1922_E;
    private static final /* synthetic */ u_4608_G[] u_1723_Y;

    public static u_4608_G[] values() {
        return (u_4608_G[])u_1723_Y.clone();
    }

    public static u_4608_G valueOf(String name) {
        return Enum.valueOf(u_4608_G.class, name);
    }

    private u_4608_G(String id) {
        this.G_564_y = new F_2904_S("pack.incompatible." + id).n_1700_B(D_4024_W.w_1484_f);
        this.P_1922_E = new F_2904_S("pack.incompatible.confirm." + id);
    }

    public boolean n_1700_B() {
        return this == R_4764_Y;
    }

    public static u_4608_G n_1700_B(int packVersionIn) {
        if (packVersionIn < SharedConstants.n_1700_B().getPackVersion()) {
            return n_1700_B;
        }
        return packVersionIn > SharedConstants.n_1700_B().getPackVersion() ? J_1907_R : R_4764_Y;
    }

    public x_282_a J_1907_R() {
        return this.G_564_y;
    }

    public x_282_a R_4764_Y() {
        return this.P_1922_E;
    }

    private static /* synthetic */ u_4608_G[] G_564_y() {
        return new u_4608_G[]{n_1700_B, J_1907_R, R_4764_Y};
    }

    static {
        u_1723_Y = u_4608_G.G_564_y();
    }
}


