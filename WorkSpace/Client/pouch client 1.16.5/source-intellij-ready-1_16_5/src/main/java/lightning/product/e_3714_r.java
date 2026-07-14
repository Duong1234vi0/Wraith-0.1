/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.D_2364_U;
import lightning.product.D_3833_N;
import lightning.product.RandomStrollGoal;
import lightning.product.BlockGetter;
import lightning.product.G_4536_S;
import lightning.product.Attributes;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.AvoidEntityGoal;
import lightning.product.FloatGoal;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.W_4304_a;
import lightning.product.SoundEvent;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.SpellcasterIllager;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.TargetingConditions;
import lightning.product.e_3591_l;
import lightning.product.e_933_M;
import lightning.product.MobType;
import lightning.product.g_3408_G;
import lightning.product.g_4621_i;
import lightning.product.Monster;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.s_1415_m;
import lightning.product.t_5_h;
import lightning.product.t_950_g;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;

public class e_3714_r
extends SpellcasterIllager {
    private G_4536_S h_1847_R;

    public e_3714_r(t_5_h<? extends e_3714_r> type, b_4507_u worldIn) {
        super((t_5_h<? extends SpellcasterIllager>)type, worldIn);
        this.P_1922_E = 10;
    }

    @Override
    protected void M_182_A() {
        super.M_182_A();
        this.s_956_w.n_1700_B(0, new FloatGoal(this));
        this.s_956_w.n_1700_B(1, new J_1907_R());
        this.s_956_w.n_1700_B(2, new AvoidEntityGoal<a_3913_L>(this, a_3913_L.class, 8.0f, 0.6, 1.0));
        this.s_956_w.n_1700_B(4, new R_4764_Y());
        this.s_956_w.n_1700_B(5, new n_1700_B());
        this.s_956_w.n_1700_B(6, new G_564_y());
        this.s_956_w.n_1700_B(8, new RandomStrollGoal(this, 0.6));
        this.s_956_w.n_1700_B(9, new LookAtPlayerGoal(this, a_3913_L.class, 3.0f, 1.0f));
        this.s_956_w.n_1700_B(10, new LookAtPlayerGoal(this, Z_530_i.class, 8.0f));
        this.u_2550_I.n_1700_B(1, new g_3408_G(this, W_4304_a.class).n_1700_B(new Class[0]));
        this.u_2550_I.n_1700_B(2, new NearestAttackableTargetGoal<a_3913_L>((Z_530_i)this, a_3913_L.class, true).n_1700_B(300));
        this.u_2550_I.n_1700_B(3, new NearestAttackableTargetGoal<g_4621_i>((Z_530_i)this, g_4621_i.class, false).n_1700_B(300));
        this.u_2550_I.n_1700_B(3, new NearestAttackableTargetGoal<D_2364_U>((Z_530_i)this, D_2364_U.class, false));
    }

    public static s_1415_m.n_1700_B U_1697_c() {
        return Monster.o_4117_e().n_1700_B(Attributes.G_564_y, 0.5).n_1700_B(Attributes.J_1907_R, 12.0).n_1700_B(Attributes.n_1700_B, 24.0);
    }

    @Override
    protected void a_() {
        super.a_();
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
    }

    @Override
    public SoundEvent P_2295_B() {
        return SoundEvents.n_4539_g;
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
    }

    @Override
    protected void X_933_l() {
        super.X_933_l();
    }

    @Override
    public boolean Q_4569_t(N_4263_v entityIn) {
        if (entityIn == null) {
            return false;
        }
        if (entityIn == this) {
            return true;
        }
        if (super.Q_4569_t(entityIn)) {
            return true;
        }
        if (entityIn instanceof D_3833_N) {
            return this.Q_4569_t(((D_3833_N)entityIn).y_4642_Y());
        }
        if (entityIn instanceof r_4811_B && ((r_4811_B)entityIn).F_2860_q() == MobType.G_564_y) {
            return this.L_1362_X() == null && entityIn.L_1362_X() == null;
        }
        return false;
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.J_2061_p;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.U_4087_m;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.B_1146_q;
    }

    private void n_1700_B(@Nullable G_4536_S wololoTargetIn) {
        this.h_1847_R = wololoTargetIn;
    }

    @Nullable
    private G_4536_S ModuleManager() {
        return this.h_1847_R;
    }

    @Override
    protected SoundEvent V_537_k() {
        return SoundEvents.L_1733_J;
    }

    @Override
    public void n_1700_B(int wave, boolean p_213660_2_) {
    }

    class J_1907_R
    extends SpellcasterIllager.n_1700_B {
        private J_1907_R() {
            super(e_3714_r.this);
        }

        @Override
        public void P_1922_E() {
            if (e_3714_r.this.t_148_a() != null) {
                e_3714_r.this.c_3005_b().n_1700_B(e_3714_r.this.t_148_a(), (float)e_3714_r.this.H_1990_U(), (float)e_3714_r.this.Z_976_R());
            } else if (e_3714_r.this.ModuleManager() != null) {
                e_3714_r.this.c_3005_b().n_1700_B(e_3714_r.this.ModuleManager(), (float)e_3714_r.this.H_1990_U(), (float)e_3714_r.this.Z_976_R());
            }
        }
    }

    class R_4764_Y
    extends SpellcasterIllager.R_4764_Y {
        private final TargetingConditions P_1922_E;

        private R_4764_Y() {
            super(e_3714_r.this);
            this.P_1922_E = new TargetingConditions().n_1700_B(16.0).R_4764_Y().P_1922_E().n_1700_B().J_1907_R();
        }

        @Override
        public boolean n_1700_B() {
            if (!super.n_1700_B()) {
                return false;
            }
            int i = e_3714_r.this.O_508_d.n_1700_B(D_3833_N.class, this.P_1922_E, e_3714_r.this, e_3714_r.this.i_601_W().grow(16.0)).size();
            return e_3714_r.this.RealmsWorldOptions.nextInt(8) + 1 > i;
        }

        @Override
        protected int v_4262_N() {
            return 100;
        }

        @Override
        protected int w_1484_f() {
            return 340;
        }

        @Override
        protected void s_956_w() {
            e_3591_l serverworld = (e_3591_l)e_3714_r.this.O_508_d;
            for (int i = 0; i < 3; ++i) {
                c_1514_x blockpos = e_3714_r.this.b_2312_j().add(-2 + e_3714_r.this.RealmsWorldOptions.nextInt(5), 1, -2 + e_3714_r.this.RealmsWorldOptions.nextInt(5));
                D_3833_N vexentity = t_5_h.F_2624_D.n_1700_B(e_3714_r.this.O_508_d);
                vexentity.n_1700_B(blockpos, 0.0f, 0.0f);
                vexentity.n_1700_B(serverworld, e_3714_r.this.O_508_d.J_1907_R(blockpos), a_3160_D.u_1723_Y, (V_3157_k)null, null);
                vexentity.n_1700_B(e_3714_r.this);
                vexentity.v_4262_N(blockpos);
                vexentity.n_1700_B(20 * (30 + e_3714_r.this.RealmsWorldOptions.nextInt(90)));
                serverworld.n_1700_B((N_4263_v)vexentity);
            }
        }

        @Override
        protected SoundEvent u_2550_I() {
            return SoundEvents.A_2714_y;
        }

        @Override
        protected SpellcasterIllager.J_1907_R M_588_G() {
            return SpellcasterIllager.J_1907_R.J_1907_R;
        }
    }

    class n_1700_B
    extends SpellcasterIllager.R_4764_Y {
        private n_1700_B() {
            super(e_3714_r.this);
        }

        @Override
        protected int v_4262_N() {
            return 40;
        }

        @Override
        protected int w_1484_f() {
            return 100;
        }

        @Override
        protected void s_956_w() {
            r_4811_B livingentity = e_3714_r.this.t_148_a();
            double d0 = Math.min(livingentity.X_2960_b(), e_3714_r.this.X_2960_b());
            double d1 = Math.max(livingentity.X_2960_b(), e_3714_r.this.X_2960_b()) + 1.0;
            float f = (float)u_530_F.G_564_y(livingentity.l_2647_k() - e_3714_r.this.l_2647_k(), livingentity.O_3598_v() - e_3714_r.this.O_3598_v());
            if (e_3714_r.this.G_564_y((N_4263_v)livingentity) < 9.0) {
                for (int i = 0; i < 5; ++i) {
                    float f1 = f + (float)i * (float)Math.PI * 0.4f;
                    this.n_1700_B(e_3714_r.this.O_3598_v() + (double)u_530_F.J_1907_R(f1) * 1.5, e_3714_r.this.l_2647_k() + (double)u_530_F.n_1700_B(f1) * 1.5, d0, d1, f1, 0);
                }
                for (int k = 0; k < 8; ++k) {
                    float f2 = f + (float)k * (float)Math.PI * 2.0f / 8.0f + 1.2566371f;
                    this.n_1700_B(e_3714_r.this.O_3598_v() + (double)u_530_F.J_1907_R(f2) * 2.5, e_3714_r.this.l_2647_k() + (double)u_530_F.n_1700_B(f2) * 2.5, d0, d1, f2, 3);
                }
            } else {
                for (int l = 0; l < 16; ++l) {
                    double d2 = 1.25 * (double)(l + 1);
                    int j = 1 * l;
                    this.n_1700_B(e_3714_r.this.O_3598_v() + (double)u_530_F.J_1907_R(f) * d2, e_3714_r.this.l_2647_k() + (double)u_530_F.n_1700_B(f) * d2, d0, d1, f, j);
                }
            }
        }

        private void n_1700_B(double p_190876_1_, double p_190876_3_, double p_190876_5_, double p_190876_7_, float p_190876_9_, int p_190876_10_) {
            c_1514_x blockpos = new c_1514_x(p_190876_1_, p_190876_7_, p_190876_3_);
            boolean flag = false;
            double d0 = 0.0;
            do {
                K_4074_S blockstate1;
                s_1395_c voxelshape;
                c_1514_x blockpos1;
                K_4074_S blockstate;
                if (!(blockstate = e_3714_r.this.O_508_d.getBlockState(blockpos1 = blockpos.down())).G_564_y((BlockGetter)e_3714_r.this.O_508_d, blockpos1, b_257_Y.J_1907_R)) continue;
                if (!e_3714_r.this.O_508_d.u_1723_Y(blockpos) && !(voxelshape = (blockstate1 = e_3714_r.this.O_508_d.getBlockState(blockpos)).u_2550_I(e_3714_r.this.O_508_d, blockpos)).J_1907_R()) {
                    d0 = voxelshape.R_4764_Y(b_257_Y.n_1700_B.J_1907_R);
                }
                flag = true;
                break;
            } while ((blockpos = blockpos.down()).getY() >= u_530_F.R_4764_Y(p_190876_5_) - 1);
            if (flag) {
                e_3714_r.this.O_508_d.a_(new t_950_g(e_3714_r.this.O_508_d, p_190876_1_, (double)blockpos.getY() + d0, p_190876_3_, p_190876_9_, p_190876_10_, e_3714_r.this));
            }
        }

        @Override
        protected SoundEvent u_2550_I() {
            return SoundEvents.F_2860_q;
        }

        @Override
        protected SpellcasterIllager.J_1907_R M_588_G() {
            return SpellcasterIllager.J_1907_R.R_4764_Y;
        }
    }

    public class G_564_y
    extends SpellcasterIllager.R_4764_Y {
        private final TargetingConditions P_1922_E;

        public G_564_y() {
            super(e_3714_r.this);
            this.P_1922_E = new TargetingConditions().n_1700_B(16.0).n_1700_B().n_1700_B((r_4811_B p_220844_0_) -> ((G_4536_S)p_220844_0_).h_1640_b() == e_933_M.M_588_G);
        }

        @Override
        public boolean n_1700_B() {
            if (e_3714_r.this.t_148_a() != null) {
                return false;
            }
            if (e_3714_r.this.ModuleCategory()) {
                return false;
            }
            if (e_3714_r.this.RealmsWorldResetDto < this.R_4764_Y) {
                return false;
            }
            if (!e_3714_r.this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.J_1907_R)) {
                return false;
            }
            List<G_4536_S> list = e_3714_r.this.O_508_d.n_1700_B(G_4536_S.class, this.P_1922_E, e_3714_r.this, e_3714_r.this.i_601_W().grow(16.0, 4.0, 16.0));
            if (list.isEmpty()) {
                return false;
            }
            e_3714_r.this.n_1700_B(list.get(e_3714_r.this.RealmsWorldOptions.nextInt(list.size())));
            return true;
        }

        @Override
        public boolean J_1907_R() {
            return e_3714_r.this.ModuleManager() != null && this.J_1907_R > 0;
        }

        @Override
        public void G_564_y() {
            super.G_564_y();
            e_3714_r.this.n_1700_B((G_4536_S)null);
        }

        @Override
        protected void s_956_w() {
            G_4536_S sheepentity = e_3714_r.this.ModuleManager();
            if (sheepentity != null && sheepentity.RealmsLongRunningMcoTaskScreen()) {
                sheepentity.J_1907_R(e_933_M.Q_4569_t);
            }
        }

        @Override
        protected int P_4830_p() {
            return 40;
        }

        @Override
        protected int v_4262_N() {
            return 60;
        }

        @Override
        protected int w_1484_f() {
            return 140;
        }

        @Override
        protected SoundEvent u_2550_I() {
            return SoundEvents.S_4035_N;
        }

        @Override
        protected SpellcasterIllager.J_1907_R M_588_G() {
            return SpellcasterIllager.J_1907_R.G_564_y;
        }
    }
}



