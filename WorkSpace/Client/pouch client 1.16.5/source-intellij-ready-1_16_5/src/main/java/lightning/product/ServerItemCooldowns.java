/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_4088_l;
import lightning.product.G_4919_s;
import lightning.product.q_1613_l;
import lightning.product.v_576_m;

public class ServerItemCooldowns
extends v_576_m {
    private final B_4088_l n_1700_B;

    public ServerItemCooldowns(B_4088_l playerIn) {
        this.n_1700_B = playerIn;
    }

    @Override
    protected void J_1907_R(q_1613_l itemIn, int ticksIn) {
        super.J_1907_R(itemIn, ticksIn);
        this.n_1700_B.n_1700_B.n_1700_B(new G_4919_s(itemIn, ticksIn));
    }

    @Override
    protected void R_4764_Y(q_1613_l itemIn) {
        super.R_4764_Y(itemIn);
        this.n_1700_B.n_1700_B.n_1700_B(new G_4919_s(itemIn, 0));
    }
}


