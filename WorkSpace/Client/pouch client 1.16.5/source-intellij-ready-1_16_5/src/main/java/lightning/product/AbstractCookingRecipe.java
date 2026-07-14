/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Container;
import lightning.product.NonNullList;
import lightning.product.Z_1993_T;
import lightning.product.b_3278_X;
import lightning.product.b_4507_u;
import lightning.product.g_2336_b;
import lightning.product.RecipeType;
import lightning.product.Recipe;

public abstract class AbstractCookingRecipe
implements Recipe<Container> {
    protected final RecipeType<?> n_1700_B;
    protected final g_2336_b J_1907_R;
    protected final String R_4764_Y;
    protected final b_3278_X G_564_y;
    protected final Z_1993_T P_1922_E;
    protected final float u_1723_Y;
    protected final int v_4262_N;

    public AbstractCookingRecipe(RecipeType<?> typeIn, g_2336_b idIn, String groupIn, b_3278_X ingredientIn, Z_1993_T resultIn, float experienceIn, int cookTimeIn) {
        this.n_1700_B = typeIn;
        this.J_1907_R = idIn;
        this.R_4764_Y = groupIn;
        this.G_564_y = ingredientIn;
        this.P_1922_E = resultIn;
        this.u_1723_Y = experienceIn;
        this.v_4262_N = cookTimeIn;
    }

    @Override
    public boolean n_1700_B(Container inv, b_4507_u worldIn) {
        return this.G_564_y.n_1700_B(inv.s_956_w(0));
    }

    @Override
    public Z_1993_T n_1700_B(Container inv) {
        return this.P_1922_E.t_148_a();
    }

    @Override
    public boolean n_1700_B(int width, int height) {
        return true;
    }

    @Override
    public NonNullList<b_3278_X> n_1700_B() {
        NonNullList<b_3278_X> nonnulllist = NonNullList.n_1700_B();
        nonnulllist.add(this.G_564_y);
        return nonnulllist;
    }

    public float J_1907_R() {
        return this.u_1723_Y;
    }

    @Override
    public Z_1993_T R_4764_Y() {
        return this.P_1922_E;
    }

    @Override
    public String G_564_y() {
        return this.R_4764_Y;
    }

    public int P_1922_E() {
        return this.v_4262_N;
    }

    @Override
    public g_2336_b u_1723_Y() {
        return this.J_1907_R;
    }

    @Override
    public RecipeType<?> v_4262_N() {
        return this.n_1700_B;
    }
}


