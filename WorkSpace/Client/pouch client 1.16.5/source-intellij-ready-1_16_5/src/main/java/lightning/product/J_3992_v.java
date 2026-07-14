/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.OptionalInt;
import javax.annotation.Nullable;
import lightning.product.A_4115_X;
import lightning.product.Projectile;
import lightning.product.C_4114_x;
import lightning.product.D_38_f;
import lightning.product.G_1066_I;
import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.H_2333_J;
import lightning.product.HitResult;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.L_461_d;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_2312_j;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.h_256_u;
import lightning.product.i_3196_G;
import lightning.product.EntityDataSerializers;
import lightning.product.q_2896_o;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_4990_V;
import lightning.product.ParticleTypes;
import lightning.product.Packet;
import lightning.product.t_5_h;
import lightning.product.EntityHitResult;

public class J_3992_v
extends Projectile
implements G_1066_I {
    private static final h_256_u<Z_1993_T> n_1700_B = C_4114_x.n_1700_B(J_3992_v.class, EntityDataSerializers.v_4262_N);
    private static final h_256_u<OptionalInt> J_1907_R = C_4114_x.n_1700_B(J_3992_v.class, EntityDataSerializers.multiplayerClientSuggestionProvider);
    private static final h_256_u<Boolean> R_4764_Y = C_4114_x.n_1700_B(J_3992_v.class, EntityDataSerializers.t_148_a);
    private int G_564_y;
    private int P_1922_E;
    private r_4811_B u_1723_Y;

    public J_3992_v(t_5_h<? extends J_3992_v> p_i50164_1_, b_4507_u p_i50164_2_) {
        super((t_5_h<? extends Projectile>)p_i50164_1_, p_i50164_2_);
    }

    public J_3992_v(b_4507_u worldIn, double x, double y, double z, Z_1993_T givenItem) {
        super((t_5_h<? extends Projectile>)t_5_h.H_2857_Y, worldIn);
        this.G_564_y = 0;
        this.J_1907_R(x, y, z);
        int i = 1;
        if (!givenItem.n_1700_B() && givenItem.h_1847_R()) {
            this.l_4537_E.J_1907_R(n_1700_B, givenItem.t_148_a());
            i += givenItem.n_1700_B("Fireworks").u_1723_Y("Flight");
        }
        this.h_1847_R(this.RealmsWorldOptions.nextGaussian() * 0.001, 0.05, this.RealmsWorldOptions.nextGaussian() * 0.001);
        this.P_1922_E = 10 * i + this.RealmsWorldOptions.nextInt(6) + this.RealmsWorldOptions.nextInt(7);
    }

    public J_3992_v(b_4507_u p_i231581_1_, @Nullable N_4263_v p_i231581_2_, double p_i231581_3_, double p_i231581_5_, double p_i231581_7_, Z_1993_T p_i231581_9_) {
        this(p_i231581_1_, p_i231581_3_, p_i231581_5_, p_i231581_7_, p_i231581_9_);
        this.J_1907_R(p_i231581_2_);
    }

    public J_3992_v(b_4507_u p_i47367_1_, Z_1993_T p_i47367_2_, r_4811_B p_i47367_3_) {
        this(p_i47367_1_, p_i47367_3_, p_i47367_3_.O_3598_v(), p_i47367_3_.X_2960_b(), p_i47367_3_.l_2647_k(), p_i47367_2_);
        this.l_4537_E.J_1907_R(J_1907_R, OptionalInt.of(p_i47367_3_.j_276_v()));
        this.u_1723_Y = p_i47367_3_;
    }

    public J_3992_v(b_4507_u p_i50165_1_, Z_1993_T p_i50165_2_, double p_i50165_3_, double p_i50165_5_, double p_i50165_7_, boolean p_i50165_9_) {
        this(p_i50165_1_, p_i50165_3_, p_i50165_5_, p_i50165_7_, p_i50165_2_);
        this.l_4537_E.J_1907_R(R_4764_Y, p_i50165_9_);
    }

    public J_3992_v(b_4507_u p_i231582_1_, Z_1993_T p_i231582_2_, N_4263_v p_i231582_3_, double p_i231582_4_, double p_i231582_6_, double p_i231582_8_, boolean p_i231582_10_) {
        this(p_i231582_1_, p_i231582_2_, p_i231582_4_, p_i231582_6_, p_i231582_8_, p_i231582_10_);
        this.J_1907_R(p_i231582_3_);
    }

    @Override
    protected void a_() {
        this.l_4537_E.n_1700_B(n_1700_B, Z_1993_T.J_1907_R);
        this.l_4537_E.n_1700_B(J_1907_R, OptionalInt.empty());
        this.l_4537_E.n_1700_B(R_4764_Y, false);
    }

    @Override
    public boolean n_1700_B(double distance) {
        return distance < 4096.0 && !this.t_148_a();
    }

    @Override
    public boolean t_148_a(double x, double y, double z) {
        return super.t_148_a(x, y, z) && !this.t_148_a();
    }

    @Override
    public void v_() {
        super.v_();
        if (this.t_148_a()) {
            if (this.u_1723_Y == null) {
                this.l_4537_E.n_1700_B(J_1907_R).ifPresent(p_213891_1_ -> {
                    N_4263_v entity = this.O_508_d.J_1907_R(p_213891_1_);
                    if (entity instanceof r_4811_B) {
                        this.u_1723_Y = (r_4811_B)entity;
                    }
                });
            }
            if (this.u_1723_Y != null) {
                if (this.u_1723_Y.k_578_l()) {
                    i_3196_G eventElytraRotation = new i_3196_G(this.u_1723_Y.RealmsSettingsScreen());
                    A_4115_X.n_1700_B(eventElytraRotation);
                    double d0 = 1.5;
                    double d1 = 0.1;
                    b_2312_j eventFireworkVelocity = new b_2312_j(this.u_1723_Y.I_4348_c());
                    A_4115_X.n_1700_B(eventFireworkVelocity);
                    s_4990_V event = new s_4990_V(1.5f, 0.1f, 0.5f, eventElytraRotation.n_1700_B());
                    A_4115_X.n_1700_B(event);
                    this.u_1723_Y.v_4262_N(eventFireworkVelocity.n_1700_B().J_1907_R(event.u_1723_Y().J_1907_R * (double)event.R_4764_Y() + (event.u_1723_Y().J_1907_R * (double)event.J_1907_R() - eventFireworkVelocity.n_1700_B().J_1907_R) * (double)event.G_564_y(), event.u_1723_Y().R_4764_Y * (double)event.R_4764_Y() + (event.u_1723_Y().R_4764_Y * (double)event.P_1922_E() - eventFireworkVelocity.n_1700_B().R_4764_Y) * (double)event.G_564_y(), event.u_1723_Y().G_564_y * (double)event.R_4764_Y() + (event.u_1723_Y().G_564_y * (double)event.J_1907_R() - eventFireworkVelocity.n_1700_B().G_564_y) * (double)event.G_564_y()));
                }
                this.J_1907_R(this.u_1723_Y.O_3598_v(), this.u_1723_Y.X_2960_b(), this.u_1723_Y.l_2647_k());
                this.v_4262_N(this.u_1723_Y.I_4348_c());
            }
        } else {
            if (!this.P_1922_E()) {
                double d2 = this.D_60_a ? 1.0 : 1.15;
                this.v_4262_N(this.I_4348_c().G_564_y(d2, 1.0, d2).J_1907_R(0.0, 0.04, 0.0));
            }
            e_2866_D vector3d2 = this.I_4348_c();
            this.n_1700_B(L_461_d.n_1700_B, vector3d2);
            this.v_4262_N(vector3d2);
        }
        HitResult raytraceresult = H_2333_J.n_1700_B((N_4263_v)this, this::n_1700_B);
        if (!this.j_1564_a) {
            this.n_1700_B(raytraceresult);
            this.LongRunningTask = true;
        }
        this.Y_259_p();
        if (this.G_564_y == 0 && !this.y_1700_S()) {
            this.O_508_d.n_1700_B((a_3913_L)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.e_1231_S, D_38_f.t_148_a, 3.0f, 1.0f);
        }
        ++this.G_564_y;
        if (this.O_508_d.Y_259_p && this.G_564_y % 2 < 2) {
            this.O_508_d.n_1700_B(ParticleTypes.q_2307_F, this.O_3598_v(), this.X_2960_b() - 0.3, this.l_2647_k(), this.RealmsWorldOptions.nextGaussian() * 0.05, -this.I_4348_c().R_4764_Y * 0.5, this.RealmsWorldOptions.nextGaussian() * 0.05);
        }
        if (!this.O_508_d.Y_259_p && this.G_564_y > this.P_1922_E) {
            this.u_1723_Y();
        }
    }

    private void u_1723_Y() {
        this.O_508_d.n_1700_B((N_4263_v)this, (byte)17);
        this.w_1484_f();
        this.Ops();
    }

    @Override
    protected void n_1700_B(EntityHitResult p_213868_1_) {
        super.n_1700_B(p_213868_1_);
        if (!this.O_508_d.Y_259_p) {
            this.u_1723_Y();
        }
    }

    @Override
    protected void n_1700_B(BlockHitResult p_230299_1_) {
        c_1514_x blockpos = new c_1514_x(p_230299_1_.n_1700_B());
        this.O_508_d.getBlockState(blockpos).n_1700_B(this.O_508_d, blockpos, (N_4263_v)this);
        if (!this.O_508_d.v_4276_D() && this.v_4262_N()) {
            this.u_1723_Y();
        }
        super.n_1700_B(p_230299_1_);
    }

    private boolean v_4262_N() {
        Z_1993_T itemstack = this.l_4537_E.n_1700_B(n_1700_B);
        U_2912_j compoundnbt = itemstack.n_1700_B() ? null : itemstack.J_1907_R("Fireworks");
        q_2896_o listnbt = compoundnbt != null ? compoundnbt.G_564_y("Explosions", 10) : null;
        return listnbt != null && !listnbt.isEmpty();
    }

    private void w_1484_f() {
        q_2896_o listnbt;
        float f = 0.0f;
        Z_1993_T itemstack = this.l_4537_E.n_1700_B(n_1700_B);
        U_2912_j compoundnbt = itemstack.n_1700_B() ? null : itemstack.J_1907_R("Fireworks");
        q_2896_o q_2896_o2 = listnbt = compoundnbt != null ? compoundnbt.G_564_y("Explosions", 10) : null;
        if (listnbt != null && !listnbt.isEmpty()) {
            f = 5.0f + (float)(listnbt.size() * 2);
        }
        if (f > 0.0f) {
            if (this.u_1723_Y != null) {
                this.u_1723_Y.n_1700_B(P_11_z.n_1700_B(this, this.Y_601_j()), 5.0f + (float)(listnbt.size() * 2));
            }
            double d0 = 5.0;
            e_2866_D vector3d = this.s_4990_V();
            for (r_4811_B livingentity : this.O_508_d.n_1700_B(r_4811_B.class, this.i_601_W().grow(5.0))) {
                if (livingentity == this.u_1723_Y || this.G_564_y(livingentity) > 25.0) continue;
                boolean flag = false;
                for (int i = 0; i < 2; ++i) {
                    e_2866_D vector3d1 = new e_2866_D(livingentity.O_3598_v(), livingentity.P_1922_E(0.5 * (double)i), livingentity.l_2647_k());
                    BlockHitResult raytraceresult = this.O_508_d.n_1700_B(new ClipContext(vector3d, vector3d1, ClipContext.n_1700_B.n_1700_B, ClipContext.J_1907_R.n_1700_B, this));
                    if (((HitResult)raytraceresult).R_4764_Y() != HitResult.n_1700_B.n_1700_B) continue;
                    flag = true;
                    break;
                }
                if (!flag) continue;
                float f1 = f * (float)Math.sqrt((5.0 - (double)this.R_4764_Y(livingentity)) / 5.0);
                livingentity.n_1700_B(P_11_z.n_1700_B(this, this.Y_601_j()), f1);
            }
        }
    }

    private boolean t_148_a() {
        return this.l_4537_E.n_1700_B(J_1907_R).isPresent();
    }

    public boolean P_1922_E() {
        return this.l_4537_E.n_1700_B(R_4764_Y);
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 17 && this.O_508_d.Y_259_p) {
            if (!this.v_4262_N()) {
                for (int i = 0; i < this.RealmsWorldOptions.nextInt(3) + 2; ++i) {
                    this.O_508_d.n_1700_B(ParticleTypes.z_4693_k, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), this.RealmsWorldOptions.nextGaussian() * 0.05, 0.005, this.RealmsWorldOptions.nextGaussian() * 0.05);
                }
            } else {
                Z_1993_T itemstack = this.l_4537_E.n_1700_B(n_1700_B);
                U_2912_j compoundnbt = itemstack.n_1700_B() ? null : itemstack.J_1907_R("Fireworks");
                e_2866_D vector3d = this.I_4348_c();
                this.O_508_d.n_1700_B(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y, compoundnbt);
            }
        }
        super.n_1700_B(id);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("Life", this.G_564_y);
        compound.J_1907_R("LifeTime", this.P_1922_E);
        Z_1993_T itemstack = this.l_4537_E.n_1700_B(n_1700_B);
        if (!itemstack.n_1700_B()) {
            compound.n_1700_B("FireworksItem", itemstack.J_1907_R(new U_2912_j()));
        }
        compound.n_1700_B("ShotAtAngle", this.l_4537_E.n_1700_B(R_4764_Y));
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.G_564_y = compound.w_1484_f("Life");
        this.P_1922_E = compound.w_1484_f("LifeTime");
        Z_1993_T itemstack = Z_1993_T.n_1700_B(compound.M_182_A("FireworksItem"));
        if (!itemstack.n_1700_B()) {
            this.l_4537_E.J_1907_R(n_1700_B, itemstack);
        }
        if (compound.P_1922_E("ShotAtAngle")) {
            this.l_4537_E.J_1907_R(R_4764_Y, compound.t_1786_h("ShotAtAngle"));
        }
    }

    @Override
    public Z_1993_T n_1700_B() {
        Z_1993_T itemstack = this.l_4537_E.n_1700_B(n_1700_B);
        return itemstack.n_1700_B() ? new Z_1993_T(Items.FenceBlock) : itemstack;
    }

    @Override
    public boolean Z_735_d() {
        return false;
    }

    @Override
    public Packet<?> f_() {
        return new ClientboundAddEntityPacket(this);
    }
}


