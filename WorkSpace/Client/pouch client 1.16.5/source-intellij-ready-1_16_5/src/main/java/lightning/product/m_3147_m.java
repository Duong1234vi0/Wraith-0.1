/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.M_766_z;
import lightning.product.Q_1939_l;
import lightning.product.Y_1740_V;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.a_408_T;
import lightning.product.MinecraftAccess;
import lightning.product.i_1894_C;
import lightning.product.y_1945_D;
import lombok.Generated;

public class m_3147_m
implements MinecraftAccess {
    public static final int n_1700_B = 3;
    private int J_1907_R = -1;
    private int R_4764_Y = -1;
    private int G_564_y = -1;
    private boolean P_1922_E = false;
    private boolean u_1723_Y = false;
    private final Z_1993_T[] v_4262_N = new Z_1993_T[3];

    public void n_1700_B(int wheelSlotIndex) {
        if (!this.u_1723_Y() || !this.u_1723_Y(wheelSlotIndex)) {
            return;
        }
        this.J_1907_R = wheelSlotIndex;
        this.P_1922_E = true;
        c_3005_b.n_1700_B(new Q_1939_l(m_3147_m.c_3005_b.Y_259_p));
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
        if (this.u_1723_Y(this.J_1907_R)) {
            Z_1993_T clicked = slot.n_1700_B();
            if (clicked.n_1700_B()) {
                return;
            }
            if (!this.n_1700_B(clicked, this.J_1907_R)) {
                this.v_4262_N[this.J_1907_R] = clicked.t_148_a();
                if (this.u_1723_Y() && this.n_1700_B(m_3147_m.c_3005_b.Y_259_p.S_4035_N(), clicked)) {
                    this.R_4764_Y = this.J_1907_R;
                    this.u_1723_Y = false;
                }
            }
        }
        this.J_1907_R = -1;
        if (m_3147_m.c_3005_b.Y_1740_V instanceof Q_1939_l) {
            c_3005_b.n_1700_B(new i_1894_C());
        }
    }

    @Y_1740_V
    public void n_1700_B(y_1945_D e) {
        if (!this.P_1922_E) {
            return;
        }
        e.n_1700_B(true);
        this.P_1922_E = false;
        this.J_1907_R = -1;
    }

    public Z_1993_T J_1907_R(int wheelIndex) {
        if (!this.u_1723_Y(wheelIndex) || !this.u_1723_Y()) {
            return Z_1993_T.J_1907_R;
        }
        Z_1993_T template = this.v_4262_N[wheelIndex];
        if (template == null || template.n_1700_B()) {
            return Z_1993_T.J_1907_R;
        }
        return this.n_1700_B(template) ? template : Z_1993_T.J_1907_R;
    }

    public void R_4764_Y(int wheelIndex) {
        if (!this.u_1723_Y(wheelIndex)) {
            return;
        }
        this.v_4262_N[wheelIndex] = null;
        if (this.R_4764_Y == wheelIndex) {
            this.R_4764_Y = -1;
        }
        if (this.G_564_y == wheelIndex) {
            this.G_564_y = -1;
        }
    }

    public void G_564_y(int index) {
        if (!this.u_1723_Y(index)) {
            this.G_564_y = -1;
            return;
        }
        this.G_564_y = this.G_564_y == index ? -1 : index;
    }

    public Z_1993_T n_1700_B() {
        if (!this.u_1723_Y(this.G_564_y) || !this.u_1723_Y()) {
            return Z_1993_T.J_1907_R;
        }
        return this.J_1907_R(this.G_564_y);
    }

    public void P_1922_E(int index) {
        if (!this.u_1723_Y(index)) {
            this.R_4764_Y = -1;
            return;
        }
        if (this.R_4764_Y != index) {
            this.R_4764_Y = index;
            this.u_1723_Y = true;
        }
    }

    public boolean J_1907_R() {
        if (!this.u_1723_Y) {
            return false;
        }
        this.u_1723_Y = false;
        return true;
    }

    public boolean R_4764_Y() {
        return this.u_1723_Y;
    }

    private boolean n_1700_B(Z_1993_T target) {
        if (!this.u_1723_Y() || target.n_1700_B()) {
            return false;
        }
        for (int slot = 0; slot < m_3147_m.c_3005_b.Y_259_p.l_1268_F.Y_259_p(); ++slot) {
            Z_1993_T stack = m_3147_m.c_3005_b.Y_259_p.l_1268_F.s_956_w(slot);
            if (!this.n_1700_B(stack, target)) continue;
            return true;
        }
        return false;
    }

    private boolean u_1723_Y() {
        return m_3147_m.c_3005_b.Y_259_p != null && m_3147_m.c_3005_b.Y_601_j != null;
    }

    private boolean u_1723_Y(int index) {
        return index >= 0 && index < this.v_4262_N.length;
    }

    private boolean n_1700_B(Z_1993_T stack, int ignoreIndex) {
        for (int i = 0; i < this.v_4262_N.length; ++i) {
            Z_1993_T saved;
            if (i == ignoreIndex || !this.n_1700_B(saved = this.v_4262_N[i], stack)) continue;
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
    public int G_564_y() {
        return this.R_4764_Y;
    }

    @Generated
    public int P_1922_E() {
        return this.G_564_y;
    }
}



