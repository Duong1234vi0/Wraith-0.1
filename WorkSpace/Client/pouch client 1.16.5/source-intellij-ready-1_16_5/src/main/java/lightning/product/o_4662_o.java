/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_4355_q;
import lightning.product.AbstractZombieModel;
import lightning.product.Z_530_i;

public class o_4662_o<T extends F_4355_q>
extends AbstractZombieModel<T> {
    public o_4662_o(float modelSize, boolean p_i1168_2_) {
        this(modelSize, 0.0f, 64, p_i1168_2_ ? 32 : 64);
    }

    protected o_4662_o(float p_i48914_1_, float p_i48914_2_, int p_i48914_3_, int p_i48914_4_) {
        super(p_i48914_1_, p_i48914_2_, p_i48914_3_, p_i48914_4_);
    }

    @Override
    public boolean n_1700_B(T entityIn) {
        return ((Z_530_i)entityIn).P_2272_O();
    }
}


