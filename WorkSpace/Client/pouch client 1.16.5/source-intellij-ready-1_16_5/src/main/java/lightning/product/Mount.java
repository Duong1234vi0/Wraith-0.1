/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lightning.product.N_4263_v;
import lightning.product.S_50_d;
import lightning.product.a_3236_r;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class Mount<E extends r_4811_B>
extends Behavior<E> {
    private final float n_1700_B;

    public Mount(float p_i231524_1_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.h_1847_R, (Object)((Object)S_50_d.R_4764_Y), MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.J_1907_R), MemoryModuleType.w_1457_N, (Object)((Object)S_50_d.n_1700_B)));
        this.n_1700_B = p_i231524_1_;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, E owner) {
        return !((N_4263_v)owner).y_2772_m();
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        if (this.n_1700_B(entityIn)) {
            ((N_4263_v)entityIn).s_956_w(this.J_1907_R(entityIn));
        } else {
            a_3236_r.n_1700_B(entityIn, this.J_1907_R(entityIn), this.n_1700_B, 1);
        }
    }

    private boolean n_1700_B(E p_233925_1_) {
        return this.J_1907_R(p_233925_1_).n_1700_B((N_4263_v)p_233925_1_, 1.0);
    }

    private N_4263_v J_1907_R(E p_233926_1_) {
        return ((r_4811_B)p_233926_1_).y_1945_D().R_4764_Y(MemoryModuleType.w_1457_N).get();
    }
}


