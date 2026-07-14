/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.e_3591_l;
import lightning.product.LevelAccessor;

public interface ServerLevelAccessor
extends LevelAccessor {
    public e_3591_l J_1907_R();

    default public void n_1700_B(N_4263_v p_242417_1_) {
        p_242417_1_.O_1795_e().forEach(this::a_);
    }
}


