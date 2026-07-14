/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicates
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.base.Predicates;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.Container;
import lightning.product.N_4263_v;
import lightning.product.R_2450_T;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.o_3050_h;
import lightning.product.r_4811_B;

public final class I_408_V {
    public static final Predicate<N_4263_v> n_1700_B = N_4263_v::RealmsLongRunningMcoTaskScreen;
    public static final Predicate<r_4811_B> J_1907_R = r_4811_B::RealmsLongRunningMcoTaskScreen;
    public static final Predicate<N_4263_v> R_4764_Y = entity -> entity.RealmsLongRunningMcoTaskScreen() && !entity.H_1883_T() && !entity.y_2772_m();
    public static final Predicate<N_4263_v> G_564_y = entity -> entity instanceof Container && entity.RealmsLongRunningMcoTaskScreen();
    public static final Predicate<N_4263_v> P_1922_E = entity -> !(entity instanceof a_3913_L) || !entity.d_2461_k() && !((a_3913_L)entity).G_624_v();
    public static final Predicate<N_4263_v> u_1723_Y = entity -> !(entity instanceof a_3913_L) || !entity.d_2461_k() && !((a_3913_L)entity).G_624_v() && entity.O_508_d.x_607_J() != R_2450_T.n_1700_B;
    public static final Predicate<N_4263_v> v_4262_N = entity -> !entity.d_2461_k();

    public static Predicate<N_4263_v> n_1700_B(double x, double y, double z, double range) {
        double d0 = range * range;
        return entity -> entity != null && entity.v_4262_N(x, y, z) <= d0;
    }

    public static Predicate<N_4263_v> n_1700_B(N_4263_v entityIn) {
        o_3050_h team = entityIn.L_1362_X();
        o_3050_h.n_1700_B team$collisionrule = team == null ? o_3050_h.n_1700_B.n_1700_B : team.u_2550_I();
        return team$collisionrule == o_3050_h.n_1700_B.J_1907_R ? Predicates.alwaysFalse() : v_4262_N.and(entity -> {
            if (!entity.w_728_N()) {
                return false;
            }
            if (!entityIn.O_508_d.Y_259_p || entity instanceof a_3913_L && ((a_3913_L)entity).w_1484_f()) {
                boolean flag;
                o_3050_h.n_1700_B team$collisionrule1;
                o_3050_h team1 = entity.L_1362_X();
                o_3050_h.n_1700_B n_1700_B2 = team$collisionrule1 = team1 == null ? o_3050_h.n_1700_B.n_1700_B : team1.u_2550_I();
                if (team$collisionrule1 == o_3050_h.n_1700_B.J_1907_R) {
                    return false;
                }
                boolean bl = flag = team != null && team.n_1700_B(team1);
                if ((team$collisionrule == o_3050_h.n_1700_B.G_564_y || team$collisionrule1 == o_3050_h.n_1700_B.G_564_y) && flag) {
                    return false;
                }
                return team$collisionrule != o_3050_h.n_1700_B.R_4764_Y && team$collisionrule1 != o_3050_h.n_1700_B.R_4764_Y || flag;
            }
            return false;
        });
    }

    public static Predicate<N_4263_v> J_1907_R(N_4263_v entityIn) {
        return entity -> {
            while (entity.y_2772_m()) {
                if ((entity = entity.l_3609_d()) != entityIn) continue;
                return false;
            }
            return true;
        };
    }

    public static class n_1700_B
    implements Predicate<N_4263_v> {
        private final Z_1993_T n_1700_B;

        public n_1700_B(Z_1993_T armor) {
            this.n_1700_B = armor;
        }

        public boolean n_1700_B(@Nullable N_4263_v p_test_1_) {
            if (!p_test_1_.RealmsLongRunningMcoTaskScreen()) {
                return false;
            }
            if (!(p_test_1_ instanceof r_4811_B)) {
                return false;
            }
            r_4811_B livingentity = (r_4811_B)p_test_1_;
            return livingentity.P_1922_E(this.n_1700_B);
        }

        @Override
        public /* synthetic */ boolean test(@Nullable Object object) {
            return this.n_1700_B((N_4263_v)object);
        }
    }
}


