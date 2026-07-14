/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.A_4115_X;
import lightning.product.B_1146_q;
import lightning.product.Timer;
import lombok.Generated;

public class u_2124_A {
    public float n_1700_B;
    public float J_1907_R;
    private long R_4764_Y;
    private final float G_564_y;
    private float P_1922_E = 1.0f;

    public void n_1700_B() {
        this.n_1700_B(1.0f);
    }

    public u_2124_A(float ticks, long lastSyncSysClock) {
        this.G_564_y = 1000.0f / ticks;
        this.R_4764_Y = lastSyncSysClock;
    }

    public int n_1700_B(long gameTime) {
        B_1146_q event = new B_1146_q(gameTime);
        A_4115_X.n_1700_B(event);
        this.J_1907_R = (float)(gameTime - this.R_4764_Y) / this.G_564_y * this.P_1922_E;
        this.R_4764_Y = gameTime;
        this.n_1700_B += this.J_1907_R;
        int i = (int)this.n_1700_B;
        this.n_1700_B -= (float)i;
        if (i > 0) {
            Timer.J_1907_R(i);
        }
        return i;
    }

    @Generated
    public float J_1907_R() {
        return this.P_1922_E;
    }

    @Generated
    public void n_1700_B(float speed) {
        this.P_1922_E = speed;
    }
}


