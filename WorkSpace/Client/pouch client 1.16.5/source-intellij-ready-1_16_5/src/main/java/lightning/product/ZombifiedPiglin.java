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
import lightning.product.A_4388_s;
import lightning.product.F_4355_q;
import lightning.product.G_3246_f;
import lightning.product.Attributes;
import lightning.product.I_1869_h;
import lightning.product.I_4817_s;
import lightning.product.J_2548_M;
import lightning.product.DifficultyInstance;
import lightning.product.P_11_z;
import lightning.product.ZombieAttackGoal;
import lightning.product.ResetUniversalAngerTargetGoal;
import lightning.product.R_2450_T;
import lightning.product.T_1316_M;
import lightning.product.U_1880_G;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_1174_E;
import lightning.product.e_3591_l;
import lightning.product.TimeUtil;
import lightning.product.g_1941_L;
import lightning.product.g_3408_G;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;

public class ZombifiedPiglin
extends F_4355_q
implements G_3246_f {
    private static final UUID n_1700_B = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
    private static final U_1880_G J_1907_R = new U_1880_G(n_1700_B, "Attacking speed boost", 0.05, U_1880_G.n_1700_B.n_1700_B);
    private static final J_2548_M R_4764_Y = TimeUtil.n_1700_B(0, 1);
    private int h_1847_R;
    private static final J_2548_M Q_4569_t = TimeUtil.n_1700_B(20, 39);
    private int M_182_A;
    private UUID t_1786_h;
    private static final J_2548_M multiplayerClientSuggestionProvider = TimeUtil.n_1700_B(4, 6);
    private int w_1457_N;

    public ZombifiedPiglin(t_5_h<? extends ZombifiedPiglin> p_i231568_1_, b_4507_u p_i231568_2_) {
        super((t_5_h<? extends F_4355_q>)p_i231568_1_, p_i231568_2_);
        this.n_1700_B(I_1869_h.v_4262_N, 8.0f);
    }

    @Override
    public void n_1700_B(@Nullable UUID target) {
        this.t_1786_h = target;
    }

    @Override
    public double O_2151_c() {
        return this.d_() ? -0.05 : -0.45;
    }

    @Override
    protected void u_1723_Y() {
        this.s_956_w.n_1700_B(2, new ZombieAttackGoal(this, 1.0, false));
        this.s_956_w.n_1700_B(7, new g_1941_L(this, 1.0));
        this.u_2550_I.n_1700_B(1, new g_3408_G(this, new Class[0]).n_1700_B(new Class[0]));
        this.u_2550_I.n_1700_B(2, new NearestAttackableTargetGoal<a_3913_L>(this, a_3913_L.class, 10, true, false, this::c_));
        this.u_2550_I.n_1700_B(3, new ResetUniversalAngerTargetGoal<ZombifiedPiglin>(this, true));
    }

    public static s_1415_m.n_1700_B c_2086_l() {
        return F_4355_q.f_2787_O().n_1700_B(Attributes.M_588_G, 0.0).n_1700_B(Attributes.G_564_y, 0.23f).n_1700_B(Attributes.u_1723_Y, 5.0);
    }

    @Override
    protected boolean J_3635_s() {
        return false;
    }

    @Override
    protected void X_933_l() {
        A_4388_s modifiableattributeinstance = this.n_1700_B(Attributes.G_564_y);
        if (this.B_()) {
            if (!this.d_() && !modifiableattributeinstance.n_1700_B(J_1907_R)) {
                modifiableattributeinstance.J_1907_R(J_1907_R);
            }
            this.U_3758_B();
        } else if (modifiableattributeinstance.n_1700_B(J_1907_R)) {
            modifiableattributeinstance.G_564_y(J_1907_R);
        }
        this.n_1700_B((e_3591_l)this.O_508_d, true);
        if (this.t_148_a() != null) {
            this.y_3417_N();
        }
        if (this.B_()) {
            this.d_4007_L = this.RealmsWorldResetDto;
        }
        super.X_933_l();
    }

    private void U_3758_B() {
        if (this.h_1847_R > 0) {
            --this.h_1847_R;
            if (this.h_1847_R == 0) {
                this.D_3612_q();
            }
        }
    }

    private void y_3417_N() {
        if (this.w_1457_N > 0) {
            --this.w_1457_N;
        } else {
            if (this.n_3318_d().n_1700_B(this.t_148_a())) {
                this.A_1306_N();
            }
            this.w_1457_N = multiplayerClientSuggestionProvider.n_1700_B(this.RealmsWorldOptions);
        }
    }

    private void A_1306_N() {
        double d0 = this.J_1907_R(Attributes.J_1907_R);
        I_4817_s axisalignedbb = I_4817_s.fromVector(this.s_4990_V()).grow(d0, 10.0, d0);
        this.O_508_d.J_1907_R(ZombifiedPiglin.class, axisalignedbb).stream().filter(p_241408_1_ -> p_241408_1_ != this).filter(p_241407_0_ -> p_241407_0_.t_148_a() == null).filter(p_241406_1_ -> !p_241406_1_.Q_4569_t(this.t_148_a())).forEach(p_241405_1_ -> p_241405_1_.R_4764_Y(this.t_148_a()));
    }

    private void D_3612_q() {
        this.n_1700_B(SoundEvents.WeightedPressurePlateBlock, this.d_4500_Q() * 2.0f, this.O_2761_o() * 1.8f);
    }

    @Override
    public void R_4764_Y(@Nullable r_4811_B entitylivingbaseIn) {
        if (this.t_148_a() == null && entitylivingbaseIn != null) {
            this.h_1847_R = R_4764_Y.n_1700_B(this.RealmsWorldOptions);
            this.w_1457_N = multiplayerClientSuggestionProvider.n_1700_B(this.RealmsWorldOptions);
        }
        if (entitylivingbaseIn instanceof a_3913_L) {
            this.J_1907_R((a_3913_L)entitylivingbaseIn);
        }
        super.R_4764_Y(entitylivingbaseIn);
    }

    @Override
    public void P_1922_E() {
        this.n_1700_B(Q_4569_t.n_1700_B(this.RealmsWorldOptions));
    }

    public static boolean J_1907_R(t_5_h<ZombifiedPiglin> p_234351_0_, LevelAccessor p_234351_1_, a_3160_D p_234351_2_, c_1514_x p_234351_3_, Random p_234351_4_) {
        return p_234351_1_.x_607_J() != R_2450_T.n_1700_B && p_234351_1_.getBlockState(p_234351_3_.down()).J_1907_R() != a_3742_W.LockSlot;
    }

    @Override
    public boolean n_1700_B(T_1316_M worldIn) {
        return worldIn.P_1922_E(this) && !worldIn.G_564_y(this.i_601_W());
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        this.a_(compound);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.n_1700_B((e_3591_l)this.O_508_d, compound);
    }

    @Override
    public void n_1700_B(int time) {
        this.M_182_A = time;
    }

    @Override
    public int n_1700_B() {
        return this.M_182_A;
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        return this.n_1700_B(source) ? false : super.n_1700_B(source, amount);
    }

    @Override
    protected SoundEvent z_4693_k() {
        return this.B_() ? SoundEvents.WeightedPressurePlateBlock : SoundEvents.d_3251_B;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.WitherRoseBlock;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.n_290_G;
    }

    @Override
    protected void n_1700_B(DifficultyInstance difficulty) {
        this.n_1700_B(e_1174_E.n_1700_B, new Z_1993_T(Items.n_2412_y));
    }

    @Override
    protected Z_1993_T y_2447_C() {
        return Z_1993_T.J_1907_R;
    }

    @Override
    protected void V_537_k() {
        this.n_1700_B(Attributes.M_588_G).n_1700_B(0.0);
    }

    @Override
    public UUID G_564_y() {
        return this.t_1786_h;
    }

    @Override
    public boolean P_1922_E(a_3913_L p_230292_1_) {
        return this.c_((r_4811_B)p_230292_1_);
    }
}



