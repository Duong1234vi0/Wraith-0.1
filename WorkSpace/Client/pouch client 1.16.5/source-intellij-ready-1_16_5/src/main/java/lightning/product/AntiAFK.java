/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.RandomStringUtils
 */
package lightning.product;

import java.util.Random;
import lightning.product.F_747_P;
import lightning.product.NumberSetting;
import lightning.product.MultiBooleanSetting;
import lightning.product.V_4557_X;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.h_1015_G;
import lightning.product.BooleanSetting;
import lightning.product.ModeSetting;
import lightning.product.u_925_K;
import lightning.product.x_1688_C;
import lightning.product.ModuleCategory;
import org.apache.commons.lang3.RandomStringUtils;

public class AntiAFK
extends Module {
    private final MultiBooleanSetting deystviyaOptions = new MultiBooleanSetting("\u0414\u0435\u0439\u0441\u0442\u0432\u0438\u044f", new BooleanSetting("\u041f\u0440\u044b\u0436\u043e\u043a", false), new BooleanSetting("\u041a\u043e\u043c\u0430\u043d\u0434\u0430", true), new BooleanSetting("\u041a\u0430\u0447\u0430\u043d\u0438\u0435 \u0440\u0443\u043a\u043e\u0439", false), new BooleanSetting("\u041f\u043e\u0432\u043e\u0440\u043e\u0442 \u043a\u0430\u043c\u0435\u0440\u044b", false), new BooleanSetting("\u0421\u043b\u0443\u0447\u0430\u0439\u043d\u043e\u0435 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0435", false));
    private final NumberSetting zaderzhkaSekSetting = new NumberSetting("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 (\u0441\u0435\u043a)", 20.0f, 1.0f, 120.0f, 1.0f);
    private final BooleanSetting sluchaynayaZaderzhkaEnabled = new BooleanSetting("\u0421\u043b\u0443\u0447\u0430\u0439\u043d\u0430\u044f \u0437\u0430\u0434\u0435\u0440\u0436\u043a\u0430", false);
    private final NumberSetting minZaderzhkaSekSetting = new NumberSetting("\u041c\u0438\u043d. \u0437\u0430\u0434\u0435\u0440\u0436\u043a\u0430 (\u0441\u0435\u043a)", 15.0f, 5.0f, 60.0f, 1.0f, () -> this.sluchaynayaZaderzhkaEnabled.isEnabled());
    private final NumberSetting maksZaderzhkaSekSetting = new NumberSetting("\u041c\u0430\u043a\u0441. \u0437\u0430\u0434\u0435\u0440\u0436\u043a\u0430 (\u0441\u0435\u043a)", 30.0f, 10.0f, 120.0f, 1.0f, () -> this.sluchaynayaZaderzhkaEnabled.isEnabled());
    private final ModeSetting tipKomandyMode = new ModeSetting("\u0422\u0438\u043f \u043a\u043e\u043c\u0430\u043d\u0434\u044b", "\u0421\u043b\u0443\u0447\u0430\u0439\u043d\u0430\u044f", () -> this.deystviyaOptions.isOptionEnabled("\u041a\u043e\u043c\u0430\u043d\u0434\u0430"), "\u0421\u043b\u0443\u0447\u0430\u0439\u043d\u0430\u044f");
    private final NumberSetting kolVoPryzhkovSetting = new NumberSetting("\u041a\u043e\u043b-\u0432\u043e \u043f\u0440\u044b\u0436\u043a\u043e\u0432", 1.0f, 1.0f, 5.0f, 1.0f, () -> this.deystviyaOptions.isOptionEnabled("\u041f\u0440\u044b\u0436\u043e\u043a"));
    private final NumberSetting dlitelnostDvizheniyaTikSetting = new NumberSetting("\u0414\u043b\u0438\u0442\u0435\u043b\u044c\u043d\u043e\u0441\u0442\u044c \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u044f (\u0442\u0438\u043a)", 10.0f, 5.0f, 40.0f, 1.0f, () -> this.deystviyaOptions.isOptionEnabled("\u0421\u043b\u0443\u0447\u0430\u0439\u043d\u043e\u0435 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0435"));
    private final BooleanSetting sbrosPriDvizheniiEnabled = new BooleanSetting("\u0421\u0431\u0440\u043e\u0441 \u043f\u0440\u0438 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0438", true);
    private final V_4557_X M_182_A = new V_4557_X();
    private final Random t_1786_h = new Random();
    private long multiplayerClientSuggestionProvider = 20000L;
    private int w_1457_N = 0;
    private int Y_601_j = 0;

    public AntiAFK() {
        super("AntiAFK", ModuleCategory.G_564_y);
        this.addSettings(this.deystviyaOptions, this.zaderzhkaSekSetting, this.sluchaynayaZaderzhkaEnabled, this.minZaderzhkaSekSetting, this.maksZaderzhkaSekSetting, this.tipKomandyMode, this.kolVoPryzhkovSetting, this.dlitelnostDvizheniyaTikSetting, this.sbrosPriDvizheniiEnabled);
    }

    @Override
    public void onEnable() {
        this.M_182_A.n_1700_B();
        this.multiplayerClientSuggestionProvider = (long)(((Float)this.zaderzhkaSekSetting.getValue()).floatValue() * 1000.0f);
        super.onEnable();
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (AntiAFK.c_3005_b.Y_259_p == null || AntiAFK.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.w_1457_N > 0) {
            this.P_1922_E(true);
            --this.w_1457_N;
            return;
        }
        if (this.w_1457_N == 0 && this.Y_601_j != -1) {
            this.P_1922_E(false);
            this.Y_601_j = -1;
        }
        if (this.sbrosPriDvizheniiEnabled.isEnabled().booleanValue() && u_925_K.n_1700_B()) {
            this.M_182_A.n_1700_B();
            this.multiplayerClientSuggestionProvider = this.h_1847_R();
            return;
        }
        if (this.M_182_A.n_1700_B((double)this.multiplayerClientSuggestionProvider)) {
            this.Q_4569_t();
            this.M_182_A.n_1700_B();
            this.multiplayerClientSuggestionProvider = this.h_1847_R();
        }
    }

    private void P_1922_E(boolean pressed) {
        AntiAFK.c_3005_b.P_4830_p.O_508_d.n_1700_B(false);
        AntiAFK.c_3005_b.P_4830_p.A_1038_p.n_1700_B(false);
        AntiAFK.c_3005_b.P_4830_p.r_715_M.n_1700_B(false);
        AntiAFK.c_3005_b.P_4830_p.i_1637_u.n_1700_B(false);
        if (!pressed) {
            return;
        }
        switch (this.Y_601_j) {
            case 0: {
                AntiAFK.c_3005_b.P_4830_p.O_508_d.n_1700_B(true);
                break;
            }
            case 1: {
                AntiAFK.c_3005_b.P_4830_p.A_1038_p.n_1700_B(true);
                break;
            }
            case 2: {
                AntiAFK.c_3005_b.P_4830_p.r_715_M.n_1700_B(true);
                break;
            }
            case 3: {
                AntiAFK.c_3005_b.P_4830_p.i_1637_u.n_1700_B(true);
                break;
            }
            case 4: {
                AntiAFK.c_3005_b.P_4830_p.O_508_d.n_1700_B(true);
                AntiAFK.c_3005_b.P_4830_p.r_715_M.n_1700_B(true);
                break;
            }
            case 5: {
                AntiAFK.c_3005_b.P_4830_p.O_508_d.n_1700_B(true);
                AntiAFK.c_3005_b.P_4830_p.i_1637_u.n_1700_B(true);
                break;
            }
            case 6: {
                AntiAFK.c_3005_b.P_4830_p.A_1038_p.n_1700_B(true);
                AntiAFK.c_3005_b.P_4830_p.r_715_M.n_1700_B(true);
                break;
            }
            case 7: {
                AntiAFK.c_3005_b.P_4830_p.A_1038_p.n_1700_B(true);
                AntiAFK.c_3005_b.P_4830_p.i_1637_u.n_1700_B(true);
            }
        }
    }

    private long h_1847_R() {
        if (this.sluchaynayaZaderzhkaEnabled.isEnabled().booleanValue()) {
            float min = ((Float)this.minZaderzhkaSekSetting.getValue()).floatValue();
            float max = ((Float)this.maksZaderzhkaSekSetting.getValue()).floatValue();
            float randomValue = F_747_P.G_564_y(min, max);
            return (long)(randomValue * 1000.0f);
        }
        return (long)(((Float)this.zaderzhkaSekSetting.getValue()).floatValue() * 1000.0f);
    }

    private void Q_4569_t() {
        if (this.deystviyaOptions.isOptionEnabled("\u041f\u0440\u044b\u0436\u043e\u043a").booleanValue() && AntiAFK.c_3005_b.Y_259_p.M_1641_O()) {
            int jumps = (int)((Float)this.kolVoPryzhkovSetting.getValue()).floatValue();
            for (int i = 0; i < jumps; ++i) {
                if (!AntiAFK.c_3005_b.Y_259_p.M_1641_O()) continue;
                AntiAFK.c_3005_b.Y_259_p.e_837_t();
            }
        }
        if (this.deystviyaOptions.isOptionEnabled("\u041a\u043e\u043c\u0430\u043d\u0434\u0430").booleanValue()) {
            AntiAFK.c_3005_b.Y_259_p.n_1700_B("/" + RandomStringUtils.randomAlphabetic((int)5));
        }
        if (this.deystviyaOptions.isOptionEnabled("\u041a\u0430\u0447\u0430\u043d\u0438\u0435 \u0440\u0443\u043a\u043e\u0439").booleanValue()) {
            AntiAFK.c_3005_b.Y_259_p.n_1700_B(this.t_1786_h.nextBoolean() ? x_1688_C.n_1700_B : x_1688_C.J_1907_R);
        }
        if (this.deystviyaOptions.isOptionEnabled("\u041f\u043e\u0432\u043e\u0440\u043e\u0442 \u043a\u0430\u043c\u0435\u0440\u044b").booleanValue()) {
            float randomYaw = this.t_1786_h.nextFloat() * 360.0f;
            float randomPitch = this.t_1786_h.nextFloat() * 180.0f - 90.0f;
            AntiAFK.c_3005_b.Y_259_p.p_178_J = randomYaw;
            AntiAFK.c_3005_b.Y_259_p.f_4016_n = randomPitch;
        }
        if (this.deystviyaOptions.isOptionEnabled("\u0421\u043b\u0443\u0447\u0430\u0439\u043d\u043e\u0435 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0435").booleanValue()) {
            this.Y_601_j = this.t_1786_h.nextInt(8);
            this.w_1457_N = (int)((Float)this.dlitelnostDvizheniyaTikSetting.getValue()).floatValue();
        }
    }

    @Override
    public void onDisable() {
        this.M_182_A.n_1700_B();
        if (AntiAFK.c_3005_b.P_4830_p != null) {
            AntiAFK.c_3005_b.P_4830_p.O_508_d.n_1700_B(false);
            AntiAFK.c_3005_b.P_4830_p.A_1038_p.n_1700_B(false);
            AntiAFK.c_3005_b.P_4830_p.r_715_M.n_1700_B(false);
            AntiAFK.c_3005_b.P_4830_p.i_1637_u.n_1700_B(false);
        }
        this.w_1457_N = 0;
        this.Y_601_j = -1;
        super.onDisable();
    }
}



