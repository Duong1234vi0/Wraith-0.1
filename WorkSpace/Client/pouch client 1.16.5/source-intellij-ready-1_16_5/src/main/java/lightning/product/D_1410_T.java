/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.J_3635_s;
import lightning.product.K_1200_E;
import lightning.product.U_3758_B;
import lightning.product.V_4557_X;
import lightning.product.Z_2491_A;
import lightning.product.ServerHandshakePacketListener;
import lightning.product.b_3528_u;
import lightning.product.g_221_o;
import lightning.product.h_2367_h;
import lightning.product.h_4412_P;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.BooleanSetting;
import lightning.product.q_3148_R;
import lombok.Generated;

public class D_1410_T
implements ServerHandshakePacketListener {
    private static final Pattern u_2550_I = Pattern.compile("^(?:\\[.*?\\]\\s*)?([a-zA-Z0-9_]{3,16})");
    public static BooleanSetting n_1700_B = new BooleanSetting("\u041b\u043e\u043c\u0430\u043d\u0438\u0435 \u0449\u0438\u0442\u0430", false);
    public static BooleanSetting J_1907_R = new BooleanSetting("\u041f\u0440\u043e\u0441\u044c\u0431\u0430 \u043e \u043d\u0430\u0431\u043b\u044e\u0434\u0435\u043d\u0438\u0438", false);
    public static BooleanSetting R_4764_Y = new BooleanSetting("\u041f\u0438\u0430\u0440 \u0432\u0430\u0440\u043f\u043e\u0432", false);
    public static BooleanSetting G_564_y = new BooleanSetting("\u0421\u043e\u0441\u0442\u043e\u044f\u043d\u0438\u0435 \u043c\u043e\u0434\u0443\u043b\u0435\u0439/\u043d\u0430\u0441\u0442\u0440\u043e\u0435\u043a", false);
    public static BooleanSetting P_1922_E = new BooleanSetting("\u041d\u0438\u0437\u043a\u0430\u044f \u043f\u0440\u043e\u0447\u043d\u043e\u0441\u0442\u044c \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u043e\u0432", false);
    public static BooleanSetting u_1723_Y = new BooleanSetting("\u0417\u0430\u043a\u043e\u043d\u0447\u0438\u043b\u0438\u0441\u044c \u0432\u0430\u0436\u043d\u044b\u0435 \u0437\u0435\u043b\u044c\u044f", false);
    public static h_2367_h v_4262_N = new h_2367_h("\u0426\u0432\u0435\u0442 \u0444\u043e\u043d\u0430", true, H_2506_c.n_1700_B(30, 25, 40, 255));
    public static h_2367_h w_1484_f = new h_2367_h("\u0426\u0432\u0435\u0442 \u0442\u0435\u043a\u0441\u0442\u0430", true, H_2506_c.n_1700_B(180, 140, 255, 255));
    public static h_2367_h t_148_a = new h_2367_h("\u0426\u0432\u0435\u0442 \u043e\u0431\u0432\u043e\u0434\u043a\u0438", true, H_2506_c.n_1700_B(120, 80, 160, 255));
    public static h_2367_h s_956_w = new h_2367_h("\u0426\u0432\u0435\u0442 \u0441\u0432\u0435\u0447\u0435\u043d\u0438\u044f", true, H_2506_c.n_1700_B(120, 80, 160, 100));
    private final J_3635_s M_588_G;
    private final Animation P_4830_p = new Animation(0.0f, 10.0f);
    private static final String[] h_1847_R = new String[]{"G", "S", "J", "K", "I", "H", "M", "L"};
    private final V_4557_X Q_4569_t = new V_4557_X();
    private int M_182_A = 0;
    private boolean t_1786_h = false;

    @Override
    public void n_1700_B(b_3528_u event) {
        float targetAlpha;
        g_221_o ms = event.J_1907_R();
        boolean isChatOpen = D_1410_T.c_3005_b.Y_1740_V instanceof h_4412_P;
        boolean shouldShow = isChatOpen && !U_3758_B.n_1700_B();
        float f = targetAlpha = shouldShow ? 1.0f : 0.0f;
        if (isChatOpen && !this.t_1786_h) {
            this.M_182_A = 0;
            this.Q_4569_t.n_1700_B();
        }
        this.t_1786_h = isChatOpen;
        this.P_4830_p.n_1700_B(targetAlpha);
        float globalAlpha = this.P_4830_p.n_1700_B();
        if (isChatOpen && this.Q_4569_t.n_1700_B(500L, true)) {
            this.M_182_A = (this.M_182_A + 1) % h_1847_R.length;
        }
        String icon = h_1847_R[this.M_182_A];
        String preview = "\u042d\u0442\u043e \u0443\u0432\u0435\u0434\u043e\u043c\u043b\u0435\u043d\u0438\u0435, \u043a\u043b\u0438\u043a\u043d\u0438 \u043d\u0430 \u043c\u0435\u043d\u044f \u0434\u043b\u044f \u043d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438";
        int textColorValue = H_2506_c.n_1700_B((int)((Integer)w_1484_f.J_1907_R()), globalAlpha);
        int separatorColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.Q_4569_t), q_3148_R.J_1907_R(K_1200_E.Q_4569_t) / 255.0f * globalAlpha);
        float width = l_3370_o.u_1723_Y[16].n_1700_B(icon) + l_3370_o.G_564_y[12].n_1700_B(preview) + 5.0f;
        float totalWidth = width + 11.5f;
        float posX = ((float)c_3005_b.RealmsServerPing().Q_4569_t() - totalWidth) / 2.0f;
        float posY = (float)c_3005_b.RealmsServerPing().M_182_A() / 2.0f + 13.0f;
        int bg = (Integer)v_4262_N.J_1907_R();
        F_489_x.n_1700_B(posX + l_3370_o.u_1723_Y[16].n_1700_B(icon), posY - 10.0f, totalWidth - (l_3370_o.u_1723_Y[16].n_1700_B(icon) + 9.0f) + 20.0f, 33.0f, 3.0f, (int)((Integer)s_956_w.J_1907_R()), (int)((Integer)s_956_w.J_1907_R()), (int)((Integer)s_956_w.J_1907_R()), (int)((Integer)s_956_w.J_1907_R()), (float)H_2506_c.G_564_y((Integer)s_956_w.J_1907_R()) / 255.0f * globalAlpha, 10.0f);
        F_489_x.n_1700_B(posX - 10.0f, posY - 10.0f, l_3370_o.u_1723_Y[16].n_1700_B(icon) + 9.0f + 20.0f, 33.0f, 3.0f, (int)((Integer)s_956_w.J_1907_R()), (int)((Integer)s_956_w.J_1907_R()), (int)((Integer)s_956_w.J_1907_R()), (int)((Integer)s_956_w.J_1907_R()), (float)H_2506_c.G_564_y((Integer)s_956_w.J_1907_R()) / 255.0f * globalAlpha, 10.0f);
        F_489_x.n_1700_B(posX, posY, l_3370_o.u_1723_Y[16].n_1700_B(icon) + 9.0f, 13.0f, new Z_2491_A(3.0f, 3.0f, 1.0f, 1.0f), bg, globalAlpha);
        F_489_x.n_1700_B(posX + l_3370_o.u_1723_Y[16].n_1700_B(icon) + 10.0f, posY, totalWidth - (l_3370_o.u_1723_Y[16].n_1700_B(icon) + 9.0f), 13.0f, new Z_2491_A(1.0f, 1.0f, 3.0f, 3.0f), bg, globalAlpha);
        int iconColor = H_2506_c.n_1700_B((int)((Integer)w_1484_f.J_1907_R()), globalAlpha);
        l_3370_o.u_1723_Y[16].n_1700_B(ms, icon, (double)(posX + 4.5f), (double)(posY + l_3370_o.u_1723_Y[16].h_1847_R() - 0.5f), iconColor);
        l_3370_o.G_564_y[12].n_1700_B(ms, preview, (double)(posX + l_3370_o.u_1723_Y[16].n_1700_B(icon) + 13.0f), (double)(posY + l_3370_o.G_564_y[12].h_1847_R() + 1.5f), textColorValue);
        if (this.P_4830_p.J_1907_R() >= 0.999f) {
            this.M_588_G.G_564_y(13.0f);
            this.M_588_G.R_4764_Y(totalWidth);
        } else {
            this.M_588_G.G_564_y(0.0f);
            this.M_588_G.R_4764_Y(0.0f);
        }
    }

    public static void n_1700_B(String message, String playerName) {
        String extractedName;
        if (!J_1907_R.t_148_a().booleanValue()) {
            return;
        }
        if (message == null) {
            return;
        }
        String lowerMessage = message.toLowerCase();
        if (D_1410_T.n_1700_B(lowerMessage) && (extractedName = D_1410_T.J_1907_R(message)) != null && !extractedName.equalsIgnoreCase(playerName)) {
            U_3758_B.n_1700_B("O", extractedName + " \u0445\u043e\u0447\u0435\u0442 \u0447\u0442\u043e\u0431\u044b \u0437\u0430 \u043d\u0438\u043c \u0441\u043b\u0435\u0434\u0438\u043b\u0438!", H_2506_c.n_1700_B(255, 200, 0));
        }
    }

    private static boolean n_1700_B(String message) {
        return message.contains("\u0441\u043f\u0435\u043a") || message.contains("\u044b\u0437\u0443\u0441") || message.contains("spec") || message.contains("spek") || message.contains("\u044b\u0437\u0443\u043b");
    }

    private static String J_1907_R(String message) {
        String name;
        Matcher matcher = u_2550_I.matcher(message);
        if (matcher.find()) {
            return matcher.group(1);
        }
        String[] parts = message.split("[:\\s]");
        if (parts.length > 0 && (name = parts[0].replaceAll("\\[.*?\\]", "").trim()).matches("[a-zA-Z0-9_]{3,16}")) {
            return name;
        }
        return null;
    }

    @Generated
    public D_1410_T(J_3635_s dragging) {
        this.M_588_G = dragging;
    }
}


