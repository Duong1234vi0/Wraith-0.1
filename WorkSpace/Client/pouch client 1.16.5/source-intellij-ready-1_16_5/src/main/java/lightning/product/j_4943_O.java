/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lightning.product.E_4668_a;
import lightning.product.I_3700_V;
import lightning.product.Z_530_i;
import lightning.product.Sensor;
import lightning.product.AbstractPiglin;
import lightning.product.e_3591_l;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;
import lightning.product.WitherSkeleton;

public class j_4943_O
extends Sensor<r_4811_B> {
    @Override
    public Set<MemoryModuleType<?>> n_1700_B() {
        return ImmutableSet.of(MemoryModuleType.w_1484_f, MemoryModuleType.v_4276_D, MemoryModuleType.N_2525_X);
    }

    @Override
    protected void n_1700_B(e_3591_l worldIn, r_4811_B entityIn) {
        E_4668_a<?> brain = entityIn.y_1945_D();
        Optional<Object> optional = Optional.empty();
        ArrayList list = Lists.newArrayList();
        for (r_4811_B livingentity : brain.R_4764_Y(MemoryModuleType.w_1484_f).orElse((List<r_4811_B>)ImmutableList.of())) {
            if (!(livingentity instanceof WitherSkeleton) && !(livingentity instanceof I_3700_V)) continue;
            optional = Optional.of((Z_530_i)livingentity);
            break;
        }
        for (r_4811_B livingentity1 : brain.R_4764_Y(MemoryModuleType.v_4262_N).orElse((List<r_4811_B>)ImmutableList.of())) {
            if (!(livingentity1 instanceof AbstractPiglin) || !((AbstractPiglin)livingentity1).y_2447_C()) continue;
            list.add((AbstractPiglin)livingentity1);
        }
        brain.n_1700_B(MemoryModuleType.v_4276_D, optional);
        brain.n_1700_B(MemoryModuleType.N_2525_X, list);
    }
}


