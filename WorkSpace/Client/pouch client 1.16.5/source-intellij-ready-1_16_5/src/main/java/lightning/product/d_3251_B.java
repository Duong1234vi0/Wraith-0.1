/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.NetherVines;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.GrowingPlantHeadBlock;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;

public class d_3251_B
extends GrowingPlantHeadBlock {
    protected static final s_1395_c t_1786_h = T_2915_h.n_1700_B(4.0, 9.0, 4.0, 12.0, 16.0, 12.0);

    public d_3251_B(q_4293_E.P_1922_E properties) {
        super(properties, b_257_Y.n_1700_B, t_1786_h, false, 0.1);
    }

    @Override
    protected int n_1700_B(Random rand) {
        return NetherVines.n_1700_B(rand);
    }

    @Override
    protected T_2915_h J_1907_R() {
        return a_3742_W.S_4998_h;
    }

    @Override
    protected boolean w_1484_f(K_4074_S state) {
        return NetherVines.n_1700_B(state);
    }
}


