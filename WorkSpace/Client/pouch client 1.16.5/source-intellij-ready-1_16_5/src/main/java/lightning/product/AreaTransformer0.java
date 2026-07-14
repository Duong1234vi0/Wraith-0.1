/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BigContext;
import lightning.product.Context;
import lightning.product.p_3451_N;
import lightning.product.t_4013_W;

public interface AreaTransformer0 {
    default public <R extends t_4013_W> p_3451_N<R> n_1700_B(BigContext<R> context) {
        return () -> context.n_1700_B((int p_202820_2_, int p_202820_3_) -> {
            context.n_1700_B((long)p_202820_2_, (long)p_202820_3_);
            return this.n_1700_B((Context)context, p_202820_2_, p_202820_3_);
        });
    }

    public int n_1700_B(Context var1, int var2, int var3);
}


