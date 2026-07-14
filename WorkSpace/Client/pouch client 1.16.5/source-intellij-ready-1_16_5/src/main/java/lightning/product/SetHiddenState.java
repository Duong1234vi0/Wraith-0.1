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
import lightning.product.S_50_d;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class SetHiddenState
extends Behavior<r_4811_B> {
    private final int n_1700_B;
    private final int R_4764_Y;
    private int G_564_y;

    public SetHiddenState(int p_i50349_1_, int hidingDistance) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.H_2857_Y, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.A_4115_X, (Object)((Object)S_50_d.n_1700_B)));
        this.R_4764_Y = p_i50349_1_ * 20;
        this.G_564_y = 0;
        this.n_1700_B = hidingDistance;
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, r_4811_B entityIn, long gameTimeIn) {
        boolean flag;
        E_4668_a<?> brain = entityIn.y_1945_D();
        Optional<Long> optional = brain.R_4764_Y(MemoryModuleType.A_4115_X);
        boolean bl = flag = optional.get() + 300L <= gameTimeIn;
        if (this.G_564_y <= this.R_4764_Y && !flag) {
            c_1514_x blockpos = brain.R_4764_Y(MemoryModuleType.H_2857_Y).get().J_1907_R();
            if (blockpos.withinDistance(entityIn.b_2312_j(), (double)this.n_1700_B)) {
                ++this.G_564_y;
            }
        } else {
            brain.J_1907_R(MemoryModuleType.A_4115_X);
            brain.J_1907_R(MemoryModuleType.H_2857_Y);
            brain.n_1700_B(worldIn.Z_976_R(), worldIn.X_933_l());
            this.G_564_y = 0;
        }
    }
}


