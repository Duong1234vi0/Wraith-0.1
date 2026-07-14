/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public class A_1603_w {
    private long n_1700_B;

    public A_1603_w() {
        this.n_1700_B();
    }

    public boolean n_1700_B(long delay) {
        if (System.currentTimeMillis() - this.n_1700_B >= delay) {
            this.n_1700_B();
            return true;
        }
        return false;
    }

    public void n_1700_B() {
        this.n_1700_B = System.currentTimeMillis();
    }

    public long J_1907_R() {
        return System.currentTimeMillis() - this.n_1700_B;
    }
}

