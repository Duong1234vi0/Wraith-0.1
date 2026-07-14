/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Collection;
import lightning.product.A_2352_Z;
import lightning.product.B_1132_Q;
import lightning.product.RandomLookAroundGoal;
import lightning.product.C_4114_x;
import lightning.product.F_1241_B;
import lightning.product.Attributes;
import lightning.product.K_550_M;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.AvoidEntityGoal;
import lightning.product.FloatGoal;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.W_1200_P;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.b_4953_N;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_3591_l;
import lightning.product.f_4072_M;
import lightning.product.g_1941_L;
import lightning.product.g_3408_G;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.Monster;
import lightning.product.k_2610_C;
import lightning.product.l_3090_i;
import lightning.product.m_3054_I;
import lightning.product.q_1803_e;
import lightning.product.Items;
import lightning.product.s_1415_m;
import lightning.product.LightningBolt;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;
import lightning.product.x_1688_C;

public class b_3485_j
extends Monster
implements W_1200_P {
    private static final h_256_u<Integer> n_1700_B = C_4114_x.n_1700_B(b_3485_j.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Boolean> J_1907_R = C_4114_x.n_1700_B(b_3485_j.class, EntityDataSerializers.t_148_a);
    private static final h_256_u<Boolean> R_4764_Y = C_4114_x.n_1700_B(b_3485_j.class, EntityDataSerializers.t_148_a);
    private int h_1847_R;
    private int Q_4569_t;
    private int M_182_A = 30;
    private int t_1786_h = 3;
    private int multiplayerClientSuggestionProvider;

    public b_3485_j(t_5_h<? extends b_3485_j> type, b_4507_u worldIn) {
        super((t_5_h<? extends Monster>)type, worldIn);
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(1, new FloatGoal(this));
        this.s_956_w.n_1700_B(2, new f_4072_M(this));
        this.s_956_w.n_1700_B(3, new AvoidEntityGoal<l_3090_i>(this, l_3090_i.class, 6.0f, 1.0, 1.2));
        this.s_956_w.n_1700_B(3, new AvoidEntityGoal<K_550_M>(this, K_550_M.class, 6.0f, 1.0, 1.2));
        this.s_956_w.n_1700_B(4, new b_4953_N(this, 1.0, false));
        this.s_956_w.n_1700_B(5, new g_1941_L(this, 0.8));
        this.s_956_w.n_1700_B(6, new LookAtPlayerGoal(this, a_3913_L.class, 8.0f));
        this.s_956_w.n_1700_B(6, new RandomLookAroundGoal(this));
        this.u_2550_I.n_1700_B(1, new NearestAttackableTargetGoal<a_3913_L>((Z_530_i)this, a_3913_L.class, true));
        this.u_2550_I.n_1700_B(2, new g_3408_G(this, new Class[0]));
    }

    public static s_1415_m.n_1700_B u_1723_Y() {
        return Monster.o_4117_e().n_1700_B(Attributes.G_564_y, 0.25);
    }

    @Override
    public int n_3197_X() {
        return this.t_148_a() == null ? 3 : 3 + (int)(this.g_46_E() - 1.0f);
    }

    @Override
    public boolean R_4764_Y(float distance, float damageMultiplier) {
        boolean flag = super.R_4764_Y(distance, damageMultiplier);
        this.Q_4569_t = (int)((float)this.Q_4569_t + distance * 1.5f);
        if (this.Q_4569_t > this.M_182_A - 5) {
            this.Q_4569_t = this.M_182_A - 5;
        }
        return flag;
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(n_1700_B, -1);
        this.l_4537_E.n_1700_B(J_1907_R, false);
        this.l_4537_E.n_1700_B(R_4764_Y, false);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        if (this.l_4537_E.n_1700_B(J_1907_R).booleanValue()) {
            compound.n_1700_B("powered", true);
        }
        compound.n_1700_B("Fuse", (short)this.M_182_A);
        compound.n_1700_B("ExplosionRadius", (byte)this.t_1786_h);
        compound.n_1700_B("ignited", this.V_1176_p());
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.l_4537_E.J_1907_R(J_1907_R, compound.t_1786_h("powered"));
        if (compound.R_4764_Y("Fuse", 99)) {
            this.M_182_A = compound.v_4262_N("Fuse");
        }
        if (compound.R_4764_Y("ExplosionRadius", 99)) {
            this.t_1786_h = compound.u_1723_Y("ExplosionRadius");
        }
        if (compound.t_1786_h("ignited")) {
            this.y_2447_C();
        }
    }

    @Override
    public void v_() {
        if (this.RealmsLongRunningMcoTaskScreen()) {
            int i;
            this.h_1847_R = this.Q_4569_t;
            if (this.V_1176_p()) {
                this.n_1700_B(1);
            }
            if ((i = this.y_4642_Y()) > 0 && this.Q_4569_t == 0) {
                this.n_1700_B(SoundEvents.x_92_N, 1.0f, 0.5f);
            }
            this.Q_4569_t += i;
            if (this.Q_4569_t < 0) {
                this.Q_4569_t = 0;
            }
            if (this.Q_4569_t >= this.M_182_A) {
                this.Q_4569_t = this.M_182_A;
                this.h_973_D();
            }
        }
        super.v_();
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.d_2545_n;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.o_2767_H;
    }

    @Override
    protected void n_1700_B(P_11_z source, int looting, boolean recentlyHitIn) {
        b_3485_j creeperentity;
        super.n_1700_B(source, looting, recentlyHitIn);
        N_4263_v entity = source.u_2550_I();
        if (entity != this && entity instanceof b_3485_j && (creeperentity = (b_3485_j)entity).J_3635_s()) {
            creeperentity.o_82_k();
            this.n_1700_B((q_1803_e)Items.EndPortalBlock);
        }
    }

    @Override
    public boolean q_2307_F(N_4263_v entityIn) {
        return true;
    }

    @Override
    public boolean n_1700_B() {
        return this.l_4537_E.n_1700_B(J_1907_R);
    }

    public float c_3005_b(float partialTicks) {
        return u_530_F.v_4262_N(partialTicks, this.h_1847_R, this.Q_4569_t) / (float)(this.M_182_A - 2);
    }

    public int y_4642_Y() {
        return this.l_4537_E.n_1700_B(n_1700_B);
    }

    public void n_1700_B(int state) {
        this.l_4537_E.J_1907_R(n_1700_B, state);
    }

    @Override
    public void n_1700_B(e_3591_l p_241841_1_, LightningBolt p_241841_2_) {
        super.n_1700_B(p_241841_1_, p_241841_2_);
        this.l_4537_E.J_1907_R(J_1907_R, true);
    }

    @Override
    protected m_3054_I J_1907_R(a_3913_L p_230254_1_, x_1688_C p_230254_2_) {
        Z_1993_T itemstack = p_230254_1_.R_4764_Y(p_230254_2_);
        if (itemstack.J_1907_R() == Items.S_1165_y) {
            this.O_508_d.n_1700_B(p_230254_1_, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.U_144_f, this.r_2478_U(), 1.0f, this.RealmsWorldOptions.nextFloat() * 0.4f + 0.8f);
            if (!this.O_508_d.Y_259_p) {
                this.y_2447_C();
                itemstack.n_1700_B(1, p_230254_1_, (T player) -> player.G_564_y(p_230254_2_));
            }
            return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
        }
        return super.J_1907_R(p_230254_1_, p_230254_2_);
    }

    private void h_973_D() {
        if (!this.O_508_d.Y_259_p) {
            F_1241_B.n_1700_B explosion$mode = this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.J_1907_R) ? F_1241_B.n_1700_B.R_4764_Y : F_1241_B.n_1700_B.n_1700_B;
            float f = this.n_1700_B() ? 2.0f : 1.0f;
            this.TextRenderingUtils = true;
            this.O_508_d.n_1700_B(this, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), (float)this.t_1786_h * f, explosion$mode);
            this.Ops();
            this.f_2787_O();
        }
    }

    private void f_2787_O() {
        Collection<k_2610_C> collection = this.I_3457_f();
        if (!collection.isEmpty()) {
            B_1132_Q areaeffectcloudentity = new B_1132_Q(this.O_508_d, this.O_3598_v(), this.X_2960_b(), this.l_2647_k());
            areaeffectcloudentity.n_1700_B(2.5f);
            areaeffectcloudentity.G_564_y(-0.5f);
            areaeffectcloudentity.R_4764_Y(10);
            areaeffectcloudentity.J_1907_R(areaeffectcloudentity.t_148_a() / 2);
            areaeffectcloudentity.u_1723_Y(-areaeffectcloudentity.P_1922_E() / (float)areaeffectcloudentity.t_148_a());
            for (k_2610_C effectinstance : collection) {
                areaeffectcloudentity.n_1700_B(new k_2610_C(effectinstance));
            }
            this.O_508_d.a_(areaeffectcloudentity);
        }
    }

    public boolean V_1176_p() {
        return this.l_4537_E.n_1700_B(R_4764_Y);
    }

    public void y_2447_C() {
        this.l_4537_E.J_1907_R(R_4764_Y, true);
    }

    public boolean J_3635_s() {
        return this.n_1700_B() && this.multiplayerClientSuggestionProvider < 1;
    }

    public void o_82_k() {
        ++this.multiplayerClientSuggestionProvider;
    }
}


