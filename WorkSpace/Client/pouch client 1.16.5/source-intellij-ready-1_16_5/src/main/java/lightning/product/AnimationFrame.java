/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public class AnimationFrame {
    private final int n_1700_B;
    private final int J_1907_R;

    public AnimationFrame(int frameIndexIn) {
        this(frameIndexIn, -1);
    }

    public AnimationFrame(int frameIndexIn, int frameTimeIn) {
        this.n_1700_B = frameIndexIn;
        this.J_1907_R = frameTimeIn;
    }

    public boolean n_1700_B() {
        return this.J_1907_R == -1;
    }

    public int J_1907_R() {
        return this.J_1907_R;
    }

    public int R_4764_Y() {
        return this.n_1700_B;
    }
}


