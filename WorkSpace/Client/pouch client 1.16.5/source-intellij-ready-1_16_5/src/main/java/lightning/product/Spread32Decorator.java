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

public class Spread32Decorator
extends y_2419_Z<S_4291_z> {
    public Spread32Decorator(Codec<S_4291_z> p_i242031_1_) {
        super(p_i242031_1_);
    }

    @Override
    public Stream<c_1514_x> n_1700_B(DecorationContext p_241857_1_, Random p_241857_2_, S_4291_z p_241857_3_, c_1514_x p_241857_4_) {
        int i = p_241857_2_.nextInt(p_241857_4_.getY() + 32);
        return Stream.of(new c_1514_x(p_241857_4_.getX(), i, p_241857_4_.getZ()));
    }
}


