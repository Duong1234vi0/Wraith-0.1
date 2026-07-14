/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.C_4114_x;
import lightning.product.DifficultyInstance;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.a_3160_D;
import lightning.product.b_4507_u;
import lightning.product.ServerLevelAccessor;
import lightning.product.e_3591_l;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.PathfinderMob;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;

public abstract class AgableMob
extends PathfinderMob {
    private static final h_256_u<Boolean> h_1847_R = C_4114_x.n_1700_B(AgableMob.class, EntityDataSerializers.t_148_a);
    protected int n_1700_B;
    protected int J_1907_R;
    protected int R_4764_Y;

    protected AgableMob(t_5_h<? extends AgableMob> type, b_4507_u worldIn) {
        super((t_5_h<? extends PathfinderMob>)type, worldIn);
    }

    @Override
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        n_1700_B ageableentity$ageabledata;
        if (spawnDataIn == null) {
            spawnDataIn = new n_1700_B(true);
        }
        if ((ageableentity$ageabledata = (n_1700_B)spawnDataIn).R_4764_Y() && ageableentity$ageabledata.n_1700_B() > 0 && this.RealmsWorldOptions.nextFloat() <= ageableentity$ageabledata.G_564_y()) {
            this.b_(-24000);
        }
        ageableentity$ageabledata.J_1907_R();
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Nullable
    public abstract AgableMob n_1700_B(e_3591_l var1, AgableMob var2);

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(h_1847_R, false);
    }

    public boolean w_() {
        return false;
    }

    public int x_() {
        if (this.O_508_d.Y_259_p) {
            return this.l_4537_E.n_1700_B(h_1847_R) != false ? -1 : 1;
        }
        return this.n_1700_B;
    }

    public void n_1700_B(int growthSeconds, boolean updateForcedAge) {
        int i = this.x_();
        if ((i += growthSeconds * 20) > 0) {
            i = 0;
        }
        int j = i - i;
        this.b_(i);
        if (updateForcedAge) {
            this.J_1907_R += j;
            if (this.R_4764_Y == 0) {
                this.R_4764_Y = 40;
            }
        }
        if (this.x_() == 0) {
            this.b_(this.J_1907_R);
        }
    }

    public void a_(int growth) {
        this.n_1700_B(growth, false);
    }

    public void b_(int age) {
        int i = this.n_1700_B;
        this.n_1700_B = age;
        if (i < 0 && age >= 0 || i >= 0 && age < 0) {
            this.l_4537_E.J_1907_R(h_1847_R, age < 0);
            this.y_();
        }
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("Age", this.x_());
        compound.J_1907_R("ForcedAge", this.J_1907_R);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.b_(compound.w_1484_f("Age"));
        this.J_1907_R = compound.w_1484_f("ForcedAge");
    }

    @Override
    public void n_1700_B(h_256_u<?> key) {
        if (h_1847_R.equals(key)) {
            this.g_();
        }
        super.n_1700_B(key);
    }

    @Override
    public void Y_1740_V() {
        super.Y_1740_V();
        if (this.O_508_d.Y_259_p) {
            if (this.R_4764_Y > 0) {
                if (this.R_4764_Y % 4 == 0) {
                    this.O_508_d.n_1700_B(ParticleTypes.t_4043_B, this.G_564_y(1.0), this.M_766_z() + 0.5, this.v_4262_N(1.0), 0.0, 0.0, 0.0);
                }
                --this.R_4764_Y;
            }
        } else if (this.RealmsLongRunningMcoTaskScreen()) {
            int i = this.x_();
            if (i < 0) {
                this.b_(++i);
            } else if (i > 0) {
                this.b_(--i);
            }
        }
    }

    protected void y_() {
    }

    @Override
    public boolean d_() {
        return this.x_() < 0;
    }

    @Override
    public void n_1700_B(boolean childZombie) {
        this.b_(childZombie ? -24000 : 0);
    }

    public static class n_1700_B
    implements V_3157_k {
        private int n_1700_B;
        private final boolean J_1907_R;
        private final float R_4764_Y;

        private n_1700_B(boolean canBabySpawn, float babySpawnProbability) {
            this.J_1907_R = canBabySpawn;
            this.R_4764_Y = babySpawnProbability;
        }

        public n_1700_B(boolean canBabySpawn) {
            this(canBabySpawn, 0.05f);
        }

        public n_1700_B(float babySpawnProbability) {
            this(true, babySpawnProbability);
        }

        public int n_1700_B() {
            return this.n_1700_B;
        }

        public void J_1907_R() {
            ++this.n_1700_B;
        }

        public boolean R_4764_Y() {
            return this.J_1907_R;
        }

        public float G_564_y() {
            return this.R_4764_Y;
        }
    }
}


