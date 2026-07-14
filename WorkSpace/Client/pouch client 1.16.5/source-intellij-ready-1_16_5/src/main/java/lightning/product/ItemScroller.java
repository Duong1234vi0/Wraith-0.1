/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.NumberSetting;
import lightning.product.Module;
import lightning.product.ModuleCategory;

public class ItemScroller
extends Module {
    public NumberSetting zaderzhkaSetting = new NumberSetting("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430", 8.0f, 0.0f, 10.0f, 1.0f);

    public ItemScroller() {
        super("ItemScroller", ModuleCategory.G_564_y);
        this.addSettings(this.zaderzhkaSetting);
    }
}


