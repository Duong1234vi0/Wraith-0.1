/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.Long2ObjectMap
 *  it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap
 *  it.unimi.dsi.fastutil.objects.Object2BooleanMap
 *  it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import java.util.EnumSet;
import javax.annotation.Nullable;
import lightning.product.FluidTags;
import lightning.product.C_4998_y;
import lightning.product.D_1436_R;
import lightning.product.LeavesBlock;
import lightning.product.BlockGetter;
import lightning.product.I_1869_h;
import lightning.product.I_4817_s;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.Target;
import lightning.product.S_1431_H;
import lightning.product.T_2915_h;
import lightning.product.PathNavigationRegion;
import lightning.product.Z_530_i;
import lightning.product.FenceGateBlock;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_2866_D;
import lightning.product.g_2711_h;
import lightning.product.NodeEvaluator;
import lightning.product.BlockTags;
import lightning.product.s_1395_c;
import lightning.product.Material;
import lightning.product.t_3546_P;
import lightning.product.u_530_F;

public class Z_535_q
extends NodeEvaluator {
    protected float s_956_w;
    private final Long2ObjectMap<I_1869_h> u_2550_I = new Long2ObjectOpenHashMap();
    private final Object2BooleanMap<I_4817_s> M_588_G = new Object2BooleanOpenHashMap();

    @Override
    public void n_1700_B(PathNavigationRegion p_225578_1_, Z_530_i p_225578_2_) {
        super.n_1700_B(p_225578_1_, p_225578_2_);
        this.s_956_w = p_225578_2_.n_1700_B(I_1869_h.w_1484_f);
    }

    @Override
    public void n_1700_B() {
        this.J_1907_R.n_1700_B(I_1869_h.w_1484_f, this.s_956_w);
        this.u_2550_I.clear();
        this.M_588_G.clear();
        super.n_1700_B();
    }

    @Override
    public D_1436_R J_1907_R() {
        int i;
        c_1514_x.n_1700_B blockpos$mutable;
        block11: {
            blockpos$mutable = new c_1514_x.n_1700_B();
            i = u_530_F.R_4764_Y(this.J_1907_R.X_2960_b());
            K_4074_S blockstate = this.n_1700_B.getBlockState(blockpos$mutable.n_1700_B(this.J_1907_R.O_3598_v(), (double)i, this.J_1907_R.l_2647_k()));
            if (!this.J_1907_R.n_1700_B(blockstate.P_4830_p().n_1700_B())) {
                if (this.P_1922_E() && this.J_1907_R.RowButton()) {
                    while (true) {
                        if (blockstate.J_1907_R() != a_3742_W.c_3005_b && blockstate.P_4830_p() != Fluids.R_4764_Y.n_1700_B(false)) {
                            --i;
                            break block11;
                        }
                        blockstate = this.n_1700_B.getBlockState(blockpos$mutable.n_1700_B(this.J_1907_R.O_3598_v(), (double)(++i), this.J_1907_R.l_2647_k()));
                    }
                }
                if (this.J_1907_R.M_1641_O()) {
                    i = u_530_F.R_4764_Y(this.J_1907_R.X_2960_b() + 0.5);
                } else {
                    c_1514_x blockpos = this.J_1907_R.b_2312_j();
                    while ((this.n_1700_B.getBlockState(blockpos).v_4262_N() || this.n_1700_B.getBlockState(blockpos).n_1700_B((BlockGetter)this.n_1700_B, blockpos, t_3546_P.n_1700_B)) && blockpos.getY() > 0) {
                        blockpos = blockpos.down();
                    }
                    i = blockpos.up().getY();
                }
            } else {
                while (this.J_1907_R.n_1700_B(blockstate.P_4830_p().n_1700_B())) {
                    blockstate = this.n_1700_B.getBlockState(blockpos$mutable.n_1700_B(this.J_1907_R.O_3598_v(), (double)(++i), this.J_1907_R.l_2647_k()));
                }
                --i;
            }
        }
        c_1514_x blockpos1 = this.J_1907_R.b_2312_j();
        I_1869_h pathnodetype = this.n_1700_B(this.J_1907_R, blockpos1.getX(), i, blockpos1.getZ());
        if (this.J_1907_R.n_1700_B(pathnodetype) < 0.0f) {
            I_4817_s axisalignedbb = this.J_1907_R.i_601_W();
            if (this.J_1907_R(blockpos$mutable.n_1700_B(axisalignedbb.minX, (double)i, axisalignedbb.minZ)) || this.J_1907_R(blockpos$mutable.n_1700_B(axisalignedbb.minX, (double)i, axisalignedbb.maxZ)) || this.J_1907_R(blockpos$mutable.n_1700_B(axisalignedbb.maxX, (double)i, axisalignedbb.minZ)) || this.J_1907_R(blockpos$mutable.n_1700_B(axisalignedbb.maxX, (double)i, axisalignedbb.maxZ))) {
                D_1436_R pathpoint = this.n_1700_B(blockpos$mutable);
                pathpoint.M_588_G = this.n_1700_B(this.J_1907_R, pathpoint.R_4764_Y());
                pathpoint.u_2550_I = this.J_1907_R.n_1700_B(pathpoint.M_588_G);
                return pathpoint;
            }
        }
        D_1436_R pathpoint1 = this.n_1700_B(blockpos1.getX(), i, blockpos1.getZ());
        pathpoint1.M_588_G = this.n_1700_B(this.J_1907_R, pathpoint1.R_4764_Y());
        pathpoint1.u_2550_I = this.J_1907_R.n_1700_B(pathpoint1.M_588_G);
        return pathpoint1;
    }

    private boolean J_1907_R(c_1514_x p_237239_1_) {
        I_1869_h pathnodetype = this.n_1700_B(this.J_1907_R, p_237239_1_);
        return this.J_1907_R.n_1700_B(pathnodetype) >= 0.0f;
    }

    @Override
    public Target n_1700_B(double p_224768_1_, double p_224768_3_, double p_224768_5_) {
        return new Target(this.n_1700_B(u_530_F.R_4764_Y(p_224768_1_), u_530_F.R_4764_Y(p_224768_3_), u_530_F.R_4764_Y(p_224768_5_)));
    }

    @Override
    public int n_1700_B(D_1436_R[] p_222859_1_, D_1436_R p_222859_2_) {
        D_1436_R pathpoint7;
        D_1436_R pathpoint6;
        D_1436_R pathpoint5;
        D_1436_R pathpoint4;
        D_1436_R pathpoint3;
        D_1436_R pathpoint2;
        D_1436_R pathpoint1;
        double d0;
        D_1436_R pathpoint;
        int i = 0;
        int j = 0;
        I_1869_h pathnodetype = this.n_1700_B(this.J_1907_R, p_222859_2_.n_1700_B, p_222859_2_.J_1907_R + 1, p_222859_2_.R_4764_Y);
        I_1869_h pathnodetype1 = this.n_1700_B(this.J_1907_R, p_222859_2_.n_1700_B, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y);
        if (this.J_1907_R.n_1700_B(pathnodetype) >= 0.0f && pathnodetype1 != I_1869_h.C_2741_M) {
            j = u_530_F.G_564_y(Math.max(1.0f, this.J_1907_R.RealmsServerPing));
        }
        if (this.n_1700_B(pathpoint = this.n_1700_B(p_222859_2_.n_1700_B, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y + 1, j, d0 = Z_535_q.n_1700_B((BlockGetter)this.n_1700_B, new c_1514_x(p_222859_2_.n_1700_B, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y)), b_257_Y.G_564_y, pathnodetype1), p_222859_2_)) {
            p_222859_1_[i++] = pathpoint;
        }
        if (this.n_1700_B(pathpoint1 = this.n_1700_B(p_222859_2_.n_1700_B - 1, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y, j, d0, b_257_Y.P_1922_E, pathnodetype1), p_222859_2_)) {
            p_222859_1_[i++] = pathpoint1;
        }
        if (this.n_1700_B(pathpoint2 = this.n_1700_B(p_222859_2_.n_1700_B + 1, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y, j, d0, b_257_Y.u_1723_Y, pathnodetype1), p_222859_2_)) {
            p_222859_1_[i++] = pathpoint2;
        }
        if (this.n_1700_B(pathpoint3 = this.n_1700_B(p_222859_2_.n_1700_B, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y - 1, j, d0, b_257_Y.R_4764_Y, pathnodetype1), p_222859_2_)) {
            p_222859_1_[i++] = pathpoint3;
        }
        if (this.n_1700_B(p_222859_2_, pathpoint1, pathpoint3, pathpoint4 = this.n_1700_B(p_222859_2_.n_1700_B - 1, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y - 1, j, d0, b_257_Y.R_4764_Y, pathnodetype1))) {
            p_222859_1_[i++] = pathpoint4;
        }
        if (this.n_1700_B(p_222859_2_, pathpoint2, pathpoint3, pathpoint5 = this.n_1700_B(p_222859_2_.n_1700_B + 1, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y - 1, j, d0, b_257_Y.R_4764_Y, pathnodetype1))) {
            p_222859_1_[i++] = pathpoint5;
        }
        if (this.n_1700_B(p_222859_2_, pathpoint1, pathpoint, pathpoint6 = this.n_1700_B(p_222859_2_.n_1700_B - 1, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y + 1, j, d0, b_257_Y.G_564_y, pathnodetype1))) {
            p_222859_1_[i++] = pathpoint6;
        }
        if (this.n_1700_B(p_222859_2_, pathpoint2, pathpoint, pathpoint7 = this.n_1700_B(p_222859_2_.n_1700_B + 1, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y + 1, j, d0, b_257_Y.G_564_y, pathnodetype1))) {
            p_222859_1_[i++] = pathpoint7;
        }
        return i;
    }

    private boolean n_1700_B(D_1436_R p_237235_1_, D_1436_R p_237235_2_) {
        return p_237235_1_ != null && !p_237235_1_.t_148_a && (p_237235_1_.u_2550_I >= 0.0f || p_237235_2_.u_2550_I < 0.0f);
    }

    private boolean n_1700_B(D_1436_R p_222860_1_, @Nullable D_1436_R p_222860_2_, @Nullable D_1436_R p_222860_3_, @Nullable D_1436_R p_222860_4_) {
        if (p_222860_4_ != null && p_222860_3_ != null && p_222860_2_ != null) {
            if (p_222860_4_.t_148_a) {
                return false;
            }
            if (p_222860_3_.J_1907_R <= p_222860_1_.J_1907_R && p_222860_2_.J_1907_R <= p_222860_1_.J_1907_R) {
                if (p_222860_2_.M_588_G != I_1869_h.G_564_y && p_222860_3_.M_588_G != I_1869_h.G_564_y && p_222860_4_.M_588_G != I_1869_h.G_564_y) {
                    boolean flag = p_222860_3_.M_588_G == I_1869_h.u_1723_Y && p_222860_2_.M_588_G == I_1869_h.u_1723_Y && (double)this.J_1907_R.C_415_h() < 0.5;
                    return p_222860_4_.u_2550_I >= 0.0f && (p_222860_3_.J_1907_R < p_222860_1_.J_1907_R || p_222860_3_.u_2550_I >= 0.0f || flag) && (p_222860_2_.J_1907_R < p_222860_1_.J_1907_R || p_222860_2_.u_2550_I >= 0.0f || flag);
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private boolean n_1700_B(D_1436_R p_237234_1_) {
        e_2866_D vector3d = new e_2866_D((double)p_237234_1_.n_1700_B - this.J_1907_R.O_3598_v(), (double)p_237234_1_.J_1907_R - this.J_1907_R.X_2960_b(), (double)p_237234_1_.R_4764_Y - this.J_1907_R.l_2647_k());
        I_4817_s axisalignedbb = this.J_1907_R.i_601_W();
        int i = u_530_F.P_1922_E(vector3d.u_1723_Y() / axisalignedbb.getAverageEdgeLength());
        vector3d = vector3d.n_1700_B((double)(1.0f / (float)i));
        for (int j = 1; j <= i; ++j) {
            if (!this.n_1700_B(axisalignedbb = axisalignedbb.offset(vector3d))) continue;
            return false;
        }
        return true;
    }

    public static double n_1700_B(BlockGetter p_197682_0_, c_1514_x pos) {
        c_1514_x blockpos = pos.down();
        s_1395_c voxelshape = p_197682_0_.getBlockState(blockpos).u_2550_I(p_197682_0_, blockpos);
        return (double)blockpos.getY() + (voxelshape.J_1907_R() ? 0.0 : voxelshape.R_4764_Y(b_257_Y.n_1700_B.J_1907_R));
    }

    @Nullable
    private D_1436_R n_1700_B(int x, int y, int z, int stepHeight, double groundYIn, b_257_Y facing, I_1869_h p_186332_8_) {
        double d3;
        double d2;
        I_4817_s axisalignedbb;
        D_1436_R pathpoint = null;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        double d0 = Z_535_q.n_1700_B((BlockGetter)this.n_1700_B, (c_1514_x)blockpos$mutable.n_1700_B(x, y, z));
        if (d0 - groundYIn > 1.125) {
            return null;
        }
        I_1869_h pathnodetype = this.n_1700_B(this.J_1907_R, x, y, z);
        float f = this.J_1907_R.n_1700_B(pathnodetype);
        double d1 = (double)this.J_1907_R.C_415_h() / 2.0;
        if (f >= 0.0f) {
            pathpoint = this.n_1700_B(x, y, z);
            pathpoint.M_588_G = pathnodetype;
            pathpoint.u_2550_I = Math.max(pathpoint.u_2550_I, f);
        }
        if (p_186332_8_ == I_1869_h.u_1723_Y && pathpoint != null && pathpoint.u_2550_I >= 0.0f && !this.n_1700_B(pathpoint)) {
            pathpoint = null;
        }
        if (pathnodetype == I_1869_h.R_4764_Y) {
            return pathpoint;
        }
        if ((pathpoint == null || pathpoint.u_2550_I < 0.0f) && stepHeight > 0 && pathnodetype != I_1869_h.u_1723_Y && pathnodetype != I_1869_h.u_2550_I && pathnodetype != I_1869_h.P_1922_E && (pathpoint = this.n_1700_B(x, y + 1, z, stepHeight - 1, groundYIn, facing, p_186332_8_)) != null && (pathpoint.M_588_G == I_1869_h.J_1907_R || pathpoint.M_588_G == I_1869_h.R_4764_Y) && this.J_1907_R.C_415_h() < 1.0f && this.n_1700_B(axisalignedbb = new I_4817_s((d2 = (double)(x - facing.t_148_a()) + 0.5) - d1, Z_535_q.n_1700_B((BlockGetter)this.n_1700_B, (c_1514_x)blockpos$mutable.n_1700_B(d2, (double)(y + 1), d3 = (double)(z - facing.u_2550_I()) + 0.5)) + 0.001, d3 - d1, d2 + d1, (double)this.J_1907_R.v_165_F() + Z_535_q.n_1700_B((BlockGetter)this.n_1700_B, (c_1514_x)blockpos$mutable.n_1700_B((double)pathpoint.n_1700_B, (double)pathpoint.J_1907_R, (double)pathpoint.R_4764_Y)) - 0.002, d3 + d1))) {
            pathpoint = null;
        }
        if (pathnodetype == I_1869_h.w_1484_f && !this.P_1922_E()) {
            if (this.n_1700_B(this.J_1907_R, x, y - 1, z) != I_1869_h.w_1484_f) {
                return pathpoint;
            }
            while (y > 0) {
                if ((pathnodetype = this.n_1700_B(this.J_1907_R, x, --y, z)) != I_1869_h.w_1484_f) {
                    return pathpoint;
                }
                pathpoint = this.n_1700_B(x, y, z);
                pathpoint.M_588_G = pathnodetype;
                pathpoint.u_2550_I = Math.max(pathpoint.u_2550_I, this.J_1907_R.n_1700_B(pathnodetype));
            }
        }
        if (pathnodetype == I_1869_h.J_1907_R) {
            int j = 0;
            int i = y;
            while (pathnodetype == I_1869_h.J_1907_R) {
                if (--y < 0) {
                    D_1436_R pathpoint3 = this.n_1700_B(x, i, z);
                    pathpoint3.M_588_G = I_1869_h.n_1700_B;
                    pathpoint3.u_2550_I = -1.0f;
                    return pathpoint3;
                }
                if (j++ >= this.J_1907_R.n_3197_X()) {
                    D_1436_R pathpoint2 = this.n_1700_B(x, y, z);
                    pathpoint2.M_588_G = I_1869_h.n_1700_B;
                    pathpoint2.u_2550_I = -1.0f;
                    return pathpoint2;
                }
                pathnodetype = this.n_1700_B(this.J_1907_R, x, y, z);
                f = this.J_1907_R.n_1700_B(pathnodetype);
                if (pathnodetype != I_1869_h.J_1907_R && f >= 0.0f) {
                    pathpoint = this.n_1700_B(x, y, z);
                    pathpoint.M_588_G = pathnodetype;
                    pathpoint.u_2550_I = Math.max(pathpoint.u_2550_I, f);
                    break;
                }
                if (!(f < 0.0f)) continue;
                D_1436_R pathpoint1 = this.n_1700_B(x, y, z);
                pathpoint1.M_588_G = I_1869_h.n_1700_B;
                pathpoint1.u_2550_I = -1.0f;
                return pathpoint1;
            }
        }
        if (pathnodetype == I_1869_h.u_1723_Y) {
            pathpoint = this.n_1700_B(x, y, z);
            pathpoint.t_148_a = true;
            pathpoint.M_588_G = pathnodetype;
            pathpoint.u_2550_I = pathnodetype.n_1700_B();
        }
        return pathpoint;
    }

    private boolean n_1700_B(I_4817_s p_237236_1_) {
        return (Boolean)this.M_588_G.computeIfAbsent((Object)p_237236_1_, p_237237_2_ -> !this.n_1700_B.a_(this.J_1907_R, p_237236_1_));
    }

    @Override
    public I_1869_h n_1700_B(BlockGetter blockaccessIn, int x, int y, int z, Z_530_i entitylivingIn, int xSize, int ySize, int zSize, boolean canBreakDoorsIn, boolean canEnterDoorsIn) {
        EnumSet<I_1869_h> enumset = EnumSet.noneOf(I_1869_h.class);
        I_1869_h pathnodetype = I_1869_h.n_1700_B;
        c_1514_x blockpos = entitylivingIn.b_2312_j();
        pathnodetype = this.n_1700_B(blockaccessIn, x, y, z, xSize, ySize, zSize, canBreakDoorsIn, canEnterDoorsIn, enumset, pathnodetype, blockpos);
        if (enumset.contains((Object)I_1869_h.u_1723_Y)) {
            return I_1869_h.u_1723_Y;
        }
        if (enumset.contains((Object)I_1869_h.u_2550_I)) {
            return I_1869_h.u_2550_I;
        }
        I_1869_h pathnodetype1 = I_1869_h.n_1700_B;
        for (I_1869_h pathnodetype2 : enumset) {
            if (entitylivingIn.n_1700_B(pathnodetype2) < 0.0f) {
                return pathnodetype2;
            }
            if (!(entitylivingIn.n_1700_B(pathnodetype2) >= entitylivingIn.n_1700_B(pathnodetype1))) continue;
            pathnodetype1 = pathnodetype2;
        }
        return pathnodetype == I_1869_h.J_1907_R && entitylivingIn.n_1700_B(pathnodetype1) == 0.0f && xSize <= 1 ? I_1869_h.J_1907_R : pathnodetype1;
    }

    public I_1869_h n_1700_B(BlockGetter p_193577_1_, int x, int y, int z, int xSize, int ySize, int zSize, boolean canOpenDoorsIn, boolean canEnterDoorsIn, EnumSet<I_1869_h> nodeTypeEnum, I_1869_h nodeType, c_1514_x pos) {
        for (int i = 0; i < xSize; ++i) {
            for (int j = 0; j < ySize; ++j) {
                for (int k = 0; k < zSize; ++k) {
                    int l = i + x;
                    int i1 = j + y;
                    int j1 = k + z;
                    I_1869_h pathnodetype = this.n_1700_B(p_193577_1_, l, i1, j1);
                    pathnodetype = this.n_1700_B(p_193577_1_, canOpenDoorsIn, canEnterDoorsIn, pos, pathnodetype);
                    if (i == 0 && j == 0 && k == 0) {
                        nodeType = pathnodetype;
                    }
                    nodeTypeEnum.add(pathnodetype);
                }
            }
        }
        return nodeType;
    }

    protected I_1869_h n_1700_B(BlockGetter p_215744_1_, boolean p_215744_2_, boolean p_215744_3_, c_1514_x p_215744_4_, I_1869_h p_215744_5_) {
        if (p_215744_5_ == I_1869_h.w_1457_N && p_215744_2_ && p_215744_3_) {
            p_215744_5_ = I_1869_h.G_564_y;
        }
        if (p_215744_5_ == I_1869_h.multiplayerClientSuggestionProvider && !p_215744_3_) {
            p_215744_5_ = I_1869_h.n_1700_B;
        }
        if (p_215744_5_ == I_1869_h.s_956_w && !(p_215744_1_.getBlockState(p_215744_4_).J_1907_R() instanceof g_2711_h) && !(p_215744_1_.getBlockState(p_215744_4_.down()).J_1907_R() instanceof g_2711_h)) {
            p_215744_5_ = I_1869_h.u_2550_I;
        }
        if (p_215744_5_ == I_1869_h.Q_2552_b) {
            p_215744_5_ = I_1869_h.n_1700_B;
        }
        return p_215744_5_;
    }

    private I_1869_h n_1700_B(Z_530_i entitylivingIn, c_1514_x pos) {
        return this.n_1700_B(entitylivingIn, pos.getX(), pos.getY(), pos.getZ());
    }

    private I_1869_h n_1700_B(Z_530_i p_237230_1_, int p_237230_2_, int p_237230_3_, int p_237230_4_) {
        return (I_1869_h)((Object)this.u_2550_I.computeIfAbsent(c_1514_x.pack(p_237230_2_, p_237230_3_, p_237230_4_), p_237229_5_ -> this.n_1700_B(this.n_1700_B, p_237230_2_, p_237230_3_, p_237230_4_, p_237230_1_, this.G_564_y, this.P_1922_E, this.u_1723_Y, this.G_564_y(), this.R_4764_Y())));
    }

    @Override
    public I_1869_h n_1700_B(BlockGetter blockaccessIn, int x, int y, int z) {
        return Z_535_q.n_1700_B(blockaccessIn, new c_1514_x.n_1700_B(x, y, z));
    }

    public static I_1869_h n_1700_B(BlockGetter p_237231_0_, c_1514_x.n_1700_B p_237231_1_) {
        int i = p_237231_1_.getX();
        int j = p_237231_1_.getY();
        int k = p_237231_1_.getZ();
        I_1869_h pathnodetype = Z_535_q.J_1907_R(p_237231_0_, p_237231_1_);
        if (pathnodetype == I_1869_h.J_1907_R && j >= 1) {
            I_1869_h pathnodetype1 = Z_535_q.J_1907_R(p_237231_0_, p_237231_1_.n_1700_B(i, j - 1, k));
            I_1869_h i_1869_h = pathnodetype = pathnodetype1 != I_1869_h.R_4764_Y && pathnodetype1 != I_1869_h.J_1907_R && pathnodetype1 != I_1869_h.w_1484_f && pathnodetype1 != I_1869_h.v_4262_N ? I_1869_h.R_4764_Y : I_1869_h.J_1907_R;
            if (pathnodetype1 == I_1869_h.P_4830_p) {
                pathnodetype = I_1869_h.P_4830_p;
            }
            if (pathnodetype1 == I_1869_h.Q_4569_t) {
                pathnodetype = I_1869_h.Q_4569_t;
            }
            if (pathnodetype1 == I_1869_h.t_1786_h) {
                pathnodetype = I_1869_h.t_1786_h;
            }
            if (pathnodetype1 == I_1869_h.C_2741_M) {
                pathnodetype = I_1869_h.C_2741_M;
            }
        }
        if (pathnodetype == I_1869_h.R_4764_Y) {
            pathnodetype = Z_535_q.n_1700_B(p_237231_0_, p_237231_1_.n_1700_B(i, j, k), pathnodetype);
        }
        return pathnodetype;
    }

    public static I_1869_h n_1700_B(BlockGetter p_237232_0_, c_1514_x.n_1700_B p_237232_1_, I_1869_h p_237232_2_) {
        int i = p_237232_1_.getX();
        int j = p_237232_1_.getY();
        int k = p_237232_1_.getZ();
        for (int l = -1; l <= 1; ++l) {
            for (int i1 = -1; i1 <= 1; ++i1) {
                for (int j1 = -1; j1 <= 1; ++j1) {
                    if (l == 0 && j1 == 0) continue;
                    p_237232_1_.n_1700_B(i + l, j + i1, k + j1);
                    K_4074_S blockstate = p_237232_0_.getBlockState(p_237232_1_);
                    if (blockstate.n_1700_B(a_3742_W.d_3244_b)) {
                        return I_1869_h.h_1847_R;
                    }
                    if (blockstate.n_1700_B(a_3742_W.s_4405_m)) {
                        return I_1869_h.M_182_A;
                    }
                    if (Z_535_q.n_1700_B(blockstate)) {
                        return I_1869_h.M_588_G;
                    }
                    if (!p_237232_0_.getFluidState(p_237232_1_).n_1700_B(FluidTags.J_1907_R)) continue;
                    return I_1869_h.t_148_a;
                }
            }
        }
        return p_237232_2_;
    }

    protected static I_1869_h J_1907_R(BlockGetter p_237238_0_, c_1514_x p_237238_1_) {
        K_4074_S blockstate = p_237238_0_.getBlockState(p_237238_1_);
        T_2915_h block = blockstate.J_1907_R();
        Material material = blockstate.R_4764_Y();
        if (blockstate.v_4262_N()) {
            return I_1869_h.J_1907_R;
        }
        if (!blockstate.n_1700_B(BlockTags.z_1737_N) && !blockstate.n_1700_B(a_3742_W.S_4035_N)) {
            if (blockstate.n_1700_B(a_3742_W.d_3244_b)) {
                return I_1869_h.Q_4569_t;
            }
            if (blockstate.n_1700_B(a_3742_W.s_4405_m)) {
                return I_1869_h.t_1786_h;
            }
            if (blockstate.n_1700_B(a_3742_W.B_1335_M)) {
                return I_1869_h.C_2741_M;
            }
            if (blockstate.n_1700_B(a_3742_W.U_3823_u)) {
                return I_1869_h.k_2293_S;
            }
            FluidState fluidstate = p_237238_0_.getFluidState(p_237238_1_);
            if (fluidstate.n_1700_B(FluidTags.J_1907_R)) {
                return I_1869_h.w_1484_f;
            }
            if (fluidstate.n_1700_B(FluidTags.R_4764_Y)) {
                return I_1869_h.v_4262_N;
            }
            if (Z_535_q.n_1700_B(blockstate)) {
                return I_1869_h.P_4830_p;
            }
            if (S_1431_H.t_148_a(blockstate) && !blockstate.R_4764_Y(S_1431_H.h_1847_R).booleanValue()) {
                return I_1869_h.w_1457_N;
            }
            if (block instanceof S_1431_H && material == Material.z_1737_N && !blockstate.R_4764_Y(S_1431_H.h_1847_R).booleanValue()) {
                return I_1869_h.Y_601_j;
            }
            if (block instanceof S_1431_H && blockstate.R_4764_Y(S_1431_H.h_1847_R).booleanValue()) {
                return I_1869_h.multiplayerClientSuggestionProvider;
            }
            if (block instanceof g_2711_h) {
                return I_1869_h.s_956_w;
            }
            if (block instanceof LeavesBlock) {
                return I_1869_h.Q_2552_b;
            }
            if (!(block.n_1700_B(BlockTags.G_624_v) || block.n_1700_B(BlockTags.x_607_J) || block instanceof FenceGateBlock && !blockstate.R_4764_Y(FenceGateBlock.P_4830_p).booleanValue())) {
                return !blockstate.n_1700_B(p_237238_0_, p_237238_1_, t_3546_P.n_1700_B) ? I_1869_h.n_1700_B : I_1869_h.J_1907_R;
            }
            return I_1869_h.u_1723_Y;
        }
        return I_1869_h.P_1922_E;
    }

    private static boolean n_1700_B(K_4074_S p_237233_0_) {
        return p_237233_0_.n_1700_B(BlockTags.j_276_v) || p_237233_0_.n_1700_B(a_3742_W.H_2857_Y) || p_237233_0_.n_1700_B(a_3742_W.LevitationControl) || C_4998_y.w_1484_f(p_237233_0_);
    }
}



