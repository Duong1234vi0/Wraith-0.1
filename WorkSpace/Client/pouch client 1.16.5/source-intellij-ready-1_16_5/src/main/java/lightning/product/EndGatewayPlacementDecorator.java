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
import lightning.product.S_4291_z;
import lightning.product.c_1514_x;
import lightning.product.y_2419_Z;
import lightning.product.z_2963_s;

public class EndGatewayPlacementDecorator
extends y_2419_Z<S_4291_z> {
    public EndGatewayPlacementDecorator(Codec<S_4291_z> p_i232084_1_) {
        super(p_i232084_1_);
    }

    @Override
    public Stream<c_1514_x> n_1700_B(DecorationContext p_241857_1_, Random p_241857_2_, S_4291_z p_241857_3_, c_1514_x p_241857_4_) {
        int j;
        int i;
        int k;
        if (p_241857_2_.nextInt(700) == 0 && (k = p_241857_1_.n_1700_B(z_2963_s.n_1700_B.P_1922_E, i = p_241857_2_.nextInt(16) + p_241857_4_.getX(), j = p_241857_2_.nextInt(16) + p_241857_4_.getZ())) > 0) {
            int l = k + 3 + p_241857_2_.nextInt(7);
            return Stream.of(new c_1514_x(i, l, j));
        }
        return Stream.empty();
    }
}


