/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class D_38_f
extends Enum<D_38_f> {
    public static final /* enum */ D_38_f n_1700_B = new D_38_f("master");
    public static final /* enum */ D_38_f J_1907_R = new D_38_f("music");
    public static final /* enum */ D_38_f R_4764_Y = new D_38_f("record");
    public static final /* enum */ D_38_f G_564_y = new D_38_f("weather");
    public static final /* enum */ D_38_f P_1922_E = new D_38_f("block");
    public static final /* enum */ D_38_f u_1723_Y = new D_38_f("hostile");
    public static final /* enum */ D_38_f v_4262_N = new D_38_f("neutral");
    public static final /* enum */ D_38_f w_1484_f = new D_38_f("player");
    public static final /* enum */ D_38_f t_148_a = new D_38_f("ambient");
    public static final /* enum */ D_38_f s_956_w = new D_38_f("voice");
    private static final Map<String, D_38_f> u_2550_I;
    private final String M_588_G;
    private static final /* synthetic */ D_38_f[] P_4830_p;

    public static D_38_f[] values() {
        return (D_38_f[])P_4830_p.clone();
    }

    public static D_38_f valueOf(String name) {
        return Enum.valueOf(D_38_f.class, name);
    }

    private D_38_f(String nameIn) {
        this.M_588_G = nameIn;
    }

    public String n_1700_B() {
        return this.M_588_G;
    }

    private static /* synthetic */ D_38_f[] J_1907_R() {
        return new D_38_f[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a, s_956_w};
    }

    static {
        P_4830_p = D_38_f.J_1907_R();
        u_2550_I = Arrays.stream(D_38_f.values()).collect(Collectors.toMap(D_38_f::n_1700_B, Function.identity()));
    }
}

