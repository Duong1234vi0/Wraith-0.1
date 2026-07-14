/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.mojang.serialization.Codec
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.ColumnFeatureConfiguration;
import lightning.product.K_4074_S;
import lightning.product.WorldGenLevel;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.LevelAccessor;
import lightning.product.z_1753_f;

public class BasaltColumnsFeature
extends Feature<ColumnFeatureConfiguration> {
    private static final ImmutableList<T_2915_h> n_1700_B = ImmutableList.of((Object)a_3742_W.H_2857_Y, (Object)a_3742_W.Z_875_P, (Object)a_3742_W.LevitationControl, (Object)a_3742_W.C_415_h, (Object)a_3742_W.h_1015_G, (Object)a_3742_W.d_4500_Q, (Object)a_3742_W.O_2761_o, (Object)a_3742_W.W_3729_Q, (Object)a_3742_W.L_1362_X, (Object)a_3742_W.j_306_t);

    public BasaltColumnsFeature(Codec<ColumnFeatureConfiguration> p_i231925_1_) {
        super(p_i231925_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, ColumnFeatureConfiguration p_241855_5_) {
        int i = p_241855_2_.u_1723_Y();
        if (!BasaltColumnsFeature.n_1700_B((LevelAccessor)p_241855_1_, i, p_241855_4_.toMutable())) {
            return false;
        }
        int j = p_241855_5_.J_1907_R().n_1700_B(p_241855_3_);
        boolean flag = p_241855_3_.nextFloat() < 0.9f;
        int k = Math.min(j, flag ? 5 : 8);
        int l = flag ? 50 : 15;
        boolean flag1 = false;
        for (c_1514_x blockpos : c_1514_x.getRandomPositions(p_241855_3_, l, p_241855_4_.getX() - k, p_241855_4_.getY(), p_241855_4_.getZ() - k, p_241855_4_.getX() + k, p_241855_4_.getY(), p_241855_4_.getZ() + k)) {
            int i1 = j - blockpos.manhattanDistance(p_241855_4_);
            if (i1 < 0) continue;
            flag1 |= this.n_1700_B((LevelAccessor)p_241855_1_, i, blockpos, i1, p_241855_5_.n_1700_B().n_1700_B(p_241855_3_));
        }
        return flag1;
    }

    private boolean n_1700_B(LevelAccessor p_236248_1_, int p_236248_2_, c_1514_x p_236248_3_, int p_236248_4_, int p_236248_5_) {
        boolean flag = false;
        block0: for (c_1514_x blockpos : c_1514_x.getAllInBoxMutable(p_236248_3_.getX() - p_236248_5_, p_236248_3_.getY(), p_236248_3_.getZ() - p_236248_5_, p_236248_3_.getX() + p_236248_5_, p_236248_3_.getY(), p_236248_3_.getZ() + p_236248_5_)) {
            int i = blockpos.manhattanDistance(p_236248_3_);
            c_1514_x blockpos1 = BasaltColumnsFeature.n_1700_B(p_236248_1_, p_236248_2_, blockpos) ? BasaltColumnsFeature.n_1700_B(p_236248_1_, p_236248_2_, blockpos.toMutable(), i) : BasaltColumnsFeature.n_1700_B(p_236248_1_, blockpos.toMutable(), i);
            if (blockpos1 == null) continue;
            c_1514_x.n_1700_B blockpos$mutable = blockpos1.toMutable();
            for (int j = p_236248_4_ - i / 2; j >= 0; --j) {
                if (BasaltColumnsFeature.n_1700_B(p_236248_1_, p_236248_2_, (c_1514_x)blockpos$mutable)) {
                    this.n_1700_B(p_236248_1_, blockpos$mutable, a_3742_W.s_4990_V.multiplayerClientSuggestionProvider());
                    blockpos$mutable.n_1700_B(b_257_Y.J_1907_R);
                    flag = true;
                    continue;
                }
                if (!p_236248_1_.getBlockState(blockpos$mutable).n_1700_B(a_3742_W.s_4990_V)) continue block0;
                blockpos$mutable.n_1700_B(b_257_Y.J_1907_R);
            }
        }
        return flag;
    }

    @Nullable
    private static c_1514_x n_1700_B(LevelAccessor p_236246_0_, int p_236246_1_, c_1514_x.n_1700_B p_236246_2_, int p_236246_3_) {
        while (p_236246_2_.getY() > 1 && p_236246_3_ > 0) {
            --p_236246_3_;
            if (BasaltColumnsFeature.n_1700_B(p_236246_0_, p_236246_1_, p_236246_2_)) {
                return p_236246_2_;
            }
            p_236246_2_.n_1700_B(b_257_Y.n_1700_B);
        }
        return null;
    }

    private static boolean n_1700_B(LevelAccessor p_242762_0_, int p_242762_1_, c_1514_x.n_1700_B p_242762_2_) {
        if (!BasaltColumnsFeature.n_1700_B(p_242762_0_, p_242762_1_, (c_1514_x)p_242762_2_)) {
            return false;
        }
        K_4074_S blockstate = p_242762_0_.getBlockState(p_242762_2_.n_1700_B(b_257_Y.n_1700_B));
        p_242762_2_.n_1700_B(b_257_Y.J_1907_R);
        return !blockstate.v_4262_N() && !n_1700_B.contains((Object)blockstate.J_1907_R());
    }

    @Nullable
    private static c_1514_x n_1700_B(LevelAccessor p_236249_0_, c_1514_x.n_1700_B p_236249_1_, int p_236249_2_) {
        while (p_236249_1_.getY() < p_236249_0_.c_3005_b() && p_236249_2_ > 0) {
            --p_236249_2_;
            K_4074_S blockstate = p_236249_0_.getBlockState(p_236249_1_);
            if (n_1700_B.contains((Object)blockstate.J_1907_R())) {
                return null;
            }
            if (blockstate.v_4262_N()) {
                return p_236249_1_;
            }
            p_236249_1_.n_1700_B(b_257_Y.J_1907_R);
        }
        return null;
    }

    private static boolean n_1700_B(LevelAccessor p_236247_0_, int p_236247_1_, c_1514_x p_236247_2_) {
        K_4074_S blockstate = p_236247_0_.getBlockState(p_236247_2_);
        return blockstate.v_4262_N() || blockstate.n_1700_B(a_3742_W.H_2857_Y) && p_236247_2_.getY() <= p_236247_1_;
    }
}



