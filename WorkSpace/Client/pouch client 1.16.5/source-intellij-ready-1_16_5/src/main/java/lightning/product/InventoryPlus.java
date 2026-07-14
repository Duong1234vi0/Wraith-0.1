/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.P_4526_H;
import lightning.product.Q_2753_H;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.ModuleCategory;

public class InventoryPlus
extends Module {
    public InventoryPlus() {
        super("InventoryPlus", ModuleCategory.P_1922_E);
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        if (InventoryPlus.c_3005_b.Y_259_p == null) {
            return;
        }
        if (e.G_564_y() instanceof P_4526_H) {
            e.n_1700_B(true);
        }
    }
}


