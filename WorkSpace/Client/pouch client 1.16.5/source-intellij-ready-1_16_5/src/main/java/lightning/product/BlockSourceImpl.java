/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_4074_S;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.i_2154_H;
import lightning.product.BlockSource;

public class BlockSourceImpl
implements BlockSource {
    private final e_3591_l n_1700_B;
    private final c_1514_x J_1907_R;

    public BlockSourceImpl(e_3591_l world, c_1514_x pos) {
        this.n_1700_B = world;
        this.J_1907_R = pos;
    }

    @Override
    public e_3591_l v_4262_N() {
        return this.n_1700_B;
    }

    @Override
    public double n_1700_B() {
        return (double)this.J_1907_R.getX() + 0.5;
    }

    @Override
    public double J_1907_R() {
        return (double)this.J_1907_R.getY() + 0.5;
    }

    @Override
    public double R_4764_Y() {
        return (double)this.J_1907_R.getZ() + 0.5;
    }

    @Override
    public c_1514_x G_564_y() {
        return this.J_1907_R;
    }

    @Override
    public K_4074_S P_1922_E() {
        return this.n_1700_B.getBlockState(this.J_1907_R);
    }

    @Override
    public <T extends i_2154_H> T u_1723_Y() {
        return (T)this.n_1700_B.getTileEntity(this.J_1907_R);
    }
}


