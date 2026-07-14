/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.Validate
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.C_4114_x;
import lightning.product.F_3620_e;
import lightning.product.G_3165_y;
import lightning.product.I_1170_F;
import lightning.product.I_4817_s;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.K_4074_S;
import lightning.product.L_461_d;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.P_2973_E;
import lightning.product.R_1815_U;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.m_3054_I;
import lightning.product.Items;
import lightning.product.Packet;
import lightning.product.t_5_h;
import lightning.product.u_782_h;
import lightning.product.x_1688_C;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class y_740_d
extends P_2973_E {
    private static final Logger G_564_y = LogManager.getLogger();
    private static final h_256_u<Z_1993_T> P_1922_E = C_4114_x.n_1700_B(y_740_d.class, EntityDataSerializers.v_4262_N);
    private static final h_256_u<Integer> u_1723_Y = C_4114_x.n_1700_B(y_740_d.class, EntityDataSerializers.J_1907_R);
    private float v_4262_N = 1.0f;
    private boolean w_1484_f;

    public y_740_d(t_5_h<? extends y_740_d> p_i50224_1_, b_4507_u world) {
        super((t_5_h<? extends P_2973_E>)p_i50224_1_, world);
    }

    public y_740_d(b_4507_u worldIn, c_1514_x pos, b_257_Y facing) {
        super(t_5_h.G_624_v, worldIn, pos);
        this.n_1700_B(facing);
    }

    @Override
    protected float n_1700_B(I_1170_F poseIn, R_1815_U sizeIn) {
        return 0.0f;
    }

    @Override
    protected void a_() {
        this.D_60_a().n_1700_B(P_1922_E, Z_1993_T.J_1907_R);
        this.D_60_a().n_1700_B(u_1723_Y, 0);
    }

    @Override
    protected void n_1700_B(b_257_Y facingDirectionIn) {
        Validate.notNull((Object)facingDirectionIn);
        this.R_4764_Y = facingDirectionIn;
        if (facingDirectionIn.h_1847_R().G_564_y()) {
            this.f_4016_n = 0.0f;
            this.p_178_J = this.R_4764_Y.G_564_y() * 90;
        } else {
            this.f_4016_n = -90 * facingDirectionIn.P_1922_E().n_1700_B();
            this.p_178_J = 0.0f;
        }
        this.UploadStatus = this.f_4016_n;
        this.j_276_v = this.p_178_J;
        this.P_1922_E();
    }

    @Override
    protected void P_1922_E() {
        if (this.R_4764_Y != null) {
            double d0 = 0.46875;
            double d1 = (double)this.J_1907_R.getX() + 0.5 - (double)this.R_4764_Y.t_148_a() * 0.46875;
            double d2 = (double)this.J_1907_R.getY() + 0.5 - (double)this.R_4764_Y.s_956_w() * 0.46875;
            double d3 = (double)this.J_1907_R.getZ() + 0.5 - (double)this.R_4764_Y.u_2550_I() * 0.46875;
            this.Q_4569_t(d1, d2, d3);
            double d4 = this.v_4262_N();
            double d5 = this.w_1484_f();
            double d6 = this.v_4262_N();
            b_257_Y.n_1700_B direction$axis = this.R_4764_Y.h_1847_R();
            switch (direction$axis) {
                case n_1700_B: {
                    d4 = 1.0;
                    break;
                }
                case J_1907_R: {
                    d5 = 1.0;
                    break;
                }
                case R_4764_Y: {
                    d6 = 1.0;
                }
            }
            this.n_1700_B(new I_4817_s(d1 - (d4 /= 32.0), d2 - (d5 /= 32.0), d3 - (d6 /= 32.0), d1 + d4, d2 + d5, d3 + d6));
        }
    }

    @Override
    public boolean u_1723_Y() {
        if (this.w_1484_f) {
            return true;
        }
        if (!this.O_508_d.u_1723_Y(this)) {
            return false;
        }
        K_4074_S blockstate = this.O_508_d.getBlockState(this.J_1907_R.offset(this.R_4764_Y.u_1723_Y()));
        return blockstate.R_4764_Y().J_1907_R() || this.R_4764_Y.h_1847_R().G_564_y() && u_782_h.P_4830_p(blockstate) ? this.O_508_d.J_1907_R((N_4263_v)this, this.i_601_W(), n_1700_B).isEmpty() : false;
    }

    @Override
    public void n_1700_B(L_461_d typeIn, e_2866_D pos) {
        if (!this.w_1484_f) {
            super.n_1700_B(typeIn, pos);
        }
    }

    @Override
    public void w_1484_f(double x, double y, double z) {
        if (!this.w_1484_f) {
            super.w_1484_f(x, y, z);
        }
    }

    @Override
    public float G_424_k() {
        return 0.0f;
    }

    @Override
    public void e_1992_r() {
        this.R_4764_Y(this.h_1847_R());
        super.e_1992_r();
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (this.w_1484_f) {
            return source != P_11_z.P_4830_p && !source.Q_2552_b() ? false : super.n_1700_B(source, amount);
        }
        if (this.n_1700_B(source)) {
            return false;
        }
        if (!source.G_564_y() && !this.h_1847_R().n_1700_B()) {
            if (!this.O_508_d.Y_259_p) {
                this.J_1907_R(source.u_2550_I(), false);
                this.n_1700_B(SoundEvents.Bots, 1.0f, 1.0f);
            }
            return true;
        }
        return super.n_1700_B(source, amount);
    }

    @Override
    public int v_4262_N() {
        return 12;
    }

    @Override
    public int w_1484_f() {
        return 12;
    }

    @Override
    public boolean n_1700_B(double distance) {
        double d0 = 16.0;
        return distance < (d0 = d0 * 64.0 * y_740_d.S_3139_t()) * d0;
    }

    @Override
    public void n_1700_B(@Nullable N_4263_v brokenEntity) {
        this.n_1700_B(SoundEvents.BetterMinecraft, 1.0f, 1.0f);
        this.J_1907_R(brokenEntity, true);
    }

    @Override
    public void t_148_a() {
        this.n_1700_B(SoundEvents.BotAutoCollector, 1.0f, 1.0f);
    }

    private void J_1907_R(@Nullable N_4263_v entityIn, boolean p_146065_2_) {
        if (!this.w_1484_f) {
            Z_1993_T itemstack = this.h_1847_R();
            this.J_1907_R(Z_1993_T.J_1907_R);
            if (!this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.v_4262_N)) {
                if (entityIn == null) {
                    this.R_4764_Y(itemstack);
                }
            } else {
                if (entityIn instanceof a_3913_L) {
                    a_3913_L playerentity = (a_3913_L)entityIn;
                    if (playerentity.C_415_h.G_564_y) {
                        this.R_4764_Y(itemstack);
                        return;
                    }
                }
                if (p_146065_2_) {
                    this.n_1700_B(Items.DeadBushBlock);
                }
                if (!itemstack.n_1700_B()) {
                    itemstack = itemstack.t_148_a();
                    this.R_4764_Y(itemstack);
                    if (this.RealmsWorldOptions.nextFloat() < this.v_4262_N) {
                        this.a_(itemstack);
                    }
                }
            }
        }
    }

    private void R_4764_Y(Z_1993_T stack) {
        if (stack.J_1907_R() == Items.K_4518_s) {
            F_3620_e mapdata = G_3165_y.J_1907_R(stack, this.O_508_d);
            mapdata.n_1700_B(this.J_1907_R, this.j_276_v());
            mapdata.n_1700_B(true);
        }
        stack.n_1700_B((N_4263_v)null);
    }

    public Z_1993_T h_1847_R() {
        return this.D_60_a().n_1700_B(P_1922_E);
    }

    public void J_1907_R(Z_1993_T stack) {
        this.n_1700_B(stack, true);
    }

    public void n_1700_B(Z_1993_T stack, boolean p_174864_2_) {
        if (!stack.n_1700_B()) {
            stack = stack.t_148_a();
            stack.P_1922_E(1);
            stack.n_1700_B(this);
        }
        this.D_60_a().J_1907_R(P_1922_E, stack);
        if (!stack.n_1700_B()) {
            this.n_1700_B(SoundEvents.BedrockProxy, 1.0f, 1.0f);
        }
        if (p_174864_2_ && this.J_1907_R != null) {
            this.O_508_d.R_4764_Y(this.J_1907_R, a_3742_W.n_1700_B);
        }
    }

    @Override
    public boolean n_1700_B(int inventorySlot, Z_1993_T itemStackIn) {
        if (inventorySlot == 0) {
            this.J_1907_R(itemStackIn);
            return true;
        }
        return false;
    }

    @Override
    public void n_1700_B(h_256_u<?> key) {
        Z_1993_T itemstack;
        if (key.equals(P_1922_E) && !(itemstack = this.h_1847_R()).n_1700_B() && itemstack.Z_875_P() != this) {
            itemstack.n_1700_B(this);
        }
    }

    public int Q_4569_t() {
        return this.D_60_a().n_1700_B(u_1723_Y);
    }

    public void n_1700_B(int rotationIn) {
        this.n_1700_B(rotationIn, true);
    }

    private void n_1700_B(int rotationIn, boolean p_174865_2_) {
        this.D_60_a().J_1907_R(u_1723_Y, rotationIn % 8);
        if (p_174865_2_ && this.J_1907_R != null) {
            this.O_508_d.R_4764_Y(this.J_1907_R, a_3742_W.n_1700_B);
        }
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        if (!this.h_1847_R().n_1700_B()) {
            compound.n_1700_B("Item", this.h_1847_R().J_1907_R(new U_2912_j()));
            compound.n_1700_B("ItemRotation", (byte)this.Q_4569_t());
            compound.n_1700_B("ItemDropChance", this.v_4262_N);
        }
        compound.n_1700_B("Facing", (byte)this.R_4764_Y.R_4764_Y());
        compound.n_1700_B("Invisible", this.F_3572_x());
        compound.n_1700_B("Fixed", this.w_1484_f);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        U_2912_j compoundnbt = compound.M_182_A("Item");
        if (compoundnbt != null && !compoundnbt.u_1723_Y()) {
            Z_1993_T itemstack1;
            Z_1993_T itemstack = Z_1993_T.n_1700_B(compoundnbt);
            if (itemstack.n_1700_B()) {
                G_564_y.warn("Unable to load item from: {}", (Object)compoundnbt);
            }
            if (!(itemstack1 = this.h_1847_R()).n_1700_B() && !Z_1993_T.J_1907_R(itemstack, itemstack1)) {
                this.R_4764_Y(itemstack1);
            }
            this.n_1700_B(itemstack, false);
            this.n_1700_B((int)compound.u_1723_Y("ItemRotation"), false);
            if (compound.R_4764_Y("ItemDropChance", 99)) {
                this.v_4262_N = compound.s_956_w("ItemDropChance");
            }
        }
        this.n_1700_B(b_257_Y.n_1700_B(compound.u_1723_Y("Facing")));
        this.M_588_G(compound.t_1786_h("Invisible"));
        this.w_1484_f = compound.t_1786_h("Fixed");
    }

    @Override
    public m_3054_I n_1700_B(a_3913_L player, x_1688_C hand) {
        boolean flag1;
        Z_1993_T itemstack = player.R_4764_Y(hand);
        boolean flag = !this.h_1847_R().n_1700_B();
        boolean bl = flag1 = !itemstack.n_1700_B();
        if (this.w_1484_f) {
            return m_3054_I.R_4764_Y;
        }
        if (!this.O_508_d.Y_259_p) {
            if (!flag) {
                if (flag1 && !this.t_4219_U) {
                    this.J_1907_R(itemstack);
                    if (!player.C_415_h.G_564_y) {
                        itemstack.v_4262_N(1);
                    }
                }
            } else {
                this.n_1700_B(SoundEvents.ClickFriend, 1.0f, 1.0f);
                this.n_1700_B(this.Q_4569_t() + 1);
            }
            return m_3054_I.J_1907_R;
        }
        return !flag && !flag1 ? m_3054_I.R_4764_Y : m_3054_I.n_1700_B;
    }

    public int M_182_A() {
        return this.h_1847_R().n_1700_B() ? 0 : this.Q_4569_t() % 8 + 1;
    }

    @Override
    public Packet<?> f_() {
        return new ClientboundAddEntityPacket(this, this.f_4016_n(), this.R_4764_Y.R_4764_Y(), this.u_2550_I());
    }
}



