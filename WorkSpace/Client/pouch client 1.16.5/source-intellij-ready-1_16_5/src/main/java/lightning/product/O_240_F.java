/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.a_3913_L;
import lightning.product.g_4621_i;
import lightning.product.LookAtPlayerGoal;

public class O_240_F
extends LookAtPlayerGoal {
    private final g_4621_i v_4262_N;

    public O_240_F(g_4621_i abstractVillagerEntityIn) {
        super(abstractVillagerEntityIn, a_3913_L.class, 8.0f);
        this.v_4262_N = abstractVillagerEntityIn;
    }

    @Override
    public boolean n_1700_B() {
        if (this.v_4262_N.h_1640_b()) {
            this.J_1907_R = this.v_4262_N.n_1700_B();
            return true;
        }
        return false;
    }
}


