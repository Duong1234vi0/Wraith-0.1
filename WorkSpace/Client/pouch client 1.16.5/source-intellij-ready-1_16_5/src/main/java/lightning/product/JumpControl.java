/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Z_530_i;

public class JumpControl {
    private final Z_530_i J_1907_R;
    protected boolean n_1700_B;

    public JumpControl(Z_530_i mob) {
        this.J_1907_R = mob;
    }

    public void n_1700_B() {
        this.n_1700_B = true;
    }

    public void J_1907_R() {
        this.J_1907_R.t_1786_h(this.n_1700_B);
        this.n_1700_B = false;
    }
}


