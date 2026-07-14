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

public class I_685_r
extends d_2427_y
implements x_607_J {
    public final n_1700_B n_1700_B;

    @Generated
    public I_685_r(n_1700_B noPushType) {
        this.n_1700_B = noPushType;
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
        if (!(o instanceof I_685_r)) {
            return false;
        }
        I_685_r other = (I_685_r)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        n_1700_B this$noPushType = this.J_1907_R();
        n_1700_B other$noPushType = other.J_1907_R();
        return !(this$noPushType == null ? other$noPushType != null : !((Object)((Object)this$noPushType)).equals((Object)other$noPushType));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof I_685_r;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        n_1700_B $noPushType = this.J_1907_R();
        result = result * 59 + ($noPushType == null ? 43 : ((Object)((Object)$noPushType)).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventNoPush(noPushType=" + String.valueOf((Object)this.J_1907_R()) + ")";
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
        private static final /* synthetic */ n_1700_B[] w_1484_f;

        public static n_1700_B[] values() {
            return (n_1700_B[])w_1484_f.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N};
        }

        static {
            w_1484_f = lightning.product.I_685_r$n_1700_B.n_1700_B();
        }
    }
}

