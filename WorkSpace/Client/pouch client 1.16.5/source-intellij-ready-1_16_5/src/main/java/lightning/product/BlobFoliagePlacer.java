/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.Products$P3
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  com.mojang.serialization.codecs.RecordCodecBuilder$Instance
 *  com.mojang.serialization.codecs.RecordCodecBuilder$Mu
 */
package lightning.product;

import com.mojang.datafixers.Products;
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

public class BlobFoliagePlacer
extends FoliagePlacer {
    public static final Codec<BlobFoliagePlacer> n_1700_B = RecordCodecBuilder.create(p_236742_0_ -> BlobFoliagePlacer.n_1700_B(p_236742_0_).apply((Applicative)p_236742_0_, BlobFoliagePlacer::new));
    protected final int J_1907_R;

    protected static <P extends BlobFoliagePlacer> Products.P3<RecordCodecBuilder.Mu<P>, g_1198_o, g_1198_o, Integer> n_1700_B(RecordCodecBuilder.Instance<P> p_236740_0_) {
        return BlobFoliagePlacer.J_1907_R(p_236740_0_).and((App)Codec.intRange((int)0, (int)16).fieldOf("height").forGetter(p_236741_0_ -> p_236741_0_.J_1907_R));
    }

    public BlobFoliagePlacer(g_1198_o p_i241995_1_, g_1198_o p_i241995_2_, int p_i241995_3_) {
        super(p_i241995_1_, p_i241995_2_);
        this.J_1907_R = p_i241995_3_;
    }

    @Override
    protected FoliagePlacerType<?> n_1700_B() {
        return FoliagePlacerType.n_1700_B;
    }

    @Override
    protected void n_1700_B(LevelSimulatedRW p_230372_1_, Random p_230372_2_, TreeConfiguration p_230372_3_, int p_230372_4_, FoliagePlacer.n_1700_B p_230372_5_, int p_230372_6_, int p_230372_7_, Set<c_1514_x> p_230372_8_, int p_230372_9_, BoundingBox p_230372_10_) {
        for (int i = p_230372_9_; i >= p_230372_9_ - p_230372_6_; --i) {
            int j = Math.max(p_230372_7_ + p_230372_5_.J_1907_R() - 1 - i / 2, 0);
            this.n_1700_B(p_230372_1_, p_230372_2_, p_230372_3_, p_230372_5_.n_1700_B(), j, p_230372_8_, i, p_230372_5_.R_4764_Y(), p_230372_10_);
        }
    }

    @Override
    public int n_1700_B(Random p_230374_1_, int p_230374_2_, TreeConfiguration p_230374_3_) {
        return this.J_1907_R;
    }

    @Override
    protected boolean n_1700_B(Random p_230373_1_, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {
        return p_230373_2_ == p_230373_5_ && p_230373_4_ == p_230373_5_ && (p_230373_1_.nextInt(2) == 0 || p_230373_3_ == 0);
    }
}


