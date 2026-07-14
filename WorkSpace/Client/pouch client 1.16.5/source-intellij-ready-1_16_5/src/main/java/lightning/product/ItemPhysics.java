/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Module;
import lightning.product.BooleanSetting;
import lightning.product.ModuleCategory;

public class ItemPhysics
extends Module {
    public static BooleanSetting umenshitPredmetyEnabled = new BooleanSetting("\u0423\u043c\u0435\u043d\u044c\u0448\u0438\u0442\u044c \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u044b", false);

    public ItemPhysics() {
        super("ItemPhysics", ModuleCategory.R_4764_Y);
        this.addSettings(umenshitPredmetyEnabled);
    }
}


