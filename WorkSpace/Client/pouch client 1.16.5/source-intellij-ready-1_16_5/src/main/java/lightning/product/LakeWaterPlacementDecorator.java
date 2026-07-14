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
import lightning.product.ChanceDecoratorConfiguration;
import lightning.product.y_2419_Z;

public class LakeWaterPlacementDecorator
extends y_2419_Z<ChanceDecoratorConfiguration> {
    public LakeWaterPlacementDecorator(Codec<ChanceDecoratorConfiguration> p_i232090_1_) {
        super(p_i232090_1_);
    }

    @Override
    public Stream<c_1514_x> n_1700_B(DecorationContext p_241857_1_, Random p_241857_2_, ChanceDecoratorConfiguration p_241857_3_, c_1514_x p_241857_4_) {
        if (p_241857_2_.nextInt(p_241857_3_.J_1907_R) == 0) {
            int i = p_241857_2_.nextInt(16) + p_241857_4_.getX();
            int j = p_241857_2_.nextInt(16) + p_241857_4_.getZ();
            int k = p_241857_2_.nextInt(p_241857_1_.n_1700_B());
            return Stream.of(new c_1514_x(i, k, j));
        }
        return Stream.empty();
    }
}


