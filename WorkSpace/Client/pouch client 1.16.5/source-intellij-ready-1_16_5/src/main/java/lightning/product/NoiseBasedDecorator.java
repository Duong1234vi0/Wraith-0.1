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
import lightning.product.NoiseCountFactorDecoratorConfiguration;
import lightning.product.SimpleFeatureDecorator;
import lightning.product.c_1514_x;
import lightning.product.k_594_Q;

public class NoiseBasedDecorator
extends SimpleFeatureDecorator<NoiseCountFactorDecoratorConfiguration> {
    public NoiseBasedDecorator(Codec<NoiseCountFactorDecoratorConfiguration> p_i242028_1_) {
        super(p_i242028_1_);
    }

    @Override
    public Stream<c_1514_x> n_1700_B(Random random, NoiseCountFactorDecoratorConfiguration p_212852_2_, c_1514_x pos) {
        double d0 = k_594_Q.u_1723_Y.n_1700_B((double)pos.getX() / p_212852_2_.R_4764_Y, (double)pos.getZ() / p_212852_2_.R_4764_Y, false);
        int i = (int)Math.ceil((d0 + p_212852_2_.G_564_y) * (double)p_212852_2_.J_1907_R);
        return IntStream.range(0, i).mapToObj(p_242913_1_ -> pos);
    }
}


