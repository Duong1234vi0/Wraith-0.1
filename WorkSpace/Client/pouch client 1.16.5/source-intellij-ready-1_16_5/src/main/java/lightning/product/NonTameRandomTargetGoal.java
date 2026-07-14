/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.C_3622_I;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.r_4811_B;

public class NonTameRandomTargetGoal<T extends r_4811_B>
extends NearestAttackableTargetGoal<T> {
    private final C_3622_I t_148_a;

    public NonTameRandomTargetGoal(C_3622_I tameableIn, Class<T> targetClassIn, boolean checkSight, @Nullable Predicate<r_4811_B> targetPredicate) {
        super(tameableIn, targetClassIn, 10, checkSight, false, targetPredicate);
        this.t_148_a = tameableIn;
    }

    @Override
    public boolean n_1700_B() {
        return !this.t_148_a.U_3758_B() && super.n_1700_B();
    }

    @Override
    public boolean J_1907_R() {
        return this.G_564_y != null ? this.G_564_y.n_1700_B(this.P_1922_E, this.R_4764_Y) : super.J_1907_R();
    }
}


