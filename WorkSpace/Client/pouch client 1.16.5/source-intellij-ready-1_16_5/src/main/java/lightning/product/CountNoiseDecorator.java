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
import lightning.product.NoiseDependantDecoratorConfiguration;
import lightning.product.DecorationContext;
import lightning.product.c_1514_x;
import lightning.product.k_594_Q;
import lightning.product.y_2419_Z;

public class CountNoiseDecorator
extends y_2419_Z<NoiseDependantDecoratorConfiguration> {
    public CountNoiseDecorator(Codec<NoiseDependantDecoratorConfiguration> p_i242017_1_) {
        super(p_i242017_1_);
    }

    @Override
    public Stream<c_1514_x> n_1700_B(DecorationContext p_241857_1_, Random p_241857_2_, NoiseDependantDecoratorConfiguration p_241857_3_, c_1514_x p_241857_4_) {
        double d0 = k_594_Q.u_1723_Y.n_1700_B((double)p_241857_4_.getX() / 200.0, (double)p_241857_4_.getZ() / 200.0, false);
        int i = d0 < p_241857_3_.J_1907_R ? p_241857_3_.R_4764_Y : p_241857_3_.G_564_y;
        return IntStream.range(0, i).mapToObj(p_242879_1_ -> p_241857_4_);
    }
}


