/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.V_3441_j;
import lombok.Generated;

public class G_4691_Q {
    private long n_1700_B;
    private float J_1907_R;
    private V_3441_j R_4764_Y;
    private long G_564_y;
    private float P_1922_E;
    private float u_1723_Y;
    private boolean v_4262_N;

    public G_4691_Q(long duration, float initialValue, V_3441_j easing) {
        this.n_1700_B = duration;
        this.R_4764_Y = easing;
        this.J_1907_R = initialValue;
        this.P_1922_E = initialValue;
        this.u_1723_Y = initialValue;
        this.v_4262_N = true;
    }

    public void n_1700_B(float newValue) {
        long elapsed;
        long currentTime = System.currentTimeMillis();
        if (newValue != this.u_1723_Y) {
            this.P_1922_E = this.J_1907_R;
            this.u_1723_Y = newValue;
            this.G_564_y = currentTime;
            this.v_4262_N = false;
        }
        if ((elapsed = currentTime - this.G_564_y) >= this.n_1700_B) {
            this.J_1907_R = this.u_1723_Y;
            this.v_4262_N = true;
            return;
        }
        float progress = (float)elapsed / (float)this.n_1700_B;
        float easedProgress = this.R_4764_Y.ease(progress, 0.0f, 1.0f, 1.0f);
        this.J_1907_R = this.P_1922_E + (this.u_1723_Y - this.P_1922_E) * easedProgress;
    }

    public void n_1700_B(long duration) {
        this.n_1700_B = duration;
    }

    @Generated
    public long n_1700_B() {
        return this.n_1700_B;
    }

    @Generated
    public float J_1907_R() {
        return this.J_1907_R;
    }

    @Generated
    public V_3441_j R_4764_Y() {
        return this.R_4764_Y;
    }

    @Generated
    public long G_564_y() {
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
    public boolean v_4262_N() {
        return this.v_4262_N;
    }

    @Generated
    public void J_1907_R(float value) {
        this.J_1907_R = value;
    }

    @Generated
    public void n_1700_B(V_3441_j easing) {
        this.R_4764_Y = easing;
    }

    @Generated
    public void J_1907_R(long startTime) {
        this.G_564_y = startTime;
    }

    @Generated
    public void R_4764_Y(float startValue) {
        this.P_1922_E = startValue;
    }

    @Generated
    public void G_564_y(float targetValue) {
        this.u_1723_Y = targetValue;
    }

    @Generated
    public void n_1700_B(boolean done) {
        this.v_4262_N = done;
    }
}

