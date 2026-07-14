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

public class StraightTrunkPlacer
extends TrunkPlacer {
    public static final Codec<StraightTrunkPlacer> P_1922_E = RecordCodecBuilder.create(p_236904_0_ -> StraightTrunkPlacer.n_1700_B(p_236904_0_).apply((Applicative)p_236904_0_, StraightTrunkPlacer::new));

    public StraightTrunkPlacer(int p_i232059_1_, int p_i232059_2_, int p_i232059_3_) {
        super(p_i232059_1_, p_i232059_2_, p_i232059_3_);
    }

    @Override
    protected TrunkPlacerType<?> n_1700_B() {
        return TrunkPlacerType.n_1700_B;
    }

    @Override
    public List<FoliagePlacer.n_1700_B> n_1700_B(LevelSimulatedRW p_230382_1_, Random p_230382_2_, int p_230382_3_, c_1514_x p_230382_4_, Set<c_1514_x> p_230382_5_, BoundingBox p_230382_6_, TreeConfiguration p_230382_7_) {
        StraightTrunkPlacer.n_1700_B(p_230382_1_, p_230382_4_.down());
        for (int i = 0; i < p_230382_3_; ++i) {
            StraightTrunkPlacer.n_1700_B(p_230382_1_, p_230382_2_, p_230382_4_.up(i), p_230382_5_, p_230382_6_, p_230382_7_);
        }
        return ImmutableList.of((Object)new FoliagePlacer.n_1700_B(p_230382_4_.up(p_230382_3_), 0, false));
    }
}


