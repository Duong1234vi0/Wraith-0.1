/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.RandomLookAroundGoal;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.K_4074_S;
import lightning.product.P_11_z;
import lightning.product.R_1815_U;
import lightning.product.FloatGoal;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.b_4953_N;
import lightning.product.c_1514_x;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.g_1941_L;
import lightning.product.MobType;
import lightning.product.g_3408_G;
import lightning.product.Monster;
import lightning.product.s_1415_m;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;
import lightning.product.LookAtPlayerGoal;

public class Endermite
extends Monster {
    private int n_1700_B;
    private boolean J_1907_R;

    public Endermite(t_5_h<? extends Endermite> type, b_4507_u worldIn) {
        super((t_5_h<? extends Monster>)type, worldIn);
        this.P_1922_E = 3;
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(1, new FloatGoal(this));
        this.s_956_w.n_1700_B(2, new b_4953_N(this, 1.0, false));
        this.s_956_w.n_1700_B(3, new g_1941_L(this, 1.0));
        this.s_956_w.n_1700_B(7, new LookAtPlayerGoal(this, a_3913_L.class, 8.0f));
        this.s_956_w.n_1700_B(8, new RandomLookAroundGoal(this));
        this.u_2550_I.n_1700_B(1, new g_3408_G(this, new Class[0]).n_1700_B(new Class[0]));
        this.u_2550_I.n_1700_B(2, new NearestAttackableTargetGoal<a_3913_L>((Z_530_i)this, a_3913_L.class, true));
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return 0.13f;
    }

    public static s_1415_m.n_1700_B u_1723_Y() {
        return Monster.o_4117_e().n_1700_B(Attributes.n_1700_B, 8.0).n_1700_B(Attributes.G_564_y, 0.25).n_1700_B(Attributes.u_1723_Y, 2.0);
    }

    @Override
    protected boolean RetryCallException() {
        return false;
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.b_2625_m;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.u_796_y;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.q_3401_q;
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
        this.n_1700_B(SoundEvents.Z_4720_K, 0.15f, 1.0f);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.n_1700_B = compound.w_1484_f("Lifetime");
        this.J_1907_R = compound.t_1786_h("PlayerSpawned");
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("Lifetime", this.n_1700_B);
        compound.n_1700_B("PlayerSpawned", this.J_1907_R);
    }

    @Override
    public void v_() {
        this.C_1162_e = this.p_178_J;
        super.v_();
    }

    @Override
    public void Q_4569_t(float offset) {
        this.p_178_J = offset;
        super.Q_4569_t(offset);
    }

    @Override
    public double O_2151_c() {
        return 0.1;
    }

    public boolean y_4642_Y() {
        return this.J_1907_R;
    }

    public void w_1457_N(boolean spawnedByPlayer) {
        this.J_1907_R = spawnedByPlayer;
    }

    @Override
    public void Y_1740_V() {
        super.Y_1740_V();
        if (this.O_508_d.Y_259_p) {
            for (int i = 0; i < 2; ++i) {
                this.O_508_d.n_1700_B(ParticleTypes.g_221_o, this.G_564_y(0.5), this.M_766_z(), this.v_4262_N(0.5), (this.RealmsWorldOptions.nextDouble() - 0.5) * 2.0, -this.RealmsWorldOptions.nextDouble(), (this.RealmsWorldOptions.nextDouble() - 0.5) * 2.0);
            }
        } else {
            if (!this.s_2632_s()) {
                ++this.n_1700_B;
            }
            if (this.n_1700_B >= 2400) {
                this.Ops();
            }
        }
    }

    public static boolean J_1907_R(t_5_h<Endermite> p_223328_0_, LevelAccessor p_223328_1_, a_3160_D reason, c_1514_x p_223328_3_, Random p_223328_4_) {
        if (Endermite.R_4764_Y(p_223328_0_, p_223328_1_, reason, p_223328_3_, p_223328_4_)) {
            a_3913_L playerentity = p_223328_1_.n_1700_B((double)p_223328_3_.getX() + 0.5, (double)p_223328_3_.getY() + 0.5, (double)p_223328_3_.getZ() + 0.5, 5.0, true);
            return playerentity == null;
        }
        return false;
    }

    @Override
    public MobType F_2860_q() {
        return MobType.R_4764_Y;
    }
}


