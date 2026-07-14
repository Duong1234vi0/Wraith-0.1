/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import lightning.product.Sensor;
import lightning.product.e_3591_l;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class DummySensor
extends Sensor<r_4811_B> {
    @Override
    protected void n_1700_B(e_3591_l worldIn, r_4811_B entityIn) {
    }

    @Override
    public Set<MemoryModuleType<?>> n_1700_B() {
        return ImmutableSet.of();
    }
}


