/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import javax.annotation.Nullable;
import lightning.product.BlockGetter;
import lightning.product.T_2915_h;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.g_3316_o;
import lightning.product.q_1613_l;
import lightning.product.x_282_a;

public class AirItem
extends q_1613_l {
    private final T_2915_h n_1700_B;

    public AirItem(T_2915_h blockIn, q_1613_l.n_1700_B properties) {
        super(properties);
        this.n_1700_B = blockIn;
    }

    @Override
    public String J_1907_R() {
        return this.n_1700_B.P_4830_p();
    }

    @Override
    public void n_1700_B(Z_1993_T stack, @Nullable b_4507_u worldIn, List<x_282_a> tooltip, g_3316_o flagIn) {
        super.n_1700_B(stack, worldIn, tooltip, flagIn);
        this.n_1700_B.n_1700_B(stack, (BlockGetter)worldIn, tooltip, flagIn);
    }
}


