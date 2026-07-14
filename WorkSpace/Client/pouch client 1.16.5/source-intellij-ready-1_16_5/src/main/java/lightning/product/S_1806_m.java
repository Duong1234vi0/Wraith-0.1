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
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.GrowingPlantHeadBlock;
import lightning.product.Feature;
import lightning.product.o_2105_O;
import lightning.product.LevelAccessor;
import lightning.product.u_530_F;
import lightning.product.z_1753_f;

public class S_1806_m
extends Feature<o_2105_O> {
    public S_1806_m(Codec<o_2105_O> p_i232000_1_) {
        super(p_i232000_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, o_2105_O p_241855_5_) {
        return S_1806_m.n_1700_B((LevelAccessor)p_241855_1_, p_241855_3_, p_241855_4_, 8, 4, 8);
    }

    public static boolean n_1700_B(LevelAccessor p_236423_0_, Random p_236423_1_, c_1514_x p_236423_2_, int p_236423_3_, int p_236423_4_, int p_236423_5_) {
        if (S_1806_m.n_1700_B(p_236423_0_, p_236423_2_)) {
            return false;
        }
        S_1806_m.J_1907_R(p_236423_0_, p_236423_1_, p_236423_2_, p_236423_3_, p_236423_4_, p_236423_5_);
        return true;
    }

    private static void J_1907_R(LevelAccessor p_236424_0_, Random p_236424_1_, c_1514_x p_236424_2_, int p_236424_3_, int p_236424_4_, int p_236424_5_) {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int i = 0; i < p_236424_3_ * p_236424_3_; ++i) {
            blockpos$mutable.n_1700_B(p_236424_2_).J_1907_R(u_530_F.n_1700_B(p_236424_1_, -p_236424_3_, p_236424_3_), u_530_F.n_1700_B(p_236424_1_, -p_236424_4_, p_236424_4_), u_530_F.n_1700_B(p_236424_1_, -p_236424_3_, p_236424_3_));
            if (!S_1806_m.n_1700_B(p_236424_0_, blockpos$mutable) || S_1806_m.n_1700_B(p_236424_0_, (c_1514_x)blockpos$mutable)) continue;
            int j = u_530_F.n_1700_B(p_236424_1_, 1, p_236424_5_);
            if (p_236424_1_.nextInt(6) == 0) {
                j *= 2;
            }
            if (p_236424_1_.nextInt(5) == 0) {
                j = 1;
            }
            int k = 17;
            int l = 25;
            S_1806_m.n_1700_B(p_236424_0_, p_236424_1_, blockpos$mutable, j, 17, 25);
        }
    }

    private static boolean n_1700_B(LevelAccessor p_236420_0_, c_1514_x.n_1700_B p_236420_1_) {
        do {
            p_236420_1_.J_1907_R(0, -1, 0);
            if (!b_4507_u.Q_4569_t(p_236420_1_)) continue;
            return false;
        } while (p_236420_0_.getBlockState(p_236420_1_).v_4262_N());
        p_236420_1_.J_1907_R(0, 1, 0);
        return true;
    }

    public static void n_1700_B(LevelAccessor p_236422_0_, Random p_236422_1_, c_1514_x.n_1700_B p_236422_2_, int p_236422_3_, int p_236422_4_, int p_236422_5_) {
        for (int i = 1; i <= p_236422_3_; ++i) {
            if (p_236422_0_.u_1723_Y(p_236422_2_)) {
                if (i == p_236422_3_ || !p_236422_0_.u_1723_Y((c_1514_x)p_236422_2_.up())) {
                    p_236422_0_.n_1700_B((c_1514_x)p_236422_2_, (K_4074_S)a_3742_W.SimpleCriterionTrigger.multiplayerClientSuggestionProvider().n_1700_B(GrowingPlantHeadBlock.M_182_A, u_530_F.n_1700_B(p_236422_1_, p_236422_4_, p_236422_5_)), 2);
                    break;
                }
                p_236422_0_.n_1700_B((c_1514_x)p_236422_2_, a_3742_W.T_2391_T.multiplayerClientSuggestionProvider(), 2);
            }
            p_236422_2_.n_1700_B(b_257_Y.J_1907_R);
        }
    }

    private static boolean n_1700_B(LevelAccessor p_236421_0_, c_1514_x p_236421_1_) {
        if (!p_236421_0_.u_1723_Y(p_236421_1_)) {
            return true;
        }
        K_4074_S blockstate = p_236421_0_.getBlockState(p_236421_1_.down());
        return !blockstate.n_1700_B(a_3742_W.i_3196_G) && !blockstate.n_1700_B(a_3742_W.ServerAdvancementManager) && !blockstate.n_1700_B(a_3742_W.J_1008_m);
    }
}


