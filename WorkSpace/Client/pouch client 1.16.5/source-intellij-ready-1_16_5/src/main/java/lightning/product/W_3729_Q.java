/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.d_2427_y;
import lightning.product.r_4811_B;
import lightning.product.x_607_J;
import lombok.Generated;

public class W_3729_Q
extends d_2427_y
implements x_607_J {
    private Z_1993_T n_1700_B;
    private b_4507_u J_1907_R;
    private r_4811_B R_4764_Y;

    @Generated
    public Z_1993_T J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public b_4507_u R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public r_4811_B G_564_y() {
        return this.R_4764_Y;
    }

    @Generated
    public void n_1700_B(Z_1993_T itemStack) {
        this.n_1700_B = itemStack;
    }

    @Generated
    public void n_1700_B(b_4507_u worldIn) {
        this.J_1907_R = worldIn;
    }

    @Generated
    public void n_1700_B(r_4811_B entityLiving) {
        this.R_4764_Y = entityLiving;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof W_3729_Q)) {
            return false;
        }
        W_3729_Q other = (W_3729_Q)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        Z_1993_T this$itemStack = this.J_1907_R();
        Z_1993_T other$itemStack = other.J_1907_R();
        if (this$itemStack == null ? other$itemStack != null : !this$itemStack.equals(other$itemStack)) {
            return false;
        }
        b_4507_u this$worldIn = this.R_4764_Y();
        b_4507_u other$worldIn = other.R_4764_Y();
        if (this$worldIn == null ? other$worldIn != null : !this$worldIn.equals(other$worldIn)) {
            return false;
        }
        r_4811_B this$entityLiving = this.G_564_y();
        r_4811_B other$entityLiving = other.G_564_y();
        return !(this$entityLiving == null ? other$entityLiving != null : !((Object)this$entityLiving).equals(other$entityLiving));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof W_3729_Q;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Z_1993_T $itemStack = this.J_1907_R();
        result = result * 59 + ($itemStack == null ? 43 : $itemStack.hashCode());
        b_4507_u $worldIn = this.R_4764_Y();
        result = result * 59 + ($worldIn == null ? 43 : $worldIn.hashCode());
        r_4811_B $entityLiving = this.G_564_y();
        result = result * 59 + ($entityLiving == null ? 43 : ((Object)$entityLiving).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventUseFinish(itemStack=" + String.valueOf(this.J_1907_R()) + ", worldIn=" + String.valueOf(this.R_4764_Y()) + ", entityLiving=" + String.valueOf(this.G_564_y()) + ")";
    }

    @Generated
    public W_3729_Q(Z_1993_T itemStack, b_4507_u worldIn, r_4811_B entityLiving) {
        this.n_1700_B = itemStack;
        this.J_1907_R = worldIn;
        this.R_4764_Y = entityLiving;
    }
}

