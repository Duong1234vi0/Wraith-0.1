/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.NumberSetting;
import lightning.product.Module;
import lightning.product.ModuleCategory;

public class ExtendedTab
extends Module {
    public static NumberSetting maksimalnoeKolVoIgrokovSetting = new NumberSetting("\u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u043e\u0435 \u043a\u043e\u043b-\u0432\u043e \u0438\u0433\u0440\u043e\u043a\u043e\u0432", 25.0f, 20.0f, 50.0f, 5.0f);
    public static NumberSetting maksimalnoeKolVoKolonokSetting = new NumberSetting("\u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u043e\u0435 \u043a\u043e\u043b-\u0432\u043e \u043a\u043e\u043b\u043e\u043d\u043e\u043a", 4.0f, 3.0f, 5.0f, 1.0f);

    public ExtendedTab() {
        super("ExtendedTab", ModuleCategory.R_4764_Y);
        this.addSettings(maksimalnoeKolVoIgrokovSetting, maksimalnoeKolVoKolonokSetting);
    }
}


