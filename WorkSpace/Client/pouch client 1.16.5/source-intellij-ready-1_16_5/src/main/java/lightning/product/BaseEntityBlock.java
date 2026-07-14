/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.O_2369_F;
import lightning.product.T_2915_h;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.i_2154_H;
import lightning.product.k_2789_z;
import lightning.product.q_4293_E;
import lightning.product.t_3286_u;

public abstract class BaseEntityBlock
extends T_2915_h
implements k_2789_z {
    protected BaseEntityBlock(q_4293_E.P_1922_E builder) {
        super(builder);
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.n_1700_B;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, int id, int param) {
        super.n_1700_B(state, worldIn, pos, id, param);
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        return tileentity == null ? false : tileentity.a_(id, param);
    }

    @Override
    @Nullable
    public t_3286_u n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos) {
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        return tileentity instanceof t_3286_u ? (t_3286_u)((Object)tileentity) : null;
    }
}


