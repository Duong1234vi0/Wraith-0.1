/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.Projectile;
import lightning.product.D_38_f;
import lightning.product.BlockHitResult;
import lightning.product.H_2333_J;
import lightning.product.HitResult;
import lightning.product.MobEffects;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_2450_T;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.k_2610_C;
import lightning.product.r_4811_B;
import lightning.product.ParticleTypes;
import lightning.product.Packet;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.EntityHitResult;

public class L_2837_o
extends Projectile {
    private N_4263_v n_1700_B;
    @Nullable
    private b_257_Y J_1907_R;
    private int R_4764_Y;
    private double G_564_y;
    private double P_1922_E;
    private double u_1723_Y;
    @Nullable
    private UUID v_4262_N;

    public L_2837_o(t_5_h<? extends L_2837_o> p_i50161_1_, b_4507_u p_i50161_2_) {
        super((t_5_h<? extends Projectile>)p_i50161_1_, p_i50161_2_);
        this.j_1564_a = true;
    }

    public L_2837_o(b_4507_u worldIn, double x, double y, double z, double motionXIn, double motionYIn, double motionZIn) {
        this((t_5_h<? extends L_2837_o>)t_5_h.h_4320_q, worldIn);
        this.J_1907_R(x, y, z, this.p_178_J, this.f_4016_n);
        this.h_1847_R(motionXIn, motionYIn, motionZIn);
    }

    public L_2837_o(b_4507_u worldIn, r_4811_B ownerIn, N_4263_v targetIn, b_257_Y.n_1700_B p_i46772_4_) {
        this((t_5_h<? extends L_2837_o>)t_5_h.h_4320_q, worldIn);
        this.J_1907_R(ownerIn);
        c_1514_x blockpos = ownerIn.b_2312_j();
        double d0 = (double)blockpos.getX() + 0.5;
        double d1 = (double)blockpos.getY() + 0.5;
        double d2 = (double)blockpos.getZ() + 0.5;
        this.J_1907_R(d0, d1, d2, this.p_178_J, this.f_4016_n);
        this.n_1700_B = targetIn;
        this.J_1907_R = b_257_Y.J_1907_R;
        this.n_1700_B(p_i46772_4_);
    }

    @Override
    public D_38_f r_2478_U() {
        return D_38_f.u_1723_Y;
    }

    @Override
    protected void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        if (this.n_1700_B != null) {
            compound.n_1700_B("Target", this.n_1700_B.w_2705_t());
        }
        if (this.J_1907_R != null) {
            compound.J_1907_R("Dir", this.J_1907_R.R_4764_Y());
        }
        compound.J_1907_R("Steps", this.R_4764_Y);
        compound.n_1700_B("TXD", this.G_564_y);
        compound.n_1700_B("TYD", this.P_1922_E);
        compound.n_1700_B("TZD", this.u_1723_Y);
    }

    @Override
    protected void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.R_4764_Y = compound.w_1484_f("Steps");
        this.G_564_y = compound.u_2550_I("TXD");
        this.P_1922_E = compound.u_2550_I("TYD");
        this.u_1723_Y = compound.u_2550_I("TZD");
        if (compound.R_4764_Y("Dir", 99)) {
            this.J_1907_R = b_257_Y.n_1700_B(compound.w_1484_f("Dir"));
        }
        if (compound.J_1907_R("Target")) {
            this.v_4262_N = compound.n_1700_B("Target");
        }
    }

    @Override
    protected void a_() {
    }

    private void n_1700_B(@Nullable b_257_Y directionIn) {
        this.J_1907_R = directionIn;
    }

    private void n_1700_B(@Nullable b_257_Y.n_1700_B p_184569_1_) {
        c_1514_x blockpos;
        double d0 = 0.5;
        if (this.n_1700_B == null) {
            blockpos = this.b_2312_j().down();
        } else {
            d0 = (double)this.n_1700_B.v_165_F() * 0.5;
            blockpos = new c_1514_x(this.n_1700_B.O_3598_v(), this.n_1700_B.X_2960_b() + d0, this.n_1700_B.l_2647_k());
        }
        double d1 = (double)blockpos.getX() + 0.5;
        double d2 = (double)blockpos.getY() + d0;
        double d3 = (double)blockpos.getZ() + 0.5;
        b_257_Y direction = null;
        if (!blockpos.withinDistance(this.s_4990_V(), 2.0)) {
            c_1514_x blockpos1 = this.b_2312_j();
            ArrayList list = Lists.newArrayList();
            if (p_184569_1_ != b_257_Y.n_1700_B.n_1700_B) {
                if (blockpos1.getX() < blockpos.getX() && this.O_508_d.u_1723_Y(blockpos1.east())) {
                    list.add(b_257_Y.u_1723_Y);
                } else if (blockpos1.getX() > blockpos.getX() && this.O_508_d.u_1723_Y(blockpos1.west())) {
                    list.add(b_257_Y.P_1922_E);
                }
            }
            if (p_184569_1_ != b_257_Y.n_1700_B.J_1907_R) {
                if (blockpos1.getY() < blockpos.getY() && this.O_508_d.u_1723_Y(blockpos1.up())) {
                    list.add(b_257_Y.J_1907_R);
                } else if (blockpos1.getY() > blockpos.getY() && this.O_508_d.u_1723_Y(blockpos1.down())) {
                    list.add(b_257_Y.n_1700_B);
                }
            }
            if (p_184569_1_ != b_257_Y.n_1700_B.R_4764_Y) {
                if (blockpos1.getZ() < blockpos.getZ() && this.O_508_d.u_1723_Y(blockpos1.south())) {
                    list.add(b_257_Y.G_564_y);
                } else if (blockpos1.getZ() > blockpos.getZ() && this.O_508_d.u_1723_Y(blockpos1.north())) {
                    list.add(b_257_Y.R_4764_Y);
                }
            }
            direction = b_257_Y.n_1700_B(this.RealmsWorldOptions);
            if (list.isEmpty()) {
                for (int i = 5; !this.O_508_d.u_1723_Y(blockpos1.offset(direction)) && i > 0; --i) {
                    direction = b_257_Y.n_1700_B(this.RealmsWorldOptions);
                }
            } else {
                direction = (b_257_Y)list.get(this.RealmsWorldOptions.nextInt(list.size()));
            }
            d1 = this.O_3598_v() + (double)direction.t_148_a();
            d2 = this.X_2960_b() + (double)direction.s_956_w();
            d3 = this.l_2647_k() + (double)direction.u_2550_I();
        }
        this.n_1700_B(direction);
        double d6 = d1 - this.O_3598_v();
        double d7 = d2 - this.X_2960_b();
        double d4 = d3 - this.l_2647_k();
        double d5 = u_530_F.n_1700_B(d6 * d6 + d7 * d7 + d4 * d4);
        if (d5 == 0.0) {
            this.G_564_y = 0.0;
            this.P_1922_E = 0.0;
            this.u_1723_Y = 0.0;
        } else {
            this.G_564_y = d6 / d5 * 0.15;
            this.P_1922_E = d7 / d5 * 0.15;
            this.u_1723_Y = d4 / d5 * 0.15;
        }
        this.LongRunningTask = true;
        this.R_4764_Y = 10 + this.RealmsWorldOptions.nextInt(5) * 10;
    }

    @Override
    public void a_178_J() {
        if (this.O_508_d.x_607_J() == R_2450_T.n_1700_B) {
            this.Ops();
        }
    }

    @Override
    public void v_() {
        super.v_();
        if (!this.O_508_d.Y_259_p) {
            HitResult raytraceresult;
            if (this.n_1700_B == null && this.v_4262_N != null) {
                this.n_1700_B = ((e_3591_l)this.O_508_d).J_1907_R(this.v_4262_N);
                if (this.n_1700_B == null) {
                    this.v_4262_N = null;
                }
            }
            if (this.n_1700_B == null || !this.n_1700_B.RealmsLongRunningMcoTaskScreen() || this.n_1700_B instanceof a_3913_L && ((a_3913_L)this.n_1700_B).d_2461_k()) {
                if (!this.u_744_e()) {
                    this.v_4262_N(this.I_4348_c().J_1907_R(0.0, -0.04, 0.0));
                }
            } else {
                this.G_564_y = u_530_F.n_1700_B(this.G_564_y * 1.025, -1.0, 1.0);
                this.P_1922_E = u_530_F.n_1700_B(this.P_1922_E * 1.025, -1.0, 1.0);
                this.u_1723_Y = u_530_F.n_1700_B(this.u_1723_Y * 1.025, -1.0, 1.0);
                e_2866_D vector3d = this.I_4348_c();
                this.v_4262_N(vector3d.J_1907_R((this.G_564_y - vector3d.J_1907_R) * 0.2, (this.P_1922_E - vector3d.R_4764_Y) * 0.2, (this.u_1723_Y - vector3d.G_564_y) * 0.2));
            }
            if ((raytraceresult = H_2333_J.n_1700_B((N_4263_v)this, this::n_1700_B)).R_4764_Y() != HitResult.n_1700_B.n_1700_B) {
                this.n_1700_B(raytraceresult);
            }
        }
        this.F_2624_D();
        e_2866_D vector3d1 = this.I_4348_c();
        this.J_1907_R(this.O_3598_v() + vector3d1.J_1907_R, this.X_2960_b() + vector3d1.R_4764_Y, this.l_2647_k() + vector3d1.G_564_y);
        H_2333_J.n_1700_B((N_4263_v)this, 0.5f);
        if (this.O_508_d.Y_259_p) {
            this.O_508_d.n_1700_B(ParticleTypes.Y_601_j, this.O_3598_v() - vector3d1.J_1907_R, this.X_2960_b() - vector3d1.R_4764_Y + 0.15, this.l_2647_k() - vector3d1.G_564_y, 0.0, 0.0, 0.0);
        } else if (this.n_1700_B != null && !this.n_1700_B.t_4219_U) {
            if (this.R_4764_Y > 0) {
                --this.R_4764_Y;
                if (this.R_4764_Y == 0) {
                    this.n_1700_B(this.J_1907_R == null ? null : this.J_1907_R.h_1847_R());
                }
            }
            if (this.J_1907_R != null) {
                c_1514_x blockpos = this.b_2312_j();
                b_257_Y.n_1700_B direction$axis = this.J_1907_R.h_1847_R();
                if (this.O_508_d.n_1700_B(blockpos.offset(this.J_1907_R), (N_4263_v)this)) {
                    this.n_1700_B(direction$axis);
                } else {
                    c_1514_x blockpos1 = this.n_1700_B.b_2312_j();
                    if (direction$axis == b_257_Y.n_1700_B.n_1700_B && blockpos.getX() == blockpos1.getX() || direction$axis == b_257_Y.n_1700_B.R_4764_Y && blockpos.getZ() == blockpos1.getZ() || direction$axis == b_257_Y.n_1700_B.J_1907_R && blockpos.getY() == blockpos1.getY()) {
                        this.n_1700_B(direction$axis);
                    }
                }
            }
        }
    }

    @Override
    protected boolean n_1700_B(N_4263_v p_230298_1_) {
        return super.n_1700_B(p_230298_1_) && !p_230298_1_.j_1564_a;
    }

    @Override
    public boolean RealmsPersistence() {
        return false;
    }

    @Override
    public boolean n_1700_B(double distance) {
        return distance < 16384.0;
    }

    @Override
    public float RealmsConfirmScreen() {
        return 1.0f;
    }

    @Override
    protected void n_1700_B(EntityHitResult p_213868_1_) {
        super.n_1700_B(p_213868_1_);
        N_4263_v entity = p_213868_1_.n_1700_B();
        N_4263_v entity1 = this.Y_601_j();
        r_4811_B livingentity = entity1 instanceof r_4811_B ? (r_4811_B)entity1 : null;
        boolean flag = entity.n_1700_B(P_11_z.n_1700_B((N_4263_v)this, livingentity).R_4764_Y(), 4.0f);
        if (flag) {
            this.n_1700_B(livingentity, entity);
            if (entity instanceof r_4811_B) {
                ((r_4811_B)entity).n_1700_B(new k_2610_C(MobEffects.q_2307_F, 200));
            }
        }
    }

    @Override
    protected void n_1700_B(BlockHitResult p_230299_1_) {
        super.n_1700_B(p_230299_1_);
        ((e_3591_l)this.O_508_d).n_1700_B(ParticleTypes.C_2741_M, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), 2, 0.2, 0.2, 0.2, 0.0);
        this.n_1700_B(SoundEvents.K_4518_s, 1.0f, 1.0f);
    }

    @Override
    protected void n_1700_B(HitResult result) {
        super.n_1700_B(result);
        this.Ops();
    }

    @Override
    public boolean C_290_v() {
        return true;
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (!this.O_508_d.Y_259_p) {
            this.n_1700_B(SoundEvents.LightPredicate, 1.0f, 1.0f);
            ((e_3591_l)this.O_508_d).n_1700_B(ParticleTypes.v_4262_N, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), 15, 0.2, 0.2, 0.2, 0.0);
            this.Ops();
        }
        return true;
    }

    @Override
    public Packet<?> f_() {
        return new ClientboundAddEntityPacket(this);
    }
}


