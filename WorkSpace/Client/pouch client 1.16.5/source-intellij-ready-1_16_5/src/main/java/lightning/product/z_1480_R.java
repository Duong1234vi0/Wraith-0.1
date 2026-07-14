/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.J_2548_M;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;

public class z_1480_R<E extends r_4811_B>
extends Behavior<E> {
    private boolean n_1700_B;
    private boolean R_4764_Y;
    private final J_2548_M G_564_y;
    private final Behavior<? super E> P_1922_E;
    private int u_1723_Y;

    public z_1480_R(Behavior<? super E> p_i231530_1_, J_2548_M p_i231530_2_) {
        this(p_i231530_1_, false, p_i231530_2_);
    }

    public z_1480_R(Behavior<? super E> p_i231531_1_, boolean p_i231531_2_, J_2548_M p_i231531_3_) {
        super(p_i231531_1_.J_1907_R);
        this.P_1922_E = p_i231531_1_;
        this.n_1700_B = !p_i231531_2_;
        this.G_564_y = p_i231531_3_;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, E owner) {
        if (!this.P_1922_E.n_1700_B(worldIn, owner)) {
            return false;
        }
        if (this.n_1700_B) {
            this.n_1700_B(worldIn);
            this.n_1700_B = false;
        }
        if (this.u_1723_Y > 0) {
            --this.u_1723_Y;
        }
        return !this.R_4764_Y && this.u_1723_Y == 0;
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        this.P_1922_E.G_564_y(worldIn, entityIn, gameTimeIn);
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        return this.P_1922_E.n_1700_B(worldIn, entityIn, gameTimeIn);
    }

    @Override
    protected void R_4764_Y(e_3591_l worldIn, E owner, long gameTime) {
        this.P_1922_E.R_4764_Y(worldIn, owner, gameTime);
        this.R_4764_Y = this.P_1922_E.n_1700_B() == Behavior.n_1700_B.J_1907_R;
    }

    @Override
    protected void J_1907_R(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        this.n_1700_B(worldIn);
        this.P_1922_E.J_1907_R(worldIn, entityIn, gameTimeIn);
    }

    private void n_1700_B(e_3591_l p_233949_1_) {
        this.u_1723_Y = this.G_564_y.n_1700_B(p_233949_1_.w_1457_N);
    }

    @Override
    protected boolean n_1700_B(long gameTime) {
        return false;
    }

    @Override
    public String toString() {
        return "RunSometimes: " + String.valueOf(this.P_1922_E);
    }
}


