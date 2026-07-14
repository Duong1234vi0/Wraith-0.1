/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.d_2427_y;
import lightning.product.x_607_J;
import lombok.Generated;

public class h_3270_j
extends d_2427_y
implements x_607_J {
    public final n_1700_B n_1700_B;

    @Generated
    public h_3270_j(n_1700_B norenderType) {
        this.n_1700_B = norenderType;
    }

    @Generated
    public n_1700_B J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof h_3270_j)) {
            return false;
        }
        h_3270_j other = (h_3270_j)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        n_1700_B this$norenderType = this.J_1907_R();
        n_1700_B other$norenderType = other.J_1907_R();
        return !(this$norenderType == null ? other$norenderType != null : !((Object)((Object)this$norenderType)).equals((Object)other$norenderType));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof h_3270_j;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        n_1700_B $norenderType = this.J_1907_R();
        result = result * 59 + ($norenderType == null ? 43 : ((Object)((Object)$norenderType)).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventNoRender(norenderType=" + String.valueOf((Object)this.J_1907_R()) + ")";
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B();
        public static final /* enum */ n_1700_B u_1723_Y = new n_1700_B();
        public static final /* enum */ n_1700_B v_4262_N = new n_1700_B();
        public static final /* enum */ n_1700_B w_1484_f = new n_1700_B();
        public static final /* enum */ n_1700_B t_148_a = new n_1700_B();
        public static final /* enum */ n_1700_B s_956_w = new n_1700_B();
        public static final /* enum */ n_1700_B u_2550_I = new n_1700_B();
        public static final /* enum */ n_1700_B M_588_G = new n_1700_B();
        public static final /* enum */ n_1700_B P_4830_p = new n_1700_B();
        public static final /* enum */ n_1700_B h_1847_R = new n_1700_B();
        public static final /* enum */ n_1700_B Q_4569_t = new n_1700_B();
        public static final /* enum */ n_1700_B M_182_A = new n_1700_B();
        public static final /* enum */ n_1700_B t_1786_h = new n_1700_B();
        public static final /* enum */ n_1700_B multiplayerClientSuggestionProvider = new n_1700_B();
        public static final /* enum */ n_1700_B w_1457_N = new n_1700_B();
        public static final /* enum */ n_1700_B Y_601_j = new n_1700_B();
        public static final /* enum */ n_1700_B Y_259_p = new n_1700_B();
        public static final /* enum */ n_1700_B Q_2552_b = new n_1700_B();
        public static final /* enum */ n_1700_B C_2741_M = new n_1700_B();
        public static final /* enum */ n_1700_B k_2293_S = new n_1700_B();
        public static final /* enum */ n_1700_B q_2307_F = new n_1700_B();
        public static final /* enum */ n_1700_B Z_875_P = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] c_3005_b;

        public static n_1700_B[] values() {
            return (n_1700_B[])c_3005_b.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a, s_956_w, u_2550_I, M_588_G, P_4830_p, h_1847_R, Q_4569_t, M_182_A, t_1786_h, multiplayerClientSuggestionProvider, w_1457_N, Y_601_j, Y_259_p, Q_2552_b, C_2741_M, k_2293_S, q_2307_F, Z_875_P};
        }

        static {
            c_3005_b = lightning.product.h_3270_j$n_1700_B.n_1700_B();
        }
    }
}


