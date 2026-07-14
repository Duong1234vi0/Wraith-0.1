/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Q_4863_g;
import lightning.product.Z_1993_T;
import lightning.product.g_2336_b;

public abstract class CustomRecipe
implements Q_4863_g {
    private final g_2336_b n_1700_B;

    public CustomRecipe(g_2336_b idIn) {
        this.n_1700_B = idIn;
    }

    @Override
    public g_2336_b u_1723_Y() {
        return this.n_1700_B;
    }

    @Override
    public boolean t_148_a() {
        return true;
    }

    @Override
    public Z_1993_T R_4764_Y() {
        return Z_1993_T.J_1907_R;
    }
}


