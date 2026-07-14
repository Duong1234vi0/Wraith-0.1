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
import lightning.product.N_4263_v;
import lightning.product.S_50_d;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.n_1494_c;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class StopAdmiringIfItemTooFarAway<E extends A_69_b>
extends Behavior<E> {
    private final int n_1700_B;

    public StopAdmiringIfItemTooFarAway(int p_i231574_1_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.T_2506_i, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.z_1737_N, (Object)((Object)S_50_d.R_4764_Y)));
        this.n_1700_B = p_i231574_1_;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, E owner) {
        if (!((r_4811_B)owner).S_4035_N().n_1700_B()) {
            return false;
        }
        Optional<n_1494_c> optional = ((A_69_b)owner).y_1945_D().R_4764_Y(MemoryModuleType.z_1737_N);
        if (!optional.isPresent()) {
            return true;
        }
        return !optional.get().n_1700_B((N_4263_v)owner, (double)this.n_1700_B);
    }

    protected void n_1700_B(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        ((A_69_b)entityIn).y_1945_D().J_1907_R(MemoryModuleType.T_2506_i);
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (A_69_b)r_4811_B2, l);
    }
}


