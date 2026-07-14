/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.function.Predicate;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.K_4074_S;
import lightning.product.Container;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.NonNullList;
import lightning.product.R_2515_i;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.a_4764_N;
import lightning.product.b_4507_u;
import lightning.product.e_1174_E;
import lightning.product.Nameable;
import lightning.product.ContainerHelper;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.q_1613_l;
import lightning.product.q_2896_o;
import lightning.product.r_109_r;
import lightning.product.CrashReportCategory;
import lightning.product.r_4432_i;
import lightning.product.x_282_a;

public class W_3491_f
implements Container,
Nameable {
    public final NonNullList<Z_1993_T> n_1700_B = NonNullList.n_1700_B(36, Z_1993_T.J_1907_R);
    public final NonNullList<Z_1993_T> J_1907_R = NonNullList.n_1700_B(4, Z_1993_T.J_1907_R);
    public final NonNullList<Z_1993_T> R_4764_Y = NonNullList.n_1700_B(1, Z_1993_T.J_1907_R);
    private final List<NonNullList<Z_1993_T>> u_1723_Y = ImmutableList.of(this.n_1700_B, this.J_1907_R, this.R_4764_Y);
    public int G_564_y;
    public final a_3913_L P_1922_E;
    private Z_1993_T v_4262_N = Z_1993_T.J_1907_R;
    private int w_1484_f;

    public W_3491_f(a_3913_L playerIn) {
        this.P_1922_E = playerIn;
    }

    public Z_1993_T R_4764_Y() {
        return W_3491_f.J_1907_R(this.G_564_y) ? this.n_1700_B.get(this.G_564_y) : Z_1993_T.J_1907_R;
    }

    public static int G_564_y() {
        return 9;
    }

    private boolean n_1700_B(Z_1993_T stack1, Z_1993_T stack2) {
        return !stack1.n_1700_B() && this.J_1907_R(stack1, stack2) && stack1.G_564_y() && stack1.t_4043_B() < stack1.R_4764_Y() && stack1.t_4043_B() < this.J_();
    }

    private boolean J_1907_R(Z_1993_T stack1, Z_1993_T stack2) {
        return stack1.J_1907_R() == stack2.J_1907_R() && Z_1993_T.n_1700_B(stack1, stack2);
    }

    public int P_1922_E() {
        for (int i = 0; i < this.n_1700_B.size(); ++i) {
            if (!this.n_1700_B.get(i).n_1700_B()) continue;
            return i;
        }
        return -1;
    }

    public void n_1700_B(Z_1993_T stack) {
        int i = this.J_1907_R(stack);
        if (W_3491_f.J_1907_R(i)) {
            this.G_564_y = i;
        } else if (i == -1) {
            int j;
            this.G_564_y = this.u_1723_Y();
            if (!this.n_1700_B.get(this.G_564_y).n_1700_B() && (j = this.P_1922_E()) != -1) {
                this.n_1700_B.set(j, this.n_1700_B.get(this.G_564_y));
            }
            this.n_1700_B.set(this.G_564_y, stack);
        } else {
            this.n_1700_B(i);
        }
    }

    public void n_1700_B(int index) {
        this.G_564_y = this.u_1723_Y();
        Z_1993_T itemstack = this.n_1700_B.get(this.G_564_y);
        this.n_1700_B.set(this.G_564_y, this.n_1700_B.get(index));
        this.n_1700_B.set(index, itemstack);
    }

    public static boolean J_1907_R(int index) {
        return index >= 0 && index < 9;
    }

    public int J_1907_R(Z_1993_T stack) {
        for (int i = 0; i < this.n_1700_B.size(); ++i) {
            if (this.n_1700_B.get(i).n_1700_B() || !this.J_1907_R(stack, this.n_1700_B.get(i))) continue;
            return i;
        }
        return -1;
    }

    public int R_4764_Y(Z_1993_T p_194014_1_) {
        for (int i = 0; i < this.n_1700_B.size(); ++i) {
            Z_1993_T itemstack = this.n_1700_B.get(i);
            if (this.n_1700_B.get(i).n_1700_B() || !this.J_1907_R(p_194014_1_, this.n_1700_B.get(i)) || this.n_1700_B.get(i).u_1723_Y() || itemstack.k_2293_S() || itemstack.Y_601_j()) continue;
            return i;
        }
        return -1;
    }

    public int u_1723_Y() {
        for (int i = 0; i < 9; ++i) {
            int j = (this.G_564_y + i) % 9;
            if (!this.n_1700_B.get(j).n_1700_B()) continue;
            return j;
        }
        for (int k = 0; k < 9; ++k) {
            int l = (this.G_564_y + k) % 9;
            if (this.n_1700_B.get(l).k_2293_S()) continue;
            return l;
        }
        return this.G_564_y;
    }

    public void n_1700_B(double direction) {
        if (direction > 0.0) {
            direction = 1.0;
        }
        if (direction < 0.0) {
            direction = -1.0;
        }
        this.G_564_y = (int)((double)this.G_564_y - direction);
        while (this.G_564_y < 0) {
            this.G_564_y += 9;
        }
        while (this.G_564_y >= 9) {
            this.G_564_y -= 9;
        }
    }

    public int n_1700_B(Predicate<Z_1993_T> p_234564_1_, int p_234564_2_, Container p_234564_3_) {
        int i = 0;
        boolean flag = p_234564_2_ == 0;
        i += ContainerHelper.n_1700_B(this, p_234564_1_, p_234564_2_ - i, flag);
        i += ContainerHelper.n_1700_B(p_234564_3_, p_234564_1_, p_234564_2_ - i, flag);
        i += ContainerHelper.n_1700_B(this.v_4262_N, p_234564_1_, p_234564_2_ - i, flag);
        if (this.v_4262_N.n_1700_B()) {
            this.v_4262_N = Z_1993_T.J_1907_R;
        }
        return i;
    }

    private int t_148_a(Z_1993_T itemStackIn) {
        int i = this.G_564_y(itemStackIn);
        if (i == -1) {
            i = this.P_1922_E();
        }
        return i == -1 ? itemStackIn.t_4043_B() : this.R_4764_Y(i, itemStackIn);
    }

    private int R_4764_Y(int p_191973_1_, Z_1993_T p_191973_2_) {
        q_1613_l item = p_191973_2_.J_1907_R();
        int i = p_191973_2_.t_4043_B();
        Z_1993_T itemstack = this.s_956_w(p_191973_1_);
        if (itemstack.n_1700_B()) {
            itemstack = new Z_1993_T(item, 0);
            if (p_191973_2_.h_1847_R()) {
                itemstack.R_4764_Y(p_191973_2_.Q_4569_t().v_4262_N());
            }
            this.J_1907_R(p_191973_1_, itemstack);
        }
        int j = i;
        if (i > itemstack.R_4764_Y() - itemstack.t_4043_B()) {
            j = itemstack.R_4764_Y() - itemstack.t_4043_B();
        }
        if (j > this.J_() - itemstack.t_4043_B()) {
            j = this.J_() - itemstack.t_4043_B();
        }
        if (j == 0) {
            return i;
        }
        itemstack.u_1723_Y(j);
        itemstack.G_564_y(5);
        return i -= j;
    }

    public int G_564_y(Z_1993_T itemStackIn) {
        if (this.n_1700_B(this.s_956_w(this.G_564_y), itemStackIn)) {
            return this.G_564_y;
        }
        if (this.n_1700_B(this.s_956_w(40), itemStackIn)) {
            return 40;
        }
        for (int i = 0; i < this.n_1700_B.size(); ++i) {
            if (!this.n_1700_B(this.n_1700_B.get(i), itemStackIn)) continue;
            return i;
        }
        return -1;
    }

    public void v_4262_N() {
        for (NonNullList<Z_1993_T> nonnulllist : this.u_1723_Y) {
            for (int i = 0; i < nonnulllist.size(); ++i) {
                if (nonnulllist.get(i).n_1700_B()) continue;
                nonnulllist.get(i).n_1700_B(this.P_1922_E.O_508_d, this.P_1922_E, i, this.G_564_y == i);
            }
        }
    }

    public boolean P_1922_E(Z_1993_T itemStackIn) {
        return this.n_1700_B(-1, itemStackIn);
    }

    public boolean n_1700_B(int slotIn, Z_1993_T stack) {
        if (stack.n_1700_B()) {
            return false;
        }
        try {
            int i;
            if (stack.u_1723_Y()) {
                if (slotIn == -1) {
                    slotIn = this.P_1922_E();
                }
                if (slotIn >= 0) {
                    this.n_1700_B.set(slotIn, stack.t_148_a());
                    this.n_1700_B.get(slotIn).G_564_y(5);
                    stack.P_1922_E(0);
                    return true;
                }
                if (this.P_1922_E.C_415_h.G_564_y) {
                    stack.P_1922_E(0);
                    return true;
                }
                return false;
            }
            do {
                i = stack.t_4043_B();
                if (slotIn == -1) {
                    stack.P_1922_E(this.t_148_a(stack));
                    continue;
                }
                stack.P_1922_E(this.R_4764_Y(slotIn, stack));
            } while (!stack.n_1700_B() && stack.t_4043_B() < i);
            if (stack.t_4043_B() == i && this.P_1922_E.C_415_h.G_564_y) {
                stack.P_1922_E(0);
                return true;
            }
            return stack.t_4043_B() < i;
        }
        catch (Throwable throwable) {
            n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Adding item to inventory");
            CrashReportCategory crashreportcategory = crashreport.n_1700_B("Item being added");
            crashreportcategory.n_1700_B("Item ID", q_1613_l.n_1700_B(stack.J_1907_R()));
            crashreportcategory.n_1700_B("Item data", stack.v_4262_N());
            crashreportcategory.n_1700_B("Item name", () -> stack.multiplayerClientSuggestionProvider().getString());
            throw new ReportedException(crashreport);
        }
    }

    public void n_1700_B(b_4507_u worldIn, Z_1993_T stack) {
        if (!worldIn.Y_259_p) {
            while (!stack.n_1700_B()) {
                int i = this.G_564_y(stack);
                if (i == -1) {
                    i = this.P_1922_E();
                }
                if (i == -1) {
                    this.P_1922_E.n_1700_B(stack, false);
                    break;
                }
                int j = stack.R_4764_Y() - this.s_956_w(i).t_4043_B();
                if (!this.n_1700_B(i, stack.n_1700_B(j))) continue;
                ((B_4088_l)this.P_1922_E).n_1700_B.n_1700_B(new a_4764_N(-2, i, this.s_956_w(i)));
            }
        }
    }

    @Override
    public Z_1993_T n_1700_B(int index, int count) {
        NonNullList<Z_1993_T> list = null;
        for (NonNullList<Z_1993_T> nonnulllist : this.u_1723_Y) {
            if (index < nonnulllist.size()) {
                list = nonnulllist;
                break;
            }
            index -= nonnulllist.size();
        }
        return list != null && !((Z_1993_T)list.get(index)).n_1700_B() ? ContainerHelper.n_1700_B(list, index, count) : Z_1993_T.J_1907_R;
    }

    public void u_1723_Y(Z_1993_T stack) {
        block0: for (NonNullList<Z_1993_T> nonnulllist : this.u_1723_Y) {
            for (int i = 0; i < nonnulllist.size(); ++i) {
                if (nonnulllist.get(i) != stack) continue;
                nonnulllist.set(i, Z_1993_T.J_1907_R);
                continue block0;
            }
        }
    }

    @Override
    public Z_1993_T u_2550_I(int index) {
        NonNullList<Z_1993_T> nonnulllist = null;
        for (NonNullList<Z_1993_T> nonnulllist1 : this.u_1723_Y) {
            if (index < nonnulllist1.size()) {
                nonnulllist = nonnulllist1;
                break;
            }
            index -= nonnulllist1.size();
        }
        if (nonnulllist != null && !((Z_1993_T)nonnulllist.get(index)).n_1700_B()) {
            Z_1993_T itemstack = nonnulllist.get(index);
            nonnulllist.set(index, Z_1993_T.J_1907_R);
            return itemstack;
        }
        return Z_1993_T.J_1907_R;
    }

    @Override
    public void J_1907_R(int index, Z_1993_T stack) {
        NonNullList<Z_1993_T> nonnulllist = null;
        for (NonNullList<Z_1993_T> nonnulllist1 : this.u_1723_Y) {
            if (index < nonnulllist1.size()) {
                nonnulllist = nonnulllist1;
                break;
            }
            index -= nonnulllist1.size();
        }
        if (nonnulllist != null) {
            nonnulllist.set(index, stack);
        }
    }

    public float n_1700_B(K_4074_S state) {
        return this.n_1700_B.get(this.G_564_y).n_1700_B(state);
    }

    public q_2896_o n_1700_B(q_2896_o nbtTagListIn) {
        for (int i = 0; i < this.n_1700_B.size(); ++i) {
            if (this.n_1700_B.get(i).n_1700_B()) continue;
            U_2912_j compoundnbt = new U_2912_j();
            compoundnbt.n_1700_B("Slot", (byte)i);
            this.n_1700_B.get(i).J_1907_R(compoundnbt);
            nbtTagListIn.add(compoundnbt);
        }
        for (int j = 0; j < this.J_1907_R.size(); ++j) {
            if (this.J_1907_R.get(j).n_1700_B()) continue;
            U_2912_j compoundnbt1 = new U_2912_j();
            compoundnbt1.n_1700_B("Slot", (byte)(j + 100));
            this.J_1907_R.get(j).J_1907_R(compoundnbt1);
            nbtTagListIn.add(compoundnbt1);
        }
        for (int k = 0; k < this.R_4764_Y.size(); ++k) {
            if (this.R_4764_Y.get(k).n_1700_B()) continue;
            U_2912_j compoundnbt2 = new U_2912_j();
            compoundnbt2.n_1700_B("Slot", (byte)(k + 150));
            this.R_4764_Y.get(k).J_1907_R(compoundnbt2);
            nbtTagListIn.add(compoundnbt2);
        }
        return nbtTagListIn;
    }

    public void J_1907_R(q_2896_o nbtTagListIn) {
        this.n_1700_B.clear();
        this.J_1907_R.clear();
        this.R_4764_Y.clear();
        for (int i = 0; i < nbtTagListIn.size(); ++i) {
            U_2912_j compoundnbt = nbtTagListIn.n_1700_B(i);
            int j = compoundnbt.u_1723_Y("Slot") & 0xFF;
            Z_1993_T itemstack = Z_1993_T.n_1700_B(compoundnbt);
            if (itemstack.n_1700_B()) continue;
            if (j >= 0 && j < this.n_1700_B.size()) {
                this.n_1700_B.set(j, itemstack);
                continue;
            }
            if (j >= 100 && j < this.J_1907_R.size() + 100) {
                this.J_1907_R.set(j - 100, itemstack);
                continue;
            }
            if (j < 150 || j >= this.R_4764_Y.size() + 150) continue;
            this.R_4764_Y.set(j - 150, itemstack);
        }
    }

    @Override
    public int Y_259_p() {
        return this.n_1700_B.size() + this.J_1907_R.size() + this.R_4764_Y.size();
    }

    @Override
    public boolean Q_2552_b() {
        for (Z_1993_T itemstack : this.n_1700_B) {
            if (itemstack.n_1700_B()) continue;
            return false;
        }
        for (Z_1993_T itemstack1 : this.J_1907_R) {
            if (itemstack1.n_1700_B()) continue;
            return false;
        }
        for (Z_1993_T itemstack2 : this.R_4764_Y) {
            if (itemstack2.n_1700_B()) continue;
            return false;
        }
        return true;
    }

    @Override
    public Z_1993_T s_956_w(int index) {
        NonNullList<Z_1993_T> list = null;
        for (NonNullList<Z_1993_T> nonnulllist : this.u_1723_Y) {
            if (index < nonnulllist.size()) {
                list = nonnulllist;
                break;
            }
            index -= nonnulllist.size();
        }
        return list == null ? Z_1993_T.J_1907_R : (Z_1993_T)list.get(index);
    }

    @Override
    public x_282_a O_1309_Q() {
        return new F_2904_S("container.inventory");
    }

    public Z_1993_T R_4764_Y(int slotIn) {
        return this.J_1907_R.get(slotIn);
    }

    public void n_1700_B(P_11_z p_234563_1_, float p_234563_2_) {
        if (!(p_234563_2_ <= 0.0f)) {
            if ((p_234563_2_ /= 4.0f) < 1.0f) {
                p_234563_2_ = 1.0f;
            }
            for (int i = 0; i < this.J_1907_R.size(); ++i) {
                Z_1993_T itemstack = this.J_1907_R.get(i);
                if (p_234563_1_.M_182_A() && itemstack.J_1907_R().C_2741_M() || !(itemstack.J_1907_R() instanceof R_2515_i)) continue;
                int j = i;
                itemstack.n_1700_B((int)p_234563_2_, this.P_1922_E, (T p_214023_1_) -> p_214023_1_.R_4764_Y(e_1174_E.n_1700_B(e_1174_E.n_1700_B.J_1907_R, j)));
            }
        }
    }

    public void w_1484_f() {
        for (List list : this.u_1723_Y) {
            for (int i = 0; i < list.size(); ++i) {
                Z_1993_T itemstack = (Z_1993_T)list.get(i);
                if (itemstack.n_1700_B()) continue;
                this.P_1922_E.n_1700_B(itemstack, true, false);
                list.set(i, Z_1993_T.J_1907_R);
            }
        }
    }

    @Override
    public void J_1907_R() {
        ++this.w_1484_f;
    }

    public int t_148_a() {
        return this.w_1484_f;
    }

    public void v_4262_N(Z_1993_T itemStackIn) {
        this.v_4262_N = itemStackIn;
    }

    public Z_1993_T s_956_w() {
        return this.v_4262_N;
    }

    @Override
    public boolean R_4764_Y(a_3913_L player) {
        if (this.P_1922_E.t_4219_U) {
            return false;
        }
        return !(player.G_564_y((N_4263_v)this.P_1922_E) > 64.0);
    }

    public boolean w_1484_f(Z_1993_T itemStackIn) {
        for (List list : this.u_1723_Y) {
            for (Z_1993_T itemstack : list) {
                if (itemstack.n_1700_B() || !itemstack.n_1700_B(itemStackIn)) continue;
                return true;
            }
        }
        return false;
    }

    public boolean n_1700_B(r_109_r<q_1613_l> itemTag) {
        for (List list : this.u_1723_Y) {
            for (Z_1993_T itemstack : list) {
                if (itemstack.n_1700_B() || !itemTag.n_1700_B(itemstack.J_1907_R())) continue;
                return true;
            }
        }
        return false;
    }

    public void n_1700_B(W_3491_f playerInventory) {
        for (int i = 0; i < this.Y_259_p(); ++i) {
            this.J_1907_R(i, playerInventory.s_956_w(i));
        }
        this.G_564_y = playerInventory.G_564_y;
    }

    @Override
    public void C_2741_M() {
        for (List list : this.u_1723_Y) {
            list.clear();
        }
    }

    public void n_1700_B(r_4432_i p_201571_1_) {
        for (Z_1993_T itemstack : this.n_1700_B) {
            p_201571_1_.n_1700_B(itemstack);
        }
    }
}


