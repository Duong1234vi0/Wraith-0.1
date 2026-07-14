/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_1130_n;
import lightning.product.BigContext;
import lightning.product.AreaTransformer1;
import lightning.product.Context;
import lightning.product.t_4013_W;

public interface c_1325_f
extends A_1130_n,
AreaTransformer1 {
    public int n_1700_B(Context var1, int var2, int var3, int var4, int var5, int var6);

    @Override
    default public int n_1700_B(BigContext<?> context, t_4013_W area, int x, int z) {
        return this.n_1700_B(context, area.n_1700_B(this.n_1700_B(x + 1), this.J_1907_R(z + 0)), area.n_1700_B(this.n_1700_B(x + 2), this.J_1907_R(z + 1)), area.n_1700_B(this.n_1700_B(x + 1), this.J_1907_R(z + 2)), area.n_1700_B(this.n_1700_B(x + 0), this.J_1907_R(z + 1)), area.n_1700_B(this.n_1700_B(x + 1), this.J_1907_R(z + 1)));
    }
}


