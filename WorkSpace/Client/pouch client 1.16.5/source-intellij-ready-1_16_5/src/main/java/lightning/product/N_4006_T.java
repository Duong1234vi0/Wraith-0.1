/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.H_1952_g;
import lightning.product.Module;
import lightning.product.Z_3822_q;
import lightning.product.Animation;
import lightning.product.k_1608_N;
import lombok.Generated;

public class N_4006_T
implements k_1608_N {
    private float n_1700_B;
    private float J_1907_R;
    private float R_4764_Y;
    private float G_564_y;
    private H_1952_g P_1922_E;
    private Module u_1723_Y;
    private final Animation v_4262_N = new Animation(0.0f, 15.0f);
    private final Z_3822_q.n_1700_B w_1484_f = new Z_3822_q.n_1700_B();

    public boolean n_1700_B(float mouseX, float mouseY) {
        return mouseX >= this.n_1700_B && mouseX <= this.n_1700_B + this.R_4764_Y && mouseY >= this.J_1907_R && mouseY <= this.J_1907_R + this.G_564_y;
    }

    public boolean n_1700_B(float mouseX, float mouseY, float height) {
        return mouseX >= this.n_1700_B && mouseX <= this.n_1700_B + this.R_4764_Y && mouseY >= this.J_1907_R && mouseY <= this.J_1907_R + height;
    }

    public boolean n_1700_B() {
        return true;
    }

    public float P_1922_E() {
        this.v_4262_N.n_1700_B(this.n_1700_B() ? 1.0f : 0.0f);
        return this.v_4262_N.n_1700_B();
    }

    public void J_1907_R() {
        this.v_4262_N.J_1907_R(this.n_1700_B() ? 1.0f : 0.0f);
    }

    @Generated
    public float u_1723_Y() {
        return this.n_1700_B;
    }

    @Generated
    public float v_4262_N() {
        return this.J_1907_R;
    }

    @Generated
    public float w_1484_f() {
        return this.R_4764_Y;
    }

    @Generated
    public float t_148_a() {
        return this.G_564_y;
    }

    @Generated
    public H_1952_g s_956_w() {
        return this.P_1922_E;
    }

    @Generated
    public Module u_2550_I() {
        return this.u_1723_Y;
    }

    @Generated
    public Z_3822_q.n_1700_B M_588_G() {
        return this.w_1484_f;
    }

    @Generated
    public void n_1700_B(float x) {
        this.n_1700_B = x;
    }

    @Generated
    public void J_1907_R(float y) {
        this.J_1907_R = y;
    }

    @Generated
    public void R_4764_Y(float width) {
        this.R_4764_Y = width;
    }

    @Generated
    public void G_564_y(float height) {
        this.G_564_y = height;
    }

    @Generated
    public void n_1700_B(H_1952_g panel) {
        this.P_1922_E = panel;
    }

    @Generated
    public void n_1700_B(Module module) {
        this.u_1723_Y = module;
    }
}


