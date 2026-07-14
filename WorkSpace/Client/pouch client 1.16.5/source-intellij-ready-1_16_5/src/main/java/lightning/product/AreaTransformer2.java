/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.DimensionTransformer;
import lightning.product.BigContext;
import lightning.product.Context;
import lightning.product.p_3451_N;
import lightning.product.t_4013_W;

public interface AreaTransformer2
extends DimensionTransformer {
    default public <R extends t_4013_W> p_3451_N<R> n_1700_B(BigContext<R> context, p_3451_N<R> areaFactory, p_3451_N<R> areaFactoryIn) {
        return () -> {
            Object r = areaFactory.make();
            Object r1 = areaFactoryIn.make();
            return context.n_1700_B((int p_215724_4_, int p_215724_5_) -> {
                context.n_1700_B((long)p_215724_4_, (long)p_215724_5_);
                return this.n_1700_B((Context)context, (t_4013_W)r, (t_4013_W)r1, p_215724_4_, p_215724_5_);
            }, r, r1);
        };
    }

    public int n_1700_B(Context var1, t_4013_W var2, t_4013_W var3, int var4, int var5);
}


