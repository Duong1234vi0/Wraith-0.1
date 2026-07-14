/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.function.BiPredicate;
import lightning.product.N_4263_v;
import lightning.product.S_50_d;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class k_3514_p<E extends r_4811_B, T extends N_4263_v>
extends Behavior<E> {
    private final int n_1700_B;
    private final BiPredicate<E, N_4263_v> R_4764_Y;

    public k_3514_p(int p_i231515_1_, BiPredicate<E, N_4263_v> p_i231515_2_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.w_1457_N, (Object)((Object)S_50_d.R_4764_Y)));
        this.n_1700_B = p_i231515_1_;
        this.R_4764_Y = p_i231515_2_;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, E owner) {
        N_4263_v entity = ((N_4263_v)owner).l_3609_d();
        N_4263_v entity1 = ((r_4811_B)owner).y_1945_D().R_4764_Y(MemoryModuleType.w_1457_N).orElse(null);
        if (entity == null && entity1 == null) {
            return false;
        }
        N_4263_v entity2 = entity == null ? entity1 : entity;
        return !this.n_1700_B(owner, entity2) || this.R_4764_Y.test(owner, entity2);
    }

    private boolean n_1700_B(E p_233892_1_, N_4263_v p_233892_2_) {
        return p_233892_2_.RealmsLongRunningMcoTaskScreen() && p_233892_2_.n_1700_B((N_4263_v)p_233892_1_, (double)this.n_1700_B) && p_233892_2_.O_508_d == ((r_4811_B)p_233892_1_).O_508_d;
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        ((r_4811_B)entityIn).A_3959_N();
        ((r_4811_B)entityIn).y_1945_D().J_1907_R(MemoryModuleType.w_1457_N);
    }
}


