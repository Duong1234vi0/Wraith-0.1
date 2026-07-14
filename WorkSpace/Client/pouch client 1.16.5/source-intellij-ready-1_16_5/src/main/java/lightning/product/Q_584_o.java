/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import javax.annotation.Nullable;
import lightning.product.F_4023_g;
import lightning.product.H_1468_N;
import lightning.product.I_4817_s;
import lightning.product.N_4263_v;
import lightning.product.SpawnData;
import lightning.product.T_1316_M;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.V_3157_k;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.WeighedRandom;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_2336_b;
import lightning.product.q_2896_o;
import lightning.product.s_3109_F;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Q_584_o {
    private static final Logger n_1700_B = LogManager.getLogger();
    private int J_1907_R = 20;
    private final List<SpawnData> R_4764_Y = Lists.newArrayList();
    private SpawnData G_564_y = new SpawnData();
    private double P_1922_E;
    private double u_1723_Y;
    private int v_4262_N = 200;
    private int w_1484_f = 800;
    private int t_148_a = 4;
    @Nullable
    private N_4263_v s_956_w;
    private int u_2550_I = 6;
    private int M_588_G = 16;
    private int P_4830_p = 4;

    @Nullable
    private g_2336_b v_4262_N() {
        String s = this.G_564_y.J_1907_R().M_588_G("id");
        try {
            return H_1468_N.J_1907_R(s) ? null : new g_2336_b(s);
        }
        catch (s_3109_F resourcelocationexception) {
            c_1514_x blockpos = this.J_1907_R();
            n_1700_B.warn("Invalid entity id '{}' at spawner {}:[{},{},{}]", (Object)s, (Object)this.n_1700_B().g_2268_R().n_1700_B(), (Object)blockpos.getX(), (Object)blockpos.getY(), (Object)blockpos.getZ());
            return null;
        }
    }

    public void n_1700_B(t_5_h<?> type) {
        this.G_564_y.J_1907_R().n_1700_B("id", V_3137_a.g_221_o.J_1907_R(type).toString());
    }

    private boolean w_1484_f() {
        c_1514_x blockpos = this.J_1907_R();
        return this.n_1700_B().n_1700_B((double)blockpos.getX() + 0.5, (double)blockpos.getY() + 0.5, (double)blockpos.getZ() + 0.5, (double)this.M_588_G);
    }

    public void R_4764_Y() {
        if (!this.w_1484_f()) {
            this.u_1723_Y = this.P_1922_E;
        } else {
            b_4507_u world = this.n_1700_B();
            c_1514_x blockpos = this.J_1907_R();
            if (!(world instanceof e_3591_l)) {
                double d3 = (double)blockpos.getX() + world.w_1457_N.nextDouble();
                double d4 = (double)blockpos.getY() + world.w_1457_N.nextDouble();
                double d5 = (double)blockpos.getZ() + world.w_1457_N.nextDouble();
                world.n_1700_B(ParticleTypes.B_1668_F, d3, d4, d5, 0.0, 0.0, 0.0);
                world.n_1700_B(ParticleTypes.c_3005_b, d3, d4, d5, 0.0, 0.0, 0.0);
                if (this.J_1907_R > 0) {
                    --this.J_1907_R;
                }
                this.u_1723_Y = this.P_1922_E;
                this.P_1922_E = (this.P_1922_E + (double)(1000.0f / ((float)this.J_1907_R + 200.0f))) % 360.0;
            } else {
                if (this.J_1907_R == -1) {
                    this.t_148_a();
                }
                if (this.J_1907_R > 0) {
                    --this.J_1907_R;
                    return;
                }
                boolean flag = false;
                for (int i = 0; i < this.t_148_a; ++i) {
                    double d2;
                    U_2912_j compoundnbt = this.G_564_y.J_1907_R();
                    Optional<t_5_h<?>> optional = t_5_h.n_1700_B(compoundnbt);
                    if (!optional.isPresent()) {
                        this.t_148_a();
                        return;
                    }
                    q_2896_o listnbt = compoundnbt.G_564_y("Pos", 6);
                    int j = listnbt.size();
                    double d0 = j >= 1 ? listnbt.v_4262_N(0) : (double)blockpos.getX() + (world.w_1457_N.nextDouble() - world.w_1457_N.nextDouble()) * (double)this.P_4830_p + 0.5;
                    double d1 = j >= 2 ? listnbt.v_4262_N(1) : (double)(blockpos.getY() + world.w_1457_N.nextInt(3) - 1);
                    double d = d2 = j >= 3 ? listnbt.v_4262_N(2) : (double)blockpos.getZ() + (world.w_1457_N.nextDouble() - world.w_1457_N.nextDouble()) * (double)this.P_4830_p + 0.5;
                    if (!world.J_1907_R(optional.get().n_1700_B(d0, d1, d2))) continue;
                    e_3591_l serverworld = (e_3591_l)world;
                    if (!F_4023_g.n_1700_B(optional.get(), serverworld, a_3160_D.R_4764_Y, new c_1514_x(d0, d1, d2), world.e_4240_b())) continue;
                    N_4263_v entity = t_5_h.n_1700_B(compoundnbt, world, p_221408_6_ -> {
                        p_221408_6_.J_1907_R(d0, d1, d2, p_221408_6_.p_178_J, p_221408_6_.f_4016_n);
                        return p_221408_6_;
                    });
                    if (entity == null) {
                        this.t_148_a();
                        return;
                    }
                    int k = world.n_1700_B(entity.getClass(), new I_4817_s(blockpos.getX(), blockpos.getY(), blockpos.getZ(), blockpos.getX() + 1, blockpos.getY() + 1, blockpos.getZ() + 1).grow(this.P_4830_p)).size();
                    if (k >= this.u_2550_I) {
                        this.t_148_a();
                        return;
                    }
                    entity.J_1907_R(entity.O_3598_v(), entity.X_2960_b(), entity.l_2647_k(), world.w_1457_N.nextFloat() * 360.0f, 0.0f);
                    if (entity instanceof Z_530_i) {
                        Z_530_i mobentity = (Z_530_i)entity;
                        if (!mobentity.n_1700_B((LevelAccessor)world, a_3160_D.R_4764_Y) || !mobentity.n_1700_B((T_1316_M)world)) continue;
                        if (this.G_564_y.J_1907_R().P_1922_E() == 1 && this.G_564_y.J_1907_R().R_4764_Y("id", 8)) {
                            ((Z_530_i)entity).n_1700_B(serverworld, world.J_1907_R(entity.b_2312_j()), a_3160_D.R_4764_Y, (V_3157_k)null, null);
                        }
                    }
                    if (!serverworld.t_148_a(entity)) {
                        this.t_148_a();
                        return;
                    }
                    world.R_4764_Y(2004, blockpos, 0);
                    if (entity instanceof Z_530_i) {
                        ((Z_530_i)entity).T_2506_i();
                    }
                    flag = true;
                }
                if (flag) {
                    this.t_148_a();
                }
            }
        }
    }

    private void t_148_a() {
        if (this.w_1484_f <= this.v_4262_N) {
            this.J_1907_R = this.v_4262_N;
        } else {
            int i = this.w_1484_f - this.v_4262_N;
            this.J_1907_R = this.v_4262_N + this.n_1700_B().w_1457_N.nextInt(i);
        }
        if (!this.R_4764_Y.isEmpty()) {
            this.n_1700_B(WeighedRandom.n_1700_B(this.n_1700_B().w_1457_N, this.R_4764_Y));
        }
        this.n_1700_B(1);
    }

    public void n_1700_B(U_2912_j nbt) {
        this.J_1907_R = nbt.v_4262_N("Delay");
        this.R_4764_Y.clear();
        if (nbt.R_4764_Y("SpawnPotentials", 9)) {
            q_2896_o listnbt = nbt.G_564_y("SpawnPotentials", 10);
            for (int i = 0; i < listnbt.size(); ++i) {
                this.R_4764_Y.add(new SpawnData(listnbt.n_1700_B(i)));
            }
        }
        if (nbt.R_4764_Y("SpawnData", 10)) {
            this.n_1700_B(new SpawnData(1, nbt.M_182_A("SpawnData")));
        } else if (!this.R_4764_Y.isEmpty()) {
            this.n_1700_B(WeighedRandom.n_1700_B(this.n_1700_B().w_1457_N, this.R_4764_Y));
        }
        if (nbt.R_4764_Y("MinSpawnDelay", 99)) {
            this.v_4262_N = nbt.v_4262_N("MinSpawnDelay");
            this.w_1484_f = nbt.v_4262_N("MaxSpawnDelay");
            this.t_148_a = nbt.v_4262_N("SpawnCount");
        }
        if (nbt.R_4764_Y("MaxNearbyEntities", 99)) {
            this.u_2550_I = nbt.v_4262_N("MaxNearbyEntities");
            this.M_588_G = nbt.v_4262_N("RequiredPlayerRange");
        }
        if (nbt.R_4764_Y("SpawnRange", 99)) {
            this.P_4830_p = nbt.v_4262_N("SpawnRange");
        }
        if (this.n_1700_B() != null) {
            this.s_956_w = null;
        }
    }

    public U_2912_j J_1907_R(U_2912_j compound) {
        g_2336_b resourcelocation = this.v_4262_N();
        if (resourcelocation == null) {
            return compound;
        }
        compound.n_1700_B("Delay", (short)this.J_1907_R);
        compound.n_1700_B("MinSpawnDelay", (short)this.v_4262_N);
        compound.n_1700_B("MaxSpawnDelay", (short)this.w_1484_f);
        compound.n_1700_B("SpawnCount", (short)this.t_148_a);
        compound.n_1700_B("MaxNearbyEntities", (short)this.u_2550_I);
        compound.n_1700_B("RequiredPlayerRange", (short)this.M_588_G);
        compound.n_1700_B("SpawnRange", (short)this.P_4830_p);
        compound.n_1700_B("SpawnData", this.G_564_y.J_1907_R().v_4262_N());
        q_2896_o listnbt = new q_2896_o();
        if (this.R_4764_Y.isEmpty()) {
            listnbt.add(this.G_564_y.n_1700_B());
        } else {
            for (SpawnData weightedspawnerentity : this.R_4764_Y) {
                listnbt.add(weightedspawnerentity.n_1700_B());
            }
        }
        compound.n_1700_B("SpawnPotentials", listnbt);
        return compound;
    }

    @Nullable
    public N_4263_v G_564_y() {
        if (this.s_956_w == null) {
            this.s_956_w = t_5_h.n_1700_B(this.G_564_y.J_1907_R(), this.n_1700_B(), Function.identity());
            if (this.G_564_y.J_1907_R().P_1922_E() != 1 || !this.G_564_y.J_1907_R().R_4764_Y("id", 8) || this.s_956_w instanceof Z_530_i) {
                // empty if block
            }
        }
        return this.s_956_w;
    }

    public boolean J_1907_R(int delay) {
        if (delay == 1 && this.n_1700_B().Y_259_p) {
            this.J_1907_R = this.v_4262_N;
            return true;
        }
        return false;
    }

    public void n_1700_B(SpawnData nextSpawnData) {
        this.G_564_y = nextSpawnData;
    }

    public abstract void n_1700_B(int var1);

    public abstract b_4507_u n_1700_B();

    public abstract c_1514_x J_1907_R();

    public double P_1922_E() {
        return this.P_1922_E;
    }

    public double u_1723_Y() {
        return this.u_1723_Y;
    }
}


