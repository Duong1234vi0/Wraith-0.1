/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.GrowingPlantHeadBlock;
import lightning.product.h_4327_W;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;

public class u_1147_u
extends h_4327_W {
    public static final s_1395_c M_182_A = T_2915_h.n_1700_B(4.0, 0.0, 4.0, 12.0, 16.0, 12.0);

    public u_1147_u(q_4293_E.P_1922_E properties) {
        super(properties, b_257_Y.J_1907_R, M_182_A, false);
    }

    @Override
    protected GrowingPlantHeadBlock t_148_a() {
        return (GrowingPlantHeadBlock)a_3742_W.SimpleCriterionTrigger;
    }
}


