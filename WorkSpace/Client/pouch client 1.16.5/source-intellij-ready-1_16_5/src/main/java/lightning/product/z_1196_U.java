/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 */
package lightning.product;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.Set;
import lightning.product.b_257_Y;

public final class z_1196_U
extends Enum<z_1196_U> {
    public static final /* enum */ z_1196_U n_1700_B = new z_1196_U(b_257_Y.R_4764_Y);
    public static final /* enum */ z_1196_U J_1907_R = new z_1196_U(b_257_Y.R_4764_Y, b_257_Y.u_1723_Y);
    public static final /* enum */ z_1196_U R_4764_Y = new z_1196_U(b_257_Y.u_1723_Y);
    public static final /* enum */ z_1196_U G_564_y = new z_1196_U(b_257_Y.G_564_y, b_257_Y.u_1723_Y);
    public static final /* enum */ z_1196_U P_1922_E = new z_1196_U(b_257_Y.G_564_y);
    public static final /* enum */ z_1196_U u_1723_Y = new z_1196_U(b_257_Y.G_564_y, b_257_Y.P_1922_E);
    public static final /* enum */ z_1196_U v_4262_N = new z_1196_U(b_257_Y.P_1922_E);
    public static final /* enum */ z_1196_U w_1484_f = new z_1196_U(b_257_Y.R_4764_Y, b_257_Y.P_1922_E);
    private static final int t_148_a;
    private static final int s_956_w;
    private static final int u_2550_I;
    private static final int M_588_G;
    private static final int P_4830_p;
    private static final int h_1847_R;
    private static final int Q_4569_t;
    private static final int M_182_A;
    private final Set<b_257_Y> t_1786_h;
    private static final /* synthetic */ z_1196_U[] multiplayerClientSuggestionProvider;

    public static z_1196_U[] values() {
        return (z_1196_U[])multiplayerClientSuggestionProvider.clone();
    }

    public static z_1196_U valueOf(String name) {
        return Enum.valueOf(z_1196_U.class, name);
    }

    private z_1196_U(b_257_Y ... directionsIn) {
        this.t_1786_h = Sets.immutableEnumSet(Arrays.asList(directionsIn));
    }

    public Set<b_257_Y> n_1700_B() {
        return this.t_1786_h;
    }

    private static /* synthetic */ z_1196_U[] J_1907_R() {
        return new z_1196_U[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f};
    }

    static {
        multiplayerClientSuggestionProvider = z_1196_U.J_1907_R();
        t_148_a = 1 << w_1484_f.ordinal();
        s_956_w = 1 << v_4262_N.ordinal();
        u_2550_I = 1 << u_1723_Y.ordinal();
        M_588_G = 1 << P_1922_E.ordinal();
        P_4830_p = 1 << G_564_y.ordinal();
        h_1847_R = 1 << R_4764_Y.ordinal();
        Q_4569_t = 1 << J_1907_R.ordinal();
        M_182_A = 1 << n_1700_B.ordinal();
    }
}


