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
import lightning.product.FrequencyWithExtraChanceDecoratorConfiguration;

public class CountWithExtraChanceDecorator
extends SimpleFeatureDecorator<FrequencyWithExtraChanceDecoratorConfiguration> {
    public CountWithExtraChanceDecorator(Codec<FrequencyWithExtraChanceDecoratorConfiguration> p_i242018_1_) {
        super(p_i242018_1_);
    }

    @Override
    public Stream<c_1514_x> n_1700_B(Random random, FrequencyWithExtraChanceDecoratorConfiguration p_212852_2_, c_1514_x pos) {
        int i = p_212852_2_.J_1907_R + (random.nextFloat() < p_212852_2_.R_4764_Y ? p_212852_2_.G_564_y : 0);
        return IntStream.range(0, i).mapToObj(p_242880_1_ -> pos);
    }
}


