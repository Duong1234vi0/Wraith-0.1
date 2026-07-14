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
import lightning.product.LevelSimulatedRW;
import lightning.product.TreeConfiguration;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.TrunkPlacer;
import lightning.product.FoliagePlacer;

public class w_839_Z
extends TrunkPlacer {
    public static final Codec<w_839_Z> P_1922_E = RecordCodecBuilder.create(p_236897_0_ -> w_839_Z.n_1700_B(p_236897_0_).apply((Applicative)p_236897_0_, w_839_Z::new));

    public w_839_Z(int p_i232056_1_, int p_i232056_2_, int p_i232056_3_) {
        super(p_i232056_1_, p_i232056_2_, p_i232056_3_);
    }

    @Override
    protected TrunkPlacerType<?> n_1700_B() {
        return TrunkPlacerType.J_1907_R;
    }

    @Override
    public List<FoliagePlacer.n_1700_B> n_1700_B(LevelSimulatedRW p_230382_1_, Random p_230382_2_, int p_230382_3_, c_1514_x p_230382_4_, Set<c_1514_x> p_230382_5_, BoundingBox p_230382_6_, TreeConfiguration p_230382_7_) {
        w_839_Z.n_1700_B(p_230382_1_, p_230382_4_.down());
        ArrayList list = Lists.newArrayList();
        b_257_Y direction = b_257_Y.R_4764_Y.n_1700_B.n_1700_B(p_230382_2_);
        int i = p_230382_3_ - p_230382_2_.nextInt(4) - 1;
        int j = 3 - p_230382_2_.nextInt(3);
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        int k = p_230382_4_.getX();
        int l = p_230382_4_.getZ();
        int i1 = 0;
        for (int j1 = 0; j1 < p_230382_3_; ++j1) {
            int k1 = p_230382_4_.getY() + j1;
            if (j1 >= i && j > 0) {
                k += direction.t_148_a();
                l += direction.u_2550_I();
                --j;
            }
            if (!w_839_Z.n_1700_B(p_230382_1_, p_230382_2_, (c_1514_x)blockpos$mutable.n_1700_B(k, k1, l), p_230382_5_, p_230382_6_, p_230382_7_)) continue;
            i1 = k1 + 1;
        }
        list.add(new FoliagePlacer.n_1700_B(new c_1514_x(k, i1, l), 1, false));
        k = p_230382_4_.getX();
        l = p_230382_4_.getZ();
        b_257_Y direction1 = b_257_Y.R_4764_Y.n_1700_B.n_1700_B(p_230382_2_);
        if (direction1 != direction) {
            int k2 = i - p_230382_2_.nextInt(2) - 1;
            int l1 = 1 + p_230382_2_.nextInt(3);
            i1 = 0;
            for (int i2 = k2; i2 < p_230382_3_ && l1 > 0; ++i2, --l1) {
                if (i2 < 1) continue;
                int j2 = p_230382_4_.getY() + i2;
                if (!w_839_Z.n_1700_B(p_230382_1_, p_230382_2_, (c_1514_x)blockpos$mutable.n_1700_B(k += direction1.t_148_a(), j2, l += direction1.u_2550_I()), p_230382_5_, p_230382_6_, p_230382_7_)) continue;
                i1 = j2 + 1;
            }
            if (i1 > 1) {
                list.add(new FoliagePlacer.n_1700_B(new c_1514_x(k, i1, l), 0, false));
            }
        }
        return list;
    }
}


