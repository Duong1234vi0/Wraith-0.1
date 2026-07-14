/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.c_1514_x;
import lightning.product.d_2427_y;
import lightning.product.x_607_J;
import lombok.Generated;

public class r_260_T
extends d_2427_y
implements x_607_J {
    private c_1514_x n_1700_B;
    private N_4263_v J_1907_R;
    private float R_4764_Y;
    private float G_564_y;

    @Generated
    public c_1514_x J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public N_4263_v R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public float G_564_y() {
        return this.R_4764_Y;
    }

    @Generated
    public float P_1922_E() {
        return this.G_564_y;
    }

    @Generated
    public void n_1700_B(c_1514_x blockPos) {
        this.n_1700_B = blockPos;
    }

    @Generated
    public void n_1700_B(N_4263_v entity) {
        this.J_1907_R = entity;
    }

    @Generated
    public void n_1700_B(float speed) {
        this.R_4764_Y = speed;
    }

    @Generated
    public void J_1907_R(float speedY) {
        this.G_564_y = speedY;
    }

    @Generated
    public r_260_T(c_1514_x blockPos, N_4263_v entity, float speed, float speedY) {
        this.n_1700_B = blockPos;
        this.J_1907_R = entity;
        this.R_4764_Y = speed;
        this.G_564_y = speedY;
    }
}

