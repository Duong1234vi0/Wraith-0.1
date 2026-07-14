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
import lightning.product.RangeDecoratorConfiguration;

public class y_4691_Y
extends SimpleFeatureDecorator<RangeDecoratorConfiguration> {
    public y_4691_Y(Codec<RangeDecoratorConfiguration> p_i242014_1_) {
        super(p_i242014_1_);
    }

    @Override
    public Stream<c_1514_x> n_1700_B(Random random, RangeDecoratorConfiguration p_212852_2_, c_1514_x pos) {
        int i = pos.getX();
        int j = pos.getZ();
        int k = random.nextInt(random.nextInt(p_212852_2_.G_564_y - p_212852_2_.R_4764_Y) + p_212852_2_.J_1907_R);
        return Stream.of(new c_1514_x(i, k, j));
    }
}


