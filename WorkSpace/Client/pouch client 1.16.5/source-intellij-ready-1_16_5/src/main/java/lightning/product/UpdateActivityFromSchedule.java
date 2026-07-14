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

public class UpdateActivityFromSchedule
extends Behavior<r_4811_B> {
    public UpdateActivityFromSchedule() {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of());
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, r_4811_B entityIn, long gameTimeIn) {
        entityIn.y_1945_D().n_1700_B(worldIn.Z_976_R(), worldIn.X_933_l());
    }
}


