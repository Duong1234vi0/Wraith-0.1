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
import lightning.product.E_4668_a;
import lightning.product.F_427_K;
import lightning.product.WalkTarget;
import lightning.product.N_4263_v;
import lightning.product.S_50_d;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.o_4722_d;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.MemoryModuleType;

public class SocializeAtBell
extends Behavior<r_4811_B> {
    public SocializeAtBell() {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.R_4764_Y), MemoryModuleType.h_1847_R, (Object)((Object)S_50_d.R_4764_Y), MemoryModuleType.P_1922_E, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.w_1484_f, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.t_1786_h, (Object)((Object)S_50_d.J_1907_R)));
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, r_4811_B owner) {
        E_4668_a<?> brain = owner.y_1945_D();
        Optional<F_427_K> optional = brain.R_4764_Y(MemoryModuleType.P_1922_E);
        return worldIn.e_4240_b().nextInt(100) == 0 && optional.isPresent() && worldIn.g_2268_R() == optional.get().n_1700_B() && optional.get().J_1907_R().withinDistance(owner.s_4990_V(), 4.0) && brain.R_4764_Y(MemoryModuleType.w_1484_f).get().stream().anyMatch(mob -> t_5_h.RealmsDefaultUncaughtExceptionHandler.equals(mob.f_4016_n()));
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, r_4811_B entityIn, long gameTimeIn) {
        E_4668_a<?> brain = entityIn.y_1945_D();
        brain.R_4764_Y(MemoryModuleType.w_1484_f).ifPresent(visibleMobs -> visibleMobs.stream().filter(mob -> t_5_h.RealmsDefaultUncaughtExceptionHandler.equals(mob.f_4016_n())).filter(villager -> villager.G_564_y(entityIn) <= 32.0).findFirst().ifPresent(villagerInDistance -> {
            brain.n_1700_B(MemoryModuleType.t_1786_h, villagerInDistance);
            brain.n_1700_B(MemoryModuleType.h_1847_R, new o_4722_d((N_4263_v)villagerInDistance, true));
            brain.n_1700_B(MemoryModuleType.P_4830_p, new WalkTarget(new o_4722_d((N_4263_v)villagerInDistance, false), 0.3f, 1));
        }));
    }
}


