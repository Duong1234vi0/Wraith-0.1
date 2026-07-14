/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.C_4709_v;
import lightning.product.AreaTransformer2;
import lightning.product.V_4170_D;
import lightning.product.Context;
import lightning.product.t_4013_W;

public final class C_2991_c
extends Enum<C_2991_c>
implements C_4709_v,
AreaTransformer2 {
    public static final /* enum */ C_2991_c n_1700_B = new C_2991_c();
    private static final /* synthetic */ C_2991_c[] J_1907_R;

    public static C_2991_c[] values() {
        return (C_2991_c[])J_1907_R.clone();
    }

    public static C_2991_c valueOf(String name) {
        return Enum.valueOf(C_2991_c.class, name);
    }

    @Override
    public int n_1700_B(Context p_215723_1_, t_4013_W p_215723_2_, t_4013_W p_215723_3_, int p_215723_4_, int p_215723_5_) {
        int i = p_215723_2_.n_1700_B(this.n_1700_B(p_215723_4_), this.J_1907_R(p_215723_5_));
        int j = p_215723_3_.n_1700_B(this.n_1700_B(p_215723_4_), this.J_1907_R(p_215723_5_));
        if (V_4170_D.n_1700_B(i)) {
            return i;
        }
        if (j == 7) {
            if (i == 12) {
                return 11;
            }
            return i != 14 && i != 15 ? j & 0xFF : 15;
        }
        return i;
    }

    private static /* synthetic */ C_2991_c[] n_1700_B() {
        return new C_2991_c[]{n_1700_B};
    }

    static {
        J_1907_R = C_2991_c.n_1700_B();
    }
}


