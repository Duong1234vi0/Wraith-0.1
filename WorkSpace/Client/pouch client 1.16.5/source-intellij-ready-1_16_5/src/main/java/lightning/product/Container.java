/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Set;
import lightning.product.Clearable;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.q_1613_l;

public interface Container
extends Clearable {
    public int Y_259_p();

    public boolean Q_2552_b();

    public Z_1993_T s_956_w(int var1);

    public Z_1993_T n_1700_B(int var1, int var2);

    public Z_1993_T u_2550_I(int var1);

    public void J_1907_R(int var1, Z_1993_T var2);

    default public int J_() {
        return 64;
    }

    public void J_1907_R();

    public boolean R_4764_Y(a_3913_L var1);

    default public void b_(a_3913_L player) {
    }

    default public void J_1907_R(a_3913_L player) {
    }

    default public boolean a_(int index, Z_1993_T stack) {
        return true;
    }

    default public int n_1700_B(q_1613_l itemIn) {
        int i = 0;
        for (int j = 0; j < this.Y_259_p(); ++j) {
            Z_1993_T itemstack = this.s_956_w(j);
            if (!itemstack.J_1907_R().equals(itemIn)) continue;
            i += itemstack.t_4043_B();
        }
        return i;
    }

    default public boolean n_1700_B(Set<q_1613_l> set) {
        for (int i = 0; i < this.Y_259_p(); ++i) {
            Z_1993_T itemstack = this.s_956_w(i);
            if (!set.contains(itemstack.J_1907_R()) || itemstack.t_4043_B() <= 0) continue;
            return true;
        }
        return false;
    }
}


