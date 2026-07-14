/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.U_2912_j;

public class Abilities {
    public boolean n_1700_B;
    public boolean J_1907_R;
    public boolean R_4764_Y;
    public boolean G_564_y;
    public boolean P_1922_E = true;
    private float u_1723_Y = 0.05f;
    private float v_4262_N = 0.1f;

    public void n_1700_B(U_2912_j tagCompound) {
        U_2912_j compoundnbt = new U_2912_j();
        compoundnbt.n_1700_B("invulnerable", this.n_1700_B);
        compoundnbt.n_1700_B("flying", this.J_1907_R);
        compoundnbt.n_1700_B("mayfly", this.R_4764_Y);
        compoundnbt.n_1700_B("instabuild", this.G_564_y);
        compoundnbt.n_1700_B("mayBuild", this.P_1922_E);
        compoundnbt.n_1700_B("flySpeed", this.u_1723_Y);
        compoundnbt.n_1700_B("walkSpeed", this.v_4262_N);
        tagCompound.n_1700_B("abilities", compoundnbt);
    }

    public void J_1907_R(U_2912_j tagCompound) {
        if (tagCompound.R_4764_Y("abilities", 10)) {
            U_2912_j compoundnbt = tagCompound.M_182_A("abilities");
            this.n_1700_B = compoundnbt.t_1786_h("invulnerable");
            this.J_1907_R = compoundnbt.t_1786_h("flying");
            this.R_4764_Y = compoundnbt.t_1786_h("mayfly");
            this.G_564_y = compoundnbt.t_1786_h("instabuild");
            if (compoundnbt.R_4764_Y("flySpeed", 99)) {
                this.u_1723_Y = compoundnbt.s_956_w("flySpeed");
                this.v_4262_N = compoundnbt.s_956_w("walkSpeed");
            }
            if (compoundnbt.R_4764_Y("mayBuild", 1)) {
                this.P_1922_E = compoundnbt.t_1786_h("mayBuild");
            }
        }
    }

    public float n_1700_B() {
        return this.u_1723_Y;
    }

    public void n_1700_B(float speed) {
        this.u_1723_Y = speed;
    }

    public float J_1907_R() {
        return this.v_4262_N;
    }

    public void J_1907_R(float speed) {
        this.v_4262_N = speed;
    }
}


