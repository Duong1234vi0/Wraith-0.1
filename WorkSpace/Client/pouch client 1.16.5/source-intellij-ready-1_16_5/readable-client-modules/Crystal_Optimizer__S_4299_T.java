/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.V_3354_l;
import lightning.product.X_2960_b;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.h_2739_B;
import lightning.product.y_2603_k;

public class S_4299_T
extends X_3546_T {
    private boolean v_4262_N;

    public S_4299_T() {
        super("Crystal Optimizer", y_2603_k.G_564_y);
    }

    @Y_1740_V
    public void n_1700_B(h_2739_B eventAttack) {
        N_4263_v n_4263_v = eventAttack.J_1907_R();
        if (n_4263_v instanceof V_3354_l) {
            V_3354_l entity = (V_3354_l)n_4263_v;
            if (this.v_4262_N) {
                entity.h_4811_f();
                this.v_4262_N = false;
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(X_2960_b ignored) {
        this.v_4262_N = true;
    }
}

