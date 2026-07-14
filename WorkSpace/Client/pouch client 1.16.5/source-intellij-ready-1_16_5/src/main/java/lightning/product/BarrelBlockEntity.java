/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_38_f;
import lightning.product.F_2904_S;
import lightning.product.K_4074_S;
import lightning.product.ChestMenu;
import lightning.product.NonNullList;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.V_4572_l;
import lightning.product.W_3491_f;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.ContainerHelper;
import lightning.product.q_36_y;
import lightning.product.BlockEntityType;
import lightning.product.t_693_s;
import lightning.product.x_282_a;
import lightning.product.z_3539_x;

public class BarrelBlockEntity
extends V_4572_l {
    private NonNullList<Z_1993_T> n_1700_B = NonNullList.n_1700_B(27, Z_1993_T.J_1907_R);
    private int J_1907_R;

    private BarrelBlockEntity(BlockEntityType<?> barrelType) {
        super(barrelType);
    }

    public BarrelBlockEntity() {
        this(BlockEntityType.Z_875_P);
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        if (!this.R_4764_Y(compound)) {
            ContainerHelper.n_1700_B(compound, this.n_1700_B);
        }
        return compound;
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        super.n_1700_B(state, nbt);
        this.n_1700_B = NonNullList.n_1700_B(this.Y_259_p(), Z_1993_T.J_1907_R);
        if (!this.J_1907_R(nbt)) {
            ContainerHelper.J_1907_R(nbt, this.n_1700_B);
        }
    }

    @Override
    public int Y_259_p() {
        return 27;
    }

    @Override
    protected NonNullList<Z_1993_T> L_() {
        return this.n_1700_B;
    }

    @Override
    protected void n_1700_B(NonNullList<Z_1993_T> itemsIn) {
        this.n_1700_B = itemsIn;
    }

    @Override
    protected x_282_a F_() {
        return new F_2904_S("container.barrel");
    }

    @Override
    protected a_2900_S n_1700_B(int id, W_3491_f player) {
        return ChestMenu.n_1700_B(id, player, this);
    }

    @Override
    public void b_(a_3913_L player) {
        if (!player.d_2461_k()) {
            if (this.J_1907_R < 0) {
                this.J_1907_R = 0;
            }
            ++this.J_1907_R;
            K_4074_S blockstate = this.e_4240_b();
            boolean flag = blockstate.R_4764_Y(q_36_y.h_1847_R);
            if (!flag) {
                this.n_1700_B(blockstate, SoundEvents.Ping);
                this.n_1700_B(blockstate, true);
            }
            this.w_1484_f();
        }
    }

    private void w_1484_f() {
        this.u_2550_I.u_2550_I().n_1700_B(this.x_607_J(), this.e_4240_b().J_1907_R(), 5);
    }

    public void v_4262_N() {
        int i = this.M_588_G.getX();
        int j = this.M_588_G.getY();
        int k = this.M_588_G.getZ();
        this.J_1907_R = t_693_s.n_1700_B(this.u_2550_I, this, i, j, k);
        if (this.J_1907_R > 0) {
            this.w_1484_f();
        } else {
            K_4074_S blockstate = this.e_4240_b();
            if (!blockstate.n_1700_B(a_3742_W.y_254_d)) {
                this.I_();
                return;
            }
            boolean flag = blockstate.R_4764_Y(q_36_y.h_1847_R);
            if (flag) {
                this.n_1700_B(blockstate, SoundEvents.i_1637_u);
                this.n_1700_B(blockstate, false);
            }
        }
    }

    @Override
    public void J_1907_R(a_3913_L player) {
        if (!player.d_2461_k()) {
            --this.J_1907_R;
        }
    }

    private void n_1700_B(K_4074_S state, boolean open) {
        this.u_2550_I.n_1700_B(this.x_607_J(), (K_4074_S)state.n_1700_B(q_36_y.h_1847_R, open), 3);
    }

    private void n_1700_B(K_4074_S state, SoundEvent sound) {
        z_3539_x vector3i = state.R_4764_Y(q_36_y.P_4830_p).M_182_A();
        double d0 = (double)this.M_588_G.getX() + 0.5 + (double)vector3i.getX() / 2.0;
        double d1 = (double)this.M_588_G.getY() + 0.5 + (double)vector3i.getY() / 2.0;
        double d2 = (double)this.M_588_G.getZ() + 0.5 + (double)vector3i.getZ() / 2.0;
        this.u_2550_I.n_1700_B((a_3913_L)null, d0, d1, d2, sound, D_38_f.P_1922_E, 0.5f, this.u_2550_I.w_1457_N.nextFloat() * 0.1f + 0.9f);
    }
}


