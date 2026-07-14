/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.List;
import java.util.Random;
import java.util.Set;
import lightning.product.TreeDecoratorType;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.TreeDecorator;
import lightning.product.WorldGenLevel;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.CocoaBlock;
import lightning.product.LevelWriter;

public class N_2125_b
extends TreeDecorator {
    public static final Codec<N_2125_b> n_1700_B = Codec.floatRange((float)0.0f, (float)1.0f).fieldOf("probability").xmap(N_2125_b::new, p_236868_0_ -> Float.valueOf(p_236868_0_.J_1907_R)).codec();
    private final float J_1907_R;

    public N_2125_b(float p_i225868_1_) {
        this.J_1907_R = p_i225868_1_;
    }

    @Override
    protected TreeDecoratorType<?> n_1700_B() {
        return TreeDecoratorType.R_4764_Y;
    }

    @Override
    public void n_1700_B(WorldGenLevel p_225576_1_, Random p_225576_2_, List<c_1514_x> p_225576_3_, List<c_1514_x> p_225576_4_, Set<c_1514_x> p_225576_5_, BoundingBox p_225576_6_) {
        if (!(p_225576_2_.nextFloat() >= this.J_1907_R)) {
            int i = p_225576_3_.get(0).getY();
            p_225576_3_.stream().filter(p_236867_1_ -> p_236867_1_.getY() - i <= 2).forEach(p_242865_5_ -> {
                for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
                    b_257_Y direction1;
                    c_1514_x blockpos;
                    if (!(p_225576_2_.nextFloat() <= 0.25f) || !Feature.J_1907_R(p_225576_1_, blockpos = p_242865_5_.add((direction1 = direction.u_1723_Y()).t_148_a(), 0, direction1.u_2550_I()))) continue;
                    K_4074_S blockstate = (K_4074_S)((K_4074_S)a_3742_W.U_3823_u.multiplayerClientSuggestionProvider().n_1700_B(CocoaBlock.P_4830_p, p_225576_2_.nextInt(3))).n_1700_B(CocoaBlock.w_612_n, direction);
                    this.n_1700_B((LevelWriter)p_225576_1_, blockpos, blockstate, p_225576_5_, p_225576_6_);
                }
            });
        }
    }
}


