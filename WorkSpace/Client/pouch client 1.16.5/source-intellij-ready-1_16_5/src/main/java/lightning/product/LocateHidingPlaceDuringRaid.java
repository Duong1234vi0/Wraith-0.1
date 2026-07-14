/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.LocateHidingPlace;
import lightning.product.b_3129_s;
import lightning.product.e_3591_l;
import lightning.product.r_4811_B;

public class LocateHidingPlaceDuringRaid
extends LocateHidingPlace {
    public LocateHidingPlaceDuringRaid(int p_i50360_1_, float p_i50360_2_) {
        super(p_i50360_1_, p_i50360_2_, 1);
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, r_4811_B owner) {
        b_3129_s raid = worldIn.Z_875_P(owner.b_2312_j());
        return super.n_1700_B(worldIn, owner) && raid != null && raid.Y_601_j() && !raid.P_1922_E() && !raid.u_1723_Y();
    }
}


