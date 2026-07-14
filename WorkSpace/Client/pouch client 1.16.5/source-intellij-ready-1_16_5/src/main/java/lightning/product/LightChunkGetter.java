/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.BlockGetter;
import lightning.product.K_4719_o;
import lightning.product.SectionPos;

public interface LightChunkGetter {
    @Nullable
    public BlockGetter G_564_y(int var1, int var2);

    default public void n_1700_B(K_4719_o type, SectionPos pos) {
    }

    public BlockGetter n_1700_B();
}


