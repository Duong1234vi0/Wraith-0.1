/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.MutableComponent;
import lightning.product.J_3635_s;
import lightning.product.K_1200_E;
import lightning.product.N_4263_v;
import lightning.product.Z_2491_A;
import lightning.product.ServerHandshakePacketListener;
import lightning.product.b_3528_u;
import lightning.product.g_221_o;
import lightning.product.h_2367_h;
import lightning.product.h_4412_P;
import lightning.product.j_1376_w;
import lightning.product.Animation;
import lightning.product.NameProtect;
import lightning.product.l_3370_o;
import lightning.product.q_3115_L;
import lightning.product.q_3148_R;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lombok.Generated;

public class z_4066_l
implements ServerHandshakePacketListener {
    private final J_3635_s u_1723_Y;
    private final Animation v_4262_N = new Animation(0.0f, 10.0f);
    private final Animation w_1484_f = new Animation(22.0f, 10.0f);
    public static h_2367_h n_1700_B = new h_2367_h("\u0425\u0435\u0434\u0435\u0440", true, 0);
    public static h_2367_h J_1907_R = new h_2367_h("\u0424\u043e\u043d", true, 0);
    public static h_2367_h R_4764_Y = new h_2367_h("\u0422\u0435\u043a\u0441\u0442", true, 0);
    public static h_2367_h G_564_y = new h_2367_h("\u041e\u0431\u0432\u043e\u0434\u043a\u0430", true, 0);
    public static h_2367_h P_1922_E = new h_2367_h("\u0421\u0432\u0435\u0447\u0435\u043d\u0438\u0435", true, 0);
    private boolean t_148_a;
    private long s_956_w = -1L;
    private int u_2550_I;
    private int M_588_G;
    private int P_4830_p;
    private final Map<Integer, Float> h_1847_R = new HashMap<Integer, Float>();
    private boolean Q_4569_t = true;
    private int M_182_A = -1;

    public void n_1700_B() {
        this.s_956_w = System.currentTimeMillis();
        this.u_2550_I = 0;
        this.M_588_G = 0;
        this.P_4830_p = 0;
        this.h_1847_R.clear();
        this.Q_4569_t = true;
        this.M_182_A = -1;
    }

    public void n_1700_B(N_4263_v target) {
        if (target instanceof r_4811_B) {
            r_4811_B living = (r_4811_B)target;
            if (target != z_4066_l.c_3005_b.Y_259_p) {
                this.h_1847_R.put(target.j_276_v(), Float.valueOf(living.g_46_E()));
            }
        }
    }

    @Override
    public void n_1700_B(b_3528_u event) {
        if (z_4066_l.c_3005_b.Y_259_p == null || z_4066_l.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.s_956_w == -1L) {
            this.s_956_w = System.currentTimeMillis();
        }
        this.J_1907_R();
        g_221_o ms = event.J_1907_R();
        float posX = this.u_1723_Y.J_1907_R();
        float posY = this.u_1723_Y.R_4764_Y();
        this.v_4262_N.n_1700_B(z_4066_l.c_3005_b.Y_1740_V instanceof h_4412_P ? 1.0f : 1.0f);
        float globalAlpha = this.v_4262_N.n_1700_B();
        String[][] rows = this.R_4764_Y();
        float width = this.n_1700_B(rows);
        float headerHeight = 15.0f;
        float itemSpacing = 11.0f;
        float targetHeight = Math.max(20.0f, 16.5f + (float)rows.length * itemSpacing);
        this.w_1484_f.n_1700_B(targetHeight);
        float animatedHeight = this.w_1484_f.n_1700_B();
        int glow = (Integer)P_1922_E.J_1907_R();
        float glowAlpha = (float)H_2506_c.G_564_y(glow) / 255.0f;
        F_489_x.n_1700_B(posX - 10.0f, posY - 10.0f, width + 20.0f, animatedHeight + 20.0f, 5.0f, glow, glow, glow, glow, glowAlpha * globalAlpha, 10.0f);
        F_489_x.n_1700_B(posX, posY, width, animatedHeight, 5.0f, (int)((Integer)J_1907_R.J_1907_R()), globalAlpha);
        F_489_x.n_1700_B(posX, posY, width, 15.0f, new Z_2491_A(5.0f, 0.0f, 5.0f, 0.0f), (int)((Integer)n_1700_B.J_1907_R()), globalAlpha);
        int outline = (Integer)G_564_y.J_1907_R();
        float outlineAlphaValue = (float)H_2506_c.G_564_y(outline) * globalAlpha;
        F_489_x.J_1907_R(posX, posY, width, animatedHeight, 5.0f, outline, outlineAlphaValue);
        int headerTextColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.w_1457_N), globalAlpha);
        MutableComponent gradientTitle = j_1376_w.n_1700_B("Session", q_3148_R.n_1700_B(K_1200_E.w_1457_N), H_2506_c.J_1907_R(q_3148_R.n_1700_B(K_1200_E.w_1457_N), 0.3f), 7, 15.0f);
        l_3370_o.J_1907_R[15].n_1700_B(ms, gradientTitle, (double)(posX + 4.5f), (double)(posY + 5.5f), headerTextColor);
        MutableComponent iconText = j_1376_w.n_1700_B("D", q_3148_R.n_1700_B(K_1200_E.w_1457_N), H_2506_c.J_1907_R(q_3148_R.n_1700_B(K_1200_E.w_1457_N), 0.3f), 7, 15.0f);
        l_3370_o.u_1723_Y[16].n_1700_B(ms, iconText, (double)(posX + width - l_3370_o.u_1723_Y[16].n_1700_B("D") - 4.5f), (double)(posY + 6.5f), headerTextColor);
        float baseItemY = posY + 15.0f;
        int textColorValue = H_2506_c.n_1700_B(H_2506_c.n_1700_B(255, 255, 255, 255), globalAlpha);
        for (int i = 0; i < rows.length; ++i) {
            float itemY = baseItemY + (float)i * itemSpacing;
            l_3370_o.J_1907_R[12].n_1700_B(ms, rows[i][0], (double)(posX + 4.5f), (double)(itemY + 4.5f), textColorValue);
            l_3370_o.J_1907_R[12].n_1700_B(ms, rows[i][1], (double)(posX + width - 4.5f - l_3370_o.J_1907_R[12].n_1700_B(rows[i][1])), (double)(itemY + 4.5f), textColorValue);
        }
        this.u_1723_Y.G_564_y(headerHeight + (float)rows.length * itemSpacing);
        this.u_1723_Y.R_4764_Y(width);
    }

    private void J_1907_R() {
        boolean alive;
        boolean bl = alive = z_4066_l.c_3005_b.Y_259_p.RealmsLongRunningMcoTaskScreen() && z_4066_l.c_3005_b.Y_259_p.g_46_E() > 0.0f;
        if (this.Q_4569_t && !alive) {
            ++this.M_588_G;
        }
        this.Q_4569_t = alive;
        Iterator<Map.Entry<Integer, Float>> it = this.h_1847_R.entrySet().iterator();
        while (it.hasNext()) {
            r_4811_B living;
            Map.Entry<Integer, Float> entry = it.next();
            N_4263_v entity = z_4066_l.c_3005_b.Y_601_j.J_1907_R(entry.getKey());
            if (entity == null) {
                ++this.u_2550_I;
                it.remove();
                continue;
            }
            if (!(entity instanceof r_4811_B) || !((living = (r_4811_B)entity).g_46_E() <= 0.0f) && living.O_2151_c <= 0) continue;
            ++this.u_2550_I;
            it.remove();
        }
        int totemCount = 0;
        for (int i = 0; i < z_4066_l.c_3005_b.Y_259_p.l_1268_F.Y_259_p(); ++i) {
            if (z_4066_l.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).J_1907_R() != Items.N_81_X) continue;
            totemCount += z_4066_l.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).t_4043_B();
        }
        if (this.M_182_A != -1 && totemCount < this.M_182_A) {
            this.P_4830_p += this.M_182_A - totemCount;
        }
        this.M_182_A = totemCount;
    }

    private String[][] R_4764_Y() {
        long elapsed = System.currentTimeMillis() - this.s_956_w;
        return new String[][]{{"Server Name", this.P_1922_E()}, {"Play time", this.n_1700_B(elapsed)}, {"Name", this.G_564_y()}};
    }

    private float n_1700_B(String[][] rows) {
        float maxLeft = 0.0f;
        float maxRight = 0.0f;
        for (String[] row : rows) {
            maxLeft = Math.max(maxLeft, l_3370_o.J_1907_R[13].n_1700_B(row[0]));
            maxRight = Math.max(maxRight, l_3370_o.J_1907_R[13].n_1700_B(row[1]));
        }
        return Math.max(75.0f, maxLeft + maxRight + 26.0f);
    }

    private String n_1700_B(long ms) {
        long totalSec = ms / 1000L;
        long h = totalSec / 3600L;
        long m = totalSec % 3600L / 60L;
        long s = totalSec % 60L;
        return h + "h " + m + "m " + s + "s";
    }

    private String G_564_y() {
        try {
            if (c_3005_b == null || z_4066_l.c_3005_b.w_1484_f == null || z_4066_l.c_3005_b.w_1484_f.R_4764_Y() == null) {
                return "Name";
            }
            String username = z_4066_l.c_3005_b.w_1484_f.R_4764_Y();
            NameProtect module = NameProtect.h_1847_R();
            if (module == null || !module.w_1484_f()) {
                return username;
            }
            return NameProtect.G_564_y(username);
        }
        catch (Exception ignored) {
            return "Name";
        }
    }

    private String P_1922_E() {
        try {
            String server = new q_3115_L().t_148_a();
            if (server == null || server.isEmpty()) {
                return "singleplayer";
            }
            if ("singleplayer".equalsIgnoreCase(server)) {
                return "singleplayer";
            }
            int colon = server.indexOf(58);
            return colon > 0 ? server.substring(0, colon) : server;
        }
        catch (Exception ignored) {
            return "singleplayer";
        }
    }

    @Generated
    public z_4066_l(J_3635_s dragging) {
        this.u_1723_Y = dragging;
    }
}



