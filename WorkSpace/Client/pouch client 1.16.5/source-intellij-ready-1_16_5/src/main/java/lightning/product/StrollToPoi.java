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
import lightning.product.S_50_d;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.PathfinderMob;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class StrollToPoi
extends Behavior<PathfinderMob> {
    private final MemoryModuleType<F_427_K> n_1700_B;
    private final int R_4764_Y;
    private final int G_564_y;
    private final float P_1922_E;
    private long u_1723_Y;

    public StrollToPoi(MemoryModuleType<F_427_K> p_i241910_1_, float p_i241910_2_, int p_i241910_3_, int p_i241910_4_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.R_4764_Y), p_i241910_1_, (Object)((Object)S_50_d.n_1700_B)));
        this.n_1700_B = p_i241910_1_;
        this.P_1922_E = p_i241910_2_;
        this.R_4764_Y = p_i241910_3_;
        this.G_564_y = p_i241910_4_;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, PathfinderMob owner) {
        Optional<F_427_K> optional = owner.y_1945_D().R_4764_Y(this.n_1700_B);
        return optional.isPresent() && worldIn.g_2268_R() == optional.get().n_1700_B() && optional.get().J_1907_R().withinDistance(owner.s_4990_V(), (double)this.G_564_y);
    }

    protected void n_1700_B(e_3591_l worldIn, PathfinderMob entityIn, long gameTimeIn) {
        if (gameTimeIn > this.u_1723_Y) {
            E_4668_a<?> brain = entityIn.y_1945_D();
            Optional<F_427_K> optional = brain.R_4764_Y(this.n_1700_B);
            optional.ifPresent(p_220580_2_ -> brain.n_1700_B(MemoryModuleType.P_4830_p, new WalkTarget(p_220580_2_.J_1907_R(), this.P_1922_E, this.R_4764_Y)));
            this.u_1723_Y = gameTimeIn + 80L;
        }
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (PathfinderMob)r_4811_B2, l);
    }
}


