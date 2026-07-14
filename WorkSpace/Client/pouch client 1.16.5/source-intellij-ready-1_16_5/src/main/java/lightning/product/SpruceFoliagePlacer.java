/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.mojang.datafixers.kinds.App;
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

public class SpruceFoliagePlacer
extends FoliagePlacer {
    public static final Codec<SpruceFoliagePlacer> n_1700_B = RecordCodecBuilder.create(p_242836_0_ -> SpruceFoliagePlacer.J_1907_R(p_242836_0_).and((App)g_1198_o.n_1700_B(0, 16, 8).fieldOf("trunk_height").forGetter(p_242835_0_ -> p_242835_0_.J_1907_R)).apply((Applicative)p_242836_0_, SpruceFoliagePlacer::new));
    private final g_1198_o J_1907_R;

    public SpruceFoliagePlacer(g_1198_o p_i242003_1_, g_1198_o p_i242003_2_, g_1198_o p_i242003_3_) {
        super(p_i242003_1_, p_i242003_2_);
        this.J_1907_R = p_i242003_3_;
    }

    @Override
    protected FoliagePlacerType<?> n_1700_B() {
        return FoliagePlacerType.J_1907_R;
    }

    @Override
    protected void n_1700_B(LevelSimulatedRW p_230372_1_, Random p_230372_2_, TreeConfiguration p_230372_3_, int p_230372_4_, FoliagePlacer.n_1700_B p_230372_5_, int p_230372_6_, int p_230372_7_, Set<c_1514_x> p_230372_8_, int p_230372_9_, BoundingBox p_230372_10_) {
        c_1514_x blockpos = p_230372_5_.n_1700_B();
        int i = p_230372_2_.nextInt(2);
        int j = 1;
        int k = 0;
        for (int l = p_230372_9_; l >= -p_230372_6_; --l) {
            this.n_1700_B(p_230372_1_, p_230372_2_, p_230372_3_, blockpos, i, p_230372_8_, l, p_230372_5_.R_4764_Y(), p_230372_10_);
            if (i >= j) {
                i = k;
                k = 1;
                j = Math.min(j + 1, p_230372_7_ + p_230372_5_.J_1907_R());
                continue;
            }
            ++i;
        }
    }

    @Override
    public int n_1700_B(Random p_230374_1_, int p_230374_2_, TreeConfiguration p_230374_3_) {
        return Math.max(4, p_230374_2_ - this.J_1907_R.n_1700_B(p_230374_1_));
    }

    @Override
    protected boolean n_1700_B(Random p_230373_1_, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {
        return p_230373_2_ == p_230373_5_ && p_230373_4_ == p_230373_5_ && p_230373_5_ > 0;
    }
}


