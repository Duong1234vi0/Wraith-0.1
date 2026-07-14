/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.B_477_D;
import lightning.product.CommandSource;
import lightning.product.F_2904_S;
import lightning.product.Clearable;
import lightning.product.K_4074_S;
import lightning.product.Container;
import lightning.product.P_3504_Q;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.W_3491_f;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_3913_L;
import lightning.product.a_669_v;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.h_355_y;
import lightning.product.i_2154_H;
import lightning.product.ContainerData;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.BlockEntityType;
import lightning.product.t_3286_u;
import lightning.product.u_530_F;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;

public class D_4899_Z
extends i_2154_H
implements Clearable,
t_3286_u {
    private final Container n_1700_B = new Container(){

        @Override
        public int Y_259_p() {
            return 1;
        }

        @Override
        public boolean Q_2552_b() {
            return D_4899_Z.this.R_4764_Y.n_1700_B();
        }

        @Override
        public Z_1993_T s_956_w(int index) {
            return index == 0 ? D_4899_Z.this.R_4764_Y : Z_1993_T.J_1907_R;
        }

        @Override
        public Z_1993_T n_1700_B(int index, int count) {
            if (index == 0) {
                Z_1993_T itemstack = D_4899_Z.this.R_4764_Y.n_1700_B(count);
                if (D_4899_Z.this.R_4764_Y.n_1700_B()) {
                    D_4899_Z.this.u_2550_I();
                }
                return itemstack;
            }
            return Z_1993_T.J_1907_R;
        }

        @Override
        public Z_1993_T u_2550_I(int index) {
            if (index == 0) {
                Z_1993_T itemstack = D_4899_Z.this.R_4764_Y;
                D_4899_Z.this.R_4764_Y = Z_1993_T.J_1907_R;
                D_4899_Z.this.u_2550_I();
                return itemstack;
            }
            return Z_1993_T.J_1907_R;
        }

        @Override
        public void J_1907_R(int index, Z_1993_T stack) {
        }

        @Override
        public int J_() {
            return 1;
        }

        @Override
        public void J_1907_R() {
            D_4899_Z.this.J_1907_R();
        }

        @Override
        public boolean R_4764_Y(a_3913_L player) {
            if (D_4899_Z.this.u_2550_I.getTileEntity(D_4899_Z.this.M_588_G) != D_4899_Z.this) {
                return false;
            }
            return player.v_4262_N((double)D_4899_Z.this.M_588_G.getX() + 0.5, (double)D_4899_Z.this.M_588_G.getY() + 0.5, (double)D_4899_Z.this.M_588_G.getZ() + 0.5) > 64.0 ? false : D_4899_Z.this.v_4262_N();
        }

        @Override
        public boolean a_(int index, Z_1993_T stack) {
            return false;
        }

        @Override
        public void C_2741_M() {
        }
    };
    private final ContainerData J_1907_R = new ContainerData(){

        @Override
        public int n_1700_B(int index) {
            return index == 0 ? D_4899_Z.this.G_564_y : 0;
        }

        @Override
        public void n_1700_B(int index, int value) {
            if (index == 0) {
                D_4899_Z.this.n_1700_B(value);
            }
        }

        @Override
        public int n_1700_B() {
            return 1;
        }
    };
    private Z_1993_T R_4764_Y = Z_1993_T.J_1907_R;
    private int G_564_y;
    private int P_1922_E;

    public D_4899_Z() {
        super(BlockEntityType.A_4115_X);
    }

    public Z_1993_T P_1922_E() {
        return this.R_4764_Y;
    }

    public boolean v_4262_N() {
        q_1613_l item = this.R_4764_Y.J_1907_R();
        return item == Items.CropBlock || item == Items.CryingObsidianBlock;
    }

    public void n_1700_B(Z_1993_T stack) {
        this.n_1700_B(stack, (a_3913_L)null);
    }

    private void u_2550_I() {
        this.G_564_y = 0;
        this.P_1922_E = 0;
        h_355_y.n_1700_B(this.c_3005_b(), this.x_607_J(), this.e_4240_b(), false);
    }

    public void n_1700_B(Z_1993_T stack, @Nullable a_3913_L player) {
        this.R_4764_Y = this.J_1907_R(stack, player);
        this.G_564_y = 0;
        this.P_1922_E = B_477_D.v_4262_N(this.R_4764_Y);
        this.J_1907_R();
    }

    private void n_1700_B(int pageIn) {
        int i = u_530_F.n_1700_B(pageIn, 0, this.P_1922_E - 1);
        if (i != this.G_564_y) {
            this.G_564_y = i;
            this.J_1907_R();
            h_355_y.n_1700_B(this.c_3005_b(), this.x_607_J(), this.e_4240_b());
        }
    }

    public int w_1484_f() {
        return this.G_564_y;
    }

    public int s_956_w() {
        float f = this.P_1922_E > 1 ? (float)this.w_1484_f() / ((float)this.P_1922_E - 1.0f) : 1.0f;
        return u_530_F.G_564_y(f * 14.0f) + (this.v_4262_N() ? 1 : 0);
    }

    private Z_1993_T J_1907_R(Z_1993_T stack, @Nullable a_3913_L player) {
        if (this.u_2550_I instanceof e_3591_l && stack.J_1907_R() == Items.CryingObsidianBlock) {
            B_477_D.n_1700_B(stack, this.n_1700_B(player), player);
        }
        return stack;
    }

    private y_2498_m n_1700_B(@Nullable a_3913_L player) {
        x_282_a itextcomponent;
        String s;
        if (player == null) {
            s = "Lectern";
            itextcomponent = new U_2871_b("Lectern");
        } else {
            s = player.O_1309_Q().getString();
            itextcomponent = player.c_();
        }
        e_2866_D vector3d = e_2866_D.n_1700_B(this.M_588_G);
        return new y_2498_m(CommandSource.T_3594_S, vector3d, P_3504_Q.n_1700_B, (e_3591_l)this.u_2550_I, 2, s, itextcomponent, this.u_2550_I.T_2506_i(), player);
    }

    @Override
    public boolean K_() {
        return true;
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        super.n_1700_B(state, nbt);
        this.R_4764_Y = nbt.R_4764_Y("Book", 10) ? this.J_1907_R(Z_1993_T.n_1700_B(nbt.M_182_A("Book")), (a_3913_L)null) : Z_1993_T.J_1907_R;
        this.P_1922_E = B_477_D.v_4262_N(this.R_4764_Y);
        this.G_564_y = u_530_F.n_1700_B(nbt.w_1484_f("Page"), 0, this.P_1922_E - 1);
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        if (!this.P_1922_E().n_1700_B()) {
            compound.n_1700_B("Book", this.P_1922_E().J_1907_R(new U_2912_j()));
            compound.J_1907_R("Page", this.G_564_y);
        }
        return compound;
    }

    @Override
    public void C_2741_M() {
        this.n_1700_B(Z_1993_T.J_1907_R);
    }

    @Override
    public a_2900_S createMenu(int p_createMenu_1_, W_3491_f p_createMenu_2_, a_3913_L p_createMenu_3_) {
        return new a_669_v(p_createMenu_1_, this.n_1700_B, this.J_1907_R);
    }

    @Override
    public x_282_a c_() {
        return new F_2904_S("container.lectern");
    }
}


