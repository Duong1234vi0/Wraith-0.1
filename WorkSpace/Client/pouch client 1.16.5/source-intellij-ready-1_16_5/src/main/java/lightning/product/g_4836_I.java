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
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.o_2105_O;
import lightning.product.LevelAccessor;
import lightning.product.u_530_F;
import lightning.product.z_1753_f;

public class g_4836_I
extends Feature<o_2105_O> {
    public g_4836_I(Codec<o_2105_O> p_i231926_1_) {
        super(p_i231926_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, o_2105_O p_241855_5_) {
        if (p_241855_1_.u_1723_Y(p_241855_4_) && !p_241855_1_.u_1723_Y(p_241855_4_.up())) {
            c_1514_x.n_1700_B blockpos$mutable = p_241855_4_.toMutable();
            c_1514_x.n_1700_B blockpos$mutable1 = p_241855_4_.toMutable();
            boolean flag = true;
            boolean flag1 = true;
            boolean flag2 = true;
            boolean flag3 = true;
            while (p_241855_1_.u_1723_Y(blockpos$mutable)) {
                if (b_4507_u.Q_4569_t(blockpos$mutable)) {
                    return true;
                }
                p_241855_1_.n_1700_B((c_1514_x)blockpos$mutable, a_3742_W.s_4990_V.multiplayerClientSuggestionProvider(), 2);
                flag = flag && this.J_1907_R(p_241855_1_, p_241855_3_, blockpos$mutable1.n_1700_B(blockpos$mutable, b_257_Y.R_4764_Y));
                flag1 = flag1 && this.J_1907_R(p_241855_1_, p_241855_3_, blockpos$mutable1.n_1700_B(blockpos$mutable, b_257_Y.G_564_y));
                flag2 = flag2 && this.J_1907_R(p_241855_1_, p_241855_3_, blockpos$mutable1.n_1700_B(blockpos$mutable, b_257_Y.P_1922_E));
                flag3 = flag3 && this.J_1907_R(p_241855_1_, p_241855_3_, blockpos$mutable1.n_1700_B(blockpos$mutable, b_257_Y.u_1723_Y));
                blockpos$mutable.n_1700_B(b_257_Y.n_1700_B);
            }
            blockpos$mutable.n_1700_B(b_257_Y.J_1907_R);
            this.n_1700_B(p_241855_1_, p_241855_3_, blockpos$mutable1.n_1700_B(blockpos$mutable, b_257_Y.R_4764_Y));
            this.n_1700_B(p_241855_1_, p_241855_3_, blockpos$mutable1.n_1700_B(blockpos$mutable, b_257_Y.G_564_y));
            this.n_1700_B(p_241855_1_, p_241855_3_, blockpos$mutable1.n_1700_B(blockpos$mutable, b_257_Y.P_1922_E));
            this.n_1700_B(p_241855_1_, p_241855_3_, blockpos$mutable1.n_1700_B(blockpos$mutable, b_257_Y.u_1723_Y));
            blockpos$mutable.n_1700_B(b_257_Y.n_1700_B);
            c_1514_x.n_1700_B blockpos$mutable2 = new c_1514_x.n_1700_B();
            for (int i = -3; i < 4; ++i) {
                for (int j = -3; j < 4; ++j) {
                    int k = u_530_F.n_1700_B(i) * u_530_F.n_1700_B(j);
                    if (p_241855_3_.nextInt(10) >= 10 - k) continue;
                    blockpos$mutable2.n_1700_B(blockpos$mutable.add(i, 0, j));
                    int l = 3;
                    while (p_241855_1_.u_1723_Y(blockpos$mutable1.n_1700_B(blockpos$mutable2, b_257_Y.n_1700_B))) {
                        blockpos$mutable2.n_1700_B(b_257_Y.n_1700_B);
                        if (--l > 0) continue;
                    }
                    if (p_241855_1_.u_1723_Y(blockpos$mutable1.n_1700_B(blockpos$mutable2, b_257_Y.n_1700_B))) continue;
                    p_241855_1_.n_1700_B((c_1514_x)blockpos$mutable2, a_3742_W.s_4990_V.multiplayerClientSuggestionProvider(), 2);
                }
            }
            return true;
        }
        return false;
    }

    private void n_1700_B(LevelAccessor p_236252_1_, Random p_236252_2_, c_1514_x p_236252_3_) {
        if (p_236252_2_.nextBoolean()) {
            p_236252_1_.n_1700_B(p_236252_3_, a_3742_W.s_4990_V.multiplayerClientSuggestionProvider(), 2);
        }
    }

    private boolean J_1907_R(LevelAccessor p_236253_1_, Random p_236253_2_, c_1514_x p_236253_3_) {
        if (p_236253_2_.nextInt(10) != 0) {
            p_236253_1_.n_1700_B(p_236253_3_, a_3742_W.s_4990_V.multiplayerClientSuggestionProvider(), 2);
            return true;
        }
        return false;
    }
}


