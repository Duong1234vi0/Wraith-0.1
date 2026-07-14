/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.BitSet;

public class RegionBitmap {
    private final BitSet n_1700_B = new BitSet();

    public void n_1700_B(int p_227120_1_, int p_227120_2_) {
        this.n_1700_B.set(p_227120_1_, p_227120_1_ + p_227120_2_);
    }

    public void J_1907_R(int p_227121_1_, int p_227121_2_) {
        this.n_1700_B.clear(p_227121_1_, p_227121_1_ + p_227121_2_);
    }

    public int n_1700_B(int p_227119_1_) {
        int i = 0;
        while (true) {
            int j;
            int k;
            if ((k = this.n_1700_B.nextSetBit(j = this.n_1700_B.nextClearBit(i))) == -1 || k - j >= p_227119_1_) {
                this.n_1700_B(j, p_227119_1_);
                return j;
            }
            i = k;
        }
    }
}


