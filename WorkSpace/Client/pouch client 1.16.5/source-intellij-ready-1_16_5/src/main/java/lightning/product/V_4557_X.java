/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public class V_4557_X {
    private long n_1700_B;
    private final long J_1907_R = System.currentTimeMillis();

    public V_4557_X() {
        this.n_1700_B();
    }

    public void n_1700_B() {
        this.n_1700_B = System.currentTimeMillis();
    }

    public long J_1907_R() {
        return System.currentTimeMillis() - this.n_1700_B;
    }

    public void n_1700_B(long time) {
        this.n_1700_B = time;
    }

    public boolean J_1907_R(long time) {
        return this.J_1907_R() >= time;
    }

    public boolean n_1700_B(long time, boolean reset) {
        boolean hasElapsed;
        boolean bl = hasElapsed = this.J_1907_R() >= time;
        if (hasElapsed && reset) {
            this.n_1700_B();
        }
        return hasElapsed;
    }

    public boolean R_4764_Y() {
        return this.J_1907_R < System.currentTimeMillis();
    }

    public boolean n_1700_B(double milliseconds) {
        return (double)this.J_1907_R() >= milliseconds;
    }
}

