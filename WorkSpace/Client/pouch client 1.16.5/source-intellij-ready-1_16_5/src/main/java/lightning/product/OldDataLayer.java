/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public class OldDataLayer {
    public final byte[] n_1700_B;
    private final int J_1907_R;
    private final int R_4764_Y;

    public OldDataLayer(byte[] dataIn, int depthBitsIn) {
        this.n_1700_B = dataIn;
        this.J_1907_R = depthBitsIn;
        this.R_4764_Y = depthBitsIn + 4;
    }

    public int n_1700_B(int x, int y, int z) {
        int i = x << this.R_4764_Y | z << this.J_1907_R | y;
        int j = i >> 1;
        int k = i & 1;
        return k == 0 ? this.n_1700_B[j] & 0xF : this.n_1700_B[j] >> 4 & 0xF;
    }
}


