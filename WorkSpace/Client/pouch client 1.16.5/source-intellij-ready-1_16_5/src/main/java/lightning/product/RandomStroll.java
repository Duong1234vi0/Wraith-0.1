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
import lightning.product.WalkTarget;
import lightning.product.S_50_d;
import lightning.product.W_3371_U;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.PathfinderMob;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class RandomStroll
extends Behavior<PathfinderMob> {
    private final float n_1700_B;
    private final int R_4764_Y;
    private final int G_564_y;

    public RandomStroll(float p_i231526_1_) {
        this(p_i231526_1_, 10, 7);
    }

    public RandomStroll(float p_i231527_1_, int p_i231527_2_, int p_i231527_3_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.J_1907_R)));
        this.n_1700_B = p_i231527_1_;
        this.R_4764_Y = p_i231527_2_;
        this.G_564_y = p_i231527_3_;
    }

    protected void n_1700_B(e_3591_l worldIn, PathfinderMob entityIn, long gameTimeIn) {
        Optional<e_2866_D> optional = Optional.ofNullable(W_3371_U.J_1907_R(entityIn, this.R_4764_Y, this.G_564_y));
        entityIn.y_1945_D().n_1700_B(MemoryModuleType.P_4830_p, optional.map(p_233939_1_ -> new WalkTarget((e_2866_D)p_233939_1_, this.n_1700_B, 0)));
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (PathfinderMob)r_4811_B2, l);
    }
}


