/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.K_4074_S;
import lightning.product.SurfaceBuilderBaseConfiguration;
import lightning.product.Z_927_M;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.ChunkAccess;
import lightning.product.BadlandsSurfaceBuilder;
import lightning.product.k_594_Q;

public class WoodedBadlandsSurfaceBuilder
extends BadlandsSurfaceBuilder {
    private static final K_4074_S v_4276_D = a_3742_W.I_2209_R.multiplayerClientSuggestionProvider();
    private static final K_4074_S d_2461_k = a_3742_W.h_3858_e.multiplayerClientSuggestionProvider();
    private static final K_4074_S G_624_v = a_3742_W.InventoryPlus.multiplayerClientSuggestionProvider();

    public WoodedBadlandsSurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> p_i232138_1_) {
        super(p_i232138_1_);
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
                    if (j1 > 86 + k * 2) {
                        if (flag) {
                            chunkIn.setBlockState(blockpos$mutable, a_3742_W.u_2550_I.multiplayerClientSuggestionProvider(), false);
                        } else {
                            chunkIn.setBlockState(blockpos$mutable, a_3742_W.t_148_a.multiplayerClientSuggestionProvider(), false);
                        }
                    } else if (j1 > seaLevel + 3 + k) {
                        K_4074_S blockstate5 = j1 >= 64 && j1 <= 127 ? (flag ? G_624_v : this.n_1700_B(x, j1, z)) : d_2461_k;
                        chunkIn.setBlockState(blockpos$mutable, blockstate5, false);
                    } else {
                        chunkIn.setBlockState(blockpos$mutable, blockstate2, false);
                        flag1 = true;
                    }
                } else {
                    chunkIn.setBlockState(blockpos$mutable, blockstate3, false);
                    if (blockstate3 == v_4276_D) {
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
}



