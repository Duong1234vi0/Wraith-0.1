/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;
import lightning.product.BlockStateProperties;
import lightning.product.BitSetDiscreteVoxelShape;
import lightning.product.K_4074_S;
import lightning.product.DiscreteVoxelShape;
import lightning.product.BoundingBox;
import lightning.product.LevelSimulatedRW;
import lightning.product.WorldGenLevel;
import lightning.product.TreeConfiguration;
import lightning.product.T_2915_h;
import lightning.product.a_2886_t;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.FoliagePlacer;
import lightning.product.BlockTags;
import lightning.product.LevelSimulatedReader;
import lightning.product.LevelAccessor;
import lightning.product.Material;
import lightning.product.LevelWriter;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;
import lightning.product.z_3539_x;

public class TreeFeature
extends Feature<TreeConfiguration> {
    public TreeFeature(Codec<TreeConfiguration> p_i231999_1_) {
        super(p_i231999_1_);
    }

    public static boolean R_4764_Y(LevelSimulatedReader p_236410_0_, c_1514_x p_236410_1_) {
        return TreeFeature.P_1922_E(p_236410_0_, p_236410_1_) || p_236410_0_.n_1700_B(p_236410_1_, p_236417_0_ -> p_236417_0_.n_1700_B(BlockTags.w_1457_N));
    }

    private static boolean u_1723_Y(LevelSimulatedReader p_236414_0_, c_1514_x p_236414_1_) {
        return p_236414_0_.n_1700_B(p_236414_1_, p_236415_0_ -> p_236415_0_.n_1700_B(a_3742_W.U_4087_m));
    }

    private static boolean v_4262_N(LevelSimulatedReader p_236416_0_, c_1514_x p_236416_1_) {
        return p_236416_0_.n_1700_B(p_236416_1_, p_236413_0_ -> p_236413_0_.n_1700_B(a_3742_W.c_3005_b));
    }

    public static boolean G_564_y(LevelSimulatedReader p_236412_0_, c_1514_x p_236412_1_) {
        return p_236412_0_.n_1700_B(p_236412_1_, p_236411_0_ -> p_236411_0_.v_4262_N() || p_236411_0_.n_1700_B(BlockTags.d_2427_y));
    }

    private static boolean w_1484_f(LevelSimulatedReader p_236418_0_, c_1514_x p_236418_1_) {
        return p_236418_0_.n_1700_B(p_236418_1_, p_236409_0_ -> {
            T_2915_h block = p_236409_0_.J_1907_R();
            return TreeFeature.J_1907_R(block) || block == a_3742_W.Z_735_d;
        });
    }

    private static boolean t_148_a(LevelSimulatedReader p_236419_0_, c_1514_x p_236419_1_) {
        return p_236419_0_.n_1700_B(p_236419_1_, p_236406_0_ -> {
            Material material = p_236406_0_.R_4764_Y();
            return material == Material.v_4262_N;
        });
    }

    public static void J_1907_R(LevelWriter p_236408_0_, c_1514_x p_236408_1_, K_4074_S p_236408_2_) {
        p_236408_0_.n_1700_B(p_236408_1_, p_236408_2_, 19);
    }

    public static boolean P_1922_E(LevelSimulatedReader p_236404_0_, c_1514_x p_236404_1_) {
        return TreeFeature.G_564_y(p_236404_0_, p_236404_1_) || TreeFeature.t_148_a(p_236404_0_, p_236404_1_) || TreeFeature.v_4262_N(p_236404_0_, p_236404_1_);
    }

    private boolean n_1700_B(LevelSimulatedRW generationReader, Random rand, c_1514_x positionIn, Set<c_1514_x> p_225557_4_, Set<c_1514_x> p_225557_5_, BoundingBox boundingBoxIn, TreeConfiguration configIn) {
        c_1514_x blockpos;
        int i = configIn.v_4262_N.n_1700_B(rand);
        int j = configIn.u_1723_Y.n_1700_B(rand, i, configIn);
        int k = i - j;
        int l = configIn.u_1723_Y.n_1700_B(rand, k);
        if (!configIn.P_1922_E) {
            int i1 = generationReader.n_1700_B(z_2963_s.n_1700_B.G_564_y, positionIn).getY();
            int j1 = generationReader.n_1700_B(z_2963_s.n_1700_B.J_1907_R, positionIn).getY();
            if (j1 - i1 > configIn.t_148_a) {
                return false;
            }
            int k1 = configIn.u_2550_I == z_2963_s.n_1700_B.G_564_y ? i1 : (configIn.u_2550_I == z_2963_s.n_1700_B.J_1907_R ? j1 : generationReader.n_1700_B(configIn.u_2550_I, positionIn).getY());
            blockpos = new c_1514_x(positionIn.getX(), k1, positionIn.getZ());
        } else {
            blockpos = positionIn;
        }
        if (blockpos.getY() >= 1 && blockpos.getY() + i + 1 <= 256) {
            if (!TreeFeature.w_1484_f(generationReader, blockpos.down())) {
                return false;
            }
            OptionalInt optionalint = configIn.w_1484_f.R_4764_Y();
            int l1 = this.n_1700_B(generationReader, i, blockpos, configIn);
            if (l1 >= i || optionalint.isPresent() && l1 >= optionalint.getAsInt()) {
                List<FoliagePlacer.n_1700_B> list = configIn.v_4262_N.n_1700_B(generationReader, rand, l1, blockpos, p_225557_4_, boundingBoxIn, configIn);
                list.forEach(p_236407_8_ -> configIn.u_1723_Y.n_1700_B(generationReader, rand, configIn, l1, (FoliagePlacer.n_1700_B)p_236407_8_, j, l, p_225557_5_, boundingBoxIn));
                return true;
            }
            return false;
        }
        return false;
    }

    private int n_1700_B(LevelSimulatedReader p_241521_1_, int p_241521_2_, c_1514_x p_241521_3_, TreeConfiguration p_241521_4_) {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int i = 0; i <= p_241521_2_ + 1; ++i) {
            int j = p_241521_4_.w_1484_f.n_1700_B(p_241521_2_, i);
            for (int k = -j; k <= j; ++k) {
                for (int l = -j; l <= j; ++l) {
                    blockpos$mutable.n_1700_B(p_241521_3_, k, i, l);
                    if (TreeFeature.R_4764_Y(p_241521_1_, blockpos$mutable) && (p_241521_4_.s_956_w || !TreeFeature.u_1723_Y(p_241521_1_, blockpos$mutable))) continue;
                    return i - 2;
                }
            }
        }
        return p_241521_2_;
    }

    @Override
    protected void n_1700_B(LevelWriter world, c_1514_x pos, K_4074_S state) {
        TreeFeature.J_1907_R(world, pos, state);
    }

    @Override
    public final boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, TreeConfiguration p_241855_5_) {
        HashSet set = Sets.newHashSet();
        HashSet set1 = Sets.newHashSet();
        HashSet set2 = Sets.newHashSet();
        BoundingBox mutableboundingbox = BoundingBox.n_1700_B();
        boolean flag = this.n_1700_B(p_241855_1_, p_241855_3_, p_241855_4_, set, set1, mutableboundingbox, p_241855_5_);
        if (mutableboundingbox.n_1700_B <= mutableboundingbox.G_564_y && flag && !set.isEmpty()) {
            if (!p_241855_5_.G_564_y.isEmpty()) {
                ArrayList list = Lists.newArrayList((Iterable)set);
                ArrayList list1 = Lists.newArrayList((Iterable)set1);
                list.sort(Comparator.comparingInt(z_3539_x::getY));
                list1.sort(Comparator.comparingInt(z_3539_x::getY));
                p_241855_5_.G_564_y.forEach(p_236405_6_ -> p_236405_6_.n_1700_B(p_241855_1_, p_241855_3_, list, list1, set2, mutableboundingbox));
            }
            DiscreteVoxelShape voxelshapepart = this.n_1700_B(p_241855_1_, mutableboundingbox, set, set2);
            a_2886_t.n_1700_B(p_241855_1_, 3, voxelshapepart, mutableboundingbox.n_1700_B, mutableboundingbox.J_1907_R, mutableboundingbox.R_4764_Y);
            return true;
        }
        return false;
    }

    private DiscreteVoxelShape n_1700_B(LevelAccessor p_236403_1_, BoundingBox p_236403_2_, Set<c_1514_x> p_236403_3_, Set<c_1514_x> p_236403_4_) {
        ArrayList list = Lists.newArrayList();
        BitSetDiscreteVoxelShape voxelshapepart = new BitSetDiscreteVoxelShape(p_236403_2_.G_564_y(), p_236403_2_.P_1922_E(), p_236403_2_.u_1723_Y());
        int i = 6;
        for (int j = 0; j < 6; ++j) {
            list.add(Sets.newHashSet());
        }
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (c_1514_x blockpos : Lists.newArrayList(p_236403_4_)) {
            if (!p_236403_2_.J_1907_R(blockpos)) continue;
            ((DiscreteVoxelShape)voxelshapepart).n_1700_B(blockpos.getX() - p_236403_2_.n_1700_B, blockpos.getY() - p_236403_2_.J_1907_R, blockpos.getZ() - p_236403_2_.R_4764_Y, true, true);
        }
        for (c_1514_x blockpos1 : Lists.newArrayList(p_236403_3_)) {
            if (p_236403_2_.J_1907_R(blockpos1)) {
                ((DiscreteVoxelShape)voxelshapepart).n_1700_B(blockpos1.getX() - p_236403_2_.n_1700_B, blockpos1.getY() - p_236403_2_.J_1907_R, blockpos1.getZ() - p_236403_2_.R_4764_Y, true, true);
            }
            for (b_257_Y direction : b_257_Y.values()) {
                K_4074_S blockstate;
                blockpos$mutable.n_1700_B(blockpos1, direction);
                if (p_236403_3_.contains(blockpos$mutable) || !(blockstate = p_236403_1_.getBlockState(blockpos$mutable)).J_1907_R(BlockStateProperties.j_276_v)) continue;
                ((Set)list.get(0)).add(blockpos$mutable.toImmutable());
                TreeFeature.J_1907_R(p_236403_1_, blockpos$mutable, (K_4074_S)blockstate.n_1700_B(BlockStateProperties.j_276_v, 1));
                if (!p_236403_2_.J_1907_R(blockpos$mutable)) continue;
                ((DiscreteVoxelShape)voxelshapepart).n_1700_B(blockpos$mutable.getX() - p_236403_2_.n_1700_B, blockpos$mutable.getY() - p_236403_2_.J_1907_R, blockpos$mutable.getZ() - p_236403_2_.R_4764_Y, true, true);
            }
        }
        for (int l = 1; l < 6; ++l) {
            Set set = (Set)list.get(l - 1);
            Set set1 = (Set)list.get(l);
            for (c_1514_x blockpos2 : set) {
                if (p_236403_2_.J_1907_R(blockpos2)) {
                    ((DiscreteVoxelShape)voxelshapepart).n_1700_B(blockpos2.getX() - p_236403_2_.n_1700_B, blockpos2.getY() - p_236403_2_.J_1907_R, blockpos2.getZ() - p_236403_2_.R_4764_Y, true, true);
                }
                for (b_257_Y direction1 : b_257_Y.values()) {
                    int k;
                    K_4074_S blockstate1;
                    blockpos$mutable.n_1700_B(blockpos2, direction1);
                    if (set.contains(blockpos$mutable) || set1.contains(blockpos$mutable) || !(blockstate1 = p_236403_1_.getBlockState(blockpos$mutable)).J_1907_R(BlockStateProperties.j_276_v) || (k = blockstate1.R_4764_Y(BlockStateProperties.j_276_v).intValue()) <= l + 1) continue;
                    K_4074_S blockstate2 = (K_4074_S)blockstate1.n_1700_B(BlockStateProperties.j_276_v, l + 1);
                    TreeFeature.J_1907_R(p_236403_1_, blockpos$mutable, blockstate2);
                    if (p_236403_2_.J_1907_R(blockpos$mutable)) {
                        ((DiscreteVoxelShape)voxelshapepart).n_1700_B(blockpos$mutable.getX() - p_236403_2_.n_1700_B, blockpos$mutable.getY() - p_236403_2_.J_1907_R, blockpos$mutable.getZ() - p_236403_2_.R_4764_Y, true, true);
                    }
                    set1.add(blockpos$mutable.toImmutable());
                }
            }
        }
        return voxelshapepart;
    }
}


