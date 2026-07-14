/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.function.BiPredicate;
import lightning.product.A_2352_Z;
import lightning.product.S_50_d;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.MemoryModuleType;

public class StartCelebratingIfTargetDead
extends Behavior<r_4811_B> {
    private final int n_1700_B;
    private final BiPredicate<r_4811_B, r_4811_B> R_4764_Y;

    public StartCelebratingIfTargetDead(int p_i231538_1_, BiPredicate<r_4811_B, r_4811_B> p_i231538_2_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.Q_4569_t, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.d_2461_k, (Object)((Object)S_50_d.R_4764_Y), MemoryModuleType.B_1668_F, (Object)((Object)S_50_d.J_1907_R), MemoryModuleType.g_164_R, (Object)((Object)S_50_d.R_4764_Y)));
        this.n_1700_B = p_i231538_1_;
        this.R_4764_Y = p_i231538_2_;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, r_4811_B owner) {
        return this.n_1700_B(owner).Z_2812_M();
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, r_4811_B entityIn, long gameTimeIn) {
        r_4811_B livingentity = this.n_1700_B(entityIn);
        if (this.R_4764_Y.test(entityIn, livingentity)) {
            entityIn.y_1945_D().n_1700_B(MemoryModuleType.g_164_R, true, this.n_1700_B);
        }
        entityIn.y_1945_D().n_1700_B(MemoryModuleType.B_1668_F, livingentity.b_2312_j(), this.n_1700_B);
        if (livingentity.f_4016_n() != t_5_h.g_4106_L || worldIn.H_1990_U().J_1907_R(A_2352_Z.x_607_J)) {
            entityIn.y_1945_D().J_1907_R(MemoryModuleType.Q_4569_t);
            entityIn.y_1945_D().J_1907_R(MemoryModuleType.d_2461_k);
        }
    }

    private r_4811_B n_1700_B(r_4811_B livingEntity) {
        return livingEntity.y_1945_D().R_4764_Y(MemoryModuleType.Q_4569_t).get();
    }
}


