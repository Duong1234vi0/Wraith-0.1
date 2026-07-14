/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.J_2868_p;
import lightning.product.e_933_M;
import lightning.product.i_2154_H;
import lightning.product.ClientboundBlockEntityDataPacket;
import lightning.product.BlockEntityType;

public class BedBlockEntity
extends i_2154_H {
    private e_933_M n_1700_B;

    public BedBlockEntity() {
        super(BlockEntityType.k_2293_S);
    }

    public BedBlockEntity(e_933_M colorIn) {
        this();
        this.n_1700_B(colorIn);
    }

    @Override
    public ClientboundBlockEntityDataPacket G_() {
        return new ClientboundBlockEntityDataPacket(this.M_588_G, 11, this.H_());
    }

    public e_933_M n_1700_B() {
        if (this.n_1700_B == null) {
            this.n_1700_B = ((J_2868_p)this.e_4240_b().J_1907_R()).J_1907_R();
        }
        return this.n_1700_B;
    }

    public void n_1700_B(e_933_M color) {
        this.n_1700_B = color;
    }
}


