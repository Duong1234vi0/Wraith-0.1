/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public class LinearCongruentialGenerator {
    public static long n_1700_B(long left, long right) {
        left *= left * 6364136223846793005L + 1442695040888963407L;
        return left + right;
    }
}


