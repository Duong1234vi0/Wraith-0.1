/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.Random;
import java.util.Set;
import lightning.product.TrunkPlacerType;
import lightning.product.BoundingBox;
import lightning.product.LevelSimulatedRW;
import lightning.product.TreeConfiguration;
import lightning.product.c_1514_x;
import lightning.product.TrunkPlacer;
import lightning.product.FoliagePlacer;

public class GiantTrunkPlacer
extends TrunkPlacer {
    public static final Codec<GiantTrunkPlacer> P_1922_E = RecordCodecBuilder.create(p_236900_0_ -> GiantTrunkPlacer.n_1700_B(p_236900_0_).apply((Applicative)p_236900_0_, GiantTrunkPlacer::new));

    public GiantTrunkPlacer(int p_i232057_1_, int p_i232057_2_, int p_i232057_3_) {
        super(p_i232057_1_, p_i232057_2_, p_i232057_3_);
    }

    @Override
    protected TrunkPlacerType<?> n_1700_B() {
        return TrunkPlacerType.R_4764_Y;
    }

    @Override
    public List<FoliagePlacer.n_1700_B> n_1700_B(LevelSimulatedRW p_230382_1_, Random p_230382_2_, int p_230382_3_, c_1514_x p_230382_4_, Set<c_1514_x> p_230382_5_, BoundingBox p_230382_6_, TreeConfiguration p_230382_7_) {
        c_1514_x blockpos = p_230382_4_.down();
        GiantTrunkPlacer.n_1700_B(p_230382_1_, blockpos);
        GiantTrunkPlacer.n_1700_B(p_230382_1_, blockpos.east());
        GiantTrunkPlacer.n_1700_B(p_230382_1_, blockpos.south());
        GiantTrunkPlacer.n_1700_B(p_230382_1_, blockpos.south().east());
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int i = 0; i < p_230382_3_; ++i) {
            GiantTrunkPlacer.n_1700_B(p_230382_1_, p_230382_2_, blockpos$mutable, p_230382_5_, p_230382_6_, p_230382_7_, p_230382_4_, 0, i, 0);
            if (i >= p_230382_3_ - 1) continue;
            GiantTrunkPlacer.n_1700_B(p_230382_1_, p_230382_2_, blockpos$mutable, p_230382_5_, p_230382_6_, p_230382_7_, p_230382_4_, 1, i, 0);
            GiantTrunkPlacer.n_1700_B(p_230382_1_, p_230382_2_, blockpos$mutable, p_230382_5_, p_230382_6_, p_230382_7_, p_230382_4_, 1, i, 1);
            GiantTrunkPlacer.n_1700_B(p_230382_1_, p_230382_2_, blockpos$mutable, p_230382_5_, p_230382_6_, p_230382_7_, p_230382_4_, 0, i, 1);
        }
        return ImmutableList.of((Object)new FoliagePlacer.n_1700_B(p_230382_4_.up(p_230382_3_), 0, true));
    }

    private static void n_1700_B(LevelSimulatedRW p_236899_0_, Random p_236899_1_, c_1514_x.n_1700_B p_236899_2_, Set<c_1514_x> p_236899_3_, BoundingBox p_236899_4_, TreeConfiguration p_236899_5_, c_1514_x p_236899_6_, int p_236899_7_, int p_236899_8_, int p_236899_9_) {
        p_236899_2_.n_1700_B(p_236899_6_, p_236899_7_, p_236899_8_, p_236899_9_);
        GiantTrunkPlacer.n_1700_B(p_236899_0_, p_236899_1_, p_236899_2_, p_236899_3_, p_236899_4_, p_236899_5_);
    }
}


