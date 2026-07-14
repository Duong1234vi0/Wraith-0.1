/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_489_x;
import lightning.product.G_624_v;
import lightning.product.H_2506_c;
import lightning.product.J_3635_s;
import lightning.product.K_1200_E;
import lightning.product.ServerHandshakePacketListener;
import lightning.product.b_3528_u;
import lightning.product.MinecraftClient;
import lightning.product.f_2403_E;
import lightning.product.g_221_o;
import lightning.product.h_2367_h;
import lightning.product.l_3370_o;
import lightning.product.l_3729_r;
import lightning.product.BooleanSetting;
import lightning.product.q_3148_R;
import lightning.product.ModeSetting;

public class n_2412_y
implements ServerHandshakePacketListener {
    public static BooleanSetting n_1700_B = new BooleanSetting("\u041b\u043e\u0433\u0438\u043d", true);
    public static BooleanSetting J_1907_R = new BooleanSetting("\u0424\u043f\u0441", true);
    public static BooleanSetting R_4764_Y = new BooleanSetting("\u041a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u044b", true);
    public static BooleanSetting G_564_y = new BooleanSetting("\u0410\u0434\u0441\u043a\u0438\u0435 \u043a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u044b", true);
    public static BooleanSetting P_1922_E = new BooleanSetting("\u041f\u0438\u043d\u0433", true);
    public static BooleanSetting u_1723_Y = new BooleanSetting("\u0422\u041f\u0421", true);
    public static BooleanSetting v_4262_N = new BooleanSetting("\u0411\u041f\u0421", true);
    public static h_2367_h w_1484_f = new h_2367_h("\u0426\u0432\u0435\u0442 \u0444\u043e\u043d\u0430", true, H_2506_c.n_1700_B(30, 25, 40, 255));
    public static h_2367_h t_148_a = new h_2367_h("\u0426\u0432\u0435\u0442 \u0442\u0435\u043a\u0441\u0442\u0430", true, H_2506_c.n_1700_B(180, 140, 255, 255));
    public static h_2367_h s_956_w = new h_2367_h("\u0426\u0432\u0435\u0442 \u043e\u0431\u0432\u043e\u0434\u043a\u0438", true, H_2506_c.n_1700_B(30, 25, 40, 255));
    public static h_2367_h u_2550_I = new h_2367_h("\u0426\u0432\u0435\u0442 \u0441\u0432\u0435\u0447\u0435\u043d\u0438\u044f", true, H_2506_c.n_1700_B(120, 80, 160, 100));
    public static h_2367_h M_588_G = new h_2367_h("\u0418\u043a\u043e\u043d\u043a\u0438", true, H_2506_c.n_1700_B(120, 80, 160, 100));
    public static ModeSetting P_4830_p = new ModeSetting("\u041f\u043e\u0437\u0438\u0446\u0438\u044f", "\u0426\u0435\u043d\u0442\u0440", "\u041b\u0435\u0432\u043e", "\u0426\u0435\u043d\u0442\u0440", "\u041f\u0440\u0430\u0432\u043e");
    private final J_3635_s h_1847_R;
    private float Q_4569_t = 0.0f;
    private static final float M_182_A = 5.0f;
    private static final float t_1786_h = 5.0f;

    public n_2412_y(J_3635_s dragging) {
        this.h_1847_R = dragging;
    }

    private float n_1700_B() {
        float offset = 0.0f;
        if (P_4830_p.J_1907_R("\u0426\u0435\u043d\u0442\u0440")) {
            try {
                if (n_2412_y.c_3005_b.M_588_G != null && n_2412_y.c_3005_b.M_588_G.t_148_a() != null && n_2412_y.c_3005_b.M_588_G.t_148_a().n_1700_B()) {
                    offset += (float)n_2412_y.c_3005_b.M_588_G.t_148_a().J_1907_R() * 19.0f;
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return offset;
    }

    @Override
    public void n_1700_B(b_3528_u event) {
        float combinedX;
        float buildX;
        boolean mirrorAll;
        float posY = 5.0f + this.n_1700_B();
        g_221_o ms = event.J_1907_R();
        int currentFps = MinecraftClient.x_607_J;
        if (this.Q_4569_t == 0.0f) {
            this.Q_4569_t = currentFps;
        }
        this.Q_4569_t += ((float)currentFps - this.Q_4569_t) * 0.1f;
        String nameText = G_624_v.t_148_a.n_1700_B;
        String fpsText = Math.round(this.Q_4569_t) + " Fps";
        String pingText = l_3729_r.P_1922_E() + " Ping";
        String tpsText = f_2403_E.J_1907_R() + " Ticks";
        float buildTextWidth = l_3370_o.G_564_y[14].n_1700_B("Beta");
        float nameTextWidth = l_3370_o.G_564_y[13].n_1700_B(nameText);
        float fpsTextWidth = l_3370_o.G_564_y[13].n_1700_B(fpsText);
        float pingTextWidth = l_3370_o.G_564_y[13].n_1700_B(pingText);
        float tpsTextWidth = l_3370_o.G_564_y[13].n_1700_B(tpsText);
        float iconPWidth = l_3370_o.w_1484_f[20].n_1700_B("P");
        float iconWWidth = l_3370_o.u_1723_Y[14].n_1700_B("W");
        float iconXWidth = l_3370_o.u_1723_Y[14].n_1700_B("X");
        float iconQWidth = l_3370_o.u_1723_Y[14].n_1700_B("Q");
        float iconGWidth = l_3370_o.u_1723_Y[14].n_1700_B("G");
        int textColorValue = (Integer)t_148_a.J_1907_R();
        int iconColorValue = (Integer)M_588_G.J_1907_R();
        float buildRectWidth = iconPWidth + buildTextWidth + 17.5f;
        float nameRectWidth = 5.0f + iconWWidth + 3.0f + nameTextWidth;
        float fpsRectWidth = 5.0f + iconXWidth + 3.0f + fpsTextWidth;
        float pingRectWidth = 5.0f + iconQWidth + 3.0f + pingTextWidth;
        float tpsRectWidth = 5.0f + iconGWidth + 3.0f + tpsTextWidth;
        boolean nameEnabled = n_1700_B.t_148_a();
        boolean fpsEnabled = J_1907_R.t_148_a();
        boolean pingEnabled = P_1922_E.t_148_a();
        boolean tpsEnabled = u_1723_Y.t_148_a();
        float combinedRectWidth = 0.0f;
        int blockCount = 0;
        if (nameEnabled) {
            combinedRectWidth += nameRectWidth;
            ++blockCount;
        }
        if (fpsEnabled) {
            combinedRectWidth += fpsRectWidth;
            ++blockCount;
        }
        if (pingEnabled) {
            combinedRectWidth += pingRectWidth;
            ++blockCount;
        }
        if (tpsEnabled) {
            combinedRectWidth += tpsRectWidth;
            ++blockCount;
        }
        if (blockCount > 1) {
            combinedRectWidth += (float)blockCount + 5.0f;
        } else if (blockCount == 1) {
            combinedRectWidth += 3.5f;
        }
        float combinedWidthRendered = blockCount > 0 ? combinedRectWidth : 0.0f;
        float totalWidth = buildRectWidth + (blockCount > 0 ? 2.0f + combinedWidthRendered : 0.0f);
        MinecraftClient mcInstance = MinecraftClient.A_4115_X();
        float screenW = mcInstance.RealmsServerPing().Q_4569_t();
        boolean isCenter = P_4830_p.J_1907_R("\u0426\u0435\u043d\u0442\u0440");
        boolean isRight = P_4830_p.J_1907_R("\u041f\u0440\u0430\u0432\u043e");
        float posBaseX = isRight ? (float)Math.floor(screenW - totalWidth - 5.0f) : (P_4830_p.J_1907_R("\u041b\u0435\u0432\u043e") ? (float)Math.floor(5.0) : (float)Math.floor((screenW - totalWidth) / 2.0f));
        boolean bl = mirrorAll = isRight && !isCenter;
        if (!mirrorAll) {
            buildX = (float)Math.floor(posBaseX);
            combinedX = (float)Math.floor(posBaseX + buildRectWidth + 2.0f);
        } else {
            combinedX = (float)Math.floor(posBaseX);
            buildX = (float)Math.floor(posBaseX + combinedWidthRendered + 2.0f);
        }
        int glow = (Integer)u_2550_I.J_1907_R();
        float glowAlpha = (float)H_2506_c.G_564_y(glow) / 255.0f;
        int outline = (Integer)s_956_w.J_1907_R();
        float outlineAlphaValue = H_2506_c.G_564_y(outline);
        int bgColorValue = (Integer)w_1484_f.J_1907_R();
        F_489_x.n_1700_B(buildX - 10.0f, posY - 10.0f, buildRectWidth + 20.0f, 36.0f, 3.0f, glow, glow, glow, glow, glowAlpha, 10.0f);
        F_489_x.n_1700_B(buildX, posY, buildRectWidth, 14.0f, 3.0f, bgColorValue, 1.0f);
        F_489_x.J_1907_R(buildX, posY, buildRectWidth, 14.0f, 3.0f, outline, outlineAlphaValue);
        l_3370_o.w_1484_f[18].n_1700_B(ms, "P", (double)(buildX + 5.5f), (double)(posY + 5.0f), iconColorValue);
        F_489_x.n_1700_B(ms, buildX + 8.0f + iconPWidth, posY + 3.0f, 0.5f, 8.0f, q_3148_R.n_1700_B(K_1200_E.Q_4569_t));
        l_3370_o.G_564_y[14].n_1700_B(ms, "Beta", (double)(buildX + 12.0f + iconPWidth), (double)(posY + 5.5f), textColorValue);
        float currentX = combinedX;
        if (blockCount > 0) {
            F_489_x.n_1700_B(currentX - 10.0f, posY - 10.0f, combinedWidthRendered + 20.0f, 36.0f, 3.0f, glow, glow, glow, glow, glowAlpha, 10.0f);
            F_489_x.n_1700_B(currentX, posY, combinedWidthRendered, 14.0f, 3.0f, bgColorValue, 1.0f);
            F_489_x.J_1907_R(currentX, posY, combinedWidthRendered, 14.0f, 3.0f, outline, outlineAlphaValue);
            float subBlockX = currentX + 5.0f;
            if (nameEnabled) {
                l_3370_o.u_1723_Y[14].n_1700_B(ms, "W", (double)subBlockX, (double)(posY + 6.5f), iconColorValue);
                l_3370_o.G_564_y[13].n_1700_B(ms, nameText, (double)((subBlockX += iconWWidth + 3.0f) - 1.0f), (double)(posY + 6.0f), textColorValue);
                subBlockX += nameTextWidth + 5.0f;
                if (fpsEnabled || pingEnabled || tpsEnabled) {
                    F_489_x.n_1700_B(ms, subBlockX - 3.0f, posY + 3.5f, 0.5f, 7.0f, q_3148_R.n_1700_B(K_1200_E.Q_4569_t));
                    subBlockX += 2.0f;
                }
            }
            if (fpsEnabled) {
                l_3370_o.u_1723_Y[14].n_1700_B(ms, "X", (double)subBlockX, (double)(posY + 6.5f), iconColorValue);
                l_3370_o.G_564_y[13].n_1700_B(ms, fpsText, (double)((subBlockX += iconXWidth + 3.0f) - 0.5f), (double)(posY + 6.0f), textColorValue);
                subBlockX += fpsTextWidth + 5.0f;
                if (pingEnabled || tpsEnabled) {
                    F_489_x.n_1700_B(ms, subBlockX - 2.5f, posY + 3.5f, 0.5f, 7.0f, q_3148_R.n_1700_B(K_1200_E.Q_4569_t));
                    subBlockX += 2.0f;
                }
            }
            if (pingEnabled) {
                l_3370_o.u_1723_Y[14].n_1700_B(ms, "Q", (double)subBlockX, (double)(posY + 6.5f), iconColorValue);
                l_3370_o.G_564_y[13].n_1700_B(ms, pingText, (double)((subBlockX += iconQWidth + 3.0f) - 1.0f), (double)(posY + 6.0f), textColorValue);
                subBlockX += pingTextWidth + 5.0f;
                if (tpsEnabled) {
                    F_489_x.n_1700_B(ms, subBlockX - 2.5f, posY + 3.5f, 0.5f, 7.0f, q_3148_R.n_1700_B(K_1200_E.Q_4569_t));
                    subBlockX += 2.0f;
                }
            }
            if (tpsEnabled) {
                l_3370_o.u_1723_Y[14].n_1700_B(ms, "$", (double)(subBlockX - 0.5f), (double)(posY + 6.5f), iconColorValue);
                l_3370_o.G_564_y[13].n_1700_B(ms, tpsText, (double)((subBlockX += iconGWidth + 3.0f) - 1.0f), (double)(posY + 6.0f), textColorValue);
                subBlockX += tpsTextWidth + 5.0f;
            }
        }
        this.h_1847_R.n_1700_B(posBaseX);
        this.h_1847_R.J_1907_R(5.0f + this.n_1700_B());
        this.h_1847_R.R_4764_Y(totalWidth);
        this.h_1847_R.G_564_y(29.0f);
    }

    public void J_1907_R(b_3528_u event) {
        float posX;
        if (!R_4764_Y.t_148_a().booleanValue() && !v_4262_N.t_148_a().booleanValue()) {
            return;
        }
        float posY = 21.0f + this.n_1700_B();
        g_221_o ms = event.J_1907_R();
        int textColorValue = (Integer)t_148_a.J_1907_R();
        int iconColorValue = (Integer)M_588_G.J_1907_R();
        int glow = (Integer)u_2550_I.J_1907_R();
        float glowAlpha = (float)H_2506_c.G_564_y(glow) / 255.0f;
        int outline = (Integer)s_956_w.J_1907_R();
        float outlineAlphaValue = H_2506_c.G_564_y(outline);
        int bgColorValue = (Integer)w_1484_f.J_1907_R();
        String coordsText = R_4764_Y.t_148_a() != false ? l_3729_r.J_1907_R() : "";
        String netherCoordsText = R_4764_Y.t_148_a() != false && G_564_y.t_148_a() != false ? l_3729_r.R_4764_Y() : "";
        float coordsOnlyWidth = R_4764_Y.t_148_a() != false ? l_3370_o.G_564_y[13].n_1700_B(coordsText) : 0.0f;
        float netherOnlyWidth = R_4764_Y.t_148_a() != false && G_564_y.t_148_a() != false ? l_3370_o.G_564_y[13].n_1700_B(netherCoordsText) : 0.0f;
        float separatorWidth = R_4764_Y.t_148_a() != false && G_564_y.t_148_a() != false ? 6.0f : 0.0f;
        float coordsTextWidth = coordsOnlyWidth + separatorWidth + netherOnlyWidth;
        float iconFWidth = l_3370_o.u_1723_Y[14].n_1700_B("F");
        Object bpsText = v_4262_N.t_148_a() != false ? l_3729_r.G_564_y() + " Bps" : "";
        float bpsTextWidth = v_4262_N.t_148_a() != false ? l_3370_o.G_564_y[13].n_1700_B((String)bpsText) : 0.0f;
        float iconAtWidth = l_3370_o.u_1723_Y[14].n_1700_B("@");
        float coordsBlockWidth = iconFWidth + 3.0f + coordsTextWidth;
        float bpsBlockWidth = iconAtWidth + 3.0f + bpsTextWidth;
        float totalWidth = 5.0f;
        int blockCount = 0;
        if (R_4764_Y.t_148_a().booleanValue()) {
            totalWidth += coordsBlockWidth;
            ++blockCount;
        }
        if (v_4262_N.t_148_a().booleanValue()) {
            totalWidth += bpsBlockWidth;
            ++blockCount;
        }
        if (blockCount > 1) {
            totalWidth += 5.0f;
        }
        totalWidth += 5.0f;
        if (P_4830_p.J_1907_R("\u041b\u0435\u0432\u043e")) {
            posX = (float)Math.floor(this.h_1847_R.J_1907_R());
        } else if (P_4830_p.J_1907_R("\u041f\u0440\u0430\u0432\u043e")) {
            posX = (float)Math.floor(this.h_1847_R.J_1907_R() + this.h_1847_R.G_564_y() - totalWidth);
        } else {
            float watermarkCenter = this.h_1847_R.J_1907_R() + this.h_1847_R.G_564_y() / 2.0f;
            posX = (float)Math.floor(watermarkCenter - totalWidth / 2.0f);
        }
        F_489_x.n_1700_B(posX - 10.0f, posY - 10.0f, totalWidth + 20.0f, 36.0f, 4.0f, glow, glow, glow, glow, glowAlpha, 10.0f);
        F_489_x.n_1700_B(posX, posY, totalWidth, 14.0f, 3.0f, bgColorValue, 1.0f);
        F_489_x.J_1907_R(posX, posY, totalWidth, 14.0f, 3.0f, outline, outlineAlphaValue);
        float contentX = posX + 5.0f;
        if (R_4764_Y.t_148_a().booleanValue()) {
            l_3370_o.u_1723_Y[14].n_1700_B(ms, "F", (double)contentX, (double)(posY + 6.5f), iconColorValue);
            l_3370_o.G_564_y[13].n_1700_B(ms, coordsText, (double)(contentX += iconFWidth + 3.0f), (double)(posY + 6.0f), textColorValue);
            contentX += coordsOnlyWidth;
            if (G_564_y.t_148_a().booleanValue()) {
                F_489_x.n_1700_B(ms, contentX += 2.5f, posY + 3.5f, 0.5f, 7.0f, q_3148_R.n_1700_B(K_1200_E.Q_4569_t));
                int netherColor = H_2506_c.n_1700_B(255, 80, 80, 125);
                l_3370_o.G_564_y[13].n_1700_B(ms, netherCoordsText, (double)(contentX += 3.0f), (double)(posY + 6.0f), netherColor);
                contentX += netherOnlyWidth;
            }
        }
        if (R_4764_Y.t_148_a().booleanValue() && v_4262_N.t_148_a().booleanValue()) {
            F_489_x.n_1700_B(ms, contentX + 2.5f, posY + 3.5f, 0.5f, 7.0f, q_3148_R.n_1700_B(K_1200_E.Q_4569_t));
            contentX += 5.0f;
        }
        if (v_4262_N.t_148_a().booleanValue()) {
            l_3370_o.u_1723_Y[14].n_1700_B(ms, "@", (double)contentX, (double)(posY + 6.5f), iconColorValue);
            l_3370_o.G_564_y[13].n_1700_B(ms, (String)bpsText, (double)(contentX += iconAtWidth + 3.0f), (double)(posY + 6.0f), textColorValue);
        }
    }

    public void R_4764_Y(b_3528_u event) {
    }
}



