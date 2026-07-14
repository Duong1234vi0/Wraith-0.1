/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_4074_S;
import lightning.product.U_2912_j;
import lightning.product.i_2154_H;
import lightning.product.BlockEntityType;

public class ComparatorBlockEntity
extends i_2154_H {
    private int n_1700_B;

    public ComparatorBlockEntity() {
        super(BlockEntityType.multiplayerClientSuggestionProvider);
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("OutputSignal", this.n_1700_B);
        return compound;
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        super.n_1700_B(state, nbt);
        this.n_1700_B = nbt.w_1484_f("OutputSignal");
    }

    public int P_1922_E() {
        return this.n_1700_B;
    }

    public void n_1700_B(int outputSignalIn) {
        this.n_1700_B = outputSignalIn;
    }
}


