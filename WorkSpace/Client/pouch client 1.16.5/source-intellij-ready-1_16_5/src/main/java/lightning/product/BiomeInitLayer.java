/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.V_4170_D;
import lightning.product.C0Transformer;
import lightning.product.Context;

public class BiomeInitLayer
implements C0Transformer {
    private static final int[] n_1700_B = new int[]{2, 4, 3, 6, 1, 5};
    private static final int[] J_1907_R = new int[]{2, 2, 2, 35, 35, 1};
    private static final int[] R_4764_Y = new int[]{4, 29, 3, 1, 27, 6};
    private static final int[] G_564_y = new int[]{4, 3, 5, 1};
    private static final int[] P_1922_E = new int[]{12, 12, 12, 30};
    private int[] u_1723_Y = J_1907_R;

    public BiomeInitLayer(boolean p_i232147_1_) {
        if (p_i232147_1_) {
            this.u_1723_Y = n_1700_B;
        }
    }

    @Override
    public int n_1700_B(Context context, int value) {
        int i = (value & 0xF00) >> 8;
        if (!V_4170_D.n_1700_B(value &= 0xFFFFF0FF) && value != 14) {
            switch (value) {
                case 1: {
                    if (i > 0) {
                        return context.n_1700_B(3) == 0 ? 39 : 38;
                    }
                    return this.u_1723_Y[context.n_1700_B(this.u_1723_Y.length)];
                }
                case 2: {
                    if (i > 0) {
                        return 21;
                    }
                    return R_4764_Y[context.n_1700_B(R_4764_Y.length)];
                }
                case 3: {
                    if (i > 0) {
                        return 32;
                    }
                    return G_564_y[context.n_1700_B(G_564_y.length)];
                }
                case 4: {
                    return P_1922_E[context.n_1700_B(P_1922_E.length)];
                }
            }
            return 14;
        }
        return value;
    }
}


