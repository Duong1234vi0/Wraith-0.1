/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lightning.product.Sensor;
import lightning.product.e_3591_l;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.MemoryModuleType;

public class VillagerBabiesSensor
extends Sensor<r_4811_B> {
    @Override
    public Set<MemoryModuleType<?>> n_1700_B() {
        return ImmutableSet.of(MemoryModuleType.t_148_a);
    }

    @Override
    protected void n_1700_B(e_3591_l worldIn, r_4811_B entityIn) {
        entityIn.y_1945_D().n_1700_B(MemoryModuleType.t_148_a, this.n_1700_B(entityIn));
    }

    private List<r_4811_B> n_1700_B(r_4811_B livingEntity) {
        return this.R_4764_Y(livingEntity).stream().filter(this::J_1907_R).collect(Collectors.toList());
    }

    private boolean J_1907_R(r_4811_B livingEntity) {
        return livingEntity.f_4016_n() == t_5_h.RealmsDefaultUncaughtExceptionHandler && livingEntity.d_();
    }

    private List<r_4811_B> R_4764_Y(r_4811_B livingEntity) {
        return livingEntity.y_1945_D().R_4764_Y(MemoryModuleType.w_1484_f).orElse(Lists.newArrayList());
    }
}


