/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.M_1336_P;
import lightning.product.u_530_F;

public final class w_3785_E {
    public static final w_3785_E n_1700_B = new w_3785_E(0.0f, 0.0f, 0.0f, 1.0f);
    private float J_1907_R;
    private float R_4764_Y;
    private float G_564_y;
    private float P_1922_E;

    public w_3785_E(float x, float y, float z, float w) {
        this.J_1907_R = x;
        this.R_4764_Y = y;
        this.G_564_y = z;
        this.P_1922_E = w;
    }

    public w_3785_E(M_1336_P axis, float angle, boolean degrees) {
        if (degrees) {
            angle *= (float)Math.PI / 180;
        }
        float f = w_3785_E.R_4764_Y(angle / 2.0f);
        this.J_1907_R = axis.n_1700_B() * f;
        this.R_4764_Y = axis.J_1907_R() * f;
        this.G_564_y = axis.R_4764_Y() * f;
        this.P_1922_E = w_3785_E.J_1907_R(angle / 2.0f);
    }

    public w_3785_E(float xAngle, float yAngle, float zAngle, boolean degrees) {
        if (degrees) {
            xAngle *= (float)Math.PI / 180;
            yAngle *= (float)Math.PI / 180;
            zAngle *= (float)Math.PI / 180;
        }
        float f = w_3785_E.R_4764_Y(0.5f * xAngle);
        float f1 = w_3785_E.J_1907_R(0.5f * xAngle);
        float f2 = w_3785_E.R_4764_Y(0.5f * yAngle);
        float f3 = w_3785_E.J_1907_R(0.5f * yAngle);
        float f4 = w_3785_E.R_4764_Y(0.5f * zAngle);
        float f5 = w_3785_E.J_1907_R(0.5f * zAngle);
        this.J_1907_R = f * f3 * f5 + f1 * f2 * f4;
        this.R_4764_Y = f1 * f2 * f5 - f * f3 * f4;
        this.G_564_y = f * f2 * f5 + f1 * f3 * f4;
        this.P_1922_E = f1 * f3 * f5 - f * f2 * f4;
    }

    public w_3785_E(w_3785_E quaternionIn) {
        this.J_1907_R = quaternionIn.J_1907_R;
        this.R_4764_Y = quaternionIn.R_4764_Y;
        this.G_564_y = quaternionIn.G_564_y;
        this.P_1922_E = quaternionIn.P_1922_E;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            w_3785_E quaternion = (w_3785_E)p_equals_1_;
            if (Float.compare(quaternion.J_1907_R, this.J_1907_R) != 0) {
                return false;
            }
            if (Float.compare(quaternion.R_4764_Y, this.R_4764_Y) != 0) {
                return false;
            }
            if (Float.compare(quaternion.G_564_y, this.G_564_y) != 0) {
                return false;
            }
            return Float.compare(quaternion.P_1922_E, this.P_1922_E) == 0;
        }
        return false;
    }

    public int hashCode() {
        int i = Float.floatToIntBits(this.J_1907_R);
        i = 31 * i + Float.floatToIntBits(this.R_4764_Y);
        i = 31 * i + Float.floatToIntBits(this.G_564_y);
        return 31 * i + Float.floatToIntBits(this.P_1922_E);
    }

    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("Quaternion[").append(this.G_564_y()).append(" + ");
        stringbuilder.append(this.n_1700_B()).append("i + ");
        stringbuilder.append(this.J_1907_R()).append("j + ");
        stringbuilder.append(this.R_4764_Y()).append("k]");
        return stringbuilder.toString();
    }

    public float n_1700_B() {
        return this.J_1907_R;
    }

    public float J_1907_R() {
        return this.R_4764_Y;
    }

    public float R_4764_Y() {
        return this.G_564_y;
    }

    public float G_564_y() {
        return this.P_1922_E;
    }

    public void n_1700_B(w_3785_E quaternionIn) {
        float f = this.n_1700_B();
        float f1 = this.J_1907_R();
        float f2 = this.R_4764_Y();
        float f3 = this.G_564_y();
        float f4 = quaternionIn.n_1700_B();
        float f5 = quaternionIn.J_1907_R();
        float f6 = quaternionIn.R_4764_Y();
        float f7 = quaternionIn.G_564_y();
        this.J_1907_R = f3 * f4 + f * f7 + f1 * f6 - f2 * f5;
        this.R_4764_Y = f3 * f5 - f * f6 + f1 * f7 + f2 * f4;
        this.G_564_y = f3 * f6 + f * f5 - f1 * f4 + f2 * f7;
        this.P_1922_E = f3 * f7 - f * f4 - f1 * f5 - f2 * f6;
    }

    public void n_1700_B(float valueIn) {
        this.J_1907_R *= valueIn;
        this.R_4764_Y *= valueIn;
        this.G_564_y *= valueIn;
        this.P_1922_E *= valueIn;
    }

    public void P_1922_E() {
        this.J_1907_R = -this.J_1907_R;
        this.R_4764_Y = -this.R_4764_Y;
        this.G_564_y = -this.G_564_y;
    }

    public void n_1700_B(float p_227066_1_, float p_227066_2_, float p_227066_3_, float p_227066_4_) {
        this.J_1907_R = p_227066_1_;
        this.R_4764_Y = p_227066_2_;
        this.G_564_y = p_227066_3_;
        this.P_1922_E = p_227066_4_;
    }

    private static float J_1907_R(float p_214904_0_) {
        return (float)Math.cos(p_214904_0_);
    }

    private static float R_4764_Y(float p_214903_0_) {
        return (float)Math.sin(p_214903_0_);
    }

    public void u_1723_Y() {
        float f = this.n_1700_B() * this.n_1700_B() + this.J_1907_R() * this.J_1907_R() + this.R_4764_Y() * this.R_4764_Y() + this.G_564_y() * this.G_564_y();
        if (f > 1.0E-6f) {
            float f1 = u_530_F.t_148_a(f);
            this.J_1907_R *= f1;
            this.R_4764_Y *= f1;
            this.G_564_y *= f1;
            this.P_1922_E *= f1;
        } else {
            this.J_1907_R = 0.0f;
            this.R_4764_Y = 0.0f;
            this.G_564_y = 0.0f;
            this.P_1922_E = 0.0f;
        }
    }

    public w_3785_E v_4262_N() {
        return new w_3785_E(this);
    }
}

