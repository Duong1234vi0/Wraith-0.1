/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.K_4074_S;
import lightning.product.c_1514_x;
import lightning.product.d_2427_y;
import lightning.product.x_607_J;
import lombok.Generated;

public class x_4991_F
extends d_2427_y
implements x_607_J {
    private K_4074_S n_1700_B;
    private c_1514_x J_1907_R;
    private n_1700_B R_4764_Y;

    @Generated
    public K_4074_S J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public c_1514_x R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public n_1700_B G_564_y() {
        return this.R_4764_Y;
    }

    @Generated
    public void n_1700_B(K_4074_S blockState) {
        this.n_1700_B = blockState;
    }

    @Generated
    public void n_1700_B(c_1514_x pos) {
        this.J_1907_R = pos;
    }

    @Generated
    public void n_1700_B(n_1700_B state) {
        this.R_4764_Y = state;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof x_4991_F)) {
            return false;
        }
        x_4991_F other = (x_4991_F)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        K_4074_S this$blockState = this.J_1907_R();
        K_4074_S other$blockState = other.J_1907_R();
        if (this$blockState == null ? other$blockState != null : !this$blockState.equals(other$blockState)) {
            return false;
        }
        c_1514_x this$pos = this.R_4764_Y();
        c_1514_x other$pos = other.R_4764_Y();
        if (this$pos == null ? other$pos != null : !((Object)this$pos).equals(other$pos)) {
            return false;
        }
        n_1700_B this$state = this.G_564_y();
        n_1700_B other$state = other.G_564_y();
        return !(this$state == null ? other$state != null : !((Object)((Object)this$state)).equals((Object)other$state));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof x_4991_F;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        K_4074_S $blockState = this.J_1907_R();
        result = result * 59 + ($blockState == null ? 43 : $blockState.hashCode());
        c_1514_x $pos = this.R_4764_Y();
        result = result * 59 + ($pos == null ? 43 : ((Object)$pos).hashCode());
        n_1700_B $state = this.G_564_y();
        result = result * 59 + ($state == null ? 43 : ((Object)((Object)$state)).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventBlockDamage(blockState=" + String.valueOf(this.J_1907_R()) + ", pos=" + String.valueOf(this.R_4764_Y()) + ", state=" + String.valueOf((Object)this.G_564_y()) + ")";
    }

    @Generated
    public x_4991_F(K_4074_S blockState, c_1514_x pos, n_1700_B state) {
        this.n_1700_B = blockState;
        this.J_1907_R = pos;
        this.R_4764_Y = state;
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
            R_4764_Y = lightning.product.x_4991_F$n_1700_B.n_1700_B();
        }
    }
}

