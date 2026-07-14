/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Iterables
 *  com.google.common.collect.LinkedHashMultiset
 *  com.google.common.collect.Multiset
 *  com.google.common.collect.Multisets
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedHashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.ComplexItem;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.BlockGetter;
import lightning.product.F_3620_e;
import lightning.product.H_1748_a;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.U_2912_j;
import lightning.product.Y_1387_d;
import lightning.product.UseOnContext;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_3591_l;
import lightning.product.f_2392_k;
import lightning.product.g_3316_o;
import lightning.product.k_594_Q;
import lightning.product.m_3054_I;
import lightning.product.BlockTags;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.Packet;
import lightning.product.MaterialColor;
import lightning.product.u_530_F;
import lightning.product.x_282_a;
import lightning.product.z_2963_s;

public class G_3165_y
extends ComplexItem {
    public G_3165_y(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    public static Z_1993_T n_1700_B(b_4507_u worldIn, int worldX, int worldZ, byte scale, boolean trackingPosition, boolean unlimitedTracking) {
        Z_1993_T itemstack = new Z_1993_T(Items.K_4518_s);
        G_3165_y.n_1700_B(itemstack, worldIn, worldX, worldZ, scale, trackingPosition, unlimitedTracking, worldIn.g_2268_R());
        return itemstack;
    }

    @Nullable
    public static F_3620_e n_1700_B(Z_1993_T stack, b_4507_u worldIn) {
        return worldIn.n_1700_B(G_3165_y.n_1700_B(G_3165_y.G_564_y(stack)));
    }

    @Nullable
    public static F_3620_e J_1907_R(Z_1993_T stack, b_4507_u worldIn) {
        F_3620_e mapdata = G_3165_y.n_1700_B(stack, worldIn);
        if (mapdata == null && worldIn instanceof e_3591_l) {
            mapdata = G_3165_y.n_1700_B(stack, worldIn, worldIn.k_2293_S().J_1907_R(), worldIn.k_2293_S().G_564_y(), 3, false, false, worldIn.g_2268_R());
        }
        return mapdata;
    }

    public static int G_564_y(Z_1993_T stack) {
        U_2912_j compoundnbt = stack.Q_4569_t();
        return compoundnbt != null && compoundnbt.R_4764_Y("map", 99) ? compoundnbt.w_1484_f("map") : 0;
    }

    private static F_3620_e n_1700_B(Z_1993_T stack, b_4507_u worldIn, int x, int z, int scale, boolean trackingPosition, boolean unlimitedTracking, f_2392_k<b_4507_u> dimensionTypeIn) {
        int i = worldIn.h_1847_R();
        F_3620_e mapdata = new F_3620_e(G_3165_y.n_1700_B(i));
        mapdata.n_1700_B(x, z, scale, trackingPosition, unlimitedTracking, dimensionTypeIn);
        worldIn.n_1700_B(mapdata);
        stack.M_182_A().J_1907_R("map", i);
        return mapdata;
    }

    public static String n_1700_B(int mapId) {
        return "map_" + mapId;
    }

    public void n_1700_B(b_4507_u worldIn, N_4263_v viewer, F_3620_e data) {
        if (worldIn.g_2268_R() == data.R_4764_Y && viewer instanceof a_3913_L) {
            int i = 1 << data.u_1723_Y;
            int j = data.n_1700_B;
            int k = data.J_1907_R;
            int l = u_530_F.R_4764_Y(viewer.O_3598_v() - (double)j) / i + 64;
            int i1 = u_530_F.R_4764_Y(viewer.l_2647_k() - (double)k) / i + 64;
            int j1 = 128 / i;
            if (worldIn.G_624_v().R_4764_Y()) {
                j1 /= 2;
            }
            F_3620_e.n_1700_B mapdata$mapinfo = data.n_1700_B((a_3913_L)viewer);
            ++mapdata$mapinfo.J_1907_R;
            boolean flag = false;
            for (int k1 = l - j1 + 1; k1 < l + j1; ++k1) {
                if ((k1 & 0xF) != (mapdata$mapinfo.J_1907_R & 0xF) && !flag) continue;
                flag = false;
                double d0 = 0.0;
                for (int l1 = i1 - j1 - 1; l1 < i1 + j1; ++l1) {
                    byte b1;
                    byte b0;
                    MaterialColor materialcolor;
                    if (k1 < 0 || l1 < -1 || k1 >= 128 || l1 >= 128) continue;
                    int i2 = k1 - l;
                    int j2 = l1 - i1;
                    boolean flag1 = i2 * i2 + j2 * j2 > (j1 - 2) * (j1 - 2);
                    int k2 = (j / i + k1 - 64) * i;
                    int l2 = (k / i + l1 - 64) * i;
                    LinkedHashMultiset multiset = LinkedHashMultiset.create();
                    H_1748_a chunk = worldIn.M_182_A(new c_1514_x(k2, 0, l2));
                    if (chunk.isEmpty()) continue;
                    Y_1387_d chunkpos = chunk.getPos();
                    int i3 = k2 & 0xF;
                    int j3 = l2 & 0xF;
                    int k3 = 0;
                    double d1 = 0.0;
                    if (worldIn.G_624_v().R_4764_Y()) {
                        int l3 = k2 + l2 * 231871;
                        if (((l3 = l3 * l3 * 31287121 + l3 * 11) >> 20 & 1) == 0) {
                            multiset.add((Object)a_3742_W.s_956_w.multiplayerClientSuggestionProvider().G_564_y(worldIn, c_1514_x.ZERO), 10);
                        } else {
                            multiset.add((Object)a_3742_W.J_1907_R.multiplayerClientSuggestionProvider().G_564_y(worldIn, c_1514_x.ZERO), 100);
                        }
                        d1 = 100.0;
                    } else {
                        c_1514_x.n_1700_B blockpos$mutable1 = new c_1514_x.n_1700_B();
                        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
                        for (int i4 = 0; i4 < i; ++i4) {
                            for (int j4 = 0; j4 < i; ++j4) {
                                K_4074_S blockstate;
                                int k4 = chunk.getTopBlockY(z_2963_s.n_1700_B.J_1907_R, i4 + i3, j4 + j3) + 1;
                                if (k4 <= 1) {
                                    blockstate = a_3742_W.Z_875_P.multiplayerClientSuggestionProvider();
                                } else {
                                    do {
                                        blockpos$mutable1.n_1700_B(chunkpos.J_1907_R() + i4 + i3, --k4, chunkpos.R_4764_Y() + j4 + j3);
                                    } while ((blockstate = chunk.getBlockState(blockpos$mutable1)).G_564_y(worldIn, blockpos$mutable1) == MaterialColor.J_1907_R && k4 > 0);
                                    if (k4 > 0 && !blockstate.P_4830_p().R_4764_Y()) {
                                        K_4074_S blockstate1;
                                        int l4 = k4 - 1;
                                        blockpos$mutable.n_1700_B(blockpos$mutable1);
                                        do {
                                            blockpos$mutable.setY(l4--);
                                            blockstate1 = chunk.getBlockState(blockpos$mutable);
                                            ++k3;
                                        } while (l4 > 0 && !blockstate1.P_4830_p().R_4764_Y());
                                        blockstate = this.n_1700_B(worldIn, blockstate, blockpos$mutable1);
                                    }
                                }
                                data.n_1700_B(worldIn, chunkpos.J_1907_R() + i4 + i3, chunkpos.R_4764_Y() + j4 + j3);
                                d1 += (double)k4 / (double)(i * i);
                                multiset.add((Object)blockstate.G_564_y(worldIn, blockpos$mutable1));
                            }
                        }
                    }
                    k3 /= i * i;
                    double d2 = (d1 - d0) * 4.0 / (double)(i + 4) + ((double)(k1 + l1 & 1) - 0.5) * 0.4;
                    int i5 = 1;
                    if (d2 > 0.6) {
                        i5 = 2;
                    }
                    if (d2 < -0.6) {
                        i5 = 0;
                    }
                    if ((materialcolor = (MaterialColor)Iterables.getFirst((Iterable)Multisets.copyHighestCountFirst((Multiset)multiset), (Object)MaterialColor.J_1907_R)) == MaterialColor.h_1847_R) {
                        d2 = (double)k3 * 0.1 + (double)(k1 + l1 & 1) * 0.2;
                        i5 = 1;
                        if (d2 < 0.5) {
                            i5 = 2;
                        }
                        if (d2 > 0.9) {
                            i5 = 0;
                        }
                    }
                    d0 = d1;
                    if (l1 < 0 || i2 * i2 + j2 * j2 >= j1 * j1 || flag1 && (k1 + l1 & 1) == 0 || (b0 = data.v_4262_N[k1 + l1 * 128]) == (b1 = (byte)(materialcolor.Ping * 4 + i5))) continue;
                    data.v_4262_N[k1 + l1 * 128] = b1;
                    data.n_1700_B(k1, l1);
                    flag = true;
                }
            }
        }
    }

    private K_4074_S n_1700_B(b_4507_u worldIn, K_4074_S state, c_1514_x pos) {
        FluidState fluidstate = state.P_4830_p();
        return !fluidstate.R_4764_Y() && !state.G_564_y((BlockGetter)worldIn, pos, b_257_Y.J_1907_R) ? fluidstate.v_4262_N() : state;
    }

    private static boolean n_1700_B(k_594_Q[] biomes, int p_195954_1_, int p_195954_2_, int p_195954_3_) {
        return biomes[p_195954_2_ * p_195954_1_ + p_195954_3_ * p_195954_1_ * 128 * p_195954_1_].w_1484_f() >= 0.0f;
    }

    public static void n_1700_B(e_3591_l p_226642_0_, Z_1993_T p_226642_1_) {
        F_3620_e mapdata = G_3165_y.J_1907_R(p_226642_1_, p_226642_0_);
        if (mapdata != null && p_226642_0_.g_2268_R() == mapdata.R_4764_Y) {
            int i = 1 << mapdata.u_1723_Y;
            int j = mapdata.n_1700_B;
            int k = mapdata.J_1907_R;
            k_594_Q[] abiome = new k_594_Q[128 * i * 128 * i];
            for (int l = 0; l < 128 * i; ++l) {
                for (int i1 = 0; i1 < 128 * i; ++i1) {
                    abiome[l * 128 * i + i1] = p_226642_0_.P_1922_E(new c_1514_x((j / i - 64) * i + i1, 0, (k / i - 64) * i + l));
                }
            }
            for (int l1 = 0; l1 < 128; ++l1) {
                for (int i2 = 0; i2 < 128; ++i2) {
                    if (l1 <= 0 || i2 <= 0 || l1 >= 127 || i2 >= 127) continue;
                    k_594_Q biome = abiome[l1 * i + i2 * i * 128 * i];
                    int j1 = 8;
                    if (G_3165_y.n_1700_B(abiome, i, l1 - 1, i2 - 1)) {
                        --j1;
                    }
                    if (G_3165_y.n_1700_B(abiome, i, l1 - 1, i2 + 1)) {
                        --j1;
                    }
                    if (G_3165_y.n_1700_B(abiome, i, l1 - 1, i2)) {
                        --j1;
                    }
                    if (G_3165_y.n_1700_B(abiome, i, l1 + 1, i2 - 1)) {
                        --j1;
                    }
                    if (G_3165_y.n_1700_B(abiome, i, l1 + 1, i2 + 1)) {
                        --j1;
                    }
                    if (G_3165_y.n_1700_B(abiome, i, l1 + 1, i2)) {
                        --j1;
                    }
                    if (G_3165_y.n_1700_B(abiome, i, l1, i2 - 1)) {
                        --j1;
                    }
                    if (G_3165_y.n_1700_B(abiome, i, l1, i2 + 1)) {
                        --j1;
                    }
                    int k1 = 3;
                    MaterialColor materialcolor = MaterialColor.J_1907_R;
                    if (biome.w_1484_f() < 0.0f) {
                        materialcolor = MaterialColor.t_1786_h;
                        if (j1 > 7 && i2 % 2 == 0) {
                            k1 = (l1 + (int)(u_530_F.n_1700_B((float)i2 + 0.0f) * 7.0f)) / 8 % 5;
                            if (k1 == 3) {
                                k1 = 1;
                            } else if (k1 == 4) {
                                k1 = 0;
                            }
                        } else if (j1 > 7) {
                            materialcolor = MaterialColor.J_1907_R;
                        } else if (j1 > 5) {
                            k1 = 1;
                        } else if (j1 > 3) {
                            k1 = 0;
                        } else if (j1 > 1) {
                            k1 = 0;
                        }
                    } else if (j1 > 0) {
                        materialcolor = MaterialColor.H_2857_Y;
                        k1 = j1 > 3 ? 1 : 3;
                    }
                    if (materialcolor == MaterialColor.J_1907_R) continue;
                    mapdata.v_4262_N[l1 + i2 * 128] = (byte)(materialcolor.Ping * 4 + k1);
                    mapdata.n_1700_B(l1, i2);
                }
            }
        }
    }

    @Override
    public void n_1700_B(Z_1993_T stack, b_4507_u worldIn, N_4263_v entityIn, int itemSlot, boolean isSelected) {
        F_3620_e mapdata;
        if (!worldIn.Y_259_p && (mapdata = G_3165_y.J_1907_R(stack, worldIn)) != null) {
            if (entityIn instanceof a_3913_L) {
                a_3913_L playerentity = (a_3913_L)entityIn;
                mapdata.n_1700_B(playerentity, stack);
            }
            if (!mapdata.w_1484_f && (isSelected || entityIn instanceof a_3913_L && ((a_3913_L)entityIn).S_4035_N() == stack)) {
                this.n_1700_B(worldIn, entityIn, mapdata);
            }
        }
    }

    @Override
    @Nullable
    public Packet<?> n_1700_B(Z_1993_T stack, b_4507_u worldIn, a_3913_L player) {
        return G_3165_y.J_1907_R(stack, worldIn).n_1700_B(stack, worldIn, player);
    }

    @Override
    public void J_1907_R(Z_1993_T stack, b_4507_u worldIn, a_3913_L playerIn) {
        U_2912_j compoundnbt = stack.Q_4569_t();
        if (compoundnbt != null && compoundnbt.R_4764_Y("map_scale_direction", 99)) {
            G_3165_y.n_1700_B(stack, worldIn, compoundnbt.w_1484_f("map_scale_direction"));
            compoundnbt.multiplayerClientSuggestionProvider("map_scale_direction");
        } else if (compoundnbt != null && compoundnbt.R_4764_Y("map_to_lock", 1) && compoundnbt.t_1786_h("map_to_lock")) {
            G_3165_y.n_1700_B(worldIn, stack);
            compoundnbt.multiplayerClientSuggestionProvider("map_to_lock");
        }
    }

    protected static void n_1700_B(Z_1993_T p_185063_0_, b_4507_u p_185063_1_, int p_185063_2_) {
        F_3620_e mapdata = G_3165_y.J_1907_R(p_185063_0_, p_185063_1_);
        if (mapdata != null) {
            G_3165_y.n_1700_B(p_185063_0_, p_185063_1_, mapdata.n_1700_B, mapdata.J_1907_R, u_530_F.n_1700_B(mapdata.u_1723_Y + p_185063_2_, 0, 4), mapdata.G_564_y, mapdata.P_1922_E, mapdata.R_4764_Y);
        }
    }

    public static void n_1700_B(b_4507_u worldIn, Z_1993_T stack) {
        F_3620_e mapdata = G_3165_y.J_1907_R(stack, worldIn);
        if (mapdata != null) {
            F_3620_e mapdata1 = G_3165_y.n_1700_B(stack, worldIn, 0, 0, mapdata.u_1723_Y, mapdata.G_564_y, mapdata.P_1922_E, mapdata.R_4764_Y);
            mapdata1.n_1700_B(mapdata);
        }
    }

    @Override
    public void n_1700_B(Z_1993_T stack, @Nullable b_4507_u worldIn, List<x_282_a> tooltip, g_3316_o flagIn) {
        F_3620_e mapdata;
        F_3620_e f_3620_e = mapdata = worldIn == null ? null : G_3165_y.J_1907_R(stack, worldIn);
        if (mapdata != null && mapdata.w_1484_f) {
            tooltip.add(new F_2904_S("filled_map.locked", G_3165_y.G_564_y(stack)).n_1700_B(D_4024_W.w_1484_f));
        }
        if (flagIn.n_1700_B()) {
            if (mapdata != null) {
                tooltip.add(new F_2904_S("filled_map.id", G_3165_y.G_564_y(stack)).n_1700_B(D_4024_W.w_1484_f));
                tooltip.add(new F_2904_S("filled_map.scale", 1 << mapdata.u_1723_Y).n_1700_B(D_4024_W.w_1484_f));
                tooltip.add(new F_2904_S("filled_map.level", mapdata.u_1723_Y, 4).n_1700_B(D_4024_W.w_1484_f));
            } else {
                tooltip.add(new F_2904_S("filled_map.unknown").n_1700_B(D_4024_W.w_1484_f));
            }
        }
    }

    public static int v_4262_N(Z_1993_T stack) {
        U_2912_j compoundnbt = stack.J_1907_R("display");
        if (compoundnbt != null && compoundnbt.R_4764_Y("MapColor", 99)) {
            int i = compoundnbt.w_1484_f("MapColor");
            return 0xFF000000 | i & 0xFFFFFF;
        }
        return -12173266;
    }

    @Override
    public m_3054_I n_1700_B(UseOnContext context) {
        K_4074_S blockstate = context.getWorld().getBlockState(context.getPos());
        if (blockstate.n_1700_B(BlockTags.H_2857_Y)) {
            if (!context.getWorld().Y_259_p) {
                F_3620_e mapdata = G_3165_y.J_1907_R(context.getItem(), context.getWorld());
                mapdata.n_1700_B(context.getWorld(), context.getPos());
            }
            return m_3054_I.n_1700_B(context.getWorld().Y_259_p);
        }
        return super.n_1700_B(context);
    }
}


