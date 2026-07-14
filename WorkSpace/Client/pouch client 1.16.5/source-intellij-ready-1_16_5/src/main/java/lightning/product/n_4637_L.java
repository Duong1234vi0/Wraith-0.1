/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Map;
import lightning.product.FluidTags;
import lightning.product.K_4096_w;
import lightning.product.L_461_d;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.Enchantments;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.ClientboundAddExperienceOrbPacket;
import lightning.product.Packet;
import lightning.product.t_5_h;

public class n_4637_L
extends N_4263_v {
    public int n_1700_B;
    public int J_1907_R;
    public int R_4764_Y;
    private int G_564_y = 5;
    private int P_1922_E;
    private a_3913_L u_1723_Y;
    private int v_4262_N;

    public n_4637_L(b_4507_u worldIn, double x, double y, double z, int expValue) {
        this((t_5_h<? extends n_4637_L>)t_5_h.q_2307_F, worldIn);
        this.J_1907_R(x, y, z);
        this.p_178_J = (float)(this.RealmsWorldOptions.nextDouble() * 360.0);
        this.h_1847_R((this.RealmsWorldOptions.nextDouble() * (double)0.2f - (double)0.1f) * 2.0, this.RealmsWorldOptions.nextDouble() * 0.2 * 2.0, (this.RealmsWorldOptions.nextDouble() * (double)0.2f - (double)0.1f) * 2.0);
        this.P_1922_E = expValue;
    }

    public n_4637_L(t_5_h<? extends n_4637_L> p_i50382_1_, b_4507_u entity) {
        super(p_i50382_1_, entity);
    }

    @Override
    protected boolean RetryCallException() {
        return false;
    }

    @Override
    protected void a_() {
    }

    @Override
    public void v_() {
        e_2866_D vector3d;
        double d1;
        super.v_();
        if (this.R_4764_Y > 0) {
            --this.R_4764_Y;
        }
        this.r_715_M = this.O_3598_v();
        this.A_1038_p = this.X_2960_b();
        this.i_1637_u = this.l_2647_k();
        if (this.n_1700_B(FluidTags.J_1907_R)) {
            this.v_4262_N();
        } else if (!this.u_744_e()) {
            this.v_4262_N(this.I_4348_c().J_1907_R(0.0, -0.03, 0.0));
        }
        if (this.O_508_d.getFluidState(this.b_2312_j()).n_1700_B(FluidTags.R_4764_Y)) {
            this.h_1847_R((this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f, 0.2f, (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f);
            this.n_1700_B(SoundEvents.y_2622_c, 0.4f, 2.0f + this.RealmsWorldOptions.nextFloat() * 0.4f);
        }
        if (!this.O_508_d.J_1907_R(this.i_601_W())) {
            this.u_2550_I(this.O_3598_v(), (this.i_601_W().minY + this.i_601_W().maxY) / 2.0, this.l_2647_k());
        }
        double d0 = 8.0;
        if (this.v_4262_N < this.n_1700_B - 20 + this.j_276_v() % 100) {
            if (this.u_1723_Y == null || this.u_1723_Y.G_564_y(this) > 64.0) {
                this.u_1723_Y = this.O_508_d.n_1700_B((N_4263_v)this, 8.0);
            }
            this.v_4262_N = this.n_1700_B;
        }
        if (this.u_1723_Y != null && this.u_1723_Y.d_2461_k()) {
            this.u_1723_Y = null;
        }
        if (this.u_1723_Y != null && (d1 = (vector3d = new e_2866_D(this.u_1723_Y.O_3598_v() - this.O_3598_v(), this.u_1723_Y.X_2960_b() + (double)this.u_1723_Y.X_1313_W() / 2.0 - this.X_2960_b(), this.u_1723_Y.l_2647_k() - this.l_2647_k())).v_4262_N()) < 64.0) {
            double d2 = 1.0 - Math.sqrt(d1) / 8.0;
            this.v_4262_N(this.I_4348_c().P_1922_E(vector3d.G_564_y().n_1700_B(d2 * d2 * 0.1)));
        }
        this.n_1700_B(L_461_d.n_1700_B, this.I_4348_c());
        float f = 0.98f;
        if (this.e_1992_r) {
            f = this.O_508_d.getBlockState(new c_1514_x(this.O_3598_v(), this.X_2960_b() - 1.0, this.l_2647_k())).J_1907_R().h_1847_R() * 0.98f;
        }
        this.v_4262_N(this.I_4348_c().G_564_y(f, 0.98, f));
        if (this.e_1992_r) {
            this.v_4262_N(this.I_4348_c().G_564_y(1.0, -0.9, 1.0));
        }
        ++this.n_1700_B;
        ++this.J_1907_R;
        if (this.J_1907_R >= 6000) {
            this.Ops();
        }
    }

    private void v_4262_N() {
        e_2866_D vector3d = this.I_4348_c();
        this.h_1847_R(vector3d.J_1907_R * (double)0.99f, Math.min(vector3d.R_4764_Y + (double)5.0E-4f, (double)0.06f), vector3d.G_564_y * (double)0.99f);
    }

    @Override
    protected void c_132_F() {
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (this.n_1700_B(source)) {
            return false;
        }
        this.RealmsCreateRealmScreen();
        this.G_564_y = (int)((float)this.G_564_y - amount);
        if (this.G_564_y <= 0) {
            this.Ops();
        }
        return false;
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        compound.n_1700_B("Health", (short)this.G_564_y);
        compound.n_1700_B("Age", (short)this.J_1907_R);
        compound.n_1700_B("Value", (short)this.P_1922_E);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        this.G_564_y = compound.v_4262_N("Health");
        this.J_1907_R = compound.v_4262_N("Age");
        this.P_1922_E = compound.v_4262_N("Value");
    }

    @Override
    public void c_(a_3913_L entityIn) {
        if (!this.O_508_d.Y_259_p && this.R_4764_Y == 0 && entityIn.l_697_B == 0) {
            Z_1993_T itemstack;
            entityIn.l_697_B = 2;
            entityIn.n_1700_B((N_4263_v)this, 1);
            Map.Entry<e_1174_E, Z_1993_T> entry = K_4096_w.n_1700_B(Enchantments.v_4276_D, entityIn, Z_1993_T::u_1723_Y);
            if (entry != null && !(itemstack = entry.getValue()).n_1700_B() && itemstack.u_1723_Y()) {
                int i = Math.min(this.R_4764_Y(this.P_1922_E), itemstack.v_4262_N());
                this.P_1922_E -= this.J_1907_R(i);
                itemstack.J_1907_R(itemstack.v_4262_N() - i);
            }
            if (this.P_1922_E > 0) {
                entityIn.multiplayerClientSuggestionProvider(this.P_1922_E);
            }
            this.Ops();
        }
    }

    private int J_1907_R(int durability) {
        return durability / 2;
    }

    private int R_4764_Y(int xp) {
        return xp * 2;
    }

    public int P_1922_E() {
        return this.P_1922_E;
    }

    public int u_1723_Y() {
        if (this.P_1922_E >= 2477) {
            return 10;
        }
        if (this.P_1922_E >= 1237) {
            return 9;
        }
        if (this.P_1922_E >= 617) {
            return 8;
        }
        if (this.P_1922_E >= 307) {
            return 7;
        }
        if (this.P_1922_E >= 149) {
            return 6;
        }
        if (this.P_1922_E >= 73) {
            return 5;
        }
        if (this.P_1922_E >= 37) {
            return 4;
        }
        if (this.P_1922_E >= 17) {
            return 3;
        }
        if (this.P_1922_E >= 7) {
            return 2;
        }
        return this.P_1922_E >= 3 ? 1 : 0;
    }

    public static int n_1700_B(int expValue) {
        if (expValue >= 2477) {
            return 2477;
        }
        if (expValue >= 1237) {
            return 1237;
        }
        if (expValue >= 617) {
            return 617;
        }
        if (expValue >= 307) {
            return 307;
        }
        if (expValue >= 149) {
            return 149;
        }
        if (expValue >= 73) {
            return 73;
        }
        if (expValue >= 37) {
            return 37;
        }
        if (expValue >= 17) {
            return 17;
        }
        if (expValue >= 7) {
            return 7;
        }
        return expValue >= 3 ? 3 : 1;
    }

    @Override
    public boolean Z_735_d() {
        return false;
    }

    @Override
    public Packet<?> f_() {
        return new ClientboundAddExperienceOrbPacket(this);
    }
}


