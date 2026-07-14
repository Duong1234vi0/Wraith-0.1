/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.NumberSetting;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.h_1015_G;
import lightning.product.ModeSetting;
import lightning.product.ModuleCategory;

public class TapeMouse
extends Module {
    public static ModeSetting klavishaMode = new ModeSetting("\u041a\u043b\u0430\u0432\u0438\u0448\u0430", "\u041b\u0435\u0432\u0430\u044f", "\u041b\u0435\u0432\u0430\u044f", "\u041f\u0440\u0430\u0432\u0430\u044f");
    public static NumberSetting zaderzhkaSetting = new NumberSetting("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430", 1.0f, 1.0f, 30.0f, 1.0f);
    private int t_148_a = 0;

    public TapeMouse() {
        super("TapeMouse", ModuleCategory.P_1922_E);
        this.addSettings(klavishaMode, zaderzhkaSetting);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        ++this.t_148_a;
        if ((float)this.t_148_a >= ((Float)zaderzhkaSetting.getValue()).floatValue()) {
            if (klavishaMode.isMode("\u041b\u0435\u0432\u0430\u044f")) {
                c_3005_b.M_182_A();
            } else if (klavishaMode.isMode("\u041f\u0440\u0430\u0432\u0430\u044f")) {
                c_3005_b.t_1786_h();
            }
            this.t_148_a = 0;
        }
    }
}


