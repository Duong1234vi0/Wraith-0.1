/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.T_1316_M;
import lightning.product.c_1514_x;
import lightning.product.i_2154_H;

public class BlockInWorld {
    private final T_1316_M n_1700_B;
    private final c_1514_x J_1907_R;
    private final boolean R_4764_Y;
    private K_4074_S G_564_y;
    private i_2154_H P_1922_E;
    private boolean u_1723_Y;

    public BlockInWorld(T_1316_M worldIn, c_1514_x posIn, boolean forceLoadIn) {
        this.n_1700_B = worldIn;
        this.J_1907_R = posIn.toImmutable();
        this.R_4764_Y = forceLoadIn;
    }

    public K_4074_S n_1700_B() {
        if (this.G_564_y == null && (this.R_4764_Y || this.n_1700_B.M_588_G(this.J_1907_R))) {
            this.G_564_y = this.n_1700_B.getBlockState(this.J_1907_R);
        }
        return this.G_564_y;
    }

    @Nullable
    public i_2154_H J_1907_R() {
        if (this.P_1922_E == null && !this.u_1723_Y) {
            this.P_1922_E = this.n_1700_B.getTileEntity(this.J_1907_R);
            this.u_1723_Y = true;
        }
        return this.P_1922_E;
    }

    public T_1316_M R_4764_Y() {
        return this.n_1700_B;
    }

    public c_1514_x G_564_y() {
        return this.J_1907_R;
    }

    public static Predicate<BlockInWorld> n_1700_B(Predicate<K_4074_S> predicatesIn) {
        return p_201002_1_ -> p_201002_1_ != null && predicatesIn.test(p_201002_1_.n_1700_B());
    }
}


