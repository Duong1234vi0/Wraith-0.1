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

public class CountDecorator
extends SimpleFeatureDecorator<CountConfiguration> {
    public CountDecorator(Codec<CountConfiguration> p_i242016_1_) {
        super(p_i242016_1_);
    }

    @Override
    public Stream<c_1514_x> n_1700_B(Random random, CountConfiguration p_212852_2_, c_1514_x pos) {
        return IntStream.range(0, p_212852_2_.J_1907_R().n_1700_B(random)).mapToObj(p_242878_1_ -> pos);
    }
}


