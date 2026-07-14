/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import lightning.product.A_4388_s;
import lightning.product.FluidTags;
import lightning.product.RandomLookAroundGoal;
import lightning.product.C_4114_x;
import lightning.product.F_666_T;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.J_548_T;
import lightning.product.MobEffects;
import lightning.product.L_1875_m;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_1815_U;
import lightning.product.Potions;
import lightning.product.FloatGoal;
import lightning.product.U_1880_G;
import lightning.product.SoundEvents;
import lightning.product.W_4304_a;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.NearestAttackableWitchTargetGoal;
import lightning.product.b_4507_u;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.g_1941_L;
import lightning.product.g_3408_G;
import lightning.product.RangedAttackMob;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.Monster;
import lightning.product.k_2610_C;
import lightning.product.NearestHealableRaiderTargetGoal;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;
import lightning.product.y_528_b;

public class w_3611_Y
extends W_4304_a
implements RangedAttackMob {
    private static final UUID R_4764_Y = UUID.fromString("5CD17E52-A79A-43D3-A529-90FDE04B181E");
    private static final U_1880_G h_1847_R = new U_1880_G(R_4764_Y, "Drinking speed penalty", -0.25, U_1880_G.n_1700_B.n_1700_B);
    private static final h_256_u<Boolean> Q_4569_t = C_4114_x.n_1700_B(w_3611_Y.class, EntityDataSerializers.t_148_a);
    private int M_182_A;
    private NearestHealableRaiderTargetGoal<W_4304_a> t_1786_h;
    private NearestAttackableWitchTargetGoal<a_3913_L> multiplayerClientSuggestionProvider;

    public w_3611_Y(t_5_h<? extends w_3611_Y> typeIn, b_4507_u worldIn) {
        super((t_5_h<? extends W_4304_a>)typeIn, worldIn);
    }

    @Override
    protected void M_182_A() {
        super.M_182_A();
        this.t_1786_h = new NearestHealableRaiderTargetGoal<W_4304_a>(this, W_4304_a.class, true, p_213693_1_ -> p_213693_1_ != null && this.J_3635_s() && p_213693_1_.f_4016_n() != t_5_h.RetryCallException);
        this.multiplayerClientSuggestionProvider = new NearestAttackableWitchTargetGoal<a_3913_L>(this, a_3913_L.class, 10, true, false, (Predicate<r_4811_B>)null);
        this.s_956_w.n_1700_B(1, new FloatGoal(this));
        this.s_956_w.n_1700_B(2, new J_548_T(this, 1.0, 60, 10.0f));
        this.s_956_w.n_1700_B(2, new g_1941_L(this, 1.0));
        this.s_956_w.n_1700_B(3, new LookAtPlayerGoal(this, a_3913_L.class, 8.0f));
        this.s_956_w.n_1700_B(3, new RandomLookAroundGoal(this));
        this.u_2550_I.n_1700_B(1, new g_3408_G(this, W_4304_a.class));
        this.u_2550_I.n_1700_B(2, this.t_1786_h);
        this.u_2550_I.n_1700_B(3, this.multiplayerClientSuggestionProvider);
    }

    @Override
    protected void a_() {
        super.a_();
        this.D_60_a().n_1700_B(Q_4569_t, false);
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.Z_4734_t;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.SandBlock;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.P_2605_j;
    }

    public void C_2741_M(boolean drinkingPotion) {
        this.D_60_a().J_1907_R(Q_4569_t, drinkingPotion);
    }

    public boolean u_1723_Y() {
        return this.D_60_a().n_1700_B(Q_4569_t);
    }

    public static s_1415_m.n_1700_B U_1697_c() {
        return Monster.o_4117_e().n_1700_B(Attributes.n_1700_B, 26.0).n_1700_B(Attributes.G_564_y, 0.25);
    }

    @Override
    public void Y_1740_V() {
        if (!this.O_508_d.Y_259_p && this.RealmsLongRunningMcoTaskScreen()) {
            this.t_1786_h.w_1484_f();
            if (this.t_1786_h.v_4262_N() <= 0) {
                this.multiplayerClientSuggestionProvider.n_1700_B(true);
            } else {
                this.multiplayerClientSuggestionProvider.n_1700_B(false);
            }
            if (this.u_1723_Y()) {
                if (this.M_182_A-- <= 0) {
                    List<k_2610_C> list;
                    this.C_2741_M(false);
                    Z_1993_T itemstack = this.A_2714_y();
                    this.n_1700_B(e_1174_E.n_1700_B, Z_1993_T.J_1907_R);
                    if (itemstack.J_1907_R() == Items.j_2461_G && (list = L_1875_m.n_1700_B(itemstack)) != null) {
                        for (k_2610_C effectinstance : list) {
                            this.n_1700_B(new k_2610_C(effectinstance));
                        }
                    }
                    this.n_1700_B(Attributes.G_564_y).G_564_y(h_1847_R);
                }
            } else {
                y_528_b potion = null;
                if (this.RealmsWorldOptions.nextFloat() < 0.15f && ((N_4263_v)this).n_1700_B(FluidTags.J_1907_R) && !this.J_1907_R(MobEffects.P_4830_p)) {
                    potion = Potions.k_2293_S;
                } else if (this.RealmsWorldOptions.nextFloat() < 0.15f && (this.RealmsPersistence() || this.J_4125_o() != null && this.J_4125_o().M_182_A()) && !this.J_1907_R(MobEffects.M_588_G)) {
                    potion = Potions.P_4830_p;
                } else if (this.RealmsWorldOptions.nextFloat() < 0.05f && this.g_46_E() < this.L_1733_J()) {
                    potion = Potions.Z_875_P;
                } else if (this.RealmsWorldOptions.nextFloat() < 0.5f && this.t_148_a() != null && !this.J_1907_R(MobEffects.n_1700_B) && this.t_148_a().G_564_y((N_4263_v)this) > 121.0) {
                    potion = Potions.Q_4569_t;
                }
                if (potion != null) {
                    this.n_1700_B(e_1174_E.n_1700_B, L_1875_m.n_1700_B(new Z_1993_T(Items.j_2461_G), potion));
                    this.M_182_A = this.A_2714_y().u_2550_I();
                    this.C_2741_M(true);
                    if (!this.y_1700_S()) {
                        this.O_508_d.n_1700_B((a_3913_L)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.RotatedPillarBlock, this.r_2478_U(), 1.0f, 0.8f + this.RealmsWorldOptions.nextFloat() * 0.4f);
                    }
                    A_4388_s modifiableattributeinstance = this.n_1700_B(Attributes.G_564_y);
                    modifiableattributeinstance.G_564_y(h_1847_R);
                    modifiableattributeinstance.J_1907_R(h_1847_R);
                }
            }
            if (this.RealmsWorldOptions.nextFloat() < 7.5E-4f) {
                this.O_508_d.n_1700_B((N_4263_v)this, (byte)15);
            }
        }
        super.Y_1740_V();
    }

    @Override
    public SoundEvent P_2295_B() {
        return SoundEvents.RepeaterBlock;
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 15) {
            for (int i = 0; i < this.RealmsWorldOptions.nextInt(35) + 10; ++i) {
                this.O_508_d.n_1700_B(ParticleTypes.T_3594_S, this.O_3598_v() + this.RealmsWorldOptions.nextGaussian() * (double)0.13f, this.i_601_W().maxY + 0.5 + this.RealmsWorldOptions.nextGaussian() * (double)0.13f, this.l_2647_k() + this.RealmsWorldOptions.nextGaussian() * (double)0.13f, 0.0, 0.0, 0.0);
            }
        } else {
            super.n_1700_B(id);
        }
    }

    @Override
    protected float P_1922_E(P_11_z source, float damage) {
        damage = super.P_1922_E(source, damage);
        if (source.u_2550_I() == this) {
            damage = 0.0f;
        }
        if (source.Y_601_j()) {
            damage = (float)((double)damage * 0.15);
        }
        return damage;
    }

    @Override
    public void J_1907_R(r_4811_B target, float distanceFactor) {
        if (!this.u_1723_Y()) {
            e_2866_D vector3d = target.I_4348_c();
            double d0 = target.O_3598_v() + vector3d.J_1907_R - this.O_3598_v();
            double d1 = target.X_2048_Y() - (double)1.1f - this.X_2960_b();
            double d2 = target.l_2647_k() + vector3d.G_564_y - this.l_2647_k();
            float f = u_530_F.n_1700_B(d0 * d0 + d2 * d2);
            y_528_b potion = Potions.H_2857_Y;
            if (target instanceof W_4304_a) {
                potion = target.g_46_E() <= 4.0f ? Potions.Z_875_P : Potions.e_4240_b;
                this.R_4764_Y((r_4811_B)null);
            } else if (f >= 8.0f && !target.J_1907_R(MobEffects.J_1907_R)) {
                potion = Potions.multiplayerClientSuggestionProvider;
            } else if (target.g_46_E() >= 8.0f && !target.J_1907_R(MobEffects.w_1457_N)) {
                potion = Potions.Y_1740_V;
            } else if (f <= 3.0f && !target.J_1907_R(MobEffects.multiplayerClientSuggestionProvider) && this.RealmsWorldOptions.nextFloat() < 0.25f) {
                potion = Potions.G_624_v;
            }
            F_666_T potionentity = new F_666_T(this.O_508_d, this);
            potionentity.J_1907_R(L_1875_m.n_1700_B(new Z_1993_T(Items.g_2492_v), potion));
            potionentity.f_4016_n -= -20.0f;
            potionentity.R_4764_Y(d0, d1 + (double)(f * 0.2f), d2, 0.75f, 8.0f);
            if (!this.y_1700_S()) {
                this.O_508_d.n_1700_B((a_3913_L)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.SaplingBlock, this.r_2478_U(), 1.0f, 0.8f + this.RealmsWorldOptions.nextFloat() * 0.4f);
            }
            this.O_508_d.a_(potionentity);
        }
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return 1.62f;
    }

    @Override
    public void n_1700_B(int wave, boolean p_213660_2_) {
    }

    @Override
    public boolean c_2086_l() {
        return false;
    }
}


