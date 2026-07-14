/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.BitSet;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lightning.product.DecorationContext;
import lightning.product.Y_1387_d;
import lightning.product.c_1514_x;
import lightning.product.CarvingMaskDecoratorConfiguration;
import lightning.product.y_2419_Z;

public class CarvingMaskDecorator
extends y_2419_Z<CarvingMaskDecoratorConfiguration> {
    public CarvingMaskDecorator(Codec<CarvingMaskDecoratorConfiguration> p_i232065_1_) {
        super(p_i232065_1_);
    }

    @Override
    public Stream<c_1514_x> n_1700_B(DecorationContext p_241857_1_, Random p_241857_2_, CarvingMaskDecoratorConfiguration p_241857_3_, c_1514_x p_241857_4_) {
        Y_1387_d chunkpos = new Y_1387_d(p_241857_4_);
        BitSet bitset = p_241857_1_.n_1700_B(chunkpos, p_241857_3_.J_1907_R);
        return IntStream.range(0, bitset.length()).filter(p_215067_3_ -> bitset.get(p_215067_3_) && p_241857_2_.nextFloat() < p_241857_3_.R_4764_Y).mapToObj(p_215068_1_ -> {
            int i = p_215068_1_ & 0xF;
            int j = p_215068_1_ >> 4 & 0xF;
            int k = p_215068_1_ >> 8;
            return new c_1514_x(chunkpos.J_1907_R() + i, k, chunkpos.R_4764_Y() + j);
        });
    }
}


