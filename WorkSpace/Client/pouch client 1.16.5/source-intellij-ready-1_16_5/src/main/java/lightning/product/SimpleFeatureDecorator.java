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
import lightning.product.y_2419_Z;

public abstract class SimpleFeatureDecorator<DC extends P_1781_m>
extends y_2419_Z<DC> {
    public SimpleFeatureDecorator(Codec<DC> p_i232095_1_) {
        super(p_i232095_1_);
    }

    @Override
    public final Stream<c_1514_x> n_1700_B(DecorationContext p_241857_1_, Random p_241857_2_, DC p_241857_3_, c_1514_x p_241857_4_) {
        return this.n_1700_B(p_241857_2_, p_241857_3_, p_241857_4_);
    }

    protected abstract Stream<c_1514_x> n_1700_B(Random var1, DC var2, c_1514_x var3);
}


