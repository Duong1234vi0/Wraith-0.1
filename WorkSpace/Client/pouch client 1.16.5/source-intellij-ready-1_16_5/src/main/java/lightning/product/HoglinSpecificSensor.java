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
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import lightning.product.A_69_b;
import lightning.product.E_4668_a;
import lightning.product.Hoglin;
import lightning.product.Sensor;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.BlockTags;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class HoglinSpecificSensor
extends Sensor<Hoglin> {
    @Override
    public Set<MemoryModuleType<?>> n_1700_B() {
        return ImmutableSet.of(MemoryModuleType.w_1484_f, MemoryModuleType.r_715_M, MemoryModuleType.T_3594_S, MemoryModuleType.g_2268_R, MemoryModuleType.s_2632_s, MemoryModuleType.l_1233_K, (Object[])new MemoryModuleType[0]);
    }

    @Override
    protected void n_1700_B(e_3591_l worldIn, Hoglin entityIn) {
        E_4668_a<Hoglin> brain = entityIn.y_1945_D();
        brain.n_1700_B(MemoryModuleType.r_715_M, this.J_1907_R(worldIn, entityIn));
        Optional<Object> optional = Optional.empty();
        int i = 0;
        ArrayList list = Lists.newArrayList();
        for (r_4811_B livingentity : brain.R_4764_Y(MemoryModuleType.w_1484_f).orElse(Lists.newArrayList())) {
            if (livingentity instanceof A_69_b && !livingentity.d_()) {
                ++i;
                if (!optional.isPresent()) {
                    optional = Optional.of((A_69_b)livingentity);
                }
            }
            if (!(livingentity instanceof Hoglin) || livingentity.d_()) continue;
            list.add((Hoglin)livingentity);
        }
        brain.n_1700_B(MemoryModuleType.T_3594_S, optional);
        brain.n_1700_B(MemoryModuleType.g_2268_R, list);
        brain.n_1700_B(MemoryModuleType.s_2632_s, Integer.valueOf(i));
        brain.n_1700_B(MemoryModuleType.l_1233_K, Integer.valueOf(list.size()));
    }

    private Optional<c_1514_x> J_1907_R(e_3591_l world, Hoglin hoglin) {
        return c_1514_x.getClosestMatchingPosition(hoglin.b_2312_j(), 8, 4, pos -> world.getBlockState((c_1514_x)pos).n_1700_B(BlockTags.V_1446_Y));
    }
}


