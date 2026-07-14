/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.MenuConstructor;
import lightning.product.W_3491_f;
import lightning.product.a_2900_S;
import lightning.product.a_3913_L;
import lightning.product.t_3286_u;
import lightning.product.x_282_a;

public final class SimpleMenuProvider
implements t_3286_u {
    private final x_282_a n_1700_B;
    private final MenuConstructor J_1907_R;

    public SimpleMenuProvider(MenuConstructor p_i50396_1_, x_282_a p_i50396_2_) {
        this.J_1907_R = p_i50396_1_;
        this.n_1700_B = p_i50396_2_;
    }

    @Override
    public x_282_a c_() {
        return this.n_1700_B;
    }

    @Override
    public a_2900_S createMenu(int p_createMenu_1_, W_3491_f p_createMenu_2_, a_3913_L p_createMenu_3_) {
        return this.J_1907_R.createMenu(p_createMenu_1_, p_createMenu_2_, p_createMenu_3_);
    }
}


