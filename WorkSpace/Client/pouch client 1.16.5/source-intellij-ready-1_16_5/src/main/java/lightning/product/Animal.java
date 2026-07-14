/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.B_4088_l;
import lightning.product.AgableMob;
import lightning.product.I_1869_h;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.T_1316_M;
import lightning.product.Stats;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.Z_1993_T;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.m_3054_I;
import lightning.product.n_4637_L;
import lightning.product.Items;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;
import lightning.product.x_1688_C;

public abstract class Animal
extends AgableMob {
    private int h_1847_R;
    private UUID Q_4569_t;

    protected Animal(t_5_h<? extends Animal> type, b_4507_u worldIn) {
        super((t_5_h<? extends AgableMob>)type, worldIn);
        this.n_1700_B(I_1869_h.M_588_G, 16.0f);
        this.n_1700_B(I_1869_h.P_4830_p, -1.0f);
    }

    @Override
    protected void X_933_l() {
        if (this.x_() != 0) {
            this.h_1847_R = 0;
        }
        super.X_933_l();
    }

    @Override
    public void Y_1740_V() {
        super.Y_1740_V();
        if (this.x_() != 0) {
            this.h_1847_R = 0;
        }
        if (this.h_1847_R > 0) {
            --this.h_1847_R;
            if (this.h_1847_R % 10 == 0) {
                double d0 = this.RealmsWorldOptions.nextGaussian() * 0.02;
                double d1 = this.RealmsWorldOptions.nextGaussian() * 0.02;
                double d2 = this.RealmsWorldOptions.nextGaussian() * 0.02;
                this.O_508_d.n_1700_B(ParticleTypes.e_4240_b, this.G_564_y(1.0), this.M_766_z() + 0.5, this.v_4262_N(1.0), d0, d1, d2);
            }
        }
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (this.n_1700_B(source)) {
            return false;
        }
        this.h_1847_R = 0;
        return super.n_1700_B(source, amount);
    }

    @Override
    public float n_1700_B(c_1514_x pos, T_1316_M worldIn) {
        return worldIn.getBlockState(pos.down()).n_1700_B(a_3742_W.t_148_a) ? 10.0f : worldIn.w_1484_f(pos) - 0.5f;
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("InLove", this.h_1847_R);
        if (this.Q_4569_t != null) {
            compound.n_1700_B("LoveCause", this.Q_4569_t);
        }
    }

    @Override
    public double O_2151_c() {
        return 0.14;
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.h_1847_R = compound.w_1484_f("InLove");
        this.Q_4569_t = compound.J_1907_R("LoveCause") ? compound.n_1700_B("LoveCause") : null;
    }

    public static boolean R_4764_Y(t_5_h<? extends Animal> animal, LevelAccessor worldIn, a_3160_D reason, c_1514_x pos, Random random) {
        return worldIn.getBlockState(pos.down()).n_1700_B(a_3742_W.t_148_a) && worldIn.n_1700_B(pos, 0) > 8;
    }

    @Override
    public int v_4276_D() {
        return 120;
    }

    @Override
    public boolean w_1484_f(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    protected int R_4764_Y(a_3913_L player) {
        return 1 + this.O_508_d.w_1457_N.nextInt(3);
    }

    public boolean u_2550_I(Z_1993_T stack) {
        return stack.J_1907_R() == Items.V_3441_j;
    }

    @Override
    public m_3054_I J_1907_R(a_3913_L p_230254_1_, x_1688_C p_230254_2_) {
        Z_1993_T itemstack = p_230254_1_.R_4764_Y(p_230254_2_);
        if (this.u_2550_I(itemstack)) {
            int i = this.x_();
            if (!this.O_508_d.Y_259_p && i == 0 && this.o_82_k()) {
                this.n_1700_B(p_230254_1_, itemstack);
                this.P_1922_E(p_230254_1_);
                return m_3054_I.n_1700_B;
            }
            if (this.d_()) {
                this.n_1700_B(p_230254_1_, itemstack);
                this.n_1700_B((int)((float)(-i / 20) * 0.1f), true);
                return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
            }
            if (this.O_508_d.Y_259_p) {
                return m_3054_I.J_1907_R;
            }
        }
        return super.J_1907_R(p_230254_1_, p_230254_2_);
    }

    protected void n_1700_B(a_3913_L player, Z_1993_T stack) {
        if (!player.C_415_h.G_564_y) {
            stack.v_4262_N(1);
        }
    }

    public boolean o_82_k() {
        return this.h_1847_R <= 0;
    }

    public void P_1922_E(@Nullable a_3913_L player) {
        this.h_1847_R = 600;
        if (player != null) {
            this.Q_4569_t = player.w_2705_t();
        }
        this.O_508_d.n_1700_B((N_4263_v)this, (byte)18);
    }

    public void w_1457_N(int ticks) {
        this.h_1847_R = ticks;
    }

    public int h_973_D() {
        return this.h_1847_R;
    }

    @Nullable
    public B_4088_l f_2787_O() {
        if (this.Q_4569_t == null) {
            return null;
        }
        a_3913_L playerentity = this.O_508_d.n_1700_B(this.Q_4569_t);
        return playerentity instanceof B_4088_l ? (B_4088_l)playerentity : null;
    }

    public boolean P_2295_B() {
        return this.h_1847_R > 0;
    }

    public void U_1697_c() {
        this.h_1847_R = 0;
    }

    public boolean n_1700_B(Animal otherAnimal) {
        if (otherAnimal == this) {
            return false;
        }
        if (otherAnimal.getClass() != this.getClass()) {
            return false;
        }
        return this.P_2295_B() && otherAnimal.P_2295_B();
    }

    public void n_1700_B(e_3591_l p_234177_1_, Animal p_234177_2_) {
        AgableMob ageableentity = this.n_1700_B(p_234177_1_, (AgableMob)p_234177_2_);
        if (ageableentity != null) {
            B_4088_l serverplayerentity = this.f_2787_O();
            if (serverplayerentity == null && p_234177_2_.f_2787_O() != null) {
                serverplayerentity = p_234177_2_.f_2787_O();
            }
            if (serverplayerentity != null) {
                serverplayerentity.J_1907_R(Stats.q_4610_l);
                U_3554_Q.Q_4569_t.n_1700_B(serverplayerentity, this, p_234177_2_, ageableentity);
            }
            this.b_(6000);
            p_234177_2_.b_(6000);
            this.U_1697_c();
            p_234177_2_.U_1697_c();
            ageableentity.n_1700_B(true);
            ageableentity.J_1907_R(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), 0.0f, 0.0f);
            p_234177_1_.n_1700_B((N_4263_v)ageableentity);
            p_234177_1_.n_1700_B((N_4263_v)this, (byte)18);
            if (p_234177_1_.H_1990_U().J_1907_R(A_2352_Z.P_1922_E)) {
                p_234177_1_.a_(new n_4637_L(p_234177_1_, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), this.M_3508_C().nextInt(7) + 1));
            }
        }
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 18) {
            for (int i = 0; i < 7; ++i) {
                double d0 = this.RealmsWorldOptions.nextGaussian() * 0.02;
                double d1 = this.RealmsWorldOptions.nextGaussian() * 0.02;
                double d2 = this.RealmsWorldOptions.nextGaussian() * 0.02;
                this.O_508_d.n_1700_B(ParticleTypes.e_4240_b, this.G_564_y(1.0), this.M_766_z() + 0.5, this.v_4262_N(1.0), d0, d1, d2);
            }
        } else {
            super.n_1700_B(id);
        }
    }
}


