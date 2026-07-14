/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lightning.product.B_3871_I;
import lightning.product.C_2701_A;
import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.MutableComponent;
import lightning.product.J_3635_s;
import lightning.product.K_1200_E;
import lightning.product.K_1289_S;
import lightning.product.MobEffectUtil;
import lightning.product.Z_2491_A;
import lightning.product.ServerHandshakePacketListener;
import lightning.product.b_3528_u;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_422_i;
import lightning.product.h_2367_h;
import lightning.product.h_4412_P;
import lightning.product.j_1376_w;
import lightning.product.Animation;
import lightning.product.j_956_y;
import lightning.product.k_2610_C;
import lightning.product.l_3370_o;
import lightning.product.BooleanSetting;
import lightning.product.q_3148_R;
import lombok.Generated;

public class Y_3623_f
implements ServerHandshakePacketListener {
    private final J_3635_s v_4262_N;
    private final Animation w_1484_f = new Animation(0.0f, 10.0f);
    private final Animation t_148_a = new Animation(22.0f, 10.0f);
    private final Map<n_1700_B, Animation[]> s_956_w = new HashMap<n_1700_B, Animation[]>();
    private final Map<n_1700_B, String> u_2550_I = new HashMap<n_1700_B, String>();
    private final Map<n_1700_B, Integer> M_588_G = new HashMap<n_1700_B, Integer>();
    private static final int P_4830_p = 72000;
    private static final float h_1847_R = 9.0f;
    private static final float Q_4569_t = 3.0f;
    private static final float M_182_A = 4.5f;
    private static final float t_1786_h = 2.5f;
    public static BooleanSetting n_1700_B = new BooleanSetting("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u043f\u043b\u043e\u0445\u0438\u0435 \u044d\u0444\u0444\u0435\u043a\u0442\u044b", true);
    public static h_2367_h J_1907_R = new h_2367_h("\u0425\u0435\u0434\u0435\u0440", true, H_2506_c.n_1700_B(30, 25, 40, 255));
    public static h_2367_h R_4764_Y = new h_2367_h("\u0424\u043e\u043d", true, H_2506_c.n_1700_B(30, 25, 40, 255));
    public static h_2367_h G_564_y = new h_2367_h("\u0422\u0435\u043a\u0441\u0442", true, H_2506_c.n_1700_B(180, 140, 255, 255));
    public static h_2367_h P_1922_E = new h_2367_h("\u041e\u0431\u0432\u043e\u0434\u043a\u0430", true, H_2506_c.n_1700_B(120, 80, 160, 255));
    public static h_2367_h u_1723_Y = new h_2367_h("\u0421\u0432\u0435\u0447\u0435\u043d\u0438\u0435", true, H_2506_c.n_1700_B(120, 80, 160, 100));

    @Override
    public void n_1700_B(b_3528_u event) {
        Animation[] anims;
        g_221_o ms = event.J_1907_R();
        float posX = this.v_4262_N.J_1907_R();
        float posY = this.v_4262_N.R_4764_Y();
        List<n_1700_B> activeItems = this.n_1700_B();
        this.w_1484_f.n_1700_B(Y_3623_f.c_3005_b.Y_1740_V instanceof h_4412_P || !activeItems.isEmpty() ? 1.0f : 0.0f);
        float globalAlpha = this.w_1484_f.n_1700_B();
        HashMap<n_1700_B, k_2610_C> effectInstances = new HashMap<n_1700_B, k_2610_C>();
        for (k_2610_C instance : Y_3623_f.c_3005_b.Y_259_p.I_3457_f()) {
            n_1700_B item2 = new n_1700_B(instance.n_1700_B(), instance.R_4764_Y());
            effectInstances.put(item2, instance);
            String durText = instance.J_1907_R() > 72000 ? "**:**" : MobEffectUtil.n_1700_B(instance, 1.0f);
            this.u_2550_I.put(item2, durText);
            this.M_588_G.put(item2, instance.J_1907_R());
        }
        ArrayList<n_1700_B> dimsItems = new ArrayList<n_1700_B>(activeItems);
        dimsItems.addAll(this.s_956_w.keySet());
        float[] dimensions = this.n_1700_B(dimsItems, effectInstances);
        float width = dimensions[0];
        float headerHeight = 15.0f;
        float itemSpacing = 11.0f;
        float targetHeight = Math.max(20.0f, 16.5f + (float)activeItems.size() * itemSpacing);
        this.t_148_a.n_1700_B(targetHeight);
        float animatedHeight = this.t_148_a.n_1700_B();
        int glow = (Integer)u_1723_Y.J_1907_R();
        float glowAlpha = (float)H_2506_c.G_564_y(glow) / 255.0f;
        F_489_x.n_1700_B(posX - 10.0f, posY - 10.0f, width + 20.0f, animatedHeight + 20.0f, 5.0f, glow, glow, glow, glow, glowAlpha * globalAlpha, 10.0f);
        F_489_x.n_1700_B(posX, posY, width, animatedHeight, 5.0f, (int)((Integer)R_4764_Y.J_1907_R()), globalAlpha);
        F_489_x.n_1700_B(posX, posY, width, 15.0f, new Z_2491_A(5.0f, 0.0f, 5.0f, 0.0f), (int)((Integer)J_1907_R.J_1907_R()), globalAlpha);
        int outline = (Integer)P_1922_E.J_1907_R();
        float outlineAlphaValue = (float)H_2506_c.G_564_y(outline) * globalAlpha;
        F_489_x.J_1907_R(posX, posY, width, animatedHeight, 5.0f, outline, outlineAlphaValue);
        int headerTextColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.w_1457_N), globalAlpha);
        MutableComponent gradientTitle = j_1376_w.n_1700_B("Potions", q_3148_R.n_1700_B(K_1200_E.w_1457_N), H_2506_c.J_1907_R(q_3148_R.n_1700_B(K_1200_E.w_1457_N), 0.3f), 7, 15.0f);
        l_3370_o.J_1907_R[15].n_1700_B(ms, gradientTitle, (double)(posX + 4.5f), (double)(posY + 5.5f), headerTextColor);
        MutableComponent iconText = j_1376_w.n_1700_B("E", q_3148_R.n_1700_B(K_1200_E.w_1457_N), H_2506_c.J_1907_R(q_3148_R.n_1700_B(K_1200_E.w_1457_N), 0.3f), 7, 15.0f);
        l_3370_o.u_1723_Y[16].n_1700_B(ms, iconText, (double)(posX + width - l_3370_o.u_1723_Y[16].n_1700_B("E") - 4.5f), (double)(posY + 6.5f), headerTextColor);
        float baseItemY = posY + 15.0f;
        ArrayList<n_1700_B> toRemove = new ArrayList<n_1700_B>();
        for (int i = 0; i < activeItems.size(); ++i) {
            n_1700_B item3 = activeItems.get(i);
            int finalI = i;
            anims = this.s_956_w.computeIfAbsent(item3, k -> new Animation[]{new Animation(0.0f, 10.0f), new Animation(-5.0f, 10.0f), new Animation((float)finalI * itemSpacing, 10.0f)});
            anims[0].n_1700_B(1.0f);
            anims[1].n_1700_B(0.0f);
            float targetY = (float)i * itemSpacing;
            anims[2].n_1700_B(targetY);
        }
        for (Map.Entry<n_1700_B, Animation[]> entry : this.s_956_w.entrySet()) {
            if (activeItems.contains(entry.getKey())) continue;
            Animation[] anims2 = entry.getValue();
            anims2[0].n_1700_B(0.0f);
            anims2[1].n_1700_B(-5.0f);
            if (!anims2[0].R_4764_Y() || !anims2[1].R_4764_Y()) continue;
            toRemove.add(entry.getKey());
        }
        toRemove.forEach(item -> {
            this.s_956_w.remove(item);
            this.u_2550_I.remove(item);
            this.M_588_G.remove(item);
        });
        for (Map.Entry<n_1700_B, Animation[]> entry : this.s_956_w.entrySet()) {
            int durationTicks;
            n_1700_B item4 = entry.getKey();
            anims = entry.getValue();
            float itemAlpha = globalAlpha * anims[0].n_1700_B();
            if (itemAlpha <= 0.01f && anims[0].R_4764_Y()) continue;
            float itemY = baseItemY + anims[2].n_1700_B();
            k_2610_C instance = (k_2610_C)effectInstances.get(item4);
            String nameText = K_1289_S.n_1700_B(item4.n_1700_B.R_4764_Y(), new Object[0]);
            int n = durationTicks = instance != null ? instance.J_1907_R() : this.M_588_G.getOrDefault(item4, 0).intValue();
            String durationText = durationTicks > 72000 ? "**:**" : (instance != null ? MobEffectUtil.n_1700_B(instance, 1.0f) : this.u_2550_I.getOrDefault(item4, ""));
            boolean isLowDuration = durationTicks > 0 && durationTicks <= 120;
            float pulseValue = isLowDuration ? 0.6f + 0.4f * (float)Math.sin((double)System.currentTimeMillis() / 150.0) : 1.0f;
            boolean isHarmful = item4.n_1700_B.P_1922_E() == j_956_y.J_1907_R;
            int baseTextRgb = isHarmful ? H_2506_c.n_1700_B(255, 85, 85, 255) : H_2506_c.n_1700_B(255, 255, 255, 255);
            String levelText = item4.R_4764_Y() > 0 ? String.valueOf(item4.R_4764_Y() + 1) : "";
            int animatedTextColor = H_2506_c.n_1700_B(baseTextRgb, itemAlpha * pulseValue);
            float xOffset = anims[1].n_1700_B();
            float nameStartX = posX + 2.5f + 9.0f + 3.0f + xOffset;
            B_3871_I potionSprite = c_3005_b.V_1446_Y().n_1700_B(item4.n_1700_B);
            c_3005_b.G_624_v().n_1700_B(potionSprite.u_2550_I().R_4764_Y());
            c_4037_x.Y_601_j();
            c_4037_x.s_2632_s();
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, itemAlpha * pulseValue);
            C_2701_A.blit(ms, Math.round(posX + 2.5f + xOffset), Math.round(itemY + 1.0f), 0, 9, 9, potionSprite);
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            l_3370_o.J_1907_R[12].n_1700_B(ms, nameText, (double)nameStartX, (double)(itemY + 4.5f), animatedTextColor);
            if (!levelText.isEmpty()) {
                l_3370_o.J_1907_R[12].n_1700_B(ms, levelText, (double)(nameStartX + l_3370_o.J_1907_R[12].n_1700_B(nameText) + 3.0f), (double)(itemY + 4.5f), animatedTextColor);
            }
            l_3370_o.J_1907_R[12].n_1700_B(ms, durationText, (double)(posX + width - 2.5f - l_3370_o.J_1907_R[12].n_1700_B(durationText) + xOffset), (double)(itemY + 4.5f), animatedTextColor);
        }
        this.v_4262_N.G_564_y(headerHeight + (float)activeItems.size() * itemSpacing);
        this.v_4262_N.R_4764_Y(width);
    }

    private List<n_1700_B> n_1700_B() {
        ArrayList<n_1700_B> activeItems = new ArrayList<n_1700_B>();
        for (k_2610_C instance : Y_3623_f.c_3005_b.Y_259_p.I_3457_f()) {
            g_422_i effect = instance.n_1700_B();
            if (!n_1700_B.t_148_a().booleanValue() && effect.P_1922_E() == j_956_y.J_1907_R) continue;
            activeItems.add(new n_1700_B(effect, instance.R_4764_Y()));
        }
        return activeItems;
    }

    private float[] n_1700_B(List<n_1700_B> dimsItems, Map<n_1700_B, k_2610_C> effectInstances) {
        int fontSize = 13;
        float maxNameWidth = 0.0f;
        float maxLevelWidth = 0.0f;
        float maxDurationWidth = 0.0f;
        float iconColumn = 12.0f;
        for (n_1700_B item : dimsItems) {
            String baseName = K_1289_S.n_1700_B(item.n_1700_B.R_4764_Y(), new Object[0]);
            maxNameWidth = Math.max(maxNameWidth, l_3370_o.G_564_y[fontSize].n_1700_B(baseName));
            String levelText = item.R_4764_Y() > 0 ? String.valueOf(item.R_4764_Y() + 1) : "";
            maxLevelWidth = Math.max(maxLevelWidth, levelText.isEmpty() ? 0.0f : l_3370_o.P_1922_E[fontSize].n_1700_B(levelText));
            String duration = effectInstances.containsKey(item) ? (effectInstances.get(item).J_1907_R() > 72000 ? "**:**" : MobEffectUtil.n_1700_B(effectInstances.get(item), 1.0f)) : (this.M_588_G.getOrDefault(item, 0) > 72000 ? "**:**" : this.u_2550_I.getOrDefault(item, ""));
            maxDurationWidth = Math.max(maxDurationWidth, l_3370_o.P_1922_E[fontSize].n_1700_B(duration));
        }
        float totalContentWidth = iconColumn + maxNameWidth + (maxLevelWidth > 0.0f ? maxLevelWidth + 3.0f : 0.0f) + maxDurationWidth;
        float width = Math.max(60.0f, totalContentWidth + 18.0f + 8.5f);
        return new float[]{width};
    }

    @Generated
    public Y_3623_f(J_3635_s dragging) {
        this.v_4262_N = dragging;
    }

    private static class n_1700_B {
        private final g_422_i n_1700_B;
        private final int J_1907_R;

        public n_1700_B(g_422_i effect, int amplifier) {
            this.n_1700_B = effect;
            this.J_1907_R = amplifier;
        }

        public String n_1700_B() {
            return K_1289_S.n_1700_B(this.n_1700_B.R_4764_Y(), new Object[0]) + (String)(this.J_1907_R > 0 ? " " + (this.J_1907_R + 1) : "");
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            n_1700_B that = (n_1700_B)o;
            return this.J_1907_R == that.J_1907_R && this.n_1700_B.equals(that.n_1700_B);
        }

        public int hashCode() {
            return 31 * this.n_1700_B.hashCode() + this.J_1907_R;
        }

        @Generated
        public g_422_i J_1907_R() {
            return this.n_1700_B;
        }

        @Generated
        public int R_4764_Y() {
            return this.J_1907_R;
        }
    }
}


