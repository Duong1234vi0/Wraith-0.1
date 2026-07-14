/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.NumberSetting;
import lightning.product.Module;
import lightning.product.ModeSetting;
import lightning.product.ModuleCategory;

public class ToggleSounds
extends Module {
    public static ModeSetting tipZvukaMode = new ModeSetting("\u0422\u0438\u043f \u0437\u0432\u0443\u043a\u0430", "\u0422\u0438\u043f 1", "\u0422\u0438\u043f 1", "\u0422\u0438\u043f 2", "\u0422\u0438\u043f 3", "\u0422\u0438\u043f 4");
    public static NumberSetting gromkostSetting = new NumberSetting("\u0413\u0440\u043e\u043c\u043a\u043e\u0441\u0442\u044c", 75.0f, 0.0f, 100.0f, 1.0f);

    public ToggleSounds() {
        super("ToggleSounds", ModuleCategory.P_1922_E);
        this.addSettings(tipZvukaMode, gromkostSetting);
    }

    public static String P_1922_E(boolean isEnable) {
        String mode;
        return switch (mode = (String)tipZvukaMode.getValue()) {
            case "\u0422\u0438\u043f 1" -> {
                if (isEnable) {
                    yield "enabled0";
                }
                yield "disabled0";
            }
            case "\u0422\u0438\u043f 2" -> {
                if (isEnable) {
                    yield "enabled1";
                }
                yield "disabled1";
            }
            case "\u0422\u0438\u043f 3" -> {
                if (isEnable) {
                    yield "enabled2";
                }
                yield "disabled2";
            }
            case "\u0422\u0438\u043f 4" -> {
                if (isEnable) {
                    yield "enabled3";
                }
                yield "disabled3";
            }
            default -> null;
        };
    }
}


