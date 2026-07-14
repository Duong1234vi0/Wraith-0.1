/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.SoundInstance;
import lightning.product.d_2427_y;
import lightning.product.x_607_J;
import lombok.Generated;

public class Z_4720_K
extends d_2427_y
implements x_607_J {
    private SoundInstance n_1700_B;
    private float J_1907_R;

    @Generated
    public SoundInstance J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public float R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public void n_1700_B(SoundInstance iSound) {
        this.n_1700_B = iSound;
    }

    @Generated
    public void n_1700_B(float factor) {
        this.J_1907_R = factor;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Z_4720_K)) {
            return false;
        }
        Z_4720_K other = (Z_4720_K)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (Float.compare(this.R_4764_Y(), other.R_4764_Y()) != 0) {
            return false;
        }
        SoundInstance this$iSound = this.J_1907_R();
        SoundInstance other$iSound = other.J_1907_R();
        return !(this$iSound == null ? other$iSound != null : !this$iSound.equals(other$iSound));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof Z_4720_K;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + Float.floatToIntBits(this.R_4764_Y());
        SoundInstance $iSound = this.J_1907_R();
        result = result * 59 + ($iSound == null ? 43 : $iSound.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventSound(iSound=" + String.valueOf(this.J_1907_R()) + ", factor=" + this.R_4764_Y() + ")";
    }

    @Generated
    public Z_4720_K(SoundInstance iSound, float factor) {
        this.n_1700_B = iSound;
        this.J_1907_R = factor;
    }
}


