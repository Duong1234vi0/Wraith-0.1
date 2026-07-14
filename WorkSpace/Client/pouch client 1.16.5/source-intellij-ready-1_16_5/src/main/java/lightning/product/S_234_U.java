/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.MutableComponent;
import lightning.product.J_3635_s;
import lightning.product.K_1200_E;
import lightning.product.Z_1993_T;
import lightning.product.Z_2491_A;
import lightning.product.ServerHandshakePacketListener;
import lightning.product.b_3528_u;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.h_2367_h;
import lightning.product.h_4412_P;
import lightning.product.j_1376_w;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.q_1613_l;
import lightning.product.q_3148_R;
import lombok.Generated;

public class S_234_U
implements ServerHandshakePacketListener {
    private static final float u_1723_Y = 9.0f;
    private static final float v_4262_N = 3.0f;
    private static final float w_1484_f = 2.5f;
    private static final float t_148_a = 4.5f;
    private final J_3635_s s_956_w;
    private final Animation u_2550_I = new Animation(0.0f, 10.0f);
    private final Animation M_588_G = new Animation(26.0f, 10.0f);
    private final Map<q_1613_l, Animation[]> P_4830_p = new HashMap<q_1613_l, Animation[]>();
    private final Map<q_1613_l, String> h_1847_R = new HashMap<q_1613_l, String>();
    private final Map<q_1613_l, Float> Q_4569_t = new HashMap<q_1613_l, Float>();
    public static h_2367_h n_1700_B = new h_2367_h("\u0425\u0435\u0434\u0435\u0440", true, H_2506_c.n_1700_B(30, 25, 40, 255));
    public static h_2367_h J_1907_R = new h_2367_h("\u0424\u043e\u043d", true, H_2506_c.n_1700_B(30, 25, 40, 255));
    public static h_2367_h R_4764_Y = new h_2367_h("\u0422\u0435\u043a\u0441\u0442", true, H_2506_c.n_1700_B(180, 140, 255, 255));
    public static h_2367_h G_564_y = new h_2367_h("\u041e\u0431\u0432\u043e\u0434\u043a\u0430", true, H_2506_c.n_1700_B(120, 80, 160, 255));
    public static h_2367_h P_1922_E = new h_2367_h("\u0421\u0432\u0435\u0447\u0435\u043d\u0438\u0435", true, H_2506_c.n_1700_B(120, 80, 160, 100));

    @Override
    public void n_1700_B(b_3528_u event) {
        Animation[] anims;
        g_221_o ms = event.J_1907_R();
        float posX = this.s_956_w.J_1907_R();
        float posY = this.s_956_w.R_4764_Y();
        List<q_1613_l> activeItems = this.n_1700_B();
        this.u_2550_I.n_1700_B(S_234_U.c_3005_b.Y_1740_V instanceof h_4412_P || !activeItems.isEmpty() ? 1.0f : 0.0f);
        float globalAlpha = this.u_2550_I.n_1700_B();
        HashMap<q_1613_l, Float> itemCooldowns = new HashMap<q_1613_l, Float>();
        for (int i = 0; i < S_234_U.c_3005_b.Y_259_p.l_1268_F.Y_259_p(); ++i) {
            Z_1993_T itemStack = S_234_U.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (itemStack.n_1700_B()) continue;
            q_1613_l item2 = itemStack.J_1907_R();
            float cooldownSeconds = S_234_U.c_3005_b.Y_259_p.p_1458_L().J_1907_R(item2, c_3005_b.RealmsClientConfig());
            if (!(cooldownSeconds > 0.0f)) continue;
            itemCooldowns.put(item2, Float.valueOf(cooldownSeconds));
            this.h_1847_R.put(item2, this.n_1700_B(cooldownSeconds));
            this.Q_4569_t.put(item2, Float.valueOf(cooldownSeconds));
        }
        ArrayList<q_1613_l> dimsItems = new ArrayList<q_1613_l>(activeItems);
        dimsItems.addAll(this.P_4830_p.keySet());
        float[] dimensions = this.n_1700_B(dimsItems, itemCooldowns);
        float width = dimensions[0];
        float headerHeight = 15.0f;
        float itemSpacing = 11.0f;
        float targetHeight = Math.max(20.0f, 16.5f + (float)activeItems.size() * itemSpacing);
        this.M_588_G.n_1700_B(targetHeight);
        float animatedHeight = this.M_588_G.n_1700_B();
        int glow = (Integer)P_1922_E.J_1907_R();
        float glowAlpha = (float)H_2506_c.G_564_y(glow) / 255.0f;
        F_489_x.n_1700_B(posX - 10.0f, posY - 10.0f, width + 20.0f, animatedHeight + 20.0f, 5.0f, glow, glow, glow, glow, glowAlpha * globalAlpha, 10.0f);
        F_489_x.n_1700_B(posX, posY, width, animatedHeight, 5.0f, (int)((Integer)J_1907_R.J_1907_R()), globalAlpha);
        F_489_x.n_1700_B(posX, posY, width, 15.0f, new Z_2491_A(5.0f, 0.0f, 5.0f, 0.0f), (int)((Integer)n_1700_B.J_1907_R()), globalAlpha);
        int outline = (Integer)G_564_y.J_1907_R();
        float outlineAlphaValue = (float)H_2506_c.G_564_y(outline) * globalAlpha;
        F_489_x.J_1907_R(posX, posY, width, animatedHeight, 5.0f, outline, outlineAlphaValue);
        int headerTextColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.w_1457_N), globalAlpha);
        MutableComponent gradientTitle = j_1376_w.n_1700_B("Cooldowns", q_3148_R.n_1700_B(K_1200_E.w_1457_N), H_2506_c.J_1907_R(q_3148_R.n_1700_B(K_1200_E.w_1457_N), 0.3f), 7, 15.0f);
        l_3370_o.J_1907_R[15].n_1700_B(ms, gradientTitle, (double)(posX + 4.5f), (double)(posY + 5.5f), headerTextColor);
        MutableComponent iconText = j_1376_w.n_1700_B("T", q_3148_R.n_1700_B(K_1200_E.w_1457_N), H_2506_c.J_1907_R(q_3148_R.n_1700_B(K_1200_E.w_1457_N), 0.3f), 7, 15.0f);
        l_3370_o.u_1723_Y[16].n_1700_B(ms, iconText, (double)(posX + width - l_3370_o.u_1723_Y[16].n_1700_B("T") - 4.5f), (double)(posY + 6.5f), headerTextColor);
        float baseItemY = posY + 15.0f;
        ArrayList<q_1613_l> toRemove = new ArrayList<q_1613_l>();
        for (int i = 0; i < activeItems.size(); ++i) {
            q_1613_l item3 = activeItems.get(i);
            int finalI = i;
            anims = this.P_4830_p.computeIfAbsent(item3, k -> new Animation[]{new Animation(0.0f, 10.0f), new Animation(-5.0f, 10.0f), new Animation(finalI * 11, 10.0f)});
            anims[0].n_1700_B(1.0f);
            anims[1].n_1700_B(0.0f);
            float targetY = (float)i * itemSpacing;
            anims[2].n_1700_B(targetY);
        }
        for (Map.Entry<q_1613_l, Animation[]> entry : this.P_4830_p.entrySet()) {
            if (activeItems.contains(entry.getKey())) continue;
            Animation[] anims2 = entry.getValue();
            anims2[0].n_1700_B(0.0f);
            anims2[1].n_1700_B(-5.0f);
            if (!anims2[0].R_4764_Y() || !anims2[1].R_4764_Y()) continue;
            toRemove.add(entry.getKey());
        }
        toRemove.forEach(item -> {
            this.P_4830_p.remove(item);
            this.h_1847_R.remove(item);
            this.Q_4569_t.remove(item);
        });
        for (Map.Entry<q_1613_l, Animation[]> entry : this.P_4830_p.entrySet()) {
            q_1613_l item4 = entry.getKey();
            anims = entry.getValue();
            float itemAlpha = globalAlpha * anims[0].n_1700_B();
            if (itemAlpha <= 0.0f && anims[0].R_4764_Y()) continue;
            float itemY = baseItemY + anims[2].n_1700_B();
            float cooldown = itemCooldowns.getOrDefault(item4, Float.valueOf(0.0f)).floatValue();
            Z_1993_T stack = new Z_1993_T(item4);
            String nameText = stack.multiplayerClientSuggestionProvider().getString();
            String cooldownText = itemCooldowns.containsKey(item4) ? this.n_1700_B(cooldown) : this.h_1847_R.getOrDefault(item4, "");
            float xOffset = anims[1].n_1700_B();
            float a = Math.max(0.0f, Math.min(1.0f, itemAlpha));
            int animatedTextColor = H_2506_c.n_1700_B(H_2506_c.n_1700_B(255, 255, 255, 255), a);
            float scale = 0.5625f;
            c_4037_x.Y_601_j();
            c_4037_x.s_2632_s();
            c_4037_x.t_1786_h();
            c_4037_x.v_4276_D();
            c_4037_x.J_1907_R(scale, scale, 1.0f);
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, a);
            int itemGuiX = Math.round((posX + 2.5f + xOffset) / scale);
            int itemGuiY = Math.round((itemY + 1.0f) / scale);
            c_3005_b.r_715_M().n_1700_B(stack, itemGuiX, itemGuiY);
            c_3005_b.r_715_M().n_1700_B(S_234_U.c_3005_b.t_148_a, stack, itemGuiX, itemGuiY);
            c_4037_x.d_2461_k();
            c_4037_x.multiplayerClientSuggestionProvider();
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            float nameStartX = posX + 2.5f + 9.0f + 3.0f + xOffset;
            l_3370_o.J_1907_R[12].n_1700_B(ms, nameText, (double)nameStartX, (double)(itemY + 4.5f), animatedTextColor);
            l_3370_o.J_1907_R[12].n_1700_B(ms, cooldownText, (double)(posX + width - 2.5f - l_3370_o.J_1907_R[12].n_1700_B(cooldownText) + xOffset), (double)(itemY + 4.5f), animatedTextColor);
        }
        this.s_956_w.G_564_y(headerHeight + (float)activeItems.size() * itemSpacing);
        this.s_956_w.R_4764_Y(width);
    }

    private List<q_1613_l> n_1700_B() {
        LinkedHashSet<q_1613_l> activeSet = new LinkedHashSet<q_1613_l>();
        for (int i = 0; i < S_234_U.c_3005_b.Y_259_p.l_1268_F.Y_259_p(); ++i) {
            Z_1993_T itemStack = S_234_U.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (itemStack.n_1700_B()) continue;
            q_1613_l item = itemStack.J_1907_R();
            float cooldown = S_234_U.c_3005_b.Y_259_p.p_1458_L().J_1907_R(item, c_3005_b.RealmsClientConfig());
            if (!(cooldown > 0.0f)) continue;
            activeSet.add(item);
        }
        return new ArrayList<q_1613_l>(activeSet);
    }

    private float[] n_1700_B(List<q_1613_l> dimsItems, Map<q_1613_l, Float> itemCooldowns) {
        int fontSize = 13;
        float iconColumn = 12.0f;
        float maxNameWidth = 0.0f;
        float maxCooldownWidth = 0.0f;
        for (q_1613_l item : dimsItems) {
            maxNameWidth = Math.max(maxNameWidth, l_3370_o.G_564_y[fontSize].n_1700_B(new Z_1993_T(item).multiplayerClientSuggestionProvider().getString()));
            String cooldownStr = itemCooldowns.containsKey(item) ? this.n_1700_B(itemCooldowns.get(item).floatValue()) : this.h_1847_R.getOrDefault(item, "");
            maxCooldownWidth = Math.max(maxCooldownWidth, l_3370_o.P_1922_E[fontSize].n_1700_B(cooldownStr));
        }
        float totalContentWidth = iconColumn + maxNameWidth + maxCooldownWidth;
        float width = Math.max(60.0f, totalContentWidth + 18.0f + 8.5f);
        return new float[]{width};
    }

    private String n_1700_B(float cooldown) {
        int minutes = (int)(cooldown / 60.0f);
        int seconds = (int)(cooldown % 60.0f);
        StringBuilder sb = new StringBuilder();
        if (minutes > 0) {
            sb.append(minutes).append("\u043c");
        }
        if (seconds > 0 || minutes == 0) {
            sb.append(seconds).append("\u0441");
        }
        return sb.toString();
    }

    @Generated
    public S_234_U(J_3635_s dragging) {
        this.s_956_w = dragging;
    }
}


