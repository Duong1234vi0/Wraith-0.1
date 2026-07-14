/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import lightning.product.D_4024_W;
import lightning.product.ScoreboardHealth;
import lightning.product.F_489_x;
import lightning.product.F_747_P;
import lightning.product.H_2506_c;
import lightning.product.HitResult;
import lightning.product.J_3635_s;
import lightning.product.K_1200_E;
import lightning.product.N_4263_v;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.ServerHandshakePacketListener;
import lightning.product.b_3528_u;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.h_2367_h;
import lightning.product.h_4412_P;
import lightning.product.Easing;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.q_3115_L;
import lightning.product.q_3148_R;
import lightning.product.ModeSetting;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.EntityHitResult;
import lombok.Generated;

public class z_283_n
implements ServerHandshakePacketListener {
    public static final ModeSetting n_1700_B = new ModeSetting("\u0420\u0435\u0436\u0438\u043c \u043e\u0442\u043e\u0431\u0440\u0430\u0436\u0435\u043d\u0438\u044f", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "\u041a\u0440\u0443\u0433", "Akrien");
    public static final ModeSetting J_1907_R = new ModeSetting("\u0426\u0432\u0435\u0442 \u0437\u0434\u043e\u0440\u043e\u0432\u044c\u044f", "\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439", "\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439", "\u0417\u0434\u043e\u0440\u043e\u0432\u044c\u0435");
    public static final BooleanSetting R_4764_Y = new BooleanSetting("\u0417\u043e\u043b\u043e\u0442\u044b\u0435 \u0441\u0435\u0440\u0434\u0446\u0430", true);
    public static final BooleanSetting G_564_y = new BooleanSetting("\u0427\u0430\u0441\u0442\u0438\u0446\u044b", true);
    public static final BooleanSetting P_1922_E = new BooleanSetting("\u041f\u0440\u0438 \u043d\u0430\u0432\u0435\u0434\u0435\u043d\u0438\u0438", false);
    public static final BooleanSetting u_1723_Y = new BooleanSetting("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u0431\u0440\u043e\u043d\u044e", false);
    public static final BooleanSetting v_4262_N = new BooleanSetting("\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u044b\u0439 \u0444\u043e\u043d", false);
    public static h_2367_h w_1484_f = new h_2367_h("\u0426\u0432\u0435\u0442 \u0444\u043e\u043d\u0430", true, H_2506_c.n_1700_B(30, 25, 40, 255));
    public static h_2367_h t_148_a = new h_2367_h("\u0426\u0432\u0435\u0442 \u0442\u0435\u043a\u0441\u0442\u0430", true, H_2506_c.n_1700_B(180, 140, 255, 255));
    public static h_2367_h s_956_w = new h_2367_h("\u0426\u0432\u0435\u0442 \u043e\u0431\u0432\u043e\u0434\u043a\u0438", true, H_2506_c.n_1700_B(120, 80, 160, 255));
    public static h_2367_h u_2550_I = new h_2367_h("\u0426\u0432\u0435\u0442 \u0441\u0432\u0435\u0447\u0435\u043d\u0438\u044f", true, H_2506_c.n_1700_B(120, 80, 160, 100));
    private final J_3635_s M_588_G;
    private final DecimalFormat P_4830_p = new DecimalFormat("0.0", new DecimalFormatSymbols(Locale.US));
    private final Animation h_1847_R = new Animation(0.0f, 3.0f, Easing.u_2550_I);
    private final Animation Q_4569_t = new Animation(0.0f, 3.0f, Easing.u_1723_Y);
    private final Animation M_182_A = new Animation(0.0f, 10.0f, Easing.u_2550_I);
    private final Animation t_1786_h = new Animation(1.0f, 15.0f, Easing.u_2550_I);
    private final Animation multiplayerClientSuggestionProvider = new Animation(0.0f, 3.0f, Easing.u_2550_I);
    private final Animation w_1457_N = new Animation(0.0f, 3.0f, Easing.u_1723_Y);
    private final Animation Y_601_j = new Animation(0.0f, 18.0f, Easing.u_1723_Y);
    private N_4263_v Y_259_p = null;
    private float Q_2552_b = 0.0f;
    private final CopyOnWriteArrayList<n_1700_B> C_2741_M = new CopyOnWriteArrayList();

    @Override
    public void n_1700_B(b_3528_u event) {
        N_4263_v entityToUpdate;
        float posX = this.M_588_G.J_1907_R();
        float posY = this.M_588_G.R_4764_Y();
        g_221_o ms = event.J_1907_R();
        r_4811_B auraTarget = ClientBootstrap.Y_601_j().J_1907_R().J_1907_R().h_1847_R();
        N_4263_v target = this.n_1700_B(auraTarget);
        float targetAlpha = target != null ? 1.0f : 0.0f;
        N_4263_v n_4263_v = entityToUpdate = target != null ? target : this.Y_259_p;
        if (entityToUpdate instanceof r_4811_B) {
            r_4811_B livingEntityToUpdate = (r_4811_B)entityToUpdate;
            float currentHP = this.J_1907_R(livingEntityToUpdate);
            float absorption = livingEntityToUpdate.U_3823_u();
            if (target != this.Y_259_p && target != null) {
                this.h_1847_R.J_1907_R(currentHP);
                this.Q_4569_t.J_1907_R(currentHP);
                this.multiplayerClientSuggestionProvider.J_1907_R(absorption);
                this.w_1457_N.J_1907_R(absorption);
            }
            this.h_1847_R.n_1700_B(currentHP);
            this.Q_4569_t.n_1700_B(currentHP);
            this.multiplayerClientSuggestionProvider.n_1700_B(absorption);
            this.w_1457_N.n_1700_B(absorption);
        }
        this.n_1700_B(targetAlpha);
        if (this.M_182_A.n_1700_B() <= 0.0f && !this.C_2741_M.isEmpty()) {
            this.C_2741_M.clear();
        }
        if (this.M_182_A.n_1700_B() > 0.0f) {
            this.n_1700_B(ms, target != null ? target : this.Y_259_p, posX, posY);
        }
        this.Y_259_p = target != null ? target : this.Y_259_p;
        this.n_1700_B(target != null ? target : this.Y_259_p);
    }

    private void n_1700_B(N_4263_v ref) {
        String raw = ref != null ? D_4024_W.n_1700_B(ref.O_1309_Q().getString()) : "";
        float dragW = 100.0f;
        float dragH = 38.0f;
        if (n_1700_B.J_1907_R("\u041a\u0440\u0443\u0433")) {
            dragW = 130.0f;
            dragH = 38.0f;
        } else if (n_1700_B.J_1907_R("\u041e\u0431\u044b\u0447\u043d\u044b\u0439")) {
            dragW = 100.0f;
            dragH = u_1723_Y.t_148_a() != false ? 60.0f : 38.0f;
        } else if (n_1700_B.J_1907_R("Akrien")) {
            dragW = Math.max(100.0f, l_3370_o.G_564_y[16].n_1700_B(raw) + 45.0f);
            dragH = u_1723_Y.t_148_a() != false ? 60.0f : 39.5f;
        }
        this.M_588_G.R_4764_Y(dragW);
        this.M_588_G.G_564_y(dragH);
    }

    private N_4263_v n_1700_B(r_4811_B auraTarget) {
        if (P_1922_E.t_148_a().booleanValue()) {
            if (z_283_n.c_3005_b.Y_1740_V instanceof h_4412_P) {
                return z_283_n.c_3005_b.Y_259_p;
            }
            HitResult hitResult = z_283_n.c_3005_b.Z_875_P;
            if (hitResult != null && hitResult.R_4764_Y() == HitResult.n_1700_B.R_4764_Y) {
                N_4263_v entity = ((EntityHitResult)hitResult).n_1700_B();
                return entity instanceof r_4811_B ? entity : auraTarget;
            }
            return auraTarget;
        }
        return auraTarget == null && z_283_n.c_3005_b.Y_1740_V instanceof h_4412_P ? z_283_n.c_3005_b.Y_259_p : auraTarget;
    }

    private float J_1907_R(r_4811_B entity) {
        boolean useScoreboard = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(ScoreboardHealth.class).w_1484_f();
        if (useScoreboard) {
            float normalHealth;
            float scoreboardHealth = q_3115_L.n_1700_B(entity);
            if ((double)scoreboardHealth == Math.floor(normalHealth = entity.g_46_E() + entity.U_3823_u())) {
                return entity.g_46_E();
            }
            return Math.max(0.0f, scoreboardHealth);
        }
        return entity.g_46_E();
    }

    private void n_1700_B(float targetAlpha) {
        this.M_182_A.n_1700_B(targetAlpha);
        this.t_1786_h.n_1700_B(u_1723_Y.t_148_a() != false && targetAlpha > 0.0f ? 1.0f : 0.0f);
    }

    private void n_1700_B(g_221_o ms, N_4263_v renderTarget, float posX, float posY) {
        float currentHurtTime;
        if (!(renderTarget instanceof r_4811_B)) {
            return;
        }
        r_4811_B livingTarget = (r_4811_B)renderTarget;
        float alpha = this.M_182_A.n_1700_B();
        float f = currentHurtTime = livingTarget.RealmsLongRunningMcoTaskScreen > 0 ? Math.min(0.5f, (float)livingTarget.RealmsLongRunningMcoTaskScreen / (float)livingTarget.i_2993_w) : 0.0f;
        if (G_564_y.t_148_a().booleanValue() && currentHurtTime > this.Q_2552_b) {
            for (int i = 0; i < 5; ++i) {
                this.C_2741_M.add(new n_1700_B(new e_2866_D(14.0, 16.5, 0.0)));
            }
        }
        this.Q_2552_b = currentHurtTime;
        float currentHP = this.J_1907_R(livingTarget);
        float absorption = livingTarget.U_3823_u();
        float maxHP = livingTarget.L_1733_J();
        float hpPercentage = Math.min(this.h_1847_R.n_1700_B() / maxHP, 1.0f);
        float secondaryHpPercentage = Math.min(this.Q_4569_t.n_1700_B() / maxHP, 1.0f);
        float hpBarWidth = 58.0f * hpPercentage;
        float secondaryHpBarWidth = 58.0f * secondaryHpPercentage;
        int textColorValue = H_2506_c.n_1700_B((int)((Integer)t_148_a.J_1907_R()), alpha);
        float bgAlphaBase = (float)H_2506_c.G_564_y((Integer)w_1484_f.J_1907_R()) / 255.0f * alpha;
        int bgColorValue = v_4262_N.t_148_a() != false ? H_2506_c.n_1700_B((int)((Integer)w_1484_f.J_1907_R()), 0) : H_2506_c.n_1700_B((int)((Integer)w_1484_f.J_1907_R()), bgAlphaBase);
        int glowColorValue = (Integer)u_2550_I.J_1907_R();
        float glowAlpha = (float)H_2506_c.G_564_y(glowColorValue) / 255.0f * alpha;
        float outlineAlphaValue = (float)H_2506_c.G_564_y((Integer)s_956_w.J_1907_R()) * alpha;
        int outlineRgb = (Integer)s_956_w.J_1907_R();
        if (n_1700_B.J_1907_R("Akrien")) {
            this.n_1700_B(ms, renderTarget, livingTarget, posX, posY, alpha);
            if (u_1723_Y.t_148_a().booleanValue()) {
                float w = Math.max(100.0f, l_3370_o.G_564_y[16].n_1700_B(D_4024_W.n_1700_B(renderTarget.O_1309_Q().getString())) + 45.0f);
                this.n_1700_B(ms, livingTarget, posX, posY, alpha, w, posY + 41.5f, false);
            }
            return;
        }
        float rectWidth = n_1700_B.J_1907_R("\u041a\u0440\u0443\u0433") ? 130.0f : 100.0f;
        F_489_x.n_1700_B(posX - 10.0f, posY - 10.0f, rectWidth + 20.0f, 58.0f, 7.0f, glowColorValue, glowColorValue, glowColorValue, glowColorValue, glowAlpha, 10.0f);
        F_489_x.n_1700_B(posX, posY, rectWidth, 38.0f, 5.5f, bgColorValue, bgAlphaBase);
        this.n_1700_B(ms, renderTarget, posX, posY, textColorValue, alpha);
        if (n_1700_B.J_1907_R("\u041a\u0440\u0443\u0433")) {
            this.n_1700_B(ms, renderTarget, posX, posY, currentHP, absorption, textColorValue, textColorValue);
            this.n_1700_B(ms, posX, posY, hpPercentage, secondaryHpPercentage, currentHP, absorption, maxHP, alpha);
        } else {
            this.n_1700_B(ms, renderTarget, posX, posY, currentHP, absorption, textColorValue, textColorValue);
            this.n_1700_B(posX, posY, hpBarWidth, secondaryHpBarWidth, currentHP, absorption, maxHP, alpha);
        }
        if (u_1723_Y.t_148_a().booleanValue()) {
            boolean circle = n_1700_B.J_1907_R("\u041a\u0440\u0443\u0433");
            this.n_1700_B(ms, livingTarget, posX, posY, alpha, rectWidth, circle ? posY + 19.0f : posY + 40.0f, circle);
        }
        this.n_1700_B(posX, posY, alpha);
        F_489_x.J_1907_R(posX, posY, rectWidth, 38.0f, 5.5f, outlineRgb, outlineAlphaValue);
    }

    private static int n_1700_B(int signedArgb, float hudAlpha) {
        long u = (long)signedArgb & 0xFFFFFFFFL;
        int r = (int)(u >>> 16 & 0xFFL);
        int g = (int)(u >>> 8 & 0xFFL);
        int b = (int)(u & 0xFFL);
        return H_2506_c.n_1700_B(r, g, b, (int)(u_530_F.n_1700_B(hudAlpha, 0.0f, 1.0f) * 255.0f));
    }

    private void n_1700_B(g_221_o ms, N_4263_v renderTarget, r_4811_B target, float x, float y, float alpha) {
        double armorValue;
        int COLOR_HP_L = -16737215;
        int COLOR_HP_R = -7405631;
        int COLOR_ARMOR_L = -16750672;
        int COLOR_ARMOR_R = -12986881;
        String namePlain = D_4024_W.n_1700_B(renderTarget.O_1309_Q().getString());
        float w = Math.max(100.0f, l_3370_o.G_564_y[16].n_1700_B(namePlain) + 45.0f);
        float h = 39.5f;
        int bg = new Color(0.0f, 0.0f, 0.0f, 0.4f * alpha).getRGB();
        F_489_x.n_1700_B(x, y, w, 39.5f, bg);
        F_489_x.n_1700_B(x + 2.5f, y + 31.0f, w - 4.5f, 2.5f, bg);
        F_489_x.n_1700_B(x + 2.5f, y + 34.5f, w - 4.5f, 2.5f, bg);
        double healthPercentage = u_530_F.n_1700_B((double)((target.g_46_E() + target.U_3823_u()) / (target.L_1733_J() + target.U_3823_u())), 0.0, 1.0);
        float barSpan = w - 3.5f;
        float endWidth = (float)Math.max(0.0, (double)barSpan * healthPercentage);
        this.Y_601_j.n_1700_B(endWidth);
        float hpFillW = u_530_F.n_1700_B(this.Y_601_j.n_1700_B(), 0.0f, barSpan);
        if (hpFillW > 0.0f) {
            F_489_x.n_1700_B(x + 2.5f, y + 31.0f, x + 1.5f + hpFillW, y + 33.5f, 0.74f, z_283_n.n_1700_B(-16737215, alpha), z_283_n.n_1700_B(-7405631, alpha), bg);
        }
        if ((armorValue = u_530_F.n_1700_B((double)target.E_3343_g() / 20.0, 0.0, 1.0)) > 0.0) {
            float armorFillW = (float)((double)barSpan * armorValue);
            F_489_x.n_1700_B(x + 2.5f, y + 34.5f, x + 1.5f + armorFillW, y + 37.0f, 0.74f, z_283_n.n_1700_B(-16750672, alpha), z_283_n.n_1700_B(-12986881, alpha), bg);
        }
        int textColor = H_2506_c.n_1700_B(-1, alpha);
        if (renderTarget instanceof a_3913_L) {
            a_3913_L pe = (a_3913_L)renderTarget;
            F_489_x.n_1700_B(c_3005_b.O_508_d().n_1700_B(renderTarget).n_1700_B(pe), pe, x + 3.0f, y + 3.0f, 26.0f, 26.0f, 0.5f, alpha);
        } else {
            l_3370_o.G_564_y[16].n_1700_B(ms, "?", (double)(x + 11.0f), (double)(y + 12.0f), textColor);
        }
        l_3370_o.G_564_y[16].n_1700_B(ms, namePlain, (double)(x + 31.0f), (double)(y + 5.0f), textColor);
        l_3370_o.G_564_y[16].n_1700_B(ms, "Health: " + this.P_4830_p.format(target.g_46_E()), (double)(x + 31.0f), (double)(y + 15.0f), textColor);
        l_3370_o.G_564_y[16].n_1700_B(ms, "Distance: " + this.P_4830_p.format(z_283_n.c_3005_b.Y_259_p.R_4764_Y(renderTarget)) + "m", (double)(x + 31.0f), (double)(y + 22.0f), textColor);
    }

    private void n_1700_B(float posX, float posY, float alpha) {
        if (!G_564_y.t_148_a().booleanValue()) {
            return;
        }
        this.C_2741_M.removeIf(particle -> System.currentTimeMillis() - particle.G_564_y > particle.u_1723_Y);
        int healthColor = J_1907_R.J_1907_R("\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439") ? q_3148_R.n_1700_B(K_1200_E.J_1907_R) : H_2506_c.n_1700_B(0, 190, 45);
        for (n_1700_B particle2 : this.C_2741_M) {
            particle2.n_1700_B(posX, posY);
            float size = 1.0f - (float)(System.currentTimeMillis() - particle2.G_564_y) / (float)particle2.u_1723_Y;
            float radius = 2.3f;
            F_489_x.n_1700_B((float)particle2.J_1907_R.J_1907_R - 3.0f, (float)particle2.J_1907_R.R_4764_Y - 3.0f, radius * 2.0f, radius * 2.0f, radius - 1.0f, H_2506_c.n_1700_B(healthColor, (int)(255.0f * particle2.P_1922_E * size * alpha)));
        }
    }

    private void n_1700_B(g_221_o ms, N_4263_v renderTarget, float posX, float posY, int textColor, float alpha) {
        if (renderTarget instanceof a_3913_L) {
            F_489_x.n_1700_B(c_3005_b.O_508_d().n_1700_B(renderTarget).n_1700_B((r_4811_B)renderTarget), (r_4811_B)renderTarget, posX + 3.0f, posY + 3.0f, 32.0f, 32.0f, 4.0f, alpha);
        } else if (renderTarget instanceof r_4811_B) {
            r_4811_B livingEntity = (r_4811_B)renderTarget;
            l_3370_o.u_1723_Y[40].n_1700_B(ms, "N", (double)(posX + 10.0f), (double)(posY + 12.0f), textColor);
        }
    }

    private void n_1700_B(g_221_o ms, r_4811_B livingTarget, float posX, float posY, float alpha, float hudWidth, float armorPanelTop, boolean compactCircle) {
        float armorPanelX;
        ArrayList<Z_1993_T> armorItems = new ArrayList<Z_1993_T>();
        for (Z_1993_T stack : livingTarget.u_55_V()) {
            armorItems.add(stack.n_1700_B() ? Z_1993_T.J_1907_R : stack);
        }
        while (armorItems.size() < 4) {
            armorItems.add(Z_1993_T.J_1907_R);
        }
        float panelY = armorPanelTop;
        float slotSize = 12.0f;
        float spacing = 2.0f;
        float panelPadding = 2.0f;
        int armorSlotCount = 4;
        boolean includeHands = !compactCircle;
        int handSlotCount = includeHands ? 2 : 0;
        float handSpacing = includeHands ? 14.0f : 0.0f;
        float armorPanelWidth = (float)armorSlotCount * slotSize + (float)(armorSlotCount - 1) * spacing + panelPadding * 2.0f;
        float handPanelWidth = handSlotCount > 0 ? (float)handSlotCount * slotSize + (float)(handSlotCount - 1) * spacing + panelPadding * 2.0f : 0.0f;
        float totalWidth = armorPanelWidth + (handSlotCount > 0 ? handSpacing + handPanelWidth : 0.0f);
        float bgArmorAlpha = (float)H_2506_c.G_564_y((Integer)w_1484_f.J_1907_R()) / 255.0f * alpha;
        int bgArmorColor = v_4262_N.t_148_a() != false ? H_2506_c.n_1700_B((int)((Integer)w_1484_f.J_1907_R()), 0) : H_2506_c.n_1700_B((int)((Integer)w_1484_f.J_1907_R()), bgArmorAlpha);
        int outlineArmorColor = (Integer)s_956_w.J_1907_R();
        float outlineArmorAlpha = (float)H_2506_c.G_564_y(outlineArmorColor) * alpha;
        int glowArmorColor = (Integer)u_2550_I.J_1907_R();
        float glowArmorAlpha = (float)H_2506_c.G_564_y(glowArmorColor) / 255.0f * alpha;
        float startX = posX + (hudWidth - totalWidth) / 2.0f;
        float f = armorPanelX = compactCircle ? startX : startX + 3.0f;
        if (!compactCircle) {
            F_489_x.n_1700_B(armorPanelX - 10.0f, panelY - 10.0f, armorPanelWidth + 20.0f, 36.0f, 3.0f, glowArmorColor, glowArmorColor, glowArmorColor, glowArmorColor, glowArmorAlpha, 10.0f);
            F_489_x.n_1700_B(armorPanelX, panelY, armorPanelWidth, 16.0f, 3.0f, bgArmorColor, bgArmorAlpha);
            F_489_x.J_1907_R(armorPanelX, panelY, armorPanelWidth, 16.0f, 3.0f, outlineArmorColor, outlineArmorAlpha);
        }
        for (int i = 0; i < armorSlotCount; ++i) {
            float slotX = armorPanelX + panelPadding + (float)i * (slotSize + spacing);
            F_489_x.n_1700_B(slotX, panelY + 2.0f, slotSize, slotSize, 2.0f, H_2506_c.n_1700_B((int)((Integer)w_1484_f.J_1907_R()), bgArmorAlpha * 0.66f));
            F_489_x.J_1907_R(slotX, panelY + 2.0f, slotSize, slotSize, 2.0f, outlineArmorColor, outlineArmorAlpha);
            if (((Z_1993_T)armorItems.get(i)).n_1700_B()) continue;
            F_489_x.n_1700_B(slotX + slotSize / 2.0f, panelY + 2.0f + slotSize / 2.0f, this.t_1786_h.n_1700_B());
            F_489_x.n_1700_B((Z_1993_T)armorItems.get(i), slotX + 2.0f, panelY + 2.0f + 2.0f, 0.5f);
            F_489_x.R_4764_Y();
        }
        if (handSlotCount > 0) {
            Z_1993_T mainhand = livingTarget.A_2714_y();
            Z_1993_T offhand = livingTarget.S_4035_N();
            float itemOffset = (slotSize - 8.0f) / 2.0f;
            float handPanelX = startX + armorPanelWidth + handSpacing - 5.0f;
            F_489_x.n_1700_B(handPanelX - 10.0f, panelY - 10.0f, handPanelWidth + 20.0f, 36.0f, 3.0f, glowArmorColor, glowArmorColor, glowArmorColor, glowArmorColor, glowArmorAlpha, 10.0f);
            F_489_x.n_1700_B(handPanelX, panelY, handPanelWidth, 16.0f, 3.0f, bgArmorColor, bgArmorAlpha);
            F_489_x.J_1907_R(handPanelX, panelY, handPanelWidth, 16.0f, 3.0f, outlineArmorColor, outlineArmorAlpha);
            float mainhandX = handPanelX + panelPadding;
            F_489_x.n_1700_B(mainhandX, panelY + 2.0f, slotSize, slotSize, 2.0f, H_2506_c.n_1700_B((int)((Integer)w_1484_f.J_1907_R()), bgArmorAlpha * 0.66f));
            F_489_x.J_1907_R(mainhandX, panelY + 2.0f, slotSize, slotSize, 2.0f, outlineArmorColor, outlineArmorAlpha);
            if (!mainhand.n_1700_B()) {
                F_489_x.n_1700_B(mainhandX + slotSize / 2.0f, panelY + 2.0f + slotSize / 2.0f, this.t_1786_h.n_1700_B());
                F_489_x.n_1700_B(mainhand, mainhandX + itemOffset, panelY + 2.0f + itemOffset, 0.5f);
                F_489_x.R_4764_Y();
            }
            float offhandX = mainhandX + slotSize + spacing;
            F_489_x.n_1700_B(offhandX, panelY + 2.0f, slotSize, slotSize, 2.0f, H_2506_c.n_1700_B((int)((Integer)w_1484_f.J_1907_R()), bgArmorAlpha * 0.66f));
            F_489_x.J_1907_R(offhandX, panelY + 2.0f, slotSize, slotSize, 2.0f, outlineArmorColor, outlineArmorAlpha);
            if (!offhand.n_1700_B()) {
                F_489_x.n_1700_B(offhandX + slotSize / 2.0f, panelY + 2.0f + slotSize / 2.0f, this.t_1786_h.n_1700_B());
                F_489_x.n_1700_B(offhand, offhandX + itemOffset, panelY + 2.0f + itemOffset, 0.5f);
                F_489_x.R_4764_Y();
            }
        }
    }

    private void n_1700_B(float posX, float posY, float hpBarWidth, float secondaryHpBarWidth, float currentHP, float absorption, float maxHP, float alpha) {
        if (hpBarWidth <= 0.0f) {
            return;
        }
        float barX = posX + 37.0f;
        float barY = posY + 26.0f;
        float barWidth = 58.0f;
        float barHeight = 8.0f;
        float radius = 2.0f;
        if (J_1907_R.J_1907_R("\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439")) {
            int activeColor = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
            int inactiveColor = H_2506_c.J_1907_R(activeColor, 0.3f);
            float baseAlpha = q_3148_R.J_1907_R(K_1200_E.J_1907_R) / 255.0f * alpha;
            F_489_x.n_1700_B(barX, barY, barWidth, barHeight, radius, inactiveColor, inactiveColor, activeColor, activeColor, baseAlpha * 0.3f);
            F_489_x.n_1700_B(barX, barY, secondaryHpBarWidth, barHeight, radius, inactiveColor, inactiveColor, activeColor, activeColor, baseAlpha * 0.75f);
            F_489_x.n_1700_B(barX, barY, hpBarWidth, barHeight, radius, inactiveColor, inactiveColor, activeColor, activeColor, baseAlpha);
        } else {
            int[] colors = this.n_1700_B(currentHP, maxHP);
            F_489_x.n_1700_B(barX, barY, barWidth, barHeight, radius, colors[0], colors[0], colors[1], colors[1], 0.3137255f * alpha);
            F_489_x.n_1700_B(barX, barY, secondaryHpBarWidth, barHeight, radius, colors[2], colors[2], colors[3], colors[3], 0.54901963f * alpha);
            F_489_x.n_1700_B(barX, barY, hpBarWidth, barHeight, radius, colors[2], colors[2], colors[4], colors[4], alpha);
        }
        if (R_4764_Y.t_148_a().booleanValue() && absorption > 0.0f) {
            float animatedAbsorption = this.multiplayerClientSuggestionProvider.n_1700_B();
            float absorptionBarWidth = 58.0f * Math.min(animatedAbsorption / maxHP, 1.0f);
            float lagAbsorption = this.w_1457_N.n_1700_B();
            float secondaryAbsorptionBarWidth = 58.0f * Math.min(lagAbsorption / maxHP, 1.0f);
            int goldTop = H_2506_c.n_1700_B(255, 210, 0);
            int goldBottom = H_2506_c.J_1907_R(goldTop, 0.3f);
            F_489_x.n_1700_B(barX, barY, secondaryAbsorptionBarWidth, barHeight, radius, H_2506_c.J_1907_R(goldBottom, 0.6f), H_2506_c.J_1907_R(goldBottom, 0.6f), H_2506_c.J_1907_R(goldTop, 0.8f), H_2506_c.J_1907_R(goldTop, 0.8f), alpha * 0.6f);
            F_489_x.n_1700_B(barX, barY, absorptionBarWidth, barHeight, radius, goldBottom, goldBottom, goldTop, goldTop, alpha);
        }
    }

    private void n_1700_B(g_221_o ms, float posX, float posY, float hpPercentage, float secondaryHpPercentage, float currentHP, float absorption, float maxHP, float alpha) {
        int bgColorValue;
        float rectWidth = n_1700_B.J_1907_R("\u041a\u0440\u0443\u0433") ? 130.0f : 100.0f;
        float centerX = posX + rectWidth - 17.0f;
        float centerY = posY + 19.0f;
        float outerRadius = 13.0f;
        float innerRadius = 11.0f;
        double startAngle = -1.5707963267948966;
        if (alpha <= 0.0f) {
            return;
        }
        hpPercentage = Math.min(hpPercentage, 1.0f);
        secondaryHpPercentage = Math.min(secondaryHpPercentage, 1.0f);
        float bgAlpha = (float)H_2506_c.G_564_y((Integer)w_1484_f.J_1907_R()) / 255.0f * alpha;
        int n = bgColorValue = v_4262_N.t_148_a() != false ? H_2506_c.n_1700_B((int)((Integer)w_1484_f.J_1907_R()), 0) : H_2506_c.n_1700_B((int)((Integer)w_1484_f.J_1907_R()), bgAlpha);
        if (J_1907_R.J_1907_R("\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439")) {
            double endAngle;
            int activeColor = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
            int inactiveColor = H_2506_c.J_1907_R(activeColor, 0.3f);
            float baseAlpha = q_3148_R.J_1907_R(K_1200_E.J_1907_R) / 255.0f * alpha;
            int bgCircleColor = H_2506_c.n_1700_B(inactiveColor, baseAlpha * 0.2f);
            F_489_x.n_1700_B(centerX, centerY, outerRadius, bgCircleColor);
            F_489_x.n_1700_B(centerX, centerY, innerRadius, bgColorValue);
            if (secondaryHpPercentage > 0.0f) {
                endAngle = startAngle + Math.PI * 2 * (double)secondaryHpPercentage;
                int secondaryColor = H_2506_c.n_1700_B(activeColor, baseAlpha * 0.5f);
                F_489_x.n_1700_B(ms, centerX, centerY, innerRadius, outerRadius, startAngle, endAngle, secondaryColor);
            }
            if (hpPercentage > 0.0f) {
                endAngle = startAngle + Math.PI * 2 * (double)hpPercentage;
                int mainColor = H_2506_c.n_1700_B(activeColor, baseAlpha);
                F_489_x.n_1700_B(ms, centerX, centerY, innerRadius, outerRadius, startAngle, endAngle, mainColor);
            }
        } else {
            double endAngle;
            int[] colors = this.n_1700_B(currentHP, maxHP);
            int bgCircleColor = H_2506_c.n_1700_B(colors[0], 0.3137255f * alpha);
            F_489_x.n_1700_B(centerX, centerY, outerRadius, bgCircleColor);
            F_489_x.n_1700_B(centerX, centerY, innerRadius, bgColorValue);
            if (secondaryHpPercentage > 0.0f) {
                endAngle = startAngle + Math.PI * 2 * (double)secondaryHpPercentage;
                int secondaryColor = H_2506_c.n_1700_B(colors[2], 0.54901963f * alpha);
                F_489_x.n_1700_B(ms, centerX, centerY, innerRadius, outerRadius, startAngle, endAngle, secondaryColor);
            }
            if (hpPercentage > 0.0f) {
                endAngle = startAngle + Math.PI * 2 * (double)hpPercentage;
                int mainColor = H_2506_c.n_1700_B(colors[4], alpha);
                F_489_x.n_1700_B(ms, centerX, centerY, innerRadius, outerRadius, startAngle, endAngle, mainColor);
            }
        }
        if (R_4764_Y.t_148_a().booleanValue() && absorption > 0.0f) {
            double endAngle;
            float animatedAbsorption = this.multiplayerClientSuggestionProvider.n_1700_B();
            float absorptionPercentage = Math.min(animatedAbsorption / maxHP, 1.0f);
            float lagAbsorption = this.w_1457_N.n_1700_B();
            float secondaryAbsorptionPercentage = Math.min(lagAbsorption / maxHP, 1.0f);
            int goldTop = H_2506_c.n_1700_B(255, 210, 0);
            if (secondaryAbsorptionPercentage > 0.0f) {
                endAngle = startAngle + Math.PI * 2 * (double)secondaryAbsorptionPercentage;
                int secondaryGoldColor = H_2506_c.n_1700_B(H_2506_c.J_1907_R(goldTop, 0.8f), alpha * 0.6f);
                F_489_x.n_1700_B(ms, centerX, centerY, innerRadius, outerRadius, startAngle, endAngle, secondaryGoldColor);
            }
            if (absorptionPercentage > 0.0f) {
                endAngle = startAngle + Math.PI * 2 * (double)absorptionPercentage;
                int mainGoldColor = H_2506_c.n_1700_B(goldTop, alpha);
                F_489_x.n_1700_B(ms, centerX, centerY, innerRadius, outerRadius, startAngle, endAngle, mainGoldColor);
            }
        }
        int textColorValue = H_2506_c.n_1700_B((int)((Integer)t_148_a.J_1907_R()), alpha);
        String healthText = String.valueOf((int)currentHP);
        float textWidth = l_3370_o.J_1907_R[14].n_1700_B(healthText);
        float textHeight = l_3370_o.J_1907_R[14].h_1847_R();
        l_3370_o.J_1907_R[15].n_1700_B(ms, healthText, (double)(centerX - textWidth / 2.1f), (double)centerY - (double)textHeight / 2.6, textColorValue);
    }

    private int[] n_1700_B(float currentHP, float maxHP) {
        if ((double)currentHP >= (double)maxHP * 0.7) {
            return new int[]{H_2506_c.n_1700_B(0, 40, 8), H_2506_c.n_1700_B(0, 80, 15), H_2506_c.n_1700_B(0, 60, 12), H_2506_c.n_1700_B(0, 160, 40), H_2506_c.n_1700_B(0, 190, 45)};
        }
        if ((double)currentHP >= (double)maxHP * 0.35) {
            return new int[]{H_2506_c.n_1700_B(50, 55, 25), H_2506_c.n_1700_B(85, 70, 50), H_2506_c.n_1700_B(55, 50, 22), H_2506_c.n_1700_B(140, 130, 60), H_2506_c.n_1700_B(160, 150, 70)};
        }
        return new int[]{H_2506_c.n_1700_B(50, 35, 25), H_2506_c.n_1700_B(70, 45, 40), H_2506_c.n_1700_B(80, 42, 32), H_2506_c.n_1700_B(160, 90, 70), H_2506_c.n_1700_B(180, 100, 75)};
    }

    private void n_1700_B(g_221_o ms, N_4263_v renderTarget, float posX, float posY, float currentHP, float absorption, int textColor, int customColor) {
        float alpha = this.M_182_A.n_1700_B();
        String nameText = D_4024_W.n_1700_B(renderTarget.O_1309_Q().getString());
        if (n_1700_B.J_1907_R("\u041a\u0440\u0443\u0433")) {
            if (nameText.length() > 10) {
                nameText = nameText.substring(0, 10);
            }
            l_3370_o.J_1907_R[18].n_1700_B(ms, nameText, posX + 37.5f, posY + 7.5f, textColor, 40.0f);
        } else {
            l_3370_o.J_1907_R[14].n_1700_B(ms, nameText, posX + 37.5f, posY + 7.5f, textColor, 47.0f);
            l_3370_o.J_1907_R[14].n_1700_B(ms, "HP: " + this.P_4830_p.format(currentHP), (double)(posX + 38.0f), (double)(posY + 18.5f), textColor);
            if (R_4764_Y.t_148_a().booleanValue() && absorption > 0.0f) {
                String hpText = "HP: " + this.P_4830_p.format(currentHP);
                String goldText = "(" + this.P_4830_p.format(absorption) + ")";
                float goldX = posX + 38.0f + l_3370_o.J_1907_R[14].n_1700_B(hpText) + 1.5f;
                l_3370_o.J_1907_R[14].n_1700_B(ms, goldText, (double)goldX, (double)(posY + 18.5f), textColor);
            }
        }
    }

    private void n_1700_B(g_221_o ms, r_4811_B livingTarget, float posX, float posY, float alpha) {
        if (alpha <= 0.0f) {
            return;
        }
        Z_1993_T mainhand = livingTarget.A_2714_y();
        Z_1993_T offhand = livingTarget.S_4035_N();
        float itemSize = 12.0f;
        float spacing = 2.0f;
        float startX = posX + 37.5f;
        float startY = posY + 20.0f;
        float bgAlpha = (float)H_2506_c.G_564_y((Integer)w_1484_f.J_1907_R()) / 255.0f * alpha;
        int bgItemColor = v_4262_N.t_148_a() != false ? H_2506_c.n_1700_B((int)((Integer)w_1484_f.J_1907_R()), 0) : H_2506_c.n_1700_B((int)((Integer)w_1484_f.J_1907_R()), bgAlpha * 0.5f);
        int outlineItemColor = (Integer)s_956_w.J_1907_R();
        float outlineItemAlpha = (float)H_2506_c.G_564_y(outlineItemColor) * alpha;
        float itemOffset = (itemSize - 8.0f) / 2.0f;
        float mainhandX = startX;
        F_489_x.n_1700_B(mainhandX, startY, itemSize, itemSize, 2.0f, bgItemColor);
        F_489_x.J_1907_R(mainhandX, startY, itemSize, itemSize, 2.0f, outlineItemColor, outlineItemAlpha);
        if (!mainhand.n_1700_B()) {
            F_489_x.n_1700_B(mainhand, mainhandX + itemOffset, startY + itemOffset, 0.5f);
        }
        float offhandX = startX + itemSize + spacing;
        F_489_x.n_1700_B(offhandX, startY, itemSize, itemSize, 2.0f, bgItemColor);
        F_489_x.J_1907_R(offhandX, startY, itemSize, itemSize, 2.0f, outlineItemColor, outlineItemAlpha);
        if (!offhand.n_1700_B()) {
            F_489_x.n_1700_B(offhand, offhandX + itemOffset, startY + itemOffset, 0.5f);
        }
    }

    @Generated
    public z_283_n(J_3635_s dragging) {
        this.M_588_G = dragging;
    }

    @Generated
    public CopyOnWriteArrayList<n_1700_B> n_1700_B() {
        return this.C_2741_M;
    }

    public static class n_1700_B {
        private e_2866_D n_1700_B;
        private e_2866_D J_1907_R;
        private final e_2866_D R_4764_Y;
        private final long G_564_y;
        private float P_1922_E;
        private final long u_1723_Y;

        public n_1700_B(e_2866_D offset) {
            this.n_1700_B = offset;
            this.J_1907_R = offset;
            this.R_4764_Y = offset.J_1907_R(-ThreadLocalRandom.current().nextFloat(-75.0f, 75.0f), -ThreadLocalRandom.current().nextFloat(-75.0f, 75.0f), -ThreadLocalRandom.current().nextFloat(-75.0f, 75.0f));
            this.G_564_y = System.currentTimeMillis();
            this.u_1723_Y = 1250L + ThreadLocalRandom.current().nextLong(750L);
        }

        public void n_1700_B(float hudX, float hudY) {
            this.P_1922_E = F_747_P.R_4764_Y(this.P_1922_E, 1.0f, 10.0f);
            this.n_1700_B = F_747_P.J_1907_R(this.n_1700_B, this.R_4764_Y, 0.75f);
            this.J_1907_R = new e_2866_D((double)hudX + this.n_1700_B.J_1907_R, (double)hudY + this.n_1700_B.R_4764_Y, this.n_1700_B.G_564_y);
        }
    }
}



