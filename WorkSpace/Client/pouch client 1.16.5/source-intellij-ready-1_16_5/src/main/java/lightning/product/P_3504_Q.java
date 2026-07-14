/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lombok.Generated;

public class P_3504_Q {
    public static final P_3504_Q n_1700_B = new P_3504_Q(0.0f, 0.0f);
    public static final P_3504_Q J_1907_R = new P_3504_Q(1.0f, 1.0f);
    public static final P_3504_Q R_4764_Y = new P_3504_Q(1.0f, 0.0f);
    public static final P_3504_Q G_564_y = new P_3504_Q(-1.0f, 0.0f);
    public static final P_3504_Q P_1922_E = new P_3504_Q(0.0f, 1.0f);
    public static final P_3504_Q u_1723_Y = new P_3504_Q(0.0f, -1.0f);
    public static final P_3504_Q v_4262_N = new P_3504_Q(Float.MAX_VALUE, Float.MAX_VALUE);
    public static final P_3504_Q w_1484_f = new P_3504_Q(Float.MIN_VALUE, Float.MIN_VALUE);
    public float t_148_a;
    public float s_956_w;

    public P_3504_Q(float xIn, float yIn) {
        this.t_148_a = xIn;
        this.s_956_w = yIn;
    }

    public boolean n_1700_B(P_3504_Q other) {
        return this.t_148_a == other.t_148_a && this.s_956_w == other.s_956_w;
    }

    @Generated
    public float n_1700_B() {
        return this.t_148_a;
    }

    @Generated
    public float J_1907_R() {
        return this.s_956_w;
    }
}

