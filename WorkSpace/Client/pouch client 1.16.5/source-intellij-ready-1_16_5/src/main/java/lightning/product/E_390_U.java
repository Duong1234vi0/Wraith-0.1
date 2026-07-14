/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.lwjgl.glfw.GLFW
 */
package lightning.product;

import java.util.ArrayList;
import java.util.List;
import lightning.product.AutoBuy;
import lightning.product.F_489_x;
import lightning.product.F_747_P;
import lightning.product.H_1475_K;
import lightning.product.H_1952_g;
import lightning.product.H_2506_c;
import lightning.product.K_1200_E;
import lightning.product.O_3016_i;
import lightning.product.U_2474_c;
import lightning.product.Z_2491_A;
import lightning.product.Z_256_c;
import lightning.product.MinecraftAccess;
import lightning.product.c_1608_O;
import lightning.product.g_221_o;
import lightning.product.Easing;
import lightning.product.i_4833_u;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.l_4397_i;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.p_3749_n;
import lightning.product.q_3148_R;
import lightning.product.u_530_F;
import lombok.Generated;
import org.lwjgl.glfw.GLFW;

public class E_390_U
extends H_1952_g
implements MinecraftAccess {
    private final List<l_4397_i> u_1723_Y = new ArrayList<l_4397_i>();
    private final List<H_1475_K> v_4262_N = new ArrayList<H_1475_K>();
    private final List<l_4397_i> w_1484_f = new ArrayList<l_4397_i>();
    private final List<p_3749_n> t_148_a = new ArrayList<p_3749_n>();
    private final List<l_4397_i> s_956_w = new ArrayList<l_4397_i>();
    private final Animation u_2550_I = new Animation(0.0f, 10.0f, Easing.Y_601_j);
    private final Animation M_588_G = new Animation(0.0f, 12.0f);
    private float P_4830_p = 0.0f;
    private float h_1847_R = 0.0f;
    private int Q_4569_t = -1;
    private String M_182_A = "";

    public E_390_U() {
        this.G_564_y = 112.0f;
        this.P_1922_E = 275.0f;
        this.u_1723_Y.add(new l_4397_i(new O_3016_i("\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435 | !\u0432\u043a\u043b | !\u0432\u044b\u043a\u043b")));
        this.k_2293_S();
    }

    private void k_2293_S() {
        String server;
        this.v_4262_N.clear();
        this.w_1484_f.clear();
        this.t_148_a.clear();
        this.s_956_w.clear();
        this.Q_4569_t = -1;
        this.M_182_A = server = this.q_2307_F();
        for (c_1608_O setting : Z_256_c.n_1700_B(server)) {
            this.v_4262_N.add(new H_1475_K(setting));
            this.t_148_a.add(new p_3749_n(new BooleanSetting("\u041f\u0440\u043e\u0434\u0430\u0432\u0430\u0442\u044c", false)));
            this.w_1484_f.add(new l_4397_i(new O_3016_i("\u0417\u0430 \u0448\u0442\u0443\u043a\u0443")));
            this.s_956_w.add(new l_4397_i(new O_3016_i("\u041e\u0442 \u0446\u0435\u043d\u044b \u043f\u043e\u043a\u0443\u043f\u043a\u0438")));
        }
        if (!this.v_4262_N.isEmpty()) {
            this.v_4262_N.get(0).n_1700_B(true);
            this.Q_4569_t = 0;
        }
    }

    private String q_2307_F() {
        AutoBuy autoBuyModule = (AutoBuy)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(AutoBuy.class);
        if (autoBuyModule != null) {
            return autoBuyModule.h_1847_R();
        }
        return "HolyWorld";
    }

    private void Z_875_P() {
        String current = this.q_2307_F();
        if (!current.equals(this.M_182_A)) {
            this.k_2293_S();
        }
    }

    @Override
    public void n_1700_B(g_221_o stack, float mouseX, float mouseY, float alpha) {
        this.Z_875_P();
        this.u_2550_I.n_1700_B(this.P_4830_p);
        float animatedListScroll = this.u_2550_I.n_1700_B();
        int currentBg = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.k_2293_S), q_3148_R.n_1700_B(K_1200_E.k_2293_S), q_3148_R.J_1907_R(K_1200_E.k_2293_S));
        float bgAlpha = (float)H_2506_c.G_564_y(currentBg) / 255.0f * alpha;
        int finalBg = H_2506_c.n_1700_B(currentBg, bgAlpha);
        F_489_x.n_1700_B(this.n_1700_B - 10.0f, this.J_1907_R - 10.0f, this.v_4262_N() + 20.0f, this.w_1484_f() + 20.0f, 9.0f, q_3148_R.n_1700_B(K_1200_E.q_2307_F), q_3148_R.n_1700_B(K_1200_E.q_2307_F), q_3148_R.n_1700_B(K_1200_E.q_2307_F), q_3148_R.n_1700_B(K_1200_E.q_2307_F), q_3148_R.J_1907_R(K_1200_E.q_2307_F) / 255.0f * alpha, 10.0f);
        F_489_x.n_1700_B(this.n_1700_B, this.J_1907_R, this.v_4262_N(), this.w_1484_f(), 9.0f, q_3148_R.n_1700_B(K_1200_E.n_1700_B), alpha);
        F_489_x.n_1700_B(this.n_1700_B, this.J_1907_R, this.v_4262_N(), 28.0f, new Z_2491_A(9.0f, 0.0f, 9.0f, 0.0f), finalBg);
        F_489_x.J_1907_R(this.n_1700_B, this.J_1907_R, this.v_4262_N(), this.w_1484_f(), 9.0f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * alpha);
        float padding = 8.0f;
        int startX = (int)(this.n_1700_B + 8.0f);
        int startY = (int)(this.J_1907_R - l_3370_o.J_1907_R[26].h_1847_R() / 2.0f + 13.0f);
        float iconWidth = l_3370_o.w_1484_f[26].n_1700_B("M");
        int startXI = (int)(this.n_1700_B + this.G_564_y - 8.0f - iconWidth);
        int headerColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.P_1922_E), q_3148_R.J_1907_R(K_1200_E.P_1922_E) / 255.0f * alpha);
        l_3370_o.J_1907_R[21].n_1700_B(stack, "Auto Buy", (double)startX, (double)(startY + 1), headerColor);
        l_3370_o.w_1484_f[26].n_1700_B(stack, "M", (double)startXI, (double)(startY + 1), headerColor);
        for (l_4397_i elem : this.u_1723_Y) {
            elem.n_1700_B(this.n_1700_B + 12.5f);
            elem.J_1907_R(this.J_1907_R + 26.0f);
            elem.R_4764_Y(100.0f);
            elem.n_1700_B(stack, mouseX, mouseY, alpha);
        }
        List<H_1475_K> filteredItems = this.c_3005_b();
        float itemBaseY = this.J_1907_R + 45.0f;
        float viewportHeight = this.w_1484_f() - 90.0f;
        float itemSpacing = 18.0f;
        this.h_1847_R = (float)filteredItems.size() * itemSpacing;
        if (this.h_1847_R > viewportHeight) {
            this.P_4830_p = u_530_F.n_1700_B(this.P_4830_p, -this.h_1847_R + viewportHeight, 0.0f);
            animatedListScroll = u_530_F.n_1700_B(animatedListScroll, -this.h_1847_R + viewportHeight, 0.0f);
        } else {
            animatedListScroll = 0.0f;
            this.P_4830_p = 0.0f;
        }
        float visibleTop = itemBaseY - 1.0f;
        float visibleBottom = visibleTop + viewportHeight - 2.0f;
        i_4833_u.n_1700_B(this.n_1700_B, itemBaseY - 1.0f, this.v_4262_N(), viewportHeight);
        for (int i = 0; i < filteredItems.size(); ++i) {
            boolean isVisibleInViewport;
            H_1475_K elem = filteredItems.get(i);
            float elemY = itemBaseY + (float)i * itemSpacing + animatedListScroll;
            elem.n_1700_B(this.n_1700_B + 3.0f);
            elem.J_1907_R(elemY);
            elem.R_4764_Y(this.v_4262_N() - 12.0f);
            float elemTop = elem.v_4262_N();
            float elemBottom = elemTop + itemSpacing;
            boolean bl = isVisibleInViewport = elemBottom > visibleTop && elemTop < visibleBottom;
            if (isVisibleInViewport) {
                elem.n_1700_B(stack, mouseX, mouseY, alpha);
                continue;
            }
            elem.J_1907_R();
        }
        i_4833_u.n_1700_B();
        if (this.Q_4569_t != -1) {
            long cur;
            String desired;
            String formatted;
            float labelX = this.n_1700_B + 5.0f;
            float labelY = this.J_1907_R + this.w_1484_f() - 41.0f;
            p_3749_n sellToggle = this.t_148_a.get(this.Q_4569_t);
            sellToggle.n_1700_B(this.n_1700_B - 1.5f);
            sellToggle.J_1907_R(labelY);
            sellToggle.R_4764_Y(this.v_4262_N() + 1.0f);
            sellToggle.n_1700_B(stack, mouseX, mouseY, alpha);
            float buyY = labelY + 7.0f;
            int textColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.R_4764_Y), q_3148_R.J_1907_R(K_1200_E.R_4764_Y) / 255.0f * alpha);
            l_3370_o.R_4764_Y[14].n_1700_B(stack, "\u0426\u0435\u043d\u0430 \u043f\u043e\u043a\u0443\u043f\u043a\u0438", (double)labelX, (double)(buyY + 8.0f), textColor);
            l_4397_i priceElem = this.w_1484_f.get(this.Q_4569_t);
            priceElem.n_1700_B(labelX + 41.0f);
            priceElem.J_1907_R(buyY);
            priceElem.R_4764_Y(82.0f);
            priceElem.n_1700_B(stack, mouseX, mouseY, alpha);
            c_1608_O sel = this.v_4262_N.get(this.Q_4569_t).R_4764_Y();
            if (!priceElem.n_1700_B && !(formatted = U_2474_c.n_1700_B(desired = (cur = sel.u_2550_I()) <= 0L ? "" : String.valueOf(cur))).equals(priceElem.h_1847_R())) {
                priceElem.n_1700_B(formatted);
            }
            sel.R_4764_Y(priceElem.h_1847_R());
            sel.J_1907_R(sellToggle.R_4764_Y().t_148_a());
            float sellY = labelY + 21.0f;
            l_4397_i sellPriceElem = this.s_956_w.get(this.Q_4569_t);
            l_3370_o.R_4764_Y[14].n_1700_B(stack, "% \u041f\u0440\u043e\u0434\u0430\u0436\u0438", (double)labelX, (double)(sellY + 8.0f), textColor);
            sellPriceElem.n_1700_B(labelX + 41.0f);
            sellPriceElem.J_1907_R(sellY);
            sellPriceElem.R_4764_Y(82.0f);
            sellPriceElem.n_1700_B(stack, mouseX, mouseY, alpha);
            c_1608_O sel2 = this.v_4262_N.get(this.Q_4569_t).R_4764_Y();
            sel2.G_564_y(sellPriceElem.h_1847_R());
        }
    }

    @Override
    public void n_1700_B(float mouseX, float mouseY, int button) {
        float elemBottom;
        float elemTop;
        this.u_1723_Y.forEach(e -> e.n_1700_B(mouseX, mouseY, button));
        List<H_1475_K> filteredItems = this.c_3005_b();
        float viewportTop = this.J_1907_R + 45.0f;
        float viewportHeight = this.w_1484_f() - 90.0f;
        boolean inViewport = F_747_P.n_1700_B(mouseX, mouseY, this.n_1700_B, viewportTop, this.v_4262_N(), viewportHeight);
        if (inViewport && button == 0) {
            for (H_1475_K elem : filteredItems) {
                elemTop = elem.v_4262_N();
                elemBottom = elemTop + 15.0f;
                if (!(elemBottom > viewportTop) || !(elemTop < viewportTop + viewportHeight)) continue;
                boolean hoveredItem = F_747_P.n_1700_B(mouseX, mouseY, elem.u_1723_Y(), elem.v_4262_N(), elem.w_1484_f(), 15.0f);
                boolean hoveredCheckbox = F_747_P.n_1700_B(mouseX, mouseY, elem.u_1723_Y() + 96.0f, elem.v_4262_N() + 3.0f, 9.0f, 9.0f);
                if (!hoveredItem || hoveredCheckbox) continue;
                if (!elem.Q_4569_t()) {
                    for (H_1475_K e2 : this.v_4262_N) {
                        e2.n_1700_B(false);
                    }
                    elem.n_1700_B(true);
                    this.Q_4569_t = this.v_4262_N.indexOf(elem);
                }
                return;
            }
        }
        if (inViewport) {
            for (H_1475_K elem : filteredItems) {
                elemTop = elem.v_4262_N();
                elemBottom = elemTop + 15.0f;
                if (!(elemBottom > viewportTop) || !(elemTop < viewportTop + viewportHeight)) continue;
                elem.n_1700_B(mouseX, mouseY, button);
            }
        }
        if (this.Q_4569_t != -1) {
            this.t_148_a.get(this.Q_4569_t).n_1700_B(mouseX, mouseY, button);
            this.s_956_w.get(this.Q_4569_t).n_1700_B(mouseX, mouseY, button);
        }
        if (this.Q_4569_t != -1) {
            this.w_1484_f.get(this.Q_4569_t).n_1700_B(mouseX, mouseY, button);
        }
        super.n_1700_B(mouseX, mouseY, button);
    }

    @Override
    public boolean n_1700_B(double mouseX, double mouseY, double delta) {
        float viewportTop = this.J_1907_R + 45.0f;
        float viewportHeight = this.w_1484_f() - 90.0f;
        if (F_747_P.n_1700_B((float)mouseX, (float)mouseY, this.n_1700_B, viewportTop, this.v_4262_N(), viewportHeight)) {
            float prev = this.P_4830_p;
            this.P_4830_p += (float)(delta * 10.0);
            this.P_4830_p = this.h_1847_R > viewportHeight ? u_530_F.n_1700_B(this.P_4830_p, -this.h_1847_R + viewportHeight, 0.0f) : 0.0f;
            return prev != this.P_4830_p;
        }
        return super.n_1700_B(mouseX, mouseY, delta);
    }

    private List<H_1475_K> c_3005_b() {
        String filter = this.u_1723_Y.get(0).h_1847_R().trim();
        ArrayList<H_1475_K> filtered = new ArrayList<H_1475_K>();
        if (filter.equalsIgnoreCase("!\u0432\u043a\u043b")) {
            for (H_1475_K elem : this.v_4262_N) {
                if (!((Boolean)elem.R_4764_Y().J_1907_R()).booleanValue()) continue;
                filtered.add(elem);
            }
        } else if (filter.equalsIgnoreCase("!\u0432\u044b\u043a\u043b")) {
            for (H_1475_K elem : this.v_4262_N) {
                if (((Boolean)elem.R_4764_Y().J_1907_R()).booleanValue()) continue;
                filtered.add(elem);
            }
        } else if (!filter.isEmpty()) {
            for (H_1475_K elem : this.v_4262_N) {
                String name = elem.R_4764_Y().n_1700_B();
                if (name == null || !name.toLowerCase().contains(filter.toLowerCase())) continue;
                filtered.add(elem);
            }
        } else {
            filtered.addAll(this.v_4262_N);
        }
        return filtered;
    }

    @Override
    public void n_1700_B(int keyCode, int scanCode, int modifiers) {
        this.u_1723_Y.forEach(e -> e.n_1700_B(keyCode, scanCode, modifiers));
        if (this.Q_4569_t != -1) {
            boolean ctrlV = keyCode == 86 && (modifiers & 2) != 0;
            l_4397_i priceElem = this.w_1484_f.get(this.Q_4569_t);
            l_4397_i sellElem = this.s_956_w.get(this.Q_4569_t);
            if (ctrlV) {
                String clip = GLFW.glfwGetClipboardString((long)GLFW.glfwGetCurrentContext());
                if (clip != null && !clip.isEmpty()) {
                    StringBuilder digits = new StringBuilder();
                    for (int i = 0; i < clip.length(); ++i) {
                        char ch = clip.charAt(i);
                        if (!Character.isDigit(ch)) continue;
                        digits.append(ch);
                    }
                    String toPaste = digits.toString();
                    if (priceElem.n_1700_B) {
                        String current = priceElem.h_1847_R();
                        if (current == null) {
                            current = "";
                        }
                        if (current.isEmpty()) {
                            int idx;
                            for (idx = 0; idx < toPaste.length() && toPaste.charAt(idx) == '0'; ++idx) {
                            }
                            toPaste = toPaste.substring(idx);
                        }
                        int capacity = Math.max(0, 8 - U_2474_c.J_1907_R(current));
                        int limit = Math.min(capacity, toPaste.length());
                        for (int i = 0; i < limit; ++i) {
                            priceElem.n_1700_B(toPaste.charAt(i), 0);
                        }
                        String formatted = U_2474_c.n_1700_B(priceElem.h_1847_R());
                        priceElem.n_1700_B(formatted);
                    } else if (sellElem.n_1700_B) {
                        String current = sellElem.h_1847_R();
                        if (current == null) {
                            current = "";
                        }
                        if (current.isEmpty()) {
                            int idx;
                            for (idx = 0; idx < toPaste.length() && toPaste.charAt(idx) == '0'; ++idx) {
                            }
                            toPaste = toPaste.substring(idx);
                        }
                        int capacity = Math.max(0, 4 - current.length());
                        int limit = Math.min(capacity, toPaste.length());
                        for (int i = 0; i < limit; ++i) {
                            sellElem.n_1700_B(toPaste.charAt(i), 0);
                        }
                    }
                }
                return;
            }
            priceElem.n_1700_B(keyCode, scanCode, modifiers);
            if (priceElem.n_1700_B && keyCode == 259) {
                String formatted = U_2474_c.n_1700_B(priceElem.h_1847_R());
                priceElem.n_1700_B(formatted);
            }
            sellElem.n_1700_B(keyCode, scanCode, modifiers);
        }
        super.n_1700_B(keyCode, scanCode, modifiers);
    }

    @Override
    public void n_1700_B(char codePoint, int modifiers) {
        this.u_1723_Y.forEach(e -> e.n_1700_B(codePoint, modifiers));
        if (this.Q_4569_t != -1) {
            l_4397_i priceElem = this.w_1484_f.get(this.Q_4569_t);
            l_4397_i sellElem = this.s_956_w.get(this.Q_4569_t);
            if (Character.isDigit(codePoint)) {
                if (priceElem.n_1700_B) {
                    String current = priceElem.h_1847_R();
                    if (current == null) {
                        current = "";
                    }
                    if (!(U_2474_c.J_1907_R(current) >= 8 || current.isEmpty() && codePoint == '0')) {
                        priceElem.n_1700_B(codePoint, modifiers);
                        String formatted = U_2474_c.n_1700_B(priceElem.h_1847_R());
                        priceElem.n_1700_B(formatted);
                    }
                } else if (sellElem.n_1700_B) {
                    String current = sellElem.h_1847_R();
                    if (current == null) {
                        current = "";
                    }
                    if (!(current.length() >= 4 || current.isEmpty() && codePoint == '0')) {
                        sellElem.n_1700_B(codePoint, modifiers);
                    }
                }
            }
        }
        super.n_1700_B(codePoint, modifiers);
    }

    @Generated
    public List<l_4397_i> P_4830_p() {
        return this.u_1723_Y;
    }

    @Generated
    public List<H_1475_K> h_1847_R() {
        return this.v_4262_N;
    }

    @Generated
    public List<l_4397_i> Q_4569_t() {
        return this.w_1484_f;
    }

    @Generated
    public List<p_3749_n> M_182_A() {
        return this.t_148_a;
    }

    @Generated
    public List<l_4397_i> t_1786_h() {
        return this.s_956_w;
    }

    @Generated
    public Animation multiplayerClientSuggestionProvider() {
        return this.u_2550_I;
    }

    @Generated
    public Animation w_1457_N() {
        return this.M_588_G;
    }

    @Generated
    public float Y_601_j() {
        return this.P_4830_p;
    }

    @Generated
    public float Y_259_p() {
        return this.h_1847_R;
    }

    @Generated
    public int Q_2552_b() {
        return this.Q_4569_t;
    }

    @Generated
    public String C_2741_M() {
        return this.M_182_A;
    }
}



