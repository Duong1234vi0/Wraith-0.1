/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.d_2427_y;
import lightning.product.x_607_J;
import lombok.Generated;

public class C_415_h
extends d_2427_y
implements x_607_J {
    private N_4263_v n_1700_B;
    private float J_1907_R;

    @Generated
    public N_4263_v J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public float R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public void n_1700_B(N_4263_v entity) {
        this.n_1700_B = entity;
    }

    @Generated
    public void n_1700_B(float size) {
        this.J_1907_R = size;
    }

    @Generated
    public String toString() {
        return "EventEntityHitBox(entity=" + String.valueOf(this.J_1907_R()) + ", size=" + this.R_4764_Y() + ")";
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof C_415_h)) {
            return false;
        }
        C_415_h other = (C_415_h)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        if (Float.compare(this.R_4764_Y(), other.R_4764_Y()) != 0) {
            return false;
        }
        N_4263_v this$entity = this.J_1907_R();
        N_4263_v other$entity = other.J_1907_R();
        return !(this$entity == null ? other$entity != null : !((Object)this$entity).equals(other$entity));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof C_415_h;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = super.hashCode();
        result = result * 59 + Float.floatToIntBits(this.R_4764_Y());
        N_4263_v $entity = this.J_1907_R();
        result = result * 59 + ($entity == null ? 43 : ((Object)$entity).hashCode());
        return result;
    }

    @Generated
    public C_415_h(N_4263_v entity, float size) {
        this.n_1700_B = entity;
        this.J_1907_R = size;
    }
}

