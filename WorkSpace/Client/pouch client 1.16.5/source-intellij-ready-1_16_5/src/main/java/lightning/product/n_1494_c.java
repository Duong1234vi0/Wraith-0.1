/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Objects;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.FluidTags;
import lightning.product.C_4114_x;
import lightning.product.F_2904_S;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.L_461_d;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.Stats;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.Packet;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.x_282_a;

public class n_1494_c
extends N_4263_v {
    private static final h_256_u<Z_1993_T> J_1907_R = C_4114_x.n_1700_B(n_1494_c.class, EntityDataSerializers.v_4262_N);
    private int R_4764_Y;
    private int G_564_y;
    private int P_1922_E = 5;
    private UUID u_1723_Y;
    private UUID v_4262_N;
    public final float n_1700_B;

    public n_1494_c(t_5_h<? extends n_1494_c> p_i50217_1_, b_4507_u world) {
        super(p_i50217_1_, world);
        this.n_1700_B = (float)(Math.random() * Math.PI * 2.0);
    }

    public n_1494_c(b_4507_u worldIn, double x, double y, double z) {
        this((t_5_h<? extends n_1494_c>)t_5_h.d_2461_k, worldIn);
        this.J_1907_R(x, y, z);
        this.p_178_J = this.RealmsWorldOptions.nextFloat() * 360.0f;
        this.h_1847_R(this.RealmsWorldOptions.nextDouble() * 0.2 - 0.1, 0.2, this.RealmsWorldOptions.nextDouble() * 0.2 - 0.1);
    }

    public n_1494_c(b_4507_u worldIn, double x, double y, double z, Z_1993_T stack) {
        this(worldIn, x, y, z);
        this.J_1907_R(stack);
    }

    private n_1494_c(n_1494_c p_i231561_1_) {
        super(p_i231561_1_.f_4016_n(), p_i231561_1_.O_508_d);
        this.J_1907_R(p_i231561_1_.P_1922_E().t_148_a());
        this.multiplayerClientSuggestionProvider(p_i231561_1_);
        this.R_4764_Y = p_i231561_1_.R_4764_Y;
        this.n_1700_B = p_i231561_1_.n_1700_B;
    }

    @Override
    protected boolean RetryCallException() {
        return false;
    }

    @Override
    protected void a_() {
        this.D_60_a().n_1700_B(J_1907_R, Z_1993_T.J_1907_R);
    }

    @Override
    public void v_() {
        if (this.P_1922_E().n_1700_B()) {
            this.Ops();
        } else {
            double d0;
            int i;
            super.v_();
            if (this.G_564_y > 0 && this.G_564_y != Short.MAX_VALUE) {
                --this.G_564_y;
            }
            this.r_715_M = this.O_3598_v();
            this.A_1038_p = this.X_2960_b();
            this.i_1637_u = this.l_2647_k();
            e_2866_D vector3d = this.I_4348_c();
            float f = this.X_1313_W() - 0.11111111f;
            if (this.RowButton() && this.J_1907_R(FluidTags.J_1907_R) > (double)f) {
                this.Y_601_j();
            } else if (this.W_3464_O() && this.J_1907_R(FluidTags.R_4764_Y) > (double)f) {
                this.Y_259_p();
            } else if (!this.u_744_e()) {
                this.v_4262_N(this.I_4348_c().J_1907_R(0.0, -0.04, 0.0));
            }
            if (this.O_508_d.Y_259_p) {
                this.j_1564_a = false;
            } else {
                boolean bl = this.j_1564_a = !this.O_508_d.u_1723_Y(this);
                if (this.j_1564_a) {
                    this.u_2550_I(this.O_3598_v(), (this.i_601_W().minY + this.i_601_W().maxY) / 2.0, this.l_2647_k());
                }
            }
            if (!this.e_1992_r || n_1494_c.R_4764_Y(this.I_4348_c()) > (double)1.0E-5f || (this.RealmsWorldResetDto + this.j_276_v()) % 4 == 0) {
                this.n_1700_B(L_461_d.n_1700_B, this.I_4348_c());
                float f1 = 0.98f;
                if (this.e_1992_r) {
                    f1 = this.O_508_d.getBlockState(new c_1514_x(this.O_3598_v(), this.X_2960_b() - 1.0, this.l_2647_k())).J_1907_R().h_1847_R() * 0.98f;
                }
                this.v_4262_N(this.I_4348_c().G_564_y(f1, 0.98, f1));
                if (this.e_1992_r) {
                    e_2866_D vector3d1 = this.I_4348_c();
                    if (vector3d1.R_4764_Y < 0.0) {
                        this.v_4262_N(vector3d1.G_564_y(1.0, -0.5, 1.0));
                    }
                }
            }
            boolean flag = u_530_F.R_4764_Y(this.r_715_M) != u_530_F.R_4764_Y(this.O_3598_v()) || u_530_F.R_4764_Y(this.A_1038_p) != u_530_F.R_4764_Y(this.X_2960_b()) || u_530_F.R_4764_Y(this.i_1637_u) != u_530_F.R_4764_Y(this.l_2647_k());
            int n = i = flag ? 2 : 40;
            if (this.RealmsWorldResetDto % i == 0) {
                if (this.O_508_d.getFluidState(this.b_2312_j()).n_1700_B(FluidTags.R_4764_Y) && !this.r_3651_U()) {
                    this.n_1700_B(SoundEvents.y_2622_c, 0.4f, 2.0f + this.RealmsWorldOptions.nextFloat() * 0.4f);
                }
                if (!this.O_508_d.Y_259_p && this.C_2741_M()) {
                    this.Q_2552_b();
                }
            }
            if (this.R_4764_Y != Short.MIN_VALUE) {
                ++this.R_4764_Y;
            }
            this.LongRunningTask |= this.RealmsScreenWithCallback();
            if (!this.O_508_d.Y_259_p && (d0 = this.I_4348_c().G_564_y(vector3d).v_4262_N()) > 0.01) {
                this.LongRunningTask = true;
            }
            if (!this.O_508_d.Y_259_p && this.R_4764_Y >= 6000) {
                this.Ops();
            }
        }
    }

    private void Y_601_j() {
        e_2866_D vector3d = this.I_4348_c();
        this.h_1847_R(vector3d.J_1907_R * (double)0.99f, vector3d.R_4764_Y + (double)(vector3d.R_4764_Y < (double)0.06f ? 5.0E-4f : 0.0f), vector3d.G_564_y * (double)0.99f);
    }

    private void Y_259_p() {
        e_2866_D vector3d = this.I_4348_c();
        this.h_1847_R(vector3d.J_1907_R * (double)0.95f, vector3d.R_4764_Y + (double)(vector3d.R_4764_Y < (double)0.06f ? 5.0E-4f : 0.0f), vector3d.G_564_y * (double)0.95f);
    }

    private void Q_2552_b() {
        if (this.C_2741_M()) {
            for (n_1494_c itementity : this.O_508_d.n_1700_B(n_1494_c.class, this.i_601_W().grow(0.5, 0.0, 0.5), (? super T p_213859_1_) -> p_213859_1_ != this && p_213859_1_.C_2741_M())) {
                if (!itementity.C_2741_M()) continue;
                this.n_1700_B(itementity);
                if (!this.t_4219_U) continue;
                break;
            }
        }
    }

    private boolean C_2741_M() {
        Z_1993_T itemstack = this.P_1922_E();
        return this.RealmsLongRunningMcoTaskScreen() && this.G_564_y != Short.MAX_VALUE && this.R_4764_Y != Short.MIN_VALUE && this.R_4764_Y < 6000 && itemstack.t_4043_B() < itemstack.R_4764_Y();
    }

    private void n_1700_B(n_1494_c item) {
        Z_1993_T itemstack = this.P_1922_E();
        Z_1993_T itemstack1 = item.P_1922_E();
        if (Objects.equals(this.u_1723_Y(), item.u_1723_Y()) && n_1494_c.n_1700_B(itemstack, itemstack1)) {
            if (itemstack1.t_4043_B() < itemstack.t_4043_B()) {
                n_1494_c.n_1700_B(this, itemstack, item, itemstack1);
            } else {
                n_1494_c.n_1700_B(item, itemstack1, this, itemstack);
            }
        }
    }

    public static boolean n_1700_B(Z_1993_T stack1, Z_1993_T stack2) {
        if (stack2.J_1907_R() != stack1.J_1907_R()) {
            return false;
        }
        if (stack2.t_4043_B() + stack1.t_4043_B() > stack2.R_4764_Y()) {
            return false;
        }
        if (stack2.h_1847_R() ^ stack1.h_1847_R()) {
            return false;
        }
        return !stack2.h_1847_R() || stack2.Q_4569_t().equals(stack1.Q_4569_t());
    }

    public static Z_1993_T n_1700_B(Z_1993_T stack1, Z_1993_T stack2, int p_226533_2_) {
        int i = Math.min(Math.min(stack1.R_4764_Y(), p_226533_2_) - stack1.t_4043_B(), stack2.t_4043_B());
        Z_1993_T itemstack = stack1.t_148_a();
        itemstack.u_1723_Y(i);
        stack2.v_4262_N(i);
        return itemstack;
    }

    private static void n_1700_B(n_1494_c entity, Z_1993_T stack1, Z_1993_T stack2) {
        Z_1993_T itemstack = n_1494_c.n_1700_B(stack1, stack2, 64);
        entity.J_1907_R(itemstack);
    }

    private static void n_1700_B(n_1494_c entity1, Z_1993_T stack1, n_1494_c entity2, Z_1993_T stack2) {
        n_1494_c.n_1700_B(entity1, stack1, stack2);
        entity1.G_564_y = Math.max(entity1.G_564_y, entity2.G_564_y);
        entity1.R_4764_Y = Math.min(entity1.R_4764_Y, entity2.R_4764_Y);
        if (stack2.n_1700_B()) {
            entity2.Ops();
        }
    }

    @Override
    public boolean r_3651_U() {
        return this.P_1922_E().J_1907_R().C_2741_M() || super.r_3651_U();
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (this.n_1700_B(source)) {
            return false;
        }
        if (!this.P_1922_E().n_1700_B() && this.P_1922_E().J_1907_R() == Items.FallingBlock && source.G_564_y()) {
            return false;
        }
        if (!this.P_1922_E().J_1907_R().n_1700_B(source)) {
            return false;
        }
        this.RealmsCreateRealmScreen();
        this.P_1922_E = (int)((float)this.P_1922_E - amount);
        if (this.P_1922_E <= 0) {
            this.Ops();
        }
        return false;
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        compound.n_1700_B("Health", (short)this.P_1922_E);
        compound.n_1700_B("Age", (short)this.R_4764_Y);
        compound.n_1700_B("PickupDelay", (short)this.G_564_y);
        if (this.v_4262_N() != null) {
            compound.n_1700_B("Thrower", this.v_4262_N());
        }
        if (this.u_1723_Y() != null) {
            compound.n_1700_B("Owner", this.u_1723_Y());
        }
        if (!this.P_1922_E().n_1700_B()) {
            compound.n_1700_B("Item", this.P_1922_E().J_1907_R(new U_2912_j()));
        }
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        this.P_1922_E = compound.v_4262_N("Health");
        this.R_4764_Y = compound.v_4262_N("Age");
        if (compound.P_1922_E("PickupDelay")) {
            this.G_564_y = compound.v_4262_N("PickupDelay");
        }
        if (compound.J_1907_R("Owner")) {
            this.v_4262_N = compound.n_1700_B("Owner");
        }
        if (compound.J_1907_R("Thrower")) {
            this.u_1723_Y = compound.n_1700_B("Thrower");
        }
        U_2912_j compoundnbt = compound.M_182_A("Item");
        this.J_1907_R(Z_1993_T.n_1700_B(compoundnbt));
        if (this.P_1922_E().n_1700_B()) {
            this.Ops();
        }
    }

    @Override
    public void c_(a_3913_L entityIn) {
        if (!this.O_508_d.Y_259_p) {
            Z_1993_T itemstack = this.P_1922_E();
            q_1613_l item = itemstack.J_1907_R();
            int i = itemstack.t_4043_B();
            if (this.G_564_y == 0 && (this.v_4262_N == null || this.v_4262_N.equals(entityIn.w_2705_t())) && entityIn.l_1268_F.P_1922_E(itemstack)) {
                entityIn.n_1700_B((N_4263_v)this, i);
                if (itemstack.n_1700_B()) {
                    this.Ops();
                    itemstack.P_1922_E(i);
                }
                entityIn.n_1700_B(Stats.P_1922_E.J_1907_R(item), i);
                entityIn.n_1700_B(this);
            }
        }
    }

    @Override
    public x_282_a O_1309_Q() {
        x_282_a itextcomponent = this.k_2302_P();
        return itextcomponent != null ? itextcomponent : new F_2904_S(this.P_1922_E().s_956_w());
    }

    @Override
    public boolean Z_735_d() {
        return false;
    }

    @Override
    @Nullable
    public N_4263_v n_1700_B(e_3591_l server) {
        N_4263_v entity = super.n_1700_B(server);
        if (!this.O_508_d.Y_259_p && entity instanceof n_1494_c) {
            ((n_1494_c)entity).Q_2552_b();
        }
        return entity;
    }

    public Z_1993_T P_1922_E() {
        return this.D_60_a().n_1700_B(J_1907_R);
    }

    public void J_1907_R(Z_1993_T stack) {
        this.D_60_a().J_1907_R(J_1907_R, stack);
    }

    @Override
    public void n_1700_B(h_256_u<?> key) {
        super.n_1700_B(key);
        if (J_1907_R.equals(key)) {
            this.P_1922_E().n_1700_B(this);
        }
    }

    @Nullable
    public UUID u_1723_Y() {
        return this.v_4262_N;
    }

    public void J_1907_R(@Nullable UUID ownerId) {
        this.v_4262_N = ownerId;
    }

    @Nullable
    public UUID v_4262_N() {
        return this.u_1723_Y;
    }

    public void R_4764_Y(@Nullable UUID throwerId) {
        this.u_1723_Y = throwerId;
    }

    public int w_1484_f() {
        return this.R_4764_Y;
    }

    public void t_148_a() {
        this.G_564_y = 10;
    }

    public void u_2550_I() {
        this.G_564_y = 0;
    }

    public void h_1847_R() {
        this.G_564_y = Short.MAX_VALUE;
    }

    public void n_1700_B(int ticks) {
        this.G_564_y = ticks;
    }

    public boolean Q_4569_t() {
        return this.G_564_y > 0;
    }

    public void M_182_A() {
        this.R_4764_Y = -6000;
    }

    public void multiplayerClientSuggestionProvider() {
        this.h_1847_R();
        this.R_4764_Y = 5999;
    }

    public float n_1700_B(float partialTicks) {
        return ((float)this.w_1484_f() + partialTicks) / 20.0f + this.n_1700_B;
    }

    @Override
    public Packet<?> f_() {
        return new ClientboundAddEntityPacket(this);
    }

    public n_1494_c w_1457_N() {
        return new n_1494_c(this);
    }
}


