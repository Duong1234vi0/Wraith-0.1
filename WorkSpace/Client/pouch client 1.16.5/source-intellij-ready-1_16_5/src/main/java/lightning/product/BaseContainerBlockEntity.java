/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.D_38_f;
import lightning.product.F_2904_S;
import lightning.product.LockCode;
import lightning.product.K_4074_S;
import lightning.product.Container;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.W_3491_f;
import lightning.product.a_2900_S;
import lightning.product.a_3913_L;
import lightning.product.Nameable;
import lightning.product.i_2154_H;
import lightning.product.BlockEntityType;
import lightning.product.t_3286_u;
import lightning.product.x_282_a;

public abstract class BaseContainerBlockEntity
extends i_2154_H
implements Container,
Nameable,
t_3286_u {
    private LockCode n_1700_B = LockCode.n_1700_B;
    private x_282_a J_1907_R;

    protected BaseContainerBlockEntity(BlockEntityType<?> typeIn) {
        super(typeIn);
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        super.n_1700_B(state, nbt);
        this.n_1700_B = LockCode.J_1907_R(nbt);
        if (nbt.R_4764_Y("CustomName", 8)) {
            this.J_1907_R = x_282_a.n_1700_B.n_1700_B(nbt.M_588_G("CustomName"));
        }
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        this.n_1700_B.n_1700_B(compound);
        if (this.J_1907_R != null) {
            compound.n_1700_B("CustomName", x_282_a.n_1700_B.n_1700_B(this.J_1907_R));
        }
        return compound;
    }

    public void n_1700_B(x_282_a name) {
        this.J_1907_R = name;
    }

    @Override
    public x_282_a O_1309_Q() {
        return this.J_1907_R != null ? this.J_1907_R : this.F_();
    }

    @Override
    public x_282_a c_() {
        return this.O_1309_Q();
    }

    @Override
    @Nullable
    public x_282_a k_2302_P() {
        return this.J_1907_R;
    }

    protected abstract x_282_a F_();

    public boolean P_1922_E(a_3913_L p_213904_1_) {
        return BaseContainerBlockEntity.n_1700_B(p_213904_1_, this.n_1700_B, this.c_());
    }

    public static boolean n_1700_B(a_3913_L p_213905_0_, LockCode p_213905_1_, x_282_a p_213905_2_) {
        if (!p_213905_0_.d_2461_k() && !p_213905_1_.n_1700_B(p_213905_0_.A_2714_y())) {
            p_213905_0_.n_1700_B((x_282_a)new F_2904_S("container.isLocked", p_213905_2_), true);
            p_213905_0_.n_1700_B(SoundEvents.H_1883_T, D_38_f.P_1922_E, 1.0f, 1.0f);
            return false;
        }
        return true;
    }

    @Override
    @Nullable
    public a_2900_S createMenu(int p_createMenu_1_, W_3491_f p_createMenu_2_, a_3913_L p_createMenu_3_) {
        return this.P_1922_E(p_createMenu_3_) ? this.n_1700_B(p_createMenu_1_, p_createMenu_2_) : null;
    }

    protected abstract a_2900_S n_1700_B(int var1, W_3491_f var2);
}


