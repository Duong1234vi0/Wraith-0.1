/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lightning.product.F_489_x;
import lightning.product.F_747_P;
import lightning.product.H_1952_g;
import lightning.product.H_2506_c;
import lightning.product.K_1200_E;
import lightning.product.O_3016_i;
import lightning.product.Z_2491_A;
import lightning.product.MinecraftAccess;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.Easing;
import lightning.product.i_4833_u;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.l_4397_i;
import lightning.product.ClientBootstrap;
import lightning.product.q_3148_R;
import lightning.product.u_530_F;
import lightning.product.y_2622_c;
import lombok.Generated;

public class N_2592_G
extends H_1952_g
implements MinecraftAccess {
    private final l_4397_i u_1723_Y;
    private final O_3016_i v_4262_N;
    private final Animation w_1484_f = new Animation(0.0f, 10.0f, Easing.Y_601_j);
    private final Animation t_148_a = new Animation(0.0f, 12.0f);
    private final Animation s_956_w = new Animation(0.0f, 6.0f);
    private float u_2550_I = 0.0f;
    private float M_588_G = 0.0f;
    private String P_4830_p = null;
    private String h_1847_R = null;
    private final Map<String, Animation> Q_4569_t = new HashMap<String, Animation>();

    public N_2592_G() {
        this.G_564_y = 112.0f;
        this.P_1922_E = 120.0f;
        this.v_4262_N = new O_3016_i("\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435");
        this.u_1723_Y = new l_4397_i(this.v_4262_N);
    }

    @Override
    public void n_1700_B(g_221_o stack, float mouseX, float mouseY, float alpha) {
        this.w_1484_f.n_1700_B(this.u_2550_I);
        float animatedListScroll = this.w_1484_f.n_1700_B();
        ArrayList<String> configs = new ArrayList<String>(ClientBootstrap.Y_601_j().R_4764_Y().P_4830_p());
        String defaultConfig = ClientBootstrap.Y_601_j().R_4764_Y().M_588_G();
        if (defaultConfig != null && configs.contains(defaultConfig)) {
            configs.remove(defaultConfig);
            configs.add(0, defaultConfig);
        }
        String currentConfig = ((y_2622_c.J_1907_R)ClientBootstrap.Y_601_j().R_4764_Y().u_2550_I()).n_1700_B();
        int currentBg = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.k_2293_S), q_3148_R.n_1700_B(K_1200_E.k_2293_S), q_3148_R.J_1907_R(K_1200_E.k_2293_S));
        float bgAlpha = (float)H_2506_c.G_564_y(currentBg) / 255.0f * alpha;
        int finalBg = H_2506_c.n_1700_B(currentBg, bgAlpha);
        F_489_x.n_1700_B(this.n_1700_B - 10.0f, this.J_1907_R - 10.0f, this.v_4262_N() + 20.0f, this.w_1484_f() + 20.0f, 9.0f, q_3148_R.n_1700_B(K_1200_E.q_2307_F), q_3148_R.n_1700_B(K_1200_E.q_2307_F), q_3148_R.n_1700_B(K_1200_E.q_2307_F), q_3148_R.n_1700_B(K_1200_E.q_2307_F), q_3148_R.J_1907_R(K_1200_E.q_2307_F) / 255.0f * alpha, 10.0f);
        F_489_x.n_1700_B(this.n_1700_B, this.J_1907_R, this.v_4262_N(), this.w_1484_f(), 9.0f, q_3148_R.n_1700_B(K_1200_E.n_1700_B), alpha);
        F_489_x.n_1700_B(this.n_1700_B, this.J_1907_R, this.v_4262_N(), 28.0f, new Z_2491_A(9.0f, 0.0f, 9.0f, 0.0f), finalBg);
        F_489_x.J_1907_R(this.n_1700_B, this.J_1907_R, this.v_4262_N(), this.w_1484_f(), 9.0f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * alpha);
        float textHeight = l_3370_o.J_1907_R[21].h_1847_R();
        int textY = (int)(this.J_1907_R - textHeight / 2.0f + 13.0f);
        int hdrCol = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.P_1922_E), q_3148_R.J_1907_R(K_1200_E.P_1922_E) / 255.0f * alpha);
        l_3370_o.J_1907_R[21].n_1700_B(stack, "Configs", (double)(this.n_1700_B + 8.0f), (double)(textY + 1), hdrCol);
        float iconWidth = l_3370_o.u_1723_Y[26].n_1700_B("F");
        l_3370_o.u_1723_Y[26].n_1700_B(stack, "F", (double)(this.n_1700_B + this.G_564_y - 8.0f - iconWidth), (double)(textY + 1), hdrCol);
        this.u_1723_Y.n_1700_B(this.n_1700_B - 2.0f);
        this.u_1723_Y.J_1907_R(this.J_1907_R + 27.0f);
        this.u_1723_Y.R_4764_Y(this.G_564_y - 13.0f);
        this.u_1723_Y.n_1700_B(stack, mouseX, mouseY, alpha);
        float buttonX = this.n_1700_B + 79.0f;
        float buttonY = this.J_1907_R + 31.0f;
        float buttonWidth = 30.0f;
        float buttonHeight = 12.0f;
        boolean createHovered = F_747_P.n_1700_B(mouseX, mouseY, buttonX, buttonY, buttonWidth, buttonHeight);
        this.s_956_w.n_1700_B(createHovered ? 1.0f : 0.0f);
        float createAnim = this.s_956_w.n_1700_B();
        int createBg = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.P_4830_p), q_3148_R.n_1700_B(K_1200_E.M_588_G), createAnim);
        int createBgWithAlpha = H_2506_c.n_1700_B(createBg, (float)H_2506_c.G_564_y(createBg) / 255.0f * alpha);
        F_489_x.n_1700_B(buttonX, buttonY, buttonWidth, buttonHeight, 2.0f, createBgWithAlpha);
        F_489_x.J_1907_R(buttonX, buttonY, buttonWidth, buttonHeight, 2.5f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * alpha);
        String createText = "\u0421\u043e\u0437\u0434\u0430\u0442\u044c";
        float createTextX = buttonX + (buttonWidth - l_3370_o.R_4764_Y[12].n_1700_B(createText)) / 2.0f;
        float createTextY = buttonY + 5.0f;
        int createTextColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.G_564_y), q_3148_R.n_1700_B(K_1200_E.R_4764_Y), createAnim);
        int createTextColorWithAlpha = H_2506_c.n_1700_B(createTextColor, (float)H_2506_c.G_564_y(createTextColor) / 255.0f * alpha);
        l_3370_o.R_4764_Y[12].n_1700_B(stack, createText, (double)createTextX, (double)createTextY, createTextColorWithAlpha);
        if (createHovered) {
            this.h_1847_R = "create";
        }
        float listY = this.J_1907_R + 48.0f;
        float listHeight = this.P_1922_E - (listY - this.J_1907_R) - 6.0f;
        float itemHeight = 14.0f;
        float itemSpacing = 2.0f;
        this.M_588_G = (float)configs.size() * (itemHeight + itemSpacing);
        float maxScroll = Math.max(0.0f, this.M_588_G - listHeight);
        this.u_2550_I = u_530_F.n_1700_B(this.u_2550_I, 0.0f, maxScroll);
        i_4833_u.n_1700_B(this.n_1700_B + 3.0f, listY, this.G_564_y - 6.0f, listHeight);
        this.P_4830_p = null;
        this.h_1847_R = null;
        for (int i = 0; i < configs.size(); ++i) {
            String configName = (String)configs.get(i);
            float itemY = listY + (float)i * (itemHeight + itemSpacing) - animatedListScroll;
            if (itemY + itemHeight < listY || itemY > listY + listHeight) continue;
            Animation hoverAnim = this.Q_4569_t.computeIfAbsent(configName, k -> new Animation(0.0f, 10.0f));
            boolean isHovered = F_747_P.n_1700_B(mouseX, mouseY, this.n_1700_B + 3.0f, itemY, this.G_564_y - 6.0f, itemHeight);
            boolean isActive = configName.equals(currentConfig);
            boolean isDefault = configName.equals(defaultConfig);
            hoverAnim.n_1700_B(isHovered ? 1.0f : 0.0f);
            if (isHovered) {
                this.P_4830_p = configName;
            }
            int itemBg = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.n_1700_B), H_2506_c.J_1907_R(q_3148_R.n_1700_B(K_1200_E.n_1700_B), 20), hoverAnim.n_1700_B());
            if (isActive) {
                itemBg = H_2506_c.n_1700_B(itemBg, q_3148_R.n_1700_B(K_1200_E.w_1457_N), 0.2f);
            }
            F_489_x.n_1700_B(this.n_1700_B + 3.0f, itemY, this.G_564_y - 6.0f, itemHeight, 3.0f, itemBg, alpha);
            if (isActive) {
                F_489_x.J_1907_R(this.n_1700_B + 3.0f, itemY, this.G_564_y - 6.0f, itemHeight, 3.0f, q_3148_R.n_1700_B(K_1200_E.w_1457_N), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * alpha * 0.7f);
            }
            float centerY = itemY + itemHeight / 2.0f;
            float starX = this.n_1700_B + 6.0f;
            float starSize = 6.0f;
            boolean starHovered = F_747_P.n_1700_B(mouseX, mouseY, starX - 1.0f, itemY, starSize + 3.0f, itemHeight);
            int starColor = isDefault ? q_3148_R.n_1700_B(K_1200_E.w_1457_N) : (starHovered ? H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.w_1457_N), 0.6f) : H_2506_c.n_1700_B(255, 255, 255, 80));
            F_489_x.n_1700_B(new g_2336_b("Pouch/icons/alts/star.png"), starX, centerY - starSize / 2.0f, starSize, starSize, H_2506_c.n_1700_B(starColor, alpha));
            if (starHovered) {
                this.h_1847_R = "star:" + configName;
            }
            float btnHeight = 10.0f;
            float btnSpacing = 2.0f;
            float saveWidth = l_3370_o.J_1907_R[12].n_1700_B("Save") + 4.0f;
            float loadWidth = l_3370_o.J_1907_R[12].n_1700_B("Load") + 4.0f;
            float saveX = this.n_1700_B + this.G_564_y - 6.0f - saveWidth;
            boolean saveHovered = F_747_P.n_1700_B(mouseX, mouseY, saveX, centerY - btnHeight / 2.0f, saveWidth, btnHeight);
            Animation saveHoverAnim = this.Q_4569_t.computeIfAbsent("save:" + configName, k -> new Animation(0.0f, 10.0f));
            saveHoverAnim.n_1700_B(saveHovered ? 1.0f : 0.0f);
            int saveBg = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.P_4830_p), q_3148_R.n_1700_B(K_1200_E.M_588_G), saveHoverAnim.n_1700_B());
            float saveBgAlpha = (float)H_2506_c.G_564_y(saveBg) / 255.0f * alpha;
            int finalSaveBg = H_2506_c.n_1700_B(saveBg, saveBgAlpha);
            F_489_x.n_1700_B(saveX, centerY - btnHeight / 2.0f, saveWidth, btnHeight, 2.0f, finalSaveBg);
            int saveTextColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.G_564_y), q_3148_R.n_1700_B(K_1200_E.R_4764_Y), saveHoverAnim.n_1700_B());
            float saveTextAlpha = (float)H_2506_c.G_564_y(saveTextColor) / 255.0f * alpha;
            int finalSaveTextColor = H_2506_c.n_1700_B(saveTextColor, saveTextAlpha);
            l_3370_o.J_1907_R[12].n_1700_B(stack, "Save", (double)(saveX + 2.0f), (double)Math.round(centerY - 1.5f), finalSaveTextColor);
            if (saveHovered) {
                this.h_1847_R = "save:" + configName;
            }
            float loadX = saveX - loadWidth - btnSpacing;
            boolean loadHovered = F_747_P.n_1700_B(mouseX, mouseY, loadX, centerY - btnHeight / 2.0f, loadWidth, btnHeight);
            Animation loadHoverAnim = this.Q_4569_t.computeIfAbsent("load:" + configName, k -> new Animation(0.0f, 10.0f));
            loadHoverAnim.n_1700_B(loadHovered ? 1.0f : 0.0f);
            int loadBg = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.P_4830_p), q_3148_R.n_1700_B(K_1200_E.M_588_G), loadHoverAnim.n_1700_B());
            float loadBgAlpha = (float)H_2506_c.G_564_y(loadBg) / 255.0f * alpha;
            int finalLoadBg = H_2506_c.n_1700_B(loadBg, loadBgAlpha);
            F_489_x.n_1700_B(loadX, centerY - btnHeight / 2.0f, loadWidth, btnHeight, 2.0f, finalLoadBg);
            int loadTextColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.G_564_y), q_3148_R.n_1700_B(K_1200_E.R_4764_Y), loadHoverAnim.n_1700_B());
            float loadTextAlpha = (float)H_2506_c.G_564_y(loadTextColor) / 255.0f * alpha;
            int finalLoadTextColor = H_2506_c.n_1700_B(loadTextColor, loadTextAlpha);
            l_3370_o.J_1907_R[12].n_1700_B(stack, "Load", (double)(loadX + 2.0f), (double)Math.round(centerY - 1.5f), finalLoadTextColor);
            if (loadHovered) {
                this.h_1847_R = "load:" + configName;
            }
            int nameColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.G_564_y), q_3148_R.n_1700_B(K_1200_E.R_4764_Y), hoverAnim.n_1700_B());
            Object displayName = configName;
            float nameStartX = starX + starSize + 4.0f;
            float maxNameWidth = loadX - nameStartX - 3.0f;
            if (l_3370_o.R_4764_Y[14].n_1700_B((String)displayName) > maxNameWidth) {
                while (l_3370_o.R_4764_Y[14].n_1700_B((String)displayName + "..") > maxNameWidth && ((String)displayName).length() > 0) {
                    displayName = ((String)displayName).substring(0, ((String)displayName).length() - 1);
                }
                displayName = (String)displayName + "..";
            }
            float txtAlpha = (float)H_2506_c.G_564_y(nameColor) / 255.0f * alpha;
            int finalTextColor = H_2506_c.n_1700_B(nameColor, txtAlpha);
            float fontHeight = l_3370_o.R_4764_Y[14].h_1847_R();
            float nameY = itemY + (itemHeight - fontHeight) / 2.0f;
            l_3370_o.R_4764_Y[14].n_1700_B(stack, (String)displayName, (double)nameStartX, (double)Math.round(nameY), finalTextColor);
        }
        i_4833_u.n_1700_B();
        if (configs.isEmpty()) {
            String emptyText = "\u041d\u0435\u0442 \u0441\u043e\u0445\u0440\u0430\u043d\u0451\u043d\u043d\u044b\u0445 \u043a\u043e\u043d\u0444\u0438\u0433\u043e\u0432";
            float textWidth = l_3370_o.J_1907_R[12].n_1700_B(emptyText);
            l_3370_o.J_1907_R[12].n_1700_B(stack, emptyText, (double)(this.n_1700_B + (this.G_564_y - textWidth) / 2.0f), (double)(listY + 10.0f), H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.P_1922_E), alpha * 0.5f));
        }
    }

    @Override
    public void n_1700_B(float mouseX, float mouseY, int button) {
        if (button == 2 && this.P_4830_p != null) {
            ClientBootstrap.Y_601_j().R_4764_Y().G_564_y(this.P_4830_p);
            return;
        }
        if (button != 0) {
            return;
        }
        this.u_1723_Y.n_1700_B(mouseX, mouseY, button);
        if (F_747_P.n_1700_B(mouseX, mouseY, this.n_1700_B + 79.0f, this.J_1907_R + 31.0f, 30.0f, 12.0f)) {
            Object name = (String)this.v_4262_N.J_1907_R();
            if (name == null || ((String)name).trim().isEmpty()) {
                name = "config_" + new SimpleDateFormat("dd-MM_HH-mm").format(new Date());
            }
            ClientBootstrap.Y_601_j().R_4764_Y().R_4764_Y(((String)name).trim());
            this.v_4262_N.n_1700_B("");
            return;
        }
        if (this.h_1847_R != null) {
            if (this.h_1847_R.startsWith("load:")) {
                String configName = this.h_1847_R.substring(5);
                ClientBootstrap.Y_601_j().R_4764_Y().J_1907_R(configName);
                return;
            }
            if (this.h_1847_R.startsWith("save:")) {
                String configName = this.h_1847_R.substring(5);
                ClientBootstrap.Y_601_j().R_4764_Y().R_4764_Y(configName);
                return;
            }
            if (this.h_1847_R.startsWith("star:")) {
                String currentDefault;
                String configName = this.h_1847_R.substring(5);
                if (configName.equals(currentDefault = ClientBootstrap.Y_601_j().R_4764_Y().M_588_G())) {
                    ClientBootstrap.Y_601_j().R_4764_Y().n_1700_B((String)null);
                } else {
                    ClientBootstrap.Y_601_j().R_4764_Y().n_1700_B(configName);
                }
                return;
            }
        }
    }

    @Override
    public void n_1700_B(int keyCode, int scanCode, int modifiers) {
        this.u_1723_Y.n_1700_B(keyCode, scanCode, modifiers);
    }

    @Override
    public void n_1700_B(char codePoint, int modifiers) {
        this.u_1723_Y.n_1700_B(codePoint, modifiers);
    }

    @Override
    public boolean n_1700_B(double mouseX, double mouseY, double delta) {
        if (F_747_P.n_1700_B((float)mouseX, (float)mouseY, this.n_1700_B, this.J_1907_R, this.G_564_y, this.P_1922_E)) {
            this.u_2550_I -= (float)delta * 15.0f;
            return true;
        }
        return false;
    }

    @Generated
    public l_4397_i P_4830_p() {
        return this.u_1723_Y;
    }

    @Generated
    public O_3016_i h_1847_R() {
        return this.v_4262_N;
    }

    @Generated
    public Animation Q_4569_t() {
        return this.w_1484_f;
    }

    @Generated
    public Animation M_182_A() {
        return this.t_148_a;
    }

    @Generated
    public Animation t_1786_h() {
        return this.s_956_w;
    }

    @Generated
    public float multiplayerClientSuggestionProvider() {
        return this.u_2550_I;
    }

    @Generated
    public float w_1457_N() {
        return this.M_588_G;
    }

    @Generated
    public String Y_601_j() {
        return this.P_4830_p;
    }

    @Generated
    public String Y_259_p() {
        return this.h_1847_R;
    }

    @Generated
    public Map<String, Animation> Q_2552_b() {
        return this.Q_4569_t;
    }
}



