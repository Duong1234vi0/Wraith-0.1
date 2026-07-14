/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.NumberSetting;
import lightning.product.MobEffects;
import lightning.product.K_4719_o;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.c_1514_x;
import lightning.product.h_1015_G;
import lightning.product.k_2610_C;
import lightning.product.BooleanSetting;
import lightning.product.ModeSetting;
import lightning.product.u_530_F;
import lightning.product.ModuleCategory;

public class FullBright
extends Module {
    private final ModeSetting rezhimMode = new ModeSetting("\u0420\u0435\u0436\u0438\u043c", "Night Vision", "Night Vision", "Gamma", "\u041f\u043b\u0430\u0432\u043d\u043e\u0435 \u043e\u0441\u0432\u0435\u0449\u0435\u043d\u0438\u0435 \u043c\u0438\u0440\u0430", "\u0422\u0435\u043c\u043d\u044b\u0439");
    private final NumberSetting kolichestvoGammySetting = new NumberSetting("\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u0413\u0430\u043c\u043c\u044b", 1.0f, 1.0f, 10.0f, 1.0f, () -> this.rezhimMode.isMode("Gamma"));
    private final NumberSetting urovenOsvescheniyaSetting = new NumberSetting("\u0423\u0440\u043e\u0432\u0435\u043d\u044c \u043e\u0441\u0432\u0435\u0449\u0435\u043d\u0438\u044f", 5.0f, 1.0f, 15.0f, 1.0f, () -> this.rezhimMode.isMode("\u041f\u043b\u0430\u0432\u043d\u043e\u0435 \u043e\u0441\u0432\u0435\u0449\u0435\u043d\u0438\u0435 \u043c\u0438\u0440\u0430"));
    private final NumberSetting urovenTemnotySetting = new NumberSetting("\u0423\u0440\u043e\u0432\u0435\u043d\u044c \u0442\u0435\u043c\u043d\u043e\u0442\u044b", 0.0f, 0.0f, 0.5f, 0.1f, () -> this.rezhimMode.isMode("\u0422\u0435\u043c\u043d\u044b\u0439"));
    private final BooleanSetting rabotatTolkoNochyuEnabled = new BooleanSetting("\u0420\u0430\u0431\u043e\u0442\u0430\u0442\u044c \u0442\u043e\u043b\u044c\u043a\u043e \u043d\u043e\u0447\u044c\u044e", false);
    private final BooleanSetting rabotatTolkoVPescherahEnabled = new BooleanSetting("\u0420\u0430\u0431\u043e\u0442\u0430\u0442\u044c \u0442\u043e\u043b\u044c\u043a\u043e \u0432 \u043f\u0435\u0449\u0435\u0440\u0430\u0445", false);
    private float P_4830_p = 1.0f;
    private boolean h_1847_R = false;

    public FullBright() {
        super("FullBright", ModuleCategory.R_4764_Y);
        this.addSettings(this.rezhimMode, this.kolichestvoGammySetting, this.urovenOsvescheniyaSetting, this.urovenTemnotySetting, this.rabotatTolkoNochyuEnabled, this.rabotatTolkoVPescherahEnabled);
    }

    @Override
    public void onEnable() {
        if (FullBright.c_3005_b.P_4830_p != null) {
            this.P_4830_p = (float)FullBright.c_3005_b.P_4830_p.c_132_F;
        }
        if (FullBright.c_3005_b.Y_259_p != null) {
            this.h_1847_R = FullBright.c_3005_b.Y_259_p.J_1907_R(MobEffects.M_182_A);
        }
        super.onEnable();
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (FullBright.c_3005_b.Y_259_p == null || FullBright.c_3005_b.P_4830_p == null || FullBright.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.rabotatTolkoNochyuEnabled.isEnabled().booleanValue() && !this.h_1847_R()) {
            this.M_182_A();
            return;
        }
        if (this.rabotatTolkoVPescherahEnabled.isEnabled().booleanValue() && !this.Q_4569_t()) {
            this.M_182_A();
            return;
        }
        if (this.rezhimMode.isMode("Gamma")) {
            this.t_1786_h();
        } else if (this.rezhimMode.isMode("Night Vision")) {
            this.multiplayerClientSuggestionProvider();
        } else if (this.rezhimMode.isMode("\u041f\u043b\u0430\u0432\u043d\u043e\u0435 \u043e\u0441\u0432\u0435\u0449\u0435\u043d\u0438\u0435 \u043c\u0438\u0440\u0430")) {
            this.w_1457_N();
        } else if (this.rezhimMode.isMode("\u0422\u0435\u043c\u043d\u044b\u0439")) {
            this.Y_601_j();
        }
    }

    private boolean h_1847_R() {
        if (FullBright.c_3005_b.Y_601_j == null) {
            return false;
        }
        long dayTime = FullBright.c_3005_b.Y_601_j.Z_976_R() % 24000L;
        return dayTime >= 13000L && dayTime < 23000L;
    }

    private boolean Q_4569_t() {
        if (FullBright.c_3005_b.Y_601_j == null || FullBright.c_3005_b.Y_259_p == null) {
            return false;
        }
        c_1514_x playerPos = FullBright.c_3005_b.Y_259_p.b_2312_j();
        int skyLight = FullBright.c_3005_b.Y_601_j.getLightFor(K_4719_o.n_1700_B, playerPos);
        return skyLight <= 3;
    }

    private void M_182_A() {
        if (FullBright.c_3005_b.P_4830_p != null) {
            FullBright.c_3005_b.P_4830_p.c_132_F = this.P_4830_p;
        }
        if (FullBright.c_3005_b.Y_259_p != null && !this.h_1847_R) {
            FullBright.c_3005_b.Y_259_p.G_564_y(MobEffects.M_182_A);
        }
    }

    private void t_1786_h() {
        c_1514_x p = FullBright.c_3005_b.Y_259_p.b_2312_j();
        int lvl = Math.max(FullBright.c_3005_b.Y_601_j.getLightFor(K_4719_o.J_1907_R, p), FullBright.c_3005_b.Y_601_j.getLightFor(K_4719_o.n_1700_B, p));
        double t = 1.2 + Math.pow(1.0 - (double)lvl / 15.0, 2.0) * 10.8;
        FullBright.c_3005_b.P_4830_p.c_132_F += (u_530_F.n_1700_B(t, 1.2, 12.0) - FullBright.c_3005_b.P_4830_p.c_132_F) * 0.3;
        FullBright.c_3005_b.Y_259_p.G_564_y(MobEffects.M_182_A);
    }

    private void multiplayerClientSuggestionProvider() {
        FullBright.c_3005_b.P_4830_p.c_132_F = 0.0;
        FullBright.c_3005_b.Y_259_p.n_1700_B(new k_2610_C(MobEffects.M_182_A, 999999999, 1));
    }

    private void w_1457_N() {
        float targetGamma = ((Float)this.urovenOsvescheniyaSetting.getValue()).floatValue();
        if (FullBright.c_3005_b.P_4830_p.c_132_F < (double)targetGamma) {
            FullBright.c_3005_b.P_4830_p.c_132_F = Math.min(FullBright.c_3005_b.P_4830_p.c_132_F + (double)0.1f, (double)targetGamma);
        } else if (FullBright.c_3005_b.P_4830_p.c_132_F > (double)targetGamma) {
            FullBright.c_3005_b.P_4830_p.c_132_F = Math.max(FullBright.c_3005_b.P_4830_p.c_132_F - (double)0.1f, (double)targetGamma);
        }
        FullBright.c_3005_b.Y_259_p.G_564_y(MobEffects.M_182_A);
    }

    private void Y_601_j() {
        float targetGamma = ((Float)this.urovenTemnotySetting.getValue()).floatValue();
        if (FullBright.c_3005_b.P_4830_p.c_132_F > (double)targetGamma) {
            FullBright.c_3005_b.P_4830_p.c_132_F = Math.max(FullBright.c_3005_b.P_4830_p.c_132_F - (double)0.1f, (double)targetGamma);
        } else if (FullBright.c_3005_b.P_4830_p.c_132_F < (double)targetGamma) {
            FullBright.c_3005_b.P_4830_p.c_132_F = Math.min(FullBright.c_3005_b.P_4830_p.c_132_F + (double)0.1f, (double)targetGamma);
        }
        FullBright.c_3005_b.Y_259_p.G_564_y(MobEffects.M_182_A);
    }

    @Override
    public void onDisable() {
        if (FullBright.c_3005_b.Y_259_p == null || FullBright.c_3005_b.P_4830_p == null) {
            return;
        }
        FullBright.c_3005_b.P_4830_p.c_132_F = this.P_4830_p;
        if (this.rezhimMode.isMode("Night Vision") || !this.h_1847_R) {
            FullBright.c_3005_b.Y_259_p.G_564_y(MobEffects.M_182_A);
        }
        super.onDisable();
    }
}



