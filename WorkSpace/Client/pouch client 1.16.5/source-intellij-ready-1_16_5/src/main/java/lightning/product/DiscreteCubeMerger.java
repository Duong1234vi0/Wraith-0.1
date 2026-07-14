/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.math.IntMath
 *  it.unimi.dsi.fastutil.doubles.DoubleList
 */
package lightning.product;

import com.google.common.math.IntMath;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import lightning.product.IndexMerger;
import lightning.product.CubePointRange;
import lightning.product.x_268_Y;

public final class DiscreteCubeMerger
implements IndexMerger {
    private final CubePointRange n_1700_B;
    private final int J_1907_R;
    private final int R_4764_Y;
    private final int G_564_y;

    DiscreteCubeMerger(int firstSize, int secondSize) {
        this.n_1700_B = new CubePointRange((int)x_268_Y.n_1700_B(firstSize, secondSize));
        this.J_1907_R = firstSize;
        this.R_4764_Y = secondSize;
        this.G_564_y = IntMath.gcd((int)firstSize, (int)secondSize);
    }

    @Override
    public boolean n_1700_B(IndexMerger.n_1700_B consumer) {
        int i = this.J_1907_R / this.G_564_y;
        int j = this.R_4764_Y / this.G_564_y;
        for (int k = 0; k <= this.n_1700_B.size(); ++k) {
            if (consumer.merge(k / j, k / i, k)) continue;
            return false;
        }
        return true;
    }

    @Override
    public DoubleList n_1700_B() {
        return this.n_1700_B;
    }
}


