/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lightning.product.A_4919_q;
import lightning.product.A_69_b;
import lightning.product.S_50_d;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.MemoryModuleType;

public class RememberIfHoglinWasKilled<E extends A_69_b>
extends Behavior<E> {
    public RememberIfHoglinWasKilled() {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.Q_4569_t, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.e_2887_G, (Object)((Object)S_50_d.R_4764_Y)));
    }

    protected void n_1700_B(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        if (this.n_1700_B(entityIn)) {
            A_4919_q.R_4764_Y(entityIn);
        }
    }

    private boolean n_1700_B(E p_234539_1_) {
        r_4811_B livingentity = ((A_69_b)p_234539_1_).y_1945_D().R_4764_Y(MemoryModuleType.Q_4569_t).get();
        return livingentity.f_4016_n() == t_5_h.e_4240_b && livingentity.Z_2812_M();
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (A_69_b)r_4811_B2, l);
    }
}


