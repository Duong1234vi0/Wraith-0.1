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
import lightning.product.DecorationContext;
import lightning.product.c_1514_x;
import lightning.product.DecoratedDecoratorConfiguration;
import lightning.product.y_2419_Z;

public class DecoratedDecorator
extends y_2419_Z<DecoratedDecoratorConfiguration> {
    public DecoratedDecorator(Codec<DecoratedDecoratorConfiguration> p_i242019_1_) {
        super(p_i242019_1_);
    }

    @Override
    public Stream<c_1514_x> n_1700_B(DecorationContext p_241857_1_, Random p_241857_2_, DecoratedDecoratorConfiguration p_241857_3_, c_1514_x p_241857_4_) {
        return p_241857_3_.n_1700_B().n_1700_B(p_241857_1_, p_241857_2_, p_241857_4_).flatMap(p_242882_3_ -> p_241857_3_.J_1907_R().n_1700_B(p_241857_1_, p_241857_2_, (c_1514_x)p_242882_3_));
    }
}


