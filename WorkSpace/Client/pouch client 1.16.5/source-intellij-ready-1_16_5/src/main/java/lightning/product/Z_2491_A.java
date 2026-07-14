/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_1098_v;
import lightning.product.M_1336_P;
import lightning.product.u_530_F;
import lightning.product.w_3785_E;

public class Z_2491_A {
    private float n_1700_B;
    private float J_1907_R;
    private float R_4764_Y;
    private float G_564_y;

    public Z_2491_A() {
    }

    public Z_2491_A(float x, float y, float z, float w) {
        this.n_1700_B = x;
        this.J_1907_R = y;
        this.R_4764_Y = z;
        this.G_564_y = w;
    }

    public Z_2491_A(M_1336_P vectorIn) {
        this(vectorIn.n_1700_B(), vectorIn.J_1907_R(), vectorIn.R_4764_Y(), 1.0f);
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            Z_2491_A vector4f = (Z_2491_A)p_equals_1_;
            if (Float.compare(vector4f.n_1700_B, this.n_1700_B) != 0) {
                return false;
            }
            if (Float.compare(vector4f.J_1907_R, this.J_1907_R) != 0) {
                return false;
            }
            if (Float.compare(vector4f.R_4764_Y, this.R_4764_Y) != 0) {
                return false;
            }
            return Float.compare(vector4f.G_564_y, this.G_564_y) == 0;
        }
        return false;
    }

    public int hashCode() {
        int i = Float.floatToIntBits(this.n_1700_B);
        i = 31 * i + Float.floatToIntBits(this.J_1907_R);
        i = 31 * i + Float.floatToIntBits(this.R_4764_Y);
        return 31 * i + Float.floatToIntBits(this.G_564_y);
    }

    public float n_1700_B() {
        return this.n_1700_B;
    }

    public float J_1907_R() {
        return this.J_1907_R;
    }

    public float R_4764_Y() {
        return this.R_4764_Y;
    }

    public float G_564_y() {
        return this.G_564_y;
    }

    public void n_1700_B(M_1336_P vec) {
        this.n_1700_B *= vec.n_1700_B();
        this.J_1907_R *= vec.J_1907_R();
        this.R_4764_Y *= vec.R_4764_Y();
    }

    public void n_1700_B(float x, float y, float z, float w) {
        this.n_1700_B = x;
        this.J_1907_R = y;
        this.R_4764_Y = z;
        this.G_564_y = w;
    }

    public float n_1700_B(Z_2491_A vectorIn) {
        return this.n_1700_B * vectorIn.n_1700_B + this.J_1907_R * vectorIn.J_1907_R + this.R_4764_Y * vectorIn.R_4764_Y + this.G_564_y * vectorIn.G_564_y;
    }

    public boolean P_1922_E() {
        float f = this.n_1700_B * this.n_1700_B + this.J_1907_R * this.J_1907_R + this.R_4764_Y * this.R_4764_Y + this.G_564_y * this.G_564_y;
        if ((double)f < 1.0E-5) {
            return false;
        }
        float f1 = u_530_F.t_148_a(f);
        this.n_1700_B *= f1;
        this.J_1907_R *= f1;
        this.R_4764_Y *= f1;
        this.G_564_y *= f1;
        return true;
    }

    public void n_1700_B(D_1098_v matrixIn) {
        float f = this.n_1700_B;
        float f1 = this.J_1907_R;
        float f2 = this.R_4764_Y;
        float f3 = this.G_564_y;
        this.n_1700_B = matrixIn.n_1700_B * f + matrixIn.J_1907_R * f1 + matrixIn.R_4764_Y * f2 + matrixIn.G_564_y * f3;
        this.J_1907_R = matrixIn.P_1922_E * f + matrixIn.u_1723_Y * f1 + matrixIn.v_4262_N * f2 + matrixIn.w_1484_f * f3;
        this.R_4764_Y = matrixIn.t_148_a * f + matrixIn.s_956_w * f1 + matrixIn.u_2550_I * f2 + matrixIn.M_588_G * f3;
        this.G_564_y = matrixIn.P_4830_p * f + matrixIn.h_1847_R * f1 + matrixIn.Q_4569_t * f2 + matrixIn.M_182_A * f3;
    }

    public void n_1700_B(w_3785_E quaternionIn) {
        w_3785_E quaternion = new w_3785_E(quaternionIn);
        quaternion.n_1700_B(new w_3785_E(this.n_1700_B(), this.J_1907_R(), this.R_4764_Y(), 0.0f));
        w_3785_E quaternion1 = new w_3785_E(quaternionIn);
        quaternion1.P_1922_E();
        quaternion.n_1700_B(quaternion1);
        this.n_1700_B(quaternion.n_1700_B(), quaternion.J_1907_R(), quaternion.R_4764_Y(), this.G_564_y());
    }

    public void u_1723_Y() {
        this.n_1700_B /= this.G_564_y;
        this.J_1907_R /= this.G_564_y;
        this.R_4764_Y /= this.G_564_y;
        this.G_564_y = 1.0f;
    }

    public String toString() {
        return "[" + this.n_1700_B + ", " + this.J_1907_R + ", " + this.R_4764_Y + ", " + this.G_564_y + "]";
    }
}

