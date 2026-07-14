/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_759_W;
import lightning.product.a_3742_W;
import lightning.product.h_1015_G;
import lightning.product.BooleanSetting;
import lightning.product.ModeSetting;
import lightning.product.u_925_K;
import lightning.product.ModuleCategory;

public class NoWeb
extends Module {
    private final ModeSetting rezhimMode = new ModeSetting("\u0420\u0435\u0436\u0438\u043c", "Motion", "Motion");
    private final BooleanSetting lomatSkvozPautinuEnabled = new BooleanSetting("\u041b\u043e\u043c\u0430\u0442\u044c \u0441\u043a\u0432\u043e\u0437\u044c \u043f\u0430\u0443\u0442\u0438\u043d\u0443", false);

    public NoWeb() {
        super("NoWeb", ModuleCategory.J_1907_R);
        this.addSettings(this.rezhimMode, this.lomatSkvozPautinuEnabled);
    }

    @Y_1740_V
    public void n_1700_B(Z_759_W event) {
        if (!this.lomatSkvozPautinuEnabled.isEnabled().booleanValue()) {
            return;
        }
        if (NoWeb.c_3005_b.Y_259_p == null || NoWeb.c_3005_b.Y_601_j == null) {
            return;
        }
        if (event.J_1907_R() != null && event.J_1907_R().J_1907_R() == a_3742_W.y_1700_S) {
            event.n_1700_B(true);
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (NoWeb.c_3005_b.Y_259_p == null || NoWeb.c_3005_b.Y_601_j == null) {
            return;
        }
        if (!NoWeb.c_3005_b.Y_259_p.RealmsClientOutdatedScreen) {
            return;
        }
        if (this.rezhimMode.isMode("Motion")) {
            this.h_1847_R();
        }
    }

    private void h_1847_R() {
        NoWeb.c_3005_b.Y_259_p.h_1847_R(NoWeb.c_3005_b.Y_259_p.I_4348_c().J_1907_R, 0.0, NoWeb.c_3005_b.Y_259_p.I_4348_c().G_564_y);
        if (NoWeb.c_3005_b.P_4830_p.Ping.G_564_y()) {
            NoWeb.c_3005_b.Y_259_p.h_1847_R(NoWeb.c_3005_b.Y_259_p.I_4348_c().J_1907_R, 0.995, NoWeb.c_3005_b.Y_259_p.I_4348_c().G_564_y);
        } else if (NoWeb.c_3005_b.P_4830_p.p_178_J.G_564_y()) {
            NoWeb.c_3005_b.Y_259_p.h_1847_R(NoWeb.c_3005_b.Y_259_p.I_4348_c().J_1907_R, -0.995, NoWeb.c_3005_b.Y_259_p.I_4348_c().G_564_y);
        }
        u_925_K.n_1700_B((double)0.22f);
    }
}



