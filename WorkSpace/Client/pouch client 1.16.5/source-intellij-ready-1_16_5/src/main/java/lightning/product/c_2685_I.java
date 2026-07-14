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

public class c_2685_I
extends SimpleFeatureDecorator<S_4291_z> {
    public c_2685_I(Codec<S_4291_z> p_i232088_1_) {
        super(p_i232088_1_);
    }

    @Override
    public Stream<c_1514_x> n_1700_B(Random random, S_4291_z p_212852_2_, c_1514_x pos) {
        int i = random.nextInt(8) + 4 + pos.getX();
        int j = random.nextInt(8) + 4 + pos.getZ();
        return Stream.of(new c_1514_x(i, pos.getY(), j));
    }
}


