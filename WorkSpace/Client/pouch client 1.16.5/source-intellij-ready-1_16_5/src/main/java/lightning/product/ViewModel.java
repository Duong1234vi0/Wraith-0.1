/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_453_w;
import lightning.product.H_1491_c;
import lightning.product.NumberSetting;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.g_221_o;
import lightning.product.k_4231_L;
import lightning.product.ModuleCategory;

public class ViewModel
extends Module {
    public final NumberSetting pravayaRukaXSetting = new NumberSetting("\u041f\u0440\u0430\u0432\u0430\u044f \u0440\u0443\u043a\u0430 X", 0.0f, -2.0f, 2.0f, 0.1f);
    public final NumberSetting pravayaRukaYSetting = new NumberSetting("\u041f\u0440\u0430\u0432\u0430\u044f \u0440\u0443\u043a\u0430 Y", 0.0f, -2.0f, 2.0f, 0.1f);
    public final NumberSetting pravayaRukaZSetting = new NumberSetting("\u041f\u0440\u0430\u0432\u0430\u044f \u0440\u0443\u043a\u0430 Z", 0.0f, -2.0f, 2.0f, 0.1f);
    public final NumberSetting levayaRukaXSetting = new NumberSetting("\u041b\u0435\u0432\u0430\u044f \u0440\u0443\u043a\u0430 X", 0.0f, -2.0f, 2.0f, 0.1f);
    public final NumberSetting levayaRukaYSetting = new NumberSetting("\u041b\u0435\u0432\u0430\u044f \u0440\u0443\u043a\u0430 Y", 0.0f, -2.0f, 2.0f, 0.1f);
    public final NumberSetting levayaRukaZSetting = new NumberSetting("\u041b\u0435\u0432\u0430\u044f \u0440\u0443\u043a\u0430 Z", 0.0f, -2.0f, 2.0f, 0.1f);
    public final H_1491_c P_4830_p = new H_1491_c("\u0421\u0431\u0440\u043e\u0441\u0438\u0442\u044c", () -> {
        this.pravayaRukaXSetting.setValue(Float.valueOf(0.0f));
        this.pravayaRukaYSetting.setValue(Float.valueOf(0.0f));
        this.pravayaRukaZSetting.setValue(Float.valueOf(0.0f));
        this.levayaRukaXSetting.setValue(Float.valueOf(0.0f));
        this.levayaRukaYSetting.setValue(Float.valueOf(0.0f));
        this.levayaRukaZSetting.setValue(Float.valueOf(0.0f));
    });

    public ViewModel() {
        super("ViewModel", ModuleCategory.R_4764_Y);
        this.addSettings(this.pravayaRukaXSetting, this.pravayaRukaYSetting, this.pravayaRukaZSetting, this.levayaRukaXSetting, this.levayaRukaYSetting, this.levayaRukaZSetting, this.P_4830_p);
    }

    @Y_1740_V
    public void n_1700_B(E_453_w e) {
        g_221_o matrixStack = e.J_1907_R();
        if (e.R_4764_Y() == k_4231_L.J_1907_R) {
            matrixStack.n_1700_B((double)((Float)this.pravayaRukaXSetting.getValue()).floatValue(), (double)((Float)this.pravayaRukaYSetting.getValue()).floatValue(), (double)((Float)this.pravayaRukaZSetting.getValue()).floatValue());
        } else {
            matrixStack.n_1700_B((double)((Float)this.levayaRukaXSetting.getValue()).floatValue(), (double)((Float)this.levayaRukaYSetting.getValue()).floatValue(), (double)((Float)this.levayaRukaZSetting.getValue()).floatValue());
        }
    }
}


