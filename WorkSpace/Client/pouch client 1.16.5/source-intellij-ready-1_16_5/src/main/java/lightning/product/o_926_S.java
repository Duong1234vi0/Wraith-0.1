/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public class o_926_S {
    public static final o_926_S n_1700_B = new o_926_S(0L){

        @Override
        public void n_1700_B(long bits) {
        }
    };
    private final long J_1907_R;
    private long R_4764_Y;

    public o_926_S(long max) {
        this.J_1907_R = max;
    }

    public void n_1700_B(long bits) {
        this.R_4764_Y += bits / 8L;
        if (this.R_4764_Y > this.J_1907_R) {
            throw new RuntimeException("Tried to read NBT tag that was too big; tried to allocate: " + this.R_4764_Y + "bytes where max allowed: " + this.J_1907_R);
        }
    }
}

