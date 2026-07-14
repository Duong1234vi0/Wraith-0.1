/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Random;
import lightning.product.S_50_d;
import lightning.product.Z_530_i;
import lightning.product.a_3236_r;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class GoToCelebrateLocation<E extends Z_530_i>
extends Behavior<E> {
    private final int n_1700_B;
    private final float R_4764_Y;

    public GoToCelebrateLocation(int p_i231518_1_, float p_i231518_2_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.B_1668_F, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.Q_4569_t, (Object)((Object)S_50_d.J_1907_R), MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.J_1907_R), MemoryModuleType.h_1847_R, (Object)((Object)S_50_d.R_4764_Y)));
        this.n_1700_B = p_i231518_1_;
        this.R_4764_Y = p_i231518_2_;
    }

    protected void n_1700_B(e_3591_l worldIn, Z_530_i entityIn, long gameTimeIn) {
        c_1514_x blockpos = GoToCelebrateLocation.n_1700_B(entityIn);
        boolean flag = blockpos.withinDistance(entityIn.b_2312_j(), (double)this.n_1700_B);
        if (!flag) {
            a_3236_r.n_1700_B((r_4811_B)entityIn, GoToCelebrateLocation.n_1700_B(entityIn, blockpos), this.R_4764_Y, this.n_1700_B);
        }
    }

    private static c_1514_x n_1700_B(Z_530_i p_233900_0_, c_1514_x p_233900_1_) {
        Random random = p_233900_0_.O_508_d.w_1457_N;
        return p_233900_1_.add(GoToCelebrateLocation.n_1700_B(random), 0, GoToCelebrateLocation.n_1700_B(random));
    }

    private static int n_1700_B(Random p_233901_0_) {
        return p_233901_0_.nextInt(3) - 1;
    }

    private static c_1514_x n_1700_B(Z_530_i p_233899_0_) {
        return p_233899_0_.y_1945_D().R_4764_Y(MemoryModuleType.B_1668_F).get();
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (Z_530_i)r_4811_B2, l);
    }
}


