/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 */
package lightning.product;

import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lightning.product.A_2226_Q;
import lightning.product.NumberSetting;
import lightning.product.O_3016_i;
import lightning.product.U_3758_B;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.b_2037_V;
import lightning.product.h_1015_G;
import lightning.product.BooleanSetting;
import lightning.product.ModeSetting;
import lightning.product.ModuleCategory;

public class Spammer
extends Module {
    private final ModeSetting rezhimMode = new ModeSetting("\u0420\u0435\u0436\u0438\u043c", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "\u041b\u0421", "\u041a\u043b\u0430\u043d", "\u0420\u0435\u043a\u043b\u0430\u043c\u043d\u044b\u0439");
    private final b_2037_V w_1484_f = new b_2037_V("\u0422\u0435\u043a\u0441\u0442 \u0441\u043f\u0430\u043c\u0430");
    private final O_3016_i t_148_a = new O_3016_i("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0441\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0435");
    private final NumberSetting zaderzhkaSekSetting = new NumberSetting("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 (\u0441\u0435\u043a)", 6.0f, 1.0f, 30.0f, 0.5f);
    private final BooleanSetting upominatIgrokovEnabled = new BooleanSetting("\u0423\u043f\u043e\u043c\u0438\u043d\u0430\u0442\u044c \u0438\u0433\u0440\u043e\u043a\u043e\u0432", false);
    private final BooleanSetting sluchaynyySuffiksEnabled = new BooleanSetting("\u0421\u043b\u0443\u0447\u0430\u0439\u043d\u044b\u0439 \u0441\u0443\u0444\u0444\u0438\u043a\u0441", true);
    private static final Pattern P_4830_p = Pattern.compile("^\\w{3,16}$");
    private long h_1847_R = 0L;
    private int Q_4569_t = 0;

    public Spammer() {
        super("Spammer", ModuleCategory.P_1922_E);
        this.addSettings(this.rezhimMode, this.w_1484_f, this.t_148_a, this.zaderzhkaSekSetting, this.upominatIgrokovEnabled, this.sluchaynyySuffiksEnabled);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if (((String)this.t_148_a.J_1907_R()).isEmpty()) {
            U_3758_B.n_1700_B("L", "\u0423\u043a\u0430\u0436\u0438 \u0442\u0435\u043a\u0441\u0442 \u0441\u043f\u0430\u043c\u0430 \u0432 \u043d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0430\u0445!", 3000);
            this.n_1700_B(false);
            return;
        }
        this.h_1847_R = 0L;
        this.Q_4569_t = 0;
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        long delayMs;
        if (Spammer.c_3005_b.Y_259_p == null || Spammer.c_3005_b.Y_601_j == null) {
            return;
        }
        String text = (String)this.t_148_a.J_1907_R();
        if (text.isEmpty()) {
            this.n_1700_B(false);
            return;
        }
        long currentTime = System.currentTimeMillis();
        if (currentTime - this.h_1847_R < (delayMs = (long)(((Float)this.zaderzhkaSekSetting.getValue()).floatValue() * 1000.0f))) {
            return;
        }
        String message = this.R_4764_Y(text);
        if (message != null && !message.isEmpty()) {
            Spammer.c_3005_b.Y_259_p.n_1700_B(message);
            this.h_1847_R = currentTime;
        }
    }

    private String R_4764_Y(String text) {
        List<String> players;
        StringBuilder message = new StringBuilder();
        String prefix = this.h_1847_R();
        if (!prefix.isEmpty()) {
            message.append(prefix);
        }
        if (this.upominatIgrokovEnabled.isEnabled().booleanValue() && !(players = this.Q_4569_t()).isEmpty()) {
            if (this.Q_4569_t >= players.size()) {
                this.Q_4569_t = 0;
            }
            String player = players.get(this.Q_4569_t);
            message.append(player).append(" ");
            ++this.Q_4569_t;
        }
        message.append(text);
        if (this.sluchaynyySuffiksEnabled.isEnabled().booleanValue()) {
            message.append(" ").append(this.M_182_A());
        }
        return message.toString();
    }

    private String h_1847_R() {
        return switch ((String)this.rezhimMode.getValue()) {
            case "\u041e\u0431\u044b\u0447\u043d\u044b\u0439" -> "! ";
            case "\u041b\u0421" -> "/msg ";
            case "\u041a\u043b\u0430\u043d" -> "/cc ";
            case "\u0420\u0435\u043a\u043b\u0430\u043c\u043d\u044b\u0439" -> "";
            default -> "";
        };
    }

    private List<String> Q_4569_t() {
        if (Spammer.c_3005_b.Y_259_p == null || Spammer.c_3005_b.Y_259_p.n_1700_B == null) {
            return new ArrayList<String>();
        }
        return Spammer.c_3005_b.Y_259_p.n_1700_B.P_1922_E().stream().map(A_2226_Q::n_1700_B).map(GameProfile::getName).filter(name -> P_4830_p.matcher((CharSequence)name).matches()).filter(name -> !name.equals(Spammer.c_3005_b.Y_259_p.y_4642_Y().getName())).collect(Collectors.toList());
    }

    private String M_182_A() {
        StringBuilder suffix = new StringBuilder("[");
        String chars = "\u0430\u0431\u0432\u0433\u0434\u0435\u0436\u0437\u0438\u043a\u043b\u043c\u043d\u043e\u043f\u0440\u0441\u0442\u0443\u0444\u0445\u0446\u0447\u0448\u0449\u044d\u044e\u044f0123456789";
        for (int i = 0; i < 3; ++i) {
            suffix.append(chars.charAt((int)(Math.random() * (double)chars.length())));
        }
        suffix.append("]");
        return suffix.toString();
    }
}


