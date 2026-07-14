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
import lightning.product.g_221_o;
import lightning.product.Easing;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.l_4397_i;
import lightning.product.q_3148_R;
import lombok.Generated;
import org.lwjgl.glfw.GLFW;

public class r_976_u {
    private float n_1700_B;
    private float J_1907_R;
    private float R_4764_Y;
    private float G_564_y;
    private String P_1922_E = "";
    private boolean u_1723_Y = false;
    private boolean v_4262_N = false;
    private boolean w_1484_f = false;
    private long t_148_a = 0L;
    private final Animation s_956_w = new Animation(0.0f, 12.0f, Easing.Y_601_j);

    public r_976_u(float x, float y, float width, float height) {
        this.n_1700_B = x;
        this.J_1907_R = y;
        this.R_4764_Y = width;
        this.G_564_y = height;
    }

    public void n_1700_B(g_221_o stack, float parentAlpha) {
        float rightBound;
        float cursorX;
        this.s_956_w.n_1700_B(this.v_4262_N ? 1.0f : 0.0f);
        float focusAlpha = this.s_956_w.n_1700_B();
        F_489_x.n_1700_B(this.n_1700_B, this.J_1907_R, this.R_4764_Y, this.G_564_y, 9.0f, q_3148_R.n_1700_B(K_1200_E.n_1700_B), parentAlpha);
        F_489_x.J_1907_R(this.n_1700_B, this.J_1907_R, this.R_4764_Y, this.G_564_y, 9.0f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * parentAlpha);
        String displayText = this.P_1922_E.isEmpty() && !this.u_1723_Y && focusAlpha < 0.01f ? "\u041f\u043e\u0438\u0441\u043a" : this.P_1922_E;
        float textX = this.n_1700_B + 8.0f;
        float maxWidth = this.R_4764_Y - 17.5f;
        if (!this.P_1922_E.isEmpty() && l_3370_o.J_1907_R[16].n_1700_B(displayText) > maxWidth) {
            while (l_3370_o.J_1907_R[16].n_1700_B(displayText) > maxWidth && displayText.length() > 1) {
                displayText = displayText.substring(1);
            }
        }
        if ((cursorX = this.P_1922_E.isEmpty() && !this.u_1723_Y && focusAlpha < 0.01f ? textX + l_3370_o.J_1907_R[16].n_1700_B("\u041f\u043e\u0438\u0441\u043a") : textX + l_3370_o.J_1907_R[16].n_1700_B(displayText)) > (rightBound = this.n_1700_B + this.R_4764_Y - 5.0f)) {
            cursorX = rightBound;
        }
        if (focusAlpha > 0.01f) {
            long now = System.currentTimeMillis();
            if (this.t_148_a == 0L) {
                this.t_148_a = now;
            }
            float blinkAlpha = (float)Math.sin((double)((float)((now - this.t_148_a) % 1000L) / 500.0f) * Math.PI) * 0.5f + 0.5f;
            float cursorWidth = 4.0f;
            int cursorCol = H_2506_c.n_1700_B(-1, (int)(blinkAlpha * focusAlpha * parentAlpha * 255.0f));
            F_489_x.n_1700_B(stack, cursorX, this.J_1907_R + this.G_564_y - 6.5f, cursorWidth, 0.5f, cursorCol);
        } else {
            this.t_148_a = 0L;
        }
        int textColor = q_3148_R.n_1700_B(K_1200_E.R_4764_Y);
        int inactiveColor = q_3148_R.n_1700_B(K_1200_E.G_564_y);
        int out = H_2506_c.n_1700_B(this.P_1922_E.isEmpty() && !this.u_1723_Y && focusAlpha < 0.01f ? inactiveColor : textColor, parentAlpha);
        l_3370_o.J_1907_R[16].n_1700_B(stack, displayText, (double)textX, (double)(this.J_1907_R + (this.G_564_y / 2.0f - 4.0f) + 1.5f), out);
    }

    public boolean n_1700_B(float mouseX, float mouseY) {
        boolean wasFocused = this.u_1723_Y;
        boolean bl = this.u_1723_Y = mouseX >= this.n_1700_B && mouseX <= this.n_1700_B + this.R_4764_Y - 1.0f && mouseY >= this.J_1907_R && mouseY <= this.J_1907_R + this.G_564_y - 1.0f;
        if (this.u_1723_Y && !wasFocused) {
            l_4397_i.R_4764_Y();
            this.v_4262_N = true;
            this.t_148_a = System.currentTimeMillis();
            this.w_1484_f = false;
        } else if (!this.u_1723_Y) {
            this.v_4262_N = false;
            this.w_1484_f = false;
            this.s_956_w.J_1907_R(0.0f);
        }
        return this.u_1723_Y;
    }

    public boolean n_1700_B(char codePoint) {
        if (this.u_1723_Y && this.v_4262_N) {
            if (this.w_1484_f) {
                this.P_1922_E = String.valueOf(codePoint);
                this.w_1484_f = false;
            } else if (codePoint >= ' ' && codePoint <= '~' && this.P_1922_E.length() < 21) {
                this.P_1922_E = this.P_1922_E + codePoint;
            }
        }
        return false;
    }

    public boolean n_1700_B(int keyCode, int scanCode, int modifiers) {
        if (this.u_1723_Y && this.v_4262_N) {
            if (keyCode == 256) {
                this.v_4262_N = false;
                this.u_1723_Y = false;
                this.w_1484_f = false;
                this.s_956_w.J_1907_R(0.0f);
                return false;
            }
            if (keyCode == 65 && (modifiers & 2) != 0) {
                if (!this.P_1922_E.isEmpty()) {
                    this.w_1484_f = true;
                }
                return true;
            }
            if (keyCode == 67 && (modifiers & 2) != 0) {
                if (this.w_1484_f && !this.P_1922_E.isEmpty()) {
                    GLFW.glfwSetClipboardString((long)GLFW.glfwGetCurrentContext(), (CharSequence)this.P_1922_E);
                }
                return true;
            }
            if (keyCode == 86 && (modifiers & 2) != 0) {
                String clipboardText = GLFW.glfwGetClipboardString((long)GLFW.glfwGetCurrentContext());
                if (clipboardText != null && !clipboardText.isEmpty()) {
                    int remainingChars;
                    if (this.w_1484_f) {
                        this.P_1922_E = "";
                        this.w_1484_f = false;
                    }
                    if ((remainingChars = 21 - this.P_1922_E.length()) > 0) {
                        this.P_1922_E = this.P_1922_E + clipboardText.substring(0, Math.min(remainingChars, clipboardText.length()));
                    }
                }
                return true;
            }
            if (keyCode == 259 && !this.P_1922_E.isEmpty()) {
                if (this.w_1484_f) {
                    this.P_1922_E = "";
                    this.w_1484_f = false;
                } else {
                    this.P_1922_E = this.P_1922_E.substring(0, this.P_1922_E.length() - 1);
                }
                return true;
            }
            if (keyCode == 257) {
                this.v_4262_N = false;
                this.u_1723_Y = false;
                this.w_1484_f = false;
                this.s_956_w.J_1907_R(0.0f);
                return true;
            }
            return true;
        }
        return false;
    }

    public void n_1700_B() {
        this.u_1723_Y = false;
        this.v_4262_N = false;
        this.w_1484_f = false;
        this.s_956_w.J_1907_R(0.0f);
    }

    @Generated
    public float J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public float R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public float G_564_y() {
        return this.R_4764_Y;
    }

    @Generated
    public float P_1922_E() {
        return this.G_564_y;
    }

    @Generated
    public String u_1723_Y() {
        return this.P_1922_E;
    }

    @Generated
    public boolean v_4262_N() {
        return this.u_1723_Y;
    }

    @Generated
    public boolean w_1484_f() {
        return this.v_4262_N;
    }

    @Generated
    public boolean t_148_a() {
        return this.w_1484_f;
    }

    @Generated
    public long s_956_w() {
        return this.t_148_a;
    }

    @Generated
    public Animation u_2550_I() {
        return this.s_956_w;
    }

    @Generated
    public void n_1700_B(float x) {
        this.n_1700_B = x;
    }

    @Generated
    public void J_1907_R(float y) {
        this.J_1907_R = y;
    }

    @Generated
    public void R_4764_Y(float width) {
        this.R_4764_Y = width;
    }

    @Generated
    public void G_564_y(float height) {
        this.G_564_y = height;
    }

    @Generated
    public void n_1700_B(String text) {
        this.P_1922_E = text;
    }

    @Generated
    public void n_1700_B(boolean focused) {
        this.u_1723_Y = focused;
    }

    @Generated
    public void J_1907_R(boolean activated) {
        this.v_4262_N = activated;
    }

    @Generated
    public void R_4764_Y(boolean isTextSelected) {
        this.w_1484_f = isTextSelected;
    }

    @Generated
    public void n_1700_B(long cursorAnimationStart) {
        this.t_148_a = cursorAnimationStart;
    }
}

