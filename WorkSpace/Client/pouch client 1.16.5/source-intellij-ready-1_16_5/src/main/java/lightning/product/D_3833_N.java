/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.EnumSet;
import javax.annotation.Nullable;
import lightning.product.C_4114_x;
import lightning.product.Attributes;
import lightning.product.J_133_e;
import lightning.product.L_461_d;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.MoveControl;
import lightning.product.FloatGoal;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.W_4304_a;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.TargetingConditions;
import lightning.product.g_3408_G;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.Monster;
import lightning.product.PathfinderMob;
import lightning.product.Goal;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;

public class D_3833_N
extends Monster {
    protected static final h_256_u<Byte> n_1700_B = C_4114_x.n_1700_B(D_3833_N.class, EntityDataSerializers.n_1700_B);
    private Z_530_i J_1907_R;
    @Nullable
    private c_1514_x R_4764_Y;
    private boolean h_1847_R;
    private int Q_4569_t;

    public D_3833_N(t_5_h<? extends D_3833_N> p_i50190_1_, b_4507_u p_i50190_2_) {
        super((t_5_h<? extends Monster>)p_i50190_1_, p_i50190_2_);
        this.v_4262_N = new R_4764_Y(this);
        this.P_1922_E = 3;
    }

    @Override
    public void n_1700_B(L_461_d typeIn, e_2866_D pos) {
        super.n_1700_B(typeIn, pos);
        this.F_2624_D();
    }

    @Override
    public void v_() {
        this.j_1564_a = true;
        super.v_();
        this.j_1564_a = false;
        this.w_1484_f(true);
        if (this.h_1847_R && --this.Q_4569_t <= 0) {
            this.Q_4569_t = 20;
            this.n_1700_B(P_11_z.t_148_a, 1.0f);
        }
    }

    @Override
    protected void M_182_A() {
        super.M_182_A();
        this.s_956_w.n_1700_B(0, new FloatGoal(this));
        this.s_956_w.n_1700_B(4, new n_1700_B());
        this.s_956_w.n_1700_B(8, new G_564_y());
        this.s_956_w.n_1700_B(9, new LookAtPlayerGoal(this, a_3913_L.class, 3.0f, 1.0f));
        this.s_956_w.n_1700_B(10, new LookAtPlayerGoal(this, Z_530_i.class, 8.0f));
        this.u_2550_I.n_1700_B(1, new g_3408_G(this, W_4304_a.class).n_1700_B(new Class[0]));
        this.u_2550_I.n_1700_B(2, new J_1907_R(this));
        this.u_2550_I.n_1700_B(3, new NearestAttackableTargetGoal<a_3913_L>((Z_530_i)this, a_3913_L.class, true));
    }

    public static s_1415_m.n_1700_B u_1723_Y() {
        return Monster.o_4117_e().n_1700_B(Attributes.n_1700_B, 14.0).n_1700_B(Attributes.u_1723_Y, 4.0);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(n_1700_B, (byte)0);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        if (compound.P_1922_E("BoundX")) {
            this.R_4764_Y = new c_1514_x(compound.w_1484_f("BoundX"), compound.w_1484_f("BoundY"), compound.w_1484_f("BoundZ"));
        }
        if (compound.P_1922_E("LifeTicks")) {
            this.n_1700_B(compound.w_1484_f("LifeTicks"));
        }
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        if (this.R_4764_Y != null) {
            compound.J_1907_R("BoundX", this.R_4764_Y.getX());
            compound.J_1907_R("BoundY", this.R_4764_Y.getY());
            compound.J_1907_R("BoundZ", this.R_4764_Y.getZ());
        }
        if (this.h_1847_R) {
            compound.J_1907_R("LifeTicks", this.Q_4569_t);
        }
    }

    public Z_530_i y_4642_Y() {
        return this.J_1907_R;
    }

    @Nullable
    public c_1514_x V_1176_p() {
        return this.R_4764_Y;
    }

    public void v_4262_N(@Nullable c_1514_x boundOriginIn) {
        this.R_4764_Y = boundOriginIn;
    }

    private boolean J_1907_R(int mask) {
        byte i = this.l_4537_E.n_1700_B(n_1700_B);
        return (i & mask) != 0;
    }

    private void n_1700_B(int mask, boolean value) {
        int i = this.l_4537_E.n_1700_B(n_1700_B).byteValue();
        i = value ? (i |= mask) : (i &= ~mask);
        this.l_4537_E.J_1907_R(n_1700_B, (byte)(i & 0xFF));
    }

    public boolean y_2447_C() {
        return this.J_1907_R(1);
    }

    public void w_1457_N(boolean charging) {
        this.n_1700_B(1, charging);
    }

    public void n_1700_B(Z_530_i ownerIn) {
        this.J_1907_R = ownerIn;
    }

    public void n_1700_B(int limitedLifeTicksIn) {
        this.h_1847_R = true;
        this.Q_4569_t = limitedLifeTicksIn;
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.LiquidBlockContainer;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.SimpleWaterloggedBlock;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.IceBlock;
    }

    @Override
    public float RealmsConfirmScreen() {
        return 1.0f;
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        this.n_1700_B(difficultyIn);
        this.J_1907_R(difficultyIn);
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void n_1700_B(DifficultyInstance difficulty) {
        this.n_1700_B(e_1174_E.n_1700_B, new Z_1993_T(Items.w_2152_d));
        this.n_1700_B(e_1174_E.n_1700_B, 0.0f);
    }

    class R_4764_Y
    extends MoveControl {
        public R_4764_Y(D_3833_N vex) {
            super(vex);
        }

        @Override
        public void n_1700_B() {
            if (this.w_1484_f == MoveControl.n_1700_B.J_1907_R) {
                e_2866_D vector3d = new e_2866_D(this.J_1907_R - D_3833_N.this.O_3598_v(), this.R_4764_Y - D_3833_N.this.X_2960_b(), this.G_564_y - D_3833_N.this.l_2647_k());
                double d0 = vector3d.u_1723_Y();
                if (d0 < D_3833_N.this.i_601_W().getAverageEdgeLength()) {
                    this.w_1484_f = MoveControl.n_1700_B.n_1700_B;
                    D_3833_N.this.v_4262_N(D_3833_N.this.I_4348_c().n_1700_B(0.5));
                } else {
                    D_3833_N.this.v_4262_N(D_3833_N.this.I_4348_c().P_1922_E(vector3d.n_1700_B(this.P_1922_E * 0.05 / d0)));
                    if (D_3833_N.this.t_148_a() == null) {
                        e_2866_D vector3d1 = D_3833_N.this.I_4348_c();
                        D_3833_N.this.C_1162_e = D_3833_N.this.p_178_J = -((float)u_530_F.G_564_y(vector3d1.J_1907_R, vector3d1.G_564_y)) * 57.295776f;
                    } else {
                        double d2 = D_3833_N.this.t_148_a().O_3598_v() - D_3833_N.this.O_3598_v();
                        double d1 = D_3833_N.this.t_148_a().l_2647_k() - D_3833_N.this.l_2647_k();
                        D_3833_N.this.C_1162_e = D_3833_N.this.p_178_J = -((float)u_530_F.G_564_y(d2, d1)) * 57.295776f;
                    }
                }
            }
        }
    }

    class n_1700_B
    extends Goal {
        public n_1700_B() {
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
        }

        @Override
        public boolean n_1700_B() {
            if (D_3833_N.this.t_148_a() != null && !D_3833_N.this.A_4115_X().J_1907_R() && D_3833_N.this.RealmsWorldOptions.nextInt(7) == 0) {
                return D_3833_N.this.G_564_y((N_4263_v)D_3833_N.this.t_148_a()) > 4.0;
            }
            return false;
        }

        @Override
        public boolean J_1907_R() {
            return D_3833_N.this.A_4115_X().J_1907_R() && D_3833_N.this.y_2447_C() && D_3833_N.this.t_148_a() != null && D_3833_N.this.t_148_a().RealmsLongRunningMcoTaskScreen();
        }

        @Override
        public void R_4764_Y() {
            r_4811_B livingentity = D_3833_N.this.t_148_a();
            e_2866_D vector3d = livingentity.u_2550_I(1.0f);
            D_3833_N.this.v_4262_N.n_1700_B(vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y, 1.0);
            D_3833_N.this.w_1457_N(true);
            D_3833_N.this.n_1700_B(SoundEvents.k_2789_z, 1.0f, 1.0f);
        }

        @Override
        public void G_564_y() {
            D_3833_N.this.w_1457_N(false);
        }

        @Override
        public void P_1922_E() {
            r_4811_B livingentity = D_3833_N.this.t_148_a();
            if (D_3833_N.this.i_601_W().intersects(livingentity.i_601_W())) {
                D_3833_N.this.q_2307_F(livingentity);
                D_3833_N.this.w_1457_N(false);
            } else {
                double d0 = D_3833_N.this.G_564_y((N_4263_v)livingentity);
                if (d0 < 9.0) {
                    e_2866_D vector3d = livingentity.u_2550_I(1.0f);
                    D_3833_N.this.v_4262_N.n_1700_B(vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y, 1.0);
                }
            }
        }
    }

    class G_564_y
    extends Goal {
        public G_564_y() {
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
        }

        @Override
        public boolean n_1700_B() {
            return !D_3833_N.this.A_4115_X().J_1907_R() && D_3833_N.this.RealmsWorldOptions.nextInt(7) == 0;
        }

        @Override
        public boolean J_1907_R() {
            return false;
        }

        @Override
        public void P_1922_E() {
            c_1514_x blockpos = D_3833_N.this.V_1176_p();
            if (blockpos == null) {
                blockpos = D_3833_N.this.b_2312_j();
            }
            for (int i = 0; i < 3; ++i) {
                c_1514_x blockpos1 = blockpos.add(D_3833_N.this.RealmsWorldOptions.nextInt(15) - 7, D_3833_N.this.RealmsWorldOptions.nextInt(11) - 5, D_3833_N.this.RealmsWorldOptions.nextInt(15) - 7);
                if (!D_3833_N.this.O_508_d.u_1723_Y(blockpos1)) continue;
                D_3833_N.this.v_4262_N.n_1700_B((double)blockpos1.getX() + 0.5, (double)blockpos1.getY() + 0.5, (double)blockpos1.getZ() + 0.5, 0.25);
                if (D_3833_N.this.t_148_a() != null) break;
                D_3833_N.this.c_3005_b().n_1700_B((double)blockpos1.getX() + 0.5, (double)blockpos1.getY() + 0.5, (double)blockpos1.getZ() + 0.5, 180.0f, 20.0f);
                break;
            }
        }
    }

    class J_1907_R
    extends J_133_e {
        private final TargetingConditions J_1907_R;

        public J_1907_R(PathfinderMob creature) {
            super(creature, false);
            this.J_1907_R = new TargetingConditions().R_4764_Y().P_1922_E();
        }

        @Override
        public boolean n_1700_B() {
            return D_3833_N.this.J_1907_R != null && D_3833_N.this.J_1907_R.t_148_a() != null && this.n_1700_B(D_3833_N.this.J_1907_R.t_148_a(), this.J_1907_R);
        }

        @Override
        public void R_4764_Y() {
            D_3833_N.this.R_4764_Y(D_3833_N.this.J_1907_R.t_148_a());
            super.R_4764_Y();
        }
    }
}


