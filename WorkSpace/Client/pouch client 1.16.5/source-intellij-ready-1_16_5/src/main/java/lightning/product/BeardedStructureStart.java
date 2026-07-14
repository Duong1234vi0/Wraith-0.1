/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.StructureFeature;
import lightning.product.BoundingBox;
import lightning.product.StructureStart;
import lightning.product.FeatureConfiguration;

public abstract class BeardedStructureStart<C extends FeatureConfiguration>
extends StructureStart<C> {
    public BeardedStructureStart(StructureFeature<C> p_i225874_1_, int p_i225874_2_, int p_i225874_3_, BoundingBox p_i225874_4_, int p_i225874_5_, long p_i225874_6_) {
        super(p_i225874_1_, p_i225874_2_, p_i225874_3_, p_i225874_4_, p_i225874_5_, p_i225874_6_);
    }

    @Override
    protected void J_1907_R() {
        super.J_1907_R();
        int i = 12;
        this.R_4764_Y.n_1700_B -= 12;
        this.R_4764_Y.J_1907_R -= 12;
        this.R_4764_Y.R_4764_Y -= 12;
        this.R_4764_Y.G_564_y += 12;
        this.R_4764_Y.P_1922_E += 12;
        this.R_4764_Y.u_1723_Y += 12;
    }
}


