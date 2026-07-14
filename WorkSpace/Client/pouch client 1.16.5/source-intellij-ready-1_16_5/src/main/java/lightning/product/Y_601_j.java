/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public class Y_601_j {
    private boolean n_1700_B = false;
    private boolean J_1907_R = false;
    private boolean R_4764_Y = false;
    private boolean G_564_y = false;
    private boolean P_1922_E = false;
    private boolean u_1723_Y = false;
    private boolean v_4262_N = false;

    public void n_1700_B() {
        this.n_1700_B = false;
        this.J_1907_R = false;
        this.R_4764_Y = false;
        this.G_564_y = false;
        this.P_1922_E = false;
        this.u_1723_Y = false;
        this.v_4262_N = false;
    }

    public void n_1700_B(boolean pressed) {
        this.n_1700_B = pressed;
    }

    public void J_1907_R(boolean pressed) {
        this.J_1907_R = pressed;
    }

    public void R_4764_Y(boolean pressed) {
        this.R_4764_Y = pressed;
    }

    public void G_564_y(boolean pressed) {
        this.G_564_y = pressed;
    }

    public void P_1922_E(boolean pressed) {
        this.P_1922_E = pressed;
    }

    public void u_1723_Y(boolean pressed) {
        this.u_1723_Y = pressed;
    }

    public void v_4262_N(boolean pressed) {
        this.v_4262_N = pressed;
    }

    public boolean J_1907_R() {
        return this.n_1700_B;
    }

    public boolean R_4764_Y() {
        return this.J_1907_R;
    }

    public boolean G_564_y() {
        return this.R_4764_Y;
    }

    public boolean P_1922_E() {
        return this.G_564_y;
    }

    public boolean u_1723_Y() {
        return this.P_1922_E;
    }

    public boolean v_4262_N() {
        return this.u_1723_Y;
    }

    public boolean w_1484_f() {
        return this.v_4262_N;
    }

    public float[] t_148_a() {
        float moveForward = 0.0f;
        float moveStrafe = 0.0f;
        if (this.n_1700_B) {
            moveForward += 1.0f;
        }
        if (this.J_1907_R) {
            moveForward -= 1.0f;
        }
        if (this.R_4764_Y) {
            moveStrafe += 1.0f;
        }
        if (this.G_564_y) {
            moveStrafe -= 1.0f;
        }
        return new float[]{moveForward, moveStrafe};
    }
}

