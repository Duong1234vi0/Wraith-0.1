/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.ChunkSource;
import lightning.product.BlockGetter;
import lightning.product.I_4817_s;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.T_603_v;
import lightning.product.Y_1387_d;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.ChunkAccess;
import lightning.product.EmptyLevelChunk;
import lightning.product.i_2154_H;
import lightning.product.o_3283_D;
import lightning.product.s_1395_c;

public class PathNavigationRegion
implements BlockGetter,
o_3283_D {
    protected final int n_1700_B;
    protected final int J_1907_R;
    protected final ChunkAccess[][] R_4764_Y;
    protected boolean G_564_y;
    protected final b_4507_u P_1922_E;

    public PathNavigationRegion(b_4507_u worldIn, c_1514_x p_i50004_2_, c_1514_x p_i50004_3_) {
        this.P_1922_E = worldIn;
        this.n_1700_B = p_i50004_2_.getX() >> 4;
        this.J_1907_R = p_i50004_2_.getZ() >> 4;
        int i = p_i50004_3_.getX() >> 4;
        int j = p_i50004_3_.getZ() >> 4;
        this.R_4764_Y = new ChunkAccess[i - this.n_1700_B + 1][j - this.J_1907_R + 1];
        ChunkSource abstractchunkprovider = worldIn.q_2307_F();
        this.G_564_y = true;
        for (int k = this.n_1700_B; k <= i; ++k) {
            for (int l = this.J_1907_R; l <= j; ++l) {
                this.R_4764_Y[k - this.n_1700_B][l - this.J_1907_R] = abstractchunkprovider.R_4764_Y(k, l);
            }
        }
        for (int i1 = p_i50004_2_.getX() >> 4; i1 <= p_i50004_3_.getX() >> 4; ++i1) {
            for (int j1 = p_i50004_2_.getZ() >> 4; j1 <= p_i50004_3_.getZ() >> 4; ++j1) {
                ChunkAccess ichunk = this.R_4764_Y[i1 - this.n_1700_B][j1 - this.J_1907_R];
                if (ichunk == null || ichunk.n_1700_B(p_i50004_2_.getY(), p_i50004_3_.getY())) continue;
                this.G_564_y = false;
                return;
            }
        }
    }

    private ChunkAccess n_1700_B(c_1514_x p_226703_1_) {
        return this.n_1700_B(p_226703_1_.getX() >> 4, p_226703_1_.getZ() >> 4);
    }

    private ChunkAccess n_1700_B(int p_226702_1_, int p_226702_2_) {
        int i = p_226702_1_ - this.n_1700_B;
        int j = p_226702_2_ - this.J_1907_R;
        if (i >= 0 && i < this.R_4764_Y.length && j >= 0 && j < this.R_4764_Y[i].length) {
            ChunkAccess ichunk = this.R_4764_Y[i][j];
            return ichunk != null ? ichunk : new EmptyLevelChunk(this.P_1922_E, new Y_1387_d(p_226702_1_, p_226702_2_));
        }
        return new EmptyLevelChunk(this.P_1922_E, new Y_1387_d(p_226702_1_, p_226702_2_));
    }

    @Override
    public T_603_v H_2857_Y() {
        return this.P_1922_E.H_2857_Y();
    }

    @Override
    public BlockGetter G_564_y(int chunkX, int chunkZ) {
        return this.n_1700_B(chunkX, chunkZ);
    }

    @Override
    @Nullable
    public i_2154_H getTileEntity(c_1514_x pos) {
        ChunkAccess ichunk = this.n_1700_B(pos);
        return ichunk.getTileEntity(pos);
    }

    @Override
    public K_4074_S getBlockState(c_1514_x pos) {
        if (b_4507_u.Q_4569_t(pos)) {
            return a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
        }
        ChunkAccess ichunk = this.n_1700_B(pos);
        return ichunk.getBlockState(pos);
    }

    @Override
    public Stream<s_1395_c> n_1700_B(@Nullable N_4263_v p_230318_1_, I_4817_s p_230318_2_, Predicate<N_4263_v> p_230318_3_) {
        return Stream.empty();
    }

    @Override
    public Stream<s_1395_c> R_4764_Y(@Nullable N_4263_v entity, I_4817_s aabb, Predicate<N_4263_v> entityPredicate) {
        return this.J_1907_R(entity, aabb);
    }

    @Override
    public FluidState getFluidState(c_1514_x pos) {
        if (b_4507_u.Q_4569_t(pos)) {
            return Fluids.n_1700_B.w_1484_f();
        }
        ChunkAccess ichunk = this.n_1700_B(pos);
        return ichunk.getFluidState(pos);
    }
}


