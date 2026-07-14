/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_4006_T;
import lightning.product.MinecraftAccess;
import lightning.product.g_221_o;
import lightning.product.p_3749_n;
import lightning.product.s_3815_K;
import lightning.product.w_2152_d;

public class s_1124_y
implements MinecraftAccess {
    private w_2152_d n_1700_B;
    private N_4006_T J_1907_R;
    private w_2152_d R_4764_Y;

    public void n_1700_B(g_221_o matrixStack, int mouseX, int mouseY, float guiAlpha) {
        if (this.R_4764_Y != null) {
            this.R_4764_Y.n_1700_B(this.R_4764_Y.J_1907_R().u_1723_Y() + this.R_4764_Y.J_1907_R().w_1484_f(), this.R_4764_Y.J_1907_R().v_4262_N() + 4.0f);
            this.R_4764_Y.n_1700_B(matrixStack, guiAlpha);
            if (this.R_4764_Y.n_1700_B && this.R_4764_Y.n_1700_B()) {
                this.R_4764_Y = null;
            }
        }
        if (this.n_1700_B == null || this.J_1907_R == null) {
            return;
        }
        this.n_1700_B.n_1700_B(this.J_1907_R.u_1723_Y() + this.J_1907_R.w_1484_f(), this.J_1907_R.v_4262_N() + 4.0f);
        this.n_1700_B.n_1700_B(matrixStack, guiAlpha);
        if (this.n_1700_B.n_1700_B && this.n_1700_B.n_1700_B()) {
            this.n_1700_B = null;
            this.J_1907_R = null;
        }
    }

    public boolean n_1700_B(float mouseX, float mouseY, int button) {
        w_2152_d current;
        w_2152_d w_2152_d2 = current = this.n_1700_B != null ? this.n_1700_B : this.R_4764_Y;
        if (current == null) {
            return false;
        }
        if (this.J_1907_R != null && this.J_1907_R.n_1700_B(mouseX, mouseY)) {
            this.J_1907_R(this.J_1907_R);
            if (this.n_1700_B != null) {
                this.n_1700_B.n_1700_B = true;
            }
            return true;
        }
        boolean handled = current.n_1700_B(mouseX, mouseY, button);
        if (current.n_1700_B) {
            return true;
        }
        return handled;
    }

    public boolean n_1700_B(int keyCode) {
        if (this.n_1700_B != null) {
            return this.n_1700_B.n_1700_B(keyCode);
        }
        if (this.R_4764_Y != null) {
            return this.R_4764_Y.n_1700_B(keyCode);
        }
        return false;
    }

    public void n_1700_B(boolean animate) {
        if (animate) {
            if (this.n_1700_B != null) {
                this.n_1700_B.n_1700_B = true;
            }
            if (this.R_4764_Y != null) {
                this.R_4764_Y.n_1700_B = true;
            }
            return;
        }
        if (this.J_1907_R != null) {
            this.J_1907_R(this.J_1907_R);
        }
        this.n_1700_B = null;
        this.J_1907_R = null;
        if (this.R_4764_Y != null) {
            this.J_1907_R(this.R_4764_Y.J_1907_R());
            this.R_4764_Y = null;
        }
    }

    public boolean n_1700_B() {
        return this.n_1700_B != null && this.J_1907_R != null && !this.n_1700_B.n_1700_B();
    }

    public boolean n_1700_B(N_4006_T element) {
        return this.n_1700_B != null && this.J_1907_R == element && !this.n_1700_B.n_1700_B();
    }

    public void n_1700_B(s_3815_K moduleElement) {
        this.n_1700_B(moduleElement, false);
    }

    public void n_1700_B(p_3749_n booleanElement, boolean keepOpen) {
        this.n_1700_B((N_4006_T)booleanElement, keepOpen);
    }

    private void n_1700_B(N_4006_T element, boolean keepOpen) {
        if (element == null) {
            return;
        }
        if (this.n_1700_B != null && this.J_1907_R == element) {
            this.n_1700_B.n_1700_B = !keepOpen && !this.n_1700_B.n_1700_B;
            return;
        }
        if (this.n_1700_B != null) {
            this.R_4764_Y = this.n_1700_B;
            this.R_4764_Y.n_1700_B = true;
        }
        this.J_1907_R = element;
        if (element instanceof s_3815_K) {
            ((s_3815_K)element).J_1907_R(true);
        } else if (element instanceof p_3749_n) {
            ((p_3749_n)element).n_1700_B(true);
        }
        this.n_1700_B = new w_2152_d(this.J_1907_R);
    }

    private void J_1907_R(N_4006_T element) {
        if (element instanceof s_3815_K) {
            ((s_3815_K)element).J_1907_R(false);
        } else if (element instanceof p_3749_n) {
            ((p_3749_n)element).n_1700_B(false);
        }
    }
}


