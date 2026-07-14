/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lightning.product.E_4668_a;
import lightning.product.S_50_d;
import lightning.product.b_3129_s;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.Activity;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class SetRaidStatus
extends Behavior<r_4811_B> {
    public SetRaidStatus() {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of());
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, r_4811_B owner) {
        return worldIn.w_1457_N.nextInt(20) == 0;
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, r_4811_B entityIn, long gameTimeIn) {
        E_4668_a<?> brain = entityIn.y_1945_D();
        b_3129_s raid = worldIn.Z_875_P(entityIn.b_2312_j());
        if (raid != null) {
            if (raid.R_4764_Y() && !raid.J_1907_R()) {
                brain.J_1907_R(Activity.w_1484_f);
                brain.n_1700_B(Activity.w_1484_f);
            } else {
                brain.J_1907_R(Activity.t_148_a);
                brain.n_1700_B(Activity.t_148_a);
            }
        }
    }
}


