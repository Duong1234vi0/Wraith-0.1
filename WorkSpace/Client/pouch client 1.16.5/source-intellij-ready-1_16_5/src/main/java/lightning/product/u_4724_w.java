/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lightning.product.AutoBuy;
import lightning.product.C_1577_A;
import lightning.product.D_590_W;
import lightning.product.E_390_U;
import lightning.product.F_489_x;
import lightning.product.F_747_P;
import lightning.product.H_1952_g;
import lightning.product.H_2506_c;
import lightning.product.K_1200_E;
import lightning.product.N_2266_w;
import lightning.product.N_2592_G;
import lightning.product.N_4006_T;
import lightning.product.P_3504_Q;
import lightning.product.Q_4113_P;
import lightning.product.Interface;
import lightning.product.V_4557_X;
import lightning.product.V_537_k;
import lightning.product.Z_1243_X;
import lightning.product.Z_2491_A;
import lightning.product.MinecraftAccess;
import lightning.product.g_221_o;
import lightning.product.j_1654_T;
import lightning.product.Animation;
import lightning.product.k_2603_m;
import lightning.product.l_3370_o;
import lightning.product.l_3729_r;
import lightning.product.p_3749_n;
import lightning.product.q_3148_R;
import lightning.product.r_976_u;
import lightning.product.s_1124_y;
import lightning.product.s_3815_K;
import lightning.product.x_282_a;
import lightning.product.ModuleCategory;
import lombok.Generated;

public class u_4724_w
extends k_2603_m
implements MinecraftAccess {
    private final Animation G_564_y = new Animation(0.0f, 12.0f);
    private final List<H_1952_g> P_1922_E = new ArrayList<H_1952_g>();
    private final r_976_u u_1723_Y;
    private final C_1577_A v_4262_N;
    private final s_1124_y w_1484_f;
    private final r_976_u t_148_a;
    private boolean s_956_w = false;
    private boolean u_2550_I;
    private boolean M_588_G;
    private boolean P_4830_p;
    private boolean h_1847_R = false;
    private StringBuilder Q_4569_t = new StringBuilder();
    private long M_182_A = 0L;
    public static boolean n_1700_B = false;
    public static Animation J_1907_R = new Animation(0.0f, 15.0f);
    public static V_4557_X R_4764_Y = new V_4557_X();

    public u_4724_w(x_282_a titleIn) {
        super(titleIn);
        for (ModuleCategory category : ModuleCategory.values()) {
            this.P_1922_E.add(new H_1952_g(category));
        }
        this.P_1922_E.add(new q_3148_R());
        this.P_1922_E.add(new E_390_U());
        this.P_1922_E.add(new N_2592_G());
        this.t_148_a = this.u_1723_Y = new r_976_u(0.0f, 0.0f, 112.0f, 20.0f);
        this.v_4262_N = new C_1577_A(this.P_1922_E);
        this.w_1484_f = new s_1124_y();
        this.G_564_y.J_1907_R(0.0f);
    }

    @Override
    protected void init() {
        super.init();
        this.h_1847_R = false;
        this.G_564_y.J_1907_R(0.0f);
        this.G_564_y.n_1700_B(1.0f);
        this.n_1700_B();
    }

    @Override
    public void tick() {
        this.Q_4569_t();
        super.tick();
    }

    @Override
    public void closeScreen() {
        if (this.h_1847_R) {
            return;
        }
        this.h_1847_R = true;
        this.G_564_y.n_1700_B(0.0f);
        this.t_1786_h();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        P_3504_Q mousevec = l_3729_r.n_1700_B(mouseX, mouseY);
        mouseX = (int)mousevec.n_1700_B();
        mouseY = (int)mousevec.J_1907_R();
        u_4724_w.c_3005_b.s_956_w.n_1700_B(2.0f);
        this.u_2550_I = (Boolean)Interface.v_4262_N.J_1907_R();
        this.M_588_G = (Boolean)AutoBuy.v_4262_N.J_1907_R();
        this.P_4830_p = (Boolean)Interface.w_1484_f.J_1907_R();
        for (H_1952_g h_1952_g : this.P_1922_E) {
            if (h_1952_g instanceof q_3148_R) {
                ((q_3148_R)h_1952_g).A_4115_X().n_1700_B(this.u_2550_I ? 1.0f : 0.0f);
            }
            if (h_1952_g instanceof E_390_U) {
                ((E_390_U)h_1952_g).w_1457_N().n_1700_B(this.M_588_G ? 1.0f : 0.0f);
            }
            if (!(h_1952_g instanceof N_2592_G)) continue;
            ((N_2592_G)h_1952_g).M_182_A().n_1700_B(this.P_4830_p ? 1.0f : 0.0f);
        }
        this.G_564_y.n_1700_B(this.h_1847_R ? 0.0f : 1.0f);
        float animValue = this.G_564_y.n_1700_B();
        if (this.h_1847_R && this.G_564_y.R_4764_Y()) {
            super.closeScreen();
            return;
        }
        for (H_1952_g panel : this.P_1922_E) {
            if (this.n_1700_B(panel)) continue;
            this.n_1700_B(panel, c_3005_b.RealmsServerPing().Q_4569_t(), c_3005_b.RealmsServerPing().M_182_A(), (float)((ModuleCategory.values().length - 1) * 120));
            float panelAlpha = 1.0f;
            if (panel instanceof q_3148_R) {
                panelAlpha = ((q_3148_R)panel).A_4115_X().n_1700_B();
            }
            if (panel instanceof E_390_U) {
                panelAlpha = ((E_390_U)panel).w_1457_N().n_1700_B();
            }
            if (panel instanceof N_2592_G) {
                panelAlpha = ((N_2592_G)panel).M_182_A().n_1700_B();
            }
            if (!((panelAlpha *= animValue) > 0.0f)) continue;
            panel.n_1700_B(matrixStack, mouseX, mouseY, panelAlpha);
        }
        this.u_1723_Y.n_1700_B(((float)c_3005_b.RealmsServerPing().Q_4569_t() - this.u_1723_Y.G_564_y()) / 2.0f);
        this.u_1723_Y.J_1907_R((float)c_3005_b.RealmsServerPing().M_182_A() / 2.0f + 148.0f);
        this.u_1723_Y.n_1700_B(matrixStack, animValue);
        this.M_182_A();
        this.n_1700_B(matrixStack, c_3005_b.RealmsServerPing().Q_4569_t(), c_3005_b.RealmsServerPing().M_182_A(), animValue);
        this.n_1700_B(matrixStack, c_3005_b.RealmsServerPing().M_182_A(), animValue);
        this.J_1907_R(matrixStack, mouseX, mouseY, animValue);
        this.v_4262_N.n_1700_B(matrixStack, (float)mouseX, (float)mouseY, animValue);
        this.w_1484_f.n_1700_B(matrixStack, mouseX, mouseY, animValue);
        if (n_1700_B) {
            if (R_4764_Y.J_1907_R() < 2000L) {
                J_1907_R.n_1700_B(1.0f);
            } else {
                J_1907_R.n_1700_B(0.0f);
                if (J_1907_R.R_4764_Y() && J_1907_R.n_1700_B() <= 0.0f) {
                    n_1700_B = false;
                    J_1907_R.J_1907_R(0.0f);
                }
            }
            float f = J_1907_R.n_1700_B();
            if (f > 0.01f) {
                float centerX = (float)c_3005_b.RealmsServerPing().Q_4569_t() / 2.0f;
                float centerY = (float)c_3005_b.RealmsServerPing().M_182_A() / 2.0f;
                int zovColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.w_1457_N), f);
                String zovText = "ZOV";
                int fontIndex = 37;
                if (fontIndex >= l_3370_o.P_1922_E.length) {
                    fontIndex = l_3370_o.P_1922_E.length - 1;
                }
                if (l_3370_o.P_1922_E[fontIndex] != null) {
                    float textWidth = l_3370_o.P_1922_E[fontIndex].n_1700_B(zovText);
                    float textHeight = l_3370_o.P_1922_E[fontIndex].h_1847_R();
                    l_3370_o.P_1922_E[fontIndex].n_1700_B(matrixStack, zovText, (double)(centerX - textWidth / 2.0f), (double)(centerY - textHeight / 2.0f), zovColor);
                }
            }
        }
        u_4724_w.c_3005_b.s_956_w.R_4764_Y();
    }

    private void Q_4569_t() {
        D_590_W[] keys;
        for (D_590_W keyBinding : keys = new D_590_W[]{u_4724_w.c_3005_b.P_4830_p.O_508_d, u_4724_w.c_3005_b.P_4830_p.A_1038_p, u_4724_w.c_3005_b.P_4830_p.r_715_M, u_4724_w.c_3005_b.P_4830_p.i_1637_u, u_4724_w.c_3005_b.P_4830_p.Ping}) {
            boolean isKeyPressed = Q_4113_P.n_1700_B(c_3005_b.RealmsServerPing().t_148_a(), keyBinding.w_1484_f().J_1907_R());
            keyBinding.n_1700_B(isKeyPressed);
        }
    }

    private boolean n_1700_B(H_1952_g panel) {
        if (panel instanceof q_3148_R) {
            return ((q_3148_R)panel).A_4115_X().n_1700_B() <= 0.0f;
        }
        if (panel instanceof E_390_U) {
            return ((E_390_U)panel).w_1457_N().n_1700_B() <= 0.0f;
        }
        if (panel instanceof N_2592_G) {
            return ((N_2592_G)panel).M_182_A().n_1700_B() <= 0.0f;
        }
        return false;
    }

    private void n_1700_B(H_1952_g panel, int windowWidth, int windowHeight, float width) {
        float baseY = (float)windowHeight / 2.0f - 138.5f + 0.5f;
        float panelSpacing = 116.0f;
        float baseX = (float)windowWidth / 2.0f - width / 2.0f + 68.0f - 116.0f;
        if (panel instanceof q_3148_R) {
            panel.n_1700_B(baseX - 116.0f);
            panel.J_1907_R(baseY);
            return;
        }
        if (panel instanceof E_390_U) {
            panel.n_1700_B(baseX + (float)ModuleCategory.values().length * 116.0f);
            panel.J_1907_R(baseY);
            return;
        }
        if (panel instanceof N_2592_G) {
            panel.n_1700_B((float)windowWidth - panel.v_4262_N() - 10.0f);
            panel.J_1907_R((float)windowHeight - panel.w_1484_f() - 10.0f);
            return;
        }
        panel.n_1700_B(baseX + (float)panel.J_1907_R().ordinal() * 116.0f);
        panel.J_1907_R(baseY);
    }

    private void n_1700_B(g_221_o matrixStack, int windowWidth, int windowHeight, float guiAlpha) {
        ArrayList<String> lines = new ArrayList<String>();
        if (this.s_956_w) {
            lines.add("\u0421\u0431\u0440\u043e\u0441\u0438\u0442\u044c \u043d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0443 \u043c\u043e\u0434\u0443\u043b\u044f \u0432 \u043c\u0435\u043d\u044e - \u0421\u041a\u041c \u043f\u043e \u043d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0435");
            lines.add("\u0414\u0435\u0442\u0430\u043b\u044c\u043d\u043e \u043d\u0430\u0441\u0442\u0440\u043e\u0438\u0442\u044c \u0441\u043b\u0430\u0439\u0434\u0435\u0440 - \u0421TRL + \u0414\u0432\u0438\u0433\u0430\u0442\u044c \u043a\u043e\u043b\u0435\u0441\u0438\u043a\u043e\u043c (\u0412\u0432\u0435\u0440\u0445 / \u0412\u043d\u0438\u0437)");
            lines.add("\u0412\u044b\u043a\u0438\u043d\u0443\u0442\u044c \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u044b \u043e\u0434\u043d\u043e\u0433\u043e \u0442\u0438\u043f\u0430 \u0421TRL + SHIFT + Q");
        } else {
            lines.add("\u0414\u043e\u043f. \u0432\u043e\u0437\u043c\u043e\u0436\u043d\u043e\u0441\u0442\u0438 - \u0421TRL + G (\u0423\u0434\u0435\u0440\u0436\u0430\u0442\u044c)");
        }
        float baseY = (float)windowHeight / 2.0f + 179.0f;
        for (int i = 0; i < lines.size(); ++i) {
            int x = (int)((float)windowWidth / 2.0f - l_3370_o.J_1907_R[15].n_1700_B((String)lines.get(i)) / 2.0f);
            int y = (int)(baseY + (float)i * (l_3370_o.J_1907_R[15].h_1847_R() + 3.5f));
            l_3370_o.J_1907_R[15].n_1700_B(matrixStack, (String)lines.get(i), (double)x, (double)y, H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.Q_2552_b), q_3148_R.J_1907_R(K_1200_E.Q_2552_b) / 255.0f * guiAlpha));
        }
    }

    private void n_1700_B(g_221_o matrixStack, int windowHeight, float guiAlpha) {
    }

    private void J_1907_R(g_221_o matrixStack, int mouseX, int mouseY, float guiAlpha) {
        for (H_1952_g panel : this.P_1922_E) {
            float visibleTop = panel.P_1922_E() + 28.0f;
            float visibleBottom = visibleTop + panel.w_1484_f() - 28.0f;
            if ((float)mouseY < visibleTop || (float)mouseY > visibleBottom) continue;
            for (s_3815_K element : panel.t_148_a()) {
                float elementTop;
                float elementBottom;
                if (!F_747_P.n_1700_B(mouseX, mouseY, element.u_1723_Y(), element.v_4262_N(), element.w_1484_f(), element.t_148_a()) || !((elementBottom = (elementTop = element.v_4262_N()) + element.t_148_a()) > visibleTop) || !(elementTop < visibleBottom)) continue;
                String description = V_537_k.n_1700_B(element.u_2550_I(), elementTop, elementBottom, visibleTop, visibleBottom);
                if (description != null && !description.isEmpty()) {
                    int tooltipX = (int)(((float)c_3005_b.RealmsServerPing().Q_4569_t() - (l_3370_o.J_1907_R[18].n_1700_B(description) + l_3370_o.u_1723_Y[18].n_1700_B("M") + 11.0f)) / 2.0f);
                    int color = q_3148_R.n_1700_B(K_1200_E.q_2307_F);
                    int textCol = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.Q_2552_b), q_3148_R.J_1907_R(K_1200_E.Q_2552_b) / 255.0f * guiAlpha);
                    F_489_x.n_1700_B((float)(tooltipX - 4 - 10), (float)c_3005_b.RealmsServerPing().M_182_A() / 2.0f - 138.5f - 36.0f - 10.0f, l_3370_o.u_1723_Y[18].n_1700_B("M") + 10.0f + 20.0f, 40.0f, new Z_2491_A(9.0f, 9.0f, 1.0f, 1.0f), color, color, color, color, q_3148_R.J_1907_R(K_1200_E.q_2307_F) / 255.0f, 10.0f);
                    F_489_x.n_1700_B((float)(tooltipX - 4) + l_3370_o.u_1723_Y[18].n_1700_B("M") + 1.0f, (float)c_3005_b.RealmsServerPing().M_182_A() / 2.0f - 138.5f - 36.0f - 10.0f, l_3370_o.J_1907_R[18].n_1700_B(description) + 8.0f + 20.0f, 40.0f, new Z_2491_A(1.0f, 1.0f, 9.0f, 9.0f), color, color, color, color, q_3148_R.J_1907_R(K_1200_E.q_2307_F) / 255.0f, 10.0f);
                    F_489_x.n_1700_B((float)(tooltipX - 4), (float)c_3005_b.RealmsServerPing().M_182_A() / 2.0f - 138.5f - 36.0f, l_3370_o.u_1723_Y[18].n_1700_B("M") + 10.0f, 20.0f, new Z_2491_A(9.0f, 9.0f, 1.0f, 1.0f), q_3148_R.n_1700_B(K_1200_E.n_1700_B), 1.0f);
                    F_489_x.n_1700_B((float)(tooltipX - 4) + l_3370_o.u_1723_Y[18].n_1700_B("M") + 11.0f, (float)c_3005_b.RealmsServerPing().M_182_A() / 2.0f - 138.5f - 36.0f, l_3370_o.J_1907_R[18].n_1700_B(description) + 8.0f, 20.0f, new Z_2491_A(1.0f, 1.0f, 9.0f, 9.0f), q_3148_R.n_1700_B(K_1200_E.n_1700_B), 1.0f);
                    l_3370_o.u_1723_Y[18].n_1700_B(matrixStack, "M", (double)((float)tooltipX + 1.5f), (double)((float)c_3005_b.RealmsServerPing().M_182_A() / 2.0f - 138.5f - 28.0f), textCol);
                    l_3370_o.J_1907_R[18].n_1700_B(matrixStack, description, (double)((float)tooltipX + l_3370_o.u_1723_Y[18].n_1700_B("M") + 11.0f), (double)((float)c_3005_b.RealmsServerPing().M_182_A() / 2.0f - 138.5f - 30.0f), textCol);
                    return;
                }
                return;
            }
        }
    }

    private void M_182_A() {
        String searchText = this.u_1723_Y.u_1723_Y().toLowerCase();
        for (H_1952_g panel : this.P_1922_E) {
            if (panel instanceof q_3148_R) continue;
            if (searchText.isEmpty()) {
                panel.n_1700_B(new ArrayList<s_3815_K>(panel.s_956_w()));
                continue;
            }
            List<s_3815_K> filteredModules = panel.s_956_w().stream().filter(module -> module.u_2550_I().G_564_y().toLowerCase().contains(searchText)).collect(Collectors.toList());
            panel.n_1700_B(filteredModules);
        }
    }

    public void n_1700_B() {
        for (H_1952_g panel : this.P_1922_E) {
            if (panel instanceof q_3148_R || panel instanceof E_390_U || panel instanceof N_2592_G) continue;
            panel.n_1700_B();
        }
        this.M_182_A();
    }

    private void t_1786_h() {
        this.v_4262_N.n_1700_B(true);
        this.w_1484_f.n_1700_B(true);
        for (H_1952_g p : this.P_1922_E) {
            if (!(p instanceof q_3148_R)) continue;
            ((q_3148_R)p).Q_4569_t();
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        P_3504_Q mousevec = l_3729_r.n_1700_B((int)mouseX, (int)mouseY);
        mouseX = mousevec.n_1700_B();
        mouseY = mousevec.J_1907_R();
        for (H_1952_g panel : this.P_1922_E) {
            boolean scrolled;
            if (panel instanceof q_3148_R && !this.u_2550_I || panel instanceof N_2592_G && !this.P_4830_p || !F_747_P.n_1700_B((float)mouseX, (float)mouseY, panel.G_564_y(), panel.P_1922_E(), panel.v_4262_N(), panel.w_1484_f()) || !(scrolled = panel.n_1700_B(mouseX, mouseY, delta))) continue;
            if (this.v_4262_N.n_1700_B(panel)) {
                this.v_4262_N.n_1700_B(true);
            }
            if (this.w_1484_f.n_1700_B()) {
                this.w_1484_f.n_1700_B(true);
            }
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, delta);
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        for (H_1952_g panel : this.P_1922_E) {
            if (panel instanceof q_3148_R && !this.u_2550_I || panel instanceof N_2592_G && !this.P_4830_p) continue;
            panel.n_1700_B(codePoint, modifiers);
        }
        if (this.u_1723_Y.v_4262_N()) {
            this.u_1723_Y.n_1700_B(codePoint);
            return true;
        }
        return super.charTyped(codePoint, modifiers);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.u_1723_Y.v_4262_N() && this.u_1723_Y.n_1700_B(keyCode, scanCode, modifiers)) {
            return true;
        }
        if (this.w_1484_f.n_1700_B(keyCode)) {
            return true;
        }
        for (H_1952_g panel : this.P_1922_E) {
            if (panel instanceof q_3148_R && !this.u_2550_I || panel instanceof N_2592_G && !this.P_4830_p) continue;
            panel.n_1700_B(keyCode, scanCode, modifiers);
        }
        if (keyCode == 71 && (modifiers & 2) != 0) {
            this.s_956_w = true;
            return true;
        }
        if (keyCode == 74 && (modifiers & 2) != 0) {
            c_3005_b.n_1700_B(new Z_1243_X());
            return true;
        }
        if (this.n_1700_B(keyCode)) {
            return true;
        }
        if (keyCode == 256) {
            this.closeScreen();
            this.u_1723_Y.n_1700_B("");
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 71 || keyCode == 341 || keyCode == 345) {
            this.s_956_w = false;
        }
        return super.keyReleased(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        s_3815_K hovered;
        P_3504_Q mousevec = l_3729_r.n_1700_B((int)mouseX, (int)mouseY);
        mouseX = mousevec.n_1700_B();
        mouseY = mousevec.J_1907_R();
        for (H_1952_g h_1952_g : this.P_1922_E) {
            if (this.n_1700_B(h_1952_g)) continue;
            for (s_3815_K module : h_1952_g.t_148_a()) {
                for (N_4006_T elem : module.Q_4569_t()) {
                    if (!(elem instanceof N_2266_w)) continue;
                    N_2266_w bind = (N_2266_w)elem;
                    if (!bind.J_1907_R) continue;
                    bind.n_1700_B((float)mouseX, (float)mouseY, button);
                    return true;
                }
            }
        }
        if (button == 1) {
            for (H_1952_g h_1952_g : this.P_1922_E) {
                for (s_3815_K module : h_1952_g.t_148_a()) {
                    if (!module.P_4830_p()) continue;
                    for (N_4006_T elem : module.Q_4569_t()) {
                        p_3749_n be;
                        if (!(elem instanceof p_3749_n) || !(be = (p_3749_n)elem).n_1700_B() || be.P_1922_E() < 0.1f || !F_747_P.n_1700_B((float)mouseX, (float)mouseY, be.u_1723_Y() + be.w_1484_f() - 15.5f, be.v_4262_N() + (be.t_148_a() - 10.0f) - 6.5f, 10.0f, 10.0f)) continue;
                        if (this.w_1484_f.n_1700_B(be)) {
                            this.w_1484_f.n_1700_B(true);
                        } else {
                            this.t_1786_h();
                            this.w_1484_f.n_1700_B(be, true);
                            this.u_1723_Y.n_1700_B();
                        }
                        return true;
                    }
                }
            }
        }
        if (button == 0 && (hovered = this.n_1700_B((int)mouseX, (int)mouseY)) != null && this.n_1700_B(hovered, (float)mouseX, (float)mouseY) && !this.v_4262_N.J_1907_R((float)mouseX, (float)mouseY)) {
            this.v_4262_N.n_1700_B(true);
            for (H_1952_g p : this.P_1922_E) {
                if (!(p instanceof q_3148_R)) continue;
                ((q_3148_R)p).Q_4569_t();
            }
            this.w_1484_f.n_1700_B(hovered);
            this.u_1723_Y.n_1700_B();
            return true;
        }
        if (this.w_1484_f.n_1700_B() && this.w_1484_f.n_1700_B((float)mouseX, (float)mouseY, button)) {
            return true;
        }
        if (this.v_4262_N.n_1700_B((float)mouseX, (float)mouseY, button, this.G_564_y.n_1700_B())) {
            this.w_1484_f.n_1700_B(true);
            this.u_1723_Y.n_1700_B();
            return true;
        }
        for (H_1952_g h_1952_g : this.P_1922_E) {
            if (h_1952_g instanceof q_3148_R) {
                q_3148_R themeEditor = (q_3148_R)h_1952_g;
                if (this.u_2550_I && F_747_P.n_1700_B((float)mouseX, (float)mouseY, themeEditor.G_564_y(), themeEditor.P_1922_E(), themeEditor.v_4262_N(), themeEditor.w_1484_f())) {
                    this.t_1786_h();
                    themeEditor.n_1700_B((float)mouseX, (float)mouseY, button);
                    return true;
                }
            }
            if (!(h_1952_g instanceof N_2592_G)) continue;
            N_2592_G cfgPanel = (N_2592_G)h_1952_g;
            if (!this.P_4830_p || !F_747_P.n_1700_B((float)mouseX, (float)mouseY, cfgPanel.G_564_y(), cfgPanel.P_1922_E(), cfgPanel.v_4262_N(), cfgPanel.w_1484_f())) continue;
            this.t_1786_h();
            cfgPanel.n_1700_B((float)mouseX, (float)mouseY, button);
            return true;
        }
        if (this.u_1723_Y.n_1700_B((float)mouseX, (float)mouseY)) {
            return true;
        }
        for (H_1952_g h_1952_g : this.P_1922_E) {
            if (this.n_1700_B(h_1952_g) || !F_747_P.n_1700_B((float)mouseX, (float)mouseY, h_1952_g.G_564_y(), h_1952_g.P_1922_E(), h_1952_g.v_4262_N(), h_1952_g.w_1484_f())) continue;
            h_1952_g.n_1700_B((float)mouseX, (float)mouseY, button);
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        P_3504_Q mousevec = l_3729_r.n_1700_B((int)mouseX, (int)mouseY);
        mouseX = (int)mousevec.n_1700_B();
        mouseY = (int)mousevec.J_1907_R();
        for (H_1952_g panel : this.P_1922_E) {
            panel.J_1907_R((float)mouseX, (float)mouseY, button);
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    private s_3815_K n_1700_B(int mouseX, int mouseY) {
        for (H_1952_g panel : this.P_1922_E) {
            if (this.n_1700_B(panel) || panel instanceof q_3148_R || panel instanceof N_2592_G) continue;
            float visibleTop = panel.P_1922_E() + 28.0f;
            float visibleBottom = visibleTop + panel.w_1484_f() - 35.0f;
            if ((float)mouseY < visibleTop || (float)mouseY > visibleBottom) continue;
            for (s_3815_K element : panel.t_148_a()) {
                float elementTop;
                float elementBottom;
                if (!F_747_P.n_1700_B(mouseX, mouseY, element.u_1723_Y(), element.v_4262_N(), element.w_1484_f(), 15.0f) || !((elementBottom = (elementTop = element.v_4262_N()) + element.t_148_a()) > visibleTop) || !(elementTop < visibleBottom)) continue;
                return element;
            }
        }
        return null;
    }

    private boolean n_1700_B(s_3815_K element, float mouseX, float mouseY) {
        float textY = element.v_4262_N() + 7.0f;
        boolean hasSettings = element.Q_4569_t().stream().anyMatch(N_4006_T::n_1700_B);
        float iconX = element.u_1723_Y() + element.w_1484_f() - 10.0f;
        String bindLabel = element.u_2550_I().v_4262_N() != -100 ? j_1654_T.n_1700_B(element.u_2550_I().v_4262_N()) : "...";
        float bindTextWidth = l_3370_o.R_4764_Y[13].n_1700_B(bindLabel);
        float iconInsideW = l_3370_o.w_1484_f[12].n_1700_B("C");
        float sepWidth = 1.0f;
        float padding = 4.0f;
        float rectH = 9.0f;
        float rectW = Math.max(iconInsideW + sepWidth + bindTextWidth + padding * 2.0f, 20.0f);
        float rectRight = hasSettings ? iconX - 6.0f : element.u_1723_Y() + element.w_1484_f() - 5.0f;
        float bindRectX = rectRight - rectW;
        float bindRectY = textY + (l_3370_o.R_4764_Y[18].h_1847_R() - rectH) / 2.0f;
        return F_747_P.n_1700_B(mouseX, mouseY, bindRectX - 2.0f, bindRectY - 2.0f, rectW + 4.0f, rectH);
    }

    private boolean n_1700_B(int keyCode) {
        if (this.u_1723_Y.v_4262_N()) {
            return false;
        }
        long currentTime = System.currentTimeMillis();
        if (currentTime - this.M_182_A > 1000L) {
            this.Q_4569_t.setLength(0);
        }
        char pressedChar = '\u0000';
        if (keyCode == 90) {
            pressedChar = 'Z';
        } else if (keyCode == 79) {
            pressedChar = 'O';
        } else if (keyCode == 86) {
            pressedChar = 'V';
        }
        if (pressedChar != '\u0000') {
            String expected = "ZOV";
            int currentIndex = this.Q_4569_t.length();
            if (currentIndex < expected.length() && pressedChar == expected.charAt(currentIndex)) {
                this.Q_4569_t.append(pressedChar);
                this.M_182_A = currentTime;
                if (this.Q_4569_t.toString().equals("ZOV")) {
                    this.Q_4569_t.setLength(0);
                    return true;
                }
            } else {
                this.Q_4569_t.setLength(0);
                if (pressedChar == 'Z') {
                    this.Q_4569_t.append('Z');
                    this.M_182_A = currentTime;
                }
            }
        }
        return false;
    }

    @Generated
    public Animation J_1907_R() {
        return this.G_564_y;
    }

    @Generated
    public List<H_1952_g> R_4764_Y() {
        return this.P_1922_E;
    }

    @Generated
    public r_976_u G_564_y() {
        return this.u_1723_Y;
    }

    @Generated
    public C_1577_A P_1922_E() {
        return this.v_4262_N;
    }

    @Generated
    public s_1124_y u_1723_Y() {
        return this.w_1484_f;
    }

    @Generated
    public r_976_u v_4262_N() {
        return this.t_148_a;
    }

    @Generated
    public boolean w_1484_f() {
        return this.s_956_w;
    }

    @Generated
    public boolean t_148_a() {
        return this.u_2550_I;
    }

    @Generated
    public boolean s_956_w() {
        return this.M_588_G;
    }

    @Generated
    public boolean u_2550_I() {
        return this.P_4830_p;
    }

    @Generated
    public boolean M_588_G() {
        return this.h_1847_R;
    }

    @Generated
    public StringBuilder P_4830_p() {
        return this.Q_4569_t;
    }

    @Generated
    public long h_1847_R() {
        return this.M_182_A;
    }
}



