/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.EnumSet;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.I_4817_s;
import lightning.product.J_133_e;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.TargetingConditions;
import lightning.product.Goal;
import lightning.product.r_4811_B;

public class NearestAttackableTargetGoal<T extends r_4811_B>
extends J_133_e {
    protected final Class<T> n_1700_B;
    protected final int J_1907_R;
    protected r_4811_B R_4764_Y;
    protected TargetingConditions G_564_y;

    public NearestAttackableTargetGoal(Z_530_i goalOwnerIn, Class<T> targetClassIn, boolean checkSight) {
        this(goalOwnerIn, targetClassIn, checkSight, false);
    }

    public NearestAttackableTargetGoal(Z_530_i goalOwnerIn, Class<T> targetClassIn, boolean checkSight, boolean nearbyOnlyIn) {
        this(goalOwnerIn, targetClassIn, 10, checkSight, nearbyOnlyIn, null);
    }

    public NearestAttackableTargetGoal(Z_530_i goalOwnerIn, Class<T> targetClassIn, int targetChanceIn, boolean checkSight, boolean nearbyOnlyIn, @Nullable Predicate<r_4811_B> targetPredicate) {
        super(goalOwnerIn, checkSight, nearbyOnlyIn);
        this.n_1700_B = targetClassIn;
        this.J_1907_R = targetChanceIn;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.G_564_y));
        this.G_564_y = new TargetingConditions().n_1700_B(this.u_2550_I()).n_1700_B(targetPredicate);
    }

    @Override
    public boolean n_1700_B() {
        if (this.J_1907_R > 0 && this.P_1922_E.M_3508_C().nextInt(this.J_1907_R) != 0) {
            return false;
        }
        this.s_956_w();
        return this.R_4764_Y != null;
    }

    protected I_4817_s n_1700_B(double targetDistance) {
        return this.P_1922_E.i_601_W().grow(targetDistance, 4.0, targetDistance);
    }

    protected void s_956_w() {
        this.R_4764_Y = this.n_1700_B != a_3913_L.class && this.n_1700_B != B_4088_l.class ? this.P_1922_E.O_508_d.J_1907_R(this.n_1700_B, this.G_564_y, this.P_1922_E, this.P_1922_E.O_3598_v(), this.P_1922_E.X_2048_Y(), this.P_1922_E.l_2647_k(), this.n_1700_B(this.u_2550_I())) : this.P_1922_E.O_508_d.n_1700_B(this.G_564_y, this.P_1922_E, this.P_1922_E.O_3598_v(), this.P_1922_E.X_2048_Y(), this.P_1922_E.l_2647_k());
    }

    @Override
    public void R_4764_Y() {
        this.P_1922_E.R_4764_Y(this.R_4764_Y);
        super.R_4764_Y();
    }

    public void n_1700_B(@Nullable r_4811_B target) {
        this.R_4764_Y = target;
    }
}


