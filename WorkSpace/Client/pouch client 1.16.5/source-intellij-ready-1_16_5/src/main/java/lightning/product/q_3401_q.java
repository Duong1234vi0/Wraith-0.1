/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.P_3504_Q;
import lightning.product.d_2427_y;
import lightning.product.e_2866_D;
import lightning.product.x_607_J;
import lombok.Generated;

public class q_3401_q
extends d_2427_y
implements x_607_J {
    private e_2866_D n_1700_B;
    private P_3504_Q J_1907_R;

    @Generated
    public e_2866_D J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public P_3504_Q R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public void n_1700_B(e_2866_D position) {
        this.n_1700_B = position;
    }

    @Generated
    public void n_1700_B(P_3504_Q rotation) {
        this.J_1907_R = rotation;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof q_3401_q)) {
            return false;
        }
        q_3401_q other = (q_3401_q)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        e_2866_D this$position = this.J_1907_R();
        e_2866_D other$position = other.J_1907_R();
        if (this$position == null ? other$position != null : !((Object)this$position).equals(other$position)) {
            return false;
        }
        P_3504_Q this$rotation = this.R_4764_Y();
        P_3504_Q other$rotation = other.R_4764_Y();
        return !(this$rotation == null ? other$rotation != null : !this$rotation.equals(other$rotation));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof q_3401_q;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        e_2866_D $position = this.J_1907_R();
        result = result * 59 + ($position == null ? 43 : ((Object)$position).hashCode());
        P_3504_Q $rotation = this.R_4764_Y();
        result = result * 59 + ($rotation == null ? 43 : $rotation.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventRotation(position=" + String.valueOf(this.J_1907_R()) + ", rotation=" + String.valueOf(this.R_4764_Y()) + ")";
    }

    @Generated
    public q_3401_q(e_2866_D position, P_3504_Q rotation) {
        this.n_1700_B = position;
        this.J_1907_R = rotation;
    }
}

