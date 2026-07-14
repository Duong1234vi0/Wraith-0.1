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
import lightning.product.P_1781_m;
import lightning.product.c_1514_x;
import lightning.product.EdgeDecorator;

public abstract class BaseHeightmapDecorator<DC extends P_1781_m>
extends EdgeDecorator<DC> {
    public BaseHeightmapDecorator(Codec<DC> p_i242013_1_) {
        super(p_i242013_1_);
    }

    @Override
    public Stream<c_1514_x> n_1700_B(DecorationContext p_241857_1_, Random p_241857_2_, DC p_241857_3_, c_1514_x p_241857_4_) {
        int i = p_241857_4_.getX();
        int j = p_241857_4_.getZ();
        int k = p_241857_1_.n_1700_B(this.n_1700_B(p_241857_3_), i, j);
        return k > 0 ? Stream.of(new c_1514_x(i, k, j)) : Stream.of(new c_1514_x[0]);
    }
}


