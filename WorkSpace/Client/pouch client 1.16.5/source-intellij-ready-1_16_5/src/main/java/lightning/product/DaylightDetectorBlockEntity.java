/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.X_1924_A;
import lightning.product.Y_2905_A;
import lightning.product.i_2154_H;
import lightning.product.BlockEntityType;

public class DaylightDetectorBlockEntity
extends i_2154_H
implements X_1924_A {
    public DaylightDetectorBlockEntity() {
        super(BlockEntityType.M_182_A);
    }

    @Override
    public void P_1922_E() {
        K_4074_S blockstate;
        T_2915_h block;
        if (this.u_2550_I != null && !this.u_2550_I.Y_259_p && this.u_2550_I.X_933_l() % 20L == 0L && (block = (blockstate = this.e_4240_b()).J_1907_R()) instanceof Y_2905_A) {
            Y_2905_A.R_4764_Y(blockstate, this.u_2550_I, this.M_588_G);
        }
    }
}


