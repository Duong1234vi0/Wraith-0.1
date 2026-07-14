/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import java.util.stream.Stream;
import lightning.product.SimpleFeatureDecorator;
import lightning.product.c_1514_x;
import lightning.product.ChanceDecoratorConfiguration;

public class ChanceDecorator
extends SimpleFeatureDecorator<ChanceDecoratorConfiguration> {
    public ChanceDecorator(Codec<ChanceDecoratorConfiguration> p_i242015_1_) {
        super(p_i242015_1_);
    }

    @Override
    public Stream<c_1514_x> n_1700_B(Random random, ChanceDecoratorConfiguration p_212852_2_, c_1514_x pos) {
        return random.nextFloat() < 1.0f / (float)p_212852_2_.J_1907_R ? Stream.of(pos) : Stream.empty();
    }
}


