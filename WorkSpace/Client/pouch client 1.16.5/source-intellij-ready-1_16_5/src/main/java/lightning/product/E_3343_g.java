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

public class E_3343_g
extends d_2427_y
implements x_607_J {
    public n_1700_B n_1700_B;

    @Generated
    public n_1700_B J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public void n_1700_B(n_1700_B state) {
        this.n_1700_B = state;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof E_3343_g)) {
            return false;
        }
        E_3343_g other = (E_3343_g)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        n_1700_B this$state = this.J_1907_R();
        n_1700_B other$state = other.J_1907_R();
        return !(this$state == null ? other$state != null : !((Object)((Object)this$state)).equals((Object)other$state));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof E_3343_g;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        n_1700_B $state = this.J_1907_R();
        result = result * 59 + ($state == null ? 43 : ((Object)((Object)$state)).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventSwapWorld(state=" + String.valueOf((Object)this.J_1907_R()) + ")";
    }

    @Generated
    public E_3343_g(n_1700_B state) {
        this.n_1700_B = state;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] R_4764_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])R_4764_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            R_4764_Y = lightning.product.E_3343_g$n_1700_B.n_1700_B();
        }
    }
}

