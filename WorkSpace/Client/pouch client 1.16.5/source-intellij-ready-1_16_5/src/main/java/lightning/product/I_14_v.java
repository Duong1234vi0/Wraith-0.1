/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.Abilities;
import lightning.product.x_282_a;

public final class I_14_v
extends Enum<I_14_v> {
    public static final /* enum */ I_14_v n_1700_B = new I_14_v(-1, "");
    public static final /* enum */ I_14_v J_1907_R = new I_14_v(0, "survival");
    public static final /* enum */ I_14_v R_4764_Y = new I_14_v(1, "creative");
    public static final /* enum */ I_14_v G_564_y = new I_14_v(2, "adventure");
    public static final /* enum */ I_14_v P_1922_E = new I_14_v(3, "spectator");
    private final int u_1723_Y;
    private final String v_4262_N;
    private static final /* synthetic */ I_14_v[] w_1484_f;

    public static I_14_v[] values() {
        return (I_14_v[])w_1484_f.clone();
    }

    public static I_14_v valueOf(String name) {
        return Enum.valueOf(I_14_v.class, name);
    }

    private I_14_v(int gameTypeId, String gameTypeName) {
        this.u_1723_Y = gameTypeId;
        this.v_4262_N = gameTypeName;
    }

    public int n_1700_B() {
        return this.u_1723_Y;
    }

    public String J_1907_R() {
        return this.v_4262_N;
    }

    public x_282_a R_4764_Y() {
        return new F_2904_S("gameMode." + this.v_4262_N);
    }

    public void n_1700_B(Abilities capabilities) {
        if (this == R_4764_Y) {
            capabilities.R_4764_Y = true;
            capabilities.G_564_y = true;
            capabilities.n_1700_B = true;
        } else if (this == P_1922_E) {
            capabilities.R_4764_Y = true;
            capabilities.G_564_y = false;
            capabilities.n_1700_B = true;
            capabilities.J_1907_R = true;
        } else {
            capabilities.R_4764_Y = false;
            capabilities.G_564_y = false;
            capabilities.n_1700_B = false;
            capabilities.J_1907_R = false;
        }
        capabilities.P_1922_E = !this.G_564_y();
    }

    public boolean G_564_y() {
        return this == G_564_y || this == P_1922_E;
    }

    public boolean P_1922_E() {
        return this == R_4764_Y;
    }

    public boolean u_1723_Y() {
        return this == J_1907_R || this == G_564_y;
    }

    public static I_14_v n_1700_B(int idIn) {
        return I_14_v.n_1700_B(idIn, J_1907_R);
    }

    public static I_14_v n_1700_B(int targetId, I_14_v fallback) {
        for (I_14_v gametype : I_14_v.values()) {
            if (gametype.u_1723_Y != targetId) continue;
            return gametype;
        }
        return fallback;
    }

    public static I_14_v n_1700_B(String gamemodeName) {
        return I_14_v.n_1700_B(gamemodeName, J_1907_R);
    }

    public static I_14_v n_1700_B(String targetName, I_14_v fallback) {
        for (I_14_v gametype : I_14_v.values()) {
            if (!gametype.v_4262_N.equals(targetName)) continue;
            return gametype;
        }
        return fallback;
    }

    private static /* synthetic */ I_14_v[] v_4262_N() {
        return new I_14_v[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E};
    }

    static {
        w_1484_f = I_14_v.v_4262_N();
    }
}


