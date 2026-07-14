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
import lightning.product.E_4668_a;
import lightning.product.N_4263_v;
import lightning.product.Z_530_i;
import lightning.product.Sensor;
import lightning.product.e_3591_l;
import lightning.product.n_1494_c;
import lightning.product.MemoryModuleType;

public class NearestItemSensor
extends Sensor<Z_530_i> {
    @Override
    public Set<MemoryModuleType<?>> n_1700_B() {
        return ImmutableSet.of(MemoryModuleType.z_1737_N);
    }

    @Override
    protected void n_1700_B(e_3591_l worldIn, Z_530_i entityIn) {
        E_4668_a<?> brain = entityIn.y_1945_D();
        List<n_1494_c> list = worldIn.n_1700_B(n_1494_c.class, entityIn.i_601_W().grow(8.0, 4.0, 8.0), itemEntity -> true);
        list.sort(Comparator.comparingDouble(entityIn::G_564_y));
        Optional<n_1494_c> optional = list.stream().filter(itemEntity -> entityIn.t_148_a(itemEntity.P_1922_E())).filter(wantedItemEntity -> wantedItemEntity.n_1700_B((N_4263_v)entityIn, 9.0)).filter(entityIn::c_3005_b).findFirst();
        brain.n_1700_B(MemoryModuleType.z_1737_N, optional);
    }
}


