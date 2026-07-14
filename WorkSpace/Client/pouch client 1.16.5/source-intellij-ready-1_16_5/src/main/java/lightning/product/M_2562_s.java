/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.I_4817_s;
import lightning.product.e_2866_D;
import lightning.product.x_607_J;
import lombok.Generated;

public class M_2562_s
implements x_607_J {
    private e_2866_D n_1700_B;
    private e_2866_D J_1907_R;
    private e_2866_D R_4764_Y;
    private boolean G_564_y;
    private I_4817_s P_1922_E;
    private boolean u_1723_Y;
    private boolean v_4262_N;
    private boolean w_1484_f;
    private boolean t_148_a;

    public M_2562_s(e_2866_D from, e_2866_D to, e_2866_D motion, boolean toGround, boolean isCollidedHorizontal, boolean isCollidedVertical, I_4817_s aabbFrom) {
        this.n_1700_B = from;
        this.J_1907_R = to;
        this.R_4764_Y = motion;
        this.G_564_y = toGround;
        this.w_1484_f = isCollidedHorizontal;
        this.t_148_a = isCollidedVertical;
        this.P_1922_E = aabbFrom;
    }

    @Generated
    public e_2866_D n_1700_B() {
        return this.n_1700_B;
    }

    @Generated
    public e_2866_D J_1907_R() {
        return this.J_1907_R;
    }

    @Generated
    public e_2866_D R_4764_Y() {
        return this.R_4764_Y;
    }

    @Generated
    public boolean G_564_y() {
        return this.G_564_y;
    }

    @Generated
    public I_4817_s P_1922_E() {
        return this.P_1922_E;
    }

    @Generated
    public boolean u_1723_Y() {
        return this.u_1723_Y;
    }

    @Generated
    public boolean v_4262_N() {
        return this.v_4262_N;
    }

    @Generated
    public boolean w_1484_f() {
        return this.w_1484_f;
    }

    @Generated
    public boolean t_148_a() {
        return this.t_148_a;
    }

    @Generated
    public void n_1700_B(e_2866_D from) {
        this.n_1700_B = from;
    }

    @Generated
    public void J_1907_R(e_2866_D to) {
        this.J_1907_R = to;
    }

    @Generated
    public void R_4764_Y(e_2866_D motion) {
        this.R_4764_Y = motion;
    }

    @Generated
    public void n_1700_B(boolean toGround) {
        this.G_564_y = toGround;
    }

    @Generated
    public void n_1700_B(I_4817_s aabbFrom) {
        this.P_1922_E = aabbFrom;
    }

    @Generated
    public void J_1907_R(boolean ignoreHorizontal) {
        this.u_1723_Y = ignoreHorizontal;
    }

    @Generated
    public void R_4764_Y(boolean ignoreVertical) {
        this.v_4262_N = ignoreVertical;
    }

    @Generated
    public void G_564_y(boolean collidedHorizontal) {
        this.w_1484_f = collidedHorizontal;
    }

    @Generated
    public void P_1922_E(boolean collidedVertical) {
        this.t_148_a = collidedVertical;
    }
}

