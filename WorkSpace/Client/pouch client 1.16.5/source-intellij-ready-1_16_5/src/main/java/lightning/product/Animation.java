/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.H_274_C;
import lightning.product.Easing;
import lightning.product.u_530_F;

public class Animation {
    private float n_1700_B;
    private float J_1907_R;
    private float R_4764_Y;
    private float G_564_y;
    private H_274_C P_1922_E;
    private long u_1723_Y;
    private double v_4262_N;
    private boolean w_1484_f;

    public Animation(float initialValue, float speed, H_274_C easing) {
        this.n_1700_B = initialValue;
        this.J_1907_R = initialValue;
        this.R_4764_Y = initialValue;
        this.G_564_y = speed;
        this.P_1922_E = easing != null ? easing : Easing.u_1723_Y;
        this.u_1723_Y = 0L;
        this.v_4262_N = 0.0;
        this.w_1484_f = false;
    }

    public Animation(float initialValue, float speed) {
        this(initialValue, speed, Easing.u_1723_Y);
    }

    public void n_1700_B(float target) {
        if (this.J_1907_R != target || !this.w_1484_f) {
            this.J_1907_R = target;
            this.R_4764_Y = this.n_1700_B;
            this.u_1723_Y = System.nanoTime();
            this.v_4262_N = 1.0 / (double)this.G_564_y * 2.0;
            this.w_1484_f = true;
        }
        if (this.R_4764_Y()) {
            this.n_1700_B = this.J_1907_R;
            this.w_1484_f = false;
            return;
        }
        double part = this.P_1922_E();
        float easedPart = (float)this.P_1922_E.ease(part);
        this.n_1700_B = u_530_F.v_4262_N(easedPart, this.R_4764_Y, this.J_1907_R);
    }

    private double P_1922_E() {
        if (!this.w_1484_f) {
            return 1.0;
        }
        long now = System.nanoTime();
        double elapsed = (double)(now - this.u_1723_Y) / 1.0E9;
        return u_530_F.n_1700_B(elapsed / this.v_4262_N, 0.0, 1.0);
    }

    public float n_1700_B() {
        return this.n_1700_B;
    }

    public void J_1907_R(float value) {
        this.n_1700_B = value;
        this.J_1907_R = value;
        this.R_4764_Y = value;
        this.w_1484_f = false;
    }

    public float J_1907_R() {
        return this.J_1907_R;
    }

    public void R_4764_Y(float speed) {
        this.G_564_y = speed;
        this.v_4262_N = 1.0 / (double)speed;
    }

    public void n_1700_B(H_274_C easing) {
        this.P_1922_E = easing != null ? easing : Easing.u_1723_Y;
    }

    public boolean R_4764_Y() {
        return this.P_1922_E() >= 1.0;
    }

    public boolean G_564_y() {
        return !this.R_4764_Y();
    }
}

