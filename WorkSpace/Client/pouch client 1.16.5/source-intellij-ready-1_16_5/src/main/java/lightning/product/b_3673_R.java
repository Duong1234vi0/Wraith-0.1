/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.WorldGenLevel;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.LevelAccessor;
import lightning.product.Material;
import lightning.product.BlockStateConfiguration;
import lightning.product.u_530_F;
import lightning.product.z_1753_f;

public class b_3673_R
extends Feature<BlockStateConfiguration> {
    public b_3673_R(Codec<BlockStateConfiguration> p_i231964_1_) {
        super(p_i231964_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, BlockStateConfiguration p_241855_5_) {
        boolean flag2;
        int l;
        p_241855_4_ = new c_1514_x(p_241855_4_.getX(), p_241855_2_.u_1723_Y(), p_241855_4_.getZ());
        boolean flag = p_241855_3_.nextDouble() > 0.7;
        K_4074_S blockstate = p_241855_5_.J_1907_R;
        double d0 = p_241855_3_.nextDouble() * 2.0 * Math.PI;
        int i = 11 - p_241855_3_.nextInt(5);
        int j = 3 + p_241855_3_.nextInt(3);
        boolean flag1 = p_241855_3_.nextDouble() > 0.7;
        int k = 11;
        int n = l = flag1 ? p_241855_3_.nextInt(6) + 6 : p_241855_3_.nextInt(15) + 3;
        if (!flag1 && p_241855_3_.nextDouble() > 0.9) {
            l += p_241855_3_.nextInt(19) + 7;
        }
        int i1 = Math.min(l + p_241855_3_.nextInt(11), 18);
        int j1 = Math.min(l + p_241855_3_.nextInt(7) - p_241855_3_.nextInt(5), 11);
        int k1 = flag1 ? i : 11;
        for (int l1 = -k1; l1 < k1; ++l1) {
            for (int i2 = -k1; i2 < k1; ++i2) {
                for (int j2 = 0; j2 < l; ++j2) {
                    int k2;
                    int n2 = k2 = flag1 ? this.J_1907_R(j2, l, j1) : this.n_1700_B(p_241855_3_, j2, l, j1);
                    if (!flag1 && l1 >= k2) continue;
                    this.n_1700_B(p_241855_1_, p_241855_3_, p_241855_4_, l, l1, j2, i2, k2, k1, flag1, j, d0, flag, blockstate);
                }
            }
        }
        this.n_1700_B(p_241855_1_, p_241855_4_, j1, l, flag1, i);
        for (int i3 = -k1; i3 < k1; ++i3) {
            for (int j3 = -k1; j3 < k1; ++j3) {
                for (int k3 = -1; k3 > -i1; --k3) {
                    int l3 = flag1 ? u_530_F.u_1723_Y((float)k1 * (1.0f - (float)Math.pow(k3, 2.0) / ((float)i1 * 8.0f))) : k1;
                    int l2 = this.J_1907_R(p_241855_3_, -k3, i1, j1);
                    if (i3 >= l2) continue;
                    this.n_1700_B(p_241855_1_, p_241855_3_, p_241855_4_, i1, i3, k3, j3, l2, l3, flag1, j, d0, flag, blockstate);
                }
            }
        }
        boolean bl = flag1 ? p_241855_3_.nextDouble() > 0.1 : (flag2 = p_241855_3_.nextDouble() > 0.7);
        if (flag2) {
            this.n_1700_B(p_241855_3_, p_241855_1_, j1, l, p_241855_4_, flag1, i, d0, j);
        }
        return true;
    }

    private void n_1700_B(Random rand, LevelAccessor worldIn, int p_205184_3_, int p_205184_4_, c_1514_x pos, boolean p_205184_6_, int p_205184_7_, double p_205184_8_, int p_205184_10_) {
        int i = rand.nextBoolean() ? -1 : 1;
        int j = rand.nextBoolean() ? -1 : 1;
        int k = rand.nextInt(Math.max(p_205184_3_ / 2 - 2, 1));
        if (rand.nextBoolean()) {
            k = p_205184_3_ / 2 + 1 - rand.nextInt(Math.max(p_205184_3_ - p_205184_3_ / 2 - 1, 1));
        }
        int l = rand.nextInt(Math.max(p_205184_3_ / 2 - 2, 1));
        if (rand.nextBoolean()) {
            l = p_205184_3_ / 2 + 1 - rand.nextInt(Math.max(p_205184_3_ - p_205184_3_ / 2 - 1, 1));
        }
        if (p_205184_6_) {
            k = l = rand.nextInt(Math.max(p_205184_7_ - 5, 1));
        }
        c_1514_x blockpos = new c_1514_x(i * k, 0, j * l);
        double d0 = p_205184_6_ ? p_205184_8_ + 1.5707963267948966 : rand.nextDouble() * 2.0 * Math.PI;
        for (int i1 = 0; i1 < p_205184_4_ - 3; ++i1) {
            int j1 = this.n_1700_B(rand, i1, p_205184_4_, p_205184_3_);
            this.n_1700_B(j1, i1, pos, worldIn, false, d0, blockpos, p_205184_7_, p_205184_10_);
        }
        for (int k1 = -1; k1 > -p_205184_4_ + rand.nextInt(5); --k1) {
            int l1 = this.J_1907_R(rand, -k1, p_205184_4_, p_205184_3_);
            this.n_1700_B(l1, k1, pos, worldIn, true, d0, blockpos, p_205184_7_, p_205184_10_);
        }
    }

    private void n_1700_B(int p_205174_1_, int yDiff, c_1514_x p_205174_3_, LevelAccessor worldIn, boolean placeWater, double p_205174_6_, c_1514_x p_205174_8_, int p_205174_9_, int p_205174_10_) {
        int i = p_205174_1_ + 1 + p_205174_9_ / 3;
        int j = Math.min(p_205174_1_ - 3, 3) + p_205174_10_ / 2 - 1;
        for (int k = -i; k < i; ++k) {
            for (int l = -i; l < i; ++l) {
                c_1514_x blockpos;
                T_2915_h block;
                double d0 = this.n_1700_B(k, l, p_205174_8_, i, j, p_205174_6_);
                if (!(d0 < 0.0) || !this.R_4764_Y(block = worldIn.getBlockState(blockpos = p_205174_3_.add(k, yDiff, l)).J_1907_R()) && block != a_3742_W.l_697_B) continue;
                if (placeWater) {
                    this.n_1700_B(worldIn, blockpos, a_3742_W.c_3005_b.multiplayerClientSuggestionProvider());
                    continue;
                }
                this.n_1700_B(worldIn, blockpos, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider());
                this.n_1700_B(worldIn, blockpos);
            }
        }
    }

    private void n_1700_B(LevelAccessor worldIn, c_1514_x posIn) {
        if (worldIn.getBlockState(posIn.up()).n_1700_B(a_3742_W.X_290_I)) {
            this.n_1700_B(worldIn, posIn.up(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider());
        }
    }

    private void n_1700_B(LevelAccessor worldIn, Random rand, c_1514_x pos, int p_205181_4_, int xIn, int yIn, int zIn, int p_205181_8_, int p_205181_9_, boolean p_205181_10_, int p_205181_11_, double p_205181_12_, boolean p_205181_14_, K_4074_S p_205181_15_) {
        double d0;
        double d = d0 = p_205181_10_ ? this.n_1700_B(xIn, zIn, c_1514_x.ZERO, p_205181_9_, this.n_1700_B(yIn, p_205181_4_, p_205181_11_), p_205181_12_) : this.n_1700_B(xIn, zIn, c_1514_x.ZERO, p_205181_8_, rand);
        if (d0 < 0.0) {
            double d1;
            c_1514_x blockpos = pos.add(xIn, yIn, zIn);
            double d2 = d1 = p_205181_10_ ? -0.5 : (double)(-6 - rand.nextInt(3));
            if (d0 > d1 && rand.nextDouble() > 0.9) {
                return;
            }
            this.n_1700_B(blockpos, worldIn, rand, p_205181_4_ - yIn, p_205181_4_, p_205181_10_, p_205181_14_, p_205181_15_);
        }
    }

    private void n_1700_B(c_1514_x pos, LevelAccessor worldIn, Random p_205175_3_, int p_205175_4_, int p_205175_5_, boolean p_205175_6_, boolean p_205175_7_, K_4074_S p_205175_8_) {
        K_4074_S blockstate = worldIn.getBlockState(pos);
        if (blockstate.R_4764_Y() == Material.n_1700_B || blockstate.n_1700_B(a_3742_W.l_697_B) || blockstate.n_1700_B(a_3742_W.O_1795_e) || blockstate.n_1700_B(a_3742_W.c_3005_b)) {
            int i;
            boolean flag = !p_205175_6_ || p_205175_3_.nextDouble() > 0.05;
            int n = i = p_205175_6_ ? 3 : 2;
            if (p_205175_7_ && !blockstate.n_1700_B(a_3742_W.c_3005_b) && (double)p_205175_4_ <= (double)p_205175_3_.nextInt(Math.max(1, p_205175_5_ / i)) + (double)p_205175_5_ * 0.6 && flag) {
                this.n_1700_B(worldIn, pos, a_3742_W.l_697_B.multiplayerClientSuggestionProvider());
            } else {
                this.n_1700_B(worldIn, pos, p_205175_8_);
            }
        }
    }

    private int n_1700_B(int p_205176_1_, int p_205176_2_, int p_205176_3_) {
        int i = p_205176_3_;
        if (p_205176_1_ > 0 && p_205176_2_ - p_205176_1_ <= 3) {
            i = p_205176_3_ - (4 - (p_205176_2_ - p_205176_1_));
        }
        return i;
    }

    private double n_1700_B(int p_205177_1_, int p_205177_2_, c_1514_x pos, int p_205177_4_, Random rand) {
        float f = 10.0f * u_530_F.n_1700_B(rand.nextFloat(), 0.2f, 0.8f) / (float)p_205177_4_;
        return (double)f + Math.pow(p_205177_1_ - pos.getX(), 2.0) + Math.pow(p_205177_2_ - pos.getZ(), 2.0) - Math.pow(p_205177_4_, 2.0);
    }

    private double n_1700_B(int xIn, int zIn, c_1514_x pos, int p_205180_4_, int p_205180_5_, double p_205180_6_) {
        return Math.pow(((double)(xIn - pos.getX()) * Math.cos(p_205180_6_) - (double)(zIn - pos.getZ()) * Math.sin(p_205180_6_)) / (double)p_205180_4_, 2.0) + Math.pow(((double)(xIn - pos.getX()) * Math.sin(p_205180_6_) + (double)(zIn - pos.getZ()) * Math.cos(p_205180_6_)) / (double)p_205180_5_, 2.0) - 1.0;
    }

    private int n_1700_B(Random rand, int p_205183_2_, int p_205183_3_, int p_205183_4_) {
        float f = 3.5f - rand.nextFloat();
        float f1 = (1.0f - (float)Math.pow(p_205183_2_, 2.0) / ((float)p_205183_3_ * f)) * (float)p_205183_4_;
        if (p_205183_3_ > 15 + rand.nextInt(5)) {
            int i = p_205183_2_ < 3 + rand.nextInt(6) ? p_205183_2_ / 2 : p_205183_2_;
            f1 = (1.0f - (float)i / ((float)p_205183_3_ * f * 0.4f)) * (float)p_205183_4_;
        }
        return u_530_F.u_1723_Y(f1 / 2.0f);
    }

    private int J_1907_R(int p_205178_1_, int p_205178_2_, int p_205178_3_) {
        float f = 1.0f;
        float f1 = (1.0f - (float)Math.pow(p_205178_1_, 2.0) / ((float)p_205178_2_ * 1.0f)) * (float)p_205178_3_;
        return u_530_F.u_1723_Y(f1 / 2.0f);
    }

    private int J_1907_R(Random rand, int p_205187_2_, int p_205187_3_, int p_205187_4_) {
        float f = 1.0f + rand.nextFloat() / 2.0f;
        float f1 = (1.0f - (float)p_205187_2_ / ((float)p_205187_3_ * f)) * (float)p_205187_4_;
        return u_530_F.u_1723_Y(f1 / 2.0f);
    }

    private boolean R_4764_Y(T_2915_h blockIn) {
        return blockIn == a_3742_W.ServerHelper || blockIn == a_3742_W.l_697_B || blockIn == a_3742_W.G_4691_Q;
    }

    private boolean n_1700_B(BlockGetter worldIn, c_1514_x pos) {
        return worldIn.getBlockState(pos.down()).R_4764_Y() == Material.n_1700_B;
    }

    private void n_1700_B(LevelAccessor worldIn, c_1514_x pos, int p_205186_3_, int p_205186_4_, boolean p_205186_5_, int p_205186_6_) {
        int i = p_205186_5_ ? p_205186_6_ : p_205186_3_ / 2;
        for (int j = -i; j <= i; ++j) {
            for (int k = -i; k <= i; ++k) {
                for (int l = 0; l <= p_205186_4_; ++l) {
                    c_1514_x blockpos = pos.add(j, l, k);
                    T_2915_h block = worldIn.getBlockState(blockpos).J_1907_R();
                    if (!this.R_4764_Y(block) && block != a_3742_W.X_290_I) continue;
                    if (this.n_1700_B((BlockGetter)worldIn, blockpos)) {
                        this.n_1700_B(worldIn, blockpos, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider());
                        this.n_1700_B(worldIn, blockpos.up(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider());
                        continue;
                    }
                    if (!this.R_4764_Y(block)) continue;
                    T_2915_h[] ablock = new T_2915_h[]{worldIn.getBlockState(blockpos.west()).J_1907_R(), worldIn.getBlockState(blockpos.east()).J_1907_R(), worldIn.getBlockState(blockpos.north()).J_1907_R(), worldIn.getBlockState(blockpos.south()).J_1907_R()};
                    int i1 = 0;
                    for (T_2915_h block1 : ablock) {
                        if (this.R_4764_Y(block1)) continue;
                        ++i1;
                    }
                    if (i1 < 3) continue;
                    this.n_1700_B(worldIn, blockpos, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider());
                }
            }
        }
    }
}



