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
import lightning.product.WalkTarget;
import lightning.product.S_50_d;
import lightning.product.Z_530_i;
import lightning.product.a_3236_r;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.o_4722_d;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class SetWalkTargetFromAttackTargetIfTargetOutOfReach
extends Behavior<Z_530_i> {
    private final float n_1700_B;

    public SetWalkTargetFromAttackTargetIfTargetOutOfReach(float speed) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.R_4764_Y), MemoryModuleType.h_1847_R, (Object)((Object)S_50_d.R_4764_Y), MemoryModuleType.Q_4569_t, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.w_1484_f, (Object)((Object)S_50_d.R_4764_Y)));
        this.n_1700_B = speed;
    }

    protected void n_1700_B(e_3591_l worldIn, Z_530_i entityIn, long gameTimeIn) {
        r_4811_B livingentity = entityIn.y_1945_D().R_4764_Y(MemoryModuleType.Q_4569_t).get();
        if (a_3236_r.R_4764_Y(entityIn, livingentity) && a_3236_r.n_1700_B(entityIn, livingentity, 1)) {
            this.n_1700_B(entityIn);
        } else {
            this.n_1700_B(entityIn, livingentity);
        }
    }

    private void n_1700_B(r_4811_B p_233968_1_, r_4811_B target) {
        E_4668_a<?> brain = p_233968_1_.y_1945_D();
        brain.n_1700_B(MemoryModuleType.h_1847_R, new o_4722_d(target, true));
        WalkTarget walktarget = new WalkTarget(new o_4722_d(target, false), this.n_1700_B, 0);
        brain.n_1700_B(MemoryModuleType.P_4830_p, walktarget);
    }

    private void n_1700_B(r_4811_B p_233967_1_) {
        p_233967_1_.y_1945_D().J_1907_R(MemoryModuleType.P_4830_p);
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (Z_530_i)r_4811_B2, l);
    }
}


