/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.baritone.utils;

import javax.annotation.Nullable;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.i_2154_H;
import mods.baritone.utils.BlockStateInterface;

public final class BlockStateInterfaceAccessWrapper
implements BlockGetter {
    private final BlockStateInterface bsi;

    BlockStateInterfaceAccessWrapper(BlockStateInterface bsi) {
        this.bsi = bsi;
    }

    @Override
    @Nullable
    public i_2154_H getTileEntity(c_1514_x pos) {
        return null;
    }

    @Override
    public K_4074_S getBlockState(c_1514_x pos) {
        return this.bsi.get0(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public FluidState getFluidState(c_1514_x blockPos) {
        return this.getBlockState(blockPos).P_4830_p();
    }
}


