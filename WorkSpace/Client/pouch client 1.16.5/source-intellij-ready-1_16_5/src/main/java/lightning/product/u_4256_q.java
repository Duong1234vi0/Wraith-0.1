/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Set;
import lightning.product.b_257_Y;

public class u_4256_q {
    private static final int n_1700_B = b_257_Y.values().length;
    private long J_1907_R;

    public void n_1700_B(Set<b_257_Y> facing) {
        for (b_257_Y direction : facing) {
            for (b_257_Y direction1 : facing) {
                this.n_1700_B(direction, direction1, true);
            }
        }
    }

    public void n_1700_B(b_257_Y facing, b_257_Y facing2, boolean value) {
        this.n_1700_B(facing.ordinal() + facing2.ordinal() * n_1700_B, value);
        this.n_1700_B(facing2.ordinal() + facing.ordinal() * n_1700_B, value);
    }

    public void n_1700_B(boolean visible) {
        this.J_1907_R = visible ? -1L : 0L;
    }

    public boolean n_1700_B(b_257_Y facing, b_257_Y facing2) {
        return this.n_1700_B(facing.ordinal() + facing2.ordinal() * n_1700_B);
    }

    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(' ');
        for (b_257_Y direction : b_257_Y.values()) {
            stringbuilder.append(' ').append(direction.toString().toUpperCase().charAt(0));
        }
        stringbuilder.append('\n');
        for (b_257_Y direction2 : b_257_Y.values()) {
            stringbuilder.append(direction2.toString().toUpperCase().charAt(0));
            for (b_257_Y direction1 : b_257_Y.values()) {
                if (direction2 == direction1) {
                    stringbuilder.append("  ");
                    continue;
                }
                boolean flag = this.n_1700_B(direction2, direction1);
                stringbuilder.append(' ').append(flag ? (char)'Y' : 'n');
            }
            stringbuilder.append('\n');
        }
        return stringbuilder.toString();
    }

    private boolean n_1700_B(int p_getBit_1_) {
        return (this.J_1907_R & (long)(1 << p_getBit_1_)) != 0L;
    }

    private void n_1700_B(int p_setBit_1_, boolean p_setBit_2_) {
        if (p_setBit_2_) {
            this.J_1907_R(p_setBit_1_);
        } else {
            this.R_4764_Y(p_setBit_1_);
        }
    }

    private void J_1907_R(int p_setBit_1_) {
        this.J_1907_R |= (long)(1 << p_setBit_1_);
    }

    private void R_4764_Y(int p_clearBit_1_) {
        this.J_1907_R &= (long)(~(1 << p_clearBit_1_));
    }
}

