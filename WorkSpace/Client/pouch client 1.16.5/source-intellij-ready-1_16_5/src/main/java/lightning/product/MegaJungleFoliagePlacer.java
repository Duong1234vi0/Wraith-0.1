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

public class MegaJungleFoliagePlacer
extends FoliagePlacer {
    public static final Codec<MegaJungleFoliagePlacer> n_1700_B = RecordCodecBuilder.create(p_236776_0_ -> MegaJungleFoliagePlacer.J_1907_R(p_236776_0_).and((App)Codec.intRange((int)0, (int)16).fieldOf("height").forGetter(p_236777_0_ -> p_236777_0_.J_1907_R)).apply((Applicative)p_236776_0_, MegaJungleFoliagePlacer::new));
    protected final int J_1907_R;

    public MegaJungleFoliagePlacer(g_1198_o p_i242000_1_, g_1198_o p_i242000_2_, int p_i242000_3_) {
        super(p_i242000_1_, p_i242000_2_);
        this.J_1907_R = p_i242000_3_;
    }

    @Override
    protected FoliagePlacerType<?> n_1700_B() {
        return FoliagePlacerType.v_4262_N;
    }

    @Override
    protected void n_1700_B(LevelSimulatedRW p_230372_1_, Random p_230372_2_, TreeConfiguration p_230372_3_, int p_230372_4_, FoliagePlacer.n_1700_B p_230372_5_, int p_230372_6_, int p_230372_7_, Set<c_1514_x> p_230372_8_, int p_230372_9_, BoundingBox p_230372_10_) {
        int i = p_230372_5_.R_4764_Y() ? p_230372_6_ : 1 + p_230372_2_.nextInt(2);
        for (int j = p_230372_9_; j >= p_230372_9_ - i; --j) {
            int k = p_230372_7_ + p_230372_5_.J_1907_R() + 1 - j;
            this.n_1700_B(p_230372_1_, p_230372_2_, p_230372_3_, p_230372_5_.n_1700_B(), k, p_230372_8_, j, p_230372_5_.R_4764_Y(), p_230372_10_);
        }
    }

    @Override
    public int n_1700_B(Random p_230374_1_, int p_230374_2_, TreeConfiguration p_230374_3_) {
        return this.J_1907_R;
    }

    @Override
    protected boolean n_1700_B(Random p_230373_1_, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {
        if (p_230373_2_ + p_230373_4_ >= 7) {
            return true;
        }
        return p_230373_2_ * p_230373_2_ + p_230373_4_ * p_230373_4_ > p_230373_5_ * p_230373_5_;
    }
}


