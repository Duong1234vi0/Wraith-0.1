/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;
import lightning.product.SimpleFeatureDecorator;
import lightning.product.c_1514_x;
import lightning.product.CountConfiguration;

public class FireDecorator
extends SimpleFeatureDecorator<CountConfiguration> {
    public FireDecorator(Codec<CountConfiguration> p_i232101_1_) {
        super(p_i232101_1_);
    }

    @Override
    public Stream<c_1514_x> n_1700_B(Random random, CountConfiguration p_212852_2_, c_1514_x pos) {
        ArrayList list = Lists.newArrayList();
        for (int i = 0; i < random.nextInt(random.nextInt(p_212852_2_.J_1907_R().n_1700_B(random)) + 1) + 1; ++i) {
            int j = random.nextInt(16) + pos.getX();
            int k = random.nextInt(16) + pos.getZ();
            int l = random.nextInt(120) + 4;
            list.add(new c_1514_x(j, l, k));
        }
        return list.stream();
    }
}


