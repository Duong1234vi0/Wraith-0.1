/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.K_4719_o;
import lightning.product.R_1900_x;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.ColorResolver;

public interface BlockAndTintGetter
extends BlockGetter {
    public float func_230487_a_(b_257_Y var1, boolean var2);

    public R_1900_x getLightManager();

    public int getBlockColor(c_1514_x var1, ColorResolver var2);

    default public int getLightFor(K_4719_o lightTypeIn, c_1514_x blockPosIn) {
        return this.getLightManager().n_1700_B(lightTypeIn).n_1700_B(blockPosIn);
    }

    default public int n_1700_B(c_1514_x blockPosIn, int amount) {
        return this.getLightManager().J_1907_R(blockPosIn, amount);
    }

    default public boolean canSeeSky(c_1514_x blockPosIn) {
        return this.getLightFor(K_4719_o.n_1700_B, blockPosIn) >= this.Z_875_P();
    }
}


