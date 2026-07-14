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
import lightning.product.DecorationContext;
import lightning.product.S_4291_z;
import lightning.product.c_1514_x;
import lightning.product.EdgeDecorator;
import lightning.product.z_2963_s;

public class DarkOakTreePlacementDecorator
extends EdgeDecorator<S_4291_z> {
    public DarkOakTreePlacementDecorator(Codec<S_4291_z> p_i232082_1_) {
        super(p_i232082_1_);
    }

    @Override
    protected z_2963_s.n_1700_B n_1700_B(S_4291_z p_241858_1_) {
        return z_2963_s.n_1700_B.P_1922_E;
    }

    @Override
    public Stream<c_1514_x> n_1700_B(DecorationContext p_241857_1_, Random p_241857_2_, S_4291_z p_241857_3_, c_1514_x p_241857_4_) {
        return IntStream.range(0, 16).mapToObj(p_242881_5_ -> {
            int i = p_242881_5_ / 4;
            int j = p_242881_5_ % 4;
            int k = i * 4 + 1 + p_241857_2_.nextInt(3) + p_241857_4_.getX();
            int l = j * 4 + 1 + p_241857_2_.nextInt(3) + p_241857_4_.getZ();
            int i1 = p_241857_1_.n_1700_B(this.n_1700_B(p_241857_3_), k, l);
            return new c_1514_x(k, i1, l);
        });
    }
}


