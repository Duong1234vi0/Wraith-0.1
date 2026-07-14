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
import lightning.product.S_4291_z;
import lightning.product.SimpleFeatureDecorator;
import lightning.product.c_1514_x;

public class EmeraldPlacementDecorator
extends SimpleFeatureDecorator<S_4291_z> {
    public EmeraldPlacementDecorator(Codec<S_4291_z> p_i232083_1_) {
        super(p_i232083_1_);
    }

    @Override
    public Stream<c_1514_x> n_1700_B(Random random, S_4291_z p_212852_2_, c_1514_x pos) {
        int i = 3 + random.nextInt(6);
        return IntStream.range(0, i).mapToObj(p_215060_2_ -> {
            int j = random.nextInt(16) + pos.getX();
            int k = random.nextInt(16) + pos.getZ();
            int l = random.nextInt(28) + 4;
            return new c_1514_x(j, l, k);
        });
    }
}


