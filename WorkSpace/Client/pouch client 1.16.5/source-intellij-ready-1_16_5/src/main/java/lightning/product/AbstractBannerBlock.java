/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.BlockGetter;
import lightning.product.BaseEntityBlock;
import lightning.product.K_4074_S;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_933_M;
import lightning.product.i_2154_H;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.r_4889_F;

public abstract class AbstractBannerBlock
extends BaseEntityBlock {
    private final e_933_M P_4830_p;

    protected AbstractBannerBlock(e_933_M color, q_4293_E.P_1922_E properties) {
        super(properties);
        this.P_4830_p = color;
    }

    @Override
    public boolean n_1700_B() {
        return true;
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new r_4889_F(this.P_4830_p);
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, @Nullable r_4811_B placer, Z_1993_T stack) {
        i_2154_H tileentity;
        if (stack.Y_601_j() && (tileentity = worldIn.getTileEntity(pos)) instanceof r_4889_F) {
            ((r_4889_F)tileentity).n_1700_B(stack.multiplayerClientSuggestionProvider());
        }
    }

    @Override
    public Z_1993_T n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state) {
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        return tileentity instanceof r_4889_F ? ((r_4889_F)tileentity).n_1700_B(state) : super.n_1700_B(worldIn, pos, state);
    }

    public e_933_M J_1907_R() {
        return this.P_4830_p;
    }
}


