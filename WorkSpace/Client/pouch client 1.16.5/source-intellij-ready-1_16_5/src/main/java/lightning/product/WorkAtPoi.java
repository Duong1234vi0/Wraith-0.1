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
import lightning.product.L_2225_p;
import lightning.product.S_50_d;
import lightning.product.Z_148_A;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class WorkAtPoi
extends Behavior<L_2225_p> {
    private long n_1700_B;

    public WorkAtPoi() {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.R_4764_Y, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.h_1847_R, (Object)((Object)S_50_d.R_4764_Y)));
    }

    protected boolean J_1907_R(e_3591_l worldIn, L_2225_p owner) {
        if (worldIn.X_933_l() - this.n_1700_B < 300L) {
            return false;
        }
        if (worldIn.w_1457_N.nextInt(2) != 0) {
            return false;
        }
        this.n_1700_B = worldIn.X_933_l();
        F_427_K globalpos = owner.y_1945_D().R_4764_Y(MemoryModuleType.R_4764_Y).get();
        return globalpos.n_1700_B() == worldIn.g_2268_R() && globalpos.J_1907_R().withinDistance(owner.s_4990_V(), 1.73);
    }

    protected void n_1700_B(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        E_4668_a<L_2225_p> brain = entityIn.y_1945_D();
        brain.n_1700_B(MemoryModuleType.n_3318_d, Long.valueOf(gameTimeIn));
        brain.R_4764_Y(MemoryModuleType.R_4764_Y).ifPresent(p_225460_1_ -> brain.n_1700_B(MemoryModuleType.h_1847_R, new Z_148_A(p_225460_1_.J_1907_R())));
        entityIn.V_537_k();
        this.n_1700_B(worldIn, entityIn);
        if (entityIn.U_1697_c()) {
            entityIn.P_2295_B();
        }
    }

    protected void n_1700_B(e_3591_l world, L_2225_p villager) {
    }

    protected boolean J_1907_R(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        Optional<F_427_K> optional = entityIn.y_1945_D().R_4764_Y(MemoryModuleType.R_4764_Y);
        if (!optional.isPresent()) {
            return false;
        }
        F_427_K globalpos = optional.get();
        return globalpos.n_1700_B() == worldIn.g_2268_R() && globalpos.J_1907_R().withinDistance(entityIn.s_4990_V(), 1.73);
    }

    @Override
    protected /* synthetic */ boolean n_1700_B(e_3591_l e_3591_l2, r_4811_B r_4811_B2) {
        return this.J_1907_R(e_3591_l2, (L_2225_p)r_4811_B2);
    }

    @Override
    protected /* synthetic */ boolean n_1700_B(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        return this.J_1907_R(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }
}


