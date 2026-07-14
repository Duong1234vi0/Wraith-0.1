/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.AreaTransformer0;
import lightning.product.Context;

public final class B_2976_q
extends Enum<B_2976_q>
implements AreaTransformer0 {
    public static final /* enum */ B_2976_q n_1700_B = new B_2976_q();
    private static final /* synthetic */ B_2976_q[] J_1907_R;

    public static B_2976_q[] values() {
        return (B_2976_q[])J_1907_R.clone();
    }

    public static B_2976_q valueOf(String name) {
        return Enum.valueOf(B_2976_q.class, name);
    }

    @Override
    public int n_1700_B(Context p_215735_1_, int p_215735_2_, int p_215735_3_) {
        if (p_215735_2_ == 0 && p_215735_3_ == 0) {
            return 1;
        }
        return p_215735_1_.n_1700_B(10) == 0 ? 1 : 0;
    }

    private static /* synthetic */ B_2976_q[] n_1700_B() {
        return new B_2976_q[]{n_1700_B};
    }

    static {
        J_1907_R = B_2976_q.n_1700_B();
    }
}


