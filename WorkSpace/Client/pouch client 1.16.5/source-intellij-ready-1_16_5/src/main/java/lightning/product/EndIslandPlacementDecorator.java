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
import lightning.product.S_4291_z;
import lightning.product.SimpleFeatureDecorator;
import lightning.product.c_1514_x;

public class EndIslandPlacementDecorator
extends SimpleFeatureDecorator<S_4291_z> {
    public EndIslandPlacementDecorator(Codec<S_4291_z> p_i232085_1_) {
        super(p_i232085_1_);
    }

    @Override
    public Stream<c_1514_x> n_1700_B(Random random, S_4291_z p_212852_2_, c_1514_x pos) {
        Stream<c_1514_x> stream = Stream.empty();
        if (random.nextInt(14) == 0) {
            stream = Stream.concat(stream, Stream.of(pos.add(random.nextInt(16), 55 + random.nextInt(16), random.nextInt(16))));
            if (random.nextInt(4) == 0) {
                stream = Stream.concat(stream, Stream.of(pos.add(random.nextInt(16), 55 + random.nextInt(16), random.nextInt(16))));
            }
            return stream;
        }
        return Stream.empty();
    }
}


