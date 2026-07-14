/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import lightning.product.E_4668_a;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.Sensor;
import lightning.product.e_3591_l;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class HurtBySensor
extends Sensor<r_4811_B> {
    @Override
    public Set<MemoryModuleType<?>> n_1700_B() {
        return ImmutableSet.of(MemoryModuleType.k_2293_S, MemoryModuleType.q_2307_F);
    }

    @Override
    protected void n_1700_B(e_3591_l worldIn, r_4811_B entityIn) {
        E_4668_a<?> brain = entityIn.y_1945_D();
        P_11_z damagesource = entityIn.J_4125_o();
        if (damagesource != null) {
            brain.n_1700_B(MemoryModuleType.k_2293_S, entityIn.J_4125_o());
            N_4263_v entity = damagesource.u_2550_I();
            if (entity instanceof r_4811_B) {
                brain.n_1700_B(MemoryModuleType.q_2307_F, (r_4811_B)entity);
            }
        } else {
            brain.J_1907_R(MemoryModuleType.k_2293_S);
        }
        brain.R_4764_Y(MemoryModuleType.q_2307_F).ifPresent(livingEntity -> {
            if (!livingEntity.RealmsLongRunningMcoTaskScreen() || livingEntity.O_508_d != worldIn) {
                brain.J_1907_R(MemoryModuleType.q_2307_F);
            }
        });
    }
}


