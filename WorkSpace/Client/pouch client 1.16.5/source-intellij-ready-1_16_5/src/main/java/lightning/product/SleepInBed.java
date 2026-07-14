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
import lightning.product.J_2868_p;
import lightning.product.K_4074_S;
import lightning.product.S_50_d;
import lightning.product.a_4468_e;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.Activity;
import lightning.product.BlockTags;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class SleepInBed
extends Behavior<r_4811_B> {
    private long n_1700_B;

    public SleepInBed() {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.J_1907_R, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.e_4240_b, (Object)((Object)S_50_d.R_4764_Y)));
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, r_4811_B owner) {
        long i;
        if (owner.y_2772_m()) {
            return false;
        }
        E_4668_a<?> brain = owner.y_1945_D();
        F_427_K globalpos = brain.R_4764_Y(MemoryModuleType.J_1907_R).get();
        if (worldIn.g_2268_R() != globalpos.n_1700_B()) {
            return false;
        }
        Optional<Long> optional = brain.R_4764_Y(MemoryModuleType.e_4240_b);
        if (optional.isPresent() && (i = worldIn.X_933_l() - optional.get()) > 0L && i < 100L) {
            return false;
        }
        K_4074_S blockstate = worldIn.getBlockState(globalpos.J_1907_R());
        return globalpos.J_1907_R().withinDistance(owner.s_4990_V(), 2.0) && blockstate.J_1907_R().n_1700_B(BlockTags.d_2461_k) && blockstate.R_4764_Y(J_2868_p.h_1847_R) == false;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, r_4811_B entityIn, long gameTimeIn) {
        Optional<F_427_K> optional = entityIn.y_1945_D().R_4764_Y(MemoryModuleType.J_1907_R);
        if (!optional.isPresent()) {
            return false;
        }
        c_1514_x blockpos = optional.get().J_1907_R();
        return entityIn.y_1945_D().R_4764_Y(Activity.P_1922_E) && entityIn.X_2960_b() > (double)blockpos.getY() + 0.4 && blockpos.withinDistance(entityIn.s_4990_V(), 1.14);
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, r_4811_B entityIn, long gameTimeIn) {
        if (gameTimeIn > this.n_1700_B) {
            a_4468_e.n_1700_B(worldIn, entityIn, null, null);
            entityIn.P_1922_E(entityIn.y_1945_D().R_4764_Y(MemoryModuleType.J_1907_R).get().J_1907_R());
        }
    }

    @Override
    protected boolean n_1700_B(long gameTime) {
        return false;
    }

    @Override
    protected void J_1907_R(e_3591_l worldIn, r_4811_B entityIn, long gameTimeIn) {
        if (entityIn.z_2372_L()) {
            entityIn.t_2932_z();
            this.n_1700_B = gameTimeIn + 40L;
        }
    }
}


