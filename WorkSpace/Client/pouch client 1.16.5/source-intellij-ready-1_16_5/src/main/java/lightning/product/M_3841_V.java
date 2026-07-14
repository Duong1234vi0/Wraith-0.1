/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import lightning.product.D_1436_R;
import lightning.product.S_1431_H;
import lightning.product.W_3371_U;
import lightning.product.GoalUtils;
import lightning.product.b_1722_e;
import lightning.product.b_4946_z;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.i_2099_H;
import lightning.product.PathfinderMob;
import lightning.product.Goal;
import lightning.product.q_2232_A;

public class M_3841_V
extends Goal {
    protected final PathfinderMob n_1700_B;
    private final double J_1907_R;
    private b_1722_e R_4764_Y;
    private c_1514_x G_564_y;
    private final boolean P_1922_E;
    private final List<c_1514_x> u_1723_Y = Lists.newArrayList();
    private final int v_4262_N;
    private final BooleanSupplier w_1484_f;

    public M_3841_V(PathfinderMob entity, double speedIn, boolean nocturnal, int maxDistanceIn, BooleanSupplier booleanSupplierIn) {
        this.n_1700_B = entity;
        this.J_1907_R = speedIn;
        this.P_1922_E = nocturnal;
        this.v_4262_N = maxDistanceIn;
        this.w_1484_f = booleanSupplierIn;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
        if (!GoalUtils.n_1700_B(entity)) {
            throw new IllegalArgumentException("Unsupported mob for MoveThroughVillageGoal");
        }
    }

    @Override
    public boolean n_1700_B() {
        if (!GoalUtils.n_1700_B(this.n_1700_B)) {
            return false;
        }
        this.v_4262_N();
        if (this.P_1922_E && this.n_1700_B.O_508_d.q_4610_l()) {
            return false;
        }
        e_3591_l serverworld = (e_3591_l)this.n_1700_B.O_508_d;
        c_1514_x blockpos = this.n_1700_B.b_2312_j();
        if (!serverworld.R_4764_Y(blockpos, 6)) {
            return false;
        }
        e_2866_D vector3d = W_3371_U.n_1700_B(this.n_1700_B, 15, 7, p_220734_3_ -> {
            if (!serverworld.q_2307_F((c_1514_x)p_220734_3_)) {
                return Double.NEGATIVE_INFINITY;
            }
            Optional<c_1514_x> optional1 = serverworld.p_178_J().R_4764_Y(q_2232_A.J_1907_R, this::n_1700_B, (c_1514_x)p_220734_3_, 10, b_4946_z.J_1907_R.J_1907_R);
            return !optional1.isPresent() ? Double.NEGATIVE_INFINITY : -optional1.get().distanceSq(blockpos);
        });
        if (vector3d == null) {
            return false;
        }
        Optional<c_1514_x> optional = serverworld.p_178_J().R_4764_Y(q_2232_A.J_1907_R, this::n_1700_B, new c_1514_x(vector3d), 10, b_4946_z.J_1907_R.J_1907_R);
        if (!optional.isPresent()) {
            return false;
        }
        this.G_564_y = optional.get().toImmutable();
        i_2099_H groundpathnavigator = (i_2099_H)this.n_1700_B.e_4240_b();
        boolean flag = groundpathnavigator.P_1922_E();
        groundpathnavigator.n_1700_B(this.w_1484_f.getAsBoolean());
        this.R_4764_Y = groundpathnavigator.n_1700_B(this.G_564_y, 0);
        groundpathnavigator.n_1700_B(flag);
        if (this.R_4764_Y == null) {
            e_2866_D vector3d1 = W_3371_U.J_1907_R(this.n_1700_B, 10, 7, e_2866_D.R_4764_Y(this.G_564_y));
            if (vector3d1 == null) {
                return false;
            }
            groundpathnavigator.n_1700_B(this.w_1484_f.getAsBoolean());
            this.R_4764_Y = this.n_1700_B.e_4240_b().n_1700_B(vector3d1.J_1907_R, vector3d1.R_4764_Y, vector3d1.G_564_y, 0);
            groundpathnavigator.n_1700_B(flag);
            if (this.R_4764_Y == null) {
                return false;
            }
        }
        for (int i = 0; i < this.R_4764_Y.P_1922_E(); ++i) {
            D_1436_R pathpoint = this.R_4764_Y.n_1700_B(i);
            c_1514_x blockpos1 = new c_1514_x(pathpoint.n_1700_B, pathpoint.J_1907_R + 1, pathpoint.R_4764_Y);
            if (!S_1431_H.n_1700_B(this.n_1700_B.O_508_d, blockpos1)) continue;
            this.R_4764_Y = this.n_1700_B.e_4240_b().n_1700_B((double)pathpoint.n_1700_B, (double)pathpoint.J_1907_R, (double)pathpoint.R_4764_Y, 0);
            break;
        }
        return this.R_4764_Y != null;
    }

    @Override
    public boolean J_1907_R() {
        if (this.n_1700_B.e_4240_b().M_588_G()) {
            return false;
        }
        return !this.G_564_y.withinDistance(this.n_1700_B.s_4990_V(), (double)(this.n_1700_B.C_415_h() + (float)this.v_4262_N));
    }

    @Override
    public void R_4764_Y() {
        this.n_1700_B.e_4240_b().n_1700_B(this.R_4764_Y, this.J_1907_R);
    }

    @Override
    public void G_564_y() {
        if (this.n_1700_B.e_4240_b().M_588_G() || this.G_564_y.withinDistance(this.n_1700_B.s_4990_V(), (double)this.v_4262_N)) {
            this.u_1723_Y.add(this.G_564_y);
        }
    }

    private boolean n_1700_B(c_1514_x p_220733_1_) {
        for (c_1514_x blockpos : this.u_1723_Y) {
            if (!Objects.equals(p_220733_1_, blockpos)) continue;
            return false;
        }
        return true;
    }

    private void v_4262_N() {
        if (this.u_1723_Y.size() > 15) {
            this.u_1723_Y.remove(0);
        }
    }
}


