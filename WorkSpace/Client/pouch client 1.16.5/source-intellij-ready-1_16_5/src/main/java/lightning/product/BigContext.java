/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Context;
import lightning.product.s_1037_T;
import lightning.product.t_4013_W;

public interface BigContext<R extends t_4013_W>
extends Context {
    public void n_1700_B(long var1, long var3);

    public R n_1700_B(s_1037_T var1);

    default public R n_1700_B(s_1037_T pixelTransformer, R area) {
        return this.n_1700_B(pixelTransformer);
    }

    default public R n_1700_B(s_1037_T p_212860_1_, R firstArea, R secondArea) {
        return this.n_1700_B(p_212860_1_);
    }

    default public int n_1700_B(int p_215715_1_, int p_215715_2_) {
        return this.n_1700_B(2) == 0 ? p_215715_1_ : p_215715_2_;
    }

    default public int n_1700_B(int p_215714_1_, int p_215714_2_, int p_215714_3_, int p_215714_4_) {
        int i = this.n_1700_B(4);
        if (i == 0) {
            return p_215714_1_;
        }
        if (i == 1) {
            return p_215714_2_;
        }
        return i == 2 ? p_215714_3_ : p_215714_4_;
    }
}


