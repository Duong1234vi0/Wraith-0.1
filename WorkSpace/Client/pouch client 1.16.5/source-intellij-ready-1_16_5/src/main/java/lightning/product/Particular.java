/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_170_p;
import lightning.product.E_3343_g;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.h_1015_G;
import lightning.product.m_229_F;
import lightning.product.ModuleCategory;

public class Particular
extends Module {
    public Particular() {
        super("Particular", ModuleCategory.R_4764_Y);
    }

    @Override
    public void onEnable() {
        m_229_F.n_1700_B();
        super.onEnable();
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        E_170_p.J_1907_R();
    }

    @Y_1740_V
    public void n_1700_B(E_3343_g event) {
        E_170_p.R_4764_Y();
    }

    @Override
    public void onDisable() {
        E_170_p.R_4764_Y();
        super.onDisable();
    }
}


