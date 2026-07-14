/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public final class ResultField
implements Comparable<ResultField> {
    public final double n_1700_B;
    public final double J_1907_R;
    public final long R_4764_Y;
    public final String G_564_y;

    public ResultField(String p_i51527_1_, double p_i51527_2_, double p_i51527_4_, long p_i51527_6_) {
        this.G_564_y = p_i51527_1_;
        this.n_1700_B = p_i51527_2_;
        this.J_1907_R = p_i51527_4_;
        this.R_4764_Y = p_i51527_6_;
    }

    public int n_1700_B(ResultField p_compareTo_1_) {
        if (p_compareTo_1_.n_1700_B < this.n_1700_B) {
            return -1;
        }
        return p_compareTo_1_.n_1700_B > this.n_1700_B ? 1 : p_compareTo_1_.G_564_y.compareTo(this.G_564_y);
    }

    public int n_1700_B() {
        return (this.G_564_y.hashCode() & 0xAAAAAA) + 0x444444;
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.n_1700_B((ResultField)object);
    }
}


