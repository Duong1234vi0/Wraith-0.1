/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ImprovedNoise;
import lightning.product.AreaTransformer0;
import lightning.product.Context;

public final class n_2175_F
extends Enum<n_2175_F>
implements AreaTransformer0 {
    public static final /* enum */ n_2175_F n_1700_B = new n_2175_F();
    private static final /* synthetic */ n_2175_F[] J_1907_R;

    public static n_2175_F[] values() {
        return (n_2175_F[])J_1907_R.clone();
    }

    public static n_2175_F valueOf(String name) {
        return Enum.valueOf(n_2175_F.class, name);
    }

    @Override
    public int n_1700_B(Context p_215735_1_, int p_215735_2_, int p_215735_3_) {
        ImprovedNoise improvednoisegenerator = p_215735_1_.n_1700_B();
        double d0 = improvednoisegenerator.n_1700_B((double)p_215735_2_ / 8.0, (double)p_215735_3_ / 8.0, 0.0, 0.0, 0.0);
        if (d0 > 0.4) {
            return 44;
        }
        if (d0 > 0.2) {
            return 45;
        }
        if (d0 < -0.4) {
            return 10;
        }
        return d0 < -0.2 ? 46 : 0;
    }

    private static /* synthetic */ n_2175_F[] n_1700_B() {
        return new n_2175_F[]{n_1700_B};
    }

    static {
        J_1907_R = n_2175_F.n_1700_B();
    }
}


