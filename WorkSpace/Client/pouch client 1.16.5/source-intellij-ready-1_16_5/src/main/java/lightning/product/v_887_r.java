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

public class v_887_r
extends d_2427_y
implements x_607_J {
    private final n_1700_B n_1700_B;

    @Generated
    public n_1700_B J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof v_887_r)) {
            return false;
        }
        v_887_r other = (v_887_r)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        n_1700_B this$damageType = this.J_1907_R();
        n_1700_B other$damageType = other.J_1907_R();
        return !(this$damageType == null ? other$damageType != null : !((Object)((Object)this$damageType)).equals((Object)other$damageType));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof v_887_r;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        n_1700_B $damageType = this.J_1907_R();
        result = result * 59 + ($damageType == null ? 43 : ((Object)((Object)$damageType)).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventDamage(damageType=" + String.valueOf((Object)this.J_1907_R()) + ")";
    }

    @Generated
    public v_887_r(n_1700_B damageType) {
        this.n_1700_B = damageType;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] P_1922_E;

        public static n_1700_B[] values() {
            return (n_1700_B[])P_1922_E.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            P_1922_E = lightning.product.v_887_r$n_1700_B.n_1700_B();
        }
    }
}

