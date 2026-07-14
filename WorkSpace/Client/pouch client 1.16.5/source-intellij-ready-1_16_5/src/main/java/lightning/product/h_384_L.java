/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  it.unimi.dsi.fastutil.ints.IntOpenHashSet
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.A_4115_X;
import lightning.product.B_4088_l;
import lightning.product.Projectile;
import lightning.product.C_4114_x;
import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.H_2333_J;
import lightning.product.I_1170_F;
import lightning.product.HitResult;
import lightning.product.I_4817_s;
import lightning.product.ClientboundGameEventPacket;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.K_4074_S;
import lightning.product.K_4096_w;
import lightning.product.L_461_d;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_1815_U;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.V_3137_a;
import lightning.product.SoundEvents;
import lightning.product.V_772_m;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.Enchantments;
import lightning.product.e_2866_D;
import lightning.product.g_2336_b;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.n_3832_I;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.ParticleTypes;
import lightning.product.Packet;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.v_887_r;
import lightning.product.EntityHitResult;

public abstract class h_384_L
extends Projectile {
    private static final h_256_u<Byte> P_1922_E = C_4114_x.n_1700_B(h_384_L.class, EntityDataSerializers.n_1700_B);
    private static final h_256_u<Byte> u_1723_Y = C_4114_x.n_1700_B(h_384_L.class, EntityDataSerializers.n_1700_B);
    @Nullable
    private K_4074_S v_4262_N;
    protected boolean n_1700_B;
    protected int J_1907_R;
    public n_1700_B R_4764_Y = lightning.product.h_384_L$n_1700_B.n_1700_B;
    public int G_564_y;
    private int w_1484_f;
    private double t_148_a = 2.0;
    private int s_956_w;
    private SoundEvent u_2550_I = this.u_1723_Y();
    private IntOpenHashSet M_588_G;
    private List<N_4263_v> P_4830_p;

    protected h_384_L(t_5_h<? extends h_384_L> type, b_4507_u worldIn) {
        super((t_5_h<? extends Projectile>)type, worldIn);
    }

    protected h_384_L(t_5_h<? extends h_384_L> type, double x, double y, double z, b_4507_u worldIn) {
        this(type, worldIn);
        this.J_1907_R(x, y, z);
    }

    protected h_384_L(t_5_h<? extends h_384_L> type, r_4811_B shooter, b_4507_u worldIn) {
        this(type, shooter.O_3598_v(), shooter.X_2048_Y() - (double)0.1f, shooter.l_2647_k(), worldIn);
        this.J_1907_R(shooter);
        if (shooter instanceof a_3913_L) {
            this.R_4764_Y = lightning.product.h_384_L$n_1700_B.J_1907_R;
        }
    }

    public void n_1700_B(SoundEvent soundIn) {
        this.u_2550_I = soundIn;
    }

    @Override
    public boolean n_1700_B(double distance) {
        double d0 = this.i_601_W().getAverageEdgeLength() * 10.0;
        if (Double.isNaN(d0)) {
            d0 = 1.0;
        }
        return distance < (d0 = d0 * 64.0 * h_384_L.S_3139_t()) * d0;
    }

    @Override
    protected void a_() {
        this.l_4537_E.n_1700_B(P_1922_E, (byte)0);
        this.l_4537_E.n_1700_B(u_1723_Y, (byte)0);
    }

    @Override
    public void R_4764_Y(double x, double y, double z, float velocity, float inaccuracy) {
        super.R_4764_Y(x, y, z, velocity, inaccuracy);
        this.w_1484_f = 0;
    }

    @Override
    public void n_1700_B(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        this.J_1907_R(x, y, z);
        this.J_1907_R(yaw, pitch);
    }

    @Override
    public void s_956_w(double x, double y, double z) {
        super.s_956_w(x, y, z);
        this.w_1484_f = 0;
    }

    @Override
    public void v_() {
        s_1395_c voxelshape;
        c_1514_x blockpos;
        K_4074_S blockstate;
        super.v_();
        boolean flag = this.multiplayerClientSuggestionProvider();
        e_2866_D vector3d = this.I_4348_c();
        if (this.UploadStatus == 0.0f && this.j_276_v == 0.0f) {
            float f = u_530_F.n_1700_B(h_384_L.R_4764_Y(vector3d));
            this.p_178_J = (float)(u_530_F.G_564_y(vector3d.J_1907_R, vector3d.G_564_y) * 57.2957763671875);
            this.f_4016_n = (float)(u_530_F.G_564_y(vector3d.R_4764_Y, (double)f) * 57.2957763671875);
            this.j_276_v = this.p_178_J;
            this.UploadStatus = this.f_4016_n;
        }
        if (!((blockstate = this.O_508_d.getBlockState(blockpos = this.b_2312_j())).v_4262_N() || flag || (voxelshape = blockstate.u_2550_I(this.O_508_d, blockpos)).J_1907_R())) {
            e_2866_D vector3d1 = this.s_4990_V();
            for (I_4817_s axisalignedbb : voxelshape.G_564_y()) {
                if (!axisalignedbb.offset(blockpos).contains(vector3d1)) continue;
                this.n_1700_B = true;
                break;
            }
        }
        if (this.G_564_y > 0) {
            --this.G_564_y;
        }
        if (this.LongRunningTask()) {
            this.RealmsServerPing();
        }
        if (this.n_1700_B && !flag) {
            if (this.v_4262_N != blockstate && this.w_1457_N()) {
                this.Q_2552_b();
            } else if (!this.O_508_d.Y_259_p) {
                this.P_1922_E();
            }
            ++this.J_1907_R;
        } else {
            e_2866_D vector3d3;
            this.J_1907_R = 0;
            e_2866_D vector3d2 = this.s_4990_V();
            HitResult raytraceresult = this.O_508_d.n_1700_B(new ClipContext(vector3d2, vector3d3 = vector3d2.P_1922_E(vector3d), ClipContext.n_1700_B.n_1700_B, ClipContext.J_1907_R.n_1700_B, this));
            if (raytraceresult.R_4764_Y() != HitResult.n_1700_B.n_1700_B) {
                vector3d3 = raytraceresult.P_1922_E();
            }
            while (!this.t_4219_U) {
                EntityHitResult entityraytraceresult = this.n_1700_B(vector3d2, vector3d3);
                if (entityraytraceresult != null) {
                    raytraceresult = entityraytraceresult;
                }
                if (raytraceresult != null && raytraceresult.R_4764_Y() == HitResult.n_1700_B.R_4764_Y) {
                    N_4263_v entity = ((EntityHitResult)raytraceresult).n_1700_B();
                    N_4263_v entity1 = this.Y_601_j();
                    if (entity instanceof a_3913_L && entity1 instanceof a_3913_L && !((a_3913_L)entity1).G_564_y((a_3913_L)entity)) {
                        raytraceresult = null;
                        entityraytraceresult = null;
                    }
                }
                if (raytraceresult != null && !flag) {
                    if (entityraytraceresult != null && raytraceresult.R_4764_Y() == HitResult.n_1700_B.R_4764_Y && entityraytraceresult.n_1700_B() instanceof V_772_m) {
                        A_4115_X.n_1700_B(new v_887_r(v_887_r.n_1700_B.J_1907_R));
                    }
                    this.n_1700_B(raytraceresult);
                    this.LongRunningTask = true;
                }
                if (entityraytraceresult == null || this.Q_4569_t() <= 0) break;
                raytraceresult = null;
            }
            vector3d = this.I_4348_c();
            double d3 = vector3d.J_1907_R;
            double d4 = vector3d.R_4764_Y;
            double d0 = vector3d.G_564_y;
            if (this.u_2550_I()) {
                for (int i = 0; i < 4; ++i) {
                    this.O_508_d.n_1700_B(ParticleTypes.v_4262_N, this.O_3598_v() + d3 * (double)i / 4.0, this.X_2960_b() + d4 * (double)i / 4.0, this.l_2647_k() + d0 * (double)i / 4.0, -d3, -d4 + 0.2, -d0);
                }
            }
            double d5 = this.O_3598_v() + d3;
            double d1 = this.X_2960_b() + d4;
            double d2 = this.l_2647_k() + d0;
            float f1 = u_530_F.n_1700_B(h_384_L.R_4764_Y(vector3d));
            this.p_178_J = flag ? (float)(u_530_F.G_564_y(-d3, -d0) * 57.2957763671875) : (float)(u_530_F.G_564_y(d3, d0) * 57.2957763671875);
            this.f_4016_n = (float)(u_530_F.G_564_y(d4, (double)f1) * 57.2957763671875);
            this.f_4016_n = h_384_L.n_1700_B(this.UploadStatus, this.f_4016_n);
            this.p_178_J = h_384_L.n_1700_B(this.j_276_v, this.p_178_J);
            float f2 = 0.99f;
            float f3 = 0.05f;
            if (this.RowButton()) {
                for (int j = 0; j < 4; ++j) {
                    float f4 = 0.25f;
                    this.O_508_d.n_1700_B(ParticleTypes.P_1922_E, d5 - d3 * 0.25, d1 - d4 * 0.25, d2 - d0 * 0.25, d3, d4, d0);
                }
                f2 = this.M_182_A();
            }
            this.v_4262_N(vector3d.n_1700_B((double)f2));
            if (!this.u_744_e() && !flag) {
                e_2866_D vector3d4 = this.I_4348_c();
                this.h_1847_R(vector3d4.J_1907_R, vector3d4.R_4764_Y - (double)0.05f, vector3d4.G_564_y);
            }
            this.J_1907_R(d5, d1, d2);
            this.F_2624_D();
        }
    }

    private boolean w_1457_N() {
        return this.n_1700_B && this.O_508_d.J_1907_R(new I_4817_s(this.s_4990_V(), this.s_4990_V()).grow(0.06));
    }

    private void Q_2552_b() {
        this.n_1700_B = false;
        e_2866_D vector3d = this.I_4348_c();
        this.v_4262_N(vector3d.G_564_y(this.RealmsWorldOptions.nextFloat() * 0.2f, this.RealmsWorldOptions.nextFloat() * 0.2f, this.RealmsWorldOptions.nextFloat() * 0.2f));
        this.w_1484_f = 0;
    }

    @Override
    public void n_1700_B(L_461_d typeIn, e_2866_D pos) {
        super.n_1700_B(typeIn, pos);
        if (typeIn != L_461_d.n_1700_B && this.w_1457_N()) {
            this.Q_2552_b();
        }
    }

    protected void P_1922_E() {
        ++this.w_1484_f;
        if (this.w_1484_f >= 1200) {
            this.Ops();
        }
    }

    private void C_2741_M() {
        if (this.P_4830_p != null) {
            this.P_4830_p.clear();
        }
        if (this.M_588_G != null) {
            this.M_588_G.clear();
        }
    }

    @Override
    protected void n_1700_B(EntityHitResult p_213868_1_) {
        P_11_z damagesource;
        N_4263_v entity1;
        super.n_1700_B(p_213868_1_);
        N_4263_v entity = p_213868_1_.n_1700_B();
        float f = (float)this.I_4348_c().u_1723_Y();
        int i = u_530_F.P_1922_E(u_530_F.n_1700_B((double)f * this.t_148_a, 0.0, 2.147483647E9));
        if (this.Q_4569_t() > 0) {
            if (this.M_588_G == null) {
                this.M_588_G = new IntOpenHashSet(5);
            }
            if (this.P_4830_p == null) {
                this.P_4830_p = Lists.newArrayListWithCapacity((int)5);
            }
            if (this.M_588_G.size() >= this.Q_4569_t() + 1) {
                this.Ops();
                return;
            }
            this.M_588_G.add(entity.j_276_v());
        }
        if (this.u_2550_I()) {
            long j = this.RealmsWorldOptions.nextInt(i / 2 + 2);
            i = (int)Math.min(j + (long)i, Integer.MAX_VALUE);
        }
        if ((entity1 = this.Y_601_j()) == null) {
            damagesource = P_11_z.n_1700_B(this, (N_4263_v)this);
        } else {
            damagesource = P_11_z.n_1700_B(this, entity1);
            if (entity1 instanceof r_4811_B) {
                ((r_4811_B)entity1).C_2741_M(entity);
            }
        }
        boolean flag = entity.f_4016_n() == t_5_h.Y_259_p;
        int k = entity.w_612_n();
        if (this.RealmsPersistence() && !flag) {
            entity.P_1922_E(5);
        }
        if (entity.n_1700_B(damagesource, (float)i)) {
            if (flag) {
                return;
            }
            if (entity instanceof r_4811_B) {
                e_2866_D vector3d;
                r_4811_B livingentity = (r_4811_B)entity;
                if (!this.O_508_d.Y_259_p && this.Q_4569_t() <= 0) {
                    livingentity.P_4830_p(livingentity.n_4539_g() + 1);
                }
                if (this.s_956_w > 0 && (vector3d = this.I_4348_c().G_564_y(1.0, 0.0, 1.0).G_564_y().n_1700_B((double)this.s_956_w * 0.6)).v_4262_N() > 0.0) {
                    livingentity.w_1484_f(vector3d.J_1907_R, 0.1, vector3d.G_564_y);
                }
                if (!this.O_508_d.Y_259_p && entity1 instanceof r_4811_B) {
                    K_4096_w.n_1700_B(livingentity, entity1);
                    K_4096_w.J_1907_R((r_4811_B)entity1, (N_4263_v)livingentity);
                }
                this.n_1700_B(livingentity);
                if (entity1 != null && livingentity != entity1 && livingentity instanceof a_3913_L && entity1 instanceof B_4088_l && !this.y_1700_S()) {
                    ((B_4088_l)entity1).n_1700_B.n_1700_B(new ClientboundGameEventPacket(ClientboundGameEventPacket.v_4262_N, 0.0f));
                }
                if (!entity.RealmsLongRunningMcoTaskScreen() && this.P_4830_p != null) {
                    this.P_4830_p.add(livingentity);
                }
                if (!this.O_508_d.Y_259_p && entity1 instanceof B_4088_l) {
                    B_4088_l serverplayerentity = (B_4088_l)entity1;
                    if (this.P_4830_p != null && this.h_1847_R()) {
                        U_3554_Q.e_4240_b.n_1700_B(serverplayerentity, this.P_4830_p);
                    } else if (!entity.RealmsLongRunningMcoTaskScreen() && this.h_1847_R()) {
                        U_3554_Q.e_4240_b.n_1700_B(serverplayerentity, Arrays.asList(entity));
                    }
                }
            }
            this.n_1700_B(this.u_2550_I, 1.0f, 1.2f / (this.RealmsWorldOptions.nextFloat() * 0.2f + 0.9f));
            if (this.Q_4569_t() <= 0) {
                this.Ops();
            }
        } else {
            entity.u_1723_Y(k);
            this.v_4262_N(this.I_4348_c().n_1700_B(-0.1));
            this.p_178_J += 180.0f;
            this.j_276_v += 180.0f;
            if (!this.O_508_d.Y_259_p && this.I_4348_c().v_4262_N() < 1.0E-7) {
                if (this.R_4764_Y == lightning.product.h_384_L$n_1700_B.J_1907_R) {
                    this.n_1700_B(this.w_1484_f(), 0.1f);
                }
                this.Ops();
            }
        }
    }

    @Override
    protected void n_1700_B(BlockHitResult p_230299_1_) {
        this.v_4262_N = this.O_508_d.getBlockState(p_230299_1_.n_1700_B());
        super.n_1700_B(p_230299_1_);
        e_2866_D vector3d = p_230299_1_.P_1922_E().n_1700_B(this.O_3598_v(), this.X_2960_b(), this.l_2647_k());
        this.v_4262_N(vector3d);
        e_2866_D vector3d1 = vector3d.G_564_y().n_1700_B((double)0.05f);
        this.Q_4569_t(this.O_3598_v() - vector3d1.J_1907_R, this.X_2960_b() - vector3d1.R_4764_Y, this.l_2647_k() - vector3d1.G_564_y);
        this.n_1700_B(this.v_4262_N(), 1.0f, 1.2f / (this.RealmsWorldOptions.nextFloat() * 0.2f + 0.9f));
        this.n_1700_B = true;
        this.G_564_y = 7;
        this.n_1700_B(false);
        this.J_1907_R((byte)0);
        this.n_1700_B(SoundEvents.H_1990_U);
        this.G_564_y(false);
        this.C_2741_M();
    }

    protected SoundEvent u_1723_Y() {
        return SoundEvents.H_1990_U;
    }

    protected final SoundEvent v_4262_N() {
        return this.u_2550_I;
    }

    protected void n_1700_B(r_4811_B living) {
    }

    @Nullable
    protected EntityHitResult n_1700_B(e_2866_D startVec, e_2866_D endVec) {
        return H_2333_J.n_1700_B(this.O_508_d, this, startVec, endVec, this.i_601_W().expand(this.I_4348_c()).grow(1.0), this::n_1700_B);
    }

    @Override
    protected boolean n_1700_B(N_4263_v p_230298_1_) {
        return super.n_1700_B(p_230298_1_) && (this.M_588_G == null || !this.M_588_G.contains(p_230298_1_.j_276_v()));
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("life", (short)this.w_1484_f);
        if (this.v_4262_N != null) {
            compound.n_1700_B("inBlockState", n_3832_I.n_1700_B(this.v_4262_N));
        }
        compound.n_1700_B("shake", (byte)this.G_564_y);
        compound.n_1700_B("inGround", this.n_1700_B);
        compound.n_1700_B("pickup", (byte)this.R_4764_Y.ordinal());
        compound.n_1700_B("damage", this.t_148_a);
        compound.n_1700_B("crit", this.u_2550_I());
        compound.n_1700_B("PierceLevel", this.Q_4569_t());
        compound.n_1700_B("SoundEvent", V_3137_a.d_2461_k.J_1907_R(this.u_2550_I).toString());
        compound.n_1700_B("ShotFromCrossbow", this.h_1847_R());
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.w_1484_f = compound.v_4262_N("life");
        if (compound.R_4764_Y("inBlockState", 10)) {
            this.v_4262_N = n_3832_I.R_4764_Y(compound.M_182_A("inBlockState"));
        }
        this.G_564_y = compound.u_1723_Y("shake") & 0xFF;
        this.n_1700_B = compound.t_1786_h("inGround");
        if (compound.R_4764_Y("damage", 99)) {
            this.t_148_a = compound.u_2550_I("damage");
        }
        if (compound.R_4764_Y("pickup", 99)) {
            this.R_4764_Y = lightning.product.h_384_L$n_1700_B.n_1700_B(compound.u_1723_Y("pickup"));
        } else if (compound.R_4764_Y("player", 99)) {
            this.R_4764_Y = compound.t_1786_h("player") ? lightning.product.h_384_L$n_1700_B.J_1907_R : lightning.product.h_384_L$n_1700_B.n_1700_B;
        }
        this.n_1700_B(compound.t_1786_h("crit"));
        this.J_1907_R(compound.u_1723_Y("PierceLevel"));
        if (compound.R_4764_Y("SoundEvent", 8)) {
            this.u_2550_I = V_3137_a.d_2461_k.J_1907_R(new g_2336_b(compound.M_588_G("SoundEvent"))).orElse(this.u_1723_Y());
        }
        this.G_564_y(compound.t_1786_h("ShotFromCrossbow"));
    }

    @Override
    public void J_1907_R(@Nullable N_4263_v entityIn) {
        super.J_1907_R(entityIn);
        if (entityIn instanceof a_3913_L) {
            this.R_4764_Y = ((a_3913_L)entityIn).C_415_h.G_564_y ? lightning.product.h_384_L$n_1700_B.R_4764_Y : lightning.product.h_384_L$n_1700_B.J_1907_R;
        }
    }

    @Override
    public void c_(a_3913_L entityIn) {
        if (!this.O_508_d.Y_259_p && (this.n_1700_B || this.multiplayerClientSuggestionProvider()) && this.G_564_y <= 0) {
            boolean flag;
            boolean bl = flag = this.R_4764_Y == lightning.product.h_384_L$n_1700_B.J_1907_R || this.R_4764_Y == lightning.product.h_384_L$n_1700_B.R_4764_Y && entityIn.C_415_h.G_564_y || this.multiplayerClientSuggestionProvider() && this.Y_601_j().w_2705_t() == entityIn.w_2705_t();
            if (this.R_4764_Y == lightning.product.h_384_L$n_1700_B.J_1907_R && !entityIn.l_1268_F.P_1922_E(this.w_1484_f())) {
                flag = false;
            }
            if (flag) {
                entityIn.n_1700_B((N_4263_v)this, 1);
                this.Ops();
            }
        }
    }

    protected abstract Z_1993_T w_1484_f();

    @Override
    protected boolean RetryCallException() {
        return false;
    }

    public void w_1484_f(double damageIn) {
        this.t_148_a = damageIn;
    }

    public double t_148_a() {
        return this.t_148_a;
    }

    public void n_1700_B(int knockbackStrengthIn) {
        this.s_956_w = knockbackStrengthIn;
    }

    @Override
    public boolean Z_735_d() {
        return false;
    }

    @Override
    protected float n_1700_B(I_1170_F poseIn, R_1815_U sizeIn) {
        return 0.13f;
    }

    public void n_1700_B(boolean critical) {
        this.n_1700_B(1, critical);
    }

    public void J_1907_R(byte level) {
        this.l_4537_E.J_1907_R(u_1723_Y, level);
    }

    private void n_1700_B(int p_203049_1_, boolean p_203049_2_) {
        byte b0 = this.l_4537_E.n_1700_B(P_1922_E);
        if (p_203049_2_) {
            this.l_4537_E.J_1907_R(P_1922_E, (byte)(b0 | p_203049_1_));
        } else {
            this.l_4537_E.J_1907_R(P_1922_E, (byte)(b0 & ~p_203049_1_));
        }
    }

    public boolean u_2550_I() {
        byte b0 = this.l_4537_E.n_1700_B(P_1922_E);
        return (b0 & 1) != 0;
    }

    public boolean h_1847_R() {
        byte b0 = this.l_4537_E.n_1700_B(P_1922_E);
        return (b0 & 4) != 0;
    }

    public byte Q_4569_t() {
        return this.l_4537_E.n_1700_B(u_1723_Y);
    }

    public void n_1700_B(r_4811_B p_190547_1_, float p_190547_2_) {
        int i = K_4096_w.n_1700_B(Enchantments.k_2293_S, p_190547_1_);
        int j = K_4096_w.n_1700_B(Enchantments.q_2307_F, p_190547_1_);
        this.w_1484_f((double)(p_190547_2_ * 2.0f) + this.RealmsWorldOptions.nextGaussian() * 0.25 + (double)((float)this.O_508_d.x_607_J().n_1700_B() * 0.11f));
        if (i > 0) {
            this.w_1484_f(this.t_148_a() + (double)i * 0.5 + 0.5);
        }
        if (j > 0) {
            this.n_1700_B(j);
        }
        if (K_4096_w.n_1700_B(Enchantments.Z_875_P, p_190547_1_) > 0) {
            this.P_1922_E(100);
        }
    }

    protected float M_182_A() {
        return 0.6f;
    }

    public void R_4764_Y(boolean noClipIn) {
        this.j_1564_a = noClipIn;
        this.n_1700_B(2, noClipIn);
    }

    public boolean multiplayerClientSuggestionProvider() {
        if (!this.O_508_d.Y_259_p) {
            return this.j_1564_a;
        }
        return (this.l_4537_E.n_1700_B(P_1922_E) & 2) != 0;
    }

    public void G_564_y(boolean fromCrossbow) {
        this.n_1700_B(4, fromCrossbow);
    }

    @Override
    public Packet<?> f_() {
        N_4263_v entity = this.Y_601_j();
        return new ClientboundAddEntityPacket(this, entity == null ? 0 : entity.j_276_v());
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] G_564_y;

        public static n_1700_B[] values() {
            return (n_1700_B[])G_564_y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        public static n_1700_B n_1700_B(int ordinal) {
            if (ordinal < 0 || ordinal > lightning.product.h_384_L$n_1700_B.values().length) {
                ordinal = 0;
            }
            return lightning.product.h_384_L$n_1700_B.values()[ordinal];
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.h_384_L$n_1700_B.n_1700_B();
        }
    }
}


