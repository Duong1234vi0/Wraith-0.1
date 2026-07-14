/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_4817_s;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.x_268_Y;

public class ShulkerSharedHelper {
    public static I_4817_s n_1700_B(c_1514_x pos, b_257_Y direction) {
        return x_268_Y.J_1907_R().n_1700_B().expand(0.5f * (float)direction.t_148_a(), 0.5f * (float)direction.s_956_w(), 0.5f * (float)direction.u_2550_I()).contract(direction.t_148_a(), direction.s_956_w(), direction.u_2550_I()).offset(pos.offset(direction));
    }
}


