/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.FluidTags;
import lightning.product.D_1436_R;
import lightning.product.BlockGetter;
import lightning.product.I_1869_h;
import lightning.product.K_4074_S;
import lightning.product.Target;
import lightning.product.Z_530_i;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.NodeEvaluator;
import lightning.product.t_3546_P;
import lightning.product.u_530_F;

public class k_1804_C
extends NodeEvaluator {
    private final boolean s_956_w;

    public k_1804_C(boolean p_i48927_1_) {
        this.s_956_w = p_i48927_1_;
    }

    @Override
    public D_1436_R J_1907_R() {
        return super.n_1700_B(u_530_F.R_4764_Y(this.J_1907_R.i_601_W().minX), u_530_F.R_4764_Y(this.J_1907_R.i_601_W().minY + 0.5), u_530_F.R_4764_Y(this.J_1907_R.i_601_W().minZ));
    }

    @Override
    public Target n_1700_B(double p_224768_1_, double p_224768_3_, double p_224768_5_) {
        return new Target(super.n_1700_B(u_530_F.R_4764_Y(p_224768_1_ - (double)(this.J_1907_R.C_415_h() / 2.0f)), u_530_F.R_4764_Y(p_224768_3_ + 0.5), u_530_F.R_4764_Y(p_224768_5_ - (double)(this.J_1907_R.C_415_h() / 2.0f))));
    }

    @Override
    public int n_1700_B(D_1436_R[] p_222859_1_, D_1436_R p_222859_2_) {
        int i = 0;
        for (b_257_Y direction : b_257_Y.values()) {
            D_1436_R pathpoint = this.J_1907_R(p_222859_2_.n_1700_B + direction.t_148_a(), p_222859_2_.J_1907_R + direction.s_956_w(), p_222859_2_.R_4764_Y + direction.u_2550_I());
            if (pathpoint == null || pathpoint.t_148_a) continue;
            p_222859_1_[i++] = pathpoint;
        }
        return i;
    }

    @Override
    public I_1869_h n_1700_B(BlockGetter blockaccessIn, int x, int y, int z, Z_530_i entitylivingIn, int xSize, int ySize, int zSize, boolean canBreakDoorsIn, boolean canEnterDoorsIn) {
        return this.n_1700_B(blockaccessIn, x, y, z);
    }

    @Override
    public I_1869_h n_1700_B(BlockGetter blockaccessIn, int x, int y, int z) {
        c_1514_x blockpos = new c_1514_x(x, y, z);
        FluidState fluidstate = blockaccessIn.getFluidState(blockpos);
        K_4074_S blockstate = blockaccessIn.getBlockState(blockpos);
        if (fluidstate.R_4764_Y() && blockstate.n_1700_B(blockaccessIn, blockpos.down(), t_3546_P.J_1907_R) && blockstate.v_4262_N()) {
            return I_1869_h.Y_259_p;
        }
        return fluidstate.n_1700_B(FluidTags.J_1907_R) && blockstate.n_1700_B(blockaccessIn, blockpos, t_3546_P.J_1907_R) ? I_1869_h.w_1484_f : I_1869_h.n_1700_B;
    }

    @Nullable
    private D_1436_R J_1907_R(int p_186328_1_, int p_186328_2_, int p_186328_3_) {
        I_1869_h pathnodetype = this.R_4764_Y(p_186328_1_, p_186328_2_, p_186328_3_);
        return (!this.s_956_w || pathnodetype != I_1869_h.Y_259_p) && pathnodetype != I_1869_h.w_1484_f ? null : this.n_1700_B(p_186328_1_, p_186328_2_, p_186328_3_);
    }

    @Override
    @Nullable
    protected D_1436_R n_1700_B(int x, int y, int z) {
        D_1436_R pathpoint = null;
        I_1869_h pathnodetype = this.n_1700_B(this.J_1907_R.O_508_d, x, y, z);
        float f = this.J_1907_R.n_1700_B(pathnodetype);
        if (f >= 0.0f) {
            pathpoint = super.n_1700_B(x, y, z);
            pathpoint.M_588_G = pathnodetype;
            pathpoint.u_2550_I = Math.max(pathpoint.u_2550_I, f);
            if (this.n_1700_B.getFluidState(new c_1514_x(x, y, z)).R_4764_Y()) {
                pathpoint.u_2550_I += 8.0f;
            }
        }
        return pathnodetype == I_1869_h.J_1907_R ? pathpoint : pathpoint;
    }

    private I_1869_h R_4764_Y(int p_186327_1_, int p_186327_2_, int p_186327_3_) {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int i = p_186327_1_; i < p_186327_1_ + this.G_564_y; ++i) {
            for (int j = p_186327_2_; j < p_186327_2_ + this.P_1922_E; ++j) {
                for (int k = p_186327_3_; k < p_186327_3_ + this.u_1723_Y; ++k) {
                    FluidState fluidstate = this.n_1700_B.getFluidState(blockpos$mutable.n_1700_B(i, j, k));
                    K_4074_S blockstate = this.n_1700_B.getBlockState(blockpos$mutable.n_1700_B(i, j, k));
                    if (fluidstate.R_4764_Y() && blockstate.n_1700_B((BlockGetter)this.n_1700_B, (c_1514_x)blockpos$mutable.down(), t_3546_P.J_1907_R) && blockstate.v_4262_N()) {
                        return I_1869_h.Y_259_p;
                    }
                    if (fluidstate.n_1700_B(FluidTags.J_1907_R)) continue;
                    return I_1869_h.n_1700_B;
                }
            }
        }
        K_4074_S blockstate1 = this.n_1700_B.getBlockState(blockpos$mutable);
        return blockstate1.n_1700_B((BlockGetter)this.n_1700_B, (c_1514_x)blockpos$mutable, t_3546_P.J_1907_R) ? I_1869_h.w_1484_f : I_1869_h.n_1700_B;
    }
}


