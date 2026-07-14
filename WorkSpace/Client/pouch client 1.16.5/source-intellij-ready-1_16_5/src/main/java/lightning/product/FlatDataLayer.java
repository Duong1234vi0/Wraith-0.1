/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.DataLayer;

public class FlatDataLayer
extends DataLayer {
    public FlatDataLayer() {
        super(128);
    }

    public FlatDataLayer(DataLayer p_i51297_1_, int p_i51297_2_) {
        super(128);
        System.arraycopy(p_i51297_1_.n_1700_B(), p_i51297_2_ * 128, this.n_1700_B, 0, 128);
    }

    @Override
    protected int J_1907_R(int x, int y, int z) {
        return z << 4 | x;
    }

    @Override
    public byte[] n_1700_B() {
        byte[] abyte = new byte[2048];
        for (int i = 0; i < 16; ++i) {
            System.arraycopy(this.n_1700_B, 0, abyte, i * 128, 128);
        }
        return abyte;
    }
}


