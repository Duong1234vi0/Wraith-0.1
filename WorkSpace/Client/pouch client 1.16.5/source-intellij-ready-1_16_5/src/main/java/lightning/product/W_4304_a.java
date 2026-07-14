/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.C_4114_x;
import lightning.product.PatrollingMonster;
import lightning.product.MobEffects;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.U_2866_z;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.PathfindToRaidGoal;
import lightning.product.AbstractIllager;
import lightning.product.W_3371_U;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.b_3129_s;
import lightning.product.b_4507_u;
import lightning.product.b_4946_z;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.TargetingConditions;
import lightning.product.e_3591_l;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.k_2610_C;
import lightning.product.n_1494_c;
import lightning.product.Goal;
import lightning.product.q_2232_A;
import lightning.product.q_2335_j;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.u_530_F;

public abstract class W_4304_a
extends PatrollingMonster {
    protected static final h_256_u<Boolean> n_1700_B = C_4114_x.n_1700_B(W_4304_a.class, EntityDataSerializers.t_148_a);
    private static final Predicate<n_1494_c> R_4764_Y = banner -> !banner.Q_4569_t() && banner.RealmsLongRunningMcoTaskScreen() && Z_1993_T.J_1907_R(banner.P_1922_E(), b_3129_s.t_1786_h());
    @Nullable
    protected b_3129_s J_1907_R;
    private int h_1847_R;
    private boolean Q_4569_t;
    private int M_182_A;

    protected W_4304_a(t_5_h<? extends W_4304_a> type, b_4507_u worldIn) {
        super((t_5_h<? extends PatrollingMonster>)type, worldIn);
    }

    @Override
    protected void M_182_A() {
        super.M_182_A();
        this.s_956_w.n_1700_B(1, new G_564_y(this, this));
        this.s_956_w.n_1700_B(3, new PathfindToRaidGoal<W_4304_a>(this));
        this.s_956_w.n_1700_B(4, new R_4764_Y(this, 1.05f, 1));
        this.s_956_w.n_1700_B(5, new n_1700_B(this));
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(n_1700_B, false);
    }

    public abstract void n_1700_B(int var1, boolean var2);

    public boolean y_4642_Y() {
        return this.Q_4569_t;
    }

    public void w_1457_N(boolean canJoin) {
        this.Q_4569_t = canJoin;
    }

    @Override
    public void Y_1740_V() {
        if (this.O_508_d instanceof e_3591_l && this.RealmsLongRunningMcoTaskScreen()) {
            b_3129_s raid = this.y_2447_C();
            if (this.y_4642_Y()) {
                if (raid == null) {
                    b_3129_s raid1;
                    if (this.O_508_d.X_933_l() % 20L == 0L && (raid1 = ((e_3591_l)this.O_508_d).Z_875_P(this.b_2312_j())) != null && U_2866_z.n_1700_B(this, raid1)) {
                        raid1.n_1700_B(raid1.t_148_a(), this, null, true);
                    }
                } else {
                    r_4811_B livingentity = this.t_148_a();
                    if (livingentity != null && (livingentity.f_4016_n() == t_5_h.g_4106_L || livingentity.f_4016_n() == t_5_h.v_4276_D)) {
                        this.UploadTokenCache = 0;
                    }
                }
            }
        }
        super.Y_1740_V();
    }

    @Override
    protected void s_() {
        this.UploadTokenCache += 2;
    }

    @Override
    public void R_4764_Y(P_11_z cause) {
        if (this.O_508_d instanceof e_3591_l) {
            N_4263_v entity = cause.u_2550_I();
            b_3129_s raid = this.y_2447_C();
            if (raid != null) {
                if (this.A_1306_N()) {
                    raid.J_1907_R(this.o_82_k());
                }
                if (entity != null && entity.f_4016_n() == t_5_h.g_4106_L) {
                    raid.n_1700_B(entity);
                }
                raid.n_1700_B(this, false);
            }
            if (this.A_1306_N() && raid == null && ((e_3591_l)this.O_508_d).Z_875_P(this.b_2312_j()) == null) {
                Z_1993_T itemstack = this.J_1907_R(e_1174_E.u_1723_Y);
                a_3913_L playerentity = null;
                if (entity instanceof a_3913_L) {
                    playerentity = (a_3913_L)entity;
                } else if (entity instanceof q_2335_j) {
                    q_2335_j wolfentity = (q_2335_j)entity;
                    r_4811_B livingentity = wolfentity.A_1306_N();
                    if (wolfentity.U_3758_B() && livingentity instanceof a_3913_L) {
                        playerentity = (a_3913_L)livingentity;
                    }
                }
                if (!itemstack.n_1700_B() && Z_1993_T.J_1907_R(itemstack, b_3129_s.t_1786_h()) && playerentity != null) {
                    k_2610_C effectinstance1 = playerentity.R_4764_Y(MobEffects.t_4043_B);
                    int i = 1;
                    if (effectinstance1 != null) {
                        i += effectinstance1.R_4764_Y();
                        playerentity.n_1700_B(MobEffects.t_4043_B);
                    } else {
                        --i;
                    }
                    i = u_530_F.n_1700_B(i, 0, 4);
                    k_2610_C effectinstance = new k_2610_C(MobEffects.t_4043_B, 120000, i, false, false, true);
                    if (!this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.k_2293_S)) {
                        playerentity.n_1700_B(effectinstance);
                    }
                }
            }
        }
        super.R_4764_Y(cause);
    }

    @Override
    public boolean V_1176_p() {
        return !this.J_3635_s();
    }

    public void n_1700_B(@Nullable b_3129_s raid) {
        this.J_1907_R = raid;
    }

    @Nullable
    public b_3129_s y_2447_C() {
        return this.J_1907_R;
    }

    public boolean J_3635_s() {
        return this.y_2447_C() != null && this.y_2447_C().Y_601_j();
    }

    public void n_1700_B(int wave) {
        this.h_1847_R = wave;
    }

    public int o_82_k() {
        return this.h_1847_R;
    }

    public boolean h_973_D() {
        return this.l_4537_E.n_1700_B(n_1700_B);
    }

    public void Y_601_j(boolean celebrate) {
        this.l_4537_E.J_1907_R(n_1700_B, celebrate);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("Wave", this.h_1847_R);
        compound.n_1700_B("CanJoinRaid", this.Q_4569_t);
        if (this.J_1907_R != null) {
            compound.J_1907_R("RaidId", this.J_1907_R.w_1457_N());
        }
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.h_1847_R = compound.w_1484_f("Wave");
        this.Q_4569_t = compound.t_1786_h("CanJoinRaid");
        if (compound.R_4764_Y("RaidId", 3)) {
            if (this.O_508_d instanceof e_3591_l) {
                this.J_1907_R = ((e_3591_l)this.O_508_d).RealmsClientConfig().n_1700_B(compound.w_1484_f("RaidId"));
            }
            if (this.J_1907_R != null) {
                this.J_1907_R.n_1700_B(this.h_1847_R, this, false);
                if (this.A_1306_N()) {
                    this.J_1907_R.n_1700_B(this.h_1847_R, this);
                }
            }
        }
    }

    @Override
    protected void J_1907_R(n_1494_c itemEntity) {
        boolean flag;
        Z_1993_T itemstack = itemEntity.P_1922_E();
        boolean bl = flag = this.J_3635_s() && this.y_2447_C().n_1700_B(this.o_82_k()) != null;
        if (this.J_3635_s() && !flag && Z_1993_T.J_1907_R(itemstack, b_3129_s.t_1786_h())) {
            e_1174_E equipmentslottype = e_1174_E.u_1723_Y;
            Z_1993_T itemstack1 = this.J_1907_R(equipmentslottype);
            double d0 = this.P_1922_E(equipmentslottype);
            if (!itemstack1.n_1700_B() && (double)Math.max(this.RealmsWorldOptions.nextFloat() - 0.1f, 0.0f) < d0) {
                this.a_(itemstack1);
            }
            this.n_1700_B(itemEntity);
            this.n_1700_B(equipmentslottype, itemstack);
            this.n_1700_B((N_4263_v)itemEntity, itemstack.t_4043_B());
            itemEntity.Ops();
            this.y_2447_C().n_1700_B(this.o_82_k(), this);
            this.Y_259_p(true);
        } else {
            super.J_1907_R(itemEntity);
        }
    }

    @Override
    public boolean w_1484_f(double distanceToClosestPlayer) {
        return this.y_2447_C() == null ? super.w_1484_f(distanceToClosestPlayer) : false;
    }

    @Override
    public boolean e_2887_G() {
        return super.e_2887_G() || this.y_2447_C() != null;
    }

    public int f_2787_O() {
        return this.M_182_A;
    }

    public void J_1907_R(int delay) {
        this.M_182_A = delay;
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (this.J_3635_s()) {
            this.y_2447_C().h_1847_R();
        }
        return super.n_1700_B(source, amount);
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        this.w_1457_N(this.f_4016_n() != t_5_h.RetryCallException || reason != a_3160_D.n_1700_B);
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public abstract SoundEvent P_2295_B();

    public class G_564_y<T extends W_4304_a>
    extends Goal {
        private final T n_1700_B;

        /*
         * WARNING - Possible parameter corruption
         */
        public G_564_y(T raiderEntity) {
            this.n_1700_B = raiderEntity;
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
        }

        @Override
        public boolean n_1700_B() {
            b_3129_s raid = ((W_4304_a)this.n_1700_B).y_2447_C();
            if (((W_4304_a)this.n_1700_B).J_3635_s() && !((W_4304_a)this.n_1700_B).y_2447_C().n_1700_B() && ((PatrollingMonster)this.n_1700_B).c_2086_l() && !Z_1993_T.J_1907_R(((Z_530_i)this.n_1700_B).J_1907_R(e_1174_E.u_1723_Y), b_3129_s.t_1786_h())) {
                List<n_1494_c> list;
                W_4304_a abstractraiderentity = raid.n_1700_B(((W_4304_a)this.n_1700_B).o_82_k());
                if (!(abstractraiderentity != null && abstractraiderentity.RealmsLongRunningMcoTaskScreen() || (list = ((W_4304_a)this.n_1700_B).O_508_d.n_1700_B(n_1494_c.class, ((N_4263_v)this.n_1700_B).i_601_W().grow(16.0, 8.0, 16.0), R_4764_Y)).isEmpty())) {
                    return ((Z_530_i)this.n_1700_B).e_4240_b().n_1700_B((N_4263_v)list.get(0), (double)1.15f);
                }
                return false;
            }
            return false;
        }

        @Override
        public void P_1922_E() {
            List<n_1494_c> list;
            if (((Z_530_i)this.n_1700_B).e_4240_b().v_4262_N().withinDistance(((N_4263_v)this.n_1700_B).s_4990_V(), 1.414) && !(list = ((W_4304_a)this.n_1700_B).O_508_d.n_1700_B(n_1494_c.class, ((N_4263_v)this.n_1700_B).i_601_W().grow(4.0, 4.0, 4.0), R_4764_Y)).isEmpty()) {
                ((W_4304_a)this.n_1700_B).J_1907_R(list.get(0));
            }
        }
    }

    static class R_4764_Y
    extends Goal {
        private final W_4304_a n_1700_B;
        private final double J_1907_R;
        private c_1514_x R_4764_Y;
        private final List<c_1514_x> G_564_y = Lists.newArrayList();
        private final int P_1922_E;
        private boolean u_1723_Y;

        public R_4764_Y(W_4304_a raiderEntity, double speed, int distance) {
            this.n_1700_B = raiderEntity;
            this.J_1907_R = speed;
            this.P_1922_E = distance;
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
        }

        @Override
        public boolean n_1700_B() {
            this.s_956_w();
            return this.v_4262_N() && this.w_1484_f() && this.n_1700_B.t_148_a() == null;
        }

        private boolean v_4262_N() {
            return this.n_1700_B.J_3635_s() && !this.n_1700_B.y_2447_C().n_1700_B();
        }

        private boolean w_1484_f() {
            e_3591_l serverworld = (e_3591_l)this.n_1700_B.O_508_d;
            c_1514_x blockpos = this.n_1700_B.b_2312_j();
            Optional<c_1514_x> optional = serverworld.p_178_J().n_1700_B(poiType -> poiType == q_2232_A.multiplayerClientSuggestionProvider, this::n_1700_B, b_4946_z.J_1907_R.R_4764_Y, blockpos, 48, this.n_1700_B.RealmsWorldOptions);
            if (!optional.isPresent()) {
                return false;
            }
            this.R_4764_Y = optional.get().toImmutable();
            return true;
        }

        @Override
        public boolean J_1907_R() {
            if (this.n_1700_B.e_4240_b().M_588_G()) {
                return false;
            }
            return this.n_1700_B.t_148_a() == null && !this.R_4764_Y.withinDistance(this.n_1700_B.s_4990_V(), (double)(this.n_1700_B.C_415_h() + (float)this.P_1922_E)) && !this.u_1723_Y;
        }

        @Override
        public void G_564_y() {
            if (this.R_4764_Y.withinDistance(this.n_1700_B.s_4990_V(), (double)this.P_1922_E)) {
                this.G_564_y.add(this.R_4764_Y);
            }
        }

        @Override
        public void R_4764_Y() {
            super.R_4764_Y();
            this.n_1700_B.u_2550_I(0);
            this.n_1700_B.e_4240_b().n_1700_B((double)this.R_4764_Y.getX(), (double)this.R_4764_Y.getY(), (double)this.R_4764_Y.getZ(), this.J_1907_R);
            this.u_1723_Y = false;
        }

        @Override
        public void P_1922_E() {
            if (this.n_1700_B.e_4240_b().M_588_G()) {
                e_2866_D vector3d = e_2866_D.R_4764_Y(this.R_4764_Y);
                e_2866_D vector3d1 = W_3371_U.n_1700_B(this.n_1700_B, 16, 7, vector3d, 0.3141592741012573);
                if (vector3d1 == null) {
                    vector3d1 = W_3371_U.J_1907_R(this.n_1700_B, 8, 7, vector3d);
                }
                if (vector3d1 == null) {
                    this.u_1723_Y = true;
                    return;
                }
                this.n_1700_B.e_4240_b().n_1700_B(vector3d1.J_1907_R, vector3d1.R_4764_Y, vector3d1.G_564_y, this.J_1907_R);
            }
        }

        private boolean n_1700_B(c_1514_x pos) {
            for (c_1514_x blockpos : this.G_564_y) {
                if (!Objects.equals(pos, blockpos)) continue;
                return false;
            }
            return true;
        }

        private void s_956_w() {
            if (this.G_564_y.size() > 2) {
                this.G_564_y.remove(0);
            }
        }
    }

    public class n_1700_B
    extends Goal {
        private final W_4304_a J_1907_R;

        n_1700_B(W_4304_a raiderEntity) {
            this.J_1907_R = raiderEntity;
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
        }

        @Override
        public boolean n_1700_B() {
            b_3129_s raid = this.J_1907_R.y_2447_C();
            return this.J_1907_R.RealmsLongRunningMcoTaskScreen() && this.J_1907_R.t_148_a() == null && raid != null && raid.u_1723_Y();
        }

        @Override
        public void R_4764_Y() {
            this.J_1907_R.Y_601_j(true);
            super.R_4764_Y();
        }

        @Override
        public void G_564_y() {
            this.J_1907_R.Y_601_j(false);
            super.G_564_y();
        }

        @Override
        public void P_1922_E() {
            if (!this.J_1907_R.y_1700_S() && this.J_1907_R.RealmsWorldOptions.nextInt(100) == 0) {
                W_4304_a.this.n_1700_B(W_4304_a.this.P_2295_B(), W_4304_a.this.d_4500_Q(), W_4304_a.this.O_2761_o());
            }
            if (!this.J_1907_R.y_2772_m() && this.J_1907_R.RealmsWorldOptions.nextInt(50) == 0) {
                this.J_1907_R.t_4043_B().n_1700_B();
            }
            super.P_1922_E();
        }
    }

    public class J_1907_R
    extends Goal {
        private final W_4304_a J_1907_R;
        private final float R_4764_Y;
        public final TargetingConditions n_1700_B = new TargetingConditions().n_1700_B(8.0).G_564_y().n_1700_B().J_1907_R().R_4764_Y().P_1922_E();

        public J_1907_R(W_4304_a this$0, AbstractIllager raiderEntity, float range) {
            this.J_1907_R = raiderEntity;
            this.R_4764_Y = range * range;
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R));
        }

        @Override
        public boolean n_1700_B() {
            r_4811_B livingentity = this.J_1907_R.q_817_e();
            return this.J_1907_R.y_2447_C() == null && this.J_1907_R.R_2822_N() && this.J_1907_R.t_148_a() != null && !this.J_1907_R.P_2272_O() && (livingentity == null || livingentity.f_4016_n() != t_5_h.g_4106_L);
        }

        @Override
        public void R_4764_Y() {
            super.R_4764_Y();
            this.J_1907_R.e_4240_b().h_1847_R();
            for (W_4304_a abstractraiderentity : this.J_1907_R.O_508_d.n_1700_B(W_4304_a.class, this.n_1700_B, this.J_1907_R, this.J_1907_R.i_601_W().grow(8.0, 8.0, 8.0))) {
                abstractraiderentity.R_4764_Y(this.J_1907_R.t_148_a());
            }
        }

        @Override
        public void G_564_y() {
            super.G_564_y();
            r_4811_B livingentity = this.J_1907_R.t_148_a();
            if (livingentity != null) {
                for (W_4304_a abstractraiderentity : this.J_1907_R.O_508_d.n_1700_B(W_4304_a.class, this.n_1700_B, this.J_1907_R, this.J_1907_R.i_601_W().grow(8.0, 8.0, 8.0))) {
                    abstractraiderentity.R_4764_Y(livingentity);
                    abstractraiderentity.multiplayerClientSuggestionProvider(true);
                }
                this.J_1907_R.multiplayerClientSuggestionProvider(true);
            }
        }

        @Override
        public void P_1922_E() {
            r_4811_B livingentity = this.J_1907_R.t_148_a();
            if (livingentity != null) {
                if (this.J_1907_R.G_564_y((N_4263_v)livingentity) > (double)this.R_4764_Y) {
                    this.J_1907_R.c_3005_b().n_1700_B(livingentity, 30.0f, 30.0f);
                    if (this.J_1907_R.RealmsWorldOptions.nextInt(50) == 0) {
                        this.J_1907_R.G_624_v();
                    }
                } else {
                    this.J_1907_R.multiplayerClientSuggestionProvider(true);
                }
                super.P_1922_E();
            }
        }
    }
}


