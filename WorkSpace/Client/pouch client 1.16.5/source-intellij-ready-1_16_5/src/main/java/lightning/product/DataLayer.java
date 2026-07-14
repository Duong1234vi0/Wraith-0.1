/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.j_3341_s;

public class DataLayer {
    @Nullable
    protected byte[] n_1700_B;

    public DataLayer() {
    }

    public DataLayer(byte[] storageArray) {
        this.n_1700_B = storageArray;
        if (storageArray.length != 2048) {
            throw j_3341_s.R_4764_Y(new IllegalArgumentException("ChunkNibbleArrays should be 2048 bytes not: " + storageArray.length));
        }
    }

    protected DataLayer(int size) {
        this.n_1700_B = new byte[size];
    }

    public int n_1700_B(int x, int y, int z) {
        return this.n_1700_B(this.J_1907_R(x, y, z));
    }

    public void n_1700_B(int x, int y, int z, int value) {
        this.n_1700_B(this.J_1907_R(x, y, z), value);
    }

    protected int J_1907_R(int x, int y, int z) {
        return y << 8 | z << 4 | x;
    }

    private int n_1700_B(int index) {
        if (this.n_1700_B == null) {
            return 0;
        }
        int i = this.R_4764_Y(index);
        return this.J_1907_R(index) ? this.n_1700_B[i] & 0xF : this.n_1700_B[i] >> 4 & 0xF;
    }

    private void n_1700_B(int index, int value) {
        if (this.n_1700_B == null) {
            this.n_1700_B = new byte[2048];
        }
        int i = this.R_4764_Y(index);
        this.n_1700_B[i] = this.J_1907_R(index) ? (byte)(this.n_1700_B[i] & 0xF0 | value & 0xF) : (byte)(this.n_1700_B[i] & 0xF | (value & 0xF) << 4);
    }

    private boolean J_1907_R(int index) {
        return (index & 1) == 0;
    }

    private int R_4764_Y(int index) {
        return index >> 1;
    }

    public byte[] n_1700_B() {
        if (this.n_1700_B == null) {
            this.n_1700_B = new byte[2048];
        }
        return this.n_1700_B;
    }

    public DataLayer J_1907_R() {
        return this.n_1700_B == null ? new DataLayer() : new DataLayer((byte[])this.n_1700_B.clone());
    }

    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();
        for (int i = 0; i < 4096; ++i) {
            stringbuilder.append(Integer.toHexString(this.n_1700_B(i)));
            if ((i & 0xF) == 15) {
                stringbuilder.append("\n");
            }
            if ((i & 0xFF) != 255) continue;
            stringbuilder.append("\n");
        }
        return stringbuilder.toString();
    }

    public boolean R_4764_Y() {
        return this.n_1700_B == null;
    }
}


