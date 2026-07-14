/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.Q_4220_D;
import lightning.product.WorldGenLevel;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.o_2105_O;
import lightning.product.z_1753_f;

public class VinesFeature
extends Feature<o_2105_O> {
    private static final b_257_Y[] n_1700_B = b_257_Y.values();

    public VinesFeature(Codec<o_2105_O> p_i232002_1_) {
        super(p_i232002_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, o_2105_O p_241855_5_) {
        c_1514_x.n_1700_B blockpos$mutable = p_241855_4_.toMutable();
        block0: for (int i = 64; i < 256; ++i) {
            blockpos$mutable.n_1700_B(p_241855_4_);
            blockpos$mutable.J_1907_R(p_241855_3_.nextInt(4) - p_241855_3_.nextInt(4), 0, p_241855_3_.nextInt(4) - p_241855_3_.nextInt(4));
            blockpos$mutable.setY(i);
            if (!p_241855_1_.u_1723_Y(blockpos$mutable)) continue;
            for (b_257_Y direction : n_1700_B) {
                if (direction == b_257_Y.n_1700_B || !Q_4220_D.n_1700_B((BlockGetter)p_241855_1_, (c_1514_x)blockpos$mutable, direction)) continue;
                p_241855_1_.n_1700_B((c_1514_x)blockpos$mutable, (K_4074_S)a_3742_W.U_4087_m.multiplayerClientSuggestionProvider().n_1700_B(Q_4220_D.n_1700_B(direction), true), 2);
                continue block0;
            }
        }
        return true;
    }
}


