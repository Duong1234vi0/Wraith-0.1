/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableSet
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lightning.product.N_4263_v;
import lightning.product.Sensor;
import lightning.product.e_3591_l;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.MemoryModuleType;

public class VillagerHostilesSensor
extends Sensor<r_4811_B> {
    private static final ImmutableMap<t_5_h<?>, Float> n_1700_B = ImmutableMap.builder().put(t_5_h.t_1786_h, (Object)Float.valueOf(8.0f)).put(t_5_h.C_2741_M, (Object)Float.valueOf(12.0f)).put(t_5_h.d_2427_y, (Object)Float.valueOf(8.0f)).put(t_5_h.z_1737_N, (Object)Float.valueOf(12.0f)).put(t_5_h.p_178_J, (Object)Float.valueOf(15.0f)).put(t_5_h.e_1992_r, (Object)Float.valueOf(12.0f)).put(t_5_h.F_2624_D, (Object)Float.valueOf(8.0f)).put(t_5_h.y_1700_S, (Object)Float.valueOf(10.0f)).put(t_5_h.S_980_j, (Object)Float.valueOf(10.0f)).put(t_5_h.R_3077_Z, (Object)Float.valueOf(8.0f)).put(t_5_h.M_2677_i, (Object)Float.valueOf(8.0f)).build();

    @Override
    public Set<MemoryModuleType<?>> n_1700_B() {
        return ImmutableSet.of(MemoryModuleType.c_3005_b);
    }

    @Override
    protected void n_1700_B(e_3591_l worldIn, r_4811_B entityIn) {
        entityIn.y_1945_D().n_1700_B(MemoryModuleType.c_3005_b, this.n_1700_B(entityIn));
    }

    private Optional<r_4811_B> n_1700_B(r_4811_B livingEntity) {
        return this.J_1907_R(livingEntity).flatMap(entities -> entities.stream().filter(this::R_4764_Y).filter(enemy -> this.J_1907_R(livingEntity, (r_4811_B)enemy)).min((enemy1, enemy2) -> this.n_1700_B(livingEntity, (r_4811_B)enemy1, (r_4811_B)enemy2)));
    }

    private Optional<List<r_4811_B>> J_1907_R(r_4811_B livingEntity) {
        return livingEntity.y_1945_D().R_4764_Y(MemoryModuleType.w_1484_f);
    }

    private int n_1700_B(r_4811_B livingEntity, r_4811_B target1, r_4811_B target2) {
        return u_530_F.R_4764_Y(target1.G_564_y((N_4263_v)livingEntity) - target2.G_564_y((N_4263_v)livingEntity));
    }

    private boolean J_1907_R(r_4811_B livingEntity, r_4811_B target) {
        float f = ((Float)n_1700_B.get(target.f_4016_n())).floatValue();
        return target.G_564_y((N_4263_v)livingEntity) <= (double)(f * f);
    }

    private boolean R_4764_Y(r_4811_B livingEntity) {
        return n_1700_B.containsKey(livingEntity.f_4016_n());
    }
}


