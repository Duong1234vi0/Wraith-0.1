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
import lightning.product.Sensor;
import lightning.product.e_3591_l;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.MemoryModuleType;

public class GolemSensor
extends Sensor<r_4811_B> {
    public GolemSensor() {
        this(200);
    }

    public GolemSensor(int interval) {
        super(interval);
    }

    @Override
    protected void n_1700_B(e_3591_l worldIn, r_4811_B entityIn) {
        GolemSensor.n_1700_B(entityIn);
    }

    @Override
    public Set<MemoryModuleType<?>> n_1700_B() {
        return ImmutableSet.of(MemoryModuleType.v_4262_N);
    }

    public static void n_1700_B(r_4811_B livingEntity) {
        boolean flag;
        Optional<List<r_4811_B>> optional = livingEntity.y_1945_D().R_4764_Y(MemoryModuleType.v_4262_N);
        if (optional.isPresent() && (flag = optional.get().stream().anyMatch(entity -> entity.f_4016_n().equals(t_5_h.v_4276_D)))) {
            GolemSensor.J_1907_R(livingEntity);
        }
    }

    public static void J_1907_R(r_4811_B livingEntity) {
        livingEntity.y_1945_D().n_1700_B(MemoryModuleType.t_4043_B, true, 600L);
    }
}


