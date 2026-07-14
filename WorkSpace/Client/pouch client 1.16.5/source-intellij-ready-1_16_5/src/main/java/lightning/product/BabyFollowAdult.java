/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lightning.product.AgableMob;
import lightning.product.J_2548_M;
import lightning.product.N_4263_v;
import lightning.product.S_50_d;
import lightning.product.a_3236_r;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class BabyFollowAdult<E extends AgableMob>
extends Behavior<E> {
    private final J_2548_M n_1700_B;
    private final float R_4764_Y;

    public BabyFollowAdult(J_2548_M distance, float speed) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.d_2427_y, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.J_1907_R)));
        this.n_1700_B = distance;
        this.R_4764_Y = speed;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, E owner) {
        if (!((AgableMob)owner).d_()) {
            return false;
        }
        AgableMob ageableentity = this.n_1700_B(owner);
        return ((N_4263_v)owner).n_1700_B((N_4263_v)ageableentity, (double)(this.n_1700_B.J_1907_R() + 1)) && !((N_4263_v)owner).n_1700_B((N_4263_v)ageableentity, (double)this.n_1700_B.n_1700_B());
    }

    protected void n_1700_B(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        a_3236_r.n_1700_B(entityIn, this.n_1700_B(entityIn), this.R_4764_Y, this.n_1700_B.n_1700_B() - 1);
    }

    private AgableMob n_1700_B(E ageable) {
        return ((r_4811_B)ageable).y_1945_D().R_4764_Y(MemoryModuleType.d_2427_y).get();
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (AgableMob)r_4811_B2, l);
    }
}


