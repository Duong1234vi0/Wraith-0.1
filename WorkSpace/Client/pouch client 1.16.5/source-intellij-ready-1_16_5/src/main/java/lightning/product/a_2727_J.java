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
import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.MutableComponent;
import lightning.product.J_3635_s;
import lightning.product.K_1200_E;
import lightning.product.Module;
import lightning.product.Z_2491_A;
import lightning.product.ServerHandshakePacketListener;
import lightning.product.b_3528_u;
import lightning.product.g_221_o;
import lightning.product.h_2367_h;
import lightning.product.h_4412_P;
import lightning.product.Setting;
import lightning.product.j_1376_w;
import lightning.product.j_1654_T;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.q_3148_R;
import lombok.Generated;

public class a_2727_J
implements ServerHandshakePacketListener {
    private final J_3635_s v_4262_N;
    private final Animation w_1484_f = new Animation(0.0f, 10.0f);
    private final Animation t_148_a = new Animation(26.0f, 10.0f);
    private final Map<n_1700_B, Animation[]> s_956_w = new HashMap<n_1700_B, Animation[]>();
    public static h_2367_h n_1700_B = new h_2367_h("\u0425\u0435\u0434\u0435\u0440", true, H_2506_c.n_1700_B(30, 25, 40, 255));
    public static h_2367_h J_1907_R = new h_2367_h("\u0424\u043e\u043d", true, H_2506_c.n_1700_B(30, 25, 40, 255));
    public static h_2367_h R_4764_Y = new h_2367_h("\u0424\u043e\u043d \u0411\u0438\u043d\u0434\u0430", true, H_2506_c.n_1700_B(30, 25, 40, 255));
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
        this.w_1484_f.n_1700_B(a_2727_J.c_3005_b.Y_1740_V instanceof h_4412_P || !activeItems.isEmpty() ? 1.0f : 0.0f);
        float globalAlpha = this.w_1484_f.n_1700_B();
        int finalTextColor = H_2506_c.n_1700_B((int)((Integer)G_564_y.J_1907_R()), globalAlpha);
        ArrayList<n_1700_B> dimsItems = new ArrayList<n_1700_B>(activeItems);
        dimsItems.addAll(this.s_956_w.keySet());
        float[] dimensions = this.n_1700_B(dimsItems);
        float width = dimensions[0];
        float maxBindWidth = dimensions[1];
        float headerHeight = 15.0f;
        float itemSpacing = 11.0f;
        float targetHeight = Math.max(20.0f, 16.5f + (float)activeItems.size() * itemSpacing);
        this.t_148_a.n_1700_B(targetHeight);
        float animatedHeight = this.t_148_a.n_1700_B();
        int glow = (Integer)u_1723_Y.J_1907_R();
        float glowAlpha = (float)H_2506_c.G_564_y(glow) / 255.0f;
        F_489_x.n_1700_B(posX - 10.0f, posY - 10.0f, width + 20.0f, animatedHeight + 20.0f, 5.0f, glow, glow, glow, glow, glowAlpha * globalAlpha, 10.0f);
        int bg = (Integer)J_1907_R.J_1907_R();
        F_489_x.n_1700_B(posX, posY, width, animatedHeight, 5.0f, bg, globalAlpha);
        F_489_x.n_1700_B(posX, posY, width, 15.0f, new Z_2491_A(5.0f, 0.0f, 5.0f, 0.0f), (int)((Integer)n_1700_B.J_1907_R()), globalAlpha);
        int outline = (Integer)P_1922_E.J_1907_R();
        float outlineAlphaValue = (float)H_2506_c.G_564_y(outline) * globalAlpha;
        F_489_x.J_1907_R(posX, posY, width, animatedHeight, 5.0f, outline, outlineAlphaValue);
        int headerTextColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.w_1457_N), globalAlpha);
        MutableComponent gradientLogo = j_1376_w.n_1700_B("Hotkeys", q_3148_R.n_1700_B(K_1200_E.w_1457_N), H_2506_c.J_1907_R(q_3148_R.n_1700_B(K_1200_E.w_1457_N), 0.3f), 4, 15.0f);
        l_3370_o.J_1907_R[15].n_1700_B(ms, gradientLogo, (double)(posX + 4.5f), (double)(posY + 5.5f), headerTextColor);
        MutableComponent iconText = j_1376_w.n_1700_B("C", q_3148_R.n_1700_B(K_1200_E.w_1457_N), H_2506_c.J_1907_R(q_3148_R.n_1700_B(K_1200_E.w_1457_N), 0.3f), 7, 15.0f);
        l_3370_o.u_1723_Y[16].n_1700_B(ms, iconText, (double)(posX + width - l_3370_o.u_1723_Y[16].n_1700_B("C") - 4.5f), (double)(posY + 6.5f), headerTextColor);
        float baseItemY = posY + 15.0f;
        ArrayList<n_1700_B> toRemove = new ArrayList<n_1700_B>();
        for (int i = 0; i < activeItems.size(); ++i) {
            n_1700_B item = activeItems.get(i);
            int finalI = i;
            anims = this.s_956_w.computeIfAbsent(item, k -> new Animation[]{new Animation(0.0f, 10.0f), new Animation(-5.0f, 10.0f), new Animation(finalI * 11, 10.0f)});
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
        toRemove.forEach(this.s_956_w::remove);
        for (Map.Entry<n_1700_B, Animation[]> entry : this.s_956_w.entrySet()) {
            n_1700_B item = entry.getKey();
            anims = entry.getValue();
            float itemAlpha = globalAlpha * anims[0].n_1700_B();
            if (itemAlpha <= 0.0f && anims[0].R_4764_Y()) continue;
            float itemY = baseItemY + anims[2].n_1700_B();
            String nameText = item.n_1700_B();
            String bindKey = j_1654_T.n_1700_B(item.J_1907_R());
            float nameWidth = l_3370_o.J_1907_R[12].n_1700_B(nameText);
            float bindWidth = l_3370_o.J_1907_R[12].n_1700_B(bindKey);
            float separatorX = Math.max(nameWidth, width - maxBindWidth - 18.5f) + 5.5f;
            float bindX = separatorX + 4.0f + (maxBindWidth - bindWidth) / 2.0f;
            int animatedRectColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.Q_4569_t), q_3148_R.J_1907_R(K_1200_E.Q_4569_t) / 255.0f * itemAlpha);
            int animatedTextColor = H_2506_c.n_1700_B(H_2506_c.n_1700_B(255, 255, 255, 255), itemAlpha);
            float xOffset = anims[1].n_1700_B();
            F_489_x.n_1700_B(posX + width - bindWidth - 7.5f + xOffset, itemY, bindWidth + 6.0f, 11.0f, 2.0f, (int)((Integer)R_4764_Y.J_1907_R()), itemAlpha);
            F_489_x.J_1907_R(posX + width - bindWidth - 7.5f + xOffset, itemY, bindWidth + 6.0f, 11.0f, 2.0f, outline, outlineAlphaValue);
            l_3370_o.J_1907_R[12].n_1700_B(ms, nameText, (double)(posX + 4.5f + xOffset), (double)(itemY + 4.5f), animatedTextColor);
            l_3370_o.J_1907_R[12].n_1700_B(ms, bindKey, (double)(posX + width - bindWidth - 4.5f + xOffset), (double)(itemY + 4.5f), animatedTextColor);
        }
        this.v_4262_N.G_564_y(headerHeight + (float)activeItems.size() * itemSpacing);
        this.v_4262_N.R_4764_Y(width);
    }

    private List<n_1700_B> n_1700_B() {
        ArrayList<n_1700_B> activeItems = new ArrayList<n_1700_B>();
        for (Module module : ClientBootstrap.Y_601_j().J_1907_R().u_1723_Y()) {
            if (module.v_4262_N() != -100 && module.w_1484_f() && module.s_956_w()) {
                activeItems.add(new n_1700_B(module.G_564_y(), module.v_4262_N()));
            }
            for (Setting<?> setting : module.u_2550_I()) {
                BooleanSetting booleanSetting;
                if (!(setting instanceof BooleanSetting) || (booleanSetting = (BooleanSetting)setting).u_2550_I() == -100 || !booleanSetting.t_148_a().booleanValue() || !booleanSetting.P_4830_p()) continue;
                activeItems.add(new n_1700_B(booleanSetting.n_1700_B(), booleanSetting.u_2550_I()));
            }
        }
        return activeItems;
    }

    private float[] n_1700_B(List<n_1700_B> activeItems) {
        int fontSize = 13;
        float maxNameWidth = 0.0f;
        float maxBindWidth = 0.0f;
        for (n_1700_B item : activeItems) {
            maxNameWidth = Math.max(maxNameWidth, l_3370_o.G_564_y[fontSize].n_1700_B(item.n_1700_B()));
            float bindWidth = l_3370_o.P_1922_E[fontSize].n_1700_B(j_1654_T.n_1700_B(item.J_1907_R()));
            maxBindWidth = Math.max(maxBindWidth, bindWidth);
        }
        float width = Math.max(60.0f, maxNameWidth + maxBindWidth + 18.0f + 8.5f);
        return new float[]{width, maxBindWidth};
    }

    @Generated
    public a_2727_J(J_3635_s dragging) {
        this.v_4262_N = dragging;
    }

    private static class n_1700_B {
        private final String n_1700_B;
        private final int J_1907_R;

        public n_1700_B(String name, int bind) {
            this.n_1700_B = name;
            this.J_1907_R = bind;
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
        public String n_1700_B() {
            return this.n_1700_B;
        }

        @Generated
        public int J_1907_R() {
            return this.J_1907_R;
        }
    }
}



