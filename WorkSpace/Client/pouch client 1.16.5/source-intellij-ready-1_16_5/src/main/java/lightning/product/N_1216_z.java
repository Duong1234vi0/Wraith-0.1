/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.stream.Collectors;
import lightning.product.Container;
import lightning.product.NonNullList;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.ContainerHelper;
import lightning.product.q_1613_l;
import lightning.product.q_2896_o;
import lightning.product.r_4432_i;
import lightning.product.u_3375_n;
import lightning.product.y_452_M;

public class N_1216_z
implements Container,
y_452_M {
    private final int n_1700_B;
    private final NonNullList<Z_1993_T> J_1907_R;
    private List<u_3375_n> R_4764_Y;

    public N_1216_z(int numSlots) {
        this.n_1700_B = numSlots;
        this.J_1907_R = NonNullList.n_1700_B(numSlots, Z_1993_T.J_1907_R);
    }

    public N_1216_z(Z_1993_T ... stacksIn) {
        this.n_1700_B = stacksIn.length;
        this.J_1907_R = NonNullList.n_1700_B(Z_1993_T.J_1907_R, stacksIn);
    }

    public void n_1700_B(u_3375_n listener) {
        if (this.R_4764_Y == null) {
            this.R_4764_Y = Lists.newArrayList();
        }
        this.R_4764_Y.add(listener);
    }

    public void J_1907_R(u_3375_n listener) {
        this.R_4764_Y.remove(listener);
    }

    @Override
    public Z_1993_T s_956_w(int index) {
        return index >= 0 && index < this.J_1907_R.size() ? this.J_1907_R.get(index) : Z_1993_T.J_1907_R;
    }

    public List<Z_1993_T> G_564_y() {
        List<Z_1993_T> list = this.J_1907_R.stream().filter(p_233544_0_ -> !p_233544_0_.n_1700_B()).collect(Collectors.toList());
        this.C_2741_M();
        return list;
    }

    @Override
    public Z_1993_T n_1700_B(int index, int count) {
        Z_1993_T itemstack = ContainerHelper.n_1700_B(this.J_1907_R, index, count);
        if (!itemstack.n_1700_B()) {
            this.J_1907_R();
        }
        return itemstack;
    }

    public Z_1993_T n_1700_B(q_1613_l p_223374_1_, int p_223374_2_) {
        Z_1993_T itemstack = new Z_1993_T(p_223374_1_, 0);
        for (int i = this.n_1700_B - 1; i >= 0; --i) {
            Z_1993_T itemstack1 = this.s_956_w(i);
            if (!itemstack1.J_1907_R().equals(p_223374_1_)) continue;
            int j = p_223374_2_ - itemstack.t_4043_B();
            Z_1993_T itemstack2 = itemstack1.n_1700_B(j);
            itemstack.u_1723_Y(itemstack2.t_4043_B());
            if (itemstack.t_4043_B() == p_223374_2_) break;
        }
        if (!itemstack.n_1700_B()) {
            this.J_1907_R();
        }
        return itemstack;
    }

    public Z_1993_T n_1700_B(Z_1993_T stack) {
        Z_1993_T itemstack = stack.t_148_a();
        this.G_564_y(itemstack);
        if (itemstack.n_1700_B()) {
            return Z_1993_T.J_1907_R;
        }
        this.R_4764_Y(itemstack);
        return itemstack.n_1700_B() ? Z_1993_T.J_1907_R : itemstack;
    }

    public boolean J_1907_R(Z_1993_T p_233541_1_) {
        boolean flag = false;
        for (Z_1993_T itemstack : this.J_1907_R) {
            if (!itemstack.n_1700_B() && (!this.n_1700_B(itemstack, p_233541_1_) || itemstack.t_4043_B() >= itemstack.R_4764_Y())) continue;
            flag = true;
            break;
        }
        return flag;
    }

    @Override
    public Z_1993_T u_2550_I(int index) {
        Z_1993_T itemstack = this.J_1907_R.get(index);
        if (itemstack.n_1700_B()) {
            return Z_1993_T.J_1907_R;
        }
        this.J_1907_R.set(index, Z_1993_T.J_1907_R);
        return itemstack;
    }

    @Override
    public void J_1907_R(int index, Z_1993_T stack) {
        this.J_1907_R.set(index, stack);
        if (!stack.n_1700_B() && stack.t_4043_B() > this.J_()) {
            stack.P_1922_E(this.J_());
        }
        this.J_1907_R();
    }

    @Override
    public int Y_259_p() {
        return this.n_1700_B;
    }

    @Override
    public boolean Q_2552_b() {
        for (Z_1993_T itemstack : this.J_1907_R) {
            if (itemstack.n_1700_B()) continue;
            return false;
        }
        return true;
    }

    @Override
    public void J_1907_R() {
        if (this.R_4764_Y != null) {
            for (u_3375_n iinventorychangedlistener : this.R_4764_Y) {
                iinventorychangedlistener.n_1700_B(this);
            }
        }
    }

    @Override
    public boolean R_4764_Y(a_3913_L player) {
        return true;
    }

    @Override
    public void C_2741_M() {
        this.J_1907_R.clear();
        this.J_1907_R();
    }

    @Override
    public void n_1700_B(r_4432_i helper) {
        for (Z_1993_T itemstack : this.J_1907_R) {
            helper.J_1907_R(itemstack);
        }
    }

    public String toString() {
        return this.J_1907_R.stream().filter(p_223371_0_ -> !p_223371_0_.n_1700_B()).collect(Collectors.toList()).toString();
    }

    private void R_4764_Y(Z_1993_T p_223375_1_) {
        for (int i = 0; i < this.n_1700_B; ++i) {
            Z_1993_T itemstack = this.s_956_w(i);
            if (!itemstack.n_1700_B()) continue;
            this.J_1907_R(i, p_223375_1_.t_148_a());
            p_223375_1_.P_1922_E(0);
            return;
        }
    }

    private void G_564_y(Z_1993_T p_223372_1_) {
        for (int i = 0; i < this.n_1700_B; ++i) {
            Z_1993_T itemstack = this.s_956_w(i);
            if (!this.n_1700_B(itemstack, p_223372_1_)) continue;
            this.J_1907_R(p_223372_1_, itemstack);
            if (!p_223372_1_.n_1700_B()) continue;
            return;
        }
    }

    private boolean n_1700_B(Z_1993_T p_233540_1_, Z_1993_T p_233540_2_) {
        return p_233540_1_.J_1907_R() == p_233540_2_.J_1907_R() && Z_1993_T.n_1700_B(p_233540_1_, p_233540_2_);
    }

    private void J_1907_R(Z_1993_T p_223373_1_, Z_1993_T p_223373_2_) {
        int i = Math.min(this.J_(), p_223373_2_.R_4764_Y());
        int j = Math.min(p_223373_1_.t_4043_B(), i - p_223373_2_.t_4043_B());
        if (j > 0) {
            p_223373_2_.u_1723_Y(j);
            p_223373_1_.v_4262_N(j);
            this.J_1907_R();
        }
    }

    public void n_1700_B(q_2896_o p_70486_1_) {
        for (int i = 0; i < p_70486_1_.size(); ++i) {
            Z_1993_T itemstack = Z_1993_T.n_1700_B(p_70486_1_.n_1700_B(i));
            if (itemstack.n_1700_B()) continue;
            this.n_1700_B(itemstack);
        }
    }

    public q_2896_o R_4764_Y() {
        q_2896_o listnbt = new q_2896_o();
        for (int i = 0; i < this.Y_259_p(); ++i) {
            Z_1993_T itemstack = this.s_956_w(i);
            if (itemstack.n_1700_B()) continue;
            listnbt.add(itemstack.J_1907_R(new U_2912_j()));
        }
        return listnbt;
    }
}


