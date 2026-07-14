/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_1436_R;
import lightning.product.I_1869_h;
import lightning.product.b_2585_i;

public class Target
extends D_1436_R {
    private float P_4830_p = Float.MAX_VALUE;
    private D_1436_R h_1847_R;
    private boolean Q_4569_t;

    public Target(D_1436_R p_i51802_1_) {
        super(p_i51802_1_.n_1700_B, p_i51802_1_.J_1907_R, p_i51802_1_.R_4764_Y);
    }

    public Target(int p_i51803_1_, int p_i51803_2_, int p_i51803_3_) {
        super(p_i51803_1_, p_i51803_2_, p_i51803_3_);
    }

    public void n_1700_B(float p_224761_1_, D_1436_R p_224761_2_) {
        if (p_224761_1_ < this.P_4830_p) {
            this.P_4830_p = p_224761_1_;
            this.h_1847_R = p_224761_2_;
        }
    }

    public D_1436_R n_1700_B() {
        return this.h_1847_R;
    }

    public void J_1907_R() {
        this.Q_4569_t = true;
    }

    public static Target n_1700_B(b_2585_i p_224760_0_) {
        Target flaggedpathpoint = new Target(p_224760_0_.readInt(), p_224760_0_.readInt(), p_224760_0_.readInt());
        flaggedpathpoint.s_956_w = p_224760_0_.readFloat();
        flaggedpathpoint.u_2550_I = p_224760_0_.readFloat();
        flaggedpathpoint.t_148_a = p_224760_0_.readBoolean();
        flaggedpathpoint.M_588_G = I_1869_h.values()[p_224760_0_.readInt()];
        flaggedpathpoint.v_4262_N = p_224760_0_.readFloat();
        return flaggedpathpoint;
    }
}


