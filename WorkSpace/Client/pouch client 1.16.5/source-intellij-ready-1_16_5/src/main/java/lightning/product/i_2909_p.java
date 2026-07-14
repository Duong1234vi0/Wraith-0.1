/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class i_2909_p {
    private final n_1700_B n_1700_B;
    private final String J_1907_R;

    public i_2909_p(n_1700_B theAction, String theValue) {
        this.n_1700_B = theAction;
        this.J_1907_R = theValue;
    }

    public n_1700_B n_1700_B() {
        return this.n_1700_B;
    }

    public String J_1907_R() {
        return this.J_1907_R;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            i_2909_p clickevent = (i_2909_p)p_equals_1_;
            if (this.n_1700_B != clickevent.n_1700_B) {
                return false;
            }
            return !(this.J_1907_R != null ? !this.J_1907_R.equals(clickevent.J_1907_R) : clickevent.J_1907_R != null);
        }
        return false;
    }

    public String toString() {
        return "ClickEvent{action=" + String.valueOf((Object)this.n_1700_B) + ", value='" + this.J_1907_R + "'}";
    }

    public int hashCode() {
        int i = this.n_1700_B.hashCode();
        return 31 * i + (this.J_1907_R != null ? this.J_1907_R.hashCode() : 0);
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("open_url", true);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("open_file", false);
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B("run_command", true);
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B("suggest_command", true);
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B("change_page", true);
        public static final /* enum */ n_1700_B u_1723_Y = new n_1700_B("copy_to_clipboard", true);
        private static final Map<String, n_1700_B> v_4262_N;
        private final boolean w_1484_f;
        private final String t_148_a;
        private static final /* synthetic */ n_1700_B[] s_956_w;

        public static n_1700_B[] values() {
            return (n_1700_B[])s_956_w.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String canonicalNameIn, boolean allowedInChatIn) {
            this.t_148_a = canonicalNameIn;
            this.w_1484_f = allowedInChatIn;
        }

        public boolean n_1700_B() {
            return this.w_1484_f;
        }

        public String J_1907_R() {
            return this.t_148_a;
        }

        public static n_1700_B n_1700_B(String canonicalNameIn) {
            return v_4262_N.get(canonicalNameIn);
        }

        private static /* synthetic */ n_1700_B[] R_4764_Y() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y};
        }

        static {
            s_956_w = lightning.product.i_2909_p$n_1700_B.R_4764_Y();
            v_4262_N = Arrays.stream(lightning.product.i_2909_p$n_1700_B.values()).collect(Collectors.toMap(n_1700_B::J_1907_R, action -> action));
        }
    }
}

