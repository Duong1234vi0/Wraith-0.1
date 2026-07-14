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
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.PositionTracker;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class SetWalkTargetFromLookTarget
extends Behavior<r_4811_B> {
    private final float n_1700_B;
    private final int R_4764_Y;

    public SetWalkTargetFromLookTarget(float p_i50344_1_, int p_i50344_2_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.J_1907_R), MemoryModuleType.h_1847_R, (Object)((Object)S_50_d.n_1700_B)));
        this.n_1700_B = p_i50344_1_;
        this.R_4764_Y = p_i50344_2_;
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, r_4811_B entityIn, long gameTimeIn) {
        E_4668_a<?> brain = entityIn.y_1945_D();
        PositionTracker iposwrapper = brain.R_4764_Y(MemoryModuleType.h_1847_R).get();
        brain.n_1700_B(MemoryModuleType.P_4830_p, new WalkTarget(iposwrapper, this.n_1700_B, this.R_4764_Y));
    }
}


