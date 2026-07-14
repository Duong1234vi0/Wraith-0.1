/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lightning.product.A_2352_Z;
import lightning.product.S_50_d;
import lightning.product.Z_530_i;
import lightning.product.a_3236_r;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.MemoryModuleType;

public class StopBeingAngryIfTargetDead<E extends Z_530_i>
extends Behavior<E> {
    public StopBeingAngryIfTargetDead() {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.d_2461_k, (Object)((Object)S_50_d.n_1700_B)));
    }

    protected void n_1700_B(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        a_3236_r.n_1700_B(entityIn, MemoryModuleType.d_2461_k).ifPresent(target -> {
            if (target.Z_2812_M() && (target.f_4016_n() != t_5_h.g_4106_L || worldIn.H_1990_U().J_1907_R(A_2352_Z.x_607_J))) {
                entityIn.y_1945_D().J_1907_R(MemoryModuleType.d_2461_k);
            }
        });
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (Z_530_i)r_4811_B2, l);
    }
}


