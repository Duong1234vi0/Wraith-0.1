/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.K_4074_S;
import lightning.product.WorldGenLevel;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.GrowingPlantHeadBlock;
import lightning.product.Feature;
import lightning.product.o_2105_O;
import lightning.product.LevelAccessor;
import lightning.product.u_530_F;
import lightning.product.z_1753_f;

public class G_3858_B
extends Feature<o_2105_O> {
    private static final b_257_Y[] n_1700_B = b_257_Y.values();

    public G_3858_B(Codec<o_2105_O> p_i232004_1_) {
        super(p_i232004_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, o_2105_O p_241855_5_) {
        if (!p_241855_1_.u_1723_Y(p_241855_4_)) {
            return false;
        }
        K_4074_S blockstate = p_241855_1_.getBlockState(p_241855_4_.up());
        if (!blockstate.n_1700_B(a_3742_W.i_3196_G) && !blockstate.n_1700_B(a_3742_W.LockSlot)) {
            return false;
        }
        this.n_1700_B(p_241855_1_, p_241855_3_, p_241855_4_);
        this.J_1907_R(p_241855_1_, p_241855_3_, p_241855_4_);
        return true;
    }

    private void n_1700_B(LevelAccessor p_236428_1_, Random p_236428_2_, c_1514_x p_236428_3_) {
        p_236428_1_.n_1700_B(p_236428_3_, a_3742_W.LockSlot.multiplayerClientSuggestionProvider(), 2);
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        c_1514_x.n_1700_B blockpos$mutable1 = new c_1514_x.n_1700_B();
        for (int i = 0; i < 200; ++i) {
            blockpos$mutable.n_1700_B(p_236428_3_, p_236428_2_.nextInt(6) - p_236428_2_.nextInt(6), p_236428_2_.nextInt(2) - p_236428_2_.nextInt(5), p_236428_2_.nextInt(6) - p_236428_2_.nextInt(6));
            if (!p_236428_1_.u_1723_Y(blockpos$mutable)) continue;
            int j = 0;
            for (b_257_Y direction : n_1700_B) {
                K_4074_S blockstate = p_236428_1_.getBlockState(blockpos$mutable1.n_1700_B(blockpos$mutable, direction));
                if (blockstate.n_1700_B(a_3742_W.i_3196_G) || blockstate.n_1700_B(a_3742_W.LockSlot)) {
                    ++j;
                }
                if (j > 1) break;
            }
            if (j != true) continue;
            p_236428_1_.n_1700_B((c_1514_x)blockpos$mutable, a_3742_W.LockSlot.multiplayerClientSuggestionProvider(), 2);
        }
    }

    private void J_1907_R(LevelAccessor p_236429_1_, Random p_236429_2_, c_1514_x p_236429_3_) {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int i = 0; i < 100; ++i) {
            K_4074_S blockstate;
            blockpos$mutable.n_1700_B(p_236429_3_, p_236429_2_.nextInt(8) - p_236429_2_.nextInt(8), p_236429_2_.nextInt(2) - p_236429_2_.nextInt(7), p_236429_2_.nextInt(8) - p_236429_2_.nextInt(8));
            if (!p_236429_1_.u_1723_Y(blockpos$mutable) || !(blockstate = p_236429_1_.getBlockState((c_1514_x)blockpos$mutable.up())).n_1700_B(a_3742_W.i_3196_G) && !blockstate.n_1700_B(a_3742_W.LockSlot)) continue;
            int j = u_530_F.n_1700_B(p_236429_2_, 1, 8);
            if (p_236429_2_.nextInt(6) == 0) {
                j *= 2;
            }
            if (p_236429_2_.nextInt(5) == 0) {
                j = 1;
            }
            int k = 17;
            int l = 25;
            G_3858_B.n_1700_B(p_236429_1_, p_236429_2_, blockpos$mutable, j, 17, 25);
        }
    }

    public static void n_1700_B(LevelAccessor p_236427_0_, Random p_236427_1_, c_1514_x.n_1700_B p_236427_2_, int p_236427_3_, int p_236427_4_, int p_236427_5_) {
        for (int i = 0; i <= p_236427_3_; ++i) {
            if (p_236427_0_.u_1723_Y(p_236427_2_)) {
                if (i == p_236427_3_ || !p_236427_0_.u_1723_Y((c_1514_x)p_236427_2_.down())) {
                    p_236427_0_.n_1700_B((c_1514_x)p_236427_2_, (K_4074_S)a_3742_W.RequirementsStrategy.multiplayerClientSuggestionProvider().n_1700_B(GrowingPlantHeadBlock.M_182_A, u_530_F.n_1700_B(p_236427_1_, p_236427_4_, p_236427_5_)), 2);
                    break;
                }
                p_236427_0_.n_1700_B((c_1514_x)p_236427_2_, a_3742_W.S_4998_h.multiplayerClientSuggestionProvider(), 2);
            }
            p_236427_2_.n_1700_B(b_257_Y.n_1700_B);
        }
    }
}



