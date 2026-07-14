/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.EntityCollisionContext;
import lightning.product.U_4243_e;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.q_1613_l;
import lightning.product.s_1395_c;

public interface CollisionContext {
    public static CollisionContext J_1907_R() {
        return EntityCollisionContext.n_1700_B;
    }

    public static CollisionContext n_1700_B(N_4263_v entityIn) {
        return new EntityCollisionContext(entityIn);
    }

    public boolean n_1700_B();

    public boolean n_1700_B(s_1395_c var1, c_1514_x var2, boolean var3);

    public boolean n_1700_B(q_1613_l var1);

    public boolean n_1700_B(FluidState var1, U_4243_e var2);
}


