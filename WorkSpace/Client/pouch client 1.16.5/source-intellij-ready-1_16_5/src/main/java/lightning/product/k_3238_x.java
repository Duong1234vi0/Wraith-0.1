/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;

public class k_3238_x {
    private final char[] n_1700_B;
    private int J_1907_R;
    private final Runnable R_4764_Y;

    public k_3238_x(char[] p_i51793_1_, Runnable p_i51793_2_) {
        this.R_4764_Y = p_i51793_2_;
        if (p_i51793_1_.length < 1) {
            throw new IllegalArgumentException("Must have at least one char");
        }
        this.n_1700_B = p_i51793_1_;
    }

    public boolean n_1700_B(char p_224799_1_) {
        if (p_224799_1_ == this.n_1700_B[this.J_1907_R++]) {
            if (this.J_1907_R == this.n_1700_B.length) {
                this.n_1700_B();
                this.R_4764_Y.run();
                return true;
            }
        } else {
            this.n_1700_B();
        }
        return false;
    }

    public void n_1700_B() {
        this.J_1907_R = 0;
    }

    public String toString() {
        return "KeyCombo{chars=" + Arrays.toString(this.n_1700_B) + ", matchIndex=" + this.J_1907_R + "}";
    }
}

