/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.C_415_h;
import lightning.product.NumberSetting;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.BooleanSetting;
import lightning.product.r_4811_B;
import lightning.product.ModuleCategory;

public class s_4054_j
extends Module {
    public static final NumberSetting v_4262_N = new NumberSetting("\u0420\u0430\u0437\u043c\u0435\u0440", 0.2f, 0.0f, 1.0f, 0.05f);
    private final BooleanSetting w_1484_f = new BooleanSetting("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u0420\u0430\u0437\u043c\u0435\u0440", true);

    public s_4054_j() {
        super("HitBoxes", "\u0423\u0432\u0435\u043b\u0438\u0447\u0438\u0432\u0430\u0435\u0442 \u0445\u0438\u0442\u0431\u043e\u043a\u0441\u044b \u0436\u0438\u0432\u044b\u0445 \u0441\u0443\u0449\u043d\u043e\u0441\u0442\u0435\u0439 \u0434\u043b\u044f \u0431\u043e\u043b\u0435\u0435 \u0441\u0442\u0430\u0431\u0438\u043b\u044c\u043d\u044b\u0445 \u0443\u0434\u0430\u0440\u043e\u0432", ModuleCategory.n_1700_B);
        this.n_1700_B(v_4262_N, this.w_1484_f);
    }

    @Y_1740_V
    public void n_1700_B(C_415_h event) {
        if (!(event.J_1907_R() instanceof r_4811_B)) {
            return;
        }
        event.n_1700_B(((Float)v_4262_N.J_1907_R()).floatValue());
    }

    public boolean h_1847_R() {
        return this.w_1484_f() && this.w_1484_f.t_148_a() != false;
    }
}


