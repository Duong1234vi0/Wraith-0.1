/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lightning.product.AgableMob;
import lightning.product.Sensor;
import lightning.product.e_3591_l;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class AdultSensor
extends Sensor<AgableMob> {
    @Override
    public Set<MemoryModuleType<?>> n_1700_B() {
        return ImmutableSet.of(MemoryModuleType.d_2427_y, MemoryModuleType.w_1484_f);
    }

    @Override
    protected void n_1700_B(e_3591_l worldIn, AgableMob entityIn) {
        entityIn.y_1945_D().R_4764_Y(MemoryModuleType.w_1484_f).ifPresent(entities -> this.n_1700_B(entityIn, (List<r_4811_B>)entities));
    }

    private void n_1700_B(AgableMob entity, List<r_4811_B> entities) {
        Optional<AgableMob> optional = entities.stream().filter(livingEntity -> livingEntity.f_4016_n() == entity.f_4016_n()).map(livingEntity -> (AgableMob)livingEntity).filter(ageable -> !ageable.d_()).findFirst();
        entity.y_1945_D().n_1700_B(MemoryModuleType.d_2427_y, optional);
    }
}


