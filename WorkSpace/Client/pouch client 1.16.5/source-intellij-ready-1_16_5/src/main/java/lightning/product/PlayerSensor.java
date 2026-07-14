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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lightning.product.E_4668_a;
import lightning.product.I_408_V;
import lightning.product.N_4263_v;
import lightning.product.a_3913_L;
import lightning.product.Sensor;
import lightning.product.e_3591_l;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class PlayerSensor
extends Sensor<r_4811_B> {
    @Override
    public Set<MemoryModuleType<?>> n_1700_B() {
        return ImmutableSet.of(MemoryModuleType.s_956_w, MemoryModuleType.u_2550_I, MemoryModuleType.M_588_G);
    }

    @Override
    protected void n_1700_B(e_3591_l worldIn, r_4811_B entityIn) {
        List list = worldIn.multiplayerClientSuggestionProvider().stream().filter(I_408_V.v_4262_N).filter(player -> entityIn.n_1700_B((N_4263_v)player, 16.0)).sorted(Comparator.comparingDouble(entityIn::G_564_y)).collect(Collectors.toList());
        E_4668_a<?> brain = entityIn.y_1945_D();
        brain.n_1700_B(MemoryModuleType.s_956_w, list);
        List list1 = list.stream().filter(player -> PlayerSensor.n_1700_B(entityIn, player)).collect(Collectors.toList());
        brain.n_1700_B(MemoryModuleType.u_2550_I, list1.isEmpty() ? null : (a_3913_L)list1.get(0));
        Optional<N_4263_v> optional = list1.stream().filter(I_408_V.u_1723_Y).findFirst();
        brain.n_1700_B(MemoryModuleType.M_588_G, optional);
    }
}


