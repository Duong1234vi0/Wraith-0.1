/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.K_4074_S;
import lightning.product.K_4096_w;
import lightning.product.T_2915_h;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.Enchantments;
import lightning.product.e_3591_l;
import lightning.product.q_4293_E;
import lightning.product.u_530_F;

public class OreBlock
extends T_2915_h {
    public OreBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    protected int n_1700_B(Random rand) {
        if (this == a_3742_W.n_3318_d) {
            return u_530_F.n_1700_B(rand, 0, 2);
        }
        if (this == a_3742_W.L_4248_u) {
            return u_530_F.n_1700_B(rand, 3, 7);
        }
        if (this == a_3742_W.V_1665_T) {
            return u_530_F.n_1700_B(rand, 3, 7);
        }
        if (this == a_3742_W.D_60_a) {
            return u_530_F.n_1700_B(rand, 2, 5);
        }
        if (this == a_3742_W.N_2266_w) {
            return u_530_F.n_1700_B(rand, 2, 5);
        }
        return this == a_3742_W.d_2427_y ? u_530_F.n_1700_B(rand, 0, 1) : 0;
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Z_1993_T stack) {
        int i;
        super.n_1700_B(state, worldIn, pos, stack);
        if (K_4096_w.n_1700_B(Enchantments.Y_259_p, stack) == 0 && (i = this.n_1700_B(worldIn.w_1457_N)) > 0) {
            this.n_1700_B(worldIn, pos, i);
        }
    }
}


