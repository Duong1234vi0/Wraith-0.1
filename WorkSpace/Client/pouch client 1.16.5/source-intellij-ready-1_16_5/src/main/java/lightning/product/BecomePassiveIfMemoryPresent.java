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

public class BecomePassiveIfMemoryPresent
extends Behavior<r_4811_B> {
    private final int n_1700_B;

    public BecomePassiveIfMemoryPresent(MemoryModuleType<?> p_i231510_1_, int p_i231510_2_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.Q_4569_t, (Object)((Object)S_50_d.R_4764_Y), MemoryModuleType.A_1038_p, (Object)((Object)S_50_d.J_1907_R), p_i231510_1_, (Object)((Object)S_50_d.n_1700_B)));
        this.n_1700_B = p_i231510_2_;
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, r_4811_B entityIn, long gameTimeIn) {
        entityIn.y_1945_D().n_1700_B(MemoryModuleType.A_1038_p, true, this.n_1700_B);
        entityIn.y_1945_D().J_1907_R(MemoryModuleType.Q_4569_t);
    }
}


