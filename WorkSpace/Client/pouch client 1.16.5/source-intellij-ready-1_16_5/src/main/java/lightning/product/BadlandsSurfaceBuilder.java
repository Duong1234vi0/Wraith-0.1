/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import lightning.product.SurfaceBuilder;
import lightning.product.PerlinSimplexNoise;
import lightning.product.K_4074_S;
import lightning.product.SurfaceBuilderBaseConfiguration;
import lightning.product.T_2915_h;
import lightning.product.Z_927_M;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.ChunkAccess;
import lightning.product.WorldgenRandom;
import lightning.product.k_594_Q;

public class BadlandsSurfaceBuilder
extends SurfaceBuilder<SurfaceBuilderBaseConfiguration> {
    private static final K_4074_S v_4276_D = a_3742_W.I_2209_R.multiplayerClientSuggestionProvider();
    private static final K_4074_S d_2461_k = a_3742_W.h_3858_e.multiplayerClientSuggestionProvider();
    private static final K_4074_S G_624_v = a_3742_W.InventoryPlus.multiplayerClientSuggestionProvider();
    private static final K_4074_S T_2506_i = a_3742_W.AimAssist.multiplayerClientSuggestionProvider();
    private static final K_4074_S q_4610_l = a_3742_W.AutoSwap.multiplayerClientSuggestionProvider();
    private static final K_4074_S z_4693_k = a_3742_W.AutoTrap.multiplayerClientSuggestionProvider();
    private static final K_4074_S g_221_o = a_3742_W.AttackAura.multiplayerClientSuggestionProvider();
    protected K_4074_S[] n_1700_B;
    protected long J_1907_R;
    protected PerlinSimplexNoise R_4764_Y;
    protected PerlinSimplexNoise G_564_y;
    protected PerlinSimplexNoise P_1922_E;

    public BadlandsSurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> p_i232122_1_) {
        super(p_i232122_1_);
    }

    @Override
    public void n_1700_B(Random random, ChunkAccess chunkIn, k_594_Q biomeIn, int x, int z, int startHeight, double noise, K_4074_S defaultBlock, K_4074_S defaultFluid, int seaLevel, long seed, SurfaceBuilderBaseConfiguration config) {
        int i = x & 0xF;
        int j = z & 0xF;
        K_4074_S blockstate = v_4276_D;
        Z_927_M isurfacebuilderconfig = biomeIn.P_1922_E().P_1922_E();
        K_4074_S blockstate1 = isurfacebuilderconfig.J_1907_R();
        K_4074_S blockstate2 = isurfacebuilderconfig.n_1700_B();
        K_4074_S blockstate3 = blockstate1;
        int k = (int)(noise / 3.0 + 3.0 + random.nextDouble() * 0.25);
        boolean flag = Math.cos(noise / 3.0 * Math.PI) > 0.0;
        int l = -1;
        boolean flag1 = false;
        int i1 = 0;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int j1 = startHeight; j1 >= 0; --j1) {
            if (i1 >= 15) continue;
            blockpos$mutable.n_1700_B(i, j1, j);
            K_4074_S blockstate4 = chunkIn.getBlockState(blockpos$mutable);
            if (blockstate4.v_4262_N()) {
                l = -1;
                continue;
            }
            if (!blockstate4.n_1700_B(defaultBlock.J_1907_R())) continue;
            if (l == -1) {
                flag1 = false;
                if (k <= 0) {
                    blockstate = a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
                    blockstate3 = defaultBlock;
                } else if (j1 >= seaLevel - 4 && j1 <= seaLevel + 1) {
                    blockstate = v_4276_D;
                    blockstate3 = blockstate1;
                }
                if (j1 < seaLevel && (blockstate == null || blockstate.v_4262_N())) {
                    blockstate = defaultFluid;
                }
                l = k + Math.max(0, j1 - seaLevel);
                if (j1 >= seaLevel - 1) {
                    if (j1 > seaLevel + 3 + k) {
                        K_4074_S blockstate5 = j1 >= 64 && j1 <= 127 ? (flag ? G_624_v : this.n_1700_B(x, j1, z)) : d_2461_k;
                        chunkIn.setBlockState(blockpos$mutable, blockstate5, false);
                    } else {
                        chunkIn.setBlockState(blockpos$mutable, blockstate2, false);
                        flag1 = true;
                    }
                } else {
                    chunkIn.setBlockState(blockpos$mutable, blockstate3, false);
                    T_2915_h block = blockstate3.J_1907_R();
                    if (block == a_3742_W.I_2209_R || block == a_3742_W.h_3858_e || block == a_3742_W.l_4397_i || block == a_3742_W.t_4433_T || block == a_3742_W.AimAssist || block == a_3742_W.AntiBot || block == a_3742_W.AntiSurround || block == a_3742_W.s_4447_V || block == a_3742_W.AttackAura || block == a_3742_W.AutoAnchor || block == a_3742_W.AutoCrystal || block == a_3742_W.AutoExplosion || block == a_3742_W.AutoSwap || block == a_3742_W.AutoTotem || block == a_3742_W.AutoTrap || block == a_3742_W.s_4054_j) {
                        chunkIn.setBlockState(blockpos$mutable, d_2461_k, false);
                    }
                }
            } else if (l > 0) {
                --l;
                if (flag1) {
                    chunkIn.setBlockState(blockpos$mutable, d_2461_k, false);
                } else {
                    chunkIn.setBlockState(blockpos$mutable, this.n_1700_B(x, j1, z), false);
                }
            }
            ++i1;
        }
    }

    @Override
    public void n_1700_B(long seed) {
        if (this.J_1907_R != seed || this.n_1700_B == null) {
            this.J_1907_R(seed);
        }
        if (this.J_1907_R != seed || this.R_4764_Y == null || this.G_564_y == null) {
            WorldgenRandom sharedseedrandom = new WorldgenRandom(seed);
            this.R_4764_Y = new PerlinSimplexNoise(sharedseedrandom, IntStream.rangeClosed(-3, 0));
            this.G_564_y = new PerlinSimplexNoise(sharedseedrandom, (List<Integer>)ImmutableList.of((Object)0));
        }
        this.J_1907_R = seed;
    }

    protected void J_1907_R(long p_215430_1_) {
        this.n_1700_B = new K_4074_S[64];
        Arrays.fill(this.n_1700_B, G_624_v);
        WorldgenRandom sharedseedrandom = new WorldgenRandom(p_215430_1_);
        this.P_1922_E = new PerlinSimplexNoise(sharedseedrandom, (List<Integer>)ImmutableList.of((Object)0));
        for (int l1 = 0; l1 < 64; ++l1) {
            if ((l1 += sharedseedrandom.nextInt(5) + 1) >= 64) continue;
            this.n_1700_B[l1] = d_2461_k;
        }
        int i2 = sharedseedrandom.nextInt(4) + 2;
        for (int i = 0; i < i2; ++i) {
            int j = sharedseedrandom.nextInt(3) + 1;
            int k = sharedseedrandom.nextInt(64);
            for (int l = 0; k + l < 64 && l < j; ++l) {
                this.n_1700_B[k + l] = T_2506_i;
            }
        }
        int j2 = sharedseedrandom.nextInt(4) + 2;
        for (int k2 = 0; k2 < j2; ++k2) {
            int i3 = sharedseedrandom.nextInt(3) + 2;
            int l3 = sharedseedrandom.nextInt(64);
            for (int i1 = 0; l3 + i1 < 64 && i1 < i3; ++i1) {
                this.n_1700_B[l3 + i1] = q_4610_l;
            }
        }
        int l2 = sharedseedrandom.nextInt(4) + 2;
        for (int j3 = 0; j3 < l2; ++j3) {
            int i4 = sharedseedrandom.nextInt(3) + 1;
            int k4 = sharedseedrandom.nextInt(64);
            for (int j1 = 0; k4 + j1 < 64 && j1 < i4; ++j1) {
                this.n_1700_B[k4 + j1] = z_4693_k;
            }
        }
        int k3 = sharedseedrandom.nextInt(3) + 3;
        int j4 = 0;
        for (int l4 = 0; l4 < k3; ++l4) {
            boolean i5 = true;
            j4 += sharedseedrandom.nextInt(16) + 4;
            for (int k1 = 0; j4 + k1 < 64 && k1 < 1; ++k1) {
                this.n_1700_B[j4 + k1] = v_4276_D;
                if (j4 + k1 > 1 && sharedseedrandom.nextBoolean()) {
                    this.n_1700_B[j4 + k1 - 1] = g_221_o;
                }
                if (j4 + k1 >= 63 || !sharedseedrandom.nextBoolean()) continue;
                this.n_1700_B[j4 + k1 + 1] = g_221_o;
            }
        }
    }

    protected K_4074_S n_1700_B(int p_215431_1_, int p_215431_2_, int p_215431_3_) {
        int i = (int)Math.round(this.P_1922_E.n_1700_B((double)p_215431_1_ / 512.0, (double)p_215431_3_ / 512.0, false) * 2.0);
        return this.n_1700_B[(p_215431_2_ + i + 64) % 64];
    }
}



