/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.HitResult;
import lightning.product.N_4263_v;
import lightning.product.e_2866_D;

public class EntityHitResult
extends HitResult {
    private final N_4263_v J_1907_R;

    public EntityHitResult(N_4263_v entityIn) {
        this(entityIn, entityIn.s_4990_V());
    }

    public EntityHitResult(N_4263_v entityIn, e_2866_D hitVec) {
        super(hitVec);
        this.J_1907_R = entityIn;
    }

    public N_4263_v n_1700_B() {
        return this.J_1907_R;
    }

    @Override
    public HitResult.n_1700_B R_4764_Y() {
        return HitResult.n_1700_B.R_4764_Y;
    }
}


