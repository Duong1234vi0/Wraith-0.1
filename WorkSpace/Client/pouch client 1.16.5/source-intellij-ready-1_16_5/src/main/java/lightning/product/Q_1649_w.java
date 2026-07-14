/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;
import javax.annotation.Nullable;

public final class Q_1649_w
extends Enum<Q_1649_w> {
    public static final /* enum */ Q_1649_w n_1700_B = new Q_1649_w("major_negative", -5, 100, 10, 10);
    public static final /* enum */ Q_1649_w J_1907_R = new Q_1649_w("minor_negative", -1, 200, 20, 20);
    public static final /* enum */ Q_1649_w R_4764_Y = new Q_1649_w("minor_positive", 1, 200, 1, 5);
    public static final /* enum */ Q_1649_w G_564_y = new Q_1649_w("major_positive", 5, 100, 0, 100);
    public static final /* enum */ Q_1649_w P_1922_E = new Q_1649_w("trading", 1, 25, 2, 20);
    public final String u_1723_Y;
    public final int v_4262_N;
    public final int w_1484_f;
    public final int t_148_a;
    public final int s_956_w;
    private static final Map<String, Q_1649_w> u_2550_I;
    private static final /* synthetic */ Q_1649_w[] M_588_G;

    public static Q_1649_w[] values() {
        return (Q_1649_w[])M_588_G.clone();
    }

    public static Q_1649_w valueOf(String name) {
        return Enum.valueOf(Q_1649_w.class, name);
    }

    private Q_1649_w(String id, int weight, int max, int decayPerDay, int decayPerTransfer) {
        this.u_1723_Y = id;
        this.v_4262_N = weight;
        this.w_1484_f = max;
        this.t_148_a = decayPerDay;
        this.s_956_w = decayPerTransfer;
    }

    @Nullable
    public static Q_1649_w n_1700_B(String id) {
        return u_2550_I.get(id);
    }

    private static /* synthetic */ Q_1649_w[] n_1700_B() {
        return new Q_1649_w[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E};
    }

    static {
        M_588_G = Q_1649_w.n_1700_B();
        u_2550_I = (Map)Stream.of(Q_1649_w.values()).collect(ImmutableMap.toImmutableMap(p_220930_0_ -> p_220930_0_.u_1723_Y, Function.identity()));
    }
}

