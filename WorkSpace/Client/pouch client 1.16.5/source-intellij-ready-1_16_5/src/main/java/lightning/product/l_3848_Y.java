/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.F_2904_S;
import lightning.product.K_4074_S;
import lightning.product.NonNullList;
import lightning.product.U_2912_j;
import lightning.product.V_4572_l;
import lightning.product.W_3491_f;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.ContainerHelper;
import lightning.product.BlockEntityType;
import lightning.product.x_282_a;
import lightning.product.x_940_l;

public class l_3848_Y
extends V_4572_l {
    private static final Random n_1700_B = new Random();
    private NonNullList<Z_1993_T> J_1907_R = NonNullList.n_1700_B(9, Z_1993_T.J_1907_R);

    protected l_3848_Y(BlockEntityType<?> p_i48286_1_) {
        super(p_i48286_1_);
    }

    public l_3848_Y() {
        this(BlockEntityType.u_1723_Y);
    }

    @Override
    public int Y_259_p() {
        return 9;
    }

    public int v_4262_N() {
        this.G_564_y(null);
        int i = -1;
        int j = 1;
        for (int k = 0; k < this.J_1907_R.size(); ++k) {
            if (this.J_1907_R.get(k).n_1700_B() || n_1700_B.nextInt(j++) != 0) continue;
            i = k;
        }
        return i;
    }

    public int n_1700_B(Z_1993_T stack) {
        for (int i = 0; i < this.J_1907_R.size(); ++i) {
            if (!this.J_1907_R.get(i).n_1700_B()) continue;
            this.J_1907_R(i, stack);
            return i;
        }
        return -1;
    }

    @Override
    protected x_282_a F_() {
        return new F_2904_S("container.dispenser");
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        super.n_1700_B(state, nbt);
        this.J_1907_R = NonNullList.n_1700_B(this.Y_259_p(), Z_1993_T.J_1907_R);
        if (!this.J_1907_R(nbt)) {
            ContainerHelper.J_1907_R(nbt, this.J_1907_R);
        }
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        if (!this.R_4764_Y(compound)) {
            ContainerHelper.n_1700_B(compound, this.J_1907_R);
        }
        return compound;
    }

    @Override
    protected NonNullList<Z_1993_T> L_() {
        return this.J_1907_R;
    }

    @Override
    protected void n_1700_B(NonNullList<Z_1993_T> itemsIn) {
        this.J_1907_R = itemsIn;
    }

    @Override
    protected a_2900_S n_1700_B(int id, W_3491_f player) {
        return new x_940_l(id, player, this);
    }
}


