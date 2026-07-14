/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.V_4557_X;
import lightning.product.g_221_o;
import lightning.product.Animation;
import lightning.product.x_282_a;
import lombok.Generated;

public abstract class o_4117_e {
    protected final x_282_a n_1700_B;
    protected final long J_1907_R = 1500L;
    protected final V_4557_X R_4764_Y = new V_4557_X();
    protected final Animation G_564_y = new Animation(0.0f, 20.0f);
    protected final Animation P_1922_E = new Animation(0.0f, 10.0f);
    protected boolean u_1723_Y = false;
    protected boolean v_4262_N = false;

    public o_4117_e(x_282_a message) {
        this.n_1700_B = message;
    }

    public abstract void n_1700_B(float var1, float var2, g_221_o var3);

    public boolean n_1700_B() {
        return this.u_1723_Y || this.R_4764_Y.J_1907_R(1500L);
    }

    public void J_1907_R() {
        this.u_1723_Y = true;
    }

    protected void n_1700_B(float y) {
        if (!this.v_4262_N) {
            this.G_564_y.J_1907_R(y);
            this.v_4262_N = true;
        }
    }

    @Generated
    public x_282_a R_4764_Y() {
        return this.n_1700_B;
    }

    @Generated
    public long G_564_y() {
        return this.J_1907_R;
    }

    @Generated
    public V_4557_X P_1922_E() {
        return this.R_4764_Y;
    }

    @Generated
    public Animation u_1723_Y() {
        return this.G_564_y;
    }

    @Generated
    public Animation v_4262_N() {
        return this.P_1922_E;
    }

    @Generated
    public boolean w_1484_f() {
        return this.u_1723_Y;
    }

    @Generated
    public boolean t_148_a() {
        return this.v_4262_N;
    }
}

