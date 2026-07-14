/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ForwardingList
 */
package lightning.product;

import com.google.common.collect.ForwardingList;
import java.util.Collection;
import java.util.List;
import lightning.product.NonNullList;
import lightning.product.U_2912_j;
import lightning.product.W_3491_f;
import lightning.product.Z_1993_T;
import lightning.product.q_2896_o;

public class Hotbar
extends ForwardingList<Z_1993_T> {
    private final NonNullList<Z_1993_T> n_1700_B = NonNullList.n_1700_B(W_3491_f.G_564_y(), Z_1993_T.J_1907_R);

    protected List<Z_1993_T> delegate() {
        return this.n_1700_B;
    }

    public q_2896_o n_1700_B() {
        q_2896_o listnbt = new q_2896_o();
        for (Z_1993_T itemstack : this.delegate()) {
            listnbt.add(itemstack.J_1907_R(new U_2912_j()));
        }
        return listnbt;
    }

    public void n_1700_B(q_2896_o tag) {
        Collection list = this.delegate();
        for (int i = 0; i < list.size(); ++i) {
            list.set(i, Z_1993_T.n_1700_B(tag.n_1700_B(i)));
        }
    }

    public boolean isEmpty() {
        for (Z_1993_T itemstack : this.delegate()) {
            if (itemstack.n_1700_B()) continue;
            return false;
        }
        return true;
    }
}


