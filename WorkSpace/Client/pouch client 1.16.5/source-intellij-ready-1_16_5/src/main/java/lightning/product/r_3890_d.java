/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Objects;
import lightning.product.TicketType;

public final class r_3890_d<T>
implements Comparable<r_3890_d<?>> {
    private final TicketType<T> n_1700_B;
    private final int J_1907_R;
    private final T R_4764_Y;
    private long G_564_y;

    protected r_3890_d(TicketType<T> p_i226095_1_, int p_i226095_2_, T p_i226095_3_) {
        this.n_1700_B = p_i226095_1_;
        this.J_1907_R = p_i226095_2_;
        this.R_4764_Y = p_i226095_3_;
    }

    public int n_1700_B(r_3890_d<?> p_compareTo_1_) {
        int i = Integer.compare(this.J_1907_R, p_compareTo_1_.J_1907_R);
        if (i != 0) {
            return i;
        }
        int j = Integer.compare(System.identityHashCode(this.n_1700_B), System.identityHashCode(p_compareTo_1_.n_1700_B));
        return j != 0 ? j : this.n_1700_B.n_1700_B().compare(this.R_4764_Y, p_compareTo_1_.R_4764_Y);
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof r_3890_d)) {
            return false;
        }
        r_3890_d ticket = (r_3890_d)p_equals_1_;
        return this.J_1907_R == ticket.J_1907_R && Objects.equals(this.n_1700_B, ticket.n_1700_B) && Objects.equals(this.R_4764_Y, ticket.R_4764_Y);
    }

    public int hashCode() {
        return Objects.hash(this.n_1700_B, this.J_1907_R, this.R_4764_Y);
    }

    public String toString() {
        return "Ticket[" + String.valueOf(this.n_1700_B) + " " + this.J_1907_R + " (" + String.valueOf(this.R_4764_Y) + ")] at " + this.G_564_y;
    }

    public TicketType<T> n_1700_B() {
        return this.n_1700_B;
    }

    public int J_1907_R() {
        return this.J_1907_R;
    }

    protected void n_1700_B(long p_229861_1_) {
        this.G_564_y = p_229861_1_;
    }

    protected boolean J_1907_R(long currentTime) {
        long i = this.n_1700_B.J_1907_R();
        return i != 0L && currentTime - this.G_564_y > i;
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.n_1700_B((r_3890_d)object);
    }
}


