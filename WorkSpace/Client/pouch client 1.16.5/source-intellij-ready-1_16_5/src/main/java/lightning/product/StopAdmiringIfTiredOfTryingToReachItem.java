/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Optional;
import lightning.product.A_69_b;
import lightning.product.E_4668_a;
import lightning.product.S_50_d;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class StopAdmiringIfTiredOfTryingToReachItem<E extends A_69_b>
extends Behavior<E> {
    private final int n_1700_B;
    private final int R_4764_Y;

    public StopAdmiringIfTiredOfTryingToReachItem(int p_i241918_1_, int p_i241918_2_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.T_2506_i, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.z_1737_N, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.q_4610_l, (Object)((Object)S_50_d.R_4764_Y), MemoryModuleType.z_4693_k, (Object)((Object)S_50_d.R_4764_Y)));
        this.n_1700_B = p_i241918_1_;
        this.R_4764_Y = p_i241918_2_;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, E owner) {
        return ((r_4811_B)owner).S_4035_N().n_1700_B();
    }

    protected void n_1700_B(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        E_4668_a<A_69_b> brain = ((A_69_b)entityIn).y_1945_D();
        Optional<Integer> optional = brain.R_4764_Y(MemoryModuleType.q_4610_l);
        if (!optional.isPresent()) {
            brain.n_1700_B(MemoryModuleType.q_4610_l, Integer.valueOf(0));
        } else {
            int i = optional.get();
            if (i > this.n_1700_B) {
                brain.J_1907_R(MemoryModuleType.T_2506_i);
                brain.J_1907_R(MemoryModuleType.q_4610_l);
                brain.n_1700_B(MemoryModuleType.z_4693_k, true, this.R_4764_Y);
            } else {
                brain.n_1700_B(MemoryModuleType.q_4610_l, Integer.valueOf(i + 1));
            }
        }
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (A_69_b)r_4811_B2, l);
    }
}


