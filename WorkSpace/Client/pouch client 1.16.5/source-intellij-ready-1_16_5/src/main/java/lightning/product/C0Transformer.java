/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.C_4709_v;
import lightning.product.BigContext;
import lightning.product.AreaTransformer1;
import lightning.product.Context;
import lightning.product.t_4013_W;

public interface C0Transformer
extends C_4709_v,
AreaTransformer1 {
    public int n_1700_B(Context var1, int var2);

    @Override
    default public int n_1700_B(BigContext<?> context, t_4013_W area, int x, int z) {
        return this.n_1700_B(context, area.n_1700_B(this.n_1700_B(x), this.J_1907_R(z)));
    }
}


