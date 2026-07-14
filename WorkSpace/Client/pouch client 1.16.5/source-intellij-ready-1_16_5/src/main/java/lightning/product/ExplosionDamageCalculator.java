/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Optional;
import lightning.product.F_1241_B;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.c_1514_x;
import lightning.product.FluidState;

public class ExplosionDamageCalculator {
    public Optional<Float> n_1700_B(F_1241_B explosion, BlockGetter reader, c_1514_x pos, K_4074_S state, FluidState fluid) {
        return state.v_4262_N() && fluid.R_4764_Y() ? Optional.empty() : Optional.of(Float.valueOf(Math.max(state.J_1907_R().u_2550_I(), fluid.t_148_a())));
    }

    public boolean n_1700_B(F_1241_B explosion, BlockGetter reader, c_1514_x pos, K_4074_S state, float power) {
        return true;
    }
}


