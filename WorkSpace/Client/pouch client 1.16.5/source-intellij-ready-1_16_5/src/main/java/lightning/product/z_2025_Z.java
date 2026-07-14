/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.r_4811_B;
import lightning.product.x_607_J;
import lombok.Generated;

public class z_2025_Z
implements x_607_J {
    private r_4811_B n_1700_B;
    private float J_1907_R;
    private float R_4764_Y;
    private float G_564_y;
    private float P_1922_E;
    private float u_1723_Y;
    private float v_4262_N;
    private float w_1484_f;
    private float t_148_a;
    private float s_956_w;

    @Generated
    public r_4811_B n_1700_B() {
        return this.n_1700_B;
    }

    @Generated
    public float J_1907_R() {
        return this.J_1907_R;
    }

    @Generated
    public float R_4764_Y() {
        return this.R_4764_Y;
    }

    @Generated
    public float G_564_y() {
        return this.G_564_y;
    }

    @Generated
    public float P_1922_E() {
        return this.P_1922_E;
    }

    @Generated
    public float u_1723_Y() {
        return this.u_1723_Y;
    }

    @Generated
    public float v_4262_N() {
        return this.v_4262_N;
    }

    @Generated
    public float w_1484_f() {
        return this.w_1484_f;
    }

    @Generated
    public float t_148_a() {
        return this.t_148_a;
    }

    @Generated
    public float s_956_w() {
        return this.s_956_w;
    }

    @Generated
    public void n_1700_B(r_4811_B entity) {
        this.n_1700_B = entity;
    }

    @Generated
    public void n_1700_B(float partialTicks) {
        this.J_1907_R = partialTicks;
    }

    @Generated
    public void J_1907_R(float renderYawOffset) {
        this.R_4764_Y = renderYawOffset;
    }

    @Generated
    public void R_4764_Y(float prevRenderYawOffset) {
        this.G_564_y = prevRenderYawOffset;
    }

    @Generated
    public void G_564_y(float rotationYawHead) {
        this.P_1922_E = rotationYawHead;
    }

    @Generated
    public void P_1922_E(float prevRotationYawHead) {
        this.u_1723_Y = prevRotationYawHead;
    }

    @Generated
    public void u_1723_Y(float rotationPitch) {
        this.v_4262_N = rotationPitch;
    }

    @Generated
    public void v_4262_N(float prevRotationPitch) {
        this.w_1484_f = prevRotationPitch;
    }

    @Generated
    public void w_1484_f(float rotationPitchHead) {
        this.t_148_a = rotationPitchHead;
    }

    @Generated
    public void t_148_a(float prevRotationPitchHead) {
        this.s_956_w = prevRotationPitchHead;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof z_2025_Z)) {
            return false;
        }
        z_2025_Z other = (z_2025_Z)o;
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
        if (Float.compare(this.u_1723_Y(), other.u_1723_Y()) != 0) {
            return false;
        }
        if (Float.compare(this.v_4262_N(), other.v_4262_N()) != 0) {
            return false;
        }
        if (Float.compare(this.w_1484_f(), other.w_1484_f()) != 0) {
            return false;
        }
        if (Float.compare(this.t_148_a(), other.t_148_a()) != 0) {
            return false;
        }
        if (Float.compare(this.s_956_w(), other.s_956_w()) != 0) {
            return false;
        }
        r_4811_B this$entity = this.n_1700_B();
        r_4811_B other$entity = other.n_1700_B();
        return !(this$entity == null ? other$entity != null : !((Object)this$entity).equals(other$entity));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof z_2025_Z;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + Float.floatToIntBits(this.J_1907_R());
        result = result * 59 + Float.floatToIntBits(this.R_4764_Y());
        result = result * 59 + Float.floatToIntBits(this.G_564_y());
        result = result * 59 + Float.floatToIntBits(this.P_1922_E());
        result = result * 59 + Float.floatToIntBits(this.u_1723_Y());
        result = result * 59 + Float.floatToIntBits(this.v_4262_N());
        result = result * 59 + Float.floatToIntBits(this.w_1484_f());
        result = result * 59 + Float.floatToIntBits(this.t_148_a());
        result = result * 59 + Float.floatToIntBits(this.s_956_w());
        r_4811_B $entity = this.n_1700_B();
        result = result * 59 + ($entity == null ? 43 : ((Object)$entity).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventPlayerRender(entity=" + String.valueOf(this.n_1700_B()) + ", partialTicks=" + this.J_1907_R() + ", renderYawOffset=" + this.R_4764_Y() + ", prevRenderYawOffset=" + this.G_564_y() + ", rotationYawHead=" + this.P_1922_E() + ", prevRotationYawHead=" + this.u_1723_Y() + ", rotationPitch=" + this.v_4262_N() + ", prevRotationPitch=" + this.w_1484_f() + ", rotationPitchHead=" + this.t_148_a() + ", prevRotationPitchHead=" + this.s_956_w() + ")";
    }

    @Generated
    public z_2025_Z(r_4811_B entity, float partialTicks, float renderYawOffset, float prevRenderYawOffset, float rotationYawHead, float prevRotationYawHead, float rotationPitch, float prevRotationPitch, float rotationPitchHead, float prevRotationPitchHead) {
        this.n_1700_B = entity;
        this.J_1907_R = partialTicks;
        this.R_4764_Y = renderYawOffset;
        this.G_564_y = prevRenderYawOffset;
        this.P_1922_E = rotationYawHead;
        this.u_1723_Y = prevRotationYawHead;
        this.v_4262_N = rotationPitch;
        this.w_1484_f = prevRotationPitch;
        this.t_148_a = rotationPitchHead;
        this.s_956_w = prevRotationPitchHead;
    }
}

