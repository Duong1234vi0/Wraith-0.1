/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.C_4114_x;
import lightning.product.D_38_f;
import lightning.product.FlyingMob;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.LookControl;
import lightning.product.I_408_V;
import lightning.product.K_550_M;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_1815_U;
import lightning.product.MoveControl;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.BodyRotationControl;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.e_2866_D;
import lightning.product.TargetingConditions;
import lightning.product.MobType;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.Goal;
import lightning.product.r_4811_B;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.x_1835_e;
import lightning.product.z_2963_s;

public class m_3937_C
extends FlyingMob
implements x_1835_e {
    private static final h_256_u<Integer> n_1700_B = C_4114_x.n_1700_B(m_3937_C.class, EntityDataSerializers.J_1907_R);
    private e_2866_D J_1907_R = e_2866_D.n_1700_B;
    private c_1514_x R_4764_Y = c_1514_x.ZERO;
    private n_1700_B h_1847_R = lightning.product.m_3937_C$n_1700_B.n_1700_B;

    public m_3937_C(t_5_h<? extends m_3937_C> type, b_4507_u worldIn) {
        super((t_5_h<? extends FlyingMob>)type, worldIn);
        this.P_1922_E = 5;
        this.v_4262_N = new u_1723_Y(this);
        this.u_1723_Y = new G_564_y(this, this);
    }

    @Override
    protected BodyRotationControl k_2293_S() {
        return new R_4764_Y(this);
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(1, new w_1484_f());
        this.s_956_w.n_1700_B(2, new t_148_a());
        this.s_956_w.n_1700_B(3, new v_4262_N());
        this.u_2550_I.n_1700_B(1, new J_1907_R());
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(n_1700_B, 0);
    }

    public void n_1700_B(int sizeIn) {
        this.l_4537_E.J_1907_R(n_1700_B, u_530_F.n_1700_B(sizeIn, 0, 64));
    }

    private void w_1484_f() {
        this.g_();
        this.n_1700_B(Attributes.u_1723_Y).n_1700_B(6 + this.u_1723_Y());
    }

    public int u_1723_Y() {
        return this.l_4537_E.n_1700_B(n_1700_B);
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return sizeIn.J_1907_R * 0.35f;
    }

    @Override
    public void n_1700_B(h_256_u<?> key) {
        if (n_1700_B.equals(key)) {
            this.w_1484_f();
        }
        super.n_1700_B(key);
    }

    @Override
    protected boolean B_1668_F() {
        return true;
    }

    @Override
    public void v_() {
        super.v_();
        if (this.O_508_d.Y_259_p) {
            float f = u_530_F.J_1907_R((float)(this.j_276_v() * 3 + this.RealmsWorldResetDto) * 0.13f + (float)Math.PI);
            float f1 = u_530_F.J_1907_R((float)(this.j_276_v() * 3 + this.RealmsWorldResetDto + 1) * 0.13f + (float)Math.PI);
            if (f > 0.0f && f1 <= 0.0f) {
                this.O_508_d.n_1700_B(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.K_1200_E, this.r_2478_U(), 0.95f + this.RealmsWorldOptions.nextFloat() * 0.05f, 0.95f + this.RealmsWorldOptions.nextFloat() * 0.05f, false);
            }
            int i = this.u_1723_Y();
            float f2 = u_530_F.J_1907_R(this.p_178_J * ((float)Math.PI / 180)) * (1.3f + 0.21f * (float)i);
            float f3 = u_530_F.n_1700_B(this.p_178_J * ((float)Math.PI / 180)) * (1.3f + 0.21f * (float)i);
            float f4 = (0.3f + f * 0.45f) * ((float)i * 0.2f + 1.0f);
            this.O_508_d.n_1700_B(ParticleTypes.T_2506_i, this.O_3598_v() + (double)f2, this.X_2960_b() + (double)f4, this.l_2647_k() + (double)f3, 0.0, 0.0, 0.0);
            this.O_508_d.n_1700_B(ParticleTypes.T_2506_i, this.O_3598_v() - (double)f2, this.X_2960_b() + (double)f4, this.l_2647_k() - (double)f3, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public void Y_1740_V() {
        if (this.RealmsLongRunningMcoTaskScreen() && this.S_2828_i()) {
            this.P_1922_E(8);
        }
        super.Y_1740_V();
    }

    @Override
    protected void X_933_l() {
        super.X_933_l();
    }

    @Override
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        this.R_4764_Y = this.b_2312_j().up(5);
        this.n_1700_B(0);
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        if (compound.P_1922_E("AX")) {
            this.R_4764_Y = new c_1514_x(compound.w_1484_f("AX"), compound.w_1484_f("AY"), compound.w_1484_f("AZ"));
        }
        this.n_1700_B(compound.w_1484_f("Size"));
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("AX", this.R_4764_Y.getX());
        compound.J_1907_R("AY", this.R_4764_Y.getY());
        compound.J_1907_R("AZ", this.R_4764_Y.getZ());
        compound.J_1907_R("Size", this.u_1723_Y());
    }

    @Override
    public boolean n_1700_B(double distance) {
        return true;
    }

    @Override
    public D_38_f r_2478_U() {
        return D_38_f.u_1723_Y;
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.C_1577_A;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.D_563_q;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.q_3148_R;
    }

    @Override
    public MobType F_2860_q() {
        return MobType.J_1907_R;
    }

    @Override
    protected float d_4500_Q() {
        return 1.0f;
    }

    @Override
    public boolean n_1700_B(t_5_h<?> typeIn) {
        return true;
    }

    @Override
    public R_1815_U n_1700_B(I_1170_F poseIn) {
        int i = this.u_1723_Y();
        R_1815_U entitysize = super.n_1700_B(poseIn);
        float f = (entitysize.n_1700_B + 0.2f * (float)i) / entitysize.n_1700_B;
        return entitysize.n_1700_B(f);
    }

    static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] R_4764_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])R_4764_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            R_4764_Y = lightning.product.m_3937_C$n_1700_B.n_1700_B();
        }
    }

    class u_1723_Y
    extends MoveControl {
        private float s_956_w;

        public u_1723_Y(Z_530_i entityIn) {
            super(entityIn);
            this.s_956_w = 0.1f;
        }

        @Override
        public void n_1700_B() {
            float f7;
            if (m_3937_C.this.D_60_a) {
                m_3937_C.this.p_178_J += 180.0f;
                this.s_956_w = 0.1f;
            }
            float f = (float)(m_3937_C.this.J_1907_R.J_1907_R - m_3937_C.this.O_3598_v());
            float f1 = (float)(m_3937_C.this.J_1907_R.R_4764_Y - m_3937_C.this.X_2960_b());
            float f2 = (float)(m_3937_C.this.J_1907_R.G_564_y - m_3937_C.this.l_2647_k());
            double d0 = u_530_F.R_4764_Y(f * f + f2 * f2);
            double d1 = 1.0 - (double)u_530_F.P_1922_E(f1 * 0.7f) / d0;
            f = (float)((double)f * d1);
            f2 = (float)((double)f2 * d1);
            d0 = u_530_F.R_4764_Y(f * f + f2 * f2);
            double d2 = u_530_F.R_4764_Y(f * f + f2 * f2 + f1 * f1);
            float f3 = m_3937_C.this.p_178_J;
            float f4 = (float)u_530_F.G_564_y((double)f2, (double)f);
            float f5 = u_530_F.v_4262_N(m_3937_C.this.p_178_J + 90.0f);
            float f6 = u_530_F.v_4262_N(f4 * 57.295776f);
            m_3937_C.this.C_1162_e = m_3937_C.this.p_178_J = u_530_F.G_564_y(f5, f6, 4.0f) - 90.0f;
            this.s_956_w = u_530_F.G_564_y(f3, m_3937_C.this.p_178_J) < 3.0f ? u_530_F.R_4764_Y(this.s_956_w, 1.8f, 0.005f * (1.8f / this.s_956_w)) : u_530_F.R_4764_Y(this.s_956_w, 0.2f, 0.025f);
            m_3937_C.this.f_4016_n = f7 = (float)(-(u_530_F.G_564_y((double)(-f1), d0) * 57.2957763671875));
            float f8 = m_3937_C.this.p_178_J + 90.0f;
            double d3 = (double)(this.s_956_w * u_530_F.J_1907_R(f8 * ((float)Math.PI / 180))) * Math.abs((double)f / d2);
            double d4 = (double)(this.s_956_w * u_530_F.n_1700_B(f8 * ((float)Math.PI / 180))) * Math.abs((double)f2 / d2);
            double d5 = (double)(this.s_956_w * u_530_F.n_1700_B(f7 * ((float)Math.PI / 180))) * Math.abs((double)f1 / d2);
            e_2866_D vector3d = m_3937_C.this.I_4348_c();
            m_3937_C.this.v_4262_N(vector3d.P_1922_E(new e_2866_D(d3, d5, d4).G_564_y(vector3d).n_1700_B(0.2)));
        }
    }

    class G_564_y
    extends LookControl {
        public G_564_y(m_3937_C this$0, Z_530_i entityIn) {
            super(entityIn);
        }

        @Override
        public void n_1700_B() {
        }
    }

    class R_4764_Y
    extends BodyRotationControl {
        public R_4764_Y(Z_530_i mob) {
            super(mob);
        }

        @Override
        public void n_1700_B() {
            m_3937_C.this.f_3449_S = m_3937_C.this.C_1162_e;
            m_3937_C.this.C_1162_e = m_3937_C.this.p_178_J;
        }
    }

    class w_1484_f
    extends Goal {
        private int J_1907_R;

        private w_1484_f() {
        }

        @Override
        public boolean n_1700_B() {
            r_4811_B livingentity = m_3937_C.this.t_148_a();
            return livingentity != null ? m_3937_C.this.n_1700_B(m_3937_C.this.t_148_a(), TargetingConditions.n_1700_B) : false;
        }

        @Override
        public void R_4764_Y() {
            this.J_1907_R = 10;
            m_3937_C.this.h_1847_R = lightning.product.m_3937_C$n_1700_B.n_1700_B;
            this.v_4262_N();
        }

        @Override
        public void G_564_y() {
            m_3937_C.this.R_4764_Y = m_3937_C.this.O_508_d.n_1700_B(z_2963_s.n_1700_B.P_1922_E, m_3937_C.this.R_4764_Y).up(10 + m_3937_C.this.RealmsWorldOptions.nextInt(20));
        }

        @Override
        public void P_1922_E() {
            if (m_3937_C.this.h_1847_R == lightning.product.m_3937_C$n_1700_B.n_1700_B) {
                --this.J_1907_R;
                if (this.J_1907_R <= 0) {
                    m_3937_C.this.h_1847_R = lightning.product.m_3937_C$n_1700_B.J_1907_R;
                    this.v_4262_N();
                    this.J_1907_R = (8 + m_3937_C.this.RealmsWorldOptions.nextInt(4)) * 20;
                    m_3937_C.this.n_1700_B(SoundEvents.K_1964_I, 10.0f, 0.95f + m_3937_C.this.RealmsWorldOptions.nextFloat() * 0.1f);
                }
            }
        }

        private void v_4262_N() {
            m_3937_C.this.R_4764_Y = m_3937_C.this.t_148_a().b_2312_j().up(20 + m_3937_C.this.RealmsWorldOptions.nextInt(20));
            if (m_3937_C.this.R_4764_Y.getY() < m_3937_C.this.O_508_d.d_2461_k()) {
                m_3937_C.this.R_4764_Y = new c_1514_x(m_3937_C.this.R_4764_Y.getX(), m_3937_C.this.O_508_d.d_2461_k() + 1, m_3937_C.this.R_4764_Y.getZ());
            }
        }
    }

    class t_148_a
    extends P_1922_E {
        private t_148_a() {
        }

        @Override
        public boolean n_1700_B() {
            return m_3937_C.this.t_148_a() != null && m_3937_C.this.h_1847_R == lightning.product.m_3937_C$n_1700_B.J_1907_R;
        }

        @Override
        public boolean J_1907_R() {
            r_4811_B livingentity = m_3937_C.this.t_148_a();
            if (livingentity == null) {
                return false;
            }
            if (!livingentity.RealmsLongRunningMcoTaskScreen()) {
                return false;
            }
            if (!(livingentity instanceof a_3913_L) || !((a_3913_L)livingentity).d_2461_k() && !((a_3913_L)livingentity).G_624_v()) {
                List<N_4263_v> list;
                if (!this.n_1700_B()) {
                    return false;
                }
                if (m_3937_C.this.RealmsWorldResetDto % 20 == 0 && !(list = m_3937_C.this.O_508_d.n_1700_B(K_550_M.class, m_3937_C.this.i_601_W().grow(16.0), I_408_V.n_1700_B)).isEmpty()) {
                    for (K_550_M k_550_M : list) {
                        k_550_M.V_537_k();
                    }
                    return false;
                }
                return true;
            }
            return false;
        }

        @Override
        public void R_4764_Y() {
        }

        @Override
        public void G_564_y() {
            m_3937_C.this.R_4764_Y((r_4811_B)null);
            m_3937_C.this.h_1847_R = lightning.product.m_3937_C$n_1700_B.n_1700_B;
        }

        @Override
        public void P_1922_E() {
            r_4811_B livingentity = m_3937_C.this.t_148_a();
            m_3937_C.this.J_1907_R = new e_2866_D(livingentity.O_3598_v(), livingentity.P_1922_E(0.5), livingentity.l_2647_k());
            if (m_3937_C.this.i_601_W().grow(0.2f).intersects(livingentity.i_601_W())) {
                m_3937_C.this.q_2307_F(livingentity);
                m_3937_C.this.h_1847_R = lightning.product.m_3937_C$n_1700_B.n_1700_B;
                if (!m_3937_C.this.y_1700_S()) {
                    m_3937_C.this.O_508_d.R_4764_Y(1039, m_3937_C.this.b_2312_j(), 0);
                }
            } else if (m_3937_C.this.D_60_a || m_3937_C.this.RealmsLongRunningMcoTaskScreen > 0) {
                m_3937_C.this.h_1847_R = lightning.product.m_3937_C$n_1700_B.n_1700_B;
            }
        }
    }

    class v_4262_N
    extends P_1922_E {
        private float R_4764_Y;
        private float G_564_y;
        private float P_1922_E;
        private float u_1723_Y;

        private v_4262_N() {
        }

        @Override
        public boolean n_1700_B() {
            return m_3937_C.this.t_148_a() == null || m_3937_C.this.h_1847_R == lightning.product.m_3937_C$n_1700_B.n_1700_B;
        }

        @Override
        public void R_4764_Y() {
            this.G_564_y = 5.0f + m_3937_C.this.RealmsWorldOptions.nextFloat() * 10.0f;
            this.P_1922_E = -4.0f + m_3937_C.this.RealmsWorldOptions.nextFloat() * 9.0f;
            this.u_1723_Y = m_3937_C.this.RealmsWorldOptions.nextBoolean() ? 1.0f : -1.0f;
            this.w_1484_f();
        }

        @Override
        public void P_1922_E() {
            if (m_3937_C.this.RealmsWorldOptions.nextInt(350) == 0) {
                this.P_1922_E = -4.0f + m_3937_C.this.RealmsWorldOptions.nextFloat() * 9.0f;
            }
            if (m_3937_C.this.RealmsWorldOptions.nextInt(250) == 0) {
                this.G_564_y += 1.0f;
                if (this.G_564_y > 15.0f) {
                    this.G_564_y = 5.0f;
                    this.u_1723_Y = -this.u_1723_Y;
                }
            }
            if (m_3937_C.this.RealmsWorldOptions.nextInt(450) == 0) {
                this.R_4764_Y = m_3937_C.this.RealmsWorldOptions.nextFloat() * 2.0f * (float)Math.PI;
                this.w_1484_f();
            }
            if (this.v_4262_N()) {
                this.w_1484_f();
            }
            if (m_3937_C.this.J_1907_R.R_4764_Y < m_3937_C.this.X_2960_b() && !m_3937_C.this.O_508_d.u_1723_Y(m_3937_C.this.b_2312_j().down(1))) {
                this.P_1922_E = Math.max(1.0f, this.P_1922_E);
                this.w_1484_f();
            }
            if (m_3937_C.this.J_1907_R.R_4764_Y > m_3937_C.this.X_2960_b() && !m_3937_C.this.O_508_d.u_1723_Y(m_3937_C.this.b_2312_j().up(1))) {
                this.P_1922_E = Math.min(-1.0f, this.P_1922_E);
                this.w_1484_f();
            }
        }

        private void w_1484_f() {
            if (c_1514_x.ZERO.equals(m_3937_C.this.R_4764_Y)) {
                m_3937_C.this.R_4764_Y = m_3937_C.this.b_2312_j();
            }
            this.R_4764_Y += this.u_1723_Y * 15.0f * ((float)Math.PI / 180);
            m_3937_C.this.J_1907_R = e_2866_D.J_1907_R(m_3937_C.this.R_4764_Y).J_1907_R(this.G_564_y * u_530_F.J_1907_R(this.R_4764_Y), -4.0f + this.P_1922_E, this.G_564_y * u_530_F.n_1700_B(this.R_4764_Y));
        }
    }

    class J_1907_R
    extends Goal {
        private final TargetingConditions J_1907_R = new TargetingConditions().n_1700_B(64.0);
        private int R_4764_Y = 20;

        private J_1907_R() {
        }

        @Override
        public boolean n_1700_B() {
            if (this.R_4764_Y > 0) {
                --this.R_4764_Y;
                return false;
            }
            this.R_4764_Y = 60;
            List<a_3913_L> list = m_3937_C.this.O_508_d.n_1700_B(this.J_1907_R, m_3937_C.this, m_3937_C.this.i_601_W().grow(16.0, 64.0, 16.0));
            if (!list.isEmpty()) {
                list.sort(Comparator.comparing(N_4263_v::X_2960_b).reversed());
                for (a_3913_L playerentity : list) {
                    if (!m_3937_C.this.n_1700_B((r_4811_B)playerentity, TargetingConditions.n_1700_B)) continue;
                    m_3937_C.this.R_4764_Y((r_4811_B)playerentity);
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean J_1907_R() {
            r_4811_B livingentity = m_3937_C.this.t_148_a();
            return livingentity != null ? m_3937_C.this.n_1700_B(livingentity, TargetingConditions.n_1700_B) : false;
        }
    }

    abstract class P_1922_E
    extends Goal {
        public P_1922_E() {
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
        }

        protected boolean v_4262_N() {
            return m_3937_C.this.J_1907_R.R_4764_Y(m_3937_C.this.O_3598_v(), m_3937_C.this.X_2960_b(), m_3937_C.this.l_2647_k()) < 4.0;
        }
    }
}


