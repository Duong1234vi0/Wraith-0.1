/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lightning.product.SimpleFeatureDecorator;
import lightning.product.c_1514_x;
import lightning.product.CountConfiguration;

public class GlowstoneDecorator
extends SimpleFeatureDecorator<CountConfiguration> {
    public GlowstoneDecorator(Codec<CountConfiguration> p_i242035_1_) {
        super(p_i242035_1_);
    }

    @Override
    public Stream<c_1514_x> n_1700_B(Random random, CountConfiguration p_212852_2_, c_1514_x pos) {
        return IntStream.range(0, random.nextInt(random.nextInt(p_212852_2_.J_1907_R().n_1700_B(random)) + 1)).mapToObj(p_242916_2_ -> {
            int i = random.nextInt(16) + pos.getX();
            int j = random.nextInt(16) + pos.getZ();
            int k = random.nextInt(120) + 4;
            return new c_1514_x(i, k, j);
        });
    }
}


