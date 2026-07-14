/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.NumberSetting;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.i_601_W;
import lightning.product.ModeSetting;
import lightning.product.ModuleCategory;

public class AspectRatio
extends Module {
    public final ModeSetting sootnoshenieMode = new ModeSetting("\u0421\u043e\u043e\u0442\u043d\u043e\u0448\u0435\u043d\u0438\u0435", "\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u043e\u0435", "4:3", "16:9", "1:1", "16:10", "\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u043e\u0435");
    public final NumberSetting znachenieSetting = new NumberSetting("\u0417\u043d\u0430\u0447\u0435\u043d\u0438\u0435", 1.0f, 0.1f, 5.0f, 0.1f, () -> this.sootnoshenieMode.isMode("\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u043e\u0435"));

    public AspectRatio() {
        super("AspectRatio", ModuleCategory.R_4764_Y);
        this.addSettings(this.sootnoshenieMode, this.znachenieSetting);
    }

    @Y_1740_V
    public void n_1700_B(i_601_W e) {
        float value = switch ((String)this.sootnoshenieMode.getValue()) {
            case "4:3" -> 1.3333334f;
            case "16:9" -> 1.7777778f;
            case "1:1" -> 1.0f;
            case "16:10" -> 1.6f;
            default -> ((Float)this.znachenieSetting.getValue()).floatValue();
        };
        e.n_1700_B(value);
    }
}


