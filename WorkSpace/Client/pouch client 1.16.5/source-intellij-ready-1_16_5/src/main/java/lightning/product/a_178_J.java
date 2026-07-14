/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.x_607_J;
import lombok.Generated;

public class a_178_J
implements x_607_J {
    private float n_1700_B;
    private float J_1907_R;
    private boolean R_4764_Y;
    private boolean G_564_y;
    private boolean P_1922_E;
    private boolean u_1723_Y;
    private boolean v_4262_N;
    private boolean w_1484_f;
    private double t_148_a;

    @Generated
    public float n_1700_B() {
        return this.n_1700_B;
    }

    @Generated
    public float J_1907_R() {
        return this.J_1907_R;
    }

    @Generated
    public boolean R_4764_Y() {
        return this.R_4764_Y;
    }

    @Generated
    public boolean G_564_y() {
        return this.G_564_y;
    }

    @Generated
    public boolean P_1922_E() {
        return this.P_1922_E;
    }

    @Generated
    public boolean u_1723_Y() {
        return this.u_1723_Y;
    }

    @Generated
    public boolean v_4262_N() {
        return this.v_4262_N;
    }

    @Generated
    public boolean w_1484_f() {
        return this.w_1484_f;
    }

    @Generated
    public double t_148_a() {
        return this.t_148_a;
    }

    @Generated
    public void n_1700_B(float forward) {
        this.n_1700_B = forward;
    }

    @Generated
    public void J_1907_R(float strafe) {
        this.J_1907_R = strafe;
    }

    @Generated
    public void n_1700_B(boolean forwardKeyDown) {
        this.R_4764_Y = forwardKeyDown;
    }

    @Generated
    public void J_1907_R(boolean backKeyDown) {
        this.G_564_y = backKeyDown;
    }

    @Generated
    public void R_4764_Y(boolean leftKeyDown) {
        this.P_1922_E = leftKeyDown;
    }

    @Generated
    public void G_564_y(boolean rightKeyDown) {
        this.u_1723_Y = rightKeyDown;
    }

    @Generated
    public void P_1922_E(boolean jump) {
        this.v_4262_N = jump;
    }

    @Generated
    public void u_1723_Y(boolean sneak) {
        this.w_1484_f = sneak;
    }

    @Generated
    public void n_1700_B(double sneakSlowDownMultiplier) {
        this.t_148_a = sneakSlowDownMultiplier;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof a_178_J)) {
            return false;
        }
        a_178_J other = (a_178_J)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (Float.compare(this.n_1700_B(), other.n_1700_B()) != 0) {
            return false;
        }
        if (Float.compare(this.J_1907_R(), other.J_1907_R()) != 0) {
            return false;
        }
        if (this.R_4764_Y() != other.R_4764_Y()) {
            return false;
        }
        if (this.G_564_y() != other.G_564_y()) {
            return false;
        }
        if (this.P_1922_E() != other.P_1922_E()) {
            return false;
        }
        if (this.u_1723_Y() != other.u_1723_Y()) {
            return false;
        }
        if (this.v_4262_N() != other.v_4262_N()) {
            return false;
        }
        if (this.w_1484_f() != other.w_1484_f()) {
            return false;
        }
        return Double.compare(this.t_148_a(), other.t_148_a()) == 0;
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof a_178_J;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + Float.floatToIntBits(this.n_1700_B());
        result = result * 59 + Float.floatToIntBits(this.J_1907_R());
        result = result * 59 + (this.R_4764_Y() ? 79 : 97);
        result = result * 59 + (this.G_564_y() ? 79 : 97);
        result = result * 59 + (this.P_1922_E() ? 79 : 97);
        result = result * 59 + (this.u_1723_Y() ? 79 : 97);
        result = result * 59 + (this.v_4262_N() ? 79 : 97);
        result = result * 59 + (this.w_1484_f() ? 79 : 97);
        long $sneakSlowDownMultiplier = Double.doubleToLongBits(this.t_148_a());
        result = result * 59 + (int)($sneakSlowDownMultiplier >>> 32 ^ $sneakSlowDownMultiplier);
        return result;
    }

    @Generated
    public String toString() {
        return "EventInput(forward=" + this.n_1700_B() + ", strafe=" + this.J_1907_R() + ", forwardKeyDown=" + this.R_4764_Y() + ", backKeyDown=" + this.G_564_y() + ", leftKeyDown=" + this.P_1922_E() + ", rightKeyDown=" + this.u_1723_Y() + ", jump=" + this.v_4262_N() + ", sneak=" + this.w_1484_f() + ", sneakSlowDownMultiplier=" + this.t_148_a() + ")";
    }

    @Generated
    public a_178_J(float forward, float strafe, boolean forwardKeyDown, boolean backKeyDown, boolean leftKeyDown, boolean rightKeyDown, boolean jump, boolean sneak, double sneakSlowDownMultiplier) {
        this.n_1700_B = forward;
        this.J_1907_R = strafe;
        this.R_4764_Y = forwardKeyDown;
        this.G_564_y = backKeyDown;
        this.P_1922_E = leftKeyDown;
        this.u_1723_Y = rightKeyDown;
        this.v_4262_N = jump;
        this.w_1484_f = sneak;
        this.t_148_a = sneakSlowDownMultiplier;
    }
}

