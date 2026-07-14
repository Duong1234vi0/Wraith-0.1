/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.H_1748_a;
import lightning.product.I_4817_s;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.R_1900_x;
import lightning.product.V_3137_a;
import lightning.product.Y_1387_d;
import lightning.product.Biomes;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.c_1108_W;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.i_2154_H;
import lightning.product.j_3341_s;
import lightning.product.k_594_Q;
import lightning.product.y_3683_b;

public class EmptyLevelChunk
extends H_1748_a {
    private static final k_594_Q[] n_1700_B = j_3341_s.n_1700_B(new k_594_Q[c_1108_W.n_1700_B], biomes -> Arrays.fill(biomes, Biomes.n_1700_B));

    public EmptyLevelChunk(b_4507_u worldIn, Y_1387_d chunkPos) {
        super(worldIn, chunkPos, new c_1108_W(worldIn.t_1786_h().J_1907_R(V_3137_a.PlayerInfo), n_1700_B));
    }

    @Override
    public K_4074_S getBlockState(c_1514_x pos) {
        return a_3742_W.z_2759_Q.multiplayerClientSuggestionProvider();
    }

    @Override
    @Nullable
    public K_4074_S setBlockState(c_1514_x pos, K_4074_S state, boolean isMoving) {
        return null;
    }

    @Override
    public FluidState getFluidState(c_1514_x pos) {
        return Fluids.n_1700_B.w_1484_f();
    }

    @Override
    @Nullable
    public R_1900_x getWorldLightManager() {
        return null;
    }

    @Override
    public int R_4764_Y(c_1514_x pos) {
        return 0;
    }

    @Override
    public void addEntity(N_4263_v entityIn) {
    }

    @Override
    public void removeEntity(N_4263_v entityIn) {
    }

    @Override
    public void removeEntityAtIndex(N_4263_v entityIn, int index) {
    }

    @Override
    @Nullable
    public i_2154_H getTileEntity(c_1514_x pos, H_1748_a.n_1700_B creationMode) {
        return null;
    }

    @Override
    public void addTileEntity(i_2154_H tileEntityIn) {
    }

    @Override
    public void addTileEntity(c_1514_x pos, i_2154_H tileEntityIn) {
    }

    @Override
    public void removeTileEntity(c_1514_x pos) {
    }

    @Override
    public void markDirty() {
    }

    @Override
    public void getEntitiesWithinAABBForEntity(@Nullable N_4263_v entityIn, I_4817_s aabb, List<N_4263_v> listToFill, Predicate<? super N_4263_v> filter) {
    }

    @Override
    public <T extends N_4263_v> void getEntitiesOfTypeWithinAABB(Class<? extends T> entityClass, I_4817_s aabb, List<T> listToFill, Predicate<? super T> filter) {
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean n_1700_B(int startY, int endY) {
        return true;
    }

    @Override
    public y_3683_b.G_564_y getLocationType() {
        return y_3683_b.G_564_y.J_1907_R;
    }
}


