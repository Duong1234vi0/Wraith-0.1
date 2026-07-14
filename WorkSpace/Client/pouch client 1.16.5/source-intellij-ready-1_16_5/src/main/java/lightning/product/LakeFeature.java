/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.StructureFeature;
import lightning.product.K_4074_S;
import lightning.product.K_4719_o;
import lightning.product.WorldGenLevel;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.Feature;
import lightning.product.k_594_Q;
import lightning.product.Material;
import lightning.product.BlockStateConfiguration;
import lightning.product.z_1753_f;

public class LakeFeature
extends Feature<BlockStateConfiguration> {
    private static final K_4074_S n_1700_B = a_3742_W.a_1344_X.multiplayerClientSuggestionProvider();

    public LakeFeature(Codec<BlockStateConfiguration> p_i231968_1_) {
        super(p_i231968_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, BlockStateConfiguration p_241855_5_) {
        while (p_241855_4_.getY() > 5 && p_241855_1_.u_1723_Y(p_241855_4_)) {
            p_241855_4_ = p_241855_4_.down();
        }
        if (p_241855_4_.getY() <= 4) {
            return false;
        }
        if (p_241855_1_.n_1700_B(SectionPos.n_1700_B(p_241855_4_ = p_241855_4_.down(4)), StructureFeature.t_1786_h).findAny().isPresent()) {
            return false;
        }
        boolean[] aboolean = new boolean[2048];
        int i = p_241855_3_.nextInt(4) + 4;
        for (int j = 0; j < i; ++j) {
            double d0 = p_241855_3_.nextDouble() * 6.0 + 3.0;
            double d1 = p_241855_3_.nextDouble() * 4.0 + 2.0;
            double d2 = p_241855_3_.nextDouble() * 6.0 + 3.0;
            double d3 = p_241855_3_.nextDouble() * (16.0 - d0 - 2.0) + 1.0 + d0 / 2.0;
            double d4 = p_241855_3_.nextDouble() * (8.0 - d1 - 4.0) + 2.0 + d1 / 2.0;
            double d5 = p_241855_3_.nextDouble() * (16.0 - d2 - 2.0) + 1.0 + d2 / 2.0;
            for (int l = 1; l < 15; ++l) {
                for (int i1 = 1; i1 < 15; ++i1) {
                    for (int j1 = 1; j1 < 7; ++j1) {
                        double d6 = ((double)l - d3) / (d0 / 2.0);
                        double d7 = ((double)j1 - d4) / (d1 / 2.0);
                        double d8 = ((double)i1 - d5) / (d2 / 2.0);
                        double d9 = d6 * d6 + d7 * d7 + d8 * d8;
                        if (!(d9 < 1.0)) continue;
                        aboolean[(l * 16 + i1) * 8 + j1] = true;
                    }
                }
            }
        }
        for (int k1 = 0; k1 < 16; ++k1) {
            for (int l2 = 0; l2 < 16; ++l2) {
                for (int k = 0; k < 8; ++k) {
                    boolean flag;
                    boolean bl = flag = !aboolean[(k1 * 16 + l2) * 8 + k] && (k1 < 15 && aboolean[((k1 + 1) * 16 + l2) * 8 + k] || k1 > 0 && aboolean[((k1 - 1) * 16 + l2) * 8 + k] || l2 < 15 && aboolean[(k1 * 16 + l2 + 1) * 8 + k] || l2 > 0 && aboolean[(k1 * 16 + (l2 - 1)) * 8 + k] || k < 7 && aboolean[(k1 * 16 + l2) * 8 + k + 1] || k > 0 && aboolean[(k1 * 16 + l2) * 8 + (k - 1)]);
                    if (!flag) continue;
                    Material material = p_241855_1_.getBlockState(p_241855_4_.add(k1, k, l2)).R_4764_Y();
                    if (k >= 4 && material.n_1700_B()) {
                        return false;
                    }
                    if (k >= 4 || material.J_1907_R() || p_241855_1_.getBlockState(p_241855_4_.add(k1, k, l2)) == p_241855_5_.J_1907_R) continue;
                    return false;
                }
            }
        }
        for (int l1 = 0; l1 < 16; ++l1) {
            for (int i3 = 0; i3 < 16; ++i3) {
                for (int i4 = 0; i4 < 8; ++i4) {
                    if (!aboolean[(l1 * 16 + i3) * 8 + i4]) continue;
                    p_241855_1_.n_1700_B(p_241855_4_.add(l1, i4, i3), i4 >= 4 ? n_1700_B : p_241855_5_.J_1907_R, 2);
                }
            }
        }
        for (int i2 = 0; i2 < 16; ++i2) {
            for (int j3 = 0; j3 < 16; ++j3) {
                for (int j4 = 4; j4 < 8; ++j4) {
                    c_1514_x blockpos;
                    if (!aboolean[(i2 * 16 + j3) * 8 + j4] || !LakeFeature.J_1907_R(p_241855_1_.getBlockState(blockpos = p_241855_4_.add(i2, j4 - 1, j3)).J_1907_R()) || p_241855_1_.getLightFor(K_4719_o.n_1700_B, p_241855_4_.add(i2, j4, j3)) <= 0) continue;
                    k_594_Q biome = p_241855_1_.P_1922_E(blockpos);
                    if (biome.P_1922_E().P_1922_E().n_1700_B().n_1700_B(a_3742_W.A_2714_y)) {
                        p_241855_1_.n_1700_B(blockpos, a_3742_W.A_2714_y.multiplayerClientSuggestionProvider(), 2);
                        continue;
                    }
                    p_241855_1_.n_1700_B(blockpos, a_3742_W.t_148_a.multiplayerClientSuggestionProvider(), 2);
                }
            }
        }
        if (p_241855_5_.J_1907_R.R_4764_Y() == Material.M_588_G) {
            for (int j2 = 0; j2 < 16; ++j2) {
                for (int k3 = 0; k3 < 16; ++k3) {
                    for (int k4 = 0; k4 < 8; ++k4) {
                        boolean flag1;
                        boolean bl = flag1 = !aboolean[(j2 * 16 + k3) * 8 + k4] && (j2 < 15 && aboolean[((j2 + 1) * 16 + k3) * 8 + k4] || j2 > 0 && aboolean[((j2 - 1) * 16 + k3) * 8 + k4] || k3 < 15 && aboolean[(j2 * 16 + k3 + 1) * 8 + k4] || k3 > 0 && aboolean[(j2 * 16 + (k3 - 1)) * 8 + k4] || k4 < 7 && aboolean[(j2 * 16 + k3) * 8 + k4 + 1] || k4 > 0 && aboolean[(j2 * 16 + k3) * 8 + (k4 - 1)]);
                        if (!flag1 || k4 >= 4 && p_241855_3_.nextInt(2) == 0 || !p_241855_1_.getBlockState(p_241855_4_.add(j2, k4, k3)).R_4764_Y().J_1907_R()) continue;
                        p_241855_1_.n_1700_B(p_241855_4_.add(j2, k4, k3), a_3742_W.J_1907_R.multiplayerClientSuggestionProvider(), 2);
                    }
                }
            }
        }
        if (p_241855_5_.J_1907_R.R_4764_Y() == Material.s_956_w) {
            for (int k2 = 0; k2 < 16; ++k2) {
                for (int l3 = 0; l3 < 16; ++l3) {
                    int l4 = 4;
                    c_1514_x blockpos1 = p_241855_4_.add(k2, 4, l3);
                    if (!p_241855_1_.P_1922_E(blockpos1).n_1700_B(p_241855_1_, blockpos1, false)) continue;
                    p_241855_1_.n_1700_B(blockpos1, a_3742_W.O_1795_e.multiplayerClientSuggestionProvider(), 2);
                }
            }
        }
        return true;
    }
}


