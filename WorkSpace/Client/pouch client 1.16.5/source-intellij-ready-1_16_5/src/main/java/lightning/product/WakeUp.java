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
import lightning.product.Activity;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class WakeUp
extends Behavior<r_4811_B> {
    public WakeUp() {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of());
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, r_4811_B owner) {
        return !owner.y_1945_D().R_4764_Y(Activity.P_1922_E) && owner.z_2372_L();
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, r_4811_B entityIn, long gameTimeIn) {
        entityIn.t_2932_z();
    }
}


