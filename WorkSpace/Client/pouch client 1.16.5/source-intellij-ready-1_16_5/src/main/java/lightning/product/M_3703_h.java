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

public class M_3703_h
extends GrowingPlantHeadBlock {
    public static final s_1395_c t_1786_h = T_2915_h.n_1700_B(4.0, 0.0, 4.0, 12.0, 15.0, 12.0);

    public M_3703_h(q_4293_E.P_1922_E properties) {
        super(properties, b_257_Y.J_1907_R, t_1786_h, false, 0.1);
    }

    @Override
    protected int n_1700_B(Random rand) {
        return NetherVines.n_1700_B(rand);
    }

    @Override
    protected T_2915_h J_1907_R() {
        return a_3742_W.T_2391_T;
    }

    @Override
    protected boolean w_1484_f(K_4074_S state) {
        return NetherVines.n_1700_B(state);
    }
}


