/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.D_3612_q;
import lightning.product.NumberSetting;
import lightning.product.N_4263_v;
import lightning.product.MultiBooleanSetting;
import lightning.product.Module;
import lightning.product.X_4340_E;
import lightning.product.Y_1740_V;
import lightning.product.a_3913_L;
import lightning.product.h_1015_G;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.q_3115_L;
import lightning.product.ModeSetting;
import lightning.product.x_2635_q;
import lightning.product.ModuleCategory;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;

public class AutoLeave
extends Module {
    private final MultiBooleanSetting livatEsliOptions = new MultiBooleanSetting("\u041b\u0438\u0432\u0430\u0442\u044c \u0435\u0441\u043b\u0438", new BooleanSetting("\u041c\u0430\u043b\u043e \u0437\u0434\u043e\u0440\u043e\u0432\u044c\u044f", true), new BooleanSetting("\u0420\u044f\u0434\u043e\u043c \u0438\u0433\u0440\u043e\u043a", true), new BooleanSetting("\u0410\u0434\u043c\u0438\u043d\u0438\u0441\u0442\u0440\u0430\u0442\u043e\u0440 \u0432 \u0432\u0430\u043d\u0438\u0448\u0435", true));
    private final ModeSetting tipUhodaMode = new ModeSetting("\u0422\u0438\u043f \u0443\u0445\u043e\u0434\u0430", "\u0421\u043f\u0430\u0432\u043d", () -> this.livatEsliOptions.isOptionEnabled("\u041c\u0430\u043b\u043e \u0437\u0434\u043e\u0440\u043e\u0432\u044c\u044f") != false || this.livatEsliOptions.isOptionEnabled("\u0420\u044f\u0434\u043e\u043c \u0438\u0433\u0440\u043e\u043a") != false || this.livatEsliOptions.isOptionEnabled("\u0410\u0434\u043c\u0438\u043d\u0438\u0441\u0442\u0440\u0430\u0442\u043e\u0440 \u0432 \u0432\u0430\u043d\u0438\u0448\u0435") != false, "\u0421\u043f\u0430\u0432\u043d", "\u0414\u043e\u043c\u043e\u0439", "\u0425\u0430\u0431");
    private final BooleanSetting livatTolkoVKoncePvpEnabled = new BooleanSetting("\u041b\u0438\u0432\u0430\u0442\u044c \u0442\u043e\u043b\u044c\u043a\u043e \u0432 \u043a\u043e\u043d\u0446\u0435 \u043f\u0432\u043f", false, () -> this.livatEsliOptions.isOptionEnabled("\u041c\u0430\u043b\u043e \u0437\u0434\u043e\u0440\u043e\u0432\u044c\u044f") != false || this.livatEsliOptions.isOptionEnabled("\u0420\u044f\u0434\u043e\u043c \u0438\u0433\u0440\u043e\u043a") != false || this.livatEsliOptions.isOptionEnabled("\u0410\u0434\u043c\u0438\u043d\u0438\u0441\u0442\u0440\u0430\u0442\u043e\u0440 \u0432 \u0432\u0430\u043d\u0438\u0448\u0435") != false);
    private final NumberSetting zdoroveSetting = new NumberSetting("\u0417\u0434\u043e\u0440\u043e\u0432\u044c\u0435", 15.0f, 1.0f, 20.0f, 1.0f, () -> this.livatEsliOptions.isOptionEnabled("\u041c\u0430\u043b\u043e \u0437\u0434\u043e\u0440\u043e\u0432\u044c\u044f"));
    private final NumberSetting radiusSetting = new NumberSetting("\u0420\u0430\u0434\u0438\u0443\u0441", 10.0f, 1.0f, 50.0f, 1.0f, () -> this.livatEsliOptions.isOptionEnabled("\u0420\u044f\u0434\u043e\u043c \u0438\u0433\u0440\u043e\u043a"));
    private final BooleanSetting otklyuchatBaritonEnabled = new BooleanSetting("\u041e\u0442\u043a\u043b\u044e\u0447\u0430\u0442\u044c \u0431\u0430\u0440\u0438\u0442\u043e\u043d", true, () -> this.livatEsliOptions.isOptionEnabled("\u041c\u0430\u043b\u043e \u0437\u0434\u043e\u0440\u043e\u0432\u044c\u044f") != false || this.livatEsliOptions.isOptionEnabled("\u0420\u044f\u0434\u043e\u043c \u0438\u0433\u0440\u043e\u043a") != false || this.livatEsliOptions.isOptionEnabled("\u0410\u0434\u043c\u0438\u043d\u0438\u0441\u0442\u0440\u0430\u0442\u043e\u0440 \u0432 \u0432\u0430\u043d\u0438\u0448\u0435") != false);

    public AutoLeave() {
        super("AutoLeave", ModuleCategory.G_564_y);
        this.addSettings(this.livatEsliOptions, this.tipUhodaMode, this.livatTolkoVKoncePvpEnabled, this.zdoroveSetting, this.radiusSetting, this.otklyuchatBaritonEnabled);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        List<D_3612_q> vanishedStaff;
        if (AutoLeave.c_3005_b.Y_259_p == null || AutoLeave.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.livatEsliOptions.isOptionEnabled("\u041c\u0430\u043b\u043e \u0437\u0434\u043e\u0440\u043e\u0432\u044c\u044f").booleanValue() && AutoLeave.c_3005_b.Y_259_p.g_46_E() <= ((Float)this.zdoroveSetting.getValue()).floatValue()) {
            this.h_1847_R();
            return;
        }
        if (this.livatEsliOptions.isOptionEnabled("\u0420\u044f\u0434\u043e\u043c \u0438\u0433\u0440\u043e\u043a").booleanValue()) {
            List<X_4340_E> players = AutoLeave.c_3005_b.Y_601_j.multiplayerClientSuggestionProvider();
            for (a_3913_L a_3913_L2 : players) {
                if (a_3913_L2 == AutoLeave.c_3005_b.Y_259_p || ClientBootstrap.Y_601_j().v_4262_N().R_4764_Y(a_3913_L2.y_4642_Y().getName()) || !(AutoLeave.c_3005_b.Y_259_p.R_4764_Y((N_4263_v)a_3913_L2) <= ((Float)this.radiusSetting.getValue()).floatValue())) continue;
                this.h_1847_R();
                return;
            }
        }
        if (this.livatEsliOptions.isOptionEnabled("\u0410\u0434\u043c\u0438\u043d\u0438\u0441\u0442\u0440\u0430\u0442\u043e\u0440 \u0432 \u0432\u0430\u043d\u0438\u0448\u0435").booleanValue() && !(vanishedStaff = x_2635_q.J_1907_R()).isEmpty()) {
            this.h_1847_R();
        }
    }

    private void h_1847_R() {
        if (this.livatTolkoVKoncePvpEnabled.isEnabled().booleanValue() && !q_3115_L.n_1700_B()) {
            return;
        }
        if (this.otklyuchatBaritonEnabled.isEnabled().booleanValue()) {
            String prefix = (String)BaritoneAPI.getSettings().prefix.value;
            if (BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().isPathing()) {
                AutoLeave.c_3005_b.Y_259_p.n_1700_B(prefix + "stop");
            }
        }
        switch ((String)this.tipUhodaMode.getValue()) {
            case "\u0421\u043f\u0430\u0432\u043d": {
                AutoLeave.c_3005_b.Y_259_p.n_1700_B("/spawn");
                break;
            }
            case "\u0414\u043e\u043c\u043e\u0439": {
                AutoLeave.c_3005_b.Y_259_p.n_1700_B("/home");
                break;
            }
            case "\u0425\u0430\u0431": {
                AutoLeave.c_3005_b.Y_259_p.n_1700_B("/hub");
            }
        }
        this.R_4764_Y();
    }
}



