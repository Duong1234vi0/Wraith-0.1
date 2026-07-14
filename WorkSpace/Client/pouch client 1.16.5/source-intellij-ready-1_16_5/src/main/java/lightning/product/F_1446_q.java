/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.ClipContext;
import lightning.product.HitResult;
import lightning.product.N_4263_v;
import lightning.product.MinecraftAccess;
import lightning.product.d_2169_p;
import lightning.product.e_2866_D;
import lightning.product.u_530_F;
import lombok.Generated;

public class F_1446_q {
    private float n_1700_B;
    private float J_1907_R;

    public F_1446_q(N_4263_v entity) {
        this.n_1700_B = entity.p_178_J;
        this.J_1907_R = entity.f_4016_n;
    }

    public static F_1446_q n_1700_B(F_1446_q currentAngle, F_1446_q targetAngle) {
        float yawDelta = u_530_F.v_4262_N(targetAngle.R_4764_Y() - currentAngle.R_4764_Y());
        float pitchDelta = u_530_F.v_4262_N(targetAngle.G_564_y() - currentAngle.G_564_y());
        return new F_1446_q(yawDelta, pitchDelta);
    }

    public double n_1700_B(F_1446_q targetRotation) {
        if (targetRotation == null) {
            return 0.0;
        }
        double yawDelta = u_530_F.v_4262_N(targetRotation.R_4764_Y() - this.n_1700_B);
        double pitchDelta = u_530_F.v_4262_N(targetRotation.G_564_y() - this.J_1907_R);
        return Math.hypot(Math.abs(yawDelta), Math.abs(pitchDelta));
    }

    public static F_1446_q n_1700_B() {
        return new F_1446_q(d_2169_p.J_1907_R(), d_2169_p.R_4764_Y());
    }

    public static F_1446_q J_1907_R() {
        return new F_1446_q(MinecraftAccess.c_3005_b.Y_259_p.d_2427_y(), MinecraftAccess.c_3005_b.Y_259_p.l_1233_K());
    }

    public HitResult n_1700_B(double rayTraceDistance, float yaw, float pitch, N_4263_v entity, ClipContext.n_1700_B mode) {
        e_2866_D startVec = MinecraftAccess.c_3005_b.Y_259_p.u_2550_I(1.0f);
        e_2866_D directionVec = F_1446_q.n_1700_B(pitch, yaw);
        e_2866_D endVec = startVec.J_1907_R(directionVec.J_1907_R * rayTraceDistance, directionVec.R_4764_Y * rayTraceDistance, directionVec.G_564_y * rayTraceDistance);
        return MinecraftAccess.c_3005_b.Y_601_j.n_1700_B(new ClipContext(startVec, endVec, mode, ClipContext.J_1907_R.n_1700_B, entity));
    }

    public F_1446_q() {
    }

    public F_1446_q(float yaw, float pitch) {
        this.n_1700_B = yaw;
        this.J_1907_R = pitch;
    }

    public static e_2866_D n_1700_B(float pitch, float yaw) {
        float yawRadians = -yaw * ((float)Math.PI / 180) - (float)Math.PI;
        float pitchRadians = -pitch * ((float)Math.PI / 180);
        float cosYaw = u_530_F.J_1907_R(yawRadians);
        float sinYaw = u_530_F.n_1700_B(yawRadians);
        float cosPitch = -u_530_F.J_1907_R(pitchRadians);
        float sinPitch = u_530_F.n_1700_B(pitchRadians);
        return new e_2866_D(sinYaw * cosPitch, sinPitch, cosYaw * cosPitch);
    }

    @Generated
    public float R_4764_Y() {
        return this.n_1700_B;
    }

    @Generated
    public float G_564_y() {
        return this.J_1907_R;
    }

    @Generated
    public void n_1700_B(float yaw) {
        this.n_1700_B = yaw;
    }

    @Generated
    public void J_1907_R(float pitch) {
        this.J_1907_R = pitch;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof F_1446_q)) {
            return false;
        }
        F_1446_q other = (F_1446_q)o;
        if (!other.n_1700_B((Object)this)) {
            return false;
        }
        if (Float.compare(this.R_4764_Y(), other.R_4764_Y()) != 0) {
            return false;
        }
        return Float.compare(this.G_564_y(), other.G_564_y()) == 0;
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof F_1446_q;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + Float.floatToIntBits(this.R_4764_Y());
        result = result * 59 + Float.floatToIntBits(this.G_564_y());
        return result;
    }

    @Generated
    public String toString() {
        return "Rotation(yaw=" + this.R_4764_Y() + ", pitch=" + this.G_564_y() + ")";
    }
}



