/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.V_3354_l;
import lightning.product.X_2960_b;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.h_2739_B;
import lightning.product.ModuleCategory;

public class CrystalOptimizer
extends Module {
    private boolean v_4262_N;

    public CrystalOptimizer() {
        super("Crystal Optimizer", ModuleCategory.G_564_y);
    }

    @Y_1740_V
    public void n_1700_B(h_2739_B eventAttack) {
        N_4263_v n_4263_v = eventAttack.J_1907_R();
        if (n_4263_v instanceof V_3354_l) {
            V_3354_l entity = (V_3354_l)n_4263_v;
            if (this.v_4262_N) {
                entity.Ops();
                this.v_4262_N = false;
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(X_2960_b ignored) {
        this.v_4262_N = true;
    }
}



