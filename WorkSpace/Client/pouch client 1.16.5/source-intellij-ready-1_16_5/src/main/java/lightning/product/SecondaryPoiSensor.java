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
import java.util.Set;
import lightning.product.E_4668_a;
import lightning.product.F_427_K;
import lightning.product.L_2225_p;
import lightning.product.Sensor;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.f_2392_k;
import lightning.product.MemoryModuleType;

public class SecondaryPoiSensor
extends Sensor<L_2225_p> {
    public SecondaryPoiSensor() {
        super(40);
    }

    @Override
    protected void n_1700_B(e_3591_l worldIn, L_2225_p entityIn) {
        f_2392_k<b_4507_u> registrykey = worldIn.g_2268_R();
        c_1514_x blockpos = entityIn.b_2312_j();
        ArrayList list = Lists.newArrayList();
        int i = 4;
        for (int j = -4; j <= 4; ++j) {
            for (int k = -2; k <= 2; ++k) {
                for (int l = -4; l <= 4; ++l) {
                    c_1514_x blockpos1 = blockpos.add(j, k, l);
                    if (!entityIn.c_2086_l().J_1907_R().R_4764_Y().contains((Object)worldIn.getBlockState(blockpos1).J_1907_R())) continue;
                    list.add(F_427_K.n_1700_B(registrykey, blockpos1));
                }
            }
        }
        E_4668_a<L_2225_p> brain = entityIn.y_1945_D();
        if (!list.isEmpty()) {
            brain.n_1700_B(MemoryModuleType.u_1723_Y, list);
        } else {
            brain.J_1907_R(MemoryModuleType.u_1723_Y);
        }
    }

    @Override
    public Set<MemoryModuleType<?>> n_1700_B() {
        return ImmutableSet.of(MemoryModuleType.u_1723_Y);
    }
}


