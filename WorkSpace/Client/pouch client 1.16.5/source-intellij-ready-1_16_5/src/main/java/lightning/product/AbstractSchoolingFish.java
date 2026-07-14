/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.FollowFlockLeaderGoal;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.AbstractFish;
import lightning.product.a_3160_D;
import lightning.product.b_4507_u;
import lightning.product.ServerLevelAccessor;
import lightning.product.t_5_h;

public abstract class AbstractSchoolingFish
extends AbstractFish {
    private AbstractSchoolingFish n_1700_B;
    private int J_1907_R = 1;

    public AbstractSchoolingFish(t_5_h<? extends AbstractSchoolingFish> type, b_4507_u worldIn) {
        super((t_5_h<? extends AbstractFish>)type, worldIn);
    }

    @Override
    protected void M_182_A() {
        super.M_182_A();
        this.s_956_w.n_1700_B(5, new FollowFlockLeaderGoal(this));
    }

    @Override
    public int c_4037_x() {
        return this.y_2447_C();
    }

    public int y_2447_C() {
        return super.c_4037_x();
    }

    @Override
    protected boolean h_1640_b() {
        return !this.J_3635_s();
    }

    public boolean J_3635_s() {
        return this.n_1700_B != null && this.n_1700_B.RealmsLongRunningMcoTaskScreen();
    }

    public AbstractSchoolingFish n_1700_B(AbstractSchoolingFish groupLeaderIn) {
        this.n_1700_B = groupLeaderIn;
        groupLeaderIn.V_537_k();
        return groupLeaderIn;
    }

    public void o_82_k() {
        this.n_1700_B.c_2086_l();
        this.n_1700_B = null;
    }

    private void V_537_k() {
        ++this.J_1907_R;
    }

    private void c_2086_l() {
        --this.J_1907_R;
    }

    public boolean h_973_D() {
        return this.f_2787_O() && this.J_1907_R < this.y_2447_C();
    }

    @Override
    public void v_() {
        List<?> list;
        super.v_();
        if (this.f_2787_O() && this.O_508_d.w_1457_N.nextInt(200) == 1 && (list = this.O_508_d.n_1700_B(this.getClass(), this.i_601_W().grow(8.0, 8.0, 8.0))).size() <= 1) {
            this.J_1907_R = 1;
        }
    }

    public boolean f_2787_O() {
        return this.J_1907_R > 1;
    }

    public boolean P_2295_B() {
        return this.G_564_y((N_4263_v)this.n_1700_B) <= 121.0;
    }

    public void U_1697_c() {
        if (this.J_3635_s()) {
            this.e_4240_b().n_1700_B((N_4263_v)this.n_1700_B, 1.0);
        }
    }

    public void n_1700_B(Stream<AbstractSchoolingFish> p_212810_1_) {
        p_212810_1_.limit(this.y_2447_C() - this.J_1907_R).filter(p_212801_1_ -> p_212801_1_ != this).forEach(p_212804_1_ -> p_212804_1_.n_1700_B(this));
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        if (spawnDataIn == null) {
            spawnDataIn = new n_1700_B(this);
        } else {
            this.n_1700_B(((n_1700_B)spawnDataIn).n_1700_B);
        }
        return spawnDataIn;
    }

    public static class n_1700_B
    implements V_3157_k {
        public final AbstractSchoolingFish n_1700_B;

        public n_1700_B(AbstractSchoolingFish groupLeaderIn) {
            this.n_1700_B = groupLeaderIn;
        }
    }
}


