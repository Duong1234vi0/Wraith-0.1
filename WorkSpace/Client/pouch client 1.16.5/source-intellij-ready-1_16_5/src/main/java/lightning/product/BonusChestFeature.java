/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lightning.product.K_4074_S;
import lightning.product.WorldGenLevel;
import lightning.product.V_4572_l;
import lightning.product.Y_1387_d;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.o_2105_O;
import lightning.product.o_4810_o;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;

public class BonusChestFeature
extends Feature<o_2105_O> {
    public BonusChestFeature(Codec<o_2105_O> p_i231934_1_) {
        super(p_i231934_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, o_2105_O p_241855_5_) {
        Y_1387_d chunkpos = new Y_1387_d(p_241855_4_);
        List list = IntStream.rangeClosed(chunkpos.J_1907_R(), chunkpos.G_564_y()).boxed().collect(Collectors.toList());
        Collections.shuffle(list, p_241855_3_);
        List list1 = IntStream.rangeClosed(chunkpos.R_4764_Y(), chunkpos.P_1922_E()).boxed().collect(Collectors.toList());
        Collections.shuffle(list1, p_241855_3_);
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (Integer integer : list) {
            for (Integer integer1 : list1) {
                blockpos$mutable.n_1700_B(integer, 0, (int)integer1);
                c_1514_x blockpos = p_241855_1_.n_1700_B(z_2963_s.n_1700_B.u_1723_Y, (c_1514_x)blockpos$mutable);
                if (!p_241855_1_.u_1723_Y(blockpos) && !p_241855_1_.getBlockState(blockpos).u_2550_I(p_241855_1_, blockpos).J_1907_R()) continue;
                p_241855_1_.n_1700_B(blockpos, a_3742_W.L_1362_X.multiplayerClientSuggestionProvider(), 2);
                V_4572_l.n_1700_B(p_241855_1_, p_241855_3_, blockpos, o_4810_o.J_1907_R);
                K_4074_S blockstate = a_3742_W.o_2341_D.multiplayerClientSuggestionProvider();
                for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
                    c_1514_x blockpos1 = blockpos.offset(direction);
                    if (!blockstate.n_1700_B(p_241855_1_, blockpos1)) continue;
                    p_241855_1_.n_1700_B(blockpos1, blockstate, 2);
                }
                return true;
            }
        }
        return false;
    }
}


