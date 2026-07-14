/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.I_4817_s;
import lightning.product.N_4263_v;
import lightning.product.LevelSimulatedRW;
import lightning.product.T_1316_M;
import lightning.product.V_3137_a;
import lightning.product.c_1514_x;
import lightning.product.f_2392_k;
import lightning.product.k_594_Q;
import lightning.product.r_4097_j;
import lightning.product.s_1395_c;
import lightning.product.s_488_F;
import lightning.product.z_2963_s;

public interface CommonLevelAccessor
extends LevelSimulatedRW,
T_1316_M,
s_488_F {
    @Override
    default public Stream<s_1395_c> n_1700_B(@Nullable N_4263_v p_230318_1_, I_4817_s p_230318_2_, Predicate<N_4263_v> p_230318_3_) {
        return s_488_F.super.n_1700_B(p_230318_1_, p_230318_2_, p_230318_3_);
    }

    @Override
    default public boolean n_1700_B(@Nullable N_4263_v entityIn, s_1395_c shape) {
        return s_488_F.super.n_1700_B(entityIn, shape);
    }

    @Override
    default public c_1514_x n_1700_B(z_2963_s.n_1700_B heightmapType, c_1514_x pos) {
        return T_1316_M.super.n_1700_B(heightmapType, pos);
    }

    public r_4097_j t_1786_h();

    default public Optional<f_2392_k<k_594_Q>> n_1700_B(c_1514_x p_242406_1_) {
        return this.t_1786_h().J_1907_R(V_3137_a.PlayerInfo).R_4764_Y(this.P_1922_E(p_242406_1_));
    }
}


