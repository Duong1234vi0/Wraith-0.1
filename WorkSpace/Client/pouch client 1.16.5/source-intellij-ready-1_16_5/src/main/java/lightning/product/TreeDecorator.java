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
import lightning.product.TreeDecoratorType;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.WorldGenLevel;
import lightning.product.U_1266_O;
import lightning.product.V_3137_a;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.LevelWriter;

public abstract class TreeDecorator {
    public static final Codec<TreeDecorator> R_4764_Y = V_3137_a.S_980_j.dispatch(TreeDecorator::n_1700_B, TreeDecoratorType::n_1700_B);

    protected abstract TreeDecoratorType<?> n_1700_B();

    public abstract void n_1700_B(WorldGenLevel var1, Random var2, List<c_1514_x> var3, List<c_1514_x> var4, Set<c_1514_x> var5, BoundingBox var6);

    protected void n_1700_B(LevelWriter p_227424_1_, c_1514_x p_227424_2_, U_1266_O p_227424_3_, Set<c_1514_x> p_227424_4_, BoundingBox p_227424_5_) {
        this.n_1700_B(p_227424_1_, p_227424_2_, (K_4074_S)a_3742_W.U_4087_m.multiplayerClientSuggestionProvider().n_1700_B(p_227424_3_, true), p_227424_4_, p_227424_5_);
    }

    protected void n_1700_B(LevelWriter p_227423_1_, c_1514_x p_227423_2_, K_4074_S p_227423_3_, Set<c_1514_x> p_227423_4_, BoundingBox p_227423_5_) {
        p_227423_1_.n_1700_B(p_227423_2_, p_227423_3_, 19);
        p_227423_4_.add(p_227423_2_);
        p_227423_5_.J_1907_R(new BoundingBox(p_227423_2_, p_227423_2_));
    }
}


