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
import lightning.product.BoundingBox;
import lightning.product.TreeDecorator;
import lightning.product.Q_4220_D;
import lightning.product.WorldGenLevel;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.LevelWriter;

public class TrunkVineDecorator
extends TreeDecorator {
    public static final Codec<TrunkVineDecorator> n_1700_B;
    public static final TrunkVineDecorator J_1907_R;

    @Override
    protected TreeDecoratorType<?> n_1700_B() {
        return TreeDecoratorType.n_1700_B;
    }

    @Override
    public void n_1700_B(WorldGenLevel p_225576_1_, Random p_225576_2_, List<c_1514_x> p_225576_3_, List<c_1514_x> p_225576_4_, Set<c_1514_x> p_225576_5_, BoundingBox p_225576_6_) {
        p_225576_3_.forEach(p_236880_5_ -> {
            c_1514_x blockpos3;
            c_1514_x blockpos2;
            c_1514_x blockpos1;
            c_1514_x blockpos;
            if (p_225576_2_.nextInt(3) > 0 && Feature.J_1907_R(p_225576_1_, blockpos = p_236880_5_.west())) {
                this.n_1700_B((LevelWriter)p_225576_1_, blockpos, Q_4220_D.Q_4569_t, p_225576_5_, p_225576_6_);
            }
            if (p_225576_2_.nextInt(3) > 0 && Feature.J_1907_R(p_225576_1_, blockpos1 = p_236880_5_.east())) {
                this.n_1700_B((LevelWriter)p_225576_1_, blockpos1, Q_4220_D.t_1786_h, p_225576_5_, p_225576_6_);
            }
            if (p_225576_2_.nextInt(3) > 0 && Feature.J_1907_R(p_225576_1_, blockpos2 = p_236880_5_.north())) {
                this.n_1700_B((LevelWriter)p_225576_1_, blockpos2, Q_4220_D.M_182_A, p_225576_5_, p_225576_6_);
            }
            if (p_225576_2_.nextInt(3) > 0 && Feature.J_1907_R(p_225576_1_, blockpos3 = p_236880_5_.south())) {
                this.n_1700_B((LevelWriter)p_225576_1_, blockpos3, Q_4220_D.h_1847_R, p_225576_5_, p_225576_6_);
            }
        });
    }

    static {
        J_1907_R = new TrunkVineDecorator();
        n_1700_B = Codec.unit(() -> J_1907_R);
    }
}


