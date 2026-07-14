/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.lwjgl.glfw.GLFW
 */
package lightning.product;

import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.K_1200_E;
import lightning.product.N_4006_T;
import lightning.product.O_3016_i;
import lightning.product.V_537_k;
import lightning.product.g_221_o;
import lightning.product.Easing;
import lightning.product.i_4833_u;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.q_3148_R;
import lombok.Generated;
import org.lwjgl.glfw.GLFW;

public class l_4397_i
extends N_4006_T {
    private static l_4397_i J_1907_R = null;
    private final O_3016_i R_4764_Y;
    private String G_564_y;
    public boolean n_1700_B = false;
    private boolean P_1922_E = false;
    private long u_1723_Y = 0L;
    private final Animation v_4262_N = new Animation(0.0f, 12.0f, Easing.Y_601_j);

    public l_4397_i(O_3016_i setting) {
        this.R_4764_Y = setting;
        this.G_564_y = (String)setting.J_1907_R();
    }

    public void n_1700_B(String text) {
        this.G_564_y = text == null ? "" : text;
        this.R_4764_Y.J_1907_R(this.G_564_y);
    }

    public static void R_4764_Y() {
        if (J_1907_R != null) {
            l_4397_i.J_1907_R.n_1700_B = false;
            l_4397_i.J_1907_R.P_1922_E = false;
            l_4397_i.J_1907_R.R_4764_Y.J_1907_R(l_4397_i.J_1907_R.G_564_y);
            J_1907_R = null;
        }
    }

    @Override
    public void n_1700_B(g_221_o matrixStack, float mouseX, float mouseY, float alpha) {
        super.n_1700_B(matrixStack, mouseX, mouseY, alpha);
        this.G_564_y(18.0f);
        String displayText = this.G_564_y.isEmpty() ? (this.u_2550_I() != null ? V_537_k.n_1700_B(this.u_2550_I().getClass().getSimpleName(), this.R_4764_Y.n_1700_B()) : this.R_4764_Y.n_1700_B()) : this.G_564_y;
        float w = this.w_1484_f() - 25.0f;
        float x = this.u_1723_Y() + 5.0f;
        float y = this.v_4262_N() + 4.0f;
        F_489_x.n_1700_B(x, y, w, 12.0f, 2.5f, H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.P_4830_p), q_3148_R.J_1907_R(K_1200_E.P_4830_p) / 255.0f * alpha));
        F_489_x.J_1907_R(x, y, w, 12.0f, 2.5f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * alpha);
        float availableWidth = w - 6.0f;
        while (l_3370_o.R_4764_Y[13].n_1700_B(displayText) > availableWidth && !displayText.isEmpty()) {
            displayText = displayText.substring(1);
        }
        float tx = x + 3.0f;
        float tw = l_3370_o.R_4764_Y[13].n_1700_B(displayText);
        i_4833_u.n_1700_B(x + 2.0f, y, w - 4.0f, 12.0);
        if (this.P_1922_E && !this.G_564_y.isEmpty()) {
            F_489_x.n_1700_B(matrixStack, tx, this.v_4262_N() + 6.0f, tw + 1.0f, 8.0f, H_2506_c.n_1700_B(H_2506_c.n_1700_B(26, 53, 255), 255.0f * alpha));
        }
        int cx = this.G_564_y.isEmpty() ? (int)tx : (int)(tx + tw);
        long now = System.currentTimeMillis();
        if (this.u_1723_Y == 0L) {
            this.u_1723_Y = now;
        }
        float blinkAlpha = (float)Math.sin((double)((float)((now - this.u_1723_Y) % 1000L) / 500.0f) * Math.PI) * 0.5f + 0.5f;
        this.v_4262_N.n_1700_B(this.n_1700_B ? 1.0f : 0.0f);
        if (!this.n_1700_B) {
            this.P_1922_E = false;
        }
        if (this.v_4262_N.n_1700_B() > 0.01f) {
            F_489_x.n_1700_B(matrixStack, (float)cx, this.v_4262_N() + 6.0f, 0.5f, 8.0f, H_2506_c.n_1700_B(-1, (int)(blinkAlpha * this.v_4262_N.n_1700_B() * 255.0f)));
        }
        int color = this.G_564_y.isEmpty() ? H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.G_564_y), q_3148_R.J_1907_R(K_1200_E.G_564_y) / 255.0f * alpha) : H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.R_4764_Y), q_3148_R.J_1907_R(K_1200_E.R_4764_Y) / 255.0f * alpha);
        l_3370_o.R_4764_Y[13].n_1700_B(matrixStack, displayText, (double)tx, (double)(this.v_4262_N() + 9.0f), color);
        i_4833_u.n_1700_B();
        this.R_4764_Y.n_1700_B(this.G_564_y);
    }

    @Override
    public void n_1700_B(float mouseX, float mouseY, int button) {
        boolean isHovered = this.n_1700_B(mouseX, mouseY);
        if (isHovered) {
            if (J_1907_R != null && J_1907_R != this) {
                l_4397_i.J_1907_R.n_1700_B = false;
                l_4397_i.J_1907_R.P_1922_E = false;
                l_4397_i.J_1907_R.R_4764_Y.J_1907_R(l_4397_i.J_1907_R.G_564_y);
            }
            J_1907_R = this;
            this.n_1700_B = true;
            this.P_1922_E = false;
            this.u_1723_Y = System.currentTimeMillis();
        } else if (this.n_1700_B) {
            this.n_1700_B = false;
            this.P_1922_E = false;
            this.R_4764_Y.J_1907_R(this.G_564_y);
            if (J_1907_R == this) {
                J_1907_R = null;
            }
        }
        super.n_1700_B(mouseX, mouseY, button);
    }

    @Override
    public void J_1907_R(float mouseX, float mouseY, int button) {
        if (this.n_1700_B && !this.n_1700_B(mouseX, mouseY)) {
            this.n_1700_B = false;
            this.P_1922_E = false;
            this.R_4764_Y.J_1907_R(this.G_564_y);
            if (J_1907_R == this) {
                J_1907_R = null;
            }
        }
        super.J_1907_R(mouseX, mouseY, button);
    }

    @Override
    public boolean n_1700_B(float mouseX, float mouseY) {
        return mouseX >= this.u_1723_Y() + 5.0f && mouseX <= this.u_1723_Y() + this.w_1484_f() - 5.0f && mouseY >= this.v_4262_N() + 4.0f && mouseY <= this.v_4262_N() + 15.0f;
    }

    @Override
    public void n_1700_B(int keyCode, int scanCode, int modifiers) {
        if (this.n_1700_B) {
            if (keyCode == 257 || keyCode == 256) {
                this.n_1700_B = false;
                this.P_1922_E = false;
                this.R_4764_Y.J_1907_R(this.G_564_y);
                if (J_1907_R == this) {
                    J_1907_R = null;
                }
                return;
            }
            if (keyCode == 65 && (modifiers & 2) != 0) {
                this.P_1922_E = true;
                return;
            }
            if (keyCode == 67 && (modifiers & 2) != 0) {
                if (this.P_1922_E && !this.G_564_y.isEmpty()) {
                    GLFW.glfwSetClipboardString((long)GLFW.glfwGetCurrentContext(), (CharSequence)this.G_564_y);
                }
                return;
            }
            if (keyCode == 86 && (modifiers & 2) != 0) {
                String clipboardText = GLFW.glfwGetClipboardString((long)GLFW.glfwGetCurrentContext());
                if (clipboardText != null && !clipboardText.isEmpty()) {
                    int remaining;
                    if (this.P_1922_E) {
                        this.G_564_y = "";
                        this.P_1922_E = false;
                    }
                    if ((remaining = 50 - this.G_564_y.length()) > 0) {
                        this.G_564_y = this.G_564_y + clipboardText.substring(0, Math.min(remaining, clipboardText.length()));
                    }
                }
                return;
            }
            if (keyCode == 259) {
                if (this.P_1922_E) {
                    this.G_564_y = "";
                    this.P_1922_E = false;
                } else if (!this.G_564_y.isEmpty()) {
                    this.G_564_y = this.G_564_y.substring(0, this.G_564_y.length() - 1);
                }
                return;
            }
        }
        super.n_1700_B(keyCode, scanCode, modifiers);
    }

    public void G_564_y() {
        this.G_564_y = "";
        this.R_4764_Y.J_1907_R("");
        this.n_1700_B = false;
        this.P_1922_E = false;
        if (J_1907_R == this) {
            J_1907_R = null;
        }
    }

    @Override
    public void n_1700_B(char codePoint, int modifiers) {
        if (this.n_1700_B) {
            if (this.P_1922_E) {
                this.G_564_y = String.valueOf(codePoint);
                this.P_1922_E = false;
            } else if (this.G_564_y.length() < 50) {
                this.G_564_y = this.G_564_y + codePoint;
            }
        }
        super.n_1700_B(codePoint, modifiers);
    }

    @Generated
    public O_3016_i P_4830_p() {
        return this.R_4764_Y;
    }

    @Generated
    public String h_1847_R() {
        return this.G_564_y;
    }

    @Generated
    public boolean Q_4569_t() {
        return this.n_1700_B;
    }

    @Generated
    public boolean M_182_A() {
        return this.P_1922_E;
    }

    @Generated
    public long t_1786_h() {
        return this.u_1723_Y;
    }

    @Generated
    public Animation multiplayerClientSuggestionProvider() {
        return this.v_4262_N;
    }
}


