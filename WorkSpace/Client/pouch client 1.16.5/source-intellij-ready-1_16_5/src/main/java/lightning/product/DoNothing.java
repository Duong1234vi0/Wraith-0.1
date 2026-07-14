/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lightning.product.S_50_d;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class DoNothing
extends Behavior<r_4811_B> {
    public DoNothing(int durationMin, int durationMax) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(), durationMin, durationMax);
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, r_4811_B entityIn, long gameTimeIn) {
        return true;
    }
}


