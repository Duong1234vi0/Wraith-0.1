/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.WorldGenLevel;
import lightning.product.Y_1387_d;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.o_2105_O;
import lightning.product.z_1753_f;

public class VoidStartPlatformFeature
extends Feature<o_2105_O> {
    private static final c_1514_x n_1700_B = new c_1514_x(8, 3, 8);
    private static final Y_1387_d D_4792_h = new Y_1387_d(n_1700_B);

    public VoidStartPlatformFeature(Codec<o_2105_O> p_i232003_1_) {
        super(p_i232003_1_);
    }

    private static int n_1700_B(int firstX, int firstZ, int secondX, int secondZ) {
        return Math.max(Math.abs(firstX - secondX), Math.abs(firstZ - secondZ));
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, o_2105_O p_241855_5_) {
        Y_1387_d chunkpos = new Y_1387_d(p_241855_4_);
        if (VoidStartPlatformFeature.n_1700_B(chunkpos.J_1907_R, chunkpos.R_4764_Y, VoidStartPlatformFeature.D_4792_h.J_1907_R, VoidStartPlatformFeature.D_4792_h.R_4764_Y) > 1) {
            return true;
        }
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int i = chunkpos.R_4764_Y(); i <= chunkpos.P_1922_E(); ++i) {
            for (int j = chunkpos.J_1907_R(); j <= chunkpos.G_564_y(); ++j) {
                if (VoidStartPlatformFeature.n_1700_B(n_1700_B.getX(), n_1700_B.getZ(), j, i) > 16) continue;
                blockpos$mutable.n_1700_B(j, n_1700_B.getY(), i);
                if (blockpos$mutable.equals(n_1700_B)) {
                    p_241855_1_.n_1700_B((c_1514_x)blockpos$mutable, a_3742_W.P_4830_p.multiplayerClientSuggestionProvider(), 2);
                    continue;
                }
                p_241855_1_.n_1700_B((c_1514_x)blockpos$mutable, a_3742_W.J_1907_R.multiplayerClientSuggestionProvider(), 2);
            }
        }
        return true;
    }
}


