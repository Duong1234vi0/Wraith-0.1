/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.time.LocalTime;
import lightning.product.NumberSetting;
import lightning.product.O_922_L;
import lightning.product.Q_2753_H;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.h_1015_G;
import lightning.product.h_2367_h;
import lightning.product.k_596_g;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.ClientboundSetTimePacket;
import lightning.product.ModeSetting;
import lightning.product.s_4405_m;
import lightning.product.u_796_y;
import lightning.product.ModuleCategory;

public class Ambience
extends Module {
    public static ModeSetting vremyaMode = new ModeSetting("\u0412\u0440\u0435\u043c\u044f", "\u041d\u0435 \u043c\u0435\u043d\u044f\u0442\u044c", "\u0420\u0430\u0441\u0441\u0432\u0435\u0442", "\u0423\u0442\u0440\u043e", "\u0414\u0435\u043d\u044c", "\u0412\u0435\u0447\u0435\u0440", "\u0417\u0430\u0445\u043e\u0434 \u0441\u043e\u043b\u043d\u0446\u0430", "\u041d\u043e\u0447\u044c", "\u0412\u0440\u0435\u043c\u044f \u0438\u0437 \u0440\u0435\u0430\u043b\u044c\u043d\u043e\u0439 \u0436\u0438\u0437\u043d\u0438", "\u041d\u0435 \u043c\u0435\u043d\u044f\u0442\u044c");
    public static ModeSetting tumanMode = new ModeSetting("\u0422\u0443\u043c\u0430\u043d", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "Adaptive", "\u041e\u0447\u0438\u0441\u0442\u0438\u0442\u044c", "\u041f\u0435\u0440\u0435\u043e\u043f\u0440\u0435\u0434\u0435\u043b\u0438\u0442\u044c");
    public static ModeSetting cvetMode = new ModeSetting("\u0426\u0432\u0435\u0442", "\u0421\u0432\u043e\u0439", "\u0418\u043d\u0442\u0435\u0440\u0444\u0435\u0439\u0441", "\u0421\u0432\u043e\u0439");
    public static h_2367_h s_956_w = new h_2367_h("\u0426\u0432\u0435\u0442 \u0442\u0443\u043c\u0430\u043d\u0430", false, -1, () -> tumanMode.isMode("\u041f\u0435\u0440\u0435\u043e\u043f\u0440\u0435\u0434\u0435\u043b\u0438\u0442\u044c") && cvetMode.isMode("\u0421\u0432\u043e\u0439"));
    public static NumberSetting konecTumanaSetting = new NumberSetting("\u041a\u043e\u043d\u0435\u0446 \u0442\u0443\u043c\u0430\u043d\u0430", 1.0f, 0.1f, 1.5f, 0.1f, () -> tumanMode.isMode("\u041f\u0435\u0440\u0435\u043e\u043f\u0440\u0435\u0434\u0435\u043b\u0438\u0442\u044c"));
    public static NumberSetting nachaloTumanaSetting = new NumberSetting("\u041d\u0430\u0447\u0430\u043b\u043e \u0442\u0443\u043c\u0430\u043d\u0430", 0.5f, 0.1f, 1.5f, 0.1f, () -> tumanMode.isMode("\u041f\u0435\u0440\u0435\u043e\u043f\u0440\u0435\u0434\u0435\u043b\u0438\u0442\u044c"), konecTumanaSetting);
    public static NumberSetting adaptiveRadiusTumanaSetting = new NumberSetting("Adaptive: \u0420\u0430\u0434\u0438\u0443\u0441 \u0442\u0443\u043c\u0430\u043d\u0430", 42.0f, 8.0f, 140.0f, 1.0f, () -> tumanMode.isMode("Adaptive"));
    public static NumberSetting adaptiveSilaTumanaSetting = new NumberSetting("Adaptive: \u0421\u0438\u043b\u0430 \u0442\u0443\u043c\u0430\u043d\u0430", 0.85f, 0.1f, 1.5f, 0.05f, () -> tumanMode.isMode("Adaptive"));
    public static BooleanSetting adaptiveTolkoVDozhdEnabled = new BooleanSetting("Adaptive: \u0422\u043e\u043b\u044c\u043a\u043e \u0432 \u0434\u043e\u0436\u0434\u044c", false, () -> tumanMode.isMode("Adaptive"));
    public static NumberSetting adaptiveMinKolichestvoVDozhdSetting = new NumberSetting("Adaptive: \u041c\u0438\u043d \u043a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u0432 \u0434\u043e\u0436\u0434\u044c", 0.1f, 0.0f, 1.0f, 0.05f, () -> tumanMode.isMode("Adaptive") && adaptiveTolkoVDozhdEnabled.isEnabled() != false);
    public static ModeSetting neboMode = new ModeSetting("\u041d\u0435\u0431\u043e", "\u041e\u0431\u044b\u0447\u043d\u043e\u0435", "\u041e\u0431\u044b\u0447\u043d\u043e\u0435", "\u041a\u043e\u0441\u043c\u043e\u0441", "\u041f\u043b\u0430\u0437\u043c\u0430", "Balatro", "\u041b\u0435\u0442\u043e", "\u0421\u0430\u043a\u0443\u0440\u0430", "Aurora", "\u042d\u0444\u0438\u0440");
    public static ModeSetting letoVidMode = new ModeSetting("\u041b\u0435\u0442\u043e: \u0432\u0438\u0434", "\u041e\u0431\u044b\u0447\u043d\u043e\u0435", Ambience::Q_4569_t, "\u041e\u0431\u044b\u0447\u043d\u043e\u0435", "\u041d\u043e\u0447\u043d\u043e\u0435");
    public static NumberSetting sheydNeboMasshtabSetting = new NumberSetting("\u0428\u0435\u0439\u0434.\u043d\u0435\u0431\u043e: \u043c\u0430\u0441\u0448\u0442\u0430\u0431", 1.0f, 0.2f, 3.0f, 0.05f, Ambience::t_1786_h);
    public static NumberSetting sheydNeboSkorostSetting = new NumberSetting("\u0428\u0435\u0439\u0434.\u043d\u0435\u0431\u043e: \u0441\u043a\u043e\u0440\u043e\u0441\u0442\u044c", 1.0f, 0.0f, 3.0f, 0.05f, Ambience::t_1786_h);
    private final BooleanSetting izmenyatNasyschennostIgryEnabled = new BooleanSetting("\u0418\u0437\u043c\u0435\u043d\u044f\u0442\u044c \u043d\u0430\u0441\u044b\u0449\u0435\u043d\u043d\u043e\u0441\u0442\u044c \u0438\u0433\u0440\u044b", true);
    private final NumberSetting nasyschennostSetting = new NumberSetting("\u041d\u0430\u0441\u044b\u0449\u0435\u043d\u043d\u043e\u0441\u0442\u044c", 1.0f, 0.0f, 2.0f, 0.1f, this.izmenyatNasyschennostIgryEnabled::isEnabled);

    public Ambience() {
        super("Ambience", ModuleCategory.R_4764_Y);
        this.addSettings(vremyaMode, neboMode, letoVidMode, sheydNeboMasshtabSetting, sheydNeboSkorostSetting, this.izmenyatNasyschennostIgryEnabled, this.nasyschennostSetting, tumanMode, cvetMode, s_956_w, nachaloTumanaSetting, konecTumanaSetting, adaptiveRadiusTumanaSetting, adaptiveSilaTumanaSetting, adaptiveTolkoVDozhdEnabled, adaptiveMinKolichestvoVDozhdSetting);
    }

    public static boolean h_1847_R() {
        if (Ambience.c_3005_b.Y_601_j == null || ClientBootstrap.Y_601_j() == null) {
            return false;
        }
        Ambience m = ClientBootstrap.Y_601_j().J_1907_R().v_4262_N;
        return m != null && m.w_1484_f() && neboMode.isMode("\u041a\u043e\u0441\u043c\u043e\u0441");
    }

    public static boolean Q_4569_t() {
        return neboMode.isMode("\u041b\u0435\u0442\u043e");
    }

    public static boolean M_182_A() {
        return Ambience.Q_4569_t() && letoVidMode.isMode("\u041d\u043e\u0447\u043d\u043e\u0435");
    }

    public static boolean t_1786_h() {
        if (Ambience.c_3005_b.Y_601_j == null || ClientBootstrap.Y_601_j() == null) {
            return false;
        }
        Ambience m = ClientBootstrap.Y_601_j().J_1907_R().v_4262_N;
        return m != null && m.w_1484_f() && (neboMode.isMode("\u041f\u043b\u0430\u0437\u043c\u0430") || neboMode.isMode("Balatro") || neboMode.isMode("\u041b\u0435\u0442\u043e") || neboMode.isMode("\u0421\u0430\u043a\u0443\u0440\u0430") || neboMode.isMode("Aurora") || neboMode.isMode("\u042d\u0444\u0438\u0440") || neboMode.isMode("\u041c\u0435\u0442\u0435\u043b\u044c"));
    }

    public static boolean multiplayerClientSuggestionProvider() {
        if (Ambience.c_3005_b.Y_601_j == null || ClientBootstrap.Y_601_j() == null) {
            return false;
        }
        Ambience m = ClientBootstrap.Y_601_j().J_1907_R().v_4262_N;
        return m != null && m.w_1484_f() && !neboMode.isMode("\u041e\u0431\u044b\u0447\u043d\u043e\u0435");
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        if (e.G_564_y() instanceof ClientboundSetTimePacket && !vremyaMode.isMode("\u041d\u0435 \u043c\u0435\u043d\u044f\u0442\u044c")) {
            e.n_1700_B(true);
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (!((String)vremyaMode.getValue()).equals("\u041d\u0435 \u043c\u0435\u043d\u044f\u0442\u044c")) {
            long time;
            if (((String)vremyaMode.getValue()).equals("\u0412\u0440\u0435\u043c\u044f \u0438\u0437 \u0440\u0435\u0430\u043b\u044c\u043d\u043e\u0439 \u0436\u0438\u0437\u043d\u0438")) {
                time = this.Y_601_j();
            } else {
                time = switch ((String)vremyaMode.getValue()) {
                    case "\u0420\u0430\u0441\u0441\u0432\u0435\u0442" -> 23000L;
                    case "\u0423\u0442\u0440\u043e" -> 1000L;
                    case "\u0414\u0435\u043d\u044c" -> 6000L;
                    case "\u0412\u0435\u0447\u0435\u0440" -> 12000L;
                    case "\u0417\u0430\u0445\u043e\u0434 \u0441\u043e\u043b\u043d\u0446\u0430" -> 13000L;
                    case "\u041d\u043e\u0447\u044c" -> 18000L;
                    default -> Ambience.c_3005_b.sheydNeboSkorostSetting.Z_976_R();
                };
            }
            Ambience.c_3005_b.sheydNeboSkorostSetting.J_1907_R(time);
        }
    }

    private boolean w_1457_N() {
        if (Ambience.c_3005_b.Y_1740_V instanceof k_596_g || Ambience.c_3005_b.Y_1740_V instanceof O_922_L) {
            return false;
        }
        return Ambience.c_3005_b.Y_601_j != null;
    }

    @Y_1740_V
    public void n_1700_B(u_796_y e) {
        if (!this.izmenyatNasyschennostIgryEnabled.isEnabled().booleanValue()) {
            return;
        }
        if (!this.w_1457_N()) {
            return;
        }
        if (e.n_1700_B == u_796_y.n_1700_B.n_1700_B) {
            s_4405_m.letoVidMode.getValue();
            s_4405_m.letoVidMode.setOptions("texture", new int[]{0});
            s_4405_m.letoVidMode.setOptions("saturation", ((Float)this.nasyschennostSetting.getValue()).floatValue());
        }
        if (e.n_1700_B == u_796_y.n_1700_B.J_1907_R) {
            s_4405_m.letoVidMode.R_4764_Y();
        }
    }

    private long Y_601_j() {
        int seconds;
        int minutes;
        LocalTime now = LocalTime.now();
        int hours = now.getHour();
        int totalSeconds = hours * 3600 + (minutes = now.getMinute()) * 60 + (seconds = now.getSecond());
        int offsetSeconds = (totalSeconds - 21600) % 86400;
        if (offsetSeconds < 0) {
            offsetSeconds += 86400;
        }
        return (long)((double)offsetSeconds / 86400.0 * 24000.0);
    }
}



