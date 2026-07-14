/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Optional;
import lightning.product.F_1241_B;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.ExplosionDamageCalculator;
import lightning.product.c_1514_x;
import lightning.product.FluidState;

public class EntityBasedExplosionDamageCalculator
extends ExplosionDamageCalculator {
    private final N_4263_v n_1700_B;

    public EntityBasedExplosionDamageCalculator(N_4263_v entity) {
        this.n_1700_B = entity;
    }

    @Override
    public Optional<Float> n_1700_B(F_1241_B explosion, BlockGetter reader, c_1514_x pos, K_4074_S state, FluidState fluid) {
        return super.n_1700_B(explosion, reader, pos, state, fluid).map(explosionPower -> Float.valueOf(this.n_1700_B.n_1700_B(explosion, reader, pos, state, fluid, explosionPower.floatValue())));
    }

    @Override
    public boolean n_1700_B(F_1241_B explosion, BlockGetter reader, c_1514_x pos, K_4074_S state, float power) {
        return this.n_1700_B.n_1700_B(explosion, reader, pos, state, power);
    }
}


