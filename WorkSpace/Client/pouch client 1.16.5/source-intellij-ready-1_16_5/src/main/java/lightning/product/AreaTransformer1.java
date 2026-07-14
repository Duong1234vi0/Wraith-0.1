/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.DimensionTransformer;
import lightning.product.BigContext;
import lightning.product.p_3451_N;
import lightning.product.t_4013_W;

public interface AreaTransformer1
extends DimensionTransformer {
    default public <R extends t_4013_W> p_3451_N<R> n_1700_B(BigContext<R> context, p_3451_N<R> areaFactory) {
        return () -> {
            Object r = areaFactory.make();
            return context.n_1700_B((int p_202711_3_, int p_202711_4_) -> {
                context.n_1700_B((long)p_202711_3_, (long)p_202711_4_);
                return this.n_1700_B(context, (t_4013_W)r, p_202711_3_, p_202711_4_);
            }, r);
        };
    }

    public int n_1700_B(BigContext<?> var1, t_4013_W var2, int var3, int var4);
}


