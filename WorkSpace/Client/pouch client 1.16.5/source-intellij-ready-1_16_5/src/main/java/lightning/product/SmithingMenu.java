/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import javax.annotation.Nullable;
import lightning.product.K_1583_J;
import lightning.product.K_4074_S;
import lightning.product.MenuType;
import lightning.product.R_1120_N;
import lightning.product.W_3491_f;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.RecipeType;
import lightning.product.ContainerLevelAccess;

public class SmithingMenu
extends R_1120_N {
    private final b_4507_u v_4262_N;
    @Nullable
    private K_1583_J w_1484_f;
    private final List<K_1583_J> t_148_a;

    public SmithingMenu(int p_i231590_1_, W_3491_f p_i231590_2_) {
        this(p_i231590_1_, p_i231590_2_, ContainerLevelAccess.n_1700_B);
    }

    public SmithingMenu(int p_i231591_1_, W_3491_f p_i231591_2_, ContainerLevelAccess p_i231591_3_) {
        super(MenuType.Y_259_p, p_i231591_1_, p_i231591_2_, p_i231591_3_);
        this.v_4262_N = p_i231591_2_.P_1922_E.O_508_d;
        this.t_148_a = this.v_4262_N.s_956_w().n_1700_B(RecipeType.v_4262_N);
    }

    @Override
    protected boolean n_1700_B(K_4074_S p_230302_1_) {
        return p_230302_1_.n_1700_B(a_3742_W.i_4833_u);
    }

    @Override
    protected boolean n_1700_B(a_3913_L p_230303_1_, boolean p_230303_2_) {
        return this.w_1484_f != null && this.w_1484_f.n_1700_B(this.J_1907_R, this.v_4262_N);
    }

    @Override
    protected Z_1993_T n_1700_B(a_3913_L p_230301_1_, Z_1993_T p_230301_2_) {
        p_230301_2_.n_1700_B(p_230301_1_.O_508_d, p_230301_1_, p_230301_2_.t_4043_B());
        this.n_1700_B.n_1700_B(p_230301_1_);
        this.G_564_y(0);
        this.G_564_y(1);
        this.R_4764_Y.n_1700_B((b_4507_u p_234653_0_, c_1514_x p_234653_1_) -> p_234653_0_.R_4764_Y(1044, (c_1514_x)p_234653_1_, 0));
        return p_230301_2_;
    }

    private void G_564_y(int p_234654_1_) {
        Z_1993_T itemstack = this.J_1907_R.s_956_w(p_234654_1_);
        itemstack.v_4262_N(1);
        this.J_1907_R.J_1907_R(p_234654_1_, itemstack);
    }

    @Override
    public void n_1700_B() {
        List<K_1583_J> list = this.v_4262_N.s_956_w().J_1907_R(RecipeType.v_4262_N, this.J_1907_R, this.v_4262_N);
        if (list.isEmpty()) {
            this.n_1700_B.J_1907_R(0, Z_1993_T.J_1907_R);
        } else {
            this.w_1484_f = list.get(0);
            Z_1993_T itemstack = this.w_1484_f.n_1700_B(this.J_1907_R);
            this.n_1700_B.n_1700_B(this.w_1484_f);
            this.n_1700_B.J_1907_R(0, itemstack);
        }
    }

    @Override
    protected boolean n_1700_B(Z_1993_T p_241210_1_) {
        return this.t_148_a.stream().anyMatch(p_241444_1_ -> p_241444_1_.n_1700_B(p_241210_1_));
    }

    @Override
    public boolean n_1700_B(Z_1993_T stack, Slot slotIn) {
        return slotIn.R_4764_Y != this.n_1700_B && super.n_1700_B(stack, slotIn);
    }
}


