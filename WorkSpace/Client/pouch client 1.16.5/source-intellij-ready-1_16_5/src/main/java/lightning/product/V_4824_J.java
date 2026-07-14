/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public final class V_4824_J
extends Enum<V_4824_J> {
    public static final /* enum */ V_4824_J n_1700_B = new V_4824_J(-3);
    public static final /* enum */ V_4824_J J_1907_R = new V_4824_J(-2);
    public static final /* enum */ V_4824_J R_4764_Y = new V_4824_J(-1);
    public static final /* enum */ V_4824_J G_564_y = new V_4824_J(0);
    public static final /* enum */ V_4824_J P_1922_E = new V_4824_J(1);
    public static final /* enum */ V_4824_J u_1723_Y = new V_4824_J(2);
    public static final /* enum */ V_4824_J v_4262_N = new V_4824_J(3);
    private final int w_1484_f;
    private static final /* synthetic */ V_4824_J[] t_148_a;

    public static V_4824_J[] values() {
        return (V_4824_J[])t_148_a.clone();
    }

    public static V_4824_J valueOf(String name) {
        return Enum.valueOf(V_4824_J.class, name);
    }

    private V_4824_J(int priority) {
        this.w_1484_f = priority;
    }

    public static V_4824_J n_1700_B(int priority) {
        for (V_4824_J tickpriority : V_4824_J.values()) {
            if (tickpriority.w_1484_f != priority) continue;
            return tickpriority;
        }
        return priority < V_4824_J.n_1700_B.w_1484_f ? n_1700_B : v_4262_N;
    }

    public int n_1700_B() {
        return this.w_1484_f;
    }

    private static /* synthetic */ V_4824_J[] J_1907_R() {
        return new V_4824_J[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N};
    }

    static {
        t_148_a = V_4824_J.J_1907_R();
    }
}

