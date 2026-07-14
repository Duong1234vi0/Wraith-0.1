/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_1130_n;
import lightning.product.BigContext;
import lightning.product.AreaTransformer1;
import lightning.product.Context;
import lightning.product.t_4013_W;

public interface C1Transformer
extends A_1130_n,
AreaTransformer1 {
    public int n_1700_B(Context var1, int var2);

    @Override
    default public int n_1700_B(BigContext<?> context, t_4013_W area, int x, int z) {
        int i = area.n_1700_B(this.n_1700_B(x + 1), this.J_1907_R(z + 1));
        return this.n_1700_B(context, i);
    }
}


