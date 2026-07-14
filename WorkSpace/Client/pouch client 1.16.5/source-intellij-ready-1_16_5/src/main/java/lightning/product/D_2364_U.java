/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.RandomLookAroundGoal;
import lightning.product.C_4114_x;
import lightning.product.OfferFlowerGoal;
import lightning.product.BlockGetter;
import lightning.product.G_3246_f;
import lightning.product.Attributes;
import lightning.product.Fluids;
import lightning.product.J_2548_M;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.AbstractGolem;
import lightning.product.MoveTowardsTargetGoal;
import lightning.product.ResetUniversalAngerTargetGoal;
import lightning.product.T_1316_M;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.X_426_i;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.DefendVillageTargetGoal;
import lightning.product.a_3913_L;
import lightning.product.b_3485_j;
import lightning.product.b_4507_u;
import lightning.product.b_4953_N;
import lightning.product.c_1514_x;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.TimeUtil;
import lightning.product.g_3408_G;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.m_3054_I;
import lightning.product.PathfinderMob;
import lightning.product.n_1778_y;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.u_743_i;
import lightning.product.LookAtPlayerGoal;
import lightning.product.x_1688_C;
import lightning.product.x_1835_e;
import lightning.product.MoveBackToVillageGoal;

public class D_2364_U
extends AbstractGolem
implements G_3246_f {
    protected static final h_256_u<Byte> n_1700_B = C_4114_x.n_1700_B(D_2364_U.class, EntityDataSerializers.n_1700_B);
    private int J_1907_R;
    private int R_4764_Y;
    private static final J_2548_M h_1847_R = TimeUtil.n_1700_B(20, 39);
    private int Q_4569_t;
    private UUID M_182_A;

    public D_2364_U(t_5_h<? extends D_2364_U> type, b_4507_u worldIn) {
        super((t_5_h<? extends AbstractGolem>)type, worldIn);
        this.RealmsServerPing = 1.0f;
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(1, new b_4953_N(this, 1.0, true));
        this.s_956_w.n_1700_B(2, new MoveTowardsTargetGoal(this, 0.9, 32.0f));
        this.s_956_w.n_1700_B(2, new MoveBackToVillageGoal((PathfinderMob)this, 0.6, false));
        this.s_956_w.n_1700_B(4, new n_1778_y(this, 0.6));
        this.s_956_w.n_1700_B(5, new OfferFlowerGoal(this));
        this.s_956_w.n_1700_B(7, new LookAtPlayerGoal(this, a_3913_L.class, 6.0f));
        this.s_956_w.n_1700_B(8, new RandomLookAroundGoal(this));
        this.u_2550_I.n_1700_B(1, new DefendVillageTargetGoal(this));
        this.u_2550_I.n_1700_B(2, new g_3408_G(this, new Class[0]));
        this.u_2550_I.n_1700_B(3, new NearestAttackableTargetGoal<a_3913_L>(this, a_3913_L.class, 10, true, false, this::c_));
        this.u_2550_I.n_1700_B(3, new NearestAttackableTargetGoal<Z_530_i>(this, Z_530_i.class, 5, false, false, p_234199_0_ -> p_234199_0_ instanceof x_1835_e && !(p_234199_0_ instanceof b_3485_j)));
        this.u_2550_I.n_1700_B(4, new ResetUniversalAngerTargetGoal<D_2364_U>(this, false));
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(n_1700_B, (byte)0);
    }

    public static s_1415_m.n_1700_B y_4642_Y() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.n_1700_B, 100.0).n_1700_B(Attributes.G_564_y, 0.25).n_1700_B(Attributes.R_4764_Y, 1.0).n_1700_B(Attributes.u_1723_Y, 15.0);
    }

    @Override
    protected int R_4764_Y(int air) {
        return air;
    }

    @Override
    protected void Z_875_P(N_4263_v entityIn) {
        if (entityIn instanceof x_1835_e && !(entityIn instanceof b_3485_j) && this.M_3508_C().nextInt(20) == 0) {
            this.R_4764_Y((r_4811_B)entityIn);
        }
        super.Z_875_P(entityIn);
    }

    @Override
    public void Y_1740_V() {
        int k;
        int j;
        int i;
        K_4074_S blockstate;
        super.Y_1740_V();
        if (this.J_1907_R > 0) {
            --this.J_1907_R;
        }
        if (this.R_4764_Y > 0) {
            --this.R_4764_Y;
        }
        if (D_2364_U.R_4764_Y(this.I_4348_c()) > 2.500000277905201E-7 && this.RealmsWorldOptions.nextInt(5) == 0 && !(blockstate = this.O_508_d.getBlockState(new c_1514_x(i = u_530_F.R_4764_Y(this.O_3598_v()), j = u_530_F.R_4764_Y(this.X_2960_b() - (double)0.2f), k = u_530_F.R_4764_Y(this.l_2647_k())))).v_4262_N()) {
            this.O_508_d.n_1700_B(new X_426_i(ParticleTypes.G_564_y, blockstate), this.O_3598_v() + ((double)this.RealmsWorldOptions.nextFloat() - 0.5) * (double)this.C_415_h(), this.X_2960_b() + 0.1, this.l_2647_k() + ((double)this.RealmsWorldOptions.nextFloat() - 0.5) * (double)this.C_415_h(), 4.0 * ((double)this.RealmsWorldOptions.nextFloat() - 0.5), 0.5, ((double)this.RealmsWorldOptions.nextFloat() - 0.5) * 4.0);
        }
        if (!this.O_508_d.Y_259_p) {
            this.n_1700_B((e_3591_l)this.O_508_d, true);
        }
    }

    @Override
    public boolean n_1700_B(t_5_h<?> typeIn) {
        if (this.J_3635_s() && typeIn == t_5_h.g_4106_L) {
            return false;
        }
        return typeIn == t_5_h.P_4830_p ? false : super.n_1700_B(typeIn);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("PlayerCreated", this.J_3635_s());
        this.a_(compound);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.Y_601_j(compound.t_1786_h("PlayerCreated"));
        this.n_1700_B((e_3591_l)this.O_508_d, compound);
    }

    @Override
    public void P_1922_E() {
        this.n_1700_B(h_1847_R.n_1700_B(this.RealmsWorldOptions));
    }

    @Override
    public void n_1700_B(int time) {
        this.Q_4569_t = time;
    }

    @Override
    public int n_1700_B() {
        return this.Q_4569_t;
    }

    @Override
    public void n_1700_B(@Nullable UUID target) {
        this.M_182_A = target;
    }

    @Override
    public UUID G_564_y() {
        return this.M_182_A;
    }

    private float o_82_k() {
        return (float)this.J_1907_R(Attributes.u_1723_Y);
    }

    @Override
    public boolean q_2307_F(N_4263_v entityIn) {
        this.J_1907_R = 10;
        this.O_508_d.n_1700_B((N_4263_v)this, (byte)4);
        float f = this.o_82_k();
        float f1 = (int)f > 0 ? f / 2.0f + (float)this.RealmsWorldOptions.nextInt((int)f) : f;
        boolean flag = entityIn.n_1700_B(P_11_z.R_4764_Y(this), f1);
        if (flag) {
            entityIn.v_4262_N(entityIn.I_4348_c().J_1907_R(0.0, 0.4f, 0.0));
            this.n_1700_B((r_4811_B)this, entityIn);
        }
        this.n_1700_B(SoundEvents.t_4864_b, 1.0f, 1.0f);
        return flag;
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        n_1700_B irongolementity$cracks = this.h_1640_b();
        boolean flag = super.n_1700_B(source, amount);
        if (flag && this.h_1640_b() != irongolementity$cracks) {
            this.n_1700_B(SoundEvents.u_1980_X, 1.0f, 1.0f);
        }
        return flag;
    }

    public n_1700_B h_1640_b() {
        return lightning.product.D_2364_U$n_1700_B.n_1700_B(this.g_46_E() / this.L_1733_J());
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 4) {
            this.J_1907_R = 10;
            this.n_1700_B(SoundEvents.t_4864_b, 1.0f, 1.0f);
        } else if (id == 11) {
            this.R_4764_Y = 400;
        } else if (id == 34) {
            this.R_4764_Y = 0;
        } else {
            super.n_1700_B(id);
        }
    }

    public int V_1176_p() {
        return this.J_1907_R;
    }

    public void w_1457_N(boolean holdingRose) {
        if (holdingRose) {
            this.R_4764_Y = 400;
            this.O_508_d.n_1700_B((N_4263_v)this, (byte)11);
        } else {
            this.R_4764_Y = 0;
            this.O_508_d.n_1700_B((N_4263_v)this, (byte)34);
        }
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.M_4609_z;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.T_33_Q;
    }

    @Override
    protected m_3054_I J_1907_R(a_3913_L p_230254_1_, x_1688_C p_230254_2_) {
        Z_1993_T itemstack = p_230254_1_.R_4764_Y(p_230254_2_);
        q_1613_l item = itemstack.J_1907_R();
        if (item != Items.D_1621_L) {
            return m_3054_I.R_4764_Y;
        }
        float f = this.g_46_E();
        this.n_1700_B(25.0f);
        if (this.g_46_E() == f) {
            return m_3054_I.R_4764_Y;
        }
        float f1 = 1.0f + (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f;
        this.n_1700_B(SoundEvents.AuctionHelper, 1.0f, f1);
        if (!p_230254_1_.C_415_h.G_564_y) {
            itemstack.v_4262_N(1);
        }
        return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
        this.n_1700_B(SoundEvents.AutoAccept, 1.0f, 1.0f);
    }

    public int y_2447_C() {
        return this.R_4764_Y;
    }

    public boolean J_3635_s() {
        return (this.l_4537_E.n_1700_B(n_1700_B) & 1) != 0;
    }

    public void Y_601_j(boolean playerCreated) {
        byte b0 = this.l_4537_E.n_1700_B(n_1700_B);
        if (playerCreated) {
            this.l_4537_E.J_1907_R(n_1700_B, (byte)(b0 | 1));
        } else {
            this.l_4537_E.J_1907_R(n_1700_B, (byte)(b0 & 0xFFFFFFFE));
        }
    }

    @Override
    public void R_4764_Y(P_11_z cause) {
        super.R_4764_Y(cause);
    }

    @Override
    public boolean n_1700_B(T_1316_M worldIn) {
        c_1514_x blockpos = this.b_2312_j();
        c_1514_x blockpos1 = blockpos.down();
        K_4074_S blockstate = worldIn.getBlockState(blockpos1);
        if (!blockstate.n_1700_B((BlockGetter)worldIn, blockpos1, (N_4263_v)this)) {
            return false;
        }
        for (int i = 1; i < 3; ++i) {
            K_4074_S blockstate1;
            c_1514_x blockpos2 = blockpos.up(i);
            if (u_743_i.n_1700_B(worldIn, blockpos2, blockstate1 = worldIn.getBlockState(blockpos2), blockstate1.P_4830_p(), t_5_h.v_4276_D)) continue;
            return false;
        }
        return u_743_i.n_1700_B(worldIn, blockpos, worldIn.getBlockState(blockpos), Fluids.n_1700_B.w_1484_f(), t_5_h.v_4276_D) && worldIn.P_1922_E(this);
    }

    @Override
    public e_2866_D x_4991_F() {
        return new e_2866_D(0.0, 0.875f * this.X_1313_W(), this.C_415_h() * 0.4f);
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B(1.0f);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B(0.75f);
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B(0.5f);
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B(0.25f);
        private static final List<n_1700_B> P_1922_E;
        private final float u_1723_Y;
        private static final /* synthetic */ n_1700_B[] v_4262_N;

        public static n_1700_B[] values() {
            return (n_1700_B[])v_4262_N.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(float p_i225732_3_) {
            this.u_1723_Y = p_i225732_3_;
        }

        public static n_1700_B n_1700_B(float p_226515_0_) {
            for (n_1700_B irongolementity$cracks : P_1922_E) {
                if (!(p_226515_0_ < irongolementity$cracks.u_1723_Y)) continue;
                return irongolementity$cracks;
            }
            return n_1700_B;
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            v_4262_N = lightning.product.D_2364_U$n_1700_B.n_1700_B();
            P_1922_E = (List)Stream.of(lightning.product.D_2364_U$n_1700_B.values()).sorted(Comparator.comparingDouble(p_226516_0_ -> p_226516_0_.u_1723_Y)).collect(ImmutableList.toImmutableList());
        }
    }
}



