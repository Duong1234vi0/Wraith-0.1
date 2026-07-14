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
import lightning.product.LevelSimulatedRW;
import lightning.product.WorldGenLevel;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.BlockStateProvider;

public class AlterGroundDecorator
extends TreeDecorator {
    public static final Codec<AlterGroundDecorator> n_1700_B = BlockStateProvider.J_1907_R.fieldOf("provider").xmap(AlterGroundDecorator::new, p_236862_0_ -> p_236862_0_.J_1907_R).codec();
    private final BlockStateProvider J_1907_R;

    public AlterGroundDecorator(BlockStateProvider p_i225864_1_) {
        this.J_1907_R = p_i225864_1_;
    }

    @Override
    protected TreeDecoratorType<?> n_1700_B() {
        return TreeDecoratorType.P_1922_E;
    }

    @Override
    public void n_1700_B(WorldGenLevel p_225576_1_, Random p_225576_2_, List<c_1514_x> p_225576_3_, List<c_1514_x> p_225576_4_, Set<c_1514_x> p_225576_5_, BoundingBox p_225576_6_) {
        int i = p_225576_3_.get(0).getY();
        p_225576_3_.stream().filter(p_236860_1_ -> p_236860_1_.getY() == i).forEach(p_236861_3_ -> {
            this.n_1700_B((LevelSimulatedRW)p_225576_1_, p_225576_2_, p_236861_3_.west().north());
            this.n_1700_B((LevelSimulatedRW)p_225576_1_, p_225576_2_, p_236861_3_.east(2).north());
            this.n_1700_B((LevelSimulatedRW)p_225576_1_, p_225576_2_, p_236861_3_.west().south(2));
            this.n_1700_B((LevelSimulatedRW)p_225576_1_, p_225576_2_, p_236861_3_.east(2).south(2));
            for (int j = 0; j < 5; ++j) {
                int k = p_225576_2_.nextInt(64);
                int l = k % 8;
                int i1 = k / 8;
                if (l != 0 && l != 7 && i1 != 0 && i1 != 7) continue;
                this.n_1700_B((LevelSimulatedRW)p_225576_1_, p_225576_2_, p_236861_3_.add(-3 + l, 0, -3 + i1));
            }
        });
    }

    private void n_1700_B(LevelSimulatedRW p_227413_1_, Random p_227413_2_, c_1514_x p_227413_3_) {
        for (int i = -2; i <= 2; ++i) {
            for (int j = -2; j <= 2; ++j) {
                if (Math.abs(i) == 2 && Math.abs(j) == 2) continue;
                this.J_1907_R(p_227413_1_, p_227413_2_, p_227413_3_.add(i, 0, j));
            }
        }
    }

    private void J_1907_R(LevelSimulatedRW p_227414_1_, Random p_227414_2_, c_1514_x p_227414_3_) {
        for (int i = 2; i >= -3; --i) {
            c_1514_x blockpos = p_227414_3_.up(i);
            if (Feature.n_1700_B(p_227414_1_, blockpos)) {
                p_227414_1_.n_1700_B(blockpos, this.J_1907_R.n_1700_B(p_227414_2_, p_227414_3_), 19);
                break;
            }
            if (!Feature.J_1907_R(p_227414_1_, blockpos) && i < 0) break;
        }
    }
}


