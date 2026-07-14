/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.D_38_f;
import lightning.product.I_4817_s;
import lightning.product.MobEffects;
import lightning.product.K_4074_S;
import lightning.product.P_11_z;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.X_1924_A;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.i_2154_H;
import lightning.product.k_2610_C;
import lightning.product.ClientboundBlockEntityDataPacket;
import lightning.product.BlockEntityType;
import lightning.product.r_4811_B;
import lightning.product.ParticleTypes;
import lightning.product.u_530_F;
import lightning.product.x_1835_e;

public class m_1551_m
extends i_2154_H
implements X_1924_A {
    private static final T_2915_h[] J_1907_R = new T_2915_h[]{a_3742_W.z_2311_U, a_3742_W.Q_1082_O, a_3742_W.T_33_Q, a_3742_W.G_3540_E};
    public int n_1700_B;
    private float R_4764_Y;
    private boolean G_564_y;
    private boolean P_1922_E;
    private final List<c_1514_x> u_1723_Y = Lists.newArrayList();
    @Nullable
    private r_4811_B v_4262_N;
    @Nullable
    private UUID w_1484_f;
    private long t_148_a;

    public m_1551_m() {
        this(BlockEntityType.q_2307_F);
    }

    public m_1551_m(BlockEntityType<?> p_i48929_1_) {
        super(p_i48929_1_);
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        super.n_1700_B(state, nbt);
        this.w_1484_f = nbt.J_1907_R("Target") ? nbt.n_1700_B("Target") : null;
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        if (this.v_4262_N != null) {
            compound.n_1700_B("Target", this.v_4262_N.w_2705_t());
        }
        return compound;
    }

    @Override
    @Nullable
    public ClientboundBlockEntityDataPacket G_() {
        return new ClientboundBlockEntityDataPacket(this.M_588_G, 5, this.H_());
    }

    @Override
    public U_2912_j H_() {
        return this.n_1700_B(new U_2912_j());
    }

    @Override
    public void P_1922_E() {
        ++this.n_1700_B;
        long i = this.u_2550_I.X_933_l();
        if (i % 40L == 0L) {
            this.n_1700_B(this.s_956_w());
            if (!this.u_2550_I.Y_259_p && this.v_4262_N()) {
                this.u_2550_I();
                this.M_588_G();
            }
        }
        if (i % 80L == 0L && this.v_4262_N()) {
            this.n_1700_B(SoundEvents.X_4895_T);
        }
        if (i > this.t_148_a && this.v_4262_N()) {
            this.t_148_a = i + 60L + (long)this.u_2550_I.e_4240_b().nextInt(40);
            this.n_1700_B(SoundEvents.L_103_L);
        }
        if (this.u_2550_I.Y_259_p) {
            this.P_4830_p();
            this.t_1786_h();
            if (this.v_4262_N()) {
                this.R_4764_Y += 1.0f;
            }
        }
    }

    private boolean s_956_w() {
        this.u_1723_Y.clear();
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                for (int k = -1; k <= 1; ++k) {
                    c_1514_x blockpos = this.M_588_G.add(i, j, k);
                    if (this.u_2550_I.s_956_w(blockpos)) continue;
                    return false;
                }
            }
        }
        for (int j1 = -2; j1 <= 2; ++j1) {
            for (int k1 = -2; k1 <= 2; ++k1) {
                for (int l1 = -2; l1 <= 2; ++l1) {
                    int i2 = Math.abs(j1);
                    int l = Math.abs(k1);
                    int i1 = Math.abs(l1);
                    if (i2 <= 1 && l <= 1 && i1 <= 1 || (j1 != 0 || l != 2 && i1 != 2) && (k1 != 0 || i2 != 2 && i1 != 2) && (l1 != 0 || i2 != 2 && l != 2)) continue;
                    c_1514_x blockpos1 = this.M_588_G.add(j1, k1, l1);
                    K_4074_S blockstate = this.u_2550_I.getBlockState(blockpos1);
                    for (T_2915_h block : J_1907_R) {
                        if (!blockstate.n_1700_B(block)) continue;
                        this.u_1723_Y.add(blockpos1);
                    }
                }
            }
        }
        this.J_1907_R(this.u_1723_Y.size() >= 42);
        return this.u_1723_Y.size() >= 16;
    }

    private void u_2550_I() {
        int i1;
        int l;
        int i = this.u_1723_Y.size();
        int j = i / 7 * 16;
        int k = this.M_588_G.getX();
        I_4817_s axisalignedbb = new I_4817_s(k, l = this.M_588_G.getY(), i1 = this.M_588_G.getZ(), k + 1, l + 1, i1 + 1).grow(j).expand(0.0, this.u_2550_I.c_3005_b(), 0.0);
        List<a_3913_L> list = this.u_2550_I.n_1700_B(a_3913_L.class, axisalignedbb);
        if (!list.isEmpty()) {
            for (a_3913_L playerentity : list) {
                if (!this.M_588_G.withinDistance(playerentity.b_2312_j(), (double)j) || !playerentity.LongRunningTask()) continue;
                playerentity.n_1700_B(new k_2610_C(MobEffects.A_4115_X, 260, 0, true, true));
            }
        }
    }

    private void M_588_G() {
        r_4811_B livingentity = this.v_4262_N;
        int i = this.u_1723_Y.size();
        if (i < 42) {
            this.v_4262_N = null;
        } else if (this.v_4262_N == null && this.w_1484_f != null) {
            this.v_4262_N = this.Q_4569_t();
            this.w_1484_f = null;
        } else if (this.v_4262_N == null) {
            List<r_4811_B> list = this.u_2550_I.n_1700_B(r_4811_B.class, this.h_1847_R(), p_205033_0_ -> p_205033_0_ instanceof x_1835_e && p_205033_0_.LongRunningTask());
            if (!list.isEmpty()) {
                this.v_4262_N = list.get(this.u_2550_I.w_1457_N.nextInt(list.size()));
            }
        } else if (!this.v_4262_N.RealmsLongRunningMcoTaskScreen() || !this.M_588_G.withinDistance(this.v_4262_N.b_2312_j(), 8.0)) {
            this.v_4262_N = null;
        }
        if (this.v_4262_N != null) {
            this.u_2550_I.n_1700_B((a_3913_L)null, this.v_4262_N.O_3598_v(), this.v_4262_N.X_2960_b(), this.v_4262_N.l_2647_k(), SoundEvents.n_3197_X, D_38_f.P_1922_E, 1.0f, 1.0f);
            this.v_4262_N.n_1700_B(P_11_z.Q_4569_t, 4.0f);
        }
        if (livingentity != this.v_4262_N) {
            K_4074_S blockstate = this.e_4240_b();
            this.u_2550_I.n_1700_B(this.M_588_G, blockstate, blockstate, 2);
        }
    }

    private void P_4830_p() {
        if (this.w_1484_f == null) {
            this.v_4262_N = null;
        } else if (this.v_4262_N == null || !this.v_4262_N.w_2705_t().equals(this.w_1484_f)) {
            this.v_4262_N = this.Q_4569_t();
            if (this.v_4262_N == null) {
                this.w_1484_f = null;
            }
        }
    }

    private I_4817_s h_1847_R() {
        int i = this.M_588_G.getX();
        int j = this.M_588_G.getY();
        int k = this.M_588_G.getZ();
        return new I_4817_s(i, j, k, i + 1, j + 1, k + 1).grow(8.0);
    }

    @Nullable
    private r_4811_B Q_4569_t() {
        List<r_4811_B> list = this.u_2550_I.n_1700_B(r_4811_B.class, this.h_1847_R(), p_205032_1_ -> p_205032_1_.w_2705_t().equals(this.w_1484_f));
        return list.size() == 1 ? list.get(0) : null;
    }

    private void t_1786_h() {
        Random random = this.u_2550_I.w_1457_N;
        double d0 = u_530_F.n_1700_B((float)(this.n_1700_B + 35) * 0.1f) / 2.0f + 0.5f;
        d0 = (d0 * d0 + d0) * (double)0.3f;
        e_2866_D vector3d = new e_2866_D((double)this.M_588_G.getX() + 0.5, (double)this.M_588_G.getY() + 1.5 + d0, (double)this.M_588_G.getZ() + 0.5);
        for (c_1514_x blockpos : this.u_1723_Y) {
            if (random.nextInt(50) != 0) continue;
            float f = -0.5f + random.nextFloat();
            float f1 = -2.0f + random.nextFloat();
            float f2 = -0.5f + random.nextFloat();
            c_1514_x blockpos1 = blockpos.subtract(this.M_588_G);
            e_2866_D vector3d1 = new e_2866_D(f, f1, f2).J_1907_R(blockpos1.getX(), blockpos1.getY(), blockpos1.getZ());
            this.u_2550_I.n_1700_B(ParticleTypes.z_1333_t, vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y, vector3d1.J_1907_R, vector3d1.R_4764_Y, vector3d1.G_564_y);
        }
        if (this.v_4262_N != null) {
            e_2866_D vector3d2 = new e_2866_D(this.v_4262_N.O_3598_v(), this.v_4262_N.X_2048_Y(), this.v_4262_N.l_2647_k());
            float f3 = (-0.5f + random.nextFloat()) * (3.0f + this.v_4262_N.C_415_h());
            float f4 = -1.0f + random.nextFloat() * this.v_4262_N.v_165_F();
            float f5 = (-0.5f + random.nextFloat()) * (3.0f + this.v_4262_N.C_415_h());
            e_2866_D vector3d3 = new e_2866_D(f3, f4, f5);
            this.u_2550_I.n_1700_B(ParticleTypes.z_1333_t, vector3d2.J_1907_R, vector3d2.R_4764_Y, vector3d2.G_564_y, vector3d3.J_1907_R, vector3d3.R_4764_Y, vector3d3.G_564_y);
        }
    }

    public boolean v_4262_N() {
        return this.G_564_y;
    }

    public boolean w_1484_f() {
        return this.P_1922_E;
    }

    private void n_1700_B(boolean p_205739_1_) {
        if (p_205739_1_ != this.G_564_y) {
            this.n_1700_B(p_205739_1_ ? SoundEvents.P_925_e : SoundEvents.P_2947_S);
        }
        this.G_564_y = p_205739_1_;
    }

    private void J_1907_R(boolean p_207736_1_) {
        this.P_1922_E = p_207736_1_;
    }

    public float n_1700_B(float p_205036_1_) {
        return (this.R_4764_Y + p_205036_1_) * -0.0375f;
    }

    public void n_1700_B(SoundEvent p_205738_1_) {
        this.u_2550_I.n_1700_B((a_3913_L)null, this.M_588_G, p_205738_1_, D_38_f.P_1922_E, 1.0f, 1.0f);
    }
}


