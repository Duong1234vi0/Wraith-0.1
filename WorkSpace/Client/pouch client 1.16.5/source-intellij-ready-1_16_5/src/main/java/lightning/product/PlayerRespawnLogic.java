/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.H_1748_a;
import lightning.product.K_4074_S;
import lightning.product.Y_1387_d;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.k_594_Q;
import lightning.product.BlockTags;
import lightning.product.z_2963_s;

public class PlayerRespawnLogic {
    @Nullable
    protected static c_1514_x n_1700_B(e_3591_l p_241092_0_, int p_241092_1_, int p_241092_2_, boolean p_241092_3_) {
        int i;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B(p_241092_1_, 0, p_241092_2_);
        k_594_Q biome = p_241092_0_.P_1922_E(blockpos$mutable);
        boolean flag = p_241092_0_.G_624_v().R_4764_Y();
        K_4074_S blockstate = biome.P_1922_E().P_1922_E().n_1700_B();
        if (p_241092_3_ && !blockstate.J_1907_R().n_1700_B(BlockTags.Z_976_R)) {
            return null;
        }
        H_1748_a chunk = p_241092_0_.u_1723_Y(p_241092_1_ >> 4, p_241092_2_ >> 4);
        int n = i = flag ? p_241092_0_.Y_259_p().t_148_a().R_4764_Y() : chunk.getTopBlockY(z_2963_s.n_1700_B.P_1922_E, p_241092_1_ & 0xF, p_241092_2_ & 0xF);
        if (i < 0) {
            return null;
        }
        int j = chunk.getTopBlockY(z_2963_s.n_1700_B.J_1907_R, p_241092_1_ & 0xF, p_241092_2_ & 0xF);
        if (j <= i && j > chunk.getTopBlockY(z_2963_s.n_1700_B.G_564_y, p_241092_1_ & 0xF, p_241092_2_ & 0xF)) {
            return null;
        }
        for (int k = i + 1; k >= 0; --k) {
            blockpos$mutable.n_1700_B(p_241092_1_, k, p_241092_2_);
            K_4074_S blockstate1 = p_241092_0_.getBlockState(blockpos$mutable);
            if (!blockstate1.P_4830_p().R_4764_Y()) break;
            if (!blockstate1.equals(blockstate)) continue;
            return ((c_1514_x)blockpos$mutable.up()).toImmutable();
        }
        return null;
    }

    @Nullable
    public static c_1514_x n_1700_B(e_3591_l p_241094_0_, Y_1387_d p_241094_1_, boolean p_241094_2_) {
        for (int i = p_241094_1_.J_1907_R(); i <= p_241094_1_.G_564_y(); ++i) {
            for (int j = p_241094_1_.R_4764_Y(); j <= p_241094_1_.P_1922_E(); ++j) {
                c_1514_x blockpos = PlayerRespawnLogic.n_1700_B(p_241094_0_, i, j, p_241094_2_);
                if (blockpos == null) continue;
                return blockpos;
            }
        }
        return null;
    }
}


