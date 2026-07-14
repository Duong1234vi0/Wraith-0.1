/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Container;
import lightning.product.NonNullList;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.b_3278_X;
import lightning.product.b_4507_u;
import lightning.product.g_2336_b;
import lightning.product.RecipeType;
import lightning.product.RecipeSerializer;
import lightning.product.q_1613_l;

public interface Recipe<C extends Container> {
    public boolean n_1700_B(C var1, b_4507_u var2);

    public Z_1993_T n_1700_B(C var1);

    public boolean n_1700_B(int var1, int var2);

    public Z_1993_T R_4764_Y();

    default public NonNullList<Z_1993_T> J_1907_R(C inv) {
        NonNullList<Z_1993_T> nonnulllist = NonNullList.n_1700_B(inv.Y_259_p(), Z_1993_T.J_1907_R);
        for (int i = 0; i < nonnulllist.size(); ++i) {
            q_1613_l item = inv.s_956_w(i).J_1907_R();
            if (!item.multiplayerClientSuggestionProvider()) continue;
            nonnulllist.set(i, new Z_1993_T(item.t_1786_h()));
        }
        return nonnulllist;
    }

    default public NonNullList<b_3278_X> n_1700_B() {
        return NonNullList.n_1700_B();
    }

    default public boolean t_148_a() {
        return false;
    }

    default public String G_564_y() {
        return "";
    }

    default public Z_1993_T w_1484_f() {
        return new Z_1993_T(a_3742_W.O_2934_T);
    }

    public g_2336_b u_1723_Y();

    public RecipeSerializer<?> E_();

    public RecipeType<?> v_4262_N();
}


