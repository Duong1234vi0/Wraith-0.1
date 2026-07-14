/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Vector2f
 */
package lightning.product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import lightning.product.D_4024_W;
import lightning.product.E_4612_l;
import lightning.product.F_3104_Z;
import lightning.product.F_489_x;
import lightning.product.F_747_P;
import lightning.product.G_1539_D;
import lightning.product.H_2506_c;
import lightning.product.H_2671_n;
import lightning.product.I_4817_s;
import lightning.product.K_1289_S;
import lightning.product.K_1310_v;
import lightning.product.K_4096_w;
import lightning.product.N_4263_v;
import lightning.product.N_4463_r;
import lightning.product.U_2474_c;
import lightning.product.U_2871_b;
import lightning.product.V_3137_a;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.Z_2491_A;
import lightning.product.Z_361_l;
import lightning.product.Z_3822_q;
import lightning.product.a_2725_z;
import lightning.product.a_3913_L;
import lightning.product.b_3528_u;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.k_1320_C;
import lightning.product.k_2610_C;
import lightning.product.l_3370_o;
import lightning.product.n_1494_c;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.q_1874_T;
import lightning.product.q_3115_L;
import lightning.product.r_4811_B;
import lightning.product.t_1509_b;
import lightning.product.u_530_F;
import lightning.product.v_2826_q;
import lightning.product.x_282_a;
import lightning.product.y_2603_k;
import mods.voicechat.voice.client.ClientManager;
import mods.voicechat.voice.client.ClientPlayerStateManager;
import mods.voicechat.voice.client.ClientVoicechat;
import org.joml.Vector2f;

public class q_2753_Q
extends X_3546_T {
    private static final Map<String, String> M_182_A = q_2753_Q.h_1847_R();
    public N_4463_r v_4262_N = new N_4463_r("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c", new p_1977_n("\u0418\u0433\u0440\u043e\u043a\u043e\u0432", true), new p_1977_n("\u041c\u043e\u043d\u0441\u0442\u0440\u043e\u0432", false), new p_1977_n("\u0414\u0440\u0443\u0437\u0435\u0439", false), new p_1977_n("\u0416\u0438\u0432\u043e\u0442\u043d\u044b\u0445", false), new p_1977_n("\u0421\u0435\u0431\u044f", false), new p_1977_n("\u0416\u0438\u0442\u0435\u043b\u0435\u0439", false), new p_1977_n("\u0413\u043e\u043b\u044b\u0445", false), new p_1977_n("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b", true));
    public p_1977_n w_1484_f = new p_1977_n("\u0411\u043b\u044e\u0440 \u0444\u043e\u043d", false);
    public p_1977_n t_148_a = new p_1977_n("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u0437\u0435\u043b\u044c\u044f", false);
    public p_1977_n s_956_w = new p_1977_n("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u0431\u0440\u043e\u043d\u044e", false);
    public p_1977_n u_2550_I = new p_1977_n("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u0437\u0430\u0447\u0430\u0440\u043e\u0432\u0430\u043d\u0438\u044f", false);
    public p_1977_n M_588_G = new p_1977_n("\u041e\u0431\u0445\u043e\u0434 HW", true, q_3115_L::u_1723_Y);
    public p_1977_n P_4830_p = new p_1977_n("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u044b \u0432 \u0440\u0443\u043a\u0430\u0445", false, () -> !q_3115_L.u_1723_Y());
    public p_1977_n h_1847_R = new p_1977_n("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u0432\u0438\u0437\u0443\u0430\u043b\u044c\u043d\u043e\u0435 \u0438\u043c\u044f \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430", false, () -> this.P_4830_p.t_148_a());
    public p_1977_n Q_4569_t = new p_1977_n("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u0441\u0442\u0430\u0442\u0443\u0441 VoiceChat", false);
    private static final double t_1786_h = 1.0E8;

    public q_2753_Q() {
        super("Tags", y_2603_k.R_4764_Y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G, this.P_4830_p, this.h_1847_R, this.Q_4569_t);
    }

    @Y_1740_V
    public void n_1700_B(b_3528_u event) {
        if (q_2753_Q.c_3005_b.Y_601_j == null) {
            return;
        }
        float partialTicks = c_3005_b.P_2565_J();
        Z_3822_q font = l_3370_o.J_1907_R[14];
        Z_3822_q smallFont = l_3370_o.J_1907_R[12];
        Z_3822_q tinyFont = l_3370_o.J_1907_R[11];
        Z_3822_q enchantFont = l_3370_o.J_1907_R[10];
        int bgColor = H_2506_c.n_1700_B(0, 0, 0, 120);
        g_221_o matrix = event.J_1907_R();
        matrix.n_1700_B();
        for (N_4263_v entity : q_2753_Q.c_3005_b.Y_601_j.J_1907_R()) {
            a_3913_L pl;
            r_4811_B living;
            Z_2491_A bounds;
            if (!v_2826_q.n_1700_B(entity) || (bounds = this.n_1700_B(entity, partialTicks)) == null) continue;
            if (entity instanceof n_1494_c) {
                n_1494_c itemEntity = (n_1494_c)entity;
                if (E_4612_l.R_4764_Y(entity, this.v_4262_N)) {
                    this.n_1700_B(event, itemEntity, bounds, font, bgColor);
                    continue;
                }
            }
            if (!(entity instanceof r_4811_B) || !(living = (r_4811_B)entity).H_3699_F() || !this.n_1700_B(living) && (!(living instanceof a_3913_L) || (pl = (a_3913_L)living) == q_2753_Q.c_3005_b.Y_259_p || !G_1539_D.R_4764_Y(pl.y_4642_Y().getName()))) continue;
            this.n_1700_B(event, living, bounds, font, smallFont, tinyFont, enchantFont, bgColor);
        }
        this.n_1700_B(event, font, bgColor);
        matrix.J_1907_R();
    }

    private boolean n_1700_B(r_4811_B entity) {
        return E_4612_l.J_1907_R((N_4263_v)entity, this.v_4262_N) || E_4612_l.n_1700_B(entity, this.v_4262_N, true) || E_4612_l.n_1700_B(entity, this.v_4262_N) || E_4612_l.J_1907_R(entity, this.v_4262_N) || E_4612_l.G_564_y(entity, this.v_4262_N);
    }

    private Z_2491_A n_1700_B(N_4263_v entity, float partialTicks) {
        e_2866_D interpolated = F_747_P.n_1700_B(entity, partialTicks);
        I_4817_s aabb = v_2826_q.n_1700_B(entity, interpolated);
        e_2866_D[] corners = v_2826_q.n_1700_B(aabb);
        Vector2f min = null;
        Vector2f max = null;
        for (e_2866_D corner : corners) {
            Vector2f projected = v_2826_q.n_1700_B(corner);
            if (projected.x == Float.MAX_VALUE || projected.y == Float.MAX_VALUE) continue;
            if (min == null) {
                min = new Vector2f(projected.x, projected.y);
                max = new Vector2f(projected.x, projected.y);
                continue;
            }
            min.x = Math.min(min.x, projected.x);
            min.y = Math.min(min.y, projected.y);
            max.x = Math.max(max.x, projected.x);
            max.y = Math.max(max.y, projected.y);
        }
        if (max == null) {
            return null;
        }
        return new Z_2491_A(min.x, min.y, max.x, max.y);
    }

    private void n_1700_B(b_3528_u event, n_1494_c itemEntity, Z_2491_A bounds, Z_3822_q font, int bgColor) {
        x_282_a nameComp;
        Z_1993_T stack = itemEntity.P_1922_E();
        t_1509_b.J_1907_R info = t_1509_b.n_1700_B(stack);
        boolean useVisualName = this.h_1847_R.t_148_a() != false && !this.Q_4569_t();
        int textColor = -1;
        if (useVisualName) {
            nameComp = stack.N_4405_n();
        } else if (info != null) {
            nameComp = new U_2871_b(info.n_1700_B(stack));
            textColor = info.J_1907_R(stack);
        } else {
            nameComp = stack.J_1907_R().w_1484_f(stack);
        }
        if (this.Q_4569_t()) {
            nameComp = new U_2871_b(q_2753_Q.R_4764_Y(nameComp.getString()));
        }
        int count = stack.t_4043_B();
        q_1874_T rarity = stack.Q_2552_b();
        Object countSuffix = count > 1 ? " x" + count : "";
        H_2671_n label = new U_2871_b("").n_1700_B(nameComp);
        if (info == null) {
            label.n_1700_B(rarity.P_1922_E);
        }
        if (count > 1) {
            label.n_1700_B(new U_2871_b((String)countSuffix));
        }
        String labelPlain = nameComp.getString() + (String)countSuffix;
        float textWidth = font.n_1700_B(labelPlain);
        float centerX = (bounds.n_1700_B() + bounds.R_4764_Y()) / 2.0f;
        float rectHeight = 10.0f;
        float tagY = bounds.J_1907_R() - font.h_1847_R() - 3.0f;
        this.n_1700_B(event, centerX - textWidth / 2.0f - 2.0f, tagY - 2.0f, textWidth + 4.0f, rectHeight, 2.0f, bgColor);
        font.n_1700_B(event.J_1907_R(), label, (double)(centerX - textWidth / 2.0f), (double)(tagY + 1.0f), textColor);
    }

    /*
     * Unable to fully structure code
     */
    private void n_1700_B(b_3528_u event, r_4811_B entity, Z_2491_A bounds, Z_3822_q font, Z_3822_q smallFont, Z_3822_q tinyFont, Z_3822_q enchantFont, int bgColor) {
        nameComponent = entity.c_();
        if (!(entity instanceof a_3913_L)) ** GOTO lbl-1000
        player = (a_3913_L)entity;
        if (o_148_s.Y_601_j().v_4262_N().R_4764_Y(player.y_4642_Y().getName())) {
            v0 = true;
        } else lbl-1000:
        // 2 sources

        {
            v0 = false;
        }
        isFriend = v0;
        nameText = nameComponent.getString();
        cleanedName = k_1320_C.n_1700_B(nameText, isFriend);
        if (entity instanceof a_3913_L) {
            playerEnt = (a_3913_L)entity;
            cleanedName = k_1320_C.n_1700_B(cleanedName, playerEnt.y_4642_Y().getName());
        }
        if (!nameText.equals(cleanedName)) {
            nameComponent = new U_2871_b(cleanedName).n_1700_B(nameComponent.n_1700_B());
        }
        plainName = D_4024_W.n_1700_B(nameComponent.getString());
        globals = a_2725_z.h_1847_R();
        globalsEnabled = globals != null && globals.w_1484_f() != false;
        useScoreboard = o_148_s.Y_601_j().J_1907_R().n_1700_B(F_3104_Z.class).w_1484_f();
        hp = useScoreboard != false ? q_3115_L.n_1700_B(entity) : entity.g_46_E();
        healthValue = Math.max(0, Math.round(hp));
        hpText = String.valueOf(healthValue);
        centerX = (bounds.n_1700_B() + bounds.R_4764_Y()) / 2.0f;
        rectHeight = 12.0f;
        tagY = bounds.J_1907_R() - rectHeight;
        baseFriendColor = H_2506_c.n_1700_B(0, 100, 0, 180);
        v1 = rectColor = isFriend != false ? baseFriendColor : bgColor;
        if (globalsEnabled && entity instanceof a_3913_L) {
            player = (a_3913_L)entity;
            globalColor = (Integer)globals.s_956_w.J_1907_R();
            if (entity == q_2753_Q.c_3005_b.Y_259_p && globals.w_1484_f.t_148_a().booleanValue()) {
                rectColor = globalColor;
            } else if (entity != q_2753_Q.c_3005_b.Y_259_p && globals.n_1700_B(player)) {
                rectColor = globalColor;
            }
        }
        gap = 1.0f;
        paddingX = 3.0f;
        nameWidth = font.n_1700_B(plainName);
        hpWidth = font.n_1700_B(hpText);
        nameRectWidth = nameWidth + paddingX * 2.0f;
        hpRectWidth = hpWidth + paddingX * 2.0f;
        totalWidth = nameRectWidth + gap + hpRectWidth;
        currentX = centerX - totalWidth / 2.0f;
        textY = tagY + (rectHeight - font.h_1847_R()) / 2.0f + 0.5f;
        this.n_1700_B(event, currentX, tagY, nameRectWidth, rectHeight, 2.0f, rectColor);
        font.n_1700_B(event.J_1907_R(), nameComponent, (double)(currentX + paddingX), (double)textY, -1);
        this.n_1700_B(event, currentX += nameRectWidth + gap, tagY, hpRectWidth, rectHeight, 2.0f, rectColor);
        font.n_1700_B(event.J_1907_R(), hpText, (double)(currentX + paddingX), (double)textY, H_2506_c.n_1700_B(255, 90, 90, 255));
        if (this.Q_4569_t.t_148_a().booleanValue() && entity instanceof a_3913_L) {
            player2 = (a_3913_L)entity;
            this.n_1700_B(event, player2, centerX, tagY, totalWidth, rectHeight);
        }
        if (this.s_956_w.t_148_a().booleanValue()) {
            this.n_1700_B(event.J_1907_R(), event, entity, centerX, tagY, rectColor, enchantFont);
        }
        effectsBaseY = bounds.G_564_y() + 6.0f;
        if (this.Q_4569_t() || this.P_4830_p.t_148_a().booleanValue()) {
            effectsBaseY = this.n_1700_B(event, entity, centerX, effectsBaseY, rectColor, smallFont);
        }
        if (this.t_148_a.t_148_a().booleanValue()) {
            this.n_1700_B(event, entity, bounds.R_4764_Y(), bounds.J_1907_R(), tinyFont, rectColor);
        }
        if (globalsEnabled && globals.t_148_a.t_148_a().booleanValue() && entity instanceof a_3913_L) {
            player = (a_3913_L)entity;
            if (entity != q_2753_Q.c_3005_b.Y_259_p && (globalInfo = globals.J_1907_R(player)) != null) {
                x = (int)Math.floor(globalInfo.R_4764_Y);
                y = (int)Math.floor(globalInfo.G_564_y);
                z = (int)Math.floor(globalInfo.P_1922_E);
                coordsText = "X: " + x + " Y: " + y + " Z: " + z;
                textWidth = smallFont.n_1700_B(coordsText);
                coordsY = effectsBaseY + 4.0f;
                coordsX = centerX - textWidth / 2.0f;
                coordsBgColor = (Integer)globals.s_956_w.J_1907_R();
                this.n_1700_B(event, coordsX - 2.0f, coordsY - 4.0f, textWidth + 4.0f, 10.0f, 2.0f, coordsBgColor);
                smallFont.n_1700_B(event.J_1907_R(), coordsText, (double)coordsX, (double)(coordsY - 0.5f), -1);
            }
        }
    }

    private void n_1700_B(g_221_o matrixStack, b_3528_u event, r_4811_B entity, float centerX, float baseY, int rectColor, Z_3822_q enchantFont) {
        ArrayList armor = new ArrayList();
        entity.u_55_V().forEach(armor::add);
        Z_1993_T mainHand = entity.A_2714_y();
        Z_1993_T offHand = entity.S_4035_N();
        float y = baseY - 12.0f;
        float itemSize = 8.0f;
        float gap = 1.5f;
        float padding = 2.0f;
        int itemCount = 0;
        for (int i = 3; i >= 0; --i) {
            if (((Z_1993_T)armor.get(i)).n_1700_B()) continue;
            ++itemCount;
        }
        if (!mainHand.n_1700_B()) {
            ++itemCount;
        }
        if (!offHand.n_1700_B()) {
            ++itemCount;
        }
        if (itemCount == 0) {
            return;
        }
        float totalItemsWidth = (float)itemCount * itemSize + (float)(itemCount - 1) * gap;
        float rectWidth = totalItemsWidth + padding * 2.0f;
        float rectX = centerX - rectWidth / 2.0f;
        float rowHeight = itemSize + padding * 2.0f + 4.0f;
        this.n_1700_B(event, rectX, y - padding, rectWidth, rowHeight - 2.5f, 2.0f, rectColor);
        float offsetX = -totalItemsWidth / 2.0f;
        for (int i = 0; i < 6; ++i) {
            List<String> enchantments;
            Z_1993_T stack;
            Z_1993_T z_1993_T = i == 0 ? offHand : (stack = i == 5 ? mainHand : (Z_1993_T)armor.get(4 - i));
            if (stack.n_1700_B()) continue;
            if (this.u_2550_I.t_148_a().booleanValue() && !(enchantments = this.n_1700_B(stack)).isEmpty()) {
                float enchantY = y - padding - 4.0f;
                for (String enchantment : enchantments) {
                    String plainText = D_4024_W.n_1700_B(enchantment);
                    float enchantWidth = enchantFont.n_1700_B(plainText);
                    enchantFont.n_1700_B(matrixStack, plainText, (double)(centerX + offsetX + itemSize / 2.0f - enchantWidth / 2.0f), (double)enchantY, -1);
                    enchantY -= 6.0f;
                }
            }
            F_489_x.n_1700_B(stack, centerX + offsetX, y, 0.5f);
            if (stack.P_1922_E() && stack.w_1484_f() > 0) {
                int damage = stack.v_4262_N();
                int maxDamage = stack.w_1484_f();
                float durabilityPercent = Math.max(0.0f, (float)(maxDamage - damage) / (float)maxDamage);
                float barWidth = itemSize;
                float barHeight = 1.0f;
                float barX = centerX + offsetX;
                float barY = y + itemSize + 0.5f;
                int barColor = this.n_1700_B(durabilityPercent);
                F_489_x.n_1700_B(matrixStack, barX, barY, barWidth, barHeight, H_2506_c.n_1700_B(0, 0, 0, 255));
                float fillWidth = barWidth * durabilityPercent;
                if (fillWidth > 0.0f) {
                    F_489_x.n_1700_B(matrixStack, barX, barY, fillWidth, barHeight, barColor);
                }
            }
            offsetX += itemSize + gap;
        }
    }

    private int n_1700_B(float durabilityPercent) {
        float hue = durabilityPercent / 3.0f;
        return u_530_F.u_1723_Y(hue, 1.0f, 1.0f) | 0xFF000000;
    }

    private float n_1700_B(b_3528_u event, r_4811_B entity, float centerX, float startY, int rectColor, Z_3822_q font) {
        Z_1993_T off;
        float lineHeight = 10.0f;
        float y = startY;
        Z_1993_T main = entity.A_2714_y();
        if (!main.n_1700_B()) {
            x_282_a comp = this.h_1847_R.t_148_a() != false ? main.N_4405_n() : main.J_1907_R().w_1484_f(main);
            String text = comp.getString();
            float w = font.n_1700_B(text);
            float x = centerX - w / 2.0f;
            this.n_1700_B(event, x - 2.0f, y - 4.0f, w + 4.0f, 10.0f, 2.0f, rectColor);
            font.n_1700_B(event.J_1907_R(), comp, (double)x, (double)(y - 0.5f), -1);
            y += lineHeight;
        }
        if (!(off = entity.S_4035_N()).n_1700_B()) {
            x_282_a comp;
            x_282_a x_282_a2 = comp = this.h_1847_R.t_148_a() != false ? off.N_4405_n() : off.J_1907_R().w_1484_f(off);
            if (entity instanceof a_3913_L) {
                t_1509_b.J_1907_R info;
                a_3913_L player = (a_3913_L)entity;
                if (this.Q_4569_t()) {
                    String boostName = t_1509_b.n_1700_B(player);
                    if (!"EMPTY".equals(boostName)) {
                        comp = new U_2871_b(q_2753_Q.R_4764_Y(boostName));
                    } else {
                        t_1509_b.J_1907_R info2 = t_1509_b.n_1700_B(off);
                        if (info2 != null) {
                            comp = new U_2871_b(q_2753_Q.R_4764_Y(info2.n_1700_B(off)));
                        }
                    }
                } else if (!this.h_1847_R.t_148_a().booleanValue() && (info = t_1509_b.n_1700_B(off)) != null) {
                    comp = new U_2871_b(info.n_1700_B(off));
                }
            }
            if (this.Q_4569_t()) {
                comp = new U_2871_b(q_2753_Q.R_4764_Y(comp.getString()));
            }
            String text = comp.getString();
            float w = font.n_1700_B(text);
            float x = centerX - w / 2.0f;
            this.n_1700_B(event, x - 2.0f, y - 3.0f, w + 4.0f, 10.0f, 2.0f, rectColor);
            font.n_1700_B(event.J_1907_R(), comp, (double)x, (double)(y + 1.0f), -1);
            y += lineHeight;
        }
        return y;
    }

    private static Map<String, String> h_1847_R() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("SPEED_1", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c 1");
        map.put("SPEED_2", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c 2");
        map.put("SPEED_3", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c 3");
        map.put("MYTHIC_DAMAGE", "\u0421\u0444\u0435\u0440\u0430 \u043d\u0430 \u0443\u0440\u043e\u043d");
        map.put("MYTHIC SPEED", "\u0421\u0444\u0435\u0440\u0430 \u043d\u0430 \u0441\u043a\u043e\u0440\u043e\u0441\u0442\u044c");
        map.put("MYTHIC_SPEED", "\u0421\u0444\u0435\u0440\u0430 \u043d\u0430 \u0441\u043a\u043e\u0440\u043e\u0441\u0442\u044c");
        map.put("MITHYC DAMAGE", "\u0421\u0444\u0435\u0440\u0430 \u043d\u0430 \u0443\u0440\u043e\u043d");
        map.put("MITHYC_DAMAGE", "\u0421\u0444\u0435\u0440\u0430 \u043d\u0430 \u0443\u0440\u043e\u043d");
        map.put("ARMORTALITY", "\u0410\u0440\u043c\u043e\u0440\u0442\u0430\u043b\u0438\u0442\u0438");
        map.put("IMMORTALITY", "\u0418\u043c\u043c\u043e\u0440\u0442\u0430\u043b\u0438\u0442\u0438");
        map.put("CERBERUS", "\u0426\u0435\u0440\u0431\u0435\u0440");
        map.put("FLESH", "\u0424\u043b\u0435\u0448\u0430");
        map.put("LEGENDARY", "\u041b\u0435\u0433\u0435\u043d\u0434\u0430\u0440\u043d\u0430\u044f \u0441\u0444\u0435\u0440\u0430");
        map.put("SATYR", "\u0421\u0430\u0442\u0438\u0440");
        map.put("INFINITY", "\u0418\u043d\u0444\u0438\u043d\u0438\u0442\u0438");
        map.put("STINGER", "\u0421\u0442\u0438\u043d\u0433\u0435\u0440\u0430");
        map.put("ETERNITY", "\u042d\u0442\u0435\u0440\u043d\u0438\u0442\u0438");
        return map;
    }

    private static String R_4764_Y(String rawName) {
        if (rawName == null || rawName.isEmpty()) {
            return rawName;
        }
        String trimmed = rawName.trim();
        String direct = M_182_A.get(trimmed);
        if (direct != null) {
            return direct;
        }
        String normalized = trimmed.toUpperCase(Locale.ROOT).replace('-', '_');
        String byNormalized = M_182_A.get(normalized);
        if (byNormalized != null) {
            return byNormalized;
        }
        return trimmed;
    }

    private boolean Q_4569_t() {
        return q_3115_L.u_1723_Y() && this.M_588_G.t_148_a() != false;
    }

    private List<String> n_1700_B(Z_1993_T stack) {
        ArrayList<String> enchantments = new ArrayList<String>();
        Map<K_1310_v, Integer> enchantmentMap = K_4096_w.n_1700_B(stack);
        for (Map.Entry<K_1310_v, Integer> entry : enchantmentMap.entrySet()) {
            String shortName;
            K_1310_v enchantment = entry.getKey();
            String enchantName = K_1289_S.n_1700_B(enchantment.v_4262_N(), new Object[0]);
            String string = shortName = enchantName.length() >= 2 ? enchantName.substring(0, 2) : enchantName;
            if (enchantment.n_1700_B() == 1 && entry.getValue() == 1) {
                enchantments.add(String.valueOf((Object)D_4024_W.M_182_A) + shortName);
                continue;
            }
            enchantments.add(String.valueOf((Object)D_4024_W.M_182_A) + shortName + String.valueOf(entry.getValue()));
        }
        return enchantments;
    }

    private void n_1700_B(b_3528_u event, r_4811_B entity, float rightEdge, float topY, Z_3822_q font, int rectColor) {
        Collection<k_2610_C> effects = entity.I_3457_f();
        if (effects.isEmpty()) {
            return;
        }
        ArrayList<n_1700_B> lines = new ArrayList<n_1700_B>();
        for (k_2610_C effect : effects) {
            if (effect.J_1907_R() <= 20) continue;
            String key = V_3137_a.T_2506_i.J_1907_R(effect.n_1700_B()).J_1907_R();
            D_4024_W tf = Z_361_l.n_1700_B(key);
            Integer rgb = tf.G_564_y();
            int color = rgb != null ? 0xFF000000 | rgb : -1;
            String name = effect.n_1700_B().G_564_y().getString();
            int amp = effect.R_4764_Y() + 1;
            StringBuilder sb = new StringBuilder(name);
            if (amp > 1) {
                sb.append(' ').append(U_2474_c.n_1700_B(amp));
            }
            sb.append(' ').append(Z_361_l.n_1700_B(effect.J_1907_R()));
            String text = sb.toString();
            lines.add(new n_1700_B(text, font.n_1700_B(text), color));
        }
        if (lines.isEmpty()) {
            return;
        }
        lines.sort(Comparator.comparingDouble(e -> e.J_1907_R));
        float lineStep = font.h_1847_R() + 5.0f;
        float startY = topY + 13.0f;
        for (int i = 0; i < lines.size(); ++i) {
            n_1700_B line = (n_1700_B)lines.get(i);
            float padding = 2.0f;
            float bgHeight = font.h_1847_R() + padding * 2.0f;
            float bgY = startY + (float)i * lineStep;
            float bgX = rightEdge + 5.0f;
            float bgWidth = line.J_1907_R + padding * 3.0f;
            float textX = bgX + padding * 1.5f;
            float textY = bgY + padding + 1.0f;
            this.n_1700_B(event, bgX, bgY, bgWidth, bgHeight, 2.0f, rectColor);
            F_489_x.n_1700_B(event.J_1907_R(), bgX, bgY, 2.0f, bgHeight, line.R_4764_Y);
            font.n_1700_B(event.J_1907_R(), line.n_1700_B, (double)textX, (double)textY, line.R_4764_Y);
        }
    }

    private void n_1700_B(b_3528_u event, Z_3822_q font, int bgColor) {
        if (!G_1539_D.n_1700_B.P_1922_E() || !G_1539_D.v_4262_N()) {
            return;
        }
        if (q_2753_Q.c_3005_b.Y_259_p == null || q_2753_Q.c_3005_b.Y_601_j == null) {
            return;
        }
        String selfMc = q_2753_Q.c_3005_b.Y_259_p.y_4642_Y().getName();
        if (selfMc == null) {
            return;
        }
        HashSet<String> loadedMcLower = new HashSet<String>();
        for (a_3913_L a_3913_L2 : q_2753_Q.c_3005_b.Y_601_j.N_4405_n()) {
            String n;
            if (a_3913_L2 == null || a_3913_L2.y_4642_Y() == null || (n = a_3913_L2.y_4642_Y().getName()) == null) continue;
            loadedMcLower.add(n.toLowerCase(Locale.ROOT));
        }
        float rectHeight = 12.0f;
        float f = 1.0f;
        float paddingX = 3.0f;
        int rectColor = bgColor;
        String rightText = "IRC";
        for (G_1539_D.n_1700_B rm : G_1539_D.w_1484_f()) {
            if (rm == null || rm.n_1700_B == null || !rm.J_1907_R || rm.n_1700_B.equalsIgnoreCase(selfMc) || loadedMcLower.contains(rm.n_1700_B.toLowerCase(Locale.ROOT)) || q_2753_Q.c_3005_b.Y_259_p.v_4262_N(rm.R_4764_Y, rm.G_564_y, rm.P_1922_E) > 1.0E8) continue;
            e_2866_D head = new e_2866_D(rm.R_4764_Y, rm.G_564_y + 2.2, rm.P_1922_E);
            Vector2f screen = v_2826_q.n_1700_B(head);
            if (screen.x == Float.MAX_VALUE || screen.y == Float.MAX_VALUE) continue;
            String plainName = D_4024_W.n_1700_B(rm.n_1700_B);
            if (plainName == null || plainName.isEmpty()) {
                plainName = rm.n_1700_B;
            }
            float centerX = screen.x;
            float tagY = screen.y - rectHeight;
            float nameWidth = font.n_1700_B(plainName);
            float rightW = font.n_1700_B(rightText);
            float nameRectWidth = nameWidth + paddingX * 2.0f;
            float hpRectWidth = rightW + paddingX * 2.0f;
            float totalWidth = nameRectWidth + f + hpRectWidth;
            float currentX = centerX - totalWidth / 2.0f;
            float textY = tagY + (rectHeight - font.h_1847_R()) / 2.0f + 0.5f;
            this.n_1700_B(event, currentX, tagY, nameRectWidth, rectHeight, 2.0f, rectColor);
            font.n_1700_B(event.J_1907_R(), plainName, (double)(currentX + paddingX), (double)textY, -1);
            this.n_1700_B(event, currentX += nameRectWidth + f, tagY, hpRectWidth, rectHeight, 2.0f, rectColor);
            font.n_1700_B(event.J_1907_R(), rightText, (double)(currentX + paddingX), (double)textY, H_2506_c.n_1700_B(255, 90, 90, 255));
        }
    }

    private void n_1700_B(b_3528_u event, a_3913_L player, float centerX, float tagY, float totalWidth, float rectHeight) {
        boolean isSpeaking;
        ClientVoicechat client = ClientManager.getClient();
        if (client == null) {
            return;
        }
        ClientPlayerStateManager manager = ClientManager.getPlayerStateManager();
        if (manager.isPlayerDisconnected(player)) {
            return;
        }
        boolean bl = isSpeaking = client.getTalkCache().isWhispering(player) || client.getTalkCache().isTalking(player);
        int color = manager.isPlayerDisabled(player) ? H_2506_c.n_1700_B("#550000") : (isSpeaking ? H_2506_c.n_1700_B("#00FF00") : H_2506_c.n_1700_B("#FF0000"));
        float rectX = centerX - totalWidth / 2.0f;
        F_489_x.n_1700_B(event.J_1907_R(), rectX - 2.0f, tagY, 1.5f, rectHeight, color);
        F_489_x.n_1700_B(event.J_1907_R(), rectX + totalWidth + 0.5f, tagY, 1.5f, rectHeight, color);
    }

    private void n_1700_B(b_3528_u event, float x, float y, float width, float height, float radius, int color) {
        if (this.w_1484_f.t_148_a().booleanValue()) {
            F_489_x.n_1700_B(event.J_1907_R(), x, y, width, height, radius, H_2506_c.n_1700_B(80, 80, 80, 100), 1.0f);
        } else {
            F_489_x.n_1700_B(event.J_1907_R(), x, y, width, height, radius, color);
        }
    }

    private static class n_1700_B {
        final String n_1700_B;
        final float J_1907_R;
        final int R_4764_Y;

        n_1700_B(String text, float width, int color) {
            this.n_1700_B = text;
            this.J_1907_R = width;
            this.R_4764_Y = color;
        }
    }
}

