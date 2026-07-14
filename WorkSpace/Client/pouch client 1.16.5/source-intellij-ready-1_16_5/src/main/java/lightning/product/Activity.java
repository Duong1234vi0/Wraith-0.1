/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.V_3137_a;

public class Activity {
    public static final Activity n_1700_B = Activity.n_1700_B("core");
    public static final Activity J_1907_R = Activity.n_1700_B("idle");
    public static final Activity R_4764_Y = Activity.n_1700_B("work");
    public static final Activity G_564_y = Activity.n_1700_B("play");
    public static final Activity P_1922_E = Activity.n_1700_B("rest");
    public static final Activity u_1723_Y = Activity.n_1700_B("meet");
    public static final Activity v_4262_N = Activity.n_1700_B("panic");
    public static final Activity w_1484_f = Activity.n_1700_B("raid");
    public static final Activity t_148_a = Activity.n_1700_B("pre_raid");
    public static final Activity s_956_w = Activity.n_1700_B("hide");
    public static final Activity u_2550_I = Activity.n_1700_B("fight");
    public static final Activity M_588_G = Activity.n_1700_B("celebrate");
    public static final Activity P_4830_p = Activity.n_1700_B("admire_item");
    public static final Activity h_1847_R = Activity.n_1700_B("avoid");
    public static final Activity Q_4569_t = Activity.n_1700_B("ride");
    private final String M_182_A;
    private final int t_1786_h;

    private Activity(String key) {
        this.M_182_A = key;
        this.t_1786_h = key.hashCode();
    }

    public String n_1700_B() {
        return this.M_182_A;
    }

    private static Activity n_1700_B(String key) {
        return V_3137_a.n_1700_B(V_3137_a.RealmsClientConfig, key, new Activity(key));
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            Activity activity = (Activity)p_equals_1_;
            return this.M_182_A.equals(activity.M_182_A);
        }
        return false;
    }

    public int hashCode() {
        return this.t_1786_h;
    }

    public String toString() {
        return this.n_1700_B();
    }
}


