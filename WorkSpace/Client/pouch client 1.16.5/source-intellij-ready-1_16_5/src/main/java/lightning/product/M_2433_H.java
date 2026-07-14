/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.C_4114_x;
import lightning.product.AgableMob;
import lightning.product.D_38_f;
import lightning.product.F_2904_S;
import lightning.product.Attributes;
import lightning.product.K_4074_S;
import lightning.product.PanicGoal;
import lightning.product.Animal;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.BreedGoal;
import lightning.product.MoveToBlockGoal;
import lightning.product.TemptGoal;
import lightning.product.MoveControl;
import lightning.product.AvoidEntityGoal;
import lightning.product.T_1316_M;
import lightning.product.FloatGoal;
import lightning.product.T_2915_h;
import lightning.product.JumpControl;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_1722_e;
import lightning.product.b_3278_X;
import lightning.product.b_4507_u;
import lightning.product.b_4953_N;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.g_1941_L;
import lightning.product.g_2336_b;
import lightning.product.g_3408_G;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.j_3341_s;
import lightning.product.Monster;
import lightning.product.k_4738_s;
import lightning.product.k_594_Q;
import lightning.product.PathfinderMob;
import lightning.product.q_1613_l;
import lightning.product.q_2335_j;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;

public class M_2433_H
extends Animal {
    private static final h_256_u<Integer> h_1847_R = C_4114_x.n_1700_B(M_2433_H.class, EntityDataSerializers.J_1907_R);
    private static final g_2336_b Q_4569_t = new g_2336_b("killer_bunny");
    private int M_182_A;
    private int t_1786_h;
    private boolean multiplayerClientSuggestionProvider;
    private int w_1457_N;
    private int Y_601_j;

    public M_2433_H(t_5_h<? extends M_2433_H> p_i50247_1_, b_4507_u p_i50247_2_) {
        super((t_5_h<? extends Animal>)p_i50247_1_, p_i50247_2_);
        this.w_1484_f = new R_4764_Y(this, this);
        this.v_4262_N = new G_564_y(this);
        this.t_148_a(0.0);
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(1, new FloatGoal(this));
        this.s_956_w.n_1700_B(1, new P_1922_E(this, 2.2));
        this.s_956_w.n_1700_B(2, new BreedGoal(this, 0.8));
        this.s_956_w.n_1700_B(3, new TemptGoal((PathfinderMob)this, 1.0, b_3278_X.n_1700_B(Items.BaseCoralWallFanBlock, Items.DoublePlantBlock, a_3742_W.s_1671_u), false));
        this.s_956_w.n_1700_B(4, new n_1700_B<a_3913_L>(this, a_3913_L.class, 8.0f, 2.2, 2.2));
        this.s_956_w.n_1700_B(4, new n_1700_B<q_2335_j>(this, q_2335_j.class, 10.0f, 2.2, 2.2));
        this.s_956_w.n_1700_B(4, new n_1700_B<Monster>(this, Monster.class, 4.0f, 2.2, 2.2));
        this.s_956_w.n_1700_B(5, new v_4262_N(this));
        this.s_956_w.n_1700_B(6, new g_1941_L(this, 0.6));
        this.s_956_w.n_1700_B(11, new LookAtPlayerGoal(this, a_3913_L.class, 10.0f));
    }

    @Override
    protected float E_453_w() {
        if (!(this.D_60_a || this.v_4262_N.J_1907_R() && this.v_4262_N.P_1922_E() > this.X_2960_b() + 0.5)) {
            b_1722_e path = this.t_148_a.s_956_w();
            if (path != null && !path.R_4764_Y()) {
                e_2866_D vector3d = path.n_1700_B(this);
                if (vector3d.R_4764_Y > this.X_2960_b() + 0.5) {
                    return 0.5f;
                }
            }
            return this.v_4262_N.R_4764_Y() <= 0.6 ? 0.2f : 0.3f;
        }
        return 0.5f;
    }

    @Override
    protected void e_837_t() {
        double d1;
        super.e_837_t();
        double d0 = this.v_4262_N.R_4764_Y();
        if (d0 > 0.0 && (d1 = M_2433_H.R_4764_Y(this.I_4348_c())) < 0.01) {
            this.n_1700_B(0.1f, new e_2866_D(0.0, 0.0, 1.0));
        }
        if (!this.O_508_d.Y_259_p) {
            this.O_508_d.n_1700_B((N_4263_v)this, (byte)1);
        }
    }

    public float c_3005_b(float p_175521_1_) {
        return this.t_1786_h == 0 ? 0.0f : ((float)this.M_182_A + p_175521_1_) / (float)this.t_1786_h;
    }

    public void t_148_a(double newSpeed) {
        this.e_4240_b().n_1700_B(newSpeed);
        this.v_4262_N.n_1700_B(this.v_4262_N.G_564_y(), this.v_4262_N.P_1922_E(), this.v_4262_N.u_1723_Y(), newSpeed);
    }

    @Override
    public void t_1786_h(boolean jumping) {
        super.t_1786_h(jumping);
        if (jumping) {
            this.n_1700_B(this.V_1176_p(), this.d_4500_Q(), ((this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f + 1.0f) * 0.8f);
        }
    }

    public void y_4642_Y() {
        this.t_1786_h(true);
        this.t_1786_h = 10;
        this.M_182_A = 0;
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(h_1847_R, 0);
    }

    @Override
    public void X_933_l() {
        if (this.w_1457_N > 0) {
            --this.w_1457_N;
        }
        if (this.Y_601_j > 0) {
            this.Y_601_j -= this.RealmsWorldOptions.nextInt(3);
            if (this.Y_601_j < 0) {
                this.Y_601_j = 0;
            }
        }
        if (this.e_1992_r) {
            R_4764_Y rabbitentity$jumphelpercontroller;
            r_4811_B livingentity;
            if (!this.multiplayerClientSuggestionProvider) {
                this.t_1786_h(false);
                this.o_4117_e();
            }
            if (this.y_2447_C() == 99 && this.w_1457_N == 0 && (livingentity = this.t_148_a()) != null && this.G_564_y((N_4263_v)livingentity) < 16.0) {
                this.J_1907_R(livingentity.O_3598_v(), livingentity.l_2647_k());
                this.v_4262_N.n_1700_B(livingentity.O_3598_v(), livingentity.X_2960_b(), livingentity.l_2647_k(), this.v_4262_N.R_4764_Y());
                this.y_4642_Y();
                this.multiplayerClientSuggestionProvider = true;
            }
            if (!(rabbitentity$jumphelpercontroller = (R_4764_Y)this.w_1484_f).R_4764_Y()) {
                if (this.v_4262_N.J_1907_R() && this.w_1457_N == 0) {
                    b_1722_e path = this.t_148_a.s_956_w();
                    e_2866_D vector3d = new e_2866_D(this.v_4262_N.G_564_y(), this.v_4262_N.P_1922_E(), this.v_4262_N.u_1723_Y());
                    if (path != null && !path.R_4764_Y()) {
                        vector3d = path.n_1700_B(this);
                    }
                    this.J_1907_R(vector3d.J_1907_R, vector3d.G_564_y);
                    this.y_4642_Y();
                }
            } else if (!rabbitentity$jumphelpercontroller.G_564_y()) {
                this.J_3635_s();
            }
        }
        this.multiplayerClientSuggestionProvider = this.e_1992_r;
    }

    @Override
    public boolean s_956_w() {
        return false;
    }

    private void J_1907_R(double x, double z) {
        this.p_178_J = (float)(u_530_F.G_564_y(z - this.l_2647_k(), x - this.O_3598_v()) * 57.2957763671875) - 90.0f;
    }

    private void J_3635_s() {
        ((R_4764_Y)this.w_1484_f).n_1700_B(true);
    }

    private void V_537_k() {
        ((R_4764_Y)this.w_1484_f).n_1700_B(false);
    }

    private void c_2086_l() {
        this.w_1457_N = this.v_4262_N.R_4764_Y() < 2.2 ? 10 : 1;
    }

    private void o_4117_e() {
        this.c_2086_l();
        this.V_537_k();
    }

    @Override
    public void Y_1740_V() {
        super.Y_1740_V();
        if (this.M_182_A != this.t_1786_h) {
            ++this.M_182_A;
        } else if (this.t_1786_h != 0) {
            this.M_182_A = 0;
            this.t_1786_h = 0;
            this.t_1786_h(false);
        }
    }

    public static s_1415_m.n_1700_B h_1640_b() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.n_1700_B, 3.0).n_1700_B(Attributes.G_564_y, 0.3f);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("RabbitType", this.y_2447_C());
        compound.J_1907_R("MoreCarrotTicks", this.Y_601_j);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.Y_601_j(compound.w_1484_f("RabbitType"));
        this.Y_601_j = compound.w_1484_f("MoreCarrotTicks");
    }

    protected SoundEvent V_1176_p() {
        return SoundEvents.K_4237_u;
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.F_489_x;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.l_3370_o;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.f_4705_f;
    }

    @Override
    public boolean q_2307_F(N_4263_v entityIn) {
        if (this.y_2447_C() == 99) {
            this.n_1700_B(SoundEvents.i_4833_u, 1.0f, (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f + 1.0f);
            return entityIn.n_1700_B(P_11_z.R_4764_Y(this), 8.0f);
        }
        return entityIn.n_1700_B(P_11_z.R_4764_Y(this), 3.0f);
    }

    @Override
    public D_38_f r_2478_U() {
        return this.y_2447_C() == 99 ? D_38_f.u_1723_Y : D_38_f.v_4262_N;
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        return this.n_1700_B(source) ? false : super.n_1700_B(source, amount);
    }

    private boolean J_1907_R(q_1613_l itemIn) {
        return itemIn == Items.BaseCoralWallFanBlock || itemIn == Items.DoublePlantBlock || itemIn == a_3742_W.s_1671_u.u_1723_Y();
    }

    public M_2433_H J_1907_R(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        M_2433_H rabbitentity = t_5_h.UploadStatus.n_1700_B(p_241840_1_);
        int i = this.n_1700_B((LevelAccessor)p_241840_1_);
        if (this.RealmsWorldOptions.nextInt(20) != 0) {
            i = p_241840_2_ instanceof M_2433_H && this.RealmsWorldOptions.nextBoolean() ? ((M_2433_H)p_241840_2_).y_2447_C() : this.y_2447_C();
        }
        rabbitentity.Y_601_j(i);
        return rabbitentity;
    }

    @Override
    public boolean u_2550_I(Z_1993_T stack) {
        return this.J_1907_R(stack.J_1907_R());
    }

    public int y_2447_C() {
        return this.l_4537_E.n_1700_B(h_1847_R);
    }

    public void Y_601_j(int rabbitTypeId) {
        if (rabbitTypeId == 99) {
            this.n_1700_B(Attributes.t_148_a).n_1700_B(8.0);
            this.s_956_w.n_1700_B(4, new J_1907_R(this));
            this.u_2550_I.n_1700_B(1, new g_3408_G(this, new Class[0]).n_1700_B(new Class[0]));
            this.u_2550_I.n_1700_B(2, new NearestAttackableTargetGoal<a_3913_L>((Z_530_i)this, a_3913_L.class, true));
            this.u_2550_I.n_1700_B(2, new NearestAttackableTargetGoal<q_2335_j>((Z_530_i)this, q_2335_j.class, true));
            if (!this.t_3452_g()) {
                this.n_1700_B(new F_2904_S(j_3341_s.n_1700_B("entity", Q_4569_t)));
            }
        }
        this.l_4537_E.J_1907_R(h_1847_R, rabbitTypeId);
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        int i = this.n_1700_B(worldIn);
        if (spawnDataIn instanceof u_1723_Y) {
            i = ((u_1723_Y)spawnDataIn).n_1700_B;
        } else {
            spawnDataIn = new u_1723_Y(i);
        }
        this.Y_601_j(i);
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    private int n_1700_B(LevelAccessor p_213610_1_) {
        k_594_Q biome = p_213610_1_.P_1922_E(this.b_2312_j());
        int i = this.RealmsWorldOptions.nextInt(100);
        if (biome.R_4764_Y() == k_594_Q.P_1922_E.R_4764_Y) {
            return i < 80 ? 1 : 3;
        }
        if (biome.Y_601_j() == k_594_Q.R_4764_Y.P_4830_p) {
            return 4;
        }
        return i < 50 ? 0 : (i < 90 ? 5 : 2);
    }

    public static boolean J_1907_R(t_5_h<M_2433_H> p_223321_0_, LevelAccessor p_223321_1_, a_3160_D reason, c_1514_x p_223321_3_, Random p_223321_4_) {
        K_4074_S blockstate = p_223321_1_.getBlockState(p_223321_3_.down());
        return (blockstate.n_1700_B(a_3742_W.t_148_a) || blockstate.n_1700_B(a_3742_W.X_290_I) || blockstate.n_1700_B(a_3742_W.A_4115_X)) && p_223321_1_.n_1700_B(p_223321_3_, 0) > 8;
    }

    private boolean U_3758_B() {
        return this.Y_601_j == 0;
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 1) {
            this.RealmsClientOutdatedScreen();
            this.t_1786_h = 10;
            this.M_182_A = 0;
        } else {
            super.n_1700_B(id);
        }
    }

    @Override
    public e_2866_D x_4991_F() {
        return new e_2866_D(0.0, 0.6f * this.X_1313_W(), this.C_415_h() * 0.4f);
    }

    @Override
    public /* synthetic */ AgableMob n_1700_B(e_3591_l e_3591_l2, AgableMob c_893_i) {
        return this.J_1907_R(e_3591_l2, c_893_i);
    }

    public class R_4764_Y
    extends JumpControl {
        private final M_2433_H J_1907_R;
        private boolean R_4764_Y;

        public R_4764_Y(M_2433_H this$0, M_2433_H rabbit) {
            super(rabbit);
            this.J_1907_R = rabbit;
        }

        public boolean R_4764_Y() {
            return this.n_1700_B;
        }

        public boolean G_564_y() {
            return this.R_4764_Y;
        }

        public void n_1700_B(boolean canJumpIn) {
            this.R_4764_Y = canJumpIn;
        }

        @Override
        public void J_1907_R() {
            if (this.n_1700_B) {
                this.J_1907_R.y_4642_Y();
                this.n_1700_B = false;
            }
        }
    }

    static class G_564_y
    extends MoveControl {
        private final M_2433_H t_148_a;
        private double s_956_w;

        public G_564_y(M_2433_H rabbit) {
            super(rabbit);
            this.t_148_a = rabbit;
        }

        @Override
        public void n_1700_B() {
            if (this.t_148_a.e_1992_r && !this.t_148_a.F_3572_x && !((R_4764_Y)this.t_148_a.w_1484_f).R_4764_Y()) {
                this.t_148_a.t_148_a(0.0);
            } else if (this.J_1907_R()) {
                this.t_148_a.t_148_a(this.s_956_w);
            }
            super.n_1700_B();
        }

        @Override
        public void n_1700_B(double x, double y, double z, double speedIn) {
            if (this.t_148_a.RowButton()) {
                speedIn = 1.5;
            }
            super.n_1700_B(x, y, z, speedIn);
            if (speedIn > 0.0) {
                this.s_956_w = speedIn;
            }
        }
    }

    static class P_1922_E
    extends PanicGoal {
        private final M_2433_H v_4262_N;

        public P_1922_E(M_2433_H rabbit, double speedIn) {
            super(rabbit, speedIn);
            this.v_4262_N = rabbit;
        }

        @Override
        public void P_1922_E() {
            super.P_1922_E();
            this.v_4262_N.t_148_a(this.J_1907_R);
        }
    }

    static class n_1700_B<T extends r_4811_B>
    extends AvoidEntityGoal<T> {
        private final M_2433_H t_148_a;

        public n_1700_B(M_2433_H rabbit, Class<T> p_i46403_2_, float p_i46403_3_, double p_i46403_4_, double p_i46403_6_) {
            super(rabbit, p_i46403_2_, p_i46403_3_, p_i46403_4_, p_i46403_6_);
            this.t_148_a = rabbit;
        }

        @Override
        public boolean n_1700_B() {
            return this.t_148_a.y_2447_C() != 99 && super.n_1700_B();
        }
    }

    static class v_4262_N
    extends MoveToBlockGoal {
        private final M_2433_H v_4262_N;
        private boolean w_1484_f;
        private boolean t_148_a;

        public v_4262_N(M_2433_H rabbitIn) {
            super(rabbitIn, 0.7f, 16);
            this.v_4262_N = rabbitIn;
        }

        @Override
        public boolean n_1700_B() {
            if (this.R_4764_Y <= 0) {
                if (!this.v_4262_N.O_508_d.H_1990_U().J_1907_R(A_2352_Z.J_1907_R)) {
                    return false;
                }
                this.t_148_a = false;
                this.w_1484_f = this.v_4262_N.U_3758_B();
                this.w_1484_f = true;
            }
            return super.n_1700_B();
        }

        @Override
        public boolean J_1907_R() {
            return this.t_148_a && super.J_1907_R();
        }

        @Override
        public void P_1922_E() {
            super.P_1922_E();
            this.v_4262_N.c_3005_b().n_1700_B((double)this.P_1922_E.getX() + 0.5, this.P_1922_E.getY() + 1, (double)this.P_1922_E.getZ() + 0.5, 10.0f, this.v_4262_N.Z_976_R());
            if (this.M_588_G()) {
                b_4507_u world = this.v_4262_N.O_508_d;
                c_1514_x blockpos = this.P_1922_E.up();
                K_4074_S blockstate = world.getBlockState(blockpos);
                T_2915_h block = blockstate.J_1907_R();
                if (this.t_148_a && block instanceof k_4738_s) {
                    Integer integer = blockstate.R_4764_Y(k_4738_s.h_1847_R);
                    if (integer == 0) {
                        world.n_1700_B(blockpos, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 2);
                        world.n_1700_B(blockpos, true, this.v_4262_N);
                    } else {
                        world.n_1700_B(blockpos, (K_4074_S)blockstate.n_1700_B(k_4738_s.h_1847_R, integer - 1), 2);
                        world.R_4764_Y(2001, blockpos, T_2915_h.s_956_w(blockstate));
                    }
                    this.v_4262_N.Y_601_j = 40;
                }
                this.t_148_a = false;
                this.R_4764_Y = 10;
            }
        }

        @Override
        protected boolean n_1700_B(T_1316_M worldIn, c_1514_x pos) {
            K_4074_S blockstate;
            T_2915_h block = worldIn.getBlockState(pos).J_1907_R();
            if (block == a_3742_W.Z_735_d && this.w_1484_f && !this.t_148_a && (block = (blockstate = worldIn.getBlockState(pos = pos.up())).J_1907_R()) instanceof k_4738_s && ((k_4738_s)block).t_148_a(blockstate)) {
                this.t_148_a = true;
                return true;
            }
            return false;
        }
    }

    static class J_1907_R
    extends b_4953_N {
        public J_1907_R(M_2433_H rabbit) {
            super(rabbit, 1.4, true);
        }

        @Override
        protected double n_1700_B(r_4811_B attackTarget) {
            return 4.0f + attackTarget.C_415_h();
        }
    }

    public static class u_1723_Y
    extends AgableMob.n_1700_B {
        public final int n_1700_B;

        public u_1723_Y(int type) {
            super(1.0f);
            this.n_1700_B = type;
        }
    }
}


