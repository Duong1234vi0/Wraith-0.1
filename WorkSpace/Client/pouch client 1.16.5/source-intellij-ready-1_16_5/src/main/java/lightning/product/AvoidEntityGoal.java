/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import java.util.function.Predicate;
import lightning.product.PathNavigation;
import lightning.product.I_408_V;
import lightning.product.N_4263_v;
import lightning.product.W_3371_U;
import lightning.product.b_1722_e;
import lightning.product.e_2866_D;
import lightning.product.TargetingConditions;
import lightning.product.PathfinderMob;
import lightning.product.Goal;
import lightning.product.r_4811_B;

public class AvoidEntityGoal<T extends r_4811_B>
extends Goal {
    protected final PathfinderMob n_1700_B;
    private final double t_148_a;
    private final double s_956_w;
    protected T J_1907_R;
    protected final float R_4764_Y;
    protected b_1722_e G_564_y;
    protected final PathNavigation P_1922_E;
    protected final Class<T> u_1723_Y;
    protected final Predicate<r_4811_B> v_4262_N;
    protected final Predicate<r_4811_B> w_1484_f;
    private final TargetingConditions u_2550_I;

    public AvoidEntityGoal(PathfinderMob entityIn, Class<T> classToAvoidIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
        this(entityIn, classToAvoidIn, p_200828_0_ -> true, avoidDistanceIn, farSpeedIn, nearSpeedIn, I_408_V.P_1922_E::test);
    }

    public AvoidEntityGoal(PathfinderMob entityIn, Class<T> avoidClass, Predicate<r_4811_B> targetPredicate, float distance, double nearSpeedIn, double farSpeedIn, Predicate<r_4811_B> p_i48859_9_) {
        this.n_1700_B = entityIn;
        this.u_1723_Y = avoidClass;
        this.v_4262_N = targetPredicate;
        this.R_4764_Y = distance;
        this.t_148_a = nearSpeedIn;
        this.s_956_w = farSpeedIn;
        this.w_1484_f = p_i48859_9_;
        this.P_1922_E = entityIn.e_4240_b();
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
        this.u_2550_I = new TargetingConditions().n_1700_B(distance).n_1700_B(p_i48859_9_.and(targetPredicate));
    }

    public AvoidEntityGoal(PathfinderMob entityIn, Class<T> avoidClass, float distance, double nearSpeedIn, double farSpeedIn, Predicate<r_4811_B> targetPredicate) {
        this(entityIn, avoidClass, p_203782_0_ -> true, distance, nearSpeedIn, farSpeedIn, targetPredicate);
    }

    @Override
    public boolean n_1700_B() {
        this.J_1907_R = this.n_1700_B.O_508_d.J_1907_R(this.u_1723_Y, this.u_2550_I, this.n_1700_B, this.n_1700_B.O_3598_v(), this.n_1700_B.X_2960_b(), this.n_1700_B.l_2647_k(), this.n_1700_B.i_601_W().grow(this.R_4764_Y, 3.0, this.R_4764_Y));
        if (this.J_1907_R == null) {
            return false;
        }
        e_2866_D vector3d = W_3371_U.R_4764_Y(this.n_1700_B, 16, 7, ((N_4263_v)this.J_1907_R).s_4990_V());
        if (vector3d == null) {
            return false;
        }
        if (((N_4263_v)this.J_1907_R).v_4262_N(vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y) < ((N_4263_v)this.J_1907_R).G_564_y(this.n_1700_B)) {
            return false;
        }
        this.G_564_y = this.P_1922_E.n_1700_B(vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y, 0);
        return this.G_564_y != null;
    }

    @Override
    public boolean J_1907_R() {
        return !this.P_1922_E.M_588_G();
    }

    @Override
    public void R_4764_Y() {
        this.P_1922_E.n_1700_B(this.G_564_y, this.t_148_a);
    }

    @Override
    public void G_564_y() {
        this.J_1907_R = null;
    }

    @Override
    public void P_1922_E() {
        if (this.n_1700_B.G_564_y((N_4263_v)this.J_1907_R) < 49.0) {
            this.n_1700_B.e_4240_b().n_1700_B(this.s_956_w);
        } else {
            this.n_1700_B.e_4240_b().n_1700_B(this.t_148_a);
        }
    }
}


