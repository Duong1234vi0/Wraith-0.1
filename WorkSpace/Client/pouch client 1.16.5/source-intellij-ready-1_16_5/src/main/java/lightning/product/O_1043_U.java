/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lombok.Generated;

public class O_1043_U {
    private long n_1700_B;
    private long J_1907_R = System.currentTimeMillis();

    public O_1043_U() {
        this.J_1907_R();
    }

    public boolean n_1700_B(long time) {
        return System.currentTimeMillis() - this.n_1700_B > time;
    }

    public static O_1043_U n_1700_B() {
        return new O_1043_U();
    }

    public void J_1907_R() {
        this.n_1700_B = System.currentTimeMillis();
    }

    public long R_4764_Y() {
        return System.currentTimeMillis() - this.n_1700_B;
    }

    public boolean J_1907_R(long time) {
        return this.R_4764_Y() >= time;
    }

    public void R_4764_Y(long newValue) {
        this.n_1700_B = System.currentTimeMillis() + newValue;
    }

    public void G_564_y(long time) {
        this.n_1700_B = time;
    }

    public long G_564_y() {
        return System.currentTimeMillis() - this.n_1700_B;
    }

    public boolean P_1922_E() {
        return System.currentTimeMillis() - this.n_1700_B <= 0L;
    }

    public boolean u_1723_Y() {
        return this.n_1700_B < System.currentTimeMillis();
    }

    public boolean P_1922_E(long time) {
        return System.currentTimeMillis() - this.n_1700_B > time;
    }

    public boolean u_1723_Y(long delay) {
        return System.currentTimeMillis() - delay >= this.n_1700_B;
    }

    public boolean v_4262_N(long time) {
        return System.currentTimeMillis() - this.J_1907_R > time;
    }

    public boolean n_1700_B(double ms) {
        return (double)this.R_4764_Y() >= ms;
    }

    @Generated
    public long v_4262_N() {
        return this.n_1700_B;
    }

    @Generated
    public long w_1484_f() {
        return this.J_1907_R;
    }

    @Generated
    public void w_1484_f(long startTime) {
        this.J_1907_R = startTime;
    }
}

