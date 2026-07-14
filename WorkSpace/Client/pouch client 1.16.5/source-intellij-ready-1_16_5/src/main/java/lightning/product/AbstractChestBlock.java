/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Supplier;
import lightning.product.BaseEntityBlock;
import lightning.product.K_4074_S;
import lightning.product.DoubleBlockCombiner;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.i_2154_H;
import lightning.product.q_4293_E;
import lightning.product.BlockEntityType;
import lightning.product.t_693_s;

public abstract class AbstractChestBlock<E extends i_2154_H>
extends BaseEntityBlock {
    protected final Supplier<BlockEntityType<? extends E>> P_4830_p;

    protected AbstractChestBlock(q_4293_E.P_1922_E builder, Supplier<BlockEntityType<? extends E>> tileEntityTypeSupplier) {
        super(builder);
        this.P_4830_p = tileEntityTypeSupplier;
    }

    public abstract DoubleBlockCombiner.J_1907_R<? extends t_693_s> n_1700_B(K_4074_S var1, b_4507_u var2, c_1514_x var3, boolean var4);
}


