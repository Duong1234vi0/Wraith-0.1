/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import java.util.function.Predicate;
import lightning.product.Container;
import lightning.product.NonNullList;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.q_2896_o;

public class ContainerHelper {
    public static Z_1993_T n_1700_B(List<Z_1993_T> stacks, int index, int amount) {
        return index >= 0 && index < stacks.size() && !stacks.get(index).n_1700_B() && amount > 0 ? stacks.get(index).n_1700_B(amount) : Z_1993_T.J_1907_R;
    }

    public static Z_1993_T n_1700_B(List<Z_1993_T> stacks, int index) {
        return index >= 0 && index < stacks.size() ? stacks.set(index, Z_1993_T.J_1907_R) : Z_1993_T.J_1907_R;
    }

    public static U_2912_j n_1700_B(U_2912_j tag, NonNullList<Z_1993_T> list) {
        return ContainerHelper.n_1700_B(tag, list, true);
    }

    public static U_2912_j n_1700_B(U_2912_j tag, NonNullList<Z_1993_T> list, boolean saveEmpty) {
        q_2896_o listnbt = new q_2896_o();
        for (int i = 0; i < list.size(); ++i) {
            Z_1993_T itemstack = list.get(i);
            if (itemstack.n_1700_B()) continue;
            U_2912_j compoundnbt = new U_2912_j();
            compoundnbt.n_1700_B("Slot", (byte)i);
            itemstack.J_1907_R(compoundnbt);
            listnbt.add(compoundnbt);
        }
        if (!listnbt.isEmpty() || saveEmpty) {
            tag.n_1700_B("Items", listnbt);
        }
        return tag;
    }

    public static void J_1907_R(U_2912_j tag, NonNullList<Z_1993_T> list) {
        q_2896_o listnbt = tag.G_564_y("Items", 10);
        for (int i = 0; i < listnbt.size(); ++i) {
            U_2912_j compoundnbt = listnbt.n_1700_B(i);
            int j = compoundnbt.u_1723_Y("Slot") & 0xFF;
            if (j < 0 || j >= list.size()) continue;
            list.set(j, Z_1993_T.n_1700_B(compoundnbt));
        }
    }

    public static int n_1700_B(Container p_233534_0_, Predicate<Z_1993_T> p_233534_1_, int p_233534_2_, boolean p_233534_3_) {
        int i = 0;
        for (int j = 0; j < p_233534_0_.Y_259_p(); ++j) {
            Z_1993_T itemstack = p_233534_0_.s_956_w(j);
            int k = ContainerHelper.n_1700_B(itemstack, p_233534_1_, p_233534_2_ - i, p_233534_3_);
            if (k > 0 && !p_233534_3_ && itemstack.n_1700_B()) {
                p_233534_0_.J_1907_R(j, Z_1993_T.J_1907_R);
            }
            i += k;
        }
        return i;
    }

    public static int n_1700_B(Z_1993_T p_233535_0_, Predicate<Z_1993_T> p_233535_1_, int p_233535_2_, boolean p_233535_3_) {
        if (!p_233535_0_.n_1700_B() && p_233535_1_.test(p_233535_0_)) {
            if (p_233535_3_) {
                return p_233535_0_.t_4043_B();
            }
            int i = p_233535_2_ < 0 ? p_233535_0_.t_4043_B() : Math.min(p_233535_2_, p_233535_0_.t_4043_B());
            p_233535_0_.v_4262_N(i);
            return i;
        }
        return 0;
    }
}


