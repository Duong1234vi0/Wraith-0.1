/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  lombok.Generated
 */
package lightning.product;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lightning.product.C_1577_A;
import lightning.product.C_332_W;
import lightning.product.F_489_x;
import lightning.product.F_747_P;
import lightning.product.H_1952_g;
import lightning.product.H_2506_c;
import lightning.product.K_1200_E;
import lightning.product.M_2029_A;
import lightning.product.O_3016_i;
import lightning.product.S_4088_D;
import lightning.product.V_1176_p;
import lightning.product.W_1488_x;
import lightning.product.Z_2491_A;
import lightning.product.MinecraftClient;
import lightning.product.f_887_Z;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.h_2367_h;
import lightning.product.Easing;
import lightning.product.i_4833_u;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.l_4397_i;
import lightning.product.n_4915_F;
import lightning.product.ClientBootstrap;
import lightning.product.u_530_F;
import lightning.product.v_1900_v;
import lombok.Generated;

public class q_3148_R
extends H_1952_g {
    private static q_3148_R M_588_G;
    public static final EnumMap<K_1200_E, h_2367_h> u_1723_Y;
    public static List<O_3016_i> v_4262_N;
    public static List<l_4397_i> w_1484_f;
    private final List<f_887_Z> P_4830_p = new ArrayList<f_887_Z>();
    private final Map<String, int[]> h_1847_R = new LinkedHashMap<String, int[]>();
    private final LinkedHashMap<String, int[]> Q_4569_t = new LinkedHashMap();
    private final Map<String, String> M_182_A = new HashMap<String, String>();
    private final V_1176_p t_1786_h = new V_1176_p();
    private final float multiplayerClientSuggestionProvider = 4.5f;
    private final float w_1457_N = 1.0f;
    private J_1907_R Y_601_j = null;
    private n_1700_B Y_259_p = null;
    private n_1700_B Q_2552_b = null;
    private final C_1577_A C_2741_M;
    float t_148_a;
    float s_956_w;
    private final Animation k_2293_S = new Animation(0.0f, 10.0f, Easing.Y_601_j);
    private final Animation q_2307_F = new Animation(0.0f, 6.0f);
    private final Animation Z_875_P = new Animation(0.0f, 12.0f);
    private final Map<String, Animation> c_3005_b = new HashMap<String, Animation>();
    float u_2550_I = 0.0f;
    private float H_2857_Y = 0.0f;
    private String A_4115_X = null;
    private String Y_1740_V = null;
    private float t_4043_B = 0.0f;
    private float x_607_J = 0.0f;
    private float e_4240_b = 0.0f;
    private float n_3318_d = 0.0f;
    private float d_2427_y = 0.0f;
    private final Animation z_1737_N = new Animation(0.0f, 10.0f, Easing.Y_601_j);
    private final Map<String, JsonObject> v_4276_D = new HashMap<String, JsonObject>();

    public q_3148_R() {
        M_588_G = this;
        this.C_2741_M = new C_1577_A(Collections.singletonList(this));
        this.q_4610_l();
        this.X_933_l();
        this.Z_976_R();
    }

    public static q_3148_R P_4830_p() {
        return M_588_G;
    }

    public static void n_1700_B(String presetName, int[] colors, String author) {
        if (M_588_G != null) {
            q_3148_R.M_588_G.Q_4569_t.put(presetName, colors);
            if (author != null) {
                q_3148_R.M_588_G.M_182_A.put(presetName, author);
            }
        }
    }

    public static void n_1700_B(String presetName, int[] colors) {
        if (M_588_G != null) {
            M_588_G.n_1700_B(colors, presetName, true);
        }
    }

    public void h_1847_R() {
        this.Q_4569_t.clear();
        this.X_933_l();
    }

    private void q_4610_l() {
        O_3016_i nameSetting = new O_3016_i("\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435");
        v_4262_N.add(nameSetting);
        w_1484_f.add(new l_4397_i(nameSetting));
        for (Object setting : K_1200_E.values()) {
            u_1723_Y.put((K_1200_E)((Object)setting), new h_2367_h(setting.n_1700_B(), true, 0));
        }
        for (h_2367_h colorSetting : u_1723_Y.values()) {
            colorSetting.n_1700_B(this::z_4693_k);
        }
        this.g_221_o();
        for (Object setting : K_1200_E.values()) {
            this.P_4830_p.add(new f_887_Z(u_1723_Y.get(setting)));
        }
    }

    private void z_4693_k() {
        if (this.Y_259_p != null && this.Y_259_p.G_564_y()) {
            int[] updatedColors = new int[u_1723_Y.size()];
            K_1200_E[] settings = K_1200_E.values();
            for (int i = 0; i < settings.length; ++i) {
                updatedColors[i] = (Integer)u_1723_Y.get((Object)settings[i]).J_1907_R();
            }
            String creator = S_4088_D.n_1700_B();
            String cleanPresetName = this.Y_259_p.n_1700_B().replace("\u0418\u043c\u044f: ", "");
            this.Y_259_p = new n_1700_B(this.Y_259_p.n_1700_B(), updatedColors, creator, true);
            this.Q_4569_t.put(this.Y_259_p.n_1700_B(), updatedColors);
            JsonObject elementColors = ClientBootstrap.Y_601_j().M_182_A().Q_4569_t();
            this.t_1786_h.n_1700_B(cleanPresetName, this.Y_259_p, creator, elementColors);
        }
    }

    public static int n_1700_B(K_1200_E setting) {
        h_2367_h colorSetting = u_1723_Y.get((Object)setting);
        if (colorSetting == null) {
            return H_2506_c.n_1700_B(255, 255, 255, 255);
        }
        return (Integer)colorSetting.J_1907_R();
    }

    public static float J_1907_R(K_1200_E setting) {
        h_2367_h colorSetting = u_1723_Y.get((Object)setting);
        if (colorSetting == null) {
            return 255.0f;
        }
        return colorSetting.w_1484_f();
    }

    private void g_221_o() {
        this.h_1847_R.clear();
        this.h_1847_R.putAll(M_2029_A.n_1700_B());
        String presetToUse = "\u0418\u043c\u044f: \u0422\u0435\u043c\u043d\u0430\u044f";
        int[] colors = this.h_1847_R.get(presetToUse);
        if (colors == null && !this.h_1847_R.isEmpty()) {
            Map.Entry<String, int[]> first = this.h_1847_R.entrySet().iterator().next();
            presetToUse = first.getKey();
            colors = first.getValue();
        }
        boolean isCustom = false;
        this.Y_259_p = new n_1700_B(presetToUse, colors, "Unknown", isCustom);
        n_4915_F.v_4262_N(presetToUse);
        K_1200_E[] settings = K_1200_E.values();
        for (int i = 0; i < Math.min(colors.length, settings.length); ++i) {
            u_1723_Y.get((Object)settings[i]).n_1700_B(colors[i]);
        }
        JsonObject elementColors = M_2029_A.J_1907_R().get(presetToUse);
        if (elementColors != null) {
            ClientBootstrap.Y_601_j().M_182_A().J_1907_R(elementColors);
        }
    }

    private void J_1907_R(g_221_o stack, float mouseX, float mouseY, float panelAlpha) {
        float presetAreaY = this.J_1907_R + this.P_1922_E - 26.0f;
        float presetAreaHeight = 14.0f;
        float startX = this.n_1700_B + 6.0f;
        this.z_1737_N.n_1700_B(this.n_3318_d);
        this.d_2427_y = this.z_1737_N.n_1700_B();
        int totalPresets = this.h_1847_R.size() + this.Q_4569_t.size();
        float circleSize = 10.0f;
        float totalPresetsWidth = (float)totalPresets * circleSize + 4.0f;
        float availableWidth = this.v_4262_N() - 12.0f;
        float maxPresetScroll = Math.max(0.0f, totalPresetsWidth - availableWidth);
        this.n_3318_d = u_530_F.n_1700_B(this.n_3318_d, -maxPresetScroll, 0.0f);
        this.d_2427_y = u_530_F.n_1700_B(this.d_2427_y, -maxPresetScroll, 0.0f);
        i_4833_u.n_1700_B(this.n_1700_B + 4.0f, presetAreaY - 2.0f, this.v_4262_N() - 8.0f, presetAreaHeight + 4.0f);
        this.A_4115_X = null;
        int index = 0;
        index = this.n_1700_B(stack, mouseX, mouseY, this.h_1847_R, index, startX + this.d_2427_y, presetAreaY, panelAlpha);
        this.n_1700_B(stack, mouseX, mouseY, this.Q_4569_t, index, startX + this.d_2427_y, presetAreaY, panelAlpha);
        i_4833_u.n_1700_B();
        if (this.A_4115_X != null && this.e_4240_b > 0.0f) {
            float nameWidth = l_3370_o.R_4764_Y[12].n_1700_B(this.A_4115_X);
            float authorWidth = l_3370_o.R_4764_Y[12].n_1700_B("\u0410\u0432\u0442\u043e\u0440: " + this.Y_1740_V);
            float textWidth = Math.max(nameWidth, authorWidth);
            F_489_x.n_1700_B(this.t_4043_B - 4.0f, this.x_607_J - 4.0f, textWidth + 8.0f, 17.0f, 3.0f, q_3148_R.n_1700_B(K_1200_E.Y_259_p), this.e_4240_b);
            int textColorWithAlpha = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.R_4764_Y), this.e_4240_b);
            l_3370_o.R_4764_Y[12].n_1700_B(stack, this.A_4115_X, (double)this.t_4043_B, (double)this.x_607_J, textColorWithAlpha);
            l_3370_o.R_4764_Y[12].n_1700_B(stack, "\u0410\u0432\u0442\u043e\u0440: " + this.Y_1740_V, (double)this.t_4043_B, (double)(this.x_607_J + 7.0f), textColorWithAlpha);
        }
        this.R_4764_Y(stack, mouseX, mouseY, panelAlpha);
    }

    private void R_4764_Y(g_221_o stack, float mouseX, float mouseY, float panelAlpha) {
        float buttonY = this.J_1907_R + this.P_1922_E - 17.0f;
        float buttonWidth = (this.v_4262_N() - 18.0f) / 2.0f;
        float buttonHeight = 12.0f;
        float button1X = this.n_1700_B + 6.0f;
        float button2X = this.n_1700_B + 12.0f + buttonWidth;
        boolean hovered1 = F_747_P.n_1700_B(mouseX, mouseY, button1X, buttonY, buttonWidth, buttonHeight);
        boolean hovered2 = F_747_P.n_1700_B(mouseX, mouseY, button2X, buttonY, buttonWidth, buttonHeight);
        int importBg = hovered1 ? q_3148_R.n_1700_B(K_1200_E.M_588_G) : q_3148_R.n_1700_B(K_1200_E.P_4830_p);
        int importBgWithAlpha = H_2506_c.n_1700_B(importBg, (float)H_2506_c.G_564_y(importBg) / 255.0f * panelAlpha);
        F_489_x.n_1700_B(button1X, buttonY, buttonWidth, buttonHeight, 2.0f, importBgWithAlpha);
        F_489_x.J_1907_R(button1X, buttonY, buttonWidth, buttonHeight, 2.5f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * panelAlpha);
        int importTextColor = hovered1 ? q_3148_R.n_1700_B(K_1200_E.R_4764_Y) : q_3148_R.n_1700_B(K_1200_E.G_564_y);
        int importTextColorWithAlpha = H_2506_c.n_1700_B(importTextColor, (float)H_2506_c.G_564_y(importTextColor) / 255.0f * panelAlpha);
        float importTextX = button1X + (buttonWidth - l_3370_o.R_4764_Y[12].n_1700_B("\u0418\u043c\u043f\u043e\u0440\u0442")) / 2.0f;
        l_3370_o.R_4764_Y[12].n_1700_B(stack, "\u0418\u043c\u043f\u043e\u0440\u0442", (double)importTextX, (double)(buttonY + 5.0f), importTextColorWithAlpha);
        int exportBg = hovered2 ? q_3148_R.n_1700_B(K_1200_E.M_588_G) : q_3148_R.n_1700_B(K_1200_E.P_4830_p);
        int exportBgWithAlpha = H_2506_c.n_1700_B(exportBg, (float)H_2506_c.G_564_y(exportBg) / 255.0f * panelAlpha);
        F_489_x.n_1700_B(button2X, buttonY, buttonWidth, buttonHeight, 2.0f, exportBgWithAlpha);
        F_489_x.J_1907_R(button2X, buttonY, buttonWidth, buttonHeight, 2.5f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * panelAlpha);
        int exportTextColor = hovered2 ? q_3148_R.n_1700_B(K_1200_E.R_4764_Y) : q_3148_R.n_1700_B(K_1200_E.G_564_y);
        int exportTextColorWithAlpha = H_2506_c.n_1700_B(exportTextColor, (float)H_2506_c.G_564_y(exportTextColor) / 255.0f * panelAlpha);
        float exportTextX = button2X + (buttonWidth - l_3370_o.R_4764_Y[12].n_1700_B("\u042d\u043a\u0441\u043f\u043e\u0440\u0442")) / 2.0f;
        l_3370_o.R_4764_Y[12].n_1700_B(stack, "\u042d\u043a\u0441\u043f\u043e\u0440\u0442", (double)exportTextX, (double)(buttonY + 5.0f), exportTextColorWithAlpha);
    }

    private int n_1700_B(g_221_o stack, float mouseX, float mouseY, Map<String, int[]> presets, int startIndex, float startX, float startY, float panelAlpha) {
        float circleSize = 10.0f;
        float presetAreaLeft = this.n_1700_B + 4.0f;
        float presetAreaRight = this.n_1700_B + this.v_4262_N() - 4.0f;
        for (Map.Entry<String, int[]> preset : presets.entrySet()) {
            boolean panelClosing;
            float circleX = startX + circleSize * (float)startIndex + 4.0f;
            float circleY = startY + 2.0f;
            boolean inVisibleArea = circleX + 4.5f > presetAreaLeft && circleX - 4.5f < presetAreaRight;
            boolean hovered = inVisibleArea && F_747_P.n_1700_B(mouseX, mouseY, circleX - 4.5f, circleY - 4.5f, 9.0f, 9.0f);
            int presetColor = preset.getValue()[K_1200_E.w_1457_N.ordinal()];
            float themeSettingAlpha = (float)H_2506_c.G_564_y(presetColor) / 255.0f * panelAlpha;
            F_489_x.n_1700_B(circleX - 4.5f, circleY - 4.5f, 9.0f, 9.0f, 3.5f, H_2506_c.n_1700_B(presetColor, themeSettingAlpha));
            if (preset.getKey().equals(n_4915_F.Y_601_j())) {
                float selectedCircleRadius = 2.4750001f;
                F_489_x.n_1700_B(circleX - selectedCircleRadius, circleY - selectedCircleRadius, selectedCircleRadius * 2.0f, selectedCircleRadius * 2.0f, selectedCircleRadius - 1.0f, H_2506_c.n_1700_B(H_2506_c.n_1700_B(0, 0, 0), panelAlpha));
            }
            Animation anim = this.c_3005_b.computeIfAbsent(preset.getKey(), key -> new Animation(0.0f, 12.0f));
            boolean bl = panelClosing = panelAlpha < this.H_2857_Y - 0.001f;
            anim.n_1700_B(panelClosing ? 0.0f : (hovered ? 1.0f : 0.0f));
            float tooltipAlpha = anim.n_1700_B() * panelAlpha;
            if (tooltipAlpha > 0.0f && inVisibleArea) {
                this.A_4115_X = preset.getKey();
                this.Y_1740_V = presets == this.h_1847_R ? "Pouch" : this.M_182_A.getOrDefault(preset.getKey(), S_4088_D.n_1700_B());
                this.t_4043_B = circleX - 4.5f + 2.0f;
                this.x_607_J = circleY - 4.5f - 16.0f;
                this.e_4240_b = tooltipAlpha;
            }
            ++startIndex;
        }
        return startIndex;
    }

    @Override
    public void n_1700_B(g_221_o stack, float mouseX, float mouseY, float alpha) {
        float contentHeight;
        this.k_2293_S.n_1700_B(this.t_148_a);
        float vis = this.Z_875_P.n_1700_B();
        float panelAlpha = alpha *= vis;
        this.s_956_w = this.k_2293_S.n_1700_B();
        if (this.q_2307_F.J_1907_R() == 1.0f && this.q_2307_F.R_4764_Y()) {
            this.q_2307_F.n_1700_B(0.0f);
        }
        if (!this.q_2307_F.R_4764_Y()) {
            this.q_2307_F.n_1700_B(this.q_2307_F.J_1907_R());
        }
        if (this.u_2550_I > (contentHeight = this.w_1484_f() - 200.0f)) {
            this.t_148_a = u_530_F.n_1700_B(this.t_148_a, -this.u_2550_I + contentHeight, 0.0f);
            this.s_956_w = u_530_F.n_1700_B(this.s_956_w, -this.u_2550_I + contentHeight, 0.0f);
        } else {
            this.s_956_w = 0.0f;
            this.t_148_a = 0.0f;
        }
        int currentBg = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.k_2293_S), q_3148_R.n_1700_B(K_1200_E.k_2293_S), q_3148_R.J_1907_R(K_1200_E.k_2293_S));
        float bgAlpha = (float)H_2506_c.G_564_y(currentBg) / 255.0f * panelAlpha;
        int finalBg = H_2506_c.n_1700_B(currentBg, bgAlpha);
        F_489_x.n_1700_B(this.n_1700_B - 10.0f, this.J_1907_R - 10.0f, this.v_4262_N() + 20.0f, this.w_1484_f() + 20.0f, 9.0f, q_3148_R.n_1700_B(K_1200_E.q_2307_F), q_3148_R.n_1700_B(K_1200_E.q_2307_F), q_3148_R.n_1700_B(K_1200_E.q_2307_F), q_3148_R.n_1700_B(K_1200_E.q_2307_F), q_3148_R.J_1907_R(K_1200_E.q_2307_F) / 255.0f * panelAlpha, 10.0f);
        F_489_x.n_1700_B(this.n_1700_B, this.J_1907_R, this.v_4262_N(), this.w_1484_f(), 9.0f, q_3148_R.n_1700_B(K_1200_E.n_1700_B), panelAlpha);
        F_489_x.n_1700_B(this.n_1700_B, this.J_1907_R, this.v_4262_N(), 28.0f, new Z_2491_A(9.0f, 0.0f, 9.0f, 0.0f), finalBg);
        F_489_x.J_1907_R(this.n_1700_B, this.J_1907_R, this.v_4262_N(), this.w_1484_f(), 9.0f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * panelAlpha);
        float padding = 8.0f;
        float textWidth = l_3370_o.R_4764_Y[26].n_1700_B("Theme Editor");
        float textHeight = l_3370_o.J_1907_R[21].h_1847_R();
        int startX = (int)(this.n_1700_B + 8.0f);
        int startY = (int)(this.J_1907_R - textHeight / 2.0f + 13.0f);
        float iconWidth = l_3370_o.w_1484_f[26].n_1700_B("Z");
        int startXI = (int)(this.n_1700_B + this.G_564_y - 8.0f - iconWidth);
        int headerColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.P_1922_E), q_3148_R.J_1907_R(K_1200_E.P_1922_E) / 255.0f * panelAlpha);
        l_3370_o.J_1907_R[21].n_1700_B(stack, "Theme Editor", (double)startX, (double)(startY + 1), headerColor);
        l_3370_o.w_1484_f[26].n_1700_B(stack, "Z", (double)startXI, (double)(startY + 1), headerColor);
        for (l_4397_i stringElement : w_1484_f) {
            stringElement.n_1700_B(this.n_1700_B - 2.0f);
            stringElement.J_1907_R(this.J_1907_R + 27.0f);
            stringElement.R_4764_Y(this.v_4262_N() - 13.0f);
            stringElement.n_1700_B(stack, mouseX, mouseY, alpha);
        }
        float buttonX = this.n_1700_B + 79.0f;
        float buttonY = this.J_1907_R + 31.0f;
        float buttonWidth = 30.0f;
        float buttonHeight = 12.0f;
        float createAnim = this.q_2307_F.n_1700_B();
        int createBg = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.P_4830_p), q_3148_R.n_1700_B(K_1200_E.M_588_G), createAnim);
        int createBgWithAlpha = H_2506_c.n_1700_B(createBg, (float)H_2506_c.G_564_y(createBg) / 255.0f * panelAlpha);
        F_489_x.n_1700_B(buttonX, buttonY, buttonWidth, buttonHeight, 2.0f, createBgWithAlpha);
        F_489_x.J_1907_R(buttonX, buttonY, buttonWidth, buttonHeight, 2.5f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * panelAlpha);
        float textX = buttonX + (buttonWidth - l_3370_o.R_4764_Y[12].n_1700_B("\u0421\u043e\u0437\u0434\u0430\u0442\u044c")) / 2.0f;
        float textY = buttonY + 5.0f;
        int createTextColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.G_564_y), q_3148_R.n_1700_B(K_1200_E.R_4764_Y), createAnim);
        int createTextColorWithAlpha = H_2506_c.n_1700_B(createTextColor, (float)H_2506_c.G_564_y(createTextColor) / 255.0f * panelAlpha);
        l_3370_o.R_4764_Y[12].n_1700_B(stack, "\u0421\u043e\u0437\u0434\u0430\u0442\u044c", (double)textX, (double)textY, createTextColorWithAlpha);
        float pickerY = this.J_1907_R + 55.0f;
        float visibleTop = this.J_1907_R + 50.0f;
        float visibleBottom = visibleTop + this.w_1484_f() - 74.0f;
        for (f_887_Z colorPicker : this.P_4830_p) {
            colorPicker.n_1700_B(this.n_1700_B + 3.0f);
            colorPicker.J_1907_R(pickerY + this.s_956_w);
            colorPicker.R_4764_Y(this.v_4262_N() - 6.0f);
            pickerY += colorPicker.t_148_a() + 1.0f;
        }
        this.u_2550_I = pickerY - (this.J_1907_R + 48.0f) + 10.0f;
        i_4833_u.n_1700_B(this.n_1700_B + 3.0f, this.J_1907_R + 48.0f, this.v_4262_N() - 6.0f, this.w_1484_f() - 79.0f);
        for (f_887_Z colorPicker : this.P_4830_p) {
            float elemTop = colorPicker.v_4262_N() - 3.5f;
            float elemBottom = elemTop + colorPicker.t_148_a();
            if (!(elemBottom > visibleTop) || !(elemTop < visibleBottom)) continue;
            F_489_x.n_1700_B(colorPicker.u_1723_Y(), colorPicker.v_4262_N() - 3.5f, colorPicker.w_1484_f(), colorPicker.t_148_a(), 4.0f, H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.multiplayerClientSuggestionProvider), q_3148_R.J_1907_R(K_1200_E.multiplayerClientSuggestionProvider) / 255.0f * panelAlpha));
            colorPicker.n_1700_B(stack, mouseX, mouseY, alpha);
        }
        i_4833_u.n_1700_B();
        this.J_1907_R(stack, mouseX, mouseY, panelAlpha);
        this.C_2741_M.n_1700_B(stack, (float)((int)mouseX), (float)((int)mouseY), panelAlpha);
        if (this.Y_601_j != null) {
            this.Y_601_j.n_1700_B(stack, panelAlpha);
            if (this.Y_601_j.J_1907_R()) {
                this.Y_601_j = null;
            }
        }
        this.H_2857_Y = panelAlpha;
    }

    @Override
    public void n_1700_B(float mouseX, float mouseY, int button) {
        for (l_4397_i stringElement : w_1484_f) {
            stringElement.n_1700_B(mouseX, mouseY, button);
        }
        if (this.Y_601_j != null) {
            if (button == 0) {
                float menuX = this.Y_601_j.R_4764_Y();
                float menuY = this.Y_601_j.G_564_y() + 4.0f;
                float menuW = this.Y_601_j.P_1922_E();
                float menuH = this.Y_601_j.u_1723_Y();
                float halfH = menuH / 2.0f;
                boolean clickedTop = F_747_P.n_1700_B(mouseX, mouseY, menuX, menuY, menuW, halfH);
                boolean clickedBottom = F_747_P.n_1700_B(mouseX, mouseY, menuX, menuY + halfH, menuW, halfH);
                if (clickedTop) {
                    String presetName = this.n_1700_B(this.Y_601_j.v_4262_N());
                    if (presetName != null && this.Q_4569_t.containsKey(presetName)) {
                        String cleanPresetName = presetName.replace("\u0418\u043c\u044f: ", "");
                        this.t_1786_h.J_1907_R(cleanPresetName);
                        this.Q_4569_t.remove(presetName);
                        if (this.Y_259_p != null && this.Y_259_p.n_1700_B().equals(presetName)) {
                            ArrayList<Map.Entry<String, int[]>> allPresets = new ArrayList<Map.Entry<String, int[]>>(this.h_1847_R.entrySet());
                            allPresets.addAll(this.Q_4569_t.entrySet());
                            int removedIndex = this.Y_601_j.v_4262_N();
                            int fallbackIndex = Math.max(0, removedIndex - 1);
                            Map.Entry entry = (Map.Entry)allPresets.get(fallbackIndex);
                            this.n_1700_B((int[])entry.getValue(), (String)entry.getKey(), this.Q_4569_t.containsKey(entry.getKey()));
                        }
                    }
                    this.Y_601_j.n_1700_B();
                    return;
                }
                if (clickedBottom) {
                    String presetName = this.n_1700_B(this.Y_601_j.v_4262_N());
                    if (presetName != null && this.Q_4569_t.containsKey(presetName)) {
                        String cleanName = presetName.replace("\u0418\u043c\u044f: ", "");
                        File themeFile = new File(C_332_W.n_1700_B + "themes\\", cleanName + ".file");
                        try {
                            new ProcessBuilder("powershell.exe", "-NoProfile", "-Command", "Set-Clipboard -Path '" + themeFile.getAbsolutePath() + "'").start();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    this.Y_601_j.n_1700_B();
                    return;
                }
                this.Y_601_j.n_1700_B();
            } else if (button == 1) {
                this.Y_601_j.n_1700_B();
                return;
            }
        }
        if (F_747_P.n_1700_B(mouseX, mouseY, this.n_1700_B + 79.0f, this.J_1907_R + 31.0f, 30.0f, 12.0f) && button == 0) {
            if (this.q_2307_F.G_564_y()) {
                return;
            }
            String themeName = (String)v_4262_N.get(0).J_1907_R();
            if (themeName == null || themeName.trim().isEmpty()) {
                v_1900_v.n_1700_B("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u043d\u0430\u0437\u0432\u0430\u043d\u0438\u0435 \u0442\u0435\u043c\u044b!", new Object[0]);
                return;
            }
            this.q_2307_F.J_1907_R(0.0f);
            this.q_2307_F.n_1700_B(1.0f);
            this.g_164_R();
            return;
        }
        float buttonY = this.J_1907_R + this.P_1922_E - 17.0f;
        float buttonWidth = (this.v_4262_N() - 18.0f) / 2.0f;
        float buttonHeight = 12.0f;
        float button1X = this.n_1700_B + 6.0f;
        float button2X = this.n_1700_B + 12.0f + buttonWidth;
        if (button == 0) {
            if (F_747_P.n_1700_B(mouseX, mouseY, button1X, buttonY, buttonWidth, buttonHeight)) {
                this.e_2887_G();
                return;
            }
            if (F_747_P.n_1700_B(mouseX, mouseY, button2X, buttonY, buttonWidth, buttonHeight)) {
                this.B_1668_F();
                return;
            }
        }
        float presetAreaY = this.J_1907_R + this.P_1922_E - 26.0f;
        float presetAreaLeft = this.n_1700_B + 4.0f;
        float presetAreaRight = this.n_1700_B + this.v_4262_N() - 4.0f;
        if (F_747_P.n_1700_B(mouseX, mouseY, presetAreaLeft, presetAreaY - 2.0f, this.v_4262_N() - 8.0f, 18.0f)) {
            int index = 0;
            ArrayList<Map.Entry<String, int[]>> allPresets = new ArrayList<Map.Entry<String, int[]>>();
            allPresets.addAll(this.h_1847_R.entrySet());
            allPresets.addAll(this.Q_4569_t.entrySet());
            float circleSize = 10.0f;
            for (Map.Entry entry : allPresets) {
                boolean inVisibleArea;
                float circleX = this.n_1700_B + 6.0f + this.d_2427_y + circleSize * (float)index + 4.0f;
                float circleY = presetAreaY + 2.0f;
                boolean bl = inVisibleArea = circleX + 4.5f > presetAreaLeft && circleX - 4.5f < presetAreaRight;
                if (inVisibleArea && F_747_P.n_1700_B(mouseX, mouseY, circleX - 4.5f, circleY - 4.5f, 9.0f, 9.0f)) {
                    boolean isCustom = this.Q_4569_t.containsKey(entry.getKey());
                    if (button == 0) {
                        this.n_1700_B((int[])entry.getValue(), (String)entry.getKey(), isCustom);
                        if (this.Y_601_j != null) {
                            this.Y_601_j.n_1700_B();
                        }
                    } else if (button == 1 && isCustom) {
                        this.Y_601_j = new J_1907_R(circleX + 2.0f, circleY - 4.5f - 1.0f, 52.0f, 28.0f, index);
                    }
                    return;
                }
                ++index;
            }
        }
    }

    private void e_2887_G() {
        try {
            String clipboardContent = MinecraftClient.A_4115_X().Q_4569_t.n_1700_B().trim();
            String decrypted = W_1488_x.J_1907_R(clipboardContent);
            JsonObject config = new JsonParser().parse(decrypted).getAsJsonObject();
            String presetName = config.get("presetName").getAsString();
            String creator = config.has("creator") ? config.get("creator").getAsString() : "Unknown";
            JsonArray colorsArray = config.getAsJsonArray("presetColors");
            int[] colors = new int[colorsArray.size()];
            for (int i = 0; i < colorsArray.size(); ++i) {
                colors[i] = colorsArray.get(i).getAsInt();
            }
            JsonObject elementColors = null;
            if (config.has("elementColors") && config.get("elementColors").isJsonObject()) {
                elementColors = config.getAsJsonObject("elementColors");
            }
            if (this.h_1847_R.containsKey(presetName) || this.Q_4569_t.containsKey(presetName)) {
                v_1900_v.n_1700_B("\u0422\u0435\u043c\u0430 \u0441 \u0442\u0430\u043a\u0438\u043c \u0438\u043c\u0435\u043d\u0435\u043c \u0443\u0436\u0435 \u0441\u0443\u0449\u0435\u0441\u0442\u0432\u0443\u0435\u0442!", new Object[0]);
                return;
            }
            String cleanName = presetName.replace("\u0418\u043c\u044f: ", "");
            n_1700_B preset = new n_1700_B(presetName, colors, creator, true);
            this.t_1786_h.n_1700_B(cleanName, preset, creator, elementColors);
            this.Q_4569_t.put(presetName, colors);
            v_1900_v.n_1700_B("\u0422\u0435\u043c\u0430 \"" + cleanName + "\" \u0438\u043c\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u043d\u0430!", new Object[0]);
        }
        catch (Exception e) {
            v_1900_v.n_1700_B("\u0421\u043a\u043e\u043f\u0438\u0440\u0443\u0439\u0442\u0435 \u0442\u0435\u043c\u0443 \u0432 \u0431\u0443\u0444\u0435\u0440 \u043e\u0431\u043c\u0435\u043d\u0430!", new Object[0]);
        }
    }

    private void B_1668_F() {
        if (this.Y_259_p == null) {
            v_1900_v.n_1700_B("\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0442\u0435\u043c\u0443 \u0434\u043b\u044f \u044d\u043a\u0441\u043f\u043e\u0440\u0442\u0430!", new Object[0]);
            return;
        }
        try {
            JsonObject config = new JsonObject();
            config.addProperty("presetName", this.Y_259_p.n_1700_B() != null ? this.Y_259_p.n_1700_B() : "Unknown");
            config.addProperty("creator", this.Y_259_p.R_4764_Y() != null ? this.Y_259_p.R_4764_Y() : "Unknown");
            int[] colors = this.Y_259_p.J_1907_R();
            JsonArray colorsArray = new JsonArray();
            if (colors != null) {
                for (int color : colors) {
                    colorsArray.add((Number)color);
                }
            }
            config.add("presetColors", (JsonElement)colorsArray);
            try {
                JsonObject elementColors = ClientBootstrap.Y_601_j().M_182_A().Q_4569_t();
                if (elementColors != null && elementColors.size() > 0) {
                    config.add("elementColors", (JsonElement)elementColors);
                }
            }
            catch (Exception elementColors) {
                // empty catch block
            }
            String jsonText = new GsonBuilder().create().toJson((JsonElement)config);
            String encrypted = W_1488_x.n_1700_B(jsonText);
            MinecraftClient.A_4115_X().Q_4569_t.n_1700_B(encrypted);
            String cleanName = this.Y_259_p.n_1700_B() != null ? this.Y_259_p.n_1700_B().replace("\u0418\u043c\u044f: ", "") : "Theme";
            v_1900_v.n_1700_B("\u0422\u0435\u043c\u0430 \"" + cleanName + "\" \u0441\u043a\u043e\u043f\u0438\u0440\u043e\u0432\u0430\u043d\u0430!", new Object[0]);
        }
        catch (Exception e) {
            e.printStackTrace();
            v_1900_v.n_1700_B("\u041e\u0448\u0438\u0431\u043a\u0430 \u044d\u043a\u0441\u043f\u043e\u0440\u0442\u0430 \u0442\u0435\u043c\u044b: " + e.getMessage(), new Object[0]);
        }
    }

    private String n_1700_B(int index) {
        ArrayList<Map.Entry<String, int[]>> allPresets = new ArrayList<Map.Entry<String, int[]>>();
        allPresets.addAll(this.h_1847_R.entrySet());
        allPresets.addAll(this.Q_4569_t.entrySet());
        if (index >= 0 && index < allPresets.size()) {
            return (String)((Map.Entry)allPresets.get(index)).getKey();
        }
        return null;
    }

    private void g_164_R() {
        if (this.h_1847_R.size() + this.Q_4569_t.size() >= 22) {
            return;
        }
        String themeName = (String)v_4262_N.get(0).J_1907_R();
        if (themeName == null || themeName.trim().isEmpty()) {
            return;
        }
        String fullThemeName = "\u0418\u043c\u044f: " + themeName;
        if (this.h_1847_R.containsKey(fullThemeName) || this.Q_4569_t.containsKey(fullThemeName)) {
            return;
        }
        String creator = S_4088_D.n_1700_B();
        int[] colors = new int[K_1200_E.values().length];
        K_1200_E[] settings = K_1200_E.values();
        for (int i = 0; i < settings.length; ++i) {
            colors[i] = (Integer)u_1723_Y.get((Object)settings[i]).J_1907_R();
        }
        this.Q_2552_b = this.Y_259_p;
        this.Y_259_p = new n_1700_B(fullThemeName, colors, creator, true);
        JsonObject elementColors = ClientBootstrap.Y_601_j().M_182_A().Q_4569_t();
        this.t_1786_h.n_1700_B(themeName, this.Y_259_p, creator, elementColors);
        this.Q_4569_t.put(fullThemeName, colors);
        n_4915_F.v_4262_N(fullThemeName);
        n_4915_F clientConfig = ClientBootstrap.Y_601_j().u_1723_Y();
        if (clientConfig != null) {
            clientConfig.J_1907_R(fullThemeName);
        }
        v_4262_N.get(0).J_1907_R("");
        w_1484_f.get(0).G_564_y();
    }

    private void n_1700_B(int[] colors, String presetName, boolean isCustom) {
        this.Q_2552_b = this.Y_259_p;
        K_1200_E[] settings = K_1200_E.values();
        for (int i = 0; i < Math.min(colors.length, settings.length); ++i) {
            h_2367_h colorSetting = u_1723_Y.get((Object)settings[i]);
            Runnable originalCallback = colorSetting.u_1723_Y();
            colorSetting.n_1700_B((Runnable)null);
            colorSetting.n_1700_B(colors[i]);
            colorSetting.n_1700_B(originalCallback);
        }
        this.Y_259_p = new n_1700_B(presetName, colors, S_4088_D.n_1700_B(), isCustom);
        n_4915_F.v_4262_N(presetName);
        if (isCustom) {
            String cleanName = presetName.replace("\u0418\u043c\u044f: ", "");
            V_1176_p.n_1700_B theme = this.t_1786_h.n_1700_B(cleanName);
            if (theme != null && theme.u_1723_Y() != null) {
                ClientBootstrap.Y_601_j().M_182_A().J_1907_R(theme.u_1723_Y());
            }
        } else {
            JsonObject elementColors = M_2029_A.J_1907_R().get(presetName);
            if (elementColors != null) {
                ClientBootstrap.Y_601_j().M_182_A().J_1907_R(elementColors);
            }
        }
        this.C_2741_M.n_1700_B(true);
    }

    private void X_933_l() {
        this.t_1786_h.M_588_G().forEach(name -> {
            V_1176_p.n_1700_B theme = this.t_1786_h.n_1700_B((String)name);
            if (theme != null && theme.R_4764_Y() != null && theme.G_564_y() != null) {
                this.Q_4569_t.put(theme.R_4764_Y(), theme.G_564_y());
                if (theme.P_1922_E() != null) {
                    this.M_182_A.put(theme.R_4764_Y(), theme.P_1922_E());
                }
            }
        });
    }

    private void n_1700_B(String presetName, JsonObject elementColors) {
        if (elementColors != null) {
            this.v_4276_D.put(presetName, elementColors);
        }
    }

    private void Z_976_R() {
        String savedTheme;
        n_4915_F clientConfig = ClientBootstrap.Y_601_j().u_1723_Y();
        if (clientConfig != null && (savedTheme = ((n_4915_F.n_1700_B)clientConfig.u_2550_I()).n_1700_B()) != null && !savedTheme.trim().isEmpty()) {
            int[] colors = this.h_1847_R.get(savedTheme);
            if (colors != null) {
                this.n_1700_B(colors, savedTheme, false);
            } else {
                colors = this.Q_4569_t.get(savedTheme);
                if (colors != null) {
                    this.n_1700_B(colors, savedTheme, true);
                    String cleanName = savedTheme.replace("\u0418\u043c\u044f: ", "");
                    V_1176_p.n_1700_B theme = this.t_1786_h.n_1700_B(cleanName);
                    if (theme != null && theme.u_1723_Y() != null) {
                        ClientBootstrap.Y_601_j().M_182_A().J_1907_R(theme.u_1723_Y());
                    }
                }
            }
        }
    }

    @Override
    public void J_1907_R(float mouseX, float mouseY, int button) {
        for (f_887_Z colorPicker : this.P_4830_p) {
            if (!colorPicker.G_564_y().G_564_y()) continue;
            colorPicker.G_564_y().n_1700_B();
        }
    }

    @Override
    public boolean n_1700_B(double mouseX, double mouseY, double delta) {
        if (F_747_P.n_1700_B((float)mouseX, (float)mouseY, this.G_564_y(), this.P_1922_E(), this.v_4262_N(), this.w_1484_f())) {
            float presetAreaY = this.J_1907_R + this.P_1922_E - 26.0f;
            if (F_747_P.n_1700_B((float)mouseX, (float)mouseY, this.n_1700_B + 4.0f, presetAreaY - 2.0f, this.v_4262_N() - 8.0f, 18.0f)) {
                float availableWidth;
                float circleSize;
                int totalPresets = this.h_1847_R.size() + this.Q_4569_t.size();
                float totalPresetsWidth = (float)totalPresets * (circleSize = 10.0f) + 4.0f;
                float maxPresetScroll = Math.max(0.0f, totalPresetsWidth - (availableWidth = this.v_4262_N() - 12.0f));
                if (maxPresetScroll > 0.0f) {
                    float previousPresetScroll = this.n_3318_d;
                    this.n_3318_d = (float)((double)this.n_3318_d + delta * 15.0);
                    this.n_3318_d = u_530_F.n_1700_B(this.n_3318_d, -maxPresetScroll, 0.0f);
                    return this.n_3318_d != previousPresetScroll;
                }
                return false;
            }
            float scissorY = this.J_1907_R + 48.0f;
            float scissorHeight = this.w_1484_f() - 79.0f;
            if (F_747_P.n_1700_B((float)mouseX, (float)mouseY, this.n_1700_B + 3.0f, scissorY, this.v_4262_N() - 6.0f, scissorHeight)) {
                float contentHeight = this.w_1484_f() - 66.0f;
                boolean canScroll = this.u_2550_I > contentHeight;
                float previousScroll = this.t_148_a;
                this.u_1723_Y((float)((double)this.M_588_G() + delta * 20.0));
                this.t_148_a = u_530_F.n_1700_B(this.t_148_a, -this.u_2550_I + contentHeight, 0.0f);
                if (canScroll && this.t_148_a != previousScroll) {
                    this.C_2741_M.n_1700_B(true);
                }
                return this.t_148_a != previousScroll;
            }
            return false;
        }
        return super.n_1700_B(mouseX, mouseY, delta);
    }

    @Override
    public void n_1700_B(int keyCode, int scanCode, int modifiers) {
        for (l_4397_i stringElement : w_1484_f) {
            stringElement.n_1700_B(keyCode, scanCode, modifiers);
        }
    }

    @Override
    public void n_1700_B(char codePoint, int modifiers) {
        for (l_4397_i stringElement : w_1484_f) {
            stringElement.n_1700_B(codePoint, modifiers);
        }
    }

    public void Q_4569_t() {
        if (this.Y_601_j != null) {
            this.Y_601_j.n_1700_B();
        }
    }

    @Generated
    public void n_1700_B(J_1907_R contextMenu) {
        this.Y_601_j = contextMenu;
    }

    @Generated
    public void n_1700_B(n_1700_B currentPreset) {
        this.Y_259_p = currentPreset;
    }

    @Generated
    public void J_1907_R(n_1700_B lastPreset) {
        this.Q_2552_b = lastPreset;
    }

    @Override
    @Generated
    public void u_1723_Y(float scroll) {
        this.t_148_a = scroll;
    }

    @Generated
    public void v_4262_N(float animatedScroll) {
        this.s_956_w = animatedScroll;
    }

    @Override
    @Generated
    public void R_4764_Y(float maxHeight) {
        this.u_2550_I = maxHeight;
    }

    @Generated
    public void w_1484_f(float lastPanelAlpha) {
        this.H_2857_Y = lastPanelAlpha;
    }

    @Generated
    public void J_1907_R(String pendingTooltipName) {
        this.A_4115_X = pendingTooltipName;
    }

    @Generated
    public void R_4764_Y(String pendingTooltipAuthor) {
        this.Y_1740_V = pendingTooltipAuthor;
    }

    @Generated
    public void t_148_a(float pendingTooltipX) {
        this.t_4043_B = pendingTooltipX;
    }

    @Generated
    public void s_956_w(float pendingTooltipY) {
        this.x_607_J = pendingTooltipY;
    }

    @Generated
    public void u_2550_I(float pendingTooltipAlpha) {
        this.e_4240_b = pendingTooltipAlpha;
    }

    @Generated
    public void M_588_G(float presetScroll) {
        this.n_3318_d = presetScroll;
    }

    @Generated
    public void P_4830_p(float animatedPresetScroll) {
        this.d_2427_y = animatedPresetScroll;
    }

    @Generated
    public List<f_887_Z> M_182_A() {
        return this.P_4830_p;
    }

    @Generated
    public Map<String, int[]> t_1786_h() {
        return this.h_1847_R;
    }

    @Generated
    public LinkedHashMap<String, int[]> multiplayerClientSuggestionProvider() {
        return this.Q_4569_t;
    }

    @Generated
    public Map<String, String> w_1457_N() {
        return this.M_182_A;
    }

    @Generated
    public V_1176_p Y_601_j() {
        return this.t_1786_h;
    }

    @Generated
    public float Y_259_p() {
        return this.multiplayerClientSuggestionProvider;
    }

    @Generated
    public float Q_2552_b() {
        return this.w_1457_N;
    }

    @Generated
    public J_1907_R C_2741_M() {
        return this.Y_601_j;
    }

    @Generated
    public n_1700_B k_2293_S() {
        return this.Y_259_p;
    }

    @Generated
    public n_1700_B q_2307_F() {
        return this.Q_2552_b;
    }

    @Generated
    public C_1577_A Z_875_P() {
        return this.C_2741_M;
    }

    @Override
    @Generated
    public float M_588_G() {
        return this.t_148_a;
    }

    @Generated
    public float c_3005_b() {
        return this.s_956_w;
    }

    @Override
    @Generated
    public Animation u_2550_I() {
        return this.k_2293_S;
    }

    @Generated
    public Animation H_2857_Y() {
        return this.q_2307_F;
    }

    @Generated
    public Animation A_4115_X() {
        return this.Z_875_P;
    }

    @Generated
    public Map<String, Animation> Y_1740_V() {
        return this.c_3005_b;
    }

    @Override
    @Generated
    public float u_1723_Y() {
        return this.u_2550_I;
    }

    @Generated
    public float t_4043_B() {
        return this.H_2857_Y;
    }

    @Generated
    public String x_607_J() {
        return this.A_4115_X;
    }

    @Generated
    public String e_4240_b() {
        return this.Y_1740_V;
    }

    @Generated
    public float n_3318_d() {
        return this.t_4043_B;
    }

    @Generated
    public float d_2427_y() {
        return this.x_607_J;
    }

    @Generated
    public float z_1737_N() {
        return this.e_4240_b;
    }

    @Generated
    public float v_4276_D() {
        return this.n_3318_d;
    }

    @Generated
    public float d_2461_k() {
        return this.d_2427_y;
    }

    @Generated
    public Animation G_624_v() {
        return this.z_1737_N;
    }

    @Generated
    public Map<String, JsonObject> T_2506_i() {
        return this.v_4276_D;
    }

    static {
        u_1723_Y = new EnumMap(K_1200_E.class);
        v_4262_N = new ArrayList<O_3016_i>();
        w_1484_f = new ArrayList<l_4397_i>();
    }

    private static class J_1907_R {
        private final float n_1700_B;
        private final float J_1907_R;
        private final float R_4764_Y;
        private final float G_564_y;
        private final int P_1922_E;
        private final Animation u_1723_Y;
        private boolean v_4262_N;

        public J_1907_R(float x, float y, float width, float height, int presetIndex) {
            this.n_1700_B = x;
            this.J_1907_R = y;
            this.R_4764_Y = width;
            this.G_564_y = height;
            this.P_1922_E = presetIndex;
            this.u_1723_Y = new Animation(0.0f, 12.0f);
            this.v_4262_N = false;
        }

        public void n_1700_B(g_221_o stack, float parentAlpha) {
            this.u_1723_Y.n_1700_B(this.v_4262_N ? 0.0f : 1.0f);
            float effectiveAlpha = parentAlpha * this.u_1723_Y.n_1700_B();
            F_489_x.n_1700_B(this.n_1700_B, this.J_1907_R + 4.0f, this.R_4764_Y, this.G_564_y, 4.0f, q_3148_R.n_1700_B(K_1200_E.n_1700_B), effectiveAlpha);
            F_489_x.J_1907_R(this.n_1700_B, this.J_1907_R + 4.0f, this.R_4764_Y, this.G_564_y, 4.0f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * effectiveAlpha);
            float textX = this.n_1700_B + 13.0f;
            F_489_x.n_1700_B(new g_2336_b("Pouch/icons/gui/bin.png"), this.n_1700_B + 3.0f, this.J_1907_R + 7.5f, 8.0f, 8.0f, H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.R_4764_Y), effectiveAlpha));
            l_3370_o.G_564_y[12].n_1700_B(stack, "\u0423\u0434\u0430\u043b\u0438\u0442\u044c", (double)(textX - 0.5f), (double)(this.J_1907_R + 10.5f), H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.R_4764_Y), effectiveAlpha));
            F_489_x.n_1700_B(this.n_1700_B + 2.0f, this.J_1907_R + 17.5f, 48.0f, 1.0f, 1.5f, H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.Q_4569_t), effectiveAlpha));
            F_489_x.n_1700_B(new g_2336_b("Pouch/icons/gui/copy.png"), this.n_1700_B + 3.5f, this.J_1907_R + 20.5f, 8.0f, 8.0f, H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.R_4764_Y), effectiveAlpha));
            l_3370_o.G_564_y[12].n_1700_B(stack, "\u041a\u043e\u043f\u0438\u0440\u043e\u0432\u0430\u0442\u044c", (double)textX, (double)(this.J_1907_R + 23.5f), H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.R_4764_Y), effectiveAlpha));
        }

        public void n_1700_B() {
            if (!this.v_4262_N) {
                this.v_4262_N = true;
                this.u_1723_Y.n_1700_B(0.0f);
            }
        }

        public boolean J_1907_R() {
            return this.v_4262_N && this.u_1723_Y.R_4764_Y();
        }

        @Generated
        public float R_4764_Y() {
            return this.n_1700_B;
        }

        @Generated
        public float G_564_y() {
            return this.J_1907_R;
        }

        @Generated
        public float P_1922_E() {
            return this.R_4764_Y;
        }

        @Generated
        public float u_1723_Y() {
            return this.G_564_y;
        }

        @Generated
        public int v_4262_N() {
            return this.P_1922_E;
        }

        @Generated
        public Animation w_1484_f() {
            return this.u_1723_Y;
        }

        @Generated
        public boolean t_148_a() {
            return this.v_4262_N;
        }
    }

    public static class n_1700_B {
        private final String n_1700_B;
        private final int[] J_1907_R;
        private final String R_4764_Y;
        private final boolean G_564_y;

        public n_1700_B(String name, int[] colors, String creator, boolean isCustom) {
            this.n_1700_B = name;
            this.J_1907_R = colors;
            this.R_4764_Y = creator != null ? creator : "Unknown";
            this.G_564_y = isCustom;
        }

        @Generated
        public String n_1700_B() {
            return this.n_1700_B;
        }

        @Generated
        public int[] J_1907_R() {
            return this.J_1907_R;
        }

        @Generated
        public String R_4764_Y() {
            return this.R_4764_Y;
        }

        @Generated
        public boolean G_564_y() {
            return this.G_564_y;
        }
    }
}



