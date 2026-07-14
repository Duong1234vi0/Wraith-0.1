/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public class i_452_g
implements Runnable {
    private final int n_1700_B;
    private final Runnable J_1907_R;

    public i_452_g(int p_i50745_1_, Runnable p_i50745_2_) {
        this.n_1700_B = p_i50745_1_;
        this.J_1907_R = p_i50745_2_;
    }

    public int n_1700_B() {
        return this.n_1700_B;
    }

    @Override
    public void run() {
        this.J_1907_R.run();
    }
}

