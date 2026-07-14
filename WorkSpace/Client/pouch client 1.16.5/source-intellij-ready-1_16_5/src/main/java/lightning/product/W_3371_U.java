/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import javax.annotation.Nullable;
import lightning.product.FluidTags;
import lightning.product.PathNavigation;
import lightning.product.BlockGetter;
import lightning.product.I_1869_h;
import lightning.product.Z_535_q;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.PathfinderMob;
import lightning.product.u_530_F;

public class W_3371_U {
    @Nullable
    public static e_2866_D n_1700_B(PathfinderMob entitycreatureIn, int xz, int y) {
        return W_3371_U.n_1700_B(entitycreatureIn, xz, y, 0, null, true, 1.5707963705062866, entitycreatureIn::n_1700_B, false, 0, 0, true);
    }

    @Nullable
    public static e_2866_D n_1700_B(PathfinderMob p_226338_0_, int p_226338_1_, int p_226338_2_, int p_226338_3_, @Nullable e_2866_D p_226338_4_, double p_226338_5_) {
        return W_3371_U.n_1700_B(p_226338_0_, p_226338_1_, p_226338_2_, p_226338_3_, p_226338_4_, true, p_226338_5_, p_226338_0_::n_1700_B, true, 0, 0, false);
    }

    @Nullable
    public static e_2866_D J_1907_R(PathfinderMob creature, int maxXZ, int maxY) {
        return W_3371_U.n_1700_B(creature, maxXZ, maxY, creature::n_1700_B);
    }

    @Nullable
    public static e_2866_D n_1700_B(PathfinderMob p_221024_0_, int p_221024_1_, int p_221024_2_, ToDoubleFunction<c_1514_x> p_221024_3_) {
        return W_3371_U.n_1700_B(p_221024_0_, p_221024_1_, p_221024_2_, 0, null, false, 0.0, p_221024_3_, true, 0, 0, true);
    }

    @Nullable
    public static e_2866_D n_1700_B(PathfinderMob p_226340_0_, int p_226340_1_, int p_226340_2_, e_2866_D p_226340_3_, float p_226340_4_, int p_226340_5_, int p_226340_6_) {
        return W_3371_U.n_1700_B(p_226340_0_, p_226340_1_, p_226340_2_, 0, p_226340_3_, false, p_226340_4_, p_226340_0_::n_1700_B, true, p_226340_5_, p_226340_6_, true);
    }

    @Nullable
    public static e_2866_D n_1700_B(PathfinderMob p_234133_0_, int p_234133_1_, int p_234133_2_, e_2866_D p_234133_3_) {
        e_2866_D vector3d = p_234133_3_.n_1700_B(p_234133_0_.O_3598_v(), p_234133_0_.X_2960_b(), p_234133_0_.l_2647_k());
        return W_3371_U.n_1700_B(p_234133_0_, p_234133_1_, p_234133_2_, 0, vector3d, false, 1.5707963705062866, p_234133_0_::n_1700_B, true, 0, 0, true);
    }

    @Nullable
    public static e_2866_D J_1907_R(PathfinderMob entitycreatureIn, int xz, int y, e_2866_D targetVec3) {
        e_2866_D vector3d = targetVec3.n_1700_B(entitycreatureIn.O_3598_v(), entitycreatureIn.X_2960_b(), entitycreatureIn.l_2647_k());
        return W_3371_U.n_1700_B(entitycreatureIn, xz, y, 0, vector3d, true, 1.5707963705062866, entitycreatureIn::n_1700_B, false, 0, 0, true);
    }

    @Nullable
    public static e_2866_D n_1700_B(PathfinderMob p_203155_0_, int xz, int p_203155_2_, e_2866_D p_203155_3_, double p_203155_4_) {
        e_2866_D vector3d = p_203155_3_.n_1700_B(p_203155_0_.O_3598_v(), p_203155_0_.X_2960_b(), p_203155_0_.l_2647_k());
        return W_3371_U.n_1700_B(p_203155_0_, xz, p_203155_2_, 0, vector3d, true, p_203155_4_, p_203155_0_::n_1700_B, false, 0, 0, true);
    }

    @Nullable
    public static e_2866_D J_1907_R(PathfinderMob p_226344_0_, int p_226344_1_, int p_226344_2_, int p_226344_3_, e_2866_D p_226344_4_, double p_226344_5_) {
        e_2866_D vector3d = p_226344_4_.n_1700_B(p_226344_0_.O_3598_v(), p_226344_0_.X_2960_b(), p_226344_0_.l_2647_k());
        return W_3371_U.n_1700_B(p_226344_0_, p_226344_1_, p_226344_2_, p_226344_3_, vector3d, false, p_226344_5_, p_226344_0_::n_1700_B, true, 0, 0, false);
    }

    @Nullable
    public static e_2866_D R_4764_Y(PathfinderMob entitycreatureIn, int xz, int y, e_2866_D targetVec3) {
        e_2866_D vector3d = entitycreatureIn.s_4990_V().G_564_y(targetVec3);
        return W_3371_U.n_1700_B(entitycreatureIn, xz, y, 0, vector3d, true, 1.5707963705062866, entitycreatureIn::n_1700_B, false, 0, 0, true);
    }

    @Nullable
    public static e_2866_D G_564_y(PathfinderMob p_223548_0_, int p_223548_1_, int p_223548_2_, e_2866_D p_223548_3_) {
        e_2866_D vector3d = p_223548_0_.s_4990_V().G_564_y(p_223548_3_);
        return W_3371_U.n_1700_B(p_223548_0_, p_223548_1_, p_223548_2_, 0, vector3d, false, 1.5707963705062866, p_223548_0_::n_1700_B, true, 0, 0, true);
    }

    @Nullable
    private static e_2866_D n_1700_B(PathfinderMob p_226339_0_, int p_226339_1_, int p_226339_2_, int p_226339_3_, @Nullable e_2866_D p_226339_4_, boolean p_226339_5_, double p_226339_6_, ToDoubleFunction<c_1514_x> p_226339_8_, boolean p_226339_9_, int p_226339_10_, int p_226339_11_, boolean p_226339_12_) {
        PathNavigation pathnavigator = p_226339_0_.e_4240_b();
        Random random = p_226339_0_.M_3508_C();
        boolean flag = p_226339_0_.z_3000_g() ? p_226339_0_.z_1333_t().withinDistance(p_226339_0_.s_4990_V(), (double)(p_226339_0_.L_3537_K() + (float)p_226339_1_) + 1.0) : false;
        boolean flag1 = false;
        double d0 = Double.NEGATIVE_INFINITY;
        c_1514_x blockpos = p_226339_0_.b_2312_j();
        for (int i = 0; i < 10; ++i) {
            double d1;
            I_1869_h pathnodetype;
            c_1514_x blockpos3;
            c_1514_x blockpos1 = W_3371_U.n_1700_B(random, p_226339_1_, p_226339_2_, p_226339_3_, p_226339_4_, p_226339_6_);
            if (blockpos1 == null) continue;
            int j = blockpos1.getX();
            int k = blockpos1.getY();
            int l = blockpos1.getZ();
            if (p_226339_0_.z_3000_g() && p_226339_1_ > 1) {
                c_1514_x blockpos2 = p_226339_0_.z_1333_t();
                j = p_226339_0_.O_3598_v() > (double)blockpos2.getX() ? (j -= random.nextInt(p_226339_1_ / 2)) : (j += random.nextInt(p_226339_1_ / 2));
                l = p_226339_0_.l_2647_k() > (double)blockpos2.getZ() ? (l -= random.nextInt(p_226339_1_ / 2)) : (l += random.nextInt(p_226339_1_ / 2));
            }
            if ((blockpos3 = new c_1514_x((double)j + p_226339_0_.O_3598_v(), (double)k + p_226339_0_.X_2960_b(), (double)l + p_226339_0_.l_2647_k())).getY() < 0 || blockpos3.getY() > p_226339_0_.O_508_d.c_3005_b() || flag && !p_226339_0_.u_1723_Y(blockpos3) || p_226339_12_ && !pathnavigator.n_1700_B(blockpos3)) continue;
            if (p_226339_9_) {
                blockpos3 = W_3371_U.n_1700_B(blockpos3, random.nextInt(p_226339_10_ + 1) + p_226339_11_, p_226339_0_.O_508_d.c_3005_b(), (c_1514_x p_226341_1_) -> p_226339_0_.O_508_d.getBlockState((c_1514_x)p_226341_1_).R_4764_Y().J_1907_R());
            }
            if (!p_226339_5_ && p_226339_0_.O_508_d.getFluidState(blockpos3).n_1700_B(FluidTags.J_1907_R) || p_226339_0_.n_1700_B(pathnodetype = Z_535_q.n_1700_B((BlockGetter)p_226339_0_.O_508_d, blockpos3.toMutable())) != 0.0f || !((d1 = p_226339_8_.applyAsDouble(blockpos3)) > d0)) continue;
            d0 = d1;
            blockpos = blockpos3;
            flag1 = true;
        }
        return flag1 ? e_2866_D.R_4764_Y(blockpos) : null;
    }

    @Nullable
    private static c_1514_x n_1700_B(Random p_226343_0_, int p_226343_1_, int p_226343_2_, int p_226343_3_, @Nullable e_2866_D p_226343_4_, double p_226343_5_) {
        if (p_226343_4_ != null && !(p_226343_5_ >= Math.PI)) {
            double d3 = u_530_F.G_564_y(p_226343_4_.G_564_y, p_226343_4_.J_1907_R) - 1.5707963705062866;
            double d4 = d3 + (double)(2.0f * p_226343_0_.nextFloat() - 1.0f) * p_226343_5_;
            double d0 = Math.sqrt(p_226343_0_.nextDouble()) * (double)u_530_F.n_1700_B * (double)p_226343_1_;
            double d1 = -d0 * Math.sin(d4);
            double d2 = d0 * Math.cos(d4);
            if (!(Math.abs(d1) > (double)p_226343_1_) && !(Math.abs(d2) > (double)p_226343_1_)) {
                int l = p_226343_0_.nextInt(2 * p_226343_2_ + 1) - p_226343_2_ + p_226343_3_;
                return new c_1514_x(d1, (double)l, d2);
            }
            return null;
        }
        int i = p_226343_0_.nextInt(2 * p_226343_1_ + 1) - p_226343_1_;
        int j = p_226343_0_.nextInt(2 * p_226343_2_ + 1) - p_226343_2_ + p_226343_3_;
        int k = p_226343_0_.nextInt(2 * p_226343_1_ + 1) - p_226343_1_;
        return new c_1514_x(i, j, k);
    }

    static c_1514_x n_1700_B(c_1514_x p_226342_0_, int p_226342_1_, int p_226342_2_, Predicate<c_1514_x> p_226342_3_) {
        c_1514_x blockpos2;
        if (p_226342_1_ < 0) {
            throw new IllegalArgumentException("aboveSolidAmount was " + p_226342_1_ + ", expected >= 0");
        }
        if (!p_226342_3_.test(p_226342_0_)) {
            return p_226342_0_;
        }
        c_1514_x blockpos = p_226342_0_.up();
        while (blockpos.getY() < p_226342_2_ && p_226342_3_.test(blockpos)) {
            blockpos = blockpos.up();
        }
        c_1514_x blockpos1 = blockpos;
        while (blockpos1.getY() < p_226342_2_ && blockpos1.getY() - blockpos.getY() < p_226342_1_ && !p_226342_3_.test(blockpos2 = blockpos1.up())) {
            blockpos1 = blockpos2;
        }
        return blockpos1;
    }
}


