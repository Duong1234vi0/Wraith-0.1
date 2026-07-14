/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.I_1084_e;
import lightning.product.K_1200_E;
import lightning.product.R_1148_E;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.Z_3822_q;
import lightning.product.g_221_o;
import lightning.product.i_4833_u;
import lightning.product.j_1654_T;
import lightning.product.k_2603_m;
import lightning.product.l_3370_o;
import lightning.product.ServerHelper;
import lightning.product.o_1343_U;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.q_1613_l;
import lightning.product.q_1803_e;
import lightning.product.q_3148_R;
import lightning.product.KeyBindSetting;
import lightning.product.Items;
import lightning.product.r_976_u;

public class m_2594_d
extends k_2603_m {
    private final o_1343_U n_1700_B;
    private final List<R_1148_E> J_1907_R;
    private r_976_u R_4764_Y;
    private List<q_1613_l> G_564_y;
    private List<q_1613_l> P_1922_E;
    private float u_1723_Y = 0.0f;
    private R_1148_E v_4262_N;
    private KeyBindSetting w_1484_f;
    private BooleanSetting t_148_a;
    private BooleanSetting s_956_w;
    private BooleanSetting u_2550_I;
    private BooleanSetting M_588_G;
    private BooleanSetting P_4830_p;
    private int h_1847_R = -1;
    private boolean Q_4569_t = false;
    private static final float M_182_A = 20.0f;
    private static final float t_1786_h = 250.0f;
    private static final float multiplayerClientSuggestionProvider = 220.0f;
    private static final float w_1457_N = 150.0f;

    public m_2594_d(o_1343_U helper) {
        super(I_1084_e.n_1700_B);
        this.n_1700_B = helper;
        this.J_1907_R = new ArrayList<R_1148_E>(helper.w_1484_f());
        this.G_564_y = new ArrayList<q_1613_l>();
        V_3137_a.e_2887_G.forEach(item -> {
            if (item != Items.n_1700_B) {
                this.G_564_y.add((q_1613_l)item);
            }
        });
        this.P_1922_E = new ArrayList<q_1613_l>(this.G_564_y);
    }

    @Override
    protected void init() {
        super.init();
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        this.R_4764_Y = new r_976_u((float)centerX - 110.0f, (float)centerY - 125.0f - 30.0f, 220.0f, 20.0f);
        if (this.v_4262_N != null) {
            this.w_1484_f = new KeyBindSetting("\u0411\u0438\u043d\u0434", () -> true);
            this.w_1484_f.n_1700_B(this.v_4262_N.R_4764_Y());
            this.t_148_a = new BooleanSetting("\u041a\u0438\u0434\u0430\u0442\u044c \u0432\u043e \u0432\u0440\u0430\u0433\u0430", this.v_4262_N.G_564_y(), () -> true);
            this.s_956_w = new BooleanSetting("\u0411\u0440\u043e\u0441\u0438\u0442\u044c \u0432\u043f\u0435\u0440\u0435\u0434", this.v_4262_N.u_1723_Y(), () -> true);
            this.u_2550_I = new BooleanSetting("\u0410\u043a\u0442\u0438\u0432\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u043d\u0430 \u043f\u0440\u0430\u0432\u044b\u0439 \u0448\u0438\u0444\u0442", this.v_4262_N.P_1922_E(), () -> true);
            this.M_588_G = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u0432 \u0431\u043e\u044e", this.v_4262_N.v_4262_N(), () -> true);
            this.P_4830_p = new BooleanSetting("\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c \u043d\u0430 \u0441\u043e\u044e\u0437\u043d\u0438\u043a\u043e\u0432", this.v_4262_N.w_1484_f(), () -> true);
        }
    }

    @Override
    public void render(g_221_o matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        float listX = (float)centerX - 110.0f;
        float listY = (float)centerY - 125.0f;
        F_489_x.n_1700_B(listX, listY, 220.0f, 250.0f, 5.0f, q_3148_R.n_1700_B(K_1200_E.n_1700_B), 1.0f);
        F_489_x.J_1907_R(listX, listY, 220.0f, 250.0f, 5.0f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R));
        this.R_4764_Y.n_1700_B(matrices, 1.0f);
        float itemsStartY = listY + 6.0f;
        float itemsEndY = listY + 250.0f - 6.0f;
        float itemsHeight = itemsEndY - itemsStartY;
        i_4833_u.n_1700_B(listX, itemsStartY, 220.0, itemsHeight);
        float maxVisibleItems = itemsHeight / 20.0f;
        int startIndex = Math.max(0, (int)(-this.u_1723_Y / 20.0f));
        int endIndex = Math.min(this.P_1922_E.size(), startIndex + (int)maxVisibleItems + 2);
        for (int i = startIndex; i < endIndex; ++i) {
            boolean selected;
            q_1613_l item = this.P_1922_E.get(i);
            float itemY = itemsStartY + (float)i * 20.0f + this.u_1723_Y;
            boolean hovered = (float)mouseX >= listX && (float)mouseX <= listX + 220.0f && (float)mouseY >= itemY && (float)mouseY <= itemY + 20.0f;
            boolean bl = selected = i == this.h_1847_R;
            int bgColor = selected ? H_2506_c.J_1907_R(q_3148_R.n_1700_B(K_1200_E.n_1700_B), 0.3f) : (hovered ? H_2506_c.J_1907_R(q_3148_R.n_1700_B(K_1200_E.n_1700_B), 0.1f) : q_3148_R.n_1700_B(K_1200_E.n_1700_B));
            F_489_x.n_1700_B(listX + 2.0f, itemY, 216.0f, 19.0f, 3.0f, bgColor);
            F_489_x.n_1700_B(new Z_1993_T(item), listX + 4.0f, itemY + 3.5f, 0.7f);
            Object itemName = new Z_1993_T(item).multiplayerClientSuggestionProvider().getString();
            if (((String)itemName).length() > 20) {
                itemName = ((String)itemName).substring(0, 17) + "...";
            }
            l_3370_o.P_1922_E[14].n_1700_B(matrices, (String)itemName, (double)(listX + 18.0f), (double)(itemY + 7.0f), q_3148_R.n_1700_B(K_1200_E.R_4764_Y));
        }
        i_4833_u.n_1700_B();
        float configPanelX = (float)centerX + 110.0f + 20.0f;
        float configPanelY = (float)centerY - 125.0f;
        float configPanelWidth = 150.0f;
        float configPanelHeight = 250.0f;
        if (this.v_4262_N != null) {
            F_489_x.n_1700_B(configPanelX, configPanelY, configPanelWidth, configPanelHeight, 5.0f, q_3148_R.n_1700_B(K_1200_E.n_1700_B), 1.0f);
            F_489_x.J_1907_R(configPanelX, configPanelY, configPanelWidth, configPanelHeight, 5.0f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R));
            String itemName = new Z_1993_T(this.v_4262_N.n_1700_B()).multiplayerClientSuggestionProvider().getString();
            l_3370_o.P_1922_E[16].n_1700_B(matrices, itemName, (double)(configPanelX + 6.5f), (double)(configPanelY + 12.0f), q_3148_R.n_1700_B(K_1200_E.R_4764_Y));
            float yOffset = configPanelY + 30.0f;
            String bindText = this.Q_4569_t ? "..." : ((Integer)this.w_1484_f.J_1907_R() == -1 ? "None" : j_1654_T.n_1700_B((Integer)this.w_1484_f.J_1907_R()));
            float bindTextWidth = l_3370_o.P_1922_E[16].n_1700_B(bindText);
            float bindBoxWidth = Math.max(bindTextWidth + 10.0f, 60.0f);
            float bindBoxHeight = 12.0f;
            float bindBoxX = configPanelX + (150.0f - bindBoxWidth) / 2.0f;
            float bindBoxY = yOffset - 5.0f;
            l_3370_o.P_1922_E[16].n_1700_B(matrices, "\u0411\u0438\u043d\u0434", (double)(bindBoxX - 38.0f), (double)(yOffset - 2.0f), q_3148_R.n_1700_B(K_1200_E.R_4764_Y));
            int bindBgColor = this.Q_4569_t ? q_3148_R.n_1700_B(K_1200_E.M_588_G) : q_3148_R.n_1700_B(K_1200_E.P_4830_p);
            F_489_x.n_1700_B(bindBoxX, bindBoxY, bindBoxWidth, bindBoxHeight, 3.0f, bindBgColor);
            F_489_x.J_1907_R(bindBoxX, bindBoxY, bindBoxWidth, bindBoxHeight, 3.0f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * 0.5f);
            float bindTextX = bindBoxX + (bindBoxWidth - bindTextWidth) / 2.0f;
            float bindTextY = bindBoxY + (bindBoxHeight - l_3370_o.P_1922_E[13].h_1847_R()) / 2.0f + 1.0f;
            l_3370_o.P_1922_E[13].n_1700_B(matrices, bindText, (double)bindTextX, (double)bindTextY, q_3148_R.n_1700_B(K_1200_E.R_4764_Y));
            this.n_1700_B(matrices, this.t_148_a, configPanelX + 6.8f, yOffset += 20.0f, mouseX, mouseY);
            this.n_1700_B(matrices, this.s_956_w, configPanelX + 6.8f, yOffset += 20.0f, mouseX, mouseY);
            this.n_1700_B(matrices, this.u_2550_I, configPanelX + 6.8f, yOffset += 20.0f, mouseX, mouseY);
            this.n_1700_B(matrices, this.M_588_G, configPanelX + 6.8f, yOffset += 20.0f, mouseX, mouseY);
            this.n_1700_B(matrices, this.P_4830_p, configPanelX + 6.8f, yOffset += 20.0f, mouseX, mouseY);
        }
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int clickedIndex;
        if (this.R_4764_Y.n_1700_B((float)mouseX, (float)mouseY)) {
            return true;
        }
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        float listX = (float)centerX - 110.0f;
        float listY = (float)centerY - 125.0f;
        float itemsStartY = listY + 5.0f;
        if (mouseX >= (double)listX && mouseX <= (double)(listX + 220.0f) && mouseY >= (double)listY && mouseY <= (double)(listY + 250.0f) && (clickedIndex = (int)((mouseY - (double)itemsStartY - (double)this.u_1723_Y) / 20.0)) >= 0 && clickedIndex < this.P_1922_E.size()) {
            this.h_1847_R = clickedIndex;
            q_1613_l selectedItem = this.P_1922_E.get(clickedIndex);
            this.v_4262_N = this.J_1907_R.stream().filter(config -> config.n_1700_B() == selectedItem).findFirst().orElse(null);
            if (this.v_4262_N == null) {
                this.v_4262_N = new R_1148_E(selectedItem, new Z_1993_T(selectedItem).multiplayerClientSuggestionProvider().getString());
                this.J_1907_R.add(this.v_4262_N);
            }
            this.w_1484_f = new KeyBindSetting("\u0411\u0438\u043d\u0434", () -> true);
            this.w_1484_f.n_1700_B(this.v_4262_N.R_4764_Y());
            this.t_148_a = new BooleanSetting("\u041a\u0438\u0434\u0430\u0442\u044c \u0432\u043e \u0432\u0440\u0430\u0433\u0430", this.v_4262_N.G_564_y(), () -> true);
            this.s_956_w = new BooleanSetting("\u0411\u0440\u043e\u0441\u0438\u0442\u044c \u0432\u043f\u0435\u0440\u0435\u0434", this.v_4262_N.u_1723_Y(), () -> true);
            this.u_2550_I = new BooleanSetting("\u0410\u043a\u0442\u0438\u0432\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u043d\u0430 \u043f\u0440\u0430\u0432\u044b\u0439 \u0448\u0438\u0444\u0442", this.v_4262_N.P_1922_E(), () -> true);
            this.M_588_G = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u0432 \u0431\u043e\u044e", this.v_4262_N.v_4262_N(), () -> true);
            this.P_4830_p = new BooleanSetting("\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c \u043d\u0430 \u0441\u043e\u044e\u0437\u043d\u0438\u043a\u043e\u0432", this.v_4262_N.w_1484_f(), () -> true);
            return true;
        }
        if (this.v_4262_N != null) {
            float configPanelX = (float)centerX + 110.0f + 20.0f;
            float configPanelY = (float)centerY - 125.0f;
            float yOffset = configPanelY + 30.0f;
            String bindText = (Integer)this.w_1484_f.J_1907_R() == -1 ? "None" : j_1654_T.n_1700_B((Integer)this.w_1484_f.J_1907_R());
            float bindTextWidth = l_3370_o.P_1922_E[13].n_1700_B(bindText);
            float bindBoxWidth = Math.max(bindTextWidth + 10.0f, 60.0f);
            float bindBoxHeight = 12.0f;
            float bindBoxX = configPanelX + (150.0f - bindBoxWidth) / 2.0f;
            float bindBoxY = yOffset - 5.0f;
            if (mouseX >= (double)bindBoxX && mouseX <= (double)(bindBoxX + bindBoxWidth) && mouseY >= (double)bindBoxY && mouseY <= (double)(bindBoxY + bindBoxHeight) && button == 0) {
                this.Q_4569_t = true;
                return true;
            }
            if (this.n_1700_B(this.t_148_a, configPanelX + 6.8f, yOffset += 20.0f, mouseX, mouseY, button)) {
                this.t_148_a.n_1700_B((Boolean)(this.t_148_a.t_148_a() == false ? 1 : 0));
                return true;
            }
            if (this.n_1700_B(this.s_956_w, configPanelX + 6.8f, yOffset += 20.0f, mouseX, mouseY, button)) {
                this.s_956_w.n_1700_B((Boolean)(this.s_956_w.t_148_a() == false ? 1 : 0));
                return true;
            }
            if (this.n_1700_B(this.u_2550_I, configPanelX + 6.8f, yOffset += 20.0f, mouseX, mouseY, button)) {
                this.u_2550_I.n_1700_B((Boolean)(this.u_2550_I.t_148_a() == false ? 1 : 0));
                return true;
            }
            if (this.n_1700_B(this.M_588_G, configPanelX + 6.8f, yOffset += 20.0f, mouseX, mouseY, button)) {
                this.M_588_G.n_1700_B((Boolean)(this.M_588_G.t_148_a() == false ? 1 : 0));
                return true;
            }
            if (this.n_1700_B(this.P_4830_p, configPanelX + 6.8f, yOffset += 20.0f, mouseX, mouseY, button)) {
                this.P_4830_p.n_1700_B((Boolean)(this.P_4830_p.t_148_a() == false ? 1 : 0));
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        float listX = (float)centerX - 110.0f;
        float listY = (float)centerY - 125.0f;
        if (mouseX >= (double)listX && mouseX <= (double)(listX + 220.0f) && mouseY >= (double)listY && mouseY <= (double)(listY + 250.0f)) {
            float itemsStartY = listY + 6.0f;
            float itemsEndY = listY + 250.0f - 6.0f;
            float availableHeight = itemsEndY - itemsStartY;
            float totalHeight = (float)this.P_1922_E.size() * 20.0f;
            float maxScroll = Math.max(0.0f, totalHeight - availableHeight);
            this.u_1723_Y += (float)(delta * 10.0);
            this.u_1723_Y = Math.max(-maxScroll, Math.min(0.0f, this.u_1723_Y));
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, delta);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.R_4764_Y.n_1700_B(keyCode, scanCode, modifiers)) {
            this.n_1700_B();
            return true;
        }
        if (this.Q_4569_t && this.v_4262_N != null && this.w_1484_f != null) {
            if (keyCode == 256 || keyCode == 261) {
                this.w_1484_f.n_1700_B(-1);
                this.v_4262_N.n_1700_B(-1);
                this.Q_4569_t = false;
                return true;
            }
            if (keyCode != -1) {
                this.w_1484_f.n_1700_B(keyCode);
                this.v_4262_N.n_1700_B(keyCode);
                this.Q_4569_t = false;
                return true;
            }
        }
        if (keyCode == 256) {
            if (this.Q_4569_t) {
                this.Q_4569_t = false;
                return true;
            }
            this.closeScreen();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        if (this.R_4764_Y.n_1700_B(codePoint)) {
            this.n_1700_B();
            return true;
        }
        return super.charTyped(codePoint, modifiers);
    }

    private void n_1700_B() {
        String filter = this.R_4764_Y.u_1723_Y().toLowerCase();
        this.P_1922_E = filter.isEmpty() ? new ArrayList<q_1613_l>(this.G_564_y) : this.G_564_y.stream().filter(item -> {
            String name = new Z_1993_T((q_1803_e)item).multiplayerClientSuggestionProvider().getString().toLowerCase();
            return name.contains(filter);
        }).collect(Collectors.toList());
        this.u_1723_Y = 0.0f;
        this.h_1847_R = -1;
    }

    private void J_1907_R() {
        if (this.v_4262_N != null) {
            this.v_4262_N.n_1700_B((Integer)this.w_1484_f.J_1907_R());
            this.v_4262_N.n_1700_B(this.t_148_a.t_148_a());
            this.v_4262_N.R_4764_Y(this.s_956_w.t_148_a());
            this.v_4262_N.J_1907_R(this.u_2550_I.t_148_a());
            this.v_4262_N.G_564_y(this.M_588_G.t_148_a());
            this.v_4262_N.P_1922_E(this.P_4830_p.t_148_a());
            this.n_1700_B.n_1700_B(this.J_1907_R);
            ServerHelper manager = (ServerHelper)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(ServerHelper.class);
            if (manager != null) {
                manager.h_1847_R();
            }
        }
    }

    private void n_1700_B(g_221_o matrices, BooleanSetting setting, float x, float y, int mouseX, int mouseY) {
        int bgColor;
        String name = setting.n_1700_B();
        float textWidth = l_3370_o.P_1922_E[14].n_1700_B(name);
        float maxTextWidth = 146.0f;
        l_3370_o.P_1922_E[14].n_1700_B(matrices, name, x, y, maxTextWidth, q_3148_R.n_1700_B(K_1200_E.R_4764_Y), (float)mouseX >= x && (float)mouseX <= x + maxTextWidth && (float)mouseY >= y && (float)mouseY <= y + 15.0f, new Z_3822_q.n_1700_B());
        float switchX = x + Math.min(textWidth + 12.0f, 122.0f);
        float switchY = y - 3.5f;
        float switchW = 24.0f;
        float switchH = 12.0f;
        boolean hovered = (float)mouseX >= switchX && (float)mouseX <= switchX + switchW && (float)mouseY >= switchY && (float)mouseY <= switchY + switchH;
        int n = bgColor = setting.t_148_a() != false ? q_3148_R.n_1700_B(K_1200_E.M_588_G) : q_3148_R.n_1700_B(K_1200_E.P_4830_p);
        if (hovered) {
            bgColor = H_2506_c.J_1907_R(bgColor, 0.1f);
        }
        F_489_x.n_1700_B(switchX, switchY, switchW, switchH, 3.0f, bgColor);
        float knobX = switchX + (setting.t_148_a() != false ? switchW - 10.0f : 2.0f);
        F_489_x.n_1700_B(knobX, switchY + 1.5f, 9.0f, 9.0f, 2.0f, -1);
    }

    private boolean n_1700_B(BooleanSetting setting, float x, float y, double mouseX, double mouseY, int button) {
        if (button != 0) {
            return false;
        }
        String name = setting.n_1700_B();
        float textWidth = l_3370_o.P_1922_E[14].n_1700_B(name);
        float switchX = x + Math.min(textWidth + 12.0f, 122.0f);
        float switchY = y - 3.5f;
        float switchW = 24.0f;
        float switchH = 12.0f;
        return mouseX >= (double)switchX && mouseX <= (double)(switchX + switchW) && mouseY >= (double)switchY && mouseY <= (double)(switchY + switchH);
    }

    @Override
    public void onClose() {
        this.J_1907_R();
        this.n_1700_B.n_1700_B(this.J_1907_R);
        super.onClose();
    }
}



