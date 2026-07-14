/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.WitherBossModel;
import lightning.product.I_3700_V;
import lightning.product.EntityModel;
import lightning.product.g_2336_b;
import lightning.product.j_4203_m;
import lightning.product.u_530_F;
import lightning.product.EnergySwirlLayer;

public class WitherArmorLayer
extends EnergySwirlLayer<I_3700_V, WitherBossModel<I_3700_V>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/wither/wither_armor.png");
    private final WitherBossModel<I_3700_V> J_1907_R = new WitherBossModel(0.5f);

    public WitherArmorLayer(j_4203_m<I_3700_V, WitherBossModel<I_3700_V>> p_i50915_1_) {
        super(p_i50915_1_);
    }

    @Override
    protected float n_1700_B(float p_225634_1_) {
        return u_530_F.J_1907_R(p_225634_1_ * 0.02f) * 3.0f;
    }

    @Override
    protected g_2336_b n_1700_B() {
        return n_1700_B;
    }

    @Override
    protected EntityModel<I_3700_V> J_1907_R() {
        return this.J_1907_R;
    }
}


