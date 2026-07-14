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

public class AcaciaFoliagePlacer
extends FoliagePlacer {
    public static final Codec<AcaciaFoliagePlacer> n_1700_B = RecordCodecBuilder.create(p_236737_0_ -> AcaciaFoliagePlacer.J_1907_R(p_236737_0_).apply((Applicative)p_236737_0_, AcaciaFoliagePlacer::new));

    public AcaciaFoliagePlacer(g_1198_o p_i241994_1_, g_1198_o p_i241994_2_) {
        super(p_i241994_1_, p_i241994_2_);
    }

    @Override
    protected FoliagePlacerType<?> n_1700_B() {
        return FoliagePlacerType.G_564_y;
    }

    @Override
    protected void n_1700_B(LevelSimulatedRW p_230372_1_, Random p_230372_2_, TreeConfiguration p_230372_3_, int p_230372_4_, FoliagePlacer.n_1700_B p_230372_5_, int p_230372_6_, int p_230372_7_, Set<c_1514_x> p_230372_8_, int p_230372_9_, BoundingBox p_230372_10_) {
        boolean flag = p_230372_5_.R_4764_Y();
        c_1514_x blockpos = p_230372_5_.n_1700_B().up(p_230372_9_);
        this.n_1700_B(p_230372_1_, p_230372_2_, p_230372_3_, blockpos, p_230372_7_ + p_230372_5_.J_1907_R(), p_230372_8_, -1 - p_230372_6_, flag, p_230372_10_);
        this.n_1700_B(p_230372_1_, p_230372_2_, p_230372_3_, blockpos, p_230372_7_ - 1, p_230372_8_, -p_230372_6_, flag, p_230372_10_);
        this.n_1700_B(p_230372_1_, p_230372_2_, p_230372_3_, blockpos, p_230372_7_ + p_230372_5_.J_1907_R() - 1, p_230372_8_, 0, flag, p_230372_10_);
    }

    @Override
    public int n_1700_B(Random p_230374_1_, int p_230374_2_, TreeConfiguration p_230374_3_) {
        return 0;
    }

    @Override
    protected boolean n_1700_B(Random p_230373_1_, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {
        if (p_230373_3_ == 0) {
            return (p_230373_2_ > 1 || p_230373_4_ > 1) && p_230373_2_ != 0 && p_230373_4_ != 0;
        }
        return p_230373_2_ == p_230373_5_ && p_230373_4_ == p_230373_5_ && p_230373_5_ > 0;
    }
}


