/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.M_766_z;
import lightning.product.Q_1939_l;
import lightning.product.W_2756_H;
import lightning.product.Y_1740_V;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.a_408_T;
import lightning.product.MinecraftAccess;
import lightning.product.y_1945_D;
import lombok.Generated;

public class t_2932_z
implements MinecraftAccess {
    public static final int n_1700_B = 3;
    private int J_1907_R = -1;
    private int R_4764_Y = -1;
    private boolean G_564_y = false;
    private boolean P_1922_E = false;
    private final Z_1993_T[] u_1723_Y = new Z_1993_T[3];

    public void n_1700_B(int wheelSlotIndex) {
        if (!this.G_564_y() || !this.P_1922_E(wheelSlotIndex)) {
            return;
        }
        this.J_1907_R = wheelSlotIndex;
        this.G_564_y = true;
        c_3005_b.n_1700_B(new Q_1939_l(t_2932_z.c_3005_b.Y_259_p));
    }

    @Y_1740_V
    public void n_1700_B(M_766_z e) {
        if (this.J_1907_R < 0) {
            return;
        }
        Slot slot = e.J_1907_R();
        if (slot == null) {
            return;
        }
        if ((e.P_1922_E() != a_408_T.n_1700_B || e.G_564_y() != 0 && e.G_564_y() != 1) && e.P_1922_E() != a_408_T.J_1907_R) {
            return;
        }
        e.n_1700_B(true);
        if (this.P_1922_E(this.J_1907_R)) {
            Z_1993_T clicked = slot.n_1700_B();
            if (clicked.n_1700_B()) {
                return;
            }
            if (!this.n_1700_B(clicked, this.J_1907_R)) {
                this.u_1723_Y[this.J_1907_R] = clicked.t_148_a();
                if (this.G_564_y() && this.n_1700_B(t_2932_z.c_3005_b.Y_259_p.l_1268_F.s_956_w(39), clicked)) {
                    this.R_4764_Y = this.J_1907_R;
                    this.P_1922_E = false;
                }
            }
        }
        this.J_1907_R = -1;
        if (t_2932_z.c_3005_b.Y_1740_V instanceof Q_1939_l) {
            c_3005_b.n_1700_B(new W_2756_H());
        }
    }

    @Y_1740_V
    public void n_1700_B(y_1945_D e) {
        if (!this.G_564_y) {
            return;
        }
        e.n_1700_B(true);
        this.G_564_y = false;
        this.J_1907_R = -1;
    }

    public Z_1993_T J_1907_R(int wheelIndex) {
        if (!this.P_1922_E(wheelIndex) || !this.G_564_y()) {
            return Z_1993_T.J_1907_R;
        }
        Z_1993_T template = this.u_1723_Y[wheelIndex];
        if (template == null || template.n_1700_B()) {
            return Z_1993_T.J_1907_R;
        }
        return this.n_1700_B(template) ? template : Z_1993_T.J_1907_R;
    }

    public void R_4764_Y(int wheelIndex) {
        if (!this.P_1922_E(wheelIndex)) {
            return;
        }
        this.u_1723_Y[wheelIndex] = null;
        if (this.R_4764_Y == wheelIndex) {
            this.R_4764_Y = -1;
        }
    }

    public void G_564_y(int index) {
        if (!this.P_1922_E(index)) {
            this.R_4764_Y = -1;
            return;
        }
        if (this.R_4764_Y != index) {
            this.R_4764_Y = index;
            this.P_1922_E = true;
        }
    }

    public boolean n_1700_B() {
        if (!this.P_1922_E) {
            return false;
        }
        this.P_1922_E = false;
        return true;
    }

    public boolean J_1907_R() {
        return this.P_1922_E;
    }

    private boolean n_1700_B(Z_1993_T target) {
        if (!this.G_564_y() || target.n_1700_B()) {
            return false;
        }
        for (int slot = 0; slot < t_2932_z.c_3005_b.Y_259_p.l_1268_F.Y_259_p(); ++slot) {
            Z_1993_T stack = t_2932_z.c_3005_b.Y_259_p.l_1268_F.s_956_w(slot);
            if (!this.n_1700_B(stack, target)) continue;
            return true;
        }
        return false;
    }

    private boolean G_564_y() {
        return t_2932_z.c_3005_b.Y_259_p != null && t_2932_z.c_3005_b.Y_601_j != null;
    }

    private boolean P_1922_E(int index) {
        return index >= 0 && index < this.u_1723_Y.length;
    }

    private boolean n_1700_B(Z_1993_T stack, int ignoreIndex) {
        for (int i = 0; i < this.u_1723_Y.length; ++i) {
            Z_1993_T saved;
            if (i == ignoreIndex || !this.n_1700_B(saved = this.u_1723_Y[i], stack)) continue;
            return true;
        }
        return false;
    }

    private boolean n_1700_B(Z_1993_T a, Z_1993_T b) {
        if (a == null || b == null || a.n_1700_B() || b.n_1700_B()) {
            return false;
        }
        if (!Z_1993_T.J_1907_R(a, b)) {
            return false;
        }
        return a.multiplayerClientSuggestionProvider().getString().equals(b.multiplayerClientSuggestionProvider().getString());
    }

    @Generated
    public int R_4764_Y() {
        return this.R_4764_Y;
    }
}



