/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.WallTorchBlock;
import lightning.product.K_4074_S;
import lightning.product.WorldGenLevel;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.o_2105_O;
import lightning.product.z_1753_f;

public class EndPodiumFeature
extends Feature<o_2105_O> {
    public static final c_1514_x n_1700_B = c_1514_x.ZERO;
    private final boolean D_4792_h;

    public EndPodiumFeature(boolean activePortalIn) {
        super(o_2105_O.n_1700_B);
        this.D_4792_h = activePortalIn;
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, o_2105_O p_241855_5_) {
        for (c_1514_x blockpos : c_1514_x.getAllInBoxMutable(new c_1514_x(p_241855_4_.getX() - 4, p_241855_4_.getY() - 1, p_241855_4_.getZ() - 4), new c_1514_x(p_241855_4_.getX() + 4, p_241855_4_.getY() + 32, p_241855_4_.getZ() + 4))) {
            boolean flag = blockpos.withinDistance(p_241855_4_, 2.5);
            if (!flag && !blockpos.withinDistance(p_241855_4_, 3.5)) continue;
            if (blockpos.getY() < p_241855_4_.getY()) {
                if (flag) {
                    this.n_1700_B(p_241855_1_, blockpos, a_3742_W.Z_875_P.multiplayerClientSuggestionProvider());
                    continue;
                }
                if (blockpos.getY() >= p_241855_4_.getY()) continue;
                this.n_1700_B(p_241855_1_, blockpos, a_3742_W.e_1231_S.multiplayerClientSuggestionProvider());
                continue;
            }
            if (blockpos.getY() > p_241855_4_.getY()) {
                this.n_1700_B(p_241855_1_, blockpos, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider());
                continue;
            }
            if (!flag) {
                this.n_1700_B(p_241855_1_, blockpos, a_3742_W.Z_875_P.multiplayerClientSuggestionProvider());
                continue;
            }
            if (this.D_4792_h) {
                this.n_1700_B(p_241855_1_, new c_1514_x(blockpos), a_3742_W.M_2562_s.multiplayerClientSuggestionProvider());
                continue;
            }
            this.n_1700_B(p_241855_1_, new c_1514_x(blockpos), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider());
        }
        for (int i = 0; i < 4; ++i) {
            this.n_1700_B(p_241855_1_, p_241855_4_.up(i), a_3742_W.Z_875_P.multiplayerClientSuggestionProvider());
        }
        c_1514_x blockpos1 = p_241855_4_.up(2);
        for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
            this.n_1700_B(p_241855_1_, blockpos1.offset(direction), (K_4074_S)a_3742_W.C_1269_X.multiplayerClientSuggestionProvider().n_1700_B(WallTorchBlock.P_4830_p, direction));
        }
        return true;
    }
}


