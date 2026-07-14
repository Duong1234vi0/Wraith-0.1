/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Random;
import java.util.Set;
import lightning.product.BoundingBox;
import lightning.product.LevelSimulatedRW;
import lightning.product.TreeConfiguration;
import lightning.product.FoliagePlacerType;
import lightning.product.c_1514_x;
import lightning.product.g_1198_o;
import lightning.product.FoliagePlacer;

public class DarkOakFoliagePlacer
extends FoliagePlacer {
    public static final Codec<DarkOakFoliagePlacer> n_1700_B = RecordCodecBuilder.create(p_236746_0_ -> DarkOakFoliagePlacer.J_1907_R(p_236746_0_).apply((Applicative)p_236746_0_, DarkOakFoliagePlacer::new));

    public DarkOakFoliagePlacer(g_1198_o p_i241997_1_, g_1198_o p_i241997_2_) {
        super(p_i241997_1_, p_i241997_2_);
    }

    @Override
    protected FoliagePlacerType<?> n_1700_B() {
        return FoliagePlacerType.t_148_a;
    }

    @Override
    protected void n_1700_B(LevelSimulatedRW p_230372_1_, Random p_230372_2_, TreeConfiguration p_230372_3_, int p_230372_4_, FoliagePlacer.n_1700_B p_230372_5_, int p_230372_6_, int p_230372_7_, Set<c_1514_x> p_230372_8_, int p_230372_9_, BoundingBox p_230372_10_) {
        c_1514_x blockpos = p_230372_5_.n_1700_B().up(p_230372_9_);
        boolean flag = p_230372_5_.R_4764_Y();
        if (flag) {
            this.n_1700_B(p_230372_1_, p_230372_2_, p_230372_3_, blockpos, p_230372_7_ + 2, p_230372_8_, -1, flag, p_230372_10_);
            this.n_1700_B(p_230372_1_, p_230372_2_, p_230372_3_, blockpos, p_230372_7_ + 3, p_230372_8_, 0, flag, p_230372_10_);
            this.n_1700_B(p_230372_1_, p_230372_2_, p_230372_3_, blockpos, p_230372_7_ + 2, p_230372_8_, 1, flag, p_230372_10_);
            if (p_230372_2_.nextBoolean()) {
                this.n_1700_B(p_230372_1_, p_230372_2_, p_230372_3_, blockpos, p_230372_7_, p_230372_8_, 2, flag, p_230372_10_);
            }
        } else {
            this.n_1700_B(p_230372_1_, p_230372_2_, p_230372_3_, blockpos, p_230372_7_ + 2, p_230372_8_, -1, flag, p_230372_10_);
            this.n_1700_B(p_230372_1_, p_230372_2_, p_230372_3_, blockpos, p_230372_7_ + 1, p_230372_8_, 0, flag, p_230372_10_);
        }
    }

    @Override
    public int n_1700_B(Random p_230374_1_, int p_230374_2_, TreeConfiguration p_230374_3_) {
        return 4;
    }

    @Override
    protected boolean J_1907_R(Random p_230375_1_, int p_230375_2_, int p_230375_3_, int p_230375_4_, int p_230375_5_, boolean p_230375_6_) {
        return p_230375_3_ != 0 || !p_230375_6_ || p_230375_2_ != -p_230375_5_ && p_230375_2_ < p_230375_5_ || p_230375_4_ != -p_230375_5_ && p_230375_4_ < p_230375_5_ ? super.J_1907_R(p_230375_1_, p_230375_2_, p_230375_3_, p_230375_4_, p_230375_5_, p_230375_6_) : true;
    }

    @Override
    protected boolean n_1700_B(Random p_230373_1_, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {
        if (p_230373_3_ == -1 && !p_230373_6_) {
            return p_230373_2_ == p_230373_5_ && p_230373_4_ == p_230373_5_;
        }
        if (p_230373_3_ == 1) {
            return p_230373_2_ + p_230373_4_ > p_230373_5_ * 2 - 2;
        }
        return false;
    }
}


