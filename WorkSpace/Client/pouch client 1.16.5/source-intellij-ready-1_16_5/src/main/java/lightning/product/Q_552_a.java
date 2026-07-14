/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.S_3458_C;
import lightning.product.DirectionalPlaceContext;
import lightning.product.Z_1993_T;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.q_1613_l;
import lightning.product.v_1669_V;
import lightning.product.OptionalDispenseItemBehavior;
import lightning.product.BlockSource;

public class Q_552_a
extends OptionalDispenseItemBehavior {
    @Override
    protected Z_1993_T n_1700_B(BlockSource source, Z_1993_T stack) {
        this.n_1700_B(false);
        q_1613_l item = stack.J_1907_R();
        if (item instanceof v_1669_V) {
            b_257_Y direction = source.P_1922_E().R_4764_Y(S_3458_C.P_4830_p);
            c_1514_x blockpos = source.G_564_y().offset(direction);
            b_257_Y direction1 = source.v_4262_N().u_1723_Y(blockpos.down()) ? direction : b_257_Y.J_1907_R;
            this.n_1700_B(((v_1669_V)item).n_1700_B(new DirectionalPlaceContext((b_4507_u)source.v_4262_N(), blockpos, direction, stack, direction1)).n_1700_B());
        }
        return stack;
    }
}


