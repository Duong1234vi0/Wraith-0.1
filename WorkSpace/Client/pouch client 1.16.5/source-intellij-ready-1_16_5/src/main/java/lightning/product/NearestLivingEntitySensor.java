/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lightning.product.E_4668_a;
import lightning.product.I_4817_s;
import lightning.product.Sensor;
import lightning.product.e_3591_l;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class NearestLivingEntitySensor
extends Sensor<r_4811_B> {
    @Override
    protected void n_1700_B(e_3591_l worldIn, r_4811_B entityIn) {
        I_4817_s axisalignedbb = entityIn.i_601_W().grow(16.0, 16.0, 16.0);
        List<r_4811_B> list = worldIn.n_1700_B(r_4811_B.class, axisalignedbb, livingEntity -> livingEntity != entityIn && livingEntity.RealmsLongRunningMcoTaskScreen());
        list.sort(Comparator.comparingDouble(entityIn::G_564_y));
        E_4668_a<?> brain = entityIn.y_1945_D();
        brain.n_1700_B(MemoryModuleType.v_4262_N, list);
        brain.n_1700_B(MemoryModuleType.w_1484_f, list.stream().filter(visibleEntity -> NearestLivingEntitySensor.n_1700_B(entityIn, visibleEntity)).collect(Collectors.toList()));
    }

    @Override
    public Set<MemoryModuleType<?>> n_1700_B() {
        return ImmutableSet.of(MemoryModuleType.v_4262_N, MemoryModuleType.w_1484_f);
    }
}


