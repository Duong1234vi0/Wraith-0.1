/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import lightning.product.F_489_x;
import lightning.product.F_747_P;
import lightning.product.H_2506_c;
import lightning.product.K_1200_E;
import lightning.product.N_4006_T;
import lightning.product.Module;
import lightning.product.MinecraftAccess;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.Easing;
import lightning.product.j_1654_T;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.l_4397_i;
import lightning.product.p_3749_n;
import lightning.product.q_3148_R;
import lightning.product.s_3815_K;
import lombok.Generated;
import org.lwjgl.opengl.GL11;

public class w_2152_d
implements MinecraftAccess {
    private final N_4006_T J_1907_R;
    private final Animation R_4764_Y = new Animation(0.0f, 12.0f);
    private final Animation G_564_y = new Animation(0.0f, 6.0f);
    private final Animation P_1922_E = new Animation(0.0f, 5.0f);
    private final Animation u_1723_Y = new Animation(0.0f, 10.0f);
    private final Animation v_4262_N = new Animation(0.0f, 10.0f);
    private float w_1484_f;
    private float t_148_a;
    private final float s_956_w = 70.0f;
    private final float u_2550_I = 30.0f;
    private boolean M_588_G = false;
    public boolean n_1700_B = false;

    public w_2152_d(N_4006_T element) {
        this.J_1907_R = element;
        this.w_1484_f = element.u_1723_Y() + element.w_1484_f();
        this.t_148_a = element.v_4262_N() + 4.0f;
        boolean isVisible = element instanceof s_3815_K ? ((s_3815_K)element).u_2550_I().s_956_w() : ((p_3749_n)element).R_4764_Y().P_4830_p();
        this.G_564_y.J_1907_R(isVisible ? 0.0f : 1.0f);
        Module.n_1700_B initialMode = element instanceof s_3815_K ? ((s_3815_K)element).u_2550_I().t_148_a() : ((p_3749_n)element).R_4764_Y().M_588_G();
        this.P_1922_E.J_1907_R(initialMode == Module.n_1700_B.J_1907_R ? 1.0f : 0.0f);
        l_4397_i.R_4764_Y();
    }

    public void n_1700_B(g_221_o matrixStack, float guiAlpha) {
        Module.n_1700_B currentMode = this.J_1907_R instanceof s_3815_K ? ((s_3815_K)this.J_1907_R).u_2550_I().t_148_a() : ((p_3749_n)this.J_1907_R).R_4764_Y().M_588_G();
        this.P_1922_E.R_4764_Y(5.0f);
        this.P_1922_E.n_1700_B(Easing.u_1723_Y);
        this.P_1922_E.n_1700_B(currentMode == Module.n_1700_B.J_1907_R ? 1.0f : 0.0f);
        float tmValue = this.P_1922_E.n_1700_B();
        if (this.n_1700_B && (double)this.R_4764_Y.n_1700_B() <= 0.01) {
            if (this.J_1907_R instanceof s_3815_K) {
                ((s_3815_K)this.J_1907_R).J_1907_R(false);
            } else {
                ((p_3749_n)this.J_1907_R).n_1700_B(false);
            }
            this.n_1700_B = false;
            this.M_588_G = false;
        }
        this.R_4764_Y.n_1700_B(this.n_1700_B ? 0.0f : ((this.J_1907_R instanceof s_3815_K ? ((s_3815_K)this.J_1907_R).h_1847_R() : ((p_3749_n)this.J_1907_R).P_4830_p()) ? 1.0f : 0.0f));
        if (this.R_4764_Y.n_1700_B() <= 0.0f) {
            return;
        }
        float panelAlpha = this.R_4764_Y.n_1700_B() * guiAlpha;
        F_489_x.n_1700_B(this.w_1484_f, this.t_148_a, 70.0f, 30.0f, 4.0f, q_3148_R.n_1700_B(K_1200_E.n_1700_B), panelAlpha);
        F_489_x.J_1907_R(this.w_1484_f, this.t_148_a, 70.0f, 30.0f, 4.0f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * panelAlpha);
        l_3370_o.J_1907_R[14].n_1700_B(matrixStack, "\u0411\u0438\u043d\u0434", (double)(this.w_1484_f + 5.0f), (double)(this.t_148_a + 7.0f), H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.R_4764_Y), q_3148_R.J_1907_R(K_1200_E.R_4764_Y) / 255.0f * panelAlpha));
        float mouseX = (float)w_2152_d.c_3005_b.h_1847_R.G_564_y() * (float)c_3005_b.RealmsServerPing().Q_4569_t() / (float)c_3005_b.RealmsServerPing().P_4830_p();
        float mouseY = (float)w_2152_d.c_3005_b.h_1847_R.P_1922_E() * (float)c_3005_b.RealmsServerPing().M_182_A() / (float)c_3005_b.RealmsServerPing().h_1847_R();
        boolean isVisibleHovered = F_747_P.n_1700_B(mouseX, mouseY, this.w_1484_f + 23.5f, this.t_148_a + 4.0f, 9.0f, 8.0f);
        this.u_1723_Y.R_4764_Y(10.0f);
        this.u_1723_Y.n_1700_B(Easing.u_1723_Y);
        float prevResetAlpha = this.v_4262_N.n_1700_B();
        if (isVisibleHovered && prevResetAlpha <= 0.0f) {
            this.u_1723_Y.n_1700_B(1.0f);
        } else {
            this.u_1723_Y.n_1700_B(0.0f);
        }
        float hideAlpha = this.u_1723_Y.n_1700_B();
        if (hideAlpha > 0.0f) {
            float effectiveHide = hideAlpha * panelAlpha;
            float widthHide = l_3370_o.J_1907_R[12].n_1700_B("\u0421\u043a\u0440\u044b\u0432\u0430\u0442\u044c") + 4.0f;
            F_489_x.n_1700_B(this.w_1484_f + 23.5f, this.t_148_a - 4.5f, widthHide, 9.0f, 1.0f, q_3148_R.n_1700_B(K_1200_E.Y_259_p), effectiveHide);
            int textColor = q_3148_R.n_1700_B(K_1200_E.R_4764_Y);
            int textColorWithAlpha = H_2506_c.n_1700_B(textColor, q_3148_R.J_1907_R(K_1200_E.R_4764_Y) / 255.0f * effectiveHide);
            l_3370_o.J_1907_R[12].n_1700_B(matrixStack, "\u0421\u043a\u0440\u044b\u0432\u0430\u0442\u044c", (double)(this.w_1484_f + 25.5f), (double)(this.t_148_a - 1.0f), textColorWithAlpha);
        }
        boolean isVisible = this.J_1907_R instanceof s_3815_K ? ((s_3815_K)this.J_1907_R).u_2550_I().s_956_w() : ((p_3749_n)this.J_1907_R).R_4764_Y().P_4830_p();
        this.G_564_y.R_4764_Y(6.0f);
        this.G_564_y.n_1700_B(Easing.u_1723_Y);
        this.G_564_y.n_1700_B(isVisible ? 0.0f : 1.0f);
        float prog = this.G_564_y.n_1700_B();
        if (prog > 0.0f) {
            float cx = this.w_1484_f + 29.0f;
            float cy = this.t_148_a + 9.0f;
            int len = (int)(8.0 * Math.sqrt(2.0));
            c_4037_x.v_4276_D();
            c_4037_x.R_4764_Y(cx, cy, 0.0f);
            GL11.glRotatef((float)45.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            int crossColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.s_956_w), q_3148_R.n_1700_B(K_1200_E.u_2550_I), prog);
            crossColor = H_2506_c.n_1700_B(crossColor, q_3148_R.J_1907_R(K_1200_E.s_956_w) / 255.0f * panelAlpha);
            F_489_x.n_1700_B((float)(-len) / 2.0f, -1.0f, (float)len * prog, 1.5f, 0.0f, crossColor);
            c_4037_x.d_2461_k();
        }
        int iconColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.s_956_w), q_3148_R.n_1700_B(K_1200_E.u_2550_I), prog);
        l_3370_o.u_1723_Y[14].n_1700_B(matrixStack, "Y", (double)(this.w_1484_f + 25.5f), (double)(this.t_148_a + 8.5f), H_2506_c.n_1700_B(iconColor, q_3148_R.J_1907_R(K_1200_E.s_956_w) / 255.0f * panelAlpha));
        boolean isBinHovered = F_747_P.n_1700_B(mouseX, mouseY, this.w_1484_f + 34.0f, this.t_148_a + 5.0f, 8.0f, 8.0f);
        this.v_4262_N.R_4764_Y(10.0f);
        this.v_4262_N.n_1700_B(Easing.u_1723_Y);
        if (isBinHovered && hideAlpha <= 0.0f) {
            this.v_4262_N.n_1700_B(1.0f);
        } else {
            this.v_4262_N.n_1700_B(0.0f);
        }
        float resetAlpha = this.v_4262_N.n_1700_B();
        if (resetAlpha > 0.0f) {
            float effectiveReset = resetAlpha * panelAlpha;
            float widthReset = l_3370_o.J_1907_R[12].n_1700_B("\u0421\u0431\u0440\u043e\u0441\u0438\u0442\u044c") + 4.0f;
            F_489_x.n_1700_B(this.w_1484_f + 33.0f, this.t_148_a - 4.5f, widthReset, 9.0f, 1.0f, q_3148_R.n_1700_B(K_1200_E.Y_259_p), effectiveReset);
            int textColor = q_3148_R.n_1700_B(K_1200_E.R_4764_Y);
            int textColorWithAlpha = H_2506_c.n_1700_B(textColor, q_3148_R.J_1907_R(K_1200_E.R_4764_Y) / 255.0f * effectiveReset);
            l_3370_o.J_1907_R[12].n_1700_B(matrixStack, "\u0421\u0431\u0440\u043e\u0441\u0438\u0442\u044c", (double)(this.w_1484_f + 35.0f), (double)(this.t_148_a - 1.0f), textColorWithAlpha);
        }
        F_489_x.n_1700_B(new g_2336_b("Pouch/icons/gui/bin.png"), this.w_1484_f + 34.0f, this.t_148_a + 5.0f, 8.0f, 8.0f, H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.s_956_w), q_3148_R.J_1907_R(K_1200_E.s_956_w) / 255.0f * panelAlpha));
        int currentBind = this.J_1907_R instanceof s_3815_K ? ((s_3815_K)this.J_1907_R).u_2550_I().v_4262_N() : ((p_3749_n)this.J_1907_R).R_4764_Y().u_2550_I();
        int bindBg = this.M_588_G ? q_3148_R.n_1700_B(K_1200_E.M_588_G) : q_3148_R.n_1700_B(K_1200_E.P_4830_p);
        float bindEffective = (this.M_588_G ? q_3148_R.J_1907_R(K_1200_E.M_588_G) : q_3148_R.J_1907_R(K_1200_E.P_4830_p)) / 255.0f * panelAlpha;
        F_489_x.n_1700_B(this.w_1484_f + 43.0f, this.t_148_a + 4.0f, 23.0f, 10.0f, 2.0f, H_2506_c.n_1700_B(bindBg, bindEffective));
        float textX = this.w_1484_f + 42.5f + (23.0f - l_3370_o.J_1907_R[12].n_1700_B(this.M_588_G ? "..." : (currentBind == -100 ? "None" : j_1654_T.n_1700_B(currentBind)))) / 2.0f;
        float textY = this.t_148_a + 4.0f + (10.0f - l_3370_o.J_1907_R[12].h_1847_R()) / 2.0f + 1.0f;
        l_3370_o.J_1907_R[12].n_1700_B(matrixStack, this.M_588_G ? "..." : (currentBind == -100 ? "None" : j_1654_T.n_1700_B(currentBind)), (double)textX, (double)textY, H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.R_4764_Y), q_3148_R.J_1907_R(K_1200_E.R_4764_Y) / 255.0f * panelAlpha));
        int activeBg = q_3148_R.n_1700_B(K_1200_E.P_4830_p);
        int inactiveBg = q_3148_R.n_1700_B(K_1200_E.M_588_G);
        int toggleBg = H_2506_c.n_1700_B(inactiveBg, activeBg, tmValue);
        float toggleAlpha = (float)H_2506_c.G_564_y(toggleBg) / 255.0f * panelAlpha;
        F_489_x.n_1700_B(this.w_1484_f + 4.0f, this.t_148_a + 16.0f, 30.0f, 10.0f, 2.0f, H_2506_c.n_1700_B(toggleBg, toggleAlpha));
        float toggleTextX = this.w_1484_f + 3.5f + (30.0f - l_3370_o.R_4764_Y[12].n_1700_B("Toggle")) / 2.0f;
        float toggleTextY = this.t_148_a + 16.0f + (10.0f - l_3370_o.R_4764_Y[12].h_1847_R()) / 2.0f + 1.0f;
        int activeText = q_3148_R.n_1700_B(K_1200_E.G_564_y);
        int inactiveText = q_3148_R.n_1700_B(K_1200_E.R_4764_Y);
        int toggleTextColor = H_2506_c.n_1700_B(inactiveText, activeText, tmValue);
        float toggleTextAlpha = (float)H_2506_c.G_564_y(toggleTextColor) / 255.0f * panelAlpha;
        l_3370_o.R_4764_Y[12].n_1700_B(matrixStack, "Toggle", (double)toggleTextX, (double)toggleTextY, H_2506_c.n_1700_B(toggleTextColor, toggleTextAlpha));
        int holdBg = H_2506_c.n_1700_B(activeBg, inactiveBg, tmValue);
        float holdAlpha = (float)H_2506_c.G_564_y(holdBg) / 255.0f * panelAlpha;
        F_489_x.n_1700_B(this.w_1484_f + 36.0f, this.t_148_a + 16.0f, 30.0f, 10.0f, 2.0f, H_2506_c.n_1700_B(holdBg, holdAlpha));
        float holdTextX = this.w_1484_f + 35.5f + (30.0f - l_3370_o.R_4764_Y[12].n_1700_B("Hold")) / 2.0f;
        float holdTextY = this.t_148_a + 16.0f + (10.0f - l_3370_o.R_4764_Y[12].h_1847_R()) / 2.0f + 1.0f;
        int holdTextColor = H_2506_c.n_1700_B(activeText, inactiveText, tmValue);
        float holdTextAlpha = (float)H_2506_c.G_564_y(holdTextColor) / 255.0f * panelAlpha;
        l_3370_o.R_4764_Y[12].n_1700_B(matrixStack, "Hold", (double)holdTextX, (double)holdTextY, H_2506_c.n_1700_B(holdTextColor, holdTextAlpha));
        if (this.M_588_G) {
            F_489_x.n_1700_B(new g_2336_b("Pouch/icons/mainmenu/black_background.png"), 0.0f, 0.0f, (float)c_3005_b.RealmsServerPing().Q_4569_t(), (float)c_3005_b.RealmsServerPing().M_182_A(), H_2506_c.n_1700_B(-1, 160));
            l_3370_o.J_1907_R[32].n_1700_B(matrixStack, "\u041d\u0430\u0436\u043c\u0438\u0442\u0435 \u043d\u0430 \u043b\u044e\u0431\u0443\u044e \u043a\u043d\u043e\u043f\u043a\u0443", (double)(((float)c_3005_b.RealmsServerPing().Q_4569_t() - l_3370_o.J_1907_R[32].n_1700_B("\u041d\u0430\u0436\u043c\u0438\u0442\u0435 \u043d\u0430 \u043b\u044e\u0431\u0443\u044e \u043a\u043d\u043e\u043f\u043a\u0443")) / 2.0f), (double)(((float)c_3005_b.RealmsServerPing().M_182_A() - l_3370_o.J_1907_R[32].h_1847_R()) / 2.0f - 4.0f), -1);
        }
    }

    public boolean n_1700_B(float mouseX, float mouseY, int button) {
        boolean isBinding;
        boolean bl = isBinding = this.J_1907_R instanceof s_3815_K ? ((s_3815_K)this.J_1907_R).h_1847_R() : ((p_3749_n)this.J_1907_R).P_4830_p();
        if (!isBinding || (double)this.R_4764_Y.n_1700_B() < 0.1) {
            return false;
        }
        if (this.M_588_G) {
            if (F_747_P.n_1700_B(mouseX, mouseY, this.w_1484_f + 34.0f, this.t_148_a + 5.0f, 8.0f, 8.0f) && button == 0) {
                if (this.J_1907_R instanceof s_3815_K) {
                    ((s_3815_K)this.J_1907_R).u_2550_I().n_1700_B(-100);
                } else {
                    ((p_3749_n)this.J_1907_R).R_4764_Y().n_1700_B(-100);
                }
                this.M_588_G = false;
                return true;
            }
            if (F_747_P.n_1700_B(mouseX, mouseY, this.w_1484_f + 43.0f, this.t_148_a + 4.0f, 23.0f, 10.0f) && button == 0) {
                this.M_588_G = false;
                return true;
            }
            if (button == 0 || button == 1) {
                return true;
            }
            if (button >= 1) {
                if (this.J_1907_R instanceof s_3815_K) {
                    ((s_3815_K)this.J_1907_R).u_2550_I().n_1700_B(button);
                } else {
                    ((p_3749_n)this.J_1907_R).R_4764_Y().n_1700_B(button);
                }
                this.M_588_G = false;
                return true;
            }
            return true;
        }
        if (F_747_P.n_1700_B(mouseX, mouseY, this.w_1484_f, this.t_148_a, 70.0f, 30.0f)) {
            if (F_747_P.n_1700_B(mouseX, mouseY, this.w_1484_f + 23.5f, this.t_148_a + 4.0f, 9.0f, 8.0f) && button == 0) {
                if (this.J_1907_R instanceof s_3815_K) {
                    ((s_3815_K)this.J_1907_R).u_2550_I().R_4764_Y(!((s_3815_K)this.J_1907_R).u_2550_I().s_956_w());
                } else {
                    ((p_3749_n)this.J_1907_R).R_4764_Y().R_4764_Y(!((p_3749_n)this.J_1907_R).R_4764_Y().P_4830_p());
                }
                return true;
            }
            if (F_747_P.n_1700_B(mouseX, mouseY, this.w_1484_f + 43.0f, this.t_148_a + 4.0f, 23.0f, 10.0f) && button == 0) {
                this.M_588_G = true;
                return true;
            }
            if (F_747_P.n_1700_B(mouseX, mouseY, this.w_1484_f + 4.0f, this.t_148_a + 16.0f, 30.0f, 10.0f) && button == 0) {
                if (this.J_1907_R instanceof s_3815_K) {
                    ((s_3815_K)this.J_1907_R).u_2550_I().n_1700_B(Module.n_1700_B.n_1700_B);
                } else {
                    ((p_3749_n)this.J_1907_R).R_4764_Y().n_1700_B(Module.n_1700_B.n_1700_B);
                }
                return true;
            }
            if (F_747_P.n_1700_B(mouseX, mouseY, this.w_1484_f + 36.0f, this.t_148_a + 16.0f, 30.0f, 10.0f) && button == 0) {
                if (this.J_1907_R instanceof s_3815_K) {
                    ((s_3815_K)this.J_1907_R).u_2550_I().n_1700_B(Module.n_1700_B.J_1907_R);
                } else {
                    ((p_3749_n)this.J_1907_R).R_4764_Y().n_1700_B(Module.n_1700_B.J_1907_R);
                }
                return true;
            }
            if (F_747_P.n_1700_B(mouseX, mouseY, this.w_1484_f + 34.0f, this.t_148_a + 5.0f, 8.0f, 8.0f) && button == 0) {
                if (this.J_1907_R instanceof s_3815_K) {
                    ((s_3815_K)this.J_1907_R).u_2550_I().n_1700_B(-100);
                } else {
                    ((p_3749_n)this.J_1907_R).R_4764_Y().n_1700_B(-100);
                }
                return true;
            }
            return true;
        }
        this.n_1700_B = true;
        return true;
    }

    public boolean n_1700_B(int keyCode) {
        if (this.M_588_G) {
            if (keyCode == 256 || keyCode == 261) {
                if (this.J_1907_R instanceof s_3815_K) {
                    ((s_3815_K)this.J_1907_R).u_2550_I().n_1700_B(-100);
                } else if (this.J_1907_R instanceof p_3749_n) {
                    ((p_3749_n)this.J_1907_R).R_4764_Y().n_1700_B(-100);
                }
            } else if (keyCode != -1 && keyCode != 0 && keyCode != 1) {
                if (this.J_1907_R instanceof s_3815_K) {
                    ((s_3815_K)this.J_1907_R).u_2550_I().n_1700_B(keyCode);
                } else if (this.J_1907_R instanceof p_3749_n) {
                    ((p_3749_n)this.J_1907_R).R_4764_Y().n_1700_B(keyCode);
                }
            }
            this.M_588_G = false;
            return true;
        }
        if (keyCode == 256) {
            this.n_1700_B = true;
            return false;
        }
        return true;
    }

    public void n_1700_B(float x, float y) {
        float yOffset = this.J_1907_R instanceof p_3749_n ? -4.0f : 0.0f;
        this.w_1484_f = x;
        this.t_148_a = y + yOffset;
    }

    public boolean n_1700_B() {
        return this.R_4764_Y.n_1700_B() <= 0.01f;
    }

    @Generated
    public N_4006_T J_1907_R() {
        return this.J_1907_R;
    }
}



