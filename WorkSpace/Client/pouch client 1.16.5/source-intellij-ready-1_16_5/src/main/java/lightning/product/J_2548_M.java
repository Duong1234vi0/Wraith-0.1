/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;

public class J_2548_M {
    private final int n_1700_B;
    private final int J_1907_R;

    public J_2548_M(int minInclusive, int max) {
        if (max < minInclusive) {
            throw new IllegalArgumentException("max must be >= minInclusive! Given minInclusive: " + minInclusive + ", Given max: " + max);
        }
        this.n_1700_B = minInclusive;
        this.J_1907_R = max;
    }

    public static J_2548_M n_1700_B(int minInclusive, int max) {
        return new J_2548_M(minInclusive, max);
    }

    public int n_1700_B(Random rand) {
        return this.n_1700_B == this.J_1907_R ? this.n_1700_B : rand.nextInt(this.J_1907_R - this.n_1700_B + 1) + this.n_1700_B;
    }

    public int n_1700_B() {
        return this.n_1700_B;
    }

    public int J_1907_R() {
        return this.J_1907_R;
    }

    public String toString() {
        return "IntRange[" + this.n_1700_B + "-" + this.J_1907_R + "]";
    }
}

