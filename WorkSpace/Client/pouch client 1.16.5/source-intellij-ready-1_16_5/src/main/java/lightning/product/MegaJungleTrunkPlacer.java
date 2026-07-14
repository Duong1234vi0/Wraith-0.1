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
import lightning.product.BoundingBox;
import lightning.product.GiantTrunkPlacer;
import lightning.product.LevelSimulatedRW;
import lightning.product.TreeConfiguration;
import lightning.product.c_1514_x;
import lightning.product.FoliagePlacer;
import lightning.product.u_530_F;

public class MegaJungleTrunkPlacer
extends GiantTrunkPlacer {
    public static final Codec<MegaJungleTrunkPlacer> u_1723_Y = RecordCodecBuilder.create(p_236902_0_ -> MegaJungleTrunkPlacer.n_1700_B(p_236902_0_).apply((Applicative)p_236902_0_, MegaJungleTrunkPlacer::new));

    public MegaJungleTrunkPlacer(int p_i232058_1_, int p_i232058_2_, int p_i232058_3_) {
        super(p_i232058_1_, p_i232058_2_, p_i232058_3_);
    }

    @Override
    protected TrunkPlacerType<?> n_1700_B() {
        return TrunkPlacerType.G_564_y;
    }

    @Override
    public List<FoliagePlacer.n_1700_B> n_1700_B(LevelSimulatedRW p_230382_1_, Random p_230382_2_, int p_230382_3_, c_1514_x p_230382_4_, Set<c_1514_x> p_230382_5_, BoundingBox p_230382_6_, TreeConfiguration p_230382_7_) {
        ArrayList list = Lists.newArrayList();
        list.addAll(super.n_1700_B(p_230382_1_, p_230382_2_, p_230382_3_, p_230382_4_, p_230382_5_, p_230382_6_, p_230382_7_));
        for (int i = p_230382_3_ - 2 - p_230382_2_.nextInt(4); i > p_230382_3_ / 2; i -= 2 + p_230382_2_.nextInt(4)) {
            float f = p_230382_2_.nextFloat() * ((float)Math.PI * 2);
            int j = 0;
            int k = 0;
            for (int l = 0; l < 5; ++l) {
                j = (int)(1.5f + u_530_F.J_1907_R(f) * (float)l);
                k = (int)(1.5f + u_530_F.n_1700_B(f) * (float)l);
                c_1514_x blockpos = p_230382_4_.add(j, i - 3 + l / 2, k);
                MegaJungleTrunkPlacer.n_1700_B(p_230382_1_, p_230382_2_, blockpos, p_230382_5_, p_230382_6_, p_230382_7_);
            }
            list.add(new FoliagePlacer.n_1700_B(p_230382_4_.add(j, i, k), -2, false));
        }
        return list;
    }
}


