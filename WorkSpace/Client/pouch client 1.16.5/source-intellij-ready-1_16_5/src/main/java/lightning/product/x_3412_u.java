/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.nio.charset.StandardCharsets;

public class x_3412_u {
    public static final char[] n_1700_B = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String n_1700_B(byte[] p_72661_0_, int p_72661_1_, int p_72661_2_) {
        int j;
        int i = p_72661_2_ - 1;
        int n = j = p_72661_1_ > i ? i : p_72661_1_;
        while (0 != p_72661_0_[j] && j < i) {
            ++j;
        }
        return new String(p_72661_0_, p_72661_1_, j - p_72661_1_, StandardCharsets.UTF_8);
    }

    public static int n_1700_B(byte[] p_72662_0_, int p_72662_1_) {
        return x_3412_u.J_1907_R(p_72662_0_, p_72662_1_, p_72662_0_.length);
    }

    public static int J_1907_R(byte[] p_72665_0_, int p_72665_1_, int p_72665_2_) {
        return 0 > p_72665_2_ - p_72665_1_ - 4 ? 0 : p_72665_0_[p_72665_1_ + 3] << 24 | (p_72665_0_[p_72665_1_ + 2] & 0xFF) << 16 | (p_72665_0_[p_72665_1_ + 1] & 0xFF) << 8 | p_72665_0_[p_72665_1_] & 0xFF;
    }

    public static int R_4764_Y(byte[] p_72664_0_, int p_72664_1_, int p_72664_2_) {
        return 0 > p_72664_2_ - p_72664_1_ - 4 ? 0 : p_72664_0_[p_72664_1_] << 24 | (p_72664_0_[p_72664_1_ + 1] & 0xFF) << 16 | (p_72664_0_[p_72664_1_ + 2] & 0xFF) << 8 | p_72664_0_[p_72664_1_ + 3] & 0xFF;
    }

    public static String n_1700_B(byte input) {
        return "" + n_1700_B[(input & 0xF0) >>> 4] + n_1700_B[input & 0xF];
    }
}

