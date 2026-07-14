/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.G_3858_B;
import lightning.product.K_4074_S;
import lightning.product.WorldGenLevel;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.LevelAccessor;
import lightning.product.Material;
import lightning.product.u_530_F;
import lightning.product.HugeFungusConfiguration;
import lightning.product.z_1753_f;

public class HugeFungusFeature
extends Feature<HugeFungusConfiguration> {
    public HugeFungusFeature(Codec<HugeFungusConfiguration> p_i231959_1_) {
        super(p_i231959_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, HugeFungusConfiguration p_241855_5_) {
        T_2915_h block = p_241855_5_.u_1723_Y.J_1907_R();
        c_1514_x blockpos = null;
        T_2915_h block1 = p_241855_1_.getBlockState(p_241855_4_.down()).J_1907_R();
        if (block1 == block) {
            blockpos = p_241855_4_;
        }
        if (blockpos == null) {
            return false;
        }
        int i = u_530_F.n_1700_B(p_241855_3_, 4, 13);
        if (p_241855_3_.nextInt(12) == 0) {
            i *= 2;
        }
        if (!p_241855_5_.s_956_w) {
            int j = p_241855_2_.P_1922_E();
            if (blockpos.getY() + i + 1 >= j) {
                return false;
            }
        }
        boolean flag = !p_241855_5_.s_956_w && p_241855_3_.nextFloat() < 0.06f;
        p_241855_1_.n_1700_B(p_241855_4_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 4);
        this.n_1700_B(p_241855_1_, p_241855_3_, p_241855_5_, blockpos, i, flag);
        this.J_1907_R(p_241855_1_, p_241855_3_, p_241855_5_, blockpos, i, flag);
        return true;
    }

    private static boolean n_1700_B(LevelAccessor p_236315_0_, c_1514_x p_236315_1_, boolean p_236315_2_) {
        return p_236315_0_.n_1700_B(p_236315_1_, p_236320_1_ -> {
            Material material = p_236320_1_.R_4764_Y();
            return p_236320_1_.R_4764_Y().P_1922_E() || p_236315_2_ && material == Material.P_1922_E;
        });
    }

    private void n_1700_B(LevelAccessor p_236317_1_, Random p_236317_2_, HugeFungusConfiguration p_236317_3_, c_1514_x p_236317_4_, int p_236317_5_, boolean p_236317_6_) {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        K_4074_S blockstate = p_236317_3_.v_4262_N;
        int i = p_236317_6_ ? 1 : 0;
        for (int j = -i; j <= i; ++j) {
            for (int k = -i; k <= i; ++k) {
                boolean flag = p_236317_6_ && u_530_F.n_1700_B(j) == i && u_530_F.n_1700_B(k) == i;
                for (int l = 0; l < p_236317_5_; ++l) {
                    blockpos$mutable.n_1700_B(p_236317_4_, j, l, k);
                    if (!HugeFungusFeature.n_1700_B(p_236317_1_, blockpos$mutable, true)) continue;
                    if (p_236317_3_.s_956_w) {
                        if (!p_236317_1_.getBlockState((c_1514_x)blockpos$mutable.down()).v_4262_N()) {
                            p_236317_1_.J_1907_R((c_1514_x)blockpos$mutable, true);
                        }
                        p_236317_1_.n_1700_B((c_1514_x)blockpos$mutable, blockstate, 3);
                        continue;
                    }
                    if (flag) {
                        if (!(p_236317_2_.nextFloat() < 0.1f)) continue;
                        this.n_1700_B(p_236317_1_, blockpos$mutable, blockstate);
                        continue;
                    }
                    this.n_1700_B(p_236317_1_, blockpos$mutable, blockstate);
                }
            }
        }
    }

    private void J_1907_R(LevelAccessor p_236321_1_, Random p_236321_2_, HugeFungusConfiguration p_236321_3_, c_1514_x p_236321_4_, int p_236321_5_, boolean p_236321_6_) {
        int j;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        boolean flag = p_236321_3_.w_1484_f.n_1700_B(a_3742_W.LockSlot);
        int i = Math.min(p_236321_2_.nextInt(1 + p_236321_5_ / 3) + 5, p_236321_5_);
        for (int k = j = p_236321_5_ - i; k <= p_236321_5_; ++k) {
            int l;
            int n = l = k < p_236321_5_ - p_236321_2_.nextInt(3) ? 2 : 1;
            if (i > 8 && k < j + 4) {
                l = 3;
            }
            if (p_236321_6_) {
                ++l;
            }
            for (int i1 = -l; i1 <= l; ++i1) {
                for (int j1 = -l; j1 <= l; ++j1) {
                    boolean flag1 = i1 == -l || i1 == l;
                    boolean flag2 = j1 == -l || j1 == l;
                    boolean flag3 = !flag1 && !flag2 && k != p_236321_5_;
                    boolean flag4 = flag1 && flag2;
                    boolean flag5 = k < j + 3;
                    blockpos$mutable.n_1700_B(p_236321_4_, i1, k, j1);
                    if (!HugeFungusFeature.n_1700_B(p_236321_1_, blockpos$mutable, false)) continue;
                    if (p_236321_3_.s_956_w && !p_236321_1_.getBlockState((c_1514_x)blockpos$mutable.down()).v_4262_N()) {
                        p_236321_1_.J_1907_R((c_1514_x)blockpos$mutable, true);
                    }
                    if (flag5) {
                        if (flag3) continue;
                        this.n_1700_B(p_236321_1_, p_236321_2_, blockpos$mutable, p_236321_3_.w_1484_f, flag);
                        continue;
                    }
                    if (flag3) {
                        this.n_1700_B(p_236321_1_, p_236321_2_, p_236321_3_, blockpos$mutable, 0.1f, 0.2f, flag ? 0.1f : 0.0f);
                        continue;
                    }
                    if (flag4) {
                        this.n_1700_B(p_236321_1_, p_236321_2_, p_236321_3_, blockpos$mutable, 0.01f, 0.7f, flag ? 0.083f : 0.0f);
                        continue;
                    }
                    this.n_1700_B(p_236321_1_, p_236321_2_, p_236321_3_, blockpos$mutable, 5.0E-4f, 0.98f, flag ? 0.07f : 0.0f);
                }
            }
        }
    }

    private void n_1700_B(LevelAccessor p_236316_1_, Random p_236316_2_, HugeFungusConfiguration p_236316_3_, c_1514_x.n_1700_B p_236316_4_, float p_236316_5_, float p_236316_6_, float p_236316_7_) {
        if (p_236316_2_.nextFloat() < p_236316_5_) {
            this.n_1700_B(p_236316_1_, p_236316_4_, p_236316_3_.t_148_a);
        } else if (p_236316_2_.nextFloat() < p_236316_6_) {
            this.n_1700_B(p_236316_1_, p_236316_4_, p_236316_3_.w_1484_f);
            if (p_236316_2_.nextFloat() < p_236316_7_) {
                HugeFungusFeature.n_1700_B(p_236316_4_, p_236316_1_, p_236316_2_);
            }
        }
    }

    private void n_1700_B(LevelAccessor p_236318_1_, Random p_236318_2_, c_1514_x p_236318_3_, K_4074_S p_236318_4_, boolean p_236318_5_) {
        if (p_236318_1_.getBlockState(p_236318_3_.down()).n_1700_B(p_236318_4_.J_1907_R())) {
            this.n_1700_B(p_236318_1_, p_236318_3_, p_236318_4_);
        } else if ((double)p_236318_2_.nextFloat() < 0.15) {
            this.n_1700_B(p_236318_1_, p_236318_3_, p_236318_4_);
            if (p_236318_5_ && p_236318_2_.nextInt(11) == 0) {
                HugeFungusFeature.n_1700_B(p_236318_3_, p_236318_1_, p_236318_2_);
            }
        }
    }

    private static void n_1700_B(c_1514_x p_236319_0_, LevelAccessor p_236319_1_, Random p_236319_2_) {
        c_1514_x.n_1700_B blockpos$mutable = p_236319_0_.toMutable().n_1700_B(b_257_Y.n_1700_B);
        if (p_236319_1_.u_1723_Y(blockpos$mutable)) {
            int i = u_530_F.n_1700_B(p_236319_2_, 1, 5);
            if (p_236319_2_.nextInt(7) == 0) {
                i *= 2;
            }
            int j = 23;
            int k = 25;
            G_3858_B.n_1700_B(p_236319_1_, p_236319_2_, blockpos$mutable, i, 23, 25);
        }
    }
}



