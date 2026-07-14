/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.RandomLookAroundGoal;
import lightning.product.C_4114_x;
import lightning.product.D_38_f;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.L_2837_o;
import lightning.product.L_461_d;
import lightning.product.N_4263_v;
import lightning.product.N_81_X;
import lightning.product.P_11_z;
import lightning.product.AbstractGolem;
import lightning.product.R_1815_U;
import lightning.product.R_2450_T;
import lightning.product.U_1880_G;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_530_i;
import lightning.product.ShulkerSharedHelper;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.BodyRotationControl;
import lightning.product.c_1514_x;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_2866_D;
import lightning.product.e_933_M;
import lightning.product.g_3408_G;
import lightning.product.h_256_u;
import lightning.product.h_384_L;
import lightning.product.h_4152_b;
import lightning.product.EntityDataSerializers;
import lightning.product.Goal;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;
import lightning.product.x_1835_e;

public class m_1605_o
extends AbstractGolem
implements x_1835_e {
    private static final UUID Q_4569_t = UUID.fromString("7E0292F2-9434-48D5-A29F-9583AF7DF27F");
    private static final U_1880_G M_182_A = new U_1880_G(Q_4569_t, "Covered armor bonus", 20.0, U_1880_G.n_1700_B.n_1700_B);
    protected static final h_256_u<b_257_Y> n_1700_B = C_4114_x.n_1700_B(m_1605_o.class, EntityDataSerializers.h_1847_R);
    protected static final h_256_u<Optional<c_1514_x>> J_1907_R = C_4114_x.n_1700_B(m_1605_o.class, EntityDataSerializers.P_4830_p);
    protected static final h_256_u<Byte> R_4764_Y = C_4114_x.n_1700_B(m_1605_o.class, EntityDataSerializers.n_1700_B);
    protected static final h_256_u<Byte> h_1847_R = C_4114_x.n_1700_B(m_1605_o.class, EntityDataSerializers.n_1700_B);
    private float t_1786_h;
    private float multiplayerClientSuggestionProvider;
    private c_1514_x w_1457_N = null;
    private int Y_601_j;

    public m_1605_o(t_5_h<? extends m_1605_o> p_i50196_1_, b_4507_u p_i50196_2_) {
        super((t_5_h<? extends AbstractGolem>)p_i50196_1_, p_i50196_2_);
        this.P_1922_E = 5;
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(1, new LookAtPlayerGoal(this, a_3913_L.class, 8.0f));
        this.s_956_w.n_1700_B(4, new n_1700_B());
        this.s_956_w.n_1700_B(7, new P_1922_E());
        this.s_956_w.n_1700_B(8, new RandomLookAroundGoal(this));
        this.u_2550_I.n_1700_B(1, new g_3408_G(this, new Class[0]).n_1700_B(new Class[0]));
        this.u_2550_I.n_1700_B(2, new J_1907_R(this));
        this.u_2550_I.n_1700_B(3, new G_564_y(this));
    }

    @Override
    protected boolean RetryCallException() {
        return false;
    }

    @Override
    public D_38_f r_2478_U() {
        return D_38_f.u_1723_Y;
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.w_4866_k;
    }

    @Override
    public void G_624_v() {
        if (!this.P_2295_B()) {
            super.G_624_v();
        }
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.MinMaxBounds;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return this.P_2295_B() ? SoundEvents.y_2836_h : SoundEvents.WrappedMinMaxBounds;
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(n_1700_B, b_257_Y.n_1700_B);
        this.l_4537_E.n_1700_B(J_1907_R, Optional.empty());
        this.l_4537_E.n_1700_B(R_4764_Y, (byte)0);
        this.l_4537_E.n_1700_B(h_1847_R, (byte)16);
    }

    public static s_1415_m.n_1700_B u_1723_Y() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.n_1700_B, 30.0);
    }

    @Override
    protected BodyRotationControl k_2293_S() {
        return new R_4764_Y(this, this);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.l_4537_E.J_1907_R(n_1700_B, b_257_Y.n_1700_B(compound.u_1723_Y("AttachFace")));
        this.l_4537_E.J_1907_R(R_4764_Y, compound.u_1723_Y("Peek"));
        this.l_4537_E.J_1907_R(h_1847_R, compound.u_1723_Y("Color"));
        if (compound.P_1922_E("APX")) {
            int i = compound.w_1484_f("APX");
            int j = compound.w_1484_f("APY");
            int k = compound.w_1484_f("APZ");
            this.l_4537_E.J_1907_R(J_1907_R, Optional.of(new c_1514_x(i, j, k)));
        } else {
            this.l_4537_E.J_1907_R(J_1907_R, Optional.empty());
        }
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("AttachFace", (byte)this.l_4537_E.n_1700_B(n_1700_B).R_4764_Y());
        compound.n_1700_B("Peek", this.l_4537_E.n_1700_B(R_4764_Y));
        compound.n_1700_B("Color", this.l_4537_E.n_1700_B(h_1847_R));
        c_1514_x blockpos = this.V_1176_p();
        if (blockpos != null) {
            compound.J_1907_R("APX", blockpos.getX());
            compound.J_1907_R("APY", blockpos.getY());
            compound.J_1907_R("APZ", blockpos.getZ());
        }
    }

    @Override
    public void v_() {
        super.v_();
        c_1514_x blockpos = this.l_4537_E.n_1700_B(J_1907_R).orElse(null);
        if (blockpos == null && !this.O_508_d.Y_259_p) {
            blockpos = this.b_2312_j();
            this.l_4537_E.J_1907_R(J_1907_R, Optional.of(blockpos));
        }
        if (this.y_2772_m()) {
            float f;
            blockpos = null;
            this.p_178_J = f = this.l_3609_d().p_178_J;
            this.C_1162_e = f;
            this.D_4361_a = f;
            this.Y_601_j = 0;
        } else if (!this.O_508_d.Y_259_p) {
            b_257_Y direction4;
            K_4074_S blockstate = this.O_508_d.getBlockState(blockpos);
            if (!blockstate.v_4262_N()) {
                if (blockstate.n_1700_B(a_3742_W.O_2151_c)) {
                    b_257_Y direction = blockstate.R_4764_Y(h_4152_b.P_4830_p);
                    if (this.O_508_d.u_1723_Y(blockpos.offset(direction))) {
                        blockpos = blockpos.offset(direction);
                        this.l_4537_E.J_1907_R(J_1907_R, Optional.of(blockpos));
                    } else {
                        this.y_4642_Y();
                    }
                } else if (blockstate.n_1700_B(a_3742_W.S_980_j)) {
                    b_257_Y direction3 = blockstate.R_4764_Y(N_81_X.P_4830_p);
                    if (this.O_508_d.u_1723_Y(blockpos.offset(direction3))) {
                        blockpos = blockpos.offset(direction3);
                        this.l_4537_E.J_1907_R(J_1907_R, Optional.of(blockpos));
                    } else {
                        this.y_4642_Y();
                    }
                } else {
                    this.y_4642_Y();
                }
            }
            if (!this.n_1700_B(blockpos, direction4 = this.h_1640_b())) {
                b_257_Y direction1 = this.v_4262_N(blockpos);
                if (direction1 != null) {
                    this.l_4537_E.J_1907_R(n_1700_B, direction1);
                } else {
                    this.y_4642_Y();
                }
            }
        }
        float f1 = (float)this.y_2447_C() * 0.01f;
        this.t_1786_h = this.multiplayerClientSuggestionProvider;
        if (this.multiplayerClientSuggestionProvider > f1) {
            this.multiplayerClientSuggestionProvider = u_530_F.n_1700_B(this.multiplayerClientSuggestionProvider - 0.05f, f1, 1.0f);
        } else if (this.multiplayerClientSuggestionProvider < f1) {
            this.multiplayerClientSuggestionProvider = u_530_F.n_1700_B(this.multiplayerClientSuggestionProvider + 0.05f, 0.0f, f1);
        }
        if (blockpos != null) {
            List<N_4263_v> list;
            if (this.O_508_d.Y_259_p) {
                if (this.Y_601_j > 0 && this.w_1457_N != null) {
                    --this.Y_601_j;
                } else {
                    this.w_1457_N = blockpos;
                }
            }
            this.u_1723_Y((double)blockpos.getX() + 0.5, blockpos.getY(), (double)blockpos.getZ() + 0.5);
            double d2 = 0.5 - (double)u_530_F.n_1700_B((0.5f + this.multiplayerClientSuggestionProvider) * (float)Math.PI) * 0.5;
            double d0 = 0.5 - (double)u_530_F.n_1700_B((0.5f + this.t_1786_h) * (float)Math.PI) * 0.5;
            b_257_Y direction2 = this.h_1640_b().u_1723_Y();
            this.n_1700_B(new I_4817_s(this.O_3598_v() - 0.5, this.X_2960_b(), this.l_2647_k() - 0.5, this.O_3598_v() + 0.5, this.X_2960_b() + 1.0, this.l_2647_k() + 0.5).expand((double)direction2.t_148_a() * d2, (double)direction2.s_956_w() * d2, (double)direction2.u_2550_I() * d2));
            double d1 = d2 - d0;
            if (d1 > 0.0 && !(list = this.O_508_d.n_1700_B((N_4263_v)this, this.i_601_W())).isEmpty()) {
                for (N_4263_v entity : list) {
                    if (entity instanceof m_1605_o || entity.j_1564_a) continue;
                    entity.n_1700_B(L_461_d.P_1922_E, new e_2866_D(d1 * (double)direction2.t_148_a(), d1 * (double)direction2.s_956_w(), d1 * (double)direction2.u_2550_I()));
                }
            }
        }
    }

    @Override
    public void n_1700_B(L_461_d typeIn, e_2866_D pos) {
        if (typeIn == L_461_d.G_564_y) {
            this.y_4642_Y();
        } else {
            super.n_1700_B(typeIn, pos);
        }
    }

    @Override
    public void J_1907_R(double x, double y, double z) {
        super.J_1907_R(x, y, z);
        if (this.l_4537_E != null && this.RealmsWorldResetDto != 0) {
            Optional<c_1514_x> optional = this.l_4537_E.n_1700_B(J_1907_R);
            Optional<c_1514_x> optional1 = Optional.of(new c_1514_x(x, y, z));
            if (!optional1.equals(optional)) {
                this.l_4537_E.J_1907_R(J_1907_R, optional1);
                this.l_4537_E.J_1907_R(R_4764_Y, (byte)0);
                this.LongRunningTask = true;
            }
        }
    }

    @Nullable
    protected b_257_Y v_4262_N(c_1514_x p_234299_1_) {
        for (b_257_Y direction : b_257_Y.values()) {
            if (!this.n_1700_B(p_234299_1_, direction)) continue;
            return direction;
        }
        return null;
    }

    private boolean n_1700_B(c_1514_x p_234298_1_, b_257_Y p_234298_2_) {
        return this.O_508_d.n_1700_B(p_234298_1_.offset(p_234298_2_), (N_4263_v)this, p_234298_2_.u_1723_Y()) && this.O_508_d.a_(this, ShulkerSharedHelper.n_1700_B(p_234298_1_, p_234298_2_.u_1723_Y()));
    }

    protected boolean y_4642_Y() {
        if (!this.n_473_l() && this.RealmsLongRunningMcoTaskScreen()) {
            c_1514_x blockpos = this.b_2312_j();
            for (int i = 0; i < 5; ++i) {
                b_257_Y direction;
                c_1514_x blockpos1 = blockpos.add(8 - this.RealmsWorldOptions.nextInt(17), 8 - this.RealmsWorldOptions.nextInt(17), 8 - this.RealmsWorldOptions.nextInt(17));
                if (blockpos1.getY() <= 0 || !this.O_508_d.u_1723_Y(blockpos1) || !this.O_508_d.H_2857_Y().n_1700_B(blockpos1) || !this.O_508_d.a_(this, new I_4817_s(blockpos1)) || (direction = this.v_4262_N(blockpos1)) == null) continue;
                this.l_4537_E.J_1907_R(n_1700_B, direction);
                this.n_1700_B(SoundEvents.w_2892_f, 1.0f, 1.0f);
                this.l_4537_E.J_1907_R(J_1907_R, Optional.of(blockpos1));
                this.l_4537_E.J_1907_R(R_4764_Y, (byte)0);
                this.R_4764_Y((r_4811_B)null);
                return true;
            }
            return false;
        }
        return true;
    }

    @Override
    public void Y_1740_V() {
        super.Y_1740_V();
        this.v_4262_N(e_2866_D.n_1700_B);
        if (!this.n_473_l()) {
            this.D_4361_a = 0.0f;
            this.C_1162_e = 0.0f;
        }
    }

    @Override
    public void n_1700_B(h_256_u<?> key) {
        c_1514_x blockpos;
        if (J_1907_R.equals(key) && this.O_508_d.Y_259_p && !this.y_2772_m() && (blockpos = this.V_1176_p()) != null) {
            if (this.w_1457_N == null) {
                this.w_1457_N = blockpos;
            } else {
                this.Y_601_j = 6;
            }
            this.u_1723_Y((double)blockpos.getX() + 0.5, blockpos.getY(), (double)blockpos.getZ() + 0.5);
        }
        super.n_1700_B(key);
    }

    @Override
    public void n_1700_B(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        this.O_1309_Q = 0;
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        N_4263_v entity;
        if (this.P_2295_B() && (entity = source.s_956_w()) instanceof h_384_L) {
            return false;
        }
        if (super.n_1700_B(source, amount)) {
            if ((double)this.g_46_E() < (double)this.L_1733_J() * 0.5 && this.RealmsWorldOptions.nextInt(4) == 0) {
                this.y_4642_Y();
            }
            return true;
        }
        return false;
    }

    private boolean P_2295_B() {
        return this.y_2447_C() == 0;
    }

    @Override
    public boolean RealmsParentalConsentScreen() {
        return this.RealmsLongRunningMcoTaskScreen();
    }

    public b_257_Y h_1640_b() {
        return this.l_4537_E.n_1700_B(n_1700_B);
    }

    @Nullable
    public c_1514_x V_1176_p() {
        return this.l_4537_E.n_1700_B(J_1907_R).orElse(null);
    }

    public void w_1484_f(@Nullable c_1514_x pos) {
        this.l_4537_E.J_1907_R(J_1907_R, Optional.ofNullable(pos));
    }

    public int y_2447_C() {
        return this.l_4537_E.n_1700_B(R_4764_Y).byteValue();
    }

    public void n_1700_B(int p_184691_1_) {
        if (!this.O_508_d.Y_259_p) {
            this.n_1700_B(Attributes.t_148_a).G_564_y(M_182_A);
            if (p_184691_1_ == 0) {
                this.n_1700_B(Attributes.t_148_a).R_4764_Y(M_182_A);
                this.n_1700_B(SoundEvents.B_368_w, 1.0f, 1.0f);
            } else {
                this.n_1700_B(SoundEvents.h_2396_v, 1.0f, 1.0f);
            }
        }
        this.l_4537_E.J_1907_R(R_4764_Y, (byte)p_184691_1_);
    }

    public float c_3005_b(float p_184688_1_) {
        return u_530_F.v_4262_N(p_184688_1_, this.t_1786_h, this.multiplayerClientSuggestionProvider);
    }

    public int J_3635_s() {
        return this.Y_601_j;
    }

    public c_1514_x o_82_k() {
        return this.w_1457_N;
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return 0.5f;
    }

    @Override
    public int Z_976_R() {
        return 180;
    }

    @Override
    public int H_1990_U() {
        return 180;
    }

    @Override
    public void P_1922_E(N_4263_v entityIn) {
    }

    @Override
    public float G_424_k() {
        return 0.0f;
    }

    public boolean h_973_D() {
        return this.w_1457_N != null && this.V_1176_p() != null;
    }

    @Nullable
    public e_933_M f_2787_O() {
        Byte obyte = this.l_4537_E.n_1700_B(h_1847_R);
        return obyte != 16 && obyte <= 15 ? e_933_M.n_1700_B(obyte.byteValue()) : null;
    }

    class n_1700_B
    extends Goal {
        private int J_1907_R;

        public n_1700_B() {
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R));
        }

        @Override
        public boolean n_1700_B() {
            r_4811_B livingentity = m_1605_o.this.t_148_a();
            if (livingentity != null && livingentity.RealmsLongRunningMcoTaskScreen()) {
                return m_1605_o.this.O_508_d.x_607_J() != R_2450_T.n_1700_B;
            }
            return false;
        }

        @Override
        public void R_4764_Y() {
            this.J_1907_R = 20;
            m_1605_o.this.n_1700_B(100);
        }

        @Override
        public void G_564_y() {
            m_1605_o.this.n_1700_B(0);
        }

        @Override
        public void P_1922_E() {
            if (m_1605_o.this.O_508_d.x_607_J() != R_2450_T.n_1700_B) {
                --this.J_1907_R;
                r_4811_B livingentity = m_1605_o.this.t_148_a();
                m_1605_o.this.c_3005_b().n_1700_B(livingentity, 180.0f, 180.0f);
                double d0 = m_1605_o.this.G_564_y((N_4263_v)livingentity);
                if (d0 < 400.0) {
                    if (this.J_1907_R <= 0) {
                        this.J_1907_R = 20 + m_1605_o.this.RealmsWorldOptions.nextInt(10) * 20 / 2;
                        m_1605_o.this.O_508_d.a_(new L_2837_o(m_1605_o.this.O_508_d, m_1605_o.this, livingentity, m_1605_o.this.h_1640_b().h_1847_R()));
                        m_1605_o.this.n_1700_B(SoundEvents.x_2711_Y, 2.0f, (m_1605_o.this.RealmsWorldOptions.nextFloat() - m_1605_o.this.RealmsWorldOptions.nextFloat()) * 0.2f + 1.0f);
                    }
                } else {
                    m_1605_o.this.R_4764_Y((r_4811_B)null);
                }
                super.P_1922_E();
            }
        }
    }

    class P_1922_E
    extends Goal {
        private int J_1907_R;

        private P_1922_E() {
        }

        @Override
        public boolean n_1700_B() {
            return m_1605_o.this.t_148_a() == null && m_1605_o.this.RealmsWorldOptions.nextInt(40) == 0;
        }

        @Override
        public boolean J_1907_R() {
            return m_1605_o.this.t_148_a() == null && this.J_1907_R > 0;
        }

        @Override
        public void R_4764_Y() {
            this.J_1907_R = 20 * (1 + m_1605_o.this.RealmsWorldOptions.nextInt(3));
            m_1605_o.this.n_1700_B(30);
        }

        @Override
        public void G_564_y() {
            if (m_1605_o.this.t_148_a() == null) {
                m_1605_o.this.n_1700_B(0);
            }
        }

        @Override
        public void P_1922_E() {
            --this.J_1907_R;
        }
    }

    class J_1907_R
    extends NearestAttackableTargetGoal<a_3913_L> {
        public J_1907_R(m_1605_o shulker) {
            super((Z_530_i)shulker, a_3913_L.class, true);
        }

        @Override
        public boolean n_1700_B() {
            return m_1605_o.this.O_508_d.x_607_J() == R_2450_T.n_1700_B ? false : super.n_1700_B();
        }

        @Override
        protected I_4817_s n_1700_B(double targetDistance) {
            b_257_Y direction = ((m_1605_o)this.P_1922_E).h_1640_b();
            if (direction.h_1847_R() == b_257_Y.n_1700_B.n_1700_B) {
                return this.P_1922_E.i_601_W().grow(4.0, targetDistance, targetDistance);
            }
            return direction.h_1847_R() == b_257_Y.n_1700_B.R_4764_Y ? this.P_1922_E.i_601_W().grow(targetDistance, targetDistance, 4.0) : this.P_1922_E.i_601_W().grow(targetDistance, 4.0, targetDistance);
        }
    }

    static class G_564_y
    extends NearestAttackableTargetGoal<r_4811_B> {
        public G_564_y(m_1605_o shulker) {
            super(shulker, r_4811_B.class, 10, true, false, p_200826_0_ -> p_200826_0_ instanceof x_1835_e);
        }

        @Override
        public boolean n_1700_B() {
            return this.P_1922_E.L_1362_X() == null ? false : super.n_1700_B();
        }

        @Override
        protected I_4817_s n_1700_B(double targetDistance) {
            b_257_Y direction = ((m_1605_o)this.P_1922_E).h_1640_b();
            if (direction.h_1847_R() == b_257_Y.n_1700_B.n_1700_B) {
                return this.P_1922_E.i_601_W().grow(4.0, targetDistance, targetDistance);
            }
            return direction.h_1847_R() == b_257_Y.n_1700_B.R_4764_Y ? this.P_1922_E.i_601_W().grow(targetDistance, targetDistance, 4.0) : this.P_1922_E.i_601_W().grow(targetDistance, 4.0, targetDistance);
        }
    }

    class R_4764_Y
    extends BodyRotationControl {
        public R_4764_Y(m_1605_o this$0, Z_530_i p_i50612_2_) {
            super(p_i50612_2_);
        }

        @Override
        public void n_1700_B() {
        }
    }
}


