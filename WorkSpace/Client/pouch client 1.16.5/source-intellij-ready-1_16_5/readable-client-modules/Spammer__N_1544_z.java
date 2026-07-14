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
import lightning.product.I_686_h;
import lightning.product.O_3016_i;
import lightning.product.U_3758_B;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.b_2037_V;
import lightning.product.h_1015_G;
import lightning.product.p_1977_n;
import lightning.product.q_366_O;
import lightning.product.y_2603_k;

public class N_1544_z
extends X_3546_T {
    private final q_366_O v_4262_N = new q_366_O("\u0420\u0435\u0436\u0438\u043c", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "\u041b\u0421", "\u041a\u043b\u0430\u043d", "\u0420\u0435\u043a\u043b\u0430\u043c\u043d\u044b\u0439");
    private final b_2037_V w_1484_f = new b_2037_V("\u0422\u0435\u043a\u0441\u0442 \u0441\u043f\u0430\u043c\u0430");
    private final O_3016_i t_148_a = new O_3016_i("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0441\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0435");
    private final I_686_h s_956_w = new I_686_h("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 (\u0441\u0435\u043a)", 6.0f, 1.0f, 30.0f, 0.5f);
    private final p_1977_n u_2550_I = new p_1977_n("\u0423\u043f\u043e\u043c\u0438\u043d\u0430\u0442\u044c \u0438\u0433\u0440\u043e\u043a\u043e\u0432", false);
    private final p_1977_n M_588_G = new p_1977_n("\u0421\u043b\u0443\u0447\u0430\u0439\u043d\u044b\u0439 \u0441\u0443\u0444\u0444\u0438\u043a\u0441", true);
    private static final Pattern P_4830_p = Pattern.compile("^\\w{3,16}$");
    private long h_1847_R = 0L;
    private int Q_4569_t = 0;

    public N_1544_z() {
        super("Spammer", y_2603_k.P_1922_E);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G);
    }

    @Override
    public void n_1700_B() {
        super.n_1700_B();
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
        if (N_1544_z.c_3005_b.Y_259_p == null || N_1544_z.c_3005_b.Y_601_j == null) {
            return;
        }
        String text = (String)this.t_148_a.J_1907_R();
        if (text.isEmpty()) {
            this.n_1700_B(false);
            return;
        }
        long currentTime = System.currentTimeMillis();
        if (currentTime - this.h_1847_R < (delayMs = (long)(((Float)this.s_956_w.J_1907_R()).floatValue() * 1000.0f))) {
            return;
        }
        String message = this.R_4764_Y(text);
        if (message != null && !message.isEmpty()) {
            N_1544_z.c_3005_b.Y_259_p.n_1700_B(message);
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
        if (this.u_2550_I.t_148_a().booleanValue() && !(players = this.Q_4569_t()).isEmpty()) {
            if (this.Q_4569_t >= players.size()) {
                this.Q_4569_t = 0;
            }
            String player = players.get(this.Q_4569_t);
            message.append(player).append(" ");
            ++this.Q_4569_t;
        }
        message.append(text);
        if (this.M_588_G.t_148_a().booleanValue()) {
            message.append(" ").append(this.M_182_A());
        }
        return message.toString();
    }

    private String h_1847_R() {
        return switch ((String)this.v_4262_N.J_1907_R()) {
            case "\u041e\u0431\u044b\u0447\u043d\u044b\u0439" -> "! ";
            case "\u041b\u0421" -> "/msg ";
            case "\u041a\u043b\u0430\u043d" -> "/cc ";
            case "\u0420\u0435\u043a\u043b\u0430\u043c\u043d\u044b\u0439" -> "";
            default -> "";
        };
    }

    private List<String> Q_4569_t() {
        if (N_1544_z.c_3005_b.Y_259_p == null || N_1544_z.c_3005_b.Y_259_p.n_1700_B == null) {
            return new ArrayList<String>();
        }
        return N_1544_z.c_3005_b.Y_259_p.n_1700_B.P_1922_E().stream().map(A_2226_Q::n_1700_B).map(GameProfile::getName).filter(name -> P_4830_p.matcher((CharSequence)name).matches()).filter(name -> !name.equals(N_1544_z.c_3005_b.Y_259_p.y_4642_Y().getName())).collect(Collectors.toList());
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

