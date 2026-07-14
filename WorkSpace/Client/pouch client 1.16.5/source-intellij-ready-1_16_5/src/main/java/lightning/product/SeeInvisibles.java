/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.NumberSetting;
import lightning.product.Module;
import lightning.product.BooleanSetting;
import lightning.product.ModuleCategory;
import lombok.Generated;

public class SeeInvisibles
extends Module {
    private final NumberSetting prozrachnostSetting = new NumberSetting("\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c", 0.5f, 0.3f, 1.0f, 0.1f);
    private final BooleanSetting otobrazhatStoykiBroniEnabled = new BooleanSetting("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u0441\u0442\u043e\u0439\u043a\u0438 \u0431\u0440\u043e\u043d\u0438", false);

    public SeeInvisibles() {
        super("SeeInvisibles", ModuleCategory.R_4764_Y);
        this.addSettings(this.prozrachnostSetting, this.otobrazhatStoykiBroniEnabled);
    }

    @Generated
    public NumberSetting h_1847_R() {
        return this.prozrachnostSetting;
    }

    @Generated
    public BooleanSetting Q_4569_t() {
        return this.otobrazhatStoykiBroniEnabled;
    }
}


