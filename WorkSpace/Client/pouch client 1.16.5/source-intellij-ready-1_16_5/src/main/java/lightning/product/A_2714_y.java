/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.k_4231_L;
import lightning.product.x_607_J;
import lombok.Generated;

public class A_2714_y
implements x_607_J {
    private k_4231_L n_1700_B;
    private float J_1907_R;

    @Generated
    public k_4231_L n_1700_B() {
        return this.n_1700_B;
    }

    @Generated
    public float J_1907_R() {
        return this.J_1907_R;
    }

    @Generated
    public void n_1700_B(k_4231_L handSide) {
        this.n_1700_B = handSide;
    }

    @Generated
    public void n_1700_B(float equippedProg) {
        this.J_1907_R = equippedProg;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof A_2714_y)) {
            return false;
        }
        A_2714_y other = (A_2714_y)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (Float.compare(this.J_1907_R(), other.J_1907_R()) != 0) {
            return false;
        }
        k_4231_L this$handSide = this.n_1700_B();
        k_4231_L other$handSide = other.n_1700_B();
        return !(this$handSide == null ? other$handSide != null : !((Object)((Object)this$handSide)).equals((Object)other$handSide));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof A_2714_y;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + Float.floatToIntBits(this.J_1907_R());
        k_4231_L $handSide = this.n_1700_B();
        result = result * 59 + ($handSide == null ? 43 : ((Object)((Object)$handSide)).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventTransformSideFirstPerson(handSide=" + String.valueOf((Object)this.n_1700_B()) + ", equippedProg=" + this.J_1907_R() + ")";
    }

    @Generated
    public A_2714_y(k_4231_L handSide, float equippedProg) {
        this.n_1700_B = handSide;
        this.J_1907_R = equippedProg;
    }
}

