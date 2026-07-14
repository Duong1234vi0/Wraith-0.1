/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.NonNullList;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.b_3278_X;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.Recipe;
import lightning.product.j_4436_c;
import lightning.product.k_2603_m;
import lightning.product.q_1613_l;

public abstract class AbstractFurnaceRecipeBookComponent
extends j_4436_c {
    private Iterator<q_1613_l> u_1723_Y;
    private Set<q_1613_l> v_4262_N;
    private Slot w_1484_f;
    private q_1613_l t_148_a;
    private float s_956_w;

    @Override
    protected void n_1700_B() {
        this.R_4764_Y.n_1700_B(152, 182, 28, 18, n_1700_B);
    }

    @Override
    public void n_1700_B(@Nullable Slot slotIn) {
        super.n_1700_B(slotIn);
        if (slotIn != null && slotIn.G_564_y < this.G_564_y.P_1922_E()) {
            this.w_1484_f = null;
        }
    }

    @Override
    public void n_1700_B(Recipe<?> p_193951_1_, List<Slot> p_193951_2_) {
        Z_1993_T itemstack = p_193951_1_.R_4764_Y();
        this.J_1907_R.n_1700_B(p_193951_1_);
        this.J_1907_R.n_1700_B(b_3278_X.n_1700_B(itemstack), p_193951_2_.get((int)2).P_1922_E, p_193951_2_.get((int)2).u_1723_Y);
        NonNullList<b_3278_X> nonnulllist = p_193951_1_.n_1700_B();
        this.w_1484_f = p_193951_2_.get(1);
        if (this.v_4262_N == null) {
            this.v_4262_N = this.J_1907_R();
        }
        this.u_1723_Y = this.v_4262_N.iterator();
        this.t_148_a = null;
        Iterator iterator = nonnulllist.iterator();
        for (int i = 0; i < 2; ++i) {
            if (!iterator.hasNext()) {
                return;
            }
            b_3278_X ingredient = (b_3278_X)iterator.next();
            if (ingredient.G_564_y()) continue;
            Slot slot = p_193951_2_.get(i);
            this.J_1907_R.n_1700_B(ingredient, slot.P_1922_E, slot.u_1723_Y);
        }
    }

    protected abstract Set<q_1613_l> J_1907_R();

    @Override
    public void n_1700_B(g_221_o p_230477_1_, int p_230477_2_, int p_230477_3_, boolean p_230477_4_, float p_230477_5_) {
        super.n_1700_B(p_230477_1_, p_230477_2_, p_230477_3_, p_230477_4_, p_230477_5_);
        if (this.w_1484_f != null) {
            if (!k_2603_m.hasControlDown()) {
                this.s_956_w += p_230477_5_;
            }
            int i = this.w_1484_f.P_1922_E + p_230477_2_;
            int j = this.w_1484_f.u_1723_Y + p_230477_3_;
            C_2701_A.fill(p_230477_1_, i, j, i + 16, j + 16, 0x30FF0000);
            this.P_1922_E.r_715_M().n_1700_B(this.P_1922_E.Y_259_p, this.s_956_w().Y_601_j(), i, j);
            c_4037_x.J_1907_R(516);
            C_2701_A.fill(p_230477_1_, i, j, i + 16, j + 16, 0x30FFFFFF);
            c_4037_x.J_1907_R(515);
        }
    }

    private q_1613_l s_956_w() {
        if (this.t_148_a == null || this.s_956_w > 30.0f) {
            this.s_956_w = 0.0f;
            if (this.u_1723_Y == null || !this.u_1723_Y.hasNext()) {
                if (this.v_4262_N == null) {
                    this.v_4262_N = this.J_1907_R();
                }
                this.u_1723_Y = this.v_4262_N.iterator();
            }
            this.t_148_a = this.u_1723_Y.next();
        }
        return this.t_148_a;
    }
}


