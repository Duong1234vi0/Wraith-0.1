/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.E_3771_B;
import lightning.product.K_4074_S;
import lightning.product.WorldGenLevel;
import lightning.product.V_4572_l;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.i_2154_H;
import lightning.product.SpawnerBlockEntity;
import lightning.product.j_3341_s;
import lightning.product.o_2105_O;
import lightning.product.o_4810_o;
import lightning.product.Material;
import lightning.product.t_5_h;
import lightning.product.z_1753_f;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MonsterRoomFeature
extends Feature<o_2105_O> {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final t_5_h<?>[] D_4792_h = new t_5_h[]{t_5_h.V_1446_Y, t_5_h.R_3077_Z, t_5_h.R_3077_Z, t_5_h.RealmsServerPing};
    private static final K_4074_S s_2632_s = a_3742_W.a_1344_X.multiplayerClientSuggestionProvider();

    public MonsterRoomFeature(Codec<o_2105_O> p_i231970_1_) {
        super(p_i231970_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, o_2105_O p_241855_5_) {
        int i = 3;
        int j = p_241855_3_.nextInt(2) + 2;
        int k = -j - 1;
        int l = j + 1;
        int i1 = -1;
        int j1 = 4;
        int k1 = p_241855_3_.nextInt(2) + 2;
        int l1 = -k1 - 1;
        int i2 = k1 + 1;
        int j2 = 0;
        for (int k2 = k; k2 <= l; ++k2) {
            for (int l2 = -1; l2 <= 4; ++l2) {
                for (int i3 = l1; i3 <= i2; ++i3) {
                    c_1514_x blockpos = p_241855_4_.add(k2, l2, i3);
                    Material material = p_241855_1_.getBlockState(blockpos).R_4764_Y();
                    boolean flag = material.J_1907_R();
                    if (l2 == -1 && !flag) {
                        return false;
                    }
                    if (l2 == 4 && !flag) {
                        return false;
                    }
                    if (k2 != k && k2 != l && i3 != l1 && i3 != i2 || l2 != 0 || !p_241855_1_.u_1723_Y(blockpos) || !p_241855_1_.u_1723_Y(blockpos.up())) continue;
                    ++j2;
                }
            }
        }
        if (j2 >= 1 && j2 <= 5) {
            for (int k3 = k; k3 <= l; ++k3) {
                for (int i4 = 3; i4 >= -1; --i4) {
                    for (int k4 = l1; k4 <= i2; ++k4) {
                        c_1514_x blockpos1 = p_241855_4_.add(k3, i4, k4);
                        K_4074_S blockstate = p_241855_1_.getBlockState(blockpos1);
                        if (k3 != k && i4 != -1 && k4 != l1 && k3 != l && i4 != 4 && k4 != i2) {
                            if (blockstate.n_1700_B(a_3742_W.L_1362_X) || blockstate.n_1700_B(a_3742_W.j_306_t)) continue;
                            p_241855_1_.n_1700_B(blockpos1, s_2632_s, 2);
                            continue;
                        }
                        if (blockpos1.getY() >= 0 && !p_241855_1_.getBlockState(blockpos1.down()).R_4764_Y().J_1907_R()) {
                            p_241855_1_.n_1700_B(blockpos1, s_2632_s, 2);
                            continue;
                        }
                        if (!blockstate.R_4764_Y().J_1907_R() || blockstate.n_1700_B(a_3742_W.L_1362_X)) continue;
                        if (i4 == -1 && p_241855_3_.nextInt(4) != 0) {
                            p_241855_1_.n_1700_B(blockpos1, a_3742_W.U_1341_G.multiplayerClientSuggestionProvider(), 2);
                            continue;
                        }
                        p_241855_1_.n_1700_B(blockpos1, a_3742_W.P_4830_p.multiplayerClientSuggestionProvider(), 2);
                    }
                }
            }
            block6: for (int l3 = 0; l3 < 2; ++l3) {
                for (int j4 = 0; j4 < 3; ++j4) {
                    int j5;
                    int i5;
                    int l4 = p_241855_4_.getX() + p_241855_3_.nextInt(j * 2 + 1) - j;
                    c_1514_x blockpos2 = new c_1514_x(l4, i5 = p_241855_4_.getY(), j5 = p_241855_4_.getZ() + p_241855_3_.nextInt(k1 * 2 + 1) - k1);
                    if (!p_241855_1_.u_1723_Y(blockpos2)) continue;
                    int j3 = 0;
                    for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
                        if (!p_241855_1_.getBlockState(blockpos2.offset(direction)).R_4764_Y().J_1907_R()) continue;
                        ++j3;
                    }
                    if (j3 != true) continue;
                    p_241855_1_.n_1700_B(blockpos2, E_3771_B.n_1700_B(p_241855_1_, blockpos2, a_3742_W.L_1362_X.multiplayerClientSuggestionProvider()), 2);
                    V_4572_l.n_1700_B(p_241855_1_, p_241855_3_, blockpos2, o_4810_o.G_564_y);
                    continue block6;
                }
            }
            p_241855_1_.n_1700_B(p_241855_4_, a_3742_W.j_306_t.multiplayerClientSuggestionProvider(), 2);
            i_2154_H tileentity = p_241855_1_.getTileEntity(p_241855_4_);
            if (tileentity instanceof SpawnerBlockEntity) {
                ((SpawnerBlockEntity)tileentity).v_4262_N().n_1700_B(this.n_1700_B(p_241855_3_));
            } else {
                n_1700_B.error("Failed to fetch mob spawner entity at ({}, {}, {})", (Object)p_241855_4_.getX(), (Object)p_241855_4_.getY(), (Object)p_241855_4_.getZ());
            }
            return true;
        }
        return false;
    }

    private t_5_h<?> n_1700_B(Random rand) {
        return j_3341_s.n_1700_B(D_4792_h, rand);
    }
}


