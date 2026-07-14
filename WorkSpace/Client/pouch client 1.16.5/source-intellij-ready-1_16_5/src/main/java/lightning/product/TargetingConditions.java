/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.Z_530_i;
import lightning.product.r_4811_B;

public class TargetingConditions {
    public static final TargetingConditions n_1700_B = new TargetingConditions();
    private double J_1907_R = -1.0;
    private boolean R_4764_Y;
    private boolean G_564_y;
    private boolean P_1922_E;
    private boolean u_1723_Y;
    private boolean v_4262_N = true;
    private Predicate<r_4811_B> w_1484_f;

    public TargetingConditions n_1700_B(double distanceIn) {
        this.J_1907_R = distanceIn;
        return this;
    }

    public TargetingConditions n_1700_B() {
        this.R_4764_Y = true;
        return this;
    }

    public TargetingConditions J_1907_R() {
        this.G_564_y = true;
        return this;
    }

    public TargetingConditions R_4764_Y() {
        this.P_1922_E = true;
        return this;
    }

    public TargetingConditions G_564_y() {
        this.u_1723_Y = true;
        return this;
    }

    public TargetingConditions P_1922_E() {
        this.v_4262_N = false;
        return this;
    }

    public TargetingConditions n_1700_B(@Nullable Predicate<r_4811_B> customPredicate) {
        this.w_1484_f = customPredicate;
        return this;
    }

    public boolean n_1700_B(@Nullable r_4811_B attacker, r_4811_B target) {
        if (attacker == target) {
            return false;
        }
        if (target.d_2461_k()) {
            return false;
        }
        if (!target.RealmsLongRunningMcoTaskScreen()) {
            return false;
        }
        if (!this.R_4764_Y && target.P_925_e()) {
            return false;
        }
        if (this.w_1484_f != null && !this.w_1484_f.test(target)) {
            return false;
        }
        if (attacker != null) {
            if (!this.u_1723_Y) {
                if (!attacker.n_1700_B(target)) {
                    return false;
                }
                if (!attacker.n_1700_B(target.f_4016_n())) {
                    return false;
                }
            }
            if (!this.G_564_y && attacker.Q_4569_t(target)) {
                return false;
            }
            if (this.J_1907_R > 0.0) {
                double d0 = this.v_4262_N ? target.k_2293_S(attacker) : 1.0;
                double d1 = Math.max(this.J_1907_R * d0, 2.0);
                double d2 = attacker.v_4262_N(target.O_3598_v(), target.X_2960_b(), target.l_2647_k());
                if (d2 > d1 * d1) {
                    return false;
                }
            }
            if (!this.P_1922_E && attacker instanceof Z_530_i && !((Z_530_i)attacker).n_3318_d().n_1700_B(target)) {
                return false;
            }
        }
        return true;
    }
}


