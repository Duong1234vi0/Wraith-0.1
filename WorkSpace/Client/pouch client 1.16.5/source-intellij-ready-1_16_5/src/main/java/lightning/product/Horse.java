/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.C_4114_x;
import lightning.product.AgableMob;
import lightning.product.Attributes;
import lightning.product.H_4868_c;
import lightning.product.Container;
import lightning.product.Animal;
import lightning.product.DifficultyInstance;
import lightning.product.P_11_z;
import lightning.product.Q_3744_j;
import lightning.product.U_1880_G;
import lightning.product.U_2534_D;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.ServerLevelAccessor;
import lightning.product.SoundType;
import lightning.product.Donkey;
import lightning.product.e_1174_E;
import lightning.product.e_3591_l;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.j_3341_s;
import lightning.product.m_3054_I;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.v_21_F;
import lightning.product.x_1688_C;

public class Horse
extends U_2534_D {
    private static final UUID Q_2552_b = UUID.fromString("556E1665-8B10-40C8-8F9D-CF9B1667F295");
    private static final h_256_u<Integer> C_2741_M = C_4114_x.n_1700_B(Horse.class, EntityDataSerializers.J_1907_R);

    public Horse(t_5_h<? extends Horse> type, b_4507_u worldIn) {
        super((t_5_h<? extends U_2534_D>)type, worldIn);
    }

    @Override
    protected void y_4642_Y() {
        this.n_1700_B(Attributes.n_1700_B).n_1700_B(this.NumberSetting());
        this.n_1700_B(Attributes.G_564_y).n_1700_B(this.b_2037_V());
        this.n_1700_B(Attributes.P_4830_p).n_1700_B(this.O_3016_i());
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(C_2741_M, 0);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("Variant", this.V_537_k());
        if (!this.t_1786_h.s_956_w(1).n_1700_B()) {
            compound.n_1700_B("ArmorItem", this.t_1786_h.s_956_w(1).J_1907_R(new U_2912_j()));
        }
    }

    public Z_1993_T h_1640_b() {
        return this.J_1907_R(e_1174_E.P_1922_E);
    }

    private void P_4830_p(Z_1993_T p_213805_1_) {
        this.n_1700_B(e_1174_E.P_1922_E, p_213805_1_);
        this.n_1700_B(e_1174_E.P_1922_E, 0.0f);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        Z_1993_T itemstack;
        super.J_1907_R(compound);
        this.C_2741_M(compound.w_1484_f("Variant"));
        if (compound.R_4764_Y("ArmorItem", 10) && !(itemstack = Z_1993_T.n_1700_B(compound.M_182_A("ArmorItem"))).n_1700_B() && this.M_588_G(itemstack)) {
            this.t_1786_h.J_1907_R(1, itemstack);
        }
        this.Module();
    }

    private void C_2741_M(int p_234242_1_) {
        this.l_4537_E.J_1907_R(C_2741_M, p_234242_1_);
    }

    private int V_537_k() {
        return this.l_4537_E.n_1700_B(C_2741_M);
    }

    private void n_1700_B(Q_3744_j p_234238_1_, H_4868_c p_234238_2_) {
        this.C_2741_M(p_234238_1_.n_1700_B() & 0xFF | p_234238_2_.n_1700_B() << 8 & 0xFF00);
    }

    public Q_3744_j V_1176_p() {
        return Q_3744_j.n_1700_B(this.V_537_k() & 0xFF);
    }

    public H_4868_c J_3635_s() {
        return H_4868_c.n_1700_B((this.V_537_k() & 0xFF00) >> 8);
    }

    @Override
    protected void Module() {
        if (!this.O_508_d.Y_259_p) {
            super.Module();
            this.h_1847_R(this.t_1786_h.s_956_w(1));
            this.n_1700_B(e_1174_E.P_1922_E, 0.0f);
        }
    }

    private void h_1847_R(Z_1993_T p_213804_1_) {
        this.P_4830_p(p_213804_1_);
        if (!this.O_508_d.Y_259_p) {
            int i;
            this.n_1700_B(Attributes.t_148_a).J_1907_R(Q_2552_b);
            if (this.M_588_G(p_213804_1_) && (i = ((v_21_F)p_213804_1_.J_1907_R()).w_1484_f()) != 0) {
                this.n_1700_B(Attributes.t_148_a).J_1907_R(new U_1880_G(Q_2552_b, "Horse armor bonus", (double)i, U_1880_G.n_1700_B.n_1700_B));
            }
        }
    }

    @Override
    public void n_1700_B(Container invBasic) {
        Z_1993_T itemstack = this.h_1640_b();
        super.n_1700_B(invBasic);
        Z_1993_T itemstack1 = this.h_1640_b();
        if (this.RealmsWorldResetDto > 20 && this.M_588_G(itemstack1) && itemstack != itemstack1) {
            this.n_1700_B(SoundEvents.AutoTotem, 0.5f, 1.0f);
        }
    }

    @Override
    protected void n_1700_B(SoundType p_190680_1_) {
        super.n_1700_B(p_190680_1_);
        if (this.RealmsWorldOptions.nextInt(10) == 0) {
            this.n_1700_B(SoundEvents.AutoTrap, p_190680_1_.n_1700_B() * 0.6f, p_190680_1_.J_1907_R());
        }
    }

    @Override
    protected SoundEvent z_4693_k() {
        super.z_4693_k();
        return SoundEvents.AutoExplosion;
    }

    @Override
    protected SoundEvent u_796_y() {
        super.u_796_y();
        return SoundEvents.s_4054_j;
    }

    @Override
    @Nullable
    protected SoundEvent Setting() {
        return SoundEvents.HoleFill;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        super.P_1922_E(damageSourceIn);
        return SoundEvents.NoEntityTrace;
    }

    @Override
    protected SoundEvent KeyBindSetting() {
        super.KeyBindSetting();
        return SoundEvents.AutoSwap;
    }

    @Override
    public m_3054_I J_1907_R(a_3913_L p_230254_1_, x_1688_C p_230254_2_) {
        Z_1993_T itemstack = p_230254_1_.R_4764_Y(p_230254_2_);
        if (!this.d_()) {
            if (this.o_4117_e() && p_230254_1_.z_3000_g()) {
                this.u_1723_Y(p_230254_1_);
                return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
            }
            if (this.H_1883_T()) {
                return super.J_1907_R(p_230254_1_, p_230254_2_);
            }
        }
        if (!itemstack.n_1700_B()) {
            boolean flag;
            if (this.u_2550_I(itemstack)) {
                return this.J_1907_R(p_230254_1_, itemstack);
            }
            m_3054_I actionresulttype = itemstack.n_1700_B(p_230254_1_, (r_4811_B)this, p_230254_2_);
            if (actionresulttype.n_1700_B()) {
                return actionresulttype;
            }
            if (!this.o_4117_e()) {
                this.c_1608_O();
                return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
            }
            boolean bl = flag = !this.d_() && !this.G_564_y() && itemstack.J_1907_R() == Items.Z_361_l;
            if (this.M_588_G(itemstack) || flag) {
                this.u_1723_Y(p_230254_1_);
                return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
            }
        }
        if (this.d_()) {
            return super.J_1907_R(p_230254_1_, p_230254_2_);
        }
        this.v_4262_N(p_230254_1_);
        return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
    }

    @Override
    public boolean n_1700_B(Animal otherAnimal) {
        if (otherAnimal == this) {
            return false;
        }
        if (!(otherAnimal instanceof Donkey) && !(otherAnimal instanceof Horse)) {
            return false;
        }
        return this.MultiBooleanSetting() && ((U_2534_D)otherAnimal).MultiBooleanSetting();
    }

    @Override
    public AgableMob n_1700_B(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        U_2534_D abstracthorseentity;
        if (p_241840_2_ instanceof Donkey) {
            abstracthorseentity = t_5_h.T_3594_S.n_1700_B(p_241840_1_);
        } else {
            Horse horseentity = (Horse)p_241840_2_;
            abstracthorseentity = t_5_h.n_3318_d.n_1700_B(p_241840_1_);
            int i = this.RealmsWorldOptions.nextInt(9);
            Q_3744_j coatcolors = i < 4 ? this.V_1176_p() : (i < 8 ? horseentity.V_1176_p() : j_3341_s.n_1700_B(Q_3744_j.values(), this.RealmsWorldOptions));
            int j = this.RealmsWorldOptions.nextInt(5);
            H_4868_c coattypes = j < 2 ? this.J_3635_s() : (j < 4 ? horseentity.J_3635_s() : j_3341_s.n_1700_B(H_4868_c.values(), this.RealmsWorldOptions));
            ((Horse)abstracthorseentity).n_1700_B(coatcolors, coattypes);
        }
        this.n_1700_B(p_241840_2_, abstracthorseentity);
        return abstracthorseentity;
    }

    @Override
    public boolean N_4006_T() {
        return true;
    }

    @Override
    public boolean M_588_G(Z_1993_T stack) {
        return stack.J_1907_R() instanceof v_21_F;
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        Q_3744_j coatcolors;
        if (spawnDataIn instanceof n_1700_B) {
            coatcolors = ((n_1700_B)spawnDataIn).n_1700_B;
        } else {
            coatcolors = j_3341_s.n_1700_B(Q_3744_j.values(), this.RealmsWorldOptions);
            spawnDataIn = new n_1700_B(coatcolors);
        }
        this.n_1700_B(coatcolors, j_3341_s.n_1700_B(H_4868_c.values(), this.RealmsWorldOptions));
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public static class n_1700_B
    extends AgableMob.n_1700_B {
        public final Q_3744_j n_1700_B;

        public n_1700_B(Q_3744_j p_i231557_1_) {
            super(true);
            this.n_1700_B = p_i231557_1_;
        }
    }
}



