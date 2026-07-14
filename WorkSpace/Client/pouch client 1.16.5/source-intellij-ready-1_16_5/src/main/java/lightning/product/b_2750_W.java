/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import lightning.product.TrunkPlacerType;
import lightning.product.TreeFeature;
import lightning.product.BoundingBox;
import lightning.product.LevelSimulatedRW;
import lightning.product.TreeConfiguration;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.TrunkPlacer;
import lightning.product.FoliagePlacer;

public class b_2750_W
extends TrunkPlacer {
    public static final Codec<b_2750_W> P_1922_E = RecordCodecBuilder.create(p_236883_0_ -> b_2750_W.n_1700_B(p_236883_0_).apply((Applicative)p_236883_0_, b_2750_W::new));

    public b_2750_W(int p_i232053_1_, int p_i232053_2_, int p_i232053_3_) {
        super(p_i232053_1_, p_i232053_2_, p_i232053_3_);
    }

    @Override
    protected TrunkPlacerType<?> n_1700_B() {
        return TrunkPlacerType.P_1922_E;
    }

    @Override
    public List<FoliagePlacer.n_1700_B> n_1700_B(LevelSimulatedRW p_230382_1_, Random p_230382_2_, int p_230382_3_, c_1514_x p_230382_4_, Set<c_1514_x> p_230382_5_, BoundingBox p_230382_6_, TreeConfiguration p_230382_7_) {
        ArrayList list = Lists.newArrayList();
        c_1514_x blockpos = p_230382_4_.down();
        b_2750_W.n_1700_B(p_230382_1_, blockpos);
        b_2750_W.n_1700_B(p_230382_1_, blockpos.east());
        b_2750_W.n_1700_B(p_230382_1_, blockpos.south());
        b_2750_W.n_1700_B(p_230382_1_, blockpos.south().east());
        b_257_Y direction = b_257_Y.R_4764_Y.n_1700_B.n_1700_B(p_230382_2_);
        int i = p_230382_3_ - p_230382_2_.nextInt(4);
        int j = 2 - p_230382_2_.nextInt(3);
        int k = p_230382_4_.getX();
        int l = p_230382_4_.getY();
        int i1 = p_230382_4_.getZ();
        int j1 = k;
        int k1 = i1;
        int l1 = l + p_230382_3_ - 1;
        for (int i2 = 0; i2 < p_230382_3_; ++i2) {
            int j2;
            c_1514_x blockpos1;
            if (i2 >= i && j > 0) {
                j1 += direction.t_148_a();
                k1 += direction.u_2550_I();
                --j;
            }
            if (!TreeFeature.G_564_y(p_230382_1_, blockpos1 = new c_1514_x(j1, j2 = l + i2, k1))) continue;
            b_2750_W.n_1700_B(p_230382_1_, p_230382_2_, blockpos1, p_230382_5_, p_230382_6_, p_230382_7_);
            b_2750_W.n_1700_B(p_230382_1_, p_230382_2_, blockpos1.east(), p_230382_5_, p_230382_6_, p_230382_7_);
            b_2750_W.n_1700_B(p_230382_1_, p_230382_2_, blockpos1.south(), p_230382_5_, p_230382_6_, p_230382_7_);
            b_2750_W.n_1700_B(p_230382_1_, p_230382_2_, blockpos1.east().south(), p_230382_5_, p_230382_6_, p_230382_7_);
        }
        list.add(new FoliagePlacer.n_1700_B(new c_1514_x(j1, l1, k1), 0, true));
        for (int l2 = -1; l2 <= 2; ++l2) {
            for (int i3 = -1; i3 <= 2; ++i3) {
                if (l2 >= 0 && l2 <= 1 && i3 >= 0 && i3 <= 1 || p_230382_2_.nextInt(3) > 0) continue;
                int j3 = p_230382_2_.nextInt(3) + 2;
                for (int k2 = 0; k2 < j3; ++k2) {
                    b_2750_W.n_1700_B(p_230382_1_, p_230382_2_, new c_1514_x(k + l2, l1 - k2 - 1, i1 + i3), p_230382_5_, p_230382_6_, p_230382_7_);
                }
                list.add(new FoliagePlacer.n_1700_B(new c_1514_x(j1 + l2, l1, k1 + i3), 0, false));
            }
        }
        return list;
    }
}


