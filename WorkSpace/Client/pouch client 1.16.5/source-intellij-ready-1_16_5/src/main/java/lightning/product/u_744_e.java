/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_1289_S;
import lightning.product.f_4016_n;

public class u_744_e
extends Exception {
    public final int n_1700_B;
    public final String J_1907_R;
    public final int R_4764_Y;
    public final String G_564_y;

    public u_744_e(int p_i51784_1_, String p_i51784_2_, f_4016_n p_i51784_3_) {
        super(p_i51784_2_);
        this.n_1700_B = p_i51784_1_;
        this.J_1907_R = p_i51784_2_;
        this.R_4764_Y = p_i51784_3_.J_1907_R();
        this.G_564_y = p_i51784_3_.n_1700_B();
    }

    public u_744_e(int p_i51785_1_, String p_i51785_2_, int p_i51785_3_, String p_i51785_4_) {
        super(p_i51785_2_);
        this.n_1700_B = p_i51785_1_;
        this.J_1907_R = p_i51785_2_;
        this.R_4764_Y = p_i51785_3_;
        this.G_564_y = p_i51785_4_;
    }

    @Override
    public String toString() {
        if (this.R_4764_Y == -1) {
            return "Realms (" + this.n_1700_B + ") " + this.J_1907_R;
        }
        String s = "mco.errorMessage." + this.R_4764_Y;
        String s1 = K_1289_S.n_1700_B(s, new Object[0]);
        return (s1.equals(s) ? this.G_564_y : s1) + " - " + this.R_4764_Y;
    }
}

