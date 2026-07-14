/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.EntityModel;
import lightning.product.b_3485_j;
import lightning.product.CreeperModel;
import lightning.product.g_2336_b;
import lightning.product.j_4203_m;
import lightning.product.EnergySwirlLayer;

public class CreeperPowerLayer
extends EnergySwirlLayer<b_3485_j, CreeperModel<b_3485_j>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/creeper/creeper_armor.png");
    private final CreeperModel<b_3485_j> J_1907_R = new CreeperModel(2.0f);

    public CreeperPowerLayer(j_4203_m<b_3485_j, CreeperModel<b_3485_j>> p_i50947_1_) {
        super(p_i50947_1_);
    }

    @Override
    protected float n_1700_B(float p_225634_1_) {
        return p_225634_1_ * 0.01f;
    }

    @Override
    protected g_2336_b n_1700_B() {
        return n_1700_B;
    }

    @Override
    protected EntityModel<b_3485_j> J_1907_R() {
        return this.J_1907_R;
    }
}


