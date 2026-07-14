/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.PathNavigation;
import lightning.product.K_4719_o;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.b_3129_s;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.Monster;
import lightning.product.n_3832_I;
import lightning.product.Goal;
import lightning.product.r_4811_B;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;
import lightning.product.z_2963_s;

public abstract class PatrollingMonster
extends Monster {
    private c_1514_x n_1700_B;
    private boolean J_1907_R;
    private boolean R_4764_Y;

    protected PatrollingMonster(t_5_h<? extends PatrollingMonster> p_i50201_1_, b_4507_u worldIn) {
        super((t_5_h<? extends Monster>)p_i50201_1_, worldIn);
    }

    @Override
    protected void M_182_A() {
        super.M_182_A();
        this.s_956_w.n_1700_B(4, new n_1700_B<PatrollingMonster>(this, 0.7, 0.595));
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        if (this.n_1700_B != null) {
            compound.n_1700_B("PatrolTarget", n_3832_I.n_1700_B(this.n_1700_B));
        }
        compound.n_1700_B("PatrolLeader", this.J_1907_R);
        compound.n_1700_B("Patrolling", this.R_4764_Y);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        if (compound.P_1922_E("PatrolTarget")) {
            this.n_1700_B = n_3832_I.J_1907_R(compound.M_182_A("PatrolTarget"));
        }
        this.J_1907_R = compound.t_1786_h("PatrolLeader");
        this.R_4764_Y = compound.t_1786_h("Patrolling");
    }

    @Override
    public double O_2151_c() {
        return -0.45;
    }

    public boolean c_2086_l() {
        return true;
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        if (reason != a_3160_D.M_182_A && reason != a_3160_D.w_1484_f && reason != a_3160_D.G_564_y && this.RealmsWorldOptions.nextFloat() < 0.06f && this.c_2086_l()) {
            this.J_1907_R = true;
        }
        if (this.A_1306_N()) {
            this.n_1700_B(e_1174_E.u_1723_Y, b_3129_s.t_1786_h());
            this.n_1700_B(e_1174_E.u_1723_Y, 2.0f);
        }
        if (reason == a_3160_D.M_182_A) {
            this.R_4764_Y = true;
        }
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public static boolean J_1907_R(t_5_h<? extends PatrollingMonster> patrollerType, LevelAccessor worldIn, a_3160_D reason, c_1514_x p_223330_3_, Random p_223330_4_) {
        return worldIn.getLightFor(K_4719_o.J_1907_R, p_223330_3_) > 8 ? false : PatrollingMonster.R_4764_Y(patrollerType, worldIn, reason, p_223330_3_, p_223330_4_);
    }

    @Override
    public boolean w_1484_f(double distanceToClosestPlayer) {
        return !this.R_4764_Y || distanceToClosestPlayer > 16384.0;
    }

    public void v_4262_N(c_1514_x p_213631_1_) {
        this.n_1700_B = p_213631_1_;
        this.R_4764_Y = true;
    }

    public c_1514_x U_3758_B() {
        return this.n_1700_B;
    }

    public boolean y_3417_N() {
        return this.n_1700_B != null;
    }

    public void Y_259_p(boolean isLeader) {
        this.J_1907_R = isLeader;
        this.R_4764_Y = true;
    }

    public boolean A_1306_N() {
        return this.J_1907_R;
    }

    public boolean V_1176_p() {
        return true;
    }

    public void D_3612_q() {
        this.n_1700_B = this.b_2312_j().add(-500 + this.RealmsWorldOptions.nextInt(1000), 0, -500 + this.RealmsWorldOptions.nextInt(1000));
        this.R_4764_Y = true;
    }

    protected boolean R_2822_N() {
        return this.R_4764_Y;
    }

    protected void Q_2552_b(boolean p_226541_1_) {
        this.R_4764_Y = p_226541_1_;
    }

    public static class n_1700_B<T extends PatrollingMonster>
    extends Goal {
        private final T n_1700_B;
        private final double J_1907_R;
        private final double R_4764_Y;
        private long G_564_y;

        public n_1700_B(T p_i50070_1_, double p_i50070_2_, double p_i50070_4_) {
            this.n_1700_B = p_i50070_1_;
            this.J_1907_R = p_i50070_2_;
            this.R_4764_Y = p_i50070_4_;
            this.G_564_y = -1L;
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
        }

        @Override
        public boolean n_1700_B() {
            boolean flag = ((PatrollingMonster)this.n_1700_B).O_508_d.X_933_l() < this.G_564_y;
            return ((PatrollingMonster)this.n_1700_B).R_2822_N() && ((Z_530_i)this.n_1700_B).t_148_a() == null && !((N_4263_v)this.n_1700_B).H_1883_T() && ((PatrollingMonster)this.n_1700_B).y_3417_N() && !flag;
        }

        @Override
        public void R_4764_Y() {
        }

        @Override
        public void G_564_y() {
        }

        @Override
        public void P_1922_E() {
            boolean flag = ((PatrollingMonster)this.n_1700_B).A_1306_N();
            PathNavigation pathnavigator = ((Z_530_i)this.n_1700_B).e_4240_b();
            if (pathnavigator.M_588_G()) {
                List<PatrollingMonster> list = this.v_4262_N();
                if (((PatrollingMonster)this.n_1700_B).R_2822_N() && list.isEmpty()) {
                    ((PatrollingMonster)this.n_1700_B).Q_2552_b(false);
                } else if (flag && ((PatrollingMonster)this.n_1700_B).U_3758_B().withinDistance(((N_4263_v)this.n_1700_B).s_4990_V(), 10.0)) {
                    ((PatrollingMonster)this.n_1700_B).D_3612_q();
                } else {
                    e_2866_D vector3d = e_2866_D.R_4764_Y(((PatrollingMonster)this.n_1700_B).U_3758_B());
                    e_2866_D vector3d1 = ((N_4263_v)this.n_1700_B).s_4990_V();
                    e_2866_D vector3d2 = vector3d1.G_564_y(vector3d);
                    vector3d = vector3d2.J_1907_R(90.0f).n_1700_B(0.4).P_1922_E(vector3d);
                    e_2866_D vector3d3 = vector3d.G_564_y(vector3d1).G_564_y().n_1700_B(10.0).P_1922_E(vector3d1);
                    c_1514_x blockpos = new c_1514_x(vector3d3);
                    if (!pathnavigator.n_1700_B((double)(blockpos = ((PatrollingMonster)this.n_1700_B).O_508_d.n_1700_B(z_2963_s.n_1700_B.u_1723_Y, blockpos)).getX(), (double)blockpos.getY(), (double)blockpos.getZ(), flag ? this.R_4764_Y : this.J_1907_R)) {
                        this.w_1484_f();
                        this.G_564_y = ((PatrollingMonster)this.n_1700_B).O_508_d.X_933_l() + 200L;
                    } else if (flag) {
                        for (PatrollingMonster patrollerentity : list) {
                            patrollerentity.v_4262_N(blockpos);
                        }
                    }
                }
            }
        }

        private List<PatrollingMonster> v_4262_N() {
            return ((PatrollingMonster)this.n_1700_B).O_508_d.n_1700_B(PatrollingMonster.class, ((N_4263_v)this.n_1700_B).i_601_W().grow(16.0), p_226543_1_ -> p_226543_1_.V_1176_p() && !p_226543_1_.M_182_A((N_4263_v)this.n_1700_B));
        }

        private boolean w_1484_f() {
            Random random = ((r_4811_B)this.n_1700_B).M_3508_C();
            c_1514_x blockpos = ((PatrollingMonster)this.n_1700_B).O_508_d.n_1700_B(z_2963_s.n_1700_B.u_1723_Y, ((N_4263_v)this.n_1700_B).b_2312_j().add(-8 + random.nextInt(16), 0, -8 + random.nextInt(16)));
            return ((Z_530_i)this.n_1700_B).e_4240_b().n_1700_B((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), this.J_1907_R);
        }
    }
}


