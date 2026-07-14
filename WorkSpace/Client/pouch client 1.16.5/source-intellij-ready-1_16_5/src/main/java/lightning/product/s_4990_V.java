/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.d_2427_y;
import lightning.product.e_2866_D;
import lightning.product.x_607_J;
import lombok.Generated;

public class s_4990_V
extends d_2427_y
implements x_607_J {
    private float n_1700_B;
    private float J_1907_R;
    private float R_4764_Y;
    private float G_564_y;
    private e_2866_D P_1922_E;

    public s_4990_V(float boostMultiplier, float baseBoost, float smoothingFactor, e_2866_D vec) {
        this.n_1700_B = boostMultiplier;
        this.J_1907_R = baseBoost;
        this.R_4764_Y = smoothingFactor;
        this.G_564_y = 1.0f;
        this.P_1922_E = vec;
    }

    @Generated
    public float J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public float R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public float G_564_y() {
        return this.R_4764_Y;
    }

    @Generated
    public float P_1922_E() {
        return this.G_564_y;
    }

    @Generated
    public e_2866_D u_1723_Y() {
        return this.P_1922_E;
    }

    @Generated
    public void n_1700_B(float boostMultiplier) {
        this.n_1700_B = boostMultiplier;
    }

    @Generated
    public void J_1907_R(float baseBoost) {
        this.J_1907_R = baseBoost;
    }

    @Generated
    public void R_4764_Y(float smoothingFactor) {
        this.R_4764_Y = smoothingFactor;
    }

    @Generated
    public void G_564_y(float ySpeed) {
        this.G_564_y = ySpeed;
    }

    @Generated
    public void n_1700_B(e_2866_D vector3d) {
        this.P_1922_E = vector3d;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof s_4990_V)) {
            return false;
        }
        s_4990_V other = (s_4990_V)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (Float.compare(this.J_1907_R(), other.J_1907_R()) != 0) {
            return false;
        }
        if (Float.compare(this.R_4764_Y(), other.R_4764_Y()) != 0) {
            return false;
        }
        if (Float.compare(this.G_564_y(), other.G_564_y()) != 0) {
            return false;
        }
        if (Float.compare(this.P_1922_E(), other.P_1922_E()) != 0) {
            return false;
        }
        e_2866_D this$vector3d = this.u_1723_Y();
        e_2866_D other$vector3d = other.u_1723_Y();
        return !(this$vector3d == null ? other$vector3d != null : !((Object)this$vector3d).equals(other$vector3d));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof s_4990_V;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + Float.floatToIntBits(this.J_1907_R());
        result = result * 59 + Float.floatToIntBits(this.R_4764_Y());
        result = result * 59 + Float.floatToIntBits(this.G_564_y());
        result = result * 59 + Float.floatToIntBits(this.P_1922_E());
        e_2866_D $vector3d = this.u_1723_Y();
        result = result * 59 + ($vector3d == null ? 43 : ((Object)$vector3d).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventFireworkRocket(boostMultiplier=" + this.J_1907_R() + ", baseBoost=" + this.R_4764_Y() + ", smoothingFactor=" + this.G_564_y() + ", ySpeed=" + this.P_1922_E() + ", vector3d=" + String.valueOf(this.u_1723_Y()) + ")";
    }
}

