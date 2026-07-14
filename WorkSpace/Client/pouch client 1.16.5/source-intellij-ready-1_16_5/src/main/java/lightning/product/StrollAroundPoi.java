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
import lightning.product.F_427_K;
import lightning.product.WalkTarget;
import lightning.product.S_50_d;
import lightning.product.W_3371_U;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.PathfinderMob;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class StrollAroundPoi
extends Behavior<PathfinderMob> {
    private final MemoryModuleType<F_427_K> n_1700_B;
    private long R_4764_Y;
    private final int G_564_y;
    private float P_1922_E;

    public StrollAroundPoi(MemoryModuleType<F_427_K> p_i241909_1_, float p_i241909_2_, int p_i241909_3_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.R_4764_Y), p_i241909_1_, (Object)((Object)S_50_d.n_1700_B)));
        this.n_1700_B = p_i241909_1_;
        this.P_1922_E = p_i241909_2_;
        this.G_564_y = p_i241909_3_;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, PathfinderMob owner) {
        Optional<F_427_K> optional = owner.y_1945_D().R_4764_Y(this.n_1700_B);
        return optional.isPresent() && worldIn.g_2268_R() == optional.get().n_1700_B() && optional.get().J_1907_R().withinDistance(owner.s_4990_V(), (double)this.G_564_y);
    }

    protected void n_1700_B(e_3591_l worldIn, PathfinderMob entityIn, long gameTimeIn) {
        if (gameTimeIn > this.R_4764_Y) {
            Optional<e_2866_D> optional = Optional.ofNullable(W_3371_U.J_1907_R(entityIn, 8, 6));
            entityIn.y_1945_D().n_1700_B(MemoryModuleType.P_4830_p, optional.map(p_220564_1_ -> new WalkTarget((e_2866_D)p_220564_1_, this.P_1922_E, 1)));
            this.R_4764_Y = gameTimeIn + 180L;
        }
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (PathfinderMob)r_4811_B2, l);
    }
}


