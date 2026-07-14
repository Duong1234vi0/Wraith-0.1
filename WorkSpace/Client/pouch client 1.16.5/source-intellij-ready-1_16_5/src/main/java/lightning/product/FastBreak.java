/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.NumberSetting;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.x_4991_F;
import lightning.product.ModuleCategory;

public class FastBreak
extends Module {
    private final NumberSetting uskorenieSetting = new NumberSetting("\u0423\u0441\u043a\u043e\u0440\u0435\u043d\u0438\u0435", 0.8f, 0.1f, 1.0f, 0.1f);

    public FastBreak() {
        super("FastBreak", ModuleCategory.G_564_y);
        this.addSettings(this.uskorenieSetting);
    }

    @Y_1740_V
    public void n_1700_B(x_4991_F e) {
        if (FastBreak.c_3005_b.Y_259_p.G_624_v()) {
            return;
        }
        FastBreak.c_3005_b.w_1457_N.blockHitDelay = 0;
        if (FastBreak.c_3005_b.w_1457_N.curBlockDamageMP > ((Float)this.uskorenieSetting.getValue()).floatValue()) {
            FastBreak.c_3005_b.w_1457_N.curBlockDamageMP = 1.0f;
        }
    }
}


