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
import lightning.product.DepthAverageConfigation;

public class f_3410_P
extends SimpleFeatureDecorator<DepthAverageConfigation> {
    public f_3410_P(Codec<DepthAverageConfigation> p_i242023_1_) {
        super(p_i242023_1_);
    }

    @Override
    public Stream<c_1514_x> n_1700_B(Random random, DepthAverageConfigation p_212852_2_, c_1514_x pos) {
        int i = p_212852_2_.J_1907_R;
        int j = p_212852_2_.R_4764_Y;
        int k = pos.getX();
        int l = pos.getZ();
        int i1 = random.nextInt(j) + random.nextInt(j) - j + i;
        return Stream.of(new c_1514_x(k, i1, l));
    }
}


