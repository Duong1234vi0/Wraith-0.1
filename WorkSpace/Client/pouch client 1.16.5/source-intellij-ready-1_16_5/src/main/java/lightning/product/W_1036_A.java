/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.C_4709_v;
import lightning.product.AreaTransformer2;
import lightning.product.V_4170_D;
import lightning.product.Context;
import lightning.product.t_4013_W;

public final class W_1036_A
extends Enum<W_1036_A>
implements C_4709_v,
AreaTransformer2 {
    public static final /* enum */ W_1036_A n_1700_B = new W_1036_A();
    private static final /* synthetic */ W_1036_A[] J_1907_R;

    public static W_1036_A[] values() {
        return (W_1036_A[])J_1907_R.clone();
    }

    public static W_1036_A valueOf(String name) {
        return Enum.valueOf(W_1036_A.class, name);
    }

    @Override
    public int n_1700_B(Context p_215723_1_, t_4013_W p_215723_2_, t_4013_W p_215723_3_, int p_215723_4_, int p_215723_5_) {
        int i = p_215723_2_.n_1700_B(this.n_1700_B(p_215723_4_), this.J_1907_R(p_215723_5_));
        int j = p_215723_3_.n_1700_B(this.n_1700_B(p_215723_4_), this.J_1907_R(p_215723_5_));
        if (!V_4170_D.n_1700_B(i)) {
            return i;
        }
        int k = 8;
        int l = 4;
        for (int i1 = -8; i1 <= 8; i1 += 4) {
            for (int j1 = -8; j1 <= 8; j1 += 4) {
                int k1 = p_215723_2_.n_1700_B(this.n_1700_B(p_215723_4_ + i1), this.J_1907_R(p_215723_5_ + j1));
                if (V_4170_D.n_1700_B(k1)) continue;
                if (j == 44) {
                    return 45;
                }
                if (j != 10) continue;
                return 46;
            }
        }
        if (i == 24) {
            if (j == 45) {
                return 48;
            }
            if (j == 0) {
                return 24;
            }
            if (j == 46) {
                return 49;
            }
            if (j == 10) {
                return 50;
            }
        }
        return j;
    }

    private static /* synthetic */ W_1036_A[] n_1700_B() {
        return new W_1036_A[]{n_1700_B};
    }

    static {
        J_1907_R = W_1036_A.n_1700_B();
    }
}


