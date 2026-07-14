/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.H_1748_a;
import lightning.product.K_4074_S;
import lightning.product.R_1900_x;
import lightning.product.BlockAndTintGetter;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.i_2154_H;
import lightning.product.k_594_Q;
import lightning.product.ColorResolver;

public class Y_3830_x
implements BlockAndTintGetter {
    protected final int n_1700_B;
    protected final int J_1907_R;
    protected final c_1514_x R_4764_Y;
    protected final int G_564_y;
    protected final int P_1922_E;
    protected final int u_1723_Y;
    protected final H_1748_a[][] v_4262_N;
    protected final K_4074_S[] w_1484_f;
    protected final FluidState[] t_148_a;
    protected final b_4507_u s_956_w;

    @Nullable
    public static Y_3830_x n_1700_B(b_4507_u worldIn, c_1514_x from, c_1514_x to, int padding) {
        return Y_3830_x.n_1700_B(worldIn, from, to, padding, true);
    }

    public static Y_3830_x n_1700_B(b_4507_u p_generateCache_0_, c_1514_x p_generateCache_1_, c_1514_x p_generateCache_2_, int p_generateCache_3_, boolean p_generateCache_4_) {
        int i = p_generateCache_1_.getX() - p_generateCache_3_ >> 4;
        int j = p_generateCache_1_.getZ() - p_generateCache_3_ >> 4;
        int k = p_generateCache_2_.getX() + p_generateCache_3_ >> 4;
        int l = p_generateCache_2_.getZ() + p_generateCache_3_ >> 4;
        H_1748_a[][] achunk = new H_1748_a[k - i + 1][l - j + 1];
        for (int i1 = i; i1 <= k; ++i1) {
            for (int j1 = j; j1 <= l; ++j1) {
                achunk[i1 - i][j1 - j] = p_generateCache_0_.u_1723_Y(i1, j1);
            }
        }
        if (p_generateCache_4_ && Y_3830_x.n_1700_B(p_generateCache_1_, p_generateCache_2_, i, j, achunk)) {
            return null;
        }
        boolean k1 = true;
        c_1514_x blockpos1 = p_generateCache_1_.add(-1, -1, -1);
        c_1514_x blockpos = p_generateCache_2_.add(1, 1, 1);
        return new Y_3830_x(p_generateCache_0_, i, j, achunk, blockpos1, blockpos);
    }

    public static boolean n_1700_B(c_1514_x p_241718_0_, c_1514_x p_241718_1_, int p_241718_2_, int p_241718_3_, H_1748_a[][] p_241718_4_) {
        for (int i = p_241718_0_.getX() >> 4; i <= p_241718_1_.getX() >> 4; ++i) {
            for (int j = p_241718_0_.getZ() >> 4; j <= p_241718_1_.getZ() >> 4; ++j) {
                H_1748_a chunk = p_241718_4_[i - p_241718_2_][j - p_241718_3_];
                if (chunk.n_1700_B(p_241718_0_.getY(), p_241718_1_.getY())) continue;
                return false;
            }
        }
        return true;
    }

    public Y_3830_x(b_4507_u worldIn, int chunkStartXIn, int chunkStartZIn, H_1748_a[][] chunksIn, c_1514_x startPos, c_1514_x endPos) {
        this.s_956_w = worldIn;
        this.n_1700_B = chunkStartXIn;
        this.J_1907_R = chunkStartZIn;
        this.v_4262_N = chunksIn;
        this.R_4764_Y = startPos;
        this.G_564_y = endPos.getX() - startPos.getX() + 1;
        this.P_1922_E = endPos.getY() - startPos.getY() + 1;
        this.u_1723_Y = endPos.getZ() - startPos.getZ() + 1;
        this.w_1484_f = null;
        this.t_148_a = null;
    }

    protected final int n_1700_B(c_1514_x pos) {
        return this.n_1700_B(pos.getX(), pos.getY(), pos.getZ());
    }

    protected int n_1700_B(int xIn, int yIn, int zIn) {
        int i = xIn - this.R_4764_Y.getX();
        int j = yIn - this.R_4764_Y.getY();
        int k = zIn - this.R_4764_Y.getZ();
        return k * this.G_564_y * this.P_1922_E + j * this.G_564_y + i;
    }

    @Override
    public K_4074_S getBlockState(c_1514_x pos) {
        int i = (pos.getX() >> 4) - this.n_1700_B;
        int j = (pos.getZ() >> 4) - this.J_1907_R;
        return this.v_4262_N[i][j].getBlockState(pos);
    }

    @Override
    public FluidState getFluidState(c_1514_x pos) {
        int i = (pos.getX() >> 4) - this.n_1700_B;
        int j = (pos.getZ() >> 4) - this.J_1907_R;
        return this.v_4262_N[i][j].getFluidState(pos);
    }

    @Override
    public float func_230487_a_(b_257_Y p_230487_1_, boolean p_230487_2_) {
        return this.s_956_w.func_230487_a_(p_230487_1_, p_230487_2_);
    }

    @Override
    public R_1900_x getLightManager() {
        return this.s_956_w.getLightManager();
    }

    @Override
    @Nullable
    public i_2154_H getTileEntity(c_1514_x pos) {
        return this.n_1700_B(pos, H_1748_a.n_1700_B.n_1700_B);
    }

    @Nullable
    public i_2154_H n_1700_B(c_1514_x pos, H_1748_a.n_1700_B creationType) {
        int i = (pos.getX() >> 4) - this.n_1700_B;
        int j = (pos.getZ() >> 4) - this.J_1907_R;
        return this.v_4262_N[i][j].getTileEntity(pos, creationType);
    }

    @Override
    public int getBlockColor(c_1514_x blockPosIn, ColorResolver colorResolverIn) {
        return this.s_956_w.getBlockColor(blockPosIn, colorResolverIn);
    }

    public k_594_Q J_1907_R(c_1514_x p_getBiome_1_) {
        return this.s_956_w.P_1922_E(p_getBiome_1_);
    }

    public H_1748_a n_1700_B(int p_getChunk_1_, int p_getChunk_2_) {
        return this.v_4262_N[p_getChunk_1_][p_getChunk_2_];
    }
}


