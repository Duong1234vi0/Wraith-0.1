/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.Container;
import lightning.product.T_2915_h;
import lightning.product.b_4507_u;
import lightning.product.s_1395_c;
import lightning.product.x_268_Y;

public interface Hopper
extends Container {
    public static final s_1395_c n_1700_B = T_2915_h.n_1700_B(2.0, 11.0, 2.0, 14.0, 16.0, 14.0);
    public static final s_1395_c J_1907_R = T_2915_h.n_1700_B(0.0, 16.0, 0.0, 16.0, 32.0, 16.0);
    public static final s_1395_c R_4764_Y = x_268_Y.n_1700_B(n_1700_B, J_1907_R);

    default public s_1395_c R_4764_Y() {
        return R_4764_Y;
    }

    @Nullable
    public b_4507_u c_3005_b();

    public double H_2857_Y();

    public double A_4115_X();

    public double Y_1740_V();
}


