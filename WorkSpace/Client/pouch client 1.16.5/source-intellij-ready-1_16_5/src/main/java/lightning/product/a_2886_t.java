/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.D_908_R;
import lightning.product.BitSetDiscreteVoxelShape;
import lightning.product.G_4961_S;
import lightning.product.I_4817_s;
import lightning.product.SharedConstants;
import lightning.product.Clearable;
import lightning.product.K_4074_S;
import lightning.product.DiscreteVoxelShape;
import lightning.product.BoundingBox;
import lightning.product.N_4263_v;
import lightning.product.Painting;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.V_4572_l;
import lightning.product.W_2163_m;
import lightning.product.StructureProcessor;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.ServerLevelAccessor;
import lightning.product.e_2866_D;
import lightning.product.i_2154_H;
import lightning.product.n_3832_I;
import lightning.product.q_2896_o;
import lightning.product.q_4099_E;
import lightning.product.LiquidBlockContainer;
import lightning.product.IntTag;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;
import lightning.product.w_1748_S;
import lightning.product.w_424_u;

public class a_2886_t {
    private final List<G_564_y> n_1700_B = Lists.newArrayList();
    private final List<R_4764_Y> J_1907_R = Lists.newArrayList();
    private c_1514_x R_4764_Y = c_1514_x.ZERO;
    private String G_564_y = "?";

    public c_1514_x n_1700_B() {
        return this.R_4764_Y;
    }

    public void n_1700_B(String authorIn) {
        this.G_564_y = authorIn;
    }

    public String J_1907_R() {
        return this.G_564_y;
    }

    public void n_1700_B(b_4507_u worldIn, c_1514_x startPos, c_1514_x size, boolean takeEntities, @Nullable T_2915_h toIgnore) {
        if (size.getX() >= 1 && size.getY() >= 1 && size.getZ() >= 1) {
            c_1514_x blockpos = startPos.add(size).add(-1, -1, -1);
            ArrayList list = Lists.newArrayList();
            ArrayList list1 = Lists.newArrayList();
            ArrayList list2 = Lists.newArrayList();
            c_1514_x blockpos1 = new c_1514_x(Math.min(startPos.getX(), blockpos.getX()), Math.min(startPos.getY(), blockpos.getY()), Math.min(startPos.getZ(), blockpos.getZ()));
            c_1514_x blockpos2 = new c_1514_x(Math.max(startPos.getX(), blockpos.getX()), Math.max(startPos.getY(), blockpos.getY()), Math.max(startPos.getZ(), blockpos.getZ()));
            this.R_4764_Y = size;
            for (c_1514_x blockpos3 : c_1514_x.getAllInBoxMutable(blockpos1, blockpos2)) {
                J_1907_R template$blockinfo;
                c_1514_x blockpos4 = blockpos3.subtract(blockpos1);
                K_4074_S blockstate = worldIn.getBlockState(blockpos3);
                if (toIgnore != null && toIgnore == blockstate.J_1907_R()) continue;
                i_2154_H tileentity = worldIn.getTileEntity(blockpos3);
                if (tileentity != null) {
                    U_2912_j compoundnbt = tileentity.n_1700_B(new U_2912_j());
                    compoundnbt.multiplayerClientSuggestionProvider("x");
                    compoundnbt.multiplayerClientSuggestionProvider("y");
                    compoundnbt.multiplayerClientSuggestionProvider("z");
                    template$blockinfo = new J_1907_R(blockpos4, blockstate, compoundnbt.v_4262_N());
                } else {
                    template$blockinfo = new J_1907_R(blockpos4, blockstate, null);
                }
                a_2886_t.n_1700_B(template$blockinfo, list, list1, list2);
            }
            List<J_1907_R> list3 = a_2886_t.n_1700_B(list, list1, list2);
            this.n_1700_B.clear();
            this.n_1700_B.add(new G_564_y(list3));
            if (takeEntities) {
                this.n_1700_B(worldIn, blockpos1, blockpos2.add(1, 1, 1));
            } else {
                this.J_1907_R.clear();
            }
        }
    }

    private static void n_1700_B(J_1907_R p_237149_0_, List<J_1907_R> p_237149_1_, List<J_1907_R> p_237149_2_, List<J_1907_R> p_237149_3_) {
        if (p_237149_0_.R_4764_Y != null) {
            p_237149_2_.add(p_237149_0_);
        } else if (!p_237149_0_.J_1907_R.J_1907_R().w_1457_N() && p_237149_0_.J_1907_R.multiplayerClientSuggestionProvider(G_4961_S.n_1700_B, c_1514_x.ZERO)) {
            p_237149_1_.add(p_237149_0_);
        } else {
            p_237149_3_.add(p_237149_0_);
        }
    }

    private static List<J_1907_R> n_1700_B(List<J_1907_R> p_237151_0_, List<J_1907_R> p_237151_1_, List<J_1907_R> p_237151_2_) {
        Comparator<J_1907_R> comparator = Comparator.comparingInt(p_237154_0_ -> p_237154_0_.n_1700_B.getY()).thenComparingInt(p_237153_0_ -> p_237153_0_.n_1700_B.getX()).thenComparingInt(p_237148_0_ -> p_237148_0_.n_1700_B.getZ());
        p_237151_0_.sort(comparator);
        p_237151_2_.sort(comparator);
        p_237151_1_.sort(comparator);
        ArrayList list = Lists.newArrayList();
        list.addAll(p_237151_0_);
        list.addAll(p_237151_2_);
        list.addAll(p_237151_1_);
        return list;
    }

    private void n_1700_B(b_4507_u worldIn, c_1514_x startPos, c_1514_x endPos) {
        List<N_4263_v> list = worldIn.n_1700_B(N_4263_v.class, new I_4817_s(startPos, endPos), (? super T p_237142_0_) -> !(p_237142_0_ instanceof a_3913_L));
        this.J_1907_R.clear();
        for (N_4263_v entity : list) {
            e_2866_D vector3d = new e_2866_D(entity.O_3598_v() - (double)startPos.getX(), entity.X_2960_b() - (double)startPos.getY(), entity.l_2647_k() - (double)startPos.getZ());
            U_2912_j compoundnbt = new U_2912_j();
            entity.G_564_y(compoundnbt);
            c_1514_x blockpos = entity instanceof Painting ? ((Painting)entity).u_2550_I().subtract(startPos) : new c_1514_x(vector3d);
            this.J_1907_R.add(new R_4764_Y(vector3d, blockpos, compoundnbt.v_4262_N()));
        }
    }

    public List<J_1907_R> n_1700_B(c_1514_x p_215381_1_, w_1748_S p_215381_2_, T_2915_h p_215381_3_) {
        return this.n_1700_B(p_215381_1_, p_215381_2_, p_215381_3_, true);
    }

    public List<J_1907_R> n_1700_B(c_1514_x p_215386_1_, w_1748_S p_215386_2_, T_2915_h p_215386_3_, boolean p_215386_4_) {
        ArrayList list = Lists.newArrayList();
        BoundingBox mutableboundingbox = p_215386_2_.v_4262_N();
        if (this.n_1700_B.isEmpty()) {
            return Collections.emptyList();
        }
        for (J_1907_R template$blockinfo : p_215386_2_.n_1700_B(this.n_1700_B, p_215386_1_).n_1700_B(p_215386_3_)) {
            c_1514_x blockpos;
            c_1514_x c_1514_x2 = blockpos = p_215386_4_ ? a_2886_t.n_1700_B(p_215386_2_, template$blockinfo.n_1700_B).add(p_215386_1_) : template$blockinfo.n_1700_B;
            if (mutableboundingbox != null && !mutableboundingbox.J_1907_R(blockpos)) continue;
            list.add(new J_1907_R(blockpos, template$blockinfo.J_1907_R.n_1700_B(p_215386_2_.G_564_y()), template$blockinfo.R_4764_Y));
        }
        return list;
    }

    public c_1514_x n_1700_B(w_1748_S placementIn, c_1514_x p_186262_2_, w_1748_S p_186262_3_, c_1514_x p_186262_4_) {
        c_1514_x blockpos = a_2886_t.n_1700_B(placementIn, p_186262_2_);
        c_1514_x blockpos1 = a_2886_t.n_1700_B(p_186262_3_, p_186262_4_);
        return blockpos.subtract(blockpos1);
    }

    public static c_1514_x n_1700_B(w_1748_S placementIn, c_1514_x pos) {
        return a_2886_t.n_1700_B(pos, placementIn.R_4764_Y(), placementIn.G_564_y(), placementIn.P_1922_E());
    }

    public void n_1700_B(ServerLevelAccessor p_237144_1_, c_1514_x p_237144_2_, w_1748_S p_237144_3_, Random p_237144_4_) {
        p_237144_3_.s_956_w();
        this.J_1907_R(p_237144_1_, p_237144_2_, p_237144_3_, p_237144_4_);
    }

    public void J_1907_R(ServerLevelAccessor p_237152_1_, c_1514_x p_237152_2_, w_1748_S p_237152_3_, Random p_237152_4_) {
        this.n_1700_B(p_237152_1_, p_237152_2_, p_237152_2_, p_237152_3_, p_237152_4_, 2);
    }

    public boolean n_1700_B(ServerLevelAccessor p_237146_1_, c_1514_x p_237146_2_, c_1514_x p_237146_3_, w_1748_S p_237146_4_, Random p_237146_5_, int p_237146_6_) {
        if (this.n_1700_B.isEmpty()) {
            return false;
        }
        List<J_1907_R> list = p_237146_4_.n_1700_B(this.n_1700_B, p_237146_2_).n_1700_B();
        if (!(list.isEmpty() && (p_237146_4_.u_1723_Y() || this.J_1907_R.isEmpty()) || this.R_4764_Y.getX() < 1 || this.R_4764_Y.getY() < 1 || this.R_4764_Y.getZ() < 1)) {
            BoundingBox mutableboundingbox = p_237146_4_.v_4262_N();
            ArrayList list1 = Lists.newArrayListWithCapacity((int)(p_237146_4_.u_2550_I() ? list.size() : 0));
            ArrayList list2 = Lists.newArrayListWithCapacity((int)list.size());
            int i = Integer.MAX_VALUE;
            int j = Integer.MAX_VALUE;
            int k = Integer.MAX_VALUE;
            int l = Integer.MIN_VALUE;
            int i1 = Integer.MIN_VALUE;
            int j1 = Integer.MIN_VALUE;
            for (J_1907_R template$blockinfo : a_2886_t.n_1700_B(p_237146_1_, p_237146_2_, p_237146_3_, p_237146_4_, list)) {
                i_2154_H tileentity1;
                c_1514_x blockpos = template$blockinfo.n_1700_B;
                if (mutableboundingbox != null && !mutableboundingbox.J_1907_R(blockpos)) continue;
                FluidState fluidstate = p_237146_4_.u_2550_I() ? p_237146_1_.getFluidState(blockpos) : null;
                K_4074_S blockstate = template$blockinfo.J_1907_R.n_1700_B(p_237146_4_.R_4764_Y()).n_1700_B(p_237146_4_.G_564_y());
                if (template$blockinfo.R_4764_Y != null) {
                    i_2154_H tileentity = p_237146_1_.getTileEntity(blockpos);
                    Clearable.n_1700_B(tileentity);
                    p_237146_1_.n_1700_B(blockpos, a_3742_W.N_4890_q.multiplayerClientSuggestionProvider(), 20);
                }
                if (!p_237146_1_.n_1700_B(blockpos, blockstate, p_237146_6_)) continue;
                i = Math.min(i, blockpos.getX());
                j = Math.min(j, blockpos.getY());
                k = Math.min(k, blockpos.getZ());
                l = Math.max(l, blockpos.getX());
                i1 = Math.max(i1, blockpos.getY());
                j1 = Math.max(j1, blockpos.getZ());
                list2.add(Pair.of((Object)blockpos, (Object)template$blockinfo.R_4764_Y));
                if (template$blockinfo.R_4764_Y != null && (tileentity1 = p_237146_1_.getTileEntity(blockpos)) != null) {
                    template$blockinfo.R_4764_Y.J_1907_R("x", blockpos.getX());
                    template$blockinfo.R_4764_Y.J_1907_R("y", blockpos.getY());
                    template$blockinfo.R_4764_Y.J_1907_R("z", blockpos.getZ());
                    if (tileentity1 instanceof V_4572_l) {
                        template$blockinfo.R_4764_Y.n_1700_B("LootTableSeed", p_237146_5_.nextLong());
                    }
                    tileentity1.n_1700_B(template$blockinfo.J_1907_R, template$blockinfo.R_4764_Y);
                    tileentity1.J_1907_R(p_237146_4_.R_4764_Y());
                    tileentity1.J_1907_R(p_237146_4_.G_564_y());
                }
                if (fluidstate == null || !(blockstate.J_1907_R() instanceof LiquidBlockContainer)) continue;
                ((LiquidBlockContainer)((Object)blockstate.J_1907_R())).n_1700_B(p_237146_1_, blockpos, blockstate, fluidstate);
                if (fluidstate.J_1907_R()) continue;
                list1.add(blockpos);
            }
            boolean flag = true;
            b_257_Y[] adirection = new b_257_Y[]{b_257_Y.J_1907_R, b_257_Y.R_4764_Y, b_257_Y.u_1723_Y, b_257_Y.G_564_y, b_257_Y.P_1922_E};
            while (flag && !list1.isEmpty()) {
                flag = false;
                Iterator iterator = list1.iterator();
                while (iterator.hasNext()) {
                    K_4074_S blockstate2;
                    T_2915_h block;
                    c_1514_x blockpos2;
                    c_1514_x blockpos3 = blockpos2 = (c_1514_x)iterator.next();
                    FluidState fluidstate2 = p_237146_1_.getFluidState(blockpos2);
                    for (int k1 = 0; k1 < adirection.length && !fluidstate2.J_1907_R(); ++k1) {
                        c_1514_x blockpos1 = blockpos3.offset(adirection[k1]);
                        FluidState fluidstate1 = p_237146_1_.getFluidState(blockpos1);
                        if (!(fluidstate1.n_1700_B(p_237146_1_, blockpos1) > fluidstate2.n_1700_B(p_237146_1_, blockpos3)) && (!fluidstate1.J_1907_R() || fluidstate2.J_1907_R())) continue;
                        fluidstate2 = fluidstate1;
                        blockpos3 = blockpos1;
                    }
                    if (!fluidstate2.J_1907_R() || !((block = (blockstate2 = p_237146_1_.getBlockState(blockpos2)).J_1907_R()) instanceof LiquidBlockContainer)) continue;
                    ((LiquidBlockContainer)((Object)block)).n_1700_B(p_237146_1_, blockpos2, blockstate2, fluidstate2);
                    flag = true;
                    iterator.remove();
                }
            }
            if (i <= l) {
                if (!p_237146_4_.w_1484_f()) {
                    BitSetDiscreteVoxelShape voxelshapepart = new BitSetDiscreteVoxelShape(l - i + 1, i1 - j + 1, j1 - k + 1);
                    int l1 = i;
                    int i2 = j;
                    int j2 = k;
                    for (Pair pair1 : list2) {
                        c_1514_x blockpos5 = (c_1514_x)pair1.getFirst();
                        ((DiscreteVoxelShape)voxelshapepart).n_1700_B(blockpos5.getX() - l1, blockpos5.getY() - i2, blockpos5.getZ() - j2, true, true);
                    }
                    a_2886_t.n_1700_B(p_237146_1_, p_237146_6_, voxelshapepart, l1, i2, j2);
                }
                for (Pair pair : list2) {
                    i_2154_H tileentity2;
                    c_1514_x blockpos4 = (c_1514_x)pair.getFirst();
                    if (!p_237146_4_.w_1484_f()) {
                        K_4074_S blockstate3;
                        K_4074_S blockstate1 = p_237146_1_.getBlockState(blockpos4);
                        if (blockstate1 != (blockstate3 = T_2915_h.J_1907_R(blockstate1, p_237146_1_, blockpos4))) {
                            p_237146_1_.n_1700_B(blockpos4, blockstate3, p_237146_6_ & 0xFFFFFFFE | 0x10);
                        }
                        p_237146_1_.n_1700_B(blockpos4, blockstate3.J_1907_R());
                    }
                    if (pair.getSecond() == null || (tileentity2 = p_237146_1_.getTileEntity(blockpos4)) == null) continue;
                    tileentity2.J_1907_R();
                }
            }
            if (!p_237146_4_.u_1723_Y()) {
                this.n_1700_B(p_237146_1_, p_237146_2_, p_237146_4_.R_4764_Y(), p_237146_4_.G_564_y(), p_237146_4_.P_1922_E(), mutableboundingbox, p_237146_4_.M_588_G());
            }
            return true;
        }
        return false;
    }

    public static void n_1700_B(LevelAccessor worldIn, int p_222857_1_, DiscreteVoxelShape voxelShapePartIn, int xIn, int yIn, int zIn) {
        voxelShapePartIn.n_1700_B((b_257_Y p_237141_5_, int p_237141_6_, int p_237141_7_, int p_237141_8_) -> {
            K_4074_S blockstate3;
            K_4074_S blockstate1;
            K_4074_S blockstate2;
            c_1514_x blockpos = new c_1514_x(xIn + p_237141_6_, yIn + p_237141_7_, zIn + p_237141_8_);
            c_1514_x blockpos1 = blockpos.offset(p_237141_5_);
            K_4074_S blockstate = worldIn.getBlockState(blockpos);
            if (blockstate != (blockstate2 = blockstate.n_1700_B(p_237141_5_, blockstate1 = worldIn.getBlockState(blockpos1), worldIn, blockpos, blockpos1))) {
                worldIn.n_1700_B(blockpos, blockstate2, p_222857_1_ & 0xFFFFFFFE);
            }
            if (blockstate1 != (blockstate3 = blockstate1.n_1700_B(p_237141_5_.u_1723_Y(), blockstate2, worldIn, blockpos1, blockpos))) {
                worldIn.n_1700_B(blockpos1, blockstate3, p_222857_1_ & 0xFFFFFFFE);
            }
        });
    }

    public static List<J_1907_R> n_1700_B(LevelAccessor p_237145_0_, c_1514_x p_237145_1_, c_1514_x p_237145_2_, w_1748_S p_237145_3_, List<J_1907_R> p_237145_4_) {
        ArrayList list = Lists.newArrayList();
        for (J_1907_R template$blockinfo : p_237145_4_) {
            c_1514_x blockpos = a_2886_t.n_1700_B(p_237145_3_, template$blockinfo.n_1700_B).add(p_237145_1_);
            J_1907_R template$blockinfo1 = new J_1907_R(blockpos, template$blockinfo.J_1907_R, template$blockinfo.R_4764_Y != null ? template$blockinfo.R_4764_Y.v_4262_N() : null);
            Iterator<StructureProcessor> iterator = p_237145_3_.t_148_a().iterator();
            while (template$blockinfo1 != null && iterator.hasNext()) {
                template$blockinfo1 = iterator.next().n_1700_B(p_237145_0_, p_237145_1_, p_237145_2_, template$blockinfo, template$blockinfo1, p_237145_3_);
            }
            if (template$blockinfo1 == null) continue;
            list.add(template$blockinfo1);
        }
        return list;
    }

    private void n_1700_B(ServerLevelAccessor p_237143_1_, c_1514_x p_237143_2_, q_4099_E p_237143_3_, W_2163_m p_237143_4_, c_1514_x p_237143_5_, @Nullable BoundingBox p_237143_6_, boolean p_237143_7_) {
        for (R_4764_Y template$entityinfo : this.J_1907_R) {
            c_1514_x blockpos = a_2886_t.n_1700_B(template$entityinfo.J_1907_R, p_237143_3_, p_237143_4_, p_237143_5_).add(p_237143_2_);
            if (p_237143_6_ != null && !p_237143_6_.J_1907_R(blockpos)) continue;
            U_2912_j compoundnbt = template$entityinfo.R_4764_Y.v_4262_N();
            e_2866_D vector3d = a_2886_t.n_1700_B(template$entityinfo.n_1700_B, p_237143_3_, p_237143_4_, p_237143_5_);
            e_2866_D vector3d1 = vector3d.J_1907_R(p_237143_2_.getX(), p_237143_2_.getY(), p_237143_2_.getZ());
            q_2896_o listnbt = new q_2896_o();
            listnbt.add(D_908_R.n_1700_B(vector3d1.J_1907_R));
            listnbt.add(D_908_R.n_1700_B(vector3d1.R_4764_Y));
            listnbt.add(D_908_R.n_1700_B(vector3d1.G_564_y));
            compoundnbt.n_1700_B("Pos", listnbt);
            compoundnbt.multiplayerClientSuggestionProvider("UUID");
            a_2886_t.n_1700_B(p_237143_1_, compoundnbt).ifPresent(p_242927_6_ -> {
                float f = p_242927_6_.n_1700_B(p_237143_3_);
                p_242927_6_.J_1907_R(vector3d1.J_1907_R, vector3d1.R_4764_Y, vector3d1.G_564_y, f += p_242927_6_.p_178_J - p_242927_6_.n_1700_B(p_237143_4_), p_242927_6_.f_4016_n);
                if (p_237143_7_ && p_242927_6_ instanceof Z_530_i) {
                    ((Z_530_i)p_242927_6_).n_1700_B(p_237143_1_, p_237143_1_.J_1907_R(new c_1514_x(vector3d1)), a_3160_D.G_564_y, (V_3157_k)null, compoundnbt);
                }
                p_237143_1_.n_1700_B((N_4263_v)p_242927_6_);
            });
        }
    }

    private static Optional<N_4263_v> n_1700_B(ServerLevelAccessor worldIn, U_2912_j nbt) {
        try {
            return t_5_h.n_1700_B(nbt, (b_4507_u)worldIn.J_1907_R());
        }
        catch (Exception exception) {
            return Optional.empty();
        }
    }

    public c_1514_x n_1700_B(W_2163_m rotationIn) {
        switch (rotationIn) {
            case G_564_y: 
            case J_1907_R: {
                return new c_1514_x(this.R_4764_Y.getZ(), this.R_4764_Y.getY(), this.R_4764_Y.getX());
            }
        }
        return this.R_4764_Y;
    }

    public static c_1514_x n_1700_B(c_1514_x targetPos, q_4099_E mirrorIn, W_2163_m rotationIn, c_1514_x offset) {
        int i = targetPos.getX();
        int j = targetPos.getY();
        int k = targetPos.getZ();
        boolean flag = true;
        switch (mirrorIn) {
            case J_1907_R: {
                k = -k;
                break;
            }
            case R_4764_Y: {
                i = -i;
                break;
            }
            default: {
                flag = false;
            }
        }
        int l = offset.getX();
        int i1 = offset.getZ();
        switch (rotationIn) {
            case G_564_y: {
                return new c_1514_x(l - i1 + k, j, l + i1 - i);
            }
            case J_1907_R: {
                return new c_1514_x(l + i1 - k, j, i1 - l + i);
            }
            case R_4764_Y: {
                return new c_1514_x(l + l - i, j, i1 + i1 - k);
            }
        }
        return flag ? new c_1514_x(i, j, k) : targetPos;
    }

    public static e_2866_D n_1700_B(e_2866_D target, q_4099_E mirrorIn, W_2163_m rotationIn, c_1514_x centerOffset) {
        double d0 = target.J_1907_R;
        double d1 = target.R_4764_Y;
        double d2 = target.G_564_y;
        boolean flag = true;
        switch (mirrorIn) {
            case J_1907_R: {
                d2 = 1.0 - d2;
                break;
            }
            case R_4764_Y: {
                d0 = 1.0 - d0;
                break;
            }
            default: {
                flag = false;
            }
        }
        int i = centerOffset.getX();
        int j = centerOffset.getZ();
        switch (rotationIn) {
            case G_564_y: {
                return new e_2866_D((double)(i - j) + d2, d1, (double)(i + j + 1) - d0);
            }
            case J_1907_R: {
                return new e_2866_D((double)(i + j + 1) - d2, d1, (double)(j - i) + d0);
            }
            case R_4764_Y: {
                return new e_2866_D((double)(i + i + 1) - d0, d1, (double)(j + j + 1) - d2);
            }
        }
        return flag ? new e_2866_D(d0, d1, d2) : target;
    }

    public c_1514_x n_1700_B(c_1514_x p_189961_1_, q_4099_E p_189961_2_, W_2163_m p_189961_3_) {
        return a_2886_t.n_1700_B(p_189961_1_, p_189961_2_, p_189961_3_, this.n_1700_B().getX(), this.n_1700_B().getZ());
    }

    public static c_1514_x n_1700_B(c_1514_x p_191157_0_, q_4099_E p_191157_1_, W_2163_m p_191157_2_, int p_191157_3_, int p_191157_4_) {
        int i = p_191157_1_ == q_4099_E.R_4764_Y ? --p_191157_3_ : 0;
        int j = p_191157_1_ == q_4099_E.J_1907_R ? --p_191157_4_ : 0;
        c_1514_x blockpos = p_191157_0_;
        switch (p_191157_2_) {
            case G_564_y: {
                blockpos = p_191157_0_.add(j, 0, p_191157_3_ - i);
                break;
            }
            case J_1907_R: {
                blockpos = p_191157_0_.add(p_191157_4_ - j, 0, i);
                break;
            }
            case R_4764_Y: {
                blockpos = p_191157_0_.add(p_191157_3_ - i, 0, p_191157_4_ - j);
                break;
            }
            case n_1700_B: {
                blockpos = p_191157_0_.add(i, 0, j);
            }
        }
        return blockpos;
    }

    public BoundingBox J_1907_R(w_1748_S p_215388_1_, c_1514_x p_215388_2_) {
        return this.n_1700_B(p_215388_2_, p_215388_1_.G_564_y(), p_215388_1_.P_1922_E(), p_215388_1_.R_4764_Y());
    }

    public BoundingBox n_1700_B(c_1514_x p_237150_1_, W_2163_m p_237150_2_, c_1514_x p_237150_3_, q_4099_E p_237150_4_) {
        c_1514_x blockpos = this.n_1700_B(p_237150_2_);
        int i = p_237150_3_.getX();
        int j = p_237150_3_.getZ();
        int k = blockpos.getX() - 1;
        int l = blockpos.getY() - 1;
        int i1 = blockpos.getZ() - 1;
        BoundingBox mutableboundingbox = new BoundingBox(0, 0, 0, 0, 0, 0);
        switch (p_237150_2_) {
            case G_564_y: {
                mutableboundingbox = new BoundingBox(i - j, 0, i + j - i1, i - j + k, l, i + j);
                break;
            }
            case J_1907_R: {
                mutableboundingbox = new BoundingBox(i + j - k, 0, j - i, i + j, l, j - i + i1);
                break;
            }
            case R_4764_Y: {
                mutableboundingbox = new BoundingBox(i + i - k, 0, j + j - i1, i + i, l, j + j);
                break;
            }
            case n_1700_B: {
                mutableboundingbox = new BoundingBox(0, 0, 0, k, l, i1);
            }
        }
        switch (p_237150_4_) {
            case J_1907_R: {
                this.n_1700_B(p_237150_2_, i1, k, mutableboundingbox, b_257_Y.R_4764_Y, b_257_Y.G_564_y);
                break;
            }
            case R_4764_Y: {
                this.n_1700_B(p_237150_2_, k, i1, mutableboundingbox, b_257_Y.P_1922_E, b_257_Y.u_1723_Y);
            }
        }
        mutableboundingbox.n_1700_B(p_237150_1_.getX(), p_237150_1_.getY(), p_237150_1_.getZ());
        return mutableboundingbox;
    }

    private void n_1700_B(W_2163_m rotationIn, int offsetFront, int p_215385_3_, BoundingBox p_215385_4_, b_257_Y p_215385_5_, b_257_Y p_215385_6_) {
        c_1514_x blockpos = c_1514_x.ZERO;
        blockpos = rotationIn != W_2163_m.J_1907_R && rotationIn != W_2163_m.G_564_y ? (rotationIn == W_2163_m.R_4764_Y ? blockpos.offset(p_215385_6_, offsetFront) : blockpos.offset(p_215385_5_, offsetFront)) : blockpos.offset(rotationIn.n_1700_B(p_215385_5_), p_215385_3_);
        p_215385_4_.n_1700_B(blockpos.getX(), 0, blockpos.getZ());
    }

    public U_2912_j n_1700_B(U_2912_j nbt) {
        if (this.n_1700_B.isEmpty()) {
            nbt.n_1700_B("blocks", new q_2896_o());
            nbt.n_1700_B("palette", new q_2896_o());
        } else {
            ArrayList list = Lists.newArrayList();
            n_1700_B template$basicpalette = new n_1700_B();
            list.add(template$basicpalette);
            for (int i = 1; i < this.n_1700_B.size(); ++i) {
                list.add(new n_1700_B());
            }
            q_2896_o listnbt1 = new q_2896_o();
            List<J_1907_R> list1 = this.n_1700_B.get(0).n_1700_B();
            for (int j = 0; j < list1.size(); ++j) {
                J_1907_R template$blockinfo = list1.get(j);
                U_2912_j compoundnbt = new U_2912_j();
                compoundnbt.n_1700_B("pos", this.n_1700_B(template$blockinfo.n_1700_B.getX(), template$blockinfo.n_1700_B.getY(), template$blockinfo.n_1700_B.getZ()));
                int k = template$basicpalette.n_1700_B(template$blockinfo.J_1907_R);
                compoundnbt.J_1907_R("state", k);
                if (template$blockinfo.R_4764_Y != null) {
                    compoundnbt.n_1700_B("nbt", template$blockinfo.R_4764_Y);
                }
                listnbt1.add(compoundnbt);
                for (int l = 1; l < this.n_1700_B.size(); ++l) {
                    n_1700_B template$basicpalette1 = (n_1700_B)list.get(l);
                    template$basicpalette1.n_1700_B(this.n_1700_B.get((int)l).n_1700_B().get((int)j).J_1907_R, k);
                }
            }
            nbt.n_1700_B("blocks", listnbt1);
            if (list.size() == 1) {
                q_2896_o listnbt2 = new q_2896_o();
                for (K_4074_S blockstate : template$basicpalette) {
                    listnbt2.add(n_3832_I.n_1700_B(blockstate));
                }
                nbt.n_1700_B("palette", listnbt2);
            } else {
                q_2896_o listnbt3 = new q_2896_o();
                for (n_1700_B template$basicpalette2 : list) {
                    q_2896_o listnbt4 = new q_2896_o();
                    for (K_4074_S blockstate1 : template$basicpalette2) {
                        listnbt4.add(n_3832_I.n_1700_B(blockstate1));
                    }
                    listnbt3.add(listnbt4);
                }
                nbt.n_1700_B("palettes", listnbt3);
            }
        }
        q_2896_o listnbt = new q_2896_o();
        for (R_4764_Y template$entityinfo : this.J_1907_R) {
            U_2912_j compoundnbt1 = new U_2912_j();
            compoundnbt1.n_1700_B("pos", this.n_1700_B(template$entityinfo.n_1700_B.J_1907_R, template$entityinfo.n_1700_B.R_4764_Y, template$entityinfo.n_1700_B.G_564_y));
            compoundnbt1.n_1700_B("blockPos", this.n_1700_B(template$entityinfo.J_1907_R.getX(), template$entityinfo.J_1907_R.getY(), template$entityinfo.J_1907_R.getZ()));
            if (template$entityinfo.R_4764_Y != null) {
                compoundnbt1.n_1700_B("nbt", template$entityinfo.R_4764_Y);
            }
            listnbt.add(compoundnbt1);
        }
        nbt.n_1700_B("entities", listnbt);
        nbt.n_1700_B("size", this.n_1700_B(this.R_4764_Y.getX(), this.R_4764_Y.getY(), this.R_4764_Y.getZ()));
        nbt.J_1907_R("DataVersion", SharedConstants.n_1700_B().getWorldVersion());
        return nbt;
    }

    public void J_1907_R(U_2912_j compound) {
        this.n_1700_B.clear();
        this.J_1907_R.clear();
        q_2896_o listnbt = compound.G_564_y("size", 3);
        this.R_4764_Y = new c_1514_x(listnbt.P_1922_E(0), listnbt.P_1922_E(1), listnbt.P_1922_E(2));
        q_2896_o listnbt1 = compound.G_564_y("blocks", 10);
        if (compound.R_4764_Y("palettes", 9)) {
            q_2896_o listnbt2 = compound.G_564_y("palettes", 9);
            for (int i = 0; i < listnbt2.size(); ++i) {
                this.n_1700_B(listnbt2.J_1907_R(i), listnbt1);
            }
        } else {
            this.n_1700_B(compound.G_564_y("palette", 10), listnbt1);
        }
        q_2896_o listnbt5 = compound.G_564_y("entities", 10);
        for (int j = 0; j < listnbt5.size(); ++j) {
            U_2912_j compoundnbt = listnbt5.n_1700_B(j);
            q_2896_o listnbt3 = compoundnbt.G_564_y("pos", 6);
            e_2866_D vector3d = new e_2866_D(listnbt3.v_4262_N(0), listnbt3.v_4262_N(1), listnbt3.v_4262_N(2));
            q_2896_o listnbt4 = compoundnbt.G_564_y("blockPos", 3);
            c_1514_x blockpos = new c_1514_x(listnbt4.P_1922_E(0), listnbt4.P_1922_E(1), listnbt4.P_1922_E(2));
            if (!compoundnbt.P_1922_E("nbt")) continue;
            U_2912_j compoundnbt1 = compoundnbt.M_182_A("nbt");
            this.J_1907_R.add(new R_4764_Y(vector3d, blockpos, compoundnbt1));
        }
    }

    private void n_1700_B(q_2896_o palletesNBT, q_2896_o blocksNBT) {
        n_1700_B template$basicpalette = new n_1700_B();
        for (int i = 0; i < palletesNBT.size(); ++i) {
            template$basicpalette.n_1700_B(n_3832_I.R_4764_Y(palletesNBT.n_1700_B(i)), i);
        }
        ArrayList list2 = Lists.newArrayList();
        ArrayList list = Lists.newArrayList();
        ArrayList list1 = Lists.newArrayList();
        for (int j = 0; j < blocksNBT.size(); ++j) {
            U_2912_j compoundnbt = blocksNBT.n_1700_B(j);
            q_2896_o listnbt = compoundnbt.G_564_y("pos", 3);
            c_1514_x blockpos = new c_1514_x(listnbt.P_1922_E(0), listnbt.P_1922_E(1), listnbt.P_1922_E(2));
            K_4074_S blockstate = template$basicpalette.n_1700_B(compoundnbt.w_1484_f("state"));
            U_2912_j compoundnbt1 = compoundnbt.P_1922_E("nbt") ? compoundnbt.M_182_A("nbt") : null;
            J_1907_R template$blockinfo = new J_1907_R(blockpos, blockstate, compoundnbt1);
            a_2886_t.n_1700_B(template$blockinfo, list2, list, list1);
        }
        List<J_1907_R> list3 = a_2886_t.n_1700_B(list2, list, list1);
        this.n_1700_B.add(new G_564_y(list3));
    }

    private q_2896_o n_1700_B(int ... values) {
        q_2896_o listnbt = new q_2896_o();
        for (int i : values) {
            listnbt.add(IntTag.n_1700_B(i));
        }
        return listnbt;
    }

    private q_2896_o n_1700_B(double ... values) {
        q_2896_o listnbt = new q_2896_o();
        for (double d0 : values) {
            listnbt.add(D_908_R.n_1700_B(d0));
        }
        return listnbt;
    }

    public static class J_1907_R {
        public final c_1514_x n_1700_B;
        public final K_4074_S J_1907_R;
        public final U_2912_j R_4764_Y;

        public J_1907_R(c_1514_x pos, K_4074_S state, @Nullable U_2912_j nbt) {
            this.n_1700_B = pos;
            this.J_1907_R = state;
            this.R_4764_Y = nbt;
        }

        public String toString() {
            return String.format("<StructureBlockInfo | %s | %s | %s>", this.n_1700_B, this.J_1907_R, this.R_4764_Y);
        }
    }

    public static final class G_564_y {
        private final List<J_1907_R> n_1700_B;
        private final Map<T_2915_h, List<J_1907_R>> J_1907_R = Maps.newHashMap();

        private G_564_y(List<J_1907_R> p_i232120_1_) {
            this.n_1700_B = p_i232120_1_;
        }

        public List<J_1907_R> n_1700_B() {
            return this.n_1700_B;
        }

        public List<J_1907_R> n_1700_B(T_2915_h p_237158_1_) {
            return this.J_1907_R.computeIfAbsent(p_237158_1_, p_237160_1_ -> this.n_1700_B.stream().filter(p_237159_1_ -> p_237159_1_.J_1907_R.n_1700_B((T_2915_h)p_237160_1_)).collect(Collectors.toList()));
        }
    }

    public static class R_4764_Y {
        public final e_2866_D n_1700_B;
        public final c_1514_x J_1907_R;
        public final U_2912_j R_4764_Y;

        public R_4764_Y(e_2866_D vecIn, c_1514_x posIn, U_2912_j nbt) {
            this.n_1700_B = vecIn;
            this.J_1907_R = posIn;
            this.R_4764_Y = nbt;
        }
    }

    static class n_1700_B
    implements Iterable<K_4074_S> {
        public static final K_4074_S n_1700_B = a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
        private final w_424_u<K_4074_S> J_1907_R = new w_424_u(16);
        private int R_4764_Y;

        private n_1700_B() {
        }

        public int n_1700_B(K_4074_S state) {
            int i = this.J_1907_R.n_1700_B(state);
            if (i == -1) {
                i = this.R_4764_Y++;
                this.J_1907_R.n_1700_B(state, i);
            }
            return i;
        }

        @Nullable
        public K_4074_S n_1700_B(int id) {
            K_4074_S blockstate = this.J_1907_R.n_1700_B(id);
            return blockstate == null ? n_1700_B : blockstate;
        }

        @Override
        public Iterator<K_4074_S> iterator() {
            return this.J_1907_R.iterator();
        }

        public void n_1700_B(K_4074_S p_189956_1_, int p_189956_2_) {
            this.J_1907_R.n_1700_B(p_189956_1_, p_189956_2_);
        }
    }
}


