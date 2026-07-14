/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_4925_L;
import lightning.product.NumberSetting;
import lightning.product.MobEffects;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.MultiBooleanSetting;
import lightning.product.P_2605_j;
import lightning.product.P_4526_H;
import lightning.product.Q_1939_l;
import lightning.product.Q_2753_H;
import lightning.product.V_3354_l;
import lightning.product.V_4557_X;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.a_178_J;
import lightning.product.GuiMove;
import lightning.product.a_3742_W;
import lightning.product.a_408_T;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.h_1015_G;
import lightning.product.k_2603_m;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.Items;
import lightning.product.r_1637_F;
import lightning.product.PrimedTnt;
import lightning.product.t_5_h;
import lightning.product.x_1688_C;
import lightning.product.ModuleCategory;

public class AutoTotem
extends Module {
    private final NumberSetting urovenZdorovyaSetting = new NumberSetting("\u0423\u0440\u043e\u0432\u0435\u043d\u044c \u0437\u0434\u043e\u0440\u043e\u0432\u044c\u044f", 4.0f, 1.0f, 20.0f, 0.5f);
    private final NumberSetting zdoroveNaElitreSetting = new NumberSetting("\u0417\u0434\u043e\u0440\u043e\u0432\u044c\u0435 \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u0435", 8.0f, 1.0f, 20.0f, 0.5f);
    private final BooleanSetting vozvraschatPredmetEnabled = new BooleanSetting("\u0412\u043e\u0437\u0432\u0440\u0430\u0449\u0430\u0442\u044c \u043f\u0440\u0435\u0434\u043c\u0435\u0442", true);
    private final BooleanSetting neMenyatSharEnabled = new BooleanSetting("\u041d\u0435 \u043c\u0435\u043d\u044f\u0442\u044c \u0448\u0430\u0440", true);
    private final BooleanSetting neSvapatEsliRukaAktivnaEnabled = new BooleanSetting("\u041d\u0435 \u0441\u0432\u0430\u043f\u0430\u0442\u044c, \u0435\u0441\u043b\u0438 \u0440\u0443\u043a\u0430 \u0430\u043a\u0442\u0438\u0432\u043d\u0430", false);
    private final BooleanSetting ignorirovatDeystviyaEnabled = new BooleanSetting("\u0418\u0433\u043d\u043e\u0440\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u0434\u0435\u0439\u0441\u0442\u0432\u0438\u044f", true, this::h_1847_R);
    private final BooleanSetting sohranyatZacharovannyyEnabled = new BooleanSetting("\u0421\u043e\u0445\u0440\u0430\u043d\u044f\u0442\u044c \u0437\u0430\u0447\u0430\u0440\u043e\u0432\u0430\u043d\u043d\u044b\u0439", true);
    private final BooleanSetting neBratTotemDrakonidaEnabled = new BooleanSetting("\u041d\u0435 \u0431\u0440\u0430\u0442\u044c \u0442\u043e\u0442\u0435\u043c \u0434\u0440\u0430\u043a\u043e\u043d\u0438\u0434\u0430", false);
    private final MultiBooleanSetting proverkiNaOptions = new MultiBooleanSetting("\u041f\u0440\u043e\u0432\u0435\u0440\u043a\u0438 \u043d\u0430", new BooleanSetting("\u0417\u043e\u043b\u043e\u0442\u044b\u0435 \u0441\u0435\u0440\u0434\u0446\u0430", true), new BooleanSetting("\u041a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u044b", true), new BooleanSetting("\u0414\u0438\u043d\u0430\u043c\u0438\u0442", true), new BooleanSetting("\u0412\u0430\u0433\u043e\u043d\u0435\u0442\u043a\u0430 \u0441 TNT", true), new BooleanSetting("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446", true), new BooleanSetting("\u042f\u043a\u043e\u0440\u044c", true), new BooleanSetting("\u041f\u0430\u0434\u0435\u043d\u0438\u0435", true));
    private final BooleanSetting bratOtHpPriVzryveEnabled = new BooleanSetting("\u0411\u0440\u0430\u0442\u044c \u043e\u0442 \u0445\u043f \u043f\u0440\u0438 \u0432\u0437\u0440\u044b\u0432\u0435", false);
    private final NumberSetting otSkolkiHpPriVzryveSetting = new NumberSetting("\u041e\u0442 \u0441\u043a\u043e\u043b\u044c\u043a\u0438 \u0445\u043f \u043f\u0440\u0438 \u0432\u0437\u0440\u044b\u0432\u0435", 10.0f, 1.0f, 20.0f, 0.5f, () -> this.bratOtHpPriVzryveEnabled.isEnabled());
    private final NumberSetting distanciyaDoKristallaSetting = new NumberSetting("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0434\u043e \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u0430", 6.0f, 1.0f, 10.0f, 1.0f, () -> this.proverkiNaOptions.isOptionEnabled("\u041a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u044b"));
    private final NumberSetting distanciyaDoDinamitaSetting = new NumberSetting("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0434\u043e \u0434\u0438\u043d\u0430\u043c\u0438\u0442\u0430", 6.0f, 1.0f, 50.0f, 1.0f, () -> this.proverkiNaOptions.isOptionEnabled("\u0414\u0438\u043d\u0430\u043c\u0438\u0442"));
    private final NumberSetting distanciyaDoTntvagonetkiSetting = new NumberSetting("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0434\u043e \u0422\u041d\u0422\u0412\u0430\u0433\u043e\u043d\u0435\u0442\u043a\u0438", 6.0f, 1.0f, 10.0f, 1.0f, () -> this.proverkiNaOptions.isOptionEnabled("\u0412\u0430\u0433\u043e\u043d\u0435\u0442\u043a\u0430 \u0441 TNT"));
    private final NumberSetting distanciyaTrezubcaSetting = new NumberSetting("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0442\u0440\u0435\u0437\u0443\u0431\u0446\u0430", 8.0f, 1.0f, 15.0f, 1.0f, () -> this.proverkiNaOptions.isOptionEnabled("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446"));
    private final NumberSetting distanciyaDoYakoryaSetting = new NumberSetting("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0434\u043e \u044f\u043a\u043e\u0440\u044f", 6.0f, 1.0f, 10.0f, 1.0f, () -> this.proverkiNaOptions.isOptionEnabled("\u042f\u043a\u043e\u0440\u044c"));
    private final BooleanSetting nastroykiDlyaSharovEnabled = new BooleanSetting("\u041d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438 \u0434\u043b\u044f \u0448\u0430\u0440\u043e\u0432", false);
    private final MultiBooleanSetting spisokSharovOptions = new MultiBooleanSetting("\u0421\u043f\u0438\u0441\u043e\u043a \u0448\u0430\u0440\u043e\u0432", this.nastroykiDlyaSharovEnabled::isEnabled, new BooleanSetting("\u0428\u0430\u0440 \u041e\u0433\u043d\u044f", false), new BooleanSetting("\u0428\u0430\u0440 \u0412\u043e\u0434\u044b", false), new BooleanSetting("\u0428\u0430\u0440 \u0417\u0435\u043c\u043b\u0438", false), new BooleanSetting("\u0428\u0430\u0440 \u0421\u0432\u0435\u0442\u0430", false), new BooleanSetting("\u0428\u0430\u0440 \u041f\u043e\u0440\u044f\u0434\u043a\u0430", false), new BooleanSetting("\u0428\u0430\u0440 \u0425\u0430\u043e\u0441\u0430", false), new BooleanSetting("\u0428\u0430\u0440 \u0412\u043e\u0437\u0434\u0443\u0445\u0430", false), new BooleanSetting("\u0428\u0430\u0440 BUNNY", false), new BooleanSetting("\u0428\u0430\u0440 \u0414.\u0425\u0435\u043b\u043f\u0435\u0440\u0430", false), new BooleanSetting("\u0428\u0430\u0440 \u0411\u043e\u0433\u0430", false));
    private final NumberSetting ogonDistanciyaKristallaSetting = new NumberSetting("\u041e\u0433\u043e\u043d\u044c: \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u0430", 6.0f, 1.0f, 10.0f, 1.0f, () -> this.nastroykiDlyaSharovEnabled.isEnabled() != false && this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u041e\u0433\u043d\u044f") != false);
    private final NumberSetting ogonHpTotemaSetting = new NumberSetting("\u041e\u0433\u043e\u043d\u044c: \u0445\u043f \u0442\u043e\u0442\u0435\u043c\u0430", 4.0f, 1.0f, 20.0f, 0.5f, () -> this.nastroykiDlyaSharovEnabled.isEnabled() != false && this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u041e\u0433\u043d\u044f") != false);
    private final NumberSetting vodaDistanciyaKristallaSetting = new NumberSetting("\u0412\u043e\u0434\u0430: \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u0430", 6.0f, 1.0f, 10.0f, 1.0f, () -> this.nastroykiDlyaSharovEnabled.isEnabled() != false && this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0412\u043e\u0434\u044b") != false);
    private final NumberSetting vodaHpTotemaSetting = new NumberSetting("\u0412\u043e\u0434\u0430: \u0445\u043f \u0442\u043e\u0442\u0435\u043c\u0430", 4.0f, 1.0f, 20.0f, 0.5f, () -> this.nastroykiDlyaSharovEnabled.isEnabled() != false && this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0412\u043e\u0434\u044b") != false);
    private final NumberSetting zemlyaDistanciyaKristallaSetting = new NumberSetting("\u0417\u0435\u043c\u043b\u044f: \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u0430", 6.0f, 1.0f, 10.0f, 1.0f, () -> this.nastroykiDlyaSharovEnabled.isEnabled() != false && this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0417\u0435\u043c\u043b\u0438") != false);
    private final NumberSetting zemlyaHpTotemaSetting = new NumberSetting("\u0417\u0435\u043c\u043b\u044f: \u0445\u043f \u0442\u043e\u0442\u0435\u043c\u0430", 4.0f, 1.0f, 20.0f, 0.5f, () -> this.nastroykiDlyaSharovEnabled.isEnabled() != false && this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0417\u0435\u043c\u043b\u0438") != false);
    private final NumberSetting svetDistanciyaKristallaSetting = new NumberSetting("\u0421\u0432\u0435\u0442: \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u0430", 6.0f, 1.0f, 10.0f, 1.0f, () -> this.nastroykiDlyaSharovEnabled.isEnabled() != false && this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0421\u0432\u0435\u0442\u0430") != false);
    private final NumberSetting svetHpTotemaSetting = new NumberSetting("\u0421\u0432\u0435\u0442: \u0445\u043f \u0442\u043e\u0442\u0435\u043c\u0430", 4.0f, 1.0f, 20.0f, 0.5f, () -> this.nastroykiDlyaSharovEnabled.isEnabled() != false && this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0421\u0432\u0435\u0442\u0430") != false);
    private final NumberSetting poryadokDistanciyaKristallaSetting = new NumberSetting("\u041f\u043e\u0440\u044f\u0434\u043e\u043a: \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u0430", 6.0f, 1.0f, 10.0f, 1.0f, () -> this.nastroykiDlyaSharovEnabled.isEnabled() != false && this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u041f\u043e\u0440\u044f\u0434\u043a\u0430") != false);
    private final NumberSetting poryadokHpTotemaSetting = new NumberSetting("\u041f\u043e\u0440\u044f\u0434\u043e\u043a: \u0445\u043f \u0442\u043e\u0442\u0435\u043c\u0430", 4.0f, 1.0f, 20.0f, 0.5f, () -> this.nastroykiDlyaSharovEnabled.isEnabled() != false && this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u041f\u043e\u0440\u044f\u0434\u043a\u0430") != false);
    private final NumberSetting haosDistanciyaKristallaSetting = new NumberSetting("\u0425\u0430\u043e\u0441: \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u0430", 6.0f, 1.0f, 10.0f, 1.0f, () -> this.nastroykiDlyaSharovEnabled.isEnabled() != false && this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0425\u0430\u043e\u0441\u0430") != false);
    private final NumberSetting haosHpTotemaSetting = new NumberSetting("\u0425\u0430\u043e\u0441: \u0445\u043f \u0442\u043e\u0442\u0435\u043c\u0430", 4.0f, 1.0f, 20.0f, 0.5f, () -> this.nastroykiDlyaSharovEnabled.isEnabled() != false && this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0425\u0430\u043e\u0441\u0430") != false);
    private final NumberSetting vozduhDistanciyaKristallaSetting = new NumberSetting("\u0412\u043e\u0437\u0434\u0443\u0445: \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u0430", 6.0f, 1.0f, 10.0f, 1.0f, () -> this.nastroykiDlyaSharovEnabled.isEnabled() != false && this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0412\u043e\u0437\u0434\u0443\u0445\u0430") != false);
    private final NumberSetting vozduhHpTotemaSetting = new NumberSetting("\u0412\u043e\u0437\u0434\u0443\u0445: \u0445\u043f \u0442\u043e\u0442\u0435\u043c\u0430", 4.0f, 1.0f, 20.0f, 0.5f, () -> this.nastroykiDlyaSharovEnabled.isEnabled() != false && this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0412\u043e\u0437\u0434\u0443\u0445\u0430") != false);
    private final NumberSetting bunnyDistanciyaKristallaSetting = new NumberSetting("BUNNY: \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u0430", 6.0f, 1.0f, 10.0f, 1.0f, () -> this.nastroykiDlyaSharovEnabled.isEnabled() != false && this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 BUNNY") != false);
    private final NumberSetting bunnyHpTotemaSetting = new NumberSetting("BUNNY: \u0445\u043f \u0442\u043e\u0442\u0435\u043c\u0430", 4.0f, 1.0f, 20.0f, 0.5f, () -> this.nastroykiDlyaSharovEnabled.isEnabled() != false && this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 BUNNY") != false);
    private final NumberSetting dHelperDistanciyaKristallaSetting = new NumberSetting("\u0414.\u0425\u0435\u043b\u043f\u0435\u0440: \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u0430", 6.0f, 1.0f, 10.0f, 1.0f, () -> this.nastroykiDlyaSharovEnabled.isEnabled() != false && this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0414.\u0425\u0435\u043b\u043f\u0435\u0440\u0430") != false);
    private final NumberSetting dHelperHpTotemaSetting = new NumberSetting("\u0414.\u0425\u0435\u043b\u043f\u0435\u0440: \u0445\u043f \u0442\u043e\u0442\u0435\u043c\u0430", 4.0f, 1.0f, 20.0f, 0.5f, () -> this.nastroykiDlyaSharovEnabled.isEnabled() != false && this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0414.\u0425\u0435\u043b\u043f\u0435\u0440\u0430") != false);
    private final NumberSetting bogDistanciyaKristallaSetting = new NumberSetting("\u0411\u043e\u0433: \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u0430", 6.0f, 1.0f, 10.0f, 1.0f, () -> this.nastroykiDlyaSharovEnabled.isEnabled() != false && this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0411\u043e\u0433\u0430") != false);
    private final NumberSetting bogHpTotemaSetting = new NumberSetting("\u0411\u043e\u0433: \u0445\u043f \u0442\u043e\u0442\u0435\u043c\u0430", 4.0f, 1.0f, 20.0f, 0.5f, () -> this.nastroykiDlyaSharovEnabled.isEnabled() != false && this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0411\u043e\u0433\u0430") != false);
    public static boolean v_4262_N = false;
    private int N_2525_X;
    private int c_4037_x = -1;
    private int g_2268_R;
    private int T_3594_S = 0;
    private int D_4792_h = -1;
    private int s_2632_s = 0;
    private final V_4557_X l_1233_K = new V_4557_X();
    private boolean z_1333_t = false;
    private int O_508_d = -1;
    private boolean r_715_M = false;
    private boolean A_1038_p = false;

    public AutoTotem() {
        super("AutoTotem", ModuleCategory.n_1700_B);
        this.addSettings(this.urovenZdorovyaSetting, this.zdoroveNaElitreSetting, this.vozvraschatPredmetEnabled, this.neMenyatSharEnabled, this.neSvapatEsliRukaAktivnaEnabled, this.ignorirovatDeystviyaEnabled, this.sohranyatZacharovannyyEnabled, this.neBratTotemDrakonidaEnabled, this.proverkiNaOptions, this.bratOtHpPriVzryveEnabled, this.otSkolkiHpPriVzryveSetting, this.distanciyaDoKristallaSetting, this.distanciyaDoDinamitaSetting, this.distanciyaDoTntvagonetkiSetting, this.distanciyaTrezubcaSetting, this.distanciyaDoYakoryaSetting);
    }

    private boolean h_1847_R() {
        GuiMove guiWalk = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
        return guiWalk != null && guiWalk.w_1484_f() && guiWalk.v_4262_N.J_1907_R("Funtime");
    }

    private boolean Q_4569_t() {
        GuiMove guiWalk = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
        return guiWalk != null && guiWalk.Q_4569_t();
    }

    private boolean M_182_A() {
        GuiMove guiWalk = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
        return guiWalk != null && guiWalk.h_1847_R();
    }

    private boolean t_1786_h() {
        GuiMove guiWalk = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
        return guiWalk != null && guiWalk.M_182_A();
    }

    @Y_1740_V
    private void n_1700_B(h_1015_G event) {
        GuiMove guiWalk;
        long delay;
        Slot totemSlot;
        if (this.s_2632_s > 0) {
            --this.s_2632_s;
        }
        if (this.z_1333_t && !this.Y_259_p() && !this.multiplayerClientSuggestionProvider() && this.Q_2552_b() && this.r_715_M && (totemSlot = this.Y_601_j()) != null) {
            this.O_508_d = totemSlot.G_564_y;
            this.r_715_M = false;
            this.l_1233_K.n_1700_B();
        }
        long l = delay = this.A_1038_p ? 50L : 100L;
        if (this.z_1333_t && this.O_508_d != -1 && this.l_1233_K.J_1907_R(delay)) {
            if (AutoTotem.c_3005_b.Y_259_p.Y_601_j() && !this.Q_2552_b() && !this.w_1457_N()) {
                return;
            }
            if (this.r_715_M && (this.Q_2552_b() || this.w_1457_N())) {
                this.z_1333_t = false;
                this.r_715_M = false;
                this.O_508_d = -1;
                this.g_2268_R = 0;
                return;
            }
            if (this.ignorirovatDeystviyaEnabled.isEnabled().booleanValue()) {
                v_4262_N = false;
            }
            c_3005_b.n_1700_B(new Q_1939_l(AutoTotem.c_3005_b.Y_259_p));
            AutoTotem.c_3005_b.w_1457_N.windowClick(AutoTotem.c_3005_b.Y_259_p.o_1800_r.u_1723_Y, this.O_508_d, 40, a_408_T.R_4764_Y, AutoTotem.c_3005_b.Y_259_p);
            AutoTotem.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(AutoTotem.c_3005_b.Y_259_p.o_1800_r.u_1723_Y));
            c_3005_b.n_1700_B((k_2603_m)null);
            this.z_1333_t = false;
            this.A_1038_p = false;
            v_4262_N = false;
            if (this.r_715_M) {
                this.c_4037_x = -1;
                this.r_715_M = false;
            }
            this.O_508_d = -1;
            this.g_2268_R = 0;
        }
        if (this.z_1333_t) {
            return;
        }
        if (this.T_3594_S == 1) {
            if (this.Q_2552_b() || this.w_1457_N()) {
                this.T_3594_S = 0;
                this.D_4792_h = -1;
                return;
            }
            guiWalk = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
            if (guiWalk != null && guiWalk.M_182_A()) {
                guiWalk.s_956_w = true;
            }
            AutoTotem.c_3005_b.w_1457_N.windowClick(AutoTotem.c_3005_b.Y_259_p.o_1800_r.u_1723_Y, this.D_4792_h, 40, a_408_T.R_4764_Y, AutoTotem.c_3005_b.Y_259_p);
            AutoTotem.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(AutoTotem.c_3005_b.Y_259_p.o_1800_r.u_1723_Y));
            if (guiWalk != null && guiWalk.M_182_A()) {
                guiWalk.s_956_w = false;
            }
            this.T_3594_S = 0;
            this.D_4792_h = -1;
        }
        if (this.T_3594_S == 2 && this.s_2632_s == 0) {
            this.T_3594_S = 3;
        }
        if (this.T_3594_S == 3 && this.D_4792_h != -1) {
            guiWalk = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
            if (guiWalk != null && guiWalk.M_182_A()) {
                guiWalk.s_956_w = true;
            }
            AutoTotem.c_3005_b.w_1457_N.windowClick(AutoTotem.c_3005_b.Y_259_p.o_1800_r.u_1723_Y, this.D_4792_h, 40, a_408_T.R_4764_Y, AutoTotem.c_3005_b.Y_259_p);
            AutoTotem.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(AutoTotem.c_3005_b.Y_259_p.o_1800_r.u_1723_Y));
            if (guiWalk != null && guiWalk.M_182_A()) {
                guiWalk.s_956_w = false;
            }
            this.T_3594_S = 0;
            this.D_4792_h = -1;
            return;
        }
        if (this.T_3594_S > 0) {
            return;
        }
        ++this.g_2268_R;
        Slot slot = this.Y_601_j();
        if (this.g_2268_R >= 15) {
            if (!this.Y_259_p() && !this.multiplayerClientSuggestionProvider() && this.Q_2552_b() && slot != null) {
                if (this.h_1847_R()) {
                    this.A_1038_p = true;
                }
                this.J_1907_R(slot.G_564_y);
                return;
            }
            if (this.w_1457_N()) {
                if (slot != null && !this.multiplayerClientSuggestionProvider()) {
                    this.J_1907_R(slot.G_564_y);
                }
            } else if (this.c_4037_x != -1 && this.vozvraschatPredmetEnabled.isEnabled().booleanValue()) {
                if (this.Q_2552_b()) {
                    return;
                }
                if (this.h_1847_R()) {
                    this.l_1233_K.n_1700_B();
                    this.z_1333_t = true;
                    this.r_715_M = true;
                    this.O_508_d = this.c_4037_x;
                    this.g_2268_R = 0;
                } else if (this.M_182_A() || this.t_1786_h()) {
                    this.s_2632_s = 1;
                    this.T_3594_S = 1;
                    this.D_4792_h = this.c_4037_x;
                    this.c_4037_x = -1;
                } else {
                    AutoTotem.c_3005_b.w_1457_N.windowClick(AutoTotem.c_3005_b.Y_259_p.o_1800_r.u_1723_Y, this.c_4037_x, 40, a_408_T.R_4764_Y, AutoTotem.c_3005_b.Y_259_p);
                    AutoTotem.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(AutoTotem.c_3005_b.Y_259_p.o_1800_r.u_1723_Y));
                    this.c_4037_x = -1;
                    this.g_2268_R = 0;
                }
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(a_178_J e) {
        boolean isUsingItem = AutoTotem.c_3005_b.Y_259_p.Y_601_j();
        if (this.s_2632_s > 0 && !isUsingItem) {
            e.n_1700_B(0.0f);
            e.J_1907_R(0.0f);
            e.P_1922_E(false);
            e.u_1723_Y(false);
        }
        if (this.z_1333_t && !this.l_1233_K.J_1907_R(150L) && !isUsingItem) {
            e.n_1700_B(0.0f);
            e.J_1907_R(0.0f);
            e.P_1922_E(false);
            e.u_1723_Y(false);
        }
    }

    @Y_1740_V
    private void n_1700_B(Q_2753_H event) {
        if (!event.J_1907_R()) {
            return;
        }
        if (event.G_564_y() instanceof ClientboundAddEntityPacket) {
            ClientboundAddEntityPacket packet = (ClientboundAddEntityPacket)event.G_564_y();
            t_5_h<?> type = packet.M_588_G();
            double distance = Math.sqrt(Math.pow(AutoTotem.c_3005_b.Y_259_p.O_3598_v() - packet.G_564_y(), 2.0) + Math.pow(AutoTotem.c_3005_b.Y_259_p.X_2960_b() - packet.P_1922_E(), 2.0) + Math.pow(AutoTotem.c_3005_b.Y_259_p.l_2647_k() - packet.u_1723_Y(), 2.0));
            boolean shouldSwap = false;
            if (type == t_5_h.w_1457_N && this.proverkiNaOptions.isOptionEnabled("\u041a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u044b").booleanValue() && distance <= (double)this.C_2741_M()) {
                if (this.bratOtHpPriVzryveEnabled.isEnabled().booleanValue() && AutoTotem.c_3005_b.Y_259_p.S_4035_N().J_1907_R() == Items.C_3560_B) {
                    float health = AutoTotem.c_3005_b.Y_259_p.g_46_E();
                    if (AutoTotem.c_3005_b.Y_259_p.J_1907_R(MobEffects.Q_2552_b)) {
                        health += AutoTotem.c_3005_b.Y_259_p.U_3823_u();
                    }
                    shouldSwap = health <= ((Float)this.otSkolkiHpPriVzryveSetting.getValue()).floatValue();
                } else {
                    shouldSwap = true;
                }
            } else if (type == t_5_h.f_4016_n && this.proverkiNaOptions.isOptionEnabled("\u0414\u0438\u043d\u0430\u043c\u0438\u0442").booleanValue() && distance <= (double)((Float)this.distanciyaDoDinamitaSetting.getValue()).floatValue()) {
                shouldSwap = true;
            } else if (type == t_5_h.g_2268_R && this.proverkiNaOptions.isOptionEnabled("\u0412\u0430\u0433\u043e\u043d\u0435\u0442\u043a\u0430 \u0441 TNT").booleanValue() && distance <= (double)((Float)this.distanciyaDoTntvagonetkiSetting.getValue()).floatValue()) {
                shouldSwap = true;
            } else if (type == t_5_h.ValueObject && this.proverkiNaOptions.isOptionEnabled("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446").booleanValue() && distance <= (double)((Float)this.distanciyaTrezubcaSetting.getValue()).floatValue()) {
                shouldSwap = true;
            }
            if (!shouldSwap) {
                return;
            }
            if (this.Y_259_p() || this.multiplayerClientSuggestionProvider()) {
                return;
            }
            Slot totemSlot = this.Y_601_j();
            if (totemSlot == null) {
                return;
            }
            if (this.h_1847_R()) {
                this.A_1038_p = true;
            }
            this.J_1907_R(totemSlot.G_564_y);
        }
    }

    private boolean multiplayerClientSuggestionProvider() {
        for (x_1688_C hand : x_1688_C.values()) {
            Z_1993_T stack = AutoTotem.c_3005_b.Y_259_p.R_4764_Y(hand);
            if (stack.J_1907_R() != Items.N_81_X || this.sohranyatZacharovannyyEnabled.isEnabled().booleanValue() && stack.k_2293_S() && this.N_2525_X > 0 || this.neBratTotemDrakonidaEnabled.isEnabled().booleanValue() && stack.multiplayerClientSuggestionProvider().getString().equals("\u0422\u043e\u0442\u0435\u043c \u0414\u0440\u0430\u043a\u043e\u043d\u0438\u0434\u0430")) continue;
            return true;
        }
        return false;
    }

    private boolean w_1457_N() {
        boolean blockByBall;
        float currentHealth = AutoTotem.c_3005_b.Y_259_p.g_46_E();
        if (this.proverkiNaOptions.isOptionEnabled("\u0417\u043e\u043b\u043e\u0442\u044b\u0435 \u0441\u0435\u0440\u0434\u0446\u0430").booleanValue() && AutoTotem.c_3005_b.Y_259_p.J_1907_R(MobEffects.Q_2552_b)) {
            currentHealth += AutoTotem.c_3005_b.Y_259_p.U_3823_u();
        }
        if (!(blockByBall = this.Y_259_p()) && this.proverkiNaOptions.isOptionEnabled("\u041f\u0430\u0434\u0435\u043d\u0438\u0435").booleanValue() && !AutoTotem.c_3005_b.Y_259_p.RowButton() && !AutoTotem.c_3005_b.Y_259_p.k_578_l() && AutoTotem.c_3005_b.Y_259_p.U_1241_n > 10.0f) {
            return true;
        }
        float healthThreshold = this.k_2293_S();
        return currentHealth <= healthThreshold;
    }

    private Slot Y_601_j() {
        this.N_2525_X = (int)AutoTotem.c_3005_b.Y_259_p.H_1873_g.P_1922_E.stream().filter(s -> s.n_1700_B().J_1907_R() == Items.N_81_X && !s.n_1700_B().k_2293_S()).count();
        return AutoTotem.c_3005_b.Y_259_p.H_1873_g.P_1922_E.stream().filter(s -> s.n_1700_B().J_1907_R() == Items.N_81_X && (this.sohranyatZacharovannyyEnabled.isEnabled() == false || !s.n_1700_B().k_2293_S() || this.N_2525_X <= 0)).filter(s -> this.neBratTotemDrakonidaEnabled.isEnabled() == false || !s.n_1700_B().multiplayerClientSuggestionProvider().getString().equals("\u0422\u043e\u0442\u0435\u043c \u0414\u0440\u0430\u043a\u043e\u043d\u0438\u0434\u0430")).findFirst().orElse(null);
    }

    private void J_1907_R(int fromSlot) {
        if (this.neSvapatEsliRukaAktivnaEnabled.isEnabled().booleanValue() && AutoTotem.c_3005_b.Y_259_p.Y_601_j()) {
            return;
        }
        if (!AutoTotem.c_3005_b.Y_259_p.S_4035_N().n_1700_B() && this.c_4037_x == -1) {
            this.c_4037_x = fromSlot;
        }
        if (this.h_1847_R()) {
            this.l_1233_K.n_1700_B();
            this.z_1333_t = true;
            this.O_508_d = fromSlot;
            this.g_2268_R = 0;
            return;
        }
        if (this.M_182_A() || this.t_1786_h()) {
            this.s_2632_s = 2;
            this.T_3594_S = 2;
            this.D_4792_h = fromSlot;
            this.g_2268_R = 0;
            return;
        }
        if (this.Q_4569_t()) {
            GuiMove guiWalk = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
            guiWalk.multiplayerClientSuggestionProvider();
        }
        AutoTotem.c_3005_b.w_1457_N.windowClick(0, fromSlot, 40, a_408_T.R_4764_Y, AutoTotem.c_3005_b.Y_259_p);
        AutoTotem.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(AutoTotem.c_3005_b.Y_259_p.H_1873_g.u_1723_Y));
        this.g_2268_R = 0;
    }

    private boolean Y_259_p() {
        return !(this.neMenyatSharEnabled.isEnabled() == false || AutoTotem.c_3005_b.Y_259_p.S_4035_N().J_1907_R() != Items.C_3560_B || this.proverkiNaOptions.isOptionEnabled("\u041f\u0430\u0434\u0435\u043d\u0438\u0435") != false && AutoTotem.c_3005_b.Y_259_p.U_1241_n > 5.0f);
    }

    private boolean Q_2552_b() {
        if (this.proverkiNaOptions.isOptionEnabled("\u041a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u044b").booleanValue()) {
            boolean crystalNearby;
            boolean bl = crystalNearby = !AutoTotem.c_3005_b.Y_601_j.n_1700_B(V_3354_l.class, AutoTotem.c_3005_b.Y_259_p.i_601_W().grow(this.C_2741_M())).isEmpty();
            if (crystalNearby) {
                if (this.bratOtHpPriVzryveEnabled.isEnabled().booleanValue() && AutoTotem.c_3005_b.Y_259_p.S_4035_N().J_1907_R() == Items.C_3560_B) {
                    float health = AutoTotem.c_3005_b.Y_259_p.g_46_E();
                    if (AutoTotem.c_3005_b.Y_259_p.J_1907_R(MobEffects.Q_2552_b)) {
                        health += AutoTotem.c_3005_b.Y_259_p.U_3823_u();
                    }
                    if (health <= ((Float)this.otSkolkiHpPriVzryveSetting.getValue()).floatValue()) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }
        if (this.proverkiNaOptions.isOptionEnabled("\u0414\u0438\u043d\u0430\u043c\u0438\u0442").booleanValue() && !AutoTotem.c_3005_b.Y_601_j.n_1700_B(PrimedTnt.class, AutoTotem.c_3005_b.Y_259_p.i_601_W().grow(((Float)this.distanciyaDoDinamitaSetting.getValue()).floatValue())).isEmpty()) {
            return true;
        }
        if (this.proverkiNaOptions.isOptionEnabled("\u0412\u0430\u0433\u043e\u043d\u0435\u0442\u043a\u0430 \u0441 TNT").booleanValue() && !AutoTotem.c_3005_b.Y_601_j.n_1700_B(r_1637_F.class, AutoTotem.c_3005_b.Y_259_p.i_601_W().grow(((Float)this.distanciyaDoTntvagonetkiSetting.getValue()).floatValue())).isEmpty()) {
            return true;
        }
        if (this.proverkiNaOptions.isOptionEnabled("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446").booleanValue()) {
            for (E_4925_L trident : AutoTotem.c_3005_b.Y_601_j.n_1700_B(E_4925_L.class, AutoTotem.c_3005_b.Y_259_p.i_601_W().grow(((Float)this.distanciyaTrezubcaSetting.getValue()).floatValue()))) {
                if (trident.Y_601_j() == AutoTotem.c_3005_b.Y_259_p) continue;
                return true;
            }
        }
        return this.proverkiNaOptions.isOptionEnabled("\u042f\u043a\u043e\u0440\u044c") != false && AutoTotem.c_3005_b.Y_601_j.g_2268_R() != b_4507_u.v_4262_N && this.Z_875_P();
    }

    private float C_2741_M() {
        if (!this.nastroykiDlyaSharovEnabled.isEnabled().booleanValue()) {
            return ((Float)this.distanciyaDoKristallaSetting.getValue()).floatValue();
        }
        String sphere = this.q_2307_F();
        if (sphere == null) {
            return ((Float)this.distanciyaDoKristallaSetting.getValue()).floatValue();
        }
        return switch (sphere) {
            case "\u0428\u0430\u0440 \u041e\u0433\u043d\u044f" -> (this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u041e\u0433\u043d\u044f") != false ? (Float)this.ogonDistanciyaKristallaSetting.getValue() : (Float)this.distanciyaDoKristallaSetting.getValue()).floatValue();
            case "\u0428\u0430\u0440 \u0412\u043e\u0434\u044b" -> (this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0412\u043e\u0434\u044b") != false ? (Float)this.vodaDistanciyaKristallaSetting.getValue() : (Float)this.distanciyaDoKristallaSetting.getValue()).floatValue();
            case "\u0428\u0430\u0440 \u0417\u0435\u043c\u043b\u0438" -> (this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0417\u0435\u043c\u043b\u0438") != false ? (Float)this.zemlyaDistanciyaKristallaSetting.getValue() : (Float)this.distanciyaDoKristallaSetting.getValue()).floatValue();
            case "\u0428\u0430\u0440 \u0421\u0432\u0435\u0442\u0430" -> (this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0421\u0432\u0435\u0442\u0430") != false ? (Float)this.svetDistanciyaKristallaSetting.getValue() : (Float)this.distanciyaDoKristallaSetting.getValue()).floatValue();
            case "\u0428\u0430\u0440 \u041f\u043e\u0440\u044f\u0434\u043a\u0430" -> (this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u041f\u043e\u0440\u044f\u0434\u043a\u0430") != false ? (Float)this.poryadokDistanciyaKristallaSetting.getValue() : (Float)this.distanciyaDoKristallaSetting.getValue()).floatValue();
            case "\u0428\u0430\u0440 \u0425\u0430\u043e\u0441\u0430" -> (this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0425\u0430\u043e\u0441\u0430") != false ? (Float)this.haosDistanciyaKristallaSetting.getValue() : (Float)this.distanciyaDoKristallaSetting.getValue()).floatValue();
            case "\u0428\u0430\u0440 \u0412\u043e\u0437\u0434\u0443\u0445\u0430" -> (this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0412\u043e\u0437\u0434\u0443\u0445\u0430") != false ? (Float)this.vozduhDistanciyaKristallaSetting.getValue() : (Float)this.distanciyaDoKristallaSetting.getValue()).floatValue();
            case "\u0428\u0430\u0440 BUNNY" -> (this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 BUNNY") != false ? (Float)this.bunnyDistanciyaKristallaSetting.getValue() : (Float)this.distanciyaDoKristallaSetting.getValue()).floatValue();
            case "\u0428\u0430\u0440 \u0414.\u0425\u0435\u043b\u043f\u0435\u0440\u0430" -> (this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0414.\u0425\u0435\u043b\u043f\u0435\u0440\u0430") != false ? (Float)this.dHelperDistanciyaKristallaSetting.getValue() : (Float)this.distanciyaDoKristallaSetting.getValue()).floatValue();
            case "\u0428\u0430\u0440 \u0411\u043e\u0433\u0430" -> (this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0411\u043e\u0433\u0430") != false ? (Float)this.bogDistanciyaKristallaSetting.getValue() : (Float)this.distanciyaDoKristallaSetting.getValue()).floatValue();
            default -> ((Float)this.distanciyaDoKristallaSetting.getValue()).floatValue();
        };
    }

    private float k_2293_S() {
        if (!this.nastroykiDlyaSharovEnabled.isEnabled().booleanValue()) {
            return (AutoTotem.c_3005_b.Y_259_p.k_578_l() ? (Float)this.zdoroveNaElitreSetting.getValue() : (Float)this.urovenZdorovyaSetting.getValue()).floatValue();
        }
        String sphere = this.q_2307_F();
        if (sphere == null) {
            return (AutoTotem.c_3005_b.Y_259_p.k_578_l() ? (Float)this.zdoroveNaElitreSetting.getValue() : (Float)this.urovenZdorovyaSetting.getValue()).floatValue();
        }
        return switch (sphere) {
            case "\u0428\u0430\u0440 \u041e\u0433\u043d\u044f" -> (this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u041e\u0433\u043d\u044f") != false ? (Float)this.ogonHpTotemaSetting.getValue() : (AutoTotem.c_3005_b.Y_259_p.k_578_l() ? (Float)this.zdoroveNaElitreSetting.getValue() : (Float)this.urovenZdorovyaSetting.getValue())).floatValue();
            case "\u0428\u0430\u0440 \u0412\u043e\u0434\u044b" -> (this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0412\u043e\u0434\u044b") != false ? (Float)this.vodaHpTotemaSetting.getValue() : (AutoTotem.c_3005_b.Y_259_p.k_578_l() ? (Float)this.zdoroveNaElitreSetting.getValue() : (Float)this.urovenZdorovyaSetting.getValue())).floatValue();
            case "\u0428\u0430\u0440 \u0417\u0435\u043c\u043b\u0438" -> (this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0417\u0435\u043c\u043b\u0438") != false ? (Float)this.zemlyaHpTotemaSetting.getValue() : (AutoTotem.c_3005_b.Y_259_p.k_578_l() ? (Float)this.zdoroveNaElitreSetting.getValue() : (Float)this.urovenZdorovyaSetting.getValue())).floatValue();
            case "\u0428\u0430\u0440 \u0421\u0432\u0435\u0442\u0430" -> (this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0421\u0432\u0435\u0442\u0430") != false ? (Float)this.svetHpTotemaSetting.getValue() : (AutoTotem.c_3005_b.Y_259_p.k_578_l() ? (Float)this.zdoroveNaElitreSetting.getValue() : (Float)this.urovenZdorovyaSetting.getValue())).floatValue();
            case "\u0428\u0430\u0440 \u041f\u043e\u0440\u044f\u0434\u043a\u0430" -> (this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u041f\u043e\u0440\u044f\u0434\u043a\u0430") != false ? (Float)this.poryadokHpTotemaSetting.getValue() : (AutoTotem.c_3005_b.Y_259_p.k_578_l() ? (Float)this.zdoroveNaElitreSetting.getValue() : (Float)this.urovenZdorovyaSetting.getValue())).floatValue();
            case "\u0428\u0430\u0440 \u0425\u0430\u043e\u0441\u0430" -> (this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0425\u0430\u043e\u0441\u0430") != false ? (Float)this.haosHpTotemaSetting.getValue() : (AutoTotem.c_3005_b.Y_259_p.k_578_l() ? (Float)this.zdoroveNaElitreSetting.getValue() : (Float)this.urovenZdorovyaSetting.getValue())).floatValue();
            case "\u0428\u0430\u0440 \u0412\u043e\u0437\u0434\u0443\u0445\u0430" -> (this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0412\u043e\u0437\u0434\u0443\u0445\u0430") != false ? (Float)this.vozduhHpTotemaSetting.getValue() : (AutoTotem.c_3005_b.Y_259_p.k_578_l() ? (Float)this.zdoroveNaElitreSetting.getValue() : (Float)this.urovenZdorovyaSetting.getValue())).floatValue();
            case "\u0428\u0430\u0440 BUNNY" -> (this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 BUNNY") != false ? (Float)this.bunnyHpTotemaSetting.getValue() : (AutoTotem.c_3005_b.Y_259_p.k_578_l() ? (Float)this.zdoroveNaElitreSetting.getValue() : (Float)this.urovenZdorovyaSetting.getValue())).floatValue();
            case "\u0428\u0430\u0440 \u0414.\u0425\u0435\u043b\u043f\u0435\u0440\u0430" -> (this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0414.\u0425\u0435\u043b\u043f\u0435\u0440\u0430") != false ? (Float)this.dHelperHpTotemaSetting.getValue() : (AutoTotem.c_3005_b.Y_259_p.k_578_l() ? (Float)this.zdoroveNaElitreSetting.getValue() : (Float)this.urovenZdorovyaSetting.getValue())).floatValue();
            case "\u0428\u0430\u0440 \u0411\u043e\u0433\u0430" -> (this.spisokSharovOptions.isOptionEnabled("\u0428\u0430\u0440 \u0411\u043e\u0433\u0430") != false ? (Float)this.bogHpTotemaSetting.getValue() : (AutoTotem.c_3005_b.Y_259_p.k_578_l() ? (Float)this.zdoroveNaElitreSetting.getValue() : (Float)this.urovenZdorovyaSetting.getValue())).floatValue();
            default -> (AutoTotem.c_3005_b.Y_259_p.k_578_l() ? (Float)this.zdoroveNaElitreSetting.getValue() : (Float)this.urovenZdorovyaSetting.getValue()).floatValue();
        };
    }

    private String q_2307_F() {
        if (AutoTotem.c_3005_b.Y_259_p == null) {
            return null;
        }
        Z_1993_T offhand = AutoTotem.c_3005_b.Y_259_p.S_4035_N();
        if (offhand.n_1700_B() || offhand.J_1907_R() != Items.C_3560_B) {
            return null;
        }
        String name = offhand.multiplayerClientSuggestionProvider().getString().toLowerCase();
        if (name.contains("\u0448\u0430\u0440 \u043e\u0433\u043d\u044f")) {
            return "\u0428\u0430\u0440 \u041e\u0433\u043d\u044f";
        }
        if (name.contains("\u0448\u0430\u0440 \u0432\u043e\u0434\u044b")) {
            return "\u0428\u0430\u0440 \u0412\u043e\u0434\u044b";
        }
        if (name.contains("\u0448\u0430\u0440 \u0437\u0435\u043c\u043b\u0438")) {
            return "\u0428\u0430\u0440 \u0417\u0435\u043c\u043b\u0438";
        }
        if (name.contains("\u0448\u0430\u0440 \u0441\u0432\u0435\u0442\u0430")) {
            return "\u0428\u0430\u0440 \u0421\u0432\u0435\u0442\u0430";
        }
        if (name.contains("\u0448\u0430\u0440 \u043f\u043e\u0440\u044f\u0434\u043a\u0430")) {
            return "\u0428\u0430\u0440 \u041f\u043e\u0440\u044f\u0434\u043a\u0430";
        }
        if (name.contains("\u0448\u0430\u0440 \u0445\u0430\u043e\u0441\u0430")) {
            return "\u0428\u0430\u0440 \u0425\u0430\u043e\u0441\u0430";
        }
        if (name.contains("\u0448\u0430\u0440 \u0432\u043e\u0437\u0434\u0443\u0445\u0430")) {
            return "\u0428\u0430\u0440 \u0412\u043e\u0437\u0434\u0443\u0445\u0430";
        }
        if (name.contains("\u0448\u0430\u0440 bunny")) {
            return "\u0428\u0430\u0440 BUNNY";
        }
        if (name.contains("\u0448\u0430\u0440 \u0414.\u0425\u0435\u043b\u043f\u0435\u0440\u0430")) {
            return "\u0428\u0430\u0440 \u0414.\u0425\u0435\u043b\u043f\u0435\u0440\u0430";
        }
        if (name.contains("\u0448\u0430\u0440 \u0431\u043e\u0433\u0430")) {
            return "\u0428\u0430\u0440 \u0411\u043e\u0433\u0430";
        }
        return null;
    }

    private boolean Z_875_P() {
        c_1514_x playerPos = AutoTotem.c_3005_b.Y_259_p.b_2312_j();
        int range = (int)Math.ceil(((Float)this.distanciyaDoYakoryaSetting.getValue()).floatValue());
        for (int x = -range; x <= range; ++x) {
            for (int y = -range; y <= range; ++y) {
                for (int z = -range; z <= range; ++z) {
                    double dist;
                    int charges;
                    c_1514_x pos = playerPos.add(x, y, z);
                    if (AutoTotem.c_3005_b.Y_601_j.getBlockState(pos).J_1907_R() != a_3742_W.WrappedMinMaxBounds || (charges = AutoTotem.c_3005_b.Y_601_j.getBlockState(pos).R_4764_Y(P_2605_j.P_4830_p).intValue()) <= 0 || !((dist = AutoTotem.c_3005_b.Y_259_p.v_4262_N((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5)) <= (double)(((Float)this.distanciyaDoYakoryaSetting.getValue()).floatValue() * ((Float)this.distanciyaDoYakoryaSetting.getValue()).floatValue()))) continue;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onDisable() {
        this.c_4037_x = -1;
        this.g_2268_R = 0;
        this.T_3594_S = 0;
        this.D_4792_h = -1;
        this.s_2632_s = 0;
        this.z_1333_t = false;
        this.O_508_d = -1;
        this.r_715_M = false;
        this.A_1038_p = false;
        v_4262_N = false;
        super.onDisable();
    }
}



