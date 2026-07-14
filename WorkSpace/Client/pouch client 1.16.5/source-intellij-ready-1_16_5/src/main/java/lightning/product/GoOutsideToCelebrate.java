/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.MoveToSkySeeingSpot;
import lightning.product.b_3129_s;
import lightning.product.e_3591_l;
import lightning.product.r_4811_B;

public class GoOutsideToCelebrate
extends MoveToSkySeeingSpot {
    public GoOutsideToCelebrate(float speed) {
        super(speed);
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, r_4811_B owner) {
        b_3129_s raid = worldIn.Z_875_P(owner.b_2312_j());
        return raid != null && raid.P_1922_E() && super.n_1700_B(worldIn, owner);
    }
}


