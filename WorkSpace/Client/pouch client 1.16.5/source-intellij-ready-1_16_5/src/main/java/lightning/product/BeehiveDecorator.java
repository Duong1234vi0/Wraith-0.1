/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import lightning.product.F_997_G;
import lightning.product.TreeDecoratorType;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.TreeDecorator;
import lightning.product.WorldGenLevel;
import lightning.product.a_3742_W;
import lightning.product.b_1913_J;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.i_2154_H;
import lightning.product.t_5_h;
import lightning.product.v_1577_d;
import lightning.product.LevelWriter;

public class BeehiveDecorator
extends TreeDecorator {
    public static final Codec<BeehiveDecorator> n_1700_B = Codec.floatRange((float)0.0f, (float)1.0f).fieldOf("probability").xmap(BeehiveDecorator::new, p_236865_0_ -> Float.valueOf(p_236865_0_.J_1907_R)).codec();
    private final float J_1907_R;

    public BeehiveDecorator(float probabilityIn) {
        this.J_1907_R = probabilityIn;
    }

    @Override
    protected TreeDecoratorType<?> n_1700_B() {
        return TreeDecoratorType.G_564_y;
    }

    @Override
    public void n_1700_B(WorldGenLevel p_225576_1_, Random p_225576_2_, List<c_1514_x> p_225576_3_, List<c_1514_x> p_225576_4_, Set<c_1514_x> p_225576_5_, BoundingBox p_225576_6_) {
        if (!(p_225576_2_.nextFloat() >= this.J_1907_R)) {
            c_1514_x blockpos;
            c_1514_x blockpos1;
            b_257_Y direction = v_1577_d.n_1700_B(p_225576_2_);
            int i = !p_225576_4_.isEmpty() ? Math.max(p_225576_4_.get(0).getY() - 1, p_225576_3_.get(0).getY()) : Math.min(p_225576_3_.get(0).getY() + 1 + p_225576_2_.nextInt(3), p_225576_3_.get(p_225576_3_.size() - 1).getY());
            List list = p_225576_3_.stream().filter(p_236864_1_ -> p_236864_1_.getY() == i).collect(Collectors.toList());
            if (!list.isEmpty() && Feature.J_1907_R(p_225576_1_, blockpos1 = (blockpos = (c_1514_x)list.get(p_225576_2_.nextInt(list.size()))).offset(direction)) && Feature.J_1907_R(p_225576_1_, blockpos1.offset(b_257_Y.G_564_y))) {
                K_4074_S blockstate = (K_4074_S)a_3742_W.w_4866_k.multiplayerClientSuggestionProvider().n_1700_B(v_1577_d.P_4830_p, b_257_Y.G_564_y);
                this.n_1700_B((LevelWriter)p_225576_1_, blockpos1, blockstate, p_225576_5_, p_225576_6_);
                i_2154_H tileentity = p_225576_1_.getTileEntity(blockpos1);
                if (tileentity instanceof F_997_G) {
                    F_997_G beehivetileentity = (F_997_G)tileentity;
                    int j = 2 + p_225576_2_.nextInt(2);
                    for (int k = 0; k < j; ++k) {
                        b_1913_J beeentity = new b_1913_J((t_5_h<? extends b_1913_J>)t_5_h.P_1922_E, (b_4507_u)p_225576_1_.J_1907_R());
                        beehivetileentity.n_1700_B(beeentity, false, p_225576_2_.nextInt(599));
                    }
                }
            }
        }
    }
}


