/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;
import java.util.Comparator;
import lightning.product.F_2904_S;
import lightning.product.u_530_F;
import lightning.product.x_282_a;

public final class e_3022_i
extends Enum<e_3022_i> {
    public static final /* enum */ e_3022_i n_1700_B = new e_3022_i(0, "options.narrator.off");
    public static final /* enum */ e_3022_i J_1907_R = new e_3022_i(1, "options.narrator.all");
    public static final /* enum */ e_3022_i R_4764_Y = new e_3022_i(2, "options.narrator.chat");
    public static final /* enum */ e_3022_i G_564_y = new e_3022_i(3, "options.narrator.system");
    private static final e_3022_i[] P_1922_E;
    private final int u_1723_Y;
    private final x_282_a v_4262_N;
    private static final /* synthetic */ e_3022_i[] w_1484_f;

    public static e_3022_i[] values() {
        return (e_3022_i[])w_1484_f.clone();
    }

    public static e_3022_i valueOf(String name) {
        return Enum.valueOf(e_3022_i.class, name);
    }

    private e_3022_i(int id, String resourceKeyIn) {
        this.u_1723_Y = id;
        this.v_4262_N = new F_2904_S(resourceKeyIn);
    }

    public int n_1700_B() {
        return this.u_1723_Y;
    }

    public x_282_a J_1907_R() {
        return this.v_4262_N;
    }

    public static e_3022_i n_1700_B(int id) {
        return P_1922_E[u_530_F.J_1907_R(id, P_1922_E.length)];
    }

    private static /* synthetic */ e_3022_i[] R_4764_Y() {
        return new e_3022_i[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
    }

    static {
        w_1484_f = e_3022_i.R_4764_Y();
        P_1922_E = (e_3022_i[])Arrays.stream(e_3022_i.values()).sorted(Comparator.comparingInt(e_3022_i::n_1700_B)).toArray(e_3022_i[]::new);
    }
}

