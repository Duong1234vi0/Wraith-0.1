/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.EnumSet;
import javax.annotation.Nullable;
import lightning.product.Goal;

public class b_3075_S
extends Goal {
    private final Goal n_1700_B;
    private final int J_1907_R;
    private boolean R_4764_Y;

    public b_3075_S(int priorityIn, Goal goalIn) {
        this.J_1907_R = priorityIn;
        this.n_1700_B = goalIn;
    }

    public boolean n_1700_B(b_3075_S other) {
        return this.r_() && other.w_1484_f() < this.w_1484_f();
    }

    @Override
    public boolean n_1700_B() {
        return this.n_1700_B.n_1700_B();
    }

    @Override
    public boolean J_1907_R() {
        return this.n_1700_B.J_1907_R();
    }

    @Override
    public boolean r_() {
        return this.n_1700_B.r_();
    }

    @Override
    public void R_4764_Y() {
        if (!this.R_4764_Y) {
            this.R_4764_Y = true;
            this.n_1700_B.R_4764_Y();
        }
    }

    @Override
    public void G_564_y() {
        if (this.R_4764_Y) {
            this.R_4764_Y = false;
            this.n_1700_B.G_564_y();
        }
    }

    @Override
    public void P_1922_E() {
        this.n_1700_B.P_1922_E();
    }

    @Override
    public void n_1700_B(EnumSet<Goal.n_1700_B> flagSet) {
        this.n_1700_B.n_1700_B(flagSet);
    }

    @Override
    public EnumSet<Goal.n_1700_B> t_148_a() {
        return this.n_1700_B.t_148_a();
    }

    public boolean v_4262_N() {
        return this.R_4764_Y;
    }

    public int w_1484_f() {
        return this.J_1907_R;
    }

    public Goal s_956_w() {
        return this.n_1700_B;
    }

    public boolean equals(@Nullable Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        return p_equals_1_ != null && this.getClass() == p_equals_1_.getClass() ? this.n_1700_B.equals(((b_3075_S)p_equals_1_).n_1700_B) : false;
    }

    public int hashCode() {
        return this.n_1700_B.hashCode();
    }
}


