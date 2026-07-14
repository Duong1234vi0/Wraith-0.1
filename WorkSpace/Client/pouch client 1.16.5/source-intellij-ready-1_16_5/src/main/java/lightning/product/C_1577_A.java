/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.F_747_P;
import lightning.product.H_1952_g;
import lightning.product.N_4006_T;
import lightning.product.R_3213_X;
import lightning.product.Interface;
import lightning.product.f_887_Z;
import lightning.product.g_221_o;
import lightning.product.j_1654_T;
import lightning.product.l_3370_o;
import lightning.product.q_3148_R;
import lightning.product.s_3815_K;

public class C_1577_A {
    private final List<H_1952_g> n_1700_B;

    public C_1577_A(List<H_1952_g> panels) {
        this.n_1700_B = panels;
    }

    public void n_1700_B(g_221_o matrixStack, float mouseX, float mouseY, float guiAlpha) {
        for (H_1952_g panel : this.n_1700_B) {
            if (this.J_1907_R(panel)) continue;
            if (panel instanceof q_3148_R) {
                q_3148_R themeEditor = (q_3148_R)panel;
                float panelVis = themeEditor.A_4115_X().n_1700_B() * guiAlpha;
                for (f_887_Z ce : themeEditor.M_182_A()) {
                    R_3213_X picker = ce.G_564_y();
                    if (!picker.G_564_y() && !picker.M_182_A().G_564_y()) continue;
                    picker.n_1700_B(ce.u_1723_Y() + 110.0f);
                    picker.J_1907_R(ce.v_4262_N());
                    picker.n_1700_B(matrixStack, mouseX, mouseY, panelVis);
                }
                continue;
            }
            for (s_3815_K module : panel.t_148_a()) {
                for (N_4006_T elem : module.Q_4569_t()) {
                    f_887_Z ce;
                    R_3213_X picker;
                    if (!(elem instanceof f_887_Z) || !(picker = (ce = (f_887_Z)elem).G_564_y()).G_564_y() && !picker.M_182_A().G_564_y()) continue;
                    picker.n_1700_B(ce.u_1723_Y() + ce.w_1484_f() - 7.0f);
                    picker.J_1907_R(ce.v_4262_N() - 3.0f);
                    picker.n_1700_B(matrixStack, mouseX, mouseY, guiAlpha);
                }
            }
        }
    }

    public boolean n_1700_B(float mouseX, float mouseY, int button, float guiAlpha) {
        Object picker;
        q_3148_R themeEditor;
        boolean isColorPickerOpen = false;
        Object activeEyedropperPicker = null;
        for (H_1952_g panel : this.n_1700_B) {
            if (this.J_1907_R(panel)) continue;
            if (panel instanceof q_3148_R) {
                themeEditor = (q_3148_R)panel;
                for (f_887_Z ce : themeEditor.M_182_A()) {
                    picker = ce.G_564_y();
                    if (!((R_3213_X)picker).G_564_y()) continue;
                    isColorPickerOpen = true;
                    if (!((R_3213_X)picker).P_1922_E()) continue;
                    activeEyedropperPicker = picker;
                }
                continue;
            }
            for (s_3815_K module : panel.t_148_a()) {
                for (N_4006_T elem : module.Q_4569_t()) {
                    f_887_Z ce;
                    R_3213_X picker2;
                    if (!(elem instanceof f_887_Z) || !(picker2 = (ce = (f_887_Z)elem).G_564_y()).G_564_y()) continue;
                    isColorPickerOpen = true;
                    if (!picker2.P_1922_E()) continue;
                    activeEyedropperPicker = picker2;
                }
            }
        }
        if (activeEyedropperPicker != null) {
            ((R_3213_X)activeEyedropperPicker).n_1700_B(mouseX, mouseY);
            return true;
        }
        for (H_1952_g panel : this.n_1700_B) {
            if (this.J_1907_R(panel)) continue;
            if (panel instanceof q_3148_R) {
                themeEditor = (q_3148_R)panel;
                for (f_887_Z ce : themeEditor.M_182_A()) {
                    picker = ce.G_564_y();
                    if (((R_3213_X)picker).G_564_y()) {
                        float pickerWidth = 93.0f + (((R_3213_X)picker).w_1484_f().P_1922_E ? 0.0f : -7.0f);
                        float pickerHeight = 90.0f;
                        if (F_747_P.n_1700_B(mouseX, mouseY, ((R_3213_X)picker).u_1723_Y() + 7.0f, ((R_3213_X)picker).v_4262_N(), pickerWidth, pickerHeight)) {
                            ((R_3213_X)picker).n_1700_B(mouseX, mouseY);
                            return true;
                        }
                    }
                    float effectiveY = ce.v_4262_N();
                    float scissorTop = themeEditor.P_1922_E() + 40.0f;
                    float scissorBottom = themeEditor.P_1922_E() + themeEditor.w_1484_f() - 20.0f;
                    if (!ce.n_1700_B() || button != 0 || themeEditor.k_2293_S() != null && !themeEditor.k_2293_S().G_564_y() || !F_747_P.n_1700_B(mouseX, mouseY, ce.u_1723_Y() + 2.5f, ce.v_4262_N() - 3.5f, ce.w_1484_f() - 5.5f, ce.t_148_a()) || !(effectiveY >= scissorTop) || !(effectiveY + ce.t_148_a() <= scissorBottom)) continue;
                    for (f_887_Z otherCe : themeEditor.M_182_A()) {
                        if (otherCe == ce || !otherCe.G_564_y().G_564_y()) continue;
                        otherCe.G_564_y().R_4764_Y();
                    }
                    boolean newState = !((R_3213_X)picker).G_564_y();
                    ((R_3213_X)picker).n_1700_B(newState);
                    if (newState) {
                        themeEditor.Q_4569_t();
                    }
                    return true;
                }
                continue;
            }
            for (s_3815_K module : panel.t_148_a()) {
                for (N_4006_T elem : module.Q_4569_t()) {
                    if (!(elem instanceof f_887_Z)) continue;
                    f_887_Z ce = (f_887_Z)elem;
                    R_3213_X picker3 = ce.G_564_y();
                    if (picker3.G_564_y()) {
                        float pickerWidth = 93.0f + (picker3.w_1484_f().P_1922_E ? 0.0f : -7.0f);
                        float pickerHeight = 90.0f;
                        if (F_747_P.n_1700_B(mouseX, mouseY, picker3.u_1723_Y() + 7.0f, picker3.v_4262_N() - 2.0f, pickerWidth, pickerHeight + 1.0f)) {
                            picker3.n_1700_B(mouseX, mouseY);
                            return true;
                        }
                    }
                    float scissorTop = panel.P_1922_E() + 28.0f;
                    float scissorBottom = panel.P_1922_E() + 28.0f + panel.w_1484_f() - 35.0f;
                    float effectiveY = ce.v_4262_N();
                    if (!ce.n_1700_B() || button != 0 || !module.P_4830_p() || !F_747_P.n_1700_B(mouseX, mouseY, ce.u_1723_Y(), ce.v_4262_N() - 1.0f, ce.w_1484_f(), ce.t_148_a() - 7.0f) || !(effectiveY >= scissorTop) || !(effectiveY + ce.t_148_a() <= scissorBottom)) continue;
                    for (H_1952_g otherPanel : this.n_1700_B) {
                        if (this.J_1907_R(otherPanel)) continue;
                        for (s_3815_K otherModule : otherPanel.t_148_a()) {
                            for (N_4006_T otherElem : otherModule.Q_4569_t()) {
                                f_887_Z otherCe;
                                if (!(otherElem instanceof f_887_Z) || (otherCe = (f_887_Z)otherElem) == ce || !otherCe.G_564_y().G_564_y()) continue;
                                otherCe.G_564_y().R_4764_Y();
                            }
                        }
                    }
                    picker3.n_1700_B(!picker3.G_564_y());
                    return true;
                }
            }
        }
        if (isColorPickerOpen && (button == 1 || button == 2)) {
            this.n_1700_B(true);
            return false;
        }
        if (isColorPickerOpen && button == 0) {
            this.n_1700_B(true);
            return false;
        }
        return false;
    }

    public void n_1700_B(boolean animate) {
        for (H_1952_g panel : this.n_1700_B) {
            if (this.J_1907_R(panel)) continue;
            if (panel instanceof q_3148_R) {
                q_3148_R themeEditor = (q_3148_R)panel;
                for (f_887_Z ce : themeEditor.M_182_A()) {
                    R_3213_X picker = ce.G_564_y();
                    if (!picker.G_564_y()) continue;
                    picker.R_4764_Y();
                }
                continue;
            }
            for (s_3815_K module : panel.t_148_a()) {
                for (N_4006_T elem : module.Q_4569_t()) {
                    f_887_Z ce;
                    R_3213_X picker;
                    if (!(elem instanceof f_887_Z) || !(picker = (ce = (f_887_Z)elem).G_564_y()).G_564_y()) continue;
                    picker.R_4764_Y();
                }
            }
        }
    }

    public boolean n_1700_B(H_1952_g panel) {
        if (panel instanceof q_3148_R) {
            q_3148_R themeEditor = (q_3148_R)panel;
            for (f_887_Z ce : themeEditor.M_182_A()) {
                if (!ce.G_564_y().G_564_y()) continue;
                return true;
            }
        } else {
            for (s_3815_K module : panel.t_148_a()) {
                for (N_4006_T elem : module.Q_4569_t()) {
                    f_887_Z ce;
                    if (!(elem instanceof f_887_Z) || !(ce = (f_887_Z)elem).G_564_y().G_564_y()) continue;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean J_1907_R(H_1952_g panel) {
        return panel instanceof q_3148_R && (Boolean)Interface.v_4262_N.J_1907_R() == false;
    }

    private boolean R_4764_Y(float mouseX, float mouseY) {
        for (H_1952_g panel : this.n_1700_B) {
            if (this.J_1907_R(panel) || panel instanceof q_3148_R) continue;
            for (s_3815_K module : panel.t_148_a()) {
                if (!this.n_1700_B(module, mouseX, mouseY)) continue;
                return true;
            }
        }
        return false;
    }

    public boolean n_1700_B(float mouseX, float mouseY) {
        return this.R_4764_Y(mouseX, mouseY);
    }

    public boolean J_1907_R(float mouseX, float mouseY) {
        for (H_1952_g panel : this.n_1700_B) {
            if (this.J_1907_R(panel)) continue;
            if (panel instanceof q_3148_R) {
                q_3148_R themeEditor = (q_3148_R)panel;
                for (f_887_Z ce : themeEditor.M_182_A()) {
                    R_3213_X picker = ce.G_564_y();
                    if (!picker.G_564_y()) continue;
                    float pickerWidth = 93.0f + (picker.w_1484_f().P_1922_E ? 0.0f : -7.0f);
                    float pickerHeight = 90.0f;
                    if (!F_747_P.n_1700_B(mouseX, mouseY, picker.u_1723_Y() + 7.0f, picker.v_4262_N(), pickerWidth, pickerHeight)) continue;
                    return true;
                }
                continue;
            }
            for (s_3815_K module : panel.t_148_a()) {
                for (N_4006_T elem : module.Q_4569_t()) {
                    f_887_Z ce;
                    R_3213_X picker;
                    if (!(elem instanceof f_887_Z) || !(picker = (ce = (f_887_Z)elem).G_564_y()).G_564_y()) continue;
                    float pickerWidth = 93.0f + (picker.w_1484_f().P_1922_E ? 0.0f : -7.0f);
                    float pickerHeight = 90.0f;
                    if (!F_747_P.n_1700_B(mouseX, mouseY, picker.u_1723_Y() + 7.0f, picker.v_4262_N() - 2.0f, pickerWidth, pickerHeight + 1.0f)) continue;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean n_1700_B(s_3815_K element, float mouseX, float mouseY) {
        float textY = element.v_4262_N() + 4.0f;
        boolean hasSettings = element.Q_4569_t().stream().anyMatch(N_4006_T::n_1700_B);
        float iconX = element.u_1723_Y() + element.w_1484_f() - 10.0f;
        String bindLabel = element.u_2550_I().v_4262_N() != -100 ? j_1654_T.n_1700_B(element.u_2550_I().v_4262_N()) : "...";
        float bindTextWidth = l_3370_o.R_4764_Y[13].n_1700_B(bindLabel);
        float iconInsideW = l_3370_o.w_1484_f[12].n_1700_B("C");
        float iconInsideH = l_3370_o.w_1484_f[12].h_1847_R();
        float sepWidth = 1.0f;
        float padding = 4.0f;
        float rectH = 9.0f;
        float rectW = Math.max(iconInsideW + sepWidth + bindTextWidth + padding * 2.0f, 20.0f);
        float rectRight = hasSettings ? iconX - 6.0f : element.u_1723_Y() + element.w_1484_f() - 5.0f;
        float rectX = rectRight - rectW;
        float rectY = textY + (l_3370_o.R_4764_Y[18].h_1847_R() - rectH) / 2.0f;
        return F_747_P.n_1700_B(mouseX, mouseY, rectX - 2.0f, rectY - 2.0f, rectW + 4.0f, rectH + 4.0f);
    }
}


