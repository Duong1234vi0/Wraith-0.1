/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.Validate
 */
package lightning.product;

import java.util.function.IntConsumer;
import javax.annotation.Nullable;
import lightning.product.j_3341_s;
import mods.baritone.utils.accessor.IBitArray;
import org.apache.commons.lang3.Validate;

public class J_270_s
implements IBitArray {
    private static final int[] n_1700_B = new int[]{-1, -1, 0, Integer.MIN_VALUE, 0, 0, 0x55555555, 0x55555555, 0, Integer.MIN_VALUE, 0, 1, 0x33333333, 0x33333333, 0, 0x2AAAAAAA, 0x2AAAAAAA, 0, 0x24924924, 0x24924924, 0, Integer.MIN_VALUE, 0, 2, 0x1C71C71C, 0x1C71C71C, 0, 0x19999999, 0x19999999, 0, 390451572, 390451572, 0, 0x15555555, 0x15555555, 0, 0x13B13B13, 0x13B13B13, 0, 306783378, 306783378, 0, 0x11111111, 0x11111111, 0, Integer.MIN_VALUE, 0, 3, 0xF0F0F0F, 0xF0F0F0F, 0, 0xE38E38E, 0xE38E38E, 0, 226050910, 226050910, 0, 0xCCCCCCC, 0xCCCCCCC, 0, 0xC30C30C, 0xC30C30C, 0, 195225786, 195225786, 0, 186737708, 186737708, 0, 0xAAAAAAA, 0xAAAAAAA, 0, 171798691, 171798691, 0, 0x9D89D89, 0x9D89D89, 0, 159072862, 159072862, 0, 0x9249249, 0x9249249, 0, 148102320, 148102320, 0, 0x8888888, 0x8888888, 0, 138547332, 138547332, 0, Integer.MIN_VALUE, 0, 4, 130150524, 130150524, 0, 0x7878787, 0x7878787, 0, 0x7507507, 0x7507507, 0, 0x71C71C7, 0x71C71C7, 0, 116080197, 116080197, 0, 113025455, 113025455, 0, 0x6906906, 0x6906906, 0, 0x6666666, 0x6666666, 0, 104755299, 104755299, 0, 0x6186186, 0x6186186, 0, 99882960, 99882960, 0, 97612893, 97612893, 0, 0x5B05B05, 0x5B05B05, 0, 93368854, 93368854, 0, 91382282, 91382282, 0, 0x5555555, 0x5555555, 0, 87652393, 87652393, 0, 85899345, 85899345, 0, 0x5050505, 0x5050505, 0, 0x4EC4EC4, 0x4EC4EC4, 0, 81037118, 81037118, 0, 79536431, 79536431, 0, 78090314, 78090314, 0, 0x4924924, 0x4924924, 0, 75350303, 75350303, 0, 74051160, 74051160, 0, 72796055, 72796055, 0, 0x4444444, 0x4444444, 0, 70409299, 70409299, 0, 69273666, 69273666, 0, 0x4104104, 0x4104104, 0, Integer.MIN_VALUE, 0, 5};
    private final long[] J_1907_R;
    private final int R_4764_Y;
    private final long G_564_y;
    private final int P_1922_E;
    private final int u_1723_Y;
    private final int v_4262_N;
    private final int w_1484_f;
    private final int t_148_a;

    public J_270_s(int bitsPerEntryIn, int arraySizeIn) {
        this(bitsPerEntryIn, arraySizeIn, null);
    }

    public J_270_s(int bitsPerEntryIn, int arraySizeIn, @Nullable long[] data) {
        Validate.inclusiveBetween((long)1L, (long)32L, (long)bitsPerEntryIn);
        this.P_1922_E = arraySizeIn;
        this.R_4764_Y = bitsPerEntryIn;
        this.G_564_y = (1L << bitsPerEntryIn) - 1L;
        this.u_1723_Y = (char)(64 / bitsPerEntryIn);
        int i = 3 * (this.u_1723_Y - 1);
        this.v_4262_N = n_1700_B[i + 0];
        this.w_1484_f = n_1700_B[i + 1];
        this.t_148_a = n_1700_B[i + 2];
        int j = (arraySizeIn + this.u_1723_Y - 1) / this.u_1723_Y;
        if (data != null) {
            if (data.length != j) {
                throw j_3341_s.R_4764_Y(new RuntimeException("Invalid length given for storage, got: " + data.length + " but expected: " + j));
            }
            this.J_1907_R = data;
        } else {
            this.J_1907_R = new long[j];
        }
    }

    private int J_1907_R(int p_232986_1_) {
        long i = Integer.toUnsignedLong(this.v_4262_N);
        long j = Integer.toUnsignedLong(this.w_1484_f);
        return (int)((long)p_232986_1_ * i + j >> 32 >> this.t_148_a);
    }

    public int n_1700_B(int index, int value) {
        Validate.inclusiveBetween((long)0L, (long)(this.P_1922_E - 1), (long)index);
        Validate.inclusiveBetween((long)0L, (long)this.G_564_y, (long)value);
        int i = this.J_1907_R(index);
        long j = this.J_1907_R[i];
        int k = (index - i * this.u_1723_Y) * this.R_4764_Y;
        int l = (int)(j >> k & this.G_564_y);
        this.J_1907_R[i] = j & (this.G_564_y << k ^ 0xFFFFFFFFFFFFFFFFL) | ((long)value & this.G_564_y) << k;
        return l;
    }

    public void J_1907_R(int index, int value) {
        Validate.inclusiveBetween((long)0L, (long)(this.P_1922_E - 1), (long)index);
        Validate.inclusiveBetween((long)0L, (long)this.G_564_y, (long)value);
        int i = this.J_1907_R(index);
        long j = this.J_1907_R[i];
        int k = (index - i * this.u_1723_Y) * this.R_4764_Y;
        this.J_1907_R[i] = j & (this.G_564_y << k ^ 0xFFFFFFFFFFFFFFFFL) | ((long)value & this.G_564_y) << k;
    }

    public int n_1700_B(int index) {
        Validate.inclusiveBetween((long)0L, (long)(this.P_1922_E - 1), (long)index);
        int i = this.J_1907_R(index);
        long j = this.J_1907_R[i];
        int k = (index - i * this.u_1723_Y) * this.R_4764_Y;
        return (int)(j >> k & this.G_564_y);
    }

    public long[] n_1700_B() {
        return this.J_1907_R;
    }

    public int J_1907_R() {
        return this.P_1922_E;
    }

    public void n_1700_B(IntConsumer consumer) {
        int i = 0;
        for (long j : this.J_1907_R) {
            for (int k = 0; k < this.u_1723_Y; ++k) {
                consumer.accept((int)(j & this.G_564_y));
                j >>= this.R_4764_Y;
                if (++i < this.P_1922_E) continue;
                return;
            }
        }
    }

    @Override
    public long getMaxEntryValue() {
        return this.G_564_y;
    }

    @Override
    public int getBitsPerEntry() {
        return this.R_4764_Y;
    }
}

